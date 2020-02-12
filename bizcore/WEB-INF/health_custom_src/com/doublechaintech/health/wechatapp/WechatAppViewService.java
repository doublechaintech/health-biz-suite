package com.doublechaintech.health.wechatapp;

import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.BaseViewPage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.doublechaintech.health.teacher.TeacherFormProcessor;


/**
 * 此类负责：所有的页面入口。 以及页面的组装
 * @author clariones
 *
 */
public abstract class WechatAppViewService extends BaseWechatAppViewService{
	// 查看首页(view homepage)
	public Object viewHomepage(HealthUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("viewHomepage", false);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		getCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "viewHomepage", "查看首页", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestViewHomepage(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerHomePage(ctx, "viewHomepage");
		page.setLinkToUrl(accessUrl);
		return page.doRender(ctx);
	}
	
	// 默认的客户端登录接口(client login)
	public Object clientLogin(HealthUserContext userContext, com.terapico.caf.baseelement.LoginParam loginParam) throws Exception {
		String accessUrl = makeUrlF("clientLogin", false, loginParam);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		getCurrentUserInfo(ctx);
		ctx.setLoginParam(loginParam);
		commonLog(ctx, "clientLogin", "默认的客户端登录接口", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, loginParam), null);
		int resultCode = processRequestClientLogin(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerMePage(ctx, "clientLogin");
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(update profile)
	public Object customerUpdateProfile(HealthUserContext userContext, String name , String avatar , String userType) throws Exception {
		String accessUrl = makeUrlF("customerUpdateProfile", false, name , avatar , userType);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setName(name);
		ctx.setAvatar(avatar);
		ctx.setUserType(userType);
		commonLog(ctx, "customerUpdateProfile", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, name , avatar , userType), null);
		int resultCode = processRequestCustomerUpdateProfile(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerMePage(ctx, "customerUpdateProfile");
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(switch to teacher)
	public Object customerSwitchToTeacher(HealthUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("customerSwitchToTeacher", false);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "customerSwitchToTeacher", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestCustomerSwitchToTeacher(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = null;
		switch(resultCode){
			case PRC_EMPTY_CLASS:{
				// 
				page = assemblerAddClassPage(ctx, "customerSwitchToTeacher");
				break;
			}
			case PRC_BY_DEFAULT: {
				page = assemblerMePage(ctx, "customerSwitchToTeacher");
				break;
			}
			default: {
				throw new Exception("未定义的分支代码"+resultCode);
			}
		}
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(add class)
	public Object customerAddClass(HealthUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("customerAddClass", false);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "customerAddClass", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestCustomerAddClass(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerAddClassPage(ctx, "customerAddClass");
		return page.doRender(ctx);
	}
	
	// 创建班级(submit class)
	public Object customerSubmitClass(HealthUserContext userContext, String formData) throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		if (hasFormResubmitFlag(ctx)) {
			throwsExceptionWithMessage(ctx, "请不要重复提交");
		}
		try{
			String accessUrl = makeUrlF("customerSubmitClass", false, "formData");
			ctx.setAccessUrl(accessUrl);
			ensureCurrentUserInfo(ctx);
			TeacherFormProcessor form = new TeacherFormProcessor().initByRequest(ctx, formData);
			form.verifyInputs();
			ctx.setInputFormData(form);
			commonLog(ctx, "customerSubmitClass", "创建班级", ctx.getRemoteIP(), ctx.tokenId(), formData, null);
			int resultCode = processRequestCustomerSubmitClass(ctx);
			if (returnRightNow(resultCode)){
				return ctx.getResultObject();
			}
			BaseViewPage page = assemblerMePage(ctx, "customerSubmitClass");
			return page.doRender(ctx);
		}finally {
			ctx.clearFormResubmitFlag();
		}
	}
	
	// 这个程序员很懒,什么也没留下(view class detail)
	public Object customerViewClassDetail(HealthUserContext userContext, String teacherId) throws Exception {
		String accessUrl = makeUrlF("customerViewClassDetail", false, teacherId);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setTeacherId(teacherId);
		commonLog(ctx, "customerViewClassDetail", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, teacherId), null);
		int resultCode = processRequestCustomerViewClassDetail(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerSurveyListPage(ctx, "customerViewClassDetail");
		return page.doRender(ctx);
	}
	
	// 问卷详情(view survey detail)
	public Object customerViewSurveyDetail(HealthUserContext userContext, String surveyId) throws Exception {
		String accessUrl = makeUrlF("customerViewSurveyDetail", false, surveyId);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setSurveyId(surveyId);
		commonLog(ctx, "customerViewSurveyDetail", "问卷详情", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, surveyId), null);
		int resultCode = processRequestCustomerViewSurveyDetail(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerSurveyDetailPage(ctx, "customerViewSurveyDetail");
		return page.doRender(ctx);
	}
	
	// 添加问卷(add survey)
	public Object customerAddSurvey(HealthUserContext userContext, String teacherId) throws Exception {
		String accessUrl = makeUrlF("customerAddSurvey", false, teacherId);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setTeacherId(teacherId);
		commonLog(ctx, "customerAddSurvey", "添加问卷", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, teacherId), null);
		int resultCode = processRequestCustomerAddSurvey(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerAddSurveyPage(ctx, "customerAddSurvey");
		return page.doRender(ctx);
	}
	
	// 发布问卷(publish survey)
	public Object customerPublishSurvey(HealthUserContext userContext, String teacherId , String surveyDate) throws Exception {
		String accessUrl = makeUrlF("customerPublishSurvey", false, teacherId , surveyDate);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setTeacherId(teacherId);
		ctx.setSurveyDate(surveyDate);
		commonLog(ctx, "customerPublishSurvey", "发布问卷", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, teacherId , surveyDate), null);
		int resultCode = processRequestCustomerPublishSurvey(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerSurveyDetailPage(ctx, "customerPublishSurvey");
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(switch to student)
	public Object customerSwitchToStudent(HealthUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("customerSwitchToStudent", false);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "customerSwitchToStudent", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestCustomerSwitchToStudent(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerStudentSurveyListPage(ctx, "customerSwitchToStudent");
		return page.doRender(ctx);
	}
	
	// 学生登录后查看问卷(student view survey)
	public Object customerStudentViewSurvey(HealthUserContext userContext, String surveyId) throws Exception {
		String accessUrl = makeUrlF("customerStudentViewSurvey", false, surveyId);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setSurveyId(surveyId);
		commonLog(ctx, "customerStudentViewSurvey", "学生登录后查看问卷", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, surveyId), null);
		int resultCode = processRequestCustomerStudentViewSurvey(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = null;
		switch(resultCode){
			case PRC_SUBMITTED:{
				// 
				page = assemblerStudentSurveyListPage(ctx, "customerStudentViewSurvey");
				break;
			}
			case PRC_BY_DEFAULT: {
				page = assemblerStudentSurveyFormPage(ctx, "customerStudentViewSurvey");
				break;
			}
			default: {
				throw new Exception("未定义的分支代码"+resultCode);
			}
		}
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(view student survey detail)
	public Object customerViewStudentSurveyDetail(HealthUserContext userContext, String studentSurveyId) throws Exception {
		String accessUrl = makeUrlF("customerViewStudentSurveyDetail", false, studentSurveyId);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setStudentSurveyId(studentSurveyId);
		commonLog(ctx, "customerViewStudentSurveyDetail", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, studentSurveyId), null);
		int resultCode = processRequestCustomerViewStudentSurveyDetail(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerStudentSurveyDetailPage(ctx, "customerViewStudentSurveyDetail");
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(submit student survey)
	public Object customerSubmitStudentSurvey(HealthUserContext userContext, String surveyId , String answer , String studentName) throws Exception {
		String accessUrl = makeUrlF("customerSubmitStudentSurvey", false, surveyId , answer , studentName);
		
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setSurveyId(surveyId);
		ctx.setAnswer(answer);
		ctx.setStudentName(studentName);
		commonLog(ctx, "customerSubmitStudentSurvey", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, surveyId , answer , studentName), null);
		int resultCode = processRequestCustomerSubmitStudentSurvey(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerStudentSurveyListPage(ctx, "customerSubmitStudentSurvey");
		return page.doRender(ctx);
	}
	
}

