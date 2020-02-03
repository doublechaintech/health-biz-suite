
package com.doublechaintech.health.teacher;

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
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.user.UserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class TeacherJDBCTemplateDAO extends HealthBaseDAOImpl implements TeacherDAO{
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
 	}
 
 	
 	private  UserDAO  userDAO;
 	public void setUserDAO(UserDAO userDAO){
	 	this.userDAO = userDAO;
 	}
 	public UserDAO getUserDAO(){
	 	return this.userDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
 	
			
		
	
  	private  StudentHealthSurveyDAO  studentHealthSurveyDAO;
 	public void setStudentHealthSurveyDAO(StudentHealthSurveyDAO pStudentHealthSurveyDAO){
 	
 		if(pStudentHealthSurveyDAO == null){
 			throw new IllegalStateException("Do not try to set studentHealthSurveyDAO to null.");
 		}
	 	this.studentHealthSurveyDAO = pStudentHealthSurveyDAO;
 	}
 	public StudentHealthSurveyDAO getStudentHealthSurveyDAO(){
 		if(this.studentHealthSurveyDAO == null){
 			throw new IllegalStateException("The studentHealthSurveyDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentHealthSurveyDAO;
 	}	
 	
			
		
	
  	private  HealthSurveyReportDAO  healthSurveyReportDAO;
 	public void setHealthSurveyReportDAO(HealthSurveyReportDAO pHealthSurveyReportDAO){
 	
 		if(pHealthSurveyReportDAO == null){
 			throw new IllegalStateException("Do not try to set healthSurveyReportDAO to null.");
 		}
	 	this.healthSurveyReportDAO = pHealthSurveyReportDAO;
 	}
 	public HealthSurveyReportDAO getHealthSurveyReportDAO(){
 		if(this.healthSurveyReportDAO == null){
 			throw new IllegalStateException("The healthSurveyReportDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.healthSurveyReportDAO;
 	}	
 	
			
		

	
	/*
	protected Teacher load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalTeacher(accessKey, options);
	}
	*/
	
	public SmartList<Teacher> loadAll() {
	    return this.loadAll(getTeacherMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Teacher load(String id,Map<String,Object> options) throws Exception{
		return loadInternalTeacher(TeacherTable.withId(id), options);
	}
	
	
	
	public Teacher save(Teacher teacher,Map<String,Object> options){
		
		String methodName="save(Teacher teacher,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(teacher, methodName, "teacher");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalTeacher(teacher,options);
	}
	public Teacher clone(String teacherId, Map<String,Object> options) throws Exception{
	
		return clone(TeacherTable.withId(teacherId),options);
	}
	
	protected Teacher clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String teacherId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Teacher newTeacher = loadInternalTeacher(accessKey, options);
		newTeacher.setVersion(0);
		
		
 		
 		if(isSaveClassDailyHealthSurveyListEnabled(options)){
 			for(ClassDailyHealthSurvey item: newTeacher.getClassDailyHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentHealthSurveyListEnabled(options)){
 			for(StudentHealthSurvey item: newTeacher.getStudentHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveHealthSurveyReportListEnabled(options)){
 			for(HealthSurveyReport item: newTeacher.getHealthSurveyReportList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalTeacher(newTeacher,options);
		
		return newTeacher;
	}
	
	
	
	

	protected void throwIfHasException(String teacherId,int version,int count) throws Exception{
		if (count == 1) {
			throw new TeacherVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new TeacherNotFoundException(
					"The " + this.getTableName() + "(" + teacherId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String teacherId, int version) throws Exception{
	
		String methodName="delete(String teacherId, int version)";
		assertMethodArgumentNotNull(teacherId, methodName, "teacherId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{teacherId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(teacherId,version);
		}
		
	
	}
	
	
	
	
	

	public Teacher disconnectFromAll(String teacherId, int version) throws Exception{
	
		
		Teacher teacher = loadInternalTeacher(TeacherTable.withId(teacherId), emptyOptions());
		teacher.clearFromAll();
		this.saveTeacher(teacher);
		return teacher;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return TeacherTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "teacher";
	}
	@Override
	protected String getBeanName() {
		
		return "teacher";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return TeacherTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TeacherTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TeacherTokens.PLATFORM);
 	}
 	

 	
  

 	protected boolean isExtractUserEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TeacherTokens.USER);
 	}

 	protected boolean isSaveUserEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TeacherTokens.USER);
 	}
 	

 	
  

 	protected boolean isExtractChangeRequestEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TeacherTokens.CHANGEREQUEST);
 	}

 	protected boolean isSaveChangeRequestEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TeacherTokens.CHANGEREQUEST);
 	}
 	

 	
 
		
	
	protected boolean isExtractClassDailyHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,TeacherTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeClassDailyHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return TeacherTokens.of(options).analyzeClassDailyHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveClassDailyHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, TeacherTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,TeacherTokens.STUDENT_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeStudentHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return TeacherTokens.of(options).analyzeStudentHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveStudentHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, TeacherTokens.STUDENT_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractHealthSurveyReportListEnabled(Map<String,Object> options){		
 		return checkOptions(options,TeacherTokens.HEALTH_SURVEY_REPORT_LIST);
 	}
 	protected boolean isAnalyzeHealthSurveyReportListEnabled(Map<String,Object> options){		 		
 		return TeacherTokens.of(options).analyzeHealthSurveyReportListEnabled();
 	}
	
	protected boolean isSaveHealthSurveyReportListEnabled(Map<String,Object> options){
		return checkOptions(options, TeacherTokens.HEALTH_SURVEY_REPORT_LIST);
		
 	}
 	
		

	

	protected TeacherMapper getTeacherMapper(){
		return new TeacherMapper();
	}

	
	
	protected Teacher extractTeacher(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Teacher teacher = loadSingleObject(accessKey, getTeacherMapper());
			return teacher;
		}catch(EmptyResultDataAccessException e){
			throw new TeacherNotFoundException("Teacher("+accessKey+") is not found!");
		}

	}

	
	

	protected Teacher loadInternalTeacher(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Teacher teacher = extractTeacher(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(teacher, loadOptions);
 		}
  	
 		if(isExtractUserEnabled(loadOptions)){
	 		extractUser(teacher, loadOptions);
 		}
  	
 		if(isExtractChangeRequestEnabled(loadOptions)){
	 		extractChangeRequest(teacher, loadOptions);
 		}
 
		
		if(isExtractClassDailyHealthSurveyListEnabled(loadOptions)){
	 		extractClassDailyHealthSurveyList(teacher, loadOptions);
 		}	
 		if(isAnalyzeClassDailyHealthSurveyListEnabled(loadOptions)){
	 		analyzeClassDailyHealthSurveyList(teacher, loadOptions);
 		}
 		
		
		if(isExtractStudentHealthSurveyListEnabled(loadOptions)){
	 		extractStudentHealthSurveyList(teacher, loadOptions);
 		}	
 		if(isAnalyzeStudentHealthSurveyListEnabled(loadOptions)){
	 		analyzeStudentHealthSurveyList(teacher, loadOptions);
 		}
 		
		
		if(isExtractHealthSurveyReportListEnabled(loadOptions)){
	 		extractHealthSurveyReportList(teacher, loadOptions);
 		}	
 		if(isAnalyzeHealthSurveyReportListEnabled(loadOptions)){
	 		analyzeHealthSurveyReportList(teacher, loadOptions);
 		}
 		
		
		return teacher;
		
	}

	 

 	protected Teacher extractPlatform(Teacher teacher, Map<String,Object> options) throws Exception{

		if(teacher.getPlatform() == null){
			return teacher;
		}
		String platformId = teacher.getPlatform().getId();
		if( platformId == null){
			return teacher;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			teacher.setPlatform(platform);
		}
		
 		
 		return teacher;
 	}
 		
  

 	protected Teacher extractUser(Teacher teacher, Map<String,Object> options) throws Exception{

		if(teacher.getUser() == null){
			return teacher;
		}
		String userId = teacher.getUser().getId();
		if( userId == null){
			return teacher;
		}
		User user = getUserDAO().load(userId,options);
		if(user != null){
			teacher.setUser(user);
		}
		
 		
 		return teacher;
 	}
 		
  

 	protected Teacher extractChangeRequest(Teacher teacher, Map<String,Object> options) throws Exception{

		if(teacher.getChangeRequest() == null){
			return teacher;
		}
		String changeRequestId = teacher.getChangeRequest().getId();
		if( changeRequestId == null){
			return teacher;
		}
		ChangeRequest changeRequest = getChangeRequestDAO().load(changeRequestId,options);
		if(changeRequest != null){
			teacher.setChangeRequest(changeRequest);
		}
		
 		
 		return teacher;
 	}
 		
 
		
	protected void enhanceClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Teacher extractClassDailyHealthSurveyList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyByTeacher(teacher.getId(),options);
		if(classDailyHealthSurveyList != null){
			enhanceClassDailyHealthSurveyList(classDailyHealthSurveyList,options);
			teacher.setClassDailyHealthSurveyList(classDailyHealthSurveyList);
		}
		
		return teacher;
	
	}	
	
	protected Teacher analyzeClassDailyHealthSurveyList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList != null){
			getClassDailyHealthSurveyDAO().analyzeClassDailyHealthSurveyByTeacher(classDailyHealthSurveyList, teacher.getId(), options);
			
		}
		
		return teacher;
	
	}	
	
		
	protected void enhanceStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Teacher extractStudentHealthSurveyList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = getStudentHealthSurveyDAO().findStudentHealthSurveyByTeacher(teacher.getId(),options);
		if(studentHealthSurveyList != null){
			enhanceStudentHealthSurveyList(studentHealthSurveyList,options);
			teacher.setStudentHealthSurveyList(studentHealthSurveyList);
		}
		
		return teacher;
	
	}	
	
	protected Teacher analyzeStudentHealthSurveyList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();
		if(studentHealthSurveyList != null){
			getStudentHealthSurveyDAO().analyzeStudentHealthSurveyByTeacher(studentHealthSurveyList, teacher.getId(), options);
			
		}
		
		return teacher;
	
	}	
	
		
	protected void enhanceHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Teacher extractHealthSurveyReportList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<HealthSurveyReport> healthSurveyReportList = getHealthSurveyReportDAO().findHealthSurveyReportByTeacher(teacher.getId(),options);
		if(healthSurveyReportList != null){
			enhanceHealthSurveyReportList(healthSurveyReportList,options);
			teacher.setHealthSurveyReportList(healthSurveyReportList);
		}
		
		return teacher;
	
	}	
	
	protected Teacher analyzeHealthSurveyReportList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();
		if(healthSurveyReportList != null){
			getHealthSurveyReportDAO().analyzeHealthSurveyReportByTeacher(healthSurveyReportList, teacher.getId(), options);
			
		}
		
		return teacher;
	
	}	
	
		
		
  	
 	public SmartList<Teacher> findTeacherByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Teacher> resultList = queryWith(TeacherTable.COLUMN_PLATFORM, platformId, options, getTeacherMapper());
		// analyzeTeacherByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Teacher> findTeacherByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Teacher> resultList =  queryWithRange(TeacherTable.COLUMN_PLATFORM, platformId, options, getTeacherMapper(), start, count);
 		//analyzeTeacherByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeTeacherByPlatform(SmartList<Teacher> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Teacher.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Teacher.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("老师");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Teacher.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Teacher.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTeacherByPlatform(String platformId,Map<String,Object> options){

 		return countWith(TeacherTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countTeacherByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TeacherTable.COLUMN_PLATFORM, ids, options);
	}
 	
  	
 	public SmartList<Teacher> findTeacherByUser(String userId,Map<String,Object> options){
 	
  		SmartList<Teacher> resultList = queryWith(TeacherTable.COLUMN_USER, userId, options, getTeacherMapper());
		// analyzeTeacherByUser(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Teacher> findTeacherByUser(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Teacher> resultList =  queryWithRange(TeacherTable.COLUMN_USER, userId, options, getTeacherMapper(), start, count);
 		//analyzeTeacherByUser(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzeTeacherByUser(SmartList<Teacher> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Teacher.USER_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Teacher.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("老师");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Teacher.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Teacher.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTeacherByUser(String userId,Map<String,Object> options){

 		return countWith(TeacherTable.COLUMN_USER, userId, options);
 	}
 	@Override
	public Map<String, Integer> countTeacherByUserIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TeacherTable.COLUMN_USER, ids, options);
	}
 	
  	
 	public SmartList<Teacher> findTeacherByChangeRequest(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<Teacher> resultList = queryWith(TeacherTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getTeacherMapper());
		// analyzeTeacherByChangeRequest(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Teacher> findTeacherByChangeRequest(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Teacher> resultList =  queryWithRange(TeacherTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getTeacherMapper(), start, count);
 		//analyzeTeacherByChangeRequest(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeTeacherByChangeRequest(SmartList<Teacher> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Teacher.CHANGE_REQUEST_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Teacher.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("老师");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Teacher.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Teacher.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTeacherByChangeRequest(String changeRequestId,Map<String,Object> options){

 		return countWith(TeacherTable.COLUMN_CHANGE_REQUEST, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countTeacherByChangeRequestIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TeacherTable.COLUMN_CHANGE_REQUEST, ids, options);
	}
 	
 	
		
		
		

	

	protected Teacher saveTeacher(Teacher  teacher){
		
		if(!teacher.isChanged()){
			return teacher;
		}
		
		
		String SQL=this.getSaveTeacherSQL(teacher);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveTeacherParameters(teacher);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		teacher.incVersion();
		return teacher;
	
	}
	public SmartList<Teacher> saveTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitTeacherList(teacherList);
		
		batchTeacherCreate((List<Teacher>)lists[CREATE_LIST_INDEX]);
		
		batchTeacherUpdate((List<Teacher>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Teacher teacher:teacherList){
			if(teacher.isChanged()){
				teacher.incVersion();
			}
			
		
		}
		
		
		return teacherList;
	}

	public SmartList<Teacher> removeTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		
		
		super.removeList(teacherList, options);
		
		return teacherList;
		
		
	}
	
	protected List<Object[]> prepareTeacherBatchCreateArgs(List<Teacher> teacherList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Teacher teacher:teacherList ){
			Object [] parameters = prepareTeacherCreateParameters(teacher);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareTeacherBatchUpdateArgs(List<Teacher> teacherList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Teacher teacher:teacherList ){
			if(!teacher.isChanged()){
				continue;
			}
			Object [] parameters = prepareTeacherUpdateParameters(teacher);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchTeacherCreate(List<Teacher> teacherList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareTeacherBatchCreateArgs(teacherList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchTeacherUpdate(List<Teacher> teacherList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareTeacherBatchUpdateArgs(teacherList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitTeacherList(List<Teacher> teacherList){
		
		List<Teacher> teacherCreateList=new ArrayList<Teacher>();
		List<Teacher> teacherUpdateList=new ArrayList<Teacher>();
		
		for(Teacher teacher: teacherList){
			if(isUpdateRequest(teacher)){
				teacherUpdateList.add( teacher);
				continue;
			}
			teacherCreateList.add(teacher);
		}
		
		return new Object[]{teacherCreateList,teacherUpdateList};
	}
	
	protected boolean isUpdateRequest(Teacher teacher){
 		return teacher.getVersion() > 0;
 	}
 	protected String getSaveTeacherSQL(Teacher teacher){
 		if(isUpdateRequest(teacher)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveTeacherParameters(Teacher teacher){
 		if(isUpdateRequest(teacher) ){
 			return prepareTeacherUpdateParameters(teacher);
 		}
 		return prepareTeacherCreateParameters(teacher);
 	}
 	protected Object[] prepareTeacherUpdateParameters(Teacher teacher){
 		Object[] parameters = new Object[12];
 
 		parameters[0] = teacher.getName();
 		parameters[1] = teacher.getMobile();
 		parameters[2] = teacher.getSchool();
 		parameters[3] = teacher.getSchoolClass();
 		parameters[4] = teacher.getClassSize();
 		parameters[5] = teacher.getCreateTime(); 	
 		if(teacher.getPlatform() != null){
 			parameters[6] = teacher.getPlatform().getId();
 		}
  	
 		if(teacher.getUser() != null){
 			parameters[7] = teacher.getUser().getId();
 		}
  	
 		if(teacher.getChangeRequest() != null){
 			parameters[8] = teacher.getChangeRequest().getId();
 		}
 		
 		parameters[9] = teacher.nextVersion();
 		parameters[10] = teacher.getId();
 		parameters[11] = teacher.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareTeacherCreateParameters(Teacher teacher){
		Object[] parameters = new Object[10];
		String newTeacherId=getNextId();
		teacher.setId(newTeacherId);
		parameters[0] =  teacher.getId();
 
 		parameters[1] = teacher.getName();
 		parameters[2] = teacher.getMobile();
 		parameters[3] = teacher.getSchool();
 		parameters[4] = teacher.getSchoolClass();
 		parameters[5] = teacher.getClassSize();
 		parameters[6] = teacher.getCreateTime(); 	
 		if(teacher.getPlatform() != null){
 			parameters[7] = teacher.getPlatform().getId();
 		
 		}
 		 	
 		if(teacher.getUser() != null){
 			parameters[8] = teacher.getUser().getId();
 		
 		}
 		 	
 		if(teacher.getChangeRequest() != null){
 			parameters[9] = teacher.getChangeRequest().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Teacher saveInternalTeacher(Teacher teacher, Map<String,Object> options){
		
		saveTeacher(teacher);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(teacher, options);
 		}
  	
 		if(isSaveUserEnabled(options)){
	 		saveUser(teacher, options);
 		}
  	
 		if(isSaveChangeRequestEnabled(options)){
	 		saveChangeRequest(teacher, options);
 		}
 
		
		if(isSaveClassDailyHealthSurveyListEnabled(options)){
	 		saveClassDailyHealthSurveyList(teacher, options);
	 		//removeClassDailyHealthSurveyList(teacher, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentHealthSurveyListEnabled(options)){
	 		saveStudentHealthSurveyList(teacher, options);
	 		//removeStudentHealthSurveyList(teacher, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveHealthSurveyReportListEnabled(options)){
	 		saveHealthSurveyReportList(teacher, options);
	 		//removeHealthSurveyReportList(teacher, options);
	 		//Not delete the record
	 		
 		}		
		
		return teacher;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Teacher savePlatform(Teacher teacher, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(teacher.getPlatform() == null){
 			return teacher;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(teacher.getPlatform(),options);
 		return teacher;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Teacher saveUser(Teacher teacher, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(teacher.getUser() == null){
 			return teacher;//do nothing when it is null
 		}
 		
 		getUserDAO().save(teacher.getUser(),options);
 		return teacher;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Teacher saveChangeRequest(Teacher teacher, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(teacher.getChangeRequest() == null){
 			return teacher;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(teacher.getChangeRequest(),options);
 		return teacher;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Teacher planToRemoveClassDailyHealthSurveyList(Teacher teacher, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(ClassDailyHealthSurvey.ID_PROPERTY, classDailyHealthSurveyIds);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return teacher;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){

			classDailyHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return teacher;	
	
	}


	//disconnect Teacher with creator in ClassDailyHealthSurvey
	public Teacher planToRemoveClassDailyHealthSurveyListWithCreator(Teacher teacher, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, creatorId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return teacher;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearCreator();
			classDailyHealthSurveyItem.clearTeacher();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return teacher;
	}
	
	public int countClassDailyHealthSurveyListWithCreator(String teacherId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacherId);
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, creatorId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Teacher with change_request in ClassDailyHealthSurvey
	public Teacher planToRemoveClassDailyHealthSurveyListWithChangeRequest(Teacher teacher, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(ClassDailyHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return teacher;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearChangeRequest();
			classDailyHealthSurveyItem.clearTeacher();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return teacher;
	}
	
	public int countClassDailyHealthSurveyListWithChangeRequest(String teacherId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacherId);
		key.put(ClassDailyHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	public Teacher planToRemoveStudentHealthSurveyList(Teacher teacher, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(StudentHealthSurvey.ID_PROPERTY, studentHealthSurveyIds);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return teacher;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){

			studentHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return teacher;	
	
	}


	//disconnect Teacher with student in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithStudent(Teacher teacher, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return teacher;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearStudent();
			studentHealthSurveyItem.clearTeacher();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return teacher;
	}
	
	public int countStudentHealthSurveyListWithStudent(String teacherId, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Teacher with survey_status in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithSurveyStatus(Teacher teacher, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return teacher;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSurveyStatus();
			studentHealthSurveyItem.clearTeacher();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return teacher;
	}
	
	public int countStudentHealthSurveyListWithSurveyStatus(String teacherId, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Teacher with class_daily_health_survey in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(Teacher teacher, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return teacher;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			studentHealthSurveyItem.clearTeacher();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return teacher;
	}
	
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String teacherId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Teacher with change_request in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithChangeRequest(Teacher teacher, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacher.getId());
		key.put(StudentHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return teacher;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return teacher;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearChangeRequest();
			studentHealthSurveyItem.clearTeacher();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return teacher;
	}
	
	public int countStudentHealthSurveyListWithChangeRequest(String teacherId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		key.put(StudentHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	public Teacher planToRemoveHealthSurveyReportList(Teacher teacher, String healthSurveyReportIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(HealthSurveyReport.TEACHER_PROPERTY, teacher.getId());
		key.put(HealthSurveyReport.ID_PROPERTY, healthSurveyReportIds);
		
		SmartList<HealthSurveyReport> externalHealthSurveyReportList = getHealthSurveyReportDAO().
				findHealthSurveyReportWithKey(key, options);
		if(externalHealthSurveyReportList == null){
			return teacher;
		}
		if(externalHealthSurveyReportList.isEmpty()){
			return teacher;
		}
		
		for(HealthSurveyReport healthSurveyReportItem: externalHealthSurveyReportList){

			healthSurveyReportItem.clearFromAll();
		}
		
		
		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();		
		healthSurveyReportList.addAllToRemoveList(externalHealthSurveyReportList);
		return teacher;	
	
	}


	//disconnect Teacher with student in HealthSurveyReport
	public Teacher planToRemoveHealthSurveyReportListWithStudent(Teacher teacher, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(HealthSurveyReport.TEACHER_PROPERTY, teacher.getId());
		key.put(HealthSurveyReport.STUDENT_PROPERTY, studentId);
		
		SmartList<HealthSurveyReport> externalHealthSurveyReportList = getHealthSurveyReportDAO().
				findHealthSurveyReportWithKey(key, options);
		if(externalHealthSurveyReportList == null){
			return teacher;
		}
		if(externalHealthSurveyReportList.isEmpty()){
			return teacher;
		}
		
		for(HealthSurveyReport healthSurveyReportItem: externalHealthSurveyReportList){
			healthSurveyReportItem.clearStudent();
			healthSurveyReportItem.clearTeacher();
			
		}
		
		
		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();		
		healthSurveyReportList.addAllToRemoveList(externalHealthSurveyReportList);
		return teacher;
	}
	
	public int countHealthSurveyReportListWithStudent(String teacherId, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(HealthSurveyReport.TEACHER_PROPERTY, teacherId);
		key.put(HealthSurveyReport.STUDENT_PROPERTY, studentId);
		
		int count = getHealthSurveyReportDAO().countHealthSurveyReportWithKey(key, options);
		return count;
	}
	
	//disconnect Teacher with survey in HealthSurveyReport
	public Teacher planToRemoveHealthSurveyReportListWithSurvey(Teacher teacher, String surveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(HealthSurveyReport.TEACHER_PROPERTY, teacher.getId());
		key.put(HealthSurveyReport.SURVEY_PROPERTY, surveyId);
		
		SmartList<HealthSurveyReport> externalHealthSurveyReportList = getHealthSurveyReportDAO().
				findHealthSurveyReportWithKey(key, options);
		if(externalHealthSurveyReportList == null){
			return teacher;
		}
		if(externalHealthSurveyReportList.isEmpty()){
			return teacher;
		}
		
		for(HealthSurveyReport healthSurveyReportItem: externalHealthSurveyReportList){
			healthSurveyReportItem.clearSurvey();
			healthSurveyReportItem.clearTeacher();
			
		}
		
		
		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();		
		healthSurveyReportList.addAllToRemoveList(externalHealthSurveyReportList);
		return teacher;
	}
	
	public int countHealthSurveyReportListWithSurvey(String teacherId, String surveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(HealthSurveyReport.TEACHER_PROPERTY, teacherId);
		key.put(HealthSurveyReport.SURVEY_PROPERTY, surveyId);
		
		int count = getHealthSurveyReportDAO().countHealthSurveyReportWithKey(key, options);
		return count;
	}
	

		
	protected Teacher saveClassDailyHealthSurveyList(Teacher teacher, Map<String,Object> options){
		
		
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			//null list means nothing
			return teacher;
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
		
		
		return teacher;
	
	}
	
	protected Teacher removeClassDailyHealthSurveyList(Teacher teacher, Map<String,Object> options){
	
	
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			return teacher;
		}	
	
		SmartList<ClassDailyHealthSurvey> toRemoveClassDailyHealthSurveyList = classDailyHealthSurveyList.getToRemoveList();
		
		if(toRemoveClassDailyHealthSurveyList == null){
			return teacher;
		}
		if(toRemoveClassDailyHealthSurveyList.isEmpty()){
			return teacher;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassDailyHealthSurveyDAO().removeClassDailyHealthSurveyList(toRemoveClassDailyHealthSurveyList,options);
		
		return teacher;
	
	}
	
	

 	
 	
	
	
	
		
	protected Teacher saveStudentHealthSurveyList(Teacher teacher, Map<String,Object> options){
		
		
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			//null list means nothing
			return teacher;
		}
		SmartList<StudentHealthSurvey> mergedUpdateStudentHealthSurveyList = new SmartList<StudentHealthSurvey>();
		
		
		mergedUpdateStudentHealthSurveyList.addAll(studentHealthSurveyList); 
		if(studentHealthSurveyList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentHealthSurveyList.addAll(studentHealthSurveyList.getToRemoveList());
			studentHealthSurveyList.removeAll(studentHealthSurveyList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentHealthSurveyDAO().saveStudentHealthSurveyList(mergedUpdateStudentHealthSurveyList,options);
		
		if(studentHealthSurveyList.getToRemoveList() != null){
			studentHealthSurveyList.removeAll(studentHealthSurveyList.getToRemoveList());
		}
		
		
		return teacher;
	
	}
	
	protected Teacher removeStudentHealthSurveyList(Teacher teacher, Map<String,Object> options){
	
	
		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			return teacher;
		}	
	
		SmartList<StudentHealthSurvey> toRemoveStudentHealthSurveyList = studentHealthSurveyList.getToRemoveList();
		
		if(toRemoveStudentHealthSurveyList == null){
			return teacher;
		}
		if(toRemoveStudentHealthSurveyList.isEmpty()){
			return teacher;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentHealthSurveyDAO().removeStudentHealthSurveyList(toRemoveStudentHealthSurveyList,options);
		
		return teacher;
	
	}
	
	

 	
 	
	
	
	
		
	protected Teacher saveHealthSurveyReportList(Teacher teacher, Map<String,Object> options){
		
		
		
		
		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();
		if(healthSurveyReportList == null){
			//null list means nothing
			return teacher;
		}
		SmartList<HealthSurveyReport> mergedUpdateHealthSurveyReportList = new SmartList<HealthSurveyReport>();
		
		
		mergedUpdateHealthSurveyReportList.addAll(healthSurveyReportList); 
		if(healthSurveyReportList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateHealthSurveyReportList.addAll(healthSurveyReportList.getToRemoveList());
			healthSurveyReportList.removeAll(healthSurveyReportList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getHealthSurveyReportDAO().saveHealthSurveyReportList(mergedUpdateHealthSurveyReportList,options);
		
		if(healthSurveyReportList.getToRemoveList() != null){
			healthSurveyReportList.removeAll(healthSurveyReportList.getToRemoveList());
		}
		
		
		return teacher;
	
	}
	
	protected Teacher removeHealthSurveyReportList(Teacher teacher, Map<String,Object> options){
	
	
		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();
		if(healthSurveyReportList == null){
			return teacher;
		}	
	
		SmartList<HealthSurveyReport> toRemoveHealthSurveyReportList = healthSurveyReportList.getToRemoveList();
		
		if(toRemoveHealthSurveyReportList == null){
			return teacher;
		}
		if(toRemoveHealthSurveyReportList.isEmpty()){
			return teacher;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getHealthSurveyReportDAO().removeHealthSurveyReportList(toRemoveHealthSurveyReportList,options);
		
		return teacher;
	
	}
	
	

 	
 	
	
	
	
		

	public Teacher present(Teacher teacher,Map<String, Object> options){
	
		presentClassDailyHealthSurveyList(teacher,options);
		presentStudentHealthSurveyList(teacher,options);
		presentHealthSurveyReportList(teacher,options);

		return teacher;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Teacher presentClassDailyHealthSurveyList(
			Teacher teacher,
			Map<String, Object> options) {

		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = teacher.getClassDailyHealthSurveyList();		
				SmartList<ClassDailyHealthSurvey> newList= presentSubList(teacher.getId(),
				classDailyHealthSurveyList,
				options,
				getClassDailyHealthSurveyDAO()::countClassDailyHealthSurveyByTeacher,
				getClassDailyHealthSurveyDAO()::findClassDailyHealthSurveyByTeacher
				);

		
		teacher.setClassDailyHealthSurveyList(newList);
		

		return teacher;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Teacher presentStudentHealthSurveyList(
			Teacher teacher,
			Map<String, Object> options) {

		SmartList<StudentHealthSurvey> studentHealthSurveyList = teacher.getStudentHealthSurveyList();		
				SmartList<StudentHealthSurvey> newList= presentSubList(teacher.getId(),
				studentHealthSurveyList,
				options,
				getStudentHealthSurveyDAO()::countStudentHealthSurveyByTeacher,
				getStudentHealthSurveyDAO()::findStudentHealthSurveyByTeacher
				);

		
		teacher.setStudentHealthSurveyList(newList);
		

		return teacher;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Teacher presentHealthSurveyReportList(
			Teacher teacher,
			Map<String, Object> options) {

		SmartList<HealthSurveyReport> healthSurveyReportList = teacher.getHealthSurveyReportList();		
				SmartList<HealthSurveyReport> newList= presentSubList(teacher.getId(),
				healthSurveyReportList,
				options,
				getHealthSurveyReportDAO()::countHealthSurveyReportByTeacher,
				getHealthSurveyReportDAO()::findHealthSurveyReportByTeacher
				);

		
		teacher.setHealthSurveyReportList(newList);
		

		return teacher;
	}			
		

	
    public SmartList<Teacher> requestCandidateTeacherForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(TeacherTable.COLUMN_NAME, filterKey, pageNo, pageSize, getTeacherMapper());
    }
		
    public SmartList<Teacher> requestCandidateTeacherForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(TeacherTable.COLUMN_NAME, filterKey, pageNo, pageSize, getTeacherMapper());
    }
		
    public SmartList<Teacher> requestCandidateTeacherForHealthSurveyReport(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(TeacherTable.COLUMN_NAME, filterKey, pageNo, pageSize, getTeacherMapper());
    }
		

	protected String getTableName(){
		return TeacherTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Teacher> teacherList) {		
		this.enhanceListInternal(teacherList, this.getTeacherMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的teacher的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassDailyHealthSurvey> loadedObjs = userContext.getDAOGroup().getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyWithKey(key, options);
		Map<String, List<ClassDailyHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getTeacher().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的teacher的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentHealthSurvey> loadedObjs = userContext.getDAOGroup().getStudentHealthSurveyDAO().findStudentHealthSurveyWithKey(key, options);
		Map<String, List<StudentHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getTeacher().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<StudentHealthSurvey> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<StudentHealthSurvey> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentHealthSurveyList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:HealthSurveyReport的teacher的HealthSurveyReportList
	public SmartList<HealthSurveyReport> loadOurHealthSurveyReportList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(HealthSurveyReport.TEACHER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<HealthSurveyReport> loadedObjs = userContext.getDAOGroup().getHealthSurveyReportDAO().findHealthSurveyReportWithKey(key, options);
		Map<String, List<HealthSurveyReport>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getTeacher().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<HealthSurveyReport> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<HealthSurveyReport> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setHealthSurveyReportList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Teacher> teacherList = ownerEntity.collectRefsWithType(Teacher.INTERNAL_TYPE);
		this.enhanceList(teacherList);
		
	}
	
	@Override
	public SmartList<Teacher> findTeacherWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getTeacherMapper());

	}
	@Override
	public int countTeacherWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countTeacherWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Teacher> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getTeacherMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


