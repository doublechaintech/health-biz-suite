package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.CollectionUtils;

import java.util.List;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.survey.SurveyFormProcessor;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.wechatapp.DBQuery;

public class StudentSurveyDetailPage extends BaseViewPage{
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions", SerializeScope.EXCLUDE())
			.field("actionList")
			.field("title")
			.field("answerTime")
			.field("list",SerializeScope.INCLUDE().field("title").field("optionA").field("optionB").field("optionC").field("optionD").field("answer"))
			.field("questionList", MiscUtils
					.buildEntityScope(DailySurveyQuestion.ID_PROPERTY, "type","title","placeholder")
						.field("candidateValues",
								SerializeScope.INCLUDE().field("id").field("name").field("selected")))
			.field("repliedList",
					MiscUtils.buildEntityScope("answerTime").field("surveyStatus",SerializeScope.INCLUDE().field("code").field("studentDailyAnswerList",ViewScope.getStudentDailyAnswerDetailScope()))
					)
			.field("surveyDate")
			.field("classSize")
			.field("school")
			.field("schoolClass")
			.field("answerCount")
			.field("riskCount")
			.field("shareLink");
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "student survey detail";
		}
		return pageTitle;
	}
	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName)throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl)userContext;
		String studentSurveyId = ctx.getStudentSurveyId();
		StudentHealthSurvey studentSurvey = ctx.getManagerGroup().getStudentHealthSurveyManager().loadStudentHealthSurvey(userContext, studentSurveyId, StudentHealthSurveyTokens.start().withClassDailyHealthSurvey().withStudentDailyAnswerList().toArray());
		SmartList<StudentDailyAnswer> answers = ctx.getDAOGroup().getStudentHealthSurveyDAO().loadOurStudentDailyAnswerList(userContext, CollectionUtils.toList(studentSurvey), StudentDailyAnswerTokens.all());
		List<DailySurveyQuestion> questions = MiscUtils.collectReferencedObjectWithType(ctx, answers, DailySurveyQuestion.class);
		ctx.getDAOGroup().getDailySurveyQuestionDAO().enhanceList(questions);
//		answers.stream().forEach(a->{
//			DailySurveyQuestion question = a.getQuestion();
//			a.addItemToValueMap("title", question.getTopic());
//			a.addItemToValueMap("optionA", question.getOptionA());
//			a.addItemToValueMap("optionB", question.getOptionB());
//			a.addItemToValueMap("optionC", question.getOptionC());
//			a.addItemToValueMap("optionD", question.getOptionD());
//			a.addItemToValueMap("answer", a.getAnswer());
//			a.addItemToValueMap("candidateValues", item);
//			
//		});
		set("title",studentSurvey.getClassDailyHealthSurvey().getName());
		set("answerTime",studentSurvey.getAnswerTime());
		set("questionList",MiscUtils.constructQuestionList3(answers));
		setPageTitle("问卷详情");
	}
}
