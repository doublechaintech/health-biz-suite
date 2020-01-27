
package com.doublechaintech.health.surveystatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface SurveyStatusManager extends BaseManager{

		
	

	public SurveyStatus loadSurveyStatusWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public SurveyStatus createSurveyStatus(HealthUserContext userContext, String name,String code,String platformId) throws Exception;	
	public SurveyStatus updateSurveyStatus(HealthUserContext userContext,String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public SurveyStatus loadSurveyStatus(HealthUserContext userContext, String surveyStatusId, String [] tokensExpr) throws Exception;
	public SurveyStatus internalSaveSurveyStatus(HealthUserContext userContext, SurveyStatus surveyStatus) throws Exception;
	public SurveyStatus internalSaveSurveyStatus(HealthUserContext userContext, SurveyStatus surveyStatus,Map<String,Object>option) throws Exception;
	
	public SurveyStatus transferToAnotherPlatform(HealthUserContext userContext, String surveyStatusId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String surveyStatusId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, SurveyStatus newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String surveyStatusId, String studentId, DateTime answerTime, String teacherId, String classDailyHealthSurveyId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  SurveyStatus addStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentId, DateTime answerTime, String teacherId, String classDailyHealthSurveyId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  SurveyStatus removeStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  SurveyStatus updateStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


