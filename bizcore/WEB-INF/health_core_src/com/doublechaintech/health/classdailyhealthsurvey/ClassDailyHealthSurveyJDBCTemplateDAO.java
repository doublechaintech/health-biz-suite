
package com.doublechaintech.health.classdailyhealthsurvey;

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


import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.user.UserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class ClassDailyHealthSurveyJDBCTemplateDAO extends HealthBaseDAOImpl implements ClassDailyHealthSurveyDAO{
 
 	
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
 
 	
 	private  TeacherDAO  teacherDAO;
 	public void setTeacherDAO(TeacherDAO teacherDAO){
	 	this.teacherDAO = teacherDAO;
 	}
 	public TeacherDAO getTeacherDAO(){
	 	return this.teacherDAO;
 	}


			
		
	
  	private  DailySurveyQuestionDAO  dailySurveyQuestionDAO;
 	public void setDailySurveyQuestionDAO(DailySurveyQuestionDAO pDailySurveyQuestionDAO){
 	
 		if(pDailySurveyQuestionDAO == null){
 			throw new IllegalStateException("Do not try to set dailySurveyQuestionDAO to null.");
 		}
	 	this.dailySurveyQuestionDAO = pDailySurveyQuestionDAO;
 	}
 	public DailySurveyQuestionDAO getDailySurveyQuestionDAO(){
 		if(this.dailySurveyQuestionDAO == null){
 			throw new IllegalStateException("The dailySurveyQuestionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.dailySurveyQuestionDAO;
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
 	
			
		

	
	/*
	protected ClassDailyHealthSurvey load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalClassDailyHealthSurvey(accessKey, options);
	}
	*/
	
	public SmartList<ClassDailyHealthSurvey> loadAll() {
	    return this.loadAll(getClassDailyHealthSurveyMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ClassDailyHealthSurvey load(String id,Map<String,Object> options) throws Exception{
		return loadInternalClassDailyHealthSurvey(ClassDailyHealthSurveyTable.withId(id), options);
	}
	
	
	
	public ClassDailyHealthSurvey save(ClassDailyHealthSurvey classDailyHealthSurvey,Map<String,Object> options){
		
		String methodName="save(ClassDailyHealthSurvey classDailyHealthSurvey,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(classDailyHealthSurvey, methodName, "classDailyHealthSurvey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalClassDailyHealthSurvey(classDailyHealthSurvey,options);
	}
	public ClassDailyHealthSurvey clone(String classDailyHealthSurveyId, Map<String,Object> options) throws Exception{
	
		return clone(ClassDailyHealthSurveyTable.withId(classDailyHealthSurveyId),options);
	}
	
	protected ClassDailyHealthSurvey clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String classDailyHealthSurveyId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ClassDailyHealthSurvey newClassDailyHealthSurvey = loadInternalClassDailyHealthSurvey(accessKey, options);
		newClassDailyHealthSurvey.setVersion(0);
		
		
 		
 		if(isSaveDailySurveyQuestionListEnabled(options)){
 			for(DailySurveyQuestion item: newClassDailyHealthSurvey.getDailySurveyQuestionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentHealthSurveyListEnabled(options)){
 			for(StudentHealthSurvey item: newClassDailyHealthSurvey.getStudentHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalClassDailyHealthSurvey(newClassDailyHealthSurvey,options);
		
		return newClassDailyHealthSurvey;
	}
	
	
	
	

	protected void throwIfHasException(String classDailyHealthSurveyId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ClassDailyHealthSurveyVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ClassDailyHealthSurveyNotFoundException(
					"The " + this.getTableName() + "(" + classDailyHealthSurveyId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String classDailyHealthSurveyId, int version) throws Exception{
	
		String methodName="delete(String classDailyHealthSurveyId, int version)";
		assertMethodArgumentNotNull(classDailyHealthSurveyId, methodName, "classDailyHealthSurveyId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{classDailyHealthSurveyId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(classDailyHealthSurveyId,version);
		}
		
	
	}
	
	
	
	
	

	public ClassDailyHealthSurvey disconnectFromAll(String classDailyHealthSurveyId, int version) throws Exception{
	
		
		ClassDailyHealthSurvey classDailyHealthSurvey = loadInternalClassDailyHealthSurvey(ClassDailyHealthSurveyTable.withId(classDailyHealthSurveyId), emptyOptions());
		classDailyHealthSurvey.clearFromAll();
		this.saveClassDailyHealthSurvey(classDailyHealthSurvey);
		return classDailyHealthSurvey;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ClassDailyHealthSurveyTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "class_daily_health_survey";
	}
	@Override
	protected String getBeanName() {
		
		return "classDailyHealthSurvey";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ClassDailyHealthSurveyTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractTeacherEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassDailyHealthSurveyTokens.TEACHER);
 	}

 	protected boolean isSaveTeacherEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassDailyHealthSurveyTokens.TEACHER);
 	}
 	

 	
  

 	protected boolean isExtractCreatorEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassDailyHealthSurveyTokens.CREATOR);
 	}

 	protected boolean isSaveCreatorEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassDailyHealthSurveyTokens.CREATOR);
 	}
 	

 	
  

 	protected boolean isExtractChangeRequestEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassDailyHealthSurveyTokens.CHANGEREQUEST);
 	}

 	protected boolean isSaveChangeRequestEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassDailyHealthSurveyTokens.CHANGEREQUEST);
 	}
 	

 	
 
		
	
	protected boolean isExtractDailySurveyQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ClassDailyHealthSurveyTokens.DAILY_SURVEY_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeDailySurveyQuestionListEnabled(Map<String,Object> options){		 		
 		return ClassDailyHealthSurveyTokens.of(options).analyzeDailySurveyQuestionListEnabled();
 	}
	
	protected boolean isSaveDailySurveyQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, ClassDailyHealthSurveyTokens.DAILY_SURVEY_QUESTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ClassDailyHealthSurveyTokens.STUDENT_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeStudentHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return ClassDailyHealthSurveyTokens.of(options).analyzeStudentHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveStudentHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, ClassDailyHealthSurveyTokens.STUDENT_HEALTH_SURVEY_LIST);
		
 	}
 	
		

	

	protected ClassDailyHealthSurveyMapper getClassDailyHealthSurveyMapper(){
		return new ClassDailyHealthSurveyMapper();
	}

	
	
	protected ClassDailyHealthSurvey extractClassDailyHealthSurvey(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ClassDailyHealthSurvey classDailyHealthSurvey = loadSingleObject(accessKey, getClassDailyHealthSurveyMapper());
			return classDailyHealthSurvey;
		}catch(EmptyResultDataAccessException e){
			throw new ClassDailyHealthSurveyNotFoundException("ClassDailyHealthSurvey("+accessKey+") is not found!");
		}

	}

	
	

	protected ClassDailyHealthSurvey loadInternalClassDailyHealthSurvey(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ClassDailyHealthSurvey classDailyHealthSurvey = extractClassDailyHealthSurvey(accessKey, loadOptions);
 	
 		if(isExtractTeacherEnabled(loadOptions)){
	 		extractTeacher(classDailyHealthSurvey, loadOptions);
 		}
  	
 		if(isExtractCreatorEnabled(loadOptions)){
	 		extractCreator(classDailyHealthSurvey, loadOptions);
 		}
  	
 		if(isExtractChangeRequestEnabled(loadOptions)){
	 		extractChangeRequest(classDailyHealthSurvey, loadOptions);
 		}
 
		
		if(isExtractDailySurveyQuestionListEnabled(loadOptions)){
	 		extractDailySurveyQuestionList(classDailyHealthSurvey, loadOptions);
 		}	
 		if(isAnalyzeDailySurveyQuestionListEnabled(loadOptions)){
	 		analyzeDailySurveyQuestionList(classDailyHealthSurvey, loadOptions);
 		}
 		
		
		if(isExtractStudentHealthSurveyListEnabled(loadOptions)){
	 		extractStudentHealthSurveyList(classDailyHealthSurvey, loadOptions);
 		}	
 		if(isAnalyzeStudentHealthSurveyListEnabled(loadOptions)){
	 		analyzeStudentHealthSurveyList(classDailyHealthSurvey, loadOptions);
 		}
 		
		
		return classDailyHealthSurvey;
		
	}

	 

 	protected ClassDailyHealthSurvey extractTeacher(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options) throws Exception{

		if(classDailyHealthSurvey.getTeacher() == null){
			return classDailyHealthSurvey;
		}
		String teacherId = classDailyHealthSurvey.getTeacher().getId();
		if( teacherId == null){
			return classDailyHealthSurvey;
		}
		Teacher teacher = getTeacherDAO().load(teacherId,options);
		if(teacher != null){
			classDailyHealthSurvey.setTeacher(teacher);
		}
		
 		
 		return classDailyHealthSurvey;
 	}
 		
  

 	protected ClassDailyHealthSurvey extractCreator(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options) throws Exception{

		if(classDailyHealthSurvey.getCreator() == null){
			return classDailyHealthSurvey;
		}
		String creatorId = classDailyHealthSurvey.getCreator().getId();
		if( creatorId == null){
			return classDailyHealthSurvey;
		}
		User creator = getUserDAO().load(creatorId,options);
		if(creator != null){
			classDailyHealthSurvey.setCreator(creator);
		}
		
 		
 		return classDailyHealthSurvey;
 	}
 		
  

 	protected ClassDailyHealthSurvey extractChangeRequest(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options) throws Exception{

		if(classDailyHealthSurvey.getChangeRequest() == null){
			return classDailyHealthSurvey;
		}
		String changeRequestId = classDailyHealthSurvey.getChangeRequest().getId();
		if( changeRequestId == null){
			return classDailyHealthSurvey;
		}
		ChangeRequest changeRequest = getChangeRequestDAO().load(changeRequestId,options);
		if(changeRequest != null){
			classDailyHealthSurvey.setChangeRequest(changeRequest);
		}
		
 		
 		return classDailyHealthSurvey;
 	}
 		
 
		
	protected void enhanceDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ClassDailyHealthSurvey extractDailySurveyQuestionList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		
		if(classDailyHealthSurvey == null){
			return null;
		}
		if(classDailyHealthSurvey.getId() == null){
			return classDailyHealthSurvey;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = getDailySurveyQuestionDAO().findDailySurveyQuestionByClassDailyHealthSurvey(classDailyHealthSurvey.getId(),options);
		if(dailySurveyQuestionList != null){
			enhanceDailySurveyQuestionList(dailySurveyQuestionList,options);
			classDailyHealthSurvey.setDailySurveyQuestionList(dailySurveyQuestionList);
		}
		
		return classDailyHealthSurvey;
	
	}	
	
	protected ClassDailyHealthSurvey analyzeDailySurveyQuestionList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		
		if(classDailyHealthSurvey == null){
			return null;
		}
		if(classDailyHealthSurvey.getId() == null){
			return classDailyHealthSurvey;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();
		if(dailySurveyQuestionList != null){
			getDailySurveyQuestionDAO().analyzeDailySurveyQuestionByClassDailyHealthSurvey(dailySurveyQuestionList, classDailyHealthSurvey.getId(), options);
			
		}
		
		return classDailyHealthSurvey;
	
	}	
	
		
	protected void enhanceStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ClassDailyHealthSurvey extractStudentHealthSurveyList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		
		if(classDailyHealthSurvey == null){
			return null;
		}
		if(classDailyHealthSurvey.getId() == null){
			return classDailyHealthSurvey;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = getStudentHealthSurveyDAO().findStudentHealthSurveyByClassDailyHealthSurvey(classDailyHealthSurvey.getId(),options);
		if(studentHealthSurveyList != null){
			enhanceStudentHealthSurveyList(studentHealthSurveyList,options);
			classDailyHealthSurvey.setStudentHealthSurveyList(studentHealthSurveyList);
		}
		
		return classDailyHealthSurvey;
	
	}	
	
	protected ClassDailyHealthSurvey analyzeStudentHealthSurveyList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		
		if(classDailyHealthSurvey == null){
			return null;
		}
		if(classDailyHealthSurvey.getId() == null){
			return classDailyHealthSurvey;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();
		if(studentHealthSurveyList != null){
			getStudentHealthSurveyDAO().analyzeStudentHealthSurveyByClassDailyHealthSurvey(studentHealthSurveyList, classDailyHealthSurvey.getId(), options);
			
		}
		
		return classDailyHealthSurvey;
	
	}	
	
		
		
  	
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByTeacher(String teacherId,Map<String,Object> options){
 	
  		SmartList<ClassDailyHealthSurvey> resultList = queryWith(ClassDailyHealthSurveyTable.COLUMN_TEACHER, teacherId, options, getClassDailyHealthSurveyMapper());
		// analyzeClassDailyHealthSurveyByTeacher(resultList, teacherId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByTeacher(String teacherId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassDailyHealthSurvey> resultList =  queryWithRange(ClassDailyHealthSurveyTable.COLUMN_TEACHER, teacherId, options, getClassDailyHealthSurveyMapper(), start, count);
 		//analyzeClassDailyHealthSurveyByTeacher(resultList, teacherId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassDailyHealthSurveyByTeacher(SmartList<ClassDailyHealthSurvey> resultList, String teacherId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassDailyHealthSurvey.TEACHER_PROPERTY, teacherId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem surveyTimeStatsItem = new StatsItem();
		//ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY
		surveyTimeStatsItem.setDisplayName("每日健康调查");
		surveyTimeStatsItem.setInternalName(formatKeyForDateLine(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY));
		surveyTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(surveyTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassDailyHealthSurveyByTeacher(String teacherId,Map<String,Object> options){

 		return countWith(ClassDailyHealthSurveyTable.COLUMN_TEACHER, teacherId, options);
 	}
 	@Override
	public Map<String, Integer> countClassDailyHealthSurveyByTeacherIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassDailyHealthSurveyTable.COLUMN_TEACHER, ids, options);
	}
 	
  	
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByCreator(String userId,Map<String,Object> options){
 	
  		SmartList<ClassDailyHealthSurvey> resultList = queryWith(ClassDailyHealthSurveyTable.COLUMN_CREATOR, userId, options, getClassDailyHealthSurveyMapper());
		// analyzeClassDailyHealthSurveyByCreator(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByCreator(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassDailyHealthSurvey> resultList =  queryWithRange(ClassDailyHealthSurveyTable.COLUMN_CREATOR, userId, options, getClassDailyHealthSurveyMapper(), start, count);
 		//analyzeClassDailyHealthSurveyByCreator(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassDailyHealthSurveyByCreator(SmartList<ClassDailyHealthSurvey> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem surveyTimeStatsItem = new StatsItem();
		//ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY
		surveyTimeStatsItem.setDisplayName("每日健康调查");
		surveyTimeStatsItem.setInternalName(formatKeyForDateLine(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY));
		surveyTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(surveyTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassDailyHealthSurveyByCreator(String userId,Map<String,Object> options){

 		return countWith(ClassDailyHealthSurveyTable.COLUMN_CREATOR, userId, options);
 	}
 	@Override
	public Map<String, Integer> countClassDailyHealthSurveyByCreatorIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassDailyHealthSurveyTable.COLUMN_CREATOR, ids, options);
	}
 	
  	
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByChangeRequest(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<ClassDailyHealthSurvey> resultList = queryWith(ClassDailyHealthSurveyTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getClassDailyHealthSurveyMapper());
		// analyzeClassDailyHealthSurveyByChangeRequest(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByChangeRequest(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassDailyHealthSurvey> resultList =  queryWithRange(ClassDailyHealthSurveyTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getClassDailyHealthSurveyMapper(), start, count);
 		//analyzeClassDailyHealthSurveyByChangeRequest(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassDailyHealthSurveyByChangeRequest(SmartList<ClassDailyHealthSurvey> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassDailyHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem surveyTimeStatsItem = new StatsItem();
		//ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY
		surveyTimeStatsItem.setDisplayName("每日健康调查");
		surveyTimeStatsItem.setInternalName(formatKeyForDateLine(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY));
		surveyTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(surveyTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassDailyHealthSurveyByChangeRequest(String changeRequestId,Map<String,Object> options){

 		return countWith(ClassDailyHealthSurveyTable.COLUMN_CHANGE_REQUEST, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countClassDailyHealthSurveyByChangeRequestIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassDailyHealthSurveyTable.COLUMN_CHANGE_REQUEST, ids, options);
	}
 	
 	
		
		
		

	

	protected ClassDailyHealthSurvey saveClassDailyHealthSurvey(ClassDailyHealthSurvey  classDailyHealthSurvey){
		
		if(!classDailyHealthSurvey.isChanged()){
			return classDailyHealthSurvey;
		}
		
		
		String SQL=this.getSaveClassDailyHealthSurveySQL(classDailyHealthSurvey);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveClassDailyHealthSurveyParameters(classDailyHealthSurvey);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		classDailyHealthSurvey.incVersion();
		return classDailyHealthSurvey;
	
	}
	public SmartList<ClassDailyHealthSurvey> saveClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitClassDailyHealthSurveyList(classDailyHealthSurveyList);
		
		batchClassDailyHealthSurveyCreate((List<ClassDailyHealthSurvey>)lists[CREATE_LIST_INDEX]);
		
		batchClassDailyHealthSurveyUpdate((List<ClassDailyHealthSurvey>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList){
			if(classDailyHealthSurvey.isChanged()){
				classDailyHealthSurvey.incVersion();
			}
			
		
		}
		
		
		return classDailyHealthSurveyList;
	}

	public SmartList<ClassDailyHealthSurvey> removeClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		
		
		super.removeList(classDailyHealthSurveyList, options);
		
		return classDailyHealthSurveyList;
		
		
	}
	
	protected List<Object[]> prepareClassDailyHealthSurveyBatchCreateArgs(List<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList ){
			Object [] parameters = prepareClassDailyHealthSurveyCreateParameters(classDailyHealthSurvey);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareClassDailyHealthSurveyBatchUpdateArgs(List<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList ){
			if(!classDailyHealthSurvey.isChanged()){
				continue;
			}
			Object [] parameters = prepareClassDailyHealthSurveyUpdateParameters(classDailyHealthSurvey);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchClassDailyHealthSurveyCreate(List<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareClassDailyHealthSurveyBatchCreateArgs(classDailyHealthSurveyList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchClassDailyHealthSurveyUpdate(List<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareClassDailyHealthSurveyBatchUpdateArgs(classDailyHealthSurveyList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitClassDailyHealthSurveyList(List<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		
		List<ClassDailyHealthSurvey> classDailyHealthSurveyCreateList=new ArrayList<ClassDailyHealthSurvey>();
		List<ClassDailyHealthSurvey> classDailyHealthSurveyUpdateList=new ArrayList<ClassDailyHealthSurvey>();
		
		for(ClassDailyHealthSurvey classDailyHealthSurvey: classDailyHealthSurveyList){
			if(isUpdateRequest(classDailyHealthSurvey)){
				classDailyHealthSurveyUpdateList.add( classDailyHealthSurvey);
				continue;
			}
			classDailyHealthSurveyCreateList.add(classDailyHealthSurvey);
		}
		
		return new Object[]{classDailyHealthSurveyCreateList,classDailyHealthSurveyUpdateList};
	}
	
	protected boolean isUpdateRequest(ClassDailyHealthSurvey classDailyHealthSurvey){
 		return classDailyHealthSurvey.getVersion() > 0;
 	}
 	protected String getSaveClassDailyHealthSurveySQL(ClassDailyHealthSurvey classDailyHealthSurvey){
 		if(isUpdateRequest(classDailyHealthSurvey)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveClassDailyHealthSurveyParameters(ClassDailyHealthSurvey classDailyHealthSurvey){
 		if(isUpdateRequest(classDailyHealthSurvey) ){
 			return prepareClassDailyHealthSurveyUpdateParameters(classDailyHealthSurvey);
 		}
 		return prepareClassDailyHealthSurveyCreateParameters(classDailyHealthSurvey);
 	}
 	protected Object[] prepareClassDailyHealthSurveyUpdateParameters(ClassDailyHealthSurvey classDailyHealthSurvey){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = classDailyHealthSurvey.getName(); 	
 		if(classDailyHealthSurvey.getTeacher() != null){
 			parameters[1] = classDailyHealthSurvey.getTeacher().getId();
 		}
 
 		parameters[2] = classDailyHealthSurvey.getSurveyTime(); 	
 		if(classDailyHealthSurvey.getCreator() != null){
 			parameters[3] = classDailyHealthSurvey.getCreator().getId();
 		}
  	
 		if(classDailyHealthSurvey.getChangeRequest() != null){
 			parameters[4] = classDailyHealthSurvey.getChangeRequest().getId();
 		}
 		
 		parameters[5] = classDailyHealthSurvey.nextVersion();
 		parameters[6] = classDailyHealthSurvey.getId();
 		parameters[7] = classDailyHealthSurvey.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareClassDailyHealthSurveyCreateParameters(ClassDailyHealthSurvey classDailyHealthSurvey){
		Object[] parameters = new Object[6];
		String newClassDailyHealthSurveyId=getNextId();
		classDailyHealthSurvey.setId(newClassDailyHealthSurveyId);
		parameters[0] =  classDailyHealthSurvey.getId();
 
 		parameters[1] = classDailyHealthSurvey.getName(); 	
 		if(classDailyHealthSurvey.getTeacher() != null){
 			parameters[2] = classDailyHealthSurvey.getTeacher().getId();
 		
 		}
 		
 		parameters[3] = classDailyHealthSurvey.getSurveyTime(); 	
 		if(classDailyHealthSurvey.getCreator() != null){
 			parameters[4] = classDailyHealthSurvey.getCreator().getId();
 		
 		}
 		 	
 		if(classDailyHealthSurvey.getChangeRequest() != null){
 			parameters[5] = classDailyHealthSurvey.getChangeRequest().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ClassDailyHealthSurvey saveInternalClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		saveClassDailyHealthSurvey(classDailyHealthSurvey);
 	
 		if(isSaveTeacherEnabled(options)){
	 		saveTeacher(classDailyHealthSurvey, options);
 		}
  	
 		if(isSaveCreatorEnabled(options)){
	 		saveCreator(classDailyHealthSurvey, options);
 		}
  	
 		if(isSaveChangeRequestEnabled(options)){
	 		saveChangeRequest(classDailyHealthSurvey, options);
 		}
 
		
		if(isSaveDailySurveyQuestionListEnabled(options)){
	 		saveDailySurveyQuestionList(classDailyHealthSurvey, options);
	 		//removeDailySurveyQuestionList(classDailyHealthSurvey, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentHealthSurveyListEnabled(options)){
	 		saveStudentHealthSurveyList(classDailyHealthSurvey, options);
	 		//removeStudentHealthSurveyList(classDailyHealthSurvey, options);
	 		//Not delete the record
	 		
 		}		
		
		return classDailyHealthSurvey;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ClassDailyHealthSurvey saveTeacher(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classDailyHealthSurvey.getTeacher() == null){
 			return classDailyHealthSurvey;//do nothing when it is null
 		}
 		
 		getTeacherDAO().save(classDailyHealthSurvey.getTeacher(),options);
 		return classDailyHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ClassDailyHealthSurvey saveCreator(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classDailyHealthSurvey.getCreator() == null){
 			return classDailyHealthSurvey;//do nothing when it is null
 		}
 		
 		getUserDAO().save(classDailyHealthSurvey.getCreator(),options);
 		return classDailyHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ClassDailyHealthSurvey saveChangeRequest(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classDailyHealthSurvey.getChangeRequest() == null){
 			return classDailyHealthSurvey;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(classDailyHealthSurvey.getChangeRequest(),options);
 		return classDailyHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ClassDailyHealthSurvey planToRemoveDailySurveyQuestionList(ClassDailyHealthSurvey classDailyHealthSurvey, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(DailySurveyQuestion.ID_PROPERTY, dailySurveyQuestionIds);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return classDailyHealthSurvey;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){

			dailySurveyQuestionItem.clearFromAll();
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return classDailyHealthSurvey;	
	
	}


	//disconnect ClassDailyHealthSurvey with question_type in DailySurveyQuestion
	public ClassDailyHealthSurvey planToRemoveDailySurveyQuestionListWithQuestionType(ClassDailyHealthSurvey classDailyHealthSurvey, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return classDailyHealthSurvey;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearQuestionType();
			dailySurveyQuestionItem.clearClassDailyHealthSurvey();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return classDailyHealthSurvey;
	}
	
	public int countDailySurveyQuestionListWithQuestionType(String classDailyHealthSurveyId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect ClassDailyHealthSurvey with survey_question in DailySurveyQuestion
	public ClassDailyHealthSurvey planToRemoveDailySurveyQuestionListWithSurveyQuestion(ClassDailyHealthSurvey classDailyHealthSurvey, String surveyQuestionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, surveyQuestionId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return classDailyHealthSurvey;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearSurveyQuestion();
			dailySurveyQuestionItem.clearClassDailyHealthSurvey();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return classDailyHealthSurvey;
	}
	
	public int countDailySurveyQuestionListWithSurveyQuestion(String classDailyHealthSurveyId, String surveyQuestionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, surveyQuestionId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyList(ClassDailyHealthSurvey classDailyHealthSurvey, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(StudentHealthSurvey.ID_PROPERTY, studentHealthSurveyIds);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){

			studentHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return classDailyHealthSurvey;	
	
	}


	//disconnect ClassDailyHealthSurvey with student in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithStudent(ClassDailyHealthSurvey classDailyHealthSurvey, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearStudent();
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return classDailyHealthSurvey;
	}
	
	public int countStudentHealthSurveyListWithStudent(String classDailyHealthSurveyId, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ClassDailyHealthSurvey with survey_status in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithSurveyStatus(ClassDailyHealthSurvey classDailyHealthSurvey, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSurveyStatus();
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return classDailyHealthSurvey;
	}
	
	public int countStudentHealthSurveyListWithSurveyStatus(String classDailyHealthSurveyId, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ClassDailyHealthSurvey with teacher in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithTeacher(ClassDailyHealthSurvey classDailyHealthSurvey, String teacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearTeacher();
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return classDailyHealthSurvey;
	}
	
	public int countStudentHealthSurveyListWithTeacher(String classDailyHealthSurveyId, String teacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ClassDailyHealthSurvey with change_request in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithChangeRequest(ClassDailyHealthSurvey classDailyHealthSurvey, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurvey.getId());
		key.put(StudentHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return classDailyHealthSurvey;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearChangeRequest();
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return classDailyHealthSurvey;
	}
	
	public int countStudentHealthSurveyListWithChangeRequest(String classDailyHealthSurveyId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		key.put(StudentHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	

		
	protected ClassDailyHealthSurvey saveDailySurveyQuestionList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			//null list means nothing
			return classDailyHealthSurvey;
		}
		SmartList<DailySurveyQuestion> mergedUpdateDailySurveyQuestionList = new SmartList<DailySurveyQuestion>();
		
		
		mergedUpdateDailySurveyQuestionList.addAll(dailySurveyQuestionList); 
		if(dailySurveyQuestionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateDailySurveyQuestionList.addAll(dailySurveyQuestionList.getToRemoveList());
			dailySurveyQuestionList.removeAll(dailySurveyQuestionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getDailySurveyQuestionDAO().saveDailySurveyQuestionList(mergedUpdateDailySurveyQuestionList,options);
		
		if(dailySurveyQuestionList.getToRemoveList() != null){
			dailySurveyQuestionList.removeAll(dailySurveyQuestionList.getToRemoveList());
		}
		
		
		return classDailyHealthSurvey;
	
	}
	
	protected ClassDailyHealthSurvey removeDailySurveyQuestionList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
	
	
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			return classDailyHealthSurvey;
		}	
	
		SmartList<DailySurveyQuestion> toRemoveDailySurveyQuestionList = dailySurveyQuestionList.getToRemoveList();
		
		if(toRemoveDailySurveyQuestionList == null){
			return classDailyHealthSurvey;
		}
		if(toRemoveDailySurveyQuestionList.isEmpty()){
			return classDailyHealthSurvey;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getDailySurveyQuestionDAO().removeDailySurveyQuestionList(toRemoveDailySurveyQuestionList,options);
		
		return classDailyHealthSurvey;
	
	}
	
	

 	
 	
	
	
	
		
	protected ClassDailyHealthSurvey saveStudentHealthSurveyList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
		
		
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			//null list means nothing
			return classDailyHealthSurvey;
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
		
		
		return classDailyHealthSurvey;
	
	}
	
	protected ClassDailyHealthSurvey removeStudentHealthSurveyList(ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options){
	
	
		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}	
	
		SmartList<StudentHealthSurvey> toRemoveStudentHealthSurveyList = studentHealthSurveyList.getToRemoveList();
		
		if(toRemoveStudentHealthSurveyList == null){
			return classDailyHealthSurvey;
		}
		if(toRemoveStudentHealthSurveyList.isEmpty()){
			return classDailyHealthSurvey;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentHealthSurveyDAO().removeStudentHealthSurveyList(toRemoveStudentHealthSurveyList,options);
		
		return classDailyHealthSurvey;
	
	}
	
	

 	
 	
	
	
	
		

	public ClassDailyHealthSurvey present(ClassDailyHealthSurvey classDailyHealthSurvey,Map<String, Object> options){
	
		presentDailySurveyQuestionList(classDailyHealthSurvey,options);
		presentStudentHealthSurveyList(classDailyHealthSurvey,options);

		return classDailyHealthSurvey;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ClassDailyHealthSurvey presentDailySurveyQuestionList(
			ClassDailyHealthSurvey classDailyHealthSurvey,
			Map<String, Object> options) {

		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classDailyHealthSurvey.getDailySurveyQuestionList();		
				SmartList<DailySurveyQuestion> newList= presentSubList(classDailyHealthSurvey.getId(),
				dailySurveyQuestionList,
				options,
				getDailySurveyQuestionDAO()::countDailySurveyQuestionByClassDailyHealthSurvey,
				getDailySurveyQuestionDAO()::findDailySurveyQuestionByClassDailyHealthSurvey
				);

		
		classDailyHealthSurvey.setDailySurveyQuestionList(newList);
		

		return classDailyHealthSurvey;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ClassDailyHealthSurvey presentStudentHealthSurveyList(
			ClassDailyHealthSurvey classDailyHealthSurvey,
			Map<String, Object> options) {

		SmartList<StudentHealthSurvey> studentHealthSurveyList = classDailyHealthSurvey.getStudentHealthSurveyList();		
				SmartList<StudentHealthSurvey> newList= presentSubList(classDailyHealthSurvey.getId(),
				studentHealthSurveyList,
				options,
				getStudentHealthSurveyDAO()::countStudentHealthSurveyByClassDailyHealthSurvey,
				getStudentHealthSurveyDAO()::findStudentHealthSurveyByClassDailyHealthSurvey
				);

		
		classDailyHealthSurvey.setStudentHealthSurveyList(newList);
		

		return classDailyHealthSurvey;
	}			
		

	
    public SmartList<ClassDailyHealthSurvey> requestCandidateClassDailyHealthSurveyForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ClassDailyHealthSurveyTable.COLUMN_NAME, filterKey, pageNo, pageSize, getClassDailyHealthSurveyMapper());
    }
		
    public SmartList<ClassDailyHealthSurvey> requestCandidateClassDailyHealthSurveyForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ClassDailyHealthSurveyTable.COLUMN_NAME, filterKey, pageNo, pageSize, getClassDailyHealthSurveyMapper());
    }
		

	protected String getTableName(){
		return ClassDailyHealthSurveyTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ClassDailyHealthSurvey> classDailyHealthSurveyList) {		
		this.enhanceListInternal(classDailyHealthSurveyList, this.getClassDailyHealthSurveyMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的classDailyHealthSurvey的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<ClassDailyHealthSurvey> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<DailySurveyQuestion> loadedObjs = userContext.getDAOGroup().getDailySurveyQuestionDAO().findDailySurveyQuestionWithKey(key, options);
		Map<String, List<DailySurveyQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getClassDailyHealthSurvey().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<DailySurveyQuestion> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<DailySurveyQuestion> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setDailySurveyQuestionList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的classDailyHealthSurvey的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<ClassDailyHealthSurvey> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentHealthSurvey> loadedObjs = userContext.getDAOGroup().getStudentHealthSurveyDAO().findStudentHealthSurveyWithKey(key, options);
		Map<String, List<StudentHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getClassDailyHealthSurvey().getId()));
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
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<ClassDailyHealthSurvey> classDailyHealthSurveyList = ownerEntity.collectRefsWithType(ClassDailyHealthSurvey.INTERNAL_TYPE);
		this.enhanceList(classDailyHealthSurveyList);
		
	}
	
	@Override
	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getClassDailyHealthSurveyMapper());

	}
	@Override
	public int countClassDailyHealthSurveyWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countClassDailyHealthSurveyWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ClassDailyHealthSurvey> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getClassDailyHealthSurveyMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


