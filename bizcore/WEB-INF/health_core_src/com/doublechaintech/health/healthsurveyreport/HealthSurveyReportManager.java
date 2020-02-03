
package com.doublechaintech.health.healthsurveyreport;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface HealthSurveyReportManager extends BaseManager{

		

	public HealthSurveyReport createHealthSurveyReport(HealthUserContext userContext, String surveyName,DateTime surveyTime,String teacherName,String school,String schoolClass,String studentName,String studentNumber,String guardianName,String guardianMobile,String studentId,String teacherId,String surveyId) throws Exception;	
	public HealthSurveyReport updateHealthSurveyReport(HealthUserContext userContext,String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public HealthSurveyReport loadHealthSurveyReport(HealthUserContext userContext, String healthSurveyReportId, String [] tokensExpr) throws Exception;
	public HealthSurveyReport internalSaveHealthSurveyReport(HealthUserContext userContext, HealthSurveyReport healthSurveyReport) throws Exception;
	public HealthSurveyReport internalSaveHealthSurveyReport(HealthUserContext userContext, HealthSurveyReport healthSurveyReport,Map<String,Object>option) throws Exception;
	
	public HealthSurveyReport transferToAnotherStudent(HealthUserContext userContext, String healthSurveyReportId, String anotherStudentId)  throws Exception;
 	public HealthSurveyReport transferToAnotherTeacher(HealthUserContext userContext, String healthSurveyReportId, String anotherTeacherId)  throws Exception;
 	public HealthSurveyReport transferToAnotherSurvey(HealthUserContext userContext, String healthSurveyReportId, String anotherSurveyId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String healthSurveyReportId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, HealthSurveyReport newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentAnswerManager getStudentAnswerManager(HealthUserContext userContext, String healthSurveyReportId, String dailyAnswerId, String questionTopic, String answer ,String [] tokensExpr)  throws Exception;
	
	public  HealthSurveyReport addStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String dailyAnswerId, String questionTopic, String answer , String [] tokensExpr)  throws Exception;
	public  HealthSurveyReport removeStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String studentAnswerId, int studentAnswerVersion,String [] tokensExpr)  throws Exception;
	public  HealthSurveyReport updateStudentAnswer(HealthUserContext userContext, String healthSurveyReportId, String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


