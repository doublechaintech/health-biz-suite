
package com.doublechaintech.health.wechatminiappidentify;

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


import com.doublechaintech.health.secuser.SecUser;

import com.doublechaintech.health.secuser.CandidateSecUser;







public class WechatMiniappIdentifyManagerImpl extends CustomHealthCheckerManager implements WechatMiniappIdentifyManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "WechatMiniappIdentify";
	@Override
	public WechatMiniappIdentifyDAO daoOf(HealthUserContext userContext) {
		return wechatMiniappIdentifyDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws WechatMiniappIdentifyManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new WechatMiniappIdentifyManagerException(message);

	}



 	protected WechatMiniappIdentify saveWechatMiniappIdentify(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify, String [] tokensExpr) throws Exception{	
 		//return getWechatMiniappIdentifyDAO().save(wechatMiniappIdentify, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, tokens);
 	}
 	
 	protected WechatMiniappIdentify saveWechatMiniappIdentifyDetail(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify) throws Exception{	

 		
 		return saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, allTokens());
 	}
 	
 	public WechatMiniappIdentify loadWechatMiniappIdentify(HealthUserContext userContext, String wechatMiniappIdentifyId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatMiniappIdentifyManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify( userContext, wechatMiniappIdentifyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,wechatMiniappIdentify, tokens);
 	}
 	
 	
 	 public WechatMiniappIdentify searchWechatMiniappIdentify(HealthUserContext userContext, String wechatMiniappIdentifyId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatMiniappIdentifyManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify( userContext, wechatMiniappIdentifyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,wechatMiniappIdentify, tokens);
 	}
 	
 	

 	protected WechatMiniappIdentify present(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,wechatMiniappIdentify,tokens);
		
		
		WechatMiniappIdentify  wechatMiniappIdentifyToPresent = wechatMiniappIdentifyDaoOf(userContext).present(wechatMiniappIdentify, tokens);
		
		List<BaseEntity> entityListToNaming = wechatMiniappIdentifyToPresent.collectRefercencesFromLists();
		wechatMiniappIdentifyDaoOf(userContext).alias(entityListToNaming);
		
		return  wechatMiniappIdentifyToPresent;
		
		
	}
 
 	
 	
 	public WechatMiniappIdentify loadWechatMiniappIdentifyDetail(HealthUserContext userContext, String wechatMiniappIdentifyId) throws Exception{	
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify( userContext, wechatMiniappIdentifyId, allTokens());
 		return present(userContext,wechatMiniappIdentify, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String wechatMiniappIdentifyId) throws Exception{	
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify( userContext, wechatMiniappIdentifyId, viewTokens());
 		return present(userContext,wechatMiniappIdentify, allTokens());
		
 	}
 	protected WechatMiniappIdentify saveWechatMiniappIdentify(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify, Map<String,Object>tokens) throws Exception{	
 		return wechatMiniappIdentifyDaoOf(userContext).save(wechatMiniappIdentify, tokens);
 	}
 	protected WechatMiniappIdentify loadWechatMiniappIdentify(HealthUserContext userContext, String wechatMiniappIdentifyId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatMiniappIdentifyManagerException.class);

 
 		return wechatMiniappIdentifyDaoOf(userContext).load(wechatMiniappIdentifyId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify, Map<String, Object> tokens){
		super.addActions(userContext, wechatMiniappIdentify, tokens);
		
		addAction(userContext, wechatMiniappIdentify, tokens,"@create","createWechatMiniappIdentify","createWechatMiniappIdentify/","main","primary");
		addAction(userContext, wechatMiniappIdentify, tokens,"@update","updateWechatMiniappIdentify","updateWechatMiniappIdentify/"+wechatMiniappIdentify.getId()+"/","main","primary");
		addAction(userContext, wechatMiniappIdentify, tokens,"@copy","cloneWechatMiniappIdentify","cloneWechatMiniappIdentify/"+wechatMiniappIdentify.getId()+"/","main","primary");
		
		addAction(userContext, wechatMiniappIdentify, tokens,"wechat_miniapp_identify.transfer_to_sec_user","transferToAnotherSecUser","transferToAnotherSecUser/"+wechatMiniappIdentify.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public WechatMiniappIdentify createWechatMiniappIdentify(HealthUserContext userContext, String openId,String appId,String secUserId,DateTime lastLoginTime) throws Exception
	//public WechatMiniappIdentify createWechatMiniappIdentify(HealthUserContext userContext,String openId, String appId, String secUserId, DateTime lastLoginTime) throws Exception
	{

		

		

		checkerOf(userContext).checkOpenIdOfWechatMiniappIdentify(openId);
		checkerOf(userContext).checkAppIdOfWechatMiniappIdentify(appId);
		checkerOf(userContext).checkLastLoginTimeOfWechatMiniappIdentify(lastLoginTime);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatMiniappIdentifyManagerException.class);


		WechatMiniappIdentify wechatMiniappIdentify=createNewWechatMiniappIdentify();	

		wechatMiniappIdentify.setOpenId(openId);
		wechatMiniappIdentify.setAppId(appId);
			
		SecUser secUser = loadSecUser(userContext, secUserId,emptyOptions());
		wechatMiniappIdentify.setSecUser(secUser);
		
		
		wechatMiniappIdentify.setCreateTime(userContext.now());
		wechatMiniappIdentify.setLastLoginTime(lastLoginTime);

		wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, emptyOptions());
		
		onNewInstanceCreated(userContext, wechatMiniappIdentify);
		return wechatMiniappIdentify;


	}
	protected WechatMiniappIdentify createNewWechatMiniappIdentify()
	{

		return new WechatMiniappIdentify();
	}

	protected void checkParamsForUpdatingWechatMiniappIdentify(HealthUserContext userContext,String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
		checkerOf(userContext).checkVersionOfWechatMiniappIdentify( wechatMiniappIdentifyVersion);
		

		if(WechatMiniappIdentify.OPEN_ID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkOpenIdOfWechatMiniappIdentify(parseString(newValueExpr));
		
			
		}
		if(WechatMiniappIdentify.APP_ID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAppIdOfWechatMiniappIdentify(parseString(newValueExpr));
		
			
		}		

		
		if(WechatMiniappIdentify.LAST_LOGIN_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLastLoginTimeOfWechatMiniappIdentify(parseTimestamp(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatMiniappIdentifyManagerException.class);


	}



	public WechatMiniappIdentify clone(HealthUserContext userContext, String fromWechatMiniappIdentifyId) throws Exception{

		return wechatMiniappIdentifyDaoOf(userContext).clone(fromWechatMiniappIdentifyId, this.allTokens());
	}

	public WechatMiniappIdentify internalSaveWechatMiniappIdentify(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify) throws Exception
	{
		return internalSaveWechatMiniappIdentify(userContext, wechatMiniappIdentify, allTokens());

	}
	public WechatMiniappIdentify internalSaveWechatMiniappIdentify(HealthUserContext userContext, WechatMiniappIdentify wechatMiniappIdentify, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, wechatMiniappIdentifyVersion, property, newValueExpr, tokensExpr);


		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatMiniappIdentify.
			if (wechatMiniappIdentify.isChanged()){
			
			}
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, options);
			return wechatMiniappIdentify;

		}

	}

	public WechatMiniappIdentify updateWechatMiniappIdentify(HealthUserContext userContext,String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, wechatMiniappIdentifyVersion, property, newValueExpr, tokensExpr);



		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, allTokens());
		if(wechatMiniappIdentify.getVersion() != wechatMiniappIdentifyVersion){
			String message = "The target version("+wechatMiniappIdentify.getVersion()+") is not equals to version("+wechatMiniappIdentifyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatMiniappIdentify.
			
			wechatMiniappIdentify.changeProperty(property, newValueExpr);
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, tokens().done());
			return present(userContext,wechatMiniappIdentify, mergedAllTokens(tokensExpr));
			//return saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, tokens().done());
		}

	}

	public WechatMiniappIdentify updateWechatMiniappIdentifyProperty(HealthUserContext userContext,String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, wechatMiniappIdentifyVersion, property, newValueExpr, tokensExpr);

		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, allTokens());
		if(wechatMiniappIdentify.getVersion() != wechatMiniappIdentifyVersion){
			String message = "The target version("+wechatMiniappIdentify.getVersion()+") is not equals to version("+wechatMiniappIdentifyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatMiniappIdentify.

			wechatMiniappIdentify.changeProperty(property, newValueExpr);
			
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, tokens().done());
			return present(userContext,wechatMiniappIdentify, mergedAllTokens(tokensExpr));
			//return saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected WechatMiniappIdentifyTokens tokens(){
		return WechatMiniappIdentifyTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return WechatMiniappIdentifyTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return WechatMiniappIdentifyTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherSecUser(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherSecUserId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
 		checkerOf(userContext).checkIdOfSecUser(anotherSecUserId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatMiniappIdentifyManagerException.class);

 	}
 	public WechatMiniappIdentify transferToAnotherSecUser(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherSecUserId) throws Exception
 	{
 		checkParamsForTransferingAnotherSecUser(userContext, wechatMiniappIdentifyId,anotherSecUserId);
 
		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, allTokens());	
		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SecUser secUser = loadSecUser(userContext, anotherSecUserId, emptyOptions());		
			wechatMiniappIdentify.updateSecUser(secUser);		
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, emptyOptions());
			
			return present(userContext,wechatMiniappIdentify, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherSecUserWithLogin(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherLogin) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
 		checkerOf(userContext).checkLoginOfSecUser( anotherLogin);
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatMiniappIdentifyManagerException.class);

 	}

 	public WechatMiniappIdentify transferToAnotherSecUserWithLogin(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherLogin) throws Exception
 	{
 		checkParamsForTransferingAnotherSecUserWithLogin(userContext, wechatMiniappIdentifyId,anotherLogin);
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, allTokens());
		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SecUser secUser = loadSecUserWithLogin(userContext, anotherLogin, emptyOptions());
			wechatMiniappIdentify.updateSecUser(secUser);
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, emptyOptions());

			return present(userContext,wechatMiniappIdentify, allTokens());

		}
 	}

	 

	protected void checkParamsForTransferingAnotherSecUserWithEmail(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherEmail) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
 		checkerOf(userContext).checkEmailOfSecUser( anotherEmail);
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatMiniappIdentifyManagerException.class);

 	}

 	public WechatMiniappIdentify transferToAnotherSecUserWithEmail(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherEmail) throws Exception
 	{
 		checkParamsForTransferingAnotherSecUserWithEmail(userContext, wechatMiniappIdentifyId,anotherEmail);
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, allTokens());
		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SecUser secUser = loadSecUserWithEmail(userContext, anotherEmail, emptyOptions());
			wechatMiniappIdentify.updateSecUser(secUser);
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, emptyOptions());

			return present(userContext,wechatMiniappIdentify, allTokens());

		}
 	}

	 

	protected void checkParamsForTransferingAnotherSecUserWithMobile(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherMobile) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatMiniappIdentify(wechatMiniappIdentifyId);
 		checkerOf(userContext).checkMobileOfSecUser( anotherMobile);
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatMiniappIdentifyManagerException.class);

 	}

 	public WechatMiniappIdentify transferToAnotherSecUserWithMobile(HealthUserContext userContext, String wechatMiniappIdentifyId, String anotherMobile) throws Exception
 	{
 		checkParamsForTransferingAnotherSecUserWithMobile(userContext, wechatMiniappIdentifyId,anotherMobile);
 		WechatMiniappIdentify wechatMiniappIdentify = loadWechatMiniappIdentify(userContext, wechatMiniappIdentifyId, allTokens());
		synchronized(wechatMiniappIdentify){
			//will be good when the wechatMiniappIdentify loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SecUser secUser = loadSecUserWithMobile(userContext, anotherMobile, emptyOptions());
			wechatMiniappIdentify.updateSecUser(secUser);
			wechatMiniappIdentify = saveWechatMiniappIdentify(userContext, wechatMiniappIdentify, emptyOptions());

			return present(userContext,wechatMiniappIdentify, allTokens());

		}
 	}

	 


	public CandidateSecUser requestCandidateSecUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSecUser result = new CandidateSecUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("login");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SecUser> candidateList = secUserDaoOf(userContext).requestCandidateSecUserForWechatMiniappIdentify(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected SecUser loadSecUser(HealthUserContext userContext, String newSecUserId, Map<String,Object> options) throws Exception
 	{

 		return secUserDaoOf(userContext).load(newSecUserId, options);
 	}
 	
 	protected SecUser loadSecUserWithLogin(HealthUserContext userContext, String newLogin, Map<String,Object> options) throws Exception
 	{

 		return secUserDaoOf(userContext).loadByLogin(newLogin, options);
 	}

 	
 	protected SecUser loadSecUserWithEmail(HealthUserContext userContext, String newEmail, Map<String,Object> options) throws Exception
 	{

 		return secUserDaoOf(userContext).loadByEmail(newEmail, options);
 	}

 	
 	protected SecUser loadSecUserWithMobile(HealthUserContext userContext, String newMobile, Map<String,Object> options) throws Exception
 	{

 		return secUserDaoOf(userContext).loadByMobile(newMobile, options);
 	}

 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion) throws Exception {
		//deleteInternal(userContext, wechatMiniappIdentifyId, wechatMiniappIdentifyVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion) throws Exception{

		wechatMiniappIdentifyDaoOf(userContext).delete(wechatMiniappIdentifyId, wechatMiniappIdentifyVersion);
	}

	public WechatMiniappIdentify forgetByAll(HealthUserContext userContext, String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion) throws Exception {
		return forgetByAllInternal(userContext, wechatMiniappIdentifyId, wechatMiniappIdentifyVersion);
	}
	protected WechatMiniappIdentify forgetByAllInternal(HealthUserContext userContext,
			String wechatMiniappIdentifyId, int wechatMiniappIdentifyVersion) throws Exception{

		return wechatMiniappIdentifyDaoOf(userContext).disconnectFromAll(wechatMiniappIdentifyId, wechatMiniappIdentifyVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new WechatMiniappIdentifyManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return wechatMiniappIdentifyDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(HealthUserContext userContext, WechatMiniappIdentify newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, WechatMiniappIdentify.INTERNAL_TYPE);
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







