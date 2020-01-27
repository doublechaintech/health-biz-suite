
package com.doublechaintech.health.studenthealthsurvey;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface StudentHealthSurveyManager extends BaseManager{

		

	public StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId,DateTime answerTime,String surveyStatusId,String teacherId,String classDailyHealthSurveyId,String changeRequestId) throws Exception;	
	public StudentHealthSurvey updateStudentHealthSurvey(HealthUserContext userContext,String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public StudentHealthSurvey loadStudentHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, String [] tokensExpr) throws Exception;
	public StudentHealthSurvey internalSaveStudentHealthSurvey(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey) throws Exception;
	public StudentHealthSurvey internalSaveStudentHealthSurvey(HealthUserContext userContext, StudentHealthSurvey studentHealthSurvey,Map<String,Object>option) throws Exception;
	
	public StudentHealthSurvey transferToAnotherStudent(HealthUserContext userContext, String studentHealthSurveyId, String anotherStudentId)  throws Exception;
 	public StudentHealthSurvey transferToAnotherSurveyStatus(HealthUserContext userContext, String studentHealthSurveyId, String anotherSurveyStatusId)  throws Exception;
 	public StudentHealthSurvey transferToAnotherTeacher(HealthUserContext userContext, String studentHealthSurveyId, String anotherTeacherId)  throws Exception;
 	public StudentHealthSurvey transferToAnotherClassDailyHealthSurvey(HealthUserContext userContext, String studentHealthSurveyId, String anotherClassDailyHealthSurveyId)  throws Exception;
 	public StudentHealthSurvey transferToAnotherChangeRequest(HealthUserContext userContext, String studentHealthSurveyId, String anotherChangeRequestId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String studentHealthSurveyId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, StudentHealthSurvey newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentDailyAnswerManager getStudentDailyAnswerManager(HealthUserContext userContext, String studentHealthSurveyId, String questionId, String answer, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  StudentHealthSurvey addStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String questionId, String answer, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  StudentHealthSurvey removeStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr)  throws Exception;
	public  StudentHealthSurvey updateStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


