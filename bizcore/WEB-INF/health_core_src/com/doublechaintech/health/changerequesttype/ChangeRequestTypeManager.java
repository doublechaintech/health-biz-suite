
package com.doublechaintech.health.changerequesttype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface ChangeRequestTypeManager extends BaseManager{

		
	

	public ChangeRequestType loadChangeRequestTypeWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public ChangeRequestType createChangeRequestType(HealthUserContext userContext, String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration,String platformId) throws Exception;	
	public ChangeRequestType updateChangeRequestType(HealthUserContext userContext,String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ChangeRequestType loadChangeRequestType(HealthUserContext userContext, String changeRequestTypeId, String [] tokensExpr) throws Exception;
	public ChangeRequestType internalSaveChangeRequestType(HealthUserContext userContext, ChangeRequestType changeRequestType) throws Exception;
	public ChangeRequestType internalSaveChangeRequestType(HealthUserContext userContext, ChangeRequestType changeRequestType,Map<String,Object>option) throws Exception;
	
	public ChangeRequestType transferToAnotherPlatform(HealthUserContext userContext, String changeRequestTypeId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String changeRequestTypeId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, ChangeRequestType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ChangeRequestManager getChangeRequestManager(HealthUserContext userContext, String changeRequestTypeId, String name, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  ChangeRequestType addChangeRequest(HealthUserContext userContext, String changeRequestTypeId, String name, String platformId , String [] tokensExpr)  throws Exception;
	public  ChangeRequestType removeChangeRequest(HealthUserContext userContext, String changeRequestTypeId, String changeRequestId, int changeRequestVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequestType updateChangeRequest(HealthUserContext userContext, String changeRequestTypeId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


