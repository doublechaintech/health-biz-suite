
package com.doublechaintech.health.location;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface LocationManager extends BaseManager{

		

	public Location createLocation(HealthUserContext userContext, String name,String address,String districtId,String provinceId,BigDecimal latitude,BigDecimal longitude) throws Exception;	
	public Location updateLocation(HealthUserContext userContext,String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Location loadLocation(HealthUserContext userContext, String locationId, String [] tokensExpr) throws Exception;
	public Location internalSaveLocation(HealthUserContext userContext, Location location) throws Exception;
	public Location internalSaveLocation(HealthUserContext userContext, Location location,Map<String,Object>option) throws Exception;
	
	public Location transferToAnotherDistrict(HealthUserContext userContext, String locationId, String anotherDistrictId)  throws Exception;
 	public Location transferToAnotherProvince(HealthUserContext userContext, String locationId, String anotherProvinceId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String locationId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Location newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  GuardianManager getGuardianManager(HealthUserContext userContext, String locationId, String name, String mobile, String wechatUserId, String platformId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Location addGuardian(HealthUserContext userContext, String locationId, String name, String mobile, String wechatUserId, String platformId, String cqId , String [] tokensExpr)  throws Exception;
	public  Location removeGuardian(HealthUserContext userContext, String locationId, String guardianId, int guardianVersion,String [] tokensExpr)  throws Exception;
	public  Location updateGuardian(HealthUserContext userContext, String locationId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WechatUserManager getWechatUserManager(HealthUserContext userContext, String locationId, String name, String avatar, String userTypeId, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  Location addWechatUser(HealthUserContext userContext, String locationId, String name, String avatar, String userTypeId, String platformId , String [] tokensExpr)  throws Exception;
	public  Location removeWechatUser(HealthUserContext userContext, String locationId, String wechatUserId, int wechatUserVersion,String [] tokensExpr)  throws Exception;
	public  Location updateWechatUser(HealthUserContext userContext, String locationId, String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


