
package com.doublechaintech.health.healthsurveyreport;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Password;

import com.doublechaintech.health.*;
import com.doublechaintech.health.HealthUserContextImpl;
import com.doublechaintech.health.iamservice.*;
import com.doublechaintech.health.services.IamService;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.terapico.uccaf.BaseUserContext;


import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;

import com.doublechaintech.health.teacher.CandidateTeacher;
import com.doublechaintech.health.classdailyhealthsurvey.CandidateClassDailyHealthSurvey;
import com.doublechaintech.health.student.CandidateStudent;

import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;






public class HealthSurveyReportManagerImpl extends CustomHealthCheckerManager implements HealthSurveyReportManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "HealthSurveyReport";
	@Override
	public HealthSurveyReportDAO daoOf(HealthUserContext userContext) {
		return healthSurveyReportDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws HealthSurveyReportManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new HealthSurveyReportManagerException(message);

	}



 	protected HealthSurveyReport saveHealthSurveyReport(HealthUserContext userContext, HealthSurveyReport healthSurveyReport, String [] tokensExpr) throws Exception{	
 		//return getHealthSurveyReportDAO().save(healthSurveyReport, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveHealthSurveyReport(userContext, healthSurveyReport, tokens);
 	}
 	
 	protected HealthSurveyReport saveHealthSurveyReportDetail(HealthUserContext userContext, HealthSurveyReport healthSurveyReport) throws Exception{	

 		
 		return saveHealthSurveyReport(userContext, healthSurveyReport, allTokens());
 	}
 	
 	public HealthSurveyReport loadHealthSurveyReport(HealthUserContext userContext, String healthSurveyReportId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).throwExceptionIfHasErrors( HealthSurveyReportManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport( userContext, healthSurveyReportId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,healthSurveyReport, tokens);
 	}
 	
 	
 	 public HealthSurveyReport searchHealthSurveyReport(HealthUserContext userContext, String healthSurveyReportId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).throwExceptionIfHasErrors( HealthSurveyReportManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport( userContext, healthSurveyReportId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,healthSurveyReport, tokens);
 	}
 	
 	

 	protected HealthSurveyReport present(HealthUserContext userContext, HealthSurveyReport healthSurveyReport, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,healthSurveyReport,tokens);
		
		
		HealthSurveyReport  healthSurveyReportToPresent = healthSurveyReportDaoOf(userContext).present(healthSurveyReport, tokens);
		
		List<BaseEntity> entityListToNaming = healthSurveyReportToPresent.collectRefercencesFromLists();
		healthSurveyReportDaoOf(userContext).alias(entityListToNaming);
		
		return  healthSurveyReportToPresent;
		
		
	}
 
 	
 	
 	public HealthSurveyReport loadHealthSurveyReportDetail(HealthUserContext userContext, String healthSurveyReportId) throws Exception{	
 		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport( userContext, healthSurveyReportId, allTokens());
 		return present(userContext,healthSurveyReport, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String healthSurveyReportId) throws Exception{	
 		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport( userContext, healthSurveyReportId, viewTokens());
 		return present(userContext,healthSurveyReport, allTokens());
		
 	}
 	protected HealthSurveyReport saveHealthSurveyReport(HealthUserContext userContext, HealthSurveyReport healthSurveyReport, Map<String,Object>tokens) throws Exception{	
 		return healthSurveyReportDaoOf(userContext).save(healthSurveyReport, tokens);
 	}
 	protected HealthSurveyReport loadHealthSurveyReport(HealthUserContext userContext, String healthSurveyReportId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).throwExceptionIfHasErrors( HealthSurveyReportManagerException.class);

 
 		return healthSurveyReportDaoOf(userContext).load(healthSurveyReportId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, HealthSurveyReport healthSurveyReport, Map<String, Object> tokens){
		super.addActions(userContext, healthSurveyReport, tokens);
		
		addAction(userContext, healthSurveyReport, tokens,"@create","createHealthSurveyReport","createHealthSurveyReport/","main","primary");
		addAction(userContext, healthSurveyReport, tokens,"@update","updateHealthSurveyReport","updateHealthSurveyReport/"+healthSurveyReport.getId()+"/","main","primary");
		addAction(userContext, healthSurveyReport, tokens,"@copy","cloneHealthSurveyReport","cloneHealthSurveyReport/"+healthSurveyReport.getId()+"/","main","primary");
		
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.transfer_to_student","transferToAnotherStudent","transferToAnotherStudent/"+healthSurveyReport.getId()+"/","main","primary");
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.transfer_to_teacher","transferToAnotherTeacher","transferToAnotherTeacher/"+healthSurveyReport.getId()+"/","main","primary");
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.transfer_to_survey","transferToAnotherSurvey","transferToAnotherSurvey/"+healthSurveyReport.getId()+"/","main","primary");
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.addStudentAnswer","addStudentAnswer","addStudentAnswer/"+healthSurveyReport.getId()+"/","studentAnswerList","primary");
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.removeStudentAnswer","removeStudentAnswer","removeStudentAnswer/"+healthSurveyReport.getId()+"/","studentAnswerList","primary");
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.updateStudentAnswer","updateStudentAnswer","updateStudentAnswer/"+healthSurveyReport.getId()+"/","studentAnswerList","primary");
		addAction(userContext, healthSurveyReport, tokens,"health_survey_report.copyStudentAnswerFrom","copyStudentAnswerFrom","copyStudentAnswerFrom/"+healthSurveyReport.getId()+"/","studentAnswerList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, HealthSurveyReport healthSurveyReport, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public HealthSurveyReport createHealthSurveyReport(HealthUserContext userContext, String surveyName,DateTime surveyTime,String teacherName,String school,String schoolClass,String studentName,String studentNumber,String guardianName,String guardianMobile,String studentId,String teacherId,String surveyId) throws Exception
	//public HealthSurveyReport createHealthSurveyReport(HealthUserContext userContext,String surveyName, DateTime surveyTime, String teacherName, String school, String schoolClass, String studentName, String studentNumber, String guardianName, String guardianMobile, String studentId, String teacherId, String surveyId) throws Exception
	{

		

		

		checkerOf(userContext).checkSurveyNameOfHealthSurveyReport(surveyName);
		checkerOf(userContext).checkSurveyTimeOfHealthSurveyReport(surveyTime);
		checkerOf(userContext).checkTeacherNameOfHealthSurveyReport(teacherName);
		checkerOf(userContext).checkSchoolOfHealthSurveyReport(school);
		checkerOf(userContext).checkSchoolClassOfHealthSurveyReport(schoolClass);
		checkerOf(userContext).checkStudentNameOfHealthSurveyReport(studentName);
		checkerOf(userContext).checkStudentNumberOfHealthSurveyReport(studentNumber);
		checkerOf(userContext).checkGuardianNameOfHealthSurveyReport(guardianName);
		checkerOf(userContext).checkGuardianMobileOfHealthSurveyReport(guardianMobile);
	
		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);


		HealthSurveyReport healthSurveyReport=createNewHealthSurveyReport();	

		healthSurveyReport.setSurveyName(surveyName);
		healthSurveyReport.setSurveyTime(surveyTime);
		healthSurveyReport.setTeacherName(teacherName);
		healthSurveyReport.setSchool(school);
		healthSurveyReport.setSchoolClass(schoolClass);
		healthSurveyReport.setStudentName(studentName);
		healthSurveyReport.setStudentNumber(studentNumber);
		healthSurveyReport.setGuardianName(guardianName);
		healthSurveyReport.setGuardianMobile(guardianMobile);
			
		Student student = loadStudent(userContext, studentId,emptyOptions());
		healthSurveyReport.setStudent(student);
		
		
			
		Teacher teacher = loadTeacher(userContext, teacherId,emptyOptions());
		healthSurveyReport.setTeacher(teacher);
		
		
			
		ClassDailyHealthSurvey survey = loadClassDailyHealthSurvey(userContext, surveyId,emptyOptions());
		healthSurveyReport.setSurvey(survey);
		
		

		healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, emptyOptions());
		
		onNewInstanceCreated(userContext, healthSurveyReport);
		return healthSurveyReport;


	}
	protected HealthSurveyReport createNewHealthSurveyReport()
	{

		return new HealthSurveyReport();
	}

	protected void checkParamsForUpdatingHealthSurveyReport(HealthUserContext userContext,String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).checkVersionOfHealthSurveyReport( healthSurveyReportVersion);
		

		if(HealthSurveyReport.SURVEY_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyNameOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.SURVEY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyTimeOfHealthSurveyReport(parseTimestamp(newValueExpr));
		}
		if(HealthSurveyReport.TEACHER_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkTeacherNameOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.SCHOOL_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchoolOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.SCHOOL_CLASS_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchoolClassOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.STUDENT_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentNameOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.STUDENT_NUMBER_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentNumberOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.GUARDIAN_NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkGuardianNameOfHealthSurveyReport(parseString(newValueExpr));
		}
		if(HealthSurveyReport.GUARDIAN_MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkGuardianMobileOfHealthSurveyReport(parseString(newValueExpr));
		}		

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);


	}



	public HealthSurveyReport clone(HealthUserContext userContext, String fromHealthSurveyReportId) throws Exception{

		return healthSurveyReportDaoOf(userContext).clone(fromHealthSurveyReportId, this.allTokens());
	}

	public HealthSurveyReport internalSaveHealthSurveyReport(HealthUserContext userContext, HealthSurveyReport healthSurveyReport) throws Exception
	{
		return internalSaveHealthSurveyReport(userContext, healthSurveyReport, allTokens());

	}
	public HealthSurveyReport internalSaveHealthSurveyReport(HealthUserContext userContext, HealthSurveyReport healthSurveyReport, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingHealthSurveyReport(userContext, healthSurveyReportId, healthSurveyReportVersion, property, newValueExpr, tokensExpr);


		synchronized(healthSurveyReport){
			//will be good when the healthSurveyReport loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to HealthSurveyReport.
			if (healthSurveyReport.isChanged()){
			
			}
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, options);
			return healthSurveyReport;

		}

	}

	public HealthSurveyReport updateHealthSurveyReport(HealthUserContext userContext,String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingHealthSurveyReport(userContext, healthSurveyReportId, healthSurveyReportVersion, property, newValueExpr, tokensExpr);



		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());
		if(healthSurveyReport.getVersion() != healthSurveyReportVersion){
			String message = "The target version("+healthSurveyReport.getVersion()+") is not equals to version("+healthSurveyReportVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(healthSurveyReport){
			//will be good when the healthSurveyReport loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to HealthSurveyReport.
			
			healthSurveyReport.changeProperty(property, newValueExpr);
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().done());
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
			//return saveHealthSurveyReport(userContext, healthSurveyReport, tokens().done());
		}

	}

	public HealthSurveyReport updateHealthSurveyReportProperty(HealthUserContext userContext,String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingHealthSurveyReport(userContext, healthSurveyReportId, healthSurveyReportVersion, property, newValueExpr, tokensExpr);

		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());
		if(healthSurveyReport.getVersion() != healthSurveyReportVersion){
			String message = "The target version("+healthSurveyReport.getVersion()+") is not equals to version("+healthSurveyReportVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(healthSurveyReport){
			//will be good when the healthSurveyReport loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to HealthSurveyReport.

			healthSurveyReport.changeProperty(property, newValueExpr);
			
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().done());
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
			//return saveHealthSurveyReport(userContext, healthSurveyReport, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected HealthSurveyReportTokens tokens(){
		return HealthSurveyReportTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return HealthSurveyReportTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentAnswerListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return HealthSurveyReportTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherStudent(HealthUserContext userContext, String healthSurveyReportId, String anotherStudentId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
 		checkerOf(userContext).checkIdOfStudent(anotherStudentId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

 	}
 	public HealthSurveyReport transferToAnotherStudent(HealthUserContext userContext, String healthSurveyReportId, String anotherStudentId) throws Exception
 	{
 		checkParamsForTransferingAnotherStudent(userContext, healthSurveyReportId,anotherStudentId);
 
		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());	
		synchronized(healthSurveyReport){
			//will be good when the healthSurveyReport loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Student student = loadStudent(userContext, anotherStudentId, emptyOptions());		
			healthSurveyReport.updateStudent(student);		
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, emptyOptions());
			
			return present(userContext,healthSurveyReport, allTokens());
			
		}

 	}

	


	public CandidateStudent requestCandidateStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateStudent result = new CandidateStudent();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("studentName");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Student> candidateList = studentDaoOf(userContext).requestCandidateStudentForHealthSurveyReport(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherTeacher(HealthUserContext userContext, String healthSurveyReportId, String anotherTeacherId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
 		checkerOf(userContext).checkIdOfTeacher(anotherTeacherId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

 	}
 	public HealthSurveyReport transferToAnotherTeacher(HealthUserContext userContext, String healthSurveyReportId, String anotherTeacherId) throws Exception
 	{
 		checkParamsForTransferingAnotherTeacher(userContext, healthSurveyReportId,anotherTeacherId);
 
		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());	
		synchronized(healthSurveyReport){
			//will be good when the healthSurveyReport loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Teacher teacher = loadTeacher(userContext, anotherTeacherId, emptyOptions());		
			healthSurveyReport.updateTeacher(teacher);		
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, emptyOptions());
			
			return present(userContext,healthSurveyReport, allTokens());
			
		}

 	}

	


	public CandidateTeacher requestCandidateTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateTeacher result = new CandidateTeacher();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Teacher> candidateList = teacherDaoOf(userContext).requestCandidateTeacherForHealthSurveyReport(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherSurvey(HealthUserContext userContext, String healthSurveyReportId, String anotherSurveyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(anotherSurveyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

 	}
 	public HealthSurveyReport transferToAnotherSurvey(HealthUserContext userContext, String healthSurveyReportId, String anotherSurveyId) throws Exception
 	{
 		checkParamsForTransferingAnotherSurvey(userContext, healthSurveyReportId,anotherSurveyId);
 
		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());	
		synchronized(healthSurveyReport){
			//will be good when the healthSurveyReport loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ClassDailyHealthSurvey survey = loadClassDailyHealthSurvey(userContext, anotherSurveyId, emptyOptions());		
			healthSurveyReport.updateSurvey(survey);		
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, emptyOptions());
			
			return present(userContext,healthSurveyReport, allTokens());
			
		}

 	}

	


	public CandidateClassDailyHealthSurvey requestCandidateSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateClassDailyHealthSurvey result = new CandidateClassDailyHealthSurvey();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ClassDailyHealthSurvey> candidateList = classDailyHealthSurveyDaoOf(userContext).requestCandidateClassDailyHealthSurveyForHealthSurveyReport(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ClassDailyHealthSurvey loadClassDailyHealthSurvey(HealthUserContext userContext, String newSurveyId, Map<String,Object> options) throws Exception
 	{

 		return classDailyHealthSurveyDaoOf(userContext).load(newSurveyId, options);
 	}
 	


	

 	protected Teacher loadTeacher(HealthUserContext userContext, String newTeacherId, Map<String,Object> options) throws Exception
 	{

 		return teacherDaoOf(userContext).load(newTeacherId, options);
 	}
 	


	

 	protected Student loadStudent(HealthUserContext userContext, String newStudentId, Map<String,Object> options) throws Exception
 	{

 		return studentDaoOf(userContext).load(newStudentId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String healthSurveyReportId, int healthSurveyReportVersion) throws Exception {
		//deleteInternal(userContext, healthSurveyReportId, healthSurveyReportVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String healthSurveyReportId, int healthSurveyReportVersion) throws Exception{

		healthSurveyReportDaoOf(userContext).delete(healthSurveyReportId, healthSurveyReportVersion);
	}

	public HealthSurveyReport forgetByAll(HealthUserContext userContext, String healthSurveyReportId, int healthSurveyReportVersion) throws Exception {
		return forgetByAllInternal(userContext, healthSurveyReportId, healthSurveyReportVersion);
	}
	protected HealthSurveyReport forgetByAllInternal(HealthUserContext userContext,
			String healthSurveyReportId, int healthSurveyReportVersion) throws Exception{

		return healthSurveyReportDaoOf(userContext).disconnectFromAll(healthSurveyReportId, healthSurveyReportVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new HealthSurveyReportManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return healthSurveyReportDaoOf(userContext).deleteAll();
	}


	//disconnect HealthSurveyReport with daily_answer in StudentAnswer
	protected HealthSurveyReport breakWithStudentAnswerByDailyAnswer(HealthUserContext userContext, String healthSurveyReportId, String dailyAnswerId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());

			synchronized(healthSurveyReport){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				healthSurveyReportDaoOf(userContext).planToRemoveStudentAnswerListWithDailyAnswer(healthSurveyReport, dailyAnswerId, this.emptyOptions());

				healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().withStudentAnswerList().done());
				return healthSurveyReport;
			}
	}






	protected void checkParamsForAddingStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String dailyAnswerId, String questionTopic, String answer,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);

		
		checkerOf(userContext).checkDailyAnswerIdOfStudentAnswer(dailyAnswerId);
		
		checkerOf(userContext).checkQuestionTopicOfStudentAnswer(questionTopic);
		
		checkerOf(userContext).checkAnswerOfStudentAnswer(answer);
	
		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);


	}
	public  HealthSurveyReport addStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String dailyAnswerId, String questionTopic, String answer, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentAnswer(userContext,healthSurveyReportId,dailyAnswerId, questionTopic, answer,tokensExpr);

		StudentAnswer studentAnswer = createStudentAnswer(userContext,dailyAnswerId, questionTopic, answer);

		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, emptyOptions());
		synchronized(healthSurveyReport){
			//Will be good when the healthSurveyReport loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			healthSurveyReport.addStudentAnswer( studentAnswer );
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().withStudentAnswerList().done());
			
			userContext.getManagerGroup().getStudentAnswerManager().onNewInstanceCreated(userContext, studentAnswer);
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentAnswerProperties(HealthUserContext userContext, String healthSurveyReportId,String id,String questionTopic,String answer,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).checkIdOfStudentAnswer(id);

		checkerOf(userContext).checkQuestionTopicOfStudentAnswer( questionTopic);
		checkerOf(userContext).checkAnswerOfStudentAnswer( answer);

		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

	}
	public  HealthSurveyReport updateStudentAnswerProperties(HealthUserContext userContext, String healthSurveyReportId, String id,String questionTopic,String answer, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentAnswerProperties(userContext,healthSurveyReportId,id,questionTopic,answer,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentAnswerListList()
				.searchStudentAnswerListWith(StudentAnswer.ID_PROPERTY, "is", id).done();

		HealthSurveyReport healthSurveyReportToUpdate = loadHealthSurveyReport(userContext, healthSurveyReportId, options);

		if(healthSurveyReportToUpdate.getStudentAnswerList().isEmpty()){
			throw new HealthSurveyReportManagerException("StudentAnswer is NOT FOUND with id: '"+id+"'");
		}

		StudentAnswer item = healthSurveyReportToUpdate.getStudentAnswerList().first();

		item.updateQuestionTopic( questionTopic );
		item.updateAnswer( answer );


		//checkParamsForAddingStudentAnswer(userContext,healthSurveyReportId,name, code, used,tokensExpr);
		HealthSurveyReport healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReportToUpdate, tokens().withStudentAnswerList().done());
		synchronized(healthSurveyReport){
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentAnswer createStudentAnswer(HealthUserContext userContext, String dailyAnswerId, String questionTopic, String answer) throws Exception{

		StudentAnswer studentAnswer = new StudentAnswer();
		
		
		StudentDailyAnswer  dailyAnswer = new StudentDailyAnswer();
		dailyAnswer.setId(dailyAnswerId);		
		studentAnswer.setDailyAnswer(dailyAnswer);		
		studentAnswer.setQuestionTopic(questionTopic);		
		studentAnswer.setAnswer(answer);
	
		
		return studentAnswer;


	}

	protected StudentAnswer createIndexedStudentAnswer(String id, int version){

		StudentAnswer studentAnswer = new StudentAnswer();
		studentAnswer.setId(id);
		studentAnswer.setVersion(version);
		return studentAnswer;

	}

	protected void checkParamsForRemovingStudentAnswerList(HealthUserContext userContext, String healthSurveyReportId,
			String studentAnswerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		for(String studentAnswerIdItem: studentAnswerIds){
			checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

	}
	public  HealthSurveyReport removeStudentAnswerList(HealthUserContext userContext, String healthSurveyReportId,
			String studentAnswerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentAnswerList(userContext, healthSurveyReportId,  studentAnswerIds, tokensExpr);


			HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());
			synchronized(healthSurveyReport){
				//Will be good when the healthSurveyReport loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				healthSurveyReportDaoOf(userContext).planToRemoveStudentAnswerList(healthSurveyReport, studentAnswerIds, allTokens());
				healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().withStudentAnswerList().done());
				deleteRelationListInGraph(userContext, healthSurveyReport.getStudentAnswerList());
				return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentAnswer(HealthUserContext userContext, String healthSurveyReportId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfHealthSurveyReport( healthSurveyReportId);
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer(studentAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

	}
	public  HealthSurveyReport removeStudentAnswer(HealthUserContext userContext, String healthSurveyReportId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentAnswer(userContext,healthSurveyReportId, studentAnswerId, studentAnswerVersion,tokensExpr);

		StudentAnswer studentAnswer = createIndexedStudentAnswer(studentAnswerId, studentAnswerVersion);
		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());
		synchronized(healthSurveyReport){
			//Will be good when the healthSurveyReport loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			healthSurveyReport.removeStudentAnswer( studentAnswer );
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().withStudentAnswerList().done());
			deleteRelationInGraph(userContext, studentAnswer);
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentAnswer(HealthUserContext userContext, String healthSurveyReportId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfHealthSurveyReport( healthSurveyReportId);
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer(studentAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

	}
	public  HealthSurveyReport copyStudentAnswerFrom(HealthUserContext userContext, String healthSurveyReportId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentAnswer(userContext,healthSurveyReportId, studentAnswerId, studentAnswerVersion,tokensExpr);

		StudentAnswer studentAnswer = createIndexedStudentAnswer(studentAnswerId, studentAnswerVersion);
		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, allTokens());
		synchronized(healthSurveyReport){
			//Will be good when the healthSurveyReport loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			healthSurveyReport.copyStudentAnswerFrom( studentAnswer );
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().withStudentAnswerList().done());
			
			userContext.getManagerGroup().getStudentAnswerManager().onNewInstanceCreated(userContext, (StudentAnswer)healthSurveyReport.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer(studentAnswerVersion);
		

		if(StudentAnswer.QUESTION_TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkQuestionTopicOfStudentAnswer(parseString(newValueExpr));
		}
		
		if(StudentAnswer.ANSWER_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerOfStudentAnswer(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(HealthSurveyReportManagerException.class);

	}

	public  HealthSurveyReport updateStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentAnswer(userContext, healthSurveyReportId, studentAnswerId, studentAnswerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentAnswerList().searchStudentAnswerListWith(StudentAnswer.ID_PROPERTY, "eq", studentAnswerId).done();



		HealthSurveyReport healthSurveyReport = loadHealthSurveyReport(userContext, healthSurveyReportId, loadTokens);

		synchronized(healthSurveyReport){
			//Will be good when the healthSurveyReport loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//healthSurveyReport.removeStudentAnswer( studentAnswer );
			//make changes to AcceleraterAccount.
			StudentAnswer studentAnswerIndex = createIndexedStudentAnswer(studentAnswerId, studentAnswerVersion);

			StudentAnswer studentAnswer = healthSurveyReport.findTheStudentAnswer(studentAnswerIndex);
			if(studentAnswer == null){
				throw new HealthSurveyReportManagerException(studentAnswer+" is NOT FOUND" );
			}

			studentAnswer.changeProperty(property, newValueExpr);
			
			healthSurveyReport = saveHealthSurveyReport(userContext, healthSurveyReport, tokens().withStudentAnswerList().done());
			return present(userContext,healthSurveyReport, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, HealthSurveyReport newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

	// -----------------------------------//  登录部分处理 \\-----------------------------------
	// 手机号+短信验证码 登录
	public Object loginByMobile(HealthUserContextImpl userContext, String mobile, String verifyCode) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByMobile");
		LoginData loginData = new LoginData();
		loginData.setMobile(mobile);
		loginData.setVerifyCode(verifyCode);

		LoginContext loginContext = LoginContext.of(LoginMethod.MOBILE, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 账号+密码登录
	public Object loginByPassword(HealthUserContextImpl userContext, String loginId, Password password) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(), "loginByPassword");
		LoginData loginData = new LoginData();
		loginData.setLoginId(loginId);
		loginData.setPassword(password.getClearTextPassword());

		LoginContext loginContext = LoginContext.of(LoginMethod.PASSWORD, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 微信小程序登录
	public Object loginByWechatMiniProgram(HealthUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 企业微信小程序登录
	public Object loginByWechatWorkMiniProgram(HealthUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatWorkMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_WORK_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 调用登录处理
	protected Object processLoginRequest(HealthUserContextImpl userContext, LoginContext loginContext) throws Exception {
		IamService iamService = (IamService) userContext.getBean("iamService");
		LoginResult loginResult = iamService.doLogin(userContext, loginContext, this);
		// 根据登录结果
		if (!loginResult.isAuthenticated()) {
			throw new Exception(loginResult.getMessage());
		}
		if (loginResult.isSuccess()) {
			return onLoginSuccess(userContext, loginResult);
		}
		if (loginResult.isNewUser()) {
			throw new Exception("请联系你的上级,先为你创建账号,然后再来登录.");
		}
		return new LoginForm();
	}
	
	@Override
	public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
			throws IllegalAccessException {
		HealthUserContextImpl userContext = (HealthUserContextImpl)baseUserContext;
		IamService iamService = (IamService) userContext.getBean("iamService");
		Map<String, Object> loginInfo = iamService.getCachedLoginInfo(userContext);
		
		SecUser secUser = iamService.tryToLoadSecUser(userContext, loginInfo);
		UserApp userApp = iamService.tryToLoadUserApp(userContext, loginInfo);
		if (userApp != null) {
			userApp.setSecUser(secUser);
		}
		afterSecUserAppLoadedWhenCheckAccess(userContext, loginInfo, secUser, userApp);
		if (!isMethodNeedLogin(userContext, methodName, parameters)) {
			return accessOK();
		}
		
		return super.checkAccess(baseUserContext, methodName, parameters);
	}
	
	// 判断哪些接口需要登录后才能执行. 默认除了loginBy开头的,其他都要登录
	protected boolean isMethodNeedLogin(HealthUserContextImpl userContext, String methodName, Object[] parameters) {
		if (methodName.startsWith("loginBy")) {
			return false;
		}
		if (methodName.startsWith("logout")) {
			return false;
		}
		return true;
	}

	// 在checkAccess中加载了secUser和userApp后会调用此方法,用于定制化的用户数据加载. 默认什么也不做
	protected void afterSecUserAppLoadedWhenCheckAccess(HealthUserContextImpl userContext, Map<String, Object> loginInfo,
			SecUser secUser, UserApp userApp) throws IllegalAccessException{
	}



	protected Object onLoginSuccess(HealthUserContext userContext, LoginResult loginResult) throws Exception {
		// by default, return the view of this object
		UserApp userApp = loginResult.getLoginContext().getLoginTarget().getUserApp();
		return this.view(userContext, userApp.getObjectId());
	}

	public void onAuthenticationFailed(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, failed is failed, nothing can do
	}
	public void onAuthenticateNewUserLogged(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, should create a account and bind with sec user, BUT, I don't know how to 
		// create new object as of generate this method. It depends on business logical. So,
		throw new Exception("请重载函数onAuthenticateNewUserLogged()以处理新用户登录");
	}
	public void onAuthenticateUserLogged(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, find the correct user-app
		SecUser secUser = loginResult.getLoginContext().getLoginTarget().getSecUser();
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
		key.put(UserApp.OBJECT_TYPE_PROPERTY, HealthSurveyReport.INTERNAL_TYPE);
		SmartList<UserApp> userApps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, EO);
		if (userApps == null || userApps.isEmpty()) {
			throw new Exception("您的账号未关联销售人员,请联系客服处理账号异常.");
		}
		UserApp userApp = userApps.first();
		userApp.setSecUser(secUser);
		loginResult.getLoginContext().getLoginTarget().setUserApp(userApp);
	}
	// -----------------------------------\\  登录部分处理 //-----------------------------------
}


