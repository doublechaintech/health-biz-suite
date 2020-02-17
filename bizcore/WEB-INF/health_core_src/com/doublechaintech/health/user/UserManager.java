
package com.doublechaintech.health.user;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface UserManager extends BaseManager{

		

	public User createUser(HealthUserContext userContext, String name,String avatar,String platformId) throws Exception;	
	public User updateUser(HealthUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public User loadUser(HealthUserContext userContext, String userId, String [] tokensExpr) throws Exception;
	public User internalSaveUser(HealthUserContext userContext, User user) throws Exception;
	public User internalSaveUser(HealthUserContext userContext, User user,Map<String,Object>option) throws Exception;
	
	public User transferToAnotherPlatform(HealthUserContext userContext, String userId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String userId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, User newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  TeacherManager getTeacherManager(HealthUserContext userContext, String userId, String name, String mobile, String school, String schoolClass, int classSize, String platformId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  User addTeacher(HealthUserContext userContext, String userId, String name, String mobile, String school, String schoolClass, int classSize, String platformId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  User removeTeacher(HealthUserContext userContext, String userId, String teacherId, int teacherVersion,String [] tokensExpr)  throws Exception;
	public  User updateTeacher(HealthUserContext userContext, String userId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  User associateTeacherListToNewChangeRequest(HealthUserContext userContext, String userId, String  teacherIds[], String name, String requestTypeId, String platformId, String [] tokensExpr) throws Exception ;
	public  User associateTeacherListToChangeRequest(HealthUserContext userContext, String userId, String  teacherIds[],String changeRequestId, String [] tokensExpr) throws Exception ;

	*/

	//public  StudentManager getStudentManager(HealthUserContext userContext, String userId, String studentName, String studentNumber, String studentAvatar, String guardianName, String guardianMobile, String addressId, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  User addStudent(HealthUserContext userContext, String userId, String studentName, String studentNumber, String studentAvatar, String guardianName, String guardianMobile, String addressId, String platformId , String [] tokensExpr)  throws Exception;
	public  User removeStudent(HealthUserContext userContext, String userId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  User updateStudent(HealthUserContext userContext, String userId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  User associateStudentListToNewAddress(HealthUserContext userContext, String userId, String  studentIds[], String name, String address, String districtId, String provinceId, BigDecimal latitude, BigDecimal longitude, String [] tokensExpr) throws Exception ;
	public  User associateStudentListToAddress(HealthUserContext userContext, String userId, String  studentIds[],String addressId, String [] tokensExpr) throws Exception ;

	*/

	//public  QuestionManager getQuestionManager(HealthUserContext userContext, String userId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  User addQuestion(HealthUserContext userContext, String userId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId , String [] tokensExpr)  throws Exception;
	public  User removeQuestion(HealthUserContext userContext, String userId, String questionId, int questionVersion,String [] tokensExpr)  throws Exception;
	public  User updateQuestion(HealthUserContext userContext, String userId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  User associateQuestionListToNewCq(HealthUserContext userContext, String userId, String  questionIds[], String name, String requestTypeId, String platformId, String [] tokensExpr) throws Exception ;
	public  User associateQuestionListToCq(HealthUserContext userContext, String userId, String  questionIds[],String cqId, String [] tokensExpr) throws Exception ;

	*/

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String userId, String name, String teacherId, DateTime surveyTime, String downloadUrl, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  User addClassDailyHealthSurvey(HealthUserContext userContext, String userId, String name, String teacherId, DateTime surveyTime, String downloadUrl, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  User removeClassDailyHealthSurvey(HealthUserContext userContext, String userId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  User updateClassDailyHealthSurvey(HealthUserContext userContext, String userId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  User associateClassDailyHealthSurveyListToNewChangeRequest(HealthUserContext userContext, String userId, String  classDailyHealthSurveyIds[], String name, String requestTypeId, String platformId, String [] tokensExpr) throws Exception ;
	public  User associateClassDailyHealthSurveyListToChangeRequest(HealthUserContext userContext, String userId, String  classDailyHealthSurveyIds[],String changeRequestId, String [] tokensExpr) throws Exception ;

	*/

	//public  WechatLoginInfoManager getWechatLoginInfoManager(HealthUserContext userContext, String userId, String appId, String openId, String sessionKey ,String [] tokensExpr)  throws Exception;
	
	public  User addWechatLoginInfo(HealthUserContext userContext, String userId, String appId, String openId, String sessionKey , String [] tokensExpr)  throws Exception;
	public  User removeWechatLoginInfo(HealthUserContext userContext, String userId, String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr)  throws Exception;
	public  User updateWechatLoginInfo(HealthUserContext userContext, String userId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


