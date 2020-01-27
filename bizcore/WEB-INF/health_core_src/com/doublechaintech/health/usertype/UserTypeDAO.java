
package com.doublechaintech.health.usertype;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;


public interface UserTypeDAO extends BaseDAO{

	public SmartList<UserType> loadAll();
	public UserType load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<UserType> userTypeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public UserType loadByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public UserType present(UserType userType,Map<String,Object> options) throws Exception;
	public UserType clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public UserType cloneByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public UserType save(UserType userType,Map<String,Object> options);
	public SmartList<UserType> saveUserTypeList(SmartList<UserType> userTypeList,Map<String,Object> options);
	public SmartList<UserType> removeUserTypeList(SmartList<UserType> userTypeList,Map<String,Object> options);
	public SmartList<UserType> findUserTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUserTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUserTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String userTypeId, int version) throws Exception;
	public UserType disconnectFromAll(String userTypeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public WechatUserDAO getWechatUserDAO();
		
	
 	public SmartList<UserType> requestCandidateUserTypeForWechatUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public UserType planToRemoveWechatUserList(UserType userType, String wechatUserIds[], Map<String,Object> options)throws Exception;


	//disconnect UserType with address in WechatUser
	public UserType planToRemoveWechatUserListWithAddress(UserType userType, String addressId, Map<String,Object> options)throws Exception;
	public int countWechatUserListWithAddress(String userTypeId, String addressId, Map<String,Object> options)throws Exception;
	
	//disconnect UserType with platform in WechatUser
	public UserType planToRemoveWechatUserListWithPlatform(UserType userType, String platformId, Map<String,Object> options)throws Exception;
	public int countWechatUserListWithPlatform(String userTypeId, String platformId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<UserType> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<UserType> findUserTypeByPlatform(String platformId, Map<String,Object> options);
 	public int countUserTypeByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countUserTypeByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<UserType> findUserTypeByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeUserTypeByPlatform(SmartList<UserType> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:WechatUser的userType的WechatUserList
	public SmartList<WechatUser> loadOurWechatUserList(HealthUserContext userContext, List<UserType> us, Map<String,Object> options) throws Exception;
	
}


