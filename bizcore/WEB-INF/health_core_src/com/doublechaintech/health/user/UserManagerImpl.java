
package com.doublechaintech.health.user;

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
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.question.Question;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.location.CandidateLocation;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.user.User;






public class UserManagerImpl extends CustomHealthCheckerManager implements UserManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "User";
	@Override
	public UserDAO daoOf(HealthUserContext userContext) {
		return userDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws UserManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new UserManagerException(message);

	}



 	protected User saveUser(HealthUserContext userContext, User user, String [] tokensExpr) throws Exception{	
 		//return getUserDAO().save(user, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveUser(userContext, user, tokens);
 	}
 	
 	protected User saveUserDetail(HealthUserContext userContext, User user) throws Exception{	

 		
 		return saveUser(userContext, user, allTokens());
 	}
 	
 	public User loadUser(HealthUserContext userContext, String userId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		User user = loadUser( userContext, userId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,user, tokens);
 	}
 	
 	
 	 public User searchUser(HealthUserContext userContext, String userId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		User user = loadUser( userContext, userId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,user, tokens);
 	}
 	
 	

 	protected User present(HealthUserContext userContext, User user, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,user,tokens);
		
		
		User  userToPresent = userDaoOf(userContext).present(user, tokens);
		
		List<BaseEntity> entityListToNaming = userToPresent.collectRefercencesFromLists();
		userDaoOf(userContext).alias(entityListToNaming);
		
		return  userToPresent;
		
		
	}
 
 	
 	
 	public User loadUserDetail(HealthUserContext userContext, String userId) throws Exception{	
 		User user = loadUser( userContext, userId, allTokens());
 		return present(userContext,user, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String userId) throws Exception{	
 		User user = loadUser( userContext, userId, viewTokens());
 		return present(userContext,user, allTokens());
		
 	}
 	protected User saveUser(HealthUserContext userContext, User user, Map<String,Object>tokens) throws Exception{	
 		return userDaoOf(userContext).save(user, tokens);
 	}
 	protected User loadUser(HealthUserContext userContext, String userId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserManagerException.class);

 
 		return userDaoOf(userContext).load(userId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, User user, Map<String, Object> tokens){
		super.addActions(userContext, user, tokens);
		
		addAction(userContext, user, tokens,"@create","createUser","createUser/","main","primary");
		addAction(userContext, user, tokens,"@update","updateUser","updateUser/"+user.getId()+"/","main","primary");
		addAction(userContext, user, tokens,"@copy","cloneUser","cloneUser/"+user.getId()+"/","main","primary");
		
		addAction(userContext, user, tokens,"user.transfer_to_address","transferToAnotherAddress","transferToAnotherAddress/"+user.getId()+"/","main","primary");
		addAction(userContext, user, tokens,"user.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+user.getId()+"/","main","primary");
		addAction(userContext, user, tokens,"user.addStudent","addStudent","addStudent/"+user.getId()+"/","studentList","primary");
		addAction(userContext, user, tokens,"user.removeStudent","removeStudent","removeStudent/"+user.getId()+"/","studentList","primary");
		addAction(userContext, user, tokens,"user.updateStudent","updateStudent","updateStudent/"+user.getId()+"/","studentList","primary");
		addAction(userContext, user, tokens,"user.copyStudentFrom","copyStudentFrom","copyStudentFrom/"+user.getId()+"/","studentList","primary");
		addAction(userContext, user, tokens,"user.addQuestion","addQuestion","addQuestion/"+user.getId()+"/","questionList","primary");
		addAction(userContext, user, tokens,"user.removeQuestion","removeQuestion","removeQuestion/"+user.getId()+"/","questionList","primary");
		addAction(userContext, user, tokens,"user.updateQuestion","updateQuestion","updateQuestion/"+user.getId()+"/","questionList","primary");
		addAction(userContext, user, tokens,"user.copyQuestionFrom","copyQuestionFrom","copyQuestionFrom/"+user.getId()+"/","questionList","primary");
		addAction(userContext, user, tokens,"user.addClassDailyHealthSurvey","addClassDailyHealthSurvey","addClassDailyHealthSurvey/"+user.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, user, tokens,"user.removeClassDailyHealthSurvey","removeClassDailyHealthSurvey","removeClassDailyHealthSurvey/"+user.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, user, tokens,"user.updateClassDailyHealthSurvey","updateClassDailyHealthSurvey","updateClassDailyHealthSurvey/"+user.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, user, tokens,"user.copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom/"+user.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, user, tokens,"user.addWechatLoginInfo","addWechatLoginInfo","addWechatLoginInfo/"+user.getId()+"/","wechatLoginInfoList","primary");
		addAction(userContext, user, tokens,"user.removeWechatLoginInfo","removeWechatLoginInfo","removeWechatLoginInfo/"+user.getId()+"/","wechatLoginInfoList","primary");
		addAction(userContext, user, tokens,"user.updateWechatLoginInfo","updateWechatLoginInfo","updateWechatLoginInfo/"+user.getId()+"/","wechatLoginInfoList","primary");
		addAction(userContext, user, tokens,"user.copyWechatLoginInfoFrom","copyWechatLoginInfoFrom","copyWechatLoginInfoFrom/"+user.getId()+"/","wechatLoginInfoList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, User user, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public User createUser(HealthUserContext userContext, String name,String avatar,String addressId,String platformId) throws Exception
	//public User createUser(HealthUserContext userContext,String name, String avatar, String addressId, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfUser(name);
		checkerOf(userContext).checkAvatarOfUser(avatar);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


		User user=createNewUser();	

		user.setName(name);
		user.setAvatar(avatar);
			
		Location address = loadLocation(userContext, addressId,emptyOptions());
		user.setAddress(address);
		
		
		user.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		user.setPlatform(platform);
		
		

		user = saveUser(userContext, user, emptyOptions());
		
		onNewInstanceCreated(userContext, user);
		return user;


	}
	protected User createNewUser()
	{

		return new User();
	}

	protected void checkParamsForUpdatingUser(HealthUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser( userVersion);
		

		if(User.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfUser(parseString(newValueExpr));
		}
		if(User.AVATAR_PROPERTY.equals(property)){
			checkerOf(userContext).checkAvatarOfUser(parseString(newValueExpr));
		}		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}



	public User clone(HealthUserContext userContext, String fromUserId) throws Exception{

		return userDaoOf(userContext).clone(fromUserId, this.allTokens());
	}

	public User internalSaveUser(HealthUserContext userContext, User user) throws Exception
	{
		return internalSaveUser(userContext, user, allTokens());

	}
	public User internalSaveUser(HealthUserContext userContext, User user, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingUser(userContext, userId, userVersion, property, newValueExpr, tokensExpr);


		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to User.
			if (user.isChanged()){
			
			}
			user = saveUser(userContext, user, options);
			return user;

		}

	}

	public User updateUser(HealthUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUser(userContext, userId, userVersion, property, newValueExpr, tokensExpr);



		User user = loadUser(userContext, userId, allTokens());
		if(user.getVersion() != userVersion){
			String message = "The target version("+user.getVersion()+") is not equals to version("+userVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to User.
			
			user.changeProperty(property, newValueExpr);
			user = saveUser(userContext, user, tokens().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
			//return saveUser(userContext, user, tokens().done());
		}

	}

	public User updateUserProperty(HealthUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUser(userContext, userId, userVersion, property, newValueExpr, tokensExpr);

		User user = loadUser(userContext, userId, allTokens());
		if(user.getVersion() != userVersion){
			String message = "The target version("+user.getVersion()+") is not equals to version("+userVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to User.

			user.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
			//return saveUser(userContext, user, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected UserTokens tokens(){
		return UserTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return UserTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentListWith("id","desc")
		.sortQuestionListWith("id","desc")
		.sortClassDailyHealthSurveyListWith("id","desc")
		.sortWechatLoginInfoListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return UserTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherAddress(HealthUserContext userContext, String userId, String anotherAddressId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfUser(userId);
 		checkerOf(userContext).checkIdOfLocation(anotherAddressId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

 	}
 	public User transferToAnotherAddress(HealthUserContext userContext, String userId, String anotherAddressId) throws Exception
 	{
 		checkParamsForTransferingAnotherAddress(userContext, userId,anotherAddressId);
 
		User user = loadUser(userContext, userId, allTokens());	
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Location address = loadLocation(userContext, anotherAddressId, emptyOptions());		
			user.updateAddress(address);		
			user = saveUser(userContext, user, emptyOptions());
			
			return present(userContext,user, allTokens());
			
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
		SmartList<Location> candidateList = locationDaoOf(userContext).requestCandidateLocationForUser(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String userId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfUser(userId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

 	}
 	public User transferToAnotherPlatform(HealthUserContext userContext, String userId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, userId,anotherPlatformId);
 
		User user = loadUser(userContext, userId, allTokens());	
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			user.updatePlatform(platform);		
			user = saveUser(userContext, user, emptyOptions());
			
			return present(userContext,user, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForUser(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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
 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String userId, int userVersion) throws Exception {
		//deleteInternal(userContext, userId, userVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String userId, int userVersion) throws Exception{

		userDaoOf(userContext).delete(userId, userVersion);
	}

	public User forgetByAll(HealthUserContext userContext, String userId, int userVersion) throws Exception {
		return forgetByAllInternal(userContext, userId, userVersion);
	}
	protected User forgetByAllInternal(HealthUserContext userContext,
			String userId, int userVersion) throws Exception{

		return userDaoOf(userContext).disconnectFromAll(userId, userVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new UserManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return userDaoOf(userContext).deleteAll();
	}


	//disconnect User with student_id in Student
	protected User breakWithStudentByStudentId(HealthUserContext userContext, String userId, String studentIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveStudentListWithStudentId(user, studentIdId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withStudentList().done());
				return user;
			}
	}
	//disconnect User with address in Student
	protected User breakWithStudentByAddress(HealthUserContext userContext, String userId, String addressId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveStudentListWithAddress(user, addressId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withStudentList().done());
				return user;
			}
	}
	//disconnect User with platform in Student
	protected User breakWithStudentByPlatform(HealthUserContext userContext, String userId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveStudentListWithPlatform(user, platformId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withStudentList().done());
				return user;
			}
	}
	//disconnect User with change_request in Student
	protected User breakWithStudentByChangeRequest(HealthUserContext userContext, String userId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveStudentListWithChangeRequest(user, changeRequestId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withStudentList().done());
				return user;
			}
	}
	//disconnect User with question_type in Question
	protected User breakWithQuestionByQuestionType(HealthUserContext userContext, String userId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveQuestionListWithQuestionType(user, questionTypeId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withQuestionList().done());
				return user;
			}
	}
	//disconnect User with platform in Question
	protected User breakWithQuestionByPlatform(HealthUserContext userContext, String userId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveQuestionListWithPlatform(user, platformId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withQuestionList().done());
				return user;
			}
	}
	//disconnect User with cq in Question
	protected User breakWithQuestionByCq(HealthUserContext userContext, String userId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveQuestionListWithCq(user, cqId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withQuestionList().done());
				return user;
			}
	}
	//disconnect User with teacher in ClassDailyHealthSurvey
	protected User breakWithClassDailyHealthSurveyByTeacher(HealthUserContext userContext, String userId, String teacherId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithTeacher(user, teacherId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
				return user;
			}
	}
	//disconnect User with change_request in ClassDailyHealthSurvey
	protected User breakWithClassDailyHealthSurveyByChangeRequest(HealthUserContext userContext, String userId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithChangeRequest(user, changeRequestId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
				return user;
			}
	}
	//disconnect User with app_id in WechatLoginInfo
	protected User breakWithWechatLoginInfoByAppId(HealthUserContext userContext, String userId, String appIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveWechatLoginInfoListWithAppId(user, appIdId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
				return user;
			}
	}
	//disconnect User with open_id in WechatLoginInfo
	protected User breakWithWechatLoginInfoByOpenId(HealthUserContext userContext, String userId, String openIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveWechatLoginInfoListWithOpenId(user, openIdId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
				return user;
			}
	}






	protected void checkParamsForAddingStudent(HealthUserContext userContext, String userId, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String platformId, String changeRequestId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkStudentNameOfStudent(studentName);
		
		checkerOf(userContext).checkStudentIdOfStudent(studentId);
		
		checkerOf(userContext).checkGuardianNameOfStudent(guardianName);
		
		checkerOf(userContext).checkGuardianMobileOfStudent(guardianMobile);
		
		checkerOf(userContext).checkAddressIdOfStudent(addressId);
		
		checkerOf(userContext).checkPlatformIdOfStudent(platformId);
		
		checkerOf(userContext).checkChangeRequestIdOfStudent(changeRequestId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addStudent(HealthUserContext userContext, String userId, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String platformId, String changeRequestId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudent(userContext,userId,studentName, studentId, guardianName, guardianMobile, addressId, platformId, changeRequestId,tokensExpr);

		Student student = createStudent(userContext,studentName, studentId, guardianName, guardianMobile, addressId, platformId, changeRequestId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addStudent( student );
			user = saveUser(userContext, user, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, student);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentProperties(HealthUserContext userContext, String userId,String id,String studentName,String studentId,String guardianName,String guardianMobile,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfStudent(id);

		checkerOf(userContext).checkStudentNameOfStudent( studentName);
		checkerOf(userContext).checkStudentIdOfStudent( studentId);
		checkerOf(userContext).checkGuardianNameOfStudent( guardianName);
		checkerOf(userContext).checkGuardianMobileOfStudent( guardianMobile);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateStudentProperties(HealthUserContext userContext, String userId, String id,String studentName,String studentId,String guardianName,String guardianMobile, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentProperties(userContext,userId,id,studentName,studentId,guardianName,guardianMobile,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentListList()
				.searchStudentListWith(Student.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getStudentList().isEmpty()){
			throw new UserManagerException("Student is NOT FOUND with id: '"+id+"'");
		}

		Student item = userToUpdate.getStudentList().first();

		item.updateStudentName( studentName );
		item.updateStudentId( studentId );
		item.updateGuardianName( guardianName );
		item.updateGuardianMobile( guardianMobile );


		//checkParamsForAddingStudent(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withStudentList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected Student createStudent(HealthUserContext userContext, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String platformId, String changeRequestId) throws Exception{

		Student student = new Student();
		
		
		student.setStudentName(studentName);		
		student.setStudentId(studentId);		
		student.setGuardianName(guardianName);		
		student.setGuardianMobile(guardianMobile);		
		Location  address = new Location();
		address.setId(addressId);		
		student.setAddress(address);		
		student.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		student.setPlatform(platform);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		student.setChangeRequest(changeRequest);
	
		
		return student;


	}

	protected Student createIndexedStudent(String id, int version){

		Student student = new Student();
		student.setId(id);
		student.setVersion(version);
		return student;

	}

	protected void checkParamsForRemovingStudentList(HealthUserContext userContext, String userId,
			String studentIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String studentIdItem: studentIds){
			checkerOf(userContext).checkIdOfStudent(studentIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeStudentList(HealthUserContext userContext, String userId,
			String studentIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentList(userContext, userId,  studentIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveStudentList(user, studentIds, allTokens());
				user = saveUser(userContext, user, tokens().withStudentList().done());
				deleteRelationListInGraph(userContext, user.getStudentList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudent(HealthUserContext userContext, String userId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeStudent(HealthUserContext userContext, String userId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudent(userContext,userId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeStudent( student );
			user = saveUser(userContext, user, tokens().withStudentList().done());
			deleteRelationInGraph(userContext, student);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudent(HealthUserContext userContext, String userId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyStudentFrom(HealthUserContext userContext, String userId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudent(userContext,userId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyStudentFrom( student );
			user = saveUser(userContext, user, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, (Student)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudent(HealthUserContext userContext, String userId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		

		if(Student.STUDENT_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentNameOfStudent(parseString(newValueExpr));
		}
		
		if(Student.STUDENT_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentIdOfStudent(parseString(newValueExpr));
		}
		
		if(Student.GUARDIAN_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkGuardianNameOfStudent(parseString(newValueExpr));
		}
		
		if(Student.GUARDIAN_MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkGuardianMobileOfStudent(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateStudent(HealthUserContext userContext, String userId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudent(userContext, userId, studentId, studentVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentList().searchStudentListWith(Student.ID_PROPERTY, "eq", studentId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeStudent( student );
			//make changes to AcceleraterAccount.
			Student studentIndex = createIndexedStudent(studentId, studentVersion);

			Student student = user.findTheStudent(studentIndex);
			if(student == null){
				throw new UserManagerException(student+" is NOT FOUND" );
			}

			student.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withStudentList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingQuestion(HealthUserContext userContext, String userId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkTopicOfQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfQuestion(optionD);
		
		checkerOf(userContext).checkPlatformIdOfQuestion(platformId);
		
		checkerOf(userContext).checkCqIdOfQuestion(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addQuestion(HealthUserContext userContext, String userId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingQuestion(userContext,userId,topic, questionTypeId, optionA, optionB, optionC, optionD, platformId, cqId,tokensExpr);

		Question question = createQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD, platformId, cqId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addQuestion( question );
			user = saveUser(userContext, user, tokens().withQuestionList().done());
			
			userContext.getManagerGroup().getQuestionManager().onNewInstanceCreated(userContext, question);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingQuestionProperties(HealthUserContext userContext, String userId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfQuestion(id);

		checkerOf(userContext).checkTopicOfQuestion( topic);
		checkerOf(userContext).checkOptionAOfQuestion( optionA);
		checkerOf(userContext).checkOptionBOfQuestion( optionB);
		checkerOf(userContext).checkOptionCOfQuestion( optionC);
		checkerOf(userContext).checkOptionDOfQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateQuestionProperties(HealthUserContext userContext, String userId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionProperties(userContext,userId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withQuestionListList()
				.searchQuestionListWith(Question.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getQuestionList().isEmpty()){
			throw new UserManagerException("Question is NOT FOUND with id: '"+id+"'");
		}

		Question item = userToUpdate.getQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingQuestion(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withQuestionList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected Question createQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId) throws Exception{

		Question question = new Question();
		
		
		question.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		question.setQuestionType(questionType);		
		question.setOptionA(optionA);		
		question.setOptionB(optionB);		
		question.setOptionC(optionC);		
		question.setOptionD(optionD);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		question.setPlatform(platform);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		question.setCq(cq);
	
		
		return question;


	}

	protected Question createIndexedQuestion(String id, int version){

		Question question = new Question();
		question.setId(id);
		question.setVersion(version);
		return question;

	}

	protected void checkParamsForRemovingQuestionList(HealthUserContext userContext, String userId,
			String questionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String questionIdItem: questionIds){
			checkerOf(userContext).checkIdOfQuestion(questionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeQuestionList(HealthUserContext userContext, String userId,
			String questionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingQuestionList(userContext, userId,  questionIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveQuestionList(user, questionIds, allTokens());
				user = saveUser(userContext, user, tokens().withQuestionList().done());
				deleteRelationListInGraph(userContext, user.getQuestionList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingQuestion(HealthUserContext userContext, String userId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeQuestion(HealthUserContext userContext, String userId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingQuestion(userContext,userId, questionId, questionVersion,tokensExpr);

		Question question = createIndexedQuestion(questionId, questionVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeQuestion( question );
			user = saveUser(userContext, user, tokens().withQuestionList().done());
			deleteRelationInGraph(userContext, question);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingQuestion(HealthUserContext userContext, String userId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyQuestionFrom(HealthUserContext userContext, String userId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingQuestion(userContext,userId, questionId, questionVersion,tokensExpr);

		Question question = createIndexedQuestion(questionId, questionVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyQuestionFrom( question );
			user = saveUser(userContext, user, tokens().withQuestionList().done());
			
			userContext.getManagerGroup().getQuestionManager().onNewInstanceCreated(userContext, (Question)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingQuestion(HealthUserContext userContext, String userId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		

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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateQuestion(HealthUserContext userContext, String userId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingQuestion(userContext, userId, questionId, questionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withQuestionList().searchQuestionListWith(Question.ID_PROPERTY, "eq", questionId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeQuestion( question );
			//make changes to AcceleraterAccount.
			Question questionIndex = createIndexedQuestion(questionId, questionVersion);

			Question question = user.findTheQuestion(questionIndex);
			if(question == null){
				throw new UserManagerException(question+" is NOT FOUND" );
			}

			question.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withQuestionList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingClassDailyHealthSurvey(HealthUserContext userContext, String userId, String name, String teacherId, DateTime surveyTime, String changeRequestId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkNameOfClassDailyHealthSurvey(name);
		
		checkerOf(userContext).checkTeacherIdOfClassDailyHealthSurvey(teacherId);
		
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(surveyTime);
		
		checkerOf(userContext).checkChangeRequestIdOfClassDailyHealthSurvey(changeRequestId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addClassDailyHealthSurvey(HealthUserContext userContext, String userId, String name, String teacherId, DateTime surveyTime, String changeRequestId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassDailyHealthSurvey(userContext,userId,name, teacherId, surveyTime, changeRequestId,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createClassDailyHealthSurvey(userContext,name, teacherId, surveyTime, changeRequestId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addClassDailyHealthSurvey( classDailyHealthSurvey );
			user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, classDailyHealthSurvey);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassDailyHealthSurveyProperties(HealthUserContext userContext, String userId,String id,String name,DateTime surveyTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(id);

		checkerOf(userContext).checkNameOfClassDailyHealthSurvey( name);
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey( surveyTime);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateClassDailyHealthSurveyProperties(HealthUserContext userContext, String userId, String id,String name,DateTime surveyTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurveyProperties(userContext,userId,id,name,surveyTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassDailyHealthSurveyListList()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getClassDailyHealthSurveyList().isEmpty()){
			throw new UserManagerException("ClassDailyHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		ClassDailyHealthSurvey item = userToUpdate.getClassDailyHealthSurveyList().first();

		item.updateName( name );
		item.updateSurveyTime( surveyTime );


		//checkParamsForAddingClassDailyHealthSurvey(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withClassDailyHealthSurveyList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name, String teacherId, DateTime surveyTime, String changeRequestId) throws Exception{

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		
		
		classDailyHealthSurvey.setName(name);		
		Teacher  teacher = new Teacher();
		teacher.setId(teacherId);		
		classDailyHealthSurvey.setTeacher(teacher);		
		classDailyHealthSurvey.setSurveyTime(surveyTime);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		classDailyHealthSurvey.setChangeRequest(changeRequest);
	
		
		return classDailyHealthSurvey;


	}

	protected ClassDailyHealthSurvey createIndexedClassDailyHealthSurvey(String id, int version){

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(id);
		classDailyHealthSurvey.setVersion(version);
		return classDailyHealthSurvey;

	}

	protected void checkParamsForRemovingClassDailyHealthSurveyList(HealthUserContext userContext, String userId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String classDailyHealthSurveyIdItem: classDailyHealthSurveyIds){
			checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeClassDailyHealthSurveyList(HealthUserContext userContext, String userId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassDailyHealthSurveyList(userContext, userId,  classDailyHealthSurveyIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveClassDailyHealthSurveyList(user, classDailyHealthSurveyIds, allTokens());
				user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
				deleteRelationListInGraph(userContext, user.getClassDailyHealthSurveyList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassDailyHealthSurvey(HealthUserContext userContext, String userId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeClassDailyHealthSurvey(HealthUserContext userContext, String userId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassDailyHealthSurvey(userContext,userId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
			deleteRelationInGraph(userContext, classDailyHealthSurvey);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassDailyHealthSurvey(HealthUserContext userContext, String userId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyClassDailyHealthSurveyFrom(HealthUserContext userContext, String userId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassDailyHealthSurvey(userContext,userId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyClassDailyHealthSurveyFrom( classDailyHealthSurvey );
			user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, (ClassDailyHealthSurvey)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassDailyHealthSurvey(HealthUserContext userContext, String userId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		

		if(ClassDailyHealthSurvey.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfClassDailyHealthSurvey(parseString(newValueExpr));
		}
		
		if(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateClassDailyHealthSurvey(HealthUserContext userContext, String userId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassDailyHealthSurvey(userContext, userId, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassDailyHealthSurveyList().searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "eq", classDailyHealthSurveyId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			//make changes to AcceleraterAccount.
			ClassDailyHealthSurvey classDailyHealthSurveyIndex = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);

			ClassDailyHealthSurvey classDailyHealthSurvey = user.findTheClassDailyHealthSurvey(classDailyHealthSurveyIndex);
			if(classDailyHealthSurvey == null){
				throw new UserManagerException(classDailyHealthSurvey+" is NOT FOUND" );
			}

			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withClassDailyHealthSurveyList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingWechatLoginInfo(HealthUserContext userContext, String userId, String appId, String openId, String sessionKey,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkAppIdOfWechatLoginInfo(appId);
		
		checkerOf(userContext).checkOpenIdOfWechatLoginInfo(openId);
		
		checkerOf(userContext).checkSessionKeyOfWechatLoginInfo(sessionKey);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addWechatLoginInfo(HealthUserContext userContext, String userId, String appId, String openId, String sessionKey, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingWechatLoginInfo(userContext,userId,appId, openId, sessionKey,tokensExpr);

		WechatLoginInfo wechatLoginInfo = createWechatLoginInfo(userContext,appId, openId, sessionKey);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addWechatLoginInfo( wechatLoginInfo );
			user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
			
			userContext.getManagerGroup().getWechatLoginInfoManager().onNewInstanceCreated(userContext, wechatLoginInfo);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingWechatLoginInfoProperties(HealthUserContext userContext, String userId,String id,String appId,String openId,String sessionKey,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(id);

		checkerOf(userContext).checkAppIdOfWechatLoginInfo( appId);
		checkerOf(userContext).checkOpenIdOfWechatLoginInfo( openId);
		checkerOf(userContext).checkSessionKeyOfWechatLoginInfo( sessionKey);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateWechatLoginInfoProperties(HealthUserContext userContext, String userId, String id,String appId,String openId,String sessionKey, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatLoginInfoProperties(userContext,userId,id,appId,openId,sessionKey,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withWechatLoginInfoListList()
				.searchWechatLoginInfoListWith(WechatLoginInfo.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getWechatLoginInfoList().isEmpty()){
			throw new UserManagerException("WechatLoginInfo is NOT FOUND with id: '"+id+"'");
		}

		WechatLoginInfo item = userToUpdate.getWechatLoginInfoList().first();

		item.updateAppId( appId );
		item.updateOpenId( openId );
		item.updateSessionKey( sessionKey );


		//checkParamsForAddingWechatLoginInfo(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withWechatLoginInfoList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
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

	protected void checkParamsForRemovingWechatLoginInfoList(HealthUserContext userContext, String userId,
			String wechatLoginInfoIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String wechatLoginInfoIdItem: wechatLoginInfoIds){
			checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeWechatLoginInfoList(HealthUserContext userContext, String userId,
			String wechatLoginInfoIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingWechatLoginInfoList(userContext, userId,  wechatLoginInfoIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveWechatLoginInfoList(user, wechatLoginInfoIds, allTokens());
				user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
				deleteRelationListInGraph(userContext, user.getWechatLoginInfoList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingWechatLoginInfo(HealthUserContext userContext, String userId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).checkVersionOfWechatLoginInfo(wechatLoginInfoVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeWechatLoginInfo(HealthUserContext userContext, String userId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingWechatLoginInfo(userContext,userId, wechatLoginInfoId, wechatLoginInfoVersion,tokensExpr);

		WechatLoginInfo wechatLoginInfo = createIndexedWechatLoginInfo(wechatLoginInfoId, wechatLoginInfoVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeWechatLoginInfo( wechatLoginInfo );
			user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
			deleteRelationInGraph(userContext, wechatLoginInfo);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingWechatLoginInfo(HealthUserContext userContext, String userId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfWechatLoginInfo(wechatLoginInfoId);
		checkerOf(userContext).checkVersionOfWechatLoginInfo(wechatLoginInfoVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyWechatLoginInfoFrom(HealthUserContext userContext, String userId,
		String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingWechatLoginInfo(userContext,userId, wechatLoginInfoId, wechatLoginInfoVersion,tokensExpr);

		WechatLoginInfo wechatLoginInfo = createIndexedWechatLoginInfo(wechatLoginInfoId, wechatLoginInfoVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			wechatLoginInfo.updateLastUpdateTime(userContext.now());

			user.copyWechatLoginInfoFrom( wechatLoginInfo );
			user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
			
			userContext.getManagerGroup().getWechatLoginInfoManager().onNewInstanceCreated(userContext, (WechatLoginInfo)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingWechatLoginInfo(HealthUserContext userContext, String userId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateWechatLoginInfo(HealthUserContext userContext, String userId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingWechatLoginInfo(userContext, userId, wechatLoginInfoId, wechatLoginInfoVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withWechatLoginInfoList().searchWechatLoginInfoListWith(WechatLoginInfo.ID_PROPERTY, "eq", wechatLoginInfoId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeWechatLoginInfo( wechatLoginInfo );
			//make changes to AcceleraterAccount.
			WechatLoginInfo wechatLoginInfoIndex = createIndexedWechatLoginInfo(wechatLoginInfoId, wechatLoginInfoVersion);

			WechatLoginInfo wechatLoginInfo = user.findTheWechatLoginInfo(wechatLoginInfoIndex);
			if(wechatLoginInfo == null){
				throw new UserManagerException(wechatLoginInfo+" is NOT FOUND" );
			}

			wechatLoginInfo.changeProperty(property, newValueExpr);
			wechatLoginInfo.updateLastUpdateTime(userContext.now());
			user = saveUser(userContext, user, tokens().withWechatLoginInfoList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, User newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, User.INTERNAL_TYPE);
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


