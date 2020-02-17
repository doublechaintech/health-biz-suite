
package com.doublechaintech.health.studentanswer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;


public interface StudentAnswerDAO extends BaseDAO{

	public SmartList<StudentAnswer> loadAll();
	public StudentAnswer load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<StudentAnswer> studentAnswerList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public StudentAnswer present(StudentAnswer studentAnswer,Map<String,Object> options) throws Exception;
	public StudentAnswer clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public StudentAnswer save(StudentAnswer studentAnswer,Map<String,Object> options);
	public SmartList<StudentAnswer> saveStudentAnswerList(SmartList<StudentAnswer> studentAnswerList,Map<String,Object> options);
	public SmartList<StudentAnswer> removeStudentAnswerList(SmartList<StudentAnswer> studentAnswerList,Map<String,Object> options);
	public SmartList<StudentAnswer> findStudentAnswerWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countStudentAnswerWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countStudentAnswerWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String studentAnswerId, int version) throws Exception;
	public StudentAnswer disconnectFromAll(String studentAnswerId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<StudentAnswer> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<StudentAnswer> findStudentAnswerByHealthSurveyReport(String healthSurveyReportId, Map<String,Object> options);
 	public int countStudentAnswerByHealthSurveyReport(String healthSurveyReportId, Map<String,Object> options);
 	public Map<String, Integer> countStudentAnswerByHealthSurveyReportIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentAnswer> findStudentAnswerByHealthSurveyReport(String healthSurveyReportId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentAnswerByHealthSurveyReport(SmartList<StudentAnswer> resultList, String healthSurveyReportId, Map<String,Object> options);

 
  
 	public SmartList<StudentAnswer> findStudentAnswerByDailyAnswer(String studentDailyAnswerId, Map<String,Object> options);
 	public int countStudentAnswerByDailyAnswer(String studentDailyAnswerId, Map<String,Object> options);
 	public Map<String, Integer> countStudentAnswerByDailyAnswerIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentAnswer> findStudentAnswerByDailyAnswer(String studentDailyAnswerId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentAnswerByDailyAnswer(SmartList<StudentAnswer> resultList, String studentDailyAnswerId, Map<String,Object> options);

 
 
}


