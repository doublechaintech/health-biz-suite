
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
	

	//public  StudentManager getStudentManager(HealthUserContext userContext, String locationId, String studentName, String studentNumber, String studentAvatar, String guardianName, String guardianMobile, String userId, String platformId, String changeRequestId ,String [] tokensExpr)  throws Exception;
	
	public  Location addStudent(HealthUserContext userContext, String locationId, String studentName, String studentNumber, String studentAvatar, String guardianName, String guardianMobile, String userId, String platformId, String changeRequestId , String [] tokensExpr)  throws Exception;
	public  Location removeStudent(HealthUserContext userContext, String locationId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  Location updateStudent(HealthUserContext userContext, String locationId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  UserManager getUserManager(HealthUserContext userContext, String locationId, String name, String avatar, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  Location addUser(HealthUserContext userContext, String locationId, String name, String avatar, String platformId , String [] tokensExpr)  throws Exception;
	public  Location removeUser(HealthUserContext userContext, String locationId, String userId, int userVersion,String [] tokensExpr)  throws Exception;
	public  Location updateUser(HealthUserContext userContext, String locationId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


