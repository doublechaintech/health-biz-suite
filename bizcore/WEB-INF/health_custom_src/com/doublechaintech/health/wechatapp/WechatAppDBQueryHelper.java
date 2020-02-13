package com.doublechaintech.health.wechatapp;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.HealthBaseUtils;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.wechatapp.BasicWxappService;
import com.doublechaintech.health.wechatapppageview.*;

import com.terapico.utils.TextUtil;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.teacher.Teacher;

/**
 * 此类负责：声明所有{@link WechatAppViewService}中所使用的数据库搜索方法。 单独列出的目的是便于维护。
 * @author clariones
 *
 */
public abstract class WechatAppDBQueryHelper{
	public static final Map<String, Object> EO = new HashMap<>();
	public int getPageSize(CustomHealthUserContextImpl ctx, String queryName) {
		return 20;
	}
	@SuppressWarnings("serial")
	public <T> List<T> asList(T object) {
		return new ArrayList<T>() {{add(object);}};
	}
	/**
	 * .
	 */
    public StudentHealthSurvey findStudentHealthSurveyWhichStudentBySurveyId( CustomHealthUserContextImpl ctx , String surveyId , String userId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForFindStudentHealthSurveyWhichStudentBySurveyId(ctx, params, surveyId , userId);
		SmartList<StudentHealthSurvey> list = ctx.getDAOGroup().getStudentHealthSurveyDAO().queryList(sql, params.toArray());
		if (list == null) {
			return null;
		}
		StudentHealthSurvey result = list.first();
		enhanceStudentHealthSurveyWhichStudentBySurveyId(ctx, result, "findStudentHealthSurveyWhichStudentBySurveyId", surveyId , userId);
		return result;
	}

	protected String prepareSqlAndParamsForFindStudentHealthSurveyWhichStudentBySurveyId( CustomHealthUserContextImpl ctx, List<Object> params, String surveyId , String userId ) throws Exception {
		String sql = "SELECT DISTINCT T1.* from student_health_survey_data T1 " +
			"        LEFT JOIN student_data T2 ON T1.student = T2.id " +
			"    WHERE  ((T1.class_daily_health_survey = ?)" +
			"      AND (T2.user = ?)" +
			"      AND (T1.survey_status = ?)) " +
			"    ORDER BY T1.id DESC " +
			"    LIMIT ? ";
		params.add(surveyId);
		params.add(userId);
		params.add(SurveyStatus.UN_SUBMITTED);
		params.add(1);
		return sql;
	}
	
	protected void enhanceStudentHealthSurveyWhichStudentBySurveyId(CustomHealthUserContextImpl ctx, StudentHealthSurvey data, String queryName, String surveyId , String userId ) throws Exception {
		if (data == null) {
			return;
		}
		List<StudentDailyAnswer> studentDailyAnswerListListOfStudentHealthSurveyAsT1 = ctx.getDAOGroup().getStudentHealthSurveyDAO().loadOurStudentDailyAnswerList(ctx,asList(data), EO);
		List<DailySurveyQuestion> questionListOfStudentDailyAnswerAsT2 = HealthBaseUtils.collectReferencedObjectWithType(ctx, studentDailyAnswerListListOfStudentHealthSurveyAsT1, DailySurveyQuestion.class);
		ctx.getDAOGroup().enhanceList(questionListOfStudentDailyAnswerAsT2, DailySurveyQuestion.class);
	}
	
	protected void enhanceStudentHealthSurvey(CustomHealthUserContextImpl ctx, StudentHealthSurvey data, String queryName) throws Exception {
		// 默认什么都不增强. 需要额外增强请重载此函数
	}

	/**
	 * .
	 */
    public StudentHealthSurvey findStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId( CustomHealthUserContextImpl ctx , String surveyId , String userId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForFindStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId(ctx, params, surveyId , userId);
		SmartList<StudentHealthSurvey> list = ctx.getDAOGroup().getStudentHealthSurveyDAO().queryList(sql, params.toArray());
		if (list == null) {
			return null;
		}
		StudentHealthSurvey result = list.first();
		enhanceStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId(ctx, result, "findStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId", surveyId , userId);
		return result;
	}

	protected String prepareSqlAndParamsForFindStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId( CustomHealthUserContextImpl ctx, List<Object> params, String surveyId , String userId ) throws Exception {
		String sql = "SELECT DISTINCT T1.* from student_health_survey_data T1 " +
			"        LEFT JOIN student_data T2 ON T1.student = T2.id " +
			"    WHERE  ((T1.class_daily_health_survey = ?)" +
			"      AND (T2.user = ?)" +
			"      AND (T1.survey_status = ?)) " +
			"    ORDER BY T1.id DESC " +
			"    LIMIT ? ";
		params.add(surveyId);
		params.add(userId);
		params.add(SurveyStatus.SUBMITTE);
		params.add(1);
		return sql;
	}
	
	protected void enhanceStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId(CustomHealthUserContextImpl ctx, StudentHealthSurvey data, String queryName, String surveyId , String userId ) throws Exception {
		if (data == null) {
			return;
		}
		List<StudentDailyAnswer> studentDailyAnswerListListOfStudentHealthSurveyAsT1 = ctx.getDAOGroup().getStudentHealthSurveyDAO().loadOurStudentDailyAnswerList(ctx,asList(data), EO);
		List<DailySurveyQuestion> questionListOfStudentDailyAnswerAsT2 = HealthBaseUtils.collectReferencedObjectWithType(ctx, studentDailyAnswerListListOfStudentHealthSurveyAsT1, DailySurveyQuestion.class);
		ctx.getDAOGroup().enhanceList(questionListOfStudentDailyAnswerAsT2, DailySurveyQuestion.class);
	}
	

	/**
	 * .
	 */
	public SmartList<StudentHealthSurvey> queryStudentHealthSurveyListOfStudentBySurveyId( CustomHealthUserContextImpl ctx , String surveyId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForQueryStudentHealthSurveyListOfStudentBySurveyId(ctx, params, surveyId);
		
		SmartList<StudentHealthSurvey> list = executeQueryStudentHealthSurveyListOfStudentBySurveyId(ctx, sql, params);
		list.setRowsPerPage(Integer.MAX_VALUE);
		if (list == null || list.isEmpty()){
			return list;
		}
		ctx.setLastRecordId(null);
		enhanceStudentHealthSurveyListOfStudentBySurveyId(ctx, list, "queryStudentHealthSurveyListOfStudentBySurveyId", surveyId);
		return list;
	}
	protected SmartList<StudentHealthSurvey> executeQueryStudentHealthSurveyListOfStudentBySurveyId(CustomHealthUserContextImpl ctx, String sql, List<Object> params) throws Exception {
		return ctx.getDAOGroup().getStudentHealthSurveyDAO().queryList(sql, params.toArray());
	}
	/**
	 * .
	 */
	protected String prepareSqlAndParamsForQueryStudentHealthSurveyListOfStudentBySurveyId( CustomHealthUserContextImpl ctx , List<Object> params, String surveyId ) throws Exception {
	
		String sql = "SELECT DISTINCT T1.* from student_health_survey_data T1 " +
			"    WHERE  ((T1.class_daily_health_survey = ?)" +
			"      AND (T1.survey_status = ?)) " +
			"    ORDER BY T1.id DESC ";
		params.add(surveyId);
		params.add(SurveyStatus.SUBMITTE);
		return sql;
	}
	protected void enhanceStudentHealthSurveyListOfStudentBySurveyId(CustomHealthUserContextImpl ctx, SmartList<StudentHealthSurvey> list, String queryName, String surveyId) throws Exception {
		// 重载此函数, 根据查询的结果, 加载更多的相关数据
	}
	/**
	 * .
	 */
    public Student findStudentWhichNameIs( CustomHealthUserContextImpl ctx , String studentName , String userId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForFindStudentWhichNameIs(ctx, params, studentName , userId);
		SmartList<Student> list = ctx.getDAOGroup().getStudentDAO().queryList(sql, params.toArray());
		if (list == null) {
			return null;
		}
		Student result = list.first();
		enhanceStudentWhichNameIs(ctx, result, "findStudentWhichNameIs", studentName , userId);
		return result;
	}

	protected String prepareSqlAndParamsForFindStudentWhichNameIs( CustomHealthUserContextImpl ctx, List<Object> params, String studentName , String userId ) throws Exception {
		String sql = "SELECT DISTINCT T1.* from student_data T1 " +
			"    WHERE  ((T1.student_name = ?) " +
			"      AND (T1.user = ?)) " +
			"    ORDER BY T1.id DESC " +
			"    LIMIT ? ";
		params.add(studentName);
		params.add(userId);
		params.add(1);
		return sql;
	}
	
	protected void enhanceStudentWhichNameIs(CustomHealthUserContextImpl ctx, Student data, String queryName, String studentName , String userId ) throws Exception {
		enhanceStudent(ctx, data, queryName);
	}
	
	protected void enhanceStudent(CustomHealthUserContextImpl ctx, Student data, String queryName) throws Exception {
		// 默认什么都不增强. 需要额外增强请重载此函数
	}

	/**
	 * .
	 */
	public SmartList<StudentHealthSurvey> queryStudentHealthSurveyListOfIsSubmittedByTeacherId( CustomHealthUserContextImpl ctx , String teacherId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForQueryStudentHealthSurveyListOfIsSubmittedByTeacherId(ctx, params, teacherId);
		
		SmartList<StudentHealthSurvey> list = executeQueryStudentHealthSurveyListOfIsSubmittedByTeacherId(ctx, sql, params);
		list.setRowsPerPage(Integer.MAX_VALUE);
		if (list == null || list.isEmpty()){
			return list;
		}
		ctx.setLastRecordId(null);
		enhanceStudentHealthSurveyListOfIsSubmittedByTeacherId(ctx, list, "queryStudentHealthSurveyListOfIsSubmittedByTeacherId", teacherId);
		return list;
	}
	protected SmartList<StudentHealthSurvey> executeQueryStudentHealthSurveyListOfIsSubmittedByTeacherId(CustomHealthUserContextImpl ctx, String sql, List<Object> params) throws Exception {
		return ctx.getDAOGroup().getStudentHealthSurveyDAO().queryList(sql, params.toArray());
	}
	/**
	 * .
	 */
	protected String prepareSqlAndParamsForQueryStudentHealthSurveyListOfIsSubmittedByTeacherId( CustomHealthUserContextImpl ctx , List<Object> params, String teacherId ) throws Exception {
	
		String sql = "SELECT DISTINCT T1.* from student_health_survey_data T1 " +
			"    WHERE  ((T1.teacher = ?)" +
			"      AND (T1.survey_status = ?)) " +
			"    ORDER BY T1.id DESC ";
		params.add(teacherId);
		params.add(SurveyStatus.SUBMITTE);
		return sql;
	}
	protected void enhanceStudentHealthSurveyListOfIsSubmittedByTeacherId(CustomHealthUserContextImpl ctx, SmartList<StudentHealthSurvey> list, String queryName, String teacherId) throws Exception {
		// 重载此函数, 根据查询的结果, 加载更多的相关数据
	}
	/**
	 * .
	 */
	public SmartList<StudentHealthSurvey> queryStudentHealthSurveyListOfUser( CustomHealthUserContextImpl ctx , String userId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForQueryStudentHealthSurveyListOfUser(ctx, params, userId);
		
		SmartList<StudentHealthSurvey> list = executeQueryStudentHealthSurveyListOfUser(ctx, sql, params);
		list.setRowsPerPage(Integer.MAX_VALUE);
		if (list == null || list.isEmpty()){
			return list;
		}
		ctx.setLastRecordId(null);
		enhanceStudentHealthSurveyListOfUser(ctx, list, "queryStudentHealthSurveyListOfUser", userId);
		return list;
	}
	protected SmartList<StudentHealthSurvey> executeQueryStudentHealthSurveyListOfUser(CustomHealthUserContextImpl ctx, String sql, List<Object> params) throws Exception {
		return ctx.getDAOGroup().getStudentHealthSurveyDAO().queryList(sql, params.toArray());
	}
	/**
	 * .
	 */
	protected String prepareSqlAndParamsForQueryStudentHealthSurveyListOfUser( CustomHealthUserContextImpl ctx , List<Object> params, String userId ) throws Exception {
	
		String sql = "SELECT DISTINCT T1.* from student_health_survey_data T1 " +
			"        LEFT JOIN student_data T2 ON T1.student = T2.id " +
			"    WHERE (T2.user = ?)" +
			"    ORDER BY T1.answer_time desc ";
		params.add(userId);
		return sql;
	}
	protected void enhanceStudentHealthSurveyListOfUser(CustomHealthUserContextImpl ctx, SmartList<StudentHealthSurvey> list, String queryName, String userId) throws Exception {
		if (list == null || list.isEmpty()) {
			return;
		}
		List<Student> studentListOfStudentHealthSurveyAsT1 = HealthBaseUtils.collectReferencedObjectWithType(ctx, list, Student.class);
		ctx.getDAOGroup().enhanceList(studentListOfStudentHealthSurveyAsT1, Student.class);
		List<StudentDailyAnswer> studentDailyAnswerListListOfStudentHealthSurveyAsT1 = ctx.getDAOGroup().getStudentHealthSurveyDAO().loadOurStudentDailyAnswerList(ctx,list, EO);
		List<DailySurveyQuestion> questionListOfStudentDailyAnswerAsT3 = HealthBaseUtils.collectReferencedObjectWithType(ctx, studentDailyAnswerListListOfStudentHealthSurveyAsT1, DailySurveyQuestion.class);
		ctx.getDAOGroup().enhanceList(questionListOfStudentDailyAnswerAsT3, DailySurveyQuestion.class);
		List<QuestionType> questionTypeListOfDailySurveyQuestionAsT4 = HealthBaseUtils.collectReferencedObjectWithType(ctx, questionListOfStudentDailyAnswerAsT3, QuestionType.class);
		ctx.getDAOGroup().enhanceList(questionTypeListOfDailySurveyQuestionAsT4, QuestionType.class);
	}
	/**
	 * .
	 */
    public ClassDailyHealthSurvey findClassDailyHealthSurveyWhichId( CustomHealthUserContextImpl ctx , String surveyId ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForFindClassDailyHealthSurveyWhichId(ctx, params, surveyId);
		SmartList<ClassDailyHealthSurvey> list = ctx.getDAOGroup().getClassDailyHealthSurveyDAO().queryList(sql, params.toArray());
		if (list == null) {
			return null;
		}
		ClassDailyHealthSurvey result = list.first();
		enhanceClassDailyHealthSurveyWhichId(ctx, result, "findClassDailyHealthSurveyWhichId", surveyId);
		return result;
	}

	protected String prepareSqlAndParamsForFindClassDailyHealthSurveyWhichId( CustomHealthUserContextImpl ctx, List<Object> params, String surveyId ) throws Exception {
		String sql = "SELECT DISTINCT T1.* from class_daily_health_survey_data T1 " +
			"    WHERE  (T1.id = ?) " +
			"    ORDER BY T1.id DESC " +
			"    LIMIT ? ";
		params.add(surveyId);
		params.add(1);
		return sql;
	}
	
	protected void enhanceClassDailyHealthSurveyWhichId(CustomHealthUserContextImpl ctx, ClassDailyHealthSurvey data, String queryName, String surveyId ) throws Exception {
		if (data == null) {
			return;
		}
		List<Teacher> teacherListOfClassDailyHealthSurveyAsT1 = HealthBaseUtils.collectReferencedObjectWithType(ctx, data, Teacher.class);
		ctx.getDAOGroup().enhanceList(teacherListOfClassDailyHealthSurveyAsT1, Teacher.class);
		List<DailySurveyQuestion> dailySurveyQuestionListListOfClassDailyHealthSurveyAsT1 = ctx.getDAOGroup().getClassDailyHealthSurveyDAO().loadOurDailySurveyQuestionList(ctx,asList(data), EO);
		List<StudentHealthSurvey> studentHealthSurveyListListOfClassDailyHealthSurveyAsT1 = ctx.getDAOGroup().getClassDailyHealthSurveyDAO().loadOurStudentHealthSurveyList(ctx,asList(data), EO);
		List<StudentDailyAnswer> studentDailyAnswerListListOfStudentHealthSurveyAsT4 = ctx.getDAOGroup().getStudentHealthSurveyDAO().loadOurStudentDailyAnswerList(ctx,studentHealthSurveyListListOfClassDailyHealthSurveyAsT1, EO);
		List<DailySurveyQuestion> questionListOfStudentDailyAnswerAsT5 = HealthBaseUtils.collectReferencedObjectWithType(ctx, studentDailyAnswerListListOfStudentHealthSurveyAsT4, DailySurveyQuestion.class);
		ctx.getDAOGroup().enhanceList(questionListOfStudentDailyAnswerAsT5, DailySurveyQuestion.class);
		List<QuestionType> questionTypeListOfDailySurveyQuestionAsT6 = HealthBaseUtils.collectReferencedObjectWithType(ctx, questionListOfStudentDailyAnswerAsT5, QuestionType.class);
		ctx.getDAOGroup().enhanceList(questionTypeListOfDailySurveyQuestionAsT6, QuestionType.class);
		List<Student> studentListOfStudentHealthSurveyAsT4 = HealthBaseUtils.collectReferencedObjectWithType(ctx, studentHealthSurveyListListOfClassDailyHealthSurveyAsT1, Student.class);
		ctx.getDAOGroup().enhanceList(studentListOfStudentHealthSurveyAsT4, Student.class);
	}
	
	protected void enhanceClassDailyHealthSurvey(CustomHealthUserContextImpl ctx, ClassDailyHealthSurvey data, String queryName) throws Exception {
		// 默认什么都不增强. 需要额外增强请重载此函数
	}

	/**
	 * .
	 */
    public ClassDailyHealthSurvey findClassDailyHealthSurveyWhichLastTime( CustomHealthUserContextImpl ctx , String teacherId , Date currentSurveyTime ) throws Exception {
		List<Object> params = new ArrayList<>();
		String sql = prepareSqlAndParamsForFindClassDailyHealthSurveyWhichLastTime(ctx, params, teacherId , currentSurveyTime);
		SmartList<ClassDailyHealthSurvey> list = ctx.getDAOGroup().getClassDailyHealthSurveyDAO().queryList(sql, params.toArray());
		if (list == null) {
			return null;
		}
		ClassDailyHealthSurvey result = list.first();
		enhanceClassDailyHealthSurveyWhichLastTime(ctx, result, "findClassDailyHealthSurveyWhichLastTime", teacherId , currentSurveyTime);
		return result;
	}

	protected String prepareSqlAndParamsForFindClassDailyHealthSurveyWhichLastTime( CustomHealthUserContextImpl ctx, List<Object> params, String teacherId , Date currentSurveyTime ) throws Exception {
		String sql = "SELECT DISTINCT T1.* from class_daily_health_survey_data T1 " +
			"    WHERE  ((T1.teacher = ?)" +
			"      AND  (T1.survey_time < ?) ) " +
			"    ORDER BY T1.survey_time desc " +
			"    LIMIT ? ";
		params.add(teacherId);
		params.add(currentSurveyTime);
		params.add(1);
		return sql;
	}
	
	protected void enhanceClassDailyHealthSurveyWhichLastTime(CustomHealthUserContextImpl ctx, ClassDailyHealthSurvey data, String queryName, String teacherId , Date currentSurveyTime ) throws Exception {
		if (data == null) {
			return;
		}
		List<StudentHealthSurvey> studentHealthSurveyListListOfClassDailyHealthSurveyAsT1 = ctx.getDAOGroup().getClassDailyHealthSurveyDAO().loadOurStudentHealthSurveyList(ctx,asList(data), EO);
		List<Student> studentListOfStudentHealthSurveyAsT2 = HealthBaseUtils.collectReferencedObjectWithType(ctx, studentHealthSurveyListListOfClassDailyHealthSurveyAsT1, Student.class);
		ctx.getDAOGroup().enhanceList(studentListOfStudentHealthSurveyAsT2, Student.class);
	}
	

}
