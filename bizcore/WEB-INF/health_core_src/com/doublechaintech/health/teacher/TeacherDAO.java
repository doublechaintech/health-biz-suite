
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
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.user.UserDAO;


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

	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO();
		
	public StudentHealthSurveyDAO getStudentHealthSurveyDAO();
		
	public HealthSurveyReportDAO getHealthSurveyReportDAO();
		
	
 	public SmartList<Teacher> requestCandidateTeacherForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Teacher> requestCandidateTeacherForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Teacher> requestCandidateTeacherForHealthSurveyReport(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Teacher planToRemoveClassDailyHealthSurveyList(Teacher teacher, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect Teacher with creator in ClassDailyHealthSurvey
	public Teacher planToRemoveClassDailyHealthSurveyListWithCreator(Teacher teacher, String creatorId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithCreator(String teacherId, String creatorId, Map<String,Object> options)throws Exception;
	
	//disconnect Teacher with change_request in ClassDailyHealthSurvey
	public Teacher planToRemoveClassDailyHealthSurveyListWithChangeRequest(Teacher teacher, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithChangeRequest(String teacherId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public Teacher planToRemoveStudentHealthSurveyList(Teacher teacher, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect Teacher with student in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithStudent(Teacher teacher, String studentId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithStudent(String teacherId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect Teacher with survey_status in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithSurveyStatus(Teacher teacher, String surveyStatusId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSurveyStatus(String teacherId, String surveyStatusId, Map<String,Object> options)throws Exception;
	
	//disconnect Teacher with class_daily_health_survey in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(Teacher teacher, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String teacherId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect Teacher with change_request in StudentHealthSurvey
	public Teacher planToRemoveStudentHealthSurveyListWithChangeRequest(Teacher teacher, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithChangeRequest(String teacherId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public Teacher planToRemoveHealthSurveyReportList(Teacher teacher, String healthSurveyReportIds[], Map<String,Object> options)throws Exception;


	//disconnect Teacher with student in HealthSurveyReport
	public Teacher planToRemoveHealthSurveyReportListWithStudent(Teacher teacher, String studentId, Map<String,Object> options)throws Exception;
	public int countHealthSurveyReportListWithStudent(String teacherId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect Teacher with survey in HealthSurveyReport
	public Teacher planToRemoveHealthSurveyReportListWithSurvey(Teacher teacher, String surveyId, Map<String,Object> options)throws Exception;
	public int countHealthSurveyReportListWithSurvey(String teacherId, String surveyId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Teacher> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Teacher> findTeacherByPlatform(String platformId, Map<String,Object> options);
 	public int countTeacherByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countTeacherByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Teacher> findTeacherByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeTeacherByPlatform(SmartList<Teacher> resultList, String platformId, Map<String,Object> options);

 
  
 	public SmartList<Teacher> findTeacherByUser(String userId, Map<String,Object> options);
 	public int countTeacherByUser(String userId, Map<String,Object> options);
 	public Map<String, Integer> countTeacherByUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<Teacher> findTeacherByUser(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeTeacherByUser(SmartList<Teacher> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<Teacher> findTeacherByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countTeacherByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countTeacherByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<Teacher> findTeacherByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeTeacherByChangeRequest(SmartList<Teacher> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的teacher的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的teacher的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:HealthSurveyReport的teacher的HealthSurveyReportList
	public SmartList<HealthSurveyReport> loadOurHealthSurveyReportList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception;
	
}


