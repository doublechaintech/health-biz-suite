
package com.doublechaintech.health.wechatuser;

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
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.usertype.UserType;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.location.CandidateLocation;
import com.doublechaintech.health.usertype.CandidateUserType;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;






public class WechatUserManagerImpl extends CustomHealthCheckerManager implements WechatUserManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "WechatUser";
	@Override
	public WechatUserDAO daoOf(HealthUserContext userContext) {
		return wechatUserDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws WechatUserManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new WechatUserManagerException(message);

	}



 	protected WechatUser saveWechatUser(HealthUserContext userContext, WechatUser wechatUser, String [] tokensExpr) throws Exception{	
 		//return getWechatUserDAO().save(wechatUser, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveWechatUser(userContext, wechatUser, tokens);
 	}
 	
 	protected WechatUser saveWechatUserDetail(HealthUserContext userContext, WechatUser wechatUser) throws Exception{	

 		
 		return saveWechatUser(userContext, wechatUser, allTokens());
 	}
 	
 	public WechatUser loadWechatUser(HealthUserContext userContext, String wechatUserId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatUserManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		WechatUser wechatUser = loadWechatUser( userContext, wechatUserId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,wechatUser, tokens);
 	}
 	
 	
 	 public WechatUser searchWechatUser(HealthUserContext userContext, String wechatUserId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatUserManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		WechatUser wechatUser = loadWechatUser( userContext, wechatUserId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,wechatUser, tokens);
 	}
 	
 	

 	protected WechatUser present(HealthUserContext userContext, WechatUser wechatUser, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,wechatUser,tokens);
		
		
		WechatUser  wechatUserToPresent = wechatUserDaoOf(userContext).present(wechatUser, tokens);
		
		List<BaseEntity> entityListToNaming = wechatUserToPresent.collectRefercencesFromLists();
		wechatUserDaoOf(userContext).alias(entityListToNaming);
		
		return  wechatUserToPresent;
		
		
	}
 
 	
 	
 	public WechatUser loadWechatUserDetail(HealthUserContext userContext, String wechatUserId) throws Exception{	
 		WechatUser wechatUser = loadWechatUser( userContext, wechatUserId, allTokens());
 		return present(userContext,wechatUser, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String wechatUserId) throws Exception{	
 		WechatUser wechatUser = loadWechatUser( userContext, wechatUserId, viewTokens());
 		return present(userContext,wechatUser, allTokens());
		
 	}
 	protected WechatUser saveWechatUser(HealthUserContext userContext, WechatUser wechatUser, Map<String,Object>tokens) throws Exception{	
 		return wechatUserDaoOf(userContext).save(wechatUser, tokens);
 	}
 	protected WechatUser loadWechatUser(HealthUserContext userContext, String wechatUserId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).throwExceptionIfHasErrors( WechatUserManagerException.class);

 
 		return wechatUserDaoOf(userContext).load(wechatUserId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, WechatUser wechatUser, Map<String, Object> tokens){
		super.addActions(userContext, wechatUser, tokens);
		
		addAction(userContext, wechatUser, tokens,"@create","createWechatUser","createWechatUser/","main","primary");
		addAction(userContext, wechatUser, tokens,"@update","updateWechatUser","updateWechatUser/"+wechatUser.getId()+"/","main","primary");
		addAction(userContext, wechatUser, tokens,"@copy","cloneWechatUser","cloneWechatUser/"+wechatUser.getId()+"/","main","primary");
		
		addAction(userContext, wechatUser, tokens,"wechat_user.transfer_to_address","transferToAnotherAddress","transferToAnotherAddress/"+wechatUser.getId()+"/","main","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.transfer_to_user_type","transferToAnotherUserType","transferToAnotherUserType/"+wechatUser.getId()+"/","main","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+wechatUser.getId()+"/","main","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.addGuardian","addGuardian","addGuardian/"+wechatUser.getId()+"/","guardianList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.removeGuardian","removeGuardian","removeGuardian/"+wechatUser.getId()+"/","guardianList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.updateGuardian","updateGuardian","updateGuardian/"+wechatUser.getId()+"/","guardianList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.copyGuardianFrom","copyGuardianFrom","copyGuardianFrom/"+wechatUser.getId()+"/","guardianList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.addClassQuestion","addClassQuestion","addClassQuestion/"+wechatUser.getId()+"/","classQuestionList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.removeClassQuestion","removeClassQuestion","removeClassQuestion/"+wechatUser.getId()+"/","classQuestionList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.updateClassQuestion","updateClassQuestion","updateClassQuestion/"+wechatUser.getId()+"/","classQuestionList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.copyClassQuestionFrom","copyClassQuestionFrom","copyClassQuestionFrom/"+wechatUser.getId()+"/","classQuestionList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.addClassDailyHealthSurvey","addClassDailyHealthSurvey","addClassDailyHealthSurvey/"+wechatUser.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.removeClassDailyHealthSurvey","removeClassDailyHealthSurvey","removeClassDailyHealthSurvey/"+wechatUser.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.updateClassDailyHealthSurvey","updateClassDailyHealthSurvey","updateClassDailyHealthSurvey/"+wechatUser.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom/"+wechatUser.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.addWechatLoginInfo","addWechatLoginInfo","addWechatLoginInfo/"+wechatUser.getId()+"/","wechatLoginInfoList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.removeWechatLoginInfo","removeWechatLoginInfo","removeWechatLoginInfo/"+wechatUser.getId()+"/","wechatLoginInfoList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.updateWechatLoginInfo","updateWechatLoginInfo","updateWechatLoginInfo/"+wechatUser.getId()+"/","wechatLoginInfoList","primary");
		addAction(userContext, wechatUser, tokens,"wechat_user.copyWechatLoginInfoFrom","copyWechatLoginInfoFrom","copyWechatLoginInfoFrom/"+wechatUser.getId()+"/","wechatLoginInfoList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, WechatUser wechatUser, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public WechatUser createWechatUser(HealthUserContext userContext, String name,String avatar,String addressId,String userTypeId,String platformId) throws Exception
	//public WechatUser createWechatUser(HealthUserContext userContext,String name, String avatar, String addressId, String userTypeId, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfWechatUser(name);
		checkerOf(userContext).checkAvatarOfWechatUser(avatar);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);


		WechatUser wechatUser=createNewWechatUser();	

		wechatUser.setName(name);
		wechatUser.setAvatar(avatar);
			
		Location address = loadLocation(userContext, addressId,emptyOptions());
		wechatUser.setAddress(address);
		
		
			
		UserType userType = loadUserType(userContext, userTypeId,emptyOptions());
		wechatUser.setUserType(userType);
		
		
		wechatUser.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		wechatUser.setPlatform(platform);
		
		

		wechatUser = saveWechatUser(userContext, wechatUser, emptyOptions());
		
		onNewInstanceCreated(userContext, wechatUser);
		return wechatUser;


	}
	protected WechatUser createNewWechatUser()
	{

		return new WechatUser();
	}

	protected void checkParamsForUpdatingWechatUser(HealthUserContext userContext,String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser( wechatUserVersion);
		

		if(WechatUser.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfWechatUser(parseString(newValueExpr));
		}
		if(WechatUser.AVATAR_PROPERTY.equals(property)){
			checkerOf(userContext).checkAvatarOfWechatUser(parseString(newValueExpr));
		}		

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);


	}



	public WechatUser clone(HealthUserContext userContext, String fromWechatUserId) throws Exception{

		return wechatUserDaoOf(userContext).clone(fromWechatUserId, this.allTokens());
	}

	public WechatUser internalSaveWechatUser(HealthUserContext userContext, WechatUser wechatUser) throws Exception
	{
		return internalSaveWechatUser(userContext, wechatUser, allTokens());

	}
	public WechatUser internalSaveWechatUser(HealthUserContext userContext, WechatUser wechatUser, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingWechatUser(userContext, wechatUserId, wechatUserVersion, property, newValueExpr, tokensExpr);


		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatUser.
			if (wechatUser.isChanged()){
			
			}
			wechatUser = saveWechatUser(userContext, wechatUser, options);
			return wechatUser;

		}

	}

	public WechatUser updateWechatUser(HealthUserContext userContext,String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatUser(userContext, wechatUserId, wechatUserVersion, property, newValueExpr, tokensExpr);



		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		if(wechatUser.getVersion() != wechatUserVersion){
			String message = "The target version("+wechatUser.getVersion()+") is not equals to version("+wechatUserVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatUser.
			
			wechatUser.changeProperty(property, newValueExpr);
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().done());
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
			//return saveWechatUser(userContext, wechatUser, tokens().done());
		}

	}

	public WechatUser updateWechatUserProperty(HealthUserContext userContext,String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatUser(userContext, wechatUserId, wechatUserVersion, property, newValueExpr, tokensExpr);

		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		if(wechatUser.getVersion() != wechatUserVersion){
			String message = "The target version("+wechatUser.getVersion()+") is not equals to version("+wechatUserVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to WechatUser.

			wechatUser.changeProperty(property, newValueExpr);
			
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().done());
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
			//return saveWechatUser(userContext, wechatUser, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected WechatUserTokens tokens(){
		return WechatUserTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return WechatUserTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortGuardianListWith("id","desc")
		.sortClassQuestionListWith("id","desc")
		.sortClassDailyHealthSurveyListWith("id","desc")
		.sortWechatLoginInfoListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return WechatUserTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherAddress(HealthUserContext userContext, String wechatUserId, String anotherAddressId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
 		checkerOf(userContext).checkIdOfLocation(anotherAddressId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

 	}
 	public WechatUser transferToAnotherAddress(HealthUserContext userContext, String wechatUserId, String anotherAddressId) throws Exception
 	{
 		checkParamsForTransferingAnotherAddress(userContext, wechatUserId,anotherAddressId);
 
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());	
		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Location address = loadLocation(userContext, anotherAddressId, emptyOptions());		
			wechatUser.updateAddress(address);		
			wechatUser = saveWechatUser(userContext, wechatUser, emptyOptions());
			
			return present(userContext,wechatUser, allTokens());
			
		}

 	}

	


	public CandidateLocation requestCandidateAddress(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateLocation result = new CandidateLocation();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Location> candidateList = locationDaoOf(userContext).requestCandidateLocationForWechatUser(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherUserType(HealthUserContext userContext, String wechatUserId, String anotherUserTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
 		checkerOf(userContext).checkIdOfUserType(anotherUserTypeId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

 	}
 	public WechatUser transferToAnotherUserType(HealthUserContext userContext, String wechatUserId, String anotherUserTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherUserType(userContext, wechatUserId,anotherUserTypeId);
 
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());	
		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			UserType userType = loadUserType(userContext, anotherUserTypeId, emptyOptions());		
			wechatUser.updateUserType(userType);		
			wechatUser = saveWechatUser(userContext, wechatUser, emptyOptions());
			
			return present(userContext,wechatUser, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherUserTypeWithCode(HealthUserContext userContext, String wechatUserId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
 		checkerOf(userContext).checkCodeOfUserType( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

 	}

 	public WechatUser transferToAnotherUserTypeWithCode(HealthUserContext userContext, String wechatUserId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherUserTypeWithCode(userContext, wechatUserId,anotherCode);
 		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			UserType userType = loadUserTypeWithCode(userContext, anotherCode, emptyOptions());
			wechatUser.updateUserType(userType);
			wechatUser = saveWechatUser(userContext, wechatUser, emptyOptions());

			return present(userContext,wechatUser, allTokens());

		}
 	}

	 


	public CandidateUserType requestCandidateUserType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateUserType result = new CandidateUserType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<UserType> candidateList = userTypeDaoOf(userContext).requestCandidateUserTypeForWechatUser(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String wechatUserId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

 	}
 	public WechatUser transferToAnotherPlatform(HealthUserContext userContext, String wechatUserId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, wechatUserId,anotherPlatformId);
 
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());	
		synchronized(wechatUser){
			//will be good when the wechatUser loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			wechatUser.updatePlatform(platform);		
			wechatUser = saveWechatUser(userContext, wechatUser, emptyOptions());
			
			return present(userContext,wechatUser, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForWechatUser(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Location loadLocation(HealthUserContext userContext, String newAddressId, Map<String,Object> options) throws Exception
 	{

 		return locationDaoOf(userContext).load(newAddressId, options);
 	}
 	


	

 	protected UserType loadUserType(HealthUserContext userContext, String newUserTypeId, Map<String,Object> options) throws Exception
 	{

 		return userTypeDaoOf(userContext).load(newUserTypeId, options);
 	}
 	
 	protected UserType loadUserTypeWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return userTypeDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String wechatUserId, int wechatUserVersion) throws Exception {
		//deleteInternal(userContext, wechatUserId, wechatUserVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String wechatUserId, int wechatUserVersion) throws Exception{

		wechatUserDaoOf(userContext).delete(wechatUserId, wechatUserVersion);
	}

	public WechatUser forgetByAll(HealthUserContext userContext, String wechatUserId, int wechatUserVersion) throws Exception {
		return forgetByAllInternal(userContext, wechatUserId, wechatUserVersion);
	}
	protected WechatUser forgetByAllInternal(HealthUserContext userContext,
			String wechatUserId, int wechatUserVersion) throws Exception{

		return wechatUserDaoOf(userContext).disconnectFromAll(wechatUserId, wechatUserVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new WechatUserManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return wechatUserDaoOf(userContext).deleteAll();
	}


	//disconnect WechatUser with address in Guardian
	protected WechatUser breakWithGuardianByAddress(HealthUserContext userContext, String wechatUserId, String addressId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveGuardianListWithAddress(wechatUser, addressId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with platform in Guardian
	protected WechatUser breakWithGuardianByPlatform(HealthUserContext userContext, String wechatUserId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveGuardianListWithPlatform(wechatUser, platformId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with cq in Guardian
	protected WechatUser breakWithGuardianByCq(HealthUserContext userContext, String wechatUserId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveGuardianListWithCq(wechatUser, cqId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with question_type in ClassQuestion
	protected WechatUser breakWithClassQuestionByQuestionType(HealthUserContext userContext, String wechatUserId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveClassQuestionListWithQuestionType(wechatUser, questionTypeId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with question_source in ClassQuestion
	protected WechatUser breakWithClassQuestionByQuestionSource(HealthUserContext userContext, String wechatUserId, String questionSourceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveClassQuestionListWithQuestionSource(wechatUser, questionSourceId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with cq in ClassQuestion
	protected WechatUser breakWithClassQuestionByCq(HealthUserContext userContext, String wechatUserId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveClassQuestionListWithCq(wechatUser, cqId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with school_class in ClassDailyHealthSurvey
	protected WechatUser breakWithClassDailyHealthSurveyBySchoolClass(HealthUserContext userContext, String wechatUserId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithSchoolClass(wechatUser, schoolClassId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with cq in ClassDailyHealthSurvey
	protected WechatUser breakWithClassDailyHealthSurveyByCq(HealthUserContext userContext, String wechatUserId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithCq(wechatUser, cqId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with app_id in WechatLoginInfo
	protected WechatUser breakWithWechatLoginInfoByAppId(HealthUserContext userContext, String wechatUserId, String appIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveWechatLoginInfoListWithAppId(wechatUser, appIdId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
				return wechatUser;
			}
	}
	//disconnect WechatUser with open_id in WechatLoginInfo
	protected WechatUser breakWithWechatLoginInfoByOpenId(HealthUserContext userContext, String wechatUserId, String openIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());

			synchronized(wechatUser){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				wechatUserDaoOf(userContext).planToRemoveWechatLoginInfoListWithOpenId(wechatUser, openIdId, this.emptyOptions());

				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
				return wechatUser;
			}
	}






	protected void checkParamsForAddingGuardian(HealthUserContext userContext, String wechatUserId, String name, String mobile, String addressId, String platformId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfWechatUser(wechatUserId);

		
		checkerOf(userContext).checkNameOfGuardian(name);
		
		checkerOf(userContext).checkMobileOfGuardian(mobile);
		
		checkerOf(userContext).checkAddressIdOfGuardian(addressId);
		
		checkerOf(userContext).checkPlatformIdOfGuardian(platformId);
		
		checkerOf(userContext).checkCqIdOfGuardian(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);


	}
	public  WechatUser addGuardian(HealthUserContext userContext, String wechatUserId, String name, String mobile, String addressId, String platformId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingGuardian(userContext,wechatUserId,name, mobile, addressId, platformId, cqId,tokensExpr);

		Guardian guardian = createGuardian(userContext,name, mobile, addressId, platformId, cqId);

		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, emptyOptions());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.addGuardian( guardian );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
			
			userContext.getManagerGroup().getGuardianManager().onNewInstanceCreated(userContext, guardian);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingGuardianProperties(HealthUserContext userContext, String wechatUserId,String id,String name,String mobile,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfGuardian(id);

		checkerOf(userContext).checkNameOfGuardian( name);
		checkerOf(userContext).checkMobileOfGuardian( mobile);

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser updateGuardianProperties(HealthUserContext userContext, String wechatUserId, String id,String name,String mobile, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingGuardianProperties(userContext,wechatUserId,id,name,mobile,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withGuardianListList()
				.searchGuardianListWith(Guardian.ID_PROPERTY, "is", id).done();

		WechatUser wechatUserToUpdate = loadWechatUser(userContext, wechatUserId, options);

		if(wechatUserToUpdate.getGuardianList().isEmpty()){
			throw new WechatUserManagerException("Guardian is NOT FOUND with id: '"+id+"'");
		}

		Guardian item = wechatUserToUpdate.getGuardianList().first();

		item.updateName( name );
		item.updateMobile( mobile );


		//checkParamsForAddingGuardian(userContext,wechatUserId,name, code, used,tokensExpr);
		WechatUser wechatUser = saveWechatUser(userContext, wechatUserToUpdate, tokens().withGuardianList().done());
		synchronized(wechatUser){
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}


	protected Guardian createGuardian(HealthUserContext userContext, String name, String mobile, String addressId, String platformId, String cqId) throws Exception{

		Guardian guardian = new Guardian();
		
		
		guardian.setName(name);		
		guardian.setMobile(mobile);		
		Location  address = new Location();
		address.setId(addressId);		
		guardian.setAddress(address);		
		guardian.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		guardian.setPlatform(platform);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		guardian.setCq(cq);
	
		
		return guardian;


	}

	protected Guardian createIndexedGuardian(String id, int version){

		Guardian guardian = new Guardian();
		guardian.setId(id);
		guardian.setVersion(version);
		return guardian;

	}

	protected void checkParamsForRemovingGuardianList(HealthUserContext userContext, String wechatUserId,
			String guardianIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		for(String guardianIdItem: guardianIds){
			checkerOf(userContext).checkIdOfGuardian(guardianIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeGuardianList(HealthUserContext userContext, String wechatUserId,
			String guardianIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingGuardianList(userContext, wechatUserId,  guardianIds, tokensExpr);


			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
			synchronized(wechatUser){
				//Will be good when the wechatUser loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				wechatUserDaoOf(userContext).planToRemoveGuardianList(wechatUser, guardianIds, allTokens());
				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
				deleteRelationListInGraph(userContext, wechatUser.getGuardianList());
				return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingGuardian(HealthUserContext userContext, String wechatUserId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeGuardian(HealthUserContext userContext, String wechatUserId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingGuardian(userContext,wechatUserId, guardianId, guardianVersion,tokensExpr);

		Guardian guardian = createIndexedGuardian(guardianId, guardianVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.removeGuardian( guardian );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
			deleteRelationInGraph(userContext, guardian);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingGuardian(HealthUserContext userContext, String wechatUserId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser copyGuardianFrom(HealthUserContext userContext, String wechatUserId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingGuardian(userContext,wechatUserId, guardianId, guardianVersion,tokensExpr);

		Guardian guardian = createIndexedGuardian(guardianId, guardianVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			wechatUser.copyGuardianFrom( guardian );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
			
			userContext.getManagerGroup().getGuardianManager().onNewInstanceCreated(userContext, (Guardian)wechatUser.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingGuardian(HealthUserContext userContext, String wechatUserId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		

		if(Guardian.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfGuardian(parseString(newValueExpr));
		}
		
		if(Guardian.MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkMobileOfGuardian(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}

	public  WechatUser updateGuardian(HealthUserContext userContext, String wechatUserId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingGuardian(userContext, wechatUserId, guardianId, guardianVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withGuardianList().searchGuardianListWith(Guardian.ID_PROPERTY, "eq", guardianId).done();



		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, loadTokens);

		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//wechatUser.removeGuardian( guardian );
			//make changes to AcceleraterAccount.
			Guardian guardianIndex = createIndexedGuardian(guardianId, guardianVersion);

			Guardian guardian = wechatUser.findTheGuardian(guardianIndex);
			if(guardian == null){
				throw new WechatUserManagerException(guardian+" is NOT FOUND" );
			}

			guardian.changeProperty(property, newValueExpr);
			
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withGuardianList().done());
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingClassQuestion(HealthUserContext userContext, String wechatUserId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfWechatUser(wechatUserId);

		
		checkerOf(userContext).checkTopicOfClassQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfClassQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfClassQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfClassQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfClassQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfClassQuestion(optionD);
		
		checkerOf(userContext).checkQuestionSourceIdOfClassQuestion(questionSourceId);
		
		checkerOf(userContext).checkCqIdOfClassQuestion(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);


	}
	public  WechatUser addClassQuestion(HealthUserContext userContext, String wechatUserId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassQuestion(userContext,wechatUserId,topic, questionTypeId, optionA, optionB, optionC, optionD, questionSourceId, cqId,tokensExpr);

		ClassQuestion classQuestion = createClassQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD, questionSourceId, cqId);

		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, emptyOptions());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.addClassQuestion( classQuestion );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, classQuestion);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassQuestionProperties(HealthUserContext userContext, String wechatUserId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfClassQuestion(id);

		checkerOf(userContext).checkTopicOfClassQuestion( topic);
		checkerOf(userContext).checkOptionAOfClassQuestion( optionA);
		checkerOf(userContext).checkOptionBOfClassQuestion( optionB);
		checkerOf(userContext).checkOptionCOfClassQuestion( optionC);
		checkerOf(userContext).checkOptionDOfClassQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser updateClassQuestionProperties(HealthUserContext userContext, String wechatUserId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassQuestionProperties(userContext,wechatUserId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassQuestionListList()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "is", id).done();

		WechatUser wechatUserToUpdate = loadWechatUser(userContext, wechatUserId, options);

		if(wechatUserToUpdate.getClassQuestionList().isEmpty()){
			throw new WechatUserManagerException("ClassQuestion is NOT FOUND with id: '"+id+"'");
		}

		ClassQuestion item = wechatUserToUpdate.getClassQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingClassQuestion(userContext,wechatUserId,name, code, used,tokensExpr);
		WechatUser wechatUser = saveWechatUser(userContext, wechatUserToUpdate, tokens().withClassQuestionList().done());
		synchronized(wechatUser){
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassQuestion createClassQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String cqId) throws Exception{

		ClassQuestion classQuestion = new ClassQuestion();
		
		
		classQuestion.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		classQuestion.setQuestionType(questionType);		
		classQuestion.setOptionA(optionA);		
		classQuestion.setOptionB(optionB);		
		classQuestion.setOptionC(optionC);		
		classQuestion.setOptionD(optionD);		
		QuestionSource  questionSource = new QuestionSource();
		questionSource.setId(questionSourceId);		
		classQuestion.setQuestionSource(questionSource);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		classQuestion.setCq(cq);
	
		
		return classQuestion;


	}

	protected ClassQuestion createIndexedClassQuestion(String id, int version){

		ClassQuestion classQuestion = new ClassQuestion();
		classQuestion.setId(id);
		classQuestion.setVersion(version);
		return classQuestion;

	}

	protected void checkParamsForRemovingClassQuestionList(HealthUserContext userContext, String wechatUserId,
			String classQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		for(String classQuestionIdItem: classQuestionIds){
			checkerOf(userContext).checkIdOfClassQuestion(classQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeClassQuestionList(HealthUserContext userContext, String wechatUserId,
			String classQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassQuestionList(userContext, wechatUserId,  classQuestionIds, tokensExpr);


			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
			synchronized(wechatUser){
				//Will be good when the wechatUser loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				wechatUserDaoOf(userContext).planToRemoveClassQuestionList(wechatUser, classQuestionIds, allTokens());
				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
				deleteRelationListInGraph(userContext, wechatUser.getClassQuestionList());
				return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassQuestion(HealthUserContext userContext, String wechatUserId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeClassQuestion(HealthUserContext userContext, String wechatUserId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassQuestion(userContext,wechatUserId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.removeClassQuestion( classQuestion );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
			deleteRelationInGraph(userContext, classQuestion);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassQuestion(HealthUserContext userContext, String wechatUserId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser copyClassQuestionFrom(HealthUserContext userContext, String wechatUserId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassQuestion(userContext,wechatUserId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			wechatUser.copyClassQuestionFrom( classQuestion );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, (ClassQuestion)wechatUser.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassQuestion(HealthUserContext userContext, String wechatUserId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		

		if(ClassQuestion.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfClassQuestion(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}

	public  WechatUser updateClassQuestion(HealthUserContext userContext, String wechatUserId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassQuestion(userContext, wechatUserId, classQuestionId, classQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassQuestionList().searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "eq", classQuestionId).done();



		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, loadTokens);

		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//wechatUser.removeClassQuestion( classQuestion );
			//make changes to AcceleraterAccount.
			ClassQuestion classQuestionIndex = createIndexedClassQuestion(classQuestionId, classQuestionVersion);

			ClassQuestion classQuestion = wechatUser.findTheClassQuestion(classQuestionIndex);
			if(classQuestion == null){
				throw new WechatUserManagerException(classQuestion+" is NOT FOUND" );
			}

			classQuestion.changeProperty(property, newValueExpr);
			
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassQuestionList().done());
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String name, String schoolClassId, DateTime surveyTime, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfWechatUser(wechatUserId);

		
		checkerOf(userContext).checkNameOfClassDailyHealthSurvey(name);
		
		checkerOf(userContext).checkSchoolClassIdOfClassDailyHealthSurvey(schoolClassId);
		
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(surveyTime);
		
		checkerOf(userContext).checkCqIdOfClassDailyHealthSurvey(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);


	}
	public  WechatUser addClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String name, String schoolClassId, DateTime surveyTime, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassDailyHealthSurvey(userContext,wechatUserId,name, schoolClassId, surveyTime, cqId,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createClassDailyHealthSurvey(userContext,name, schoolClassId, surveyTime, cqId);

		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, emptyOptions());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.addClassDailyHealthSurvey( classDailyHealthSurvey );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, classDailyHealthSurvey);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassDailyHealthSurveyProperties(HealthUserContext userContext, String wechatUserId,String id,String name,DateTime surveyTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(id);

		checkerOf(userContext).checkNameOfClassDailyHealthSurvey( name);
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey( surveyTime);

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser updateClassDailyHealthSurveyProperties(HealthUserContext userContext, String wechatUserId, String id,String name,DateTime surveyTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurveyProperties(userContext,wechatUserId,id,name,surveyTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassDailyHealthSurveyListList()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "is", id).done();

		WechatUser wechatUserToUpdate = loadWechatUser(userContext, wechatUserId, options);

		if(wechatUserToUpdate.getClassDailyHealthSurveyList().isEmpty()){
			throw new WechatUserManagerException("ClassDailyHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		ClassDailyHealthSurvey item = wechatUserToUpdate.getClassDailyHealthSurveyList().first();

		item.updateName( name );
		item.updateSurveyTime( surveyTime );


		//checkParamsForAddingClassDailyHealthSurvey(userContext,wechatUserId,name, code, used,tokensExpr);
		WechatUser wechatUser = saveWechatUser(userContext, wechatUserToUpdate, tokens().withClassDailyHealthSurveyList().done());
		synchronized(wechatUser){
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name, String schoolClassId, DateTime surveyTime, String cqId) throws Exception{

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		
		
		classDailyHealthSurvey.setName(name);		
		SchoolClass  schoolClass = new SchoolClass();
		schoolClass.setId(schoolClassId);		
		classDailyHealthSurvey.setSchoolClass(schoolClass);		
		classDailyHealthSurvey.setSurveyTime(surveyTime);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		classDailyHealthSurvey.setCq(cq);
	
		
		return classDailyHealthSurvey;


	}

	protected ClassDailyHealthSurvey createIndexedClassDailyHealthSurvey(String id, int version){

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(id);
		classDailyHealthSurvey.setVersion(version);
		return classDailyHealthSurvey;

	}

	protected void checkParamsForRemovingClassDailyHealthSurveyList(HealthUserContext userContext, String wechatUserId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		for(String classDailyHealthSurveyIdItem: classDailyHealthSurveyIds){
			checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeClassDailyHealthSurveyList(HealthUserContext userContext, String wechatUserId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassDailyHealthSurveyList(userContext, wechatUserId,  classDailyHealthSurveyIds, tokensExpr);


			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
			synchronized(wechatUser){
				//Will be good when the wechatUser loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				wechatUserDaoOf(userContext).planToRemoveClassDailyHealthSurveyList(wechatUser, classDailyHealthSurveyIds, allTokens());
				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
				deleteRelationListInGraph(userContext, wechatUser.getClassDailyHealthSurveyList());
				return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassDailyHealthSurvey(userContext,wechatUserId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
			deleteRelationInGraph(userContext, classDailyHealthSurvey);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser copyClassDailyHealthSurveyFrom(HealthUserContext userContext, String wechatUserId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassDailyHealthSurvey(userContext,wechatUserId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			wechatUser.copyClassDailyHealthSurveyFrom( classDailyHealthSurvey );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, (ClassDailyHealthSurvey)wechatUser.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		

		if(ClassDailyHealthSurvey.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfClassDailyHealthSurvey(parseString(newValueExpr));
		}
		
		if(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}

	public  WechatUser updateClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassDailyHealthSurvey(userContext, wechatUserId, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassDailyHealthSurveyList().searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "eq", classDailyHealthSurveyId).done();



		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, loadTokens);

		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//wechatUser.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			//make changes to AcceleraterAccount.
			ClassDailyHealthSurvey classDailyHealthSurveyIndex = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);

			ClassDailyHealthSurvey classDailyHealthSurvey = wechatUser.findTheClassDailyHealthSurvey(classDailyHealthSurveyIndex);
			if(classDailyHealthSurvey == null){
				throw new WechatUserManagerException(classDailyHealthSurvey+" is NOT FOUND" );
			}

			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withClassDailyHealthSurveyList().done());
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String appId, String openId, String sessionKey,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfWechatUser(wechatUserId);

		
		checkerOf(userContext).checkAppIdOfWechatLoginInfo(appId);
		
		checkerOf(userContext).checkOpenIdOfWechatLoginInfo(openId);
		
		checkerOf(userContext).checkSessionKeyOfWechatLoginInfo(sessionKey);
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);


	}
	public  WechatUser addWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String appId, String openId, String sessionKey, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingWechatLoginInfo(userContext,wechatUserId,appId, openId, sessionKey,tokensExpr);

		WechatLoginInfo wechatLoginInfo = createWechatLoginInfo(userContext,appId, openId, sessionKey);

		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, emptyOptions());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.addWechatLoginInfo( wechatLoginInfo );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
			
			userContext.getManagerGroup().getWechatLoginInfoManager().onNewInstanceCreated(userContext, wechatLoginInfo);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingWechatLoginInfoProperties(HealthUserContext userContext, String wechatUserId,String id,String appId,String openId,String sessionKey,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(id);

		checkerOf(userContext).checkAppIdOfWechatLoginInfo( appId);
		checkerOf(userContext).checkOpenIdOfWechatLoginInfo( openId);
		checkerOf(userContext).checkSessionKeyOfWechatLoginInfo( sessionKey);

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser updateWechatLoginInfoProperties(HealthUserContext userContext, String wechatUserId, String id,String appId,String openId,String sessionKey, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatLoginInfoProperties(userContext,wechatUserId,id,appId,openId,sessionKey,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withWechatLoginInfoListList()
				.searchWechatLoginInfoListWith(WechatLoginInfo.ID_PROPERTY, "is", id).done();

		WechatUser wechatUserToUpdate = loadWechatUser(userContext, wechatUserId, options);

		if(wechatUserToUpdate.getWechatLoginInfoList().isEmpty()){
			throw new WechatUserManagerException("WechatLoginInfo is NOT FOUND with id: '"+id+"'");
		}

		WechatLoginInfo item = wechatUserToUpdate.getWechatLoginInfoList().first();

		item.updateAppId( appId );
		item.updateOpenId( openId );
		item.updateSessionKey( sessionKey );


		//checkParamsForAddingWechatLoginInfo(userContext,wechatUserId,name, code, used,tokensExpr);
		WechatUser wechatUser = saveWechatUser(userContext, wechatUserToUpdate, tokens().withWechatLoginInfoList().done());
		synchronized(wechatUser){
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}
	}


	protected WechatLoginInfo createWechatLoginInfo(HealthUserContext userContext, String appId, String openId, String sessionKey) throws Exception{

		WechatLoginInfo wechatLoginInfo = new WechatLoginInfo();
		
		
		wechatLoginInfo.setAppId(appId);		
		wechatLoginInfo.setOpenId(openId);		
		wechatLoginInfo.setSessionKey(sessionKey);		
		wechatLoginInfo.setLastUpdateTime(userContext.now());
	
		
		return wechatLoginInfo;


	}

	protected WechatLoginInfo createIndexedWechatLoginInfo(String id, int version){

		WechatLoginInfo wechatLoginInfo = new WechatLoginInfo();
		wechatLoginInfo.setId(id);
		wechatLoginInfo.setVersion(version);
		return wechatLoginInfo;

	}

	protected void checkParamsForRemovingWechatLoginInfoList(HealthUserContext userContext, String wechatUserId,
			String wechatLoginInfoIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		for(String wechatLoginInfoIdItem: wechatLoginInfoIds){
			checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeWechatLoginInfoList(HealthUserContext userContext, String wechatUserId,
			String wechatLoginInfoIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingWechatLoginInfoList(userContext, wechatUserId,  wechatLoginInfoIds, tokensExpr);


			WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
			synchronized(wechatUser){
				//Will be good when the wechatUser loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				wechatUserDaoOf(userContext).planToRemoveWechatLoginInfoList(wechatUser, wechatLoginInfoIds, allTokens());
				wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
				deleteRelationListInGraph(userContext, wechatUser.getWechatLoginInfoList());
				return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingWechatLoginInfo(HealthUserContext userContext, String wechatUserId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).checkVersionOfWechatLoginInfo(wechatLoginInfoVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser removeWechatLoginInfo(HealthUserContext userContext, String wechatUserId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingWechatLoginInfo(userContext,wechatUserId, wechatLoginInfoId, wechatLoginInfoVersion,tokensExpr);

		WechatLoginInfo wechatLoginInfo = createIndexedWechatLoginInfo(wechatLoginInfoId, wechatLoginInfoVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			wechatUser.removeWechatLoginInfo( wechatLoginInfo );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
			deleteRelationInGraph(userContext, wechatLoginInfo);
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingWechatLoginInfo(HealthUserContext userContext, String wechatUserId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfWechatUser( wechatUserId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).checkVersionOfWechatLoginInfo(wechatLoginInfoVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}
	public  WechatUser copyWechatLoginInfoFrom(HealthUserContext userContext, String wechatUserId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingWechatLoginInfo(userContext,wechatUserId, wechatLoginInfoId, wechatLoginInfoVersion,tokensExpr);

		WechatLoginInfo wechatLoginInfo = createIndexedWechatLoginInfo(wechatLoginInfoId, wechatLoginInfoVersion);
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, allTokens());
		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			wechatLoginInfo.updateLastUpdateTime(userContext.now());

			wechatUser.copyWechatLoginInfoFrom( wechatLoginInfo );
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
			
			userContext.getManagerGroup().getWechatLoginInfoManager().onNewInstanceCreated(userContext, (WechatLoginInfo)wechatUser.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).checkVersionOfWechatLoginInfo(wechatLoginInfoVersion);
		

		if(WechatLoginInfo.APP_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkAppIdOfWechatLoginInfo(parseString(newValueExpr));
		}
		
		if(WechatLoginInfo.OPEN_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkOpenIdOfWechatLoginInfo(parseString(newValueExpr));
		}
		
		if(WechatLoginInfo.SESSION_KEY_PROPERTY.equals(property)){
			checkerOf(userContext).checkSessionKeyOfWechatLoginInfo(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(WechatUserManagerException.class);

	}

	public  WechatUser updateWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingWechatLoginInfo(userContext, wechatUserId, wechatLoginInfoId, wechatLoginInfoVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withWechatLoginInfoList().searchWechatLoginInfoListWith(WechatLoginInfo.ID_PROPERTY, "eq", wechatLoginInfoId).done();



		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId, loadTokens);

		synchronized(wechatUser){
			//Will be good when the wechatUser loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//wechatUser.removeWechatLoginInfo( wechatLoginInfo );
			//make changes to AcceleraterAccount.
			WechatLoginInfo wechatLoginInfoIndex = createIndexedWechatLoginInfo(wechatLoginInfoId, wechatLoginInfoVersion);

			WechatLoginInfo wechatLoginInfo = wechatUser.findTheWechatLoginInfo(wechatLoginInfoIndex);
			if(wechatLoginInfo == null){
				throw new WechatUserManagerException(wechatLoginInfo+" is NOT FOUND" );
			}

			wechatLoginInfo.changeProperty(property, newValueExpr);
			wechatLoginInfo.updateLastUpdateTime(userContext.now());
			wechatUser = saveWechatUser(userContext, wechatUser, tokens().withWechatLoginInfoList().done());
			return present(userContext,wechatUser, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, WechatUser newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, WechatUser.INTERNAL_TYPE);
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


