
package com.doublechaintech.health.platform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface PlatformManager extends BaseManager{

		

	public Platform createPlatform(HealthUserContext userContext, String name,String description) throws Exception;	
	public Platform updatePlatform(HealthUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Platform loadPlatform(HealthUserContext userContext, String platformId, String [] tokensExpr) throws Exception;
	public Platform internalSavePlatform(HealthUserContext userContext, Platform platform) throws Exception;
	public Platform internalSavePlatform(HealthUserContext userContext, Platform platform,Map<String,Object>option) throws Exception;
	


	public void delete(HealthUserContext userContext, String platformId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Platform newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ProvinceManager getProvinceManager(HealthUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;
	
	public  Platform addProvince(HealthUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removeProvince(HealthUserContext userContext, String platformId, String provinceId, int provinceVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateProvince(HealthUserContext userContext, String platformId, String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  CityManager getCityManager(HealthUserContext userContext, String platformId, String name, String provinceId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addCity(HealthUserContext userContext, String platformId, String name, String provinceId , String [] tokensExpr)  throws Exception;
	public  Platform removeCity(HealthUserContext userContext, String platformId, String cityId, int cityVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateCity(HealthUserContext userContext, String platformId, String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  DistrictManager getDistrictManager(HealthUserContext userContext, String platformId, String name, String cityId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addDistrict(HealthUserContext userContext, String platformId, String name, String cityId , String [] tokensExpr)  throws Exception;
	public  Platform removeDistrict(HealthUserContext userContext, String platformId, String districtId, int districtVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateDistrict(HealthUserContext userContext, String platformId, String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  TeacherManager getTeacherManager(HealthUserContext userContext, String platformId, String name, String mobile, String school, String schoolClass, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addTeacher(HealthUserContext userContext, String platformId, String name, String mobile, String school, String schoolClass, String cqId , String [] tokensExpr)  throws Exception;
	public  Platform removeTeacher(HealthUserContext userContext, String platformId, String teacherId, int teacherVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateTeacher(HealthUserContext userContext, String platformId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  StudentManager getStudentManager(HealthUserContext userContext, String platformId, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String userId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addStudent(HealthUserContext userContext, String platformId, String studentName, String studentId, String guardianName, String guardianMobile, String addressId, String userId, String cqId , String [] tokensExpr)  throws Exception;
	public  Platform removeStudent(HealthUserContext userContext, String platformId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateStudent(HealthUserContext userContext, String platformId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  QuestionManager getQuestionManager(HealthUserContext userContext, String platformId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addQuestion(HealthUserContext userContext, String platformId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String creatorId, String cqId , String [] tokensExpr)  throws Exception;
	public  Platform removeQuestion(HealthUserContext userContext, String platformId, String questionId, int questionVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateQuestion(HealthUserContext userContext, String platformId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  Platform associateQuestionListToNewCreator(HealthUserContext userContext, String platformId, String  questionIds[], String name, String avatar, String addressId, String platformId, String [] tokensExpr) throws Exception ;
	public  Platform associateQuestionListToCreator(HealthUserContext userContext, String platformId, String  questionIds[],String creatorId, String [] tokensExpr) throws Exception ;

	*/

	//public  QuestionTypeManager getQuestionTypeManager(HealthUserContext userContext, String platformId, String name, String code ,String [] tokensExpr)  throws Exception;
	
	public  Platform addQuestionType(HealthUserContext userContext, String platformId, String name, String code , String [] tokensExpr)  throws Exception;
	public  Platform removeQuestionType(HealthUserContext userContext, String platformId, String questionTypeId, int questionTypeVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateQuestionType(HealthUserContext userContext, String platformId, String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  SurveyStatusManager getSurveyStatusManager(HealthUserContext userContext, String platformId, String name, String code ,String [] tokensExpr)  throws Exception;
	
	public  Platform addSurveyStatus(HealthUserContext userContext, String platformId, String name, String code , String [] tokensExpr)  throws Exception;
	public  Platform removeSurveyStatus(HealthUserContext userContext, String platformId, String surveyStatusId, int surveyStatusVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateSurveyStatus(HealthUserContext userContext, String platformId, String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  UserManager getUserManager(HealthUserContext userContext, String platformId, String name, String avatar, String addressId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addUser(HealthUserContext userContext, String platformId, String name, String avatar, String addressId , String [] tokensExpr)  throws Exception;
	public  Platform removeUser(HealthUserContext userContext, String platformId, String userId, int userVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateUser(HealthUserContext userContext, String platformId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ChangeRequestManager getChangeRequestManager(HealthUserContext userContext, String platformId, String name, String requestTypeId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addChangeRequest(HealthUserContext userContext, String platformId, String name, String requestTypeId , String [] tokensExpr)  throws Exception;
	public  Platform removeChangeRequest(HealthUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateChangeRequest(HealthUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ChangeRequestTypeManager getChangeRequestTypeManager(HealthUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration ,String [] tokensExpr)  throws Exception;
	
	public  Platform addChangeRequestType(HealthUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration , String [] tokensExpr)  throws Exception;
	public  Platform removeChangeRequestType(HealthUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateChangeRequestType(HealthUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


