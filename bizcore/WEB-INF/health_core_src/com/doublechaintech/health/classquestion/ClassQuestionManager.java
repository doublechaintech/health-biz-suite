
package com.doublechaintech.health.classquestion;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface ClassQuestionManager extends BaseManager{

		

	public ClassQuestion createClassQuestion(HealthUserContext userContext, String topic,String questionTypeId,String optionA,String optionB,String optionC,String optionD,String questionSourceId,String creatorId,String cqId) throws Exception;	
	public ClassQuestion updateClassQuestion(HealthUserContext userContext,String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ClassQuestion loadClassQuestion(HealthUserContext userContext, String classQuestionId, String [] tokensExpr) throws Exception;
	public ClassQuestion internalSaveClassQuestion(HealthUserContext userContext, ClassQuestion classQuestion) throws Exception;
	public ClassQuestion internalSaveClassQuestion(HealthUserContext userContext, ClassQuestion classQuestion,Map<String,Object>option) throws Exception;
	
	public ClassQuestion transferToAnotherQuestionType(HealthUserContext userContext, String classQuestionId, String anotherQuestionTypeId)  throws Exception;
 	public ClassQuestion transferToAnotherQuestionSource(HealthUserContext userContext, String classQuestionId, String anotherQuestionSourceId)  throws Exception;
 	public ClassQuestion transferToAnotherCreator(HealthUserContext userContext, String classQuestionId, String anotherCreatorId)  throws Exception;
 	public ClassQuestion transferToAnotherCq(HealthUserContext userContext, String classQuestionId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String classQuestionId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, ClassQuestion newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  DailySurveyQuestionManager getDailySurveyQuestionManager(HealthUserContext userContext, String classQuestionId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId ,String [] tokensExpr)  throws Exception;
	
	public  ClassQuestion addDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classDailyHealthSurveyId , String [] tokensExpr)  throws Exception;
	public  ClassQuestion removeDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr)  throws Exception;
	public  ClassQuestion updateDailySurveyQuestion(HealthUserContext userContext, String classQuestionId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


