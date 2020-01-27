
package com.doublechaintech.health.wechatuser;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface WechatUserManager extends BaseManager{

		

	public WechatUser createWechatUser(HealthUserContext userContext, String name,String avatar,String addressId,String userTypeId,String platformId) throws Exception;	
	public WechatUser updateWechatUser(HealthUserContext userContext,String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public WechatUser loadWechatUser(HealthUserContext userContext, String wechatUserId, String [] tokensExpr) throws Exception;
	public WechatUser internalSaveWechatUser(HealthUserContext userContext, WechatUser wechatUser) throws Exception;
	public WechatUser internalSaveWechatUser(HealthUserContext userContext, WechatUser wechatUser,Map<String,Object>option) throws Exception;
	
	public WechatUser transferToAnotherAddress(HealthUserContext userContext, String wechatUserId, String anotherAddressId)  throws Exception;
 	public WechatUser transferToAnotherUserType(HealthUserContext userContext, String wechatUserId, String anotherUserTypeId)  throws Exception;
 	public WechatUser transferToAnotherPlatform(HealthUserContext userContext, String wechatUserId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String wechatUserId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, WechatUser newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  GuardianManager getGuardianManager(HealthUserContext userContext, String wechatUserId, String name, String mobile, String addressId, String platformId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  WechatUser addGuardian(HealthUserContext userContext, String wechatUserId, String name, String mobile, String addressId, String platformId, String cqId , String [] tokensExpr)  throws Exception;
	public  WechatUser removeGuardian(HealthUserContext userContext, String wechatUserId, String guardianId, int guardianVersion,String [] tokensExpr)  throws Exception;
	public  WechatUser updateGuardian(HealthUserContext userContext, String wechatUserId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ClassQuestionManager getClassQuestionManager(HealthUserContext userContext, String wechatUserId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  WechatUser addClassQuestion(HealthUserContext userContext, String wechatUserId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String cqId , String [] tokensExpr)  throws Exception;
	public  WechatUser removeClassQuestion(HealthUserContext userContext, String wechatUserId, String classQuestionId, int classQuestionVersion,String [] tokensExpr)  throws Exception;
	public  WechatUser updateClassQuestion(HealthUserContext userContext, String wechatUserId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ClassDailyHealthSurveyManager getClassDailyHealthSurveyManager(HealthUserContext userContext, String wechatUserId, String name, String schoolClassId, DateTime surveyTime, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  WechatUser addClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String name, String schoolClassId, DateTime surveyTime, String cqId , String [] tokensExpr)  throws Exception;
	public  WechatUser removeClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr)  throws Exception;
	public  WechatUser updateClassDailyHealthSurvey(HealthUserContext userContext, String wechatUserId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WechatLoginInfoManager getWechatLoginInfoManager(HealthUserContext userContext, String wechatUserId, String appId, String openId, String sessionKey ,String [] tokensExpr)  throws Exception;
	
	public  WechatUser addWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String appId, String openId, String sessionKey , String [] tokensExpr)  throws Exception;
	public  WechatUser removeWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String wechatLoginInfoId, int wechatLoginInfoVersion,String [] tokensExpr)  throws Exception;
	public  WechatUser updateWechatLoginInfo(HealthUserContext userContext, String wechatUserId, String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


