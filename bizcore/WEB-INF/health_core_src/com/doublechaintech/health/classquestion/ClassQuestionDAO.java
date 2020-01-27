
package com.doublechaintech.health.classquestion;
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
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.questionsource.QuestionSourceDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;


public interface ClassQuestionDAO extends BaseDAO{

	public SmartList<ClassQuestion> loadAll();
	public ClassQuestion load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ClassQuestion> classQuestionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ClassQuestion present(ClassQuestion classQuestion,Map<String,Object> options) throws Exception;
	public ClassQuestion clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ClassQuestion save(ClassQuestion classQuestion,Map<String,Object> options);
	public SmartList<ClassQuestion> saveClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options);
	public SmartList<ClassQuestion> removeClassQuestionList(SmartList<ClassQuestion> classQuestionList,Map<String,Object> options);
	public SmartList<ClassQuestion> findClassQuestionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countClassQuestionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countClassQuestionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String classQuestionId, int version) throws Exception;
	public ClassQuestion disconnectFromAll(String classQuestionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public DailySurveyQuestionDAO getDailySurveyQuestionDAO();
		
	
 	public SmartList<ClassQuestion> requestCandidateClassQuestionForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ClassQuestion planToRemoveDailySurveyQuestionList(ClassQuestion classQuestion, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect ClassQuestion with question_type in DailySurveyQuestion
	public ClassQuestion planToRemoveDailySurveyQuestionListWithQuestionType(ClassQuestion classQuestion, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithQuestionType(String classQuestionId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect ClassQuestion with class_daily_health_survey in DailySurveyQuestion
	public ClassQuestion planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(ClassQuestion classQuestion, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithClassDailyHealthSurvey(String classQuestionId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ClassQuestion> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<ClassQuestion> findClassQuestionByQuestionType(String questionTypeId, Map<String,Object> options);
 	public int countClassQuestionByQuestionType(String questionTypeId, Map<String,Object> options);
 	public Map<String, Integer> countClassQuestionByQuestionTypeIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassQuestion> findClassQuestionByQuestionType(String questionTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeClassQuestionByQuestionType(SmartList<ClassQuestion> resultList, String questionTypeId, Map<String,Object> options);

 
  
 	public SmartList<ClassQuestion> findClassQuestionByQuestionSource(String questionSourceId, Map<String,Object> options);
 	public int countClassQuestionByQuestionSource(String questionSourceId, Map<String,Object> options);
 	public Map<String, Integer> countClassQuestionByQuestionSourceIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassQuestion> findClassQuestionByQuestionSource(String questionSourceId, int start, int count, Map<String,Object> options);
 	public void analyzeClassQuestionByQuestionSource(SmartList<ClassQuestion> resultList, String questionSourceId, Map<String,Object> options);

 
  
 	public SmartList<ClassQuestion> findClassQuestionByCreator(String wechatUserId, Map<String,Object> options);
 	public int countClassQuestionByCreator(String wechatUserId, Map<String,Object> options);
 	public Map<String, Integer> countClassQuestionByCreatorIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassQuestion> findClassQuestionByCreator(String wechatUserId, int start, int count, Map<String,Object> options);
 	public void analyzeClassQuestionByCreator(SmartList<ClassQuestion> resultList, String wechatUserId, Map<String,Object> options);

 
  
 	public SmartList<ClassQuestion> findClassQuestionByCq(String changeRequestId, Map<String,Object> options);
 	public int countClassQuestionByCq(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countClassQuestionByCqIds(String[] ids, Map<String,Object> options);
 	public SmartList<ClassQuestion> findClassQuestionByCq(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeClassQuestionByCq(SmartList<ClassQuestion> resultList, String changeRequestId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的classQuestion的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<ClassQuestion> us, Map<String,Object> options) throws Exception;
	
}


