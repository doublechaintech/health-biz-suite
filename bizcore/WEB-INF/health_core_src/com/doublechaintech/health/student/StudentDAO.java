
package com.doublechaintech.health.student;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;

import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.guardian.GuardianDAO;


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
	
	//disconnect Student with school_class in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithSchoolClass(Student student, String schoolClassId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSchoolClass(String studentId, String schoolClassId, Map<String,Object> options)throws Exception;
	
	//disconnect Student with class_daily_health_survey in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(Student student, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String studentId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect Student with cq in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithCq(Student student, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithCq(String studentId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Student> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Student> findStudentByGuardian(String guardianId, Map<String,Object> options);
 	public int countStudentByGuardian(String guardianId, Map<String,Object> options);
 	public Map<String, Integer> countStudentByGuardianIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentByGuardian(String guardianId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentByGuardian(SmartList<Student> resultList, String guardianId, Map<String,Object> options);

 
  
 	public SmartList<Student> findStudentBySchoolClass(String schoolClassId, Map<String,Object> options);
 	public int countStudentBySchoolClass(String schoolClassId, Map<String,Object> options);
 	public Map<String, Integer> countStudentBySchoolClassIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentBySchoolClass(String schoolClassId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentBySchoolClass(SmartList<Student> resultList, String schoolClassId, Map<String,Object> options);

 
  
 	public SmartList<Student> findStudentByCq(String changeRequestId, Map<String,Object> options);
 	public int countStudentByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countStudentByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<Student> findStudentByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentByCq(SmartList<Student> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的student的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<Student> us, Map<String,Object> options) throws Exception;
	
}


