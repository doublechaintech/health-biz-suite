
package com.doublechaintech.health.question;
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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.user.UserDAO;


public interface QuestionDAO extends BaseDAO{

	public SmartList<Question> loadAll();
	public Question load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Question> questionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Question present(Question question,Map<String,Object> options) throws Exception;
	public Question clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Question save(Question question,Map<String,Object> options);
	public SmartList<Question> saveQuestionList(SmartList<Question> questionList,Map<String,Object> options);
	public SmartList<Question> removeQuestionList(SmartList<Question> questionList,Map<String,Object> options);
	public SmartList<Question> findQuestionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countQuestionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countQuestionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String questionId, int version) throws Exception;
	public Question disconnectFromAll(String questionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public DailySurveyQuestionDAO getDailySurveyQuestionDAO();
		
	
 	public SmartList<Question> requestCandidateQuestionForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Question planToRemoveDailySurveyQuestionList(Question question, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect Question with question_type in DailySurveyQuestion
	public Question planToRemoveDailySurveyQuestionListWithQuestionType(Question question, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithQuestionType(String questionId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect Question with class_daily_health_survey in DailySurveyQuestion
	public Question planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(Question question, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithClassDailyHealthSurvey(String questionId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Question> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<Question> findQuestionByQuestionType(String questionTypeId, Map<String,Object> options);
 	public int countQuestionByQuestionType(String questionTypeId, Map<String,Object> options);
 	public Map<String, Integer> countQuestionByQuestionTypeIds(String[] ids, Map<String,Object> options);
 	public SmartList<Question> findQuestionByQuestionType(String questionTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeQuestionByQuestionType(SmartList<Question> resultList, String questionTypeId, Map<String,Object> options);

 
  
 	public SmartList<Question> findQuestionByPlatform(String platformId, Map<String,Object> options);
 	public int countQuestionByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countQuestionByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Question> findQuestionByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeQuestionByPlatform(SmartList<Question> resultList, String platformId, Map<String,Object> options);

 
  
 	public SmartList<Question> findQuestionByCreator(String userId, Map<String,Object> options);
 	public int countQuestionByCreator(String userId, Map<String,Object> options);
 	public Map<String, Integer> countQuestionByCreatorIds(String[] ids, Map<String,Object> options);
 	public SmartList<Question> findQuestionByCreator(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeQuestionByCreator(SmartList<Question> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<Question> findQuestionByCq(String changeRequestId, Map<String,Object> options);
 	public int countQuestionByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countQuestionByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<Question> findQuestionByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeQuestionByCq(SmartList<Question> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的surveyQuestion的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<Question> us, Map<String,Object> options) throws Exception;
	
}


