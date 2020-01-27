
package com.doublechaintech.health.questionsource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;


public interface QuestionSourceDAO extends BaseDAO{

	public SmartList<QuestionSource> loadAll();
	public QuestionSource load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<QuestionSource> questionSourceList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public QuestionSource loadByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public QuestionSource present(QuestionSource questionSource,Map<String,Object> options) throws Exception;
	public QuestionSource clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public QuestionSource cloneByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public QuestionSource save(QuestionSource questionSource,Map<String,Object> options);
	public SmartList<QuestionSource> saveQuestionSourceList(SmartList<QuestionSource> questionSourceList,Map<String,Object> options);
	public SmartList<QuestionSource> removeQuestionSourceList(SmartList<QuestionSource> questionSourceList,Map<String,Object> options);
	public SmartList<QuestionSource> findQuestionSourceWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countQuestionSourceWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countQuestionSourceWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String questionSourceId, int version) throws Exception;
	public QuestionSource disconnectFromAll(String questionSourceId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ClassQuestionDAO getClassQuestionDAO();
		
	
 	public SmartList<QuestionSource> requestCandidateQuestionSourceForClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public QuestionSource planToRemoveClassQuestionList(QuestionSource questionSource, String classQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect QuestionSource with question_type in ClassQuestion
	public QuestionSource planToRemoveClassQuestionListWithQuestionType(QuestionSource questionSource, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithQuestionType(String questionSourceId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect QuestionSource with creator in ClassQuestion
	public QuestionSource planToRemoveClassQuestionListWithCreator(QuestionSource questionSource, String creatorId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithCreator(String questionSourceId, String creatorId, Map<String,Object> options)throws Exception;
	
	//disconnect QuestionSource with cq in ClassQuestion
	public QuestionSource planToRemoveClassQuestionListWithCq(QuestionSource questionSource, String cqId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithCq(String questionSourceId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<QuestionSource> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<QuestionSource> findQuestionSourceByPlatform(String platformId, Map<String,Object> options);
 	public int countQuestionSourceByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countQuestionSourceByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<QuestionSource> findQuestionSourceByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeQuestionSourceByPlatform(SmartList<QuestionSource> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:ClassQuestion的questionSource的ClassQuestionList
	public SmartList<ClassQuestion> loadOurClassQuestionList(HealthUserContext userContext, List<QuestionSource> us, Map<String,Object> options) throws Exception;
	
}


