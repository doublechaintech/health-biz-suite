
package com.doublechaintech.health.wechatlogininfo;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.wechatuser.WechatUserDAO;


public interface WechatLoginInfoDAO extends BaseDAO{

	public SmartList<WechatLoginInfo> loadAll();
	public WechatLoginInfo load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<WechatLoginInfo> wechatLoginInfoList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public WechatLoginInfo present(WechatLoginInfo wechatLoginInfo,Map<String,Object> options) throws Exception;
	public WechatLoginInfo clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public WechatLoginInfo save(WechatLoginInfo wechatLoginInfo,Map<String,Object> options);
	public SmartList<WechatLoginInfo> saveWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList,Map<String,Object> options);
	public SmartList<WechatLoginInfo> removeWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList,Map<String,Object> options);
	public SmartList<WechatLoginInfo> findWechatLoginInfoWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countWechatLoginInfoWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countWechatLoginInfoWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String wechatLoginInfoId, int version) throws Exception;
	public WechatLoginInfo disconnectFromAll(String wechatLoginInfoId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<WechatLoginInfo> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<WechatLoginInfo> findWechatLoginInfoByWechatUser(String wechatUserId, Map<String,Object> options);
 	public int countWechatLoginInfoByWechatUser(String wechatUserId, Map<String,Object> options);
 	public Map<String, Integer> countWechatLoginInfoByWechatUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<WechatLoginInfo> findWechatLoginInfoByWechatUser(String wechatUserId, int start, int count, Map<String,Object> options);
 	public void analyzeWechatLoginInfoByWechatUser(SmartList<WechatLoginInfo> resultList, String wechatUserId, Map<String,Object> options);

 
 
}


