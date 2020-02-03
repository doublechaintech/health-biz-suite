package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;

import java.util.Collections;
import java.util.Optional;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherTokens;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class ClassDetailPage extends BaseViewPage{
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions", SerializeScope.EXCLUDE())
			.field("actionList")
			.field("displayMode")
			.field("emptyMessage")
			.field("list")
			.field("tabs")
			;
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "class detail";
		}
		return pageTitle;
	}
	@Override
	protected void beforeDoRendering() {
		super.beforeDoRendering();
		this.set("displayMode", "survey");
	}
	@Override
	protected void afterDoRendering() {
		super.afterDoRendering();
		forceResponseAsListOfPage();
	}
	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName)throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl)userContext;
		String teacherId = ctx.getTeacherId();
		Teacher teacher = ctx.getManagerGroup().getTeacherManager().loadTeacher(ctx, teacherId, TeacherTokens.start().withClassDailyHealthSurveyList().toArray());
		SmartList<ClassDailyHealthSurvey> survey = Optional.ofNullable(teacher).map(Teacher::getClassDailyHealthSurveyList).orElse(null);
		if(survey!=null) {
			survey.forEach(s->s.addItemToValueMap(HealthCustomConstants.LINK_TO_URL, WechatAppViewBizService.makeViewSurveyDetailUrl(ctx,s.getId())));
		}
		setPageTitle("班级");
		set("list", survey);
		set(X_EMPTY_MESSAGE, "目前没有创建任何班级");
	}
}
