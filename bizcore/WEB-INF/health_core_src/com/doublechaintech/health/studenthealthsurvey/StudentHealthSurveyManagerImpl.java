
package com.doublechaintech.health.studenthealthsurvey;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.Password;

import com.doublechaintech.health.*;
import com.doublechaintech.health.HealthUserContextImpl;
import com.doublechaintech.health.iamservice.*;
import com.doublechaintech.health.services.IamService;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.terapico.uccaf.BaseUserContext;


import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;

import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.teacher.CandidateTeacher;
import com.doublechaintech.health.classdailyhealthsurvey.CandidateClassDailyHealthSurvey;
import com.doublechaintech.health.student.CandidateStudent;
import com.doublechaintech.health.surveystatus.CandidateSurveyStatus;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;






public class StudentHealthSurveyManagerImpl extends CustomHealthCheckerManager implements StudentHealthSurveyManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "StudentHealthSurvey";
	@Override
	public StudentHealthSurveyDAO daoOf(HealthUserContext userContext) {
		return studentHealthSurveyDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws StudentHealthSurveyManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new StudentHealthSurveyManagerException(message);

	}



 	protected StudentHealthSurvey saveStudentHealthSurvey(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey, String [] tokensExpr) throws Exception{	
 		//return getStudentHealthSurveyDAO().save(studentHealthSurvey, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens);
 	}
 	
 	protected StudentHealthSurvey saveStudentHealthSurveyDetail(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey) throws Exception{	

 		
 		return saveStudentHealthSurvey(userContext, studentHealthSurvey, allTokens());
 	}
 	
 	public StudentHealthSurvey loadStudentHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentHealthSurveyManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey( userContext, studentHealthSurveyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,studentHealthSurvey, tokens);
 	}
 	
 	
 	 public StudentHealthSurvey searchStudentHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentHealthSurveyManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey( userContext, studentHealthSurveyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,studentHealthSurvey, tokens);
 	}
 	
 	

 	protected StudentHealthSurvey present(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,studentHealthSurvey,tokens);
		
		
		StudentHealthSurvey  studentHealthSurveyToPresent = studentHealthSurveyDaoOf(userContext).present(studentHealthSurvey, tokens);
		
		List<BaseEntity> entityListToNaming = studentHealthSurveyToPresent.collectRefercencesFromLists();
		studentHealthSurveyDaoOf(userContext).alias(entityListToNaming);
		
		return  studentHealthSurveyToPresent;
		
		
	}
 
 	
 	
 	public StudentHealthSurvey loadStudentHealthSurveyDetail(HealthUserContext userContext, String studentHealthSurveyId) throws Exception{	
 		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey( userContext, studentHealthSurveyId, allTokens());
 		return present(userContext,studentHealthSurvey, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String studentHealthSurveyId) throws Exception{	
 		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey( userContext, studentHealthSurveyId, viewTokens());
 		return present(userContext,studentHealthSurvey, allTokens());
		
 	}
 	protected StudentHealthSurvey saveStudentHealthSurvey(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey, Map<String,Object>tokens) throws Exception{	
 		return studentHealthSurveyDaoOf(userContext).save(studentHealthSurvey, tokens);
 	}
 	protected StudentHealthSurvey loadStudentHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentHealthSurveyManagerException.class);

 
 		return studentHealthSurveyDaoOf(userContext).load(studentHealthSurveyId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey, Map<String, Object> tokens){
		super.addActions(userContext, studentHealthSurvey, tokens);
		
		addAction(userContext, studentHealthSurvey, tokens,"@create","createStudentHealthSurvey","createStudentHealthSurvey/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"@update","updateStudentHealthSurvey","updateStudentHealthSurvey/"+studentHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"@copy","cloneStudentHealthSurvey","cloneStudentHealthSurvey/"+studentHealthSurvey.getId()+"/","main","primary");
		
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.transfer_to_student","transferToAnotherStudent","transferToAnotherStudent/"+studentHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.transfer_to_survey_status","transferToAnotherSurveyStatus","transferToAnotherSurveyStatus/"+studentHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.transfer_to_teacher","transferToAnotherTeacher","transferToAnotherTeacher/"+studentHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.transfer_to_class_daily_health_survey","transferToAnotherClassDailyHealthSurvey","transferToAnotherClassDailyHealthSurvey/"+studentHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.transfer_to_change_request","transferToAnotherChangeRequest","transferToAnotherChangeRequest/"+studentHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.addStudentDailyAnswer","addStudentDailyAnswer","addStudentDailyAnswer/"+studentHealthSurvey.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.removeStudentDailyAnswer","removeStudentDailyAnswer","removeStudentDailyAnswer/"+studentHealthSurvey.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.updateStudentDailyAnswer","updateStudentDailyAnswer","updateStudentDailyAnswer/"+studentHealthSurvey.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, studentHealthSurvey, tokens,"student_health_survey.copyStudentDailyAnswerFrom","copyStudentDailyAnswerFrom","copyStudentDailyAnswerFrom/"+studentHealthSurvey.getId()+"/","studentDailyAnswerList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId,DateTime answerTime,String surveyStatusId,String teacherId,String classDailyHealthSurveyId,String changeRequestId) throws Exception
	//public StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext,String studentId, DateTime answerTime, String surveyStatusId, String teacherId, String classDailyHealthSurveyId, String changeRequestId) throws Exception
	{

		

		

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);


		StudentHealthSurvey studentHealthSurvey=createNewStudentHealthSurvey();	

			
		Student student = loadStudent(userContext, studentId,emptyOptions());
		studentHealthSurvey.setStudent(student);
		
		
		studentHealthSurvey.setAnswerTime(answerTime);
			
		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId,emptyOptions());
		studentHealthSurvey.setSurveyStatus(surveyStatus);
		
		
			
		Teacher teacher = loadTeacher(userContext, teacherId,emptyOptions());
		studentHealthSurvey.setTeacher(teacher);
		
		
			
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId,emptyOptions());
		studentHealthSurvey.setClassDailyHealthSurvey(classDailyHealthSurvey);
		
		
		studentHealthSurvey.setCreateTime(userContext.now());
		studentHealthSurvey.setLastUpdateTime(userContext.now());
		if(isValidIdentifier(changeRequestId)){	
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId,emptyOptions());
			studentHealthSurvey.setChangeRequest(changeRequest);
		}
		

		studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());
		
		onNewInstanceCreated(userContext, studentHealthSurvey);
		return studentHealthSurvey;


	}
	protected StudentHealthSurvey createNewStudentHealthSurvey()
	{

		return new StudentHealthSurvey();
	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext,String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey( studentHealthSurveyVersion);
		
		

		
		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		
			
		}		

				

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);


	}



	public StudentHealthSurvey clone(HealthUserContext userContext, String fromStudentHealthSurveyId) throws Exception{

		return studentHealthSurveyDaoOf(userContext).clone(fromStudentHealthSurveyId, this.allTokens());
	}

	public StudentHealthSurvey internalSaveStudentHealthSurvey(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey) throws Exception
	{
		return internalSaveStudentHealthSurvey(userContext, studentHealthSurvey, allTokens());

	}
	public StudentHealthSurvey internalSaveStudentHealthSurvey(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingStudentHealthSurvey(userContext, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr, tokensExpr);


		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentHealthSurvey.
			if (studentHealthSurvey.isChanged()){
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			}
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, options);
			return studentHealthSurvey;

		}

	}

	public StudentHealthSurvey updateStudentHealthSurvey(HealthUserContext userContext,String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurvey(userContext, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr, tokensExpr);



		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());
		if(studentHealthSurvey.getVersion() != studentHealthSurveyVersion){
			String message = "The target version("+studentHealthSurvey.getVersion()+") is not equals to version("+studentHealthSurveyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentHealthSurvey.
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().done());
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
			//return saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().done());
		}

	}

	public StudentHealthSurvey updateStudentHealthSurveyProperty(HealthUserContext userContext,String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurvey(userContext, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr, tokensExpr);

		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());
		if(studentHealthSurvey.getVersion() != studentHealthSurveyVersion){
			String message = "The target version("+studentHealthSurvey.getVersion()+") is not equals to version("+studentHealthSurveyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentHealthSurvey.

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().done());
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
			//return saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected StudentHealthSurveyTokens tokens(){
		return StudentHealthSurveyTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return StudentHealthSurveyTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentDailyAnswerListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return StudentHealthSurveyTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherStudent(HealthUserContext userContext, String studentHealthSurveyId, String anotherStudentId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
 		checkerOf(userContext).checkIdOfStudent(anotherStudentId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

 	}
 	public StudentHealthSurvey transferToAnotherStudent(HealthUserContext userContext, String studentHealthSurveyId, String anotherStudentId) throws Exception
 	{
 		checkParamsForTransferingAnotherStudent(userContext, studentHealthSurveyId,anotherStudentId);
 
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());	
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Student student = loadStudent(userContext, anotherStudentId, emptyOptions());		
			studentHealthSurvey.updateStudent(student);		
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());
			
			return present(userContext,studentHealthSurvey, allTokens());
			
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
		SmartList<Student> candidateList = studentDaoOf(userContext).requestCandidateStudentForStudentHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherSurveyStatus(HealthUserContext userContext, String studentHealthSurveyId, String anotherSurveyStatusId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
 		checkerOf(userContext).checkIdOfSurveyStatus(anotherSurveyStatusId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

 	}
 	public StudentHealthSurvey transferToAnotherSurveyStatus(HealthUserContext userContext, String studentHealthSurveyId, String anotherSurveyStatusId) throws Exception
 	{
 		checkParamsForTransferingAnotherSurveyStatus(userContext, studentHealthSurveyId,anotherSurveyStatusId);
 
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());	
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SurveyStatus surveyStatus = loadSurveyStatus(userContext, anotherSurveyStatusId, emptyOptions());		
			studentHealthSurvey.updateSurveyStatus(surveyStatus);		
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());
			
			return present(userContext,studentHealthSurvey, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherSurveyStatusWithCode(HealthUserContext userContext, String studentHealthSurveyId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
 		checkerOf(userContext).checkCodeOfSurveyStatus( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

 	}

 	public StudentHealthSurvey transferToAnotherSurveyStatusWithCode(HealthUserContext userContext, String studentHealthSurveyId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherSurveyStatusWithCode(userContext, studentHealthSurveyId,anotherCode);
 		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SurveyStatus surveyStatus = loadSurveyStatusWithCode(userContext, anotherCode, emptyOptions());
			studentHealthSurvey.updateSurveyStatus(surveyStatus);
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());

			return present(userContext,studentHealthSurvey, allTokens());

		}
 	}

	 


	public CandidateSurveyStatus requestCandidateSurveyStatus(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSurveyStatus result = new CandidateSurveyStatus();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SurveyStatus> candidateList = surveyStatusDaoOf(userContext).requestCandidateSurveyStatusForStudentHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherTeacher(HealthUserContext userContext, String studentHealthSurveyId, String anotherTeacherId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
 		checkerOf(userContext).checkIdOfTeacher(anotherTeacherId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

 	}
 	public StudentHealthSurvey transferToAnotherTeacher(HealthUserContext userContext, String studentHealthSurveyId, String anotherTeacherId) throws Exception
 	{
 		checkParamsForTransferingAnotherTeacher(userContext, studentHealthSurveyId,anotherTeacherId);
 
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());	
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Teacher teacher = loadTeacher(userContext, anotherTeacherId, emptyOptions());		
			studentHealthSurvey.updateTeacher(teacher);		
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());
			
			return present(userContext,studentHealthSurvey, allTokens());
			
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
		SmartList<Teacher> candidateList = teacherDaoOf(userContext).requestCandidateTeacherForStudentHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherClassDailyHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, String anotherClassDailyHealthSurveyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(anotherClassDailyHealthSurveyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

 	}
 	public StudentHealthSurvey transferToAnotherClassDailyHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, String anotherClassDailyHealthSurveyId) throws Exception
 	{
 		checkParamsForTransferingAnotherClassDailyHealthSurvey(userContext, studentHealthSurveyId,anotherClassDailyHealthSurveyId);
 
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());	
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, anotherClassDailyHealthSurveyId, emptyOptions());		
			studentHealthSurvey.updateClassDailyHealthSurvey(classDailyHealthSurvey);		
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());
			
			return present(userContext,studentHealthSurvey, allTokens());
			
		}

 	}

	


	public CandidateClassDailyHealthSurvey requestCandidateClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<ClassDailyHealthSurvey> candidateList = classDailyHealthSurveyDaoOf(userContext).requestCandidateClassDailyHealthSurveyForStudentHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherChangeRequest(HealthUserContext userContext, String studentHealthSurveyId, String anotherChangeRequestId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherChangeRequestId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

 	}
 	public StudentHealthSurvey transferToAnotherChangeRequest(HealthUserContext userContext, String studentHealthSurveyId, String anotherChangeRequestId) throws Exception
 	{
 		checkParamsForTransferingAnotherChangeRequest(userContext, studentHealthSurveyId,anotherChangeRequestId);
 
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());	
		synchronized(studentHealthSurvey){
			//will be good when the studentHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest changeRequest = loadChangeRequest(userContext, anotherChangeRequestId, emptyOptions());		
			studentHealthSurvey.updateChangeRequest(changeRequest);		
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, emptyOptions());
			
			return present(userContext,studentHealthSurvey, allTokens());
			
		}

 	}

	


	public CandidateChangeRequest requestCandidateChangeRequest(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequest result = new CandidateChangeRequest();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForStudentHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected SurveyStatus loadSurveyStatus(HealthUserContext userContext, String newSurveyStatusId, Map<String,Object> options) throws Exception
 	{

 		return surveyStatusDaoOf(userContext).load(newSurveyStatusId, options);
 	}
 	
 	protected SurveyStatus loadSurveyStatusWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return surveyStatusDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newChangeRequestId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newChangeRequestId, options);
 	}
 	


	

 	protected ClassDailyHealthSurvey loadClassDailyHealthSurvey(HealthUserContext userContext, String newClassDailyHealthSurveyId, Map<String,Object> options) throws Exception
 	{

 		return classDailyHealthSurveyDaoOf(userContext).load(newClassDailyHealthSurveyId, options);
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

	public void delete(HealthUserContext userContext, String studentHealthSurveyId, int studentHealthSurveyVersion) throws Exception {
		//deleteInternal(userContext, studentHealthSurveyId, studentHealthSurveyVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String studentHealthSurveyId, int studentHealthSurveyVersion) throws Exception{

		studentHealthSurveyDaoOf(userContext).delete(studentHealthSurveyId, studentHealthSurveyVersion);
	}

	public StudentHealthSurvey forgetByAll(HealthUserContext userContext, String studentHealthSurveyId, int studentHealthSurveyVersion) throws Exception {
		return forgetByAllInternal(userContext, studentHealthSurveyId, studentHealthSurveyVersion);
	}
	protected StudentHealthSurvey forgetByAllInternal(HealthUserContext userContext,
			String studentHealthSurveyId, int studentHealthSurveyVersion) throws Exception{

		return studentHealthSurveyDaoOf(userContext).disconnectFromAll(studentHealthSurveyId, studentHealthSurveyVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new StudentHealthSurveyManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return studentHealthSurveyDaoOf(userContext).deleteAll();
	}


	//disconnect StudentHealthSurvey with question in StudentDailyAnswer
	protected StudentHealthSurvey breakWithStudentDailyAnswerByQuestion(HealthUserContext userContext, String studentHealthSurveyId, String questionId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());

			synchronized(studentHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				studentHealthSurveyDaoOf(userContext).planToRemoveStudentDailyAnswerListWithQuestion(studentHealthSurvey, questionId, this.emptyOptions());

				studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().withStudentDailyAnswerList().done());
				return studentHealthSurvey;
			}
	}






	protected void checkParamsForAddingStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String questionId, String answer,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);

		
		checkerOf(userContext).checkQuestionIdOfStudentDailyAnswer(questionId);
		
		checkerOf(userContext).checkAnswerOfStudentDailyAnswer(answer);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);


	}
	public  StudentHealthSurvey addStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String questionId, String answer, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentDailyAnswer(userContext,studentHealthSurveyId,questionId, answer,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createStudentDailyAnswer(userContext,questionId, answer);

		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, emptyOptions());
		synchronized(studentHealthSurvey){
			//Will be good when the studentHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			studentHealthSurvey.addStudentDailyAnswer( studentDailyAnswer );
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().withStudentDailyAnswerList().done());
			
			userContext.getManagerGroup().getStudentDailyAnswerManager().onNewInstanceCreated(userContext, studentDailyAnswer);
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentDailyAnswerProperties(HealthUserContext userContext, String studentHealthSurveyId,String id,String answer,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(id);

		checkerOf(userContext).checkAnswerOfStudentDailyAnswer( answer);

		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

	}
	public  StudentHealthSurvey updateStudentDailyAnswerProperties(HealthUserContext userContext, String studentHealthSurveyId, String id,String answer, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentDailyAnswerProperties(userContext,studentHealthSurveyId,id,answer,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentDailyAnswerListList()
				.searchStudentDailyAnswerListWith(StudentDailyAnswer.ID_PROPERTY, "is", id).done();

		StudentHealthSurvey studentHealthSurveyToUpdate = loadStudentHealthSurvey(userContext, studentHealthSurveyId, options);

		if(studentHealthSurveyToUpdate.getStudentDailyAnswerList().isEmpty()){
			throw new StudentHealthSurveyManagerException("StudentDailyAnswer is NOT FOUND with id: '"+id+"'");
		}

		StudentDailyAnswer item = studentHealthSurveyToUpdate.getStudentDailyAnswerList().first();

		item.updateAnswer( answer );


		//checkParamsForAddingStudentDailyAnswer(userContext,studentHealthSurveyId,name, code, used,tokensExpr);
		StudentHealthSurvey studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurveyToUpdate, tokens().withStudentDailyAnswerList().done());
		synchronized(studentHealthSurvey){
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentDailyAnswer createStudentDailyAnswer(HealthUserContext userContext, String questionId, String answer) throws Exception{

		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		
		
		DailySurveyQuestion  question = new DailySurveyQuestion();
		question.setId(questionId);		
		studentDailyAnswer.setQuestion(question);		
		studentDailyAnswer.setAnswer(answer);		
		studentDailyAnswer.setCreateTime(userContext.now());		
		studentDailyAnswer.setLastUpdateTime(userContext.now());
	
		
		return studentDailyAnswer;


	}

	protected StudentDailyAnswer createIndexedStudentDailyAnswer(String id, int version){

		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		studentDailyAnswer.setId(id);
		studentDailyAnswer.setVersion(version);
		return studentDailyAnswer;

	}

	protected void checkParamsForRemovingStudentDailyAnswerList(HealthUserContext userContext, String studentHealthSurveyId,
			String studentDailyAnswerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		for(String studentDailyAnswerIdItem: studentDailyAnswerIds){
			checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

	}
	public  StudentHealthSurvey removeStudentDailyAnswerList(HealthUserContext userContext, String studentHealthSurveyId,
			String studentDailyAnswerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentDailyAnswerList(userContext, studentHealthSurveyId,  studentDailyAnswerIds, tokensExpr);


			StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());
			synchronized(studentHealthSurvey){
				//Will be good when the studentHealthSurvey loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				studentHealthSurveyDaoOf(userContext).planToRemoveStudentDailyAnswerList(studentHealthSurvey, studentDailyAnswerIds, allTokens());
				studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().withStudentDailyAnswerList().done());
				deleteRelationListInGraph(userContext, studentHealthSurvey.getStudentDailyAnswerList());
				return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfStudentHealthSurvey( studentHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

	}
	public  StudentHealthSurvey removeStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentDailyAnswer(userContext,studentHealthSurveyId, studentDailyAnswerId, studentDailyAnswerVersion,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());
		synchronized(studentHealthSurvey){
			//Will be good when the studentHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			studentHealthSurvey.removeStudentDailyAnswer( studentDailyAnswer );
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().withStudentDailyAnswerList().done());
			deleteRelationInGraph(userContext, studentDailyAnswer);
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfStudentHealthSurvey( studentHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

	}
	public  StudentHealthSurvey copyStudentDailyAnswerFrom(HealthUserContext userContext, String studentHealthSurveyId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentDailyAnswer(userContext,studentHealthSurveyId, studentDailyAnswerId, studentDailyAnswerVersion,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, allTokens());
		synchronized(studentHealthSurvey){
			//Will be good when the studentHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentDailyAnswer.updateLastUpdateTime(userContext.now());

			studentHealthSurvey.copyStudentDailyAnswerFrom( studentDailyAnswer );
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().withStudentDailyAnswerList().done());
			
			userContext.getManagerGroup().getStudentDailyAnswerManager().onNewInstanceCreated(userContext, (StudentDailyAnswer)studentHealthSurvey.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		

		if(StudentDailyAnswer.ANSWER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerOfStudentDailyAnswer(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentHealthSurveyManagerException.class);

	}

	public  StudentHealthSurvey updateStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentDailyAnswer(userContext, studentHealthSurveyId, studentDailyAnswerId, studentDailyAnswerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentDailyAnswerList().searchStudentDailyAnswerListWith(StudentDailyAnswer.ID_PROPERTY, "eq", studentDailyAnswerId).done();



		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId, loadTokens);

		synchronized(studentHealthSurvey){
			//Will be good when the studentHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//studentHealthSurvey.removeStudentDailyAnswer( studentDailyAnswer );
			//make changes to AcceleraterAccount.
			StudentDailyAnswer studentDailyAnswerIndex = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);

			StudentDailyAnswer studentDailyAnswer = studentHealthSurvey.findTheStudentDailyAnswer(studentDailyAnswerIndex);
			if(studentDailyAnswer == null){
				throw new StudentHealthSurveyManagerException(studentDailyAnswer+" is NOT FOUND" );
			}

			studentDailyAnswer.changeProperty(property, newValueExpr);
			studentDailyAnswer.updateLastUpdateTime(userContext.now());
			studentHealthSurvey = saveStudentHealthSurvey(userContext, studentHealthSurvey, tokens().withStudentDailyAnswerList().done());
			return present(userContext,studentHealthSurvey, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, StudentHealthSurvey newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, StudentHealthSurvey.INTERNAL_TYPE);
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


