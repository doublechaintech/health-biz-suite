
package com.doublechaintech.health.schoolclass;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface SchoolClassManager extends BaseManager{

		

	public SchoolClass createSchoolClass(HealthUserContext userContext, String name,String classTeacherId,String platformId,String schoole,String cqId) throws Exception;	
	public SchoolClass updateSchoolClass(HealthUserContext userContext,String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public SchoolClass loadSchoolClass(HealthUserContext userContext, String schoolClassId, String [] tokensExpr) throws Exception;
	public SchoolClass internalSaveSchoolClass(HealthUserContext userContext, SchoolClass schoolClass) throws Exception;
	public SchoolClass internalSaveSchoolClass(HealthUserContext userContext, SchoolClass schoolClass,Map<String,Object>option) throws Exception;
	
	public SchoolClass transferToAnotherClassTeacher(HealthUserContext userContext, String schoolClassId, String anotherClassTeacherId)  throws Exception;
 	public SchoolClass transferToAnotherPlatform(HealthUserContext userContext, String schoolClassId, String anotherPlatformId)  throws Exception;
 	public SchoolClass transferToAnotherCq(HealthUserContext userContext, String schoolClassId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String schoolClassId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, SchoolClass newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String schoolClassId, String name, DateTime surveyTime, String creatorId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  SchoolClass addClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String name, DateTime surveyTime, String creatorId, String cqId , String [] tokensExpr)  throws Exception;
	public  SchoolClass removeClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  SchoolClass updateClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentManager getStudentManager(HealthUserContext userContext, String schoolClassId, String name, String gender, String guardianId, String studentId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  SchoolClass addStudent(HealthUserContext userContext, String schoolClassId, String name, String gender, String guardianId, String studentId, String cqId , String [] tokensExpr)  throws Exception;
	public  SchoolClass removeStudent(HealthUserContext userContext, String schoolClassId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  SchoolClass updateStudent(HealthUserContext userContext, String schoolClassId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String schoolClassId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  SchoolClass addStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId , String [] tokensExpr)  throws Exception;
	public  SchoolClass removeStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  SchoolClass updateStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


