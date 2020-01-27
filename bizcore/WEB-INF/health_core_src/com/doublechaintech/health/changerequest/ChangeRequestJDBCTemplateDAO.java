
package com.doublechaintech.health.changerequest;

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
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.guardian.GuardianDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;
import com.doublechaintech.health.changerequesttype.ChangeRequestTypeDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class ChangeRequestJDBCTemplateDAO extends HealthBaseDAOImpl implements ChangeRequestDAO{
 
 	
 	private  ChangeRequestTypeDAO  changeRequestTypeDAO;
 	public void setChangeRequestTypeDAO(ChangeRequestTypeDAO changeRequestTypeDAO){
	 	this.changeRequestTypeDAO = changeRequestTypeDAO;
 	}
 	public ChangeRequestTypeDAO getChangeRequestTypeDAO(){
	 	return this.changeRequestTypeDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  SchoolClassDAO  schoolClassDAO;
 	public void setSchoolClassDAO(SchoolClassDAO pSchoolClassDAO){
 	
 		if(pSchoolClassDAO == null){
 			throw new IllegalStateException("Do not try to set schoolClassDAO to null.");
 		}
	 	this.schoolClassDAO = pSchoolClassDAO;
 	}
 	public SchoolClassDAO getSchoolClassDAO(){
 		if(this.schoolClassDAO == null){
 			throw new IllegalStateException("The schoolClassDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.schoolClassDAO;
 	}	
 	
			
		
	
  	private  TeacherDAO  teacherDAO;
 	public void setTeacherDAO(TeacherDAO pTeacherDAO){
 	
 		if(pTeacherDAO == null){
 			throw new IllegalStateException("Do not try to set teacherDAO to null.");
 		}
	 	this.teacherDAO = pTeacherDAO;
 	}
 	public TeacherDAO getTeacherDAO(){
 		if(this.teacherDAO == null){
 			throw new IllegalStateException("The teacherDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.teacherDAO;
 	}	
 	
			
		
	
  	private  GuardianDAO  guardianDAO;
 	public void setGuardianDAO(GuardianDAO pGuardianDAO){
 	
 		if(pGuardianDAO == null){
 			throw new IllegalStateException("Do not try to set guardianDAO to null.");
 		}
	 	this.guardianDAO = pGuardianDAO;
 	}
 	public GuardianDAO getGuardianDAO(){
 		if(this.guardianDAO == null){
 			throw new IllegalStateException("The guardianDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.guardianDAO;
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
	protected ChangeRequest load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequest(accessKey, options);
	}
	*/
	
	public SmartList<ChangeRequest> loadAll() {
	    return this.loadAll(getChangeRequestMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ChangeRequest load(String id,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequest(ChangeRequestTable.withId(id), options);
	}
	
	
	
	public ChangeRequest save(ChangeRequest changeRequest,Map<String,Object> options){
		
		String methodName="save(ChangeRequest changeRequest,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(changeRequest, methodName, "changeRequest");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalChangeRequest(changeRequest,options);
	}
	public ChangeRequest clone(String changeRequestId, Map<String,Object> options) throws Exception{
	
		return clone(ChangeRequestTable.withId(changeRequestId),options);
	}
	
	protected ChangeRequest clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String changeRequestId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ChangeRequest newChangeRequest = loadInternalChangeRequest(accessKey, options);
		newChangeRequest.setVersion(0);
		
		
 		
 		if(isSaveSchoolClassListEnabled(options)){
 			for(SchoolClass item: newChangeRequest.getSchoolClassList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveTeacherListEnabled(options)){
 			for(Teacher item: newChangeRequest.getTeacherList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveGuardianListEnabled(options)){
 			for(Guardian item: newChangeRequest.getGuardianList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveClassQuestionListEnabled(options)){
 			for(ClassQuestion item: newChangeRequest.getClassQuestionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveClassDailyHealthSurveyListEnabled(options)){
 			for(ClassDailyHealthSurvey item: newChangeRequest.getClassDailyHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentListEnabled(options)){
 			for(Student item: newChangeRequest.getStudentList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentHealthSurveyListEnabled(options)){
 			for(StudentHealthSurvey item: newChangeRequest.getStudentHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentDailyAnswerListEnabled(options)){
 			for(StudentDailyAnswer item: newChangeRequest.getStudentDailyAnswerList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalChangeRequest(newChangeRequest,options);
		
		return newChangeRequest;
	}
	
	
	
	

	protected void throwIfHasException(String changeRequestId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ChangeRequestVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ChangeRequestNotFoundException(
					"The " + this.getTableName() + "(" + changeRequestId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String changeRequestId, int version) throws Exception{
	
		String methodName="delete(String changeRequestId, int version)";
		assertMethodArgumentNotNull(changeRequestId, methodName, "changeRequestId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{changeRequestId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(changeRequestId,version);
		}
		
	
	}
	
	
	
	
	

	public ChangeRequest disconnectFromAll(String changeRequestId, int version) throws Exception{
	
		
		ChangeRequest changeRequest = loadInternalChangeRequest(ChangeRequestTable.withId(changeRequestId), emptyOptions());
		changeRequest.clearFromAll();
		this.saveChangeRequest(changeRequest);
		return changeRequest;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ChangeRequestTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "change_request";
	}
	@Override
	protected String getBeanName() {
		
		return "changeRequest";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ChangeRequestTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractRequestTypeEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestTokens.REQUESTTYPE);
 	}

 	protected boolean isSaveRequestTypeEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestTokens.REQUESTTYPE);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractSchoolClassListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.SCHOOL_CLASS_LIST);
 	}
 	protected boolean isAnalyzeSchoolClassListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeSchoolClassListEnabled();
 	}
	
	protected boolean isSaveSchoolClassListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.SCHOOL_CLASS_LIST);
		
 	}
 	
		
	
	protected boolean isExtractTeacherListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.TEACHER_LIST);
 	}
 	protected boolean isAnalyzeTeacherListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeTeacherListEnabled();
 	}
	
	protected boolean isSaveTeacherListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.TEACHER_LIST);
		
 	}
 	
		
	
	protected boolean isExtractGuardianListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.GUARDIAN_LIST);
 	}
 	protected boolean isAnalyzeGuardianListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeGuardianListEnabled();
 	}
	
	protected boolean isSaveGuardianListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.GUARDIAN_LIST);
		
 	}
 	
		
	
	protected boolean isExtractClassQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.CLASS_QUESTION_LIST);
 	}
 	protected boolean isAnalyzeClassQuestionListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeClassQuestionListEnabled();
 	}
	
	protected boolean isSaveClassQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.CLASS_QUESTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractClassDailyHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeClassDailyHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeClassDailyHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveClassDailyHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.CLASS_DAILY_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.STUDENT_LIST);
 	}
 	protected boolean isAnalyzeStudentListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeStudentListEnabled();
 	}
	
	protected boolean isSaveStudentListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.STUDENT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.STUDENT_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeStudentHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeStudentHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveStudentHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.STUDENT_HEALTH_SURVEY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentDailyAnswerListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.STUDENT_DAILY_ANSWER_LIST);
 	}
 	protected boolean isAnalyzeStudentDailyAnswerListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeStudentDailyAnswerListEnabled();
 	}
	
	protected boolean isSaveStudentDailyAnswerListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.STUDENT_DAILY_ANSWER_LIST);
		
 	}
 	
		

	

	protected ChangeRequestMapper getChangeRequestMapper(){
		return new ChangeRequestMapper();
	}

	
	
	protected ChangeRequest extractChangeRequest(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ChangeRequest changeRequest = loadSingleObject(accessKey, getChangeRequestMapper());
			return changeRequest;
		}catch(EmptyResultDataAccessException e){
			throw new ChangeRequestNotFoundException("ChangeRequest("+accessKey+") is not found!");
		}

	}

	
	

	protected ChangeRequest loadInternalChangeRequest(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ChangeRequest changeRequest = extractChangeRequest(accessKey, loadOptions);
 	
 		if(isExtractRequestTypeEnabled(loadOptions)){
	 		extractRequestType(changeRequest, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(changeRequest, loadOptions);
 		}
 
		
		if(isExtractSchoolClassListEnabled(loadOptions)){
	 		extractSchoolClassList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeSchoolClassListEnabled(loadOptions)){
	 		analyzeSchoolClassList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractTeacherListEnabled(loadOptions)){
	 		extractTeacherList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeTeacherListEnabled(loadOptions)){
	 		analyzeTeacherList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractGuardianListEnabled(loadOptions)){
	 		extractGuardianList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeGuardianListEnabled(loadOptions)){
	 		analyzeGuardianList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractClassQuestionListEnabled(loadOptions)){
	 		extractClassQuestionList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeClassQuestionListEnabled(loadOptions)){
	 		analyzeClassQuestionList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractClassDailyHealthSurveyListEnabled(loadOptions)){
	 		extractClassDailyHealthSurveyList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeClassDailyHealthSurveyListEnabled(loadOptions)){
	 		analyzeClassDailyHealthSurveyList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractStudentListEnabled(loadOptions)){
	 		extractStudentList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeStudentListEnabled(loadOptions)){
	 		analyzeStudentList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractStudentHealthSurveyListEnabled(loadOptions)){
	 		extractStudentHealthSurveyList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeStudentHealthSurveyListEnabled(loadOptions)){
	 		analyzeStudentHealthSurveyList(changeRequest, loadOptions);
 		}
 		
		
		if(isExtractStudentDailyAnswerListEnabled(loadOptions)){
	 		extractStudentDailyAnswerList(changeRequest, loadOptions);
 		}	
 		if(isAnalyzeStudentDailyAnswerListEnabled(loadOptions)){
	 		analyzeStudentDailyAnswerList(changeRequest, loadOptions);
 		}
 		
		
		return changeRequest;
		
	}

	 

 	protected ChangeRequest extractRequestType(ChangeRequest changeRequest, Map<String,Object> options) throws Exception{

		if(changeRequest.getRequestType() == null){
			return changeRequest;
		}
		String requestTypeId = changeRequest.getRequestType().getId();
		if( requestTypeId == null){
			return changeRequest;
		}
		ChangeRequestType requestType = getChangeRequestTypeDAO().load(requestTypeId,options);
		if(requestType != null){
			changeRequest.setRequestType(requestType);
		}
		
 		
 		return changeRequest;
 	}
 		
  

 	protected ChangeRequest extractPlatform(ChangeRequest changeRequest, Map<String,Object> options) throws Exception{

		if(changeRequest.getPlatform() == null){
			return changeRequest;
		}
		String platformId = changeRequest.getPlatform().getId();
		if( platformId == null){
			return changeRequest;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			changeRequest.setPlatform(platform);
		}
		
 		
 		return changeRequest;
 	}
 		
 
		
	protected void enhanceSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractSchoolClassList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<SchoolClass> schoolClassList = getSchoolClassDAO().findSchoolClassByCq(changeRequest.getId(),options);
		if(schoolClassList != null){
			enhanceSchoolClassList(schoolClassList,options);
			changeRequest.setSchoolClassList(schoolClassList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeSchoolClassList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();
		if(schoolClassList != null){
			getSchoolClassDAO().analyzeSchoolClassByCq(schoolClassList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractTeacherList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Teacher> teacherList = getTeacherDAO().findTeacherByCq(changeRequest.getId(),options);
		if(teacherList != null){
			enhanceTeacherList(teacherList,options);
			changeRequest.setTeacherList(teacherList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeTeacherList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Teacher> teacherList = changeRequest.getTeacherList();
		if(teacherList != null){
			getTeacherDAO().analyzeTeacherByCq(teacherList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractGuardianList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Guardian> guardianList = getGuardianDAO().findGuardianByCq(changeRequest.getId(),options);
		if(guardianList != null){
			enhanceGuardianList(guardianList,options);
			changeRequest.setGuardianList(guardianList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeGuardianList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();
		if(guardianList != null){
			getGuardianDAO().analyzeGuardianByCq(guardianList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractClassQuestionList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<ClassQuestion> classQuestionList = getClassQuestionDAO().findClassQuestionByCq(changeRequest.getId(),options);
		if(classQuestionList != null){
			enhanceClassQuestionList(classQuestionList,options);
			changeRequest.setClassQuestionList(classQuestionList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeClassQuestionList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();
		if(classQuestionList != null){
			getClassQuestionDAO().analyzeClassQuestionByCq(classQuestionList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractClassDailyHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyByCq(changeRequest.getId(),options);
		if(classDailyHealthSurveyList != null){
			enhanceClassDailyHealthSurveyList(classDailyHealthSurveyList,options);
			changeRequest.setClassDailyHealthSurveyList(classDailyHealthSurveyList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeClassDailyHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList != null){
			getClassDailyHealthSurveyDAO().analyzeClassDailyHealthSurveyByCq(classDailyHealthSurveyList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractStudentList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Student> studentList = getStudentDAO().findStudentByCq(changeRequest.getId(),options);
		if(studentList != null){
			enhanceStudentList(studentList,options);
			changeRequest.setStudentList(studentList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeStudentList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<Student> studentList = changeRequest.getStudentList();
		if(studentList != null){
			getStudentDAO().analyzeStudentByCq(studentList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractStudentHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = getStudentHealthSurveyDAO().findStudentHealthSurveyByCq(changeRequest.getId(),options);
		if(studentHealthSurveyList != null){
			enhanceStudentHealthSurveyList(studentHealthSurveyList,options);
			changeRequest.setStudentHealthSurveyList(studentHealthSurveyList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeStudentHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();
		if(studentHealthSurveyList != null){
			getStudentHealthSurveyDAO().analyzeStudentHealthSurveyByCq(studentHealthSurveyList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
	protected void enhanceStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractStudentDailyAnswerList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = getStudentDailyAnswerDAO().findStudentDailyAnswerByCq(changeRequest.getId(),options);
		if(studentDailyAnswerList != null){
			enhanceStudentDailyAnswerList(studentDailyAnswerList,options);
			changeRequest.setStudentDailyAnswerList(studentDailyAnswerList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeStudentDailyAnswerList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();
		if(studentDailyAnswerList != null){
			getStudentDailyAnswerDAO().analyzeStudentDailyAnswerByCq(studentDailyAnswerList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
		
  	
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId,Map<String,Object> options){
 	
  		SmartList<ChangeRequest> resultList = queryWith(ChangeRequestTable.COLUMN_REQUEST_TYPE, changeRequestTypeId, options, getChangeRequestMapper());
		// analyzeChangeRequestByRequestType(resultList, changeRequestTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequest> resultList =  queryWithRange(ChangeRequestTable.COLUMN_REQUEST_TYPE, changeRequestTypeId, options, getChangeRequestMapper(), start, count);
 		//analyzeChangeRequestByRequestType(resultList, changeRequestTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestByRequestType(SmartList<ChangeRequest> resultList, String changeRequestTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequest.REQUEST_TYPE_PROPERTY, changeRequestTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//ChangeRequest.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("变更请求");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(ChangeRequest.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ChangeRequest.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestByRequestType(String changeRequestTypeId,Map<String,Object> options){

 		return countWith(ChangeRequestTable.COLUMN_REQUEST_TYPE, changeRequestTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestByRequestTypeIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestTable.COLUMN_REQUEST_TYPE, ids, options);
	}
 	
  	
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<ChangeRequest> resultList = queryWith(ChangeRequestTable.COLUMN_PLATFORM, platformId, options, getChangeRequestMapper());
		// analyzeChangeRequestByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequest> resultList =  queryWithRange(ChangeRequestTable.COLUMN_PLATFORM, platformId, options, getChangeRequestMapper(), start, count);
 		//analyzeChangeRequestByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestByPlatform(SmartList<ChangeRequest> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequest.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//ChangeRequest.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("变更请求");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(ChangeRequest.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ChangeRequest.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ChangeRequestTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected ChangeRequest saveChangeRequest(ChangeRequest  changeRequest){
		
		if(!changeRequest.isChanged()){
			return changeRequest;
		}
		
		
		String SQL=this.getSaveChangeRequestSQL(changeRequest);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveChangeRequestParameters(changeRequest);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		changeRequest.incVersion();
		return changeRequest;
	
	}
	public SmartList<ChangeRequest> saveChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitChangeRequestList(changeRequestList);
		
		batchChangeRequestCreate((List<ChangeRequest>)lists[CREATE_LIST_INDEX]);
		
		batchChangeRequestUpdate((List<ChangeRequest>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ChangeRequest changeRequest:changeRequestList){
			if(changeRequest.isChanged()){
				changeRequest.incVersion();
			}
			
		
		}
		
		
		return changeRequestList;
	}

	public SmartList<ChangeRequest> removeChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		
		
		super.removeList(changeRequestList, options);
		
		return changeRequestList;
		
		
	}
	
	protected List<Object[]> prepareChangeRequestBatchCreateArgs(List<ChangeRequest> changeRequestList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequest changeRequest:changeRequestList ){
			Object [] parameters = prepareChangeRequestCreateParameters(changeRequest);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareChangeRequestBatchUpdateArgs(List<ChangeRequest> changeRequestList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequest changeRequest:changeRequestList ){
			if(!changeRequest.isChanged()){
				continue;
			}
			Object [] parameters = prepareChangeRequestUpdateParameters(changeRequest);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchChangeRequestCreate(List<ChangeRequest> changeRequestList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareChangeRequestBatchCreateArgs(changeRequestList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchChangeRequestUpdate(List<ChangeRequest> changeRequestList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareChangeRequestBatchUpdateArgs(changeRequestList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitChangeRequestList(List<ChangeRequest> changeRequestList){
		
		List<ChangeRequest> changeRequestCreateList=new ArrayList<ChangeRequest>();
		List<ChangeRequest> changeRequestUpdateList=new ArrayList<ChangeRequest>();
		
		for(ChangeRequest changeRequest: changeRequestList){
			if(isUpdateRequest(changeRequest)){
				changeRequestUpdateList.add( changeRequest);
				continue;
			}
			changeRequestCreateList.add(changeRequest);
		}
		
		return new Object[]{changeRequestCreateList,changeRequestUpdateList};
	}
	
	protected boolean isUpdateRequest(ChangeRequest changeRequest){
 		return changeRequest.getVersion() > 0;
 	}
 	protected String getSaveChangeRequestSQL(ChangeRequest changeRequest){
 		if(isUpdateRequest(changeRequest)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveChangeRequestParameters(ChangeRequest changeRequest){
 		if(isUpdateRequest(changeRequest) ){
 			return prepareChangeRequestUpdateParameters(changeRequest);
 		}
 		return prepareChangeRequestCreateParameters(changeRequest);
 	}
 	protected Object[] prepareChangeRequestUpdateParameters(ChangeRequest changeRequest){
 		Object[] parameters = new Object[8];
 
 		parameters[0] = changeRequest.getName();
 		parameters[1] = changeRequest.getCreateTime();
 		parameters[2] = changeRequest.getRemoteIp(); 	
 		if(changeRequest.getRequestType() != null){
 			parameters[3] = changeRequest.getRequestType().getId();
 		}
  	
 		if(changeRequest.getPlatform() != null){
 			parameters[4] = changeRequest.getPlatform().getId();
 		}
 		
 		parameters[5] = changeRequest.nextVersion();
 		parameters[6] = changeRequest.getId();
 		parameters[7] = changeRequest.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareChangeRequestCreateParameters(ChangeRequest changeRequest){
		Object[] parameters = new Object[6];
		String newChangeRequestId=getNextId();
		changeRequest.setId(newChangeRequestId);
		parameters[0] =  changeRequest.getId();
 
 		parameters[1] = changeRequest.getName();
 		parameters[2] = changeRequest.getCreateTime();
 		parameters[3] = changeRequest.getRemoteIp(); 	
 		if(changeRequest.getRequestType() != null){
 			parameters[4] = changeRequest.getRequestType().getId();
 		
 		}
 		 	
 		if(changeRequest.getPlatform() != null){
 			parameters[5] = changeRequest.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ChangeRequest saveInternalChangeRequest(ChangeRequest changeRequest, Map<String,Object> options){
		
		saveChangeRequest(changeRequest);
 	
 		if(isSaveRequestTypeEnabled(options)){
	 		saveRequestType(changeRequest, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(changeRequest, options);
 		}
 
		
		if(isSaveSchoolClassListEnabled(options)){
	 		saveSchoolClassList(changeRequest, options);
	 		//removeSchoolClassList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveTeacherListEnabled(options)){
	 		saveTeacherList(changeRequest, options);
	 		//removeTeacherList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveGuardianListEnabled(options)){
	 		saveGuardianList(changeRequest, options);
	 		//removeGuardianList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveClassQuestionListEnabled(options)){
	 		saveClassQuestionList(changeRequest, options);
	 		//removeClassQuestionList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveClassDailyHealthSurveyListEnabled(options)){
	 		saveClassDailyHealthSurveyList(changeRequest, options);
	 		//removeClassDailyHealthSurveyList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentListEnabled(options)){
	 		saveStudentList(changeRequest, options);
	 		//removeStudentList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentHealthSurveyListEnabled(options)){
	 		saveStudentHealthSurveyList(changeRequest, options);
	 		//removeStudentHealthSurveyList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentDailyAnswerListEnabled(options)){
	 		saveStudentDailyAnswerList(changeRequest, options);
	 		//removeStudentDailyAnswerList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		return changeRequest;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ChangeRequest saveRequestType(ChangeRequest changeRequest, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequest.getRequestType() == null){
 			return changeRequest;//do nothing when it is null
 		}
 		
 		getChangeRequestTypeDAO().save(changeRequest.getRequestType(),options);
 		return changeRequest;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ChangeRequest savePlatform(ChangeRequest changeRequest, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequest.getPlatform() == null){
 			return changeRequest;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(changeRequest.getPlatform(),options);
 		return changeRequest;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ChangeRequest planToRemoveSchoolClassList(ChangeRequest changeRequest, String schoolClassIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CQ_PROPERTY, changeRequest.getId());
		key.put(SchoolClass.ID_PROPERTY, schoolClassIds);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return changeRequest;
		}
		if(externalSchoolClassList.isEmpty()){
			return changeRequest;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){

			schoolClassItem.clearFromAll();
		}
		
		
		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with class_teacher in SchoolClass
	public ChangeRequest planToRemoveSchoolClassListWithClassTeacher(ChangeRequest changeRequest, String classTeacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CQ_PROPERTY, changeRequest.getId());
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, classTeacherId);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return changeRequest;
		}
		if(externalSchoolClassList.isEmpty()){
			return changeRequest;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){
			schoolClassItem.clearClassTeacher();
			schoolClassItem.clearCq();
			
		}
		
		
		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return changeRequest;
	}
	
	public int countSchoolClassListWithClassTeacher(String changeRequestId, String classTeacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CQ_PROPERTY, changeRequestId);
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, classTeacherId);
		
		int count = getSchoolClassDAO().countSchoolClassWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with platform in SchoolClass
	public ChangeRequest planToRemoveSchoolClassListWithPlatform(ChangeRequest changeRequest, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CQ_PROPERTY, changeRequest.getId());
		key.put(SchoolClass.PLATFORM_PROPERTY, platformId);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return changeRequest;
		}
		if(externalSchoolClassList.isEmpty()){
			return changeRequest;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){
			schoolClassItem.clearPlatform();
			schoolClassItem.clearCq();
			
		}
		
		
		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return changeRequest;
	}
	
	public int countSchoolClassListWithPlatform(String changeRequestId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CQ_PROPERTY, changeRequestId);
		key.put(SchoolClass.PLATFORM_PROPERTY, platformId);
		
		int count = getSchoolClassDAO().countSchoolClassWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveTeacherList(ChangeRequest changeRequest, String teacherIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.CQ_PROPERTY, changeRequest.getId());
		key.put(Teacher.ID_PROPERTY, teacherIds);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return changeRequest;
		}
		if(externalTeacherList.isEmpty()){
			return changeRequest;
		}
		
		for(Teacher teacherItem: externalTeacherList){

			teacherItem.clearFromAll();
		}
		
		
		SmartList<Teacher> teacherList = changeRequest.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with platform in Teacher
	public ChangeRequest planToRemoveTeacherListWithPlatform(ChangeRequest changeRequest, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.CQ_PROPERTY, changeRequest.getId());
		key.put(Teacher.PLATFORM_PROPERTY, platformId);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return changeRequest;
		}
		if(externalTeacherList.isEmpty()){
			return changeRequest;
		}
		
		for(Teacher teacherItem: externalTeacherList){
			teacherItem.clearPlatform();
			teacherItem.clearCq();
			
		}
		
		
		SmartList<Teacher> teacherList = changeRequest.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return changeRequest;
	}
	
	public int countTeacherListWithPlatform(String changeRequestId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.CQ_PROPERTY, changeRequestId);
		key.put(Teacher.PLATFORM_PROPERTY, platformId);
		
		int count = getTeacherDAO().countTeacherWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveGuardianList(ChangeRequest changeRequest, String guardianIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequest.getId());
		key.put(Guardian.ID_PROPERTY, guardianIds);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return changeRequest;
		}
		if(externalGuardianList.isEmpty()){
			return changeRequest;
		}
		
		for(Guardian guardianItem: externalGuardianList){

			guardianItem.clearFromAll();
		}
		
		
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with address in Guardian
	public ChangeRequest planToRemoveGuardianListWithAddress(ChangeRequest changeRequest, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequest.getId());
		key.put(Guardian.ADDRESS_PROPERTY, addressId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return changeRequest;
		}
		if(externalGuardianList.isEmpty()){
			return changeRequest;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearAddress();
			guardianItem.clearCq();
			
		}
		
		
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return changeRequest;
	}
	
	public int countGuardianListWithAddress(String changeRequestId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequestId);
		key.put(Guardian.ADDRESS_PROPERTY, addressId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with wechat_user in Guardian
	public ChangeRequest planToRemoveGuardianListWithWechatUser(ChangeRequest changeRequest, String wechatUserId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequest.getId());
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return changeRequest;
		}
		if(externalGuardianList.isEmpty()){
			return changeRequest;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearWechatUser();
			guardianItem.clearCq();
			
		}
		
		
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return changeRequest;
	}
	
	public int countGuardianListWithWechatUser(String changeRequestId, String wechatUserId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequestId);
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with platform in Guardian
	public ChangeRequest planToRemoveGuardianListWithPlatform(ChangeRequest changeRequest, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequest.getId());
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return changeRequest;
		}
		if(externalGuardianList.isEmpty()){
			return changeRequest;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearPlatform();
			guardianItem.clearCq();
			
		}
		
		
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return changeRequest;
	}
	
	public int countGuardianListWithPlatform(String changeRequestId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, changeRequestId);
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveClassQuestionList(ChangeRequest changeRequest, String classQuestionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassQuestion.ID_PROPERTY, classQuestionIds);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return changeRequest;
		}
		if(externalClassQuestionList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){

			classQuestionItem.clearFromAll();
		}
		
		
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with question_type in ClassQuestion
	public ChangeRequest planToRemoveClassQuestionListWithQuestionType(ChangeRequest changeRequest, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return changeRequest;
		}
		if(externalClassQuestionList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearQuestionType();
			classQuestionItem.clearCq();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return changeRequest;
	}
	
	public int countClassQuestionListWithQuestionType(String changeRequestId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequestId);
		key.put(ClassQuestion.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with question_source in ClassQuestion
	public ChangeRequest planToRemoveClassQuestionListWithQuestionSource(ChangeRequest changeRequest, String questionSourceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return changeRequest;
		}
		if(externalClassQuestionList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearQuestionSource();
			classQuestionItem.clearCq();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return changeRequest;
	}
	
	public int countClassQuestionListWithQuestionSource(String changeRequestId, String questionSourceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequestId);
		key.put(ClassQuestion.QUESTION_SOURCE_PROPERTY, questionSourceId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with creator in ClassQuestion
	public ChangeRequest planToRemoveClassQuestionListWithCreator(ChangeRequest changeRequest, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassQuestion.CREATOR_PROPERTY, creatorId);
		
		SmartList<ClassQuestion> externalClassQuestionList = getClassQuestionDAO().
				findClassQuestionWithKey(key, options);
		if(externalClassQuestionList == null){
			return changeRequest;
		}
		if(externalClassQuestionList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassQuestion classQuestionItem: externalClassQuestionList){
			classQuestionItem.clearCreator();
			classQuestionItem.clearCq();
			
		}
		
		
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();		
		classQuestionList.addAllToRemoveList(externalClassQuestionList);
		return changeRequest;
	}
	
	public int countClassQuestionListWithCreator(String changeRequestId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, changeRequestId);
		key.put(ClassQuestion.CREATOR_PROPERTY, creatorId);
		
		int count = getClassQuestionDAO().countClassQuestionWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveClassDailyHealthSurveyList(ChangeRequest changeRequest, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassDailyHealthSurvey.ID_PROPERTY, classDailyHealthSurveyIds);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return changeRequest;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){

			classDailyHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with school_class in ClassDailyHealthSurvey
	public ChangeRequest planToRemoveClassDailyHealthSurveyListWithSchoolClass(ChangeRequest changeRequest, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return changeRequest;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearSchoolClass();
			classDailyHealthSurveyItem.clearCq();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return changeRequest;
	}
	
	public int countClassDailyHealthSurveyListWithSchoolClass(String changeRequestId, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, changeRequestId);
		key.put(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with creator in ClassDailyHealthSurvey
	public ChangeRequest planToRemoveClassDailyHealthSurveyListWithCreator(ChangeRequest changeRequest, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, creatorId);
		
		SmartList<ClassDailyHealthSurvey> externalClassDailyHealthSurveyList = getClassDailyHealthSurveyDAO().
				findClassDailyHealthSurveyWithKey(key, options);
		if(externalClassDailyHealthSurveyList == null){
			return changeRequest;
		}
		if(externalClassDailyHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(ClassDailyHealthSurvey classDailyHealthSurveyItem: externalClassDailyHealthSurveyList){
			classDailyHealthSurveyItem.clearCreator();
			classDailyHealthSurveyItem.clearCq();
			
		}
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();		
		classDailyHealthSurveyList.addAllToRemoveList(externalClassDailyHealthSurveyList);
		return changeRequest;
	}
	
	public int countClassDailyHealthSurveyListWithCreator(String changeRequestId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, changeRequestId);
		key.put(ClassDailyHealthSurvey.CREATOR_PROPERTY, creatorId);
		
		int count = getClassDailyHealthSurveyDAO().countClassDailyHealthSurveyWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveStudentList(ChangeRequest changeRequest, String studentIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequest.getId());
		key.put(Student.ID_PROPERTY, studentIds);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return changeRequest;
		}
		if(externalStudentList.isEmpty()){
			return changeRequest;
		}
		
		for(Student studentItem: externalStudentList){

			studentItem.clearFromAll();
		}
		
		
		SmartList<Student> studentList = changeRequest.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with guardian in Student
	public ChangeRequest planToRemoveStudentListWithGuardian(ChangeRequest changeRequest, String guardianId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequest.getId());
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return changeRequest;
		}
		if(externalStudentList.isEmpty()){
			return changeRequest;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearGuardian();
			studentItem.clearCq();
			
		}
		
		
		SmartList<Student> studentList = changeRequest.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return changeRequest;
	}
	
	public int countStudentListWithGuardian(String changeRequestId, String guardianId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequestId);
		key.put(Student.GUARDIAN_PROPERTY, guardianId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with school_class in Student
	public ChangeRequest planToRemoveStudentListWithSchoolClass(ChangeRequest changeRequest, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequest.getId());
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return changeRequest;
		}
		if(externalStudentList.isEmpty()){
			return changeRequest;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearSchoolClass();
			studentItem.clearCq();
			
		}
		
		
		SmartList<Student> studentList = changeRequest.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return changeRequest;
	}
	
	public int countStudentListWithSchoolClass(String changeRequestId, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequestId);
		key.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with student_id in Student
	public ChangeRequest planToRemoveStudentListWithStudentId(ChangeRequest changeRequest, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequest.getId());
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return changeRequest;
		}
		if(externalStudentList.isEmpty()){
			return changeRequest;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearStudentId();
			studentItem.clearCq();
			
		}
		
		
		SmartList<Student> studentList = changeRequest.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return changeRequest;
	}
	
	public int countStudentListWithStudentId(String changeRequestId, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, changeRequestId);
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveStudentHealthSurveyList(ChangeRequest changeRequest, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentHealthSurvey.ID_PROPERTY, studentHealthSurveyIds);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return changeRequest;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){

			studentHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with student in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithStudent(ChangeRequest changeRequest, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return changeRequest;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearStudent();
			studentHealthSurveyItem.clearCq();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return changeRequest;
	}
	
	public int countStudentHealthSurveyListWithStudent(String changeRequestId, String studentId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequestId);
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with survey_status in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithSurveyStatus(ChangeRequest changeRequest, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return changeRequest;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSurveyStatus();
			studentHealthSurveyItem.clearCq();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return changeRequest;
	}
	
	public int countStudentHealthSurveyListWithSurveyStatus(String changeRequestId, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequestId);
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with school_class in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithSchoolClass(ChangeRequest changeRequest, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return changeRequest;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSchoolClass();
			studentHealthSurveyItem.clearCq();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return changeRequest;
	}
	
	public int countStudentHealthSurveyListWithSchoolClass(String changeRequestId, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequestId);
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with class_daily_health_survey in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(ChangeRequest changeRequest, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return changeRequest;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			studentHealthSurveyItem.clearCq();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return changeRequest;
	}
	
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String changeRequestId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, changeRequestId);
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	public ChangeRequest planToRemoveStudentDailyAnswerList(ChangeRequest changeRequest, String studentDailyAnswerIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentDailyAnswer.ID_PROPERTY, studentDailyAnswerIds);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return changeRequest;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){

			studentDailyAnswerItem.clearFromAll();
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with student_health_survey in StudentDailyAnswer
	public ChangeRequest planToRemoveStudentDailyAnswerListWithStudentHealthSurvey(ChangeRequest changeRequest, String studentHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return changeRequest;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){
			studentDailyAnswerItem.clearStudentHealthSurvey();
			studentDailyAnswerItem.clearCq();
			
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return changeRequest;
	}
	
	public int countStudentDailyAnswerListWithStudentHealthSurvey(String changeRequestId, String studentHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.CQ_PROPERTY, changeRequestId);
		key.put(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, studentHealthSurveyId);
		
		int count = getStudentDailyAnswerDAO().countStudentDailyAnswerWithKey(key, options);
		return count;
	}
	
	//disconnect ChangeRequest with question in StudentDailyAnswer
	public ChangeRequest planToRemoveStudentDailyAnswerListWithQuestion(ChangeRequest changeRequest, String questionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.CQ_PROPERTY, changeRequest.getId());
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, questionId);
		
		SmartList<StudentDailyAnswer> externalStudentDailyAnswerList = getStudentDailyAnswerDAO().
				findStudentDailyAnswerWithKey(key, options);
		if(externalStudentDailyAnswerList == null){
			return changeRequest;
		}
		if(externalStudentDailyAnswerList.isEmpty()){
			return changeRequest;
		}
		
		for(StudentDailyAnswer studentDailyAnswerItem: externalStudentDailyAnswerList){
			studentDailyAnswerItem.clearQuestion();
			studentDailyAnswerItem.clearCq();
			
		}
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();		
		studentDailyAnswerList.addAllToRemoveList(externalStudentDailyAnswerList);
		return changeRequest;
	}
	
	public int countStudentDailyAnswerListWithQuestion(String changeRequestId, String questionId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.CQ_PROPERTY, changeRequestId);
		key.put(StudentDailyAnswer.QUESTION_PROPERTY, questionId);
		
		int count = getStudentDailyAnswerDAO().countStudentDailyAnswerWithKey(key, options);
		return count;
	}
	

		
	protected ChangeRequest saveSchoolClassList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();
		if(schoolClassList == null){
			//null list means nothing
			return changeRequest;
		}
		SmartList<SchoolClass> mergedUpdateSchoolClassList = new SmartList<SchoolClass>();
		
		
		mergedUpdateSchoolClassList.addAll(schoolClassList); 
		if(schoolClassList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateSchoolClassList.addAll(schoolClassList.getToRemoveList());
			schoolClassList.removeAll(schoolClassList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getSchoolClassDAO().saveSchoolClassList(mergedUpdateSchoolClassList,options);
		
		if(schoolClassList.getToRemoveList() != null){
			schoolClassList.removeAll(schoolClassList.getToRemoveList());
		}
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeSchoolClassList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();
		if(schoolClassList == null){
			return changeRequest;
		}	
	
		SmartList<SchoolClass> toRemoveSchoolClassList = schoolClassList.getToRemoveList();
		
		if(toRemoveSchoolClassList == null){
			return changeRequest;
		}
		if(toRemoveSchoolClassList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getSchoolClassDAO().removeSchoolClassList(toRemoveSchoolClassList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveTeacherList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<Teacher> teacherList = changeRequest.getTeacherList();
		if(teacherList == null){
			//null list means nothing
			return changeRequest;
		}
		SmartList<Teacher> mergedUpdateTeacherList = new SmartList<Teacher>();
		
		
		mergedUpdateTeacherList.addAll(teacherList); 
		if(teacherList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTeacherList.addAll(teacherList.getToRemoveList());
			teacherList.removeAll(teacherList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTeacherDAO().saveTeacherList(mergedUpdateTeacherList,options);
		
		if(teacherList.getToRemoveList() != null){
			teacherList.removeAll(teacherList.getToRemoveList());
		}
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeTeacherList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<Teacher> teacherList = changeRequest.getTeacherList();
		if(teacherList == null){
			return changeRequest;
		}	
	
		SmartList<Teacher> toRemoveTeacherList = teacherList.getToRemoveList();
		
		if(toRemoveTeacherList == null){
			return changeRequest;
		}
		if(toRemoveTeacherList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTeacherDAO().removeTeacherList(toRemoveTeacherList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveGuardianList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();
		if(guardianList == null){
			//null list means nothing
			return changeRequest;
		}
		SmartList<Guardian> mergedUpdateGuardianList = new SmartList<Guardian>();
		
		
		mergedUpdateGuardianList.addAll(guardianList); 
		if(guardianList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateGuardianList.addAll(guardianList.getToRemoveList());
			guardianList.removeAll(guardianList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getGuardianDAO().saveGuardianList(mergedUpdateGuardianList,options);
		
		if(guardianList.getToRemoveList() != null){
			guardianList.removeAll(guardianList.getToRemoveList());
		}
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeGuardianList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<Guardian> guardianList = changeRequest.getGuardianList();
		if(guardianList == null){
			return changeRequest;
		}	
	
		SmartList<Guardian> toRemoveGuardianList = guardianList.getToRemoveList();
		
		if(toRemoveGuardianList == null){
			return changeRequest;
		}
		if(toRemoveGuardianList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getGuardianDAO().removeGuardianList(toRemoveGuardianList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveClassQuestionList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();
		if(classQuestionList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeClassQuestionList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();
		if(classQuestionList == null){
			return changeRequest;
		}	
	
		SmartList<ClassQuestion> toRemoveClassQuestionList = classQuestionList.getToRemoveList();
		
		if(toRemoveClassQuestionList == null){
			return changeRequest;
		}
		if(toRemoveClassQuestionList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassQuestionDAO().removeClassQuestionList(toRemoveClassQuestionList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveClassDailyHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeClassDailyHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();
		if(classDailyHealthSurveyList == null){
			return changeRequest;
		}	
	
		SmartList<ClassDailyHealthSurvey> toRemoveClassDailyHealthSurveyList = classDailyHealthSurveyList.getToRemoveList();
		
		if(toRemoveClassDailyHealthSurveyList == null){
			return changeRequest;
		}
		if(toRemoveClassDailyHealthSurveyList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getClassDailyHealthSurveyDAO().removeClassDailyHealthSurveyList(toRemoveClassDailyHealthSurveyList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveStudentList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<Student> studentList = changeRequest.getStudentList();
		if(studentList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeStudentList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<Student> studentList = changeRequest.getStudentList();
		if(studentList == null){
			return changeRequest;
		}	
	
		SmartList<Student> toRemoveStudentList = studentList.getToRemoveList();
		
		if(toRemoveStudentList == null){
			return changeRequest;
		}
		if(toRemoveStudentList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDAO().removeStudentList(toRemoveStudentList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveStudentHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeStudentHealthSurveyList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			return changeRequest;
		}	
	
		SmartList<StudentHealthSurvey> toRemoveStudentHealthSurveyList = studentHealthSurveyList.getToRemoveList();
		
		if(toRemoveStudentHealthSurveyList == null){
			return changeRequest;
		}
		if(toRemoveStudentHealthSurveyList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentHealthSurveyDAO().removeStudentHealthSurveyList(toRemoveStudentHealthSurveyList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		
	protected ChangeRequest saveStudentDailyAnswerList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();
		if(studentDailyAnswerList == null){
			//null list means nothing
			return changeRequest;
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
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeStudentDailyAnswerList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();
		if(studentDailyAnswerList == null){
			return changeRequest;
		}	
	
		SmartList<StudentDailyAnswer> toRemoveStudentDailyAnswerList = studentDailyAnswerList.getToRemoveList();
		
		if(toRemoveStudentDailyAnswerList == null){
			return changeRequest;
		}
		if(toRemoveStudentDailyAnswerList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDailyAnswerDAO().removeStudentDailyAnswerList(toRemoveStudentDailyAnswerList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		

	public ChangeRequest present(ChangeRequest changeRequest,Map<String, Object> options){
	
		presentSchoolClassList(changeRequest,options);
		presentTeacherList(changeRequest,options);
		presentGuardianList(changeRequest,options);
		presentClassQuestionList(changeRequest,options);
		presentClassDailyHealthSurveyList(changeRequest,options);
		presentStudentList(changeRequest,options);
		presentStudentHealthSurveyList(changeRequest,options);
		presentStudentDailyAnswerList(changeRequest,options);

		return changeRequest;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentSchoolClassList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<SchoolClass> schoolClassList = changeRequest.getSchoolClassList();		
				SmartList<SchoolClass> newList= presentSubList(changeRequest.getId(),
				schoolClassList,
				options,
				getSchoolClassDAO()::countSchoolClassByCq,
				getSchoolClassDAO()::findSchoolClassByCq
				);

		
		changeRequest.setSchoolClassList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentTeacherList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<Teacher> teacherList = changeRequest.getTeacherList();		
				SmartList<Teacher> newList= presentSubList(changeRequest.getId(),
				teacherList,
				options,
				getTeacherDAO()::countTeacherByCq,
				getTeacherDAO()::findTeacherByCq
				);

		
		changeRequest.setTeacherList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentGuardianList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<Guardian> guardianList = changeRequest.getGuardianList();		
				SmartList<Guardian> newList= presentSubList(changeRequest.getId(),
				guardianList,
				options,
				getGuardianDAO()::countGuardianByCq,
				getGuardianDAO()::findGuardianByCq
				);

		
		changeRequest.setGuardianList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentClassQuestionList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<ClassQuestion> classQuestionList = changeRequest.getClassQuestionList();		
				SmartList<ClassQuestion> newList= presentSubList(changeRequest.getId(),
				classQuestionList,
				options,
				getClassQuestionDAO()::countClassQuestionByCq,
				getClassQuestionDAO()::findClassQuestionByCq
				);

		
		changeRequest.setClassQuestionList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentClassDailyHealthSurveyList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList = changeRequest.getClassDailyHealthSurveyList();		
				SmartList<ClassDailyHealthSurvey> newList= presentSubList(changeRequest.getId(),
				classDailyHealthSurveyList,
				options,
				getClassDailyHealthSurveyDAO()::countClassDailyHealthSurveyByCq,
				getClassDailyHealthSurveyDAO()::findClassDailyHealthSurveyByCq
				);

		
		changeRequest.setClassDailyHealthSurveyList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentStudentList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<Student> studentList = changeRequest.getStudentList();		
				SmartList<Student> newList= presentSubList(changeRequest.getId(),
				studentList,
				options,
				getStudentDAO()::countStudentByCq,
				getStudentDAO()::findStudentByCq
				);

		
		changeRequest.setStudentList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentStudentHealthSurveyList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<StudentHealthSurvey> studentHealthSurveyList = changeRequest.getStudentHealthSurveyList();		
				SmartList<StudentHealthSurvey> newList= presentSubList(changeRequest.getId(),
				studentHealthSurveyList,
				options,
				getStudentHealthSurveyDAO()::countStudentHealthSurveyByCq,
				getStudentHealthSurveyDAO()::findStudentHealthSurveyByCq
				);

		
		changeRequest.setStudentHealthSurveyList(newList);
		

		return changeRequest;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentStudentDailyAnswerList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<StudentDailyAnswer> studentDailyAnswerList = changeRequest.getStudentDailyAnswerList();		
				SmartList<StudentDailyAnswer> newList= presentSubList(changeRequest.getId(),
				studentDailyAnswerList,
				options,
				getStudentDailyAnswerDAO()::countStudentDailyAnswerByCq,
				getStudentDailyAnswerDAO()::findStudentDailyAnswerByCq
				);

		
		changeRequest.setStudentDailyAnswerList(newList);
		

		return changeRequest;
	}			
		

	
    public SmartList<ChangeRequest> requestCandidateChangeRequestForSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		
    public SmartList<ChangeRequest> requestCandidateChangeRequestForStudentDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		

	protected String getTableName(){
		return ChangeRequestTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ChangeRequest> changeRequestList) {		
		this.enhanceListInternal(changeRequestList, this.getChangeRequestMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:SchoolClass的cq的SchoolClassList
	public SmartList<SchoolClass> loadOurSchoolClassList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<SchoolClass> loadedObjs = userContext.getDAOGroup().getSchoolClassDAO().findSchoolClassWithKey(key, options);
		Map<String, List<SchoolClass>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<SchoolClass> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<SchoolClass> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setSchoolClassList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Teacher的cq的TeacherList
	public SmartList<Teacher> loadOurTeacherList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Teacher> loadedObjs = userContext.getDAOGroup().getTeacherDAO().findTeacherWithKey(key, options);
		Map<String, List<Teacher>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Teacher> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Teacher> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setTeacherList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Guardian的cq的GuardianList
	public SmartList<Guardian> loadOurGuardianList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Guardian> loadedObjs = userContext.getDAOGroup().getGuardianDAO().findGuardianWithKey(key, options);
		Map<String, List<Guardian>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Guardian> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Guardian> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setGuardianList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ClassQuestion的cq的ClassQuestionList
	public SmartList<ClassQuestion> loadOurClassQuestionList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassQuestion.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassQuestion> loadedObjs = userContext.getDAOGroup().getClassQuestionDAO().findClassQuestionWithKey(key, options);
		Map<String, List<ClassQuestion>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的cq的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ClassDailyHealthSurvey.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ClassDailyHealthSurvey> loadedObjs = userContext.getDAOGroup().getClassDailyHealthSurveyDAO().findClassDailyHealthSurveyWithKey(key, options);
		Map<String, List<ClassDailyHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:Student的cq的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Student> loadedObjs = userContext.getDAOGroup().getStudentDAO().findStudentWithKey(key, options);
		Map<String, List<Student>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的cq的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentHealthSurvey> loadedObjs = userContext.getDAOGroup().getStudentHealthSurveyDAO().findStudentHealthSurveyWithKey(key, options);
		Map<String, List<StudentHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:StudentDailyAnswer的cq的StudentDailyAnswerList
	public SmartList<StudentDailyAnswer> loadOurStudentDailyAnswerList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentDailyAnswer.CQ_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentDailyAnswer> loadedObjs = userContext.getDAOGroup().getStudentDailyAnswerDAO().findStudentDailyAnswerWithKey(key, options);
		Map<String, List<StudentDailyAnswer>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCq().getId()));
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
		List<ChangeRequest> changeRequestList = ownerEntity.collectRefsWithType(ChangeRequest.INTERNAL_TYPE);
		this.enhanceList(changeRequestList);
		
	}
	
	@Override
	public SmartList<ChangeRequest> findChangeRequestWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getChangeRequestMapper());

	}
	@Override
	public int countChangeRequestWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countChangeRequestWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ChangeRequest> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getChangeRequestMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


