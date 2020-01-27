
package com.doublechaintech.health.dailysurveyquestion;

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


import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.questiontype.CandidateQuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.CandidateClassDailyHealthSurvey;
import com.doublechaintech.health.classquestion.CandidateClassQuestion;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;






public class DailySurveyQuestionManagerImpl extends CustomHealthCheckerManager implements DailySurveyQuestionManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "DailySurveyQuestion";
	@Override
	public DailySurveyQuestionDAO daoOf(HealthUserContext userContext) {
		return dailySurveyQuestionDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws DailySurveyQuestionManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new DailySurveyQuestionManagerException(message);

	}



 	protected DailySurveyQuestion saveDailySurveyQuestion(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion, String [] tokensExpr) throws Exception{	
 		//return getDailySurveyQuestionDAO().save(dailySurveyQuestion, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens);
 	}
 	
 	protected DailySurveyQuestion saveDailySurveyQuestionDetail(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion) throws Exception{	

 		
 		return saveDailySurveyQuestion(userContext, dailySurveyQuestion, allTokens());
 	}
 	
 	public DailySurveyQuestion loadDailySurveyQuestion(HealthUserContext userContext, String dailySurveyQuestionId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).throwExceptionIfHasErrors( DailySurveyQuestionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion( userContext, dailySurveyQuestionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,dailySurveyQuestion, tokens);
 	}
 	
 	
 	 public DailySurveyQuestion searchDailySurveyQuestion(HealthUserContext userContext, String dailySurveyQuestionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).throwExceptionIfHasErrors( DailySurveyQuestionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion( userContext, dailySurveyQuestionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,dailySurveyQuestion, tokens);
 	}
 	
 	

 	protected DailySurveyQuestion present(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,dailySurveyQuestion,tokens);
		
		
		DailySurveyQuestion  dailySurveyQuestionToPresent = dailySurveyQuestionDaoOf(userContext).present(dailySurveyQuestion, tokens);
		
		List<BaseEntity> entityListToNaming = dailySurveyQuestionToPresent.collectRefercencesFromLists();
		dailySurveyQuestionDaoOf(userContext).alias(entityListToNaming);
		
		return  dailySurveyQuestionToPresent;
		
		
	}
 
 	
 	
 	public DailySurveyQuestion loadDailySurveyQuestionDetail(HealthUserContext userContext, String dailySurveyQuestionId) throws Exception{	
 		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion( userContext, dailySurveyQuestionId, allTokens());
 		return present(userContext,dailySurveyQuestion, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String dailySurveyQuestionId) throws Exception{	
 		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion( userContext, dailySurveyQuestionId, viewTokens());
 		return present(userContext,dailySurveyQuestion, allTokens());
		
 	}
 	protected DailySurveyQuestion saveDailySurveyQuestion(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion, Map<String,Object>tokens) throws Exception{	
 		return dailySurveyQuestionDaoOf(userContext).save(dailySurveyQuestion, tokens);
 	}
 	protected DailySurveyQuestion loadDailySurveyQuestion(HealthUserContext userContext, String dailySurveyQuestionId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).throwExceptionIfHasErrors( DailySurveyQuestionManagerException.class);

 
 		return dailySurveyQuestionDaoOf(userContext).load(dailySurveyQuestionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion, Map<String, Object> tokens){
		super.addActions(userContext, dailySurveyQuestion, tokens);
		
		addAction(userContext, dailySurveyQuestion, tokens,"@create","createDailySurveyQuestion","createDailySurveyQuestion/","main","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"@update","updateDailySurveyQuestion","updateDailySurveyQuestion/"+dailySurveyQuestion.getId()+"/","main","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"@copy","cloneDailySurveyQuestion","cloneDailySurveyQuestion/"+dailySurveyQuestion.getId()+"/","main","primary");
		
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.transfer_to_question_type","transferToAnotherQuestionType","transferToAnotherQuestionType/"+dailySurveyQuestion.getId()+"/","main","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.transfer_to_class_daily_health_survey","transferToAnotherClassDailyHealthSurvey","transferToAnotherClassDailyHealthSurvey/"+dailySurveyQuestion.getId()+"/","main","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.transfer_to_class_question","transferToAnotherClassQuestion","transferToAnotherClassQuestion/"+dailySurveyQuestion.getId()+"/","main","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.addStudentDailyAnswer","addStudentDailyAnswer","addStudentDailyAnswer/"+dailySurveyQuestion.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.removeStudentDailyAnswer","removeStudentDailyAnswer","removeStudentDailyAnswer/"+dailySurveyQuestion.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.updateStudentDailyAnswer","updateStudentDailyAnswer","updateStudentDailyAnswer/"+dailySurveyQuestion.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, dailySurveyQuestion, tokens,"daily_survey_question.copyStudentDailyAnswerFrom","copyStudentDailyAnswerFrom","copyStudentDailyAnswerFrom/"+dailySurveyQuestion.getId()+"/","studentDailyAnswerList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public DailySurveyQuestion createDailySurveyQuestion(HealthUserContext userContext, String topic,String questionTypeId,String optionA,String optionB,String optionC,String optionD,String classDailyHealthSurveyId,String classQuestionId) throws Exception
	//public DailySurveyQuestion createDailySurveyQuestion(HealthUserContext userContext,String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String classQuestionId) throws Exception
	{

		

		

		checkerOf(userContext).checkTopicOfDailySurveyQuestion(topic);
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion(optionA);
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion(optionB);
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion(optionC);
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion(optionD);
	
		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);


		DailySurveyQuestion dailySurveyQuestion=createNewDailySurveyQuestion();	

		dailySurveyQuestion.setTopic(topic);
			
		QuestionType questionType = loadQuestionType(userContext, questionTypeId,emptyOptions());
		dailySurveyQuestion.setQuestionType(questionType);
		
		
		dailySurveyQuestion.setOptionA(optionA);
		dailySurveyQuestion.setOptionB(optionB);
		dailySurveyQuestion.setOptionC(optionC);
		dailySurveyQuestion.setOptionD(optionD);
			
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId,emptyOptions());
		dailySurveyQuestion.setClassDailyHealthSurvey(classDailyHealthSurvey);
		
		
			
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId,emptyOptions());
		dailySurveyQuestion.setClassQuestion(classQuestion);
		
		

		dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, emptyOptions());
		
		onNewInstanceCreated(userContext, dailySurveyQuestion);
		return dailySurveyQuestion;


	}
	protected DailySurveyQuestion createNewDailySurveyQuestion()
	{

		return new DailySurveyQuestion();
	}

	protected void checkParamsForUpdatingDailySurveyQuestion(HealthUserContext userContext,String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion( dailySurveyQuestionVersion);
		

		if(DailySurveyQuestion.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfDailySurveyQuestion(parseString(newValueExpr));
		}		

		
		if(DailySurveyQuestion.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfDailySurveyQuestion(parseString(newValueExpr));
		}
		if(DailySurveyQuestion.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfDailySurveyQuestion(parseString(newValueExpr));
		}
		if(DailySurveyQuestion.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfDailySurveyQuestion(parseString(newValueExpr));
		}
		if(DailySurveyQuestion.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfDailySurveyQuestion(parseString(newValueExpr));
		}		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);


	}



	public DailySurveyQuestion clone(HealthUserContext userContext, String fromDailySurveyQuestionId) throws Exception{

		return dailySurveyQuestionDaoOf(userContext).clone(fromDailySurveyQuestionId, this.allTokens());
	}

	public DailySurveyQuestion internalSaveDailySurveyQuestion(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion) throws Exception
	{
		return internalSaveDailySurveyQuestion(userContext, dailySurveyQuestion, allTokens());

	}
	public DailySurveyQuestion internalSaveDailySurveyQuestion(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingDailySurveyQuestion(userContext, dailySurveyQuestionId, dailySurveyQuestionVersion, property, newValueExpr, tokensExpr);


		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to DailySurveyQuestion.
			if (dailySurveyQuestion.isChanged()){
			
			}
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, options);
			return dailySurveyQuestion;

		}

	}

	public DailySurveyQuestion updateDailySurveyQuestion(HealthUserContext userContext,String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDailySurveyQuestion(userContext, dailySurveyQuestionId, dailySurveyQuestionVersion, property, newValueExpr, tokensExpr);



		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());
		if(dailySurveyQuestion.getVersion() != dailySurveyQuestionVersion){
			String message = "The target version("+dailySurveyQuestion.getVersion()+") is not equals to version("+dailySurveyQuestionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to DailySurveyQuestion.
			
			dailySurveyQuestion.changeProperty(property, newValueExpr);
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().done());
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
			//return saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().done());
		}

	}

	public DailySurveyQuestion updateDailySurveyQuestionProperty(HealthUserContext userContext,String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDailySurveyQuestion(userContext, dailySurveyQuestionId, dailySurveyQuestionVersion, property, newValueExpr, tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());
		if(dailySurveyQuestion.getVersion() != dailySurveyQuestionVersion){
			String message = "The target version("+dailySurveyQuestion.getVersion()+") is not equals to version("+dailySurveyQuestionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to DailySurveyQuestion.

			dailySurveyQuestion.changeProperty(property, newValueExpr);
			
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().done());
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
			//return saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected DailySurveyQuestionTokens tokens(){
		return DailySurveyQuestionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return DailySurveyQuestionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentDailyAnswerListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return DailySurveyQuestionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherQuestionType(HealthUserContext userContext, String dailySurveyQuestionId, String anotherQuestionTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
 		checkerOf(userContext).checkIdOfQuestionType(anotherQuestionTypeId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

 	}
 	public DailySurveyQuestion transferToAnotherQuestionType(HealthUserContext userContext, String dailySurveyQuestionId, String anotherQuestionTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionType(userContext, dailySurveyQuestionId,anotherQuestionTypeId);
 
		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());	
		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionType questionType = loadQuestionType(userContext, anotherQuestionTypeId, emptyOptions());		
			dailySurveyQuestion.updateQuestionType(questionType);		
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, emptyOptions());
			
			return present(userContext,dailySurveyQuestion, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherQuestionTypeWithCode(HealthUserContext userContext, String dailySurveyQuestionId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
 		checkerOf(userContext).checkCodeOfQuestionType( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

 	}

 	public DailySurveyQuestion transferToAnotherQuestionTypeWithCode(HealthUserContext userContext, String dailySurveyQuestionId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionTypeWithCode(userContext, dailySurveyQuestionId,anotherCode);
 		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());
		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionType questionType = loadQuestionTypeWithCode(userContext, anotherCode, emptyOptions());
			dailySurveyQuestion.updateQuestionType(questionType);
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, emptyOptions());

			return present(userContext,dailySurveyQuestion, allTokens());

		}
 	}

	 


	public CandidateQuestionType requestCandidateQuestionType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateQuestionType result = new CandidateQuestionType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<QuestionType> candidateList = questionTypeDaoOf(userContext).requestCandidateQuestionTypeForDailySurveyQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherClassDailyHealthSurvey(HealthUserContext userContext, String dailySurveyQuestionId, String anotherClassDailyHealthSurveyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(anotherClassDailyHealthSurveyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

 	}
 	public DailySurveyQuestion transferToAnotherClassDailyHealthSurvey(HealthUserContext userContext, String dailySurveyQuestionId, String anotherClassDailyHealthSurveyId) throws Exception
 	{
 		checkParamsForTransferingAnotherClassDailyHealthSurvey(userContext, dailySurveyQuestionId,anotherClassDailyHealthSurveyId);
 
		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());	
		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, anotherClassDailyHealthSurveyId, emptyOptions());		
			dailySurveyQuestion.updateClassDailyHealthSurvey(classDailyHealthSurvey);		
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, emptyOptions());
			
			return present(userContext,dailySurveyQuestion, allTokens());
			
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
		SmartList<ClassDailyHealthSurvey> candidateList = classDailyHealthSurveyDaoOf(userContext).requestCandidateClassDailyHealthSurveyForDailySurveyQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherClassQuestion(HealthUserContext userContext, String dailySurveyQuestionId, String anotherClassQuestionId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
 		checkerOf(userContext).checkIdOfClassQuestion(anotherClassQuestionId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

 	}
 	public DailySurveyQuestion transferToAnotherClassQuestion(HealthUserContext userContext, String dailySurveyQuestionId, String anotherClassQuestionId) throws Exception
 	{
 		checkParamsForTransferingAnotherClassQuestion(userContext, dailySurveyQuestionId,anotherClassQuestionId);
 
		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());	
		synchronized(dailySurveyQuestion){
			//will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ClassQuestion classQuestion = loadClassQuestion(userContext, anotherClassQuestionId, emptyOptions());		
			dailySurveyQuestion.updateClassQuestion(classQuestion);		
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, emptyOptions());
			
			return present(userContext,dailySurveyQuestion, allTokens());
			
		}

 	}

	


	public CandidateClassQuestion requestCandidateClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateClassQuestion result = new CandidateClassQuestion();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("topic");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ClassQuestion> candidateList = classQuestionDaoOf(userContext).requestCandidateClassQuestionForDailySurveyQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected QuestionType loadQuestionType(HealthUserContext userContext, String newQuestionTypeId, Map<String,Object> options) throws Exception
 	{

 		return questionTypeDaoOf(userContext).load(newQuestionTypeId, options);
 	}
 	
 	protected QuestionType loadQuestionTypeWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return questionTypeDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected ClassDailyHealthSurvey loadClassDailyHealthSurvey(HealthUserContext userContext, String newClassDailyHealthSurveyId, Map<String,Object> options) throws Exception
 	{

 		return classDailyHealthSurveyDaoOf(userContext).load(newClassDailyHealthSurveyId, options);
 	}
 	


	

 	protected ClassQuestion loadClassQuestion(HealthUserContext userContext, String newClassQuestionId, Map<String,Object> options) throws Exception
 	{

 		return classQuestionDaoOf(userContext).load(newClassQuestionId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String dailySurveyQuestionId, int dailySurveyQuestionVersion) throws Exception {
		//deleteInternal(userContext, dailySurveyQuestionId, dailySurveyQuestionVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String dailySurveyQuestionId, int dailySurveyQuestionVersion) throws Exception{

		dailySurveyQuestionDaoOf(userContext).delete(dailySurveyQuestionId, dailySurveyQuestionVersion);
	}

	public DailySurveyQuestion forgetByAll(HealthUserContext userContext, String dailySurveyQuestionId, int dailySurveyQuestionVersion) throws Exception {
		return forgetByAllInternal(userContext, dailySurveyQuestionId, dailySurveyQuestionVersion);
	}
	protected DailySurveyQuestion forgetByAllInternal(HealthUserContext userContext,
			String dailySurveyQuestionId, int dailySurveyQuestionVersion) throws Exception{

		return dailySurveyQuestionDaoOf(userContext).disconnectFromAll(dailySurveyQuestionId, dailySurveyQuestionVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new DailySurveyQuestionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return dailySurveyQuestionDaoOf(userContext).deleteAll();
	}


	//disconnect DailySurveyQuestion with student_health_survey in StudentDailyAnswer
	protected DailySurveyQuestion breakWithStudentDailyAnswerByStudentHealthSurvey(HealthUserContext userContext, String dailySurveyQuestionId, String studentHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());

			synchronized(dailySurveyQuestion){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				dailySurveyQuestionDaoOf(userContext).planToRemoveStudentDailyAnswerListWithStudentHealthSurvey(dailySurveyQuestion, studentHealthSurveyId, this.emptyOptions());

				dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
				return dailySurveyQuestion;
			}
	}
	//disconnect DailySurveyQuestion with cq in StudentDailyAnswer
	protected DailySurveyQuestion breakWithStudentDailyAnswerByCq(HealthUserContext userContext, String dailySurveyQuestionId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());

			synchronized(dailySurveyQuestion){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				dailySurveyQuestionDaoOf(userContext).planToRemoveStudentDailyAnswerListWithCq(dailySurveyQuestion, cqId, this.emptyOptions());

				dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
				return dailySurveyQuestion;
			}
	}






	protected void checkParamsForAddingStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentHealthSurveyId, String answer, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);

		
		checkerOf(userContext).checkStudentHealthSurveyIdOfStudentDailyAnswer(studentHealthSurveyId);
		
		checkerOf(userContext).checkAnswerOfStudentDailyAnswer(answer);
		
		checkerOf(userContext).checkCqIdOfStudentDailyAnswer(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);


	}
	public  DailySurveyQuestion addStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentHealthSurveyId, String answer, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentDailyAnswer(userContext,dailySurveyQuestionId,studentHealthSurveyId, answer, cqId,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createStudentDailyAnswer(userContext,studentHealthSurveyId, answer, cqId);

		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, emptyOptions());
		synchronized(dailySurveyQuestion){
			//Will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			dailySurveyQuestion.addStudentDailyAnswer( studentDailyAnswer );
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
			
			userContext.getManagerGroup().getStudentDailyAnswerManager().onNewInstanceCreated(userContext, studentDailyAnswer);
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentDailyAnswerProperties(HealthUserContext userContext, String dailySurveyQuestionId,String id,String answer,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(id);

		checkerOf(userContext).checkAnswerOfStudentDailyAnswer( answer);

		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

	}
	public  DailySurveyQuestion updateStudentDailyAnswerProperties(HealthUserContext userContext, String dailySurveyQuestionId, String id,String answer, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentDailyAnswerProperties(userContext,dailySurveyQuestionId,id,answer,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentDailyAnswerListList()
				.searchStudentDailyAnswerListWith(StudentDailyAnswer.ID_PROPERTY, "is", id).done();

		DailySurveyQuestion dailySurveyQuestionToUpdate = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, options);

		if(dailySurveyQuestionToUpdate.getStudentDailyAnswerList().isEmpty()){
			throw new DailySurveyQuestionManagerException("StudentDailyAnswer is NOT FOUND with id: '"+id+"'");
		}

		StudentDailyAnswer item = dailySurveyQuestionToUpdate.getStudentDailyAnswerList().first();

		item.updateAnswer( answer );


		//checkParamsForAddingStudentDailyAnswer(userContext,dailySurveyQuestionId,name, code, used,tokensExpr);
		DailySurveyQuestion dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestionToUpdate, tokens().withStudentDailyAnswerList().done());
		synchronized(dailySurveyQuestion){
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentDailyAnswer createStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String answer, String cqId) throws Exception{

		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		
		
		StudentHealthSurvey  studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(studentHealthSurveyId);		
		studentDailyAnswer.setStudentHealthSurvey(studentHealthSurvey);		
		studentDailyAnswer.setAnswer(answer);		
		studentDailyAnswer.setCreateTime(userContext.now());		
		studentDailyAnswer.setLastUpdateTime(userContext.now());		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		studentDailyAnswer.setCq(cq);
	
		
		return studentDailyAnswer;


	}

	protected StudentDailyAnswer createIndexedStudentDailyAnswer(String id, int version){

		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		studentDailyAnswer.setId(id);
		studentDailyAnswer.setVersion(version);
		return studentDailyAnswer;

	}

	protected void checkParamsForRemovingStudentDailyAnswerList(HealthUserContext userContext, String dailySurveyQuestionId,
			String studentDailyAnswerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		for(String studentDailyAnswerIdItem: studentDailyAnswerIds){
			checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

	}
	public  DailySurveyQuestion removeStudentDailyAnswerList(HealthUserContext userContext, String dailySurveyQuestionId,
			String studentDailyAnswerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentDailyAnswerList(userContext, dailySurveyQuestionId,  studentDailyAnswerIds, tokensExpr);


			DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());
			synchronized(dailySurveyQuestion){
				//Will be good when the dailySurveyQuestion loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				dailySurveyQuestionDaoOf(userContext).planToRemoveStudentDailyAnswerList(dailySurveyQuestion, studentDailyAnswerIds, allTokens());
				dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
				deleteRelationListInGraph(userContext, dailySurveyQuestion.getStudentDailyAnswerList());
				return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfDailySurveyQuestion( dailySurveyQuestionId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

	}
	public  DailySurveyQuestion removeStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentDailyAnswer(userContext,dailySurveyQuestionId, studentDailyAnswerId, studentDailyAnswerVersion,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);
		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());
		synchronized(dailySurveyQuestion){
			//Will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			dailySurveyQuestion.removeStudentDailyAnswer( studentDailyAnswer );
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
			deleteRelationInGraph(userContext, studentDailyAnswer);
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfDailySurveyQuestion( dailySurveyQuestionId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

	}
	public  DailySurveyQuestion copyStudentDailyAnswerFrom(HealthUserContext userContext, String dailySurveyQuestionId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentDailyAnswer(userContext,dailySurveyQuestionId, studentDailyAnswerId, studentDailyAnswerVersion,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);
		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, allTokens());
		synchronized(dailySurveyQuestion){
			//Will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentDailyAnswer.updateLastUpdateTime(userContext.now());

			dailySurveyQuestion.copyStudentDailyAnswerFrom( studentDailyAnswer );
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
			
			userContext.getManagerGroup().getStudentDailyAnswerManager().onNewInstanceCreated(userContext, (StudentDailyAnswer)dailySurveyQuestion.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		

		if(StudentDailyAnswer.ANSWER_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerOfStudentDailyAnswer(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(DailySurveyQuestionManagerException.class);

	}

	public  DailySurveyQuestion updateStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentDailyAnswer(userContext, dailySurveyQuestionId, studentDailyAnswerId, studentDailyAnswerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentDailyAnswerList().searchStudentDailyAnswerListWith(StudentDailyAnswer.ID_PROPERTY, "eq", studentDailyAnswerId).done();



		DailySurveyQuestion dailySurveyQuestion = loadDailySurveyQuestion(userContext, dailySurveyQuestionId, loadTokens);

		synchronized(dailySurveyQuestion){
			//Will be good when the dailySurveyQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//dailySurveyQuestion.removeStudentDailyAnswer( studentDailyAnswer );
			//make changes to AcceleraterAccount.
			StudentDailyAnswer studentDailyAnswerIndex = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);

			StudentDailyAnswer studentDailyAnswer = dailySurveyQuestion.findTheStudentDailyAnswer(studentDailyAnswerIndex);
			if(studentDailyAnswer == null){
				throw new DailySurveyQuestionManagerException(studentDailyAnswer+" is NOT FOUND" );
			}

			studentDailyAnswer.changeProperty(property, newValueExpr);
			studentDailyAnswer.updateLastUpdateTime(userContext.now());
			dailySurveyQuestion = saveDailySurveyQuestion(userContext, dailySurveyQuestion, tokens().withStudentDailyAnswerList().done());
			return present(userContext,dailySurveyQuestion, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, DailySurveyQuestion newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, DailySurveyQuestion.INTERNAL_TYPE);
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


