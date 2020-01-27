
package com.doublechaintech.health.student;
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
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.user.UserDAO;


public interface StudentDAO extends BaseDAO{

	public SmartList<Student> loadAll();
	public Student load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Student> studentList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Student present(Student student,Map<String,Object> options) throws Exception;
	public Student clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Student save(Student student,Map<String,Object> options);
	public SmartList<Student> saveStudentList(SmartList<Student> studentList,Map<String,Object> options);
	public SmartList<Student> removeStudentList(SmartList<Student> studentList,Map<String,Object> options);
	public SmartList<Student> findStudentWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countStudentWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countStudentWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String studentId, int version) throws Exception;
	public Student disconnectFromAll(String studentId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public StudentHealthSurveyDAO getStudentHealthSurveyDAO();
		
	
 	public SmartList<Student> requestCandidateStudentForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Student planToRemoveStudentHealthSurveyList(Student student, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect Student with survey_status in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithSurveyStatus(Student student, String surveyStatusId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSurveyStatus(String studentId, String surveyStatusId, Map<String,Object> options)throws Exception;
	
	//disconnect Student with teacher in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithTeacher(Student student, String teacherId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithTeacher(String studentId, String teacherId, Map<String,Object> options)throws Exception;
	
	//disconnect Student with class_daily_health_survey in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(Student student, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String studentId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect Student with change_request in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithChangeRequest(Student student, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithChangeRequest(String studentId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Student> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Student> findStudentByAddress(String locationId, Map<String,Object> options);
 	public int countStudentByAddress(String locationId, Map<String,Object> options);
 	public Map<String, Integer> countStudentByAddressIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentByAddress(String locationId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentByAddress(SmartList<Student> resultList, String locationId, Map<String,Object> options);

 
  
 	public SmartList<Student> findStudentByUser(String userId, Map<String,Object> options);
 	public int countStudentByUser(String userId, Map<String,Object> options);
 	public Map<String, Integer> countStudentByUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentByUser(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentByUser(SmartList<Student> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<Student> findStudentByPlatform(String platformId, Map<String,Object> options);
 	public int countStudentByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countStudentByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentByPlatform(SmartList<Student> resultList, String platformId, Map<String,Object> options);

 
  
 	public SmartList<Student> findStudentByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countStudentByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countStudentByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentByChangeRequest(SmartList<Student> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的student的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<Student> us, Map<String,Object> options) throws Exception;
	
}


