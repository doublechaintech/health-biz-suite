
package com.doublechaintech.health.questionsource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface QuestionSourceManager extends BaseManager{

		
	

	public QuestionSource loadQuestionSourceWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public QuestionSource createQuestionSource(HealthUserContext userContext, String name,String code,String platformId) throws Exception;	
	public QuestionSource updateQuestionSource(HealthUserContext userContext,String questionSourceId, int questionSourceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public QuestionSource loadQuestionSource(HealthUserContext userContext, String questionSourceId, String [] tokensExpr) throws Exception;
	public QuestionSource internalSaveQuestionSource(HealthUserContext userContext, QuestionSource questionSource) throws Exception;
	public QuestionSource internalSaveQuestionSource(HealthUserContext userContext, QuestionSource questionSource,Map<String,Object>option) throws Exception;
	
	public QuestionSource transferToAnotherPlatform(HealthUserContext userContext, String questionSourceId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String questionSourceId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, QuestionSource newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ClassQuestionManager getClassQuestionManager(HealthUserContext userContext, String questionSourceId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  QuestionSource addClassQuestion(HealthUserContext userContext, String questionSourceId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId , String [] tokensExpr)  throws Exception;
	public  QuestionSource removeClassQuestion(HealthUserContext userContext, String questionSourceId, String classQuestionId, int classQuestionVersion,String [] tokensExpr)  throws Exception;
	public  QuestionSource updateClassQuestion(HealthUserContext userContext, String questionSourceId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  QuestionSource associateClassQuestionListToNewCreator(HealthUserContext userContext, String questionSourceId, String  classQuestionIds[], String name, String avatar, String addressId, String userTypeId, String platformId, String [] tokensExpr) throws Exception ;
	public  QuestionSource associateClassQuestionListToCreator(HealthUserContext userContext, String questionSourceId, String  classQuestionIds[],String creatorId, String [] tokensExpr) throws Exception ;

	*/



}


