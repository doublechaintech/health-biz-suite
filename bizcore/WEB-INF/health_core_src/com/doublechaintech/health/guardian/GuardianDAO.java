
package com.doublechaintech.health.guardian;
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
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;


public interface GuardianDAO extends BaseDAO{

	public SmartList<Guardian> loadAll();
	public Guardian load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Guardian> guardianList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Guardian present(Guardian guardian,Map<String,Object> options) throws Exception;
	public Guardian clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Guardian save(Guardian guardian,Map<String,Object> options);
	public SmartList<Guardian> saveGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options);
	public SmartList<Guardian> removeGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options);
	public SmartList<Guardian> findGuardianWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countGuardianWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countGuardianWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String guardianId, int version) throws Exception;
	public Guardian disconnectFromAll(String guardianId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public StudentDAO getStudentDAO();
		
	
 	public SmartList<Guardian> requestCandidateGuardianForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Guardian planToRemoveStudentList(Guardian guardian, String studentIds[], Map<String,Object> options)throws Exception;


	//disconnect Guardian with school_class in Student
	public Guardian planToRemoveStudentListWithSchoolClass(Guardian guardian, String schoolClassId, Map<String,Object> options)throws Exception;
	public int countStudentListWithSchoolClass(String guardianId, String schoolClassId, Map<String,Object> options)throws Exception;
	
	//disconnect Guardian with student_id in Student
	public Guardian planToRemoveStudentListWithStudentId(Guardian guardian, String studentIdId, Map<String,Object> options)throws Exception;
	public int countStudentListWithStudentId(String guardianId, String studentIdId, Map<String,Object> options)throws Exception;
	
	//disconnect Guardian with cq in Student
	public Guardian planToRemoveStudentListWithCq(Guardian guardian, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentListWithCq(String guardianId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Guardian> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Guardian> findGuardianByAddress(String locationId, Map<String,Object> options);
 	public int countGuardianByAddress(String locationId, Map<String,Object> options);
 	public Map<String, Integer> countGuardianByAddressIds(String[] ids, Map<String,Object> options);
 	public SmartList<Guardian> findGuardianByAddress(String locationId, int start, int count, Map<String,Object> options);
 	public void analyzeGuardianByAddress(SmartList<Guardian> resultList, String locationId, Map<String,Object> options);

 
  
 	public SmartList<Guardian> findGuardianByWechatUser(String wechatUserId, Map<String,Object> options);
 	public int countGuardianByWechatUser(String wechatUserId, Map<String,Object> options);
 	public Map<String, Integer> countGuardianByWechatUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<Guardian> findGuardianByWechatUser(String wechatUserId, int start, int count, Map<String,Object> options);
 	public void analyzeGuardianByWechatUser(SmartList<Guardian> resultList, String wechatUserId, Map<String,Object> options);

 
  
 	public SmartList<Guardian> findGuardianByPlatform(String platformId, Map<String,Object> options);
 	public int countGuardianByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countGuardianByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Guardian> findGuardianByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeGuardianByPlatform(SmartList<Guardian> resultList, String platformId, Map<String,Object> options);

 
  
 	public SmartList<Guardian> findGuardianByCq(String changeRequestId, Map<String,Object> options);
 	public int countGuardianByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countGuardianByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<Guardian> findGuardianByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeGuardianByCq(SmartList<Guardian> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Student的guardian的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<Guardian> us, Map<String,Object> options) throws Exception;
	
}


