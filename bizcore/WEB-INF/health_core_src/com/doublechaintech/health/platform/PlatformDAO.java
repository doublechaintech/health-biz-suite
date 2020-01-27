
package com.doublechaintech.health.platform;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.questionsource.QuestionSource;
import com.doublechaintech.health.usertype.UserType;

import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.surveystatus.SurveyStatusDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;
import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.usertype.UserTypeDAO;
import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.guardian.GuardianDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.questionsource.QuestionSourceDAO;
import com.doublechaintech.health.question.QuestionDAO;
import com.doublechaintech.health.district.DistrictDAO;
import com.doublechaintech.health.changerequesttype.ChangeRequestTypeDAO;


public interface PlatformDAO extends BaseDAO{

	public SmartList<Platform> loadAll();
	public Platform load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Platform> platformList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Platform present(Platform platform,Map<String,Object> options) throws Exception;
	public Platform clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Platform save(Platform platform,Map<String,Object> options);
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String platformId, int version) throws Exception;
	public Platform disconnectFromAll(String platformId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ProvinceDAO getProvinceDAO();
		
	public CityDAO getCityDAO();
		
	public DistrictDAO getDistrictDAO();
		
	public SchoolClassDAO getSchoolClassDAO();
		
	public TeacherDAO getTeacherDAO();
		
	public GuardianDAO getGuardianDAO();
		
	public QuestionDAO getQuestionDAO();
		
	public QuestionTypeDAO getQuestionTypeDAO();
		
	public QuestionSourceDAO getQuestionSourceDAO();
		
	public SurveyStatusDAO getSurveyStatusDAO();
		
	public WechatUserDAO getWechatUserDAO();
		
	public UserTypeDAO getUserTypeDAO();
		
	public ChangeRequestDAO getChangeRequestDAO();
		
	public ChangeRequestTypeDAO getChangeRequestTypeDAO();
		
	
 	public SmartList<Platform> requestCandidatePlatformForProvince(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForCity(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForDistrict(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForQuestionType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForQuestionSource(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForSurveyStatus(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForWechatUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForUserType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForChangeRequest(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForChangeRequestType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Platform planToRemoveProvinceList(Platform platform, String provinceIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveCityList(Platform platform, String cityIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with province in City
	public Platform planToRemoveCityListWithProvince(Platform platform, String provinceId, Map<String,Object> options)throws Exception;
	public int countCityListWithProvince(String platformId, String provinceId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveDistrictList(Platform platform, String districtIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with city in District
	public Platform planToRemoveDistrictListWithCity(Platform platform, String cityId, Map<String,Object> options)throws Exception;
	public int countDistrictListWithCity(String platformId, String cityId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveSchoolClassList(Platform platform, String schoolClassIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with class_teacher in SchoolClass
	public Platform planToRemoveSchoolClassListWithClassTeacher(Platform platform, String classTeacherId, Map<String,Object> options)throws Exception;
	public int countSchoolClassListWithClassTeacher(String platformId, String classTeacherId, Map<String,Object> options)throws Exception;
	
	//disconnect Platform with cq in SchoolClass
	public Platform planToRemoveSchoolClassListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception;
	public int countSchoolClassListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveTeacherList(Platform platform, String teacherIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with cq in Teacher
	public Platform planToRemoveTeacherListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception;
	public int countTeacherListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveGuardianList(Platform platform, String guardianIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with address in Guardian
	public Platform planToRemoveGuardianListWithAddress(Platform platform, String addressId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithAddress(String platformId, String addressId, Map<String,Object> options)throws Exception;
	
	//disconnect Platform with wechat_user in Guardian
	public Platform planToRemoveGuardianListWithWechatUser(Platform platform, String wechatUserId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithWechatUser(String platformId, String wechatUserId, Map<String,Object> options)throws Exception;
	
	//disconnect Platform with cq in Guardian
	public Platform planToRemoveGuardianListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception;
	public int countGuardianListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveQuestionList(Platform platform, String questionIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with question_type in Question
	public Platform planToRemoveQuestionListWithQuestionType(Platform platform, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithQuestionType(String platformId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveQuestionTypeList(Platform platform, String questionTypeIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveQuestionSourceList(Platform platform, String questionSourceIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveSurveyStatusList(Platform platform, String surveyStatusIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveWechatUserList(Platform platform, String wechatUserIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with address in WechatUser
	public Platform planToRemoveWechatUserListWithAddress(Platform platform, String addressId, Map<String,Object> options)throws Exception;
	public int countWechatUserListWithAddress(String platformId, String addressId, Map<String,Object> options)throws Exception;
	
	//disconnect Platform with user_type in WechatUser
	public Platform planToRemoveWechatUserListWithUserType(Platform platform, String userTypeId, Map<String,Object> options)throws Exception;
	public int countWechatUserListWithUserType(String platformId, String userTypeId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveUserTypeList(Platform platform, String userTypeIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveChangeRequestList(Platform platform, String changeRequestIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with request_type in ChangeRequest
	public Platform planToRemoveChangeRequestListWithRequestType(Platform platform, String requestTypeId, Map<String,Object> options)throws Exception;
	public int countChangeRequestListWithRequestType(String platformId, String requestTypeId, Map<String,Object> options)throws Exception;
	
	public Platform planToRemoveChangeRequestTypeList(Platform platform, String changeRequestTypeIds[], Map<String,Object> options)throws Exception;


	
	public SmartList<Platform> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);

	// 需要一个加载引用我的对象的enhance方法:Province的platform的ProvinceList
	public SmartList<Province> loadOurProvinceList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:City的platform的CityList
	public SmartList<City> loadOurCityList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:District的platform的DistrictList
	public SmartList<District> loadOurDistrictList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:SchoolClass的platform的SchoolClassList
	public SmartList<SchoolClass> loadOurSchoolClassList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Teacher的platform的TeacherList
	public SmartList<Teacher> loadOurTeacherList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Guardian的platform的GuardianList
	public SmartList<Guardian> loadOurGuardianList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Question的platform的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:QuestionType的platform的QuestionTypeList
	public SmartList<QuestionType> loadOurQuestionTypeList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:QuestionSource的platform的QuestionSourceList
	public SmartList<QuestionSource> loadOurQuestionSourceList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:SurveyStatus的platform的SurveyStatusList
	public SmartList<SurveyStatus> loadOurSurveyStatusList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WechatUser的platform的WechatUserList
	public SmartList<WechatUser> loadOurWechatUserList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:UserType的platform的UserTypeList
	public SmartList<UserType> loadOurUserTypeList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequest的platform的ChangeRequestList
	public SmartList<ChangeRequest> loadOurChangeRequestList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestType的platform的ChangeRequestTypeList
	public SmartList<ChangeRequestType> loadOurChangeRequestTypeList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception;
	
}


