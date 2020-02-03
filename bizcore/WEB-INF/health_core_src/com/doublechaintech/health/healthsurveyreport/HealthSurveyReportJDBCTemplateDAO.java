
package com.doublechaintech.health.healthsurveyreport;

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


import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;

import com.doublechaintech.health.studentanswer.StudentAnswerDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class HealthSurveyReportJDBCTemplateDAO extends HealthBaseDAOImpl implements HealthSurveyReportDAO{
 
 	
 	private  ClassDailyHealthSurveyDAO  classDailyHealthSurveyDAO;
 	public void setClassDailyHealthSurveyDAO(ClassDailyHealthSurveyDAO classDailyHealthSurveyDAO){
	 	this.classDailyHealthSurveyDAO = classDailyHealthSurveyDAO;
 	}
 	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO(){
	 	return this.classDailyHealthSurveyDAO;
 	}
 
 	
 	private  TeacherDAO  teacherDAO;
 	public void setTeacherDAO(TeacherDAO teacherDAO){
	 	this.teacherDAO = teacherDAO;
 	}
 	public TeacherDAO getTeacherDAO(){
	 	return this.teacherDAO;
 	}
 
 	
 	private  StudentDAO  studentDAO;
 	public void setStudentDAO(StudentDAO studentDAO){
	 	this.studentDAO = studentDAO;
 	}
 	public StudentDAO getStudentDAO(){
	 	return this.studentDAO;
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
	protected HealthSurveyReport load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalHealthSurveyReport(accessKey, options);
	}
	*/
	
	public SmartList<HealthSurveyReport> loadAll() {
	    return this.loadAll(getHealthSurveyReportMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public HealthSurveyReport load(String id,Map<String,Object> options) throws Exception{
		return loadInternalHealthSurveyReport(HealthSurveyReportTable.withId(id), options);
	}
	
	
	
	public HealthSurveyReport save(HealthSurveyReport healthSurveyReport,Map<String,Object> options){
		
		String methodName="save(HealthSurveyReport healthSurveyReport,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(healthSurveyReport, methodName, "healthSurveyReport");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalHealthSurveyReport(healthSurveyReport,options);
	}
	public HealthSurveyReport clone(String healthSurveyReportId, Map<String,Object> options) throws Exception{
	
		return clone(HealthSurveyReportTable.withId(healthSurveyReportId),options);
	}
	
	protected HealthSurveyReport clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String healthSurveyReportId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		HealthSurveyReport newHealthSurveyReport = loadInternalHealthSurveyReport(accessKey, options);
		newHealthSurveyReport.setVersion(0);
		
		
 		
 		if(isSaveStudentAnswerListEnabled(options)){
 			for(StudentAnswer item: newHealthSurveyReport.getStudentAnswerList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalHealthSurveyReport(newHealthSurveyReport,options);
		
		return newHealthSurveyReport;
	}
	
	
	
	

	protected void throwIfHasException(String healthSurveyReportId,int version,int count) throws Exception{
		if (count == 1) {
			throw new HealthSurveyReportVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new HealthSurveyReportNotFoundException(
					"The " + this.getTableName() + "(" + healthSurveyReportId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String healthSurveyReportId, int version) throws Exception{
	
		String methodName="delete(String healthSurveyReportId, int version)";
		assertMethodArgumentNotNull(healthSurveyReportId, methodName, "healthSurveyReportId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{healthSurveyReportId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(healthSurveyReportId,version);
		}
		
	
	}
	
	
	
	
	

	public HealthSurveyReport disconnectFromAll(String healthSurveyReportId, int version) throws Exception{
	
		
		HealthSurveyReport healthSurveyReport = loadInternalHealthSurveyReport(HealthSurveyReportTable.withId(healthSurveyReportId), emptyOptions());
		healthSurveyReport.clearFromAll();
		this.saveHealthSurveyReport(healthSurveyReport);
		return healthSurveyReport;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return HealthSurveyReportTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "health_survey_report";
	}
	@Override
	protected String getBeanName() {
		
		return "healthSurveyReport";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return HealthSurveyReportTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractStudentEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, HealthSurveyReportTokens.STUDENT);
 	}

 	protected boolean isSaveStudentEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, HealthSurveyReportTokens.STUDENT);
 	}
 	

 	
  

 	protected boolean isExtractTeacherEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, HealthSurveyReportTokens.TEACHER);
 	}

 	protected boolean isSaveTeacherEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, HealthSurveyReportTokens.TEACHER);
 	}
 	

 	
  

 	protected boolean isExtractSurveyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, HealthSurveyReportTokens.SURVEY);
 	}

 	protected boolean isSaveSurveyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, HealthSurveyReportTokens.SURVEY);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentAnswerListEnabled(Map<String,Object> options){		
 		return checkOptions(options,HealthSurveyReportTokens.STUDENT_ANSWER_LIST);
 	}
 	protected boolean isAnalyzeStudentAnswerListEnabled(Map<String,Object> options){		 		
 		return HealthSurveyReportTokens.of(options).analyzeStudentAnswerListEnabled();
 	}
	
	protected boolean isSaveStudentAnswerListEnabled(Map<String,Object> options){
		return checkOptions(options, HealthSurveyReportTokens.STUDENT_ANSWER_LIST);
		
 	}
 	
		

	

	protected HealthSurveyReportMapper getHealthSurveyReportMapper(){
		return new HealthSurveyReportMapper();
	}

	
	
	protected HealthSurveyReport extractHealthSurveyReport(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			HealthSurveyReport healthSurveyReport = loadSingleObject(accessKey, getHealthSurveyReportMapper());
			return healthSurveyReport;
		}catch(EmptyResultDataAccessException e){
			throw new HealthSurveyReportNotFoundException("HealthSurveyReport("+accessKey+") is not found!");
		}

	}

	
	

	protected HealthSurveyReport loadInternalHealthSurveyReport(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		HealthSurveyReport healthSurveyReport = extractHealthSurveyReport(accessKey, loadOptions);
 	
 		if(isExtractStudentEnabled(loadOptions)){
	 		extractStudent(healthSurveyReport, loadOptions);
 		}
  	
 		if(isExtractTeacherEnabled(loadOptions)){
	 		extractTeacher(healthSurveyReport, loadOptions);
 		}
  	
 		if(isExtractSurveyEnabled(loadOptions)){
	 		extractSurvey(healthSurveyReport, loadOptions);
 		}
 
		
		if(isExtractStudentAnswerListEnabled(loadOptions)){
	 		extractStudentAnswerList(healthSurveyReport, loadOptions);
 		}	
 		if(isAnalyzeStudentAnswerListEnabled(loadOptions)){
	 		analyzeStudentAnswerList(healthSurveyReport, loadOptions);
 		}
 		
		
		return healthSurveyReport;
		
	}

	 

 	protected HealthSurveyReport extractStudent(HealthSurveyReport healthSurveyReport, Map<String,Object> options) throws Exception{

		if(healthSurveyReport.getStudent() == null){
			return healthSurveyReport;
		}
		String studentId = healthSurveyReport.getStudent().getId();
		if( studentId == null){
			return healthSurveyReport;
		}
		Student student = getStudentDAO().load(studentId,options);
		if(student != null){
			healthSurveyReport.setStudent(student);
		}
		
 		
 		return healthSurveyReport;
 	}
 		
  

 	protected HealthSurveyReport extractTeacher(HealthSurveyReport healthSurveyReport, Map<String,Object> options) throws Exception{

		if(healthSurveyReport.getTeacher() == null){
			return healthSurveyReport;
		}
		String teacherId = healthSurveyReport.getTeacher().getId();
		if( teacherId == null){
			return healthSurveyReport;
		}
		Teacher teacher = getTeacherDAO().load(teacherId,options);
		if(teacher != null){
			healthSurveyReport.setTeacher(teacher);
		}
		
 		
 		return healthSurveyReport;
 	}
 		
  

 	protected HealthSurveyReport extractSurvey(HealthSurveyReport healthSurveyReport, Map<String,Object> options) throws Exception{

		if(healthSurveyReport.getSurvey() == null){
			return healthSurveyReport;
		}
		String surveyId = healthSurveyReport.getSurvey().getId();
		if( surveyId == null){
			return healthSurveyReport;
		}
		ClassDailyHealthSurvey survey = getClassDailyHealthSurveyDAO().load(surveyId,options);
		if(survey != null){
			healthSurveyReport.setSurvey(survey);
		}
		
 		
 		return healthSurveyReport;
 	}
 		
 
		
	protected void enhanceStudentAnswerList(SmartList<StudentAnswer> studentAnswerList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected HealthSurveyReport extractStudentAnswerList(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
		
		
		if(healthSurveyReport == null){
			return null;
		}
		if(healthSurveyReport.getId() == null){
			return healthSurveyReport;
		}

		
		
		SmartList<StudentAnswer> studentAnswerList = getStudentAnswerDAO().findStudentAnswerByHealthSurveyReport(healthSurveyReport.getId(),options);
		if(studentAnswerList != null){
			enhanceStudentAnswerList(studentAnswerList,options);
			healthSurveyReport.setStudentAnswerList(studentAnswerList);
		}
		
		return healthSurveyReport;
	
	}	
	
	protected HealthSurveyReport analyzeStudentAnswerList(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
		
		
		if(healthSurveyReport == null){
			return null;
		}
		if(healthSurveyReport.getId() == null){
			return healthSurveyReport;
		}

		
		
		SmartList<StudentAnswer> studentAnswerList = healthSurveyReport.getStudentAnswerList();
		if(studentAnswerList != null){
			getStudentAnswerDAO().analyzeStudentAnswerByHealthSurveyReport(studentAnswerList, healthSurveyReport.getId(), options);
			
		}
		
		return healthSurveyReport;
	
	}	
	
		
		
  	
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByStudent(String studentId,Map<String,Object> options){
 	
  		SmartList<HealthSurveyReport> resultList = queryWith(HealthSurveyReportTable.COLUMN_STUDENT, studentId, options, getHealthSurveyReportMapper());
		// analyzeHealthSurveyReportByStudent(resultList, studentId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByStudent(String studentId, int start, int count,Map<String,Object> options){
 		
 		SmartList<HealthSurveyReport> resultList =  queryWithRange(HealthSurveyReportTable.COLUMN_STUDENT, studentId, options, getHealthSurveyReportMapper(), start, count);
 		//analyzeHealthSurveyReportByStudent(resultList, studentId, options);
 		return resultList;
 		
 	}
 	public void analyzeHealthSurveyReportByStudent(SmartList<HealthSurveyReport> resultList, String studentId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(HealthSurveyReport.STUDENT_PROPERTY, studentId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem surveyTimeStatsItem = new StatsItem();
		//HealthSurveyReport.SURVEY_TIME_PROPERTY
		surveyTimeStatsItem.setDisplayName("健康调查报告");
		surveyTimeStatsItem.setInternalName(formatKeyForDateLine(HealthSurveyReport.SURVEY_TIME_PROPERTY));
		surveyTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(HealthSurveyReport.SURVEY_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(surveyTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countHealthSurveyReportByStudent(String studentId,Map<String,Object> options){

 		return countWith(HealthSurveyReportTable.COLUMN_STUDENT, studentId, options);
 	}
 	@Override
	public Map<String, Integer> countHealthSurveyReportByStudentIds(String[] ids, Map<String, Object> options) {
		return countWithIds(HealthSurveyReportTable.COLUMN_STUDENT, ids, options);
	}
 	
  	
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByTeacher(String teacherId,Map<String,Object> options){
 	
  		SmartList<HealthSurveyReport> resultList = queryWith(HealthSurveyReportTable.COLUMN_TEACHER, teacherId, options, getHealthSurveyReportMapper());
		// analyzeHealthSurveyReportByTeacher(resultList, teacherId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByTeacher(String teacherId, int start, int count,Map<String,Object> options){
 		
 		SmartList<HealthSurveyReport> resultList =  queryWithRange(HealthSurveyReportTable.COLUMN_TEACHER, teacherId, options, getHealthSurveyReportMapper(), start, count);
 		//analyzeHealthSurveyReportByTeacher(resultList, teacherId, options);
 		return resultList;
 		
 	}
 	public void analyzeHealthSurveyReportByTeacher(SmartList<HealthSurveyReport> resultList, String teacherId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(HealthSurveyReport.TEACHER_PROPERTY, teacherId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem surveyTimeStatsItem = new StatsItem();
		//HealthSurveyReport.SURVEY_TIME_PROPERTY
		surveyTimeStatsItem.setDisplayName("健康调查报告");
		surveyTimeStatsItem.setInternalName(formatKeyForDateLine(HealthSurveyReport.SURVEY_TIME_PROPERTY));
		surveyTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(HealthSurveyReport.SURVEY_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(surveyTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countHealthSurveyReportByTeacher(String teacherId,Map<String,Object> options){

 		return countWith(HealthSurveyReportTable.COLUMN_TEACHER, teacherId, options);
 	}
 	@Override
	public Map<String, Integer> countHealthSurveyReportByTeacherIds(String[] ids, Map<String, Object> options) {
		return countWithIds(HealthSurveyReportTable.COLUMN_TEACHER, ids, options);
	}
 	
  	
 	public SmartList<HealthSurveyReport> findHealthSurveyReportBySurvey(String classDailyHealthSurveyId,Map<String,Object> options){
 	
  		SmartList<HealthSurveyReport> resultList = queryWith(HealthSurveyReportTable.COLUMN_SURVEY, classDailyHealthSurveyId, options, getHealthSurveyReportMapper());
		// analyzeHealthSurveyReportBySurvey(resultList, classDailyHealthSurveyId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<HealthSurveyReport> findHealthSurveyReportBySurvey(String classDailyHealthSurveyId, int start, int count,Map<String,Object> options){
 		
 		SmartList<HealthSurveyReport> resultList =  queryWithRange(HealthSurveyReportTable.COLUMN_SURVEY, classDailyHealthSurveyId, options, getHealthSurveyReportMapper(), start, count);
 		//analyzeHealthSurveyReportBySurvey(resultList, classDailyHealthSurveyId, options);
 		return resultList;
 		
 	}
 	public void analyzeHealthSurveyReportBySurvey(SmartList<HealthSurveyReport> resultList, String classDailyHealthSurveyId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(HealthSurveyReport.SURVEY_PROPERTY, classDailyHealthSurveyId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem surveyTimeStatsItem = new StatsItem();
		//HealthSurveyReport.SURVEY_TIME_PROPERTY
		surveyTimeStatsItem.setDisplayName("健康调查报告");
		surveyTimeStatsItem.setInternalName(formatKeyForDateLine(HealthSurveyReport.SURVEY_TIME_PROPERTY));
		surveyTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(HealthSurveyReport.SURVEY_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(surveyTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countHealthSurveyReportBySurvey(String classDailyHealthSurveyId,Map<String,Object> options){

 		return countWith(HealthSurveyReportTable.COLUMN_SURVEY, classDailyHealthSurveyId, options);
 	}
 	@Override
	public Map<String, Integer> countHealthSurveyReportBySurveyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(HealthSurveyReportTable.COLUMN_SURVEY, ids, options);
	}
 	
 	
		
		
		

	

	protected HealthSurveyReport saveHealthSurveyReport(HealthSurveyReport  healthSurveyReport){
		
		if(!healthSurveyReport.isChanged()){
			return healthSurveyReport;
		}
		
		
		String SQL=this.getSaveHealthSurveyReportSQL(healthSurveyReport);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveHealthSurveyReportParameters(healthSurveyReport);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		healthSurveyReport.incVersion();
		return healthSurveyReport;
	
	}
	public SmartList<HealthSurveyReport> saveHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitHealthSurveyReportList(healthSurveyReportList);
		
		batchHealthSurveyReportCreate((List<HealthSurveyReport>)lists[CREATE_LIST_INDEX]);
		
		batchHealthSurveyReportUpdate((List<HealthSurveyReport>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(HealthSurveyReport healthSurveyReport:healthSurveyReportList){
			if(healthSurveyReport.isChanged()){
				healthSurveyReport.incVersion();
			}
			
		
		}
		
		
		return healthSurveyReportList;
	}

	public SmartList<HealthSurveyReport> removeHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList,Map<String,Object> options){
		
		
		super.removeList(healthSurveyReportList, options);
		
		return healthSurveyReportList;
		
		
	}
	
	protected List<Object[]> prepareHealthSurveyReportBatchCreateArgs(List<HealthSurveyReport> healthSurveyReportList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(HealthSurveyReport healthSurveyReport:healthSurveyReportList ){
			Object [] parameters = prepareHealthSurveyReportCreateParameters(healthSurveyReport);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareHealthSurveyReportBatchUpdateArgs(List<HealthSurveyReport> healthSurveyReportList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(HealthSurveyReport healthSurveyReport:healthSurveyReportList ){
			if(!healthSurveyReport.isChanged()){
				continue;
			}
			Object [] parameters = prepareHealthSurveyReportUpdateParameters(healthSurveyReport);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchHealthSurveyReportCreate(List<HealthSurveyReport> healthSurveyReportList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareHealthSurveyReportBatchCreateArgs(healthSurveyReportList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchHealthSurveyReportUpdate(List<HealthSurveyReport> healthSurveyReportList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareHealthSurveyReportBatchUpdateArgs(healthSurveyReportList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitHealthSurveyReportList(List<HealthSurveyReport> healthSurveyReportList){
		
		List<HealthSurveyReport> healthSurveyReportCreateList=new ArrayList<HealthSurveyReport>();
		List<HealthSurveyReport> healthSurveyReportUpdateList=new ArrayList<HealthSurveyReport>();
		
		for(HealthSurveyReport healthSurveyReport: healthSurveyReportList){
			if(isUpdateRequest(healthSurveyReport)){
				healthSurveyReportUpdateList.add( healthSurveyReport);
				continue;
			}
			healthSurveyReportCreateList.add(healthSurveyReport);
		}
		
		return new Object[]{healthSurveyReportCreateList,healthSurveyReportUpdateList};
	}
	
	protected boolean isUpdateRequest(HealthSurveyReport healthSurveyReport){
 		return healthSurveyReport.getVersion() > 0;
 	}
 	protected String getSaveHealthSurveyReportSQL(HealthSurveyReport healthSurveyReport){
 		if(isUpdateRequest(healthSurveyReport)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveHealthSurveyReportParameters(HealthSurveyReport healthSurveyReport){
 		if(isUpdateRequest(healthSurveyReport) ){
 			return prepareHealthSurveyReportUpdateParameters(healthSurveyReport);
 		}
 		return prepareHealthSurveyReportCreateParameters(healthSurveyReport);
 	}
 	protected Object[] prepareHealthSurveyReportUpdateParameters(HealthSurveyReport healthSurveyReport){
 		Object[] parameters = new Object[15];
 
 		parameters[0] = healthSurveyReport.getSurveyName();
 		parameters[1] = healthSurveyReport.getSurveyTime();
 		parameters[2] = healthSurveyReport.getTeacherName();
 		parameters[3] = healthSurveyReport.getSchool();
 		parameters[4] = healthSurveyReport.getSchoolClass();
 		parameters[5] = healthSurveyReport.getStudentName();
 		parameters[6] = healthSurveyReport.getStudentNumber();
 		parameters[7] = healthSurveyReport.getGuardianName();
 		parameters[8] = healthSurveyReport.getGuardianMobile(); 	
 		if(healthSurveyReport.getStudent() != null){
 			parameters[9] = healthSurveyReport.getStudent().getId();
 		}
  	
 		if(healthSurveyReport.getTeacher() != null){
 			parameters[10] = healthSurveyReport.getTeacher().getId();
 		}
  	
 		if(healthSurveyReport.getSurvey() != null){
 			parameters[11] = healthSurveyReport.getSurvey().getId();
 		}
 		
 		parameters[12] = healthSurveyReport.nextVersion();
 		parameters[13] = healthSurveyReport.getId();
 		parameters[14] = healthSurveyReport.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareHealthSurveyReportCreateParameters(HealthSurveyReport healthSurveyReport){
		Object[] parameters = new Object[13];
		String newHealthSurveyReportId=getNextId();
		healthSurveyReport.setId(newHealthSurveyReportId);
		parameters[0] =  healthSurveyReport.getId();
 
 		parameters[1] = healthSurveyReport.getSurveyName();
 		parameters[2] = healthSurveyReport.getSurveyTime();
 		parameters[3] = healthSurveyReport.getTeacherName();
 		parameters[4] = healthSurveyReport.getSchool();
 		parameters[5] = healthSurveyReport.getSchoolClass();
 		parameters[6] = healthSurveyReport.getStudentName();
 		parameters[7] = healthSurveyReport.getStudentNumber();
 		parameters[8] = healthSurveyReport.getGuardianName();
 		parameters[9] = healthSurveyReport.getGuardianMobile(); 	
 		if(healthSurveyReport.getStudent() != null){
 			parameters[10] = healthSurveyReport.getStudent().getId();
 		
 		}
 		 	
 		if(healthSurveyReport.getTeacher() != null){
 			parameters[11] = healthSurveyReport.getTeacher().getId();
 		
 		}
 		 	
 		if(healthSurveyReport.getSurvey() != null){
 			parameters[12] = healthSurveyReport.getSurvey().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected HealthSurveyReport saveInternalHealthSurveyReport(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
		
		saveHealthSurveyReport(healthSurveyReport);
 	
 		if(isSaveStudentEnabled(options)){
	 		saveStudent(healthSurveyReport, options);
 		}
  	
 		if(isSaveTeacherEnabled(options)){
	 		saveTeacher(healthSurveyReport, options);
 		}
  	
 		if(isSaveSurveyEnabled(options)){
	 		saveSurvey(healthSurveyReport, options);
 		}
 
		
		if(isSaveStudentAnswerListEnabled(options)){
	 		saveStudentAnswerList(healthSurveyReport, options);
	 		//removeStudentAnswerList(healthSurveyReport, options);
	 		//Not delete the record
	 		
 		}		
		
		return healthSurveyReport;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected HealthSurveyReport saveStudent(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(healthSurveyReport.getStudent() == null){
 			return healthSurveyReport;//do nothing when it is null
 		}
 		
 		getStudentDAO().save(healthSurveyReport.getStudent(),options);
 		return healthSurveyReport;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected HealthSurveyReport saveTeacher(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(healthSurveyReport.getTeacher() == null){
 			return healthSurveyReport;//do nothing when it is null
 		}
 		
 		getTeacherDAO().save(healthSurveyReport.getTeacher(),options);
 		return healthSurveyReport;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected HealthSurveyReport saveSurvey(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(healthSurveyReport.getSurvey() == null){
 			return healthSurveyReport;//do nothing when it is null
 		}
 		
 		getClassDailyHealthSurveyDAO().save(healthSurveyReport.getSurvey(),options);
 		return healthSurveyReport;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public HealthSurveyReport planToRemoveStudentAnswerList(HealthSurveyReport healthSurveyReport, String studentAnswerIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, healthSurveyReport.getId());
		key.put(StudentAnswer.ID_PROPERTY, studentAnswerIds);
		
		SmartList<StudentAnswer> externalStudentAnswerList = getStudentAnswerDAO().
				findStudentAnswerWithKey(key, options);
		if(externalStudentAnswerList == null){
			return healthSurveyReport;
		}
		if(externalStudentAnswerList.isEmpty()){
			return healthSurveyReport;
		}
		
		for(StudentAnswer studentAnswerItem: externalStudentAnswerList){

			studentAnswerItem.clearFromAll();
		}
		
		
		SmartList<StudentAnswer> studentAnswerList = healthSurveyReport.getStudentAnswerList();		
		studentAnswerList.addAllToRemoveList(externalStudentAnswerList);
		return healthSurveyReport;	
	
	}


	//disconnect HealthSurveyReport with daily_answer in StudentAnswer
	public HealthSurveyReport planToRemoveStudentAnswerListWithDailyAnswer(HealthSurveyReport healthSurveyReport, String dailyAnswerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, healthSurveyReport.getId());
		key.put(StudentAnswer.DAILY_ANSWER_PROPERTY, dailyAnswerId);
		
		SmartList<StudentAnswer> externalStudentAnswerList = getStudentAnswerDAO().
				findStudentAnswerWithKey(key, options);
		if(externalStudentAnswerList == null){
			return healthSurveyReport;
		}
		if(externalStudentAnswerList.isEmpty()){
			return healthSurveyReport;
		}
		
		for(StudentAnswer studentAnswerItem: externalStudentAnswerList){
			studentAnswerItem.clearDailyAnswer();
			studentAnswerItem.clearHealthSurveyReport();
			
		}
		
		
		SmartList<StudentAnswer> studentAnswerList = healthSurveyReport.getStudentAnswerList();		
		studentAnswerList.addAllToRemoveList(externalStudentAnswerList);
		return healthSurveyReport;
	}
	
	public int countStudentAnswerListWithDailyAnswer(String healthSurveyReportId, String dailyAnswerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, healthSurveyReportId);
		key.put(StudentAnswer.DAILY_ANSWER_PROPERTY, dailyAnswerId);
		
		int count = getStudentAnswerDAO().countStudentAnswerWithKey(key, options);
		return count;
	}
	

		
	protected HealthSurveyReport saveStudentAnswerList(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
		
		
		
		
		SmartList<StudentAnswer> studentAnswerList = healthSurveyReport.getStudentAnswerList();
		if(studentAnswerList == null){
			//null list means nothing
			return healthSurveyReport;
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
		
		
		return healthSurveyReport;
	
	}
	
	protected HealthSurveyReport removeStudentAnswerList(HealthSurveyReport healthSurveyReport, Map<String,Object> options){
	
	
		SmartList<StudentAnswer> studentAnswerList = healthSurveyReport.getStudentAnswerList();
		if(studentAnswerList == null){
			return healthSurveyReport;
		}	
	
		SmartList<StudentAnswer> toRemoveStudentAnswerList = studentAnswerList.getToRemoveList();
		
		if(toRemoveStudentAnswerList == null){
			return healthSurveyReport;
		}
		if(toRemoveStudentAnswerList.isEmpty()){
			return healthSurveyReport;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentAnswerDAO().removeStudentAnswerList(toRemoveStudentAnswerList,options);
		
		return healthSurveyReport;
	
	}
	
	

 	
 	
	
	
	
		

	public HealthSurveyReport present(HealthSurveyReport healthSurveyReport,Map<String, Object> options){
	
		presentStudentAnswerList(healthSurveyReport,options);

		return healthSurveyReport;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected HealthSurveyReport presentStudentAnswerList(
			HealthSurveyReport healthSurveyReport,
			Map<String, Object> options) {

		SmartList<StudentAnswer> studentAnswerList = healthSurveyReport.getStudentAnswerList();		
				SmartList<StudentAnswer> newList= presentSubList(healthSurveyReport.getId(),
				studentAnswerList,
				options,
				getStudentAnswerDAO()::countStudentAnswerByHealthSurveyReport,
				getStudentAnswerDAO()::findStudentAnswerByHealthSurveyReport
				);

		
		healthSurveyReport.setStudentAnswerList(newList);
		

		return healthSurveyReport;
	}			
		

	
    public SmartList<HealthSurveyReport> requestCandidateHealthSurveyReportForStudentAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(HealthSurveyReportTable.COLUMN_SURVEY_NAME, filterKey, pageNo, pageSize, getHealthSurveyReportMapper());
    }
		

	protected String getTableName(){
		return HealthSurveyReportTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<HealthSurveyReport> healthSurveyReportList) {		
		this.enhanceListInternal(healthSurveyReportList, this.getHealthSurveyReportMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:StudentAnswer的healthSurveyReport的StudentAnswerList
	public SmartList<StudentAnswer> loadOurStudentAnswerList(HealthUserContext userContext, List<HealthSurveyReport> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentAnswer.HEALTH_SURVEY_REPORT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentAnswer> loadedObjs = userContext.getDAOGroup().getStudentAnswerDAO().findStudentAnswerWithKey(key, options);
		Map<String, List<StudentAnswer>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getHealthSurveyReport().getId()));
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
		List<HealthSurveyReport> healthSurveyReportList = ownerEntity.collectRefsWithType(HealthSurveyReport.INTERNAL_TYPE);
		this.enhanceList(healthSurveyReportList);
		
	}
	
	@Override
	public SmartList<HealthSurveyReport> findHealthSurveyReportWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getHealthSurveyReportMapper());

	}
	@Override
	public int countHealthSurveyReportWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countHealthSurveyReportWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<HealthSurveyReport> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getHealthSurveyReportMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


