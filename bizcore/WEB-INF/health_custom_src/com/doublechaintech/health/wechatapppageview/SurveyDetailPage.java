package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;

import java.util.List;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthBaseUtils;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyTokens;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

/*
 * 老师看到的问卷详情页
 */
public class SurveyDetailPage extends BaseViewPage {
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope
			.INCLUDE()
				.field("title")
				.field("popup")
				.field("toast", SerializeScope.EXCLUDE())
				.field("refreshAction")
				.field("actions", SerializeScope.EXCLUDE())
				.field("actionList")
				.field("questionList", MiscUtils
						.buildEntityScope(DailySurveyQuestion.ID_PROPERTY, "code", DailySurveyQuestion.TOPIC_PROPERTY,
								DailySurveyQuestion.OPTION_A_PROPERTY, DailySurveyQuestion.OPTION_B_PROPERTY,
								DailySurveyQuestion.OPTION_C_PROPERTY, DailySurveyQuestion.OPTION_D_PROPERTY)
							.field(DailySurveyQuestion.QUESTION_TYPE_PROPERTY,
									SerializeScope.INCLUDE().field("id").field("name")))
				.field("repliedList",
						SerializeScope.INCLUDE().field("id").field("code").field(StudentHealthSurvey.STUDENT_PROPERTY))
				.field("surveyDate")
				.field("classSize")
				.field("shareLink");

	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "survey detail";
		}
		return pageTitle;
	}

	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName) throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		String surveyId = ctx.getSurveyId();
		ClassDailyHealthSurvey survey = ctx
				.getManagerGroup()
					.getClassDailyHealthSurveyManager()
					.loadClassDailyHealthSurvey(ctx, surveyId,
							ClassDailyHealthSurveyTokens
									.start()
										.withDailySurveyQuestionList()
										.withStudentHealthSurveyList()
										.searchStudentHealthSurveyListWith(StudentHealthSurvey.SURVEY_STATUS_PROPERTY,
												"eq", SurveyStatus.SUBMITTE)
										.toArray());
		List<Teacher> teachers = HealthBaseUtils.collectReferencedObjectWithType(ctx, survey, Teacher.class);
		ctx.getDAOGroup().getTeacherDAO().enhanceList(teachers);
		SmartList<DailySurveyQuestion> questionList = survey.getDailySurveyQuestionList();
		if (questionList != null) {
			questionList.forEach(q -> q.addItemToValueMap("code", q.getId()));
		}
		SmartList<StudentHealthSurvey> studentAnswers = survey.getStudentHealthSurveyList();
		if (studentAnswers != null) {
			studentAnswers.forEach(a -> a.addItemToValueMap("code", a.getId()));
		}
		setPageTitle(survey.getName());
		set("questionList", questionList);
		set("repliedList", studentAnswers);
		set("surveyDate", survey.getSurveyTime());
		set("classSize", survey.getTeacher().getClassSize());
		set("shareLink",WechatAppViewBizService.makeStudentViewSurveyUrl(ctx, surveyId));
	}
}
