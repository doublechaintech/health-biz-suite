
package com.doublechaintech.health.studentanswer;

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


import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class StudentAnswerJDBCTemplateDAO extends HealthBaseDAOImpl implements StudentAnswerDAO{
 
 	
 	private  HealthSurveyReportDAO  healthSurveyReportDAO;
 	public void setHealthSurveyReportDAO(HealthSurveyReportDAO healthSurveyReportDAO){
	 	this.healthSurveyReportDAO = healthSurveyReportDAO;
 	}
 	public HealthSurveyReportDAO getHealthSurveyReportDAO(){
	 	return this.healthSurveyReportDAO;
 	}
 
 	
 	private  StudentDailyAnswerDAO  studentDailyAnswerDAO;
 	public void setStudentDailyAnswerDAO(StudentDailyAnswerDAO studentDailyAnswerDAO){
	 	this.studentDailyAnswerDAO = studentDailyAnswerDAO;
 	}
 	public StudentDailyAnswerDAO getStudentDailyAnswerDAO(){
	 	return this.studentDailyAnswerDAO;
 	}


			
		

	
	/*
	protected StudentAnswer load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalStudentAnswer(accessKey, options);
	}
	*/
	
	public SmartList<StudentAnswer> loadAll() {
	    return this.loadAll(getStudentAnswerMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public StudentAnswer load(String id,Map<String,Object> options) throws Exception{
		return loadInternalStudentAnswer(StudentAnswerTable.withId(id), options);
	}
	
	
	
	public StudentAnswer save(StudentAnswer studentAnswer,Map<String,Object> options){
		
		String methodName="save(StudentAnswer studentAnswer,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(studentAnswer, methodName, "studentAnswer");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalStudentAnswer(studentAnswer,options);
	}
	public StudentAnswer clone(String studentAnswerId, Map<String,Object> options) throws Exception{
	
		return clone(StudentAnswerTable.withId(studentAnswerId),options);
	}
	
	protected StudentAnswer clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String studentAnswerId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		StudentAnswer newStudentAnswer = loadInternalStudentAnswer(accessKey, options);
		newStudentAnswer.setVersion(0);
		
		

		
		saveInternalStudentAnswer(newStudentAnswer,options);
		
		return newStudentAnswer;
	}
	
	
	
	

	protected void throwIfHasException(String studentAnswerId,int version,int count) throws Exception{
		if (count == 1) {
			throw new StudentAnswerVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new StudentAnswerNotFoundException(
					"The " + this.getTableName() + "(" + studentAnswerId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String studentAnswerId, int version) throws Exception{
	
		String methodName="delete(String studentAnswerId, int version)";
		assertMethodArgumentNotNull(studentAnswerId, methodName, "studentAnswerId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{studentAnswerId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(studentAnswerId,version);
		}
		
	
	}
	
	
	
	
	

	public StudentAnswer disconnectFromAll(String studentAnswerId, int version) throws Exception{
	
		
		StudentAnswer studentAnswer = loadInternalStudentAnswer(StudentAnswerTable.withId(studentAnswerId), emptyOptions());
		studentAnswer.clearFromAll();
		this.saveStudentAnswer(studentAnswer);
		return studentAnswer;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return StudentAnswerTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "student_answer";
	}
	@Override
	protected String getBeanName() {
		
		return "studentAnswer";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return StudentAnswerTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractHealthSurveyReportEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentAnswerTokens.HEALTHSURVEYREPORT);
 	}

 	protected boolean isSaveHealthSurveyReportEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentAnswerTokens.HEALTHSURVEYREPORT);
 	}
 	

 	
  

 	protected boolean isExtractDailyAnswerEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentAnswerTokens.DAILYANSWER);
 	}

 	protected boolean isSaveDailyAnswerEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentAnswerTokens.DAILYANSWER);
 	}
 	

 	
 
		

	

	protected StudentAnswerMapper getStudentAnswerMapper(){
		return new StudentAnswerMapper();
	}

	
	
	protected StudentAnswer extractStudentAnswer(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			StudentAnswer studentAnswer = loadSingleObject(accessKey, getStudentAnswerMapper());
			return studentAnswer;
		}catch(EmptyResultDataAccessException e){
			throw new StudentAnswerNotFoundException("StudentAnswer("+accessKey+") is not found!");
		}

	}

	
	

	protected StudentAnswer loadInternalStudentAnswer(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		StudentAnswer studentAnswer = extractStudentAnswer(accessKey, loadOptions);
 	
 		if(isExtractHealthSurveyReportEnabled(loadOptions)){
	 		extractHealthSurveyReport(studentAnswer, loadOptions);
 		}
  	
 		if(isExtractDailyAnswerEnabled(loadOptions)){
	 		extractDailyAnswer(studentAnswer, loadOptions);
 		}
 
		
		return studentAnswer;
		
	}

	 

 	protected StudentAnswer extractHealthSurveyReport(StudentAnswer studentAnswer, Map<String,Object> options) throws Exception{

		if(studentAnswer.getHealthSurveyReport() == null){
			return studentAnswer;
		}
		String healthSurveyReportId = studentAnswer.getHealthSurveyReport().getId();
		if( healthSurveyReportId == null){
			return studentAnswer;
		}
		HealthSurveyReport healthSurveyReport = getHealthSurveyReportDAO().load(healthSurveyReportId,options);
		if(healthSurveyReport != null){
			studentAnswer.setHealthSurveyReport(healthSurveyReport);
		}
		
 		
 		return studentAnswer;
 	}
 		
  

 	protected StudentAnswer extractDailyAnswer(StudentAnswer studentAnswer, Map<String,Object> options) throws Exception{

		if(studentAnswer.getDailyAnswer() == null){
			return studentAnswer;
		}
		String dailyAnswerId = studentAnswer.getDailyAnswer().getId();
		if( dailyAnswerId == null){
			return studentAnswer;
		}
		StudentDailyAnswer dailyAnswer = getStudentDailyAnswerDAO().load(dailyAnswerId,options);
		if(dailyAnswer != null){
			studentAnswer.setDailyAnswer(dailyAnswer);
		}
		
 		
 		return studentAnswer;
 	}
 		
 
		
		
  	
 	public SmartList<StudentAnswer> findStudentAnswerByHealthSurveyReport(String healthSurveyReportId,Map<String,Object> options){
 	
  		SmartList<StudentAnswer> resultList = queryWith(StudentAnswerTable.COLUMN_HEALTH_SURVEY_REPORT, healthSurveyReportId, options, getStudentAnswerMapper());
		// analyzeStudentAnswerByHealthSurveyReport(resultList, healthSurveyReportId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentAnswer> findStudentAnswerByHealthSurveyReport(String healthSurveyReportId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentAnswer> resultList =  queryWithRange(StudentAnswerTable.COLUMN_HEALTH_SURVEY_REPORT, healthSurveyReportId, options, getStudentAnswerMapper(), start, count);
 		//analyzeStudentAnswerByHealthSurveyReport(resultList, healthSurveyReportId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentAnswerByHealthSurveyReport(SmartList<StudentAnswer> resultList, String healthSurveyReportId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, healthSurveyReportId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentAnswerByHealthSurveyReport(String healthSurveyReportId,Map<String,Object> options){

 		return countWith(StudentAnswerTable.COLUMN_HEALTH_SURVEY_REPORT, healthSurveyReportId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentAnswerByHealthSurveyReportIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentAnswerTable.COLUMN_HEALTH_SURVEY_REPORT, ids, options);
	}
 	
  	
 	public SmartList<StudentAnswer> findStudentAnswerByDailyAnswer(String studentDailyAnswerId,Map<String,Object> options){
 	
  		SmartList<StudentAnswer> resultList = queryWith(StudentAnswerTable.COLUMN_DAILY_ANSWER, studentDailyAnswerId, options, getStudentAnswerMapper());
		// analyzeStudentAnswerByDailyAnswer(resultList, studentDailyAnswerId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<StudentAnswer> findStudentAnswerByDailyAnswer(String studentDailyAnswerId, int start, int count,Map<String,Object> options){
 		
 		SmartList<StudentAnswer> resultList =  queryWithRange(StudentAnswerTable.COLUMN_DAILY_ANSWER, studentDailyAnswerId, options, getStudentAnswerMapper(), start, count);
 		//analyzeStudentAnswerByDailyAnswer(resultList, studentDailyAnswerId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentAnswerByDailyAnswer(SmartList<StudentAnswer> resultList, String studentDailyAnswerId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(StudentAnswer.DAILY_ANSWER_PROPERTY, studentDailyAnswerId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentAnswerByDailyAnswer(String studentDailyAnswerId,Map<String,Object> options){

 		return countWith(StudentAnswerTable.COLUMN_DAILY_ANSWER, studentDailyAnswerId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentAnswerByDailyAnswerIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentAnswerTable.COLUMN_DAILY_ANSWER, ids, options);
	}
 	
 	
		
		
		

	

	protected StudentAnswer saveStudentAnswer(StudentAnswer  studentAnswer){
		
		if(!studentAnswer.isChanged()){
			return studentAnswer;
		}
		
		
		String SQL=this.getSaveStudentAnswerSQL(studentAnswer);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveStudentAnswerParameters(studentAnswer);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		studentAnswer.incVersion();
		return studentAnswer;
	
	}
	public SmartList<StudentAnswer> saveStudentAnswerList(SmartList<StudentAnswer> studentAnswerList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitStudentAnswerList(studentAnswerList);
		
		batchStudentAnswerCreate((List<StudentAnswer>)lists[CREATE_LIST_INDEX]);
		
		batchStudentAnswerUpdate((List<StudentAnswer>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(StudentAnswer studentAnswer:studentAnswerList){
			if(studentAnswer.isChanged()){
				studentAnswer.incVersion();
			}
			
		
		}
		
		
		return studentAnswerList;
	}

	public SmartList<StudentAnswer> removeStudentAnswerList(SmartList<StudentAnswer> studentAnswerList,Map<String,Object> options){
		
		
		super.removeList(studentAnswerList, options);
		
		return studentAnswerList;
		
		
	}
	
	protected List<Object[]> prepareStudentAnswerBatchCreateArgs(List<StudentAnswer> studentAnswerList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(StudentAnswer studentAnswer:studentAnswerList ){
			Object [] parameters = prepareStudentAnswerCreateParameters(studentAnswer);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareStudentAnswerBatchUpdateArgs(List<StudentAnswer> studentAnswerList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(StudentAnswer studentAnswer:studentAnswerList ){
			if(!studentAnswer.isChanged()){
				continue;
			}
			Object [] parameters = prepareStudentAnswerUpdateParameters(studentAnswer);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchStudentAnswerCreate(List<StudentAnswer> studentAnswerList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareStudentAnswerBatchCreateArgs(studentAnswerList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchStudentAnswerUpdate(List<StudentAnswer> studentAnswerList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareStudentAnswerBatchUpdateArgs(studentAnswerList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitStudentAnswerList(List<StudentAnswer> studentAnswerList){
		
		List<StudentAnswer> studentAnswerCreateList=new ArrayList<StudentAnswer>();
		List<StudentAnswer> studentAnswerUpdateList=new ArrayList<StudentAnswer>();
		
		for(StudentAnswer studentAnswer: studentAnswerList){
			if(isUpdateRequest(studentAnswer)){
				studentAnswerUpdateList.add( studentAnswer);
				continue;
			}
			studentAnswerCreateList.add(studentAnswer);
		}
		
		return new Object[]{studentAnswerCreateList,studentAnswerUpdateList};
	}
	
	protected boolean isUpdateRequest(StudentAnswer studentAnswer){
 		return studentAnswer.getVersion() > 0;
 	}
 	protected String getSaveStudentAnswerSQL(StudentAnswer studentAnswer){
 		if(isUpdateRequest(studentAnswer)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveStudentAnswerParameters(StudentAnswer studentAnswer){
 		if(isUpdateRequest(studentAnswer) ){
 			return prepareStudentAnswerUpdateParameters(studentAnswer);
 		}
 		return prepareStudentAnswerCreateParameters(studentAnswer);
 	}
 	protected Object[] prepareStudentAnswerUpdateParameters(StudentAnswer studentAnswer){
 		Object[] parameters = new Object[7];
  	
 		if(studentAnswer.getHealthSurveyReport() != null){
 			parameters[0] = studentAnswer.getHealthSurveyReport().getId();
 		}
  	
 		if(studentAnswer.getDailyAnswer() != null){
 			parameters[1] = studentAnswer.getDailyAnswer().getId();
 		}
 
 		
 		parameters[2] = studentAnswer.getQuestionTopic();
 		
 		
 		parameters[3] = studentAnswer.getAnswer();
 				
 		parameters[4] = studentAnswer.nextVersion();
 		parameters[5] = studentAnswer.getId();
 		parameters[6] = studentAnswer.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareStudentAnswerCreateParameters(StudentAnswer studentAnswer){
		Object[] parameters = new Object[5];
		String newStudentAnswerId=getNextId();
		studentAnswer.setId(newStudentAnswerId);
		parameters[0] =  studentAnswer.getId();
  	
 		if(studentAnswer.getHealthSurveyReport() != null){
 			parameters[1] = studentAnswer.getHealthSurveyReport().getId();
 		
 		}
 		 	
 		if(studentAnswer.getDailyAnswer() != null){
 			parameters[2] = studentAnswer.getDailyAnswer().getId();
 		
 		}
 		
 		
 		parameters[3] = studentAnswer.getQuestionTopic();
 		
 		
 		parameters[4] = studentAnswer.getAnswer();
 				
 				
 		return parameters;
 	}
 	
	protected StudentAnswer saveInternalStudentAnswer(StudentAnswer studentAnswer, Map<String,Object> options){
		
		saveStudentAnswer(studentAnswer);
 	
 		if(isSaveHealthSurveyReportEnabled(options)){
	 		saveHealthSurveyReport(studentAnswer, options);
 		}
  	
 		if(isSaveDailyAnswerEnabled(options)){
	 		saveDailyAnswer(studentAnswer, options);
 		}
 
		
		return studentAnswer;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected StudentAnswer saveHealthSurveyReport(StudentAnswer studentAnswer, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentAnswer.getHealthSurveyReport() == null){
 			return studentAnswer;//do nothing when it is null
 		}
 		
 		getHealthSurveyReportDAO().save(studentAnswer.getHealthSurveyReport(),options);
 		return studentAnswer;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected StudentAnswer saveDailyAnswer(StudentAnswer studentAnswer, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(studentAnswer.getDailyAnswer() == null){
 			return studentAnswer;//do nothing when it is null
 		}
 		
 		getStudentDailyAnswerDAO().save(studentAnswer.getDailyAnswer(),options);
 		return studentAnswer;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public StudentAnswer present(StudentAnswer studentAnswer,Map<String, Object> options){
	

		return studentAnswer;
	
	}
		

	

	protected String getTableName(){
		return StudentAnswerTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<StudentAnswer> studentAnswerList) {		
		this.enhanceListInternal(studentAnswerList, this.getStudentAnswerMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<StudentAnswer> studentAnswerList = ownerEntity.collectRefsWithType(StudentAnswer.INTERNAL_TYPE);
		this.enhanceList(studentAnswerList);
		
	}
	
	@Override
	public SmartList<StudentAnswer> findStudentAnswerWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getStudentAnswerMapper());

	}
	@Override
	public int countStudentAnswerWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countStudentAnswerWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<StudentAnswer> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getStudentAnswerMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


