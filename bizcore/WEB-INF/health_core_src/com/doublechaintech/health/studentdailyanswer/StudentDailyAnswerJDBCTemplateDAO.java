
package com.doublechaintech.health.studentdailyanswer;

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


import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.studentanswer.StudentAnswerDAO;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class StudentDailyAnswerJDBCTemplateDAO extends HealthBaseDAOImpl implements StudentDailyAnswerDAO{
 
 	
 	private  StudentHealthSurveyDAO  studentHealthSurveyDAO;
 	public void setStudentHealthSurveyDAO(StudentHealthSurveyDAO studentHealthSurveyDAO){
	 	this.studentHealthSurveyDAO = studentHealthSurveyDAO;
 	}
 	public StudentHealthSurveyDAO getStudentHealthSurveyDAO(){
	 	return this.studentHealthSurveyDAO;
 	}
 
 	
 	private  DailySurveyQuestionDAO  dailySurveyQuestionDAO;
 	public void setDailySurveyQuestionDAO(DailySurveyQuestionDAO dailySurveyQuestionDAO){
	 	this.dailySurveyQuestionDAO = dailySurveyQuestionDAO;
 	}
 	public DailySurveyQuestionDAO getDailySurveyQuestionDAO(){
	 	return this.dailySurveyQuestionDAO;
 	}


			
		
	
  	private  StudentAnswerDAO  studentAnswerDAO;
 	public void setStudentAnswerDAO(StudentAnswerDAO pStudentAnswerDAO){
 	
 		if(pStudentAnswerDAO == null){
 			throw new IllegalStateException("Do not try to set studentAnswerDAO to null.");
 		}
	 	this.studentAnswerDAO = pStudentAnswerDAO;
 	}
 	public StudentAnswerDAO getStudentAnswerDAO(){
 		if(this.studentAnswerDAO == null){
 			throw new IllegalStateException("The studentAnswerDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentAnswerDAO;
 	}	
 	
			
		

	
	/*
	protected StudentDailyAnswer load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalStudentDailyAnswer(accessKey, options);
	}
	*/
	
	public SmartList<StudentDailyAnswer> loadAll() {
	    return this.loadAll(getStudentDailyAnswerMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public StudentDailyAnswer load(String id,Map<String,Object> options) throws Exception{
		return loadInternalStudentDailyAnswer(StudentDailyAnswerTable.withId(id), options);
	}
	
	
	
	public StudentDailyAnswer save(StudentDailyAnswer studentDailyAnswer,Map<String,Object> options){
		
		String methodName="save(StudentDailyAnswer studentDailyAnswer,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(studentDailyAnswer, methodName, "studentDailyAnswer");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalStudentDailyAnswer(studentDailyAnswer,options);
	}
	public StudentDailyAnswer clone(String studentDailyAnswerId, Map<String,Object> options) throws Exception{
	
		return clone(StudentDailyAnswerTable.withId(studentDailyAnswerId),options);
	}
	
	protected StudentDailyAnswer clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String studentDailyAnswerId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		StudentDailyAnswer newStudentDailyAnswer = loadInternalStudentDailyAnswer(accessKey, options);
		newStudentDailyAnswer.setVersion(0);
		
		
 		
 		if(isSaveStudentAnswerListEnabled(options)){
 			for(StudentAnswer item: newStudentDailyAnswer.getStudentAnswerList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalStudentDailyAnswer(newStudentDailyAnswer,options);
		
		return newStudentDailyAnswer;
	}
	
	
	
	

	protected void throwIfHasException(String studentDailyAnswerId,int version,int count) throws Exception{
		if (count == 1) {
			throw new StudentDailyAnswerVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new StudentDailyAnswerNotFoundException(
					"The " + this.getTableName() + "(" + studentDailyAnswerId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String studentDailyAnswerId, int version) throws Exception{
	
		String methodName="delete(String studentDailyAnswerId, int version)";
		assertMethodArgumentNotNull(studentDailyAnswerId, methodName, "studentDailyAnswerId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{studentDailyAnswerId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(studentDailyAnswerId,version);
		}
		
	
	}
	
	
	
	
	

	public StudentDailyAnswer disconnectFromAll(String studentDailyAnswerId, int version) throws Exception{
	
		
		StudentDailyAnswer studentDailyAnswer = loadInternalStudentDailyAnswer(StudentDailyAnswerTable.withId(studentDailyAnswerId), emptyOptions());
		studentDailyAnswer.clearFromAll();
		this.saveStudentDailyAnswer(studentDailyAnswer);
		return studentDailyAnswer;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return StudentDailyAnswerTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "student_daily_answer";
	}
	@Override
	protected String getBeanName() {
		
		return "studentDailyAnswer";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return StudentDailyAnswerTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractStudentHealthSurveyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentDailyAnswerTokens.STUDENTHEALTHSURVEY);
 	}

 	protected boolean isSaveStudentHealthSurveyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentDailyAnswerTokens.STUDENTHEALTHSURVEY);
 	}
 	

 	
  

 	protected boolean isExtractQuestionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentDailyAnswerTokens.QUESTION);
 	}

 	protected boolean isSaveQuestionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentDailyAnswerTokens.QUESTION);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentAnswerListEnabled(Map<String,Object> options){		
 		return checkOptions(options,StudentDailyAnswerTokens.STUDENT_ANSWER_LIST);
 	}
 	protected boolean isAnalyzeStudentAnswerListEnabled(Map<String,Object> options){		 		
 		return StudentDailyAnswerTokens.of(options).analyzeStudentAnswerListEnabled();
 	}
	
	protected boolean isSaveStudentAnswerListEnabled(Map<String,Object> options){
		return checkOptions(options, StudentDailyAnswerTokens.STUDENT_ANSWER_LIST);
		
 	}
 	
		

	

	protected StudentDailyAnswerMapper getStudentDailyAnswerMapper(){
		return new StudentDailyAnswerMapper();
	}

	
	
	protected StudentDailyAnswer extractStudentDailyAnswer(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			StudentDailyAnswer studentDailyAnswer = loadSingleObject(accessKey, getStudentDailyAnswerMapper());
			return studentDailyAnswer;
		}catch(EmptyResultDataAccessException e){
			throw new StudentDailyAnswerNotFoundException("StudentDailyAnswer("+accessKey+") is not found!");
		}

	}

	
	

	protected StudentDailyAnswer loadInternalStudentDailyAnswer(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		StudentDailyAnswer studentDailyAnswer = extractStudentDailyAnswer(accessKey, loadOptions);
 	
 		if(isExtractStudentHealthSurveyEnabled(loadOptions)){
	 		extractStudentHealthSurvey(studentDailyAnswer, loadOptions);
 		}
  	
 		if(isExtractQuestionEnabled(loadOptions)){
	 		extractQuestion(studentDailyAnswer, loadOptions);
 		}
 
		
		if(isExtractStudentAnswerListEnabled(loadOptions)){
	 		extractStudentAnswerList(studentDailyAnswer, loadOptions);
 		}	
 		if(isAnalyzeStudentAnswerListEnabled(loadOptions)){
	 		analyzeStudentAnswerList(studentDailyAnswer, loadOptions);
 		}
 		
		
		return studentDailyAnswer;
		
	}

	 

 	protected StudentDailyAnswer extractStudentHealthSurvey(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options) throws Exception{

		if(studentDailyAnswer.getStudentHealthSurvey() == null){
			return studentDailyAnswer;
		}
		String studentHealthSurveyId = studentDailyAnswer.getStudentHealthSurvey().getId();
		if( studentHealthSurveyId == null){
			return studentDailyAnswer;
		}
		StudentHealthSurvey studentHealthSurvey = getStudentHealthSurveyDAO().load(studentHealthSurveyId,options);
		if(studentHealthSurvey != null){
			studentDailyAnswer.setStudentHealthSurvey(studentHealthSurvey);
		}
		
 		
 		return studentDailyAnswer;
 	}
 		
  

 	protected StudentDailyAnswer extractQuestion(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options) throws Exception{

		if(studentDailyAnswer.getQuestion() == null){
			return studentDailyAnswer;
		}
		String questionId = studentDailyAnswer.getQuestion().getId();
		if( questionId == null){
			return studentDailyAnswer;
		}
		DailySurveyQuestion question = getDailySurveyQuestionDAO().load(questionId,options);
		if(question != null){
			studentDailyAnswer.setQuestion(question);
		}
		
 		
 		return studentDailyAnswer;
 	}
 		
 
		
	protected void enhanceStudentAnswerList(SmartList<StudentAnswer> studentAnswerList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected StudentDailyAnswer extractStudentAnswerList(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
		
		
		if(studentDailyAnswer == null){
			return null;
		}
		if(studentDailyAnswer.getId() == null){
			return studentDailyAnswer;
		}

		
		
		SmartList<StudentAnswer> studentAnswerList = getStudentAnswerDAO().findStudentAnswerByDailyAnswer(studentDailyAnswer.getId(),options);
		if(studentAnswerList != null){
			enhanceStudentAnswerList(studentAnswerList,options);
			studentDailyAnswer.setStudentAnswerList(studentAnswerList);
		}
		
		return studentDailyAnswer;
	
	}	
	
	protected StudentDailyAnswer analyzeStudentAnswerList(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
		
		
		if(studentDailyAnswer == null){
			return null;
		}
		if(studentDailyAnswer.getId() == null){
			return studentDailyAnswer;
		}

		
		
		SmartList<StudentAnswer> studentAnswerList = studentDailyAnswer.getStudentAnswerList();
		if(studentAnswerList != null){
			getStudentAnswerDAO().analyzeStudentAnswerByDailyAnswer(studentAnswerList, studentDailyAnswer.getId(), options);
			
		}
		
		return studentDailyAnswer;
	
	}	
	
		
		
  	
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByStudentHealthSurvey(String studentHealthSurveyId,Map<String,Object> options){
 	
  		SmartList<StudentDailyAnswer> resultList = queryWith(StudentDailyAnswerTable.COLUMN_STUDENT_HEALTH_SURVEY, studentHealthSurveyId, options, getStudentDailyAnswerMapper());
		// analyzeStudentDailyAnswerByStudentHealthSurvey(resultList, studentHealthSurveyId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByStudentHealthSurvey(String studentHealthSurveyId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentDailyAnswer> resultList =  queryWithRange(StudentDailyAnswerTable.COLUMN_STUDENT_HEALTH_SURVEY, studentHealthSurveyId, options, getStudentDailyAnswerMapper(), start, count);
 		//analyzeStudentDailyAnswerByStudentHealthSurvey(resultList, studentHealthSurveyId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentDailyAnswerByStudentHealthSurvey(SmartList<StudentDailyAnswer> resultList, String studentHealthSurveyId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//StudentDailyAnswer.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("学生每天回答");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(StudentDailyAnswer.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentDailyAnswer.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentDailyAnswerByStudentHealthSurvey(String studentHealthSurveyId,Map<String,Object> options){

 		return countWith(StudentDailyAnswerTable.COLUMN_STUDENT_HEALTH_SURVEY, studentHealthSurveyId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentDailyAnswerByStudentHealthSurveyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentDailyAnswerTable.COLUMN_STUDENT_HEALTH_SURVEY, ids, options);
	}
 	
  	
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByQuestion(String dailySurveyQuestionId,Map<String,Object> options){
 	
  		SmartList<StudentDailyAnswer> resultList = queryWith(StudentDailyAnswerTable.COLUMN_QUESTION, dailySurveyQuestionId, options, getStudentDailyAnswerMapper());
		// analyzeStudentDailyAnswerByQuestion(resultList, dailySurveyQuestionId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByQuestion(String dailySurveyQuestionId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentDailyAnswer> resultList =  queryWithRange(StudentDailyAnswerTable.COLUMN_QUESTION, dailySurveyQuestionId, options, getStudentDailyAnswerMapper(), start, count);
 		//analyzeStudentDailyAnswerByQuestion(resultList, dailySurveyQuestionId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentDailyAnswerByQuestion(SmartList<StudentDailyAnswer> resultList, String dailySurveyQuestionId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentDailyAnswer.QUESTION_PROPERTY, dailySurveyQuestionId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//StudentDailyAnswer.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("学生每天回答");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(StudentDailyAnswer.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(StudentDailyAnswer.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentDailyAnswerByQuestion(String dailySurveyQuestionId,Map<String,Object> options){

 		return countWith(StudentDailyAnswerTable.COLUMN_QUESTION, dailySurveyQuestionId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentDailyAnswerByQuestionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentDailyAnswerTable.COLUMN_QUESTION, ids, options);
	}
 	
 	
		
		
		

	

	protected StudentDailyAnswer saveStudentDailyAnswer(StudentDailyAnswer  studentDailyAnswer){
		
		if(!studentDailyAnswer.isChanged()){
			return studentDailyAnswer;
		}
		
		
		String SQL=this.getSaveStudentDailyAnswerSQL(studentDailyAnswer);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveStudentDailyAnswerParameters(studentDailyAnswer);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		studentDailyAnswer.incVersion();
		return studentDailyAnswer;
	
	}
	public SmartList<StudentDailyAnswer> saveStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitStudentDailyAnswerList(studentDailyAnswerList);
		
		batchStudentDailyAnswerCreate((List<StudentDailyAnswer>)lists[CREATE_LIST_INDEX]);
		
		batchStudentDailyAnswerUpdate((List<StudentDailyAnswer>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList){
			if(studentDailyAnswer.isChanged()){
				studentDailyAnswer.incVersion();
			}
			
		
		}
		
		
		return studentDailyAnswerList;
	}

	public SmartList<StudentDailyAnswer> removeStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options){
		
		
		super.removeList(studentDailyAnswerList, options);
		
		return studentDailyAnswerList;
		
		
	}
	
	protected List<Object[]> prepareStudentDailyAnswerBatchCreateArgs(List<StudentDailyAnswer> studentDailyAnswerList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList ){
			Object [] parameters = prepareStudentDailyAnswerCreateParameters(studentDailyAnswer);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareStudentDailyAnswerBatchUpdateArgs(List<StudentDailyAnswer> studentDailyAnswerList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList ){
			if(!studentDailyAnswer.isChanged()){
				continue;
			}
			Object [] parameters = prepareStudentDailyAnswerUpdateParameters(studentDailyAnswer);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchStudentDailyAnswerCreate(List<StudentDailyAnswer> studentDailyAnswerList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareStudentDailyAnswerBatchCreateArgs(studentDailyAnswerList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchStudentDailyAnswerUpdate(List<StudentDailyAnswer> studentDailyAnswerList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareStudentDailyAnswerBatchUpdateArgs(studentDailyAnswerList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitStudentDailyAnswerList(List<StudentDailyAnswer> studentDailyAnswerList){
		
		List<StudentDailyAnswer> studentDailyAnswerCreateList=new ArrayList<StudentDailyAnswer>();
		List<StudentDailyAnswer> studentDailyAnswerUpdateList=new ArrayList<StudentDailyAnswer>();
		
		for(StudentDailyAnswer studentDailyAnswer: studentDailyAnswerList){
			if(isUpdateRequest(studentDailyAnswer)){
				studentDailyAnswerUpdateList.add( studentDailyAnswer);
				continue;
			}
			studentDailyAnswerCreateList.add(studentDailyAnswer);
		}
		
		return new Object[]{studentDailyAnswerCreateList,studentDailyAnswerUpdateList};
	}
	
	protected boolean isUpdateRequest(StudentDailyAnswer studentDailyAnswer){
 		return studentDailyAnswer.getVersion() > 0;
 	}
 	protected String getSaveStudentDailyAnswerSQL(StudentDailyAnswer studentDailyAnswer){
 		if(isUpdateRequest(studentDailyAnswer)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveStudentDailyAnswerParameters(StudentDailyAnswer studentDailyAnswer){
 		if(isUpdateRequest(studentDailyAnswer) ){
 			return prepareStudentDailyAnswerUpdateParameters(studentDailyAnswer);
 		}
 		return prepareStudentDailyAnswerCreateParameters(studentDailyAnswer);
 	}
 	protected Object[] prepareStudentDailyAnswerUpdateParameters(StudentDailyAnswer studentDailyAnswer){
 		Object[] parameters = new Object[8];
  	
 		if(studentDailyAnswer.getStudentHealthSurvey() != null){
 			parameters[0] = studentDailyAnswer.getStudentHealthSurvey().getId();
 		}
  	
 		if(studentDailyAnswer.getQuestion() != null){
 			parameters[1] = studentDailyAnswer.getQuestion().getId();
 		}
 
 		
 		parameters[2] = studentDailyAnswer.getAnswer();
 		
 		
 		parameters[3] = studentDailyAnswer.getCreateTime();
 		
 		
 		parameters[4] = studentDailyAnswer.getLastUpdateTime();
 				
 		parameters[5] = studentDailyAnswer.nextVersion();
 		parameters[6] = studentDailyAnswer.getId();
 		parameters[7] = studentDailyAnswer.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareStudentDailyAnswerCreateParameters(StudentDailyAnswer studentDailyAnswer){
		Object[] parameters = new Object[6];
		String newStudentDailyAnswerId=getNextId();
		studentDailyAnswer.setId(newStudentDailyAnswerId);
		parameters[0] =  studentDailyAnswer.getId();
  	
 		if(studentDailyAnswer.getStudentHealthSurvey() != null){
 			parameters[1] = studentDailyAnswer.getStudentHealthSurvey().getId();
 		
 		}
 		 	
 		if(studentDailyAnswer.getQuestion() != null){
 			parameters[2] = studentDailyAnswer.getQuestion().getId();
 		
 		}
 		
 		
 		parameters[3] = studentDailyAnswer.getAnswer();
 		
 		
 		parameters[4] = studentDailyAnswer.getCreateTime();
 		
 		
 		parameters[5] = studentDailyAnswer.getLastUpdateTime();
 				
 				
 		return parameters;
 	}
 	
	protected StudentDailyAnswer saveInternalStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
		
		saveStudentDailyAnswer(studentDailyAnswer);
 	
 		if(isSaveStudentHealthSurveyEnabled(options)){
	 		saveStudentHealthSurvey(studentDailyAnswer, options);
 		}
  	
 		if(isSaveQuestionEnabled(options)){
	 		saveQuestion(studentDailyAnswer, options);
 		}
 
		
		if(isSaveStudentAnswerListEnabled(options)){
	 		saveStudentAnswerList(studentDailyAnswer, options);
	 		//removeStudentAnswerList(studentDailyAnswer, options);
	 		//Not delete the record
	 		
 		}		
		
		return studentDailyAnswer;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected StudentDailyAnswer saveStudentHealthSurvey(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentDailyAnswer.getStudentHealthSurvey() == null){
 			return studentDailyAnswer;//do nothing when it is null
 		}
 		
 		getStudentHealthSurveyDAO().save(studentDailyAnswer.getStudentHealthSurvey(),options);
 		return studentDailyAnswer;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected StudentDailyAnswer saveQuestion(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentDailyAnswer.getQuestion() == null){
 			return studentDailyAnswer;//do nothing when it is null
 		}
 		
 		getDailySurveyQuestionDAO().save(studentDailyAnswer.getQuestion(),options);
 		return studentDailyAnswer;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public StudentDailyAnswer planToRemoveStudentAnswerList(StudentDailyAnswer studentDailyAnswer, String studentAnswerIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.DAILY_ANSWER_PROPERTY, studentDailyAnswer.getId());
		key.put(StudentAnswer.ID_PROPERTY, studentAnswerIds);
		
		SmartList<StudentAnswer> externalStudentAnswerList = getStudentAnswerDAO().
				findStudentAnswerWithKey(key, options);
		if(externalStudentAnswerList == null){
			return studentDailyAnswer;
		}
		if(externalStudentAnswerList.isEmpty()){
			return studentDailyAnswer;
		}
		
		for(StudentAnswer studentAnswerItem: externalStudentAnswerList){

			studentAnswerItem.clearFromAll();
		}
		
		
		SmartList<StudentAnswer> studentAnswerList = studentDailyAnswer.getStudentAnswerList();		
		studentAnswerList.addAllToRemoveList(externalStudentAnswerList);
		return studentDailyAnswer;	
	
	}


	//disconnect StudentDailyAnswer with health_survey_report in StudentAnswer
	public StudentDailyAnswer planToRemoveStudentAnswerListWithHealthSurveyReport(StudentDailyAnswer studentDailyAnswer, String healthSurveyReportId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.DAILY_ANSWER_PROPERTY, studentDailyAnswer.getId());
		key.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, healthSurveyReportId);
		
		SmartList<StudentAnswer> externalStudentAnswerList = getStudentAnswerDAO().
				findStudentAnswerWithKey(key, options);
		if(externalStudentAnswerList == null){
			return studentDailyAnswer;
		}
		if(externalStudentAnswerList.isEmpty()){
			return studentDailyAnswer;
		}
		
		for(StudentAnswer studentAnswerItem: externalStudentAnswerList){
			studentAnswerItem.clearHealthSurveyReport();
			studentAnswerItem.clearDailyAnswer();
			
		}
		
		
		SmartList<StudentAnswer> studentAnswerList = studentDailyAnswer.getStudentAnswerList();		
		studentAnswerList.addAllToRemoveList(externalStudentAnswerList);
		return studentDailyAnswer;
	}
	
	public int countStudentAnswerListWithHealthSurveyReport(String studentDailyAnswerId, String healthSurveyReportId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.DAILY_ANSWER_PROPERTY, studentDailyAnswerId);
		key.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, healthSurveyReportId);
		
		int count = getStudentAnswerDAO().countStudentAnswerWithKey(key, options);
		return count;
	}
	

		
	protected StudentDailyAnswer saveStudentAnswerList(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
		
		
		
		
		SmartList<StudentAnswer> studentAnswerList = studentDailyAnswer.getStudentAnswerList();
		if(studentAnswerList == null){
			//null list means nothing
			return studentDailyAnswer;
		}
		SmartList<StudentAnswer> mergedUpdateStudentAnswerList = new SmartList<StudentAnswer>();
		
		
		mergedUpdateStudentAnswerList.addAll(studentAnswerList); 
		if(studentAnswerList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentAnswerList.addAll(studentAnswerList.getToRemoveList());
			studentAnswerList.removeAll(studentAnswerList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentAnswerDAO().saveStudentAnswerList(mergedUpdateStudentAnswerList,options);
		
		if(studentAnswerList.getToRemoveList() != null){
			studentAnswerList.removeAll(studentAnswerList.getToRemoveList());
		}
		
		
		return studentDailyAnswer;
	
	}
	
	protected StudentDailyAnswer removeStudentAnswerList(StudentDailyAnswer studentDailyAnswer, Map<String,Object> options){
	
	
		SmartList<StudentAnswer> studentAnswerList = studentDailyAnswer.getStudentAnswerList();
		if(studentAnswerList == null){
			return studentDailyAnswer;
		}	
	
		SmartList<StudentAnswer> toRemoveStudentAnswerList = studentAnswerList.getToRemoveList();
		
		if(toRemoveStudentAnswerList == null){
			return studentDailyAnswer;
		}
		if(toRemoveStudentAnswerList.isEmpty()){
			return studentDailyAnswer;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentAnswerDAO().removeStudentAnswerList(toRemoveStudentAnswerList,options);
		
		return studentDailyAnswer;
	
	}
	
	

 	
 	
	
	
	
		

	public StudentDailyAnswer present(StudentDailyAnswer studentDailyAnswer,Map<String, Object> options){
	
		presentStudentAnswerList(studentDailyAnswer,options);

		return studentDailyAnswer;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected StudentDailyAnswer presentStudentAnswerList(
			StudentDailyAnswer studentDailyAnswer,
			Map<String, Object> options) {

		SmartList<StudentAnswer> studentAnswerList = studentDailyAnswer.getStudentAnswerList();		
				SmartList<StudentAnswer> newList= presentSubList(studentDailyAnswer.getId(),
				studentAnswerList,
				options,
				getStudentAnswerDAO()::countStudentAnswerByDailyAnswer,
				getStudentAnswerDAO()::findStudentAnswerByDailyAnswer
				);

		
		studentDailyAnswer.setStudentAnswerList(newList);
		

		return studentDailyAnswer;
	}			
		

	
    public SmartList<StudentDailyAnswer> requestCandidateStudentDailyAnswerForStudentAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(StudentDailyAnswerTable.COLUMN_STUDENT_HEALTH_SURVEY, filterKey, pageNo, pageSize, getStudentDailyAnswerMapper());
    }
		

	protected String getTableName(){
		return StudentDailyAnswerTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<StudentDailyAnswer> studentDailyAnswerList) {		
		this.enhanceListInternal(studentDailyAnswerList, this.getStudentDailyAnswerMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:StudentAnswer的dailyAnswer的StudentAnswerList
	public SmartList<StudentAnswer> loadOurStudentAnswerList(HealthUserContext userContext, List<StudentDailyAnswer> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.DAILY_ANSWER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentAnswer> loadedObjs = userContext.getDAOGroup().getStudentAnswerDAO().findStudentAnswerWithKey(key, options);
		Map<String, List<StudentAnswer>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getDailyAnswer().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<StudentAnswer> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<StudentAnswer> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentAnswerList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<StudentDailyAnswer> studentDailyAnswerList = ownerEntity.collectRefsWithType(StudentDailyAnswer.INTERNAL_TYPE);
		this.enhanceList(studentDailyAnswerList);
		
	}
	
	@Override
	public SmartList<StudentDailyAnswer> findStudentDailyAnswerWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getStudentDailyAnswerMapper());

	}
	@Override
	public int countStudentDailyAnswerWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countStudentDailyAnswerWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<StudentDailyAnswer> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getStudentDailyAnswerMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


