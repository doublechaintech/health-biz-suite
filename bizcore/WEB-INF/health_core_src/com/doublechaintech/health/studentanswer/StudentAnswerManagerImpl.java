
package com.doublechaintech.health.studentanswer;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.Password;

import com.doublechaintech.health.*;
import com.doublechaintech.health.HealthUserContextImpl;
import com.doublechaintech.health.iamservice.*;
import com.doublechaintech.health.services.IamService;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.terapico.uccaf.BaseUserContext;


import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.studentdailyanswer.CandidateStudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.CandidateHealthSurveyReport;







public class StudentAnswerManagerImpl extends CustomHealthCheckerManager implements StudentAnswerManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "StudentAnswer";
	@Override
	public StudentAnswerDAO daoOf(HealthUserContext userContext) {
		return studentAnswerDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws StudentAnswerManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new StudentAnswerManagerException(message);

	}



 	protected StudentAnswer saveStudentAnswer(HealthUserContext userContext, StudentAnswer studentAnswer, String [] tokensExpr) throws Exception{	
 		//return getStudentAnswerDAO().save(studentAnswer, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveStudentAnswer(userContext, studentAnswer, tokens);
 	}
 	
 	protected StudentAnswer saveStudentAnswerDetail(HealthUserContext userContext, StudentAnswer studentAnswer) throws Exception{	

 		
 		return saveStudentAnswer(userContext, studentAnswer, allTokens());
 	}
 	
 	public StudentAnswer loadStudentAnswer(HealthUserContext userContext, String studentAnswerId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentAnswerManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		StudentAnswer studentAnswer = loadStudentAnswer( userContext, studentAnswerId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,studentAnswer, tokens);
 	}
 	
 	
 	 public StudentAnswer searchStudentAnswer(HealthUserContext userContext, String studentAnswerId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentAnswerManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		StudentAnswer studentAnswer = loadStudentAnswer( userContext, studentAnswerId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,studentAnswer, tokens);
 	}
 	
 	

 	protected StudentAnswer present(HealthUserContext userContext, StudentAnswer studentAnswer, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,studentAnswer,tokens);
		
		
		StudentAnswer  studentAnswerToPresent = studentAnswerDaoOf(userContext).present(studentAnswer, tokens);
		
		List<BaseEntity> entityListToNaming = studentAnswerToPresent.collectRefercencesFromLists();
		studentAnswerDaoOf(userContext).alias(entityListToNaming);
		
		return  studentAnswerToPresent;
		
		
	}
 
 	
 	
 	public StudentAnswer loadStudentAnswerDetail(HealthUserContext userContext, String studentAnswerId) throws Exception{	
 		StudentAnswer studentAnswer = loadStudentAnswer( userContext, studentAnswerId, allTokens());
 		return present(userContext,studentAnswer, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String studentAnswerId) throws Exception{	
 		StudentAnswer studentAnswer = loadStudentAnswer( userContext, studentAnswerId, viewTokens());
 		return present(userContext,studentAnswer, allTokens());
		
 	}
 	protected StudentAnswer saveStudentAnswer(HealthUserContext userContext, StudentAnswer studentAnswer, Map<String,Object>tokens) throws Exception{	
 		return studentAnswerDaoOf(userContext).save(studentAnswer, tokens);
 	}
 	protected StudentAnswer loadStudentAnswer(HealthUserContext userContext, String studentAnswerId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentAnswerManagerException.class);

 
 		return studentAnswerDaoOf(userContext).load(studentAnswerId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, StudentAnswer studentAnswer, Map<String, Object> tokens){
		super.addActions(userContext, studentAnswer, tokens);
		
		addAction(userContext, studentAnswer, tokens,"@create","createStudentAnswer","createStudentAnswer/","main","primary");
		addAction(userContext, studentAnswer, tokens,"@update","updateStudentAnswer","updateStudentAnswer/"+studentAnswer.getId()+"/","main","primary");
		addAction(userContext, studentAnswer, tokens,"@copy","cloneStudentAnswer","cloneStudentAnswer/"+studentAnswer.getId()+"/","main","primary");
		
		addAction(userContext, studentAnswer, tokens,"student_answer.transfer_to_health_survey_report","transferToAnotherHealthSurveyReport","transferToAnotherHealthSurveyReport/"+studentAnswer.getId()+"/","main","primary");
		addAction(userContext, studentAnswer, tokens,"student_answer.transfer_to_daily_answer","transferToAnotherDailyAnswer","transferToAnotherDailyAnswer/"+studentAnswer.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, StudentAnswer studentAnswer, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public StudentAnswer createStudentAnswer(HealthUserContext userContext, String healthSurveyReportId,String dailyAnswerId,String questionTopic,String answer) throws Exception
	//public StudentAnswer createStudentAnswer(HealthUserContext userContext,String healthSurveyReportId, String dailyAnswerId, String questionTopic, String answer) throws Exception
	{

		

		

		checkerOf(userContext).checkQuestionTopicOfStudentAnswer(questionTopic);
		checkerOf(userContext).checkAnswerOfStudentAnswer(answer);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentAnswerManagerException.class);


		StudentAnswer studentAnswer=createNewStudentAnswer();	

			
		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId,emptyOptions());
		studentAnswer.setHealthSurveyReport(healthSurveyReport);
		
		
			
		StudentDailyAnswer dailyAnswer = loadStudentDailyAnswer(userContext, dailyAnswerId,emptyOptions());
		studentAnswer.setDailyAnswer(dailyAnswer);
		
		
		studentAnswer.setQuestionTopic(questionTopic);
		studentAnswer.setAnswer(answer);

		studentAnswer = saveStudentAnswer(userContext, studentAnswer, emptyOptions());
		
		onNewInstanceCreated(userContext, studentAnswer);
		return studentAnswer;


	}
	protected StudentAnswer createNewStudentAnswer()
	{

		return new StudentAnswer();
	}

	protected void checkParamsForUpdatingStudentAnswer(HealthUserContext userContext,String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer( studentAnswerVersion);
		
		

				

		
		if(StudentAnswer.QUESTION_TOPIC_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkQuestionTopicOfStudentAnswer(parseString(newValueExpr));
		
			
		}
		if(StudentAnswer.ANSWER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerOfStudentAnswer(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentAnswerManagerException.class);


	}



	public StudentAnswer clone(HealthUserContext userContext, String fromStudentAnswerId) throws Exception{

		return studentAnswerDaoOf(userContext).clone(fromStudentAnswerId, this.allTokens());
	}

	public StudentAnswer internalSaveStudentAnswer(HealthUserContext userContext, StudentAnswer studentAnswer) throws Exception
	{
		return internalSaveStudentAnswer(userContext, studentAnswer, allTokens());

	}
	public StudentAnswer internalSaveStudentAnswer(HealthUserContext userContext, StudentAnswer studentAnswer, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingStudentAnswer(userContext, studentAnswerId, studentAnswerVersion, property, newValueExpr, tokensExpr);


		synchronized(studentAnswer){
			//will be good when the studentAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentAnswer.
			if (studentAnswer.isChanged()){
			
			}
			studentAnswer = saveStudentAnswer(userContext, studentAnswer, options);
			return studentAnswer;

		}

	}

	public StudentAnswer updateStudentAnswer(HealthUserContext userContext,String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentAnswer(userContext, studentAnswerId, studentAnswerVersion, property, newValueExpr, tokensExpr);



		StudentAnswer studentAnswer = loadStudentAnswer(userContext, studentAnswerId, allTokens());
		if(studentAnswer.getVersion() != studentAnswerVersion){
			String message = "The target version("+studentAnswer.getVersion()+") is not equals to version("+studentAnswerVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(studentAnswer){
			//will be good when the studentAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentAnswer.
			
			studentAnswer.changeProperty(property, newValueExpr);
			studentAnswer = saveStudentAnswer(userContext, studentAnswer, tokens().done());
			return present(userContext,studentAnswer, mergedAllTokens(tokensExpr));
			//return saveStudentAnswer(userContext, studentAnswer, tokens().done());
		}

	}

	public StudentAnswer updateStudentAnswerProperty(HealthUserContext userContext,String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentAnswer(userContext, studentAnswerId, studentAnswerVersion, property, newValueExpr, tokensExpr);

		StudentAnswer studentAnswer = loadStudentAnswer(userContext, studentAnswerId, allTokens());
		if(studentAnswer.getVersion() != studentAnswerVersion){
			String message = "The target version("+studentAnswer.getVersion()+") is not equals to version("+studentAnswerVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(studentAnswer){
			//will be good when the studentAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentAnswer.

			studentAnswer.changeProperty(property, newValueExpr);
			
			studentAnswer = saveStudentAnswer(userContext, studentAnswer, tokens().done());
			return present(userContext,studentAnswer, mergedAllTokens(tokensExpr));
			//return saveStudentAnswer(userContext, studentAnswer, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected StudentAnswerTokens tokens(){
		return StudentAnswerTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return StudentAnswerTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return StudentAnswerTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherHealthSurveyReport(HealthUserContext userContext, String studentAnswerId, String anotherHealthSurveyReportId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
 		checkerOf(userContext).checkIdOfHealthSurveyReport(anotherHealthSurveyReportId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentAnswerManagerException.class);

 	}
 	public StudentAnswer transferToAnotherHealthSurveyReport(HealthUserContext userContext, String studentAnswerId, String anotherHealthSurveyReportId) throws Exception
 	{
 		checkParamsForTransferingAnotherHealthSurveyReport(userContext, studentAnswerId,anotherHealthSurveyReportId);
 
		StudentAnswer studentAnswer = loadStudentAnswer(userContext, studentAnswerId, allTokens());	
		synchronized(studentAnswer){
			//will be good when the studentAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, anotherHealthSurveyReportId, emptyOptions());		
			studentAnswer.updateHealthSurveyReport(healthSurveyReport);		
			studentAnswer = saveStudentAnswer(userContext, studentAnswer, emptyOptions());
			
			return present(userContext,studentAnswer, allTokens());
			
		}

 	}

	


	public CandidateHealthSurveyReport requestCandidateHealthSurveyReport(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateHealthSurveyReport result = new CandidateHealthSurveyReport();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("surveyName");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<HealthSurveyReport> candidateList = healthSurveyReportDaoOf(userContext).requestCandidateHealthSurveyReportForStudentAnswer(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherDailyAnswer(HealthUserContext userContext, String studentAnswerId, String anotherDailyAnswerId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
 		checkerOf(userContext).checkIdOfStudentDailyAnswer(anotherDailyAnswerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentAnswerManagerException.class);

 	}
 	public StudentAnswer transferToAnotherDailyAnswer(HealthUserContext userContext, String studentAnswerId, String anotherDailyAnswerId) throws Exception
 	{
 		checkParamsForTransferingAnotherDailyAnswer(userContext, studentAnswerId,anotherDailyAnswerId);
 
		StudentAnswer studentAnswer = loadStudentAnswer(userContext, studentAnswerId, allTokens());	
		synchronized(studentAnswer){
			//will be good when the studentAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			StudentDailyAnswer dailyAnswer = loadStudentDailyAnswer(userContext, anotherDailyAnswerId, emptyOptions());		
			studentAnswer.updateDailyAnswer(dailyAnswer);		
			studentAnswer = saveStudentAnswer(userContext, studentAnswer, emptyOptions());
			
			return present(userContext,studentAnswer, allTokens());
			
		}

 	}

	


	public CandidateStudentDailyAnswer requestCandidateDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateStudentDailyAnswer result = new CandidateStudentDailyAnswer();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("studentHealthSurvey");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<StudentDailyAnswer> candidateList = studentDailyAnswerDaoOf(userContext).requestCandidateStudentDailyAnswerForStudentAnswer(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected HealthSurveyReport loadHealthSurveyReport(HealthUserContext userContext, String newHealthSurveyReportId, Map<String,Object> options) throws Exception
 	{

 		return healthSurveyReportDaoOf(userContext).load(newHealthSurveyReportId, options);
 	}
 	


	

 	protected StudentDailyAnswer loadStudentDailyAnswer(HealthUserContext userContext, String newDailyAnswerId, Map<String,Object> options) throws Exception
 	{

 		return studentDailyAnswerDaoOf(userContext).load(newDailyAnswerId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String studentAnswerId, int studentAnswerVersion) throws Exception {
		//deleteInternal(userContext, studentAnswerId, studentAnswerVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String studentAnswerId, int studentAnswerVersion) throws Exception{

		studentAnswerDaoOf(userContext).delete(studentAnswerId, studentAnswerVersion);
	}

	public StudentAnswer forgetByAll(HealthUserContext userContext, String studentAnswerId, int studentAnswerVersion) throws Exception {
		return forgetByAllInternal(userContext, studentAnswerId, studentAnswerVersion);
	}
	protected StudentAnswer forgetByAllInternal(HealthUserContext userContext,
			String studentAnswerId, int studentAnswerVersion) throws Exception{

		return studentAnswerDaoOf(userContext).disconnectFromAll(studentAnswerId, studentAnswerVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new StudentAnswerManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return studentAnswerDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(HealthUserContext userContext, StudentAnswer newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

	// -----------------------------------//  登录部分处理 \\-----------------------------------
	// 手机号+短信验证码 登录
	public Object loginByMobile(HealthUserContextImpl userContext, String mobile, String verifyCode) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByMobile");
		LoginData loginData = new LoginData();
		loginData.setMobile(mobile);
		loginData.setVerifyCode(verifyCode);

		LoginContext loginContext = LoginContext.of(LoginMethod.MOBILE, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 账号+密码登录
	public Object loginByPassword(HealthUserContextImpl userContext, String loginId, Password password) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(), "loginByPassword");
		LoginData loginData = new LoginData();
		loginData.setLoginId(loginId);
		loginData.setPassword(password.getClearTextPassword());

		LoginContext loginContext = LoginContext.of(LoginMethod.PASSWORD, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 微信小程序登录
	public Object loginByWechatMiniProgram(HealthUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 企业微信小程序登录
	public Object loginByWechatWorkMiniProgram(HealthUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatWorkMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_WORK_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 调用登录处理
	protected Object processLoginRequest(HealthUserContextImpl userContext, LoginContext loginContext) throws Exception {
		IamService iamService = (IamService) userContext.getBean("iamService");
		LoginResult loginResult = iamService.doLogin(userContext, loginContext, this);
		// 根据登录结果
		if (!loginResult.isAuthenticated()) {
			throw new Exception(loginResult.getMessage());
		}
		if (loginResult.isSuccess()) {
			return onLoginSuccess(userContext, loginResult);
		}
		if (loginResult.isNewUser()) {
			throw new Exception("请联系你的上级,先为你创建账号,然后再来登录.");
		}
		return new LoginForm();
	}
	
	@Override
	public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
			throws IllegalAccessException {
		HealthUserContextImpl userContext = (HealthUserContextImpl)baseUserContext;
		IamService iamService = (IamService) userContext.getBean("iamService");
		Map<String, Object> loginInfo = iamService.getCachedLoginInfo(userContext);
		
		SecUser secUser = iamService.tryToLoadSecUser(userContext, loginInfo);
		UserApp userApp = iamService.tryToLoadUserApp(userContext, loginInfo);
		if (userApp != null) {
			userApp.setSecUser(secUser);
		}
		afterSecUserAppLoadedWhenCheckAccess(userContext, loginInfo, secUser, userApp);
		if (!isMethodNeedLogin(userContext, methodName, parameters)) {
			return accessOK();
		}
		
		return super.checkAccess(baseUserContext, methodName, parameters);
	}
	
	// 判断哪些接口需要登录后才能执行. 默认除了loginBy开头的,其他都要登录
	protected boolean isMethodNeedLogin(HealthUserContextImpl userContext, String methodName, Object[] parameters) {
		if (methodName.startsWith("loginBy")) {
			return false;
		}
		if (methodName.startsWith("logout")) {
			return false;
		}
		return true;
	}

	// 在checkAccess中加载了secUser和userApp后会调用此方法,用于定制化的用户数据加载. 默认什么也不做
	protected void afterSecUserAppLoadedWhenCheckAccess(HealthUserContextImpl userContext, Map<String, Object> loginInfo,
			SecUser secUser, UserApp userApp) throws IllegalAccessException{
	}



	protected Object onLoginSuccess(HealthUserContext userContext, LoginResult loginResult) throws Exception {
		// by default, return the view of this object
		UserApp userApp = loginResult.getLoginContext().getLoginTarget().getUserApp();
		return this.view(userContext, userApp.getObjectId());
	}

	public void onAuthenticationFailed(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, failed is failed, nothing can do
	}
	public void onAuthenticateNewUserLogged(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, should create a account and bind with sec user, BUT, I don't know how to 
		// create new object as of generate this method. It depends on business logical. So,
		throw new Exception("请重载函数onAuthenticateNewUserLogged()以处理新用户登录");
	}
	public void onAuthenticateUserLogged(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, find the correct user-app
		SecUser secUser = loginResult.getLoginContext().getLoginTarget().getSecUser();
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
		key.put(UserApp.OBJECT_TYPE_PROPERTY, StudentAnswer.INTERNAL_TYPE);
		SmartList<UserApp> userApps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, EO);
		if (userApps == null || userApps.isEmpty()) {
			throw new Exception("您的账号未关联销售人员,请联系客服处理账号异常.");
		}
		UserApp userApp = userApps.first();
		userApp.setSecUser(secUser);
		loginResult.getLoginContext().getLoginTarget().setUserApp(userApp);
	}
	// -----------------------------------\\  登录部分处理 //-----------------------------------
}


