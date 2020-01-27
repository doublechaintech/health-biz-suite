
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

		

	public Teacher createTeacher(HealthUserContext userContext, String name,String mobile,String school,String schoolClass,String platformId,String cqId) throws Exception;	
	public Teacher updateTeacher(HealthUserContext userContext,String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Teacher loadTeacher(HealthUserContext userContext, String teacherId, String [] tokensExpr) throws Exception;
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher) throws Exception;
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher,Map<String,Object>option) throws Exception;
	
	public Teacher transferToAnotherPlatform(HealthUserContext userContext, String teacherId, String anotherPlatformId)  throws Exception;
 	public Teacher transferToAnotherCq(HealthUserContext userContext, String teacherId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String teacherId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Teacher newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String teacherId, String name, DateTime surveyTime, String creatorId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Teacher addClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String name, DateTime surveyTime, String creatorId, String cqId , String [] tokensExpr)  throws Exception;
	public  Teacher removeClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  Teacher updateClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String teacherId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Teacher addStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId , String [] tokensExpr)  throws Exception;
	public  Teacher removeStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  Teacher updateStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


