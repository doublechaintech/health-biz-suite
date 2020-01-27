
package com.doublechaintech.health.quicklink;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface QuickLinkManager extends BaseManager{

		

	public QuickLink createQuickLink(HealthUserContext userContext, String name,String icon,String imagePath,String linkTarget,String appId) throws Exception;	
	public QuickLink updateQuickLink(HealthUserContext userContext,String quickLinkId, int quickLinkVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public QuickLink loadQuickLink(HealthUserContext userContext, String quickLinkId, String [] tokensExpr) throws Exception;
	public QuickLink internalSaveQuickLink(HealthUserContext userContext, QuickLink quickLink) throws Exception;
	public QuickLink internalSaveQuickLink(HealthUserContext userContext, QuickLink quickLink,Map<String,Object>option) throws Exception;
	
	public QuickLink transferToAnotherApp(HealthUserContext userContext, String quickLinkId, String anotherAppId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String quickLinkId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, QuickLink newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


