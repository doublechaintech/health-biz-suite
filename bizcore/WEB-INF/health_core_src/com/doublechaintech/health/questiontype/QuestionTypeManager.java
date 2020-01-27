
package com.doublechaintech.health.questiontype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface QuestionTypeManager extends BaseManager{

		
	

	public QuestionType loadQuestionTypeWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public QuestionType createQuestionType(HealthUserContext userContext, String name,String code,String platformId) throws Exception;	
	public QuestionType updateQuestionType(HealthUserContext userContext,String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public QuestionType loadQuestionType(HealthUserContext userContext, String questionTypeId, String [] tokensExpr) throws Exception;
	public QuestionType internalSaveQuestionType(HealthUserContext userContext, QuestionType questionType) throws Exception;
	public QuestionType internalSaveQuestionType(HealthUserContext userContext, QuestionType questionType,Map<String,Object>option) throws Exception;
	
	public QuestionType transferToAnotherPlatform(HealthUserContext userContext, String questionTypeId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String questionTypeId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, QuestionType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  QuestionManager getQuestionManager(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String platformId, String creatorId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  QuestionType addQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String platformId, String creatorId, String cqId , String [] tokensExpr)  throws Exception;
	public  QuestionType removeQuestion(HealthUserContext userContext, String questionTypeId, String questionId, int questionVersion,String [] tokensExpr)  throws Exception;
	public  QuestionType updateQuestion(HealthUserContext userContext, String questionTypeId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  QuestionType associateQuestionListToNewCreator(HealthUserContext userContext, String questionTypeId, String  questionIds[], String name, String avatar, String addressId, String platformId, String [] tokensExpr) throws Exception ;
	public  QuestionType associateQuestionListToCreator(HealthUserContext userContext, String questionTypeId, String  questionIds[],String creatorId, String [] tokensExpr) throws Exception ;

	*/

	//public  DailySurveyQuestionManager getDailySurveyQuestionManager(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String surveyQuestionId ,String [] tokensExpr)  throws Exception;
	
	public  QuestionType addDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String topic, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId, String surveyQuestionId , String [] tokensExpr)  throws Exception;
	public  QuestionType removeDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr)  throws Exception;
	public  QuestionType updateDailySurveyQuestion(HealthUserContext userContext, String questionTypeId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


