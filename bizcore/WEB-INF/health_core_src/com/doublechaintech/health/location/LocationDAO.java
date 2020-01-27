
package com.doublechaintech.health.location;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.guardian.GuardianDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;
import com.doublechaintech.health.district.DistrictDAO;


public interface LocationDAO extends BaseDAO{

	public SmartList<Location> loadAll();
	public Location load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Location> locationList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Location present(Location location,Map<String,Object> options) throws Exception;
	public Location clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Location save(Location location,Map<String,Object> options);
	public SmartList<Location> saveLocationList(SmartList<Location> locationList,Map<String,Object> options);
	public SmartList<Location> removeLocationList(SmartList<Location> locationList,Map<String,Object> options);
	public SmartList<Location> findLocationWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countLocationWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countLocationWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String locationId, int version) throws Exception;
	public Location disconnectFromAll(String locationId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public GuardianDAO getGuardianDAO();
		
	public WechatUserDAO getWechatUserDAO();
		
	
 	public SmartList<Location> requestCandidateLocationForGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Location> requestCandidateLocationForWechatUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Location planToRemoveGuardianList(Location location, String guardianIds[], Map<String,Object> options)throws Exception;


	//disconnect Location with wechat_user in Guardian
	public Location planToRemoveGuardianListWithWechatUser(Location location, String wechatUserId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithWechatUser(String locationId, String wechatUserId, Map<String,Object> options)throws Exception;
	
	//disconnect Location with platform in Guardian
	public Location planToRemoveGuardianListWithPlatform(Location location, String platformId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithPlatform(String locationId, String platformId, Map<String,Object> options)throws Exception;
	
	//disconnect Location with cq in Guardian
	public Location planToRemoveGuardianListWithCq(Location location, String cqId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithCq(String locationId, String cqId, Map<String,Object> options)throws Exception;
	
	public Location planToRemoveWechatUserList(Location location, String wechatUserIds[], Map<String,Object> options)throws Exception;


	//disconnect Location with user_type in WechatUser
	public Location planToRemoveWechatUserListWithUserType(Location location, String userTypeId, Map<String,Object> options)throws Exception;
	public int countWechatUserListWithUserType(String locationId, String userTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect Location with platform in WechatUser
	public Location planToRemoveWechatUserListWithPlatform(Location location, String platformId, Map<String,Object> options)throws Exception;
	public int countWechatUserListWithPlatform(String locationId, String platformId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Location> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Location> findLocationByDistrict(String districtId, Map<String,Object> options);
 	public int countLocationByDistrict(String districtId, Map<String,Object> options);
 	public Map<String, Integer> countLocationByDistrictIds(String[] ids, Map<String,Object> options);
 	public SmartList<Location> findLocationByDistrict(String districtId, int start, int count, Map<String,Object> options);
 	public void analyzeLocationByDistrict(SmartList<Location> resultList, String districtId, Map<String,Object> options);

 
  
 	public SmartList<Location> findLocationByProvince(String provinceId, Map<String,Object> options);
 	public int countLocationByProvince(String provinceId, Map<String,Object> options);
 	public Map<String, Integer> countLocationByProvinceIds(String[] ids, Map<String,Object> options);
 	public SmartList<Location> findLocationByProvince(String provinceId, int start, int count, Map<String,Object> options);
 	public void analyzeLocationByProvince(SmartList<Location> resultList, String provinceId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Guardian的address的GuardianList
	public SmartList<Guardian> loadOurGuardianList(HealthUserContext userContext, List<Location> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WechatUser的address的WechatUserList
	public SmartList<WechatUser> loadOurWechatUserList(HealthUserContext userContext, List<Location> us, Map<String,Object> options) throws Exception;
	
}


