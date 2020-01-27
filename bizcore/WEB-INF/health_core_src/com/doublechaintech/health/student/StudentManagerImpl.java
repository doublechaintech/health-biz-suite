
package com.doublechaintech.health.student;

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


import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;

import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.schoolclass.CandidateSchoolClass;
import com.doublechaintech.health.guardian.CandidateGuardian;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;






public class StudentManagerImpl extends CustomHealthCheckerManager implements StudentManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Student";
	@Override
	public StudentDAO daoOf(HealthUserContext userContext) {
		return studentDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws StudentManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new StudentManagerException(message);

	}



 	protected Student saveStudent(HealthUserContext userContext, Student student, String [] tokensExpr) throws Exception{	
 		//return getStudentDAO().save(student, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveStudent(userContext, student, tokens);
 	}
 	
 	protected Student saveStudentDetail(HealthUserContext userContext, Student student) throws Exception{	

 		
 		return saveStudent(userContext, student, allTokens());
 	}
 	
 	public Student loadStudent(HealthUserContext userContext, String studentId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Student student = loadStudent( userContext, studentId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,student, tokens);
 	}
 	
 	
 	 public Student searchStudent(HealthUserContext userContext, String studentId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Student student = loadStudent( userContext, studentId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,student, tokens);
 	}
 	
 	

 	protected Student present(HealthUserContext userContext, Student student, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,student,tokens);
		
		
		Student  studentToPresent = studentDaoOf(userContext).present(student, tokens);
		
		List<BaseEntity> entityListToNaming = studentToPresent.collectRefercencesFromLists();
		studentDaoOf(userContext).alias(entityListToNaming);
		
		return  studentToPresent;
		
		
	}
 
 	
 	
 	public Student loadStudentDetail(HealthUserContext userContext, String studentId) throws Exception{	
 		Student student = loadStudent( userContext, studentId, allTokens());
 		return present(userContext,student, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String studentId) throws Exception{	
 		Student student = loadStudent( userContext, studentId, viewTokens());
 		return present(userContext,student, allTokens());
		
 	}
 	protected Student saveStudent(HealthUserContext userContext, Student student, Map<String,Object>tokens) throws Exception{	
 		return studentDaoOf(userContext).save(student, tokens);
 	}
 	protected Student loadStudent(HealthUserContext userContext, String studentId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentManagerException.class);

 
 		return studentDaoOf(userContext).load(studentId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Student student, Map<String, Object> tokens){
		super.addActions(userContext, student, tokens);
		
		addAction(userContext, student, tokens,"@create","createStudent","createStudent/","main","primary");
		addAction(userContext, student, tokens,"@update","updateStudent","updateStudent/"+student.getId()+"/","main","primary");
		addAction(userContext, student, tokens,"@copy","cloneStudent","cloneStudent/"+student.getId()+"/","main","primary");
		
		addAction(userContext, student, tokens,"student.transfer_to_guardian","transferToAnotherGuardian","transferToAnotherGuardian/"+student.getId()+"/","main","primary");
		addAction(userContext, student, tokens,"student.transfer_to_school_class","transferToAnotherSchoolClass","transferToAnotherSchoolClass/"+student.getId()+"/","main","primary");
		addAction(userContext, student, tokens,"student.transfer_to_cq","transferToAnotherCq","transferToAnotherCq/"+student.getId()+"/","main","primary");
		addAction(userContext, student, tokens,"student.addStudentHealthSurvey","addStudentHealthSurvey","addStudentHealthSurvey/"+student.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, student, tokens,"student.removeStudentHealthSurvey","removeStudentHealthSurvey","removeStudentHealthSurvey/"+student.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, student, tokens,"student.updateStudentHealthSurvey","updateStudentHealthSurvey","updateStudentHealthSurvey/"+student.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, student, tokens,"student.copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom/"+student.getId()+"/","studentHealthSurveyList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Student student, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Student createStudent(HealthUserContext userContext, String name,String gender,String guardianId,String schoolClassId,String studentId,String cqId) throws Exception
	//public Student createStudent(HealthUserContext userContext,String name, String gender, String guardianId, String schoolClassId, String studentId, String cqId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfStudent(name);
		checkerOf(userContext).checkGenderOfStudent(gender);
		checkerOf(userContext).checkStudentIdOfStudent(studentId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);


		Student student=createNewStudent();	

		student.setName(name);
		student.setGender(gender);
			
		Guardian guardian = loadGuardian(userContext, guardianId,emptyOptions());
		student.setGuardian(guardian);
		
		
			
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId,emptyOptions());
		student.setSchoolClass(schoolClass);
		
		
		student.setStudentId(studentId);
			
		ChangeRequest cq = loadChangeRequest(userContext, cqId,emptyOptions());
		student.setCq(cq);
		
		

		student = saveStudent(userContext, student, emptyOptions());
		
		onNewInstanceCreated(userContext, student);
		return student;


	}
	protected Student createNewStudent()
	{

		return new Student();
	}

	protected void checkParamsForUpdatingStudent(HealthUserContext userContext,String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent( studentVersion);
		

		if(Student.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfStudent(parseString(newValueExpr));
		}
		if(Student.GENDER_PROPERTY.equals(property)){
			checkerOf(userContext).checkGenderOfStudent(parseString(newValueExpr));
		}		

				

		
		if(Student.STUDENT_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentIdOfStudent(parseString(newValueExpr));
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);


	}



	public Student clone(HealthUserContext userContext, String fromStudentId) throws Exception{

		return studentDaoOf(userContext).clone(fromStudentId, this.allTokens());
	}

	public Student internalSaveStudent(HealthUserContext userContext, Student student) throws Exception
	{
		return internalSaveStudent(userContext, student, allTokens());

	}
	public Student internalSaveStudent(HealthUserContext userContext, Student student, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingStudent(userContext, studentId, studentVersion, property, newValueExpr, tokensExpr);


		synchronized(student){
			//will be good when the student loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Student.
			if (student.isChanged()){
			
			}
			student = saveStudent(userContext, student, options);
			return student;

		}

	}

	public Student updateStudent(HealthUserContext userContext,String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudent(userContext, studentId, studentVersion, property, newValueExpr, tokensExpr);



		Student student = loadStudent(userContext, studentId, allTokens());
		if(student.getVersion() != studentVersion){
			String message = "The target version("+student.getVersion()+") is not equals to version("+studentVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(student){
			//will be good when the student loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Student.
			
			student.changeProperty(property, newValueExpr);
			student = saveStudent(userContext, student, tokens().done());
			return present(userContext,student, mergedAllTokens(tokensExpr));
			//return saveStudent(userContext, student, tokens().done());
		}

	}

	public Student updateStudentProperty(HealthUserContext userContext,String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudent(userContext, studentId, studentVersion, property, newValueExpr, tokensExpr);

		Student student = loadStudent(userContext, studentId, allTokens());
		if(student.getVersion() != studentVersion){
			String message = "The target version("+student.getVersion()+") is not equals to version("+studentVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(student){
			//will be good when the student loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Student.

			student.changeProperty(property, newValueExpr);
			
			student = saveStudent(userContext, student, tokens().done());
			return present(userContext,student, mergedAllTokens(tokensExpr));
			//return saveStudent(userContext, student, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected StudentTokens tokens(){
		return StudentTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return StudentTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentHealthSurveyListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return StudentTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherGuardian(HealthUserContext userContext, String studentId, String anotherGuardianId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudent(studentId);
 		checkerOf(userContext).checkIdOfGuardian(anotherGuardianId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

 	}
 	public Student transferToAnotherGuardian(HealthUserContext userContext, String studentId, String anotherGuardianId) throws Exception
 	{
 		checkParamsForTransferingAnotherGuardian(userContext, studentId,anotherGuardianId);
 
		Student student = loadStudent(userContext, studentId, allTokens());	
		synchronized(student){
			//will be good when the student loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Guardian guardian = loadGuardian(userContext, anotherGuardianId, emptyOptions());		
			student.updateGuardian(guardian);		
			student = saveStudent(userContext, student, emptyOptions());
			
			return present(userContext,student, allTokens());
			
		}

 	}

	


	public CandidateGuardian requestCandidateGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateGuardian result = new CandidateGuardian();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Guardian> candidateList = guardianDaoOf(userContext).requestCandidateGuardianForStudent(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherSchoolClass(HealthUserContext userContext, String studentId, String anotherSchoolClassId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudent(studentId);
 		checkerOf(userContext).checkIdOfSchoolClass(anotherSchoolClassId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

 	}
 	public Student transferToAnotherSchoolClass(HealthUserContext userContext, String studentId, String anotherSchoolClassId) throws Exception
 	{
 		checkParamsForTransferingAnotherSchoolClass(userContext, studentId,anotherSchoolClassId);
 
		Student student = loadStudent(userContext, studentId, allTokens());	
		synchronized(student){
			//will be good when the student loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SchoolClass schoolClass = loadSchoolClass(userContext, anotherSchoolClassId, emptyOptions());		
			student.updateSchoolClass(schoolClass);		
			student = saveStudent(userContext, student, emptyOptions());
			
			return present(userContext,student, allTokens());
			
		}

 	}

	


	public CandidateSchoolClass requestCandidateSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSchoolClass result = new CandidateSchoolClass();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SchoolClass> candidateList = schoolClassDaoOf(userContext).requestCandidateSchoolClassForStudent(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCq(HealthUserContext userContext, String studentId, String anotherCqId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudent(studentId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherCqId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

 	}
 	public Student transferToAnotherCq(HealthUserContext userContext, String studentId, String anotherCqId) throws Exception
 	{
 		checkParamsForTransferingAnotherCq(userContext, studentId,anotherCqId);
 
		Student student = loadStudent(userContext, studentId, allTokens());	
		synchronized(student){
			//will be good when the student loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest cq = loadChangeRequest(userContext, anotherCqId, emptyOptions());		
			student.updateCq(cq);		
			student = saveStudent(userContext, student, emptyOptions());
			
			return present(userContext,student, allTokens());
			
		}

 	}

	


	public CandidateChangeRequest requestCandidateCq(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForStudent(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected SchoolClass loadSchoolClass(HealthUserContext userContext, String newSchoolClassId, Map<String,Object> options) throws Exception
 	{

 		return schoolClassDaoOf(userContext).load(newSchoolClassId, options);
 	}
 	


	

 	protected Guardian loadGuardian(HealthUserContext userContext, String newGuardianId, Map<String,Object> options) throws Exception
 	{

 		return guardianDaoOf(userContext).load(newGuardianId, options);
 	}
 	


	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newCqId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newCqId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String studentId, int studentVersion) throws Exception {
		//deleteInternal(userContext, studentId, studentVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String studentId, int studentVersion) throws Exception{

		studentDaoOf(userContext).delete(studentId, studentVersion);
	}

	public Student forgetByAll(HealthUserContext userContext, String studentId, int studentVersion) throws Exception {
		return forgetByAllInternal(userContext, studentId, studentVersion);
	}
	protected Student forgetByAllInternal(HealthUserContext userContext,
			String studentId, int studentVersion) throws Exception{

		return studentDaoOf(userContext).disconnectFromAll(studentId, studentVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new StudentManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return studentDaoOf(userContext).deleteAll();
	}


	//disconnect Student with survey_status in StudentHealthSurvey
	protected Student breakWithStudentHealthSurveyBySurveyStatus(HealthUserContext userContext, String studentId, String surveyStatusId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Student student = loadStudent(userContext, studentId, allTokens());

			synchronized(student){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				studentDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSurveyStatus(student, surveyStatusId, this.emptyOptions());

				student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
				return student;
			}
	}
	//disconnect Student with school_class in StudentHealthSurvey
	protected Student breakWithStudentHealthSurveyBySchoolClass(HealthUserContext userContext, String studentId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Student student = loadStudent(userContext, studentId, allTokens());

			synchronized(student){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				studentDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSchoolClass(student, schoolClassId, this.emptyOptions());

				student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
				return student;
			}
	}
	//disconnect Student with class_daily_health_survey in StudentHealthSurvey
	protected Student breakWithStudentHealthSurveyByClassDailyHealthSurvey(HealthUserContext userContext, String studentId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Student student = loadStudent(userContext, studentId, allTokens());

			synchronized(student){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				studentDaoOf(userContext).planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(student, classDailyHealthSurveyId, this.emptyOptions());

				student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
				return student;
			}
	}
	//disconnect Student with cq in StudentHealthSurvey
	protected Student breakWithStudentHealthSurveyByCq(HealthUserContext userContext, String studentId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Student student = loadStudent(userContext, studentId, allTokens());

			synchronized(student){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				studentDaoOf(userContext).planToRemoveStudentHealthSurveyListWithCq(student, cqId, this.emptyOptions());

				student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
				return student;
			}
	}






	protected void checkParamsForAddingStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfStudent(studentId);

		
		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
		
		checkerOf(userContext).checkSurveyStatusIdOfStudentHealthSurvey(surveyStatusId);
		
		checkerOf(userContext).checkSchoolClassIdOfStudentHealthSurvey(schoolClassId);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfStudentHealthSurvey(classDailyHealthSurveyId);
		
		checkerOf(userContext).checkCqIdOfStudentHealthSurvey(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);


	}
	public  Student addStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentHealthSurvey(userContext,studentId,answerTime, surveyStatusId, schoolClassId, classDailyHealthSurveyId, cqId,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createStudentHealthSurvey(userContext,answerTime, surveyStatusId, schoolClassId, classDailyHealthSurveyId, cqId);

		Student student = loadStudent(userContext, studentId, emptyOptions());
		synchronized(student){
			//Will be good when the student loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			student.addStudentHealthSurvey( studentHealthSurvey );
			student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, studentHealthSurvey);
			return present(userContext,student, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentHealthSurveyProperties(HealthUserContext userContext, String studentId,String id,DateTime answerTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(id);

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey( answerTime);

		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

	}
	public  Student updateStudentHealthSurveyProperties(HealthUserContext userContext, String studentId, String id,DateTime answerTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurveyProperties(userContext,studentId,id,answerTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentHealthSurveyListList()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "is", id).done();

		Student studentToUpdate = loadStudent(userContext, studentId, options);

		if(studentToUpdate.getStudentHealthSurveyList().isEmpty()){
			throw new StudentManagerException("StudentHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		StudentHealthSurvey item = studentToUpdate.getStudentHealthSurveyList().first();

		item.updateAnswerTime( answerTime );


		//checkParamsForAddingStudentHealthSurvey(userContext,studentId,name, code, used,tokensExpr);
		Student student = saveStudent(userContext, studentToUpdate, tokens().withStudentHealthSurveyList().done());
		synchronized(student){
			return present(userContext,student, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId, String cqId) throws Exception{

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		
		
		studentHealthSurvey.setAnswerTime(answerTime);		
		SurveyStatus  surveyStatus = new SurveyStatus();
		surveyStatus.setId(surveyStatusId);		
		studentHealthSurvey.setSurveyStatus(surveyStatus);		
		SchoolClass  schoolClass = new SchoolClass();
		schoolClass.setId(schoolClassId);		
		studentHealthSurvey.setSchoolClass(schoolClass);		
		ClassDailyHealthSurvey  classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(classDailyHealthSurveyId);		
		studentHealthSurvey.setClassDailyHealthSurvey(classDailyHealthSurvey);		
		studentHealthSurvey.setCreateTime(userContext.now());		
		studentHealthSurvey.setLastUpdateTime(userContext.now());		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		studentHealthSurvey.setCq(cq);
	
		
		return studentHealthSurvey;


	}

	protected StudentHealthSurvey createIndexedStudentHealthSurvey(String id, int version){

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(id);
		studentHealthSurvey.setVersion(version);
		return studentHealthSurvey;

	}

	protected void checkParamsForRemovingStudentHealthSurveyList(HealthUserContext userContext, String studentId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfStudent(studentId);
		for(String studentHealthSurveyIdItem: studentHealthSurveyIds){
			checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

	}
	public  Student removeStudentHealthSurveyList(HealthUserContext userContext, String studentId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentHealthSurveyList(userContext, studentId,  studentHealthSurveyIds, tokensExpr);


			Student student = loadStudent(userContext, studentId, allTokens());
			synchronized(student){
				//Will be good when the student loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				studentDaoOf(userContext).planToRemoveStudentHealthSurveyList(student, studentHealthSurveyIds, allTokens());
				student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
				deleteRelationListInGraph(userContext, student.getStudentHealthSurveyList());
				return present(userContext,student, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentHealthSurvey(HealthUserContext userContext, String studentId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfStudent( studentId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

	}
	public  Student removeStudentHealthSurvey(HealthUserContext userContext, String studentId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentHealthSurvey(userContext,studentId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		Student student = loadStudent(userContext, studentId, allTokens());
		synchronized(student){
			//Will be good when the student loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			student.removeStudentHealthSurvey( studentHealthSurvey );
			student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
			deleteRelationInGraph(userContext, studentHealthSurvey);
			return present(userContext,student, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentHealthSurvey(HealthUserContext userContext, String studentId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfStudent( studentId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

	}
	public  Student copyStudentHealthSurveyFrom(HealthUserContext userContext, String studentId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentHealthSurvey(userContext,studentId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		Student student = loadStudent(userContext, studentId, allTokens());
		synchronized(student){
			//Will be good when the student loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentHealthSurvey.updateLastUpdateTime(userContext.now());

			student.copyStudentHealthSurveyFrom( studentHealthSurvey );
			student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, (StudentHealthSurvey)student.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,student, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext, String studentId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		

		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentManagerException.class);

	}

	public  Student updateStudentHealthSurvey(HealthUserContext userContext, String studentId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentHealthSurvey(userContext, studentId, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentHealthSurveyList().searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "eq", studentHealthSurveyId).done();



		Student student = loadStudent(userContext, studentId, loadTokens);

		synchronized(student){
			//Will be good when the student loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//student.removeStudentHealthSurvey( studentHealthSurvey );
			//make changes to AcceleraterAccount.
			StudentHealthSurvey studentHealthSurveyIndex = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);

			StudentHealthSurvey studentHealthSurvey = student.findTheStudentHealthSurvey(studentHealthSurveyIndex);
			if(studentHealthSurvey == null){
				throw new StudentManagerException(studentHealthSurvey+" is NOT FOUND" );
			}

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			student = saveStudent(userContext, student, tokens().withStudentHealthSurveyList().done());
			return present(userContext,student, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, Student newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Student.INTERNAL_TYPE);
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


