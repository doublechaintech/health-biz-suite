
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
import com.doublechaintech.health.questiontype.QuestionType;

import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.platform.PlatformDAO;


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

 
 
}


