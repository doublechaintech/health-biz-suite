
package com.doublechaintech.health.classdailyhealthsurvey;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.user.UserDAO;


public interface ClassDailyHealthSurveyDAO extends BaseDAO{

	public SmartList<ClassDailyHealthSurvey> loadAll();
	public ClassDailyHealthSurvey load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ClassDailyHealthSurvey> classDailyHealthSurveyList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ClassDailyHealthSurvey present(ClassDailyHealthSurvey classDailyHealthSurvey,Map<String,Object> options) throws Exception;
	public ClassDailyHealthSurvey clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ClassDailyHealthSurvey save(ClassDailyHealthSurvey classDailyHealthSurvey,Map<String,Object> options);
	public SmartList<ClassDailyHealthSurvey> saveClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options);
	public SmartList<ClassDailyHealthSurvey> removeClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList,Map<String,Object> options);
	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countClassDailyHealthSurveyWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countClassDailyHealthSurveyWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String classDailyHealthSurveyId, int version) throws Exception;
	public ClassDailyHealthSurvey disconnectFromAll(String classDailyHealthSurveyId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public DailySurveyQuestionDAO getDailySurveyQuestionDAO();
		
	public StudentHealthSurveyDAO getStudentHealthSurveyDAO();
		
	public HealthSurveyReportDAO getHealthSurveyReportDAO();
		
	
 	public SmartList<ClassDailyHealthSurvey> requestCandidateClassDailyHealthSurveyForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ClassDailyHealthSurvey> requestCandidateClassDailyHealthSurveyForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ClassDailyHealthSurvey> requestCandidateClassDailyHealthSurveyForHealthSurveyReport(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ClassDailyHealthSurvey planToRemoveDailySurveyQuestionList(ClassDailyHealthSurvey classDailyHealthSurvey, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect ClassDailyHealthSurvey with question_type in DailySurveyQuestion
	public ClassDailyHealthSurvey planToRemoveDailySurveyQuestionListWithQuestionType(ClassDailyHealthSurvey classDailyHealthSurvey, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithQuestionType(String classDailyHealthSurveyId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect ClassDailyHealthSurvey with survey_question in DailySurveyQuestion
	public ClassDailyHealthSurvey planToRemoveDailySurveyQuestionListWithSurveyQuestion(ClassDailyHealthSurvey classDailyHealthSurvey, String surveyQuestionId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithSurveyQuestion(String classDailyHealthSurveyId, String surveyQuestionId, Map<String,Object> options)throws Exception;
	
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyList(ClassDailyHealthSurvey classDailyHealthSurvey, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect ClassDailyHealthSurvey with student in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithStudent(ClassDailyHealthSurvey classDailyHealthSurvey, String studentId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithStudent(String classDailyHealthSurveyId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect ClassDailyHealthSurvey with survey_status in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithSurveyStatus(ClassDailyHealthSurvey classDailyHealthSurvey, String surveyStatusId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSurveyStatus(String classDailyHealthSurveyId, String surveyStatusId, Map<String,Object> options)throws Exception;
	
	//disconnect ClassDailyHealthSurvey with teacher in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithTeacher(ClassDailyHealthSurvey classDailyHealthSurvey, String teacherId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithTeacher(String classDailyHealthSurveyId, String teacherId, Map<String,Object> options)throws Exception;
	
	//disconnect ClassDailyHealthSurvey with change_request in StudentHealthSurvey
	public ClassDailyHealthSurvey planToRemoveStudentHealthSurveyListWithChangeRequest(ClassDailyHealthSurvey classDailyHealthSurvey, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithChangeRequest(String classDailyHealthSurveyId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public ClassDailyHealthSurvey planToRemoveHealthSurveyReportList(ClassDailyHealthSurvey classDailyHealthSurvey, String healthSurveyReportIds[], Map<String,Object> options)throws Exception;


	//disconnect ClassDailyHealthSurvey with student in HealthSurveyReport
	public ClassDailyHealthSurvey planToRemoveHealthSurveyReportListWithStudent(ClassDailyHealthSurvey classDailyHealthSurvey, String studentId, Map<String,Object> options)throws Exception;
	public int countHealthSurveyReportListWithStudent(String classDailyHealthSurveyId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect ClassDailyHealthSurvey with teacher in HealthSurveyReport
	public ClassDailyHealthSurvey planToRemoveHealthSurveyReportListWithTeacher(ClassDailyHealthSurvey classDailyHealthSurvey, String teacherId, Map<String,Object> options)throws Exception;
	public int countHealthSurveyReportListWithTeacher(String classDailyHealthSurveyId, String teacherId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ClassDailyHealthSurvey> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByTeacher(String teacherId, Map<String,Object> options);
 	public int countClassDailyHealthSurveyByTeacher(String teacherId, Map<String,Object> options);
 	public Map<String, Integer> countClassDailyHealthSurveyByTeacherIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByTeacher(String teacherId, int start, int count, Map<String,Object> options);
 	public void analyzeClassDailyHealthSurveyByTeacher(SmartList<ClassDailyHealthSurvey> resultList, String teacherId, Map<String,Object> options);

 
  
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByCreator(String userId, Map<String,Object> options);
 	public int countClassDailyHealthSurveyByCreator(String userId, Map<String,Object> options);
 	public Map<String, Integer> countClassDailyHealthSurveyByCreatorIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByCreator(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeClassDailyHealthSurveyByCreator(SmartList<ClassDailyHealthSurvey> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countClassDailyHealthSurveyByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countClassDailyHealthSurveyByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassDailyHealthSurvey> findClassDailyHealthSurveyByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeClassDailyHealthSurveyByChangeRequest(SmartList<ClassDailyHealthSurvey> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的classDailyHealthSurvey的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<ClassDailyHealthSurvey> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的classDailyHealthSurvey的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<ClassDailyHealthSurvey> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:HealthSurveyReport的survey的HealthSurveyReportList
	public SmartList<HealthSurveyReport> loadOurHealthSurveyReportList(HealthUserContext userContext, List<ClassDailyHealthSurvey> us, Map<String,Object> options) throws Exception;
	
}


