
package com.doublechaintech.health.classquestion;

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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;

import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.questiontype.CandidateQuestionType;
import com.doublechaintech.health.wechatuser.CandidateWechatUser;
import com.doublechaintech.health.questionsource.CandidateQuestionSource;

import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classquestion.ClassQuestion;






public class ClassQuestionManagerImpl extends CustomHealthCheckerManager implements ClassQuestionManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "ClassQuestion";
	@Override
	public ClassQuestionDAO daoOf(HealthUserContext userContext) {
		return classQuestionDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ClassQuestionManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ClassQuestionManagerException(message);

	}



 	protected ClassQuestion saveClassQuestion(HealthUserContext userContext, ClassQuestion classQuestion, String [] tokensExpr) throws Exception{	
 		//return getClassQuestionDAO().save(classQuestion, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveClassQuestion(userContext, classQuestion, tokens);
 	}
 	
 	protected ClassQuestion saveClassQuestionDetail(HealthUserContext userContext, ClassQuestion classQuestion) throws Exception{	

 		
 		return saveClassQuestion(userContext, classQuestion, allTokens());
 	}
 	
 	public ClassQuestion loadClassQuestion(HealthUserContext userContext, String classQuestionId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).throwExceptionIfHasErrors( ClassQuestionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ClassQuestion classQuestion = loadClassQuestion( userContext, classQuestionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,classQuestion, tokens);
 	}
 	
 	
 	 public ClassQuestion searchClassQuestion(HealthUserContext userContext, String classQuestionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).throwExceptionIfHasErrors( ClassQuestionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ClassQuestion classQuestion = loadClassQuestion( userContext, classQuestionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,classQuestion, tokens);
 	}
 	
 	

 	protected ClassQuestion present(HealthUserContext userContext, ClassQuestion classQuestion, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,classQuestion,tokens);
		
		
		ClassQuestion  classQuestionToPresent = classQuestionDaoOf(userContext).present(classQuestion, tokens);
		
		List<BaseEntity> entityListToNaming = classQuestionToPresent.collectRefercencesFromLists();
		classQuestionDaoOf(userContext).alias(entityListToNaming);
		
		return  classQuestionToPresent;
		
		
	}
 
 	
 	
 	public ClassQuestion loadClassQuestionDetail(HealthUserContext userContext, String classQuestionId) throws Exception{	
 		ClassQuestion classQuestion = loadClassQuestion( userContext, classQuestionId, allTokens());
 		return present(userContext,classQuestion, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String classQuestionId) throws Exception{	
 		ClassQuestion classQuestion = loadClassQuestion( userContext, classQuestionId, viewTokens());
 		return present(userContext,classQuestion, allTokens());
		
 	}
 	protected ClassQuestion saveClassQuestion(HealthUserContext userContext, ClassQuestion classQuestion, Map<String,Object>tokens) throws Exception{	
 		return classQuestionDaoOf(userContext).save(classQuestion, tokens);
 	}
 	protected ClassQuestion loadClassQuestion(HealthUserContext userContext, String classQuestionId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).throwExceptionIfHasErrors( ClassQuestionManagerException.class);

 
 		return classQuestionDaoOf(userContext).load(classQuestionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ClassQuestion classQuestion, Map<String, Object> tokens){
		super.addActions(userContext, classQuestion, tokens);
		
		addAction(userContext, classQuestion, tokens,"@create","createClassQuestion","createClassQuestion/","main","primary");
		addAction(userContext, classQuestion, tokens,"@update","updateClassQuestion","updateClassQuestion/"+classQuestion.getId()+"/","main","primary");
		addAction(userContext, classQuestion, tokens,"@copy","cloneClassQuestion","cloneClassQuestion/"+classQuestion.getId()+"/","main","primary");
		
		addAction(userContext, classQuestion, tokens,"class_question.transfer_to_question_type","transferToAnotherQuestionType","transferToAnotherQuestionType/"+classQuestion.getId()+"/","main","primary");
		addAction(userContext, classQuestion, tokens,"class_question.transfer_to_question_source","transferToAnotherQuestionSource","transferToAnotherQuestionSource/"+classQuestion.getId()+"/","main","primary");
		addAction(userContext, classQuestion, tokens,"class_question.transfer_to_creator","transferToAnotherCreator","transferToAnotherCreator/"+classQuestion.getId()+"/","main","primary");
		addAction(userContext, classQuestion, tokens,"class_question.transfer_to_cq","transferToAnotherCq","transferToAnotherCq/"+classQuestion.getId()+"/","main","primary");
		addAction(userContext, classQuestion, tokens,"class_question.addDailySurveyQuestion","addDailySurveyQuestion","addDailySurveyQuestion/"+classQuestion.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classQuestion, tokens,"class_question.removeDailySurveyQuestion","removeDailySurveyQuestion","removeDailySurveyQuestion/"+classQuestion.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classQuestion, tokens,"class_question.updateDailySurveyQuestion","updateDailySurveyQuestion","updateDailySurveyQuestion/"+classQuestion.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classQuestion, tokens,"class_question.copyDailySurveyQuestionFrom","copyDailySurveyQuestionFrom","copyDailySurveyQuestionFrom/"+classQuestion.getId()+"/","dailySurveyQuestionList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ClassQuestion classQuestion, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ClassQuestion createClassQuestion(HealthUserContext userContext, String topic,String questionTypeId,String optionA,String optionB,String optionC,String optionD,String questionSourceId,String creatorId,String cqId) throws Exception
	//public ClassQuestion createClassQuestion(HealthUserContext userContext,String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId, String cqId) throws Exception
	{

		

		

		checkerOf(userContext).checkTopicOfClassQuestion(topic);
		checkerOf(userContext).checkOptionAOfClassQuestion(optionA);
		checkerOf(userContext).checkOptionBOfClassQuestion(optionB);
		checkerOf(userContext).checkOptionCOfClassQuestion(optionC);
		checkerOf(userContext).checkOptionDOfClassQuestion(optionD);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);


		ClassQuestion classQuestion=createNewClassQuestion();	

		classQuestion.setTopic(topic);
			
		QuestionType questionType = loadQuestionType(userContext, questionTypeId,emptyOptions());
		classQuestion.setQuestionType(questionType);
		
		
		classQuestion.setOptionA(optionA);
		classQuestion.setOptionB(optionB);
		classQuestion.setOptionC(optionC);
		classQuestion.setOptionD(optionD);
			
		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId,emptyOptions());
		classQuestion.setQuestionSource(questionSource);
		
		
		if(isValidIdentifier(creatorId)){	
			WechatUser creator = loadWechatUser(userContext, creatorId,emptyOptions());
			classQuestion.setCreator(creator);
		}
		
			
		ChangeRequest cq = loadChangeRequest(userContext, cqId,emptyOptions());
		classQuestion.setCq(cq);
		
		

		classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());
		
		onNewInstanceCreated(userContext, classQuestion);
		return classQuestion;


	}
	protected ClassQuestion createNewClassQuestion()
	{

		return new ClassQuestion();
	}

	protected void checkParamsForUpdatingClassQuestion(HealthUserContext userContext,String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion( classQuestionVersion);
		

		if(ClassQuestion.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfClassQuestion(parseString(newValueExpr));
		}		

		
		if(ClassQuestion.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfClassQuestion(parseString(newValueExpr));
		}
		if(ClassQuestion.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfClassQuestion(parseString(newValueExpr));
		}
		if(ClassQuestion.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfClassQuestion(parseString(newValueExpr));
		}
		if(ClassQuestion.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfClassQuestion(parseString(newValueExpr));
		}		

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);


	}



	public ClassQuestion clone(HealthUserContext userContext, String fromClassQuestionId) throws Exception{

		return classQuestionDaoOf(userContext).clone(fromClassQuestionId, this.allTokens());
	}

	public ClassQuestion internalSaveClassQuestion(HealthUserContext userContext, ClassQuestion classQuestion) throws Exception
	{
		return internalSaveClassQuestion(userContext, classQuestion, allTokens());

	}
	public ClassQuestion internalSaveClassQuestion(HealthUserContext userContext, ClassQuestion classQuestion, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingClassQuestion(userContext, classQuestionId, classQuestionVersion, property, newValueExpr, tokensExpr);


		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ClassQuestion.
			if (classQuestion.isChanged()){
			
			}
			classQuestion = saveClassQuestion(userContext, classQuestion, options);
			return classQuestion;

		}

	}

	public ClassQuestion updateClassQuestion(HealthUserContext userContext,String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassQuestion(userContext, classQuestionId, classQuestionVersion, property, newValueExpr, tokensExpr);



		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
		if(classQuestion.getVersion() != classQuestionVersion){
			String message = "The target version("+classQuestion.getVersion()+") is not equals to version("+classQuestionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ClassQuestion.
			
			classQuestion.changeProperty(property, newValueExpr);
			classQuestion = saveClassQuestion(userContext, classQuestion, tokens().done());
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
			//return saveClassQuestion(userContext, classQuestion, tokens().done());
		}

	}

	public ClassQuestion updateClassQuestionProperty(HealthUserContext userContext,String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassQuestion(userContext, classQuestionId, classQuestionVersion, property, newValueExpr, tokensExpr);

		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
		if(classQuestion.getVersion() != classQuestionVersion){
			String message = "The target version("+classQuestion.getVersion()+") is not equals to version("+classQuestionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ClassQuestion.

			classQuestion.changeProperty(property, newValueExpr);
			
			classQuestion = saveClassQuestion(userContext, classQuestion, tokens().done());
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
			//return saveClassQuestion(userContext, classQuestion, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ClassQuestionTokens tokens(){
		return ClassQuestionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ClassQuestionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortDailySurveyQuestionListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ClassQuestionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherQuestionType(HealthUserContext userContext, String classQuestionId, String anotherQuestionTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
 		checkerOf(userContext).checkIdOfQuestionType(anotherQuestionTypeId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

 	}
 	public ClassQuestion transferToAnotherQuestionType(HealthUserContext userContext, String classQuestionId, String anotherQuestionTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionType(userContext, classQuestionId,anotherQuestionTypeId);
 
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());	
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionType questionType = loadQuestionType(userContext, anotherQuestionTypeId, emptyOptions());		
			classQuestion.updateQuestionType(questionType);		
			classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());
			
			return present(userContext,classQuestion, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherQuestionTypeWithCode(HealthUserContext userContext, String classQuestionId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
 		checkerOf(userContext).checkCodeOfQuestionType( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

 	}

 	public ClassQuestion transferToAnotherQuestionTypeWithCode(HealthUserContext userContext, String classQuestionId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionTypeWithCode(userContext, classQuestionId,anotherCode);
 		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionType questionType = loadQuestionTypeWithCode(userContext, anotherCode, emptyOptions());
			classQuestion.updateQuestionType(questionType);
			classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());

			return present(userContext,classQuestion, allTokens());

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
		SmartList<QuestionType> candidateList = questionTypeDaoOf(userContext).requestCandidateQuestionTypeForClassQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherQuestionSource(HealthUserContext userContext, String classQuestionId, String anotherQuestionSourceId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
 		checkerOf(userContext).checkIdOfQuestionSource(anotherQuestionSourceId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

 	}
 	public ClassQuestion transferToAnotherQuestionSource(HealthUserContext userContext, String classQuestionId, String anotherQuestionSourceId) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionSource(userContext, classQuestionId,anotherQuestionSourceId);
 
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());	
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionSource questionSource = loadQuestionSource(userContext, anotherQuestionSourceId, emptyOptions());		
			classQuestion.updateQuestionSource(questionSource);		
			classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());
			
			return present(userContext,classQuestion, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherQuestionSourceWithCode(HealthUserContext userContext, String classQuestionId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
 		checkerOf(userContext).checkCodeOfQuestionSource( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

 	}

 	public ClassQuestion transferToAnotherQuestionSourceWithCode(HealthUserContext userContext, String classQuestionId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherQuestionSourceWithCode(userContext, classQuestionId,anotherCode);
 		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			QuestionSource questionSource = loadQuestionSourceWithCode(userContext, anotherCode, emptyOptions());
			classQuestion.updateQuestionSource(questionSource);
			classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());

			return present(userContext,classQuestion, allTokens());

		}
 	}

	 


	public CandidateQuestionSource requestCandidateQuestionSource(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateQuestionSource result = new CandidateQuestionSource();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<QuestionSource> candidateList = questionSourceDaoOf(userContext).requestCandidateQuestionSourceForClassQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCreator(HealthUserContext userContext, String classQuestionId, String anotherCreatorId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
 		checkerOf(userContext).checkIdOfWechatUser(anotherCreatorId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

 	}
 	public ClassQuestion transferToAnotherCreator(HealthUserContext userContext, String classQuestionId, String anotherCreatorId) throws Exception
 	{
 		checkParamsForTransferingAnotherCreator(userContext, classQuestionId,anotherCreatorId);
 
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());	
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			WechatUser creator = loadWechatUser(userContext, anotherCreatorId, emptyOptions());		
			classQuestion.updateCreator(creator);		
			classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());
			
			return present(userContext,classQuestion, allTokens());
			
		}

 	}

	


	public CandidateWechatUser requestCandidateCreator(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateWechatUser result = new CandidateWechatUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<WechatUser> candidateList = wechatUserDaoOf(userContext).requestCandidateWechatUserForClassQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCq(HealthUserContext userContext, String classQuestionId, String anotherCqId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherCqId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

 	}
 	public ClassQuestion transferToAnotherCq(HealthUserContext userContext, String classQuestionId, String anotherCqId) throws Exception
 	{
 		checkParamsForTransferingAnotherCq(userContext, classQuestionId,anotherCqId);
 
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());	
		synchronized(classQuestion){
			//will be good when the classQuestion loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest cq = loadChangeRequest(userContext, anotherCqId, emptyOptions());		
			classQuestion.updateCq(cq);		
			classQuestion = saveClassQuestion(userContext, classQuestion, emptyOptions());
			
			return present(userContext,classQuestion, allTokens());
			
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
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForClassQuestion(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected QuestionSource loadQuestionSource(HealthUserContext userContext, String newQuestionSourceId, Map<String,Object> options) throws Exception
 	{

 		return questionSourceDaoOf(userContext).load(newQuestionSourceId, options);
 	}
 	
 	protected QuestionSource loadQuestionSourceWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return questionSourceDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected QuestionType loadQuestionType(HealthUserContext userContext, String newQuestionTypeId, Map<String,Object> options) throws Exception
 	{

 		return questionTypeDaoOf(userContext).load(newQuestionTypeId, options);
 	}
 	
 	protected QuestionType loadQuestionTypeWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return questionTypeDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected WechatUser loadWechatUser(HealthUserContext userContext, String newCreatorId, Map<String,Object> options) throws Exception
 	{

 		return wechatUserDaoOf(userContext).load(newCreatorId, options);
 	}
 	


	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newCqId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newCqId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String classQuestionId, int classQuestionVersion) throws Exception {
		//deleteInternal(userContext, classQuestionId, classQuestionVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String classQuestionId, int classQuestionVersion) throws Exception{

		classQuestionDaoOf(userContext).delete(classQuestionId, classQuestionVersion);
	}

	public ClassQuestion forgetByAll(HealthUserContext userContext, String classQuestionId, int classQuestionVersion) throws Exception {
		return forgetByAllInternal(userContext, classQuestionId, classQuestionVersion);
	}
	protected ClassQuestion forgetByAllInternal(HealthUserContext userContext,
			String classQuestionId, int classQuestionVersion) throws Exception{

		return classQuestionDaoOf(userContext).disconnectFromAll(classQuestionId, classQuestionVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ClassQuestionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return classQuestionDaoOf(userContext).deleteAll();
	}


	//disconnect ClassQuestion with question_type in DailySurveyQuestion
	protected ClassQuestion breakWithDailySurveyQuestionByQuestionType(HealthUserContext userContext, String classQuestionId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());

			synchronized(classQuestion){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classQuestionDaoOf(userContext).planToRemoveDailySurveyQuestionListWithQuestionType(classQuestion, questionTypeId, this.emptyOptions());

				classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
				return classQuestion;
			}
	}
	//disconnect ClassQuestion with class_daily_health_survey in DailySurveyQuestion
	protected ClassQuestion breakWithDailySurveyQuestionByClassDailyHealthSurvey(HealthUserContext userContext, String classQuestionId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());

			synchronized(classQuestion){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classQuestionDaoOf(userContext).planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(classQuestion, classDailyHealthSurveyId, this.emptyOptions());

				classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
				return classQuestion;
			}
	}






	protected void checkParamsForAddingDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);

		
		checkerOf(userContext).checkTopicOfDailySurveyQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfDailySurveyQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion(optionD);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfDailySurveyQuestion(classDailyHealthSurveyId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);


	}
	public  ClassQuestion addDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingDailySurveyQuestion(userContext,classQuestionId,topic, questionTypeId, optionA, optionB, optionC, optionD, classDailyHealthSurveyId,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createDailySurveyQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD, classDailyHealthSurveyId);

		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, emptyOptions());
		synchronized(classQuestion){
			//Will be good when the classQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			classQuestion.addDailySurveyQuestion( dailySurveyQuestion );
			classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
			
			userContext.getManagerGroup().getDailySurveyQuestionManager().onNewInstanceCreated(userContext, dailySurveyQuestion);
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingDailySurveyQuestionProperties(HealthUserContext userContext, String classQuestionId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(id);

		checkerOf(userContext).checkTopicOfDailySurveyQuestion( topic);
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion( optionA);
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion( optionB);
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion( optionC);
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

	}
	public  ClassQuestion updateDailySurveyQuestionProperties(HealthUserContext userContext, String classQuestionId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDailySurveyQuestionProperties(userContext,classQuestionId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withDailySurveyQuestionListList()
				.searchDailySurveyQuestionListWith(DailySurveyQuestion.ID_PROPERTY, "is", id).done();

		ClassQuestion classQuestionToUpdate = loadClassQuestion(userContext, classQuestionId, options);

		if(classQuestionToUpdate.getDailySurveyQuestionList().isEmpty()){
			throw new ClassQuestionManagerException("DailySurveyQuestion is NOT FOUND with id: '"+id+"'");
		}

		DailySurveyQuestion item = classQuestionToUpdate.getDailySurveyQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingDailySurveyQuestion(userContext,classQuestionId,name, code, used,tokensExpr);
		ClassQuestion classQuestion = saveClassQuestion(userContext, classQuestionToUpdate, tokens().withDailySurveyQuestionList().done());
		synchronized(classQuestion){
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
		}
	}


	protected DailySurveyQuestion createDailySurveyQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId) throws Exception{

		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		
		
		dailySurveyQuestion.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		dailySurveyQuestion.setQuestionType(questionType);		
		dailySurveyQuestion.setOptionA(optionA);		
		dailySurveyQuestion.setOptionB(optionB);		
		dailySurveyQuestion.setOptionC(optionC);		
		dailySurveyQuestion.setOptionD(optionD);		
		ClassDailyHealthSurvey  classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(classDailyHealthSurveyId);		
		dailySurveyQuestion.setClassDailyHealthSurvey(classDailyHealthSurvey);
	
		
		return dailySurveyQuestion;


	}

	protected DailySurveyQuestion createIndexedDailySurveyQuestion(String id, int version){

		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		dailySurveyQuestion.setId(id);
		dailySurveyQuestion.setVersion(version);
		return dailySurveyQuestion;

	}

	protected void checkParamsForRemovingDailySurveyQuestionList(HealthUserContext userContext, String classQuestionId,
			String dailySurveyQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		for(String dailySurveyQuestionIdItem: dailySurveyQuestionIds){
			checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

	}
	public  ClassQuestion removeDailySurveyQuestionList(HealthUserContext userContext, String classQuestionId,
			String dailySurveyQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingDailySurveyQuestionList(userContext, classQuestionId,  dailySurveyQuestionIds, tokensExpr);


			ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
			synchronized(classQuestion){
				//Will be good when the classQuestion loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				classQuestionDaoOf(userContext).planToRemoveDailySurveyQuestionList(classQuestion, dailySurveyQuestionIds, allTokens());
				classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
				deleteRelationListInGraph(userContext, classQuestion.getDailySurveyQuestionList());
				return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingDailySurveyQuestion(HealthUserContext userContext, String classQuestionId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfClassQuestion( classQuestionId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

	}
	public  ClassQuestion removeDailySurveyQuestion(HealthUserContext userContext, String classQuestionId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingDailySurveyQuestion(userContext,classQuestionId, dailySurveyQuestionId, dailySurveyQuestionVersion,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
		synchronized(classQuestion){
			//Will be good when the classQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			classQuestion.removeDailySurveyQuestion( dailySurveyQuestion );
			classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
			deleteRelationInGraph(userContext, dailySurveyQuestion);
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingDailySurveyQuestion(HealthUserContext userContext, String classQuestionId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfClassQuestion( classQuestionId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

	}
	public  ClassQuestion copyDailySurveyQuestionFrom(HealthUserContext userContext, String classQuestionId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingDailySurveyQuestion(userContext,classQuestionId, dailySurveyQuestionId, dailySurveyQuestionVersion,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);
		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, allTokens());
		synchronized(classQuestion){
			//Will be good when the classQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			classQuestion.copyDailySurveyQuestionFrom( dailySurveyQuestion );
			classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
			
			userContext.getManagerGroup().getDailySurveyQuestionManager().onNewInstanceCreated(userContext, (DailySurveyQuestion)classQuestion.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		

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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassQuestionManagerException.class);

	}

	public  ClassQuestion updateDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingDailySurveyQuestion(userContext, classQuestionId, dailySurveyQuestionId, dailySurveyQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withDailySurveyQuestionList().searchDailySurveyQuestionListWith(DailySurveyQuestion.ID_PROPERTY, "eq", dailySurveyQuestionId).done();



		ClassQuestion classQuestion = loadClassQuestion(userContext, classQuestionId, loadTokens);

		synchronized(classQuestion){
			//Will be good when the classQuestion loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//classQuestion.removeDailySurveyQuestion( dailySurveyQuestion );
			//make changes to AcceleraterAccount.
			DailySurveyQuestion dailySurveyQuestionIndex = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);

			DailySurveyQuestion dailySurveyQuestion = classQuestion.findTheDailySurveyQuestion(dailySurveyQuestionIndex);
			if(dailySurveyQuestion == null){
				throw new ClassQuestionManagerException(dailySurveyQuestion+" is NOT FOUND" );
			}

			dailySurveyQuestion.changeProperty(property, newValueExpr);
			
			classQuestion = saveClassQuestion(userContext, classQuestion, tokens().withDailySurveyQuestionList().done());
			return present(userContext,classQuestion, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, ClassQuestion newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ClassQuestion.INTERNAL_TYPE);
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


