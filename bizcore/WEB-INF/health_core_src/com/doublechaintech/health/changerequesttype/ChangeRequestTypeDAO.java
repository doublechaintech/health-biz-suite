
package com.doublechaintech.health.changerequesttype;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;

import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.platform.PlatformDAO;


public interface ChangeRequestTypeDAO extends BaseDAO{

	public SmartList<ChangeRequestType> loadAll();
	public ChangeRequestType load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ChangeRequestType> changeRequestTypeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ChangeRequestType loadByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public ChangeRequestType present(ChangeRequestType changeRequestType,Map<String,Object> options) throws Exception;
	public ChangeRequestType clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ChangeRequestType cloneByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public ChangeRequestType save(ChangeRequestType changeRequestType,Map<String,Object> options);
	public SmartList<ChangeRequestType> saveChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList,Map<String,Object> options);
	public SmartList<ChangeRequestType> removeChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList,Map<String,Object> options);
	public SmartList<ChangeRequestType> findChangeRequestTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countChangeRequestTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countChangeRequestTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String changeRequestTypeId, int version) throws Exception;
	public ChangeRequestType disconnectFromAll(String changeRequestTypeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ChangeRequestDAO getChangeRequestDAO();
		
	
 	public SmartList<ChangeRequestType> requestCandidateChangeRequestTypeForChangeRequest(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ChangeRequestType planToRemoveChangeRequestList(ChangeRequestType changeRequestType, String changeRequestIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequestType with platform in ChangeRequest
	public ChangeRequestType planToRemoveChangeRequestListWithPlatform(ChangeRequestType changeRequestType, String platformId, Map<String,Object> options)throws Exception;
	public int countChangeRequestListWithPlatform(String changeRequestTypeId, String platformId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ChangeRequestType> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<ChangeRequestType> findChangeRequestTypeByPlatform(String platformId, Map<String,Object> options);
 	public int countChangeRequestTypeByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestTypeByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequestType> findChangeRequestTypeByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestTypeByPlatform(SmartList<ChangeRequestType> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:ChangeRequest的requestType的ChangeRequestList
	public SmartList<ChangeRequest> loadOurChangeRequestList(HealthUserContext userContext, List<ChangeRequestType> us, Map<String,Object> options) throws Exception;
	
}


