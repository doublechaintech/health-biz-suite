
package com.doublechaintech.health.surveystatus;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;


public interface SurveyStatusDAO extends BaseDAO{

	public SmartList<SurveyStatus> loadAll();
	public SurveyStatus load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<SurveyStatus> surveyStatusList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public SurveyStatus loadByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public SurveyStatus present(SurveyStatus surveyStatus,Map<String,Object> options) throws Exception;
	public SurveyStatus clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public SurveyStatus cloneByCode(String code,Map<String,Object> options) throws Exception;
	
	
	public SurveyStatus save(SurveyStatus surveyStatus,Map<String,Object> options);
	public SmartList<SurveyStatus> saveSurveyStatusList(SmartList<SurveyStatus> surveyStatusList,Map<String,Object> options);
	public SmartList<SurveyStatus> removeSurveyStatusList(SmartList<SurveyStatus> surveyStatusList,Map<String,Object> options);
	public SmartList<SurveyStatus> findSurveyStatusWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countSurveyStatusWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countSurveyStatusWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String surveyStatusId, int version) throws Exception;
	public SurveyStatus disconnectFromAll(String surveyStatusId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public StudentHealthSurveyDAO getStudentHealthSurveyDAO();
		
	
 	public SmartList<SurveyStatus> requestCandidateSurveyStatusForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public SurveyStatus planToRemoveStudentHealthSurveyList(SurveyStatus surveyStatus, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect SurveyStatus with student in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithStudent(SurveyStatus surveyStatus, String studentId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithStudent(String surveyStatusId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect SurveyStatus with school_class in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithSchoolClass(SurveyStatus surveyStatus, String schoolClassId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSchoolClass(String surveyStatusId, String schoolClassId, Map<String,Object> options)throws Exception;
	
	//disconnect SurveyStatus with class_daily_health_survey in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(SurveyStatus surveyStatus, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String surveyStatusId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect SurveyStatus with cq in StudentHealthSurvey
	public SurveyStatus planToRemoveStudentHealthSurveyListWithCq(SurveyStatus surveyStatus, String cqId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithCq(String surveyStatusId, String cqId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<SurveyStatus> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<SurveyStatus> findSurveyStatusByPlatform(String platformId, Map<String,Object> options);
 	public int countSurveyStatusByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countSurveyStatusByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<SurveyStatus> findSurveyStatusByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeSurveyStatusByPlatform(SmartList<SurveyStatus> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的surveyStatus的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<SurveyStatus> us, Map<String,Object> options) throws Exception;
	
}


