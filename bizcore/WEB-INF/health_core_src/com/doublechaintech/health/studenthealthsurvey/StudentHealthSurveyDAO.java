
package com.doublechaintech.health.studenthealthsurvey;
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
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;

import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.surveystatus.SurveyStatusDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;


public interface StudentHealthSurveyDAO extends BaseDAO{

	public SmartList<StudentHealthSurvey> loadAll();
	public StudentHealthSurvey load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<StudentHealthSurvey> studentHealthSurveyList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public StudentHealthSurvey present(StudentHealthSurvey studentHealthSurvey,Map<String,Object> options) throws Exception;
	public StudentHealthSurvey clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public StudentHealthSurvey save(StudentHealthSurvey studentHealthSurvey,Map<String,Object> options);
	public SmartList<StudentHealthSurvey> saveStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options);
	public SmartList<StudentHealthSurvey> removeStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options);
	public SmartList<StudentHealthSurvey> findStudentHealthSurveyWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countStudentHealthSurveyWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countStudentHealthSurveyWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String studentHealthSurveyId, int version) throws Exception;
	public StudentHealthSurvey disconnectFromAll(String studentHealthSurveyId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public StudentDailyAnswerDAO getStudentDailyAnswerDAO();
		
	
 	public SmartList<StudentHealthSurvey> requestCandidateStudentHealthSurveyForStudentDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public StudentHealthSurvey planToRemoveStudentDailyAnswerList(StudentHealthSurvey studentHealthSurvey, String studentDailyAnswerIds[], Map<String,Object> options)throws Exception;


	//disconnect StudentHealthSurvey with question in StudentDailyAnswer
	public StudentHealthSurvey planToRemoveStudentDailyAnswerListWithQuestion(StudentHealthSurvey studentHealthSurvey, String questionId, Map<String,Object> options)throws Exception;
	public int countStudentDailyAnswerListWithQuestion(String studentHealthSurveyId, String questionId, Map<String,Object> options)throws Exception;
	
	//disconnect StudentHealthSurvey with cq in StudentDailyAnswer
	public StudentHealthSurvey planToRemoveStudentDailyAnswerListWithCq(StudentHealthSurvey studentHealthSurvey, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentDailyAnswerListWithCq(String studentHealthSurveyId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<StudentHealthSurvey> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByStudent(String studentId, Map<String,Object> options);
 	public int countStudentHealthSurveyByStudent(String studentId, Map<String,Object> options);
 	public Map<String, Integer> countStudentHealthSurveyByStudentIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByStudent(String studentId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentHealthSurveyByStudent(SmartList<StudentHealthSurvey> resultList, String studentId, Map<String,Object> options);

 
  
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySurveyStatus(String surveyStatusId, Map<String,Object> options);
 	public int countStudentHealthSurveyBySurveyStatus(String surveyStatusId, Map<String,Object> options);
 	public Map<String, Integer> countStudentHealthSurveyBySurveyStatusIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySurveyStatus(String surveyStatusId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentHealthSurveyBySurveyStatus(SmartList<StudentHealthSurvey> resultList, String surveyStatusId, Map<String,Object> options);

 
  
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySchoolClass(String schoolClassId, Map<String,Object> options);
 	public int countStudentHealthSurveyBySchoolClass(String schoolClassId, Map<String,Object> options);
 	public Map<String, Integer> countStudentHealthSurveyBySchoolClassIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyBySchoolClass(String schoolClassId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentHealthSurveyBySchoolClass(SmartList<StudentHealthSurvey> resultList, String schoolClassId, Map<String,Object> options);

 
  
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByClassDailyHealthSurvey(String classDailyHealthSurveyId, Map<String,Object> options);
 	public int countStudentHealthSurveyByClassDailyHealthSurvey(String classDailyHealthSurveyId, Map<String,Object> options);
 	public Map<String, Integer> countStudentHealthSurveyByClassDailyHealthSurveyIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByClassDailyHealthSurvey(String classDailyHealthSurveyId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentHealthSurveyByClassDailyHealthSurvey(SmartList<StudentHealthSurvey> resultList, String classDailyHealthSurveyId, Map<String,Object> options);

 
  
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByCq(String changeRequestId, Map<String,Object> options);
 	public int countStudentHealthSurveyByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countStudentHealthSurveyByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentHealthSurvey> findStudentHealthSurveyByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentHealthSurveyByCq(SmartList<StudentHealthSurvey> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:StudentDailyAnswer的studentHealthSurvey的StudentDailyAnswerList
	public SmartList<StudentDailyAnswer> loadOurStudentDailyAnswerList(HealthUserContext userContext, List<StudentHealthSurvey> us, Map<String,Object> options) throws Exception;
	
}


