package com.doublechaintech.health;
import com.terapico.caf.DateTime;
import com.terapico.uccaf.BaseUserContext;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class HealthCheckerManager extends BaseManagerImpl {
	public SmartList<BaseEntity> requestCandidateValuesForSearch(HealthUserContext ctx, String ownerMemberName,
			String ownerId, String resultMemberName, String resutlClassName, String targetClassName, String filterKey, int pageNo) {
		return ((BaseDAO)daoOf(ctx)).requestCandidateValuesForSearch(ownerMemberName, ownerId, resultMemberName,
				resutlClassName, targetClassName, filterKey, pageNo);
	}
	
	protected Object daoOf(HealthUserContext ctx) {
		throw new UnsupportedOperationException("You must implement it in your specific Manager implementation");
	}
	
	protected HealthObjectChecker checkerOf(HealthUserContext ctx) {
		return ctx.getChecker();
	}
	private static class AsyncManagerJob extends Thread {
		protected Object me;
		protected Object proxy;
		protected Method method;
		protected Object[] args;
		protected MethodProxy methodProxy;

		public AsyncManagerJob(Object me, Object proxy, Method method, Object[] args, MethodProxy methodProxy) {
			super();
			this.me = me;
			this.proxy = proxy;
			this.method = method;
			this.args = args;
			this.methodProxy = methodProxy;
		}

		@Override
		public void run() {
			try {
				method.setAccessible(true);
				method.invoke(me, args);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final Map<String, Object> EO = new HashMap<>();
	protected Object asyncProxy = null;
	protected Object getAsyncProxy() {
		if (asyncProxy != null) {
			return asyncProxy;
		}
		
		Object me = this;
		MethodInterceptor proxy = new MethodInterceptor() {

			@Override
			public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				new AsyncManagerJob(me, proxyObj, method, args, methodProxy).start();
				return null;
			}
		};
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(me.getClass());
		enhancer.setCallback(proxy);
		return asyncProxy = enhancer.create();
	}
	
	protected void cacheVerifyCode(HealthUserContext ctx, String mobile, String verifyCode) {
		String cacheKey = "verifyCode:"+mobile;
		ctx.putToCache(cacheKey, verifyCode, HealthBaseConstants.DEFAULT_CACHE_TIME_FOR_VCODE);
	}

	protected String getVerifyCodeFromCache(HealthUserContext ctx, String mobile) {
		String cacheKey = "verifyCode:"+mobile;
		return (String) ctx.getCachedObject(cacheKey, String.class);
	}
	protected void checkVerifyCode(HealthUserContext ctx, String inputVerifyCode, String mobile) throws Exception {
		String cachedVerifyCode = getVerifyCodeFromCache(ctx, mobile);
		if (cachedVerifyCode == null) {
			throw new Exception("请先获取验证码");
		}
		if (!cachedVerifyCode.equals(inputVerifyCode)) {
			throw new Exception("验证码不正确");
		}
	}
	

	
	
	
	@Override
	protected<T extends BaseEntity> void addActions(BaseUserContext baseUserContext, T entity,
			Map<String, Object> tokens){
		
		entity.addAction(createAction("@view","view","view/"+entity.getId()+"/","main","primary"));
		this.requestTypeActions(baseUserContext, entity);
	}
	
	protected void convertToActions(BaseEntity targetObject,com.doublechaintech.health.changerequesttype.ChangeRequestType changeRequestType) {
		
		
		String []bindTypes = changeRequestType.getBindTypes().split(",");
		
		boolean matchType = Arrays.stream(bindTypes).anyMatch(type->targetObject.getInternalType().equals(type.trim()));
		if(!matchType) {
			return ;
		}
		
		
		Action action = new Action();
		
		action.asChangeRequestGroup();
		action.setActionName(changeRequestType.getName());
		action.setActionKey(changeRequestType.getCode());
		action.setActionIcon(changeRequestType.getIcon());
		
		String actionPath = String.format("/%s/%s/%s/%s/%s",
				targetObject.getInternalType(),
				targetObject.getId(),
				changeRequestType.getInternalType(),
				changeRequestType.getCode(),
				changeRequestType.getName()
				);
		
		action.setActionPath(actionPath);
		targetObject.addAction(action);
		
		
		
		
	}
	protected void requestTypeActions(BaseUserContext userContext, BaseEntity targetObject) {
		
		changeRequestTypeDaoOf((HealthUserContext)userContext)
			.loadAll()
			.forEach(crType->convertToActions(targetObject,crType));
	}


	public com.doublechaintech.health.platform.PlatformManager platformManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getPlatformManager();
	}
	public com.doublechaintech.health.platform.PlatformDAO platformDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getPlatformDAO();
	}
	public com.doublechaintech.health.province.ProvinceManager provinceManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getProvinceManager();
	}
	public com.doublechaintech.health.province.ProvinceDAO provinceDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getProvinceDAO();
	}
	public com.doublechaintech.health.city.CityManager cityManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getCityManager();
	}
	public com.doublechaintech.health.city.CityDAO cityDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getCityDAO();
	}
	public com.doublechaintech.health.district.DistrictManager districtManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getDistrictManager();
	}
	public com.doublechaintech.health.district.DistrictDAO districtDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getDistrictDAO();
	}
	public com.doublechaintech.health.location.LocationManager locationManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getLocationManager();
	}
	public com.doublechaintech.health.location.LocationDAO locationDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getLocationDAO();
	}
	public com.doublechaintech.health.teacher.TeacherManager teacherManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getTeacherManager();
	}
	public com.doublechaintech.health.teacher.TeacherDAO teacherDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getTeacherDAO();
	}
	public com.doublechaintech.health.student.StudentManager studentManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getStudentManager();
	}
	public com.doublechaintech.health.student.StudentDAO studentDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getStudentDAO();
	}
	public com.doublechaintech.health.question.QuestionManager questionManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getQuestionManager();
	}
	public com.doublechaintech.health.question.QuestionDAO questionDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getQuestionDAO();
	}
	public com.doublechaintech.health.questiontype.QuestionTypeManager questionTypeManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getQuestionTypeManager();
	}
	public com.doublechaintech.health.questiontype.QuestionTypeDAO questionTypeDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getQuestionTypeDAO();
	}
	public com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyManager classDailyHealthSurveyManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getClassDailyHealthSurveyManager();
	}
	public com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO classDailyHealthSurveyDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getClassDailyHealthSurveyDAO();
	}
	public com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionManager dailySurveyQuestionManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getDailySurveyQuestionManager();
	}
	public com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO dailySurveyQuestionDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getDailySurveyQuestionDAO();
	}
	public com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyManager studentHealthSurveyManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getStudentHealthSurveyManager();
	}
	public com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO studentHealthSurveyDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getStudentHealthSurveyDAO();
	}
	public com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerManager studentDailyAnswerManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getStudentDailyAnswerManager();
	}
	public com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO studentDailyAnswerDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getStudentDailyAnswerDAO();
	}
	public com.doublechaintech.health.surveystatus.SurveyStatusManager surveyStatusManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getSurveyStatusManager();
	}
	public com.doublechaintech.health.surveystatus.SurveyStatusDAO surveyStatusDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getSurveyStatusDAO();
	}
	public com.doublechaintech.health.healthsurveyreport.HealthSurveyReportManager healthSurveyReportManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getHealthSurveyReportManager();
	}
	public com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO healthSurveyReportDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getHealthSurveyReportDAO();
	}
	public com.doublechaintech.health.studentanswer.StudentAnswerManager studentAnswerManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getStudentAnswerManager();
	}
	public com.doublechaintech.health.studentanswer.StudentAnswerDAO studentAnswerDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getStudentAnswerDAO();
	}
	public com.doublechaintech.health.user.UserManager userManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getUserManager();
	}
	public com.doublechaintech.health.user.UserDAO userDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getUserDAO();
	}
	public com.doublechaintech.health.wechatlogininfo.WechatLoginInfoManager wechatLoginInfoManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getWechatLoginInfoManager();
	}
	public com.doublechaintech.health.wechatlogininfo.WechatLoginInfoDAO wechatLoginInfoDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getWechatLoginInfoDAO();
	}
	public com.doublechaintech.health.changerequest.ChangeRequestManager changeRequestManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getChangeRequestManager();
	}
	public com.doublechaintech.health.changerequest.ChangeRequestDAO changeRequestDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getChangeRequestDAO();
	}
	public com.doublechaintech.health.changerequesttype.ChangeRequestTypeManager changeRequestTypeManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getChangeRequestTypeManager();
	}
	public com.doublechaintech.health.changerequesttype.ChangeRequestTypeDAO changeRequestTypeDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getChangeRequestTypeDAO();
	}
	public com.doublechaintech.health.userdomain.UserDomainManager userDomainManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getUserDomainManager();
	}
	public com.doublechaintech.health.userdomain.UserDomainDAO userDomainDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getUserDomainDAO();
	}
	public com.doublechaintech.health.userwhitelist.UserWhiteListManager userWhiteListManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getUserWhiteListManager();
	}
	public com.doublechaintech.health.userwhitelist.UserWhiteListDAO userWhiteListDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getUserWhiteListDAO();
	}
	public com.doublechaintech.health.secuser.SecUserManager secUserManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getSecUserManager();
	}
	public com.doublechaintech.health.secuser.SecUserDAO secUserDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getSecUserDAO();
	}
	public com.doublechaintech.health.userapp.UserAppManager userAppManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getUserAppManager();
	}
	public com.doublechaintech.health.userapp.UserAppDAO userAppDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getUserAppDAO();
	}
	public com.doublechaintech.health.quicklink.QuickLinkManager quickLinkManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getQuickLinkManager();
	}
	public com.doublechaintech.health.quicklink.QuickLinkDAO quickLinkDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getQuickLinkDAO();
	}
	public com.doublechaintech.health.listaccess.ListAccessManager listAccessManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getListAccessManager();
	}
	public com.doublechaintech.health.listaccess.ListAccessDAO listAccessDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getListAccessDAO();
	}
	public com.doublechaintech.health.objectaccess.ObjectAccessManager objectAccessManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getObjectAccessManager();
	}
	public com.doublechaintech.health.objectaccess.ObjectAccessDAO objectAccessDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getObjectAccessDAO();
	}
	public com.doublechaintech.health.loginhistory.LoginHistoryManager loginHistoryManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getLoginHistoryManager();
	}
	public com.doublechaintech.health.loginhistory.LoginHistoryDAO loginHistoryDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getLoginHistoryDAO();
	}
	public com.doublechaintech.health.genericform.GenericFormManager genericFormManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getGenericFormManager();
	}
	public com.doublechaintech.health.genericform.GenericFormDAO genericFormDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getGenericFormDAO();
	}
	public com.doublechaintech.health.formmessage.FormMessageManager formMessageManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getFormMessageManager();
	}
	public com.doublechaintech.health.formmessage.FormMessageDAO formMessageDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getFormMessageDAO();
	}
	public com.doublechaintech.health.formfieldmessage.FormFieldMessageManager formFieldMessageManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getFormFieldMessageManager();
	}
	public com.doublechaintech.health.formfieldmessage.FormFieldMessageDAO formFieldMessageDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getFormFieldMessageDAO();
	}
	public com.doublechaintech.health.formfield.FormFieldManager formFieldManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getFormFieldManager();
	}
	public com.doublechaintech.health.formfield.FormFieldDAO formFieldDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getFormFieldDAO();
	}
	public com.doublechaintech.health.formaction.FormActionManager formActionManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getFormActionManager();
	}
	public com.doublechaintech.health.formaction.FormActionDAO formActionDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getFormActionDAO();
	}
	public com.doublechaintech.health.candidatecontainer.CandidateContainerManager candidateContainerManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getCandidateContainerManager();
	}
	public com.doublechaintech.health.candidatecontainer.CandidateContainerDAO candidateContainerDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getCandidateContainerDAO();
	}
	public com.doublechaintech.health.candidateelement.CandidateElementManager candidateElementManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getCandidateElementManager();
	}
	public com.doublechaintech.health.candidateelement.CandidateElementDAO candidateElementDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getCandidateElementDAO();
	}
	public com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentifyManager wechatWorkappIdentifyManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getWechatWorkappIdentifyManager();
	}
	public com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentifyDAO wechatWorkappIdentifyDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getWechatWorkappIdentifyDAO();
	}
	public com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentifyManager wechatMiniappIdentifyManagerOf(HealthUserContext userContext){
		return userContext.getManagerGroup().getWechatMiniappIdentifyManager();
	}
	public com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentifyDAO wechatMiniappIdentifyDaoOf(HealthUserContext userContext){
		return userContext.getDAOGroup().getWechatMiniappIdentifyDAO();
	}
	
	
	
	

	protected void checkGender(String gender, int i, int j,String targetFieldName, List<Message> messageList) {
		
		
	}
	
	//for stub only
	protected void checkDateNow(Date likeTime, int i, Object now,
			String targetFieldName, HealthException exception) {
		
		
	}


	protected Object now() {

		return null;
	}
	
	protected boolean isValidIdentifier(String value){
		return hasVisualChar(value);
		
	}
	
	protected boolean hasVisualChar(String value){
		if(value==null){
			return false;
		}
		if(value.isEmpty()){
			return false;
		}
		if(value.trim().isEmpty()){
			return false;
		}
		return true;
		
	}
	protected void checkBigDecimalRange(BigDecimal projectArea, double i, double j, String projectAreaOfProject,
			List<Message> messageList) {
		
	}
    
}








