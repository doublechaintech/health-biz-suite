
package com.doublechaintech.health.changerequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
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
	

	//public  TeacherManager getTeacherManager(HealthUserContext userContext, String changeRequestId, String name, String mobile, String school, String schoolClass, int classSize, String platformId, String userId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addTeacher(HealthUserContext userContext, String changeRequestId, String name, String mobile, String school, String schoolClass, int classSize, String platformId, String userId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeTeacher(HealthUserContext userContext, String changeRequestId, String teacherId, int teacherVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateTeacher(HealthUserContext userContext, String changeRequestId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  QuestionManager getQuestionManager(HealthUserContext userContext, String changeRequestId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String creatorId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addQuestion(HealthUserContext userContext, String changeRequestId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String creatorId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeQuestion(HealthUserContext userContext, String changeRequestId, String questionId, int questionVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateQuestion(HealthUserContext userContext, String changeRequestId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  ChangeRequest associateQuestionListToNewCreator(HealthUserContext userContext, String changeRequestId, String  questionIds[], String name, String avatar, String platformId, String [] tokensExpr) throws Exception ;
	public  ChangeRequest associateQuestionListToCreator(HealthUserContext userContext, String changeRequestId, String  questionIds[],String creatorId, String [] tokensExpr) throws Exception ;

	*/

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String changeRequestId, String name, String teacherId, DateTime surveyTime, String creatorId, String downloadUrl ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String name, String teacherId, DateTime surveyTime, String creatorId, String downloadUrl , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentHealthSurveyManager getStudentHealthSurveyManager(HealthUserContext userContext, String changeRequestId, String studentId, DateTime answerTime, String surveyStatusId, String teacherId, String classDailyHealthSurveyId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequest addStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentId, DateTime answerTime, String surveyStatusId, String teacherId, String classDailyHealthSurveyId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


