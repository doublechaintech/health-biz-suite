
package com.doublechaintech.health.question;

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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.user.UserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class QuestionJDBCTemplateDAO extends HealthBaseDAOImpl implements QuestionDAO{
 
 	
 	private  UserDAO  userDAO;
 	public void setUserDAO(UserDAO userDAO){
	 	this.userDAO = userDAO;
 	}
 	public UserDAO getUserDAO(){
	 	return this.userDAO;
 	}
 
 	
 	private  QuestionTypeDAO  questionTypeDAO;
 	public void setQuestionTypeDAO(QuestionTypeDAO questionTypeDAO){
	 	this.questionTypeDAO = questionTypeDAO;
 	}
 	public QuestionTypeDAO getQuestionTypeDAO(){
	 	return this.questionTypeDAO;
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
 	
			
		

	
	/*
	protected Question load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalQuestion(accessKey, options);
	}
	*/
	
	public SmartList<Question> loadAll() {
	    return this.loadAll(getQuestionMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Question load(String id,Map<String,Object> options) throws Exception{
		return loadInternalQuestion(QuestionTable.withId(id), options);
	}
	
	
	
	public Question save(Question question,Map<String,Object> options){
		
		String methodName="save(Question question,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(question, methodName, "question");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalQuestion(question,options);
	}
	public Question clone(String questionId, Map<String,Object> options) throws Exception{
	
		return clone(QuestionTable.withId(questionId),options);
	}
	
	protected Question clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String questionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Question newQuestion = loadInternalQuestion(accessKey, options);
		newQuestion.setVersion(0);
		
		
 		
 		if(isSaveDailySurveyQuestionListEnabled(options)){
 			for(DailySurveyQuestion item: newQuestion.getDailySurveyQuestionList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalQuestion(newQuestion,options);
		
		return newQuestion;
	}
	
	
	
	

	protected void throwIfHasException(String questionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new QuestionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new QuestionNotFoundException(
					"The " + this.getTableName() + "(" + questionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String questionId, int version) throws Exception{
	
		String methodName="delete(String questionId, int version)";
		assertMethodArgumentNotNull(questionId, methodName, "questionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{questionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(questionId,version);
		}
		
	
	}
	
	
	
	
	

	public Question disconnectFromAll(String questionId, int version) throws Exception{
	
		
		Question question = loadInternalQuestion(QuestionTable.withId(questionId), emptyOptions());
		question.clearFromAll();
		this.saveQuestion(question);
		return question;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return QuestionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "question";
	}
	@Override
	protected String getBeanName() {
		
		return "question";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return QuestionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractQuestionTypeEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, QuestionTokens.QUESTIONTYPE);
 	}

 	protected boolean isSaveQuestionTypeEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, QuestionTokens.QUESTIONTYPE);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, QuestionTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, QuestionTokens.PLATFORM);
 	}
 	

 	
  

 	protected boolean isExtractCreatorEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, QuestionTokens.CREATOR);
 	}

 	protected boolean isSaveCreatorEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, QuestionTokens.CREATOR);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, QuestionTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, QuestionTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractDailySurveyQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,QuestionTokens.DAILY_SURVEY_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeDailySurveyQuestionListEnabled(Map<String,Object> options){		 		
 		return QuestionTokens.of(options).analyzeDailySurveyQuestionListEnabled();
 	}
	
	protected boolean isSaveDailySurveyQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, QuestionTokens.DAILY_SURVEY_QUESTION_LIST);
		
 	}
 	
		

	

	protected QuestionMapper getQuestionMapper(){
		return new QuestionMapper();
	}

	
	
	protected Question extractQuestion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Question question = loadSingleObject(accessKey, getQuestionMapper());
			return question;
		}catch(EmptyResultDataAccessException e){
			throw new QuestionNotFoundException("Question("+accessKey+") is not found!");
		}

	}

	
	

	protected Question loadInternalQuestion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Question question = extractQuestion(accessKey, loadOptions);
 	
 		if(isExtractQuestionTypeEnabled(loadOptions)){
	 		extractQuestionType(question, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(question, loadOptions);
 		}
  	
 		if(isExtractCreatorEnabled(loadOptions)){
	 		extractCreator(question, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(question, loadOptions);
 		}
 
		
		if(isExtractDailySurveyQuestionListEnabled(loadOptions)){
	 		extractDailySurveyQuestionList(question, loadOptions);
 		}	
 		if(isAnalyzeDailySurveyQuestionListEnabled(loadOptions)){
	 		analyzeDailySurveyQuestionList(question, loadOptions);
 		}
 		
		
		return question;
		
	}

	 

 	protected Question extractQuestionType(Question question, Map<String,Object> options) throws Exception{

		if(question.getQuestionType() == null){
			return question;
		}
		String questionTypeId = question.getQuestionType().getId();
		if( questionTypeId == null){
			return question;
		}
		QuestionType questionType = getQuestionTypeDAO().load(questionTypeId,options);
		if(questionType != null){
			question.setQuestionType(questionType);
		}
		
 		
 		return question;
 	}
 		
  

 	protected Question extractPlatform(Question question, Map<String,Object> options) throws Exception{

		if(question.getPlatform() == null){
			return question;
		}
		String platformId = question.getPlatform().getId();
		if( platformId == null){
			return question;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			question.setPlatform(platform);
		}
		
 		
 		return question;
 	}
 		
  

 	protected Question extractCreator(Question question, Map<String,Object> options) throws Exception{

		if(question.getCreator() == null){
			return question;
		}
		String creatorId = question.getCreator().getId();
		if( creatorId == null){
			return question;
		}
		User creator = getUserDAO().load(creatorId,options);
		if(creator != null){
			question.setCreator(creator);
		}
		
 		
 		return question;
 	}
 		
  

 	protected Question extractCq(Question question, Map<String,Object> options) throws Exception{

		if(question.getCq() == null){
			return question;
		}
		String cqId = question.getCq().getId();
		if( cqId == null){
			return question;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			question.setCq(cq);
		}
		
 		
 		return question;
 	}
 		
 
		
	protected void enhanceDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Question extractDailySurveyQuestionList(Question question, Map<String,Object> options){
		
		
		if(question == null){
			return null;
		}
		if(question.getId() == null){
			return question;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = getDailySurveyQuestionDAO().findDailySurveyQuestionBySurveyQuestion(question.getId(),options);
		if(dailySurveyQuestionList != null){
			enhanceDailySurveyQuestionList(dailySurveyQuestionList,options);
			question.setDailySurveyQuestionList(dailySurveyQuestionList);
		}
		
		return question;
	
	}	
	
	protected Question analyzeDailySurveyQuestionList(Question question, Map<String,Object> options){
		
		
		if(question == null){
			return null;
		}
		if(question.getId() == null){
			return question;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();
		if(dailySurveyQuestionList != null){
			getDailySurveyQuestionDAO().analyzeDailySurveyQuestionBySurveyQuestion(dailySurveyQuestionList, question.getId(), options);
			
		}
		
		return question;
	
	}	
	
		
		
  	
 	public SmartList<Question> findQuestionByQuestionType(String questionTypeId,Map<String,Object> options){
 	
  		SmartList<Question> resultList = queryWith(QuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options, getQuestionMapper());
		// analyzeQuestionByQuestionType(resultList, questionTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Question> findQuestionByQuestionType(String questionTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Question> resultList =  queryWithRange(QuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options, getQuestionMapper(), start, count);
 		//analyzeQuestionByQuestionType(resultList, questionTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeQuestionByQuestionType(SmartList<Question> resultList, String questionTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countQuestionByQuestionType(String questionTypeId,Map<String,Object> options){

 		return countWith(QuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countQuestionByQuestionTypeIds(String[] ids, Map<String, Object> options) {
		return countWithIds(QuestionTable.COLUMN_QUESTION_TYPE, ids, options);
	}
 	
  	
 	public SmartList<Question> findQuestionByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Question> resultList = queryWith(QuestionTable.COLUMN_PLATFORM, platformId, options, getQuestionMapper());
		// analyzeQuestionByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Question> findQuestionByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Question> resultList =  queryWithRange(QuestionTable.COLUMN_PLATFORM, platformId, options, getQuestionMapper(), start, count);
 		//analyzeQuestionByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeQuestionByPlatform(SmartList<Question> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Question.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countQuestionByPlatform(String platformId,Map<String,Object> options){

 		return countWith(QuestionTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countQuestionByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(QuestionTable.COLUMN_PLATFORM, ids, options);
	}
 	
  	
 	public SmartList<Question> findQuestionByCreator(String userId,Map<String,Object> options){
 	
  		SmartList<Question> resultList = queryWith(QuestionTable.COLUMN_CREATOR, userId, options, getQuestionMapper());
		// analyzeQuestionByCreator(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Question> findQuestionByCreator(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Question> resultList =  queryWithRange(QuestionTable.COLUMN_CREATOR, userId, options, getQuestionMapper(), start, count);
 		//analyzeQuestionByCreator(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzeQuestionByCreator(SmartList<Question> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Question.CREATOR_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countQuestionByCreator(String userId,Map<String,Object> options){

 		return countWith(QuestionTable.COLUMN_CREATOR, userId, options);
 	}
 	@Override
	public Map<String, Integer> countQuestionByCreatorIds(String[] ids, Map<String, Object> options) {
		return countWithIds(QuestionTable.COLUMN_CREATOR, ids, options);
	}
 	
  	
 	public SmartList<Question> findQuestionByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<Question> resultList = queryWith(QuestionTable.COLUMN_CQ, changeRequestId, options, getQuestionMapper());
		// analyzeQuestionByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Question> findQuestionByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Question> resultList =  queryWithRange(QuestionTable.COLUMN_CQ, changeRequestId, options, getQuestionMapper(), start, count);
 		//analyzeQuestionByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeQuestionByCq(SmartList<Question> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Question.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countQuestionByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(QuestionTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countQuestionByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(QuestionTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected Question saveQuestion(Question  question){
		
		if(!question.isChanged()){
			return question;
		}
		
		
		String SQL=this.getSaveQuestionSQL(question);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveQuestionParameters(question);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		question.incVersion();
		return question;
	
	}
	public SmartList<Question> saveQuestionList(SmartList<Question> questionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitQuestionList(questionList);
		
		batchQuestionCreate((List<Question>)lists[CREATE_LIST_INDEX]);
		
		batchQuestionUpdate((List<Question>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Question question:questionList){
			if(question.isChanged()){
				question.incVersion();
			}
			
		
		}
		
		
		return questionList;
	}

	public SmartList<Question> removeQuestionList(SmartList<Question> questionList,Map<String,Object> options){
		
		
		super.removeList(questionList, options);
		
		return questionList;
		
		
	}
	
	protected List<Object[]> prepareQuestionBatchCreateArgs(List<Question> questionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Question question:questionList ){
			Object [] parameters = prepareQuestionCreateParameters(question);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareQuestionBatchUpdateArgs(List<Question> questionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Question question:questionList ){
			if(!question.isChanged()){
				continue;
			}
			Object [] parameters = prepareQuestionUpdateParameters(question);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchQuestionCreate(List<Question> questionList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareQuestionBatchCreateArgs(questionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchQuestionUpdate(List<Question> questionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareQuestionBatchUpdateArgs(questionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitQuestionList(List<Question> questionList){
		
		List<Question> questionCreateList=new ArrayList<Question>();
		List<Question> questionUpdateList=new ArrayList<Question>();
		
		for(Question question: questionList){
			if(isUpdateRequest(question)){
				questionUpdateList.add( question);
				continue;
			}
			questionCreateList.add(question);
		}
		
		return new Object[]{questionCreateList,questionUpdateList};
	}
	
	protected boolean isUpdateRequest(Question question){
 		return question.getVersion() > 0;
 	}
 	protected String getSaveQuestionSQL(Question question){
 		if(isUpdateRequest(question)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveQuestionParameters(Question question){
 		if(isUpdateRequest(question) ){
 			return prepareQuestionUpdateParameters(question);
 		}
 		return prepareQuestionCreateParameters(question);
 	}
 	protected Object[] prepareQuestionUpdateParameters(Question question){
 		Object[] parameters = new Object[12];
 
 		
 		parameters[0] = question.getTopic();
 		 	
 		if(question.getQuestionType() != null){
 			parameters[1] = question.getQuestionType().getId();
 		}
 
 		
 		parameters[2] = question.getOptionA();
 		
 		
 		parameters[3] = question.getOptionB();
 		
 		
 		parameters[4] = question.getOptionC();
 		
 		
 		parameters[5] = question.getOptionD();
 		 	
 		if(question.getPlatform() != null){
 			parameters[6] = question.getPlatform().getId();
 		}
  	
 		if(question.getCreator() != null){
 			parameters[7] = question.getCreator().getId();
 		}
  	
 		if(question.getCq() != null){
 			parameters[8] = question.getCq().getId();
 		}
 		
 		parameters[9] = question.nextVersion();
 		parameters[10] = question.getId();
 		parameters[11] = question.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareQuestionCreateParameters(Question question){
		Object[] parameters = new Object[10];
		String newQuestionId=getNextId();
		question.setId(newQuestionId);
		parameters[0] =  question.getId();
 
 		
 		parameters[1] = question.getTopic();
 		 	
 		if(question.getQuestionType() != null){
 			parameters[2] = question.getQuestionType().getId();
 		
 		}
 		
 		
 		parameters[3] = question.getOptionA();
 		
 		
 		parameters[4] = question.getOptionB();
 		
 		
 		parameters[5] = question.getOptionC();
 		
 		
 		parameters[6] = question.getOptionD();
 		 	
 		if(question.getPlatform() != null){
 			parameters[7] = question.getPlatform().getId();
 		
 		}
 		 	
 		if(question.getCreator() != null){
 			parameters[8] = question.getCreator().getId();
 		
 		}
 		 	
 		if(question.getCq() != null){
 			parameters[9] = question.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Question saveInternalQuestion(Question question, Map<String,Object> options){
		
		saveQuestion(question);
 	
 		if(isSaveQuestionTypeEnabled(options)){
	 		saveQuestionType(question, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(question, options);
 		}
  	
 		if(isSaveCreatorEnabled(options)){
	 		saveCreator(question, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(question, options);
 		}
 
		
		if(isSaveDailySurveyQuestionListEnabled(options)){
	 		saveDailySurveyQuestionList(question, options);
	 		//removeDailySurveyQuestionList(question, options);
	 		//Not delete the record
	 		
 		}		
		
		return question;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Question saveQuestionType(Question question, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(question.getQuestionType() == null){
 			return question;//do nothing when it is null
 		}
 		
 		getQuestionTypeDAO().save(question.getQuestionType(),options);
 		return question;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Question savePlatform(Question question, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(question.getPlatform() == null){
 			return question;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(question.getPlatform(),options);
 		return question;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Question saveCreator(Question question, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(question.getCreator() == null){
 			return question;//do nothing when it is null
 		}
 		
 		getUserDAO().save(question.getCreator(),options);
 		return question;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Question saveCq(Question question, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(question.getCq() == null){
 			return question;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(question.getCq(),options);
 		return question;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Question planToRemoveDailySurveyQuestionList(Question question, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, question.getId());
		key.put(DailySurveyQuestion.ID_PROPERTY, dailySurveyQuestionIds);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return question;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return question;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){

			dailySurveyQuestionItem.clearFromAll();
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return question;	
	
	}


	//disconnect Question with question_type in DailySurveyQuestion
	public Question planToRemoveDailySurveyQuestionListWithQuestionType(Question question, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, question.getId());
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return question;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return question;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearQuestionType();
			dailySurveyQuestionItem.clearSurveyQuestion();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return question;
	}
	
	public int countDailySurveyQuestionListWithQuestionType(String questionId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, questionId);
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect Question with class_daily_health_survey in DailySurveyQuestion
	public Question planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(Question question, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, question.getId());
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return question;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return question;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearClassDailyHealthSurvey();
			dailySurveyQuestionItem.clearSurveyQuestion();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return question;
	}
	
	public int countDailySurveyQuestionListWithClassDailyHealthSurvey(String questionId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, questionId);
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	

		
	protected Question saveDailySurveyQuestionList(Question question, Map<String,Object> options){
		
		
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			//null list means nothing
			return question;
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
		
		
		return question;
	
	}
	
	protected Question removeDailySurveyQuestionList(Question question, Map<String,Object> options){
	
	
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			return question;
		}	
	
		SmartList<DailySurveyQuestion> toRemoveDailySurveyQuestionList = dailySurveyQuestionList.getToRemoveList();
		
		if(toRemoveDailySurveyQuestionList == null){
			return question;
		}
		if(toRemoveDailySurveyQuestionList.isEmpty()){
			return question;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getDailySurveyQuestionDAO().removeDailySurveyQuestionList(toRemoveDailySurveyQuestionList,options);
		
		return question;
	
	}
	
	

 	
 	
	
	
	
		

	public Question present(Question question,Map<String, Object> options){
	
		presentDailySurveyQuestionList(question,options);

		return question;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Question presentDailySurveyQuestionList(
			Question question,
			Map<String, Object> options) {

		SmartList<DailySurveyQuestion> dailySurveyQuestionList = question.getDailySurveyQuestionList();		
				SmartList<DailySurveyQuestion> newList= presentSubList(question.getId(),
				dailySurveyQuestionList,
				options,
				getDailySurveyQuestionDAO()::countDailySurveyQuestionBySurveyQuestion,
				getDailySurveyQuestionDAO()::findDailySurveyQuestionBySurveyQuestion
				);

		
		question.setDailySurveyQuestionList(newList);
		

		return question;
	}			
		

	
    public SmartList<Question> requestCandidateQuestionForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(QuestionTable.COLUMN_TOPIC, filterKey, pageNo, pageSize, getQuestionMapper());
    }
		

	protected String getTableName(){
		return QuestionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Question> questionList) {		
		this.enhanceListInternal(questionList, this.getQuestionMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的surveyQuestion的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<Question> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<DailySurveyQuestion> loadedObjs = userContext.getDAOGroup().getDailySurveyQuestionDAO().findDailySurveyQuestionWithKey(key, options);
		Map<String, List<DailySurveyQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSurveyQuestion().getId()));
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
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Question> questionList = ownerEntity.collectRefsWithType(Question.INTERNAL_TYPE);
		this.enhanceList(questionList);
		
	}
	
	@Override
	public SmartList<Question> findQuestionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getQuestionMapper());

	}
	@Override
	public int countQuestionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countQuestionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Question> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getQuestionMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


