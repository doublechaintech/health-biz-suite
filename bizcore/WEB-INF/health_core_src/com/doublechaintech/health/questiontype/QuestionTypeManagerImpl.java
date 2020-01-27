
package com.doublechaintech.health.questiontype;

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


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.platform.CandidatePlatform;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.questionsource.QuestionSource;






public class QuestionTypeManagerImpl extends CustomHealthCheckerManager implements QuestionTypeManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "QuestionType";
	@Override
	public QuestionTypeDAO daoOf(HealthUserContext userContext) {
		return questionTypeDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws QuestionTypeManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new QuestionTypeManagerException(message);

	}



 	protected QuestionType saveQuestionType(HealthUserContext userContext, QuestionType questionType, String [] tokensExpr) throws Exception{	
 		//return getQuestionTypeDAO().save(questionType, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveQuestionType(userContext, questionType, tokens);
 	}
 	
 	protected QuestionType saveQuestionTypeDetail(HealthUserContext userContext, QuestionType questionType) throws Exception{	

 		
 		return saveQuestionType(userContext, questionType, allTokens());
 	}
 	
 	public QuestionType loadQuestionType(HealthUserContext userContext, String questionTypeId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionTypeManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		QuestionType questionType = loadQuestionType( userContext, questionTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,questionType, tokens);
 	}
 	
 	
 	 public QuestionType searchQuestionType(HealthUserContext userContext, String questionTypeId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionTypeManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		QuestionType questionType = loadQuestionType( userContext, questionTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,questionType, tokens);
 	}
 	
 	

 	protected QuestionType present(HealthUserContext userContext, QuestionType questionType, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,questionType,tokens);
		
		
		QuestionType  questionTypeToPresent = questionTypeDaoOf(userContext).present(questionType, tokens);
		
		List<BaseEntity> entityListToNaming = questionTypeToPresent.collectRefercencesFromLists();
		questionTypeDaoOf(userContext).alias(entityListToNaming);
		
		return  questionTypeToPresent;
		
		
	}
 
 	
 	
 	public QuestionType loadQuestionTypeDetail(HealthUserContext userContext, String questionTypeId) throws Exception{	
 		QuestionType questionType = loadQuestionType( userContext, questionTypeId, allTokens());
 		return present(userContext,questionType, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String questionTypeId) throws Exception{	
 		QuestionType questionType = loadQuestionType( userContext, questionTypeId, viewTokens());
 		return present(userContext,questionType, allTokens());
		
 	}
 	protected QuestionType saveQuestionType(HealthUserContext userContext, QuestionType questionType, Map<String,Object>tokens) throws Exception{	
 		return questionTypeDaoOf(userContext).save(questionType, tokens);
 	}
 	protected QuestionType loadQuestionType(HealthUserContext userContext, String questionTypeId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionTypeManagerException.class);

 
 		return questionTypeDaoOf(userContext).load(questionTypeId, tokens);
 	}

	
	

	public QuestionType loadQuestionTypeWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception{	
 		return questionTypeDaoOf(userContext).loadByCode(code, tokens);
 	}

	 


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, QuestionType questionType, Map<String, Object> tokens){
		super.addActions(userContext, questionType, tokens);
		
		addAction(userContext, questionType, tokens,"@create","createQuestionType","createQuestionType/","main","primary");
		addAction(userContext, questionType, tokens,"@update","updateQuestionType","updateQuestionType/"+questionType.getId()+"/","main","primary");
		addAction(userContext, questionType, tokens,"@copy","cloneQuestionType","cloneQuestionType/"+questionType.getId()+"/","main","primary");
		
		addAction(userContext, questionType, tokens,"question_type.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+questionType.getId()+"/","main","primary");
		addAction(userContext, questionType, tokens,"question_type.addQuestion","addQuestion","addQuestion/"+questionType.getId()+"/","questionList","primary");
		addAction(userContext, questionType, tokens,"question_type.removeQuestion","removeQuestion","removeQuestion/"+questionType.getId()+"/","questionList","primary");
		addAction(userContext, questionType, tokens,"question_type.updateQuestion","updateQuestion","updateQuestion/"+questionType.getId()+"/","questionList","primary");
		addAction(userContext, questionType, tokens,"question_type.copyQuestionFrom","copyQuestionFrom","copyQuestionFrom/"+questionType.getId()+"/","questionList","primary");
		addAction(userContext, questionType, tokens,"question_type.addClassQuestion","addClassQuestion","addClassQuestion/"+questionType.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.removeClassQuestion","removeClassQuestion","removeClassQuestion/"+questionType.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.updateClassQuestion","updateClassQuestion","updateClassQuestion/"+questionType.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.copyClassQuestionFrom","copyClassQuestionFrom","copyClassQuestionFrom/"+questionType.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.addDailySurveyQuestion","addDailySurveyQuestion","addDailySurveyQuestion/"+questionType.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.removeDailySurveyQuestion","removeDailySurveyQuestion","removeDailySurveyQuestion/"+questionType.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.updateDailySurveyQuestion","updateDailySurveyQuestion","updateDailySurveyQuestion/"+questionType.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, questionType, tokens,"question_type.copyDailySurveyQuestionFrom","copyDailySurveyQuestionFrom","copyDailySurveyQuestionFrom/"+questionType.getId()+"/","dailySurveyQuestionList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, QuestionType questionType, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public QuestionType createQuestionType(HealthUserContext userContext, String name,String code,String platformId) throws Exception
	//public QuestionType createQuestionType(HealthUserContext userContext,String name, String code, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfQuestionType(name);
		checkerOf(userContext).checkCodeOfQuestionType(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);


		QuestionType questionType=createNewQuestionType();	

		questionType.setName(name);
		questionType.setCode(code);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		questionType.setPlatform(platform);
		
		

		questionType = saveQuestionType(userContext, questionType, emptyOptions());
		
		onNewInstanceCreated(userContext, questionType);
		return questionType;


	}
	protected QuestionType createNewQuestionType()
	{

		return new QuestionType();
	}

	protected void checkParamsForUpdatingQuestionType(HealthUserContext userContext,String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkVersionOfQuestionType( questionTypeVersion);
		

		if(QuestionType.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfQuestionType(parseString(newValueExpr));
		}
		if(QuestionType.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfQuestionType(parseString(newValueExpr));
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);


	}



	public QuestionType clone(HealthUserContext userContext, String fromQuestionTypeId) throws Exception{

		return questionTypeDaoOf(userContext).clone(fromQuestionTypeId, this.allTokens());
	}

	public QuestionType internalSaveQuestionType(HealthUserContext userContext, QuestionType questionType) throws Exception
	{
		return internalSaveQuestionType(userContext, questionType, allTokens());

	}
	public QuestionType internalSaveQuestionType(HealthUserContext userContext, QuestionType questionType, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingQuestionType(userContext, questionTypeId, questionTypeVersion, property, newValueExpr, tokensExpr);


		synchronized(questionType){
			//will be good when the questionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to QuestionType.
			if (questionType.isChanged()){
			
			}
			questionType = saveQuestionType(userContext, questionType, options);
			return questionType;

		}

	}

	public QuestionType updateQuestionType(HealthUserContext userContext,String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionType(userContext, questionTypeId, questionTypeVersion, property, newValueExpr, tokensExpr);



		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		if(questionType.getVersion() != questionTypeVersion){
			String message = "The target version("+questionType.getVersion()+") is not equals to version("+questionTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(questionType){
			//will be good when the questionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to QuestionType.
			
			questionType.changeProperty(property, newValueExpr);
			questionType = saveQuestionType(userContext, questionType, tokens().done());
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
			//return saveQuestionType(userContext, questionType, tokens().done());
		}

	}

	public QuestionType updateQuestionTypeProperty(HealthUserContext userContext,String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionType(userContext, questionTypeId, questionTypeVersion, property, newValueExpr, tokensExpr);

		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		if(questionType.getVersion() != questionTypeVersion){
			String message = "The target version("+questionType.getVersion()+") is not equals to version("+questionTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(questionType){
			//will be good when the questionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to QuestionType.

			questionType.changeProperty(property, newValueExpr);
			
			questionType = saveQuestionType(userContext, questionType, tokens().done());
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
			//return saveQuestionType(userContext, questionType, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected QuestionTypeTokens tokens(){
		return QuestionTypeTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return QuestionTypeTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortQuestionListWith("id","desc")
		.sortClassQuestionListWith("id","desc")
		.sortDailySurveyQuestionListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return QuestionTypeTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String questionTypeId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

 	}
 	public QuestionType transferToAnotherPlatform(HealthUserContext userContext, String questionTypeId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, questionTypeId,anotherPlatformId);
 
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());	
		synchronized(questionType){
			//will be good when the questionType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			questionType.updatePlatform(platform);		
			questionType = saveQuestionType(userContext, questionType, emptyOptions());
			
			return present(userContext,questionType, allTokens());
			
		}

 	}

	


	public CandidatePlatform requestCandidatePlatform(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForQuestionType(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String questionTypeId, int questionTypeVersion) throws Exception {
		//deleteInternal(userContext, questionTypeId, questionTypeVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String questionTypeId, int questionTypeVersion) throws Exception{

		questionTypeDaoOf(userContext).delete(questionTypeId, questionTypeVersion);
	}

	public QuestionType forgetByAll(HealthUserContext userContext, String questionTypeId, int questionTypeVersion) throws Exception {
		return forgetByAllInternal(userContext, questionTypeId, questionTypeVersion);
	}
	protected QuestionType forgetByAllInternal(HealthUserContext userContext,
			String questionTypeId, int questionTypeVersion) throws Exception{

		return questionTypeDaoOf(userContext).disconnectFromAll(questionTypeId, questionTypeVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new QuestionTypeManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return questionTypeDaoOf(userContext).deleteAll();
	}


	//disconnect QuestionType with platform in Question
	protected QuestionType breakWithQuestionByPlatform(HealthUserContext userContext, String questionTypeId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());

			synchronized(questionType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionTypeDaoOf(userContext).planToRemoveQuestionListWithPlatform(questionType, platformId, this.emptyOptions());

				questionType = saveQuestionType(userContext, questionType, tokens().withQuestionList().done());
				return questionType;
			}
	}
	//disconnect QuestionType with question_source in ClassQuestion
	protected QuestionType breakWithClassQuestionByQuestionSource(HealthUserContext userContext, String questionTypeId, String questionSourceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());

			synchronized(questionType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionTypeDaoOf(userContext).planToRemoveClassQuestionListWithQuestionSource(questionType, questionSourceId, this.emptyOptions());

				questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
				return questionType;
			}
	}
	//disconnect QuestionType with creator in ClassQuestion
	protected QuestionType breakWithClassQuestionByCreator(HealthUserContext userContext, String questionTypeId, String creatorId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());

			synchronized(questionType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionTypeDaoOf(userContext).planToRemoveClassQuestionListWithCreator(questionType, creatorId, this.emptyOptions());

				questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
				return questionType;
			}
	}
	//disconnect QuestionType with cq in ClassQuestion
	protected QuestionType breakWithClassQuestionByCq(HealthUserContext userContext, String questionTypeId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());

			synchronized(questionType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionTypeDaoOf(userContext).planToRemoveClassQuestionListWithCq(questionType, cqId, this.emptyOptions());

				questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
				return questionType;
			}
	}
	//disconnect QuestionType with class_daily_health_survey in DailySurveyQuestion
	protected QuestionType breakWithDailySurveyQuestionByClassDailyHealthSurvey(HealthUserContext userContext, String questionTypeId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());

			synchronized(questionType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionTypeDaoOf(userContext).planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(questionType, classDailyHealthSurveyId, this.emptyOptions());

				questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
				return questionType;
			}
	}
	//disconnect QuestionType with class_question in DailySurveyQuestion
	protected QuestionType breakWithDailySurveyQuestionByClassQuestion(HealthUserContext userContext, String questionTypeId, String classQuestionId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());

			synchronized(questionType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionTypeDaoOf(userContext).planToRemoveDailySurveyQuestionListWithClassQuestion(questionType, classQuestionId, this.emptyOptions());

				questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
				return questionType;
			}
	}






	protected void checkParamsForAddingQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfQuestionType(questionTypeId);

		
		checkerOf(userContext).checkTopicOfQuestion(topic);
		
		checkerOf(userContext).checkOptionAOfQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfQuestion(optionD);
		
		checkerOf(userContext).checkPlatformIdOfQuestion(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);


	}
	public  QuestionType addQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingQuestion(userContext,questionTypeId,topic, optionA, optionB, optionC, optionD, platformId,tokensExpr);

		Question question = createQuestion(userContext,topic, optionA, optionB, optionC, optionD, platformId);

		QuestionType questionType = loadQuestionType(userContext, questionTypeId, emptyOptions());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionType.addQuestion( question );
			questionType = saveQuestionType(userContext, questionType, tokens().withQuestionList().done());
			
			userContext.getManagerGroup().getQuestionManager().onNewInstanceCreated(userContext, question);
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingQuestionProperties(HealthUserContext userContext, String questionTypeId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkIdOfQuestion(id);

		checkerOf(userContext).checkTopicOfQuestion( topic);
		checkerOf(userContext).checkOptionAOfQuestion( optionA);
		checkerOf(userContext).checkOptionBOfQuestion( optionB);
		checkerOf(userContext).checkOptionCOfQuestion( optionC);
		checkerOf(userContext).checkOptionDOfQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType updateQuestionProperties(HealthUserContext userContext, String questionTypeId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionProperties(userContext,questionTypeId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withQuestionListList()
				.searchQuestionListWith(Question.ID_PROPERTY, "is", id).done();

		QuestionType questionTypeToUpdate = loadQuestionType(userContext, questionTypeId, options);

		if(questionTypeToUpdate.getQuestionList().isEmpty()){
			throw new QuestionTypeManagerException("Question is NOT FOUND with id: '"+id+"'");
		}

		Question item = questionTypeToUpdate.getQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingQuestion(userContext,questionTypeId,name, code, used,tokensExpr);
		QuestionType questionType = saveQuestionType(userContext, questionTypeToUpdate, tokens().withQuestionList().done());
		synchronized(questionType){
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}
	}


	protected Question createQuestion(HealthUserContext userContext, String topic, String optionA, String optionB, String optionC, String optionD, String platformId) throws Exception{

		Question question = new Question();
		
		
		question.setTopic(topic);		
		question.setOptionA(optionA);		
		question.setOptionB(optionB);		
		question.setOptionC(optionC);		
		question.setOptionD(optionD);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		question.setPlatform(platform);
	
		
		return question;


	}

	protected Question createIndexedQuestion(String id, int version){

		Question question = new Question();
		question.setId(id);
		question.setVersion(version);
		return question;

	}

	protected void checkParamsForRemovingQuestionList(HealthUserContext userContext, String questionTypeId,
			String questionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		for(String questionIdItem: questionIds){
			checkerOf(userContext).checkIdOfQuestion(questionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType removeQuestionList(HealthUserContext userContext, String questionTypeId,
			String questionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingQuestionList(userContext, questionTypeId,  questionIds, tokensExpr);


			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
			synchronized(questionType){
				//Will be good when the questionType loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				questionTypeDaoOf(userContext).planToRemoveQuestionList(questionType, questionIds, allTokens());
				questionType = saveQuestionType(userContext, questionType, tokens().withQuestionList().done());
				deleteRelationListInGraph(userContext, questionType.getQuestionList());
				return present(userContext,questionType, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingQuestion(HealthUserContext userContext, String questionTypeId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionType( questionTypeId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType removeQuestion(HealthUserContext userContext, String questionTypeId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingQuestion(userContext,questionTypeId, questionId, questionVersion,tokensExpr);

		Question question = createIndexedQuestion(questionId, questionVersion);
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionType.removeQuestion( question );
			questionType = saveQuestionType(userContext, questionType, tokens().withQuestionList().done());
			deleteRelationInGraph(userContext, question);
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingQuestion(HealthUserContext userContext, String questionTypeId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionType( questionTypeId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType copyQuestionFrom(HealthUserContext userContext, String questionTypeId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingQuestion(userContext,questionTypeId, questionId, questionVersion,tokensExpr);

		Question question = createIndexedQuestion(questionId, questionVersion);
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			questionType.copyQuestionFrom( question );
			questionType = saveQuestionType(userContext, questionType, tokens().withQuestionList().done());
			
			userContext.getManagerGroup().getQuestionManager().onNewInstanceCreated(userContext, (Question)questionType.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingQuestion(HealthUserContext userContext, String questionTypeId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		

		if(Question.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfQuestion(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}

	public  QuestionType updateQuestion(HealthUserContext userContext, String questionTypeId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingQuestion(userContext, questionTypeId, questionId, questionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withQuestionList().searchQuestionListWith(Question.ID_PROPERTY, "eq", questionId).done();



		QuestionType questionType = loadQuestionType(userContext, questionTypeId, loadTokens);

		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//questionType.removeQuestion( question );
			//make changes to AcceleraterAccount.
			Question questionIndex = createIndexedQuestion(questionId, questionVersion);

			Question question = questionType.findTheQuestion(questionIndex);
			if(question == null){
				throw new QuestionTypeManagerException(question+" is NOT FOUND" );
			}

			question.changeProperty(property, newValueExpr);
			
			questionType = saveQuestionType(userContext, questionType, tokens().withQuestionList().done());
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingClassQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfQuestionType(questionTypeId);

		
		checkerOf(userContext).checkTopicOfClassQuestion(topic);
		
		checkerOf(userContext).checkOptionAOfClassQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfClassQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfClassQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfClassQuestion(optionD);
		
		checkerOf(userContext).checkQuestionSourceIdOfClassQuestion(questionSourceId);
		
		checkerOf(userContext).checkCreatorIdOfClassQuestion(creatorId);
		
		checkerOf(userContext).checkCqIdOfClassQuestion(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);


	}
	public  QuestionType addClassQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassQuestion(userContext,questionTypeId,topic, optionA, optionB, optionC, optionD, questionSourceId, creatorId, cqId,tokensExpr);

		ClassQuestion classQuestion = createClassQuestion(userContext,topic, optionA, optionB, optionC, optionD, questionSourceId, creatorId, cqId);

		QuestionType questionType = loadQuestionType(userContext, questionTypeId, emptyOptions());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionType.addClassQuestion( classQuestion );
			questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, classQuestion);
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassQuestionProperties(HealthUserContext userContext, String questionTypeId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkIdOfClassQuestion(id);

		checkerOf(userContext).checkTopicOfClassQuestion( topic);
		checkerOf(userContext).checkOptionAOfClassQuestion( optionA);
		checkerOf(userContext).checkOptionBOfClassQuestion( optionB);
		checkerOf(userContext).checkOptionCOfClassQuestion( optionC);
		checkerOf(userContext).checkOptionDOfClassQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType updateClassQuestionProperties(HealthUserContext userContext, String questionTypeId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassQuestionProperties(userContext,questionTypeId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassQuestionListList()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "is", id).done();

		QuestionType questionTypeToUpdate = loadQuestionType(userContext, questionTypeId, options);

		if(questionTypeToUpdate.getClassQuestionList().isEmpty()){
			throw new QuestionTypeManagerException("ClassQuestion is NOT FOUND with id: '"+id+"'");
		}

		ClassQuestion item = questionTypeToUpdate.getClassQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingClassQuestion(userContext,questionTypeId,name, code, used,tokensExpr);
		QuestionType questionType = saveQuestionType(userContext, questionTypeToUpdate, tokens().withClassQuestionList().done());
		synchronized(questionType){
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassQuestion createClassQuestion(HealthUserContext userContext, String topic, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId, String cqId) throws Exception{

		ClassQuestion classQuestion = new ClassQuestion();
		
		
		classQuestion.setTopic(topic);		
		classQuestion.setOptionA(optionA);		
		classQuestion.setOptionB(optionB);		
		classQuestion.setOptionC(optionC);		
		classQuestion.setOptionD(optionD);		
		QuestionSource  questionSource = new QuestionSource();
		questionSource.setId(questionSourceId);		
		classQuestion.setQuestionSource(questionSource);		
		WechatUser  creator = new WechatUser();
		creator.setId(creatorId);		
		classQuestion.setCreator(creator);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		classQuestion.setCq(cq);
	
		
		return classQuestion;


	}

	protected ClassQuestion createIndexedClassQuestion(String id, int version){

		ClassQuestion classQuestion = new ClassQuestion();
		classQuestion.setId(id);
		classQuestion.setVersion(version);
		return classQuestion;

	}

	protected void checkParamsForRemovingClassQuestionList(HealthUserContext userContext, String questionTypeId,
			String classQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		for(String classQuestionIdItem: classQuestionIds){
			checkerOf(userContext).checkIdOfClassQuestion(classQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType removeClassQuestionList(HealthUserContext userContext, String questionTypeId,
			String classQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassQuestionList(userContext, questionTypeId,  classQuestionIds, tokensExpr);


			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
			synchronized(questionType){
				//Will be good when the questionType loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				questionTypeDaoOf(userContext).planToRemoveClassQuestionList(questionType, classQuestionIds, allTokens());
				questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
				deleteRelationListInGraph(userContext, questionType.getClassQuestionList());
				return present(userContext,questionType, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassQuestion(HealthUserContext userContext, String questionTypeId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionType( questionTypeId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType removeClassQuestion(HealthUserContext userContext, String questionTypeId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassQuestion(userContext,questionTypeId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionType.removeClassQuestion( classQuestion );
			questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
			deleteRelationInGraph(userContext, classQuestion);
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassQuestion(HealthUserContext userContext, String questionTypeId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionType( questionTypeId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType copyClassQuestionFrom(HealthUserContext userContext, String questionTypeId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassQuestion(userContext,questionTypeId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			questionType.copyClassQuestionFrom( classQuestion );
			questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, (ClassQuestion)questionType.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassQuestion(HealthUserContext userContext, String questionTypeId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		

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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}

	public  QuestionType updateClassQuestion(HealthUserContext userContext, String questionTypeId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassQuestion(userContext, questionTypeId, classQuestionId, classQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassQuestionList().searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "eq", classQuestionId).done();



		QuestionType questionType = loadQuestionType(userContext, questionTypeId, loadTokens);

		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//questionType.removeClassQuestion( classQuestion );
			//make changes to AcceleraterAccount.
			ClassQuestion classQuestionIndex = createIndexedClassQuestion(classQuestionId, classQuestionVersion);

			ClassQuestion classQuestion = questionType.findTheClassQuestion(classQuestionIndex);
			if(classQuestion == null){
				throw new QuestionTypeManagerException(classQuestion+" is NOT FOUND" );
			}

			classQuestion.changeProperty(property, newValueExpr);
			
			questionType = saveQuestionType(userContext, questionType, tokens().withClassQuestionList().done());
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  QuestionType associateClassQuestionListToNewCreator(HealthUserContext userContext, String questionTypeId, String  classQuestionIds[], String name, String avatar, String addressId, String userTypeId, String platformId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "oneof", this.joinArray("|", classQuestionIds)).done();

		QuestionType questionType = loadQuestionType(userContext, questionTypeId, options);

		WechatUser creator = wechatUserManagerOf(userContext).createWechatUser(userContext,  name,  avatar, addressId, userTypeId, platformId);

		for(ClassQuestion classQuestion: questionType.getClassQuestionList()) {
			//TODO: need to check if already associated
			classQuestion.updateCreator(creator);
		}
		return this.internalSaveQuestionType(userContext, questionType);
	}
	*/

	public  QuestionType associateClassQuestionListToCreator(HealthUserContext userContext, String questionTypeId, String  classQuestionIds[], String creatorId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "oneof", this.joinArray("|", classQuestionIds)).done();

		QuestionType questionType = loadQuestionType(userContext, questionTypeId, options);

		WechatUser creator = wechatUserManagerOf(userContext).loadWechatUser(userContext,creatorId,new String[]{"none"} );

		for(ClassQuestion classQuestion: questionType.getClassQuestionList()) {
			//TODO: need to check if already associated
			classQuestion.updateCreator(creator);
		}
		return this.internalSaveQuestionType(userContext, questionType);
	}


	protected void checkParamsForAddingDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String classQuestionId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfQuestionType(questionTypeId);

		
		checkerOf(userContext).checkTopicOfDailySurveyQuestion(topic);
		
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion(optionD);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfDailySurveyQuestion(classDailyHealthSurveyId);
		
		checkerOf(userContext).checkClassQuestionIdOfDailySurveyQuestion(classQuestionId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);


	}
	public  QuestionType addDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String classQuestionId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingDailySurveyQuestion(userContext,questionTypeId,topic, optionA, optionB, optionC, optionD, classDailyHealthSurveyId, classQuestionId,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createDailySurveyQuestion(userContext,topic, optionA, optionB, optionC, optionD, classDailyHealthSurveyId, classQuestionId);

		QuestionType questionType = loadQuestionType(userContext, questionTypeId, emptyOptions());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionType.addDailySurveyQuestion( dailySurveyQuestion );
			questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
			
			userContext.getManagerGroup().getDailySurveyQuestionManager().onNewInstanceCreated(userContext, dailySurveyQuestion);
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingDailySurveyQuestionProperties(HealthUserContext userContext, String questionTypeId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(id);

		checkerOf(userContext).checkTopicOfDailySurveyQuestion( topic);
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion( optionA);
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion( optionB);
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion( optionC);
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType updateDailySurveyQuestionProperties(HealthUserContext userContext, String questionTypeId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDailySurveyQuestionProperties(userContext,questionTypeId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withDailySurveyQuestionListList()
				.searchDailySurveyQuestionListWith(DailySurveyQuestion.ID_PROPERTY, "is", id).done();

		QuestionType questionTypeToUpdate = loadQuestionType(userContext, questionTypeId, options);

		if(questionTypeToUpdate.getDailySurveyQuestionList().isEmpty()){
			throw new QuestionTypeManagerException("DailySurveyQuestion is NOT FOUND with id: '"+id+"'");
		}

		DailySurveyQuestion item = questionTypeToUpdate.getDailySurveyQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingDailySurveyQuestion(userContext,questionTypeId,name, code, used,tokensExpr);
		QuestionType questionType = saveQuestionType(userContext, questionTypeToUpdate, tokens().withDailySurveyQuestionList().done());
		synchronized(questionType){
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}
	}


	protected DailySurveyQuestion createDailySurveyQuestion(HealthUserContext userContext, String topic, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String classQuestionId) throws Exception{

		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		
		
		dailySurveyQuestion.setTopic(topic);		
		dailySurveyQuestion.setOptionA(optionA);		
		dailySurveyQuestion.setOptionB(optionB);		
		dailySurveyQuestion.setOptionC(optionC);		
		dailySurveyQuestion.setOptionD(optionD);		
		ClassDailyHealthSurvey  classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(classDailyHealthSurveyId);		
		dailySurveyQuestion.setClassDailyHealthSurvey(classDailyHealthSurvey);		
		ClassQuestion  classQuestion = new ClassQuestion();
		classQuestion.setId(classQuestionId);		
		dailySurveyQuestion.setClassQuestion(classQuestion);
	
		
		return dailySurveyQuestion;


	}

	protected DailySurveyQuestion createIndexedDailySurveyQuestion(String id, int version){

		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		dailySurveyQuestion.setId(id);
		dailySurveyQuestion.setVersion(version);
		return dailySurveyQuestion;

	}

	protected void checkParamsForRemovingDailySurveyQuestionList(HealthUserContext userContext, String questionTypeId,
			String dailySurveyQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		for(String dailySurveyQuestionIdItem: dailySurveyQuestionIds){
			checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType removeDailySurveyQuestionList(HealthUserContext userContext, String questionTypeId,
			String dailySurveyQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingDailySurveyQuestionList(userContext, questionTypeId,  dailySurveyQuestionIds, tokensExpr);


			QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
			synchronized(questionType){
				//Will be good when the questionType loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				questionTypeDaoOf(userContext).planToRemoveDailySurveyQuestionList(questionType, dailySurveyQuestionIds, allTokens());
				questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
				deleteRelationListInGraph(userContext, questionType.getDailySurveyQuestionList());
				return present(userContext,questionType, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingDailySurveyQuestion(HealthUserContext userContext, String questionTypeId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionType( questionTypeId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType removeDailySurveyQuestion(HealthUserContext userContext, String questionTypeId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingDailySurveyQuestion(userContext,questionTypeId, dailySurveyQuestionId, dailySurveyQuestionVersion,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionType.removeDailySurveyQuestion( dailySurveyQuestion );
			questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
			deleteRelationInGraph(userContext, dailySurveyQuestion);
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingDailySurveyQuestion(HealthUserContext userContext, String questionTypeId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionType( questionTypeId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}
	public  QuestionType copyDailySurveyQuestionFrom(HealthUserContext userContext, String questionTypeId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingDailySurveyQuestion(userContext,questionTypeId, dailySurveyQuestionId, dailySurveyQuestionVersion,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);
		QuestionType questionType = loadQuestionType(userContext, questionTypeId, allTokens());
		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			questionType.copyDailySurveyQuestionFrom( dailySurveyQuestion );
			questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
			
			userContext.getManagerGroup().getDailySurveyQuestionManager().onNewInstanceCreated(userContext, (DailySurveyQuestion)questionType.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionTypeManagerException.class);

	}

	public  QuestionType updateDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingDailySurveyQuestion(userContext, questionTypeId, dailySurveyQuestionId, dailySurveyQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withDailySurveyQuestionList().searchDailySurveyQuestionListWith(DailySurveyQuestion.ID_PROPERTY, "eq", dailySurveyQuestionId).done();



		QuestionType questionType = loadQuestionType(userContext, questionTypeId, loadTokens);

		synchronized(questionType){
			//Will be good when the questionType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//questionType.removeDailySurveyQuestion( dailySurveyQuestion );
			//make changes to AcceleraterAccount.
			DailySurveyQuestion dailySurveyQuestionIndex = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);

			DailySurveyQuestion dailySurveyQuestion = questionType.findTheDailySurveyQuestion(dailySurveyQuestionIndex);
			if(dailySurveyQuestion == null){
				throw new QuestionTypeManagerException(dailySurveyQuestion+" is NOT FOUND" );
			}

			dailySurveyQuestion.changeProperty(property, newValueExpr);
			
			questionType = saveQuestionType(userContext, questionType, tokens().withDailySurveyQuestionList().done());
			return present(userContext,questionType, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, QuestionType newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, QuestionType.INTERNAL_TYPE);
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


