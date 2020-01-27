
package com.doublechaintech.health.location;

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


import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.district.CandidateDistrict;
import com.doublechaintech.health.province.CandidateProvince;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.user.User;






public class LocationManagerImpl extends CustomHealthCheckerManager implements LocationManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Location";
	@Override
	public LocationDAO daoOf(HealthUserContext userContext) {
		return locationDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws LocationManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new LocationManagerException(message);

	}



 	protected Location saveLocation(HealthUserContext userContext, Location location, String [] tokensExpr) throws Exception{	
 		//return getLocationDAO().save(location, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveLocation(userContext, location, tokens);
 	}
 	
 	protected Location saveLocationDetail(HealthUserContext userContext, Location location) throws Exception{	

 		
 		return saveLocation(userContext, location, allTokens());
 	}
 	
 	public Location loadLocation(HealthUserContext userContext, String locationId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).throwExceptionIfHasErrors( LocationManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Location location = loadLocation( userContext, locationId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,location, tokens);
 	}
 	
 	
 	 public Location searchLocation(HealthUserContext userContext, String locationId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).throwExceptionIfHasErrors( LocationManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Location location = loadLocation( userContext, locationId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,location, tokens);
 	}
 	
 	

 	protected Location present(HealthUserContext userContext, Location location, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,location,tokens);
		
		
		Location  locationToPresent = locationDaoOf(userContext).present(location, tokens);
		
		List<BaseEntity> entityListToNaming = locationToPresent.collectRefercencesFromLists();
		locationDaoOf(userContext).alias(entityListToNaming);
		
		return  locationToPresent;
		
		
	}
 
 	
 	
 	public Location loadLocationDetail(HealthUserContext userContext, String locationId) throws Exception{	
 		Location location = loadLocation( userContext, locationId, allTokens());
 		return present(userContext,location, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String locationId) throws Exception{	
 		Location location = loadLocation( userContext, locationId, viewTokens());
 		return present(userContext,location, allTokens());
		
 	}
 	protected Location saveLocation(HealthUserContext userContext, Location location, Map<String,Object>tokens) throws Exception{	
 		return locationDaoOf(userContext).save(location, tokens);
 	}
 	protected Location loadLocation(HealthUserContext userContext, String locationId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).throwExceptionIfHasErrors( LocationManagerException.class);

 
 		return locationDaoOf(userContext).load(locationId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Location location, Map<String, Object> tokens){
		super.addActions(userContext, location, tokens);
		
		addAction(userContext, location, tokens,"@create","createLocation","createLocation/","main","primary");
		addAction(userContext, location, tokens,"@update","updateLocation","updateLocation/"+location.getId()+"/","main","primary");
		addAction(userContext, location, tokens,"@copy","cloneLocation","cloneLocation/"+location.getId()+"/","main","primary");
		
		addAction(userContext, location, tokens,"location.transfer_to_district","transferToAnotherDistrict","transferToAnotherDistrict/"+location.getId()+"/","main","primary");
		addAction(userContext, location, tokens,"location.transfer_to_province","transferToAnotherProvince","transferToAnotherProvince/"+location.getId()+"/","main","primary");
		addAction(userContext, location, tokens,"location.addStudent","addStudent","addStudent/"+location.getId()+"/","studentList","primary");
		addAction(userContext, location, tokens,"location.removeStudent","removeStudent","removeStudent/"+location.getId()+"/","studentList","primary");
		addAction(userContext, location, tokens,"location.updateStudent","updateStudent","updateStudent/"+location.getId()+"/","studentList","primary");
		addAction(userContext, location, tokens,"location.copyStudentFrom","copyStudentFrom","copyStudentFrom/"+location.getId()+"/","studentList","primary");
		addAction(userContext, location, tokens,"location.addUser","addUser","addUser/"+location.getId()+"/","userList","primary");
		addAction(userContext, location, tokens,"location.removeUser","removeUser","removeUser/"+location.getId()+"/","userList","primary");
		addAction(userContext, location, tokens,"location.updateUser","updateUser","updateUser/"+location.getId()+"/","userList","primary");
		addAction(userContext, location, tokens,"location.copyUserFrom","copyUserFrom","copyUserFrom/"+location.getId()+"/","userList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Location location, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Location createLocation(HealthUserContext userContext, String name,String address,String districtId,String provinceId,BigDecimal latitude,BigDecimal longitude) throws Exception
	//public Location createLocation(HealthUserContext userContext,String name, String address, String districtId, String provinceId, BigDecimal latitude, BigDecimal longitude) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfLocation(name);
		checkerOf(userContext).checkAddressOfLocation(address);
		checkerOf(userContext).checkLatitudeOfLocation(latitude);
		checkerOf(userContext).checkLongitudeOfLocation(longitude);
	
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);


		Location location=createNewLocation();	

		location.setName(name);
		location.setAddress(address);
			
		District district = loadDistrict(userContext, districtId,emptyOptions());
		location.setDistrict(district);
		
		
			
		Province province = loadProvince(userContext, provinceId,emptyOptions());
		location.setProvince(province);
		
		
		location.setLatitude(latitude);
		location.setLongitude(longitude);

		location = saveLocation(userContext, location, emptyOptions());
		
		onNewInstanceCreated(userContext, location);
		return location;


	}
	protected Location createNewLocation()
	{

		return new Location();
	}

	protected void checkParamsForUpdatingLocation(HealthUserContext userContext,String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkVersionOfLocation( locationVersion);
		

		if(Location.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfLocation(parseString(newValueExpr));
		}
		if(Location.ADDRESS_PROPERTY.equals(property)){
			checkerOf(userContext).checkAddressOfLocation(parseString(newValueExpr));
		}		

				

		
		if(Location.LATITUDE_PROPERTY.equals(property)){
			checkerOf(userContext).checkLatitudeOfLocation(parseBigDecimal(newValueExpr));
		}
		if(Location.LONGITUDE_PROPERTY.equals(property)){
			checkerOf(userContext).checkLongitudeOfLocation(parseBigDecimal(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);


	}



	public Location clone(HealthUserContext userContext, String fromLocationId) throws Exception{

		return locationDaoOf(userContext).clone(fromLocationId, this.allTokens());
	}

	public Location internalSaveLocation(HealthUserContext userContext, Location location) throws Exception
	{
		return internalSaveLocation(userContext, location, allTokens());

	}
	public Location internalSaveLocation(HealthUserContext userContext, Location location, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingLocation(userContext, locationId, locationVersion, property, newValueExpr, tokensExpr);


		synchronized(location){
			//will be good when the location loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Location.
			if (location.isChanged()){
			
			}
			location = saveLocation(userContext, location, options);
			return location;

		}

	}

	public Location updateLocation(HealthUserContext userContext,String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingLocation(userContext, locationId, locationVersion, property, newValueExpr, tokensExpr);



		Location location = loadLocation(userContext, locationId, allTokens());
		if(location.getVersion() != locationVersion){
			String message = "The target version("+location.getVersion()+") is not equals to version("+locationVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(location){
			//will be good when the location loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Location.
			
			location.changeProperty(property, newValueExpr);
			location = saveLocation(userContext, location, tokens().done());
			return present(userContext,location, mergedAllTokens(tokensExpr));
			//return saveLocation(userContext, location, tokens().done());
		}

	}

	public Location updateLocationProperty(HealthUserContext userContext,String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingLocation(userContext, locationId, locationVersion, property, newValueExpr, tokensExpr);

		Location location = loadLocation(userContext, locationId, allTokens());
		if(location.getVersion() != locationVersion){
			String message = "The target version("+location.getVersion()+") is not equals to version("+locationVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(location){
			//will be good when the location loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Location.

			location.changeProperty(property, newValueExpr);
			
			location = saveLocation(userContext, location, tokens().done());
			return present(userContext,location, mergedAllTokens(tokensExpr));
			//return saveLocation(userContext, location, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected LocationTokens tokens(){
		return LocationTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return LocationTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentListWith("id","desc")
		.sortUserListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return LocationTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherDistrict(HealthUserContext userContext, String locationId, String anotherDistrictId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfLocation(locationId);
 		checkerOf(userContext).checkIdOfDistrict(anotherDistrictId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

 	}
 	public Location transferToAnotherDistrict(HealthUserContext userContext, String locationId, String anotherDistrictId) throws Exception
 	{
 		checkParamsForTransferingAnotherDistrict(userContext, locationId,anotherDistrictId);
 
		Location location = loadLocation(userContext, locationId, allTokens());	
		synchronized(location){
			//will be good when the location loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			District district = loadDistrict(userContext, anotherDistrictId, emptyOptions());		
			location.updateDistrict(district);		
			location = saveLocation(userContext, location, emptyOptions());
			
			return present(userContext,location, allTokens());
			
		}

 	}

	


	public CandidateDistrict requestCandidateDistrict(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateDistrict result = new CandidateDistrict();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<District> candidateList = districtDaoOf(userContext).requestCandidateDistrictForLocation(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherProvince(HealthUserContext userContext, String locationId, String anotherProvinceId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfLocation(locationId);
 		checkerOf(userContext).checkIdOfProvince(anotherProvinceId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

 	}
 	public Location transferToAnotherProvince(HealthUserContext userContext, String locationId, String anotherProvinceId) throws Exception
 	{
 		checkParamsForTransferingAnotherProvince(userContext, locationId,anotherProvinceId);
 
		Location location = loadLocation(userContext, locationId, allTokens());	
		synchronized(location){
			//will be good when the location loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Province province = loadProvince(userContext, anotherProvinceId, emptyOptions());		
			location.updateProvince(province);		
			location = saveLocation(userContext, location, emptyOptions());
			
			return present(userContext,location, allTokens());
			
		}

 	}

	


	public CandidateProvince requestCandidateProvince(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateProvince result = new CandidateProvince();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Province> candidateList = provinceDaoOf(userContext).requestCandidateProvinceForLocation(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected District loadDistrict(HealthUserContext userContext, String newDistrictId, Map<String,Object> options) throws Exception
 	{

 		return districtDaoOf(userContext).load(newDistrictId, options);
 	}
 	


	

 	protected Province loadProvince(HealthUserContext userContext, String newProvinceId, Map<String,Object> options) throws Exception
 	{

 		return provinceDaoOf(userContext).load(newProvinceId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String locationId, int locationVersion) throws Exception {
		//deleteInternal(userContext, locationId, locationVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String locationId, int locationVersion) throws Exception{

		locationDaoOf(userContext).delete(locationId, locationVersion);
	}

	public Location forgetByAll(HealthUserContext userContext, String locationId, int locationVersion) throws Exception {
		return forgetByAllInternal(userContext, locationId, locationVersion);
	}
	protected Location forgetByAllInternal(HealthUserContext userContext,
			String locationId, int locationVersion) throws Exception{

		return locationDaoOf(userContext).disconnectFromAll(locationId, locationVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new LocationManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return locationDaoOf(userContext).deleteAll();
	}


	//disconnect Location with student_id in Student
	protected Location breakWithStudentByStudentId(HealthUserContext userContext, String locationId, String studentIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Location location = loadLocation(userContext, locationId, allTokens());

			synchronized(location){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				locationDaoOf(userContext).planToRemoveStudentListWithStudentId(location, studentIdId, this.emptyOptions());

				location = saveLocation(userContext, location, tokens().withStudentList().done());
				return location;
			}
	}
	//disconnect Location with user in Student
	protected Location breakWithStudentByUser(HealthUserContext userContext, String locationId, String userId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Location location = loadLocation(userContext, locationId, allTokens());

			synchronized(location){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				locationDaoOf(userContext).planToRemoveStudentListWithUser(location, userId, this.emptyOptions());

				location = saveLocation(userContext, location, tokens().withStudentList().done());
				return location;
			}
	}
	//disconnect Location with platform in Student
	protected Location breakWithStudentByPlatform(HealthUserContext userContext, String locationId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Location location = loadLocation(userContext, locationId, allTokens());

			synchronized(location){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				locationDaoOf(userContext).planToRemoveStudentListWithPlatform(location, platformId, this.emptyOptions());

				location = saveLocation(userContext, location, tokens().withStudentList().done());
				return location;
			}
	}
	//disconnect Location with cq in Student
	protected Location breakWithStudentByCq(HealthUserContext userContext, String locationId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Location location = loadLocation(userContext, locationId, allTokens());

			synchronized(location){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				locationDaoOf(userContext).planToRemoveStudentListWithCq(location, cqId, this.emptyOptions());

				location = saveLocation(userContext, location, tokens().withStudentList().done());
				return location;
			}
	}
	//disconnect Location with platform in User
	protected Location breakWithUserByPlatform(HealthUserContext userContext, String locationId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Location location = loadLocation(userContext, locationId, allTokens());

			synchronized(location){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				locationDaoOf(userContext).planToRemoveUserListWithPlatform(location, platformId, this.emptyOptions());

				location = saveLocation(userContext, location, tokens().withUserList().done());
				return location;
			}
	}






	protected void checkParamsForAddingStudent(HealthUserContext userContext, String locationId, String studentName, String studentId, String guardianName, String guardianMobile, String userId, String platformId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfLocation(locationId);

		
		checkerOf(userContext).checkStudentNameOfStudent(studentName);
		
		checkerOf(userContext).checkStudentIdOfStudent(studentId);
		
		checkerOf(userContext).checkGuardianNameOfStudent(guardianName);
		
		checkerOf(userContext).checkGuardianMobileOfStudent(guardianMobile);
		
		checkerOf(userContext).checkUserIdOfStudent(userId);
		
		checkerOf(userContext).checkPlatformIdOfStudent(platformId);
		
		checkerOf(userContext).checkCqIdOfStudent(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);


	}
	public  Location addStudent(HealthUserContext userContext, String locationId, String studentName, String studentId, String guardianName, String guardianMobile, String userId, String platformId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudent(userContext,locationId,studentName, studentId, guardianName, guardianMobile, userId, platformId, cqId,tokensExpr);

		Student student = createStudent(userContext,studentName, studentId, guardianName, guardianMobile, userId, platformId, cqId);

		Location location = loadLocation(userContext, locationId, emptyOptions());
		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			location.addStudent( student );
			location = saveLocation(userContext, location, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, student);
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentProperties(HealthUserContext userContext, String locationId,String id,String studentName,String studentId,String guardianName,String guardianMobile,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkIdOfStudent(id);

		checkerOf(userContext).checkStudentNameOfStudent( studentName);
		checkerOf(userContext).checkStudentIdOfStudent( studentId);
		checkerOf(userContext).checkGuardianNameOfStudent( guardianName);
		checkerOf(userContext).checkGuardianMobileOfStudent( guardianMobile);

		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location updateStudentProperties(HealthUserContext userContext, String locationId, String id,String studentName,String studentId,String guardianName,String guardianMobile, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentProperties(userContext,locationId,id,studentName,studentId,guardianName,guardianMobile,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentListList()
				.searchStudentListWith(Student.ID_PROPERTY, "is", id).done();

		Location locationToUpdate = loadLocation(userContext, locationId, options);

		if(locationToUpdate.getStudentList().isEmpty()){
			throw new LocationManagerException("Student is NOT FOUND with id: '"+id+"'");
		}

		Student item = locationToUpdate.getStudentList().first();

		item.updateStudentName( studentName );
		item.updateStudentId( studentId );
		item.updateGuardianName( guardianName );
		item.updateGuardianMobile( guardianMobile );


		//checkParamsForAddingStudent(userContext,locationId,name, code, used,tokensExpr);
		Location location = saveLocation(userContext, locationToUpdate, tokens().withStudentList().done());
		synchronized(location){
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}
	}


	protected Student createStudent(HealthUserContext userContext, String studentName, String studentId, String guardianName, String guardianMobile, String userId, String platformId, String cqId) throws Exception{

		Student student = new Student();
		
		
		student.setStudentName(studentName);		
		student.setStudentId(studentId);		
		student.setGuardianName(guardianName);		
		student.setGuardianMobile(guardianMobile);		
		User  user = new User();
		user.setId(userId);		
		student.setUser(user);		
		student.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		student.setPlatform(platform);		
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

	protected void checkParamsForRemovingStudentList(HealthUserContext userContext, String locationId,
			String studentIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfLocation(locationId);
		for(String studentIdItem: studentIds){
			checkerOf(userContext).checkIdOfStudent(studentIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location removeStudentList(HealthUserContext userContext, String locationId,
			String studentIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentList(userContext, locationId,  studentIds, tokensExpr);


			Location location = loadLocation(userContext, locationId, allTokens());
			synchronized(location){
				//Will be good when the location loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				locationDaoOf(userContext).planToRemoveStudentList(location, studentIds, allTokens());
				location = saveLocation(userContext, location, tokens().withStudentList().done());
				deleteRelationListInGraph(userContext, location.getStudentList());
				return present(userContext,location, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudent(HealthUserContext userContext, String locationId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfLocation( locationId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location removeStudent(HealthUserContext userContext, String locationId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudent(userContext,locationId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		Location location = loadLocation(userContext, locationId, allTokens());
		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			location.removeStudent( student );
			location = saveLocation(userContext, location, tokens().withStudentList().done());
			deleteRelationInGraph(userContext, student);
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudent(HealthUserContext userContext, String locationId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfLocation( locationId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location copyStudentFrom(HealthUserContext userContext, String locationId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudent(userContext,locationId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		Location location = loadLocation(userContext, locationId, allTokens());
		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			location.copyStudentFrom( student );
			location = saveLocation(userContext, location, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, (Student)location.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudent(HealthUserContext userContext, String locationId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfLocation(locationId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}

	public  Location updateStudent(HealthUserContext userContext, String locationId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudent(userContext, locationId, studentId, studentVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentList().searchStudentListWith(Student.ID_PROPERTY, "eq", studentId).done();



		Location location = loadLocation(userContext, locationId, loadTokens);

		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//location.removeStudent( student );
			//make changes to AcceleraterAccount.
			Student studentIndex = createIndexedStudent(studentId, studentVersion);

			Student student = location.findTheStudent(studentIndex);
			if(student == null){
				throw new LocationManagerException(student+" is NOT FOUND" );
			}

			student.changeProperty(property, newValueExpr);
			
			location = saveLocation(userContext, location, tokens().withStudentList().done());
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingUser(HealthUserContext userContext, String locationId, String name, String avatar, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfLocation(locationId);

		
		checkerOf(userContext).checkNameOfUser(name);
		
		checkerOf(userContext).checkAvatarOfUser(avatar);
		
		checkerOf(userContext).checkPlatformIdOfUser(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);


	}
	public  Location addUser(HealthUserContext userContext, String locationId, String name, String avatar, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingUser(userContext,locationId,name, avatar, platformId,tokensExpr);

		User user = createUser(userContext,name, avatar, platformId);

		Location location = loadLocation(userContext, locationId, emptyOptions());
		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			location.addUser( user );
			location = saveLocation(userContext, location, tokens().withUserList().done());
			
			userContext.getManagerGroup().getUserManager().onNewInstanceCreated(userContext, user);
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserProperties(HealthUserContext userContext, String locationId,String id,String name,String avatar,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkIdOfUser(id);

		checkerOf(userContext).checkNameOfUser( name);
		checkerOf(userContext).checkAvatarOfUser( avatar);

		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location updateUserProperties(HealthUserContext userContext, String locationId, String id,String name,String avatar, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserProperties(userContext,locationId,id,name,avatar,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserListList()
				.searchUserListWith(User.ID_PROPERTY, "is", id).done();

		Location locationToUpdate = loadLocation(userContext, locationId, options);

		if(locationToUpdate.getUserList().isEmpty()){
			throw new LocationManagerException("User is NOT FOUND with id: '"+id+"'");
		}

		User item = locationToUpdate.getUserList().first();

		item.updateName( name );
		item.updateAvatar( avatar );


		//checkParamsForAddingUser(userContext,locationId,name, code, used,tokensExpr);
		Location location = saveLocation(userContext, locationToUpdate, tokens().withUserList().done());
		synchronized(location){
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}
	}


	protected User createUser(HealthUserContext userContext, String name, String avatar, String platformId) throws Exception{

		User user = new User();
		
		
		user.setName(name);		
		user.setAvatar(avatar);		
		user.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		user.setPlatform(platform);
	
		
		return user;


	}

	protected User createIndexedUser(String id, int version){

		User user = new User();
		user.setId(id);
		user.setVersion(version);
		return user;

	}

	protected void checkParamsForRemovingUserList(HealthUserContext userContext, String locationId,
			String userIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfLocation(locationId);
		for(String userIdItem: userIds){
			checkerOf(userContext).checkIdOfUser(userIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location removeUserList(HealthUserContext userContext, String locationId,
			String userIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingUserList(userContext, locationId,  userIds, tokensExpr);


			Location location = loadLocation(userContext, locationId, allTokens());
			synchronized(location){
				//Will be good when the location loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				locationDaoOf(userContext).planToRemoveUserList(location, userIds, allTokens());
				location = saveLocation(userContext, location, tokens().withUserList().done());
				deleteRelationListInGraph(userContext, location.getUserList());
				return present(userContext,location, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingUser(HealthUserContext userContext, String locationId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfLocation( locationId);
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser(userVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location removeUser(HealthUserContext userContext, String locationId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingUser(userContext,locationId, userId, userVersion,tokensExpr);

		User user = createIndexedUser(userId, userVersion);
		Location location = loadLocation(userContext, locationId, allTokens());
		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			location.removeUser( user );
			location = saveLocation(userContext, location, tokens().withUserList().done());
			deleteRelationInGraph(userContext, user);
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingUser(HealthUserContext userContext, String locationId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfLocation( locationId);
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser(userVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}
	public  Location copyUserFrom(HealthUserContext userContext, String locationId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingUser(userContext,locationId, userId, userVersion,tokensExpr);

		User user = createIndexedUser(userId, userVersion);
		Location location = loadLocation(userContext, locationId, allTokens());
		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			location.copyUserFrom( user );
			location = saveLocation(userContext, location, tokens().withUserList().done());
			
			userContext.getManagerGroup().getUserManager().onNewInstanceCreated(userContext, (User)location.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingUser(HealthUserContext userContext, String locationId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser(userVersion);
		

		if(User.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfUser(parseString(newValueExpr));
		}
		
		if(User.AVATAR_PROPERTY.equals(property)){
			checkerOf(userContext).checkAvatarOfUser(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(LocationManagerException.class);

	}

	public  Location updateUser(HealthUserContext userContext, String locationId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingUser(userContext, locationId, userId, userVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withUserList().searchUserListWith(User.ID_PROPERTY, "eq", userId).done();



		Location location = loadLocation(userContext, locationId, loadTokens);

		synchronized(location){
			//Will be good when the location loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//location.removeUser( user );
			//make changes to AcceleraterAccount.
			User userIndex = createIndexedUser(userId, userVersion);

			User user = location.findTheUser(userIndex);
			if(user == null){
				throw new LocationManagerException(user+" is NOT FOUND" );
			}

			user.changeProperty(property, newValueExpr);
			
			location = saveLocation(userContext, location, tokens().withUserList().done());
			return present(userContext,location, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, Location newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Location.INTERNAL_TYPE);
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


