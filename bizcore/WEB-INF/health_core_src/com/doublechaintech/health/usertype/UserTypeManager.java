
package com.doublechaintech.health.usertype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface UserTypeManager extends BaseManager{

		
	

	public UserType loadUserTypeWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public UserType createUserType(HealthUserContext userContext, String name,String code,String platformId) throws Exception;	
	public UserType updateUserType(HealthUserContext userContext,String userTypeId, int userTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UserType loadUserType(HealthUserContext userContext, String userTypeId, String [] tokensExpr) throws Exception;
	public UserType internalSaveUserType(HealthUserContext userContext, UserType userType) throws Exception;
	public UserType internalSaveUserType(HealthUserContext userContext, UserType userType,Map<String,Object>option) throws Exception;
	
	public UserType transferToAnotherPlatform(HealthUserContext userContext, String userTypeId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String userTypeId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, UserType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  WechatUserManager getWechatUserManager(HealthUserContext userContext, String userTypeId, String name, String avatar, String addressId, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  UserType addWechatUser(HealthUserContext userContext, String userTypeId, String name, String avatar, String addressId, String platformId , String [] tokensExpr)  throws Exception;
	public  UserType removeWechatUser(HealthUserContext userContext, String userTypeId, String wechatUserId, int wechatUserVersion,String [] tokensExpr)  throws Exception;
	public  UserType updateWechatUser(HealthUserContext userContext, String userTypeId, String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


