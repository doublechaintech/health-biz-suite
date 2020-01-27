
package com.doublechaintech.health.guardian;

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
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.location.CandidateLocation;
import com.doublechaintech.health.wechatuser.CandidateWechatUser;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.guardian.Guardian;






public class GuardianManagerImpl extends CustomHealthCheckerManager implements GuardianManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Guardian";
	@Override
	public GuardianDAO daoOf(HealthUserContext userContext) {
		return guardianDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws GuardianManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new GuardianManagerException(message);

	}



 	protected Guardian saveGuardian(HealthUserContext userContext, Guardian guardian, String [] tokensExpr) throws Exception{	
 		//return getGuardianDAO().save(guardian, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveGuardian(userContext, guardian, tokens);
 	}
 	
 	protected Guardian saveGuardianDetail(HealthUserContext userContext, Guardian guardian) throws Exception{	

 		
 		return saveGuardian(userContext, guardian, allTokens());
 	}
 	
 	public Guardian loadGuardian(HealthUserContext userContext, String guardianId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).throwExceptionIfHasErrors( GuardianManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Guardian guardian = loadGuardian( userContext, guardianId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,guardian, tokens);
 	}
 	
 	
 	 public Guardian searchGuardian(HealthUserContext userContext, String guardianId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).throwExceptionIfHasErrors( GuardianManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Guardian guardian = loadGuardian( userContext, guardianId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,guardian, tokens);
 	}
 	
 	

 	protected Guardian present(HealthUserContext userContext, Guardian guardian, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,guardian,tokens);
		
		
		Guardian  guardianToPresent = guardianDaoOf(userContext).present(guardian, tokens);
		
		List<BaseEntity> entityListToNaming = guardianToPresent.collectRefercencesFromLists();
		guardianDaoOf(userContext).alias(entityListToNaming);
		
		return  guardianToPresent;
		
		
	}
 
 	
 	
 	public Guardian loadGuardianDetail(HealthUserContext userContext, String guardianId) throws Exception{	
 		Guardian guardian = loadGuardian( userContext, guardianId, allTokens());
 		return present(userContext,guardian, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String guardianId) throws Exception{	
 		Guardian guardian = loadGuardian( userContext, guardianId, viewTokens());
 		return present(userContext,guardian, allTokens());
		
 	}
 	protected Guardian saveGuardian(HealthUserContext userContext, Guardian guardian, Map<String,Object>tokens) throws Exception{	
 		return guardianDaoOf(userContext).save(guardian, tokens);
 	}
 	protected Guardian loadGuardian(HealthUserContext userContext, String guardianId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).throwExceptionIfHasErrors( GuardianManagerException.class);

 
 		return guardianDaoOf(userContext).load(guardianId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Guardian guardian, Map<String, Object> tokens){
		super.addActions(userContext, guardian, tokens);
		
		addAction(userContext, guardian, tokens,"@create","createGuardian","createGuardian/","main","primary");
		addAction(userContext, guardian, tokens,"@update","updateGuardian","updateGuardian/"+guardian.getId()+"/","main","primary");
		addAction(userContext, guardian, tokens,"@copy","cloneGuardian","cloneGuardian/"+guardian.getId()+"/","main","primary");
		
		addAction(userContext, guardian, tokens,"guardian.transfer_to_address","transferToAnotherAddress","transferToAnotherAddress/"+guardian.getId()+"/","main","primary");
		addAction(userContext, guardian, tokens,"guardian.transfer_to_wechat_user","transferToAnotherWechatUser","transferToAnotherWechatUser/"+guardian.getId()+"/","main","primary");
		addAction(userContext, guardian, tokens,"guardian.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+guardian.getId()+"/","main","primary");
		addAction(userContext, guardian, tokens,"guardian.transfer_to_cq","transferToAnotherCq","transferToAnotherCq/"+guardian.getId()+"/","main","primary");
		addAction(userContext, guardian, tokens,"guardian.addStudent","addStudent","addStudent/"+guardian.getId()+"/","studentList","primary");
		addAction(userContext, guardian, tokens,"guardian.removeStudent","removeStudent","removeStudent/"+guardian.getId()+"/","studentList","primary");
		addAction(userContext, guardian, tokens,"guardian.updateStudent","updateStudent","updateStudent/"+guardian.getId()+"/","studentList","primary");
		addAction(userContext, guardian, tokens,"guardian.copyStudentFrom","copyStudentFrom","copyStudentFrom/"+guardian.getId()+"/","studentList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Guardian guardian, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Guardian createGuardian(HealthUserContext userContext, String name,String mobile,String addressId,String wechatUserId,String platformId,String cqId) throws Exception
	//public Guardian createGuardian(HealthUserContext userContext,String name, String mobile, String addressId, String wechatUserId, String platformId, String cqId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfGuardian(name);
		checkerOf(userContext).checkMobileOfGuardian(mobile);
	
		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);


		Guardian guardian=createNewGuardian();	

		guardian.setName(name);
		guardian.setMobile(mobile);
			
		Location address = loadLocation(userContext, addressId,emptyOptions());
		guardian.setAddress(address);
		
		
			
		WechatUser wechatUser = loadWechatUser(userContext, wechatUserId,emptyOptions());
		guardian.setWechatUser(wechatUser);
		
		
		guardian.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		guardian.setPlatform(platform);
		
		
			
		ChangeRequest cq = loadChangeRequest(userContext, cqId,emptyOptions());
		guardian.setCq(cq);
		
		

		guardian = saveGuardian(userContext, guardian, emptyOptions());
		
		onNewInstanceCreated(userContext, guardian);
		return guardian;


	}
	protected Guardian createNewGuardian()
	{

		return new Guardian();
	}

	protected void checkParamsForUpdatingGuardian(HealthUserContext userContext,String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian( guardianVersion);
		

		if(Guardian.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfGuardian(parseString(newValueExpr));
		}
		if(Guardian.MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkMobileOfGuardian(parseString(newValueExpr));
		}		

				

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);


	}



	public Guardian clone(HealthUserContext userContext, String fromGuardianId) throws Exception{

		return guardianDaoOf(userContext).clone(fromGuardianId, this.allTokens());
	}

	public Guardian internalSaveGuardian(HealthUserContext userContext, Guardian guardian) throws Exception
	{
		return internalSaveGuardian(userContext, guardian, allTokens());

	}
	public Guardian internalSaveGuardian(HealthUserContext userContext, Guardian guardian, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingGuardian(userContext, guardianId, guardianVersion, property, newValueExpr, tokensExpr);


		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Guardian.
			if (guardian.isChanged()){
			
			}
			guardian = saveGuardian(userContext, guardian, options);
			return guardian;

		}

	}

	public Guardian updateGuardian(HealthUserContext userContext,String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingGuardian(userContext, guardianId, guardianVersion, property, newValueExpr, tokensExpr);



		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());
		if(guardian.getVersion() != guardianVersion){
			String message = "The target version("+guardian.getVersion()+") is not equals to version("+guardianVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Guardian.
			
			guardian.changeProperty(property, newValueExpr);
			guardian = saveGuardian(userContext, guardian, tokens().done());
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
			//return saveGuardian(userContext, guardian, tokens().done());
		}

	}

	public Guardian updateGuardianProperty(HealthUserContext userContext,String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingGuardian(userContext, guardianId, guardianVersion, property, newValueExpr, tokensExpr);

		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());
		if(guardian.getVersion() != guardianVersion){
			String message = "The target version("+guardian.getVersion()+") is not equals to version("+guardianVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Guardian.

			guardian.changeProperty(property, newValueExpr);
			
			guardian = saveGuardian(userContext, guardian, tokens().done());
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
			//return saveGuardian(userContext, guardian, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected GuardianTokens tokens(){
		return GuardianTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return GuardianTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return GuardianTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherAddress(HealthUserContext userContext, String guardianId, String anotherAddressId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfGuardian(guardianId);
 		checkerOf(userContext).checkIdOfLocation(anotherAddressId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

 	}
 	public Guardian transferToAnotherAddress(HealthUserContext userContext, String guardianId, String anotherAddressId) throws Exception
 	{
 		checkParamsForTransferingAnotherAddress(userContext, guardianId,anotherAddressId);
 
		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());	
		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Location address = loadLocation(userContext, anotherAddressId, emptyOptions());		
			guardian.updateAddress(address);		
			guardian = saveGuardian(userContext, guardian, emptyOptions());
			
			return present(userContext,guardian, allTokens());
			
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
		SmartList<Location> candidateList = locationDaoOf(userContext).requestCandidateLocationForGuardian(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherWechatUser(HealthUserContext userContext, String guardianId, String anotherWechatUserId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfGuardian(guardianId);
 		checkerOf(userContext).checkIdOfWechatUser(anotherWechatUserId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

 	}
 	public Guardian transferToAnotherWechatUser(HealthUserContext userContext, String guardianId, String anotherWechatUserId) throws Exception
 	{
 		checkParamsForTransferingAnotherWechatUser(userContext, guardianId,anotherWechatUserId);
 
		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());	
		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			WechatUser wechatUser = loadWechatUser(userContext, anotherWechatUserId, emptyOptions());		
			guardian.updateWechatUser(wechatUser);		
			guardian = saveGuardian(userContext, guardian, emptyOptions());
			
			return present(userContext,guardian, allTokens());
			
		}

 	}

	


	public CandidateWechatUser requestCandidateWechatUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateWechatUser result = new CandidateWechatUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<WechatUser> candidateList = wechatUserDaoOf(userContext).requestCandidateWechatUserForGuardian(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String guardianId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfGuardian(guardianId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

 	}
 	public Guardian transferToAnotherPlatform(HealthUserContext userContext, String guardianId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, guardianId,anotherPlatformId);
 
		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());	
		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			guardian.updatePlatform(platform);		
			guardian = saveGuardian(userContext, guardian, emptyOptions());
			
			return present(userContext,guardian, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForGuardian(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCq(HealthUserContext userContext, String guardianId, String anotherCqId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfGuardian(guardianId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherCqId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

 	}
 	public Guardian transferToAnotherCq(HealthUserContext userContext, String guardianId, String anotherCqId) throws Exception
 	{
 		checkParamsForTransferingAnotherCq(userContext, guardianId,anotherCqId);
 
		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());	
		synchronized(guardian){
			//will be good when the guardian loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest cq = loadChangeRequest(userContext, anotherCqId, emptyOptions());		
			guardian.updateCq(cq);		
			guardian = saveGuardian(userContext, guardian, emptyOptions());
			
			return present(userContext,guardian, allTokens());
			
		}

 	}

	


	public CandidateChangeRequest requestCandidateCq(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequest result = new CandidateChangeRequest();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForGuardian(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected WechatUser loadWechatUser(HealthUserContext userContext, String newWechatUserId, Map<String,Object> options) throws Exception
 	{

 		return wechatUserDaoOf(userContext).load(newWechatUserId, options);
 	}
 	


	

 	protected Location loadLocation(HealthUserContext userContext, String newAddressId, Map<String,Object> options) throws Exception
 	{

 		return locationDaoOf(userContext).load(newAddressId, options);
 	}
 	


	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newCqId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newCqId, options);
 	}
 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String guardianId, int guardianVersion) throws Exception {
		//deleteInternal(userContext, guardianId, guardianVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String guardianId, int guardianVersion) throws Exception{

		guardianDaoOf(userContext).delete(guardianId, guardianVersion);
	}

	public Guardian forgetByAll(HealthUserContext userContext, String guardianId, int guardianVersion) throws Exception {
		return forgetByAllInternal(userContext, guardianId, guardianVersion);
	}
	protected Guardian forgetByAllInternal(HealthUserContext userContext,
			String guardianId, int guardianVersion) throws Exception{

		return guardianDaoOf(userContext).disconnectFromAll(guardianId, guardianVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new GuardianManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return guardianDaoOf(userContext).deleteAll();
	}


	//disconnect Guardian with school_class in Student
	protected Guardian breakWithStudentBySchoolClass(HealthUserContext userContext, String guardianId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Guardian guardian = loadGuardian(userContext, guardianId, allTokens());

			synchronized(guardian){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				guardianDaoOf(userContext).planToRemoveStudentListWithSchoolClass(guardian, schoolClassId, this.emptyOptions());

				guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
				return guardian;
			}
	}
	//disconnect Guardian with student_id in Student
	protected Guardian breakWithStudentByStudentId(HealthUserContext userContext, String guardianId, String studentIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Guardian guardian = loadGuardian(userContext, guardianId, allTokens());

			synchronized(guardian){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				guardianDaoOf(userContext).planToRemoveStudentListWithStudentId(guardian, studentIdId, this.emptyOptions());

				guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
				return guardian;
			}
	}
	//disconnect Guardian with cq in Student
	protected Guardian breakWithStudentByCq(HealthUserContext userContext, String guardianId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Guardian guardian = loadGuardian(userContext, guardianId, allTokens());

			synchronized(guardian){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				guardianDaoOf(userContext).planToRemoveStudentListWithCq(guardian, cqId, this.emptyOptions());

				guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
				return guardian;
			}
	}






	protected void checkParamsForAddingStudent(HealthUserContext userContext, String guardianId, String name, String gender, String schoolClassId, String studentId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfGuardian(guardianId);

		
		checkerOf(userContext).checkNameOfStudent(name);
		
		checkerOf(userContext).checkGenderOfStudent(gender);
		
		checkerOf(userContext).checkSchoolClassIdOfStudent(schoolClassId);
		
		checkerOf(userContext).checkStudentIdOfStudent(studentId);
		
		checkerOf(userContext).checkCqIdOfStudent(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);


	}
	public  Guardian addStudent(HealthUserContext userContext, String guardianId, String name, String gender, String schoolClassId, String studentId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudent(userContext,guardianId,name, gender, schoolClassId, studentId, cqId,tokensExpr);

		Student student = createStudent(userContext,name, gender, schoolClassId, studentId, cqId);

		Guardian guardian = loadGuardian(userContext, guardianId, emptyOptions());
		synchronized(guardian){
			//Will be good when the guardian loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			guardian.addStudent( student );
			guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, student);
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentProperties(HealthUserContext userContext, String guardianId,String id,String name,String gender,String studentId,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkIdOfStudent(id);

		checkerOf(userContext).checkNameOfStudent( name);
		checkerOf(userContext).checkGenderOfStudent( gender);
		checkerOf(userContext).checkStudentIdOfStudent( studentId);

		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

	}
	public  Guardian updateStudentProperties(HealthUserContext userContext, String guardianId, String id,String name,String gender,String studentId, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentProperties(userContext,guardianId,id,name,gender,studentId,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentListList()
				.searchStudentListWith(Student.ID_PROPERTY, "is", id).done();

		Guardian guardianToUpdate = loadGuardian(userContext, guardianId, options);

		if(guardianToUpdate.getStudentList().isEmpty()){
			throw new GuardianManagerException("Student is NOT FOUND with id: '"+id+"'");
		}

		Student item = guardianToUpdate.getStudentList().first();

		item.updateName( name );
		item.updateGender( gender );
		item.updateStudentId( studentId );


		//checkParamsForAddingStudent(userContext,guardianId,name, code, used,tokensExpr);
		Guardian guardian = saveGuardian(userContext, guardianToUpdate, tokens().withStudentList().done());
		synchronized(guardian){
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
		}
	}


	protected Student createStudent(HealthUserContext userContext, String name, String gender, String schoolClassId, String studentId, String cqId) throws Exception{

		Student student = new Student();
		
		
		student.setName(name);		
		student.setGender(gender);		
		SchoolClass  schoolClass = new SchoolClass();
		schoolClass.setId(schoolClassId);		
		student.setSchoolClass(schoolClass);		
		student.setStudentId(studentId);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		student.setCq(cq);
	
		
		return student;


	}

	protected Student createIndexedStudent(String id, int version){

		Student student = new Student();
		student.setId(id);
		student.setVersion(version);
		return student;

	}

	protected void checkParamsForRemovingStudentList(HealthUserContext userContext, String guardianId,
			String studentIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfGuardian(guardianId);
		for(String studentIdItem: studentIds){
			checkerOf(userContext).checkIdOfStudent(studentIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

	}
	public  Guardian removeStudentList(HealthUserContext userContext, String guardianId,
			String studentIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentList(userContext, guardianId,  studentIds, tokensExpr);


			Guardian guardian = loadGuardian(userContext, guardianId, allTokens());
			synchronized(guardian){
				//Will be good when the guardian loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				guardianDaoOf(userContext).planToRemoveStudentList(guardian, studentIds, allTokens());
				guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
				deleteRelationListInGraph(userContext, guardian.getStudentList());
				return present(userContext,guardian, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudent(HealthUserContext userContext, String guardianId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGuardian( guardianId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

	}
	public  Guardian removeStudent(HealthUserContext userContext, String guardianId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudent(userContext,guardianId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());
		synchronized(guardian){
			//Will be good when the guardian loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			guardian.removeStudent( student );
			guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
			deleteRelationInGraph(userContext, student);
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudent(HealthUserContext userContext, String guardianId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfGuardian( guardianId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

	}
	public  Guardian copyStudentFrom(HealthUserContext userContext, String guardianId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudent(userContext,guardianId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		Guardian guardian = loadGuardian(userContext, guardianId, allTokens());
		synchronized(guardian){
			//Will be good when the guardian loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			guardian.copyStudentFrom( student );
			guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, (Student)guardian.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudent(HealthUserContext userContext, String guardianId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		

		if(Student.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfStudent(parseString(newValueExpr));
		}
		
		if(Student.GENDER_PROPERTY.equals(property)){
			checkerOf(userContext).checkGenderOfStudent(parseString(newValueExpr));
		}
		
		if(Student.STUDENT_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentIdOfStudent(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(GuardianManagerException.class);

	}

	public  Guardian updateStudent(HealthUserContext userContext, String guardianId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudent(userContext, guardianId, studentId, studentVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentList().searchStudentListWith(Student.ID_PROPERTY, "eq", studentId).done();



		Guardian guardian = loadGuardian(userContext, guardianId, loadTokens);

		synchronized(guardian){
			//Will be good when the guardian loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//guardian.removeStudent( student );
			//make changes to AcceleraterAccount.
			Student studentIndex = createIndexedStudent(studentId, studentVersion);

			Student student = guardian.findTheStudent(studentIndex);
			if(student == null){
				throw new GuardianManagerException(student+" is NOT FOUND" );
			}

			student.changeProperty(property, newValueExpr);
			
			guardian = saveGuardian(userContext, guardian, tokens().withStudentList().done());
			return present(userContext,guardian, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, Guardian newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Guardian.INTERNAL_TYPE);
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


