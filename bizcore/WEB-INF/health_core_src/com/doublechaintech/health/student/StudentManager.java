
package com.doublechaintech.health.student;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface StudentManager extends BaseManager{

		

	public Student createStudent(HealthUserContext userContext, String studentName,String studentId,String guardianName,String guardianMobile,String addressId,String userId,String platformId,String changeRequestId) throws Exception;	
	public Student updateStudent(HealthUserContext userContext,String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Student loadStudent(HealthUserContext userContext, String studentId, String [] tokensExpr) throws Exception;
	public Student internalSaveStudent(HealthUserContext userContext, Student student) throws Exception;
	public Student internalSaveStudent(HealthUserContext userContext, Student student,Map<String,Object>option) throws Exception;
	
	public Student transferToAnotherAddress(HealthUserContext userContext, String studentId, String anotherAddressId)  throws Exception;
 	public Student transferToAnotherUser(HealthUserContext userContext, String studentId, String anotherUserId)  throws Exception;
 	public Student transferToAnotherPlatform(HealthUserContext userContext, String studentId, String anotherPlatformId)  throws Exception;
 	public Student transferToAnotherChangeRequest(HealthUserContext userContext, String studentId, String anotherChangeRequestId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String studentId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Student newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String teacherId, String classDailyHealthSurveyId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Student addStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String teacherId, String classDailyHealthSurveyId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Student removeStudentHealthSurvey(HealthUserContext userContext, String studentId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  Student updateStudentHealthSurvey(HealthUserContext userContext, String studentId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


