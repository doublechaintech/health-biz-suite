
package com.doublechaintech.health.questiontype;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;
import com.doublechaintech.health.question.QuestionDAO;


public interface QuestionTypeDAO extends BaseDAO{

	public SmartList<QuestionType> loadAll();
	public QuestionType load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<QuestionType> questionTypeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public QuestionType loadByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public QuestionType present(QuestionType questionType,Map<String,Object> options) throws Exception;
	public QuestionType clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public QuestionType cloneByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public QuestionType save(QuestionType questionType,Map<String,Object> options);
	public SmartList<QuestionType> saveQuestionTypeList(SmartList<QuestionType> questionTypeList,Map<String,Object> options);
	public SmartList<QuestionType> removeQuestionTypeList(SmartList<QuestionType> questionTypeList,Map<String,Object> options);
	public SmartList<QuestionType> findQuestionTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countQuestionTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countQuestionTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String questionTypeId, int version) throws Exception;
	public QuestionType disconnectFromAll(String questionTypeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public QuestionDAO getQuestionDAO();
		
	public ClassQuestionDAO getClassQuestionDAO();
		
	public DailySurveyQuestionDAO getDailySurveyQuestionDAO();
		
	
 	public SmartList<QuestionType> requestCandidateQuestionTypeForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<QuestionType> requestCandidateQuestionTypeForClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<QuestionType> requestCandidateQuestionTypeForDailySurveyQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public QuestionType planToRemoveQuestionList(QuestionType questionType, String questionIds[], Map<String,Object> options)throws Exception;


	//disconnect QuestionType with platform in Question
	public QuestionType planToRemoveQuestionListWithPlatform(QuestionType questionType, String platformId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithPlatform(String questionTypeId, String platformId, Map<String,Object> options)throws Exception;
	
	public QuestionType planToRemoveClassQuestionList(QuestionType questionType, String classQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect QuestionType with question_source in ClassQuestion
	public QuestionType planToRemoveClassQuestionListWithQuestionSource(QuestionType questionType, String questionSourceId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithQuestionSource(String questionTypeId, String questionSourceId, Map<String,Object> options)throws Exception;
	
	//disconnect QuestionType with creator in ClassQuestion
	public QuestionType planToRemoveClassQuestionListWithCreator(QuestionType questionType, String creatorId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithCreator(String questionTypeId, String creatorId, Map<String,Object> options)throws Exception;
	
	//disconnect QuestionType with cq in ClassQuestion
	public QuestionType planToRemoveClassQuestionListWithCq(QuestionType questionType, String cqId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithCq(String questionTypeId, String cqId, Map<String,Object> options)throws Exception;
	
	public QuestionType planToRemoveDailySurveyQuestionList(QuestionType questionType, String dailySurveyQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect QuestionType with class_daily_health_survey in DailySurveyQuestion
	public QuestionType planToRemoveDailySurveyQuestionListWithClassDailyHealthSurvey(QuestionType questionType, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithClassDailyHealthSurvey(String questionTypeId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect QuestionType with class_question in DailySurveyQuestion
	public QuestionType planToRemoveDailySurveyQuestionListWithClassQuestion(QuestionType questionType, String classQuestionId, Map<String,Object> options)throws Exception;
	public int countDailySurveyQuestionListWithClassQuestion(String questionTypeId, String classQuestionId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<QuestionType> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<QuestionType> findQuestionTypeByPlatform(String platformId, Map<String,Object> options);
 	public int countQuestionTypeByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countQuestionTypeByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<QuestionType> findQuestionTypeByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeQuestionTypeByPlatform(SmartList<QuestionType> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Question的questionType的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<QuestionType> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ClassQuestion的questionType的ClassQuestionList
	public SmartList<ClassQuestion> loadOurClassQuestionList(HealthUserContext userContext, List<QuestionType> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:DailySurveyQuestion的questionType的DailySurveyQuestionList
	public SmartList<DailySurveyQuestion> loadOurDailySurveyQuestionList(HealthUserContext userContext, List<QuestionType> us, Map<String,Object> options) throws Exception;
	
}


