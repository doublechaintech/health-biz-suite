
package com.doublechaintech.health.classquestion;

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
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.questionsource.QuestionSourceDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class ClassQuestionJDBCTemplateDAO extends HealthBaseDAOImpl implements ClassQuestionDAO{
 
 	
 	private  QuestionSourceDAO  questionSourceDAO;
 	public void setQuestionSourceDAO(QuestionSourceDAO questionSourceDAO){
	 	this.questionSourceDAO = questionSourceDAO;
 	}
 	public QuestionSourceDAO getQuestionSourceDAO(){
	 	return this.questionSourceDAO;
 	}
 
 	
 	private  QuestionTypeDAO  questionTypeDAO;
 	public void setQuestionTypeDAO(QuestionTypeDAO questionTypeDAO){
	 	this.questionTypeDAO = questionTypeDAO;
 	}
 	public QuestionTypeDAO getQuestionTypeDAO(){
	 	return this.questionTypeDAO;
 	}
 
 	
 	private  WechatUserDAO  wechatUserDAO;
 	public void setWechatUserDAO(WechatUserDAO wechatUserDAO){
	 	this.wechatUserDAO = wechatUserDAO;
 	}
 	public WechatUserDAO getWechatUserDAO(){
	 	return this.wechatUserDAO;
 	}
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
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
	protected ClassQuestion load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalClassQuestion(accessKey, options);
	}
	*/
	
	public SmartList<ClassQuestion> loadAll() {
	    return this.loadAll(getClassQuestionMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ClassQuestion load(String id,Map<String,Object> options) throws Exception{
		return loadInternalClassQuestion(ClassQuestionTable.withId(id), options);
	}
	
	
	
	public ClassQuestion save(ClassQuestion classQuestion,Map<String,Object> options){
		
		String methodName="save(ClassQuestion classQuestion,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(classQuestion, methodName, "classQuestion");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalClassQuestion(classQuestion,options);
	}
	public ClassQuestion clone(String classQuestionId, Map<String,Object> options) throws Exception{
	
		return clone(ClassQuestionTable.withId(classQuestionId),options);
	}
	
	protected ClassQuestion clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String classQuestionId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ClassQuestion newClassQuestion = loadInternalClassQuestion(accessKey, options);
		newClassQuestion.setVersion(0);
		
		
 		
 		if(isSaveDailySurveyQuestionListEnabled(options)){
 			for(DailySurveyQuestion item: newClassQuestion.getDailySurveyQuestionList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalClassQuestion(newClassQuestion,options);
		
		return newClassQuestion;
	}
	
	
	
	

	protected void throwIfHasException(String classQuestionId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ClassQuestionVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ClassQuestionNotFoundException(
					"The " + this.getTableName() + "(" + classQuestionId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String classQuestionId, int version) throws Exception{
	
		String methodName="delete(String classQuestionId, int version)";
		assertMethodArgumentNotNull(classQuestionId, methodName, "classQuestionId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{classQuestionId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(classQuestionId,version);
		}
		
	
	}
	
	
	
	
	

	public ClassQuestion disconnectFromAll(String classQuestionId, int version) throws Exception{
	
		
		ClassQuestion classQuestion = loadInternalClassQuestion(ClassQuestionTable.withId(classQuestionId), emptyOptions());
		classQuestion.clearFromAll();
		this.saveClassQuestion(classQuestion);
		return classQuestion;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ClassQuestionTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "class_question";
	}
	@Override
	protected String getBeanName() {
		
		return "classQuestion";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ClassQuestionTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractQuestionTypeEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassQuestionTokens.QUESTIONTYPE);
 	}

 	protected boolean isSaveQuestionTypeEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassQuestionTokens.QUESTIONTYPE);
 	}
 	

 	
  

 	protected boolean isExtractQuestionSourceEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassQuestionTokens.QUESTIONSOURCE);
 	}

 	protected boolean isSaveQuestionSourceEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassQuestionTokens.QUESTIONSOURCE);
 	}
 	

 	
  

 	protected boolean isExtractCreatorEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassQuestionTokens.CREATOR);
 	}

 	protected boolean isSaveCreatorEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassQuestionTokens.CREATOR);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ClassQuestionTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ClassQuestionTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractDailySurveyQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ClassQuestionTokens.DAILY_SURVEY_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeDailySurveyQuestionListEnabled(Map<String,Object> options){		 		
 		return ClassQuestionTokens.of(options).analyzeDailySurveyQuestionListEnabled();
 	}
	
	protected boolean isSaveDailySurveyQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, ClassQuestionTokens.DAILY_SURVEY_QUESTION_LIST);
		
 	}
 	
		

	

	protected ClassQuestionMapper getClassQuestionMapper(){
		return new ClassQuestionMapper();
	}

	
	
	protected ClassQuestion extractClassQuestion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ClassQuestion classQuestion = loadSingleObject(accessKey, getClassQuestionMapper());
			return classQuestion;
		}catch(EmptyResultDataAccessException e){
			throw new ClassQuestionNotFoundException("ClassQuestion("+accessKey+") is not found!");
		}

	}

	
	

	protected ClassQuestion loadInternalClassQuestion(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ClassQuestion classQuestion = extractClassQuestion(accessKey, loadOptions);
 	
 		if(isExtractQuestionTypeEnabled(loadOptions)){
	 		extractQuestionType(classQuestion, loadOptions);
 		}
  	
 		if(isExtractQuestionSourceEnabled(loadOptions)){
	 		extractQuestionSource(classQuestion, loadOptions);
 		}
  	
 		if(isExtractCreatorEnabled(loadOptions)){
	 		extractCreator(classQuestion, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(classQuestion, loadOptions);
 		}
 
		
		if(isExtractDailySurveyQuestionListEnabled(loadOptions)){
	 		extractDailySurveyQuestionList(classQuestion, loadOptions);
 		}	
 		if(isAnalyzeDailySurveyQuestionListEnabled(loadOptions)){
	 		analyzeDailySurveyQuestionList(classQuestion, loadOptions);
 		}
 		
		
		return classQuestion;
		
	}

	 

 	protected ClassQuestion extractQuestionType(ClassQuestion classQuestion, Map<String,Object> options) throws Exception{

		if(classQuestion.getQuestionType() == null){
			return classQuestion;
		}
		String questionTypeId = classQuestion.getQuestionType().getId();
		if( questionTypeId == null){
			return classQuestion;
		}
		QuestionType questionType = getQuestionTypeDAO().load(questionTypeId,options);
		if(questionType != null){
			classQuestion.setQuestionType(questionType);
		}
		
 		
 		return classQuestion;
 	}
 		
  

 	protected ClassQuestion extractQuestionSource(ClassQuestion classQuestion, Map<String,Object> options) throws Exception{

		if(classQuestion.getQuestionSource() == null){
			return classQuestion;
		}
		String questionSourceId = classQuestion.getQuestionSource().getId();
		if( questionSourceId == null){
			return classQuestion;
		}
		QuestionSource questionSource = getQuestionSourceDAO().load(questionSourceId,options);
		if(questionSource != null){
			classQuestion.setQuestionSource(questionSource);
		}
		
 		
 		return classQuestion;
 	}
 		
  

 	protected ClassQuestion extractCreator(ClassQuestion classQuestion, Map<String,Object> options) throws Exception{

		if(classQuestion.getCreator() == null){
			return classQuestion;
		}
		String creatorId = classQuestion.getCreator().getId();
		if( creatorId == null){
			return classQuestion;
		}
		WechatUser creator = getWechatUserDAO().load(creatorId,options);
		if(creator != null){
			classQuestion.setCreator(creator);
		}
		
 		
 		return classQuestion;
 	}
 		
  

 	protected ClassQuestion extractCq(ClassQuestion classQuestion, Map<String,Object> options) throws Exception{

		if(classQuestion.getCq() == null){
			return classQuestion;
		}
		String cqId = classQuestion.getCq().getId();
		if( cqId == null){
			return classQuestion;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			classQuestion.setCq(cq);
		}
		
 		
 		return classQuestion;
 	}
 		
 
		
	protected void enhanceDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ClassQuestion extractDailySurveyQuestionList(ClassQuestion classQuestion, Map<String,Object> options){
		
		
		if(classQuestion == null){
			return null;
		}
		if(classQuestion.getId() == null){
			return classQuestion;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = getDailySurveyQuestionDAO().findDailySurveyQuestionByClassQuestion(classQuestion.getId(),options);
		if(dailySurveyQuestionList != null){
			enhanceDailySurveyQuestionList(dailySurveyQuestionList,options);
			classQuestion.setDailySurveyQuestionList(dailySurveyQuestionList);
		}
		
		return classQuestion;
	
	}	
	
	protected ClassQuestion analyzeDailySurveyQuestionList(ClassQuestion classQuestion, Map<String,Object> options){
		
		
		if(classQuestion == null){
			return null;
		}
		if(classQuestion.getId() == null){
			return classQuestion;
		}

		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();
		if(dailySurveyQuestionList != null){
			getDailySurveyQuestionDAO().analyzeDailySurveyQuestionByClassQuestion(dailySurveyQuestionList, classQuestion.getId(), options);
			
		}
		
		return classQuestion;
	
	}	
	
		
		
  	
 	public SmartList<ClassQuestion> findClassQuestionByQuestionType(String questionTypeId,Map<String,Object> options){
 	
  		SmartList<ClassQuestion> resultList = queryWith(ClassQuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options, getClassQuestionMapper());
		// analyzeClassQuestionByQuestionType(resultList, questionTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassQuestion> findClassQuestionByQuestionType(String questionTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassQuestion> resultList =  queryWithRange(ClassQuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options, getClassQuestionMapper(), start, count);
 		//analyzeClassQuestionByQuestionType(resultList, questionTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassQuestionByQuestionType(SmartList<ClassQuestion> resultList, String questionTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassQuestionByQuestionType(String questionTypeId,Map<String,Object> options){

 		return countWith(ClassQuestionTable.COLUMN_QUESTION_TYPE, questionTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countClassQuestionByQuestionTypeIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassQuestionTable.COLUMN_QUESTION_TYPE, ids, options);
	}
 	
  	
 	public SmartList<ClassQuestion> findClassQuestionByQuestionSource(String questionSourceId,Map<String,Object> options){
 	
  		SmartList<ClassQuestion> resultList = queryWith(ClassQuestionTable.COLUMN_QUESTION_SOURCE, questionSourceId, options, getClassQuestionMapper());
		// analyzeClassQuestionByQuestionSource(resultList, questionSourceId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassQuestion> findClassQuestionByQuestionSource(String questionSourceId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassQuestion> resultList =  queryWithRange(ClassQuestionTable.COLUMN_QUESTION_SOURCE, questionSourceId, options, getClassQuestionMapper(), start, count);
 		//analyzeClassQuestionByQuestionSource(resultList, questionSourceId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassQuestionByQuestionSource(SmartList<ClassQuestion> resultList, String questionSourceId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassQuestionByQuestionSource(String questionSourceId,Map<String,Object> options){

 		return countWith(ClassQuestionTable.COLUMN_QUESTION_SOURCE, questionSourceId, options);
 	}
 	@Override
	public Map<String, Integer> countClassQuestionByQuestionSourceIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassQuestionTable.COLUMN_QUESTION_SOURCE, ids, options);
	}
 	
  	
 	public SmartList<ClassQuestion> findClassQuestionByCreator(String wechatUserId,Map<String,Object> options){
 	
  		SmartList<ClassQuestion> resultList = queryWith(ClassQuestionTable.COLUMN_CREATOR, wechatUserId, options, getClassQuestionMapper());
		// analyzeClassQuestionByCreator(resultList, wechatUserId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassQuestion> findClassQuestionByCreator(String wechatUserId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassQuestion> resultList =  queryWithRange(ClassQuestionTable.COLUMN_CREATOR, wechatUserId, options, getClassQuestionMapper(), start, count);
 		//analyzeClassQuestionByCreator(resultList, wechatUserId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassQuestionByCreator(SmartList<ClassQuestion> resultList, String wechatUserId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassQuestion.CREATOR_PROPERTY, wechatUserId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassQuestionByCreator(String wechatUserId,Map<String,Object> options){

 		return countWith(ClassQuestionTable.COLUMN_CREATOR, wechatUserId, options);
 	}
 	@Override
	public Map<String, Integer> countClassQuestionByCreatorIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassQuestionTable.COLUMN_CREATOR, ids, options);
	}
 	
  	
 	public SmartList<ClassQuestion> findClassQuestionByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<ClassQuestion> resultList = queryWith(ClassQuestionTable.COLUMN_CQ, changeRequestId, options, getClassQuestionMapper());
		// analyzeClassQuestionByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ClassQuestion> findClassQuestionByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ClassQuestion> resultList =  queryWithRange(ClassQuestionTable.COLUMN_CQ, changeRequestId, options, getClassQuestionMapper(), start, count);
 		//analyzeClassQuestionByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeClassQuestionByCq(SmartList<ClassQuestion> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ClassQuestion.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countClassQuestionByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(ClassQuestionTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countClassQuestionByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ClassQuestionTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected ClassQuestion saveClassQuestion(ClassQuestion  classQuestion){
		
		if(!classQuestion.isChanged()){
			return classQuestion;
		}
		
		
		String SQL=this.getSaveClassQuestionSQL(classQuestion);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveClassQuestionParameters(classQuestion);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		classQuestion.incVersion();
		return classQuestion;
	
	}
	public SmartList<ClassQuestion> saveClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitClassQuestionList(classQuestionList);
		
		batchClassQuestionCreate((List<ClassQuestion>)lists[CREATE_LIST_INDEX]);
		
		batchClassQuestionUpdate((List<ClassQuestion>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ClassQuestion classQuestion:classQuestionList){
			if(classQuestion.isChanged()){
				classQuestion.incVersion();
			}
			
		
		}
		
		
		return classQuestionList;
	}

	public SmartList<ClassQuestion> removeClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options){
		
		
		super.removeList(classQuestionList, options);
		
		return classQuestionList;
		
		
	}
	
	protected List<Object[]> prepareClassQuestionBatchCreateArgs(List<ClassQuestion> classQuestionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ClassQuestion classQuestion:classQuestionList ){
			Object [] parameters = prepareClassQuestionCreateParameters(classQuestion);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareClassQuestionBatchUpdateArgs(List<ClassQuestion> classQuestionList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ClassQuestion classQuestion:classQuestionList ){
			if(!classQuestion.isChanged()){
				continue;
			}
			Object [] parameters = prepareClassQuestionUpdateParameters(classQuestion);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchClassQuestionCreate(List<ClassQuestion> classQuestionList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareClassQuestionBatchCreateArgs(classQuestionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchClassQuestionUpdate(List<ClassQuestion> classQuestionList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareClassQuestionBatchUpdateArgs(classQuestionList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitClassQuestionList(List<ClassQuestion> classQuestionList){
		
		List<ClassQuestion> classQuestionCreateList=new ArrayList<ClassQuestion>();
		List<ClassQuestion> classQuestionUpdateList=new ArrayList<ClassQuestion>();
		
		for(ClassQuestion classQuestion: classQuestionList){
			if(isUpdateRequest(classQuestion)){
				classQuestionUpdateList.add( classQuestion);
				continue;
			}
			classQuestionCreateList.add(classQuestion);
		}
		
		return new Object[]{classQuestionCreateList,classQuestionUpdateList};
	}
	
	protected boolean isUpdateRequest(ClassQuestion classQuestion){
 		return classQuestion.getVersion() > 0;
 	}
 	protected String getSaveClassQuestionSQL(ClassQuestion classQuestion){
 		if(isUpdateRequest(classQuestion)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveClassQuestionParameters(ClassQuestion classQuestion){
 		if(isUpdateRequest(classQuestion) ){
 			return prepareClassQuestionUpdateParameters(classQuestion);
 		}
 		return prepareClassQuestionCreateParameters(classQuestion);
 	}
 	protected Object[] prepareClassQuestionUpdateParameters(ClassQuestion classQuestion){
 		Object[] parameters = new Object[12];
 
 		parameters[0] = classQuestion.getTopic(); 	
 		if(classQuestion.getQuestionType() != null){
 			parameters[1] = classQuestion.getQuestionType().getId();
 		}
 
 		parameters[2] = classQuestion.getOptionA();
 		parameters[3] = classQuestion.getOptionB();
 		parameters[4] = classQuestion.getOptionC();
 		parameters[5] = classQuestion.getOptionD(); 	
 		if(classQuestion.getQuestionSource() != null){
 			parameters[6] = classQuestion.getQuestionSource().getId();
 		}
  	
 		if(classQuestion.getCreator() != null){
 			parameters[7] = classQuestion.getCreator().getId();
 		}
  	
 		if(classQuestion.getCq() != null){
 			parameters[8] = classQuestion.getCq().getId();
 		}
 		
 		parameters[9] = classQuestion.nextVersion();
 		parameters[10] = classQuestion.getId();
 		parameters[11] = classQuestion.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareClassQuestionCreateParameters(ClassQuestion classQuestion){
		Object[] parameters = new Object[10];
		String newClassQuestionId=getNextId();
		classQuestion.setId(newClassQuestionId);
		parameters[0] =  classQuestion.getId();
 
 		parameters[1] = classQuestion.getTopic(); 	
 		if(classQuestion.getQuestionType() != null){
 			parameters[2] = classQuestion.getQuestionType().getId();
 		
 		}
 		
 		parameters[3] = classQuestion.getOptionA();
 		parameters[4] = classQuestion.getOptionB();
 		parameters[5] = classQuestion.getOptionC();
 		parameters[6] = classQuestion.getOptionD(); 	
 		if(classQuestion.getQuestionSource() != null){
 			parameters[7] = classQuestion.getQuestionSource().getId();
 		
 		}
 		 	
 		if(classQuestion.getCreator() != null){
 			parameters[8] = classQuestion.getCreator().getId();
 		
 		}
 		 	
 		if(classQuestion.getCq() != null){
 			parameters[9] = classQuestion.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ClassQuestion saveInternalClassQuestion(ClassQuestion classQuestion, Map<String,Object> options){
		
		saveClassQuestion(classQuestion);
 	
 		if(isSaveQuestionTypeEnabled(options)){
	 		saveQuestionType(classQuestion, options);
 		}
  	
 		if(isSaveQuestionSourceEnabled(options)){
	 		saveQuestionSource(classQuestion, options);
 		}
  	
 		if(isSaveCreatorEnabled(options)){
	 		saveCreator(classQuestion, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(classQuestion, options);
 		}
 
		
		if(isSaveDailySurveyQuestionListEnabled(options)){
	 		saveDailySurveyQuestionList(classQuestion, options);
	 		//removeDailySurveyQuestionList(classQuestion, options);
	 		//Not delete the record
	 		
 		}		
		
		return classQuestion;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ClassQuestion saveQuestionType(ClassQuestion classQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classQuestion.getQuestionType() == null){
 			return classQuestion;//do nothing when it is null
 		}
 		
 		getQuestionTypeDAO().save(classQuestion.getQuestionType(),options);
 		return classQuestion;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ClassQuestion saveQuestionSource(ClassQuestion classQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classQuestion.getQuestionSource() == null){
 			return classQuestion;//do nothing when it is null
 		}
 		
 		getQuestionSourceDAO().save(classQuestion.getQuestionSource(),options);
 		return classQuestion;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ClassQuestion saveCreator(ClassQuestion classQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classQuestion.getCreator() == null){
 			return classQuestion;//do nothing when it is null
 		}
 		
 		getWechatUserDAO().save(classQuestion.getCreator(),options);
 		return classQuestion;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ClassQuestion saveCq(ClassQuestion classQuestion, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(classQuestion.getCq() == null){
 			return classQuestion;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(classQuestion.getCq(),options);
 		return classQuestion;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ClassQuestion planToRemoveDailySurveyQuestionList(ClassQuestion classQuestion, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, classQuestion.getId());
		key.put(DailySurveyQuestion.ID_PROPERTY, dailySurveyQuestionIds);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return classQuestion;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return classQuestion;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){

			dailySurveyQuestionItem.clearFromAll();
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return classQuestion;	
	
	}


	//disconnect ClassQuestion with question_type in DailySurveyQuestion
	public ClassQuestion planToRemoveDailySurveyQuestionListWithQuestionType(ClassQuestion classQuestion, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, classQuestion.getId());
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return classQuestion;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return classQuestion;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearQuestionType();
			dailySurveyQuestionItem.clearClassQuestion();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return classQuestion;
	}
	
	public int countDailySurveyQuestionListWithQuestionType(String classQuestionId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, classQuestionId);
		key.put(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect ClassQuestion with class_daily_health_survey in DailySurveyQuestion
	public ClassQuestion planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(ClassQuestion classQuestion, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, classQuestion.getId());
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<DailySurveyQuestion> externalDailySurveyQuestionList = getDailySurveyQuestionDAO().
				findDailySurveyQuestionWithKey(key, options);
		if(externalDailySurveyQuestionList == null){
			return classQuestion;
		}
		if(externalDailySurveyQuestionList.isEmpty()){
			return classQuestion;
		}
		
		for(DailySurveyQuestion dailySurveyQuestionItem: externalDailySurveyQuestionList){
			dailySurveyQuestionItem.clearClassDailyHealthSurvey();
			dailySurveyQuestionItem.clearClassQuestion();
			
		}
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();		
		dailySurveyQuestionList.addAllToRemoveList(externalDailySurveyQuestionList);
		return classQuestion;
	}
	
	public int countDailySurveyQuestionListWithClassDailyHealthSurvey(String classQuestionId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, classQuestionId);
		key.put(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getDailySurveyQuestionDAO().countDailySurveyQuestionWithKey(key, options);
		return count;
	}
	

		
	protected ClassQuestion saveDailySurveyQuestionList(ClassQuestion classQuestion, Map<String,Object> options){
		
		
		
		
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			//null list means nothing
			return classQuestion;
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
		
		
		return classQuestion;
	
	}
	
	protected ClassQuestion removeDailySurveyQuestionList(ClassQuestion classQuestion, Map<String,Object> options){
	
	
		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();
		if(dailySurveyQuestionList == null){
			return classQuestion;
		}	
	
		SmartList<DailySurveyQuestion> toRemoveDailySurveyQuestionList = dailySurveyQuestionList.getToRemoveList();
		
		if(toRemoveDailySurveyQuestionList == null){
			return classQuestion;
		}
		if(toRemoveDailySurveyQuestionList.isEmpty()){
			return classQuestion;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getDailySurveyQuestionDAO().removeDailySurveyQuestionList(toRemoveDailySurveyQuestionList,options);
		
		return classQuestion;
	
	}
	
	

 	
 	
	
	
	
		

	public ClassQuestion present(ClassQuestion classQuestion,Map<String, Object> options){
	
		presentDailySurveyQuestionList(classQuestion,options);

		return classQuestion;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ClassQuestion presentDailySurveyQuestionList(
			ClassQuestion classQuestion,
			Map<String, Object> options) {

		SmartList<DailySurveyQuestion> dailySurveyQuestionList = classQuestion.getDailySurveyQuestionList();		
				SmartList<DailySurveyQuestion> newList= presentSubList(classQuestion.getId(),
				dailySurveyQuestionList,
				options,
				getDailySurveyQuestionDAO()::countDailySurveyQuestionByClassQuestion,
				getDailySurveyQuestionDAO()::findDailySurveyQuestionByClassQuestion
				);

		
		classQuestion.setDailySurveyQuestionList(newList);
		

		return classQuestion;
	}			
		

	
    public SmartList<ClassQuestion> requestCandidateClassQuestionForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ClassQuestionTable.COLUMN_TOPIC, filterKey, pageNo, pageSize, getClassQuestionMapper());
    }
		

	protected String getTableName(){
		return ClassQuestionTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ClassQuestion> classQuestionList) {		
		this.enhanceListInternal(classQuestionList, this.getClassQuestionMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的classQuestion的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<ClassQuestion> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<DailySurveyQuestion> loadedObjs = userContext.getDAOGroup().getDailySurveyQuestionDAO().findDailySurveyQuestionWithKey(key, options);
		Map<String, List<DailySurveyQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getClassQuestion().getId()));
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
		List<ClassQuestion> classQuestionList = ownerEntity.collectRefsWithType(ClassQuestion.INTERNAL_TYPE);
		this.enhanceList(classQuestionList);
		
	}
	
	@Override
	public SmartList<ClassQuestion> findClassQuestionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getClassQuestionMapper());

	}
	@Override
	public int countClassQuestionWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countClassQuestionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ClassQuestion> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getClassQuestionMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


