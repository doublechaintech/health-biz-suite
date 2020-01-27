
package com.doublechaintech.health.question;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Password;

import com.doublechaintech.health.*;
import com.doublechaintech.health.HealthUserContextImpl;
import com.doublechaintech.health.iamservice.*;
import com.doublechaintech.health.services.IamService;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.terapico.uccaf.BaseUserContext;


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.questiontype.QuestionType;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.questiontype.CandidateQuestionType;







public class QuestionManagerImpl extends CustomHealthCheckerManager implements QuestionManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Question";
	@Override
	public QuestionDAO daoOf(HealthUserContext userContext) {
		return questionDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws QuestionManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new QuestionManagerException(message);

	}



 	protected Question saveQuestion(HealthUserContext userContext, Question question, String [] tokensExpr) throws Exception{	
 		//return getQuestionDAO().save(question, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveQuestion(userContext, question, tokens);
 	}
 	
 	protected Question saveQuestionDetail(HealthUserContext userContext, Question question) throws Exception{	

 		
 		return saveQuestion(userContext, question, allTokens());
 	}
 	
 	public Question loadQuestion(HealthUserContext userContext, String questionId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Question question = loadQuestion( userContext, questionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,question, tokens);
 	}
 	
 	
 	 public Question searchQuestion(HealthUserContext userContext, String questionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Question question = loadQuestion( userContext, questionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,question, tokens);
 	}
 	
 	

 	protected Question present(HealthUserContext userContext, Question question, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,question,tokens);
		
		
		Question  questionToPresent = questionDaoOf(userContext).present(question, tokens);
		
		List<BaseEntity> entityListToNaming = questionToPresent.collectRefercencesFromLists();
		questionDaoOf(userContext).alias(entityListToNaming);
		
		return  questionToPresent;
		
		
	}
 
 	
 	
 	public Question loadQuestionDetail(HealthUserContext userContext, String questionId) throws Exception{	
 		Question question = loadQuestion( userContext, questionId, allTokens());
 		return present(userContext,question, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String questionId) throws Exception{	
 		Question question = loadQuestion( userContext, questionId, viewTokens());
 		return present(userContext,question, allTokens());
		
 	}
 	protected Question saveQuestion(HealthUserContext userContext, Question question, Map<String,Object>tokens) throws Exception{	
 		return questionDaoOf(userContext).save(question, tokens);
 	}
 	protected Question loadQuestion(HealthUserContext userContext, String questionId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionManagerException.class);

 
 		return questionDaoOf(userContext).load(questionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Question question, Map<String, Object> tokens){
		super.addActions(userContext, question, tokens);
		
		addAction(userContext, question, tokens,"@create","createQuestion","createQuestion/","main","primary");
		addAction(userContext, question, tokens,"@update","updateQuestion","updateQuestion/"+question.getId()+"/","main","primary");
		addAction(userContext, question, tokens,"@copy","cloneQuestion","cloneQuestion/"+question.getId()+"/","main","primary");
		
		addAction(userContext, question, tokens,"question.transfer_to_question_type","transferToAnotherQuestionType","transferToAnotherQuestionType/"+question.getId()+"/","main","primary");
		addAction(userContext, question, tokens,"question.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+question.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Question question, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Question createQuestion(HealthUserContext userContext, String topic,String questionTypeId,String optionA,String optionB,String optionC,String optionD,String platformId) throws Exception
	//public Question createQuestion(HealthUserContext userContext,String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkTopicOfQuestion(topic);
		checkerOf(userContext).checkOptionAOfQuestion(optionA);
		checkerOf(userContext).checkOptionBOfQuestion(optionB);
		checkerOf(userContext).checkOptionCOfQuestion(optionC);
		checkerOf(userContext).checkOptionDOfQuestion(optionD);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionManagerException.class);


		Question question=createNewQuestion();	

		question.setTopic(topic);
			
		QuestionType questionType = loadQuestionType(userContext, questionTypeId,emptyOptions());
		question.setQuestionType(questionType);
		
		
		question.setOptionA(optionA);
		question.setOptionB(optionB);
		question.setOptionC(optionC);
		question.setOptionD(optionD);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		question.setPlatform(platform);
		
		

		question = saveQuestion(userContext, question, emptyOptions());
		
		onNewInstanceCreated(userContext, question);
		return question;


	}
	protected Question createNewQuestion()
	{

		return new Question();
	}

	protected void checkParamsForUpdatingQuestion(HealthUserContext userContext,String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion( questionVersion);
		

		if(Question.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfQuestion(parseString(newValueExpr));
		}		

		
		if(Question.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfQuestion(parseString(newValueExpr));
		}
		if(Question.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfQuestion(parseString(newValueExpr));
		}
		if(Question.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfQuestion(parseString(newValueExpr));
		}
		if(Question.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfQuestion(parseString(newValueExpr));
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionManagerException.class);


	}



	public Question clone(HealthUserContext userContext, String fromQuestionId) throws Exception{

		return questionDaoOf(userContext).clone(fromQuestionId, this.allTokens());
	}

	public Question internalSaveQuestion(HealthUserContext userContext, Question question) throws Exception
	{
		return internalSaveQuestion(userContext, question, allTokens());

	}
	public Question internalSaveQuestion(HealthUserContext userContext, Question question, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingQuestion(userContext, questionId, questionVersion, property, newValueExpr, tokensExpr);


		synchronized(question){
			//will be good when the question loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Question.
			if (question.isChanged()){
			
			}
			question = saveQuestion(userContext, question, options);
			return question;

		}

	}

	public Question updateQuestion(HealthUserContext userContext,String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestion(userContext, questionId, questionVersion, property, newValueExpr, tokensExpr);



		Question question = loadQuestion(userContext, questionId, allTokens());
		if(question.getVersion() != questionVersion){
			String message = "The target version("+question.getVersion()+") is not equals to version("+questionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(question){
			//will be good when the question loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Question.
			
			question.changeProperty(property, newValueExpr);
			question = saveQuestion(userContext, question, tokens().done());
			return present(userContext,question, mergedAllTokens(tokensExpr));
			//return saveQuestion(userContext, question, tokens().done());
		}

	}

	public Question updateQuestionProperty(HealthUserContext userContext,String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestion(userContext, questionId, questionVersion, property, newValueExpr, tokensExpr);

		Question question = loadQuestion(userContext, questionId, allTokens());
		if(question.getVersion() != questionVersion){
			String message = "The target version("+question.getVersion()+") is not equals to version("+questionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(question){
			//will be good when the question loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Question.

			question.changeProperty(property, newValueExpr);
			
			question = saveQuestion(userContext, question, tokens().done());
			return present(userContext,question, mergedAllTokens(tokensExpr));
			//return saveQuestion(userContext, question, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected QuestionTokens tokens(){
		return QuestionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return QuestionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return QuestionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherQuestionType(HealthUserContext userContext, String questionId, String anotherQuestionTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfQuestion(questionId);
 		checkerOf(userContext).checkIdOfQuestionType(anotherQuestionTypeId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(QuestionManagerException.class);

 	}
 	public Question transferToAnotherQuestionType(HealthUserContext userContext, String questionId, String anotherQuestionTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionType(userContext, questionId,anotherQuestionTypeId);
 
		Question question = loadQuestion(userContext, questionId, allTokens());	
		synchronized(question){
			//will be good when the question loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionType questionType = loadQuestionType(userContext, anotherQuestionTypeId, emptyOptions());		
			question.updateQuestionType(questionType);		
			question = saveQuestion(userContext, question, emptyOptions());
			
			return present(userContext,question, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherQuestionTypeWithCode(HealthUserContext userContext, String questionId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfQuestion(questionId);
 		checkerOf(userContext).checkCodeOfQuestionType( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(QuestionManagerException.class);

 	}

 	public Question transferToAnotherQuestionTypeWithCode(HealthUserContext userContext, String questionId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionTypeWithCode(userContext, questionId,anotherCode);
 		Question question = loadQuestion(userContext, questionId, allTokens());
		synchronized(question){
			//will be good when the question loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionType questionType = loadQuestionTypeWithCode(userContext, anotherCode, emptyOptions());
			question.updateQuestionType(questionType);
			question = saveQuestion(userContext, question, emptyOptions());

			return present(userContext,question, allTokens());

		}
 	}

	 


	public CandidateQuestionType requestCandidateQuestionType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateQuestionType result = new CandidateQuestionType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<QuestionType> candidateList = questionTypeDaoOf(userContext).requestCandidateQuestionTypeForQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String questionId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfQuestion(questionId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(QuestionManagerException.class);

 	}
 	public Question transferToAnotherPlatform(HealthUserContext userContext, String questionId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, questionId,anotherPlatformId);
 
		Question question = loadQuestion(userContext, questionId, allTokens());	
		synchronized(question){
			//will be good when the question loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			question.updatePlatform(platform);		
			question = saveQuestion(userContext, question, emptyOptions());
			
			return present(userContext,question, allTokens());
			
		}

 	}

	


	public CandidatePlatform requestCandidatePlatform(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected QuestionType loadQuestionType(HealthUserContext userContext, String newQuestionTypeId, Map<String,Object> options) throws Exception
 	{

 		return questionTypeDaoOf(userContext).load(newQuestionTypeId, options);
 	}
 	
 	protected QuestionType loadQuestionTypeWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return questionTypeDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String questionId, int questionVersion) throws Exception {
		//deleteInternal(userContext, questionId, questionVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String questionId, int questionVersion) throws Exception{

		questionDaoOf(userContext).delete(questionId, questionVersion);
	}

	public Question forgetByAll(HealthUserContext userContext, String questionId, int questionVersion) throws Exception {
		return forgetByAllInternal(userContext, questionId, questionVersion);
	}
	protected Question forgetByAllInternal(HealthUserContext userContext,
			String questionId, int questionVersion) throws Exception{

		return questionDaoOf(userContext).disconnectFromAll(questionId, questionVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new QuestionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return questionDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(HealthUserContext userContext, Question newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Question.INTERNAL_TYPE);
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


