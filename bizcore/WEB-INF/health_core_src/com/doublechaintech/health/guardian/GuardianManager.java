
package com.doublechaintech.health.guardian;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface GuardianManager extends BaseManager{

		

	public Guardian createGuardian(HealthUserContext userContext, String name,String mobile,String addressId,String wechatUserId,String platformId,String cqId) throws Exception;	
	public Guardian updateGuardian(HealthUserContext userContext,String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Guardian loadGuardian(HealthUserContext userContext, String guardianId, String [] tokensExpr) throws Exception;
	public Guardian internalSaveGuardian(HealthUserContext userContext, Guardian guardian) throws Exception;
	public Guardian internalSaveGuardian(HealthUserContext userContext, Guardian guardian,Map<String,Object>option) throws Exception;
	
	public Guardian transferToAnotherAddress(HealthUserContext userContext, String guardianId, String anotherAddressId)  throws Exception;
 	public Guardian transferToAnotherWechatUser(HealthUserContext userContext, String guardianId, String anotherWechatUserId)  throws Exception;
 	public Guardian transferToAnotherPlatform(HealthUserContext userContext, String guardianId, String anotherPlatformId)  throws Exception;
 	public Guardian transferToAnotherCq(HealthUserContext userContext, String guardianId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String guardianId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Guardian newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  StudentManager getStudentManager(HealthUserContext userContext, String guardianId, String name, String gender, String schoolClassId, String studentId, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Guardian addStudent(HealthUserContext userContext, String guardianId, String name, String gender, String schoolClassId, String studentId, String cqId , String [] tokensExpr)  throws Exception;
	public  Guardian removeStudent(HealthUserContext userContext, String guardianId, String studentId, int studentVersion,String [] tokensExpr)  throws Exception;
	public  Guardian updateStudent(HealthUserContext userContext, String guardianId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


