
package com.doublechaintech.health.teacher;
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
import com.doublechaintech.health.schoolclass.SchoolClass;

import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.platform.PlatformDAO;


public interface TeacherDAO extends BaseDAO{

	public SmartList<Teacher> loadAll();
	public Teacher load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Teacher> teacherList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Teacher present(Teacher teacher,Map<String,Object> options) throws Exception;
	public Teacher clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Teacher save(Teacher teacher,Map<String,Object> options);
	public SmartList<Teacher> saveTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options);
	public SmartList<Teacher> removeTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options);
	public SmartList<Teacher> findTeacherWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countTeacherWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countTeacherWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String teacherId, int version) throws Exception;
	public Teacher disconnectFromAll(String teacherId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public SchoolClassDAO getSchoolClassDAO();
		
	
 	public SmartList<Teacher> requestCandidateTeacherForSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Teacher planToRemoveSchoolClassList(Teacher teacher, String schoolClassIds[], Map<String,Object> options)throws Exception;


	//disconnect Teacher with platform in SchoolClass
	public Teacher planToRemoveSchoolClassListWithPlatform(Teacher teacher, String platformId, Map<String,Object> options)throws Exception;
	public int countSchoolClassListWithPlatform(String teacherId, String platformId, Map<String,Object> options)throws Exception;
	
	//disconnect Teacher with cq in SchoolClass
	public Teacher planToRemoveSchoolClassListWithCq(Teacher teacher, String cqId, Map<String,Object> options)throws Exception;
	public int countSchoolClassListWithCq(String teacherId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Teacher> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Teacher> findTeacherByPlatform(String platformId, Map<String,Object> options);
 	public int countTeacherByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countTeacherByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Teacher> findTeacherByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeTeacherByPlatform(SmartList<Teacher> resultList, String platformId, Map<String,Object> options);

 
  
 	public SmartList<Teacher> findTeacherByCq(String changeRequestId, Map<String,Object> options);
 	public int countTeacherByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countTeacherByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<Teacher> findTeacherByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeTeacherByCq(SmartList<Teacher> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:SchoolClass的classTeacher的SchoolClassList
	public SmartList<SchoolClass> loadOurSchoolClassList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception;
	
}


