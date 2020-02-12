package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.MapUtil;

import java.util.HashMap;
import java.util.Map;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherTokens;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class SurveyListPage extends BaseViewPage {
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope
			.INCLUDE()
				.field("title")
				.field("popup")
				.field("toast", SerializeScope.EXCLUDE())
				.field("refreshAction")
				.field("actions", SerializeScope.EXCLUDE())
				.field("actionList")
				.field("displayMode")
				.field("emptyMessage")
				.field("list",
						MiscUtils.buildEntityScope("id","code","riskCount","school","schoolClass","answerCount","hasAbnormalSituation",ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY,ClassDailyHealthSurvey.NAME_PROPERTY,HealthCustomConstants.LINK_TO_URL,Teacher.CLASS_SIZE_PROPERTY)
						)
				.field("footerAction",HealthCustomConstants.ACTION_SCOPE)
				.field("tabs");

	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "survey list";
		}
		return pageTitle;
	}

	@Override
	protected void beforeDoRendering() {
		super.beforeDoRendering();
		this.set("displayMode", "class_daily_health_survey");
	}

	@Override
	protected void afterDoRendering() {
		super.afterDoRendering();
		forceResponseAsListOfPage();
	}

	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName) throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		Teacher teacher = ctx
				.getManagerGroup()
					.getTeacherManager()
					.loadTeacher(ctx, ctx.getTeacherId(),
							TeacherTokens.start().withClassDailyHealthSurveyList().toArray());
		SmartList<ClassDailyHealthSurvey> surveyList = teacher.getClassDailyHealthSurveyList();
		if (surveyList != null) {
			surveyList.forEach(s -> {
				s
						.addItemToValueMap(HealthCustomConstants.LINK_TO_URL,
								WechatAppViewBizService.makeViewSurveyDetailUrl(ctx, s.getId()));
				s.addItemToValueMap("code", s.getId());
				s.addItemToValueMap("classSize", teacher.getClassSize());
				s.addItemToValueMap("answerCount", ctx.getDAOGroup().getStudentHealthSurveyDAO().countStudentHealthSurveyByClassDailyHealthSurvey(s.getId(), StudentHealthSurveyTokens.all()));
				s.addItemToValueMap("riskCount", MiscUtils.getRiskCount(ctx,s));
				s.addItemToValueMap("schoolClass", teacher.getSchoolClass());
				s.addItemToValueMap("school", teacher.getSchool());
			});
		}
		set("list", surveyList);
		set(X_EMPTY_MESSAGE, "没有创建任何问卷");
		setPageTitle(teacher.getSchoolClass());
		set("title",teacher.getSchoolClass());
		Map<String, Object> actions = new HashMap<>();
		actions.put("addClass", MapUtil.put("code", "addSurvey").put("title", "创建新的问卷").put("icon", "add").put("linkToUrl", WechatAppViewBizService.makeAddSurveyUrl(ctx,ctx.getTeacherId())).into_map());
		set("footerAction",MapUtil.put("code", "addSurvey").put("title", "创建新的问卷").put("icon", "add").put("linkToUrl", WechatAppViewBizService.makeAddSurveyUrl(ctx,ctx.getTeacherId())).into_map());
		set("actions", actions);
	}
}
