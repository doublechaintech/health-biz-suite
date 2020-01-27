
package com.doublechaintech.health.city;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface CityManager extends BaseManager{

		

	public City createCity(HealthUserContext userContext, String name,String provinceId,String platformId) throws Exception;	
	public City updateCity(HealthUserContext userContext,String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public City loadCity(HealthUserContext userContext, String cityId, String [] tokensExpr) throws Exception;
	public City internalSaveCity(HealthUserContext userContext, City city) throws Exception;
	public City internalSaveCity(HealthUserContext userContext, City city,Map<String,Object>option) throws Exception;
	
	public City transferToAnotherProvince(HealthUserContext userContext, String cityId, String anotherProvinceId)  throws Exception;
 	public City transferToAnotherPlatform(HealthUserContext userContext, String cityId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String cityId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, City newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  DistrictManager getDistrictManager(HealthUserContext userContext, String cityId, String name, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  City addDistrict(HealthUserContext userContext, String cityId, String name, String platformId , String [] tokensExpr)  throws Exception;
	public  City removeDistrict(HealthUserContext userContext, String cityId, String districtId, int districtVersion,String [] tokensExpr)  throws Exception;
	public  City updateDistrict(HealthUserContext userContext, String cityId, String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


