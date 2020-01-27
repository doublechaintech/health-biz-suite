
package com.doublechaintech.health.questionsource;

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
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.platform.CandidatePlatform;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;






public class QuestionSourceManagerImpl extends CustomHealthCheckerManager implements QuestionSourceManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "QuestionSource";
	@Override
	public QuestionSourceDAO daoOf(HealthUserContext userContext) {
		return questionSourceDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws QuestionSourceManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new QuestionSourceManagerException(message);

	}



 	protected QuestionSource saveQuestionSource(HealthUserContext userContext, QuestionSource questionSource, String [] tokensExpr) throws Exception{	
 		//return getQuestionSourceDAO().save(questionSource, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveQuestionSource(userContext, questionSource, tokens);
 	}
 	
 	protected QuestionSource saveQuestionSourceDetail(HealthUserContext userContext, QuestionSource questionSource) throws Exception{	

 		
 		return saveQuestionSource(userContext, questionSource, allTokens());
 	}
 	
 	public QuestionSource loadQuestionSource(HealthUserContext userContext, String questionSourceId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionSourceManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		QuestionSource questionSource = loadQuestionSource( userContext, questionSourceId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,questionSource, tokens);
 	}
 	
 	
 	 public QuestionSource searchQuestionSource(HealthUserContext userContext, String questionSourceId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionSourceManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		QuestionSource questionSource = loadQuestionSource( userContext, questionSourceId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,questionSource, tokens);
 	}
 	
 	

 	protected QuestionSource present(HealthUserContext userContext, QuestionSource questionSource, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,questionSource,tokens);
		
		
		QuestionSource  questionSourceToPresent = questionSourceDaoOf(userContext).present(questionSource, tokens);
		
		List<BaseEntity> entityListToNaming = questionSourceToPresent.collectRefercencesFromLists();
		questionSourceDaoOf(userContext).alias(entityListToNaming);
		
		return  questionSourceToPresent;
		
		
	}
 
 	
 	
 	public QuestionSource loadQuestionSourceDetail(HealthUserContext userContext, String questionSourceId) throws Exception{	
 		QuestionSource questionSource = loadQuestionSource( userContext, questionSourceId, allTokens());
 		return present(userContext,questionSource, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String questionSourceId) throws Exception{	
 		QuestionSource questionSource = loadQuestionSource( userContext, questionSourceId, viewTokens());
 		return present(userContext,questionSource, allTokens());
		
 	}
 	protected QuestionSource saveQuestionSource(HealthUserContext userContext, QuestionSource questionSource, Map<String,Object>tokens) throws Exception{	
 		return questionSourceDaoOf(userContext).save(questionSource, tokens);
 	}
 	protected QuestionSource loadQuestionSource(HealthUserContext userContext, String questionSourceId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).throwExceptionIfHasErrors( QuestionSourceManagerException.class);

 
 		return questionSourceDaoOf(userContext).load(questionSourceId, tokens);
 	}

	
	

	public QuestionSource loadQuestionSourceWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception{	
 		return questionSourceDaoOf(userContext).loadByCode(code, tokens);
 	}

	 


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, QuestionSource questionSource, Map<String, Object> tokens){
		super.addActions(userContext, questionSource, tokens);
		
		addAction(userContext, questionSource, tokens,"@create","createQuestionSource","createQuestionSource/","main","primary");
		addAction(userContext, questionSource, tokens,"@update","updateQuestionSource","updateQuestionSource/"+questionSource.getId()+"/","main","primary");
		addAction(userContext, questionSource, tokens,"@copy","cloneQuestionSource","cloneQuestionSource/"+questionSource.getId()+"/","main","primary");
		
		addAction(userContext, questionSource, tokens,"question_source.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+questionSource.getId()+"/","main","primary");
		addAction(userContext, questionSource, tokens,"question_source.addClassQuestion","addClassQuestion","addClassQuestion/"+questionSource.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionSource, tokens,"question_source.removeClassQuestion","removeClassQuestion","removeClassQuestion/"+questionSource.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionSource, tokens,"question_source.updateClassQuestion","updateClassQuestion","updateClassQuestion/"+questionSource.getId()+"/","classQuestionList","primary");
		addAction(userContext, questionSource, tokens,"question_source.copyClassQuestionFrom","copyClassQuestionFrom","copyClassQuestionFrom/"+questionSource.getId()+"/","classQuestionList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, QuestionSource questionSource, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public QuestionSource createQuestionSource(HealthUserContext userContext, String name,String code,String platformId) throws Exception
	//public QuestionSource createQuestionSource(HealthUserContext userContext,String name, String code, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfQuestionSource(name);
		checkerOf(userContext).checkCodeOfQuestionSource(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);


		QuestionSource questionSource=createNewQuestionSource();	

		questionSource.setName(name);
		questionSource.setCode(code);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		questionSource.setPlatform(platform);
		
		

		questionSource = saveQuestionSource(userContext, questionSource, emptyOptions());
		
		onNewInstanceCreated(userContext, questionSource);
		return questionSource;


	}
	protected QuestionSource createNewQuestionSource()
	{

		return new QuestionSource();
	}

	protected void checkParamsForUpdatingQuestionSource(HealthUserContext userContext,String questionSourceId, int questionSourceVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).checkVersionOfQuestionSource( questionSourceVersion);
		

		if(QuestionSource.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfQuestionSource(parseString(newValueExpr));
		}
		if(QuestionSource.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfQuestionSource(parseString(newValueExpr));
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);


	}



	public QuestionSource clone(HealthUserContext userContext, String fromQuestionSourceId) throws Exception{

		return questionSourceDaoOf(userContext).clone(fromQuestionSourceId, this.allTokens());
	}

	public QuestionSource internalSaveQuestionSource(HealthUserContext userContext, QuestionSource questionSource) throws Exception
	{
		return internalSaveQuestionSource(userContext, questionSource, allTokens());

	}
	public QuestionSource internalSaveQuestionSource(HealthUserContext userContext, QuestionSource questionSource, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingQuestionSource(userContext, questionSourceId, questionSourceVersion, property, newValueExpr, tokensExpr);


		synchronized(questionSource){
			//will be good when the questionSource loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to QuestionSource.
			if (questionSource.isChanged()){
			
			}
			questionSource = saveQuestionSource(userContext, questionSource, options);
			return questionSource;

		}

	}

	public QuestionSource updateQuestionSource(HealthUserContext userContext,String questionSourceId, int questionSourceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionSource(userContext, questionSourceId, questionSourceVersion, property, newValueExpr, tokensExpr);



		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());
		if(questionSource.getVersion() != questionSourceVersion){
			String message = "The target version("+questionSource.getVersion()+") is not equals to version("+questionSourceVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(questionSource){
			//will be good when the questionSource loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to QuestionSource.
			
			questionSource.changeProperty(property, newValueExpr);
			questionSource = saveQuestionSource(userContext, questionSource, tokens().done());
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
			//return saveQuestionSource(userContext, questionSource, tokens().done());
		}

	}

	public QuestionSource updateQuestionSourceProperty(HealthUserContext userContext,String questionSourceId, int questionSourceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionSource(userContext, questionSourceId, questionSourceVersion, property, newValueExpr, tokensExpr);

		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());
		if(questionSource.getVersion() != questionSourceVersion){
			String message = "The target version("+questionSource.getVersion()+") is not equals to version("+questionSourceVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(questionSource){
			//will be good when the questionSource loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to QuestionSource.

			questionSource.changeProperty(property, newValueExpr);
			
			questionSource = saveQuestionSource(userContext, questionSource, tokens().done());
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
			//return saveQuestionSource(userContext, questionSource, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected QuestionSourceTokens tokens(){
		return QuestionSourceTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return QuestionSourceTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortClassQuestionListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return QuestionSourceTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String questionSourceId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);

 	}
 	public QuestionSource transferToAnotherPlatform(HealthUserContext userContext, String questionSourceId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, questionSourceId,anotherPlatformId);
 
		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());	
		synchronized(questionSource){
			//will be good when the questionSource loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			questionSource.updatePlatform(platform);		
			questionSource = saveQuestionSource(userContext, questionSource, emptyOptions());
			
			return present(userContext,questionSource, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForQuestionSource(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(HealthUserContext userContext, String questionSourceId, int questionSourceVersion) throws Exception {
		//deleteInternal(userContext, questionSourceId, questionSourceVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String questionSourceId, int questionSourceVersion) throws Exception{

		questionSourceDaoOf(userContext).delete(questionSourceId, questionSourceVersion);
	}

	public QuestionSource forgetByAll(HealthUserContext userContext, String questionSourceId, int questionSourceVersion) throws Exception {
		return forgetByAllInternal(userContext, questionSourceId, questionSourceVersion);
	}
	protected QuestionSource forgetByAllInternal(HealthUserContext userContext,
			String questionSourceId, int questionSourceVersion) throws Exception{

		return questionSourceDaoOf(userContext).disconnectFromAll(questionSourceId, questionSourceVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new QuestionSourceManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return questionSourceDaoOf(userContext).deleteAll();
	}


	//disconnect QuestionSource with question_type in ClassQuestion
	protected QuestionSource breakWithClassQuestionByQuestionType(HealthUserContext userContext, String questionSourceId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());

			synchronized(questionSource){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionSourceDaoOf(userContext).planToRemoveClassQuestionListWithQuestionType(questionSource, questionTypeId, this.emptyOptions());

				questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
				return questionSource;
			}
	}
	//disconnect QuestionSource with creator in ClassQuestion
	protected QuestionSource breakWithClassQuestionByCreator(HealthUserContext userContext, String questionSourceId, String creatorId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());

			synchronized(questionSource){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionSourceDaoOf(userContext).planToRemoveClassQuestionListWithCreator(questionSource, creatorId, this.emptyOptions());

				questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
				return questionSource;
			}
	}
	//disconnect QuestionSource with cq in ClassQuestion
	protected QuestionSource breakWithClassQuestionByCq(HealthUserContext userContext, String questionSourceId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());

			synchronized(questionSource){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				questionSourceDaoOf(userContext).planToRemoveClassQuestionListWithCq(questionSource, cqId, this.emptyOptions());

				questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
				return questionSource;
			}
	}






	protected void checkParamsForAddingClassQuestion(HealthUserContext userContext, String questionSourceId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);

		
		checkerOf(userContext).checkTopicOfClassQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfClassQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfClassQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfClassQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfClassQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfClassQuestion(optionD);
		
		checkerOf(userContext).checkCreatorIdOfClassQuestion(creatorId);
		
		checkerOf(userContext).checkCqIdOfClassQuestion(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);


	}
	public  QuestionSource addClassQuestion(HealthUserContext userContext, String questionSourceId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassQuestion(userContext,questionSourceId,topic, questionTypeId, optionA, optionB, optionC, optionD, creatorId, cqId,tokensExpr);

		ClassQuestion classQuestion = createClassQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD, creatorId, cqId);

		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, emptyOptions());
		synchronized(questionSource){
			//Will be good when the questionSource loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionSource.addClassQuestion( classQuestion );
			questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, classQuestion);
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassQuestionProperties(HealthUserContext userContext, String questionSourceId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).checkIdOfClassQuestion(id);

		checkerOf(userContext).checkTopicOfClassQuestion( topic);
		checkerOf(userContext).checkOptionAOfClassQuestion( optionA);
		checkerOf(userContext).checkOptionBOfClassQuestion( optionB);
		checkerOf(userContext).checkOptionCOfClassQuestion( optionC);
		checkerOf(userContext).checkOptionDOfClassQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);

	}
	public  QuestionSource updateClassQuestionProperties(HealthUserContext userContext, String questionSourceId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassQuestionProperties(userContext,questionSourceId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassQuestionListList()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "is", id).done();

		QuestionSource questionSourceToUpdate = loadQuestionSource(userContext, questionSourceId, options);

		if(questionSourceToUpdate.getClassQuestionList().isEmpty()){
			throw new QuestionSourceManagerException("ClassQuestion is NOT FOUND with id: '"+id+"'");
		}

		ClassQuestion item = questionSourceToUpdate.getClassQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingClassQuestion(userContext,questionSourceId,name, code, used,tokensExpr);
		QuestionSource questionSource = saveQuestionSource(userContext, questionSourceToUpdate, tokens().withClassQuestionList().done());
		synchronized(questionSource){
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassQuestion createClassQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId) throws Exception{

		ClassQuestion classQuestion = new ClassQuestion();
		
		
		classQuestion.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		classQuestion.setQuestionType(questionType);		
		classQuestion.setOptionA(optionA);		
		classQuestion.setOptionB(optionB);		
		classQuestion.setOptionC(optionC);		
		classQuestion.setOptionD(optionD);		
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

	protected void checkParamsForRemovingClassQuestionList(HealthUserContext userContext, String questionSourceId,
			String classQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		for(String classQuestionIdItem: classQuestionIds){
			checkerOf(userContext).checkIdOfClassQuestion(classQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);

	}
	public  QuestionSource removeClassQuestionList(HealthUserContext userContext, String questionSourceId,
			String classQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassQuestionList(userContext, questionSourceId,  classQuestionIds, tokensExpr);


			QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());
			synchronized(questionSource){
				//Will be good when the questionSource loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				questionSourceDaoOf(userContext).planToRemoveClassQuestionList(questionSource, classQuestionIds, allTokens());
				questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
				deleteRelationListInGraph(userContext, questionSource.getClassQuestionList());
				return present(userContext,questionSource, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassQuestion(HealthUserContext userContext, String questionSourceId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionSource( questionSourceId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);

	}
	public  QuestionSource removeClassQuestion(HealthUserContext userContext, String questionSourceId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassQuestion(userContext,questionSourceId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());
		synchronized(questionSource){
			//Will be good when the questionSource loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			questionSource.removeClassQuestion( classQuestion );
			questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
			deleteRelationInGraph(userContext, classQuestion);
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassQuestion(HealthUserContext userContext, String questionSourceId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfQuestionSource( questionSourceId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);

	}
	public  QuestionSource copyClassQuestionFrom(HealthUserContext userContext, String questionSourceId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassQuestion(userContext,questionSourceId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, allTokens());
		synchronized(questionSource){
			//Will be good when the questionSource loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			questionSource.copyClassQuestionFrom( classQuestion );
			questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, (ClassQuestion)questionSource.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassQuestion(HealthUserContext userContext, String questionSourceId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(QuestionSourceManagerException.class);

	}

	public  QuestionSource updateClassQuestion(HealthUserContext userContext, String questionSourceId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassQuestion(userContext, questionSourceId, classQuestionId, classQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassQuestionList().searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "eq", classQuestionId).done();



		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, loadTokens);

		synchronized(questionSource){
			//Will be good when the questionSource loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//questionSource.removeClassQuestion( classQuestion );
			//make changes to AcceleraterAccount.
			ClassQuestion classQuestionIndex = createIndexedClassQuestion(classQuestionId, classQuestionVersion);

			ClassQuestion classQuestion = questionSource.findTheClassQuestion(classQuestionIndex);
			if(classQuestion == null){
				throw new QuestionSourceManagerException(classQuestion+" is NOT FOUND" );
			}

			classQuestion.changeProperty(property, newValueExpr);
			
			questionSource = saveQuestionSource(userContext, questionSource, tokens().withClassQuestionList().done());
			return present(userContext,questionSource, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  QuestionSource associateClassQuestionListToNewCreator(HealthUserContext userContext, String questionSourceId, String  classQuestionIds[], String name, String avatar, String addressId, String userTypeId, String platformId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "oneof", this.joinArray("|", classQuestionIds)).done();

		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, options);

		WechatUser creator = wechatUserManagerOf(userContext).createWechatUser(userContext,  name,  avatar, addressId, userTypeId, platformId);

		for(ClassQuestion classQuestion: questionSource.getClassQuestionList()) {
			//TODO: need to check if already associated
			classQuestion.updateCreator(creator);
		}
		return this.internalSaveQuestionSource(userContext, questionSource);
	}
	*/

	public  QuestionSource associateClassQuestionListToCreator(HealthUserContext userContext, String questionSourceId, String  classQuestionIds[], String creatorId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "oneof", this.joinArray("|", classQuestionIds)).done();

		QuestionSource questionSource = loadQuestionSource(userContext, questionSourceId, options);

		WechatUser creator = wechatUserManagerOf(userContext).loadWechatUser(userContext,creatorId,new String[]{"none"} );

		for(ClassQuestion classQuestion: questionSource.getClassQuestionList()) {
			//TODO: need to check if already associated
			classQuestion.updateCreator(creator);
		}
		return this.internalSaveQuestionSource(userContext, questionSource);
	}


	public void onNewInstanceCreated(HealthUserContext userContext, QuestionSource newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, QuestionSource.INTERNAL_TYPE);
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


