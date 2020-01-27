
package com.doublechaintech.health.location;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import com.doublechaintech.health.HealthBaseDAOImpl;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.AccessKey;
import com.doublechaintech.health.DateKey;
import com.doublechaintech.health.StatsInfo;
import com.doublechaintech.health.StatsItem;

import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;


import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.district.DistrictDAO;
import com.doublechaintech.health.user.UserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class LocationJDBCTemplateDAO extends HealthBaseDAOImpl implements LocationDAO{
 
 	
 	private  DistrictDAO  districtDAO;
 	public void setDistrictDAO(DistrictDAO districtDAO){
	 	this.districtDAO = districtDAO;
 	}
 	public DistrictDAO getDistrictDAO(){
	 	return this.districtDAO;
 	}
 
 	
 	private  ProvinceDAO  provinceDAO;
 	public void setProvinceDAO(ProvinceDAO provinceDAO){
	 	this.provinceDAO = provinceDAO;
 	}
 	public ProvinceDAO getProvinceDAO(){
	 	return this.provinceDAO;
 	}


			
		
	
  	private  StudentDAO  studentDAO;
 	public void setStudentDAO(StudentDAO pStudentDAO){
 	
 		if(pStudentDAO == null){
 			throw new IllegalStateException("Do not try to set studentDAO to null.");
 		}
	 	this.studentDAO = pStudentDAO;
 	}
 	public StudentDAO getStudentDAO(){
 		if(this.studentDAO == null){
 			throw new IllegalStateException("The studentDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentDAO;
 	}	
 	
			
		
	
  	private  UserDAO  userDAO;
 	public void setUserDAO(UserDAO pUserDAO){
 	
 		if(pUserDAO == null){
 			throw new IllegalStateException("Do not try to set userDAO to null.");
 		}
	 	this.userDAO = pUserDAO;
 	}
 	public UserDAO getUserDAO(){
 		if(this.userDAO == null){
 			throw new IllegalStateException("The userDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userDAO;
 	}	
 	
			
		

	
	/*
	protected Location load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalLocation(accessKey, options);
	}
	*/
	
	public SmartList<Location> loadAll() {
	    return this.loadAll(getLocationMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Location load(String id,Map<String,Object> options) throws Exception{
		return loadInternalLocation(LocationTable.withId(id), options);
	}
	
	
	
	public Location save(Location location,Map<String,Object> options){
		
		String methodName="save(Location location,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(location, methodName, "location");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalLocation(location,options);
	}
	public Location clone(String locationId, Map<String,Object> options) throws Exception{
	
		return clone(LocationTable.withId(locationId),options);
	}
	
	protected Location clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String locationId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Location newLocation = loadInternalLocation(accessKey, options);
		newLocation.setVersion(0);
		
		
 		
 		if(isSaveStudentListEnabled(options)){
 			for(Student item: newLocation.getStudentList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveUserListEnabled(options)){
 			for(User item: newLocation.getUserList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalLocation(newLocation,options);
		
		return newLocation;
	}
	
	
	
	

	protected void throwIfHasException(String locationId,int version,int count) throws Exception{
		if (count == 1) {
			throw new LocationVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new LocationNotFoundException(
					"The " + this.getTableName() + "(" + locationId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String locationId, int version) throws Exception{
	
		String methodName="delete(String locationId, int version)";
		assertMethodArgumentNotNull(locationId, methodName, "locationId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{locationId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(locationId,version);
		}
		
	
	}
	
	
	
	
	

	public Location disconnectFromAll(String locationId, int version) throws Exception{
	
		
		Location location = loadInternalLocation(LocationTable.withId(locationId), emptyOptions());
		location.clearFromAll();
		this.saveLocation(location);
		return location;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return LocationTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "location";
	}
	@Override
	protected String getBeanName() {
		
		return "location";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return LocationTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractDistrictEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, LocationTokens.DISTRICT);
 	}

 	protected boolean isSaveDistrictEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, LocationTokens.DISTRICT);
 	}
 	

 	
  

 	protected boolean isExtractProvinceEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, LocationTokens.PROVINCE);
 	}

 	protected boolean isSaveProvinceEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, LocationTokens.PROVINCE);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentListEnabled(Map<String,Object> options){		
 		return checkOptions(options,LocationTokens.STUDENT_LIST);
 	}
 	protected boolean isAnalyzeStudentListEnabled(Map<String,Object> options){		 		
 		return LocationTokens.of(options).analyzeStudentListEnabled();
 	}
	
	protected boolean isSaveStudentListEnabled(Map<String,Object> options){
		return checkOptions(options, LocationTokens.STUDENT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractUserListEnabled(Map<String,Object> options){		
 		return checkOptions(options,LocationTokens.USER_LIST);
 	}
 	protected boolean isAnalyzeUserListEnabled(Map<String,Object> options){		 		
 		return LocationTokens.of(options).analyzeUserListEnabled();
 	}
	
	protected boolean isSaveUserListEnabled(Map<String,Object> options){
		return checkOptions(options, LocationTokens.USER_LIST);
		
 	}
 	
		

	

	protected LocationMapper getLocationMapper(){
		return new LocationMapper();
	}

	
	
	protected Location extractLocation(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Location location = loadSingleObject(accessKey, getLocationMapper());
			return location;
		}catch(EmptyResultDataAccessException e){
			throw new LocationNotFoundException("Location("+accessKey+") is not found!");
		}

	}

	
	

	protected Location loadInternalLocation(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Location location = extractLocation(accessKey, loadOptions);
 	
 		if(isExtractDistrictEnabled(loadOptions)){
	 		extractDistrict(location, loadOptions);
 		}
  	
 		if(isExtractProvinceEnabled(loadOptions)){
	 		extractProvince(location, loadOptions);
 		}
 
		
		if(isExtractStudentListEnabled(loadOptions)){
	 		extractStudentList(location, loadOptions);
 		}	
 		if(isAnalyzeStudentListEnabled(loadOptions)){
	 		analyzeStudentList(location, loadOptions);
 		}
 		
		
		if(isExtractUserListEnabled(loadOptions)){
	 		extractUserList(location, loadOptions);
 		}	
 		if(isAnalyzeUserListEnabled(loadOptions)){
	 		analyzeUserList(location, loadOptions);
 		}
 		
		
		return location;
		
	}

	 

 	protected Location extractDistrict(Location location, Map<String,Object> options) throws Exception{

		if(location.getDistrict() == null){
			return location;
		}
		String districtId = location.getDistrict().getId();
		if( districtId == null){
			return location;
		}
		District district = getDistrictDAO().load(districtId,options);
		if(district != null){
			location.setDistrict(district);
		}
		
 		
 		return location;
 	}
 		
  

 	protected Location extractProvince(Location location, Map<String,Object> options) throws Exception{

		if(location.getProvince() == null){
			return location;
		}
		String provinceId = location.getProvince().getId();
		if( provinceId == null){
			return location;
		}
		Province province = getProvinceDAO().load(provinceId,options);
		if(province != null){
			location.setProvince(province);
		}
		
 		
 		return location;
 	}
 		
 
		
	protected void enhanceStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Location extractStudentList(Location location, Map<String,Object> options){
		
		
		if(location == null){
			return null;
		}
		if(location.getId() == null){
			return location;
		}

		
		
		SmartList<Student> studentList = getStudentDAO().findStudentByAddress(location.getId(),options);
		if(studentList != null){
			enhanceStudentList(studentList,options);
			location.setStudentList(studentList);
		}
		
		return location;
	
	}	
	
	protected Location analyzeStudentList(Location location, Map<String,Object> options){
		
		
		if(location == null){
			return null;
		}
		if(location.getId() == null){
			return location;
		}

		
		
		SmartList<Student> studentList = location.getStudentList();
		if(studentList != null){
			getStudentDAO().analyzeStudentByAddress(studentList, location.getId(), options);
			
		}
		
		return location;
	
	}	
	
		
	protected void enhanceUserList(SmartList<User> userList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Location extractUserList(Location location, Map<String,Object> options){
		
		
		if(location == null){
			return null;
		}
		if(location.getId() == null){
			return location;
		}

		
		
		SmartList<User> userList = getUserDAO().findUserByAddress(location.getId(),options);
		if(userList != null){
			enhanceUserList(userList,options);
			location.setUserList(userList);
		}
		
		return location;
	
	}	
	
	protected Location analyzeUserList(Location location, Map<String,Object> options){
		
		
		if(location == null){
			return null;
		}
		if(location.getId() == null){
			return location;
		}

		
		
		SmartList<User> userList = location.getUserList();
		if(userList != null){
			getUserDAO().analyzeUserByAddress(userList, location.getId(), options);
			
		}
		
		return location;
	
	}	
	
		
		
  	
 	public SmartList<Location> findLocationByDistrict(String districtId,Map<String,Object> options){
 	
  		SmartList<Location> resultList = queryWith(LocationTable.COLUMN_DISTRICT, districtId, options, getLocationMapper());
		// analyzeLocationByDistrict(resultList, districtId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Location> findLocationByDistrict(String districtId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Location> resultList =  queryWithRange(LocationTable.COLUMN_DISTRICT, districtId, options, getLocationMapper(), start, count);
 		//analyzeLocationByDistrict(resultList, districtId, options);
 		return resultList;
 		
 	}
 	public void analyzeLocationByDistrict(SmartList<Location> resultList, String districtId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Location.DISTRICT_PROPERTY, districtId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countLocationByDistrict(String districtId,Map<String,Object> options){

 		return countWith(LocationTable.COLUMN_DISTRICT, districtId, options);
 	}
 	@Override
	public Map<String, Integer> countLocationByDistrictIds(String[] ids, Map<String, Object> options) {
		return countWithIds(LocationTable.COLUMN_DISTRICT, ids, options);
	}
 	
  	
 	public SmartList<Location> findLocationByProvince(String provinceId,Map<String,Object> options){
 	
  		SmartList<Location> resultList = queryWith(LocationTable.COLUMN_PROVINCE, provinceId, options, getLocationMapper());
		// analyzeLocationByProvince(resultList, provinceId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Location> findLocationByProvince(String provinceId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Location> resultList =  queryWithRange(LocationTable.COLUMN_PROVINCE, provinceId, options, getLocationMapper(), start, count);
 		//analyzeLocationByProvince(resultList, provinceId, options);
 		return resultList;
 		
 	}
 	public void analyzeLocationByProvince(SmartList<Location> resultList, String provinceId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Location.PROVINCE_PROPERTY, provinceId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countLocationByProvince(String provinceId,Map<String,Object> options){

 		return countWith(LocationTable.COLUMN_PROVINCE, provinceId, options);
 	}
 	@Override
	public Map<String, Integer> countLocationByProvinceIds(String[] ids, Map<String, Object> options) {
		return countWithIds(LocationTable.COLUMN_PROVINCE, ids, options);
	}
 	
 	
		
		
		

	

	protected Location saveLocation(Location  location){
		
		if(!location.isChanged()){
			return location;
		}
		
		
		String SQL=this.getSaveLocationSQL(location);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveLocationParameters(location);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		location.incVersion();
		return location;
	
	}
	public SmartList<Location> saveLocationList(SmartList<Location> locationList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitLocationList(locationList);
		
		batchLocationCreate((List<Location>)lists[CREATE_LIST_INDEX]);
		
		batchLocationUpdate((List<Location>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Location location:locationList){
			if(location.isChanged()){
				location.incVersion();
			}
			
		
		}
		
		
		return locationList;
	}

	public SmartList<Location> removeLocationList(SmartList<Location> locationList,Map<String,Object> options){
		
		
		super.removeList(locationList, options);
		
		return locationList;
		
		
	}
	
	protected List<Object[]> prepareLocationBatchCreateArgs(List<Location> locationList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Location location:locationList ){
			Object [] parameters = prepareLocationCreateParameters(location);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareLocationBatchUpdateArgs(List<Location> locationList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Location location:locationList ){
			if(!location.isChanged()){
				continue;
			}
			Object [] parameters = prepareLocationUpdateParameters(location);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchLocationCreate(List<Location> locationList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareLocationBatchCreateArgs(locationList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchLocationUpdate(List<Location> locationList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareLocationBatchUpdateArgs(locationList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitLocationList(List<Location> locationList){
		
		List<Location> locationCreateList=new ArrayList<Location>();
		List<Location> locationUpdateList=new ArrayList<Location>();
		
		for(Location location: locationList){
			if(isUpdateRequest(location)){
				locationUpdateList.add( location);
				continue;
			}
			locationCreateList.add(location);
		}
		
		return new Object[]{locationCreateList,locationUpdateList};
	}
	
	protected boolean isUpdateRequest(Location location){
 		return location.getVersion() > 0;
 	}
 	protected String getSaveLocationSQL(Location location){
 		if(isUpdateRequest(location)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveLocationParameters(Location location){
 		if(isUpdateRequest(location) ){
 			return prepareLocationUpdateParameters(location);
 		}
 		return prepareLocationCreateParameters(location);
 	}
 	protected Object[] prepareLocationUpdateParameters(Location location){
 		Object[] parameters = new Object[9];
 
 		parameters[0] = location.getName();
 		parameters[1] = location.getAddress(); 	
 		if(location.getDistrict() != null){
 			parameters[2] = location.getDistrict().getId();
 		}
  	
 		if(location.getProvince() != null){
 			parameters[3] = location.getProvince().getId();
 		}
 
 		parameters[4] = location.getLatitude();
 		parameters[5] = location.getLongitude();		
 		parameters[6] = location.nextVersion();
 		parameters[7] = location.getId();
 		parameters[8] = location.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareLocationCreateParameters(Location location){
		Object[] parameters = new Object[7];
		String newLocationId=getNextId();
		location.setId(newLocationId);
		parameters[0] =  location.getId();
 
 		parameters[1] = location.getName();
 		parameters[2] = location.getAddress(); 	
 		if(location.getDistrict() != null){
 			parameters[3] = location.getDistrict().getId();
 		
 		}
 		 	
 		if(location.getProvince() != null){
 			parameters[4] = location.getProvince().getId();
 		
 		}
 		
 		parameters[5] = location.getLatitude();
 		parameters[6] = location.getLongitude();		
 				
 		return parameters;
 	}
 	
	protected Location saveInternalLocation(Location location, Map<String,Object> options){
		
		saveLocation(location);
 	
 		if(isSaveDistrictEnabled(options)){
	 		saveDistrict(location, options);
 		}
  	
 		if(isSaveProvinceEnabled(options)){
	 		saveProvince(location, options);
 		}
 
		
		if(isSaveStudentListEnabled(options)){
	 		saveStudentList(location, options);
	 		//removeStudentList(location, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveUserListEnabled(options)){
	 		saveUserList(location, options);
	 		//removeUserList(location, options);
	 		//Not delete the record
	 		
 		}		
		
		return location;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Location saveDistrict(Location location, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(location.getDistrict() == null){
 			return location;//do nothing when it is null
 		}
 		
 		getDistrictDAO().save(location.getDistrict(),options);
 		return location;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Location saveProvince(Location location, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(location.getProvince() == null){
 			return location;//do nothing when it is null
 		}
 		
 		getProvinceDAO().save(location.getProvince(),options);
 		return location;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Location planToRemoveStudentList(Location location, String studentIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, location.getId());
		key.put(Student.ID_PROPERTY, studentIds);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return location;
		}
		if(externalStudentList.isEmpty()){
			return location;
		}
		
		for(Student studentItem: externalStudentList){

			studentItem.clearFromAll();
		}
		
		
		SmartList<Student> studentList = location.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return location;	
	
	}


	//disconnect Location with student_id in Student
	public Location planToRemoveStudentListWithStudentId(Location location, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, location.getId());
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return location;
		}
		if(externalStudentList.isEmpty()){
			return location;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearStudentId();
			studentItem.clearAddress();
			
		}
		
		
		SmartList<Student> studentList = location.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return location;
	}
	
	public int countStudentListWithStudentId(String locationId, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, locationId);
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Location with user in Student
	public Location planToRemoveStudentListWithUser(Location location, String userId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, location.getId());
		key.put(Student.USER_PROPERTY, userId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return location;
		}
		if(externalStudentList.isEmpty()){
			return location;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearUser();
			studentItem.clearAddress();
			
		}
		
		
		SmartList<Student> studentList = location.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return location;
	}
	
	public int countStudentListWithUser(String locationId, String userId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, locationId);
		key.put(Student.USER_PROPERTY, userId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Location with platform in Student
	public Location planToRemoveStudentListWithPlatform(Location location, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, location.getId());
		key.put(Student.PLATFORM_PROPERTY, platformId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return location;
		}
		if(externalStudentList.isEmpty()){
			return location;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearPlatform();
			studentItem.clearAddress();
			
		}
		
		
		SmartList<Student> studentList = location.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return location;
	}
	
	public int countStudentListWithPlatform(String locationId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, locationId);
		key.put(Student.PLATFORM_PROPERTY, platformId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Location with change_request in Student
	public Location planToRemoveStudentListWithChangeRequest(Location location, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, location.getId());
		key.put(Student.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return location;
		}
		if(externalStudentList.isEmpty()){
			return location;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearChangeRequest();
			studentItem.clearAddress();
			
		}
		
		
		SmartList<Student> studentList = location.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return location;
	}
	
	public int countStudentListWithChangeRequest(String locationId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, locationId);
		key.put(Student.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	public Location planToRemoveUserList(Location location, String userIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.ADDRESS_PROPERTY, location.getId());
		key.put(User.ID_PROPERTY, userIds);
		
		SmartList<User> externalUserList = getUserDAO().
				findUserWithKey(key, options);
		if(externalUserList == null){
			return location;
		}
		if(externalUserList.isEmpty()){
			return location;
		}
		
		for(User userItem: externalUserList){

			userItem.clearFromAll();
		}
		
		
		SmartList<User> userList = location.getUserList();		
		userList.addAllToRemoveList(externalUserList);
		return location;	
	
	}


	//disconnect Location with platform in User
	public Location planToRemoveUserListWithPlatform(Location location, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.ADDRESS_PROPERTY, location.getId());
		key.put(User.PLATFORM_PROPERTY, platformId);
		
		SmartList<User> externalUserList = getUserDAO().
				findUserWithKey(key, options);
		if(externalUserList == null){
			return location;
		}
		if(externalUserList.isEmpty()){
			return location;
		}
		
		for(User userItem: externalUserList){
			userItem.clearPlatform();
			userItem.clearAddress();
			
		}
		
		
		SmartList<User> userList = location.getUserList();		
		userList.addAllToRemoveList(externalUserList);
		return location;
	}
	
	public int countUserListWithPlatform(String locationId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.ADDRESS_PROPERTY, locationId);
		key.put(User.PLATFORM_PROPERTY, platformId);
		
		int count = getUserDAO().countUserWithKey(key, options);
		return count;
	}
	

		
	protected Location saveStudentList(Location location, Map<String,Object> options){
		
		
		
		
		SmartList<Student> studentList = location.getStudentList();
		if(studentList == null){
			//null list means nothing
			return location;
		}
		SmartList<Student> mergedUpdateStudentList = new SmartList<Student>();
		
		
		mergedUpdateStudentList.addAll(studentList); 
		if(studentList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentList.addAll(studentList.getToRemoveList());
			studentList.removeAll(studentList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentDAO().saveStudentList(mergedUpdateStudentList,options);
		
		if(studentList.getToRemoveList() != null){
			studentList.removeAll(studentList.getToRemoveList());
		}
		
		
		return location;
	
	}
	
	protected Location removeStudentList(Location location, Map<String,Object> options){
	
	
		SmartList<Student> studentList = location.getStudentList();
		if(studentList == null){
			return location;
		}	
	
		SmartList<Student> toRemoveStudentList = studentList.getToRemoveList();
		
		if(toRemoveStudentList == null){
			return location;
		}
		if(toRemoveStudentList.isEmpty()){
			return location;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDAO().removeStudentList(toRemoveStudentList,options);
		
		return location;
	
	}
	
	

 	
 	
	
	
	
		
	protected Location saveUserList(Location location, Map<String,Object> options){
		
		
		
		
		SmartList<User> userList = location.getUserList();
		if(userList == null){
			//null list means nothing
			return location;
		}
		SmartList<User> mergedUpdateUserList = new SmartList<User>();
		
		
		mergedUpdateUserList.addAll(userList); 
		if(userList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateUserList.addAll(userList.getToRemoveList());
			userList.removeAll(userList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getUserDAO().saveUserList(mergedUpdateUserList,options);
		
		if(userList.getToRemoveList() != null){
			userList.removeAll(userList.getToRemoveList());
		}
		
		
		return location;
	
	}
	
	protected Location removeUserList(Location location, Map<String,Object> options){
	
	
		SmartList<User> userList = location.getUserList();
		if(userList == null){
			return location;
		}	
	
		SmartList<User> toRemoveUserList = userList.getToRemoveList();
		
		if(toRemoveUserList == null){
			return location;
		}
		if(toRemoveUserList.isEmpty()){
			return location;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getUserDAO().removeUserList(toRemoveUserList,options);
		
		return location;
	
	}
	
	

 	
 	
	
	
	
		

	public Location present(Location location,Map<String, Object> options){
	
		presentStudentList(location,options);
		presentUserList(location,options);

		return location;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Location presentStudentList(
			Location location,
			Map<String, Object> options) {

		SmartList<Student> studentList = location.getStudentList();		
				SmartList<Student> newList= presentSubList(location.getId(),
				studentList,
				options,
				getStudentDAO()::countStudentByAddress,
				getStudentDAO()::findStudentByAddress
				);

		
		location.setStudentList(newList);
		

		return location;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Location presentUserList(
			Location location,
			Map<String, Object> options) {

		SmartList<User> userList = location.getUserList();		
				SmartList<User> newList= presentSubList(location.getId(),
				userList,
				options,
				getUserDAO()::countUserByAddress,
				getUserDAO()::findUserByAddress
				);

		
		location.setUserList(newList);
		

		return location;
	}			
		

	
    public SmartList<Location> requestCandidateLocationForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(LocationTable.COLUMN_NAME, filterKey, pageNo, pageSize, getLocationMapper());
    }
		
    public SmartList<Location> requestCandidateLocationForUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(LocationTable.COLUMN_NAME, filterKey, pageNo, pageSize, getLocationMapper());
    }
		

	protected String getTableName(){
		return LocationTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Location> locationList) {		
		this.enhanceListInternal(locationList, this.getLocationMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Student的address的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<Location> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.ADDRESS_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Student> loadedObjs = userContext.getDAOGroup().getStudentDAO().findStudentWithKey(key, options);
		Map<String, List<Student>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getAddress().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Student> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Student> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:User的address的UserList
	public SmartList<User> loadOurUserList(HealthUserContext userContext, List<Location> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.ADDRESS_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<User> loadedObjs = userContext.getDAOGroup().getUserDAO().findUserWithKey(key, options);
		Map<String, List<User>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getAddress().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<User> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<User> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setUserList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Location> locationList = ownerEntity.collectRefsWithType(Location.INTERNAL_TYPE);
		this.enhanceList(locationList);
		
	}
	
	@Override
	public SmartList<Location> findLocationWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getLocationMapper());

	}
	@Override
	public int countLocationWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countLocationWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Location> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getLocationMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


