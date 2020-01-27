
package com.doublechaintech.health.questionsource;

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
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class QuestionSourceJDBCTemplateDAO extends HealthBaseDAOImpl implements QuestionSourceDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
 	
			
		

	
	/*
	protected QuestionSource load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalQuestionSource(accessKey, options);
	}
	*/
	
	public SmartList<QuestionSource> loadAll() {
	    return this.loadAll(getQuestionSourceMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public QuestionSource load(String id,Map<String,Object> options) throws Exception{
		return loadInternalQuestionSource(QuestionSourceTable.withId(id), options);
	}
	
	
	
	public QuestionSource loadByCode(String code,Map<String,Object> options) throws Exception{
		return loadInternalQuestionSource(QuestionSourceTable.withCode( code), options);
	}
	
	
	public QuestionSource save(QuestionSource questionSource,Map<String,Object> options){
		
		String methodName="save(QuestionSource questionSource,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(questionSource, methodName, "questionSource");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalQuestionSource(questionSource,options);
	}
	public QuestionSource clone(String questionSourceId, Map<String,Object> options) throws Exception{
	
		return clone(QuestionSourceTable.withId(questionSourceId),options);
	}
	
	protected QuestionSource clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String questionSourceId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		QuestionSource newQuestionSource = loadInternalQuestionSource(accessKey, options);
		newQuestionSource.setVersion(0);
		
		
 		
 		if(isSaveClassQuestionListEnabled(options)){
 			for(ClassQuestion item: newQuestionSource.getClassQuestionList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalQuestionSource(newQuestionSource,options);
		
		return newQuestionSource;
	}
	
	
	
	public QuestionSource cloneByCode(String code,Map<String,Object> options) throws Exception{
		return clone(QuestionSourceTable.withCode( code), options);
	}
	
	
	

	protected void throwIfHasException(String questionSourceId,int version,int count) throws Exception{
		if (count == 1) {
			throw new QuestionSourceVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new QuestionSourceNotFoundException(
					"The " + this.getTableName() + "(" + questionSourceId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String questionSourceId, int version) throws Exception{
	
		String methodName="delete(String questionSourceId, int version)";
		assertMethodArgumentNotNull(questionSourceId, methodName, "questionSourceId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{questionSourceId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(questionSourceId,version);
		}
		
	
	}
	
	
	
	
	

	public QuestionSource disconnectFromAll(String questionSourceId, int version) throws Exception{
	
		
		QuestionSource questionSource = loadInternalQuestionSource(QuestionSourceTable.withId(questionSourceId), emptyOptions());
		questionSource.clearFromAll();
		this.saveQuestionSource(questionSource);
		return questionSource;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return QuestionSourceTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "question_source";
	}
	@Override
	protected String getBeanName() {
		
		return "questionSource";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return QuestionSourceTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, QuestionSourceTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, QuestionSourceTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractClassQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,QuestionSourceTokens.CLASS_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeClassQuestionListEnabled(Map<String,Object> options){		 		
 		return QuestionSourceTokens.of(options).analyzeClassQuestionListEnabled();
 	}
	
	protected boolean isSaveClassQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, QuestionSourceTokens.CLASS_QUESTION_LIST);
		
 	}
 	
		

	

	protected QuestionSourceMapper getQuestionSourceMapper(){
		return new QuestionSourceMapper();
	}

	
	
	protected QuestionSource extractQuestionSource(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			QuestionSource questionSource = loadSingleObject(accessKey, getQuestionSourceMapper());
			return questionSource;
		}catch(EmptyResultDataAccessException e){
			throw new QuestionSourceNotFoundException("QuestionSource("+accessKey+") is not found!");
		}

	}

	
	

	protected QuestionSource loadInternalQuestionSource(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		QuestionSource questionSource = extractQuestionSource(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(questionSource, loadOptions);
 		}
 
		
		if(isExtractClassQuestionListEnabled(loadOptions)){
	 		extractClassQuestionList(questionSource, loadOptions);
 		}	
 		if(isAnalyzeClassQuestionListEnabled(loadOptions)){
	 		analyzeClassQuestionList(questionSource, loadOptions);
 		}
 		
		
		return questionSource;
		
	}

	 

 	protected QuestionSource extractPlatform(QuestionSource questionSource, Map<String,Object> options) throws Exception{

		if(questionSource.getPlatform() == null){
			return questionSource;
		}
		String platformId = questionSource.getPlatform().getId();
		if( platformId == null){
			return questionSource;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			questionSource.setPlatform(platform);
		}
		
 		
 		return questionSource;
 	}
 		
 
		
	protected void enhanceClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected QuestionSource extractClassQuestionList(QuestionSource questionSource, Map<String,Object> options){
		
		
		if(questionSource == null){
			return null;
		}
		if(questionSource.getId() == null){
			return questionSource;
		}

		
		
		SmartList<ClassQuestion> classQuestionList = getClassQuestionDAO().findClassQuestionByQuestionSource(questionSource.getId(),options);
		if(classQuestionList != null){
			enhanceClassQuestionList(classQuestionList,options);
			questionSource.setClassQuestionList(classQuestionList);
		}
		
		return questionSource;
	
	}	
	
	protected QuestionSource analyzeClassQuestionList(QuestionSource questionSource, Map<String,Object> options){
		
		
		if(questionSource == null){
			return null;
		}
		if(questionSource.getId() == null){
			return questionSource;
		}

		
		
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();
		if(classQuestionList != null){
			getClassQuestionDAO().analyzeClassQuestionByQuestionSource(classQuestionList, questionSource.getId(), options);
			
		}
		
		return questionSource;
	
	}	
	
		
		
  	
 	public SmartList<QuestionSource> findQuestionSourceByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<QuestionSource> resultList = queryWith(QuestionSourceTable.COLUMN_PLATFORM, platformId, options, getQuestionSourceMapper());
		// analyzeQuestionSourceByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<QuestionSource> findQuestionSourceByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<QuestionSource> resultList =  queryWithRange(QuestionSourceTable.COLUMN_PLATFORM, platformId, options, getQuestionSourceMapper(), start, count);
 		//analyzeQuestionSourceByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeQuestionSourceByPlatform(SmartList<QuestionSource> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countQuestionSourceByPlatform(String platformId,Map<String,Object> options){

 		return countWith(QuestionSourceTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countQuestionSourceByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(QuestionSourceTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected QuestionSource saveQuestionSource(QuestionSource  questionSource){
		
		if(!questionSource.isChanged()){
			return questionSource;
		}
		
		
		String SQL=this.getSaveQuestionSourceSQL(questionSource);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveQuestionSourceParameters(questionSource);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		questionSource.incVersion();
		return questionSource;
	
	}
	public SmartList<QuestionSource> saveQuestionSourceList(SmartList<QuestionSource> questionSourceList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitQuestionSourceList(questionSourceList);
		
		batchQuestionSourceCreate((List<QuestionSource>)lists[CREATE_LIST_INDEX]);
		
		batchQuestionSourceUpdate((List<QuestionSource>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(QuestionSource questionSource:questionSourceList){
			if(questionSource.isChanged()){
				questionSource.incVersion();
			}
			
		
		}
		
		
		return questionSourceList;
	}

	public SmartList<QuestionSource> removeQuestionSourceList(SmartList<QuestionSource> questionSourceList,Map<String,Object> options){
		
		
		super.removeList(questionSourceList, options);
		
		return questionSourceList;
		
		
	}
	
	protected List<Object[]> prepareQuestionSourceBatchCreateArgs(List<QuestionSource> questionSourceList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(QuestionSource questionSource:questionSourceList ){
			Object [] parameters = prepareQuestionSourceCreateParameters(questionSource);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareQuestionSourceBatchUpdateArgs(List<QuestionSource> questionSourceList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(QuestionSource questionSource:questionSourceList ){
			if(!questionSource.isChanged()){
				continue;
			}
			Object [] parameters = prepareQuestionSourceUpdateParameters(questionSource);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchQuestionSourceCreate(List<QuestionSource> questionSourceList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareQuestionSourceBatchCreateArgs(questionSourceList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchQuestionSourceUpdate(List<QuestionSource> questionSourceList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareQuestionSourceBatchUpdateArgs(questionSourceList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitQuestionSourceList(List<QuestionSource> questionSourceList){
		
		List<QuestionSource> questionSourceCreateList=new ArrayList<QuestionSource>();
		List<QuestionSource> questionSourceUpdateList=new ArrayList<QuestionSource>();
		
		for(QuestionSource questionSource: questionSourceList){
			if(isUpdateRequest(questionSource)){
				questionSourceUpdateList.add( questionSource);
				continue;
			}
			questionSourceCreateList.add(questionSource);
		}
		
		return new Object[]{questionSourceCreateList,questionSourceUpdateList};
	}
	
	protected boolean isUpdateRequest(QuestionSource questionSource){
 		return questionSource.getVersion() > 0;
 	}
 	protected String getSaveQuestionSourceSQL(QuestionSource questionSource){
 		if(isUpdateRequest(questionSource)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveQuestionSourceParameters(QuestionSource questionSource){
 		if(isUpdateRequest(questionSource) ){
 			return prepareQuestionSourceUpdateParameters(questionSource);
 		}
 		return prepareQuestionSourceCreateParameters(questionSource);
 	}
 	protected Object[] prepareQuestionSourceUpdateParameters(QuestionSource questionSource){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = questionSource.getName();
 		parameters[1] = questionSource.getCode(); 	
 		if(questionSource.getPlatform() != null){
 			parameters[2] = questionSource.getPlatform().getId();
 		}
 		
 		parameters[3] = questionSource.nextVersion();
 		parameters[4] = questionSource.getId();
 		parameters[5] = questionSource.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareQuestionSourceCreateParameters(QuestionSource questionSource){
		Object[] parameters = new Object[4];
		String newQuestionSourceId=getNextId();
		questionSource.setId(newQuestionSourceId);
		parameters[0] =  questionSource.getId();
 
 		parameters[1] = questionSource.getName();
 		parameters[2] = questionSource.getCode(); 	
 		if(questionSource.getPlatform() != null){
 			parameters[3] = questionSource.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected QuestionSource saveInternalQuestionSource(QuestionSource questionSource, Map<String,Object> options){
		
		saveQuestionSource(questionSource);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(questionSource, options);
 		}
 
		
		if(isSaveClassQuestionListEnabled(options)){
	 		saveClassQuestionList(questionSource, options);
	 		//removeClassQuestionList(questionSource, options);
	 		//Not delete the record
	 		
 		}		
		
		return questionSource;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected QuestionSource savePlatform(QuestionSource questionSource, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(questionSource.getPlatform() == null){
 			return questionSource;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(questionSource.getPlatform(),options);
 		return questionSource;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public QuestionSource planToRemoveClassQuestionList(QuestionSource questionSource, String classQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSource.getId());
		key.put(ClassQuestion.ID_PROPERTY, classQuestionIds);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return questionSource;
		}
		if(externalClassQuestionList.isEmpty()){
			return questionSource;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){

			classQuestionItem.clearFromAll();
		}
		
		
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return questionSource;	
	
	}


	//disconnect QuestionSource with question_type in ClassQuestion
	public QuestionSource planToRemoveClassQuestionListWithQuestionType(QuestionSource questionSource, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSource.getId());
		key.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return questionSource;
		}
		if(externalClassQuestionList.isEmpty()){
			return questionSource;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearQuestionType();
			classQuestionItem.clearQuestionSource();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return questionSource;
	}
	
	public int countClassQuestionListWithQuestionType(String questionSourceId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		key.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect QuestionSource with creator in ClassQuestion
	public QuestionSource planToRemoveClassQuestionListWithCreator(QuestionSource questionSource, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSource.getId());
		key.put(ClassQuestion.CREATOR_PROPERTY, creatorId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return questionSource;
		}
		if(externalClassQuestionList.isEmpty()){
			return questionSource;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearCreator();
			classQuestionItem.clearQuestionSource();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return questionSource;
	}
	
	public int countClassQuestionListWithCreator(String questionSourceId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		key.put(ClassQuestion.CREATOR_PROPERTY, creatorId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect QuestionSource with cq in ClassQuestion
	public QuestionSource planToRemoveClassQuestionListWithCq(QuestionSource questionSource, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSource.getId());
		key.put(ClassQuestion.CQ_PROPERTY, cqId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return questionSource;
		}
		if(externalClassQuestionList.isEmpty()){
			return questionSource;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearCq();
			classQuestionItem.clearQuestionSource();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return questionSource;
	}
	
	public int countClassQuestionListWithCq(String questionSourceId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		key.put(ClassQuestion.CQ_PROPERTY, cqId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	

		
	protected QuestionSource saveClassQuestionList(QuestionSource questionSource, Map<String,Object> options){
		
		
		
		
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();
		if(classQuestionList == null){
			//null list means nothing
			return questionSource;
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
		
		
		return questionSource;
	
	}
	
	protected QuestionSource removeClassQuestionList(QuestionSource questionSource, Map<String,Object> options){
	
	
		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();
		if(classQuestionList == null){
			return questionSource;
		}	
	
		SmartList<ClassQuestion> toRemoveClassQuestionList = classQuestionList.getToRemoveList();
		
		if(toRemoveClassQuestionList == null){
			return questionSource;
		}
		if(toRemoveClassQuestionList.isEmpty()){
			return questionSource;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassQuestionDAO().removeClassQuestionList(toRemoveClassQuestionList,options);
		
		return questionSource;
	
	}
	
	

 	
 	
	
	
	
		

	public QuestionSource present(QuestionSource questionSource,Map<String, Object> options){
	
		presentClassQuestionList(questionSource,options);

		return questionSource;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected QuestionSource presentClassQuestionList(
			QuestionSource questionSource,
			Map<String, Object> options) {

		SmartList<ClassQuestion> classQuestionList = questionSource.getClassQuestionList();		
				SmartList<ClassQuestion> newList= presentSubList(questionSource.getId(),
				classQuestionList,
				options,
				getClassQuestionDAO()::countClassQuestionByQuestionSource,
				getClassQuestionDAO()::findClassQuestionByQuestionSource
				);

		
		questionSource.setClassQuestionList(newList);
		

		return questionSource;
	}			
		

	
    public SmartList<QuestionSource> requestCandidateQuestionSourceForClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(QuestionSourceTable.COLUMN_NAME, filterKey, pageNo, pageSize, getQuestionSourceMapper());
    }
		

	protected String getTableName(){
		return QuestionSourceTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<QuestionSource> questionSourceList) {		
		this.enhanceListInternal(questionSourceList, this.getQuestionSourceMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:ClassQuestion的questionSource的ClassQuestionList
	public SmartList<ClassQuestion> loadOurClassQuestionList(HealthUserContext userContext, List<QuestionSource> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassQuestion> loadedObjs = userContext.getDAOGroup().getClassQuestionDAO().findClassQuestionWithKey(key, options);
		Map<String, List<ClassQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getQuestionSource().getId()));
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
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<QuestionSource> questionSourceList = ownerEntity.collectRefsWithType(QuestionSource.INTERNAL_TYPE);
		this.enhanceList(questionSourceList);
		
	}
	
	@Override
	public SmartList<QuestionSource> findQuestionSourceWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getQuestionSourceMapper());

	}
	@Override
	public int countQuestionSourceWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countQuestionSourceWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<QuestionSource> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getQuestionSourceMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


