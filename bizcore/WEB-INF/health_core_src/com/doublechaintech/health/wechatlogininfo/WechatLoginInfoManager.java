
package com.doublechaintech.health.wechatlogininfo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface WechatLoginInfoManager extends BaseManager{

		

	public WechatLoginInfo createWechatLoginInfo(HealthUserContext userContext, String wechatUserId,String appId,String openId,String sessionKey) throws Exception;	
	public WechatLoginInfo updateWechatLoginInfo(HealthUserContext userContext,String wechatLoginInfoId, int wechatLoginInfoVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public WechatLoginInfo loadWechatLoginInfo(HealthUserContext userContext, String wechatLoginInfoId, String [] tokensExpr) throws Exception;
	public WechatLoginInfo internalSaveWechatLoginInfo(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo) throws Exception;
	public WechatLoginInfo internalSaveWechatLoginInfo(HealthUserContext userContext, WechatLoginInfo wechatLoginInfo,Map<String,Object>option) throws Exception;
	
	public WechatLoginInfo transferToAnotherWechatUser(HealthUserContext userContext, String wechatLoginInfoId, String anotherWechatUserId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String wechatLoginInfoId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, WechatLoginInfo newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


