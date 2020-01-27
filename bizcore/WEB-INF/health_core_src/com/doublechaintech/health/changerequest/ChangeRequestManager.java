
package com.doublechaintech.health.changerequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface ChangeRequestManager extends BaseManager{

		

	public ChangeRequest createChangeRequest(HealthUserContext userContext, String name,String requestTypeId,String platformId) throws Exception;	
	public ChangeRequest updateChangeRequest(HealthUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ChangeRequest loadChangeRequest(HealthUserContext userContext, String changeRequestId, String [] tokensExpr) throws Exception;
	public ChangeRequest internalSaveChangeRequest(HealthUserContext userContext, ChangeRequest changeRequest) throws Exception;
	public ChangeRequest internalSaveChangeRequest(HealthUserContext userContext, ChangeRequest changeRequest,Map<String,Object>option) throws Exception;
	
	public ChangeRequest transferToAnotherRequestType(HealthUserContext userContext, String changeRequestId, String anotherRequestTypeId)  throws Exception;
 	public ChangeRequest transferToAnotherPlatform(HealthUserContext userContext, String changeRequestId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String changeRequestId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, ChangeRequest newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  SchoolClassManager getSchoolClassManager(HealthUserContext userContext, String changeRequestId, String name, String classTeacherId, String platformId, String schoole ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addSchoolClass(HealthUserContext userContext, String changeRequestId, String name, String classTeacherId, String platformId, String schoole , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeSchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId, int schoolClassVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateSchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  TeacherManager getTeacherManager(HealthUserContext userContext, String changeRequestId, String name, String mobile, String schoole, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addTeacher(HealthUserContext userContext, String changeRequestId, String name, String mobile, String schoole, String platformId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeTeacher(HealthUserContext userContext, String changeRequestId, String teacherId, int teacherVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateTeacher(HealthUserContext userContext, String changeRequestId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  GuardianManager getGuardianManager(HealthUserContext userContext, String changeRequestId, String name, String mobile, String addressId, String wechatUserId, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addGuardian(HealthUserContext userContext, String changeRequestId, String name, String mobile, String addressId, String wechatUserId, String platformId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeGuardian(HealthUserContext userContext, String changeRequestId, String guardianId, int guardianVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateGuardian(HealthUserContext userContext, String changeRequestId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ClassQuestionManager getClassQuestionManager(HealthUserContext userContext, String changeRequestId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addClassQuestion(HealthUserContext userContext, String changeRequestId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeClassQuestion(HealthUserContext userContext, String changeRequestId, String classQuestionId, int classQuestionVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateClassQuestion(HealthUserContext userContext, String changeRequestId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  ChangeRequest associateClassQuestionListToNewCreator(HealthUserContext userContext, String changeRequestId, String  classQuestionIds[], String name, String avatar, String addressId, String userTypeId, String platformId, String [] tokensExpr) throws Exception ;
	public  ChangeRequest associateClassQuestionListToCreator(HealthUserContext userContext, String changeRequestId, String  classQuestionIds[],String creatorId, String [] tokensExpr) throws Exception ;

	*/

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String changeRequestId, String name, String schoolClassId, DateTime surveyTime, String creatorId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String name, String schoolClassId, DateTime surveyTime, String creatorId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentManager getStudentManager(HealthUserContext userContext, String changeRequestId, String name, String gender, String guardianId, String schoolClassId, String studentId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addStudent(HealthUserContext userContext, String changeRequestId, String name, String gender, String guardianId, String schoolClassId, String studentId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeStudent(HealthUserContext userContext, String changeRequestId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateStudent(HealthUserContext userContext, String changeRequestId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String changeRequestId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentDailyAnswerManager getStudentDailyAnswerManager(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, String questionId, String answer ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, String questionId, String answer , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


