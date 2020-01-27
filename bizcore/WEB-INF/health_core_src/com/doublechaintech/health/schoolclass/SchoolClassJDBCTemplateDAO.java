
package com.doublechaintech.health.schoolclass;

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
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class SchoolClassJDBCTemplateDAO extends HealthBaseDAOImpl implements SchoolClassDAO{
 
 	
 	private  TeacherDAO  teacherDAO;
 	public void setTeacherDAO(TeacherDAO teacherDAO){
	 	this.teacherDAO = teacherDAO;
 	}
 	public TeacherDAO getTeacherDAO(){
	 	return this.teacherDAO;
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


			
		
	
  	private  ClassDailyHealthSurveyDAO  classDailyHealthSurveyDAO;
 	public void setClassDailyHealthSurveyDAO(ClassDailyHealthSurveyDAO pClassDailyHealthSurveyDAO){
 	
 		if(pClassDailyHealthSurveyDAO == null){
 			throw new IllegalStateException("Do not try to set classDailyHealthSurveyDAO to null.");
 		}
	 	this.classDailyHealthSurveyDAO = pClassDailyHealthSurveyDAO;
 	}
 	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO(){
 		if(this.classDailyHealthSurveyDAO == null){
 			throw new IllegalStateException("The classDailyHealthSurveyDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.classDailyHealthSurveyDAO;
 	}	
 	
			
		
	
  	private  StudentDAO  studentDAO;
 	public void setStudentDAO(StudentDAO pStudentDAO){
 	
 		if(pStudentDAO == null){
 			throw new IllegalStateException("Do not try to set studentDAO to null.");
 		}
	 	this.studentDAO = pStudentDAO;
 	}
 	public StudentDAO getStudentDAO(){
 		if(this.studentDAO == null){
 			throw new IllegalStateException("The studentDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentDAO;
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
	protected SchoolClass load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalSchoolClass(accessKey, options);
	}
	*/
	
	public SmartList<SchoolClass> loadAll() {
	    return this.loadAll(getSchoolClassMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public SchoolClass load(String id,Map<String,Object> options) throws Exception{
		return loadInternalSchoolClass(SchoolClassTable.withId(id), options);
	}
	
	
	
	public SchoolClass save(SchoolClass schoolClass,Map<String,Object> options){
		
		String methodName="save(SchoolClass schoolClass,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(schoolClass, methodName, "schoolClass");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalSchoolClass(schoolClass,options);
	}
	public SchoolClass clone(String schoolClassId, Map<String,Object> options) throws Exception{
	
		return clone(SchoolClassTable.withId(schoolClassId),options);
	}
	
	protected SchoolClass clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String schoolClassId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		SchoolClass newSchoolClass = loadInternalSchoolClass(accessKey, options);
		newSchoolClass.setVersion(0);
		
		
 		
 		if(isSaveClassDailyHealthSurveyListEnabled(options)){
 			for(ClassDailyHealthSurvey item: newSchoolClass.getClassDailyHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentListEnabled(options)){
 			for(Student item: newSchoolClass.getStudentList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentHealthSurveyListEnabled(options)){
 			for(StudentHealthSurvey item: newSchoolClass.getStudentHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalSchoolClass(newSchoolClass,options);
		
		return newSchoolClass;
	}
	
	
	
	

	protected void throwIfHasException(String schoolClassId,int version,int count) throws Exception{
		if (count == 1) {
			throw new SchoolClassVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new SchoolClassNotFoundException(
					"The " + this.getTableName() + "(" + schoolClassId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String schoolClassId, int version) throws Exception{
	
		String methodName="delete(String schoolClassId, int version)";
		assertMethodArgumentNotNull(schoolClassId, methodName, "schoolClassId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{schoolClassId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(schoolClassId,version);
		}
		
	
	}
	
	
	
	
	

	public SchoolClass disconnectFromAll(String schoolClassId, int version) throws Exception{
	
		
		SchoolClass schoolClass = loadInternalSchoolClass(SchoolClassTable.withId(schoolClassId), emptyOptions());
		schoolClass.clearFromAll();
		this.saveSchoolClass(schoolClass);
		return schoolClass;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return SchoolClassTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "school_class";
	}
	@Override
	protected String getBeanName() {
		
		return "schoolClass";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return SchoolClassTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractClassTeacherEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SchoolClassTokens.CLASSTEACHER);
 	}

 	protected boolean isSaveClassTeacherEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SchoolClassTokens.CLASSTEACHER);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SchoolClassTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SchoolClassTokens.PLATFORM);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SchoolClassTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SchoolClassTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractClassDailyHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SchoolClassTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeClassDailyHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return SchoolClassTokens.of(options).analyzeClassDailyHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveClassDailyHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, SchoolClassTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SchoolClassTokens.STUDENT_LIST);
 	}
 	protected boolean isAnalyzeStudentListEnabled(Map<String,Object> options){		 		
 		return SchoolClassTokens.of(options).analyzeStudentListEnabled();
 	}
	
	protected boolean isSaveStudentListEnabled(Map<String,Object> options){
		return checkOptions(options, SchoolClassTokens.STUDENT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,SchoolClassTokens.STUDENT_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeStudentHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return SchoolClassTokens.of(options).analyzeStudentHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveStudentHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, SchoolClassTokens.STUDENT_HEALTH_SURVEY_LIST);
		
 	}
 	
		

	

	protected SchoolClassMapper getSchoolClassMapper(){
		return new SchoolClassMapper();
	}

	
	
	protected SchoolClass extractSchoolClass(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			SchoolClass schoolClass = loadSingleObject(accessKey, getSchoolClassMapper());
			return schoolClass;
		}catch(EmptyResultDataAccessException e){
			throw new SchoolClassNotFoundException("SchoolClass("+accessKey+") is not found!");
		}

	}

	
	

	protected SchoolClass loadInternalSchoolClass(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		SchoolClass schoolClass = extractSchoolClass(accessKey, loadOptions);
 	
 		if(isExtractClassTeacherEnabled(loadOptions)){
	 		extractClassTeacher(schoolClass, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(schoolClass, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(schoolClass, loadOptions);
 		}
 
		
		if(isExtractClassDailyHealthSurveyListEnabled(loadOptions)){
	 		extractClassDailyHealthSurveyList(schoolClass, loadOptions);
 		}	
 		if(isAnalyzeClassDailyHealthSurveyListEnabled(loadOptions)){
	 		analyzeClassDailyHealthSurveyList(schoolClass, loadOptions);
 		}
 		
		
		if(isExtractStudentListEnabled(loadOptions)){
	 		extractStudentList(schoolClass, loadOptions);
 		}	
 		if(isAnalyzeStudentListEnabled(loadOptions)){
	 		analyzeStudentList(schoolClass, loadOptions);
 		}
 		
		
		if(isExtractStudentHealthSurveyListEnabled(loadOptions)){
	 		extractStudentHealthSurveyList(schoolClass, loadOptions);
 		}	
 		if(isAnalyzeStudentHealthSurveyListEnabled(loadOptions)){
	 		analyzeStudentHealthSurveyList(schoolClass, loadOptions);
 		}
 		
		
		return schoolClass;
		
	}

	 

 	protected SchoolClass extractClassTeacher(SchoolClass schoolClass, Map<String,Object> options) throws Exception{

		if(schoolClass.getClassTeacher() == null){
			return schoolClass;
		}
		String classTeacherId = schoolClass.getClassTeacher().getId();
		if( classTeacherId == null){
			return schoolClass;
		}
		Teacher classTeacher = getTeacherDAO().load(classTeacherId,options);
		if(classTeacher != null){
			schoolClass.setClassTeacher(classTeacher);
		}
		
 		
 		return schoolClass;
 	}
 		
  

 	protected SchoolClass extractPlatform(SchoolClass schoolClass, Map<String,Object> options) throws Exception{

		if(schoolClass.getPlatform() == null){
			return schoolClass;
		}
		String platformId = schoolClass.getPlatform().getId();
		if( platformId == null){
			return schoolClass;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			schoolClass.setPlatform(platform);
		}
		
 		
 		return schoolClass;
 	}
 		
  

 	protected SchoolClass extractCq(SchoolClass schoolClass, Map<String,Object> options) throws Exception{

		if(schoolClass.getCq() == null){
			return schoolClass;
		}
		String cqId = schoolClass.getCq().getId();
		if( cqId == null){
			return schoolClass;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			schoolClass.setCq(cq);
		}
		
 		
 		return schoolClass;
 	}
 		
 
		
	protected void enhanceClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SchoolClass extractClassDailyHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		if(schoolClass == null){
			return null;
		}
		if(schoolClass.getId() == null){
			return schoolClass;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyBySchoolClass(schoolClass.getId(),options);
		if(classDailyHealthSurveyList != null){
			enhanceClassDailyHealthSurveyList(classDailyHealthSurveyList,options);
			schoolClass.setClassDailyHealthSurveyList(classDailyHealthSurveyList);
		}
		
		return schoolClass;
	
	}	
	
	protected SchoolClass analyzeClassDailyHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		if(schoolClass == null){
			return null;
		}
		if(schoolClass.getId() == null){
			return schoolClass;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList != null){
			getClassDailyHealthSurveyDAO().analyzeClassDailyHealthSurveyBySchoolClass(classDailyHealthSurveyList, schoolClass.getId(), options);
			
		}
		
		return schoolClass;
	
	}	
	
		
	protected void enhanceStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SchoolClass extractStudentList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		if(schoolClass == null){
			return null;
		}
		if(schoolClass.getId() == null){
			return schoolClass;
		}

		
		
		SmartList<Student> studentList = getStudentDAO().findStudentBySchoolClass(schoolClass.getId(),options);
		if(studentList != null){
			enhanceStudentList(studentList,options);
			schoolClass.setStudentList(studentList);
		}
		
		return schoolClass;
	
	}	
	
	protected SchoolClass analyzeStudentList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		if(schoolClass == null){
			return null;
		}
		if(schoolClass.getId() == null){
			return schoolClass;
		}

		
		
		SmartList<Student> studentList = schoolClass.getStudentList();
		if(studentList != null){
			getStudentDAO().analyzeStudentBySchoolClass(studentList, schoolClass.getId(), options);
			
		}
		
		return schoolClass;
	
	}	
	
		
	protected void enhanceStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected SchoolClass extractStudentHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		if(schoolClass == null){
			return null;
		}
		if(schoolClass.getId() == null){
			return schoolClass;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = getStudentHealthSurveyDAO().findStudentHealthSurveyBySchoolClass(schoolClass.getId(),options);
		if(studentHealthSurveyList != null){
			enhanceStudentHealthSurveyList(studentHealthSurveyList,options);
			schoolClass.setStudentHealthSurveyList(studentHealthSurveyList);
		}
		
		return schoolClass;
	
	}	
	
	protected SchoolClass analyzeStudentHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		if(schoolClass == null){
			return null;
		}
		if(schoolClass.getId() == null){
			return schoolClass;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();
		if(studentHealthSurveyList != null){
			getStudentHealthSurveyDAO().analyzeStudentHealthSurveyBySchoolClass(studentHealthSurveyList, schoolClass.getId(), options);
			
		}
		
		return schoolClass;
	
	}	
	
		
		
  	
 	public SmartList<SchoolClass> findSchoolClassByClassTeacher(String teacherId,Map<String,Object> options){
 	
  		SmartList<SchoolClass> resultList = queryWith(SchoolClassTable.COLUMN_CLASS_TEACHER, teacherId, options, getSchoolClassMapper());
		// analyzeSchoolClassByClassTeacher(resultList, teacherId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SchoolClass> findSchoolClassByClassTeacher(String teacherId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SchoolClass> resultList =  queryWithRange(SchoolClassTable.COLUMN_CLASS_TEACHER, teacherId, options, getSchoolClassMapper(), start, count);
 		//analyzeSchoolClassByClassTeacher(resultList, teacherId, options);
 		return resultList;
 		
 	}
 	public void analyzeSchoolClassByClassTeacher(SmartList<SchoolClass> resultList, String teacherId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(SchoolClass.CLASS_TEACHER_PROPERTY, teacherId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//SchoolClass.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("学校类");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(SchoolClass.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(SchoolClass.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countSchoolClassByClassTeacher(String teacherId,Map<String,Object> options){

 		return countWith(SchoolClassTable.COLUMN_CLASS_TEACHER, teacherId, options);
 	}
 	@Override
	public Map<String, Integer> countSchoolClassByClassTeacherIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SchoolClassTable.COLUMN_CLASS_TEACHER, ids, options);
	}
 	
  	
 	public SmartList<SchoolClass> findSchoolClassByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<SchoolClass> resultList = queryWith(SchoolClassTable.COLUMN_PLATFORM, platformId, options, getSchoolClassMapper());
		// analyzeSchoolClassByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SchoolClass> findSchoolClassByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SchoolClass> resultList =  queryWithRange(SchoolClassTable.COLUMN_PLATFORM, platformId, options, getSchoolClassMapper(), start, count);
 		//analyzeSchoolClassByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeSchoolClassByPlatform(SmartList<SchoolClass> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(SchoolClass.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//SchoolClass.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("学校类");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(SchoolClass.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(SchoolClass.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countSchoolClassByPlatform(String platformId,Map<String,Object> options){

 		return countWith(SchoolClassTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countSchoolClassByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SchoolClassTable.COLUMN_PLATFORM, ids, options);
	}
 	
  	
 	public SmartList<SchoolClass> findSchoolClassByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<SchoolClass> resultList = queryWith(SchoolClassTable.COLUMN_CQ, changeRequestId, options, getSchoolClassMapper());
		// analyzeSchoolClassByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SchoolClass> findSchoolClassByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SchoolClass> resultList =  queryWithRange(SchoolClassTable.COLUMN_CQ, changeRequestId, options, getSchoolClassMapper(), start, count);
 		//analyzeSchoolClassByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeSchoolClassByCq(SmartList<SchoolClass> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(SchoolClass.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//SchoolClass.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("学校类");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(SchoolClass.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(SchoolClass.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countSchoolClassByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(SchoolClassTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countSchoolClassByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SchoolClassTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected SchoolClass saveSchoolClass(SchoolClass  schoolClass){
		
		if(!schoolClass.isChanged()){
			return schoolClass;
		}
		
		
		String SQL=this.getSaveSchoolClassSQL(schoolClass);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveSchoolClassParameters(schoolClass);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		schoolClass.incVersion();
		return schoolClass;
	
	}
	public SmartList<SchoolClass> saveSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitSchoolClassList(schoolClassList);
		
		batchSchoolClassCreate((List<SchoolClass>)lists[CREATE_LIST_INDEX]);
		
		batchSchoolClassUpdate((List<SchoolClass>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(SchoolClass schoolClass:schoolClassList){
			if(schoolClass.isChanged()){
				schoolClass.incVersion();
			}
			
		
		}
		
		
		return schoolClassList;
	}

	public SmartList<SchoolClass> removeSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options){
		
		
		super.removeList(schoolClassList, options);
		
		return schoolClassList;
		
		
	}
	
	protected List<Object[]> prepareSchoolClassBatchCreateArgs(List<SchoolClass> schoolClassList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SchoolClass schoolClass:schoolClassList ){
			Object [] parameters = prepareSchoolClassCreateParameters(schoolClass);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareSchoolClassBatchUpdateArgs(List<SchoolClass> schoolClassList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SchoolClass schoolClass:schoolClassList ){
			if(!schoolClass.isChanged()){
				continue;
			}
			Object [] parameters = prepareSchoolClassUpdateParameters(schoolClass);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchSchoolClassCreate(List<SchoolClass> schoolClassList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareSchoolClassBatchCreateArgs(schoolClassList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchSchoolClassUpdate(List<SchoolClass> schoolClassList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareSchoolClassBatchUpdateArgs(schoolClassList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitSchoolClassList(List<SchoolClass> schoolClassList){
		
		List<SchoolClass> schoolClassCreateList=new ArrayList<SchoolClass>();
		List<SchoolClass> schoolClassUpdateList=new ArrayList<SchoolClass>();
		
		for(SchoolClass schoolClass: schoolClassList){
			if(isUpdateRequest(schoolClass)){
				schoolClassUpdateList.add( schoolClass);
				continue;
			}
			schoolClassCreateList.add(schoolClass);
		}
		
		return new Object[]{schoolClassCreateList,schoolClassUpdateList};
	}
	
	protected boolean isUpdateRequest(SchoolClass schoolClass){
 		return schoolClass.getVersion() > 0;
 	}
 	protected String getSaveSchoolClassSQL(SchoolClass schoolClass){
 		if(isUpdateRequest(schoolClass)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveSchoolClassParameters(SchoolClass schoolClass){
 		if(isUpdateRequest(schoolClass) ){
 			return prepareSchoolClassUpdateParameters(schoolClass);
 		}
 		return prepareSchoolClassCreateParameters(schoolClass);
 	}
 	protected Object[] prepareSchoolClassUpdateParameters(SchoolClass schoolClass){
 		Object[] parameters = new Object[9];
 
 		parameters[0] = schoolClass.getName(); 	
 		if(schoolClass.getClassTeacher() != null){
 			parameters[1] = schoolClass.getClassTeacher().getId();
 		}
 
 		parameters[2] = schoolClass.getCreateTime(); 	
 		if(schoolClass.getPlatform() != null){
 			parameters[3] = schoolClass.getPlatform().getId();
 		}
 
 		parameters[4] = schoolClass.getSchoole(); 	
 		if(schoolClass.getCq() != null){
 			parameters[5] = schoolClass.getCq().getId();
 		}
 		
 		parameters[6] = schoolClass.nextVersion();
 		parameters[7] = schoolClass.getId();
 		parameters[8] = schoolClass.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareSchoolClassCreateParameters(SchoolClass schoolClass){
		Object[] parameters = new Object[7];
		String newSchoolClassId=getNextId();
		schoolClass.setId(newSchoolClassId);
		parameters[0] =  schoolClass.getId();
 
 		parameters[1] = schoolClass.getName(); 	
 		if(schoolClass.getClassTeacher() != null){
 			parameters[2] = schoolClass.getClassTeacher().getId();
 		
 		}
 		
 		parameters[3] = schoolClass.getCreateTime(); 	
 		if(schoolClass.getPlatform() != null){
 			parameters[4] = schoolClass.getPlatform().getId();
 		
 		}
 		
 		parameters[5] = schoolClass.getSchoole(); 	
 		if(schoolClass.getCq() != null){
 			parameters[6] = schoolClass.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected SchoolClass saveInternalSchoolClass(SchoolClass schoolClass, Map<String,Object> options){
		
		saveSchoolClass(schoolClass);
 	
 		if(isSaveClassTeacherEnabled(options)){
	 		saveClassTeacher(schoolClass, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(schoolClass, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(schoolClass, options);
 		}
 
		
		if(isSaveClassDailyHealthSurveyListEnabled(options)){
	 		saveClassDailyHealthSurveyList(schoolClass, options);
	 		//removeClassDailyHealthSurveyList(schoolClass, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentListEnabled(options)){
	 		saveStudentList(schoolClass, options);
	 		//removeStudentList(schoolClass, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentHealthSurveyListEnabled(options)){
	 		saveStudentHealthSurveyList(schoolClass, options);
	 		//removeStudentHealthSurveyList(schoolClass, options);
	 		//Not delete the record
	 		
 		}		
		
		return schoolClass;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected SchoolClass saveClassTeacher(SchoolClass schoolClass, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(schoolClass.getClassTeacher() == null){
 			return schoolClass;//do nothing when it is null
 		}
 		
 		getTeacherDAO().save(schoolClass.getClassTeacher(),options);
 		return schoolClass;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected SchoolClass savePlatform(SchoolClass schoolClass, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(schoolClass.getPlatform() == null){
 			return schoolClass;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(schoolClass.getPlatform(),options);
 		return schoolClass;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected SchoolClass saveCq(SchoolClass schoolClass, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(schoolClass.getCq() == null){
 			return schoolClass;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(schoolClass.getCq(),options);
 		return schoolClass;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public SchoolClass planToRemoveClassDailyHealthSurveyList(SchoolClass schoolClass, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(ClassDailyHealthSurvey.ID_PROPERTY, classDailyHealthSurveyIds);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return schoolClass;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){

			classDailyHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return schoolClass;	
	
	}


	//disconnect SchoolClass with creator in ClassDailyHealthSurvey
	public SchoolClass planToRemoveClassDailyHealthSurveyListWithCreator(SchoolClass schoolClass, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, creatorId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return schoolClass;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearCreator();
			classDailyHealthSurveyItem.clearSchoolClass();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return schoolClass;
	}
	
	public int countClassDailyHealthSurveyListWithCreator(String schoolClassId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, creatorId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SchoolClass with cq in ClassDailyHealthSurvey
	public SchoolClass planToRemoveClassDailyHealthSurveyListWithCq(SchoolClass schoolClass, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, cqId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return schoolClass;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearCq();
			classDailyHealthSurveyItem.clearSchoolClass();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return schoolClass;
	}
	
	public int countClassDailyHealthSurveyListWithCq(String schoolClassId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, cqId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	public SchoolClass planToRemoveStudentList(SchoolClass schoolClass, String studentIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(Student.ID_PROPERTY, studentIds);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return schoolClass;
		}
		if(externalStudentList.isEmpty()){
			return schoolClass;
		}
		
		for(Student studentItem: externalStudentList){

			studentItem.clearFromAll();
		}
		
		
		SmartList<Student> studentList = schoolClass.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return schoolClass;	
	
	}


	//disconnect SchoolClass with guardian in Student
	public SchoolClass planToRemoveStudentListWithGuardian(SchoolClass schoolClass, String guardianId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return schoolClass;
		}
		if(externalStudentList.isEmpty()){
			return schoolClass;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearGuardian();
			studentItem.clearSchoolClass();
			
		}
		
		
		SmartList<Student> studentList = schoolClass.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return schoolClass;
	}
	
	public int countStudentListWithGuardian(String schoolClassId, String guardianId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect SchoolClass with student_id in Student
	public SchoolClass planToRemoveStudentListWithStudentId(SchoolClass schoolClass, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return schoolClass;
		}
		if(externalStudentList.isEmpty()){
			return schoolClass;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearStudentId();
			studentItem.clearSchoolClass();
			
		}
		
		
		SmartList<Student> studentList = schoolClass.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return schoolClass;
	}
	
	public int countStudentListWithStudentId(String schoolClassId, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect SchoolClass with cq in Student
	public SchoolClass planToRemoveStudentListWithCq(SchoolClass schoolClass, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(Student.CQ_PROPERTY, cqId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return schoolClass;
		}
		if(externalStudentList.isEmpty()){
			return schoolClass;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearCq();
			studentItem.clearSchoolClass();
			
		}
		
		
		SmartList<Student> studentList = schoolClass.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return schoolClass;
	}
	
	public int countStudentListWithCq(String schoolClassId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(Student.CQ_PROPERTY, cqId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	public SchoolClass planToRemoveStudentHealthSurveyList(SchoolClass schoolClass, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(StudentHealthSurvey.ID_PROPERTY, studentHealthSurveyIds);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return schoolClass;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){

			studentHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return schoolClass;	
	
	}


	//disconnect SchoolClass with student in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithStudent(SchoolClass schoolClass, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return schoolClass;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearStudent();
			studentHealthSurveyItem.clearSchoolClass();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return schoolClass;
	}
	
	public int countStudentHealthSurveyListWithStudent(String schoolClassId, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SchoolClass with survey_status in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithSurveyStatus(SchoolClass schoolClass, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return schoolClass;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSurveyStatus();
			studentHealthSurveyItem.clearSchoolClass();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return schoolClass;
	}
	
	public int countStudentHealthSurveyListWithSurveyStatus(String schoolClassId, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SchoolClass with class_daily_health_survey in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(SchoolClass schoolClass, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return schoolClass;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			studentHealthSurveyItem.clearSchoolClass();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return schoolClass;
	}
	
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String schoolClassId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect SchoolClass with cq in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithCq(SchoolClass schoolClass, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClass.getId());
		key.put(StudentHealthSurvey.CQ_PROPERTY, cqId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return schoolClass;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return schoolClass;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearCq();
			studentHealthSurveyItem.clearSchoolClass();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return schoolClass;
	}
	
	public int countStudentHealthSurveyListWithCq(String schoolClassId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		key.put(StudentHealthSurvey.CQ_PROPERTY, cqId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	

		
	protected SchoolClass saveClassDailyHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			//null list means nothing
			return schoolClass;
		}
		SmartList<ClassDailyHealthSurvey> mergedUpdateClassDailyHealthSurveyList = new SmartList<ClassDailyHealthSurvey>();
		
		
		mergedUpdateClassDailyHealthSurveyList.addAll(classDailyHealthSurveyList); 
		if(classDailyHealthSurveyList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateClassDailyHealthSurveyList.addAll(classDailyHealthSurveyList.getToRemoveList());
			classDailyHealthSurveyList.removeAll(classDailyHealthSurveyList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getClassDailyHealthSurveyDAO().saveClassDailyHealthSurveyList(mergedUpdateClassDailyHealthSurveyList,options);
		
		if(classDailyHealthSurveyList.getToRemoveList() != null){
			classDailyHealthSurveyList.removeAll(classDailyHealthSurveyList.getToRemoveList());
		}
		
		
		return schoolClass;
	
	}
	
	protected SchoolClass removeClassDailyHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
	
	
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			return schoolClass;
		}	
	
		SmartList<ClassDailyHealthSurvey> toRemoveClassDailyHealthSurveyList = classDailyHealthSurveyList.getToRemoveList();
		
		if(toRemoveClassDailyHealthSurveyList == null){
			return schoolClass;
		}
		if(toRemoveClassDailyHealthSurveyList.isEmpty()){
			return schoolClass;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassDailyHealthSurveyDAO().removeClassDailyHealthSurveyList(toRemoveClassDailyHealthSurveyList,options);
		
		return schoolClass;
	
	}
	
	

 	
 	
	
	
	
		
	protected SchoolClass saveStudentList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		
		
		SmartList<Student> studentList = schoolClass.getStudentList();
		if(studentList == null){
			//null list means nothing
			return schoolClass;
		}
		SmartList<Student> mergedUpdateStudentList = new SmartList<Student>();
		
		
		mergedUpdateStudentList.addAll(studentList); 
		if(studentList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentList.addAll(studentList.getToRemoveList());
			studentList.removeAll(studentList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentDAO().saveStudentList(mergedUpdateStudentList,options);
		
		if(studentList.getToRemoveList() != null){
			studentList.removeAll(studentList.getToRemoveList());
		}
		
		
		return schoolClass;
	
	}
	
	protected SchoolClass removeStudentList(SchoolClass schoolClass, Map<String,Object> options){
	
	
		SmartList<Student> studentList = schoolClass.getStudentList();
		if(studentList == null){
			return schoolClass;
		}	
	
		SmartList<Student> toRemoveStudentList = studentList.getToRemoveList();
		
		if(toRemoveStudentList == null){
			return schoolClass;
		}
		if(toRemoveStudentList.isEmpty()){
			return schoolClass;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDAO().removeStudentList(toRemoveStudentList,options);
		
		return schoolClass;
	
	}
	
	

 	
 	
	
	
	
		
	protected SchoolClass saveStudentHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
		
		
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			//null list means nothing
			return schoolClass;
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
		
		
		return schoolClass;
	
	}
	
	protected SchoolClass removeStudentHealthSurveyList(SchoolClass schoolClass, Map<String,Object> options){
	
	
		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			return schoolClass;
		}	
	
		SmartList<StudentHealthSurvey> toRemoveStudentHealthSurveyList = studentHealthSurveyList.getToRemoveList();
		
		if(toRemoveStudentHealthSurveyList == null){
			return schoolClass;
		}
		if(toRemoveStudentHealthSurveyList.isEmpty()){
			return schoolClass;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentHealthSurveyDAO().removeStudentHealthSurveyList(toRemoveStudentHealthSurveyList,options);
		
		return schoolClass;
	
	}
	
	

 	
 	
	
	
	
		

	public SchoolClass present(SchoolClass schoolClass,Map<String, Object> options){
	
		presentClassDailyHealthSurveyList(schoolClass,options);
		presentStudentList(schoolClass,options);
		presentStudentHealthSurveyList(schoolClass,options);

		return schoolClass;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected SchoolClass presentClassDailyHealthSurveyList(
			SchoolClass schoolClass,
			Map<String, Object> options) {

		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = schoolClass.getClassDailyHealthSurveyList();		
				SmartList<ClassDailyHealthSurvey> newList= presentSubList(schoolClass.getId(),
				classDailyHealthSurveyList,
				options,
				getClassDailyHealthSurveyDAO()::countClassDailyHealthSurveyBySchoolClass,
				getClassDailyHealthSurveyDAO()::findClassDailyHealthSurveyBySchoolClass
				);

		
		schoolClass.setClassDailyHealthSurveyList(newList);
		

		return schoolClass;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected SchoolClass presentStudentList(
			SchoolClass schoolClass,
			Map<String, Object> options) {

		SmartList<Student> studentList = schoolClass.getStudentList();		
				SmartList<Student> newList= presentSubList(schoolClass.getId(),
				studentList,
				options,
				getStudentDAO()::countStudentBySchoolClass,
				getStudentDAO()::findStudentBySchoolClass
				);

		
		schoolClass.setStudentList(newList);
		

		return schoolClass;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected SchoolClass presentStudentHealthSurveyList(
			SchoolClass schoolClass,
			Map<String, Object> options) {

		SmartList<StudentHealthSurvey> studentHealthSurveyList = schoolClass.getStudentHealthSurveyList();		
				SmartList<StudentHealthSurvey> newList= presentSubList(schoolClass.getId(),
				studentHealthSurveyList,
				options,
				getStudentHealthSurveyDAO()::countStudentHealthSurveyBySchoolClass,
				getStudentHealthSurveyDAO()::findStudentHealthSurveyBySchoolClass
				);

		
		schoolClass.setStudentHealthSurveyList(newList);
		

		return schoolClass;
	}			
		

	
    public SmartList<SchoolClass> requestCandidateSchoolClassForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SchoolClassTable.COLUMN_NAME, filterKey, pageNo, pageSize, getSchoolClassMapper());
    }
		
    public SmartList<SchoolClass> requestCandidateSchoolClassForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SchoolClassTable.COLUMN_NAME, filterKey, pageNo, pageSize, getSchoolClassMapper());
    }
		
    public SmartList<SchoolClass> requestCandidateSchoolClassForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(SchoolClassTable.COLUMN_NAME, filterKey, pageNo, pageSize, getSchoolClassMapper());
    }
		

	protected String getTableName(){
		return SchoolClassTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<SchoolClass> schoolClassList) {		
		this.enhanceListInternal(schoolClassList, this.getSchoolClassMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的schoolClass的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<SchoolClass> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassDailyHealthSurvey> loadedObjs = userContext.getDAOGroup().getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyWithKey(key, options);
		Map<String, List<ClassDailyHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSchoolClass().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ClassDailyHealthSurvey> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ClassDailyHealthSurvey> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setClassDailyHealthSurveyList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Student的schoolClass的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<SchoolClass> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.SCHOOL_CLASS_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Student> loadedObjs = userContext.getDAOGroup().getStudentDAO().findStudentWithKey(key, options);
		Map<String, List<Student>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSchoolClass().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Student> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Student> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的schoolClass的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<SchoolClass> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentHealthSurvey> loadedObjs = userContext.getDAOGroup().getStudentHealthSurveyDAO().findStudentHealthSurveyWithKey(key, options);
		Map<String, List<StudentHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getSchoolClass().getId()));
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
		List<SchoolClass> schoolClassList = ownerEntity.collectRefsWithType(SchoolClass.INTERNAL_TYPE);
		this.enhanceList(schoolClassList);
		
	}
	
	@Override
	public SmartList<SchoolClass> findSchoolClassWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getSchoolClassMapper());

	}
	@Override
	public int countSchoolClassWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countSchoolClassWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<SchoolClass> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getSchoolClassMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


