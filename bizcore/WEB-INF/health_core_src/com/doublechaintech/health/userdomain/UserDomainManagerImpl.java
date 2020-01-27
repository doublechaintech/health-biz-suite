
package com.doublechaintech.health.userdomain;

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


import com.doublechaintech.health.userwhitelist.UserWhiteList;
import com.doublechaintech.health.secuser.SecUser;


import com.doublechaintech.health.userdomain.UserDomain;






public class UserDomainManagerImpl extends CustomHealthCheckerManager implements UserDomainManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "UserDomain";
	@Override
	public UserDomainDAO daoOf(HealthUserContext userContext) {
		return userDomainDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws UserDomainManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new UserDomainManagerException(message);

	}



 	protected UserDomain saveUserDomain(HealthUserContext userContext, UserDomain userDomain, String [] tokensExpr) throws Exception{	
 		//return getUserDomainDAO().save(userDomain, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveUserDomain(userContext, userDomain, tokens);
 	}
 	
 	protected UserDomain saveUserDomainDetail(HealthUserContext userContext, UserDomain userDomain) throws Exception{	

 		
 		return saveUserDomain(userContext, userDomain, allTokens());
 	}
 	
 	public UserDomain loadUserDomain(HealthUserContext userContext, String userDomainId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserDomainManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userDomain, tokens);
 	}
 	
 	
 	 public UserDomain searchUserDomain(HealthUserContext userContext, String userDomainId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserDomainManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userDomain, tokens);
 	}
 	
 	

 	protected UserDomain present(HealthUserContext userContext, UserDomain userDomain, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,userDomain,tokens);
		
		
		UserDomain  userDomainToPresent = userDomainDaoOf(userContext).present(userDomain, tokens);
		
		List<BaseEntity> entityListToNaming = userDomainToPresent.collectRefercencesFromLists();
		userDomainDaoOf(userContext).alias(entityListToNaming);
		
		return  userDomainToPresent;
		
		
	}
 
 	
 	
 	public UserDomain loadUserDomainDetail(HealthUserContext userContext, String userDomainId) throws Exception{	
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, allTokens());
 		return present(userContext,userDomain, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String userDomainId) throws Exception{	
 		UserDomain userDomain = loadUserDomain( userContext, userDomainId, viewTokens());
 		return present(userContext,userDomain, allTokens());
		
 	}
 	protected UserDomain saveUserDomain(HealthUserContext userContext, UserDomain userDomain, Map<String,Object>tokens) throws Exception{	
 		return userDomainDaoOf(userContext).save(userDomain, tokens);
 	}
 	protected UserDomain loadUserDomain(HealthUserContext userContext, String userDomainId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserDomainManagerException.class);

 
 		return userDomainDaoOf(userContext).load(userDomainId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, UserDomain userDomain, Map<String, Object> tokens){
		super.addActions(userContext, userDomain, tokens);
		
		addAction(userContext, userDomain, tokens,"@create","createUserDomain","createUserDomain/","main","primary");
		addAction(userContext, userDomain, tokens,"@update","updateUserDomain","updateUserDomain/"+userDomain.getId()+"/","main","primary");
		addAction(userContext, userDomain, tokens,"@copy","cloneUserDomain","cloneUserDomain/"+userDomain.getId()+"/","main","primary");
		
		addAction(userContext, userDomain, tokens,"user_domain.addUserWhiteList","addUserWhiteList","addUserWhiteList/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.removeUserWhiteList","removeUserWhiteList","removeUserWhiteList/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.updateUserWhiteList","updateUserWhiteList","updateUserWhiteList/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.copyUserWhiteListFrom","copyUserWhiteListFrom","copyUserWhiteListFrom/"+userDomain.getId()+"/","userWhiteListList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.addSecUser","addSecUser","addSecUser/"+userDomain.getId()+"/","secUserList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.removeSecUser","removeSecUser","removeSecUser/"+userDomain.getId()+"/","secUserList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.updateSecUser","updateSecUser","updateSecUser/"+userDomain.getId()+"/","secUserList","primary");
		addAction(userContext, userDomain, tokens,"user_domain.copySecUserFrom","copySecUserFrom","copySecUserFrom/"+userDomain.getId()+"/","secUserList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, UserDomain userDomain, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public UserDomain createUserDomain(HealthUserContext userContext, String name) throws Exception
	//public UserDomain createUserDomain(HealthUserContext userContext,String name) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfUserDomain(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);


		UserDomain userDomain=createNewUserDomain();	

		userDomain.setName(name);

		userDomain = saveUserDomain(userContext, userDomain, emptyOptions());
		
		onNewInstanceCreated(userContext, userDomain);
		return userDomain;


	}
	protected UserDomain createNewUserDomain()
	{

		return new UserDomain();
	}

	protected void checkParamsForUpdatingUserDomain(HealthUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).checkVersionOfUserDomain( userDomainVersion);
		

		if(UserDomain.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfUserDomain(parseString(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);


	}



	public UserDomain clone(HealthUserContext userContext, String fromUserDomainId) throws Exception{

		return userDomainDaoOf(userContext).clone(fromUserDomainId, this.allTokens());
	}

	public UserDomain internalSaveUserDomain(HealthUserContext userContext, UserDomain userDomain) throws Exception
	{
		return internalSaveUserDomain(userContext, userDomain, allTokens());

	}
	public UserDomain internalSaveUserDomain(HealthUserContext userContext, UserDomain userDomain, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingUserDomain(userContext, userDomainId, userDomainVersion, property, newValueExpr, tokensExpr);


		synchronized(userDomain){
			//will be good when the userDomain loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserDomain.
			if (userDomain.isChanged()){
			
			}
			userDomain = saveUserDomain(userContext, userDomain, options);
			return userDomain;

		}

	}

	public UserDomain updateUserDomain(HealthUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserDomain(userContext, userDomainId, userDomainVersion, property, newValueExpr, tokensExpr);



		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		if(userDomain.getVersion() != userDomainVersion){
			String message = "The target version("+userDomain.getVersion()+") is not equals to version("+userDomainVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userDomain){
			//will be good when the userDomain loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserDomain.
			
			userDomain.changeProperty(property, newValueExpr);
			userDomain = saveUserDomain(userContext, userDomain, tokens().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			//return saveUserDomain(userContext, userDomain, tokens().done());
		}

	}

	public UserDomain updateUserDomainProperty(HealthUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserDomain(userContext, userDomainId, userDomainVersion, property, newValueExpr, tokensExpr);

		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		if(userDomain.getVersion() != userDomainVersion){
			String message = "The target version("+userDomain.getVersion()+") is not equals to version("+userDomainVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userDomain){
			//will be good when the userDomain loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserDomain.

			userDomain.changeProperty(property, newValueExpr);
			
			userDomain = saveUserDomain(userContext, userDomain, tokens().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			//return saveUserDomain(userContext, userDomain, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected UserDomainTokens tokens(){
		return UserDomainTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return UserDomainTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortUserWhiteListListWith("id","desc")
		.sortSecUserListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return UserDomainTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String userDomainId, int userDomainVersion) throws Exception {
		//deleteInternal(userContext, userDomainId, userDomainVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String userDomainId, int userDomainVersion) throws Exception{

		userDomainDaoOf(userContext).delete(userDomainId, userDomainVersion);
	}

	public UserDomain forgetByAll(HealthUserContext userContext, String userDomainId, int userDomainVersion) throws Exception {
		return forgetByAllInternal(userContext, userDomainId, userDomainVersion);
	}
	protected UserDomain forgetByAllInternal(HealthUserContext userContext,
			String userDomainId, int userDomainVersion) throws Exception{

		return userDomainDaoOf(userContext).disconnectFromAll(userDomainId, userDomainVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new UserDomainManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return userDomainDaoOf(userContext).deleteAll();
	}








	protected void checkParamsForAddingUserWhiteList(HealthUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUserDomain(userDomainId);

		
		checkerOf(userContext).checkUserIdentityOfUserWhiteList(userIdentity);
		
		checkerOf(userContext).checkUserSpecialFunctionsOfUserWhiteList(userSpecialFunctions);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);


	}
	public  UserDomain addUserWhiteList(HealthUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingUserWhiteList(userContext,userDomainId,userIdentity, userSpecialFunctions,tokensExpr);

		UserWhiteList userWhiteList = createUserWhiteList(userContext,userIdentity, userSpecialFunctions);

		UserDomain userDomain = loadUserDomain(userContext, userDomainId, emptyOptions());
		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.addUserWhiteList( userWhiteList );
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			
			userContext.getManagerGroup().getUserWhiteListManager().onNewInstanceCreated(userContext, userWhiteList);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserWhiteListProperties(HealthUserContext userContext, String userDomainId,String id,String userIdentity,String userSpecialFunctions,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).checkIdOfUserWhiteList(id);

		checkerOf(userContext).checkUserIdentityOfUserWhiteList( userIdentity);
		checkerOf(userContext).checkUserSpecialFunctionsOfUserWhiteList( userSpecialFunctions);

		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain updateUserWhiteListProperties(HealthUserContext userContext, String userDomainId, String id,String userIdentity,String userSpecialFunctions, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserWhiteListProperties(userContext,userDomainId,id,userIdentity,userSpecialFunctions,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserWhiteListListList()
				.searchUserWhiteListListWith(UserWhiteList.ID_PROPERTY, "is", id).done();

		UserDomain userDomainToUpdate = loadUserDomain(userContext, userDomainId, options);

		if(userDomainToUpdate.getUserWhiteListList().isEmpty()){
			throw new UserDomainManagerException("UserWhiteList is NOT FOUND with id: '"+id+"'");
		}

		UserWhiteList item = userDomainToUpdate.getUserWhiteListList().first();

		item.updateUserIdentity( userIdentity );
		item.updateUserSpecialFunctions( userSpecialFunctions );


		//checkParamsForAddingUserWhiteList(userContext,userDomainId,name, code, used,tokensExpr);
		UserDomain userDomain = saveUserDomain(userContext, userDomainToUpdate, tokens().withUserWhiteListList().done());
		synchronized(userDomain){
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}


	protected UserWhiteList createUserWhiteList(HealthUserContext userContext, String userIdentity, String userSpecialFunctions) throws Exception{

		UserWhiteList userWhiteList = new UserWhiteList();
		
		
		userWhiteList.setUserIdentity(userIdentity);		
		userWhiteList.setUserSpecialFunctions(userSpecialFunctions);
	
		
		return userWhiteList;


	}

	protected UserWhiteList createIndexedUserWhiteList(String id, int version){

		UserWhiteList userWhiteList = new UserWhiteList();
		userWhiteList.setId(id);
		userWhiteList.setVersion(version);
		return userWhiteList;

	}

	protected void checkParamsForRemovingUserWhiteListList(HealthUserContext userContext, String userDomainId,
			String userWhiteListIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		for(String userWhiteListIdItem: userWhiteListIds){
			checkerOf(userContext).checkIdOfUserWhiteList(userWhiteListIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain removeUserWhiteListList(HealthUserContext userContext, String userDomainId,
			String userWhiteListIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingUserWhiteListList(userContext, userDomainId,  userWhiteListIds, tokensExpr);


			UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
			synchronized(userDomain){
				//Will be good when the userDomain loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDomainDaoOf(userContext).planToRemoveUserWhiteListList(userDomain, userWhiteListIds, allTokens());
				userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
				deleteRelationListInGraph(userContext, userDomain.getUserWhiteListList());
				return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingUserWhiteList(HealthUserContext userContext, String userDomainId,
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUserDomain( userDomainId);
		checkerOf(userContext).checkIdOfUserWhiteList(userWhiteListId);
		checkerOf(userContext).checkVersionOfUserWhiteList(userWhiteListVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain removeUserWhiteList(HealthUserContext userContext, String userDomainId,
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingUserWhiteList(userContext,userDomainId, userWhiteListId, userWhiteListVersion,tokensExpr);

		UserWhiteList userWhiteList = createIndexedUserWhiteList(userWhiteListId, userWhiteListVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.removeUserWhiteList( userWhiteList );
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			deleteRelationInGraph(userContext, userWhiteList);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingUserWhiteList(HealthUserContext userContext, String userDomainId,
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUserDomain( userDomainId);
		checkerOf(userContext).checkIdOfUserWhiteList(userWhiteListId);
		checkerOf(userContext).checkVersionOfUserWhiteList(userWhiteListVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain copyUserWhiteListFrom(HealthUserContext userContext, String userDomainId,
		String userWhiteListId, int userWhiteListVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingUserWhiteList(userContext,userDomainId, userWhiteListId, userWhiteListVersion,tokensExpr);

		UserWhiteList userWhiteList = createIndexedUserWhiteList(userWhiteListId, userWhiteListVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			userDomain.copyUserWhiteListFrom( userWhiteList );
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			
			userContext.getManagerGroup().getUserWhiteListManager().onNewInstanceCreated(userContext, (UserWhiteList)userDomain.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingUserWhiteList(HealthUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).checkIdOfUserWhiteList(userWhiteListId);
		checkerOf(userContext).checkVersionOfUserWhiteList(userWhiteListVersion);
		

		if(UserWhiteList.USER_IDENTITY_PROPERTY.equals(property)){
			checkerOf(userContext).checkUserIdentityOfUserWhiteList(parseString(newValueExpr));
		}
		
		if(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY.equals(property)){
			checkerOf(userContext).checkUserSpecialFunctionsOfUserWhiteList(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}

	public  UserDomain updateUserWhiteList(HealthUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingUserWhiteList(userContext, userDomainId, userWhiteListId, userWhiteListVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withUserWhiteListList().searchUserWhiteListListWith(UserWhiteList.ID_PROPERTY, "eq", userWhiteListId).done();



		UserDomain userDomain = loadUserDomain(userContext, userDomainId, loadTokens);

		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//userDomain.removeUserWhiteList( userWhiteList );
			//make changes to AcceleraterAccount.
			UserWhiteList userWhiteListIndex = createIndexedUserWhiteList(userWhiteListId, userWhiteListVersion);

			UserWhiteList userWhiteList = userDomain.findTheUserWhiteList(userWhiteListIndex);
			if(userWhiteList == null){
				throw new UserDomainManagerException(userWhiteList+" is NOT FOUND" );
			}

			userWhiteList.changeProperty(property, newValueExpr);
			
			userDomain = saveUserDomain(userContext, userDomain, tokens().withUserWhiteListList().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingSecUser(HealthUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUserDomain(userDomainId);

		
		checkerOf(userContext).checkLoginOfSecUser(login);
		
		checkerOf(userContext).checkMobileOfSecUser(mobile);
		
		checkerOf(userContext).checkEmailOfSecUser(email);
		
		checkerOf(userContext).checkPwdOfSecUser(pwd);
		
		checkerOf(userContext).checkWeixinOpenidOfSecUser(weixinOpenid);
		
		checkerOf(userContext).checkWeixinAppidOfSecUser(weixinAppid);
		
		checkerOf(userContext).checkAccessTokenOfSecUser(accessToken);
		
		checkerOf(userContext).checkVerificationCodeOfSecUser(verificationCode);
		
		checkerOf(userContext).checkVerificationCodeExpireOfSecUser(verificationCodeExpire);
		
		checkerOf(userContext).checkLastLoginTimeOfSecUser(lastLoginTime);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);


	}
	public  UserDomain addSecUser(HealthUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingSecUser(userContext,userDomainId,login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime,tokensExpr);

		SecUser secUser = createSecUser(userContext,login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime);

		UserDomain userDomain = loadUserDomain(userContext, userDomainId, emptyOptions());
		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.addSecUser( secUser );
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			
			userContext.getManagerGroup().getSecUserManager().onNewInstanceCreated(userContext, secUser);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSecUserProperties(HealthUserContext userContext, String userDomainId,String id,String login,String mobile,String email,String pwd,String weixinOpenid,String weixinAppid,String accessToken,int verificationCode,DateTime verificationCodeExpire,DateTime lastLoginTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).checkIdOfSecUser(id);

		checkerOf(userContext).checkLoginOfSecUser( login);
		checkerOf(userContext).checkMobileOfSecUser( mobile);
		checkerOf(userContext).checkEmailOfSecUser( email);
		checkerOf(userContext).checkPwdOfSecUser( pwd);
		checkerOf(userContext).checkWeixinOpenidOfSecUser( weixinOpenid);
		checkerOf(userContext).checkWeixinAppidOfSecUser( weixinAppid);
		checkerOf(userContext).checkAccessTokenOfSecUser( accessToken);
		checkerOf(userContext).checkVerificationCodeOfSecUser( verificationCode);
		checkerOf(userContext).checkVerificationCodeExpireOfSecUser( verificationCodeExpire);
		checkerOf(userContext).checkLastLoginTimeOfSecUser( lastLoginTime);

		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain updateSecUserProperties(HealthUserContext userContext, String userDomainId, String id,String login,String mobile,String email,String pwd,String weixinOpenid,String weixinAppid,String accessToken,int verificationCode,DateTime verificationCodeExpire,DateTime lastLoginTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSecUserProperties(userContext,userDomainId,id,login,mobile,email,pwd,weixinOpenid,weixinAppid,accessToken,verificationCode,verificationCodeExpire,lastLoginTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSecUserListList()
				.searchSecUserListWith(SecUser.ID_PROPERTY, "is", id).done();

		UserDomain userDomainToUpdate = loadUserDomain(userContext, userDomainId, options);

		if(userDomainToUpdate.getSecUserList().isEmpty()){
			throw new UserDomainManagerException("SecUser is NOT FOUND with id: '"+id+"'");
		}

		SecUser item = userDomainToUpdate.getSecUserList().first();

		item.updateLogin( login );
		item.updateMobile( mobile );
		item.updateEmail( email );
		item.updatePwd( pwd );
		item.updateWeixinOpenid( weixinOpenid );
		item.updateWeixinAppid( weixinAppid );
		item.updateAccessToken( accessToken );
		item.updateVerificationCode( verificationCode );
		item.updateVerificationCodeExpire( verificationCodeExpire );
		item.updateLastLoginTime( lastLoginTime );


		//checkParamsForAddingSecUser(userContext,userDomainId,name, code, used,tokensExpr);
		UserDomain userDomain = saveUserDomain(userContext, userDomainToUpdate, tokens().withSecUserList().done());
		synchronized(userDomain){
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}
	}


	protected SecUser createSecUser(HealthUserContext userContext, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime) throws Exception{

		SecUser secUser = new SecUser();
		
		
		secUser.setLogin(login);		
		secUser.setMobile(mobile);		
		secUser.setEmail(email);		
		secUser.setClearTextOfPwd(pwd);		
		secUser.setWeixinOpenid(weixinOpenid);		
		secUser.setWeixinAppid(weixinAppid);		
		secUser.setAccessToken(accessToken);		
		secUser.setVerificationCode(verificationCode);		
		secUser.setVerificationCodeExpire(verificationCodeExpire);		
		secUser.setLastLoginTime(lastLoginTime);
	
		
		return secUser;


	}

	protected SecUser createIndexedSecUser(String id, int version){

		SecUser secUser = new SecUser();
		secUser.setId(id);
		secUser.setVersion(version);
		return secUser;

	}

	protected void checkParamsForRemovingSecUserList(HealthUserContext userContext, String userDomainId,
			String secUserIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		for(String secUserIdItem: secUserIds){
			checkerOf(userContext).checkIdOfSecUser(secUserIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain removeSecUserList(HealthUserContext userContext, String userDomainId,
			String secUserIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingSecUserList(userContext, userDomainId,  secUserIds, tokensExpr);


			UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
			synchronized(userDomain){
				//Will be good when the userDomain loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDomainDaoOf(userContext).planToRemoveSecUserList(userDomain, secUserIds, allTokens());
				userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
				deleteRelationListInGraph(userContext, userDomain.getSecUserList());
				return present(userContext,userDomain, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingSecUser(HealthUserContext userContext, String userDomainId,
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUserDomain( userDomainId);
		checkerOf(userContext).checkIdOfSecUser(secUserId);
		checkerOf(userContext).checkVersionOfSecUser(secUserVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain removeSecUser(HealthUserContext userContext, String userDomainId,
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingSecUser(userContext,userDomainId, secUserId, secUserVersion,tokensExpr);

		SecUser secUser = createIndexedSecUser(secUserId, secUserVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userDomain.removeSecUser( secUser );
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			deleteRelationInGraph(userContext, secUser);
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingSecUser(HealthUserContext userContext, String userDomainId,
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUserDomain( userDomainId);
		checkerOf(userContext).checkIdOfSecUser(secUserId);
		checkerOf(userContext).checkVersionOfSecUser(secUserVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}
	public  UserDomain copySecUserFrom(HealthUserContext userContext, String userDomainId,
		String secUserId, int secUserVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingSecUser(userContext,userDomainId, secUserId, secUserVersion,tokensExpr);

		SecUser secUser = createIndexedSecUser(secUserId, secUserVersion);
		UserDomain userDomain = loadUserDomain(userContext, userDomainId, allTokens());
		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			userDomain.copySecUserFrom( secUser );
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			
			userContext.getManagerGroup().getSecUserManager().onNewInstanceCreated(userContext, (SecUser)userDomain.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingSecUser(HealthUserContext userContext, String userDomainId, String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUserDomain(userDomainId);
		checkerOf(userContext).checkIdOfSecUser(secUserId);
		checkerOf(userContext).checkVersionOfSecUser(secUserVersion);
		

		if(SecUser.LOGIN_PROPERTY.equals(property)){
			checkerOf(userContext).checkLoginOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkMobileOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.EMAIL_PROPERTY.equals(property)){
			checkerOf(userContext).checkEmailOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.PWD_PROPERTY.equals(property)){
			checkerOf(userContext).checkPwdOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.WEIXIN_OPENID_PROPERTY.equals(property)){
			checkerOf(userContext).checkWeixinOpenidOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.WEIXIN_APPID_PROPERTY.equals(property)){
			checkerOf(userContext).checkWeixinAppidOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.ACCESS_TOKEN_PROPERTY.equals(property)){
			checkerOf(userContext).checkAccessTokenOfSecUser(parseString(newValueExpr));
		}
		
		if(SecUser.VERIFICATION_CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkVerificationCodeOfSecUser(parseInt(newValueExpr));
		}
		
		if(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY.equals(property)){
			checkerOf(userContext).checkVerificationCodeExpireOfSecUser(parseTimestamp(newValueExpr));
		}
		
		if(SecUser.LAST_LOGIN_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkLastLoginTimeOfSecUser(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserDomainManagerException.class);

	}

	public  UserDomain updateSecUser(HealthUserContext userContext, String userDomainId, String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingSecUser(userContext, userDomainId, secUserId, secUserVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withSecUserList().searchSecUserListWith(SecUser.ID_PROPERTY, "eq", secUserId).done();



		UserDomain userDomain = loadUserDomain(userContext, userDomainId, loadTokens);

		synchronized(userDomain){
			//Will be good when the userDomain loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//userDomain.removeSecUser( secUser );
			//make changes to AcceleraterAccount.
			SecUser secUserIndex = createIndexedSecUser(secUserId, secUserVersion);

			SecUser secUser = userDomain.findTheSecUser(secUserIndex);
			if(secUser == null){
				throw new UserDomainManagerException(secUser+" is NOT FOUND" );
			}

			secUser.changeProperty(property, newValueExpr);
			
			userDomain = saveUserDomain(userContext, userDomain, tokens().withSecUserList().done());
			return present(userContext,userDomain, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, UserDomain newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, UserDomain.INTERNAL_TYPE);
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


