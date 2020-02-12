
package com.doublechaintech.health.surveystatus;

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
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class SurveyStatusJDBCTemplateDAO extends HealthBaseDAOImpl implements SurveyStatusDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
	protected SurveyStatus load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalSurveyStatus(accessKey, options);
	}
	*/
	
	public SmartList<SurveyStatus> loadAll() {
	    return this.loadAll(getSurveyStatusMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public SurveyStatus load(String id,Map<String,Object> options) throws Exception{
		return loadInternalSurveyStatus(SurveyStatusTable.withId(id), options);
	}
	
	
	
	public SurveyStatus loadByCode(String code,Map<String,Object> options) throws Exception{
		return loadInternalSurveyStatus(SurveyStatusTable.withCode( code), options);
	}
	
	
	public SurveyStatus save(SurveyStatus surveyStatus,Map<String,Object> options){
		
		String methodName="save(SurveyStatus surveyStatus,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(surveyStatus, methodName, "surveyStatus");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalSurveyStatus(surveyStatus,options);
	}
	public SurveyStatus clone(String surveyStatusId, Map<String,Object> options) throws Exception{
	
		return clone(SurveyStatusTable.withId(surveyStatusId),options);
	}
	
	protected SurveyStatus clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String surveyStatusId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		SurveyStatus newSurveyStatus = loadInternalSurveyStatus(accessKey, options);
		newSurveyStatus.setVersion(0);
		
		
 		
 		if(isSaveStudentHealthSurveyListEnabled(options)){
 			for(StudentHealthSurvey item: newSurveyStatus.getStudentHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalSurveyStatus(newSurveyStatus,options);
		
		return newSurveyStatus;
	}
	
	
	
	public SurveyStatus cloneByCode(String code,Map<String,Object> options) throws Exception{
		return clone(SurveyStatusTable.withCode( code), options);
	}
	
	
	

	protected void throwIfHasException(String surveyStatusId,int version,int count) throws Exception{
		if (count == 1) {
			throw new SurveyStatusVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new SurveyStatusNotFoundException(
					"The " + this.getTableName() + "(" + surveyStatusId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String surveyStatusId, int version) throws Exception{
	
		String methodName="delete(String surveyStatusId, int version)";
		assertMethodArgumentNotNull(surveyStatusId, methodName, "surveyStatusId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{surveyStatusId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(surveyStatusId,version);
		}
		
	
	}
	
	
	
	
	

	public SurveyStatus disconnectFromAll(String surveyStatusId, int version) throws Exception{
	
		
		SurveyStatus surveyStatus = loadInternalSurveyStatus(SurveyStatusTable.withId(surveyStatusId), emptyOptions());
		surveyStatus.clearFromAll();
		this.saveSurveyStatus(surveyStatus);
		return surveyStatus;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return SurveyStatusTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "survey_status";
	}
	@Override
	protected String getBeanName() {
		
		return "surveyStatus";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return SurveyStatusTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SurveyStatusTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SurveyStatusTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SurveyStatusTokens.STUDENT_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeStudentHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return SurveyStatusTokens.of(options).analyzeStudentHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveStudentHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, SurveyStatusTokens.STUDENT_HEALTH_SURVEY_LIST);
		
 	}
 	
		

	

	protected SurveyStatusMapper getSurveyStatusMapper(){
		return new SurveyStatusMapper();
	}

	
	
	protected SurveyStatus extractSurveyStatus(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			SurveyStatus surveyStatus = loadSingleObject(accessKey, getSurveyStatusMapper());
			return surveyStatus;
		}catch(EmptyResultDataAccessException e){
			throw new SurveyStatusNotFoundException("SurveyStatus("+accessKey+") is not found!");
		}

	}

	
	

	protected SurveyStatus loadInternalSurveyStatus(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		SurveyStatus surveyStatus = extractSurveyStatus(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(surveyStatus, loadOptions);
 		}
 
		
		if(isExtractStudentHealthSurveyListEnabled(loadOptions)){
	 		extractStudentHealthSurveyList(surveyStatus, loadOptions);
 		}	
 		if(isAnalyzeStudentHealthSurveyListEnabled(loadOptions)){
	 		analyzeStudentHealthSurveyList(surveyStatus, loadOptions);
 		}
 		
		
		return surveyStatus;
		
	}

	 

 	protected SurveyStatus extractPlatform(SurveyStatus surveyStatus, Map<String,Object> options) throws Exception{

		if(surveyStatus.getPlatform() == null){
			return surveyStatus;
		}
		String platformId = surveyStatus.getPlatform().getId();
		if( platformId == null){
			return surveyStatus;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			surveyStatus.setPlatform(platform);
		}
		
 		
 		return surveyStatus;
 	}
 		
 
		
	protected void enhanceStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SurveyStatus extractStudentHealthSurveyList(SurveyStatus surveyStatus, Map<String,Object> options){
		
		
		if(surveyStatus == null){
			return null;
		}
		if(surveyStatus.getId() == null){
			return surveyStatus;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = getStudentHealthSurveyDAO().findStudentHealthSurveyBySurveyStatus(surveyStatus.getId(),options);
		if(studentHealthSurveyList != null){
			enhanceStudentHealthSurveyList(studentHealthSurveyList,options);
			surveyStatus.setStudentHealthSurveyList(studentHealthSurveyList);
		}
		
		return surveyStatus;
	
	}	
	
	protected SurveyStatus analyzeStudentHealthSurveyList(SurveyStatus surveyStatus, Map<String,Object> options){
		
		
		if(surveyStatus == null){
			return null;
		}
		if(surveyStatus.getId() == null){
			return surveyStatus;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();
		if(studentHealthSurveyList != null){
			getStudentHealthSurveyDAO().analyzeStudentHealthSurveyBySurveyStatus(studentHealthSurveyList, surveyStatus.getId(), options);
			
		}
		
		return surveyStatus;
	
	}	
	
		
		
  	
 	public SmartList<SurveyStatus> findSurveyStatusByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<SurveyStatus> resultList = queryWith(SurveyStatusTable.COLUMN_PLATFORM, platformId, options, getSurveyStatusMapper());
		// analyzeSurveyStatusByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SurveyStatus> findSurveyStatusByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SurveyStatus> resultList =  queryWithRange(SurveyStatusTable.COLUMN_PLATFORM, platformId, options, getSurveyStatusMapper(), start, count);
 		//analyzeSurveyStatusByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeSurveyStatusByPlatform(SmartList<SurveyStatus> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countSurveyStatusByPlatform(String platformId,Map<String,Object> options){

 		return countWith(SurveyStatusTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countSurveyStatusByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SurveyStatusTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected SurveyStatus saveSurveyStatus(SurveyStatus  surveyStatus){
		
		if(!surveyStatus.isChanged()){
			return surveyStatus;
		}
		
		
		String SQL=this.getSaveSurveyStatusSQL(surveyStatus);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveSurveyStatusParameters(surveyStatus);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		surveyStatus.incVersion();
		return surveyStatus;
	
	}
	public SmartList<SurveyStatus> saveSurveyStatusList(SmartList<SurveyStatus> surveyStatusList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitSurveyStatusList(surveyStatusList);
		
		batchSurveyStatusCreate((List<SurveyStatus>)lists[CREATE_LIST_INDEX]);
		
		batchSurveyStatusUpdate((List<SurveyStatus>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(SurveyStatus surveyStatus:surveyStatusList){
			if(surveyStatus.isChanged()){
				surveyStatus.incVersion();
			}
			
		
		}
		
		
		return surveyStatusList;
	}

	public SmartList<SurveyStatus> removeSurveyStatusList(SmartList<SurveyStatus> surveyStatusList,Map<String,Object> options){
		
		
		super.removeList(surveyStatusList, options);
		
		return surveyStatusList;
		
		
	}
	
	protected List<Object[]> prepareSurveyStatusBatchCreateArgs(List<SurveyStatus> surveyStatusList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SurveyStatus surveyStatus:surveyStatusList ){
			Object [] parameters = prepareSurveyStatusCreateParameters(surveyStatus);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareSurveyStatusBatchUpdateArgs(List<SurveyStatus> surveyStatusList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SurveyStatus surveyStatus:surveyStatusList ){
			if(!surveyStatus.isChanged()){
				continue;
			}
			Object [] parameters = prepareSurveyStatusUpdateParameters(surveyStatus);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchSurveyStatusCreate(List<SurveyStatus> surveyStatusList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareSurveyStatusBatchCreateArgs(surveyStatusList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchSurveyStatusUpdate(List<SurveyStatus> surveyStatusList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareSurveyStatusBatchUpdateArgs(surveyStatusList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitSurveyStatusList(List<SurveyStatus> surveyStatusList){
		
		List<SurveyStatus> surveyStatusCreateList=new ArrayList<SurveyStatus>();
		List<SurveyStatus> surveyStatusUpdateList=new ArrayList<SurveyStatus>();
		
		for(SurveyStatus surveyStatus: surveyStatusList){
			if(isUpdateRequest(surveyStatus)){
				surveyStatusUpdateList.add( surveyStatus);
				continue;
			}
			surveyStatusCreateList.add(surveyStatus);
		}
		
		return new Object[]{surveyStatusCreateList,surveyStatusUpdateList};
	}
	
	protected boolean isUpdateRequest(SurveyStatus surveyStatus){
 		return surveyStatus.getVersion() > 0;
 	}
 	protected String getSaveSurveyStatusSQL(SurveyStatus surveyStatus){
 		if(isUpdateRequest(surveyStatus)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveSurveyStatusParameters(SurveyStatus surveyStatus){
 		if(isUpdateRequest(surveyStatus) ){
 			return prepareSurveyStatusUpdateParameters(surveyStatus);
 		}
 		return prepareSurveyStatusCreateParameters(surveyStatus);
 	}
 	protected Object[] prepareSurveyStatusUpdateParameters(SurveyStatus surveyStatus){
 		Object[] parameters = new Object[6];
 
 		
 		parameters[0] = surveyStatus.getName();
 		
 		
 		parameters[1] = surveyStatus.getCode();
 		 	
 		if(surveyStatus.getPlatform() != null){
 			parameters[2] = surveyStatus.getPlatform().getId();
 		}
 		
 		parameters[3] = surveyStatus.nextVersion();
 		parameters[4] = surveyStatus.getId();
 		parameters[5] = surveyStatus.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareSurveyStatusCreateParameters(SurveyStatus surveyStatus){
		Object[] parameters = new Object[4];
		String newSurveyStatusId=getNextId();
		surveyStatus.setId(newSurveyStatusId);
		parameters[0] =  surveyStatus.getId();
 
 		
 		parameters[1] = surveyStatus.getName();
 		
 		
 		parameters[2] = surveyStatus.getCode();
 		 	
 		if(surveyStatus.getPlatform() != null){
 			parameters[3] = surveyStatus.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected SurveyStatus saveInternalSurveyStatus(SurveyStatus surveyStatus, Map<String,Object> options){
		
		saveSurveyStatus(surveyStatus);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(surveyStatus, options);
 		}
 
		
		if(isSaveStudentHealthSurveyListEnabled(options)){
	 		saveStudentHealthSurveyList(surveyStatus, options);
	 		//removeStudentHealthSurveyList(surveyStatus, options);
	 		//Not delete the record
	 		
 		}		
		
		return surveyStatus;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected SurveyStatus savePlatform(SurveyStatus surveyStatus, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(surveyStatus.getPlatform() == null){
 			return surveyStatus;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(surveyStatus.getPlatform(),options);
 		return surveyStatus;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public SurveyStatus planToRemoveStudentHealthSurveyList(SurveyStatus surveyStatus, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatus.getId());
		key.put(StudentHealthSurvey.ID_PROPERTY, studentHealthSurveyIds);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return surveyStatus;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return surveyStatus;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){

			studentHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return surveyStatus;	
	
	}


	//disconnect SurveyStatus with student in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithStudent(SurveyStatus surveyStatus, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatus.getId());
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return surveyStatus;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return surveyStatus;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearStudent();
			studentHealthSurveyItem.clearSurveyStatus();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return surveyStatus;
	}
	
	public int countStudentHealthSurveyListWithStudent(String surveyStatusId, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SurveyStatus with teacher in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithTeacher(SurveyStatus surveyStatus, String teacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatus.getId());
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return surveyStatus;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return surveyStatus;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearTeacher();
			studentHealthSurveyItem.clearSurveyStatus();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return surveyStatus;
	}
	
	public int countStudentHealthSurveyListWithTeacher(String surveyStatusId, String teacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		key.put(StudentHealthSurvey.TEACHER_PROPERTY, teacherId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SurveyStatus with class_daily_health_survey in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(SurveyStatus surveyStatus, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatus.getId());
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return surveyStatus;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return surveyStatus;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			studentHealthSurveyItem.clearSurveyStatus();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return surveyStatus;
	}
	
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String surveyStatusId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SurveyStatus with change_request in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithChangeRequest(SurveyStatus surveyStatus, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatus.getId());
		key.put(StudentHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return surveyStatus;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return surveyStatus;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearChangeRequest();
			studentHealthSurveyItem.clearSurveyStatus();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return surveyStatus;
	}
	
	public int countStudentHealthSurveyListWithChangeRequest(String surveyStatusId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		key.put(StudentHealthSurvey.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	

		
	protected SurveyStatus saveStudentHealthSurveyList(SurveyStatus surveyStatus, Map<String,Object> options){
		
		
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			//null list means nothing
			return surveyStatus;
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
		
		
		return surveyStatus;
	
	}
	
	protected SurveyStatus removeStudentHealthSurveyList(SurveyStatus surveyStatus, Map<String,Object> options){
	
	
		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			return surveyStatus;
		}	
	
		SmartList<StudentHealthSurvey> toRemoveStudentHealthSurveyList = studentHealthSurveyList.getToRemoveList();
		
		if(toRemoveStudentHealthSurveyList == null){
			return surveyStatus;
		}
		if(toRemoveStudentHealthSurveyList.isEmpty()){
			return surveyStatus;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentHealthSurveyDAO().removeStudentHealthSurveyList(toRemoveStudentHealthSurveyList,options);
		
		return surveyStatus;
	
	}
	
	

 	
 	
	
	
	
		

	public SurveyStatus present(SurveyStatus surveyStatus,Map<String, Object> options){
	
		presentStudentHealthSurveyList(surveyStatus,options);

		return surveyStatus;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected SurveyStatus presentStudentHealthSurveyList(
			SurveyStatus surveyStatus,
			Map<String, Object> options) {

		SmartList<StudentHealthSurvey> studentHealthSurveyList = surveyStatus.getStudentHealthSurveyList();		
				SmartList<StudentHealthSurvey> newList= presentSubList(surveyStatus.getId(),
				studentHealthSurveyList,
				options,
				getStudentHealthSurveyDAO()::countStudentHealthSurveyBySurveyStatus,
				getStudentHealthSurveyDAO()::findStudentHealthSurveyBySurveyStatus
				);

		
		surveyStatus.setStudentHealthSurveyList(newList);
		

		return surveyStatus;
	}			
		

	
    public SmartList<SurveyStatus> requestCandidateSurveyStatusForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SurveyStatusTable.COLUMN_NAME, filterKey, pageNo, pageSize, getSurveyStatusMapper());
    }
		

	protected String getTableName(){
		return SurveyStatusTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<SurveyStatus> surveyStatusList) {		
		this.enhanceListInternal(surveyStatusList, this.getSurveyStatusMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的surveyStatus的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<SurveyStatus> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentHealthSurvey> loadedObjs = userContext.getDAOGroup().getStudentHealthSurveyDAO().findStudentHealthSurveyWithKey(key, options);
		Map<String, List<StudentHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSurveyStatus().getId()));
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
		List<SurveyStatus> surveyStatusList = ownerEntity.collectRefsWithType(SurveyStatus.INTERNAL_TYPE);
		this.enhanceList(surveyStatusList);
		
	}
	
	@Override
	public SmartList<SurveyStatus> findSurveyStatusWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getSurveyStatusMapper());

	}
	@Override
	public int countSurveyStatusWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countSurveyStatusWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<SurveyStatus> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getSurveyStatusMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


