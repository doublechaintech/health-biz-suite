
package com.doublechaintech.health.schoolclass;
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
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;


public interface SchoolClassDAO extends BaseDAO{

	public SmartList<SchoolClass> loadAll();
	public SchoolClass load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<SchoolClass> schoolClassList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public SchoolClass present(SchoolClass schoolClass,Map<String,Object> options) throws Exception;
	public SchoolClass clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public SchoolClass save(SchoolClass schoolClass,Map<String,Object> options);
	public SmartList<SchoolClass> saveSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options);
	public SmartList<SchoolClass> removeSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options);
	public SmartList<SchoolClass> findSchoolClassWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countSchoolClassWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countSchoolClassWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String schoolClassId, int version) throws Exception;
	public SchoolClass disconnectFromAll(String schoolClassId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO();
		
	public StudentDAO getStudentDAO();
		
	public StudentHealthSurveyDAO getStudentHealthSurveyDAO();
		
	
 	public SmartList<SchoolClass> requestCandidateSchoolClassForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<SchoolClass> requestCandidateSchoolClassForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<SchoolClass> requestCandidateSchoolClassForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public SchoolClass planToRemoveClassDailyHealthSurveyList(SchoolClass schoolClass, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect SchoolClass with creator in ClassDailyHealthSurvey
	public SchoolClass planToRemoveClassDailyHealthSurveyListWithCreator(SchoolClass schoolClass, String creatorId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithCreator(String schoolClassId, String creatorId, Map<String,Object> options)throws Exception;
	
	//disconnect SchoolClass with cq in ClassDailyHealthSurvey
	public SchoolClass planToRemoveClassDailyHealthSurveyListWithCq(SchoolClass schoolClass, String cqId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithCq(String schoolClassId, String cqId, Map<String,Object> options)throws Exception;
	
	public SchoolClass planToRemoveStudentList(SchoolClass schoolClass, String studentIds[], Map<String,Object> options)throws Exception;


	//disconnect SchoolClass with guardian in Student
	public SchoolClass planToRemoveStudentListWithGuardian(SchoolClass schoolClass, String guardianId, Map<String,Object> options)throws Exception;
	public int countStudentListWithGuardian(String schoolClassId, String guardianId, Map<String,Object> options)throws Exception;
	
	//disconnect SchoolClass with student_id in Student
	public SchoolClass planToRemoveStudentListWithStudentId(SchoolClass schoolClass, String studentIdId, Map<String,Object> options)throws Exception;
	public int countStudentListWithStudentId(String schoolClassId, String studentIdId, Map<String,Object> options)throws Exception;
	
	//disconnect SchoolClass with cq in Student
	public SchoolClass planToRemoveStudentListWithCq(SchoolClass schoolClass, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentListWithCq(String schoolClassId, String cqId, Map<String,Object> options)throws Exception;
	
	public SchoolClass planToRemoveStudentHealthSurveyList(SchoolClass schoolClass, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect SchoolClass with student in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithStudent(SchoolClass schoolClass, String studentId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithStudent(String schoolClassId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect SchoolClass with survey_status in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithSurveyStatus(SchoolClass schoolClass, String surveyStatusId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSurveyStatus(String schoolClassId, String surveyStatusId, Map<String,Object> options)throws Exception;
	
	//disconnect SchoolClass with class_daily_health_survey in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(SchoolClass schoolClass, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String schoolClassId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect SchoolClass with cq in StudentHealthSurvey
	public SchoolClass planToRemoveStudentHealthSurveyListWithCq(SchoolClass schoolClass, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithCq(String schoolClassId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<SchoolClass> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<SchoolClass> findSchoolClassByClassTeacher(String teacherId, Map<String,Object> options);
 	public int countSchoolClassByClassTeacher(String teacherId, Map<String,Object> options);
 	public Map<String, Integer> countSchoolClassByClassTeacherIds(String[] ids, Map<String,Object> options);
 	public SmartList<SchoolClass> findSchoolClassByClassTeacher(String teacherId, int start, int count, Map<String,Object> options);
 	public void analyzeSchoolClassByClassTeacher(SmartList<SchoolClass> resultList, String teacherId, Map<String,Object> options);

 
  
 	public SmartList<SchoolClass> findSchoolClassByPlatform(String platformId, Map<String,Object> options);
 	public int countSchoolClassByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countSchoolClassByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<SchoolClass> findSchoolClassByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeSchoolClassByPlatform(SmartList<SchoolClass> resultList, String platformId, Map<String,Object> options);

 
  
 	public SmartList<SchoolClass> findSchoolClassByCq(String changeRequestId, Map<String,Object> options);
 	public int countSchoolClassByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countSchoolClassByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<SchoolClass> findSchoolClassByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeSchoolClassByCq(SmartList<SchoolClass> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的schoolClass的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<SchoolClass> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Student的schoolClass的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<SchoolClass> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的schoolClass的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<SchoolClass> us, Map<String,Object> options) throws Exception;
	
}


