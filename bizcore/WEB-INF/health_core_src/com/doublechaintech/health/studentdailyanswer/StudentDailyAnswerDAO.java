
package com.doublechaintech.health.studentdailyanswer;
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
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;


public interface StudentDailyAnswerDAO extends BaseDAO{

	public SmartList<StudentDailyAnswer> loadAll();
	public StudentDailyAnswer load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<StudentDailyAnswer> studentDailyAnswerList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public StudentDailyAnswer present(StudentDailyAnswer studentDailyAnswer,Map<String,Object> options) throws Exception;
	public StudentDailyAnswer clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public StudentDailyAnswer save(StudentDailyAnswer studentDailyAnswer,Map<String,Object> options);
	public SmartList<StudentDailyAnswer> saveStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options);
	public SmartList<StudentDailyAnswer> removeStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList,Map<String,Object> options);
	public SmartList<StudentDailyAnswer> findStudentDailyAnswerWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countStudentDailyAnswerWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countStudentDailyAnswerWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String studentDailyAnswerId, int version) throws Exception;
	public StudentDailyAnswer disconnectFromAll(String studentDailyAnswerId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<StudentDailyAnswer> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByStudentHealthSurvey(String studentHealthSurveyId, Map<String,Object> options);
 	public int countStudentDailyAnswerByStudentHealthSurvey(String studentHealthSurveyId, Map<String,Object> options);
 	public Map<String, Integer> countStudentDailyAnswerByStudentHealthSurveyIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByStudentHealthSurvey(String studentHealthSurveyId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentDailyAnswerByStudentHealthSurvey(SmartList<StudentDailyAnswer> resultList, String studentHealthSurveyId, Map<String,Object> options);

 
  
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByQuestion(String dailySurveyQuestionId, Map<String,Object> options);
 	public int countStudentDailyAnswerByQuestion(String dailySurveyQuestionId, Map<String,Object> options);
 	public Map<String, Integer> countStudentDailyAnswerByQuestionIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByQuestion(String dailySurveyQuestionId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentDailyAnswerByQuestion(SmartList<StudentDailyAnswer> resultList, String dailySurveyQuestionId, Map<String,Object> options);

 
  
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countStudentDailyAnswerByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countStudentDailyAnswerByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<StudentDailyAnswer> findStudentDailyAnswerByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeStudentDailyAnswerByChangeRequest(SmartList<StudentDailyAnswer> resultList, String changeRequestId, Map<String,Object> options);

 
 
}


