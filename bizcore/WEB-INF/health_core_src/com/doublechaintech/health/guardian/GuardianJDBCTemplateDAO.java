
package com.doublechaintech.health.guardian;

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


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class GuardianJDBCTemplateDAO extends HealthBaseDAOImpl implements GuardianDAO{
 
 	
 	private  WechatUserDAO  wechatUserDAO;
 	public void setWechatUserDAO(WechatUserDAO wechatUserDAO){
	 	this.wechatUserDAO = wechatUserDAO;
 	}
 	public WechatUserDAO getWechatUserDAO(){
	 	return this.wechatUserDAO;
 	}
 
 	
 	private  LocationDAO  locationDAO;
 	public void setLocationDAO(LocationDAO locationDAO){
	 	this.locationDAO = locationDAO;
 	}
 	public LocationDAO getLocationDAO(){
	 	return this.locationDAO;
 	}
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
 	
			
		

	
	/*
	protected Guardian load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalGuardian(accessKey, options);
	}
	*/
	
	public SmartList<Guardian> loadAll() {
	    return this.loadAll(getGuardianMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Guardian load(String id,Map<String,Object> options) throws Exception{
		return loadInternalGuardian(GuardianTable.withId(id), options);
	}
	
	
	
	public Guardian save(Guardian guardian,Map<String,Object> options){
		
		String methodName="save(Guardian guardian,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(guardian, methodName, "guardian");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalGuardian(guardian,options);
	}
	public Guardian clone(String guardianId, Map<String,Object> options) throws Exception{
	
		return clone(GuardianTable.withId(guardianId),options);
	}
	
	protected Guardian clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String guardianId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Guardian newGuardian = loadInternalGuardian(accessKey, options);
		newGuardian.setVersion(0);
		
		
 		
 		if(isSaveStudentListEnabled(options)){
 			for(Student item: newGuardian.getStudentList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalGuardian(newGuardian,options);
		
		return newGuardian;
	}
	
	
	
	

	protected void throwIfHasException(String guardianId,int version,int count) throws Exception{
		if (count == 1) {
			throw new GuardianVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new GuardianNotFoundException(
					"The " + this.getTableName() + "(" + guardianId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String guardianId, int version) throws Exception{
	
		String methodName="delete(String guardianId, int version)";
		assertMethodArgumentNotNull(guardianId, methodName, "guardianId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{guardianId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(guardianId,version);
		}
		
	
	}
	
	
	
	
	

	public Guardian disconnectFromAll(String guardianId, int version) throws Exception{
	
		
		Guardian guardian = loadInternalGuardian(GuardianTable.withId(guardianId), emptyOptions());
		guardian.clearFromAll();
		this.saveGuardian(guardian);
		return guardian;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return GuardianTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "guardian";
	}
	@Override
	protected String getBeanName() {
		
		return "guardian";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return GuardianTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractAddressEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, GuardianTokens.ADDRESS);
 	}

 	protected boolean isSaveAddressEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, GuardianTokens.ADDRESS);
 	}
 	

 	
  

 	protected boolean isExtractWechatUserEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, GuardianTokens.WECHATUSER);
 	}

 	protected boolean isSaveWechatUserEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, GuardianTokens.WECHATUSER);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, GuardianTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, GuardianTokens.PLATFORM);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, GuardianTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, GuardianTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentListEnabled(Map<String,Object> options){		
 		return checkOptions(options,GuardianTokens.STUDENT_LIST);
 	}
 	protected boolean isAnalyzeStudentListEnabled(Map<String,Object> options){		 		
 		return GuardianTokens.of(options).analyzeStudentListEnabled();
 	}
	
	protected boolean isSaveStudentListEnabled(Map<String,Object> options){
		return checkOptions(options, GuardianTokens.STUDENT_LIST);
		
 	}
 	
		

	

	protected GuardianMapper getGuardianMapper(){
		return new GuardianMapper();
	}

	
	
	protected Guardian extractGuardian(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Guardian guardian = loadSingleObject(accessKey, getGuardianMapper());
			return guardian;
		}catch(EmptyResultDataAccessException e){
			throw new GuardianNotFoundException("Guardian("+accessKey+") is not found!");
		}

	}

	
	

	protected Guardian loadInternalGuardian(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Guardian guardian = extractGuardian(accessKey, loadOptions);
 	
 		if(isExtractAddressEnabled(loadOptions)){
	 		extractAddress(guardian, loadOptions);
 		}
  	
 		if(isExtractWechatUserEnabled(loadOptions)){
	 		extractWechatUser(guardian, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(guardian, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(guardian, loadOptions);
 		}
 
		
		if(isExtractStudentListEnabled(loadOptions)){
	 		extractStudentList(guardian, loadOptions);
 		}	
 		if(isAnalyzeStudentListEnabled(loadOptions)){
	 		analyzeStudentList(guardian, loadOptions);
 		}
 		
		
		return guardian;
		
	}

	 

 	protected Guardian extractAddress(Guardian guardian, Map<String,Object> options) throws Exception{

		if(guardian.getAddress() == null){
			return guardian;
		}
		String addressId = guardian.getAddress().getId();
		if( addressId == null){
			return guardian;
		}
		Location address = getLocationDAO().load(addressId,options);
		if(address != null){
			guardian.setAddress(address);
		}
		
 		
 		return guardian;
 	}
 		
  

 	protected Guardian extractWechatUser(Guardian guardian, Map<String,Object> options) throws Exception{

		if(guardian.getWechatUser() == null){
			return guardian;
		}
		String wechatUserId = guardian.getWechatUser().getId();
		if( wechatUserId == null){
			return guardian;
		}
		WechatUser wechatUser = getWechatUserDAO().load(wechatUserId,options);
		if(wechatUser != null){
			guardian.setWechatUser(wechatUser);
		}
		
 		
 		return guardian;
 	}
 		
  

 	protected Guardian extractPlatform(Guardian guardian, Map<String,Object> options) throws Exception{

		if(guardian.getPlatform() == null){
			return guardian;
		}
		String platformId = guardian.getPlatform().getId();
		if( platformId == null){
			return guardian;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			guardian.setPlatform(platform);
		}
		
 		
 		return guardian;
 	}
 		
  

 	protected Guardian extractCq(Guardian guardian, Map<String,Object> options) throws Exception{

		if(guardian.getCq() == null){
			return guardian;
		}
		String cqId = guardian.getCq().getId();
		if( cqId == null){
			return guardian;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			guardian.setCq(cq);
		}
		
 		
 		return guardian;
 	}
 		
 
		
	protected void enhanceStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Guardian extractStudentList(Guardian guardian, Map<String,Object> options){
		
		
		if(guardian == null){
			return null;
		}
		if(guardian.getId() == null){
			return guardian;
		}

		
		
		SmartList<Student> studentList = getStudentDAO().findStudentByGuardian(guardian.getId(),options);
		if(studentList != null){
			enhanceStudentList(studentList,options);
			guardian.setStudentList(studentList);
		}
		
		return guardian;
	
	}	
	
	protected Guardian analyzeStudentList(Guardian guardian, Map<String,Object> options){
		
		
		if(guardian == null){
			return null;
		}
		if(guardian.getId() == null){
			return guardian;
		}

		
		
		SmartList<Student> studentList = guardian.getStudentList();
		if(studentList != null){
			getStudentDAO().analyzeStudentByGuardian(studentList, guardian.getId(), options);
			
		}
		
		return guardian;
	
	}	
	
		
		
  	
 	public SmartList<Guardian> findGuardianByAddress(String locationId,Map<String,Object> options){
 	
  		SmartList<Guardian> resultList = queryWith(GuardianTable.COLUMN_ADDRESS, locationId, options, getGuardianMapper());
		// analyzeGuardianByAddress(resultList, locationId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Guardian> findGuardianByAddress(String locationId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Guardian> resultList =  queryWithRange(GuardianTable.COLUMN_ADDRESS, locationId, options, getGuardianMapper(), start, count);
 		//analyzeGuardianByAddress(resultList, locationId, options);
 		return resultList;
 		
 	}
 	public void analyzeGuardianByAddress(SmartList<Guardian> resultList, String locationId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Guardian.ADDRESS_PROPERTY, locationId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Guardian.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("《卫报》");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Guardian.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Guardian.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countGuardianByAddress(String locationId,Map<String,Object> options){

 		return countWith(GuardianTable.COLUMN_ADDRESS, locationId, options);
 	}
 	@Override
	public Map<String, Integer> countGuardianByAddressIds(String[] ids, Map<String, Object> options) {
		return countWithIds(GuardianTable.COLUMN_ADDRESS, ids, options);
	}
 	
  	
 	public SmartList<Guardian> findGuardianByWechatUser(String wechatUserId,Map<String,Object> options){
 	
  		SmartList<Guardian> resultList = queryWith(GuardianTable.COLUMN_WECHAT_USER, wechatUserId, options, getGuardianMapper());
		// analyzeGuardianByWechatUser(resultList, wechatUserId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Guardian> findGuardianByWechatUser(String wechatUserId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Guardian> resultList =  queryWithRange(GuardianTable.COLUMN_WECHAT_USER, wechatUserId, options, getGuardianMapper(), start, count);
 		//analyzeGuardianByWechatUser(resultList, wechatUserId, options);
 		return resultList;
 		
 	}
 	public void analyzeGuardianByWechatUser(SmartList<Guardian> resultList, String wechatUserId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Guardian.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("《卫报》");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Guardian.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Guardian.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countGuardianByWechatUser(String wechatUserId,Map<String,Object> options){

 		return countWith(GuardianTable.COLUMN_WECHAT_USER, wechatUserId, options);
 	}
 	@Override
	public Map<String, Integer> countGuardianByWechatUserIds(String[] ids, Map<String, Object> options) {
		return countWithIds(GuardianTable.COLUMN_WECHAT_USER, ids, options);
	}
 	
  	
 	public SmartList<Guardian> findGuardianByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Guardian> resultList = queryWith(GuardianTable.COLUMN_PLATFORM, platformId, options, getGuardianMapper());
		// analyzeGuardianByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Guardian> findGuardianByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Guardian> resultList =  queryWithRange(GuardianTable.COLUMN_PLATFORM, platformId, options, getGuardianMapper(), start, count);
 		//analyzeGuardianByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeGuardianByPlatform(SmartList<Guardian> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Guardian.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Guardian.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("《卫报》");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Guardian.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Guardian.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countGuardianByPlatform(String platformId,Map<String,Object> options){

 		return countWith(GuardianTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countGuardianByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(GuardianTable.COLUMN_PLATFORM, ids, options);
	}
 	
  	
 	public SmartList<Guardian> findGuardianByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<Guardian> resultList = queryWith(GuardianTable.COLUMN_CQ, changeRequestId, options, getGuardianMapper());
		// analyzeGuardianByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Guardian> findGuardianByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Guardian> resultList =  queryWithRange(GuardianTable.COLUMN_CQ, changeRequestId, options, getGuardianMapper(), start, count);
 		//analyzeGuardianByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeGuardianByCq(SmartList<Guardian> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Guardian.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Guardian.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("《卫报》");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Guardian.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Guardian.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countGuardianByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(GuardianTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countGuardianByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(GuardianTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected Guardian saveGuardian(Guardian  guardian){
		
		if(!guardian.isChanged()){
			return guardian;
		}
		
		
		String SQL=this.getSaveGuardianSQL(guardian);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveGuardianParameters(guardian);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		guardian.incVersion();
		return guardian;
	
	}
	public SmartList<Guardian> saveGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitGuardianList(guardianList);
		
		batchGuardianCreate((List<Guardian>)lists[CREATE_LIST_INDEX]);
		
		batchGuardianUpdate((List<Guardian>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Guardian guardian:guardianList){
			if(guardian.isChanged()){
				guardian.incVersion();
			}
			
		
		}
		
		
		return guardianList;
	}

	public SmartList<Guardian> removeGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options){
		
		
		super.removeList(guardianList, options);
		
		return guardianList;
		
		
	}
	
	protected List<Object[]> prepareGuardianBatchCreateArgs(List<Guardian> guardianList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Guardian guardian:guardianList ){
			Object [] parameters = prepareGuardianCreateParameters(guardian);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareGuardianBatchUpdateArgs(List<Guardian> guardianList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Guardian guardian:guardianList ){
			if(!guardian.isChanged()){
				continue;
			}
			Object [] parameters = prepareGuardianUpdateParameters(guardian);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchGuardianCreate(List<Guardian> guardianList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareGuardianBatchCreateArgs(guardianList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchGuardianUpdate(List<Guardian> guardianList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareGuardianBatchUpdateArgs(guardianList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitGuardianList(List<Guardian> guardianList){
		
		List<Guardian> guardianCreateList=new ArrayList<Guardian>();
		List<Guardian> guardianUpdateList=new ArrayList<Guardian>();
		
		for(Guardian guardian: guardianList){
			if(isUpdateRequest(guardian)){
				guardianUpdateList.add( guardian);
				continue;
			}
			guardianCreateList.add(guardian);
		}
		
		return new Object[]{guardianCreateList,guardianUpdateList};
	}
	
	protected boolean isUpdateRequest(Guardian guardian){
 		return guardian.getVersion() > 0;
 	}
 	protected String getSaveGuardianSQL(Guardian guardian){
 		if(isUpdateRequest(guardian)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveGuardianParameters(Guardian guardian){
 		if(isUpdateRequest(guardian) ){
 			return prepareGuardianUpdateParameters(guardian);
 		}
 		return prepareGuardianCreateParameters(guardian);
 	}
 	protected Object[] prepareGuardianUpdateParameters(Guardian guardian){
 		Object[] parameters = new Object[10];
 
 		parameters[0] = guardian.getName();
 		parameters[1] = guardian.getMobile(); 	
 		if(guardian.getAddress() != null){
 			parameters[2] = guardian.getAddress().getId();
 		}
  	
 		if(guardian.getWechatUser() != null){
 			parameters[3] = guardian.getWechatUser().getId();
 		}
 
 		parameters[4] = guardian.getCreateTime(); 	
 		if(guardian.getPlatform() != null){
 			parameters[5] = guardian.getPlatform().getId();
 		}
  	
 		if(guardian.getCq() != null){
 			parameters[6] = guardian.getCq().getId();
 		}
 		
 		parameters[7] = guardian.nextVersion();
 		parameters[8] = guardian.getId();
 		parameters[9] = guardian.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareGuardianCreateParameters(Guardian guardian){
		Object[] parameters = new Object[8];
		String newGuardianId=getNextId();
		guardian.setId(newGuardianId);
		parameters[0] =  guardian.getId();
 
 		parameters[1] = guardian.getName();
 		parameters[2] = guardian.getMobile(); 	
 		if(guardian.getAddress() != null){
 			parameters[3] = guardian.getAddress().getId();
 		
 		}
 		 	
 		if(guardian.getWechatUser() != null){
 			parameters[4] = guardian.getWechatUser().getId();
 		
 		}
 		
 		parameters[5] = guardian.getCreateTime(); 	
 		if(guardian.getPlatform() != null){
 			parameters[6] = guardian.getPlatform().getId();
 		
 		}
 		 	
 		if(guardian.getCq() != null){
 			parameters[7] = guardian.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Guardian saveInternalGuardian(Guardian guardian, Map<String,Object> options){
		
		saveGuardian(guardian);
 	
 		if(isSaveAddressEnabled(options)){
	 		saveAddress(guardian, options);
 		}
  	
 		if(isSaveWechatUserEnabled(options)){
	 		saveWechatUser(guardian, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(guardian, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(guardian, options);
 		}
 
		
		if(isSaveStudentListEnabled(options)){
	 		saveStudentList(guardian, options);
	 		//removeStudentList(guardian, options);
	 		//Not delete the record
	 		
 		}		
		
		return guardian;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Guardian saveAddress(Guardian guardian, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(guardian.getAddress() == null){
 			return guardian;//do nothing when it is null
 		}
 		
 		getLocationDAO().save(guardian.getAddress(),options);
 		return guardian;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Guardian saveWechatUser(Guardian guardian, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(guardian.getWechatUser() == null){
 			return guardian;//do nothing when it is null
 		}
 		
 		getWechatUserDAO().save(guardian.getWechatUser(),options);
 		return guardian;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Guardian savePlatform(Guardian guardian, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(guardian.getPlatform() == null){
 			return guardian;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(guardian.getPlatform(),options);
 		return guardian;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Guardian saveCq(Guardian guardian, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(guardian.getCq() == null){
 			return guardian;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(guardian.getCq(),options);
 		return guardian;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Guardian planToRemoveStudentList(Guardian guardian, String studentIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardian.getId());
		key.put(Student.ID_PROPERTY, studentIds);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return guardian;
		}
		if(externalStudentList.isEmpty()){
			return guardian;
		}
		
		for(Student studentItem: externalStudentList){

			studentItem.clearFromAll();
		}
		
		
		SmartList<Student> studentList = guardian.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return guardian;	
	
	}


	//disconnect Guardian with school_class in Student
	public Guardian planToRemoveStudentListWithSchoolClass(Guardian guardian, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardian.getId());
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return guardian;
		}
		if(externalStudentList.isEmpty()){
			return guardian;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearSchoolClass();
			studentItem.clearGuardian();
			
		}
		
		
		SmartList<Student> studentList = guardian.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return guardian;
	}
	
	public int countStudentListWithSchoolClass(String guardianId, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Guardian with student_id in Student
	public Guardian planToRemoveStudentListWithStudentId(Guardian guardian, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardian.getId());
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return guardian;
		}
		if(externalStudentList.isEmpty()){
			return guardian;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearStudentId();
			studentItem.clearGuardian();
			
		}
		
		
		SmartList<Student> studentList = guardian.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return guardian;
	}
	
	public int countStudentListWithStudentId(String guardianId, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Guardian with cq in Student
	public Guardian planToRemoveStudentListWithCq(Guardian guardian, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardian.getId());
		key.put(Student.CQ_PROPERTY, cqId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return guardian;
		}
		if(externalStudentList.isEmpty()){
			return guardian;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearCq();
			studentItem.clearGuardian();
			
		}
		
		
		SmartList<Student> studentList = guardian.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return guardian;
	}
	
	public int countStudentListWithCq(String guardianId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		key.put(Student.CQ_PROPERTY, cqId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	

		
	protected Guardian saveStudentList(Guardian guardian, Map<String,Object> options){
		
		
		
		
		SmartList<Student> studentList = guardian.getStudentList();
		if(studentList == null){
			//null list means nothing
			return guardian;
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
		
		
		return guardian;
	
	}
	
	protected Guardian removeStudentList(Guardian guardian, Map<String,Object> options){
	
	
		SmartList<Student> studentList = guardian.getStudentList();
		if(studentList == null){
			return guardian;
		}	
	
		SmartList<Student> toRemoveStudentList = studentList.getToRemoveList();
		
		if(toRemoveStudentList == null){
			return guardian;
		}
		if(toRemoveStudentList.isEmpty()){
			return guardian;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDAO().removeStudentList(toRemoveStudentList,options);
		
		return guardian;
	
	}
	
	

 	
 	
	
	
	
		

	public Guardian present(Guardian guardian,Map<String, Object> options){
	
		presentStudentList(guardian,options);

		return guardian;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Guardian presentStudentList(
			Guardian guardian,
			Map<String, Object> options) {

		SmartList<Student> studentList = guardian.getStudentList();		
				SmartList<Student> newList= presentSubList(guardian.getId(),
				studentList,
				options,
				getStudentDAO()::countStudentByGuardian,
				getStudentDAO()::findStudentByGuardian
				);

		
		guardian.setStudentList(newList);
		

		return guardian;
	}			
		

	
    public SmartList<Guardian> requestCandidateGuardianForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(GuardianTable.COLUMN_NAME, filterKey, pageNo, pageSize, getGuardianMapper());
    }
		

	protected String getTableName(){
		return GuardianTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Guardian> guardianList) {		
		this.enhanceListInternal(guardianList, this.getGuardianMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Student的guardian的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<Guardian> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.GUARDIAN_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Student> loadedObjs = userContext.getDAOGroup().getStudentDAO().findStudentWithKey(key, options);
		Map<String, List<Student>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getGuardian().getId()));
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
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Guardian> guardianList = ownerEntity.collectRefsWithType(Guardian.INTERNAL_TYPE);
		this.enhanceList(guardianList);
		
	}
	
	@Override
	public SmartList<Guardian> findGuardianWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getGuardianMapper());

	}
	@Override
	public int countGuardianWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countGuardianWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Guardian> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getGuardianMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


