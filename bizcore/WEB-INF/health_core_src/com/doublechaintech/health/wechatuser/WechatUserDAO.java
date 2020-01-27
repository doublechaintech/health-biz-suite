
package com.doublechaintech.health.wechatuser;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.usertype.UserType;

import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.usertype.UserTypeDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.guardian.GuardianDAO;
import com.doublechaintech.health.classquestion.ClassQuestionDAO;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoDAO;


public interface WechatUserDAO extends BaseDAO{

	public SmartList<WechatUser> loadAll();
	public WechatUser load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<WechatUser> wechatUserList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public WechatUser present(WechatUser wechatUser,Map<String,Object> options) throws Exception;
	public WechatUser clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public WechatUser save(WechatUser wechatUser,Map<String,Object> options);
	public SmartList<WechatUser> saveWechatUserList(SmartList<WechatUser> wechatUserList,Map<String,Object> options);
	public SmartList<WechatUser> removeWechatUserList(SmartList<WechatUser> wechatUserList,Map<String,Object> options);
	public SmartList<WechatUser> findWechatUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countWechatUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countWechatUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String wechatUserId, int version) throws Exception;
	public WechatUser disconnectFromAll(String wechatUserId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public GuardianDAO getGuardianDAO();
		
	public ClassQuestionDAO getClassQuestionDAO();
		
	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO();
		
	public WechatLoginInfoDAO getWechatLoginInfoDAO();
		
	
 	public SmartList<WechatUser> requestCandidateWechatUserForGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<WechatUser> requestCandidateWechatUserForClassQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<WechatUser> requestCandidateWechatUserForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<WechatUser> requestCandidateWechatUserForWechatLoginInfo(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public WechatUser planToRemoveGuardianList(WechatUser wechatUser, String guardianIds[], Map<String,Object> options)throws Exception;


	//disconnect WechatUser with address in Guardian
	public WechatUser planToRemoveGuardianListWithAddress(WechatUser wechatUser, String addressId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithAddress(String wechatUserId, String addressId, Map<String,Object> options)throws Exception;
	
	//disconnect WechatUser with platform in Guardian
	public WechatUser planToRemoveGuardianListWithPlatform(WechatUser wechatUser, String platformId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithPlatform(String wechatUserId, String platformId, Map<String,Object> options)throws Exception;
	
	//disconnect WechatUser with cq in Guardian
	public WechatUser planToRemoveGuardianListWithCq(WechatUser wechatUser, String cqId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithCq(String wechatUserId, String cqId, Map<String,Object> options)throws Exception;
	
	public WechatUser planToRemoveClassQuestionList(WechatUser wechatUser, String classQuestionIds[], Map<String,Object> options)throws Exception;


	//disconnect WechatUser with question_type in ClassQuestion
	public WechatUser planToRemoveClassQuestionListWithQuestionType(WechatUser wechatUser, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithQuestionType(String wechatUserId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect WechatUser with question_source in ClassQuestion
	public WechatUser planToRemoveClassQuestionListWithQuestionSource(WechatUser wechatUser, String questionSourceId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithQuestionSource(String wechatUserId, String questionSourceId, Map<String,Object> options)throws Exception;
	
	//disconnect WechatUser with cq in ClassQuestion
	public WechatUser planToRemoveClassQuestionListWithCq(WechatUser wechatUser, String cqId, Map<String,Object> options)throws Exception;
	public int countClassQuestionListWithCq(String wechatUserId, String cqId, Map<String,Object> options)throws Exception;
	
	public WechatUser planToRemoveClassDailyHealthSurveyList(WechatUser wechatUser, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect WechatUser with school_class in ClassDailyHealthSurvey
	public WechatUser planToRemoveClassDailyHealthSurveyListWithSchoolClass(WechatUser wechatUser, String schoolClassId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithSchoolClass(String wechatUserId, String schoolClassId, Map<String,Object> options)throws Exception;
	
	//disconnect WechatUser with cq in ClassDailyHealthSurvey
	public WechatUser planToRemoveClassDailyHealthSurveyListWithCq(WechatUser wechatUser, String cqId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithCq(String wechatUserId, String cqId, Map<String,Object> options)throws Exception;
	
	public WechatUser planToRemoveWechatLoginInfoList(WechatUser wechatUser, String wechatLoginInfoIds[], Map<String,Object> options)throws Exception;


	//disconnect WechatUser with app_id in WechatLoginInfo
	public WechatUser planToRemoveWechatLoginInfoListWithAppId(WechatUser wechatUser, String appIdId, Map<String,Object> options)throws Exception;
	public int countWechatLoginInfoListWithAppId(String wechatUserId, String appIdId, Map<String,Object> options)throws Exception;
	
	//disconnect WechatUser with open_id in WechatLoginInfo
	public WechatUser planToRemoveWechatLoginInfoListWithOpenId(WechatUser wechatUser, String openIdId, Map<String,Object> options)throws Exception;
	public int countWechatLoginInfoListWithOpenId(String wechatUserId, String openIdId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<WechatUser> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<WechatUser> findWechatUserByAddress(String locationId, Map<String,Object> options);
 	public int countWechatUserByAddress(String locationId, Map<String,Object> options);
 	public Map<String, Integer> countWechatUserByAddressIds(String[] ids, Map<String,Object> options);
 	public SmartList<WechatUser> findWechatUserByAddress(String locationId, int start, int count, Map<String,Object> options);
 	public void analyzeWechatUserByAddress(SmartList<WechatUser> resultList, String locationId, Map<String,Object> options);

 
  
 	public SmartList<WechatUser> findWechatUserByUserType(String userTypeId, Map<String,Object> options);
 	public int countWechatUserByUserType(String userTypeId, Map<String,Object> options);
 	public Map<String, Integer> countWechatUserByUserTypeIds(String[] ids, Map<String,Object> options);
 	public SmartList<WechatUser> findWechatUserByUserType(String userTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeWechatUserByUserType(SmartList<WechatUser> resultList, String userTypeId, Map<String,Object> options);

 
  
 	public SmartList<WechatUser> findWechatUserByPlatform(String platformId, Map<String,Object> options);
 	public int countWechatUserByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countWechatUserByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<WechatUser> findWechatUserByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeWechatUserByPlatform(SmartList<WechatUser> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Guardian的wechatUser的GuardianList
	public SmartList<Guardian> loadOurGuardianList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ClassQuestion的creator的ClassQuestionList
	public SmartList<ClassQuestion> loadOurClassQuestionList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的creator的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WechatLoginInfo的wechatUser的WechatLoginInfoList
	public SmartList<WechatLoginInfo> loadOurWechatLoginInfoList(HealthUserContext userContext, List<WechatUser> us, Map<String,Object> options) throws Exception;
	
}


