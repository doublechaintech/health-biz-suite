
package com.doublechaintech.health.usertype;

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
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.platform.CandidatePlatform;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.usertype.UserType;






public class UserTypeManagerImpl extends CustomHealthCheckerManager implements UserTypeManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "UserType";
	@Override
	public UserTypeDAO daoOf(HealthUserContext userContext) {
		return userTypeDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws UserTypeManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new UserTypeManagerException(message);

	}



 	protected UserType saveUserType(HealthUserContext userContext, UserType userType, String [] tokensExpr) throws Exception{	
 		//return getUserTypeDAO().save(userType, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveUserType(userContext, userType, tokens);
 	}
 	
 	protected UserType saveUserTypeDetail(HealthUserContext userContext, UserType userType) throws Exception{	

 		
 		return saveUserType(userContext, userType, allTokens());
 	}
 	
 	public UserType loadUserType(HealthUserContext userContext, String userTypeId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserTypeManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		UserType userType = loadUserType( userContext, userTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userType, tokens);
 	}
 	
 	
 	 public UserType searchUserType(HealthUserContext userContext, String userTypeId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserTypeManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		UserType userType = loadUserType( userContext, userTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userType, tokens);
 	}
 	
 	

 	protected UserType present(HealthUserContext userContext, UserType userType, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,userType,tokens);
		
		
		UserType  userTypeToPresent = userTypeDaoOf(userContext).present(userType, tokens);
		
		List<BaseEntity> entityListToNaming = userTypeToPresent.collectRefercencesFromLists();
		userTypeDaoOf(userContext).alias(entityListToNaming);
		
		return  userTypeToPresent;
		
		
	}
 
 	
 	
 	public UserType loadUserTypeDetail(HealthUserContext userContext, String userTypeId) throws Exception{	
 		UserType userType = loadUserType( userContext, userTypeId, allTokens());
 		return present(userContext,userType, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String userTypeId) throws Exception{	
 		UserType userType = loadUserType( userContext, userTypeId, viewTokens());
 		return present(userContext,userType, allTokens());
		
 	}
 	protected UserType saveUserType(HealthUserContext userContext, UserType userType, Map<String,Object>tokens) throws Exception{	
 		return userTypeDaoOf(userContext).save(userType, tokens);
 	}
 	protected UserType loadUserType(HealthUserContext userContext, String userTypeId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserTypeManagerException.class);

 
 		return userTypeDaoOf(userContext).load(userTypeId, tokens);
 	}

	
	

	public UserType loadUserTypeWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception{	
 		return userTypeDaoOf(userContext).loadByCode(code, tokens);
 	}

	 


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, UserType userType, Map<String, Object> tokens){
		super.addActions(userContext, userType, tokens);
		
		addAction(userContext, userType, tokens,"@create","createUserType","createUserType/","main","primary");
		addAction(userContext, userType, tokens,"@update","updateUserType","updateUserType/"+userType.getId()+"/","main","primary");
		addAction(userContext, userType, tokens,"@copy","cloneUserType","cloneUserType/"+userType.getId()+"/","main","primary");
		
		addAction(userContext, userType, tokens,"user_type.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+userType.getId()+"/","main","primary");
		addAction(userContext, userType, tokens,"user_type.addWechatUser","addWechatUser","addWechatUser/"+userType.getId()+"/","wechatUserList","primary");
		addAction(userContext, userType, tokens,"user_type.removeWechatUser","removeWechatUser","removeWechatUser/"+userType.getId()+"/","wechatUserList","primary");
		addAction(userContext, userType, tokens,"user_type.updateWechatUser","updateWechatUser","updateWechatUser/"+userType.getId()+"/","wechatUserList","primary");
		addAction(userContext, userType, tokens,"user_type.copyWechatUserFrom","copyWechatUserFrom","copyWechatUserFrom/"+userType.getId()+"/","wechatUserList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, UserType userType, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public UserType createUserType(HealthUserContext userContext, String name,String code,String platformId) throws Exception
	//public UserType createUserType(HealthUserContext userContext,String name, String code, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfUserType(name);
		checkerOf(userContext).checkCodeOfUserType(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);


		UserType userType=createNewUserType();	

		userType.setName(name);
		userType.setCode(code);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		userType.setPlatform(platform);
		
		

		userType = saveUserType(userContext, userType, emptyOptions());
		
		onNewInstanceCreated(userContext, userType);
		return userType;


	}
	protected UserType createNewUserType()
	{

		return new UserType();
	}

	protected void checkParamsForUpdatingUserType(HealthUserContext userContext,String userTypeId, int userTypeVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).checkVersionOfUserType( userTypeVersion);
		

		if(UserType.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfUserType(parseString(newValueExpr));
		}
		if(UserType.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfUserType(parseString(newValueExpr));
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);


	}



	public UserType clone(HealthUserContext userContext, String fromUserTypeId) throws Exception{

		return userTypeDaoOf(userContext).clone(fromUserTypeId, this.allTokens());
	}

	public UserType internalSaveUserType(HealthUserContext userContext, UserType userType) throws Exception
	{
		return internalSaveUserType(userContext, userType, allTokens());

	}
	public UserType internalSaveUserType(HealthUserContext userContext, UserType userType, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingUserType(userContext, userTypeId, userTypeVersion, property, newValueExpr, tokensExpr);


		synchronized(userType){
			//will be good when the userType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserType.
			if (userType.isChanged()){
			
			}
			userType = saveUserType(userContext, userType, options);
			return userType;

		}

	}

	public UserType updateUserType(HealthUserContext userContext,String userTypeId, int userTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserType(userContext, userTypeId, userTypeVersion, property, newValueExpr, tokensExpr);



		UserType userType = loadUserType(userContext, userTypeId, allTokens());
		if(userType.getVersion() != userTypeVersion){
			String message = "The target version("+userType.getVersion()+") is not equals to version("+userTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userType){
			//will be good when the userType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserType.
			
			userType.changeProperty(property, newValueExpr);
			userType = saveUserType(userContext, userType, tokens().done());
			return present(userContext,userType, mergedAllTokens(tokensExpr));
			//return saveUserType(userContext, userType, tokens().done());
		}

	}

	public UserType updateUserTypeProperty(HealthUserContext userContext,String userTypeId, int userTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserType(userContext, userTypeId, userTypeVersion, property, newValueExpr, tokensExpr);

		UserType userType = loadUserType(userContext, userTypeId, allTokens());
		if(userType.getVersion() != userTypeVersion){
			String message = "The target version("+userType.getVersion()+") is not equals to version("+userTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userType){
			//will be good when the userType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserType.

			userType.changeProperty(property, newValueExpr);
			
			userType = saveUserType(userContext, userType, tokens().done());
			return present(userContext,userType, mergedAllTokens(tokensExpr));
			//return saveUserType(userContext, userType, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected UserTypeTokens tokens(){
		return UserTypeTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return UserTypeTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortWechatUserListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return UserTypeTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String userTypeId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfUserType(userTypeId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);

 	}
 	public UserType transferToAnotherPlatform(HealthUserContext userContext, String userTypeId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, userTypeId,anotherPlatformId);
 
		UserType userType = loadUserType(userContext, userTypeId, allTokens());	
		synchronized(userType){
			//will be good when the userType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			userType.updatePlatform(platform);		
			userType = saveUserType(userContext, userType, emptyOptions());
			
			return present(userContext,userType, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForUserType(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String userTypeId, int userTypeVersion) throws Exception {
		//deleteInternal(userContext, userTypeId, userTypeVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String userTypeId, int userTypeVersion) throws Exception{

		userTypeDaoOf(userContext).delete(userTypeId, userTypeVersion);
	}

	public UserType forgetByAll(HealthUserContext userContext, String userTypeId, int userTypeVersion) throws Exception {
		return forgetByAllInternal(userContext, userTypeId, userTypeVersion);
	}
	protected UserType forgetByAllInternal(HealthUserContext userContext,
			String userTypeId, int userTypeVersion) throws Exception{

		return userTypeDaoOf(userContext).disconnectFromAll(userTypeId, userTypeVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new UserTypeManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return userTypeDaoOf(userContext).deleteAll();
	}


	//disconnect UserType with address in WechatUser
	protected UserType breakWithWechatUserByAddress(HealthUserContext userContext, String userTypeId, String addressId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			UserType userType = loadUserType(userContext, userTypeId, allTokens());

			synchronized(userType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userTypeDaoOf(userContext).planToRemoveWechatUserListWithAddress(userType, addressId, this.emptyOptions());

				userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
				return userType;
			}
	}
	//disconnect UserType with platform in WechatUser
	protected UserType breakWithWechatUserByPlatform(HealthUserContext userContext, String userTypeId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			UserType userType = loadUserType(userContext, userTypeId, allTokens());

			synchronized(userType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userTypeDaoOf(userContext).planToRemoveWechatUserListWithPlatform(userType, platformId, this.emptyOptions());

				userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
				return userType;
			}
	}






	protected void checkParamsForAddingWechatUser(HealthUserContext userContext, String userTypeId, String name, String avatar, String addressId, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUserType(userTypeId);

		
		checkerOf(userContext).checkNameOfWechatUser(name);
		
		checkerOf(userContext).checkAvatarOfWechatUser(avatar);
		
		checkerOf(userContext).checkAddressIdOfWechatUser(addressId);
		
		checkerOf(userContext).checkPlatformIdOfWechatUser(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);


	}
	public  UserType addWechatUser(HealthUserContext userContext, String userTypeId, String name, String avatar, String addressId, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingWechatUser(userContext,userTypeId,name, avatar, addressId, platformId,tokensExpr);

		WechatUser wechatUser = createWechatUser(userContext,name, avatar, addressId, platformId);

		UserType userType = loadUserType(userContext, userTypeId, emptyOptions());
		synchronized(userType){
			//Will be good when the userType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userType.addWechatUser( wechatUser );
			userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
			
			userContext.getManagerGroup().getWechatUserManager().onNewInstanceCreated(userContext, wechatUser);
			return present(userContext,userType, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingWechatUserProperties(HealthUserContext userContext, String userTypeId,String id,String name,String avatar,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).checkIdOfWechatUser(id);

		checkerOf(userContext).checkNameOfWechatUser( name);
		checkerOf(userContext).checkAvatarOfWechatUser( avatar);

		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);

	}
	public  UserType updateWechatUserProperties(HealthUserContext userContext, String userTypeId, String id,String name,String avatar, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatUserProperties(userContext,userTypeId,id,name,avatar,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withWechatUserListList()
				.searchWechatUserListWith(WechatUser.ID_PROPERTY, "is", id).done();

		UserType userTypeToUpdate = loadUserType(userContext, userTypeId, options);

		if(userTypeToUpdate.getWechatUserList().isEmpty()){
			throw new UserTypeManagerException("WechatUser is NOT FOUND with id: '"+id+"'");
		}

		WechatUser item = userTypeToUpdate.getWechatUserList().first();

		item.updateName( name );
		item.updateAvatar( avatar );


		//checkParamsForAddingWechatUser(userContext,userTypeId,name, code, used,tokensExpr);
		UserType userType = saveUserType(userContext, userTypeToUpdate, tokens().withWechatUserList().done());
		synchronized(userType){
			return present(userContext,userType, mergedAllTokens(tokensExpr));
		}
	}


	protected WechatUser createWechatUser(HealthUserContext userContext, String name, String avatar, String addressId, String platformId) throws Exception{

		WechatUser wechatUser = new WechatUser();
		
		
		wechatUser.setName(name);		
		wechatUser.setAvatar(avatar);		
		Location  address = new Location();
		address.setId(addressId);		
		wechatUser.setAddress(address);		
		wechatUser.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		wechatUser.setPlatform(platform);
	
		
		return wechatUser;


	}

	protected WechatUser createIndexedWechatUser(String id, int version){

		WechatUser wechatUser = new WechatUser();
		wechatUser.setId(id);
		wechatUser.setVersion(version);
		return wechatUser;

	}

	protected void checkParamsForRemovingWechatUserList(HealthUserContext userContext, String userTypeId,
			String wechatUserIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUserType(userTypeId);
		for(String wechatUserIdItem: wechatUserIds){
			checkerOf(userContext).checkIdOfWechatUser(wechatUserIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);

	}
	public  UserType removeWechatUserList(HealthUserContext userContext, String userTypeId,
			String wechatUserIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingWechatUserList(userContext, userTypeId,  wechatUserIds, tokensExpr);


			UserType userType = loadUserType(userContext, userTypeId, allTokens());
			synchronized(userType){
				//Will be good when the userType loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userTypeDaoOf(userContext).planToRemoveWechatUserList(userType, wechatUserIds, allTokens());
				userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
				deleteRelationListInGraph(userContext, userType.getWechatUserList());
				return present(userContext,userType, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingWechatUser(HealthUserContext userContext, String userTypeId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUserType( userTypeId);
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser(wechatUserVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);

	}
	public  UserType removeWechatUser(HealthUserContext userContext, String userTypeId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingWechatUser(userContext,userTypeId, wechatUserId, wechatUserVersion,tokensExpr);

		WechatUser wechatUser = createIndexedWechatUser(wechatUserId, wechatUserVersion);
		UserType userType = loadUserType(userContext, userTypeId, allTokens());
		synchronized(userType){
			//Will be good when the userType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			userType.removeWechatUser( wechatUser );
			userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
			deleteRelationInGraph(userContext, wechatUser);
			return present(userContext,userType, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingWechatUser(HealthUserContext userContext, String userTypeId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUserType( userTypeId);
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser(wechatUserVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);

	}
	public  UserType copyWechatUserFrom(HealthUserContext userContext, String userTypeId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingWechatUser(userContext,userTypeId, wechatUserId, wechatUserVersion,tokensExpr);

		WechatUser wechatUser = createIndexedWechatUser(wechatUserId, wechatUserVersion);
		UserType userType = loadUserType(userContext, userTypeId, allTokens());
		synchronized(userType){
			//Will be good when the userType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			userType.copyWechatUserFrom( wechatUser );
			userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
			
			userContext.getManagerGroup().getWechatUserManager().onNewInstanceCreated(userContext, (WechatUser)userType.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,userType, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingWechatUser(HealthUserContext userContext, String userTypeId, String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser(wechatUserVersion);
		

		if(WechatUser.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfWechatUser(parseString(newValueExpr));
		}
		
		if(WechatUser.AVATAR_PROPERTY.equals(property)){
			checkerOf(userContext).checkAvatarOfWechatUser(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserTypeManagerException.class);

	}

	public  UserType updateWechatUser(HealthUserContext userContext, String userTypeId, String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingWechatUser(userContext, userTypeId, wechatUserId, wechatUserVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withWechatUserList().searchWechatUserListWith(WechatUser.ID_PROPERTY, "eq", wechatUserId).done();



		UserType userType = loadUserType(userContext, userTypeId, loadTokens);

		synchronized(userType){
			//Will be good when the userType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//userType.removeWechatUser( wechatUser );
			//make changes to AcceleraterAccount.
			WechatUser wechatUserIndex = createIndexedWechatUser(wechatUserId, wechatUserVersion);

			WechatUser wechatUser = userType.findTheWechatUser(wechatUserIndex);
			if(wechatUser == null){
				throw new UserTypeManagerException(wechatUser+" is NOT FOUND" );
			}

			wechatUser.changeProperty(property, newValueExpr);
			
			userType = saveUserType(userContext, userType, tokens().withWechatUserList().done());
			return present(userContext,userType, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, UserType newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, UserType.INTERNAL_TYPE);
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


