
package com.doublechaintech.health.user;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface UserManager extends BaseManager{

		

	public User createUser(HealthUserContext userContext, String name,String avatar,String addressId,String platformId) throws Exception;	
	public User updateUser(HealthUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public User loadUser(HealthUserContext userContext, String userId, String [] tokensExpr) throws Exception;
	public User internalSaveUser(HealthUserContext userContext, User user) throws Exception;
	public User internalSaveUser(HealthUserContext userContext, User user,Map<String,Object>option) throws Exception;
	
	public User transferToAnotherAddress(HealthUserContext userContext, String userId, String anotherAddressId)  throws Exception;
 	public User transferToAnotherPlatform(HealthUserContext userContext, String userId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String userId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, User newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentManager getStudentManager(HealthUserContext userContext, String userId, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String platformId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  User addStudent(HealthUserContext userContext, String userId, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String platformId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  User removeStudent(HealthUserContext userContext, String userId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  User updateStudent(HealthUserContext userContext, String userId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  QuestionManager getQuestionManager(HealthUserContext userContext, String userId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  User addQuestion(HealthUserContext userContext, String userId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String platformId, String cqId , String [] tokensExpr)  throws Exception;
	public  User removeQuestion(HealthUserContext userContext, String userId, String questionId, int questionVersion,String [] tokensExpr)  throws Exception;
	public  User updateQuestion(HealthUserContext userContext, String userId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String userId, String name, String teacherId, DateTime surveyTime, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  User addClassDailyHealthSurvey(HealthUserContext userContext, String userId, String name, String teacherId, DateTime surveyTime, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  User removeClassDailyHealthSurvey(HealthUserContext userContext, String userId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  User updateClassDailyHealthSurvey(HealthUserContext userContext, String userId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WechatLoginInfoManager getWechatLoginInfoManager(HealthUserContext userContext, String userId, String appId, String openId, String sessionKey ,String [] tokensExpr)  throws Exception;
	
	public  User addWechatLoginInfo(HealthUserContext userContext, String userId, String appId, String openId, String sessionKey , String [] tokensExpr)  throws Exception;
	public  User removeWechatLoginInfo(HealthUserContext userContext, String userId, String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr)  throws Exception;
	public  User updateWechatLoginInfo(HealthUserContext userContext, String userId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


