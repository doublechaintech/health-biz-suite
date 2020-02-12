
package com.doublechaintech.health.dailysurveyquestion;

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


import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.question.Question;

import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;
import com.doublechaintech.health.question.QuestionDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class DailySurveyQuestionJDBCTemplateDAO extends HealthBaseDAOImpl implements DailySurveyQuestionDAO{
 
 	
 	private  QuestionTypeDAO  questionTypeDAO;
 	public void setQuestionTypeDAO(QuestionTypeDAO questionTypeDAO){
	 	this.questionTypeDAO = questionTypeDAO;
 	}
 	public QuestionTypeDAO getQuestionTypeDAO(){
	 	return this.questionTypeDAO;
 	}
 
 	
 	private  ClassDailyHealthSurveyDAO  classDailyHealthSurveyDAO;
 	public void setClassDailyHealthSurveyDAO(ClassDailyHealthSurveyDAO classDailyHealthSurveyDAO){
	 	this.classDailyHealthSurveyDAO = classDailyHealthSurveyDAO;
 	}
 	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO(){
	 	return this.classDailyHealthSurveyDAO;
 	}
 
 	
 	private  QuestionDAO  questionDAO;
 	public void setQuestionDAO(QuestionDAO questionDAO){
	 	this.questionDAO = questionDAO;
 	}
 	public QuestionDAO getQuestionDAO(){
	 	return this.questionDAO;
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
	protected DailySurveyQuestion load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalDailySurveyQuestion(accessKey, options);
	}
	*/
	
	public SmartList<DailySurveyQuestion> loadAll() {
	    return this.loadAll(getDailySurveyQuestionMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public DailySurveyQuestion load(String id,Map<String,Object> options) throws Exception{
		return loadInternalDailySurveyQuestion(DailySurveyQuestionTable.withId(id), options);
	}
	
	
	
	public DailySurveyQuestion save(DailySurveyQuestion dailySurveyQuestion,Map<String,Object> options){
		
		String methodName="save(DailySurveyQuestion dailySurveyQuestion,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(dailySurveyQuestion, methodName, "dailySurveyQuestion");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalDailySurveyQuestion(dailySurveyQuestion,options);
	}
	public DailySurveyQuestion clone(String dailySurveyQuestionId, Map<String,Object> options) throws Exception{
	
		return clone(DailySurveyQuestionTable.withId(dailySurveyQuestionId),options);
	}
	
	protected DailySurveyQuestion clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String dailySurveyQuestionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		DailySurveyQuestion newDailySurveyQuestion = loadInternalDailySurveyQuestion(accessKey, options);
		newDailySurveyQuestion.setVersion(0);
		
		
 		
 		if(isSaveStudentDailyAnswerListEnabled(options)){
 			for(StudentDailyAnswer item: newDailySurveyQuestion.getStudentDailyAnswerList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalDailySurveyQuestion(newDailySurveyQuestion,options);
		
		return newDailySurveyQuestion;
	}
	
	
	
	

	protected void throwIfHasException(String dailySurveyQuestionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new DailySurveyQuestionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new DailySurveyQuestionNotFoundException(
					"The " + this.getTableName() + "(" + dailySurveyQuestionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String dailySurveyQuestionId, int version) throws Exception{
	
		String methodName="delete(String dailySurveyQuestionId, int version)";
		assertMethodArgumentNotNull(dailySurveyQuestionId, methodName, "dailySurveyQuestionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{dailySurveyQuestionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(dailySurveyQuestionId,version);
		}
		
	
	}
	
	
	
	
	

	public DailySurveyQuestion disconnectFromAll(String dailySurveyQuestionId, int version) throws Exception{
	
		
		DailySurveyQuestion dailySurveyQuestion = loadInternalDailySurveyQuestion(DailySurveyQuestionTable.withId(dailySurveyQuestionId), emptyOptions());
		dailySurveyQuestion.clearFromAll();
		this.saveDailySurveyQuestion(dailySurveyQuestion);
		return dailySurveyQuestion;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return DailySurveyQuestionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "daily_survey_question";
	}
	@Override
	protected String getBeanName() {
		
		return "dailySurveyQuestion";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return DailySurveyQuestionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractQuestionTypeEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, DailySurveyQuestionTokens.QUESTIONTYPE);
 	}

 	protected boolean isSaveQuestionTypeEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, DailySurveyQuestionTokens.QUESTIONTYPE);
 	}
 	

 	
  

 	protected boolean isExtractClassDailyHealthSurveyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, DailySurveyQuestionTokens.CLASSDAILYHEALTHSURVEY);
 	}

 	protected boolean isSaveClassDailyHealthSurveyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, DailySurveyQuestionTokens.CLASSDAILYHEALTHSURVEY);
 	}
 	

 	
  

 	protected boolean isExtractSurveyQuestionEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, DailySurveyQuestionTokens.SURVEYQUESTION);
 	}

 	protected boolean isSaveSurveyQuestionEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, DailySurveyQuestionTokens.SURVEYQUESTION);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentDailyAnswerListEnabled(Map<String,Object> options){		
 		return checkOptions(options,DailySurveyQuestionTokens.STUDENT_DAILY_ANSWER_LIST);
 	}
 	protected boolean isAnalyzeStudentDailyAnswerListEnabled(Map<String,Object> options){		 		
 		return DailySurveyQuestionTokens.of(options).analyzeStudentDailyAnswerListEnabled();
 	}
	
	protected boolean isSaveStudentDailyAnswerListEnabled(Map<String,Object> options){
		return checkOptions(options, DailySurveyQuestionTokens.STUDENT_DAILY_ANSWER_LIST);
		
 	}
 	
		

	

	protected DailySurveyQuestionMapper getDailySurveyQuestionMapper(){
		return new DailySurveyQuestionMapper();
	}

	
	
	protected DailySurveyQuestion extractDailySurveyQuestion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			DailySurveyQuestion dailySurveyQuestion = loadSingleObject(accessKey, getDailySurveyQuestionMapper());
			return dailySurveyQuestion;
		}catch(EmptyResultDataAccessException e){
			throw new DailySurveyQuestionNotFoundException("DailySurveyQuestion("+accessKey+") is not found!");
		}

	}

	
	

	protected DailySurveyQuestion loadInternalDailySurveyQuestion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		DailySurveyQuestion dailySurveyQuestion = extractDailySurveyQuestion(accessKey, loadOptions);
 	
 		if(isExtractQuestionTypeEnabled(loadOptions)){
	 		extractQuestionType(dailySurveyQuestion, loadOptions);
 		}
  	
 		if(isExtractClassDailyHealthSurveyEnabled(loadOptions)){
	 		extractClassDailyHealthSurvey(dailySurveyQuestion, loadOptions);
 		}
  	
 		if(isExtractSurveyQuestionEnabled(loadOptions)){
	 		extractSurveyQuestion(dailySurveyQuestion, loadOptions);
 		}
 
		
		if(isExtractStudentDailyAnswerListEnabled(loadOptions)){
	 		extractStudentDailyAnswerList(dailySurveyQuestion, loadOptions);
 		}	
 		if(isAnalyzeStudentDailyAnswerListEnabled(loadOptions)){
	 		analyzeStudentDailyAnswerList(dailySurveyQuestion, loadOptions);
 		}
 		
		
		return dailySurveyQuestion;
		
	}

	 

 	protected DailySurveyQuestion extractQuestionType(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options) throws Exception{

		if(dailySurveyQuestion.getQuestionType() == null){
			return dailySurveyQuestion;
		}
		String questionTypeId = dailySurveyQuestion.getQuestionType().getId();
		if( questionTypeId == null){
			return dailySurveyQuestion;
		}
		QuestionType questionType = getQuestionTypeDAO().load(questionTypeId,options);
		if(questionType != null){
			dailySurveyQuestion.setQuestionType(questionType);
		}
		
 		
 		return dailySurveyQuestion;
 	}
 		
  

 	protected DailySurveyQuestion extractClassDailyHealthSurvey(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options) throws Exception{

		if(dailySurveyQuestion.getClassDailyHealthSurvey() == null){
			return dailySurveyQuestion;
		}
		String classDailyHealthSurveyId = dailySurveyQuestion.getClassDailyHealthSurvey().getId();
		if( classDailyHealthSurveyId == null){
			return dailySurveyQuestion;
		}
		ClassDailyHealthSurvey classDailyHealthSurvey = getClassDailyHealthSurveyDAO().load(classDailyHealthSurveyId,options);
		if(classDailyHealthSurvey != null){
			dailySurveyQuestion.setClassDailyHealthSurvey(classDailyHealthSurvey);
		}
		
 		
 		return dailySurveyQuestion;
 	}
 		
  

 	protected DailySurveyQuestion extractSurveyQuestion(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options) throws Exception{

		if(dailySurveyQuestion.getSurveyQuestion() == null){
			return dailySurveyQuestion;
		}
		String surveyQuestionId = dailySurveyQuestion.getSurveyQuestion().getId();
		if( surveyQuestionId == null){
			return dailySurveyQuestion;
		}
		Question surveyQuestion = getQuestionDAO().load(surveyQuestionId,options);
		if(surveyQuestion != null){
			dailySurveyQuestion.setSurveyQuestion(surveyQuestion);
		}
		
 		
 		return dailySurveyQuestion;
 	}
 		
 
		
	protected void enhanceStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected DailySurveyQuestion extractStudentDailyAnswerList(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
		
		
		if(dailySurveyQuestion == null){
			return null;
		}
		if(dailySurveyQuestion.getId() == null){
			return dailySurveyQuestion;
		}

		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = getStudentDailyAnswerDAO().findStudentDailyAnswerByQuestion(dailySurveyQuestion.getId(),options);
		if(studentDailyAnswerList != null){
			enhanceStudentDailyAnswerList(studentDailyAnswerList,options);
			dailySurveyQuestion.setStudentDailyAnswerList(studentDailyAnswerList);
		}
		
		return dailySurveyQuestion;
	
	}	
	
	protected DailySurveyQuestion analyzeStudentDailyAnswerList(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
		
		
		if(dailySurveyQuestion == null){
			return null;
		}
		if(dailySurveyQuestion.getId() == null){
			return dailySurveyQuestion;
		}

		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = dailySurveyQuestion.getStudentDailyAnswerList();
		if(studentDailyAnswerList != null){
			getStudentDailyAnswerDAO().analyzeStudentDailyAnswerByQuestion(studentDailyAnswerList, dailySurveyQuestion.getId(), options);
			
		}
		
		return dailySurveyQuestion;
	
	}	
	
		
		
  	
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByQuestionType(String questionTypeId,Map<String,Object> options){
 	
  		SmartList<DailySurveyQuestion> resultList = queryWith(DailySurveyQuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options, getDailySurveyQuestionMapper());
		// analyzeDailySurveyQuestionByQuestionType(resultList, questionTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByQuestionType(String questionTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<DailySurveyQuestion> resultList =  queryWithRange(DailySurveyQuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options, getDailySurveyQuestionMapper(), start, count);
 		//analyzeDailySurveyQuestionByQuestionType(resultList, questionTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeDailySurveyQuestionByQuestionType(SmartList<DailySurveyQuestion> resultList, String questionTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countDailySurveyQuestionByQuestionType(String questionTypeId,Map<String,Object> options){

 		return countWith(DailySurveyQuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countDailySurveyQuestionByQuestionTypeIds(String[] ids, Map<String, Object> options) {
		return countWithIds(DailySurveyQuestionTable.COLUMN_QUESTION_TYPE, ids, options);
	}
 	
  	
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByClassDailyHealthSurvey(String classDailyHealthSurveyId,Map<String,Object> options){
 	
  		SmartList<DailySurveyQuestion> resultList = queryWith(DailySurveyQuestionTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, classDailyHealthSurveyId, options, getDailySurveyQuestionMapper());
		// analyzeDailySurveyQuestionByClassDailyHealthSurvey(resultList, classDailyHealthSurveyId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByClassDailyHealthSurvey(String classDailyHealthSurveyId, int start, int count,Map<String,Object> options){
 		
 		SmartList<DailySurveyQuestion> resultList =  queryWithRange(DailySurveyQuestionTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, classDailyHealthSurveyId, options, getDailySurveyQuestionMapper(), start, count);
 		//analyzeDailySurveyQuestionByClassDailyHealthSurvey(resultList, classDailyHealthSurveyId, options);
 		return resultList;
 		
 	}
 	public void analyzeDailySurveyQuestionByClassDailyHealthSurvey(SmartList<DailySurveyQuestion> resultList, String classDailyHealthSurveyId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countDailySurveyQuestionByClassDailyHealthSurvey(String classDailyHealthSurveyId,Map<String,Object> options){

 		return countWith(DailySurveyQuestionTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, classDailyHealthSurveyId, options);
 	}
 	@Override
	public Map<String, Integer> countDailySurveyQuestionByClassDailyHealthSurveyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(DailySurveyQuestionTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY, ids, options);
	}
 	
  	
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionBySurveyQuestion(String questionId,Map<String,Object> options){
 	
  		SmartList<DailySurveyQuestion> resultList = queryWith(DailySurveyQuestionTable.COLUMN_SURVEY_QUESTION, questionId, options, getDailySurveyQuestionMapper());
		// analyzeDailySurveyQuestionBySurveyQuestion(resultList, questionId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionBySurveyQuestion(String questionId, int start, int count,Map<String,Object> options){
 		
 		SmartList<DailySurveyQuestion> resultList =  queryWithRange(DailySurveyQuestionTable.COLUMN_SURVEY_QUESTION, questionId, options, getDailySurveyQuestionMapper(), start, count);
 		//analyzeDailySurveyQuestionBySurveyQuestion(resultList, questionId, options);
 		return resultList;
 		
 	}
 	public void analyzeDailySurveyQuestionBySurveyQuestion(SmartList<DailySurveyQuestion> resultList, String questionId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, questionId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countDailySurveyQuestionBySurveyQuestion(String questionId,Map<String,Object> options){

 		return countWith(DailySurveyQuestionTable.COLUMN_SURVEY_QUESTION, questionId, options);
 	}
 	@Override
	public Map<String, Integer> countDailySurveyQuestionBySurveyQuestionIds(String[] ids, Map<String, Object> options) {
		return countWithIds(DailySurveyQuestionTable.COLUMN_SURVEY_QUESTION, ids, options);
	}
 	
 	
		
		
		

	

	protected DailySurveyQuestion saveDailySurveyQuestion(DailySurveyQuestion  dailySurveyQuestion){
		
		if(!dailySurveyQuestion.isChanged()){
			return dailySurveyQuestion;
		}
		
		
		String SQL=this.getSaveDailySurveyQuestionSQL(dailySurveyQuestion);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveDailySurveyQuestionParameters(dailySurveyQuestion);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		dailySurveyQuestion.incVersion();
		return dailySurveyQuestion;
	
	}
	public SmartList<DailySurveyQuestion> saveDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitDailySurveyQuestionList(dailySurveyQuestionList);
		
		batchDailySurveyQuestionCreate((List<DailySurveyQuestion>)lists[CREATE_LIST_INDEX]);
		
		batchDailySurveyQuestionUpdate((List<DailySurveyQuestion>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList){
			if(dailySurveyQuestion.isChanged()){
				dailySurveyQuestion.incVersion();
			}
			
		
		}
		
		
		return dailySurveyQuestionList;
	}

	public SmartList<DailySurveyQuestion> removeDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options){
		
		
		super.removeList(dailySurveyQuestionList, options);
		
		return dailySurveyQuestionList;
		
		
	}
	
	protected List<Object[]> prepareDailySurveyQuestionBatchCreateArgs(List<DailySurveyQuestion> dailySurveyQuestionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList ){
			Object [] parameters = prepareDailySurveyQuestionCreateParameters(dailySurveyQuestion);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareDailySurveyQuestionBatchUpdateArgs(List<DailySurveyQuestion> dailySurveyQuestionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList ){
			if(!dailySurveyQuestion.isChanged()){
				continue;
			}
			Object [] parameters = prepareDailySurveyQuestionUpdateParameters(dailySurveyQuestion);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchDailySurveyQuestionCreate(List<DailySurveyQuestion> dailySurveyQuestionList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareDailySurveyQuestionBatchCreateArgs(dailySurveyQuestionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchDailySurveyQuestionUpdate(List<DailySurveyQuestion> dailySurveyQuestionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareDailySurveyQuestionBatchUpdateArgs(dailySurveyQuestionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitDailySurveyQuestionList(List<DailySurveyQuestion> dailySurveyQuestionList){
		
		List<DailySurveyQuestion> dailySurveyQuestionCreateList=new ArrayList<DailySurveyQuestion>();
		List<DailySurveyQuestion> dailySurveyQuestionUpdateList=new ArrayList<DailySurveyQuestion>();
		
		for(DailySurveyQuestion dailySurveyQuestion: dailySurveyQuestionList){
			if(isUpdateRequest(dailySurveyQuestion)){
				dailySurveyQuestionUpdateList.add( dailySurveyQuestion);
				continue;
			}
			dailySurveyQuestionCreateList.add(dailySurveyQuestion);
		}
		
		return new Object[]{dailySurveyQuestionCreateList,dailySurveyQuestionUpdateList};
	}
	
	protected boolean isUpdateRequest(DailySurveyQuestion dailySurveyQuestion){
 		return dailySurveyQuestion.getVersion() > 0;
 	}
 	protected String getSaveDailySurveyQuestionSQL(DailySurveyQuestion dailySurveyQuestion){
 		if(isUpdateRequest(dailySurveyQuestion)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveDailySurveyQuestionParameters(DailySurveyQuestion dailySurveyQuestion){
 		if(isUpdateRequest(dailySurveyQuestion) ){
 			return prepareDailySurveyQuestionUpdateParameters(dailySurveyQuestion);
 		}
 		return prepareDailySurveyQuestionCreateParameters(dailySurveyQuestion);
 	}
 	protected Object[] prepareDailySurveyQuestionUpdateParameters(DailySurveyQuestion dailySurveyQuestion){
 		Object[] parameters = new Object[11];
 
 		
 		parameters[0] = dailySurveyQuestion.getTopic();
 		 	
 		if(dailySurveyQuestion.getQuestionType() != null){
 			parameters[1] = dailySurveyQuestion.getQuestionType().getId();
 		}
 
 		
 		parameters[2] = dailySurveyQuestion.getOptionA();
 		
 		
 		parameters[3] = dailySurveyQuestion.getOptionB();
 		
 		
 		parameters[4] = dailySurveyQuestion.getOptionC();
 		
 		
 		parameters[5] = dailySurveyQuestion.getOptionD();
 		 	
 		if(dailySurveyQuestion.getClassDailyHealthSurvey() != null){
 			parameters[6] = dailySurveyQuestion.getClassDailyHealthSurvey().getId();
 		}
  	
 		if(dailySurveyQuestion.getSurveyQuestion() != null){
 			parameters[7] = dailySurveyQuestion.getSurveyQuestion().getId();
 		}
 		
 		parameters[8] = dailySurveyQuestion.nextVersion();
 		parameters[9] = dailySurveyQuestion.getId();
 		parameters[10] = dailySurveyQuestion.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareDailySurveyQuestionCreateParameters(DailySurveyQuestion dailySurveyQuestion){
		Object[] parameters = new Object[9];
		String newDailySurveyQuestionId=getNextId();
		dailySurveyQuestion.setId(newDailySurveyQuestionId);
		parameters[0] =  dailySurveyQuestion.getId();
 
 		
 		parameters[1] = dailySurveyQuestion.getTopic();
 		 	
 		if(dailySurveyQuestion.getQuestionType() != null){
 			parameters[2] = dailySurveyQuestion.getQuestionType().getId();
 		
 		}
 		
 		
 		parameters[3] = dailySurveyQuestion.getOptionA();
 		
 		
 		parameters[4] = dailySurveyQuestion.getOptionB();
 		
 		
 		parameters[5] = dailySurveyQuestion.getOptionC();
 		
 		
 		parameters[6] = dailySurveyQuestion.getOptionD();
 		 	
 		if(dailySurveyQuestion.getClassDailyHealthSurvey() != null){
 			parameters[7] = dailySurveyQuestion.getClassDailyHealthSurvey().getId();
 		
 		}
 		 	
 		if(dailySurveyQuestion.getSurveyQuestion() != null){
 			parameters[8] = dailySurveyQuestion.getSurveyQuestion().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected DailySurveyQuestion saveInternalDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
		
		saveDailySurveyQuestion(dailySurveyQuestion);
 	
 		if(isSaveQuestionTypeEnabled(options)){
	 		saveQuestionType(dailySurveyQuestion, options);
 		}
  	
 		if(isSaveClassDailyHealthSurveyEnabled(options)){
	 		saveClassDailyHealthSurvey(dailySurveyQuestion, options);
 		}
  	
 		if(isSaveSurveyQuestionEnabled(options)){
	 		saveSurveyQuestion(dailySurveyQuestion, options);
 		}
 
		
		if(isSaveStudentDailyAnswerListEnabled(options)){
	 		saveStudentDailyAnswerList(dailySurveyQuestion, options);
	 		//removeStudentDailyAnswerList(dailySurveyQuestion, options);
	 		//Not delete the record
	 		
 		}		
		
		return dailySurveyQuestion;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected DailySurveyQuestion saveQuestionType(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(dailySurveyQuestion.getQuestionType() == null){
 			return dailySurveyQuestion;//do nothing when it is null
 		}
 		
 		getQuestionTypeDAO().save(dailySurveyQuestion.getQuestionType(),options);
 		return dailySurveyQuestion;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected DailySurveyQuestion saveClassDailyHealthSurvey(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(dailySurveyQuestion.getClassDailyHealthSurvey() == null){
 			return dailySurveyQuestion;//do nothing when it is null
 		}
 		
 		getClassDailyHealthSurveyDAO().save(dailySurveyQuestion.getClassDailyHealthSurvey(),options);
 		return dailySurveyQuestion;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected DailySurveyQuestion saveSurveyQuestion(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(dailySurveyQuestion.getSurveyQuestion() == null){
 			return dailySurveyQuestion;//do nothing when it is null
 		}
 		
 		getQuestionDAO().save(dailySurveyQuestion.getSurveyQuestion(),options);
 		return dailySurveyQuestion;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public DailySurveyQuestion planToRemoveStudentDailyAnswerList(DailySurveyQuestion dailySurveyQuestion, String studentDailyAnswerIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, dailySurveyQuestion.getId());
		key.put(StudentDailyAnswer.ID_PROPERTY, studentDailyAnswerIds);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return dailySurveyQuestion;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return dailySurveyQuestion;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){

			studentDailyAnswerItem.clearFromAll();
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = dailySurveyQuestion.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return dailySurveyQuestion;	
	
	}


	//disconnect DailySurveyQuestion with student_health_survey in StudentDailyAnswer
	public DailySurveyQuestion planToRemoveStudentDailyAnswerListWithStudentHealthSurvey(DailySurveyQuestion dailySurveyQuestion, String studentHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, dailySurveyQuestion.getId());
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return dailySurveyQuestion;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return dailySurveyQuestion;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){
			studentDailyAnswerItem.clearStudentHealthSurvey();
			studentDailyAnswerItem.clearQuestion();
			
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = dailySurveyQuestion.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return dailySurveyQuestion;
	}
	
	public int countStudentDailyAnswerListWithStudentHealthSurvey(String dailySurveyQuestionId, String studentHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, dailySurveyQuestionId);
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
		
		int count = getStudentDailyAnswerDAO().countStudentDailyAnswerWithKey(key, options);
		return count;
	}
	

		
	protected DailySurveyQuestion saveStudentDailyAnswerList(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
		
		
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = dailySurveyQuestion.getStudentDailyAnswerList();
		if(studentDailyAnswerList == null){
			//null list means nothing
			return dailySurveyQuestion;
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
		
		
		return dailySurveyQuestion;
	
	}
	
	protected DailySurveyQuestion removeStudentDailyAnswerList(DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options){
	
	
		SmartList<StudentDailyAnswer> studentDailyAnswerList = dailySurveyQuestion.getStudentDailyAnswerList();
		if(studentDailyAnswerList == null){
			return dailySurveyQuestion;
		}	
	
		SmartList<StudentDailyAnswer> toRemoveStudentDailyAnswerList = studentDailyAnswerList.getToRemoveList();
		
		if(toRemoveStudentDailyAnswerList == null){
			return dailySurveyQuestion;
		}
		if(toRemoveStudentDailyAnswerList.isEmpty()){
			return dailySurveyQuestion;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDailyAnswerDAO().removeStudentDailyAnswerList(toRemoveStudentDailyAnswerList,options);
		
		return dailySurveyQuestion;
	
	}
	
	

 	
 	
	
	
	
		

	public DailySurveyQuestion present(DailySurveyQuestion dailySurveyQuestion,Map<String, Object> options){
	
		presentStudentDailyAnswerList(dailySurveyQuestion,options);

		return dailySurveyQuestion;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected DailySurveyQuestion presentStudentDailyAnswerList(
			DailySurveyQuestion dailySurveyQuestion,
			Map<String, Object> options) {

		SmartList<StudentDailyAnswer> studentDailyAnswerList = dailySurveyQuestion.getStudentDailyAnswerList();		
				SmartList<StudentDailyAnswer> newList= presentSubList(dailySurveyQuestion.getId(),
				studentDailyAnswerList,
				options,
				getStudentDailyAnswerDAO()::countStudentDailyAnswerByQuestion,
				getStudentDailyAnswerDAO()::findStudentDailyAnswerByQuestion
				);

		
		dailySurveyQuestion.setStudentDailyAnswerList(newList);
		

		return dailySurveyQuestion;
	}			
		

	
    public SmartList<DailySurveyQuestion> requestCandidateDailySurveyQuestionForStudentDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(DailySurveyQuestionTable.COLUMN_TOPIC, filterKey, pageNo, pageSize, getDailySurveyQuestionMapper());
    }
		

	protected String getTableName(){
		return DailySurveyQuestionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<DailySurveyQuestion> dailySurveyQuestionList) {		
		this.enhanceListInternal(dailySurveyQuestionList, this.getDailySurveyQuestionMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:StudentDailyAnswer的question的StudentDailyAnswerList
	public SmartList<StudentDailyAnswer> loadOurStudentDailyAnswerList(HealthUserContext userContext, List<DailySurveyQuestion> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentDailyAnswer> loadedObjs = userContext.getDAOGroup().getStudentDailyAnswerDAO().findStudentDailyAnswerWithKey(key, options);
		Map<String, List<StudentDailyAnswer>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getQuestion().getId()));
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
		List<DailySurveyQuestion> dailySurveyQuestionList = ownerEntity.collectRefsWithType(DailySurveyQuestion.INTERNAL_TYPE);
		this.enhanceList(dailySurveyQuestionList);
		
	}
	
	@Override
	public SmartList<DailySurveyQuestion> findDailySurveyQuestionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getDailySurveyQuestionMapper());

	}
	@Override
	public int countDailySurveyQuestionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countDailySurveyQuestionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<DailySurveyQuestion> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getDailySurveyQuestionMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


