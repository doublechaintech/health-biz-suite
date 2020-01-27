
package com.doublechaintech.health.wechatuser;

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
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.usertype.UserType;

import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.usertype.UserTypeDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.guardian.GuardianDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class WechatUserJDBCTemplateDAO extends HealthBaseDAOImpl implements WechatUserDAO{
 
 	
 	private  LocationDAO  locationDAO;
 	public void setLocationDAO(LocationDAO locationDAO){
	 	this.locationDAO = locationDAO;
 	}
 	public LocationDAO getLocationDAO(){
	 	return this.locationDAO;
 	}
 
 	
 	private  UserTypeDAO  userTypeDAO;
 	public void setUserTypeDAO(UserTypeDAO userTypeDAO){
	 	this.userTypeDAO = userTypeDAO;
 	}
 	public UserTypeDAO getUserTypeDAO(){
	 	return this.userTypeDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  GuardianDAO  guardianDAO;
 	public void setGuardianDAO(GuardianDAO pGuardianDAO){
 	
 		if(pGuardianDAO == null){
 			throw new IllegalStateException("Do not try to set guardianDAO to null.");
 		}
	 	this.guardianDAO = pGuardianDAO;
 	}
 	public GuardianDAO getGuardianDAO(){
 		if(this.guardianDAO == null){
 			throw new IllegalStateException("The guardianDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.guardianDAO;
 	}	
 	
			
		
	
  	private  ClassQuestionDAO  classQuestionDAO;
 	public void setClassQuestionDAO(ClassQuestionDAO pClassQuestionDAO){
 	
 		if(pClassQuestionDAO == null){
 			throw new IllegalStateException("Do not try to set classQuestionDAO to null.");
 		}
	 	this.classQuestionDAO = pClassQuestionDAO;
 	}
 	public ClassQuestionDAO getClassQuestionDAO(){
 		if(this.classQuestionDAO == null){
 			throw new IllegalStateException("The classQuestionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.classQuestionDAO;
 	}	
 	
			
		
	
  	private  ClassDailyHealthSurveyDAO  classDailyHealthSurveyDAO;
 	public void setClassDailyHealthSurveyDAO(ClassDailyHealthSurveyDAO pClassDailyHealthSurveyDAO){
 	
 		if(pClassDailyHealthSurveyDAO == null){
 			throw new IllegalStateException("Do not try to set classDailyHealthSurveyDAO to null.");
 		}
	 	this.classDailyHealthSurveyDAO = pClassDailyHealthSurveyDAO;
 	}
 	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO(){
 		if(this.classDailyHealthSurveyDAO == null){
 			throw new IllegalStateException("The classDailyHealthSurveyDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.classDailyHealthSurveyDAO;
 	}	
 	
			
		
	
  	private  WechatLoginInfoDAO  wechatLoginInfoDAO;
 	public void setWechatLoginInfoDAO(WechatLoginInfoDAO pWechatLoginInfoDAO){
 	
 		if(pWechatLoginInfoDAO == null){
 			throw new IllegalStateException("Do not try to set wechatLoginInfoDAO to null.");
 		}
	 	this.wechatLoginInfoDAO = pWechatLoginInfoDAO;
 	}
 	public WechatLoginInfoDAO getWechatLoginInfoDAO(){
 		if(this.wechatLoginInfoDAO == null){
 			throw new IllegalStateException("The wechatLoginInfoDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.wechatLoginInfoDAO;
 	}	
 	
			
		

	
	/*
	protected WechatUser load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalWechatUser(accessKey, options);
	}
	*/
	
	public SmartList<WechatUser> loadAll() {
	    return this.loadAll(getWechatUserMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public WechatUser load(String id,Map<String,Object> options) throws Exception{
		return loadInternalWechatUser(WechatUserTable.withId(id), options);
	}
	
	
	
	public WechatUser save(WechatUser wechatUser,Map<String,Object> options){
		
		String methodName="save(WechatUser wechatUser,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(wechatUser, methodName, "wechatUser");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalWechatUser(wechatUser,options);
	}
	public WechatUser clone(String wechatUserId, Map<String,Object> options) throws Exception{
	
		return clone(WechatUserTable.withId(wechatUserId),options);
	}
	
	protected WechatUser clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String wechatUserId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		WechatUser newWechatUser = loadInternalWechatUser(accessKey, options);
		newWechatUser.setVersion(0);
		
		
 		
 		if(isSaveGuardianListEnabled(options)){
 			for(Guardian item: newWechatUser.getGuardianList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveClassQuestionListEnabled(options)){
 			for(ClassQuestion item: newWechatUser.getClassQuestionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveClassDailyHealthSurveyListEnabled(options)){
 			for(ClassDailyHealthSurvey item: newWechatUser.getClassDailyHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWechatLoginInfoListEnabled(options)){
 			for(WechatLoginInfo item: newWechatUser.getWechatLoginInfoList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalWechatUser(newWechatUser,options);
		
		return newWechatUser;
	}
	
	
	
	

	protected void throwIfHasException(String wechatUserId,int version,int count) throws Exception{
		if (count == 1) {
			throw new WechatUserVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new WechatUserNotFoundException(
					"The " + this.getTableName() + "(" + wechatUserId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String wechatUserId, int version) throws Exception{
	
		String methodName="delete(String wechatUserId, int version)";
		assertMethodArgumentNotNull(wechatUserId, methodName, "wechatUserId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{wechatUserId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(wechatUserId,version);
		}
		
	
	}
	
	
	
	
	

	public WechatUser disconnectFromAll(String wechatUserId, int version) throws Exception{
	
		
		WechatUser wechatUser = loadInternalWechatUser(WechatUserTable.withId(wechatUserId), emptyOptions());
		wechatUser.clearFromAll();
		this.saveWechatUser(wechatUser);
		return wechatUser;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return WechatUserTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "wechat_user";
	}
	@Override
	protected String getBeanName() {
		
		return "wechatUser";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return WechatUserTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractAddressEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, WechatUserTokens.ADDRESS);
 	}

 	protected boolean isSaveAddressEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, WechatUserTokens.ADDRESS);
 	}
 	

 	
  

 	protected boolean isExtractUserTypeEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, WechatUserTokens.USERTYPE);
 	}

 	protected boolean isSaveUserTypeEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, WechatUserTokens.USERTYPE);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, WechatUserTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, WechatUserTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractGuardianListEnabled(Map<String,Object> options){		
 		return checkOptions(options,WechatUserTokens.GUARDIAN_LIST);
 	}
 	protected boolean isAnalyzeGuardianListEnabled(Map<String,Object> options){		 		
 		return WechatUserTokens.of(options).analyzeGuardianListEnabled();
 	}
	
	protected boolean isSaveGuardianListEnabled(Map<String,Object> options){
		return checkOptions(options, WechatUserTokens.GUARDIAN_LIST);
		
 	}
 	
		
	
	protected boolean isExtractClassQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,WechatUserTokens.CLASS_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeClassQuestionListEnabled(Map<String,Object> options){		 		
 		return WechatUserTokens.of(options).analyzeClassQuestionListEnabled();
 	}
	
	protected boolean isSaveClassQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, WechatUserTokens.CLASS_QUESTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractClassDailyHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,WechatUserTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeClassDailyHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return WechatUserTokens.of(options).analyzeClassDailyHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveClassDailyHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, WechatUserTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWechatLoginInfoListEnabled(Map<String,Object> options){		
 		return checkOptions(options,WechatUserTokens.WECHAT_LOGIN_INFO_LIST);
 	}
 	protected boolean isAnalyzeWechatLoginInfoListEnabled(Map<String,Object> options){		 		
 		return WechatUserTokens.of(options).analyzeWechatLoginInfoListEnabled();
 	}
	
	protected boolean isSaveWechatLoginInfoListEnabled(Map<String,Object> options){
		return checkOptions(options, WechatUserTokens.WECHAT_LOGIN_INFO_LIST);
		
 	}
 	
		

	

	protected WechatUserMapper getWechatUserMapper(){
		return new WechatUserMapper();
	}

	
	
	protected WechatUser extractWechatUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			WechatUser wechatUser = loadSingleObject(accessKey, getWechatUserMapper());
			return wechatUser;
		}catch(EmptyResultDataAccessException e){
			throw new WechatUserNotFoundException("WechatUser("+accessKey+") is not found!");
		}

	}

	
	

	protected WechatUser loadInternalWechatUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		WechatUser wechatUser = extractWechatUser(accessKey, loadOptions);
 	
 		if(isExtractAddressEnabled(loadOptions)){
	 		extractAddress(wechatUser, loadOptions);
 		}
  	
 		if(isExtractUserTypeEnabled(loadOptions)){
	 		extractUserType(wechatUser, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(wechatUser, loadOptions);
 		}
 
		
		if(isExtractGuardianListEnabled(loadOptions)){
	 		extractGuardianList(wechatUser, loadOptions);
 		}	
 		if(isAnalyzeGuardianListEnabled(loadOptions)){
	 		analyzeGuardianList(wechatUser, loadOptions);
 		}
 		
		
		if(isExtractClassQuestionListEnabled(loadOptions)){
	 		extractClassQuestionList(wechatUser, loadOptions);
 		}	
 		if(isAnalyzeClassQuestionListEnabled(loadOptions)){
	 		analyzeClassQuestionList(wechatUser, loadOptions);
 		}
 		
		
		if(isExtractClassDailyHealthSurveyListEnabled(loadOptions)){
	 		extractClassDailyHealthSurveyList(wechatUser, loadOptions);
 		}	
 		if(isAnalyzeClassDailyHealthSurveyListEnabled(loadOptions)){
	 		analyzeClassDailyHealthSurveyList(wechatUser, loadOptions);
 		}
 		
		
		if(isExtractWechatLoginInfoListEnabled(loadOptions)){
	 		extractWechatLoginInfoList(wechatUser, loadOptions);
 		}	
 		if(isAnalyzeWechatLoginInfoListEnabled(loadOptions)){
	 		analyzeWechatLoginInfoList(wechatUser, loadOptions);
 		}
 		
		
		return wechatUser;
		
	}

	 

 	protected WechatUser extractAddress(WechatUser wechatUser, Map<String,Object> options) throws Exception{

		if(wechatUser.getAddress() == null){
			return wechatUser;
		}
		String addressId = wechatUser.getAddress().getId();
		if( addressId == null){
			return wechatUser;
		}
		Location address = getLocationDAO().load(addressId,options);
		if(address != null){
			wechatUser.setAddress(address);
		}
		
 		
 		return wechatUser;
 	}
 		
  

 	protected WechatUser extractUserType(WechatUser wechatUser, Map<String,Object> options) throws Exception{

		if(wechatUser.getUserType() == null){
			return wechatUser;
		}
		String userTypeId = wechatUser.getUserType().getId();
		if( userTypeId == null){
			return wechatUser;
		}
		UserType userType = getUserTypeDAO().load(userTypeId,options);
		if(userType != null){
			wechatUser.setUserType(userType);
		}
		
 		
 		return wechatUser;
 	}
 		
  

 	protected WechatUser extractPlatform(WechatUser wechatUser, Map<String,Object> options) throws Exception{

		if(wechatUser.getPlatform() == null){
			return wechatUser;
		}
		String platformId = wechatUser.getPlatform().getId();
		if( platformId == null){
			return wechatUser;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			wechatUser.setPlatform(platform);
		}
		
 		
 		return wechatUser;
 	}
 		
 
		
	protected void enhanceGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected WechatUser extractGuardianList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<Guardian> guardianList = getGuardianDAO().findGuardianByWechatUser(wechatUser.getId(),options);
		if(guardianList != null){
			enhanceGuardianList(guardianList,options);
			wechatUser.setGuardianList(guardianList);
		}
		
		return wechatUser;
	
	}	
	
	protected WechatUser analyzeGuardianList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();
		if(guardianList != null){
			getGuardianDAO().analyzeGuardianByWechatUser(guardianList, wechatUser.getId(), options);
			
		}
		
		return wechatUser;
	
	}	
	
		
	protected void enhanceClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected WechatUser extractClassQuestionList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<ClassQuestion> classQuestionList = getClassQuestionDAO().findClassQuestionByCreator(wechatUser.getId(),options);
		if(classQuestionList != null){
			enhanceClassQuestionList(classQuestionList,options);
			wechatUser.setClassQuestionList(classQuestionList);
		}
		
		return wechatUser;
	
	}	
	
	protected WechatUser analyzeClassQuestionList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();
		if(classQuestionList != null){
			getClassQuestionDAO().analyzeClassQuestionByCreator(classQuestionList, wechatUser.getId(), options);
			
		}
		
		return wechatUser;
	
	}	
	
		
	protected void enhanceClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected WechatUser extractClassDailyHealthSurveyList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyByCreator(wechatUser.getId(),options);
		if(classDailyHealthSurveyList != null){
			enhanceClassDailyHealthSurveyList(classDailyHealthSurveyList,options);
			wechatUser.setClassDailyHealthSurveyList(classDailyHealthSurveyList);
		}
		
		return wechatUser;
	
	}	
	
	protected WechatUser analyzeClassDailyHealthSurveyList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList != null){
			getClassDailyHealthSurveyDAO().analyzeClassDailyHealthSurveyByCreator(classDailyHealthSurveyList, wechatUser.getId(), options);
			
		}
		
		return wechatUser;
	
	}	
	
		
	protected void enhanceWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected WechatUser extractWechatLoginInfoList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = getWechatLoginInfoDAO().findWechatLoginInfoByWechatUser(wechatUser.getId(),options);
		if(wechatLoginInfoList != null){
			enhanceWechatLoginInfoList(wechatLoginInfoList,options);
			wechatUser.setWechatLoginInfoList(wechatLoginInfoList);
		}
		
		return wechatUser;
	
	}	
	
	protected WechatUser analyzeWechatLoginInfoList(WechatUser wechatUser, Map<String,Object> options){
		
		
		if(wechatUser == null){
			return null;
		}
		if(wechatUser.getId() == null){
			return wechatUser;
		}

		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();
		if(wechatLoginInfoList != null){
			getWechatLoginInfoDAO().analyzeWechatLoginInfoByWechatUser(wechatLoginInfoList, wechatUser.getId(), options);
			
		}
		
		return wechatUser;
	
	}	
	
		
		
  	
 	public SmartList<WechatUser> findWechatUserByAddress(String locationId,Map<String,Object> options){
 	
  		SmartList<WechatUser> resultList = queryWith(WechatUserTable.COLUMN_ADDRESS, locationId, options, getWechatUserMapper());
		// analyzeWechatUserByAddress(resultList, locationId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<WechatUser> findWechatUserByAddress(String locationId, int start, int count,Map<String,Object> options){
 		
 		SmartList<WechatUser> resultList =  queryWithRange(WechatUserTable.COLUMN_ADDRESS, locationId, options, getWechatUserMapper(), start, count);
 		//analyzeWechatUserByAddress(resultList, locationId, options);
 		return resultList;
 		
 	}
 	public void analyzeWechatUserByAddress(SmartList<WechatUser> resultList, String locationId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(WechatUser.ADDRESS_PROPERTY, locationId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//WechatUser.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("微信用户");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(WechatUser.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(WechatUser.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countWechatUserByAddress(String locationId,Map<String,Object> options){

 		return countWith(WechatUserTable.COLUMN_ADDRESS, locationId, options);
 	}
 	@Override
	public Map<String, Integer> countWechatUserByAddressIds(String[] ids, Map<String, Object> options) {
		return countWithIds(WechatUserTable.COLUMN_ADDRESS, ids, options);
	}
 	
  	
 	public SmartList<WechatUser> findWechatUserByUserType(String userTypeId,Map<String,Object> options){
 	
  		SmartList<WechatUser> resultList = queryWith(WechatUserTable.COLUMN_USER_TYPE, userTypeId, options, getWechatUserMapper());
		// analyzeWechatUserByUserType(resultList, userTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<WechatUser> findWechatUserByUserType(String userTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<WechatUser> resultList =  queryWithRange(WechatUserTable.COLUMN_USER_TYPE, userTypeId, options, getWechatUserMapper(), start, count);
 		//analyzeWechatUserByUserType(resultList, userTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeWechatUserByUserType(SmartList<WechatUser> resultList, String userTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(WechatUser.USER_TYPE_PROPERTY, userTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//WechatUser.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("微信用户");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(WechatUser.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(WechatUser.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countWechatUserByUserType(String userTypeId,Map<String,Object> options){

 		return countWith(WechatUserTable.COLUMN_USER_TYPE, userTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countWechatUserByUserTypeIds(String[] ids, Map<String, Object> options) {
		return countWithIds(WechatUserTable.COLUMN_USER_TYPE, ids, options);
	}
 	
  	
 	public SmartList<WechatUser> findWechatUserByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<WechatUser> resultList = queryWith(WechatUserTable.COLUMN_PLATFORM, platformId, options, getWechatUserMapper());
		// analyzeWechatUserByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<WechatUser> findWechatUserByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<WechatUser> resultList =  queryWithRange(WechatUserTable.COLUMN_PLATFORM, platformId, options, getWechatUserMapper(), start, count);
 		//analyzeWechatUserByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeWechatUserByPlatform(SmartList<WechatUser> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(WechatUser.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//WechatUser.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("微信用户");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(WechatUser.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(WechatUser.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countWechatUserByPlatform(String platformId,Map<String,Object> options){

 		return countWith(WechatUserTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countWechatUserByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(WechatUserTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected WechatUser saveWechatUser(WechatUser  wechatUser){
		
		if(!wechatUser.isChanged()){
			return wechatUser;
		}
		
		
		String SQL=this.getSaveWechatUserSQL(wechatUser);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveWechatUserParameters(wechatUser);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		wechatUser.incVersion();
		return wechatUser;
	
	}
	public SmartList<WechatUser> saveWechatUserList(SmartList<WechatUser> wechatUserList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitWechatUserList(wechatUserList);
		
		batchWechatUserCreate((List<WechatUser>)lists[CREATE_LIST_INDEX]);
		
		batchWechatUserUpdate((List<WechatUser>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(WechatUser wechatUser:wechatUserList){
			if(wechatUser.isChanged()){
				wechatUser.incVersion();
			}
			
		
		}
		
		
		return wechatUserList;
	}

	public SmartList<WechatUser> removeWechatUserList(SmartList<WechatUser> wechatUserList,Map<String,Object> options){
		
		
		super.removeList(wechatUserList, options);
		
		return wechatUserList;
		
		
	}
	
	protected List<Object[]> prepareWechatUserBatchCreateArgs(List<WechatUser> wechatUserList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(WechatUser wechatUser:wechatUserList ){
			Object [] parameters = prepareWechatUserCreateParameters(wechatUser);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareWechatUserBatchUpdateArgs(List<WechatUser> wechatUserList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(WechatUser wechatUser:wechatUserList ){
			if(!wechatUser.isChanged()){
				continue;
			}
			Object [] parameters = prepareWechatUserUpdateParameters(wechatUser);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchWechatUserCreate(List<WechatUser> wechatUserList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareWechatUserBatchCreateArgs(wechatUserList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchWechatUserUpdate(List<WechatUser> wechatUserList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareWechatUserBatchUpdateArgs(wechatUserList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitWechatUserList(List<WechatUser> wechatUserList){
		
		List<WechatUser> wechatUserCreateList=new ArrayList<WechatUser>();
		List<WechatUser> wechatUserUpdateList=new ArrayList<WechatUser>();
		
		for(WechatUser wechatUser: wechatUserList){
			if(isUpdateRequest(wechatUser)){
				wechatUserUpdateList.add( wechatUser);
				continue;
			}
			wechatUserCreateList.add(wechatUser);
		}
		
		return new Object[]{wechatUserCreateList,wechatUserUpdateList};
	}
	
	protected boolean isUpdateRequest(WechatUser wechatUser){
 		return wechatUser.getVersion() > 0;
 	}
 	protected String getSaveWechatUserSQL(WechatUser wechatUser){
 		if(isUpdateRequest(wechatUser)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveWechatUserParameters(WechatUser wechatUser){
 		if(isUpdateRequest(wechatUser) ){
 			return prepareWechatUserUpdateParameters(wechatUser);
 		}
 		return prepareWechatUserCreateParameters(wechatUser);
 	}
 	protected Object[] prepareWechatUserUpdateParameters(WechatUser wechatUser){
 		Object[] parameters = new Object[9];
 
 		parameters[0] = wechatUser.getName();
 		parameters[1] = wechatUser.getAvatar(); 	
 		if(wechatUser.getAddress() != null){
 			parameters[2] = wechatUser.getAddress().getId();
 		}
  	
 		if(wechatUser.getUserType() != null){
 			parameters[3] = wechatUser.getUserType().getId();
 		}
 
 		parameters[4] = wechatUser.getCreateTime(); 	
 		if(wechatUser.getPlatform() != null){
 			parameters[5] = wechatUser.getPlatform().getId();
 		}
 		
 		parameters[6] = wechatUser.nextVersion();
 		parameters[7] = wechatUser.getId();
 		parameters[8] = wechatUser.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareWechatUserCreateParameters(WechatUser wechatUser){
		Object[] parameters = new Object[7];
		String newWechatUserId=getNextId();
		wechatUser.setId(newWechatUserId);
		parameters[0] =  wechatUser.getId();
 
 		parameters[1] = wechatUser.getName();
 		parameters[2] = wechatUser.getAvatar(); 	
 		if(wechatUser.getAddress() != null){
 			parameters[3] = wechatUser.getAddress().getId();
 		
 		}
 		 	
 		if(wechatUser.getUserType() != null){
 			parameters[4] = wechatUser.getUserType().getId();
 		
 		}
 		
 		parameters[5] = wechatUser.getCreateTime(); 	
 		if(wechatUser.getPlatform() != null){
 			parameters[6] = wechatUser.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected WechatUser saveInternalWechatUser(WechatUser wechatUser, Map<String,Object> options){
		
		saveWechatUser(wechatUser);
 	
 		if(isSaveAddressEnabled(options)){
	 		saveAddress(wechatUser, options);
 		}
  	
 		if(isSaveUserTypeEnabled(options)){
	 		saveUserType(wechatUser, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(wechatUser, options);
 		}
 
		
		if(isSaveGuardianListEnabled(options)){
	 		saveGuardianList(wechatUser, options);
	 		//removeGuardianList(wechatUser, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveClassQuestionListEnabled(options)){
	 		saveClassQuestionList(wechatUser, options);
	 		//removeClassQuestionList(wechatUser, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveClassDailyHealthSurveyListEnabled(options)){
	 		saveClassDailyHealthSurveyList(wechatUser, options);
	 		//removeClassDailyHealthSurveyList(wechatUser, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveWechatLoginInfoListEnabled(options)){
	 		saveWechatLoginInfoList(wechatUser, options);
	 		//removeWechatLoginInfoList(wechatUser, options);
	 		//Not delete the record
	 		
 		}		
		
		return wechatUser;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected WechatUser saveAddress(WechatUser wechatUser, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(wechatUser.getAddress() == null){
 			return wechatUser;//do nothing when it is null
 		}
 		
 		getLocationDAO().save(wechatUser.getAddress(),options);
 		return wechatUser;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected WechatUser saveUserType(WechatUser wechatUser, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(wechatUser.getUserType() == null){
 			return wechatUser;//do nothing when it is null
 		}
 		
 		getUserTypeDAO().save(wechatUser.getUserType(),options);
 		return wechatUser;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected WechatUser savePlatform(WechatUser wechatUser, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(wechatUser.getPlatform() == null){
 			return wechatUser;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(wechatUser.getPlatform(),options);
 		return wechatUser;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public WechatUser planToRemoveGuardianList(WechatUser wechatUser, String guardianIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(Guardian.ID_PROPERTY, guardianIds);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return wechatUser;
		}
		if(externalGuardianList.isEmpty()){
			return wechatUser;
		}
		
		for(Guardian guardianItem: externalGuardianList){

			guardianItem.clearFromAll();
		}
		
		
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return wechatUser;	
	
	}


	//disconnect WechatUser with address in Guardian
	public WechatUser planToRemoveGuardianListWithAddress(WechatUser wechatUser, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(Guardian.ADDRESS_PROPERTY, addressId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return wechatUser;
		}
		if(externalGuardianList.isEmpty()){
			return wechatUser;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearAddress();
			guardianItem.clearWechatUser();
			
		}
		
		
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return wechatUser;
	}
	
	public int countGuardianListWithAddress(String wechatUserId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		key.put(Guardian.ADDRESS_PROPERTY, addressId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	//disconnect WechatUser with platform in Guardian
	public WechatUser planToRemoveGuardianListWithPlatform(WechatUser wechatUser, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return wechatUser;
		}
		if(externalGuardianList.isEmpty()){
			return wechatUser;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearPlatform();
			guardianItem.clearWechatUser();
			
		}
		
		
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return wechatUser;
	}
	
	public int countGuardianListWithPlatform(String wechatUserId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	//disconnect WechatUser with cq in Guardian
	public WechatUser planToRemoveGuardianListWithCq(WechatUser wechatUser, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(Guardian.CQ_PROPERTY, cqId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return wechatUser;
		}
		if(externalGuardianList.isEmpty()){
			return wechatUser;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearCq();
			guardianItem.clearWechatUser();
			
		}
		
		
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return wechatUser;
	}
	
	public int countGuardianListWithCq(String wechatUserId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		key.put(Guardian.CQ_PROPERTY, cqId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	public WechatUser planToRemoveClassQuestionList(WechatUser wechatUser, String classQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassQuestion.ID_PROPERTY, classQuestionIds);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return wechatUser;
		}
		if(externalClassQuestionList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){

			classQuestionItem.clearFromAll();
		}
		
		
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return wechatUser;	
	
	}


	//disconnect WechatUser with question_type in ClassQuestion
	public WechatUser planToRemoveClassQuestionListWithQuestionType(WechatUser wechatUser, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return wechatUser;
		}
		if(externalClassQuestionList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearQuestionType();
			classQuestionItem.clearCreator();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return wechatUser;
	}
	
	public int countClassQuestionListWithQuestionType(String wechatUserId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUserId);
		key.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect WechatUser with question_source in ClassQuestion
	public WechatUser planToRemoveClassQuestionListWithQuestionSource(WechatUser wechatUser, String questionSourceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return wechatUser;
		}
		if(externalClassQuestionList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearQuestionSource();
			classQuestionItem.clearCreator();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return wechatUser;
	}
	
	public int countClassQuestionListWithQuestionSource(String wechatUserId, String questionSourceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUserId);
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect WechatUser with cq in ClassQuestion
	public WechatUser planToRemoveClassQuestionListWithCq(WechatUser wechatUser, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassQuestion.CQ_PROPERTY, cqId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return wechatUser;
		}
		if(externalClassQuestionList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearCq();
			classQuestionItem.clearCreator();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return wechatUser;
	}
	
	public int countClassQuestionListWithCq(String wechatUserId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, wechatUserId);
		key.put(ClassQuestion.CQ_PROPERTY, cqId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	public WechatUser planToRemoveClassDailyHealthSurveyList(WechatUser wechatUser, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassDailyHealthSurvey.ID_PROPERTY, classDailyHealthSurveyIds);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return wechatUser;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){

			classDailyHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return wechatUser;	
	
	}


	//disconnect WechatUser with school_class in ClassDailyHealthSurvey
	public WechatUser planToRemoveClassDailyHealthSurveyListWithSchoolClass(WechatUser wechatUser, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return wechatUser;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearSchoolClass();
			classDailyHealthSurveyItem.clearCreator();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return wechatUser;
	}
	
	public int countClassDailyHealthSurveyListWithSchoolClass(String wechatUserId, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, wechatUserId);
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect WechatUser with cq in ClassDailyHealthSurvey
	public WechatUser planToRemoveClassDailyHealthSurveyListWithCq(WechatUser wechatUser, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, wechatUser.getId());
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, cqId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return wechatUser;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return wechatUser;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearCq();
			classDailyHealthSurveyItem.clearCreator();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return wechatUser;
	}
	
	public int countClassDailyHealthSurveyListWithCq(String wechatUserId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, wechatUserId);
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, cqId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	public WechatUser planToRemoveWechatLoginInfoList(WechatUser wechatUser, String wechatLoginInfoIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(WechatLoginInfo.ID_PROPERTY, wechatLoginInfoIds);
		
		SmartList<WechatLoginInfo> externalWechatLoginInfoList = getWechatLoginInfoDAO().
				findWechatLoginInfoWithKey(key, options);
		if(externalWechatLoginInfoList == null){
			return wechatUser;
		}
		if(externalWechatLoginInfoList.isEmpty()){
			return wechatUser;
		}
		
		for(WechatLoginInfo wechatLoginInfoItem: externalWechatLoginInfoList){

			wechatLoginInfoItem.clearFromAll();
		}
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();		
		wechatLoginInfoList.addAllToRemoveList(externalWechatLoginInfoList);
		return wechatUser;	
	
	}


	//disconnect WechatUser with app_id in WechatLoginInfo
	public WechatUser planToRemoveWechatLoginInfoListWithAppId(WechatUser wechatUser, String appIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(WechatLoginInfo.APP_ID_PROPERTY, appIdId);
		
		SmartList<WechatLoginInfo> externalWechatLoginInfoList = getWechatLoginInfoDAO().
				findWechatLoginInfoWithKey(key, options);
		if(externalWechatLoginInfoList == null){
			return wechatUser;
		}
		if(externalWechatLoginInfoList.isEmpty()){
			return wechatUser;
		}
		
		for(WechatLoginInfo wechatLoginInfoItem: externalWechatLoginInfoList){
			wechatLoginInfoItem.clearAppId();
			wechatLoginInfoItem.clearWechatUser();
			
		}
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();		
		wechatLoginInfoList.addAllToRemoveList(externalWechatLoginInfoList);
		return wechatUser;
	}
	
	public int countWechatLoginInfoListWithAppId(String wechatUserId, String appIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.WECHAT_USER_PROPERTY, wechatUserId);
		key.put(WechatLoginInfo.APP_ID_PROPERTY, appIdId);
		
		int count = getWechatLoginInfoDAO().countWechatLoginInfoWithKey(key, options);
		return count;
	}
	
	//disconnect WechatUser with open_id in WechatLoginInfo
	public WechatUser planToRemoveWechatLoginInfoListWithOpenId(WechatUser wechatUser, String openIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.WECHAT_USER_PROPERTY, wechatUser.getId());
		key.put(WechatLoginInfo.OPEN_ID_PROPERTY, openIdId);
		
		SmartList<WechatLoginInfo> externalWechatLoginInfoList = getWechatLoginInfoDAO().
				findWechatLoginInfoWithKey(key, options);
		if(externalWechatLoginInfoList == null){
			return wechatUser;
		}
		if(externalWechatLoginInfoList.isEmpty()){
			return wechatUser;
		}
		
		for(WechatLoginInfo wechatLoginInfoItem: externalWechatLoginInfoList){
			wechatLoginInfoItem.clearOpenId();
			wechatLoginInfoItem.clearWechatUser();
			
		}
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();		
		wechatLoginInfoList.addAllToRemoveList(externalWechatLoginInfoList);
		return wechatUser;
	}
	
	public int countWechatLoginInfoListWithOpenId(String wechatUserId, String openIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.WECHAT_USER_PROPERTY, wechatUserId);
		key.put(WechatLoginInfo.OPEN_ID_PROPERTY, openIdId);
		
		int count = getWechatLoginInfoDAO().countWechatLoginInfoWithKey(key, options);
		return count;
	}
	

		
	protected WechatUser saveGuardianList(WechatUser wechatUser, Map<String,Object> options){
		
		
		
		
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();
		if(guardianList == null){
			//null list means nothing
			return wechatUser;
		}
		SmartList<Guardian> mergedUpdateGuardianList = new SmartList<Guardian>();
		
		
		mergedUpdateGuardianList.addAll(guardianList); 
		if(guardianList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateGuardianList.addAll(guardianList.getToRemoveList());
			guardianList.removeAll(guardianList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getGuardianDAO().saveGuardianList(mergedUpdateGuardianList,options);
		
		if(guardianList.getToRemoveList() != null){
			guardianList.removeAll(guardianList.getToRemoveList());
		}
		
		
		return wechatUser;
	
	}
	
	protected WechatUser removeGuardianList(WechatUser wechatUser, Map<String,Object> options){
	
	
		SmartList<Guardian> guardianList = wechatUser.getGuardianList();
		if(guardianList == null){
			return wechatUser;
		}	
	
		SmartList<Guardian> toRemoveGuardianList = guardianList.getToRemoveList();
		
		if(toRemoveGuardianList == null){
			return wechatUser;
		}
		if(toRemoveGuardianList.isEmpty()){
			return wechatUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getGuardianDAO().removeGuardianList(toRemoveGuardianList,options);
		
		return wechatUser;
	
	}
	
	

 	
 	
	
	
	
		
	protected WechatUser saveClassQuestionList(WechatUser wechatUser, Map<String,Object> options){
		
		
		
		
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();
		if(classQuestionList == null){
			//null list means nothing
			return wechatUser;
		}
		SmartList<ClassQuestion> mergedUpdateClassQuestionList = new SmartList<ClassQuestion>();
		
		
		mergedUpdateClassQuestionList.addAll(classQuestionList); 
		if(classQuestionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateClassQuestionList.addAll(classQuestionList.getToRemoveList());
			classQuestionList.removeAll(classQuestionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getClassQuestionDAO().saveClassQuestionList(mergedUpdateClassQuestionList,options);
		
		if(classQuestionList.getToRemoveList() != null){
			classQuestionList.removeAll(classQuestionList.getToRemoveList());
		}
		
		
		return wechatUser;
	
	}
	
	protected WechatUser removeClassQuestionList(WechatUser wechatUser, Map<String,Object> options){
	
	
		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();
		if(classQuestionList == null){
			return wechatUser;
		}	
	
		SmartList<ClassQuestion> toRemoveClassQuestionList = classQuestionList.getToRemoveList();
		
		if(toRemoveClassQuestionList == null){
			return wechatUser;
		}
		if(toRemoveClassQuestionList.isEmpty()){
			return wechatUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassQuestionDAO().removeClassQuestionList(toRemoveClassQuestionList,options);
		
		return wechatUser;
	
	}
	
	

 	
 	
	
	
	
		
	protected WechatUser saveClassDailyHealthSurveyList(WechatUser wechatUser, Map<String,Object> options){
		
		
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			//null list means nothing
			return wechatUser;
		}
		SmartList<ClassDailyHealthSurvey> mergedUpdateClassDailyHealthSurveyList = new SmartList<ClassDailyHealthSurvey>();
		
		
		mergedUpdateClassDailyHealthSurveyList.addAll(classDailyHealthSurveyList); 
		if(classDailyHealthSurveyList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateClassDailyHealthSurveyList.addAll(classDailyHealthSurveyList.getToRemoveList());
			classDailyHealthSurveyList.removeAll(classDailyHealthSurveyList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getClassDailyHealthSurveyDAO().saveClassDailyHealthSurveyList(mergedUpdateClassDailyHealthSurveyList,options);
		
		if(classDailyHealthSurveyList.getToRemoveList() != null){
			classDailyHealthSurveyList.removeAll(classDailyHealthSurveyList.getToRemoveList());
		}
		
		
		return wechatUser;
	
	}
	
	protected WechatUser removeClassDailyHealthSurveyList(WechatUser wechatUser, Map<String,Object> options){
	
	
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			return wechatUser;
		}	
	
		SmartList<ClassDailyHealthSurvey> toRemoveClassDailyHealthSurveyList = classDailyHealthSurveyList.getToRemoveList();
		
		if(toRemoveClassDailyHealthSurveyList == null){
			return wechatUser;
		}
		if(toRemoveClassDailyHealthSurveyList.isEmpty()){
			return wechatUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassDailyHealthSurveyDAO().removeClassDailyHealthSurveyList(toRemoveClassDailyHealthSurveyList,options);
		
		return wechatUser;
	
	}
	
	

 	
 	
	
	
	
		
	protected WechatUser saveWechatLoginInfoList(WechatUser wechatUser, Map<String,Object> options){
		
		
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();
		if(wechatLoginInfoList == null){
			//null list means nothing
			return wechatUser;
		}
		SmartList<WechatLoginInfo> mergedUpdateWechatLoginInfoList = new SmartList<WechatLoginInfo>();
		
		
		mergedUpdateWechatLoginInfoList.addAll(wechatLoginInfoList); 
		if(wechatLoginInfoList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateWechatLoginInfoList.addAll(wechatLoginInfoList.getToRemoveList());
			wechatLoginInfoList.removeAll(wechatLoginInfoList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getWechatLoginInfoDAO().saveWechatLoginInfoList(mergedUpdateWechatLoginInfoList,options);
		
		if(wechatLoginInfoList.getToRemoveList() != null){
			wechatLoginInfoList.removeAll(wechatLoginInfoList.getToRemoveList());
		}
		
		
		return wechatUser;
	
	}
	
	protected WechatUser removeWechatLoginInfoList(WechatUser wechatUser, Map<String,Object> options){
	
	
		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();
		if(wechatLoginInfoList == null){
			return wechatUser;
		}	
	
		SmartList<WechatLoginInfo> toRemoveWechatLoginInfoList = wechatLoginInfoList.getToRemoveList();
		
		if(toRemoveWechatLoginInfoList == null){
			return wechatUser;
		}
		if(toRemoveWechatLoginInfoList.isEmpty()){
			return wechatUser;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getWechatLoginInfoDAO().removeWechatLoginInfoList(toRemoveWechatLoginInfoList,options);
		
		return wechatUser;
	
	}
	
	

 	
 	
	
	
	
		

	public WechatUser present(WechatUser wechatUser,Map<String, Object> options){
	
		presentGuardianList(wechatUser,options);
		presentClassQuestionList(wechatUser,options);
		presentClassDailyHealthSurveyList(wechatUser,options);
		presentWechatLoginInfoList(wechatUser,options);

		return wechatUser;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected WechatUser presentGuardianList(
			WechatUser wechatUser,
			Map<String, Object> options) {

		SmartList<Guardian> guardianList = wechatUser.getGuardianList();		
				SmartList<Guardian> newList= presentSubList(wechatUser.getId(),
				guardianList,
				options,
				getGuardianDAO()::countGuardianByWechatUser,
				getGuardianDAO()::findGuardianByWechatUser
				);

		
		wechatUser.setGuardianList(newList);
		

		return wechatUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected WechatUser presentClassQuestionList(
			WechatUser wechatUser,
			Map<String, Object> options) {

		SmartList<ClassQuestion> classQuestionList = wechatUser.getClassQuestionList();		
				SmartList<ClassQuestion> newList= presentSubList(wechatUser.getId(),
				classQuestionList,
				options,
				getClassQuestionDAO()::countClassQuestionByCreator,
				getClassQuestionDAO()::findClassQuestionByCreator
				);

		
		wechatUser.setClassQuestionList(newList);
		

		return wechatUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected WechatUser presentClassDailyHealthSurveyList(
			WechatUser wechatUser,
			Map<String, Object> options) {

		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = wechatUser.getClassDailyHealthSurveyList();		
				SmartList<ClassDailyHealthSurvey> newList= presentSubList(wechatUser.getId(),
				classDailyHealthSurveyList,
				options,
				getClassDailyHealthSurveyDAO()::countClassDailyHealthSurveyByCreator,
				getClassDailyHealthSurveyDAO()::findClassDailyHealthSurveyByCreator
				);

		
		wechatUser.setClassDailyHealthSurveyList(newList);
		

		return wechatUser;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected WechatUser presentWechatLoginInfoList(
			WechatUser wechatUser,
			Map<String, Object> options) {

		SmartList<WechatLoginInfo> wechatLoginInfoList = wechatUser.getWechatLoginInfoList();		
				SmartList<WechatLoginInfo> newList= presentSubList(wechatUser.getId(),
				wechatLoginInfoList,
				options,
				getWechatLoginInfoDAO()::countWechatLoginInfoByWechatUser,
				getWechatLoginInfoDAO()::findWechatLoginInfoByWechatUser
				);

		
		wechatUser.setWechatLoginInfoList(newList);
		

		return wechatUser;
	}			
		

	
    public SmartList<WechatUser> requestCandidateWechatUserForGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(WechatUserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getWechatUserMapper());
    }
		
    public SmartList<WechatUser> requestCandidateWechatUserForClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(WechatUserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getWechatUserMapper());
    }
		
    public SmartList<WechatUser> requestCandidateWechatUserForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(WechatUserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getWechatUserMapper());
    }
		
    public SmartList<WechatUser> requestCandidateWechatUserForWechatLoginInfo(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(WechatUserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getWechatUserMapper());
    }
		

	protected String getTableName(){
		return WechatUserTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<WechatUser> wechatUserList) {		
		this.enhanceListInternal(wechatUserList, this.getWechatUserMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Guardian的wechatUser的GuardianList
	public SmartList<Guardian> loadOurGuardianList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.WECHAT_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Guardian> loadedObjs = userContext.getDAOGroup().getGuardianDAO().findGuardianWithKey(key, options);
		Map<String, List<Guardian>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getWechatUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Guardian> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Guardian> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setGuardianList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ClassQuestion的creator的ClassQuestionList
	public SmartList<ClassQuestion> loadOurClassQuestionList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CREATOR_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassQuestion> loadedObjs = userContext.getDAOGroup().getClassQuestionDAO().findClassQuestionWithKey(key, options);
		Map<String, List<ClassQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCreator().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ClassQuestion> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ClassQuestion> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setClassQuestionList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的creator的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassDailyHealthSurvey> loadedObjs = userContext.getDAOGroup().getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyWithKey(key, options);
		Map<String, List<ClassDailyHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCreator().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ClassDailyHealthSurvey> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ClassDailyHealthSurvey> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setClassDailyHealthSurveyList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:WechatLoginInfo的wechatUser的WechatLoginInfoList
	public SmartList<WechatLoginInfo> loadOurWechatLoginInfoList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.WECHAT_USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WechatLoginInfo> loadedObjs = userContext.getDAOGroup().getWechatLoginInfoDAO().findWechatLoginInfoWithKey(key, options);
		Map<String, List<WechatLoginInfo>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getWechatUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<WechatLoginInfo> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<WechatLoginInfo> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setWechatLoginInfoList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<WechatUser> wechatUserList = ownerEntity.collectRefsWithType(WechatUser.INTERNAL_TYPE);
		this.enhanceList(wechatUserList);
		
	}
	
	@Override
	public SmartList<WechatUser> findWechatUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getWechatUserMapper());

	}
	@Override
	public int countWechatUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countWechatUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<WechatUser> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getWechatUserMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


