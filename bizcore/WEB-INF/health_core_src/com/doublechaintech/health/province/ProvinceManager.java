
package com.doublechaintech.health.province;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface ProvinceManager extends BaseManager{

		

	public Province createProvince(HealthUserContext userContext, String name,String platformId) throws Exception;	
	public Province updateProvince(HealthUserContext userContext,String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Province loadProvince(HealthUserContext userContext, String provinceId, String [] tokensExpr) throws Exception;
	public Province internalSaveProvince(HealthUserContext userContext, Province province) throws Exception;
	public Province internalSaveProvince(HealthUserContext userContext, Province province,Map<String,Object>option) throws Exception;
	
	public Province transferToAnotherPlatform(HealthUserContext userContext, String provinceId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String provinceId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Province newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  CityManager getCityManager(HealthUserContext userContext, String provinceId, String name, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  Province addCity(HealthUserContext userContext, String provinceId, String name, String platformId , String [] tokensExpr)  throws Exception;
	public  Province removeCity(HealthUserContext userContext, String provinceId, String cityId, int cityVersion,String [] tokensExpr)  throws Exception;
	public  Province updateCity(HealthUserContext userContext, String provinceId, String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  LocationManager getLocationManager(HealthUserContext userContext, String provinceId, String name, String address, String districtId, BigDecimal latitude, BigDecimal longitude ,String [] tokensExpr)  throws Exception;
	
	public  Province addLocation(HealthUserContext userContext, String provinceId, String name, String address, String districtId, BigDecimal latitude, BigDecimal longitude , String [] tokensExpr)  throws Exception;
	public  Province removeLocation(HealthUserContext userContext, String provinceId, String locationId, int locationVersion,String [] tokensExpr)  throws Exception;
	public  Province updateLocation(HealthUserContext userContext, String provinceId, String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


