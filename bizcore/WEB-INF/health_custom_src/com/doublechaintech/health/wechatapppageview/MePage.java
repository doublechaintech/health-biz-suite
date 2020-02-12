package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.CollectionUtils;
import com.terapico.utils.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.lucene.util.CollectionUtil;

import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.DAOGroup;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyVersionChangedException;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherTokens;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.wechatapp.DBQuery;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class MePage extends BaseViewPage {
	private static final long serialVersionUID = 1L;
	protected static final SerializeScope SCOPE = SerializeScope
			.INCLUDE()
				.field("title")
				.field("popup")
				.field("toast", SerializeScope.EXCLUDE())
				.field("refreshAction")
				.field("actions", SerializeScope.INCLUDE().field("addClass", HealthCustomConstants.ACTION_SCOPE).field("switchToTeacher", HealthCustomConstants.ACTION_SCOPE).field("switchToStudent", HealthCustomConstants.ACTION_SCOPE))
				.field("actionList")
				.field("classList",
						MiscUtils
								.buildEntityScope("id", "code", Teacher.SCHOOL_PROPERTY, Teacher.SCHOOL_CLASS_PROPERTY,
										HealthCustomConstants.LINK_TO_URL, "surveyCount", "answerCount"))
				.field("surveyList", MiscUtils
						.buildEntityScope("id", "code", "answerCount", "riskCount","teacher","schoolClass","school",
								ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY, ClassDailyHealthSurvey.NAME_PROPERTY,
								HealthCustomConstants.LINK_TO_URL, Teacher.CLASS_SIZE_PROPERTY))
				.field("classEmptyMessage")
				.field("surveyEmptyMessage")
				.field("linkToUrl")
				;

	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	@Override
	public String getPageTitle() {
		if (pageTitle == null) {
			return "me";
		}
		return pageTitle;
	}

	@Override
	protected void afterDoRendering() {
		super.afterDoRendering();
		userContext.forceResponseXClassHeader("com.terapico.appview.MePage");
	}

	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName) throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		setPageTitle("学生健康问卷调查");
		buildClassList(ctx);
		buildSurveyList(ctx);

		Map<String, Object> actions = new HashMap<>();
		actions
				.put("addClass",
						MapUtil
								.put("code", "addClass")
									.put("title", "添加班级")
									.put("icon", "add")
									.put("linkToUrl", WechatAppViewBizService.makeAddClassUrl(ctx))
									.into_map());
		
		actions
				.put("switchToTeacher",
						MapUtil
								.put("code", "switchToTeacher")
									.put("title", "我是老师")
									.put("icon", "add")
									.put("linkToUrl", WechatAppViewBizService.makeSwitchToTeacherUrl(ctx))
									.into_map());
		actions
		.put("switchToStudent",
				MapUtil
						.put("code", "switchToStudent")
							.put("title", "我是家长")
							.put("icon", "add")
							.put("linkToUrl", WechatAppViewBizService.makeSwitchToStudentUrl(ctx))
							.into_map());
		set("actions", actions);
	}

	protected void buildClassList(CustomHealthUserContextImpl ctx) throws Exception {
		User user = ctx.getCurrentUserInfo();
		DAOGroup daoGroup = ctx.getDAOGroup();
		SmartList<Teacher> teachers = daoGroup
				.getUserDAO()
					.loadOurTeacherList(ctx, CollectionUtils.toList(user), TeacherTokens.start().done());
		DBQuery dbQuery = new DBQuery();
		if (teachers != null) {
			teachers.forEach(t -> {
				t
						.addItemToValueMap(HealthCustomConstants.LINK_TO_URL,
								WechatAppViewBizService.makeViewClassDetailUrl(ctx, t.getId()));
				t.addItemToValueMap("code", t.getId());
				t
						.addItemToValueMap("surveyCount",
								daoGroup
										.getClassDailyHealthSurveyDAO()
											.countClassDailyHealthSurveyByTeacher(t.getId(),
													ClassDailyHealthSurveyTokens.empty()));
				int answerCount = 0;
				try {
					answerCount = dbQuery.queryStudentHealthSurveyListOfIsSubmittedByTeacherId(ctx, t.getId()).size();
				} catch (Exception e) {
					e.printStackTrace();
				}
				t.addItemToValueMap("answerCount", answerCount); 
			});
		}
		set("classList", teachers);
		set("classEmptyMessage", "目前没有创建任何班级");
	}

	protected void buildSurveyList(CustomHealthUserContextImpl ctx) throws Exception {
		SmartList<ClassDailyHealthSurvey> surveyList = ctx
				.getDAOGroup()
					.getClassDailyHealthSurveyDAO()
					.findClassDailyHealthSurveyByCreator(ctx.getCurrentUserInfo().getId(),
							ClassDailyHealthSurveyTokens.all());
		List<Teacher> teachers = MiscUtils.collectReferencedObjectWithType(ctx, surveyList, Teacher.class);
		ctx.getDAOGroup().getTeacherDAO().enhanceList(teachers);
		if (surveyList != null) {
			surveyList.forEach(s -> {
				s
						.addItemToValueMap(HealthCustomConstants.LINK_TO_URL,
								WechatAppViewBizService.makeViewSurveyDetailUrl(ctx, s.getId()));
				s.addItemToValueMap("code", s.getId());
				s.addItemToValueMap("classSize", s.getTeacher().getClassSize());
				s.addItemToValueMap("answerCount", ctx.getDAOGroup().getStudentHealthSurveyDAO().countStudentHealthSurveyByClassDailyHealthSurvey(s.getId(), StudentHealthSurveyTokens.all()));
				s.addItemToValueMap("riskCount", MiscUtils.getRiskCount(ctx,s));
				s.addItemToValueMap("school", s.getTeacher().getSchool());
				s.addItemToValueMap("schoolClass", s.getTeacher().getSchoolClass());
			});
		}

		set("surveyList", surveyList);
		set("surveyEmptyMessage", "目前没有创建何问卷");
	}

}
