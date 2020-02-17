
package com.doublechaintech.health.district;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface DistrictManager extends BaseManager{

		

	public District createDistrict(HealthUserContext userContext, String name,String cityId,String platformId) throws Exception;	
	public District updateDistrict(HealthUserContext userContext,String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public District loadDistrict(HealthUserContext userContext, String districtId, String [] tokensExpr) throws Exception;
	public District internalSaveDistrict(HealthUserContext userContext, District district) throws Exception;
	public District internalSaveDistrict(HealthUserContext userContext, District district,Map<String,Object>option) throws Exception;
	
	public District transferToAnotherCity(HealthUserContext userContext, String districtId, String anotherCityId)  throws Exception;
 	public District transferToAnotherPlatform(HealthUserContext userContext, String districtId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String districtId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, District newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  LocationManager getLocationManager(HealthUserContext userContext, String districtId, String name, String address, String provinceId, BigDecimal latitude, BigDecimal longitude ,String [] tokensExpr)  throws Exception;
	
	public  District addLocation(HealthUserContext userContext, String districtId, String name, String address, String provinceId, BigDecimal latitude, BigDecimal longitude , String [] tokensExpr)  throws Exception;
	public  District removeLocation(HealthUserContext userContext, String districtId, String locationId, int locationVersion,String [] tokensExpr)  throws Exception;
	public  District updateLocation(HealthUserContext userContext, String districtId, String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


