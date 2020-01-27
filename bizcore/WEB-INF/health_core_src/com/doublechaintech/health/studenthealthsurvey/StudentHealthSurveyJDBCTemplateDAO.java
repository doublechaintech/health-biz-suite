
package com.doublechaintech.health.studenthealthsurvey;

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
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;

import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.surveystatus.SurveyStatusDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class StudentHealthSurveyJDBCTemplateDAO extends HealthBaseDAOImpl implements StudentHealthSurveyDAO{
 
 	
 	private  SurveyStatusDAO  surveyStatusDAO;
 	public void setSurveyStatusDAO(SurveyStatusDAO surveyStatusDAO){
	 	this.surveyStatusDAO = surveyStatusDAO;
 	}
 	public SurveyStatusDAO getSurveyStatusDAO(){
	 	return this.surveyStatusDAO;
 	}
 
 	
 	private  ClassDailyHealthSurveyDAO  classDailyHealthSurveyDAO;
 	public void setClassDailyHealthSurveyDAO(ClassDailyHealthSurveyDAO classDailyHealthSurveyDAO){
	 	this.classDailyHealthSurveyDAO = classDailyHealthSurveyDAO;
 	}
 	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO(){
	 	return this.classDailyHealthSurveyDAO;
 	}
 
 	
 	private  SchoolClassDAO  schoolClassDAO;
 	public void setSchoolClassDAO(SchoolClassDAO schoolClassDAO){
	 	this.schoolClassDAO = schoolClassDAO;
 	}
 	public SchoolClassDAO getSchoolClassDAO(){
	 	return this.schoolClassDAO;
 	}
 
 	
 	private  StudentDAO  studentDAO;
 	public void setStudentDAO(StudentDAO studentDAO){
	 	this.studentDAO = studentDAO;
 	}
 	public StudentDAO getStudentDAO(){
	 	return this.studentDAO;
 	}
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
 	}


			
		
	
  	private  StudentDailyAnswerDAO  studentDailyAnswerDAO;
 	public void setStudentDailyAnswerDAO(StudentDailyAnswerDAO pStudentDailyAnswerDAO){
 	
 		if(pStudentDailyAnswerDAO == null){
 			throw new IllegalStateException("Do not try to set studentDailyAnswerDAO to null.");
 		}
	 	this.studentDailyAnswerDAO = pStudentDailyAnswerDAO;
 	}
 	public StudentDailyAnswerDAO getStudentDailyAnswerDAO(){
 		if(this.studentDailyAnswerDAO == null){
 			throw new IllegalStateException("The studentDailyAnswerDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentDailyAnswerDAO;
 	}	
 	
			
		

	
	/*
	protected StudentHealthSurvey load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalStudentHealthSurvey(accessKey, options);
	}
	*/
	
	public SmartList<StudentHealthSurvey> loadAll() {
	    return this.loadAll(getStudentHealthSurveyMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public StudentHealthSurvey load(String id,Map<String,Object> options) throws Exception{
		return loadInternalStudentHealthSurvey(StudentHealthSurveyTable.withId(id), options);
	}
	
	
	
	public StudentHealthSurvey save(StudentHealthSurvey studentHealthSurvey,Map<String,Object> options){
		
		String methodName="save(StudentHealthSurvey studentHealthSurvey,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(studentHealthSurvey, methodName, "studentHealthSurvey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalStudentHealthSurvey(studentHealthSurvey,options);
	}
	public StudentHealthSurvey clone(String studentHealthSurveyId, Map<String,Object> options) throws Exception{
	
		return clone(StudentHealthSurveyTable.withId(studentHealthSurveyId),options);
	}
	
	protected StudentHealthSurvey clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String studentHealthSurveyId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		StudentHealthSurvey newStudentHealthSurvey = loadInternalStudentHealthSurvey(accessKey, options);
		newStudentHealthSurvey.setVersion(0);
		
		
 		
 		if(isSaveStudentDailyAnswerListEnabled(options)){
 			for(StudentDailyAnswer item: newStudentHealthSurvey.getStudentDailyAnswerList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalStudentHealthSurvey(newStudentHealthSurvey,options);
		
		return newStudentHealthSurvey;
	}
	
	
	
	

	protected void throwIfHasException(String studentHealthSurveyId,int version,int count) throws Exception{
		if (count == 1) {
			throw new StudentHealthSurveyVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new StudentHealthSurveyNotFoundException(
					"The " + this.getTableName() + "(" + studentHealthSurveyId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String studentHealthSurveyId, int version) throws Exception{
	
		String methodName="delete(String studentHealthSurveyId, int version)";
		assertMethodArgumentNotNull(studentHealthSurveyId, methodName, "studentHealthSurveyId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{studentHealthSurveyId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(studentHealthSurveyId,version);
		}
		
	
	}
	
	
	
	
	

	public StudentHealthSurvey disconnectFromAll(String studentHealthSurveyId, int version) throws Exception{
	
		
		StudentHealthSurvey studentHealthSurvey = loadInternalStudentHealthSurvey(StudentHealthSurveyTable.withId(studentHealthSurveyId), emptyOptions());
		studentHealthSurvey.clearFromAll();
		this.saveStudentHealthSurvey(studentHealthSurvey);
		return studentHealthSurvey;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return StudentHealthSurveyTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "student_health_survey";
	}
	@Override
	protected String getBeanName() {
		
		return "studentHealthSurvey";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return StudentHealthSurveyTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractStudentEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentHealthSurveyTokens.STUDENT);
 	}

 	protected boolean isSaveStudentEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentHealthSurveyTokens.STUDENT);
 	}
 	

 	
  

 	protected boolean isExtractSurveyStatusEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentHealthSurveyTokens.SURVEYSTATUS);
 	}

 	protected boolean isSaveSurveyStatusEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentHealthSurveyTokens.SURVEYSTATUS);
 	}
 	

 	
  

 	protected boolean isExtractSchoolClassEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentHealthSurveyTokens.SCHOOLCLASS);
 	}

 	protected boolean isSaveSchoolClassEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentHealthSurveyTokens.SCHOOLCLASS);
 	}
 	

 	
  

 	protected boolean isExtractClassDailyHealthSurveyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentHealthSurveyTokens.CLASSDAILYHEALTHSURVEY);
 	}

 	protected boolean isSaveClassDailyHealthSurveyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentHealthSurveyTokens.CLASSDAILYHEALTHSURVEY);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentHealthSurveyTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentHealthSurveyTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentDailyAnswerListEnabled(Map<String,Object> options){		
 		return checkOptions(options,StudentHealthSurveyTokens.STUDENT_DAILY_ANSWER_LIST);
 	}
 	protected boolean isAnalyzeStudentDailyAnswerListEnabled(Map<String,Object> options){		 		
 		return StudentHealthSurveyTokens.of(options).analyzeStudentDailyAnswerListEnabled();
 	}
	
	protected boolean isSaveStudentDailyAnswerListEnabled(Map<String,Object> options){
		return checkOptions(options, StudentHealthSurveyTokens.STUDENT_DAILY_ANSWER_LIST);
		
 	}
 	
		

	

	protected StudentHealthSurveyMapper getStudentHealthSurveyMapper(){
		return new StudentHealthSurveyMapper();
	}

	
	
	protected StudentHealthSurvey extractStudentHealthSurvey(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			StudentHealthSurvey studentHealthSurvey = loadSingleObject(accessKey, getStudentHealthSurveyMapper());
			return studentHealthSurvey;
		}catch(EmptyResultDataAccessException e){
			throw new StudentHealthSurveyNotFoundException("StudentHealthSurvey("+accessKey+") is not found!");
		}

	}

	
	

	protected StudentHealthSurvey loadInternalStudentHealthSurvey(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		StudentHealthSurvey studentHealthSurvey = extractStudentHealthSurvey(accessKey, loadOptions);
 	
 		if(isExtractStudentEnabled(loadOptions)){
	 		extractStudent(studentHealthSurvey, loadOptions);
 		}
  	
 		if(isExtractSurveyStatusEnabled(loadOptions)){
	 		extractSurveyStatus(studentHealthSurvey, loadOptions);
 		}
  	
 		if(isExtractSchoolClassEnabled(loadOptions)){
	 		extractSchoolClass(studentHealthSurvey, loadOptions);
 		}
  	
 		if(isExtractClassDailyHealthSurveyEnabled(loadOptions)){
	 		extractClassDailyHealthSurvey(studentHealthSurvey, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(studentHealthSurvey, loadOptions);
 		}
 
		
		if(isExtractStudentDailyAnswerListEnabled(loadOptions)){
	 		extractStudentDailyAnswerList(studentHealthSurvey, loadOptions);
 		}	
 		if(isAnalyzeStudentDailyAnswerListEnabled(loadOptions)){
	 		analyzeStudentDailyAnswerList(studentHealthSurvey, loadOptions);
 		}
 		
		
		return studentHealthSurvey;
		
	}

	 

 	protected StudentHealthSurvey extractStudent(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options) throws Exception{

		if(studentHealthSurvey.getStudent() == null){
			return studentHealthSurvey;
		}
		String studentId = studentHealthSurvey.getStudent().getId();
		if( studentId == null){
			return studentHealthSurvey;
		}
		Student student = getStudentDAO().load(studentId,options);
		if(student != null){
			studentHealthSurvey.setStudent(student);
		}
		
 		
 		return studentHealthSurvey;
 	}
 		
  

 	protected StudentHealthSurvey extractSurveyStatus(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options) throws Exception{

		if(studentHealthSurvey.getSurveyStatus() == null){
			return studentHealthSurvey;
		}
		String surveyStatusId = studentHealthSurvey.getSurveyStatus().getId();
		if( surveyStatusId == null){
			return studentHealthSurvey;
		}
		SurveyStatus surveyStatus = getSurveyStatusDAO().load(surveyStatusId,options);
		if(surveyStatus != null){
			studentHealthSurvey.setSurveyStatus(surveyStatus);
		}
		
 		
 		return studentHealthSurvey;
 	}
 		
  

 	protected StudentHealthSurvey extractSchoolClass(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options) throws Exception{

		if(studentHealthSurvey.getSchoolClass() == null){
			return studentHealthSurvey;
		}
		String schoolClassId = studentHealthSurvey.getSchoolClass().getId();
		if( schoolClassId == null){
			return studentHealthSurvey;
		}
		SchoolClass schoolClass = getSchoolClassDAO().load(schoolClassId,options);
		if(schoolClass != null){
			studentHealthSurvey.setSchoolClass(schoolClass);
		}
		
 		
 		return studentHealthSurvey;
 	}
 		
  

 	protected StudentHealthSurvey extractClassDailyHealthSurvey(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options) throws Exception{

		if(studentHealthSurvey.getClassDailyHealthSurvey() == null){
			return studentHealthSurvey;
		}
		String classDailyHealthSurveyId = studentHealthSurvey.getClassDailyHealthSurvey().getId();
		if( classDailyHealthSurveyId == null){
			return studentHealthSurvey;
		}
		ClassDailyHealthSurvey classDailyHealthSurvey = getClassDailyHealthSurveyDAO().load(classDailyHealthSurveyId,options);
		if(classDailyHealthSurvey != null){
			studentHealthSurvey.setClassDailyHealthSurvey(classDailyHealthSurvey);
		}
		
 		
 		return studentHealthSurvey;
 	}
 		
  

 	protected StudentHealthSurvey extractCq(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options) throws Exception{

		if(studentHealthSurvey.getCq() == null){
			return studentHealthSurvey;
		}
		String cqId = studentHealthSurvey.getCq().getId();
		if( cqId == null){
			return studentHealthSurvey;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			studentHealthSurvey.setCq(cq);
		}
		
 		
 		return studentHealthSurvey;
 	}
 		
 
		
	protected void enhanceStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected StudentHealthSurvey extractStudentDailyAnswerList(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
		
		
		if(studentHealthSurvey == null){
			return null;
		}
		if(studentHealthSurvey.getId() == null){
			return studentHealthSurvey;
		}

		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = getStudentDailyAnswerDAO().findStudentDailyAnswerByStudentHealthSurvey(studentHealthSurvey.getId(),options);
		if(studentDailyAnswerList != null){
			enhanceStudentDailyAnswerList(studentDailyAnswerList,options);
			studentHealthSurvey.setStudentDailyAnswerList(studentDailyAnswerList);
		}
		
		return studentHealthSurvey;
	
	}	
	
	protected StudentHealthSurvey analyzeStudentDailyAnswerList(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
		
		
		if(studentHealthSurvey == null){
			return null;
		}
		if(studentHealthSurvey.getId() == null){
			return studentHealthSurvey;
		}

		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();
		if(studentDailyAnswerList != null){
			getStudentDailyAnswerDAO().analyzeStudentDailyAnswerByStudentHealthSurvey(studentDailyAnswerList, studentHealthSurvey.getId(), options);
			
		}
		
		return studentHealthSurvey;
	
	}	
	
		
		
  	
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByStudent(String studentId,Map<String,Object> options){
 	
  		SmartList<StudentHealthSurvey> resultList = queryWith(StudentHealthSurveyTable.COLUMN_STUDENT, studentId, options, getStudentHealthSurveyMapper());
		// analyzeStudentHealthSurveyByStudent(resultList, studentId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByStudent(String studentId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentHealthSurvey> resultList =  queryWithRange(StudentHealthSurveyTable.COLUMN_STUDENT, studentId, options, getStudentHealthSurveyMapper(), start, count);
 		//analyzeStudentHealthSurveyByStudent(resultList, studentId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentHealthSurveyByStudent(SmartList<StudentHealthSurvey> resultList, String studentId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem answerTimeStatsItem = new StatsItem();
		//StudentHealthSurvey.ANSWER_TIME_PROPERTY
		answerTimeStatsItem.setDisplayName("学生健康调查");
		answerTimeStatsItem.setInternalName(formatKeyForDateLine(StudentHealthSurvey.ANSWER_TIME_PROPERTY));
		answerTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentHealthSurvey.ANSWER_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(answerTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentHealthSurveyByStudent(String studentId,Map<String,Object> options){

 		return countWith(StudentHealthSurveyTable.COLUMN_STUDENT, studentId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentHealthSurveyByStudentIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentHealthSurveyTable.COLUMN_STUDENT, ids, options);
	}
 	
  	
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySurveyStatus(String surveyStatusId,Map<String,Object> options){
 	
  		SmartList<StudentHealthSurvey> resultList = queryWith(StudentHealthSurveyTable.COLUMN_SURVEY_STATUS, surveyStatusId, options, getStudentHealthSurveyMapper());
		// analyzeStudentHealthSurveyBySurveyStatus(resultList, surveyStatusId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySurveyStatus(String surveyStatusId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentHealthSurvey> resultList =  queryWithRange(StudentHealthSurveyTable.COLUMN_SURVEY_STATUS, surveyStatusId, options, getStudentHealthSurveyMapper(), start, count);
 		//analyzeStudentHealthSurveyBySurveyStatus(resultList, surveyStatusId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentHealthSurveyBySurveyStatus(SmartList<StudentHealthSurvey> resultList, String surveyStatusId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem answerTimeStatsItem = new StatsItem();
		//StudentHealthSurvey.ANSWER_TIME_PROPERTY
		answerTimeStatsItem.setDisplayName("学生健康调查");
		answerTimeStatsItem.setInternalName(formatKeyForDateLine(StudentHealthSurvey.ANSWER_TIME_PROPERTY));
		answerTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentHealthSurvey.ANSWER_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(answerTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentHealthSurveyBySurveyStatus(String surveyStatusId,Map<String,Object> options){

 		return countWith(StudentHealthSurveyTable.COLUMN_SURVEY_STATUS, surveyStatusId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentHealthSurveyBySurveyStatusIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentHealthSurveyTable.COLUMN_SURVEY_STATUS, ids, options);
	}
 	
  	
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySchoolClass(String schoolClassId,Map<String,Object> options){
 	
  		SmartList<StudentHealthSurvey> resultList = queryWith(StudentHealthSurveyTable.COLUMN_SCHOOL_CLASS, schoolClassId, options, getStudentHealthSurveyMapper());
		// analyzeStudentHealthSurveyBySchoolClass(resultList, schoolClassId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySchoolClass(String schoolClassId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentHealthSurvey> resultList =  queryWithRange(StudentHealthSurveyTable.COLUMN_SCHOOL_CLASS, schoolClassId, options, getStudentHealthSurveyMapper(), start, count);
 		//analyzeStudentHealthSurveyBySchoolClass(resultList, schoolClassId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentHealthSurveyBySchoolClass(SmartList<StudentHealthSurvey> resultList, String schoolClassId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem answerTimeStatsItem = new StatsItem();
		//StudentHealthSurvey.ANSWER_TIME_PROPERTY
		answerTimeStatsItem.setDisplayName("学生健康调查");
		answerTimeStatsItem.setInternalName(formatKeyForDateLine(StudentHealthSurvey.ANSWER_TIME_PROPERTY));
		answerTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentHealthSurvey.ANSWER_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(answerTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentHealthSurveyBySchoolClass(String schoolClassId,Map<String,Object> options){

 		return countWith(StudentHealthSurveyTable.COLUMN_SCHOOL_CLASS, schoolClassId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentHealthSurveyBySchoolClassIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentHealthSurveyTable.COLUMN_SCHOOL_CLASS, ids, options);
	}
 	
  	
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByClassDailyHealthSurvey(String classDailyHealthSurveyId,Map<String,Object> options){
 	
  		SmartList<StudentHealthSurvey> resultList = queryWith(StudentHealthSurveyTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, classDailyHealthSurveyId, options, getStudentHealthSurveyMapper());
		// analyzeStudentHealthSurveyByClassDailyHealthSurvey(resultList, classDailyHealthSurveyId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByClassDailyHealthSurvey(String classDailyHealthSurveyId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentHealthSurvey> resultList =  queryWithRange(StudentHealthSurveyTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, classDailyHealthSurveyId, options, getStudentHealthSurveyMapper(), start, count);
 		//analyzeStudentHealthSurveyByClassDailyHealthSurvey(resultList, classDailyHealthSurveyId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentHealthSurveyByClassDailyHealthSurvey(SmartList<StudentHealthSurvey> resultList, String classDailyHealthSurveyId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem answerTimeStatsItem = new StatsItem();
		//StudentHealthSurvey.ANSWER_TIME_PROPERTY
		answerTimeStatsItem.setDisplayName("学生健康调查");
		answerTimeStatsItem.setInternalName(formatKeyForDateLine(StudentHealthSurvey.ANSWER_TIME_PROPERTY));
		answerTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentHealthSurvey.ANSWER_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(answerTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentHealthSurveyByClassDailyHealthSurvey(String classDailyHealthSurveyId,Map<String,Object> options){

 		return countWith(StudentHealthSurveyTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, classDailyHealthSurveyId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentHealthSurveyByClassDailyHealthSurveyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentHealthSurveyTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, ids, options);
	}
 	
  	
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<StudentHealthSurvey> resultList = queryWith(StudentHealthSurveyTable.COLUMN_CQ, changeRequestId, options, getStudentHealthSurveyMapper());
		// analyzeStudentHealthSurveyByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentHealthSurvey> resultList =  queryWithRange(StudentHealthSurveyTable.COLUMN_CQ, changeRequestId, options, getStudentHealthSurveyMapper(), start, count);
 		//analyzeStudentHealthSurveyByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentHealthSurveyByCq(SmartList<StudentHealthSurvey> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentHealthSurvey.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem answerTimeStatsItem = new StatsItem();
		//StudentHealthSurvey.ANSWER_TIME_PROPERTY
		answerTimeStatsItem.setDisplayName("学生健康调查");
		answerTimeStatsItem.setInternalName(formatKeyForDateLine(StudentHealthSurvey.ANSWER_TIME_PROPERTY));
		answerTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentHealthSurvey.ANSWER_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(answerTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentHealthSurveyByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(StudentHealthSurveyTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentHealthSurveyByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentHealthSurveyTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected StudentHealthSurvey saveStudentHealthSurvey(StudentHealthSurvey  studentHealthSurvey){
		
		if(!studentHealthSurvey.isChanged()){
			return studentHealthSurvey;
		}
		
		
		String SQL=this.getSaveStudentHealthSurveySQL(studentHealthSurvey);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveStudentHealthSurveyParameters(studentHealthSurvey);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		studentHealthSurvey.incVersion();
		return studentHealthSurvey;
	
	}
	public SmartList<StudentHealthSurvey> saveStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitStudentHealthSurveyList(studentHealthSurveyList);
		
		batchStudentHealthSurveyCreate((List<StudentHealthSurvey>)lists[CREATE_LIST_INDEX]);
		
		batchStudentHealthSurveyUpdate((List<StudentHealthSurvey>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			if(studentHealthSurvey.isChanged()){
				studentHealthSurvey.incVersion();
			}
			
		
		}
		
		
		return studentHealthSurveyList;
	}

	public SmartList<StudentHealthSurvey> removeStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		
		
		super.removeList(studentHealthSurveyList, options);
		
		return studentHealthSurveyList;
		
		
	}
	
	protected List<Object[]> prepareStudentHealthSurveyBatchCreateArgs(List<StudentHealthSurvey> studentHealthSurveyList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList ){
			Object [] parameters = prepareStudentHealthSurveyCreateParameters(studentHealthSurvey);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareStudentHealthSurveyBatchUpdateArgs(List<StudentHealthSurvey> studentHealthSurveyList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList ){
			if(!studentHealthSurvey.isChanged()){
				continue;
			}
			Object [] parameters = prepareStudentHealthSurveyUpdateParameters(studentHealthSurvey);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchStudentHealthSurveyCreate(List<StudentHealthSurvey> studentHealthSurveyList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareStudentHealthSurveyBatchCreateArgs(studentHealthSurveyList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchStudentHealthSurveyUpdate(List<StudentHealthSurvey> studentHealthSurveyList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareStudentHealthSurveyBatchUpdateArgs(studentHealthSurveyList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitStudentHealthSurveyList(List<StudentHealthSurvey> studentHealthSurveyList){
		
		List<StudentHealthSurvey> studentHealthSurveyCreateList=new ArrayList<StudentHealthSurvey>();
		List<StudentHealthSurvey> studentHealthSurveyUpdateList=new ArrayList<StudentHealthSurvey>();
		
		for(StudentHealthSurvey studentHealthSurvey: studentHealthSurveyList){
			if(isUpdateRequest(studentHealthSurvey)){
				studentHealthSurveyUpdateList.add( studentHealthSurvey);
				continue;
			}
			studentHealthSurveyCreateList.add(studentHealthSurvey);
		}
		
		return new Object[]{studentHealthSurveyCreateList,studentHealthSurveyUpdateList};
	}
	
	protected boolean isUpdateRequest(StudentHealthSurvey studentHealthSurvey){
 		return studentHealthSurvey.getVersion() > 0;
 	}
 	protected String getSaveStudentHealthSurveySQL(StudentHealthSurvey studentHealthSurvey){
 		if(isUpdateRequest(studentHealthSurvey)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveStudentHealthSurveyParameters(StudentHealthSurvey studentHealthSurvey){
 		if(isUpdateRequest(studentHealthSurvey) ){
 			return prepareStudentHealthSurveyUpdateParameters(studentHealthSurvey);
 		}
 		return prepareStudentHealthSurveyCreateParameters(studentHealthSurvey);
 	}
 	protected Object[] prepareStudentHealthSurveyUpdateParameters(StudentHealthSurvey studentHealthSurvey){
 		Object[] parameters = new Object[11];
  	
 		if(studentHealthSurvey.getStudent() != null){
 			parameters[0] = studentHealthSurvey.getStudent().getId();
 		}
 
 		parameters[1] = studentHealthSurvey.getAnswerTime(); 	
 		if(studentHealthSurvey.getSurveyStatus() != null){
 			parameters[2] = studentHealthSurvey.getSurveyStatus().getId();
 		}
  	
 		if(studentHealthSurvey.getSchoolClass() != null){
 			parameters[3] = studentHealthSurvey.getSchoolClass().getId();
 		}
  	
 		if(studentHealthSurvey.getClassDailyHealthSurvey() != null){
 			parameters[4] = studentHealthSurvey.getClassDailyHealthSurvey().getId();
 		}
 
 		parameters[5] = studentHealthSurvey.getCreateTime();
 		parameters[6] = studentHealthSurvey.getLastUpdateTime(); 	
 		if(studentHealthSurvey.getCq() != null){
 			parameters[7] = studentHealthSurvey.getCq().getId();
 		}
 		
 		parameters[8] = studentHealthSurvey.nextVersion();
 		parameters[9] = studentHealthSurvey.getId();
 		parameters[10] = studentHealthSurvey.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareStudentHealthSurveyCreateParameters(StudentHealthSurvey studentHealthSurvey){
		Object[] parameters = new Object[9];
		String newStudentHealthSurveyId=getNextId();
		studentHealthSurvey.setId(newStudentHealthSurveyId);
		parameters[0] =  studentHealthSurvey.getId();
  	
 		if(studentHealthSurvey.getStudent() != null){
 			parameters[1] = studentHealthSurvey.getStudent().getId();
 		
 		}
 		
 		parameters[2] = studentHealthSurvey.getAnswerTime(); 	
 		if(studentHealthSurvey.getSurveyStatus() != null){
 			parameters[3] = studentHealthSurvey.getSurveyStatus().getId();
 		
 		}
 		 	
 		if(studentHealthSurvey.getSchoolClass() != null){
 			parameters[4] = studentHealthSurvey.getSchoolClass().getId();
 		
 		}
 		 	
 		if(studentHealthSurvey.getClassDailyHealthSurvey() != null){
 			parameters[5] = studentHealthSurvey.getClassDailyHealthSurvey().getId();
 		
 		}
 		
 		parameters[6] = studentHealthSurvey.getCreateTime();
 		parameters[7] = studentHealthSurvey.getLastUpdateTime(); 	
 		if(studentHealthSurvey.getCq() != null){
 			parameters[8] = studentHealthSurvey.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected StudentHealthSurvey saveInternalStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
		
		saveStudentHealthSurvey(studentHealthSurvey);
 	
 		if(isSaveStudentEnabled(options)){
	 		saveStudent(studentHealthSurvey, options);
 		}
  	
 		if(isSaveSurveyStatusEnabled(options)){
	 		saveSurveyStatus(studentHealthSurvey, options);
 		}
  	
 		if(isSaveSchoolClassEnabled(options)){
	 		saveSchoolClass(studentHealthSurvey, options);
 		}
  	
 		if(isSaveClassDailyHealthSurveyEnabled(options)){
	 		saveClassDailyHealthSurvey(studentHealthSurvey, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(studentHealthSurvey, options);
 		}
 
		
		if(isSaveStudentDailyAnswerListEnabled(options)){
	 		saveStudentDailyAnswerList(studentHealthSurvey, options);
	 		//removeStudentDailyAnswerList(studentHealthSurvey, options);
	 		//Not delete the record
	 		
 		}		
		
		return studentHealthSurvey;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected StudentHealthSurvey saveStudent(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentHealthSurvey.getStudent() == null){
 			return studentHealthSurvey;//do nothing when it is null
 		}
 		
 		getStudentDAO().save(studentHealthSurvey.getStudent(),options);
 		return studentHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected StudentHealthSurvey saveSurveyStatus(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentHealthSurvey.getSurveyStatus() == null){
 			return studentHealthSurvey;//do nothing when it is null
 		}
 		
 		getSurveyStatusDAO().save(studentHealthSurvey.getSurveyStatus(),options);
 		return studentHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected StudentHealthSurvey saveSchoolClass(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentHealthSurvey.getSchoolClass() == null){
 			return studentHealthSurvey;//do nothing when it is null
 		}
 		
 		getSchoolClassDAO().save(studentHealthSurvey.getSchoolClass(),options);
 		return studentHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected StudentHealthSurvey saveClassDailyHealthSurvey(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentHealthSurvey.getClassDailyHealthSurvey() == null){
 			return studentHealthSurvey;//do nothing when it is null
 		}
 		
 		getClassDailyHealthSurveyDAO().save(studentHealthSurvey.getClassDailyHealthSurvey(),options);
 		return studentHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected StudentHealthSurvey saveCq(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentHealthSurvey.getCq() == null){
 			return studentHealthSurvey;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(studentHealthSurvey.getCq(),options);
 		return studentHealthSurvey;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public StudentHealthSurvey planToRemoveStudentDailyAnswerList(StudentHealthSurvey studentHealthSurvey, String studentDailyAnswerIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurvey.getId());
		key.put(StudentDailyAnswer.ID_PROPERTY, studentDailyAnswerIds);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return studentHealthSurvey;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return studentHealthSurvey;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){

			studentDailyAnswerItem.clearFromAll();
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return studentHealthSurvey;	
	
	}


	//disconnect StudentHealthSurvey with question in StudentDailyAnswer
	public StudentHealthSurvey planToRemoveStudentDailyAnswerListWithQuestion(StudentHealthSurvey studentHealthSurvey, String questionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurvey.getId());
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, questionId);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return studentHealthSurvey;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return studentHealthSurvey;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){
			studentDailyAnswerItem.clearQuestion();
			studentDailyAnswerItem.clearStudentHealthSurvey();
			
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return studentHealthSurvey;
	}
	
	public int countStudentDailyAnswerListWithQuestion(String studentHealthSurveyId, String questionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, questionId);
		
		int count = getStudentDailyAnswerDAO().countStudentDailyAnswerWithKey(key, options);
		return count;
	}
	
	//disconnect StudentHealthSurvey with cq in StudentDailyAnswer
	public StudentHealthSurvey planToRemoveStudentDailyAnswerListWithCq(StudentHealthSurvey studentHealthSurvey, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurvey.getId());
		key.put(StudentDailyAnswer.CQ_PROPERTY, cqId);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return studentHealthSurvey;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return studentHealthSurvey;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){
			studentDailyAnswerItem.clearCq();
			studentDailyAnswerItem.clearStudentHealthSurvey();
			
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return studentHealthSurvey;
	}
	
	public int countStudentDailyAnswerListWithCq(String studentHealthSurveyId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
		key.put(StudentDailyAnswer.CQ_PROPERTY, cqId);
		
		int count = getStudentDailyAnswerDAO().countStudentDailyAnswerWithKey(key, options);
		return count;
	}
	

		
	protected StudentHealthSurvey saveStudentDailyAnswerList(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
		
		
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();
		if(studentDailyAnswerList == null){
			//null list means nothing
			return studentHealthSurvey;
		}
		SmartList<StudentDailyAnswer> mergedUpdateStudentDailyAnswerList = new SmartList<StudentDailyAnswer>();
		
		
		mergedUpdateStudentDailyAnswerList.addAll(studentDailyAnswerList); 
		if(studentDailyAnswerList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentDailyAnswerList.addAll(studentDailyAnswerList.getToRemoveList());
			studentDailyAnswerList.removeAll(studentDailyAnswerList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentDailyAnswerDAO().saveStudentDailyAnswerList(mergedUpdateStudentDailyAnswerList,options);
		
		if(studentDailyAnswerList.getToRemoveList() != null){
			studentDailyAnswerList.removeAll(studentDailyAnswerList.getToRemoveList());
		}
		
		
		return studentHealthSurvey;
	
	}
	
	protected StudentHealthSurvey removeStudentDailyAnswerList(StudentHealthSurvey studentHealthSurvey, Map<String,Object> options){
	
	
		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();
		if(studentDailyAnswerList == null){
			return studentHealthSurvey;
		}	
	
		SmartList<StudentDailyAnswer> toRemoveStudentDailyAnswerList = studentDailyAnswerList.getToRemoveList();
		
		if(toRemoveStudentDailyAnswerList == null){
			return studentHealthSurvey;
		}
		if(toRemoveStudentDailyAnswerList.isEmpty()){
			return studentHealthSurvey;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDailyAnswerDAO().removeStudentDailyAnswerList(toRemoveStudentDailyAnswerList,options);
		
		return studentHealthSurvey;
	
	}
	
	

 	
 	
	
	
	
		

	public StudentHealthSurvey present(StudentHealthSurvey studentHealthSurvey,Map<String, Object> options){
	
		presentStudentDailyAnswerList(studentHealthSurvey,options);

		return studentHealthSurvey;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected StudentHealthSurvey presentStudentDailyAnswerList(
			StudentHealthSurvey studentHealthSurvey,
			Map<String, Object> options) {

		SmartList<StudentDailyAnswer> studentDailyAnswerList = studentHealthSurvey.getStudentDailyAnswerList();		
				SmartList<StudentDailyAnswer> newList= presentSubList(studentHealthSurvey.getId(),
				studentDailyAnswerList,
				options,
				getStudentDailyAnswerDAO()::countStudentDailyAnswerByStudentHealthSurvey,
				getStudentDailyAnswerDAO()::findStudentDailyAnswerByStudentHealthSurvey
				);

		
		studentHealthSurvey.setStudentDailyAnswerList(newList);
		

		return studentHealthSurvey;
	}			
		

	
    public SmartList<StudentHealthSurvey> requestCandidateStudentHealthSurveyForStudentDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(StudentHealthSurveyTable.COLUMN_STUDENT, filterKey, pageNo, pageSize, getStudentHealthSurveyMapper());
    }
		

	protected String getTableName(){
		return StudentHealthSurveyTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<StudentHealthSurvey> studentHealthSurveyList) {		
		this.enhanceListInternal(studentHealthSurveyList, this.getStudentHealthSurveyMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:StudentDailyAnswer的studentHealthSurvey的StudentDailyAnswerList
	public SmartList<StudentDailyAnswer> loadOurStudentDailyAnswerList(HealthUserContext userContext, List<StudentHealthSurvey> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentDailyAnswer> loadedObjs = userContext.getDAOGroup().getStudentDailyAnswerDAO().findStudentDailyAnswerWithKey(key, options);
		Map<String, List<StudentDailyAnswer>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getStudentHealthSurvey().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<StudentDailyAnswer> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<StudentDailyAnswer> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentDailyAnswerList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<StudentHealthSurvey> studentHealthSurveyList = ownerEntity.collectRefsWithType(StudentHealthSurvey.INTERNAL_TYPE);
		this.enhanceList(studentHealthSurveyList);
		
	}
	
	@Override
	public SmartList<StudentHealthSurvey> findStudentHealthSurveyWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getStudentHealthSurveyMapper());

	}
	@Override
	public int countStudentHealthSurveyWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countStudentHealthSurveyWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<StudentHealthSurvey> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getStudentHealthSurveyMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


