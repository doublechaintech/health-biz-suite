
package com.doublechaintech.health.changerequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.health.BaseDAO;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.question.Question;

import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;
import com.doublechaintech.health.question.QuestionDAO;
import com.doublechaintech.health.changerequesttype.ChangeRequestTypeDAO;


public interface ChangeRequestDAO extends BaseDAO{

	public SmartList<ChangeRequest> loadAll();
	public ChangeRequest load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ChangeRequest> changeRequestList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ChangeRequest present(ChangeRequest changeRequest,Map<String,Object> options) throws Exception;
	public ChangeRequest clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ChangeRequest save(ChangeRequest changeRequest,Map<String,Object> options);
	public SmartList<ChangeRequest> saveChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options);
	public SmartList<ChangeRequest> removeChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options);
	public SmartList<ChangeRequest> findChangeRequestWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countChangeRequestWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countChangeRequestWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String changeRequestId, int version) throws Exception;
	public ChangeRequest disconnectFromAll(String changeRequestId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public TeacherDAO getTeacherDAO();
		
	public StudentDAO getStudentDAO();
		
	public QuestionDAO getQuestionDAO();
		
	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO();
		
	public StudentHealthSurveyDAO getStudentHealthSurveyDAO();
		
	public StudentDailyAnswerDAO getStudentDailyAnswerDAO();
		
	
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForClassDailyHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForStudentDailyAnswer(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ChangeRequest planToRemoveTeacherList(ChangeRequest changeRequest, String teacherIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with platform in Teacher
	public ChangeRequest planToRemoveTeacherListWithPlatform(ChangeRequest changeRequest, String platformId, Map<String,Object> options)throws Exception;
	public int countTeacherListWithPlatform(String changeRequestId, String platformId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveStudentList(ChangeRequest changeRequest, String studentIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with student_id in Student
	public ChangeRequest planToRemoveStudentListWithStudentId(ChangeRequest changeRequest, String studentIdId, Map<String,Object> options)throws Exception;
	public int countStudentListWithStudentId(String changeRequestId, String studentIdId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with address in Student
	public ChangeRequest planToRemoveStudentListWithAddress(ChangeRequest changeRequest, String addressId, Map<String,Object> options)throws Exception;
	public int countStudentListWithAddress(String changeRequestId, String addressId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with user in Student
	public ChangeRequest planToRemoveStudentListWithUser(ChangeRequest changeRequest, String userId, Map<String,Object> options)throws Exception;
	public int countStudentListWithUser(String changeRequestId, String userId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with platform in Student
	public ChangeRequest planToRemoveStudentListWithPlatform(ChangeRequest changeRequest, String platformId, Map<String,Object> options)throws Exception;
	public int countStudentListWithPlatform(String changeRequestId, String platformId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveQuestionList(ChangeRequest changeRequest, String questionIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with question_type in Question
	public ChangeRequest planToRemoveQuestionListWithQuestionType(ChangeRequest changeRequest, String questionTypeId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithQuestionType(String changeRequestId, String questionTypeId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with platform in Question
	public ChangeRequest planToRemoveQuestionListWithPlatform(ChangeRequest changeRequest, String platformId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithPlatform(String changeRequestId, String platformId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with creator in Question
	public ChangeRequest planToRemoveQuestionListWithCreator(ChangeRequest changeRequest, String creatorId, Map<String,Object> options)throws Exception;
	public int countQuestionListWithCreator(String changeRequestId, String creatorId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveClassDailyHealthSurveyList(ChangeRequest changeRequest, String classDailyHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with teacher in ClassDailyHealthSurvey
	public ChangeRequest planToRemoveClassDailyHealthSurveyListWithTeacher(ChangeRequest changeRequest, String teacherId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithTeacher(String changeRequestId, String teacherId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with creator in ClassDailyHealthSurvey
	public ChangeRequest planToRemoveClassDailyHealthSurveyListWithCreator(ChangeRequest changeRequest, String creatorId, Map<String,Object> options)throws Exception;
	public int countClassDailyHealthSurveyListWithCreator(String changeRequestId, String creatorId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveStudentHealthSurveyList(ChangeRequest changeRequest, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with student in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithStudent(ChangeRequest changeRequest, String studentId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithStudent(String changeRequestId, String studentId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with survey_status in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithSurveyStatus(ChangeRequest changeRequest, String surveyStatusId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithSurveyStatus(String changeRequestId, String surveyStatusId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with teacher in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithTeacher(ChangeRequest changeRequest, String teacherId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithTeacher(String changeRequestId, String teacherId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with class_daily_health_survey in StudentHealthSurvey
	public ChangeRequest planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(ChangeRequest changeRequest, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String changeRequestId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception;
	
	public ChangeRequest planToRemoveStudentDailyAnswerList(ChangeRequest changeRequest, String studentDailyAnswerIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with student_health_survey in StudentDailyAnswer
	public ChangeRequest planToRemoveStudentDailyAnswerListWithStudentHealthSurvey(ChangeRequest changeRequest, String studentHealthSurveyId, Map<String,Object> options)throws Exception;
	public int countStudentDailyAnswerListWithStudentHealthSurvey(String changeRequestId, String studentHealthSurveyId, Map<String,Object> options)throws Exception;
	
	//disconnect ChangeRequest with question in StudentDailyAnswer
	public ChangeRequest planToRemoveStudentDailyAnswerListWithQuestion(ChangeRequest changeRequest, String questionId, Map<String,Object> options)throws Exception;
	public int countStudentDailyAnswerListWithQuestion(String changeRequestId, String questionId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ChangeRequest> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
 
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId, Map<String,Object> options);
 	public int countChangeRequestByRequestType(String changeRequestTypeId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestByRequestTypeIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestByRequestType(SmartList<ChangeRequest> resultList, String changeRequestTypeId, Map<String,Object> options);

 
  
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, Map<String,Object> options);
 	public int countChangeRequestByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestByPlatform(SmartList<ChangeRequest> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:Teacher的cq的TeacherList
	public SmartList<Teacher> loadOurTeacherList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Student的cq的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Question的cq的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ClassDailyHealthSurvey的cq的ClassDailyHealthSurveyList
	public SmartList<ClassDailyHealthSurvey> loadOurClassDailyHealthSurveyList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的cq的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:StudentDailyAnswer的cq的StudentDailyAnswerList
	public SmartList<StudentDailyAnswer> loadOurStudentDailyAnswerList(HealthUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
}


