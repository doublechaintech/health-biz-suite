
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
import com.doublechaintech.health.questiontype.QuestionType;

import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.platform.PlatformDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class QuestionJDBCTemplateDAO extends HealthBaseDAOImpl implements QuestionDAO{
 
 	
 	private  QuestionTypeDAO  questionTypeDAO;
 	public void setQuestionTypeDAO(QuestionTypeDAO questionTypeDAO){
	 	this.questionTypeDAO = questionTypeDAO;
 	}
 	public QuestionTypeDAO getQuestionTypeDAO(){
	 	return this.questionTypeDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
 		Object[] parameters = new Object[10];
 
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
 		
 		parameters[7] = question.nextVersion();
 		parameters[8] = question.getId();
 		parameters[9] = question.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareQuestionCreateParameters(Question question){
		Object[] parameters = new Object[8];
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
 	
 	
 	
 	 
	
 

	

		

	public Question present(Question question,Map<String, Object> options){
	

		return question;
	
	}
		

	

	protected String getTableName(){
		return QuestionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Question> questionList) {		
		this.enhanceListInternal(questionList, this.getQuestionMapper());
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


