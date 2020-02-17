
package com.doublechaintech.health.user;
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
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.question.Question;

import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoDAO;
import com.doublechaintech.health.question.QuestionDAO;


public interface UserDAO extends BaseDAO{

	public SmartList<User> loadAll();
	public User load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<User> userList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public User present(User user,Map<String,Object> options) throws Exception;
	public User clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public User save(User user,Map<String,Object> options);
	public SmartList<User> saveUserList(SmartList<User> userList,Map<String,Object> options);
	public SmartList<User> removeUserList(SmartList<User> userList,Map<String,Object> options);
	public SmartList<User> findUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String userId, int version) throws Exception;
	public User disconnectFromAll(String userId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TeacherDAO getTeacherDAO();
		
	public StudentDAO getStudentDAO();
		
	public QuestionDAO getQuestionDAO();
		
	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO();
		
	public WechatLoginInfoDAO getWechatLoginInfoDAO();
		
	
 	public SmartList<User> requestCandidateUserForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForWechatLoginInfo(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public User planToRemoveTeacherList(User user, String teacherIds[], Map<String,Object> options)throws Exception;


	//disconnect User with platform in Teacher
	public User planToRemoveTeacherListWithPlatform(User user, String platformId, Map<String,Object> options)throws Exception;
	public int countTeacherListWithPlatform(String userId, String platformId, Map<String,Object> options)throws Exception;
	
	//disconnect User with change_request in Teacher
	public User planToRemoveTeacherListWithChangeRequest(User user, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countTeacherListWithChangeRequest(String userId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public User planToRemoveStudentList(User user, String studentIds[], Map<String,Object> options)throws Exception;


	//disconnect User with address in Student
	public User planToRemoveStudentListWithAddress(User user, String addressId, Map<String,Object> options)throws Exception;
	public int countStudentListWithAddress(String userId, String addressId, Map<String,Object> options)throws Exception;
	
	//disconnect User with platform in Student
	public User planToRemoveStudentListWithPlatform(User user, String platformId, Map<String,Object> options)throws Exception;
	public int countStudentListWithPlatform(String userId, String platformId, Map<String,Object> options)throws Exception;
	
	public User planToRemoveQuestionList(User user, String questionIds[], Map<String,Object> options)throws Exception;


	//disconnect User with question_type in Question
	public User planToRemoveQuestionListWithQuestionType(User user, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithQuestionType(String userId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect User with platform in Question
	public User planToRemoveQuestionListWithPlatform(User user, String platformId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithPlatform(String userId, String platformId, Map<String,Object> options)throws Exception;
	
	//disconnect User with cq in Question
	public User planToRemoveQuestionListWithCq(User user, String cqId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithCq(String userId, String cqId, Map<String,Object> options)throws Exception;
	
	public User planToRemoveClassDailyHealthSurveyList(User user, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect User with teacher in ClassDailyHealthSurvey
	public User planToRemoveClassDailyHealthSurveyListWithTeacher(User user, String teacherId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithTeacher(String userId, String teacherId, Map<String,Object> options)throws Exception;
	
	//disconnect User with change_request in ClassDailyHealthSurvey
	public User planToRemoveClassDailyHealthSurveyListWithChangeRequest(User user, String changeRequestId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithChangeRequest(String userId, String changeRequestId, Map<String,Object> options)throws Exception;
	
	public User planToRemoveWechatLoginInfoList(User user, String wechatLoginInfoIds[], Map<String,Object> options)throws Exception;


	//disconnect User with app_id in WechatLoginInfo
	public User planToRemoveWechatLoginInfoListWithAppId(User user, String appIdId, Map<String,Object> options)throws Exception;
	public int countWechatLoginInfoListWithAppId(String userId, String appIdId, Map<String,Object> options)throws Exception;
	
	//disconnect User with open_id in WechatLoginInfo
	public User planToRemoveWechatLoginInfoListWithOpenId(User user, String openIdId, Map<String,Object> options)throws Exception;
	public int countWechatLoginInfoListWithOpenId(String userId, String openIdId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<User> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<User> findUserByPlatform(String platformId, Map<String,Object> options);
 	public int countUserByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countUserByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<User> findUserByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeUserByPlatform(SmartList<User> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Teacher的user的TeacherList
	public SmartList<Teacher> loadOurTeacherList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Student的user的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Question的creator的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的creator的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WechatLoginInfo的user的WechatLoginInfoList
	public SmartList<WechatLoginInfo> loadOurWechatLoginInfoList(HealthUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
}


