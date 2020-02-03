
package com.doublechaintech.health.teacher;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface TeacherManager extends BaseManager{

		

	public Teacher createTeacher(HealthUserContext userContext, String name,String mobile,String school,String schoolClass,int classSize,String platformId,String userId,String changeRequestId) throws Exception;	
	public Teacher updateTeacher(HealthUserContext userContext,String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Teacher loadTeacher(HealthUserContext userContext, String teacherId, String [] tokensExpr) throws Exception;
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher) throws Exception;
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher,Map<String,Object>option) throws Exception;
	
	public Teacher transferToAnotherPlatform(HealthUserContext userContext, String teacherId, String anotherPlatformId)  throws Exception;
 	public Teacher transferToAnotherUser(HealthUserContext userContext, String teacherId, String anotherUserId)  throws Exception;
 	public Teacher transferToAnotherChangeRequest(HealthUserContext userContext, String teacherId, String anotherChangeRequestId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String teacherId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Teacher newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String teacherId, String name, DateTime surveyTime, String creatorId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Teacher addClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String name, DateTime surveyTime, String creatorId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Teacher removeClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  Teacher updateClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String teacherId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Teacher addStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Teacher removeStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  Teacher updateStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  HealthSurveyReportManager getHealthSurveyReportManager(HealthUserContext userContext, String teacherId, String surveyName, DateTime surveyTime, String teacherName, String school, String schoolClass, String studentName, String studentNumber, String guardianName, String guardianMobile, String studentId, String surveyId ,String [] tokensExpr)  throws Exception;
	
	public  Teacher addHealthSurveyReport(HealthUserContext userContext, String teacherId, String surveyName, DateTime surveyTime, String teacherName, String school, String schoolClass, String studentName, String studentNumber, String guardianName, String guardianMobile, String studentId, String surveyId , String [] tokensExpr)  throws Exception;
	public  Teacher removeHealthSurveyReport(HealthUserContext userContext, String teacherId, String healthSurveyReportId, int healthSurveyReportVersion,String [] tokensExpr)  throws Exception;
	public  Teacher updateHealthSurveyReport(HealthUserContext userContext, String teacherId, String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


