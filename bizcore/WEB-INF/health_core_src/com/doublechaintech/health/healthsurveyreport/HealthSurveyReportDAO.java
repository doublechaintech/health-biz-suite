
package com.doublechaintech.health.healthsurveyreport;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;

import com.doublechaintech.health.studentanswer.StudentAnswerDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;


public interface HealthSurveyReportDAO extends BaseDAO{

	public SmartList<HealthSurveyReport> loadAll();
	public HealthSurveyReport load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<HealthSurveyReport> healthSurveyReportList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public HealthSurveyReport present(HealthSurveyReport healthSurveyReport,Map<String,Object> options) throws Exception;
	public HealthSurveyReport clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public HealthSurveyReport save(HealthSurveyReport healthSurveyReport,Map<String,Object> options);
	public SmartList<HealthSurveyReport> saveHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList,Map<String,Object> options);
	public SmartList<HealthSurveyReport> removeHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList,Map<String,Object> options);
	public SmartList<HealthSurveyReport> findHealthSurveyReportWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countHealthSurveyReportWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countHealthSurveyReportWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String healthSurveyReportId, int version) throws Exception;
	public HealthSurveyReport disconnectFromAll(String healthSurveyReportId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public StudentAnswerDAO getStudentAnswerDAO();
		
	
 	public SmartList<HealthSurveyReport> requestCandidateHealthSurveyReportForStudentAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public HealthSurveyReport planToRemoveStudentAnswerList(HealthSurveyReport healthSurveyReport, String studentAnswerIds[], Map<String,Object> options)throws Exception;


	//disconnect HealthSurveyReport with daily_answer in StudentAnswer
	public HealthSurveyReport planToRemoveStudentAnswerListWithDailyAnswer(HealthSurveyReport healthSurveyReport, String dailyAnswerId, Map<String,Object> options)throws Exception;
	public int countStudentAnswerListWithDailyAnswer(String healthSurveyReportId, String dailyAnswerId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<HealthSurveyReport> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByStudent(String studentId, Map<String,Object> options);
 	public int countHealthSurveyReportByStudent(String studentId, Map<String,Object> options);
 	public Map<String, Integer> countHealthSurveyReportByStudentIds(String[] ids, Map<String,Object> options);
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByStudent(String studentId, int start, int count, Map<String,Object> options);
 	public void analyzeHealthSurveyReportByStudent(SmartList<HealthSurveyReport> resultList, String studentId, Map<String,Object> options);

 
  
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByTeacher(String teacherId, Map<String,Object> options);
 	public int countHealthSurveyReportByTeacher(String teacherId, Map<String,Object> options);
 	public Map<String, Integer> countHealthSurveyReportByTeacherIds(String[] ids, Map<String,Object> options);
 	public SmartList<HealthSurveyReport> findHealthSurveyReportByTeacher(String teacherId, int start, int count, Map<String,Object> options);
 	public void analyzeHealthSurveyReportByTeacher(SmartList<HealthSurveyReport> resultList, String teacherId, Map<String,Object> options);

 
  
 	public SmartList<HealthSurveyReport> findHealthSurveyReportBySurvey(String classDailyHealthSurveyId, Map<String,Object> options);
 	public int countHealthSurveyReportBySurvey(String classDailyHealthSurveyId, Map<String,Object> options);
 	public Map<String, Integer> countHealthSurveyReportBySurveyIds(String[] ids, Map<String,Object> options);
 	public SmartList<HealthSurveyReport> findHealthSurveyReportBySurvey(String classDailyHealthSurveyId, int start, int count, Map<String,Object> options);
 	public void analyzeHealthSurveyReportBySurvey(SmartList<HealthSurveyReport> resultList, String classDailyHealthSurveyId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:StudentAnswer的healthSurveyReport的StudentAnswerList
	public SmartList<StudentAnswer> loadOurStudentAnswerList(HealthUserContext userContext, List<HealthSurveyReport> us, Map<String,Object> options) throws Exception;
	
}


