
package com.doublechaintech.health.wechatworkappidentify;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface WechatWorkappIdentifyManager extends BaseManager{

		

	public WechatWorkappIdentify createWechatWorkappIdentify(HealthUserContext userContext, String corpId,String userId,String secUserId,DateTime lastLoginTime) throws Exception;	
	public WechatWorkappIdentify updateWechatWorkappIdentify(HealthUserContext userContext,String wechatWorkappIdentifyId, int wechatWorkappIdentifyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public WechatWorkappIdentify loadWechatWorkappIdentify(HealthUserContext userContext, String wechatWorkappIdentifyId, String [] tokensExpr) throws Exception;
	public WechatWorkappIdentify internalSaveWechatWorkappIdentify(HealthUserContext userContext, WechatWorkappIdentify wechatWorkappIdentify) throws Exception;
	public WechatWorkappIdentify internalSaveWechatWorkappIdentify(HealthUserContext userContext, WechatWorkappIdentify wechatWorkappIdentify,Map<String,Object>option) throws Exception;
	
	public WechatWorkappIdentify transferToAnotherSecUser(HealthUserContext userContext, String wechatWorkappIdentifyId, String anotherSecUserId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String wechatWorkappIdentifyId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, WechatWorkappIdentify newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


