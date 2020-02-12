
package com.doublechaintech.health.listaccess;

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


import com.doublechaintech.health.userapp.UserApp;

import com.doublechaintech.health.userapp.CandidateUserApp;







public class ListAccessManagerImpl extends CustomHealthCheckerManager implements ListAccessManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "ListAccess";
	@Override
	public ListAccessDAO daoOf(HealthUserContext userContext) {
		return listAccessDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ListAccessManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ListAccessManagerException(message);

	}



 	protected ListAccess saveListAccess(HealthUserContext userContext, ListAccess listAccess, String [] tokensExpr) throws Exception{	
 		//return getListAccessDAO().save(listAccess, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveListAccess(userContext, listAccess, tokens);
 	}
 	
 	protected ListAccess saveListAccessDetail(HealthUserContext userContext, ListAccess listAccess) throws Exception{	

 		
 		return saveListAccess(userContext, listAccess, allTokens());
 	}
 	
 	public ListAccess loadListAccess(HealthUserContext userContext, String listAccessId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfListAccess(listAccessId);
		checkerOf(userContext).throwExceptionIfHasErrors( ListAccessManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ListAccess listAccess = loadListAccess( userContext, listAccessId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,listAccess, tokens);
 	}
 	
 	
 	 public ListAccess searchListAccess(HealthUserContext userContext, String listAccessId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfListAccess(listAccessId);
		checkerOf(userContext).throwExceptionIfHasErrors( ListAccessManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ListAccess listAccess = loadListAccess( userContext, listAccessId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,listAccess, tokens);
 	}
 	
 	

 	protected ListAccess present(HealthUserContext userContext, ListAccess listAccess, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,listAccess,tokens);
		
		
		ListAccess  listAccessToPresent = listAccessDaoOf(userContext).present(listAccess, tokens);
		
		List<BaseEntity> entityListToNaming = listAccessToPresent.collectRefercencesFromLists();
		listAccessDaoOf(userContext).alias(entityListToNaming);
		
		return  listAccessToPresent;
		
		
	}
 
 	
 	
 	public ListAccess loadListAccessDetail(HealthUserContext userContext, String listAccessId) throws Exception{	
 		ListAccess listAccess = loadListAccess( userContext, listAccessId, allTokens());
 		return present(userContext,listAccess, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String listAccessId) throws Exception{	
 		ListAccess listAccess = loadListAccess( userContext, listAccessId, viewTokens());
 		return present(userContext,listAccess, allTokens());
		
 	}
 	protected ListAccess saveListAccess(HealthUserContext userContext, ListAccess listAccess, Map<String,Object>tokens) throws Exception{	
 		return listAccessDaoOf(userContext).save(listAccess, tokens);
 	}
 	protected ListAccess loadListAccess(HealthUserContext userContext, String listAccessId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfListAccess(listAccessId);
		checkerOf(userContext).throwExceptionIfHasErrors( ListAccessManagerException.class);

 
 		return listAccessDaoOf(userContext).load(listAccessId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ListAccess listAccess, Map<String, Object> tokens){
		super.addActions(userContext, listAccess, tokens);
		
		addAction(userContext, listAccess, tokens,"@create","createListAccess","createListAccess/","main","primary");
		addAction(userContext, listAccess, tokens,"@update","updateListAccess","updateListAccess/"+listAccess.getId()+"/","main","primary");
		addAction(userContext, listAccess, tokens,"@copy","cloneListAccess","cloneListAccess/"+listAccess.getId()+"/","main","primary");
		
		addAction(userContext, listAccess, tokens,"list_access.transfer_to_app","transferToAnotherApp","transferToAnotherApp/"+listAccess.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ListAccess listAccess, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ListAccess createListAccess(HealthUserContext userContext, String name,String internalName,boolean readPermission,boolean createPermission,boolean deletePermission,boolean updatePermission,boolean executionPermission,String appId) throws Exception
	//public ListAccess createListAccess(HealthUserContext userContext,String name, String internalName, boolean readPermission, boolean createPermission, boolean deletePermission, boolean updatePermission, boolean executionPermission, String appId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfListAccess(name);
		checkerOf(userContext).checkInternalNameOfListAccess(internalName);
		checkerOf(userContext).checkReadPermissionOfListAccess(readPermission);
		checkerOf(userContext).checkCreatePermissionOfListAccess(createPermission);
		checkerOf(userContext).checkDeletePermissionOfListAccess(deletePermission);
		checkerOf(userContext).checkUpdatePermissionOfListAccess(updatePermission);
		checkerOf(userContext).checkExecutionPermissionOfListAccess(executionPermission);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ListAccessManagerException.class);


		ListAccess listAccess=createNewListAccess();	

		listAccess.setName(name);
		listAccess.setInternalName(internalName);
		listAccess.setReadPermission(readPermission);
		listAccess.setCreatePermission(createPermission);
		listAccess.setDeletePermission(deletePermission);
		listAccess.setUpdatePermission(updatePermission);
		listAccess.setExecutionPermission(executionPermission);
			
		UserApp app = loadUserApp(userContext, appId,emptyOptions());
		listAccess.setApp(app);
		
		

		listAccess = saveListAccess(userContext, listAccess, emptyOptions());
		
		onNewInstanceCreated(userContext, listAccess);
		return listAccess;


	}
	protected ListAccess createNewListAccess()
	{

		return new ListAccess();
	}

	protected void checkParamsForUpdatingListAccess(HealthUserContext userContext,String listAccessId, int listAccessVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfListAccess(listAccessId);
		checkerOf(userContext).checkVersionOfListAccess( listAccessVersion);
		

		if(ListAccess.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfListAccess(parseString(newValueExpr));
		
			
		}
		if(ListAccess.INTERNAL_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkInternalNameOfListAccess(parseString(newValueExpr));
		
			
		}
		if(ListAccess.READ_PERMISSION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkReadPermissionOfListAccess(parseBoolean(newValueExpr));
		
			
		}
		if(ListAccess.CREATE_PERMISSION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCreatePermissionOfListAccess(parseBoolean(newValueExpr));
		
			
		}
		if(ListAccess.DELETE_PERMISSION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDeletePermissionOfListAccess(parseBoolean(newValueExpr));
		
			
		}
		if(ListAccess.UPDATE_PERMISSION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkUpdatePermissionOfListAccess(parseBoolean(newValueExpr));
		
			
		}
		if(ListAccess.EXECUTION_PERMISSION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkExecutionPermissionOfListAccess(parseBoolean(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ListAccessManagerException.class);


	}



	public ListAccess clone(HealthUserContext userContext, String fromListAccessId) throws Exception{

		return listAccessDaoOf(userContext).clone(fromListAccessId, this.allTokens());
	}

	public ListAccess internalSaveListAccess(HealthUserContext userContext, ListAccess listAccess) throws Exception
	{
		return internalSaveListAccess(userContext, listAccess, allTokens());

	}
	public ListAccess internalSaveListAccess(HealthUserContext userContext, ListAccess listAccess, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingListAccess(userContext, listAccessId, listAccessVersion, property, newValueExpr, tokensExpr);


		synchronized(listAccess){
			//will be good when the listAccess loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ListAccess.
			if (listAccess.isChanged()){
			
			}
			listAccess = saveListAccess(userContext, listAccess, options);
			return listAccess;

		}

	}

	public ListAccess updateListAccess(HealthUserContext userContext,String listAccessId, int listAccessVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingListAccess(userContext, listAccessId, listAccessVersion, property, newValueExpr, tokensExpr);



		ListAccess listAccess = loadListAccess(userContext, listAccessId, allTokens());
		if(listAccess.getVersion() != listAccessVersion){
			String message = "The target version("+listAccess.getVersion()+") is not equals to version("+listAccessVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(listAccess){
			//will be good when the listAccess loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ListAccess.
			
			listAccess.changeProperty(property, newValueExpr);
			listAccess = saveListAccess(userContext, listAccess, tokens().done());
			return present(userContext,listAccess, mergedAllTokens(tokensExpr));
			//return saveListAccess(userContext, listAccess, tokens().done());
		}

	}

	public ListAccess updateListAccessProperty(HealthUserContext userContext,String listAccessId, int listAccessVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingListAccess(userContext, listAccessId, listAccessVersion, property, newValueExpr, tokensExpr);

		ListAccess listAccess = loadListAccess(userContext, listAccessId, allTokens());
		if(listAccess.getVersion() != listAccessVersion){
			String message = "The target version("+listAccess.getVersion()+") is not equals to version("+listAccessVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(listAccess){
			//will be good when the listAccess loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ListAccess.

			listAccess.changeProperty(property, newValueExpr);
			
			listAccess = saveListAccess(userContext, listAccess, tokens().done());
			return present(userContext,listAccess, mergedAllTokens(tokensExpr));
			//return saveListAccess(userContext, listAccess, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ListAccessTokens tokens(){
		return ListAccessTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ListAccessTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ListAccessTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherApp(HealthUserContext userContext, String listAccessId, String anotherAppId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfListAccess(listAccessId);
 		checkerOf(userContext).checkIdOfUserApp(anotherAppId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ListAccessManagerException.class);

 	}
 	public ListAccess transferToAnotherApp(HealthUserContext userContext, String listAccessId, String anotherAppId) throws Exception
 	{
 		checkParamsForTransferingAnotherApp(userContext, listAccessId,anotherAppId);
 
		ListAccess listAccess = loadListAccess(userContext, listAccessId, allTokens());	
		synchronized(listAccess){
			//will be good when the listAccess loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			UserApp app = loadUserApp(userContext, anotherAppId, emptyOptions());		
			listAccess.updateApp(app);		
			listAccess = saveListAccess(userContext, listAccess, emptyOptions());
			
			return present(userContext,listAccess, allTokens());
			
		}

 	}

	


	public CandidateUserApp requestCandidateApp(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateUserApp result = new CandidateUserApp();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("title");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<UserApp> candidateList = userAppDaoOf(userContext).requestCandidateUserAppForListAccess(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected UserApp loadUserApp(HealthUserContext userContext, String newAppId, Map<String,Object> options) throws Exception
 	{

 		return userAppDaoOf(userContext).load(newAppId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String listAccessId, int listAccessVersion) throws Exception {
		//deleteInternal(userContext, listAccessId, listAccessVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String listAccessId, int listAccessVersion) throws Exception{

		listAccessDaoOf(userContext).delete(listAccessId, listAccessVersion);
	}

	public ListAccess forgetByAll(HealthUserContext userContext, String listAccessId, int listAccessVersion) throws Exception {
		return forgetByAllInternal(userContext, listAccessId, listAccessVersion);
	}
	protected ListAccess forgetByAllInternal(HealthUserContext userContext,
			String listAccessId, int listAccessVersion) throws Exception{

		return listAccessDaoOf(userContext).disconnectFromAll(listAccessId, listAccessVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ListAccessManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return listAccessDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(HealthUserContext userContext, ListAccess newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ListAccess.INTERNAL_TYPE);
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


