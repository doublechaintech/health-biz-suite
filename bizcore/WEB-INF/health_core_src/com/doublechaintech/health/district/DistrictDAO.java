
package com.doublechaintech.health.district;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.city.City;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;

import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.platform.PlatformDAO;


public interface DistrictDAO extends BaseDAO{

	public SmartList<District> loadAll();
	public District load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<District> districtList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public District present(District district,Map<String,Object> options) throws Exception;
	public District clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public District save(District district,Map<String,Object> options);
	public SmartList<District> saveDistrictList(SmartList<District> districtList,Map<String,Object> options);
	public SmartList<District> removeDistrictList(SmartList<District> districtList,Map<String,Object> options);
	public SmartList<District> findDistrictWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countDistrictWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countDistrictWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String districtId, int version) throws Exception;
	public District disconnectFromAll(String districtId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public LocationDAO getLocationDAO();
		
	
 	public SmartList<District> requestCandidateDistrictForLocation(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public District planToRemoveLocationList(District district, String locationIds[], Map<String,Object> options)throws Exception;


	//disconnect District with province in Location
	public District planToRemoveLocationListWithProvince(District district, String provinceId, Map<String,Object> options)throws Exception;
	public int countLocationListWithProvince(String districtId, String provinceId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<District> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<District> findDistrictByCity(String cityId, Map<String,Object> options);
 	public int countDistrictByCity(String cityId, Map<String,Object> options);
 	public Map<String, Integer> countDistrictByCityIds(String[] ids, Map<String,Object> options);
 	public SmartList<District> findDistrictByCity(String cityId, int start, int count, Map<String,Object> options);
 	public void analyzeDistrictByCity(SmartList<District> resultList, String cityId, Map<String,Object> options);

 
  
 	public SmartList<District> findDistrictByPlatform(String platformId, Map<String,Object> options);
 	public int countDistrictByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countDistrictByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<District> findDistrictByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeDistrictByPlatform(SmartList<District> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Location的district的LocationList
	public SmartList<Location> loadOurLocationList(HealthUserContext userContext, List<District> us, Map<String,Object> options) throws Exception;
	
}


