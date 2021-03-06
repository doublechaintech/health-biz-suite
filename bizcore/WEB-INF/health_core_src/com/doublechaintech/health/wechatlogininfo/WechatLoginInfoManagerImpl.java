
package com.doublechaintech.health.wechatlogininfo;

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


import com.doublechaintech.health.user.User;

import com.doublechaintech.health.user.CandidateUser;







public class WechatLoginInfoManagerImpl extends CustomHealthCheckerManager implements WechatLoginInfoManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "WechatLoginInfo";
	@Override
	public WechatLoginInfoDAO daoOf(HealthUserContext userContext) {
		return wechatLoginInfoDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws WechatLoginInfoManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new WechatLoginInfoManagerException(message);

	}



 	protected WechatLoginInfo saveWechatLoginInfo(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo, String [] tokensExpr) throws Exception{	
 		//return getWechatLoginInfoDAO().save(wechatLoginInfo, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveWechatLoginInfo(userContext, wechatLoginInfo, tokens);
 	}
 	
 	protected WechatLoginInfo saveWechatLoginInfoDetail(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo) throws Exception{	

 		
 		return saveWechatLoginInfo(userContext, wechatLoginInfo, allTokens());
 	}
 	
 	public WechatLoginInfo loadWechatLoginInfo(HealthUserContext userContext, String wechatLoginInfoId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatLoginInfoManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo( userContext, wechatLoginInfoId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,wechatLoginInfo, tokens);
 	}
 	
 	
 	 public WechatLoginInfo searchWechatLoginInfo(HealthUserContext userContext, String wechatLoginInfoId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatLoginInfoManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo( userContext, wechatLoginInfoId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,wechatLoginInfo, tokens);
 	}
 	
 	

 	protected WechatLoginInfo present(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,wechatLoginInfo,tokens);
		
		
		WechatLoginInfo  wechatLoginInfoToPresent = wechatLoginInfoDaoOf(userContext).present(wechatLoginInfo, tokens);
		
		List<BaseEntity> entityListToNaming = wechatLoginInfoToPresent.collectRefercencesFromLists();
		wechatLoginInfoDaoOf(userContext).alias(entityListToNaming);
		
		return  wechatLoginInfoToPresent;
		
		
	}
 
 	
 	
 	public WechatLoginInfo loadWechatLoginInfoDetail(HealthUserContext userContext, String wechatLoginInfoId) throws Exception{	
 		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo( userContext, wechatLoginInfoId, allTokens());
 		return present(userContext,wechatLoginInfo, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String wechatLoginInfoId) throws Exception{	
 		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo( userContext, wechatLoginInfoId, viewTokens());
 		return present(userContext,wechatLoginInfo, allTokens());
		
 	}
 	protected WechatLoginInfo saveWechatLoginInfo(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo, Map<String,Object>tokens) throws Exception{	
 		return wechatLoginInfoDaoOf(userContext).save(wechatLoginInfo, tokens);
 	}
 	protected WechatLoginInfo loadWechatLoginInfo(HealthUserContext userContext, String wechatLoginInfoId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatLoginInfoManagerException.class);

 
 		return wechatLoginInfoDaoOf(userContext).load(wechatLoginInfoId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo, Map<String, Object> tokens){
		super.addActions(userContext, wechatLoginInfo, tokens);
		
		addAction(userContext, wechatLoginInfo, tokens,"@create","createWechatLoginInfo","createWechatLoginInfo/","main","primary");
		addAction(userContext, wechatLoginInfo, tokens,"@update","updateWechatLoginInfo","updateWechatLoginInfo/"+wechatLoginInfo.getId()+"/","main","primary");
		addAction(userContext, wechatLoginInfo, tokens,"@copy","cloneWechatLoginInfo","cloneWechatLoginInfo/"+wechatLoginInfo.getId()+"/","main","primary");
		
		addAction(userContext, wechatLoginInfo, tokens,"wechat_login_info.transfer_to_user","transferToAnotherUser","transferToAnotherUser/"+wechatLoginInfo.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public WechatLoginInfo createWechatLoginInfo(HealthUserContext userContext, String userId,String appId,String openId,String sessionKey) throws Exception
	//public WechatLoginInfo createWechatLoginInfo(HealthUserContext userContext,String userId, String appId, String openId, String sessionKey) throws Exception
	{

		

		

		checkerOf(userContext).checkAppIdOfWechatLoginInfo(appId);
		checkerOf(userContext).checkOpenIdOfWechatLoginInfo(openId);
		checkerOf(userContext).checkSessionKeyOfWechatLoginInfo(sessionKey);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatLoginInfoManagerException.class);


		WechatLoginInfo wechatLoginInfo=createNewWechatLoginInfo();	

			
		User user = loadUser(userContext, userId,emptyOptions());
		wechatLoginInfo.setUser(user);
		
		
		wechatLoginInfo.setAppId(appId);
		wechatLoginInfo.setOpenId(openId);
		wechatLoginInfo.setSessionKey(sessionKey);
		wechatLoginInfo.setLastUpdateTime(userContext.now());

		wechatLoginInfo = saveWechatLoginInfo(userContext, wechatLoginInfo, emptyOptions());
		
		onNewInstanceCreated(userContext, wechatLoginInfo);
		return wechatLoginInfo;


	}
	protected WechatLoginInfo createNewWechatLoginInfo()
	{

		return new WechatLoginInfo();
	}

	protected void checkParamsForUpdatingWechatLoginInfo(HealthUserContext userContext,String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).checkVersionOfWechatLoginInfo( wechatLoginInfoVersion);
		
		

		
		if(WechatLoginInfo.APP_ID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAppIdOfWechatLoginInfo(parseString(newValueExpr));
		
			
		}
		if(WechatLoginInfo.OPEN_ID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkOpenIdOfWechatLoginInfo(parseString(newValueExpr));
		
			
		}
		if(WechatLoginInfo.SESSION_KEY_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSessionKeyOfWechatLoginInfo(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatLoginInfoManagerException.class);


	}



	public WechatLoginInfo clone(HealthUserContext userContext, String fromWechatLoginInfoId) throws Exception{

		return wechatLoginInfoDaoOf(userContext).clone(fromWechatLoginInfoId, this.allTokens());
	}

	public WechatLoginInfo internalSaveWechatLoginInfo(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo) throws Exception
	{
		return internalSaveWechatLoginInfo(userContext, wechatLoginInfo, allTokens());

	}
	public WechatLoginInfo internalSaveWechatLoginInfo(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingWechatLoginInfo(userContext, wechatLoginInfoId, wechatLoginInfoVersion, property, newValueExpr, tokensExpr);


		synchronized(wechatLoginInfo){
			//will be good when the wechatLoginInfo loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatLoginInfo.
			if (wechatLoginInfo.isChanged()){
			wechatLoginInfo.updateLastUpdateTime(userContext.now());
			}
			wechatLoginInfo = saveWechatLoginInfo(userContext, wechatLoginInfo, options);
			return wechatLoginInfo;

		}

	}

	public WechatLoginInfo updateWechatLoginInfo(HealthUserContext userContext,String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatLoginInfo(userContext, wechatLoginInfoId, wechatLoginInfoVersion, property, newValueExpr, tokensExpr);



		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo(userContext, wechatLoginInfoId, allTokens());
		if(wechatLoginInfo.getVersion() != wechatLoginInfoVersion){
			String message = "The target version("+wechatLoginInfo.getVersion()+") is not equals to version("+wechatLoginInfoVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(wechatLoginInfo){
			//will be good when the wechatLoginInfo loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatLoginInfo.
			wechatLoginInfo.updateLastUpdateTime(userContext.now());
			wechatLoginInfo.changeProperty(property, newValueExpr);
			wechatLoginInfo = saveWechatLoginInfo(userContext, wechatLoginInfo, tokens().done());
			return present(userContext,wechatLoginInfo, mergedAllTokens(tokensExpr));
			//return saveWechatLoginInfo(userContext, wechatLoginInfo, tokens().done());
		}

	}

	public WechatLoginInfo updateWechatLoginInfoProperty(HealthUserContext userContext,String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatLoginInfo(userContext, wechatLoginInfoId, wechatLoginInfoVersion, property, newValueExpr, tokensExpr);

		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo(userContext, wechatLoginInfoId, allTokens());
		if(wechatLoginInfo.getVersion() != wechatLoginInfoVersion){
			String message = "The target version("+wechatLoginInfo.getVersion()+") is not equals to version("+wechatLoginInfoVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(wechatLoginInfo){
			//will be good when the wechatLoginInfo loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatLoginInfo.

			wechatLoginInfo.changeProperty(property, newValueExpr);
			wechatLoginInfo.updateLastUpdateTime(userContext.now());
			wechatLoginInfo = saveWechatLoginInfo(userContext, wechatLoginInfo, tokens().done());
			return present(userContext,wechatLoginInfo, mergedAllTokens(tokensExpr));
			//return saveWechatLoginInfo(userContext, wechatLoginInfo, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected WechatLoginInfoTokens tokens(){
		return WechatLoginInfoTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return WechatLoginInfoTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return WechatLoginInfoTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherUser(HealthUserContext userContext, String wechatLoginInfoId, String anotherUserId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
 		checkerOf(userContext).checkIdOfUser(anotherUserId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatLoginInfoManagerException.class);

 	}
 	public WechatLoginInfo transferToAnotherUser(HealthUserContext userContext, String wechatLoginInfoId, String anotherUserId) throws Exception
 	{
 		checkParamsForTransferingAnotherUser(userContext, wechatLoginInfoId,anotherUserId);
 
		WechatLoginInfo wechatLoginInfo = loadWechatLoginInfo(userContext, wechatLoginInfoId, allTokens());	
		synchronized(wechatLoginInfo){
			//will be good when the wechatLoginInfo loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			User user = loadUser(userContext, anotherUserId, emptyOptions());		
			wechatLoginInfo.updateUser(user);		
			wechatLoginInfo = saveWechatLoginInfo(userContext, wechatLoginInfo, emptyOptions());
			
			return present(userContext,wechatLoginInfo, allTokens());
			
		}

 	}

	


	public CandidateUser requestCandidateUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateUser result = new CandidateUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<User> candidateList = userDaoOf(userContext).requestCandidateUserForWechatLoginInfo(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected User loadUser(HealthUserContext userContext, String newUserId, Map<String,Object> options) throws Exception
 	{

 		return userDaoOf(userContext).load(newUserId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String wechatLoginInfoId, int wechatLoginInfoVersion) throws Exception {
		//deleteInternal(userContext, wechatLoginInfoId, wechatLoginInfoVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String wechatLoginInfoId, int wechatLoginInfoVersion) throws Exception{

		wechatLoginInfoDaoOf(userContext).delete(wechatLoginInfoId, wechatLoginInfoVersion);
	}

	public WechatLoginInfo forgetByAll(HealthUserContext userContext, String wechatLoginInfoId, int wechatLoginInfoVersion) throws Exception {
		return forgetByAllInternal(userContext, wechatLoginInfoId, wechatLoginInfoVersion);
	}
	protected WechatLoginInfo forgetByAllInternal(HealthUserContext userContext,
			String wechatLoginInfoId, int wechatLoginInfoVersion) throws Exception{

		return wechatLoginInfoDaoOf(userContext).disconnectFromAll(wechatLoginInfoId, wechatLoginInfoVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new WechatLoginInfoManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return wechatLoginInfoDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(HealthUserContext userContext, WechatLoginInfo newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, WechatLoginInfo.INTERNAL_TYPE);
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


