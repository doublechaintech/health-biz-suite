
package com.doublechaintech.health.classdailyhealthsurvey;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface ClassDailyHealthSurveyManager extends BaseManager{

		

	public ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name,String schoolClassId,DateTime surveyTime,String creatorId,String cqId) throws Exception;	
	public ClassDailyHealthSurvey updateClassDailyHealthSurvey(HealthUserContext userContext,String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ClassDailyHealthSurvey loadClassDailyHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String [] tokensExpr) throws Exception;
	public ClassDailyHealthSurvey internalSaveClassDailyHealthSurvey(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey) throws Exception;
	public ClassDailyHealthSurvey internalSaveClassDailyHealthSurvey(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey,Map<String,Object>option) throws Exception;
	
	public ClassDailyHealthSurvey transferToAnotherSchoolClass(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherSchoolClassId)  throws Exception;
 	public ClassDailyHealthSurvey transferToAnotherCreator(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherCreatorId)  throws Exception;
 	public ClassDailyHealthSurvey transferToAnotherCq(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String classDailyHealthSurveyId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, ClassDailyHealthSurvey newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  DailySurveyQuestionManager getDailySurveyQuestionManager(HealthUserContext userContext, String classDailyHealthSurveyId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classQuestionId ,String [] tokensExpr)  throws Exception;
	
	public  ClassDailyHealthSurvey addDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classQuestionId , String [] tokensExpr)  throws Exception;
	public  ClassDailyHealthSurvey removeDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr)  throws Exception;
	public  ClassDailyHealthSurvey updateDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String classDailyHealthSurveyId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  ClassDailyHealthSurvey addStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String cqId , String [] tokensExpr)  throws Exception;
	public  ClassDailyHealthSurvey removeStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  ClassDailyHealthSurvey updateStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


