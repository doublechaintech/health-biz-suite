
package com.doublechaintech.health.dailysurveyquestion;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;


public interface DailySurveyQuestionDAO extends BaseDAO{

	public SmartList<DailySurveyQuestion> loadAll();
	public DailySurveyQuestion load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<DailySurveyQuestion> dailySurveyQuestionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public DailySurveyQuestion present(DailySurveyQuestion dailySurveyQuestion,Map<String,Object> options) throws Exception;
	public DailySurveyQuestion clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public DailySurveyQuestion save(DailySurveyQuestion dailySurveyQuestion,Map<String,Object> options);
	public SmartList<DailySurveyQuestion> saveDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options);
	public SmartList<DailySurveyQuestion> removeDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList,Map<String,Object> options);
	public SmartList<DailySurveyQuestion> findDailySurveyQuestionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countDailySurveyQuestionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countDailySurveyQuestionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String dailySurveyQuestionId, int version) throws Exception;
	public DailySurveyQuestion disconnectFromAll(String dailySurveyQuestionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public StudentDailyAnswerDAO getStudentDailyAnswerDAO();
		
	
 	public SmartList<DailySurveyQuestion> requestCandidateDailySurveyQuestionForStudentDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public DailySurveyQuestion planToRemoveStudentDailyAnswerList(DailySurveyQuestion dailySurveyQuestion, String studentDailyAnswerIds[], Map<String,Object> options)throws Exception;


	//disconnect DailySurveyQuestion with student_health_survey in StudentDailyAnswer
	public DailySurveyQuestion planToRemoveStudentDailyAnswerListWithStudentHealthSurvey(DailySurveyQuestion dailySurveyQuestion, String studentHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentDailyAnswerListWithStudentHealthSurvey(String dailySurveyQuestionId, String studentHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect DailySurveyQuestion with cq in StudentDailyAnswer
	public DailySurveyQuestion planToRemoveStudentDailyAnswerListWithCq(DailySurveyQuestion dailySurveyQuestion, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentDailyAnswerListWithCq(String dailySurveyQuestionId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<DailySurveyQuestion> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByQuestionType(String questionTypeId, Map<String,Object> options);
 	public int countDailySurveyQuestionByQuestionType(String questionTypeId, Map<String,Object> options);
 	public Map<String, Integer> countDailySurveyQuestionByQuestionTypeIds(String[] ids, Map<String,Object> options);
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByQuestionType(String questionTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeDailySurveyQuestionByQuestionType(SmartList<DailySurveyQuestion> resultList, String questionTypeId, Map<String,Object> options);

 
  
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByClassDailyHealthSurvey(String classDailyHealthSurveyId, Map<String,Object> options);
 	public int countDailySurveyQuestionByClassDailyHealthSurvey(String classDailyHealthSurveyId, Map<String,Object> options);
 	public Map<String, Integer> countDailySurveyQuestionByClassDailyHealthSurveyIds(String[] ids, Map<String,Object> options);
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByClassDailyHealthSurvey(String classDailyHealthSurveyId, int start, int count, Map<String,Object> options);
 	public void analyzeDailySurveyQuestionByClassDailyHealthSurvey(SmartList<DailySurveyQuestion> resultList, String classDailyHealthSurveyId, Map<String,Object> options);

 
  
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByClassQuestion(String classQuestionId, Map<String,Object> options);
 	public int countDailySurveyQuestionByClassQuestion(String classQuestionId, Map<String,Object> options);
 	public Map<String, Integer> countDailySurveyQuestionByClassQuestionIds(String[] ids, Map<String,Object> options);
 	public SmartList<DailySurveyQuestion> findDailySurveyQuestionByClassQuestion(String classQuestionId, int start, int count, Map<String,Object> options);
 	public void analyzeDailySurveyQuestionByClassQuestion(SmartList<DailySurveyQuestion> resultList, String classQuestionId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:StudentDailyAnswer的question的StudentDailyAnswerList
	public SmartList<StudentDailyAnswer> loadOurStudentDailyAnswerList(HealthUserContext userContext, List<DailySurveyQuestion> us, Map<String,Object> options) throws Exception;
	
}


