
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

		

	public Student createStudent(HealthUserContext userContext, String name,String gender,String guardianId,String schoolClassId,String studentId,String cqId) throws Exception;	
	public Student updateStudent(HealthUserContext userContext,String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Student loadStudent(HealthUserContext userContext, String studentId, String [] tokensExpr) throws Exception;
	public Student internalSaveStudent(HealthUserContext userContext, Student student) throws Exception;
	public Student internalSaveStudent(HealthUserContext userContext, Student student,Map<String,Object>option) throws Exception;
	
	public Student transferToAnotherGuardian(HealthUserContext userContext, String studentId, String anotherGuardianId)  throws Exception;
 	public Student transferToAnotherSchoolClass(HealthUserContext userContext, String studentId, String anotherSchoolClassId)  throws Exception;
 	public Student transferToAnotherCq(HealthUserContext userContext, String studentId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String studentId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Student newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Student addStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId, String cqId , String [] tokensExpr)  throws Exception;
	public  Student removeStudentHealthSurvey(HealthUserContext userContext, String studentId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  Student updateStudentHealthSurvey(HealthUserContext userContext, String studentId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


