
package com.doublechaintech.health.questiontype;

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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.question.Question;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.question.QuestionDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class QuestionTypeJDBCTemplateDAO extends HealthBaseDAOImpl implements QuestionTypeDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
	protected QuestionType load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalQuestionType(accessKey, options);
	}
	*/
	
	public SmartList<QuestionType> loadAll() {
	    return this.loadAll(getQuestionTypeMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public QuestionType load(String id,Map<String,Object> options) throws Exception{
		return loadInternalQuestionType(QuestionTypeTable.withId(id), options);
	}
	
	
	
	public QuestionType loadByCode(String code,Map<String,Object> options) throws Exception{
		return loadInternalQuestionType(QuestionTypeTable.withCode( code), options);
	}
	
	
	public QuestionType save(QuestionType questionType,Map<String,Object> options){
		
		String methodName="save(QuestionType questionType,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(questionType, methodName, "questionType");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalQuestionType(questionType,options);
	}
	public QuestionType clone(String questionTypeId, Map<String,Object> options) throws Exception{
	
		return clone(QuestionTypeTable.withId(questionTypeId),options);
	}
	
	protected QuestionType clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String questionTypeId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		QuestionType newQuestionType = loadInternalQuestionType(accessKey, options);
		newQuestionType.setVersion(0);
		
		
 		
 		if(isSaveQuestionListEnabled(options)){
 			for(Question item: newQuestionType.getQuestionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveDailySurveyQuestionListEnabled(options)){
 			for(DailySurveyQuestion item: newQuestionType.getDailySurveyQuestionList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalQuestionType(newQuestionType,options);
		
		return newQuestionType;
	}
	
	
	
	public QuestionType cloneByCode(String code,Map<String,Object> options) throws Exception{
		return clone(QuestionTypeTable.withCode( code), options);
	}
	
	
	

	protected void throwIfHasException(String questionTypeId,int version,int count) throws Exception{
		if (count == 1) {
			throw new QuestionTypeVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new QuestionTypeNotFoundException(
					"The " + this.getTableName() + "(" + questionTypeId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String questionTypeId, int version) throws Exception{
	
		String methodName="delete(String questionTypeId, int version)";
		assertMethodArgumentNotNull(questionTypeId, methodName, "questionTypeId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{questionTypeId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(questionTypeId,version);
		}
		
	
	}
	
	
	
	
	

	public QuestionType disconnectFromAll(String questionTypeId, int version) throws Exception{
	
		
		QuestionType questionType = loadInternalQuestionType(QuestionTypeTable.withId(questionTypeId), emptyOptions());
		questionType.clearFromAll();
		this.saveQuestionType(questionType);
		return questionType;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return QuestionTypeTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "question_type";
	}
	@Override
	protected String getBeanName() {
		
		return "questionType";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return QuestionTypeTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, QuestionTypeTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, QuestionTypeTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,QuestionTypeTokens.QUESTION_LIST);
 	}
 	protected boolean isAnalyzeQuestionListEnabled(Map<String,Object> options){		 		
 		return QuestionTypeTokens.of(options).analyzeQuestionListEnabled();
 	}
	
	protected boolean isSaveQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, QuestionTypeTokens.QUESTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractDailySurveyQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,QuestionTypeTokens.DAILY_SURVEY_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeDailySurveyQuestionListEnabled(Map<String,Object> options){		 		
 		return QuestionTypeTokens.of(options).analyzeDailySurveyQuestionListEnabled();
 	}
	
	protected boolean isSaveDailySurveyQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, QuestionTypeTokens.DAILY_SURVEY_QUESTION_LIST);
		
 	}
 	
		

	

	protected QuestionTypeMapper getQuestionTypeMapper(){
		return new QuestionTypeMapper();
	}

	
	
	protected QuestionType extractQuestionType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			QuestionType questionType = loadSingleObject(accessKey, getQuestionTypeMapper());
			return questionType;
		}catch(EmptyResultDataAccessException e){
			throw new QuestionTypeNotFoundException("QuestionType("+accessKey+") is not found!");
		}

	}

	
	

	protected QuestionType loadInternalQuestionType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		QuestionType questionType = extractQuestionType(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(questionType, loadOptions);
 		}
 
		
		if(isExtractQuestionListEnabled(loadOptions)){
	 		extractQuestionList(questionType, loadOptions);
 		}	
 		if(isAnalyzeQuestionListEnabled(loadOptions)){
	 		analyzeQuestionList(questionType, loadOptions);
 		}
 		
		
		if(isExtractDailySurveyQuestionListEnabled(loadOptions)){
	 		extractDailySurveyQuestionList(questionType, loadOptions);
 		}	
 		if(isAnalyzeDailySurveyQuestionListEnabled(loadOptions)){
	 		analyzeDailySurveyQuestionList(questionType, loadOptions);
 		}
 		
		
		return questionType;
		
	}

	 

 	protected QuestionType extractPlatform(QuestionType questionType, Map<String,Object> options) throws Exception{

		if(questionType.getPlatform() == null){
			return questionType;
		}
		String platformId = questionType.getPlatform().getId();
		if( platformId == null){
			return questionType;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			questionType.setPlatform(platform);
		}
		
 		
 		return questionType;
 	}
 		
 
		
	protected void enhanceQuestionList(SmartList<Question> questionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected QuestionType extractQuestionList(QuestionType questionType, Map<String,Object> options){
		
		
		if(questionType == null){
			return null;
		}
		if(questionType.getId() == null){
			return questionType;
		}

		
		
		SmartList<Question> questionList = getQuestionDAO().findQuestionByQuestionType(questionType.getId(),options);
		if(questionList != null){
			enhanceQuestionList(questionList,options);
			questionType.setQuestionList(questionList);
		}
		
		return questionType;
	
	}	
	
	protected QuestionType analyzeQuestionList(QuestionType questionType, Map<String,Object> options){
		
		
		if(questionType == null){
			return null;
		}
		if(questionType.getId() == null){
			return questionType;
		}

		
		
		SmartList<Question> questionList = questionType.getQuestionList();
		if(questionList != null){
			getQuestionDAO().analyzeQuestionByQuestionType(questionList, questionType.getId(), options);
			
		}
		
		return questionType;
	
	}	
	
		
	protected void enhanceDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected QuestionType extractDailySurveyQuestionList(QuestionType questionType, Map<String,Object> options){
		
		
		if(questionType == null){
			return null;
		}
		if(questionType.getId() == null){
			return questionType;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = getDailySurveyQuestionDAO().findDailySurveyQuestionByQuestionType(questionType.getId(),options);
		if(dailySurveyQuestionList != null){
			enhanceDailySurveyQuestionList(dailySurveyQuestionList,options);
			questionType.setDailySurveyQuestionList(dailySurveyQuestionList);
		}
		
		return questionType;
	
	}	
	
	protected QuestionType analyzeDailySurveyQuestionList(QuestionType questionType, Map<String,Object> options){
		
		
		if(questionType == null){
			return null;
		}
		if(questionType.getId() == null){
			return questionType;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();
		if(dailySurveyQuestionList != null){
			getDailySurveyQuestionDAO().analyzeDailySurveyQuestionByQuestionType(dailySurveyQuestionList, questionType.getId(), options);
			
		}
		
		return questionType;
	
	}	
	
		
		
  	
 	public SmartList<QuestionType> findQuestionTypeByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<QuestionType> resultList = queryWith(QuestionTypeTable.COLUMN_PLATFORM, platformId, options, getQuestionTypeMapper());
		// analyzeQuestionTypeByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<QuestionType> findQuestionTypeByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<QuestionType> resultList =  queryWithRange(QuestionTypeTable.COLUMN_PLATFORM, platformId, options, getQuestionTypeMapper(), start, count);
 		//analyzeQuestionTypeByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeQuestionTypeByPlatform(SmartList<QuestionType> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countQuestionTypeByPlatform(String platformId,Map<String,Object> options){

 		return countWith(QuestionTypeTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countQuestionTypeByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(QuestionTypeTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected QuestionType saveQuestionType(QuestionType  questionType){
		
		if(!questionType.isChanged()){
			return questionType;
		}
		
		
		String SQL=this.getSaveQuestionTypeSQL(questionType);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveQuestionTypeParameters(questionType);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		questionType.incVersion();
		return questionType;
	
	}
	public SmartList<QuestionType> saveQuestionTypeList(SmartList<QuestionType> questionTypeList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitQuestionTypeList(questionTypeList);
		
		batchQuestionTypeCreate((List<QuestionType>)lists[CREATE_LIST_INDEX]);
		
		batchQuestionTypeUpdate((List<QuestionType>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(QuestionType questionType:questionTypeList){
			if(questionType.isChanged()){
				questionType.incVersion();
			}
			
		
		}
		
		
		return questionTypeList;
	}

	public SmartList<QuestionType> removeQuestionTypeList(SmartList<QuestionType> questionTypeList,Map<String,Object> options){
		
		
		super.removeList(questionTypeList, options);
		
		return questionTypeList;
		
		
	}
	
	protected List<Object[]> prepareQuestionTypeBatchCreateArgs(List<QuestionType> questionTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(QuestionType questionType:questionTypeList ){
			Object [] parameters = prepareQuestionTypeCreateParameters(questionType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareQuestionTypeBatchUpdateArgs(List<QuestionType> questionTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(QuestionType questionType:questionTypeList ){
			if(!questionType.isChanged()){
				continue;
			}
			Object [] parameters = prepareQuestionTypeUpdateParameters(questionType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchQuestionTypeCreate(List<QuestionType> questionTypeList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareQuestionTypeBatchCreateArgs(questionTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchQuestionTypeUpdate(List<QuestionType> questionTypeList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareQuestionTypeBatchUpdateArgs(questionTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitQuestionTypeList(List<QuestionType> questionTypeList){
		
		List<QuestionType> questionTypeCreateList=new ArrayList<QuestionType>();
		List<QuestionType> questionTypeUpdateList=new ArrayList<QuestionType>();
		
		for(QuestionType questionType: questionTypeList){
			if(isUpdateRequest(questionType)){
				questionTypeUpdateList.add( questionType);
				continue;
			}
			questionTypeCreateList.add(questionType);
		}
		
		return new Object[]{questionTypeCreateList,questionTypeUpdateList};
	}
	
	protected boolean isUpdateRequest(QuestionType questionType){
 		return questionType.getVersion() > 0;
 	}
 	protected String getSaveQuestionTypeSQL(QuestionType questionType){
 		if(isUpdateRequest(questionType)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveQuestionTypeParameters(QuestionType questionType){
 		if(isUpdateRequest(questionType) ){
 			return prepareQuestionTypeUpdateParameters(questionType);
 		}
 		return prepareQuestionTypeCreateParameters(questionType);
 	}
 	protected Object[] prepareQuestionTypeUpdateParameters(QuestionType questionType){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = questionType.getName();
 		parameters[1] = questionType.getCode(); 	
 		if(questionType.getPlatform() != null){
 			parameters[2] = questionType.getPlatform().getId();
 		}
 		
 		parameters[3] = questionType.nextVersion();
 		parameters[4] = questionType.getId();
 		parameters[5] = questionType.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareQuestionTypeCreateParameters(QuestionType questionType){
		Object[] parameters = new Object[4];
		String newQuestionTypeId=getNextId();
		questionType.setId(newQuestionTypeId);
		parameters[0] =  questionType.getId();
 
 		parameters[1] = questionType.getName();
 		parameters[2] = questionType.getCode(); 	
 		if(questionType.getPlatform() != null){
 			parameters[3] = questionType.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected QuestionType saveInternalQuestionType(QuestionType questionType, Map<String,Object> options){
		
		saveQuestionType(questionType);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(questionType, options);
 		}
 
		
		if(isSaveQuestionListEnabled(options)){
	 		saveQuestionList(questionType, options);
	 		//removeQuestionList(questionType, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveDailySurveyQuestionListEnabled(options)){
	 		saveDailySurveyQuestionList(questionType, options);
	 		//removeDailySurveyQuestionList(questionType, options);
	 		//Not delete the record
	 		
 		}		
		
		return questionType;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected QuestionType savePlatform(QuestionType questionType, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(questionType.getPlatform() == null){
 			return questionType;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(questionType.getPlatform(),options);
 		return questionType;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public QuestionType planToRemoveQuestionList(QuestionType questionType, String questionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(Question.ID_PROPERTY, questionIds);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return questionType;
		}
		if(externalQuestionList.isEmpty()){
			return questionType;
		}
		
		for(Question questionItem: externalQuestionList){

			questionItem.clearFromAll();
		}
		
		
		SmartList<Question> questionList = questionType.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return questionType;	
	
	}


	//disconnect QuestionType with platform in Question
	public QuestionType planToRemoveQuestionListWithPlatform(QuestionType questionType, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(Question.PLATFORM_PROPERTY, platformId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return questionType;
		}
		if(externalQuestionList.isEmpty()){
			return questionType;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearPlatform();
			questionItem.clearQuestionType();
			
		}
		
		
		SmartList<Question> questionList = questionType.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return questionType;
	}
	
	public int countQuestionListWithPlatform(String questionTypeId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		key.put(Question.PLATFORM_PROPERTY, platformId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect QuestionType with creator in Question
	public QuestionType planToRemoveQuestionListWithCreator(QuestionType questionType, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(Question.CREATOR_PROPERTY, creatorId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return questionType;
		}
		if(externalQuestionList.isEmpty()){
			return questionType;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearCreator();
			questionItem.clearQuestionType();
			
		}
		
		
		SmartList<Question> questionList = questionType.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return questionType;
	}
	
	public int countQuestionListWithCreator(String questionTypeId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		key.put(Question.CREATOR_PROPERTY, creatorId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect QuestionType with cq in Question
	public QuestionType planToRemoveQuestionListWithCq(QuestionType questionType, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(Question.CQ_PROPERTY, cqId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return questionType;
		}
		if(externalQuestionList.isEmpty()){
			return questionType;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearCq();
			questionItem.clearQuestionType();
			
		}
		
		
		SmartList<Question> questionList = questionType.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return questionType;
	}
	
	public int countQuestionListWithCq(String questionTypeId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		key.put(Question.CQ_PROPERTY, cqId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	public QuestionType planToRemoveDailySurveyQuestionList(QuestionType questionType, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(DailySurveyQuestion.ID_PROPERTY, dailySurveyQuestionIds);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return questionType;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return questionType;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){

			dailySurveyQuestionItem.clearFromAll();
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return questionType;	
	
	}


	//disconnect QuestionType with class_daily_health_survey in DailySurveyQuestion
	public QuestionType planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(QuestionType questionType, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return questionType;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return questionType;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearClassDailyHealthSurvey();
			dailySurveyQuestionItem.clearQuestionType();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return questionType;
	}
	
	public int countDailySurveyQuestionListWithClassDailyHealthSurvey(String questionTypeId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect QuestionType with survey_question in DailySurveyQuestion
	public QuestionType planToRemoveDailySurveyQuestionListWithSurveyQuestion(QuestionType questionType, String surveyQuestionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionType.getId());
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, surveyQuestionId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return questionType;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return questionType;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearSurveyQuestion();
			dailySurveyQuestionItem.clearQuestionType();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return questionType;
	}
	
	public int countDailySurveyQuestionListWithSurveyQuestion(String questionTypeId, String surveyQuestionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		key.put(DailySurveyQuestion.SURVEY_QUESTION_PROPERTY, surveyQuestionId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	

		
	protected QuestionType saveQuestionList(QuestionType questionType, Map<String,Object> options){
		
		
		
		
		SmartList<Question> questionList = questionType.getQuestionList();
		if(questionList == null){
			//null list means nothing
			return questionType;
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
		
		
		return questionType;
	
	}
	
	protected QuestionType removeQuestionList(QuestionType questionType, Map<String,Object> options){
	
	
		SmartList<Question> questionList = questionType.getQuestionList();
		if(questionList == null){
			return questionType;
		}	
	
		SmartList<Question> toRemoveQuestionList = questionList.getToRemoveList();
		
		if(toRemoveQuestionList == null){
			return questionType;
		}
		if(toRemoveQuestionList.isEmpty()){
			return questionType;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getQuestionDAO().removeQuestionList(toRemoveQuestionList,options);
		
		return questionType;
	
	}
	
	

 	
 	
	
	
	
		
	protected QuestionType saveDailySurveyQuestionList(QuestionType questionType, Map<String,Object> options){
		
		
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			//null list means nothing
			return questionType;
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
		
		
		return questionType;
	
	}
	
	protected QuestionType removeDailySurveyQuestionList(QuestionType questionType, Map<String,Object> options){
	
	
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			return questionType;
		}	
	
		SmartList<DailySurveyQuestion> toRemoveDailySurveyQuestionList = dailySurveyQuestionList.getToRemoveList();
		
		if(toRemoveDailySurveyQuestionList == null){
			return questionType;
		}
		if(toRemoveDailySurveyQuestionList.isEmpty()){
			return questionType;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getDailySurveyQuestionDAO().removeDailySurveyQuestionList(toRemoveDailySurveyQuestionList,options);
		
		return questionType;
	
	}
	
	

 	
 	
	
	
	
		

	public QuestionType present(QuestionType questionType,Map<String, Object> options){
	
		presentQuestionList(questionType,options);
		presentDailySurveyQuestionList(questionType,options);

		return questionType;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected QuestionType presentQuestionList(
			QuestionType questionType,
			Map<String, Object> options) {

		SmartList<Question> questionList = questionType.getQuestionList();		
				SmartList<Question> newList= presentSubList(questionType.getId(),
				questionList,
				options,
				getQuestionDAO()::countQuestionByQuestionType,
				getQuestionDAO()::findQuestionByQuestionType
				);

		
		questionType.setQuestionList(newList);
		

		return questionType;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected QuestionType presentDailySurveyQuestionList(
			QuestionType questionType,
			Map<String, Object> options) {

		SmartList<DailySurveyQuestion> dailySurveyQuestionList = questionType.getDailySurveyQuestionList();		
				SmartList<DailySurveyQuestion> newList= presentSubList(questionType.getId(),
				dailySurveyQuestionList,
				options,
				getDailySurveyQuestionDAO()::countDailySurveyQuestionByQuestionType,
				getDailySurveyQuestionDAO()::findDailySurveyQuestionByQuestionType
				);

		
		questionType.setDailySurveyQuestionList(newList);
		

		return questionType;
	}			
		

	
    public SmartList<QuestionType> requestCandidateQuestionTypeForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(QuestionTypeTable.COLUMN_NAME, filterKey, pageNo, pageSize, getQuestionTypeMapper());
    }
		
    public SmartList<QuestionType> requestCandidateQuestionTypeForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(QuestionTypeTable.COLUMN_NAME, filterKey, pageNo, pageSize, getQuestionTypeMapper());
    }
		

	protected String getTableName(){
		return QuestionTypeTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<QuestionType> questionTypeList) {		
		this.enhanceListInternal(questionTypeList, this.getQuestionTypeMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Question的questionType的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<QuestionType> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.QUESTION_TYPE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Question> loadedObjs = userContext.getDAOGroup().getQuestionDAO().findQuestionWithKey(key, options);
		Map<String, List<Question>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getQuestionType().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的questionType的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<QuestionType> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<DailySurveyQuestion> loadedObjs = userContext.getDAOGroup().getDailySurveyQuestionDAO().findDailySurveyQuestionWithKey(key, options);
		Map<String, List<DailySurveyQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getQuestionType().getId()));
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
		List<QuestionType> questionTypeList = ownerEntity.collectRefsWithType(QuestionType.INTERNAL_TYPE);
		this.enhanceList(questionTypeList);
		
	}
	
	@Override
	public SmartList<QuestionType> findQuestionTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getQuestionTypeMapper());

	}
	@Override
	public int countQuestionTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countQuestionTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<QuestionType> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getQuestionTypeMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


