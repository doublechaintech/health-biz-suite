
package com.doublechaintech.health.user;

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
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.question.Question;

import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoDAO;
import com.doublechaintech.health.question.QuestionDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class UserJDBCTemplateDAO extends HealthBaseDAOImpl implements UserDAO{
 
 	
 	private  LocationDAO  locationDAO;
 	public void setLocationDAO(LocationDAO locationDAO){
	 	this.locationDAO = locationDAO;
 	}
 	public LocationDAO getLocationDAO(){
	 	return this.locationDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  TeacherDAO  teacherDAO;
 	public void setTeacherDAO(TeacherDAO pTeacherDAO){
 	
 		if(pTeacherDAO == null){
 			throw new IllegalStateException("Do not try to set teacherDAO to null.");
 		}
	 	this.teacherDAO = pTeacherDAO;
 	}
 	public TeacherDAO getTeacherDAO(){
 		if(this.teacherDAO == null){
 			throw new IllegalStateException("The teacherDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.teacherDAO;
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
 	
			
		
	
  	private  QuestionDAO  questionDAO;
 	public void setQuestionDAO(QuestionDAO pQuestionDAO){
 	
 		if(pQuestionDAO == null){
 			throw new IllegalStateException("Do not try to set questionDAO to null.");
 		}
	 	this.questionDAO = pQuestionDAO;
 	}
 	public QuestionDAO getQuestionDAO(){
 		if(this.questionDAO == null){
 			throw new IllegalStateException("The questionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.questionDAO;
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
	protected User load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalUser(accessKey, options);
	}
	*/
	
	public SmartList<User> loadAll() {
	    return this.loadAll(getUserMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public User load(String id,Map<String,Object> options) throws Exception{
		return loadInternalUser(UserTable.withId(id), options);
	}
	
	
	
	public User save(User user,Map<String,Object> options){
		
		String methodName="save(User user,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(user, methodName, "user");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalUser(user,options);
	}
	public User clone(String userId, Map<String,Object> options) throws Exception{
	
		return clone(UserTable.withId(userId),options);
	}
	
	protected User clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String userId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		User newUser = loadInternalUser(accessKey, options);
		newUser.setVersion(0);
		
		
 		
 		if(isSaveTeacherListEnabled(options)){
 			for(Teacher item: newUser.getTeacherList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentListEnabled(options)){
 			for(Student item: newUser.getStudentList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveQuestionListEnabled(options)){
 			for(Question item: newUser.getQuestionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveClassDailyHealthSurveyListEnabled(options)){
 			for(ClassDailyHealthSurvey item: newUser.getClassDailyHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWechatLoginInfoListEnabled(options)){
 			for(WechatLoginInfo item: newUser.getWechatLoginInfoList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalUser(newUser,options);
		
		return newUser;
	}
	
	
	
	

	protected void throwIfHasException(String userId,int version,int count) throws Exception{
		if (count == 1) {
			throw new UserVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new UserNotFoundException(
					"The " + this.getTableName() + "(" + userId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String userId, int version) throws Exception{
	
		String methodName="delete(String userId, int version)";
		assertMethodArgumentNotNull(userId, methodName, "userId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{userId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(userId,version);
		}
		
	
	}
	
	
	
	
	

	public User disconnectFromAll(String userId, int version) throws Exception{
	
		
		User user = loadInternalUser(UserTable.withId(userId), emptyOptions());
		user.clearFromAll();
		this.saveUser(user);
		return user;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return UserTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "user";
	}
	@Override
	protected String getBeanName() {
		
		return "user";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return UserTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractAddressEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, UserTokens.ADDRESS);
 	}

 	protected boolean isSaveAddressEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, UserTokens.ADDRESS);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, UserTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, UserTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractTeacherListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.TEACHER_LIST);
 	}
 	protected boolean isAnalyzeTeacherListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeTeacherListEnabled();
 	}
	
	protected boolean isSaveTeacherListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.TEACHER_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.STUDENT_LIST);
 	}
 	protected boolean isAnalyzeStudentListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeStudentListEnabled();
 	}
	
	protected boolean isSaveStudentListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.STUDENT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.QUESTION_LIST);
 	}
 	protected boolean isAnalyzeQuestionListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeQuestionListEnabled();
 	}
	
	protected boolean isSaveQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.QUESTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractClassDailyHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeClassDailyHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeClassDailyHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveClassDailyHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWechatLoginInfoListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.WECHAT_LOGIN_INFO_LIST);
 	}
 	protected boolean isAnalyzeWechatLoginInfoListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeWechatLoginInfoListEnabled();
 	}
	
	protected boolean isSaveWechatLoginInfoListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.WECHAT_LOGIN_INFO_LIST);
		
 	}
 	
		

	

	protected UserMapper getUserMapper(){
		return new UserMapper();
	}

	
	
	protected User extractUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			User user = loadSingleObject(accessKey, getUserMapper());
			return user;
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User("+accessKey+") is not found!");
		}

	}

	
	

	protected User loadInternalUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		User user = extractUser(accessKey, loadOptions);
 	
 		if(isExtractAddressEnabled(loadOptions)){
	 		extractAddress(user, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(user, loadOptions);
 		}
 
		
		if(isExtractTeacherListEnabled(loadOptions)){
	 		extractTeacherList(user, loadOptions);
 		}	
 		if(isAnalyzeTeacherListEnabled(loadOptions)){
	 		analyzeTeacherList(user, loadOptions);
 		}
 		
		
		if(isExtractStudentListEnabled(loadOptions)){
	 		extractStudentList(user, loadOptions);
 		}	
 		if(isAnalyzeStudentListEnabled(loadOptions)){
	 		analyzeStudentList(user, loadOptions);
 		}
 		
		
		if(isExtractQuestionListEnabled(loadOptions)){
	 		extractQuestionList(user, loadOptions);
 		}	
 		if(isAnalyzeQuestionListEnabled(loadOptions)){
	 		analyzeQuestionList(user, loadOptions);
 		}
 		
		
		if(isExtractClassDailyHealthSurveyListEnabled(loadOptions)){
	 		extractClassDailyHealthSurveyList(user, loadOptions);
 		}	
 		if(isAnalyzeClassDailyHealthSurveyListEnabled(loadOptions)){
	 		analyzeClassDailyHealthSurveyList(user, loadOptions);
 		}
 		
		
		if(isExtractWechatLoginInfoListEnabled(loadOptions)){
	 		extractWechatLoginInfoList(user, loadOptions);
 		}	
 		if(isAnalyzeWechatLoginInfoListEnabled(loadOptions)){
	 		analyzeWechatLoginInfoList(user, loadOptions);
 		}
 		
		
		return user;
		
	}

	 

 	protected User extractAddress(User user, Map<String,Object> options) throws Exception{

		if(user.getAddress() == null){
			return user;
		}
		String addressId = user.getAddress().getId();
		if( addressId == null){
			return user;
		}
		Location address = getLocationDAO().load(addressId,options);
		if(address != null){
			user.setAddress(address);
		}
		
 		
 		return user;
 	}
 		
  

 	protected User extractPlatform(User user, Map<String,Object> options) throws Exception{

		if(user.getPlatform() == null){
			return user;
		}
		String platformId = user.getPlatform().getId();
		if( platformId == null){
			return user;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			user.setPlatform(platform);
		}
		
 		
 		return user;
 	}
 		
 
		
	protected void enhanceTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractTeacherList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<Teacher> teacherList = getTeacherDAO().findTeacherByUser(user.getId(),options);
		if(teacherList != null){
			enhanceTeacherList(teacherList,options);
			user.setTeacherList(teacherList);
		}
		
		return user;
	
	}	
	
	protected User analyzeTeacherList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<Teacher> teacherList = user.getTeacherList();
		if(teacherList != null){
			getTeacherDAO().analyzeTeacherByUser(teacherList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhanceStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractStudentList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<Student> studentList = getStudentDAO().findStudentByUser(user.getId(),options);
		if(studentList != null){
			enhanceStudentList(studentList,options);
			user.setStudentList(studentList);
		}
		
		return user;
	
	}	
	
	protected User analyzeStudentList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<Student> studentList = user.getStudentList();
		if(studentList != null){
			getStudentDAO().analyzeStudentByUser(studentList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhanceQuestionList(SmartList<Question> questionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractQuestionList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<Question> questionList = getQuestionDAO().findQuestionByCreator(user.getId(),options);
		if(questionList != null){
			enhanceQuestionList(questionList,options);
			user.setQuestionList(questionList);
		}
		
		return user;
	
	}	
	
	protected User analyzeQuestionList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<Question> questionList = user.getQuestionList();
		if(questionList != null){
			getQuestionDAO().analyzeQuestionByCreator(questionList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhanceClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractClassDailyHealthSurveyList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyByCreator(user.getId(),options);
		if(classDailyHealthSurveyList != null){
			enhanceClassDailyHealthSurveyList(classDailyHealthSurveyList,options);
			user.setClassDailyHealthSurveyList(classDailyHealthSurveyList);
		}
		
		return user;
	
	}	
	
	protected User analyzeClassDailyHealthSurveyList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList != null){
			getClassDailyHealthSurveyDAO().analyzeClassDailyHealthSurveyByCreator(classDailyHealthSurveyList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhanceWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractWechatLoginInfoList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = getWechatLoginInfoDAO().findWechatLoginInfoByUser(user.getId(),options);
		if(wechatLoginInfoList != null){
			enhanceWechatLoginInfoList(wechatLoginInfoList,options);
			user.setWechatLoginInfoList(wechatLoginInfoList);
		}
		
		return user;
	
	}	
	
	protected User analyzeWechatLoginInfoList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();
		if(wechatLoginInfoList != null){
			getWechatLoginInfoDAO().analyzeWechatLoginInfoByUser(wechatLoginInfoList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
		
  	
 	public SmartList<User> findUserByAddress(String locationId,Map<String,Object> options){
 	
  		SmartList<User> resultList = queryWith(UserTable.COLUMN_ADDRESS, locationId, options, getUserMapper());
		// analyzeUserByAddress(resultList, locationId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<User> findUserByAddress(String locationId, int start, int count,Map<String,Object> options){
 		
 		SmartList<User> resultList =  queryWithRange(UserTable.COLUMN_ADDRESS, locationId, options, getUserMapper(), start, count);
 		//analyzeUserByAddress(resultList, locationId, options);
 		return resultList;
 		
 	}
 	public void analyzeUserByAddress(SmartList<User> resultList, String locationId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(User.ADDRESS_PROPERTY, locationId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//User.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("用户");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(User.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(User.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countUserByAddress(String locationId,Map<String,Object> options){

 		return countWith(UserTable.COLUMN_ADDRESS, locationId, options);
 	}
 	@Override
	public Map<String, Integer> countUserByAddressIds(String[] ids, Map<String, Object> options) {
		return countWithIds(UserTable.COLUMN_ADDRESS, ids, options);
	}
 	
  	
 	public SmartList<User> findUserByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<User> resultList = queryWith(UserTable.COLUMN_PLATFORM, platformId, options, getUserMapper());
		// analyzeUserByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<User> findUserByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<User> resultList =  queryWithRange(UserTable.COLUMN_PLATFORM, platformId, options, getUserMapper(), start, count);
 		//analyzeUserByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeUserByPlatform(SmartList<User> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(User.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//User.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("用户");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(User.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(User.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countUserByPlatform(String platformId,Map<String,Object> options){

 		return countWith(UserTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countUserByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(UserTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected User saveUser(User  user){
		
		if(!user.isChanged()){
			return user;
		}
		
		
		String SQL=this.getSaveUserSQL(user);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveUserParameters(user);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		user.incVersion();
		return user;
	
	}
	public SmartList<User> saveUserList(SmartList<User> userList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitUserList(userList);
		
		batchUserCreate((List<User>)lists[CREATE_LIST_INDEX]);
		
		batchUserUpdate((List<User>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(User user:userList){
			if(user.isChanged()){
				user.incVersion();
			}
			
		
		}
		
		
		return userList;
	}

	public SmartList<User> removeUserList(SmartList<User> userList,Map<String,Object> options){
		
		
		super.removeList(userList, options);
		
		return userList;
		
		
	}
	
	protected List<Object[]> prepareUserBatchCreateArgs(List<User> userList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(User user:userList ){
			Object [] parameters = prepareUserCreateParameters(user);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareUserBatchUpdateArgs(List<User> userList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(User user:userList ){
			if(!user.isChanged()){
				continue;
			}
			Object [] parameters = prepareUserUpdateParameters(user);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchUserCreate(List<User> userList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareUserBatchCreateArgs(userList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchUserUpdate(List<User> userList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareUserBatchUpdateArgs(userList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitUserList(List<User> userList){
		
		List<User> userCreateList=new ArrayList<User>();
		List<User> userUpdateList=new ArrayList<User>();
		
		for(User user: userList){
			if(isUpdateRequest(user)){
				userUpdateList.add( user);
				continue;
			}
			userCreateList.add(user);
		}
		
		return new Object[]{userCreateList,userUpdateList};
	}
	
	protected boolean isUpdateRequest(User user){
 		return user.getVersion() > 0;
 	}
 	protected String getSaveUserSQL(User user){
 		if(isUpdateRequest(user)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveUserParameters(User user){
 		if(isUpdateRequest(user) ){
 			return prepareUserUpdateParameters(user);
 		}
 		return prepareUserCreateParameters(user);
 	}
 	protected Object[] prepareUserUpdateParameters(User user){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = user.getName();
 		parameters[1] = user.getAvatar(); 	
 		if(user.getAddress() != null){
 			parameters[2] = user.getAddress().getId();
 		}
 
 		parameters[3] = user.getCreateTime(); 	
 		if(user.getPlatform() != null){
 			parameters[4] = user.getPlatform().getId();
 		}
 		
 		parameters[5] = user.nextVersion();
 		parameters[6] = user.getId();
 		parameters[7] = user.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareUserCreateParameters(User user){
		Object[] parameters = new Object[6];
		String newUserId=getNextId();
		user.setId(newUserId);
		parameters[0] =  user.getId();
 
 		parameters[1] = user.getName();
 		parameters[2] = user.getAvatar(); 	
 		if(user.getAddress() != null){
 			parameters[3] = user.getAddress().getId();
 		
 		}
 		
 		parameters[4] = user.getCreateTime(); 	
 		if(user.getPlatform() != null){
 			parameters[5] = user.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected User saveInternalUser(User user, Map<String,Object> options){
		
		saveUser(user);
 	
 		if(isSaveAddressEnabled(options)){
	 		saveAddress(user, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(user, options);
 		}
 
		
		if(isSaveTeacherListEnabled(options)){
	 		saveTeacherList(user, options);
	 		//removeTeacherList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentListEnabled(options)){
	 		saveStudentList(user, options);
	 		//removeStudentList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveQuestionListEnabled(options)){
	 		saveQuestionList(user, options);
	 		//removeQuestionList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveClassDailyHealthSurveyListEnabled(options)){
	 		saveClassDailyHealthSurveyList(user, options);
	 		//removeClassDailyHealthSurveyList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveWechatLoginInfoListEnabled(options)){
	 		saveWechatLoginInfoList(user, options);
	 		//removeWechatLoginInfoList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		return user;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected User saveAddress(User user, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(user.getAddress() == null){
 			return user;//do nothing when it is null
 		}
 		
 		getLocationDAO().save(user.getAddress(),options);
 		return user;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected User savePlatform(User user, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(user.getPlatform() == null){
 			return user;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(user.getPlatform(),options);
 		return user;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public User planToRemoveTeacherList(User user, String teacherIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.USER_PROPERTY, user.getId());
		key.put(Teacher.ID_PROPERTY, teacherIds);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return user;
		}
		if(externalTeacherList.isEmpty()){
			return user;
		}
		
		for(Teacher teacherItem: externalTeacherList){

			teacherItem.clearFromAll();
		}
		
		
		SmartList<Teacher> teacherList = user.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return user;	
	
	}


	//disconnect User with platform in Teacher
	public User planToRemoveTeacherListWithPlatform(User user, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.USER_PROPERTY, user.getId());
		key.put(Teacher.PLATFORM_PROPERTY, platformId);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return user;
		}
		if(externalTeacherList.isEmpty()){
			return user;
		}
		
		for(Teacher teacherItem: externalTeacherList){
			teacherItem.clearPlatform();
			teacherItem.clearUser();
			
		}
		
		
		SmartList<Teacher> teacherList = user.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return user;
	}
	
	public int countTeacherListWithPlatform(String userId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.USER_PROPERTY, userId);
		key.put(Teacher.PLATFORM_PROPERTY, platformId);
		
		int count = getTeacherDAO().countTeacherWithKey(key, options);
		return count;
	}
	
	//disconnect User with change_request in Teacher
	public User planToRemoveTeacherListWithChangeRequest(User user, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.USER_PROPERTY, user.getId());
		key.put(Teacher.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return user;
		}
		if(externalTeacherList.isEmpty()){
			return user;
		}
		
		for(Teacher teacherItem: externalTeacherList){
			teacherItem.clearChangeRequest();
			teacherItem.clearUser();
			
		}
		
		
		SmartList<Teacher> teacherList = user.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return user;
	}
	
	public int countTeacherListWithChangeRequest(String userId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.USER_PROPERTY, userId);
		key.put(Teacher.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getTeacherDAO().countTeacherWithKey(key, options);
		return count;
	}
	
	public User planToRemoveStudentList(User user, String studentIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, user.getId());
		key.put(Student.ID_PROPERTY, studentIds);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return user;
		}
		if(externalStudentList.isEmpty()){
			return user;
		}
		
		for(Student studentItem: externalStudentList){

			studentItem.clearFromAll();
		}
		
		
		SmartList<Student> studentList = user.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return user;	
	
	}


	//disconnect User with address in Student
	public User planToRemoveStudentListWithAddress(User user, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, user.getId());
		key.put(Student.ADDRESS_PROPERTY, addressId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return user;
		}
		if(externalStudentList.isEmpty()){
			return user;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearAddress();
			studentItem.clearUser();
			
		}
		
		
		SmartList<Student> studentList = user.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return user;
	}
	
	public int countStudentListWithAddress(String userId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, userId);
		key.put(Student.ADDRESS_PROPERTY, addressId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect User with platform in Student
	public User planToRemoveStudentListWithPlatform(User user, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, user.getId());
		key.put(Student.PLATFORM_PROPERTY, platformId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return user;
		}
		if(externalStudentList.isEmpty()){
			return user;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearPlatform();
			studentItem.clearUser();
			
		}
		
		
		SmartList<Student> studentList = user.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return user;
	}
	
	public int countStudentListWithPlatform(String userId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, userId);
		key.put(Student.PLATFORM_PROPERTY, platformId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect User with change_request in Student
	public User planToRemoveStudentListWithChangeRequest(User user, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, user.getId());
		key.put(Student.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return user;
		}
		if(externalStudentList.isEmpty()){
			return user;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearChangeRequest();
			studentItem.clearUser();
			
		}
		
		
		SmartList<Student> studentList = user.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return user;
	}
	
	public int countStudentListWithChangeRequest(String userId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, userId);
		key.put(Student.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	public User planToRemoveQuestionList(User user, String questionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, user.getId());
		key.put(Question.ID_PROPERTY, questionIds);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return user;
		}
		if(externalQuestionList.isEmpty()){
			return user;
		}
		
		for(Question questionItem: externalQuestionList){

			questionItem.clearFromAll();
		}
		
		
		SmartList<Question> questionList = user.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return user;	
	
	}


	//disconnect User with question_type in Question
	public User planToRemoveQuestionListWithQuestionType(User user, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, user.getId());
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return user;
		}
		if(externalQuestionList.isEmpty()){
			return user;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearQuestionType();
			questionItem.clearCreator();
			
		}
		
		
		SmartList<Question> questionList = user.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return user;
	}
	
	public int countQuestionListWithQuestionType(String userId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, userId);
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect User with platform in Question
	public User planToRemoveQuestionListWithPlatform(User user, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, user.getId());
		key.put(Question.PLATFORM_PROPERTY, platformId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return user;
		}
		if(externalQuestionList.isEmpty()){
			return user;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearPlatform();
			questionItem.clearCreator();
			
		}
		
		
		SmartList<Question> questionList = user.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return user;
	}
	
	public int countQuestionListWithPlatform(String userId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, userId);
		key.put(Question.PLATFORM_PROPERTY, platformId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect User with cq in Question
	public User planToRemoveQuestionListWithCq(User user, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, user.getId());
		key.put(Question.CQ_PROPERTY, cqId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return user;
		}
		if(externalQuestionList.isEmpty()){
			return user;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearCq();
			questionItem.clearCreator();
			
		}
		
		
		SmartList<Question> questionList = user.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return user;
	}
	
	public int countQuestionListWithCq(String userId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, userId);
		key.put(Question.CQ_PROPERTY, cqId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	public User planToRemoveClassDailyHealthSurveyList(User user, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, user.getId());
		key.put(ClassDailyHealthSurvey.ID_PROPERTY, classDailyHealthSurveyIds);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return user;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return user;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){

			classDailyHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return user;	
	
	}


	//disconnect User with teacher in ClassDailyHealthSurvey
	public User planToRemoveClassDailyHealthSurveyListWithTeacher(User user, String teacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, user.getId());
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacherId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return user;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return user;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearTeacher();
			classDailyHealthSurveyItem.clearCreator();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return user;
	}
	
	public int countClassDailyHealthSurveyListWithTeacher(String userId, String teacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, userId);
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacherId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect User with change_request in ClassDailyHealthSurvey
	public User planToRemoveClassDailyHealthSurveyListWithChangeRequest(User user, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, user.getId());
		key.put(ClassDailyHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return user;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return user;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearChangeRequest();
			classDailyHealthSurveyItem.clearCreator();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return user;
	}
	
	public int countClassDailyHealthSurveyListWithChangeRequest(String userId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, userId);
		key.put(ClassDailyHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	public User planToRemoveWechatLoginInfoList(User user, String wechatLoginInfoIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.USER_PROPERTY, user.getId());
		key.put(WechatLoginInfo.ID_PROPERTY, wechatLoginInfoIds);
		
		SmartList<WechatLoginInfo> externalWechatLoginInfoList = getWechatLoginInfoDAO().
				findWechatLoginInfoWithKey(key, options);
		if(externalWechatLoginInfoList == null){
			return user;
		}
		if(externalWechatLoginInfoList.isEmpty()){
			return user;
		}
		
		for(WechatLoginInfo wechatLoginInfoItem: externalWechatLoginInfoList){

			wechatLoginInfoItem.clearFromAll();
		}
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();		
		wechatLoginInfoList.addAllToRemoveList(externalWechatLoginInfoList);
		return user;	
	
	}


	//disconnect User with app_id in WechatLoginInfo
	public User planToRemoveWechatLoginInfoListWithAppId(User user, String appIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.USER_PROPERTY, user.getId());
		key.put(WechatLoginInfo.APP_ID_PROPERTY, appIdId);
		
		SmartList<WechatLoginInfo> externalWechatLoginInfoList = getWechatLoginInfoDAO().
				findWechatLoginInfoWithKey(key, options);
		if(externalWechatLoginInfoList == null){
			return user;
		}
		if(externalWechatLoginInfoList.isEmpty()){
			return user;
		}
		
		for(WechatLoginInfo wechatLoginInfoItem: externalWechatLoginInfoList){
			wechatLoginInfoItem.clearAppId();
			wechatLoginInfoItem.clearUser();
			
		}
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();		
		wechatLoginInfoList.addAllToRemoveList(externalWechatLoginInfoList);
		return user;
	}
	
	public int countWechatLoginInfoListWithAppId(String userId, String appIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.USER_PROPERTY, userId);
		key.put(WechatLoginInfo.APP_ID_PROPERTY, appIdId);
		
		int count = getWechatLoginInfoDAO().countWechatLoginInfoWithKey(key, options);
		return count;
	}
	
	//disconnect User with open_id in WechatLoginInfo
	public User planToRemoveWechatLoginInfoListWithOpenId(User user, String openIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.USER_PROPERTY, user.getId());
		key.put(WechatLoginInfo.OPEN_ID_PROPERTY, openIdId);
		
		SmartList<WechatLoginInfo> externalWechatLoginInfoList = getWechatLoginInfoDAO().
				findWechatLoginInfoWithKey(key, options);
		if(externalWechatLoginInfoList == null){
			return user;
		}
		if(externalWechatLoginInfoList.isEmpty()){
			return user;
		}
		
		for(WechatLoginInfo wechatLoginInfoItem: externalWechatLoginInfoList){
			wechatLoginInfoItem.clearOpenId();
			wechatLoginInfoItem.clearUser();
			
		}
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();		
		wechatLoginInfoList.addAllToRemoveList(externalWechatLoginInfoList);
		return user;
	}
	
	public int countWechatLoginInfoListWithOpenId(String userId, String openIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.USER_PROPERTY, userId);
		key.put(WechatLoginInfo.OPEN_ID_PROPERTY, openIdId);
		
		int count = getWechatLoginInfoDAO().countWechatLoginInfoWithKey(key, options);
		return count;
	}
	

		
	protected User saveTeacherList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<Teacher> teacherList = user.getTeacherList();
		if(teacherList == null){
			//null list means nothing
			return user;
		}
		SmartList<Teacher> mergedUpdateTeacherList = new SmartList<Teacher>();
		
		
		mergedUpdateTeacherList.addAll(teacherList); 
		if(teacherList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTeacherList.addAll(teacherList.getToRemoveList());
			teacherList.removeAll(teacherList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTeacherDAO().saveTeacherList(mergedUpdateTeacherList,options);
		
		if(teacherList.getToRemoveList() != null){
			teacherList.removeAll(teacherList.getToRemoveList());
		}
		
		
		return user;
	
	}
	
	protected User removeTeacherList(User user, Map<String,Object> options){
	
	
		SmartList<Teacher> teacherList = user.getTeacherList();
		if(teacherList == null){
			return user;
		}	
	
		SmartList<Teacher> toRemoveTeacherList = teacherList.getToRemoveList();
		
		if(toRemoveTeacherList == null){
			return user;
		}
		if(toRemoveTeacherList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTeacherDAO().removeTeacherList(toRemoveTeacherList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User saveStudentList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<Student> studentList = user.getStudentList();
		if(studentList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removeStudentList(User user, Map<String,Object> options){
	
	
		SmartList<Student> studentList = user.getStudentList();
		if(studentList == null){
			return user;
		}	
	
		SmartList<Student> toRemoveStudentList = studentList.getToRemoveList();
		
		if(toRemoveStudentList == null){
			return user;
		}
		if(toRemoveStudentList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDAO().removeStudentList(toRemoveStudentList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User saveQuestionList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<Question> questionList = user.getQuestionList();
		if(questionList == null){
			//null list means nothing
			return user;
		}
		SmartList<Question> mergedUpdateQuestionList = new SmartList<Question>();
		
		
		mergedUpdateQuestionList.addAll(questionList); 
		if(questionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateQuestionList.addAll(questionList.getToRemoveList());
			questionList.removeAll(questionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getQuestionDAO().saveQuestionList(mergedUpdateQuestionList,options);
		
		if(questionList.getToRemoveList() != null){
			questionList.removeAll(questionList.getToRemoveList());
		}
		
		
		return user;
	
	}
	
	protected User removeQuestionList(User user, Map<String,Object> options){
	
	
		SmartList<Question> questionList = user.getQuestionList();
		if(questionList == null){
			return user;
		}	
	
		SmartList<Question> toRemoveQuestionList = questionList.getToRemoveList();
		
		if(toRemoveQuestionList == null){
			return user;
		}
		if(toRemoveQuestionList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getQuestionDAO().removeQuestionList(toRemoveQuestionList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User saveClassDailyHealthSurveyList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removeClassDailyHealthSurveyList(User user, Map<String,Object> options){
	
	
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			return user;
		}	
	
		SmartList<ClassDailyHealthSurvey> toRemoveClassDailyHealthSurveyList = classDailyHealthSurveyList.getToRemoveList();
		
		if(toRemoveClassDailyHealthSurveyList == null){
			return user;
		}
		if(toRemoveClassDailyHealthSurveyList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassDailyHealthSurveyDAO().removeClassDailyHealthSurveyList(toRemoveClassDailyHealthSurveyList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User saveWechatLoginInfoList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();
		if(wechatLoginInfoList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removeWechatLoginInfoList(User user, Map<String,Object> options){
	
	
		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();
		if(wechatLoginInfoList == null){
			return user;
		}	
	
		SmartList<WechatLoginInfo> toRemoveWechatLoginInfoList = wechatLoginInfoList.getToRemoveList();
		
		if(toRemoveWechatLoginInfoList == null){
			return user;
		}
		if(toRemoveWechatLoginInfoList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getWechatLoginInfoDAO().removeWechatLoginInfoList(toRemoveWechatLoginInfoList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		

	public User present(User user,Map<String, Object> options){
	
		presentTeacherList(user,options);
		presentStudentList(user,options);
		presentQuestionList(user,options);
		presentClassDailyHealthSurveyList(user,options);
		presentWechatLoginInfoList(user,options);

		return user;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected User presentTeacherList(
			User user,
			Map<String, Object> options) {

		SmartList<Teacher> teacherList = user.getTeacherList();		
				SmartList<Teacher> newList= presentSubList(user.getId(),
				teacherList,
				options,
				getTeacherDAO()::countTeacherByUser,
				getTeacherDAO()::findTeacherByUser
				);

		
		user.setTeacherList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentStudentList(
			User user,
			Map<String, Object> options) {

		SmartList<Student> studentList = user.getStudentList();		
				SmartList<Student> newList= presentSubList(user.getId(),
				studentList,
				options,
				getStudentDAO()::countStudentByUser,
				getStudentDAO()::findStudentByUser
				);

		
		user.setStudentList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentQuestionList(
			User user,
			Map<String, Object> options) {

		SmartList<Question> questionList = user.getQuestionList();		
				SmartList<Question> newList= presentSubList(user.getId(),
				questionList,
				options,
				getQuestionDAO()::countQuestionByCreator,
				getQuestionDAO()::findQuestionByCreator
				);

		
		user.setQuestionList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentClassDailyHealthSurveyList(
			User user,
			Map<String, Object> options) {

		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = user.getClassDailyHealthSurveyList();		
				SmartList<ClassDailyHealthSurvey> newList= presentSubList(user.getId(),
				classDailyHealthSurveyList,
				options,
				getClassDailyHealthSurveyDAO()::countClassDailyHealthSurveyByCreator,
				getClassDailyHealthSurveyDAO()::findClassDailyHealthSurveyByCreator
				);

		
		user.setClassDailyHealthSurveyList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentWechatLoginInfoList(
			User user,
			Map<String, Object> options) {

		SmartList<WechatLoginInfo> wechatLoginInfoList = user.getWechatLoginInfoList();		
				SmartList<WechatLoginInfo> newList= presentSubList(user.getId(),
				wechatLoginInfoList,
				options,
				getWechatLoginInfoDAO()::countWechatLoginInfoByUser,
				getWechatLoginInfoDAO()::findWechatLoginInfoByUser
				);

		
		user.setWechatLoginInfoList(newList);
		

		return user;
	}			
		

	
    public SmartList<User> requestCandidateUserForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForWechatLoginInfo(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, filterKey, pageNo, pageSize, getUserMapper());
    }
		

	protected String getTableName(){
		return UserTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<User> userList) {		
		this.enhanceListInternal(userList, this.getUserMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Teacher的user的TeacherList
	public SmartList<Teacher> loadOurTeacherList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Teacher> loadedObjs = userContext.getDAOGroup().getTeacherDAO().findTeacherWithKey(key, options);
		Map<String, List<Teacher>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getUser().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Teacher> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Teacher> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setTeacherList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Student的user的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Student> loadedObjs = userContext.getDAOGroup().getStudentDAO().findStudentWithKey(key, options);
		Map<String, List<Student>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getUser().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:Question的creator的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.CREATOR_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Question> loadedObjs = userContext.getDAOGroup().getQuestionDAO().findQuestionWithKey(key, options);
		Map<String, List<Question>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCreator().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Question> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Question> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setQuestionList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的creator的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
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
	
	// 需要一个加载引用我的对象的enhance方法:WechatLoginInfo的user的WechatLoginInfoList
	public SmartList<WechatLoginInfo> loadOurWechatLoginInfoList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatLoginInfo.USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WechatLoginInfo> loadedObjs = userContext.getDAOGroup().getWechatLoginInfoDAO().findWechatLoginInfoWithKey(key, options);
		Map<String, List<WechatLoginInfo>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getUser().getId()));
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
		List<User> userList = ownerEntity.collectRefsWithType(User.INTERNAL_TYPE);
		this.enhanceList(userList);
		
	}
	
	@Override
	public SmartList<User> findUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getUserMapper());

	}
	@Override
	public int countUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<User> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getUserMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


