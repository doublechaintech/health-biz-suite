
package com.doublechaintech.health.location;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;

import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.student.StudentDAO;
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

	public StudentDAO getStudentDAO();
		
	
 	public SmartList<Location> requestCandidateLocationForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Location planToRemoveStudentList(Location location, String studentIds[], Map<String,Object> options)throws Exception;


	//disconnect Location with user in Student
	public Location planToRemoveStudentListWithUser(Location location, String userId, Map<String,Object> options)throws Exception;
	public int countStudentListWithUser(String locationId, String userId, Map<String,Object> options)throws Exception;
	
	//disconnect Location with platform in Student
	public Location planToRemoveStudentListWithPlatform(Location location, String platformId, Map<String,Object> options)throws Exception;
	public int countStudentListWithPlatform(String locationId, String platformId, Map<String,Object> options)throws Exception;
	
	
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

 
 
	// 需要一个加载引用我的对象的enhance方法:Student的address的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<Location> us, Map<String,Object> options) throws Exception;
	
}


