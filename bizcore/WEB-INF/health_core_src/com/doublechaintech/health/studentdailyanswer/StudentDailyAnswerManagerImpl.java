
package com.doublechaintech.health.studentdailyanswer;

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


import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.dailysurveyquestion.CandidateDailySurveyQuestion;
import com.doublechaintech.health.studenthealthsurvey.CandidateStudentHealthSurvey;

import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;






public class StudentDailyAnswerManagerImpl extends CustomHealthCheckerManager implements StudentDailyAnswerManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "StudentDailyAnswer";
	@Override
	public StudentDailyAnswerDAO daoOf(HealthUserContext userContext) {
		return studentDailyAnswerDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws StudentDailyAnswerManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new StudentDailyAnswerManagerException(message);

	}



 	protected StudentDailyAnswer saveStudentDailyAnswer(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer, String [] tokensExpr) throws Exception{	
 		//return getStudentDailyAnswerDAO().save(studentDailyAnswer, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens);
 	}
 	
 	protected StudentDailyAnswer saveStudentDailyAnswerDetail(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer) throws Exception{	

 		
 		return saveStudentDailyAnswer(userContext, studentDailyAnswer, allTokens());
 	}
 	
 	public StudentDailyAnswer loadStudentDailyAnswer(HealthUserContext userContext, String studentDailyAnswerId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentDailyAnswerManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer( userContext, studentDailyAnswerId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,studentDailyAnswer, tokens);
 	}
 	
 	
 	 public StudentDailyAnswer searchStudentDailyAnswer(HealthUserContext userContext, String studentDailyAnswerId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentDailyAnswerManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer( userContext, studentDailyAnswerId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,studentDailyAnswer, tokens);
 	}
 	
 	

 	protected StudentDailyAnswer present(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,studentDailyAnswer,tokens);
		
		
		StudentDailyAnswer  studentDailyAnswerToPresent = studentDailyAnswerDaoOf(userContext).present(studentDailyAnswer, tokens);
		
		List<BaseEntity> entityListToNaming = studentDailyAnswerToPresent.collectRefercencesFromLists();
		studentDailyAnswerDaoOf(userContext).alias(entityListToNaming);
		
		return  studentDailyAnswerToPresent;
		
		
	}
 
 	
 	
 	public StudentDailyAnswer loadStudentDailyAnswerDetail(HealthUserContext userContext, String studentDailyAnswerId) throws Exception{	
 		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer( userContext, studentDailyAnswerId, allTokens());
 		return present(userContext,studentDailyAnswer, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String studentDailyAnswerId) throws Exception{	
 		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer( userContext, studentDailyAnswerId, viewTokens());
 		return present(userContext,studentDailyAnswer, allTokens());
		
 	}
 	protected StudentDailyAnswer saveStudentDailyAnswer(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer, Map<String,Object>tokens) throws Exception{	
 		return studentDailyAnswerDaoOf(userContext).save(studentDailyAnswer, tokens);
 	}
 	protected StudentDailyAnswer loadStudentDailyAnswer(HealthUserContext userContext, String studentDailyAnswerId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).throwExceptionIfHasErrors( StudentDailyAnswerManagerException.class);

 
 		return studentDailyAnswerDaoOf(userContext).load(studentDailyAnswerId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer, Map<String, Object> tokens){
		super.addActions(userContext, studentDailyAnswer, tokens);
		
		addAction(userContext, studentDailyAnswer, tokens,"@create","createStudentDailyAnswer","createStudentDailyAnswer/","main","primary");
		addAction(userContext, studentDailyAnswer, tokens,"@update","updateStudentDailyAnswer","updateStudentDailyAnswer/"+studentDailyAnswer.getId()+"/","main","primary");
		addAction(userContext, studentDailyAnswer, tokens,"@copy","cloneStudentDailyAnswer","cloneStudentDailyAnswer/"+studentDailyAnswer.getId()+"/","main","primary");
		
		addAction(userContext, studentDailyAnswer, tokens,"student_daily_answer.transfer_to_student_health_survey","transferToAnotherStudentHealthSurvey","transferToAnotherStudentHealthSurvey/"+studentDailyAnswer.getId()+"/","main","primary");
		addAction(userContext, studentDailyAnswer, tokens,"student_daily_answer.transfer_to_question","transferToAnotherQuestion","transferToAnotherQuestion/"+studentDailyAnswer.getId()+"/","main","primary");
		addAction(userContext, studentDailyAnswer, tokens,"student_daily_answer.addStudentAnswer","addStudentAnswer","addStudentAnswer/"+studentDailyAnswer.getId()+"/","studentAnswerList","primary");
		addAction(userContext, studentDailyAnswer, tokens,"student_daily_answer.removeStudentAnswer","removeStudentAnswer","removeStudentAnswer/"+studentDailyAnswer.getId()+"/","studentAnswerList","primary");
		addAction(userContext, studentDailyAnswer, tokens,"student_daily_answer.updateStudentAnswer","updateStudentAnswer","updateStudentAnswer/"+studentDailyAnswer.getId()+"/","studentAnswerList","primary");
		addAction(userContext, studentDailyAnswer, tokens,"student_daily_answer.copyStudentAnswerFrom","copyStudentAnswerFrom","copyStudentAnswerFrom/"+studentDailyAnswer.getId()+"/","studentAnswerList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public StudentDailyAnswer createStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId,String questionId,String answer) throws Exception
	//public StudentDailyAnswer createStudentDailyAnswer(HealthUserContext userContext,String studentHealthSurveyId, String questionId, String answer) throws Exception
	{

		

		

		checkerOf(userContext).checkAnswerOfStudentDailyAnswer(answer);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);


		StudentDailyAnswer studentDailyAnswer=createNewStudentDailyAnswer();	

			
		StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, studentHealthSurveyId,emptyOptions());
		studentDailyAnswer.setStudentHealthSurvey(studentHealthSurvey);
		
		
			
		DailySurveyQuestion question = loadDailySurveyQuestion(userContext, questionId,emptyOptions());
		studentDailyAnswer.setQuestion(question);
		
		
		studentDailyAnswer.setAnswer(answer);
		studentDailyAnswer.setCreateTime(userContext.now());
		studentDailyAnswer.setLastUpdateTime(userContext.now());

		studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, emptyOptions());
		
		onNewInstanceCreated(userContext, studentDailyAnswer);
		return studentDailyAnswer;


	}
	protected StudentDailyAnswer createNewStudentDailyAnswer()
	{

		return new StudentDailyAnswer();
	}

	protected void checkParamsForUpdatingStudentDailyAnswer(HealthUserContext userContext,String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer( studentDailyAnswerVersion);
		
		

				

		
		if(StudentDailyAnswer.ANSWER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerOfStudentDailyAnswer(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);


	}



	public StudentDailyAnswer clone(HealthUserContext userContext, String fromStudentDailyAnswerId) throws Exception{

		return studentDailyAnswerDaoOf(userContext).clone(fromStudentDailyAnswerId, this.allTokens());
	}

	public StudentDailyAnswer internalSaveStudentDailyAnswer(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer) throws Exception
	{
		return internalSaveStudentDailyAnswer(userContext, studentDailyAnswer, allTokens());

	}
	public StudentDailyAnswer internalSaveStudentDailyAnswer(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingStudentDailyAnswer(userContext, studentDailyAnswerId, studentDailyAnswerVersion, property, newValueExpr, tokensExpr);


		synchronized(studentDailyAnswer){
			//will be good when the studentDailyAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentDailyAnswer.
			if (studentDailyAnswer.isChanged()){
			studentDailyAnswer.updateLastUpdateTime(userContext.now());
			}
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, options);
			return studentDailyAnswer;

		}

	}

	public StudentDailyAnswer updateStudentDailyAnswer(HealthUserContext userContext,String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentDailyAnswer(userContext, studentDailyAnswerId, studentDailyAnswerVersion, property, newValueExpr, tokensExpr);



		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());
		if(studentDailyAnswer.getVersion() != studentDailyAnswerVersion){
			String message = "The target version("+studentDailyAnswer.getVersion()+") is not equals to version("+studentDailyAnswerVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(studentDailyAnswer){
			//will be good when the studentDailyAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentDailyAnswer.
			studentDailyAnswer.updateLastUpdateTime(userContext.now());
			studentDailyAnswer.changeProperty(property, newValueExpr);
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().done());
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
			//return saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().done());
		}

	}

	public StudentDailyAnswer updateStudentDailyAnswerProperty(HealthUserContext userContext,String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentDailyAnswer(userContext, studentDailyAnswerId, studentDailyAnswerVersion, property, newValueExpr, tokensExpr);

		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());
		if(studentDailyAnswer.getVersion() != studentDailyAnswerVersion){
			String message = "The target version("+studentDailyAnswer.getVersion()+") is not equals to version("+studentDailyAnswerVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(studentDailyAnswer){
			//will be good when the studentDailyAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to StudentDailyAnswer.

			studentDailyAnswer.changeProperty(property, newValueExpr);
			studentDailyAnswer.updateLastUpdateTime(userContext.now());
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().done());
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
			//return saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected StudentDailyAnswerTokens tokens(){
		return StudentDailyAnswerTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return StudentDailyAnswerTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentAnswerListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return StudentDailyAnswerTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherStudentHealthSurvey(HealthUserContext userContext, String studentDailyAnswerId, String anotherStudentHealthSurveyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
 		checkerOf(userContext).checkIdOfStudentHealthSurvey(anotherStudentHealthSurveyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

 	}
 	public StudentDailyAnswer transferToAnotherStudentHealthSurvey(HealthUserContext userContext, String studentDailyAnswerId, String anotherStudentHealthSurveyId) throws Exception
 	{
 		checkParamsForTransferingAnotherStudentHealthSurvey(userContext, studentDailyAnswerId,anotherStudentHealthSurveyId);
 
		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());	
		synchronized(studentDailyAnswer){
			//will be good when the studentDailyAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			StudentHealthSurvey studentHealthSurvey = loadStudentHealthSurvey(userContext, anotherStudentHealthSurveyId, emptyOptions());		
			studentDailyAnswer.updateStudentHealthSurvey(studentHealthSurvey);		
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, emptyOptions());
			
			return present(userContext,studentDailyAnswer, allTokens());
			
		}

 	}

	


	public CandidateStudentHealthSurvey requestCandidateStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateStudentHealthSurvey result = new CandidateStudentHealthSurvey();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("student");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<StudentHealthSurvey> candidateList = studentHealthSurveyDaoOf(userContext).requestCandidateStudentHealthSurveyForStudentDailyAnswer(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherQuestion(HealthUserContext userContext, String studentDailyAnswerId, String anotherQuestionId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
 		checkerOf(userContext).checkIdOfDailySurveyQuestion(anotherQuestionId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

 	}
 	public StudentDailyAnswer transferToAnotherQuestion(HealthUserContext userContext, String studentDailyAnswerId, String anotherQuestionId) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestion(userContext, studentDailyAnswerId,anotherQuestionId);
 
		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());	
		synchronized(studentDailyAnswer){
			//will be good when the studentDailyAnswer loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			DailySurveyQuestion question = loadDailySurveyQuestion(userContext, anotherQuestionId, emptyOptions());		
			studentDailyAnswer.updateQuestion(question);		
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, emptyOptions());
			
			return present(userContext,studentDailyAnswer, allTokens());
			
		}

 	}

	


	public CandidateDailySurveyQuestion requestCandidateQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateDailySurveyQuestion result = new CandidateDailySurveyQuestion();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("topic");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<DailySurveyQuestion> candidateList = dailySurveyQuestionDaoOf(userContext).requestCandidateDailySurveyQuestionForStudentDailyAnswer(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected StudentHealthSurvey loadStudentHealthSurvey(HealthUserContext userContext, String newStudentHealthSurveyId, Map<String,Object> options) throws Exception
 	{

 		return studentHealthSurveyDaoOf(userContext).load(newStudentHealthSurveyId, options);
 	}
 	


	

 	protected DailySurveyQuestion loadDailySurveyQuestion(HealthUserContext userContext, String newQuestionId, Map<String,Object> options) throws Exception
 	{

 		return dailySurveyQuestionDaoOf(userContext).load(newQuestionId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String studentDailyAnswerId, int studentDailyAnswerVersion) throws Exception {
		//deleteInternal(userContext, studentDailyAnswerId, studentDailyAnswerVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String studentDailyAnswerId, int studentDailyAnswerVersion) throws Exception{

		studentDailyAnswerDaoOf(userContext).delete(studentDailyAnswerId, studentDailyAnswerVersion);
	}

	public StudentDailyAnswer forgetByAll(HealthUserContext userContext, String studentDailyAnswerId, int studentDailyAnswerVersion) throws Exception {
		return forgetByAllInternal(userContext, studentDailyAnswerId, studentDailyAnswerVersion);
	}
	protected StudentDailyAnswer forgetByAllInternal(HealthUserContext userContext,
			String studentDailyAnswerId, int studentDailyAnswerVersion) throws Exception{

		return studentDailyAnswerDaoOf(userContext).disconnectFromAll(studentDailyAnswerId, studentDailyAnswerVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new StudentDailyAnswerManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return studentDailyAnswerDaoOf(userContext).deleteAll();
	}


	//disconnect StudentDailyAnswer with health_survey_report in StudentAnswer
	protected StudentDailyAnswer breakWithStudentAnswerByHealthSurveyReport(HealthUserContext userContext, String studentDailyAnswerId, String healthSurveyReportId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());

			synchronized(studentDailyAnswer){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				studentDailyAnswerDaoOf(userContext).planToRemoveStudentAnswerListWithHealthSurveyReport(studentDailyAnswer, healthSurveyReportId, this.emptyOptions());

				studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().withStudentAnswerList().done());
				return studentDailyAnswer;
			}
	}






	protected void checkParamsForAddingStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId, String healthSurveyReportId, String questionTopic, String answer,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);

		
		checkerOf(userContext).checkHealthSurveyReportIdOfStudentAnswer(healthSurveyReportId);
		
		checkerOf(userContext).checkQuestionTopicOfStudentAnswer(questionTopic);
		
		checkerOf(userContext).checkAnswerOfStudentAnswer(answer);
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);


	}
	public  StudentDailyAnswer addStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId, String healthSurveyReportId, String questionTopic, String answer, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentAnswer(userContext,studentDailyAnswerId,healthSurveyReportId, questionTopic, answer,tokensExpr);

		StudentAnswer studentAnswer = createStudentAnswer(userContext,healthSurveyReportId, questionTopic, answer);

		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, emptyOptions());
		synchronized(studentDailyAnswer){
			//Will be good when the studentDailyAnswer loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			studentDailyAnswer.addStudentAnswer( studentAnswer );
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().withStudentAnswerList().done());
			
			userContext.getManagerGroup().getStudentAnswerManager().onNewInstanceCreated(userContext, studentAnswer);
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentAnswerProperties(HealthUserContext userContext, String studentDailyAnswerId,String id,String questionTopic,String answer,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkIdOfStudentAnswer(id);

		checkerOf(userContext).checkQuestionTopicOfStudentAnswer( questionTopic);
		checkerOf(userContext).checkAnswerOfStudentAnswer( answer);

		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

	}
	public  StudentDailyAnswer updateStudentAnswerProperties(HealthUserContext userContext, String studentDailyAnswerId, String id,String questionTopic,String answer, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentAnswerProperties(userContext,studentDailyAnswerId,id,questionTopic,answer,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentAnswerListList()
				.searchStudentAnswerListWith(StudentAnswer.ID_PROPERTY, "is", id).done();

		StudentDailyAnswer studentDailyAnswerToUpdate = loadStudentDailyAnswer(userContext, studentDailyAnswerId, options);

		if(studentDailyAnswerToUpdate.getStudentAnswerList().isEmpty()){
			throw new StudentDailyAnswerManagerException("StudentAnswer is NOT FOUND with id: '"+id+"'");
		}

		StudentAnswer item = studentDailyAnswerToUpdate.getStudentAnswerList().first();

		item.updateQuestionTopic( questionTopic );
		item.updateAnswer( answer );


		//checkParamsForAddingStudentAnswer(userContext,studentDailyAnswerId,name, code, used,tokensExpr);
		StudentDailyAnswer studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswerToUpdate, tokens().withStudentAnswerList().done());
		synchronized(studentDailyAnswer){
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentAnswer createStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String questionTopic, String answer) throws Exception{

		StudentAnswer studentAnswer = new StudentAnswer();
		
		
		HealthSurveyReport  healthSurveyReport = new HealthSurveyReport();
		healthSurveyReport.setId(healthSurveyReportId);		
		studentAnswer.setHealthSurveyReport(healthSurveyReport);		
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

	protected void checkParamsForRemovingStudentAnswerList(HealthUserContext userContext, String studentDailyAnswerId,
			String studentAnswerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		for(String studentAnswerIdItem: studentAnswerIds){
			checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

	}
	public  StudentDailyAnswer removeStudentAnswerList(HealthUserContext userContext, String studentDailyAnswerId,
			String studentAnswerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentAnswerList(userContext, studentDailyAnswerId,  studentAnswerIds, tokensExpr);


			StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());
			synchronized(studentDailyAnswer){
				//Will be good when the studentDailyAnswer loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				studentDailyAnswerDaoOf(userContext).planToRemoveStudentAnswerList(studentDailyAnswer, studentAnswerIds, allTokens());
				studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().withStudentAnswerList().done());
				deleteRelationListInGraph(userContext, studentDailyAnswer.getStudentAnswerList());
				return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfStudentDailyAnswer( studentDailyAnswerId);
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer(studentAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

	}
	public  StudentDailyAnswer removeStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentAnswer(userContext,studentDailyAnswerId, studentAnswerId, studentAnswerVersion,tokensExpr);

		StudentAnswer studentAnswer = createIndexedStudentAnswer(studentAnswerId, studentAnswerVersion);
		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());
		synchronized(studentDailyAnswer){
			//Will be good when the studentDailyAnswer loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			studentDailyAnswer.removeStudentAnswer( studentAnswer );
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().withStudentAnswerList().done());
			deleteRelationInGraph(userContext, studentAnswer);
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfStudentDailyAnswer( studentDailyAnswerId);
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer(studentAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

	}
	public  StudentDailyAnswer copyStudentAnswerFrom(HealthUserContext userContext, String studentDailyAnswerId,
		String studentAnswerId, int studentAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentAnswer(userContext,studentDailyAnswerId, studentAnswerId, studentAnswerVersion,tokensExpr);

		StudentAnswer studentAnswer = createIndexedStudentAnswer(studentAnswerId, studentAnswerVersion);
		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, allTokens());
		synchronized(studentDailyAnswer){
			//Will be good when the studentDailyAnswer loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			studentDailyAnswer.copyStudentAnswerFrom( studentAnswer );
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().withStudentAnswerList().done());
			
			userContext.getManagerGroup().getStudentAnswerManager().onNewInstanceCreated(userContext, (StudentAnswer)studentDailyAnswer.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId, String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkIdOfStudentAnswer(studentAnswerId);
		checkerOf(userContext).checkVersionOfStudentAnswer(studentAnswerVersion);
		

		if(StudentAnswer.QUESTION_TOPIC_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkQuestionTopicOfStudentAnswer(parseString(newValueExpr));
		
		}
		
		if(StudentAnswer.ANSWER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerOfStudentAnswer(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(StudentDailyAnswerManagerException.class);

	}

	public  StudentDailyAnswer updateStudentAnswer(HealthUserContext userContext, String studentDailyAnswerId, String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentAnswer(userContext, studentDailyAnswerId, studentAnswerId, studentAnswerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentAnswerList().searchStudentAnswerListWith(StudentAnswer.ID_PROPERTY, "eq", studentAnswerId).done();



		StudentDailyAnswer studentDailyAnswer = loadStudentDailyAnswer(userContext, studentDailyAnswerId, loadTokens);

		synchronized(studentDailyAnswer){
			//Will be good when the studentDailyAnswer loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//studentDailyAnswer.removeStudentAnswer( studentAnswer );
			//make changes to AcceleraterAccount.
			StudentAnswer studentAnswerIndex = createIndexedStudentAnswer(studentAnswerId, studentAnswerVersion);

			StudentAnswer studentAnswer = studentDailyAnswer.findTheStudentAnswer(studentAnswerIndex);
			if(studentAnswer == null){
				throw new StudentDailyAnswerManagerException(studentAnswer+" is NOT FOUND" );
			}

			studentAnswer.changeProperty(property, newValueExpr);
			
			studentDailyAnswer = saveStudentDailyAnswer(userContext, studentDailyAnswer, tokens().withStudentAnswerList().done());
			return present(userContext,studentDailyAnswer, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, StudentDailyAnswer newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, StudentDailyAnswer.INTERNAL_TYPE);
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


