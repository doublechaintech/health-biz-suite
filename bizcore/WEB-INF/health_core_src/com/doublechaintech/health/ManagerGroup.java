package com.doublechaintech.health;


import com.doublechaintech.health.platform.PlatformManager;

import com.doublechaintech.health.province.ProvinceManager;

import com.doublechaintech.health.city.CityManager;

import com.doublechaintech.health.district.DistrictManager;

import com.doublechaintech.health.location.LocationManager;

import com.doublechaintech.health.schoolclass.SchoolClassManager;

import com.doublechaintech.health.teacher.TeacherManager;

import com.doublechaintech.health.guardian.GuardianManager;

import com.doublechaintech.health.question.QuestionManager;

import com.doublechaintech.health.questiontype.QuestionTypeManager;

import com.doublechaintech.health.questionsource.QuestionSourceManager;

import com.doublechaintech.health.classquestion.ClassQuestionManager;

import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyManager;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionManager;

import com.doublechaintech.health.student.StudentManager;

import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyManager;

import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerManager;

import com.doublechaintech.health.surveystatus.SurveyStatusManager;

import com.doublechaintech.health.wechatuser.WechatUserManager;

import com.doublechaintech.health.usertype.UserTypeManager;

import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoManager;

import com.doublechaintech.health.changerequest.ChangeRequestManager;

import com.doublechaintech.health.changerequesttype.ChangeRequestTypeManager;

import com.doublechaintech.health.userdomain.UserDomainManager;

import com.doublechaintech.health.userwhitelist.UserWhiteListManager;

import com.doublechaintech.health.secuser.SecUserManager;

import com.doublechaintech.health.userapp.UserAppManager;

import com.doublechaintech.health.quicklink.QuickLinkManager;

import com.doublechaintech.health.listaccess.ListAccessManager;

import com.doublechaintech.health.objectaccess.ObjectAccessManager;

import com.doublechaintech.health.loginhistory.LoginHistoryManager;

import com.doublechaintech.health.genericform.GenericFormManager;

import com.doublechaintech.health.formmessage.FormMessageManager;

import com.doublechaintech.health.formfieldmessage.FormFieldMessageManager;

import com.doublechaintech.health.formfield.FormFieldManager;

import com.doublechaintech.health.formaction.FormActionManager;

import com.doublechaintech.health.candidatecontainer.CandidateContainerManager;

import com.doublechaintech.health.candidateelement.CandidateElementManager;

import com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentifyManager;

import com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentifyManager;


public class ManagerGroup {

	protected PlatformManager platformManager;

	protected ProvinceManager provinceManager;

	protected CityManager cityManager;

	protected DistrictManager districtManager;

	protected LocationManager locationManager;

	protected SchoolClassManager schoolClassManager;

	protected TeacherManager teacherManager;

	protected GuardianManager guardianManager;

	protected QuestionManager questionManager;

	protected QuestionTypeManager questionTypeManager;

	protected QuestionSourceManager questionSourceManager;

	protected ClassQuestionManager classQuestionManager;

	protected ClassDailyHealthSurveyManager classDailyHealthSurveyManager;

	protected DailySurveyQuestionManager dailySurveyQuestionManager;

	protected StudentManager studentManager;

	protected StudentHealthSurveyManager studentHealthSurveyManager;

	protected StudentDailyAnswerManager studentDailyAnswerManager;

	protected SurveyStatusManager surveyStatusManager;

	protected WechatUserManager wechatUserManager;

	protected UserTypeManager userTypeManager;

	protected WechatLoginInfoManager wechatLoginInfoManager;

	protected ChangeRequestManager changeRequestManager;

	protected ChangeRequestTypeManager changeRequestTypeManager;

	protected UserDomainManager userDomainManager;

	protected UserWhiteListManager userWhiteListManager;

	protected SecUserManager secUserManager;

	protected UserAppManager userAppManager;

	protected QuickLinkManager quickLinkManager;

	protected ListAccessManager listAccessManager;

	protected ObjectAccessManager objectAccessManager;

	protected LoginHistoryManager loginHistoryManager;

	protected GenericFormManager genericFormManager;

	protected FormMessageManager formMessageManager;

	protected FormFieldMessageManager formFieldMessageManager;

	protected FormFieldManager formFieldManager;

	protected FormActionManager formActionManager;

	protected CandidateContainerManager candidateContainerManager;

	protected CandidateElementManager candidateElementManager;

	protected WechatWorkappIdentifyManager wechatWorkappIdentifyManager;

	protected WechatMiniappIdentifyManager wechatMiniappIdentifyManager;

	

	public PlatformManager getPlatformManager(){
		return this.platformManager;
	}
	public void setPlatformManager(PlatformManager manager){
		this.platformManager = manager;
	}


	public ProvinceManager getProvinceManager(){
		return this.provinceManager;
	}
	public void setProvinceManager(ProvinceManager manager){
		this.provinceManager = manager;
	}


	public CityManager getCityManager(){
		return this.cityManager;
	}
	public void setCityManager(CityManager manager){
		this.cityManager = manager;
	}


	public DistrictManager getDistrictManager(){
		return this.districtManager;
	}
	public void setDistrictManager(DistrictManager manager){
		this.districtManager = manager;
	}


	public LocationManager getLocationManager(){
		return this.locationManager;
	}
	public void setLocationManager(LocationManager manager){
		this.locationManager = manager;
	}


	public SchoolClassManager getSchoolClassManager(){
		return this.schoolClassManager;
	}
	public void setSchoolClassManager(SchoolClassManager manager){
		this.schoolClassManager = manager;
	}


	public TeacherManager getTeacherManager(){
		return this.teacherManager;
	}
	public void setTeacherManager(TeacherManager manager){
		this.teacherManager = manager;
	}


	public GuardianManager getGuardianManager(){
		return this.guardianManager;
	}
	public void setGuardianManager(GuardianManager manager){
		this.guardianManager = manager;
	}


	public QuestionManager getQuestionManager(){
		return this.questionManager;
	}
	public void setQuestionManager(QuestionManager manager){
		this.questionManager = manager;
	}


	public QuestionTypeManager getQuestionTypeManager(){
		return this.questionTypeManager;
	}
	public void setQuestionTypeManager(QuestionTypeManager manager){
		this.questionTypeManager = manager;
	}


	public QuestionSourceManager getQuestionSourceManager(){
		return this.questionSourceManager;
	}
	public void setQuestionSourceManager(QuestionSourceManager manager){
		this.questionSourceManager = manager;
	}


	public ClassQuestionManager getClassQuestionManager(){
		return this.classQuestionManager;
	}
	public void setClassQuestionManager(ClassQuestionManager manager){
		this.classQuestionManager = manager;
	}


	public ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(){
		return this.classDailyHealthSurveyManager;
	}
	public void setClassDailyHealthSurveyManager(ClassDailyHealthSurveyManager manager){
		this.classDailyHealthSurveyManager = manager;
	}


	public DailySurveyQuestionManager getDailySurveyQuestionManager(){
		return this.dailySurveyQuestionManager;
	}
	public void setDailySurveyQuestionManager(DailySurveyQuestionManager manager){
		this.dailySurveyQuestionManager = manager;
	}


	public StudentManager getStudentManager(){
		return this.studentManager;
	}
	public void setStudentManager(StudentManager manager){
		this.studentManager = manager;
	}


	public StudentHealthSurveyManager getStudentHealthSurveyManager(){
		return this.studentHealthSurveyManager;
	}
	public void setStudentHealthSurveyManager(StudentHealthSurveyManager manager){
		this.studentHealthSurveyManager = manager;
	}


	public StudentDailyAnswerManager getStudentDailyAnswerManager(){
		return this.studentDailyAnswerManager;
	}
	public void setStudentDailyAnswerManager(StudentDailyAnswerManager manager){
		this.studentDailyAnswerManager = manager;
	}


	public SurveyStatusManager getSurveyStatusManager(){
		return this.surveyStatusManager;
	}
	public void setSurveyStatusManager(SurveyStatusManager manager){
		this.surveyStatusManager = manager;
	}


	public WechatUserManager getWechatUserManager(){
		return this.wechatUserManager;
	}
	public void setWechatUserManager(WechatUserManager manager){
		this.wechatUserManager = manager;
	}


	public UserTypeManager getUserTypeManager(){
		return this.userTypeManager;
	}
	public void setUserTypeManager(UserTypeManager manager){
		this.userTypeManager = manager;
	}


	public WechatLoginInfoManager getWechatLoginInfoManager(){
		return this.wechatLoginInfoManager;
	}
	public void setWechatLoginInfoManager(WechatLoginInfoManager manager){
		this.wechatLoginInfoManager = manager;
	}


	public ChangeRequestManager getChangeRequestManager(){
		return this.changeRequestManager;
	}
	public void setChangeRequestManager(ChangeRequestManager manager){
		this.changeRequestManager = manager;
	}


	public ChangeRequestTypeManager getChangeRequestTypeManager(){
		return this.changeRequestTypeManager;
	}
	public void setChangeRequestTypeManager(ChangeRequestTypeManager manager){
		this.changeRequestTypeManager = manager;
	}


	public UserDomainManager getUserDomainManager(){
		return this.userDomainManager;
	}
	public void setUserDomainManager(UserDomainManager manager){
		this.userDomainManager = manager;
	}


	public UserWhiteListManager getUserWhiteListManager(){
		return this.userWhiteListManager;
	}
	public void setUserWhiteListManager(UserWhiteListManager manager){
		this.userWhiteListManager = manager;
	}


	public SecUserManager getSecUserManager(){
		return this.secUserManager;
	}
	public void setSecUserManager(SecUserManager manager){
		this.secUserManager = manager;
	}


	public UserAppManager getUserAppManager(){
		return this.userAppManager;
	}
	public void setUserAppManager(UserAppManager manager){
		this.userAppManager = manager;
	}


	public QuickLinkManager getQuickLinkManager(){
		return this.quickLinkManager;
	}
	public void setQuickLinkManager(QuickLinkManager manager){
		this.quickLinkManager = manager;
	}


	public ListAccessManager getListAccessManager(){
		return this.listAccessManager;
	}
	public void setListAccessManager(ListAccessManager manager){
		this.listAccessManager = manager;
	}


	public ObjectAccessManager getObjectAccessManager(){
		return this.objectAccessManager;
	}
	public void setObjectAccessManager(ObjectAccessManager manager){
		this.objectAccessManager = manager;
	}


	public LoginHistoryManager getLoginHistoryManager(){
		return this.loginHistoryManager;
	}
	public void setLoginHistoryManager(LoginHistoryManager manager){
		this.loginHistoryManager = manager;
	}


	public GenericFormManager getGenericFormManager(){
		return this.genericFormManager;
	}
	public void setGenericFormManager(GenericFormManager manager){
		this.genericFormManager = manager;
	}


	public FormMessageManager getFormMessageManager(){
		return this.formMessageManager;
	}
	public void setFormMessageManager(FormMessageManager manager){
		this.formMessageManager = manager;
	}


	public FormFieldMessageManager getFormFieldMessageManager(){
		return this.formFieldMessageManager;
	}
	public void setFormFieldMessageManager(FormFieldMessageManager manager){
		this.formFieldMessageManager = manager;
	}


	public FormFieldManager getFormFieldManager(){
		return this.formFieldManager;
	}
	public void setFormFieldManager(FormFieldManager manager){
		this.formFieldManager = manager;
	}


	public FormActionManager getFormActionManager(){
		return this.formActionManager;
	}
	public void setFormActionManager(FormActionManager manager){
		this.formActionManager = manager;
	}


	public CandidateContainerManager getCandidateContainerManager(){
		return this.candidateContainerManager;
	}
	public void setCandidateContainerManager(CandidateContainerManager manager){
		this.candidateContainerManager = manager;
	}


	public CandidateElementManager getCandidateElementManager(){
		return this.candidateElementManager;
	}
	public void setCandidateElementManager(CandidateElementManager manager){
		this.candidateElementManager = manager;
	}


	public WechatWorkappIdentifyManager getWechatWorkappIdentifyManager(){
		return this.wechatWorkappIdentifyManager;
	}
	public void setWechatWorkappIdentifyManager(WechatWorkappIdentifyManager manager){
		this.wechatWorkappIdentifyManager = manager;
	}


	public WechatMiniappIdentifyManager getWechatMiniappIdentifyManager(){
		return this.wechatMiniappIdentifyManager;
	}
	public void setWechatMiniappIdentifyManager(WechatMiniappIdentifyManager manager){
		this.wechatMiniappIdentifyManager = manager;
	}


}






