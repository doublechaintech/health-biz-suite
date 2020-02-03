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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.surveystatus.SurveyStatus;

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
			"      AND (T2.user = ?)) " +
			"    ORDER BY T1.id DESC " +
			"    LIMIT ? ";
		params.add(surveyId);
		params.add(userId);
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
}
