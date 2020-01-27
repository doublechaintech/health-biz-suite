
package com.doublechaintech.health.dailysurveyquestion;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface DailySurveyQuestionManager extends BaseManager{

		

	public DailySurveyQuestion createDailySurveyQuestion(HealthUserContext userContext, String topic,String questionTypeId,String optionA,String optionB,String optionC,String optionD,String classDailyHealthSurveyId,String surveyQuestionId) throws Exception;	
	public DailySurveyQuestion updateDailySurveyQuestion(HealthUserContext userContext,String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public DailySurveyQuestion loadDailySurveyQuestion(HealthUserContext userContext, String dailySurveyQuestionId, String [] tokensExpr) throws Exception;
	public DailySurveyQuestion internalSaveDailySurveyQuestion(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion) throws Exception;
	public DailySurveyQuestion internalSaveDailySurveyQuestion(HealthUserContext userContext, DailySurveyQuestion dailySurveyQuestion,Map<String,Object>option) throws Exception;
	
	public DailySurveyQuestion transferToAnotherQuestionType(HealthUserContext userContext, String dailySurveyQuestionId, String anotherQuestionTypeId)  throws Exception;
 	public DailySurveyQuestion transferToAnotherClassDailyHealthSurvey(HealthUserContext userContext, String dailySurveyQuestionId, String anotherClassDailyHealthSurveyId)  throws Exception;
 	public DailySurveyQuestion transferToAnotherSurveyQuestion(HealthUserContext userContext, String dailySurveyQuestionId, String anotherSurveyQuestionId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String dailySurveyQuestionId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, DailySurveyQuestion newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentDailyAnswerManager getStudentDailyAnswerManager(HealthUserContext userContext, String dailySurveyQuestionId, String studentHealthSurveyId, String answer, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  DailySurveyQuestion addStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentHealthSurveyId, String answer, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  DailySurveyQuestion removeStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr)  throws Exception;
	public  DailySurveyQuestion updateStudentDailyAnswer(HealthUserContext userContext, String dailySurveyQuestionId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


