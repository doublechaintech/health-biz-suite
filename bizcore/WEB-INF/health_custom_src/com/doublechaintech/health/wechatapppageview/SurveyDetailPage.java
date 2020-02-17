package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.MapUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthBaseUtils;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyTokens;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.wechatapp.DBQuery;
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
				.field("actionList")
				.field("questionList",
						MiscUtils
								.buildEntityScope(DailySurveyQuestion.ID_PROPERTY, "type", "title", "placeholder")
									.field("candidateValues", SerializeScope.INCLUDE().field("id").field("name")))

				.field("repliedList",
						SerializeScope
								.INCLUDE()
									.field("id")
									.field("code")
									.field("title")
									.field("answerTime")
									.field("linkToUrl")
									.field("surveyTime")
									.field("studentName")
									.field("infoList", SerializeScope.INCLUDE().field("title").field("value")))
				.field("noReplyList",SerializeScope.INCLUDE().field("id").field("name"))
				.field("surveyDate")
				.field("classSize")
				.field("school")
				.field("schoolClass")
				.field("answerCount")
				.field("riskCount")
				.field("actions", SerializeScope.INCLUDE().field("share", HealthCustomConstants.ACTION_SCOPE).field("download", HealthCustomConstants.ACTION_SCOPE))
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
		ClassDailyHealthSurvey survey = new DBQuery().findClassDailyHealthSurveyWhichId(ctx, surveyId);
		List<Teacher> teachers = HealthBaseUtils.collectReferencedObjectWithType(ctx, survey, Teacher.class);
		ctx.getDAOGroup().getTeacherDAO().enhanceList(teachers);
		SmartList<DailySurveyQuestion> questionList = survey.getDailySurveyQuestionList();
		SmartList<StudentHealthSurvey> studentAnswers = survey.getStudentHealthSurveyList();
		ctx
				.getDAOGroup()
					.getStudentHealthSurveyDAO()
					.loadOurStudentDailyAnswerList(ctx, studentAnswers, StudentDailyAnswerTokens.all());
		setPageTitle(survey.getName());
		set("questionList", MiscUtils.constructQuestionList(questionList));
		set("repliedList", buildInfoList(ctx, studentAnswers));

		set("noReplyList", getNoReplyList(ctx, survey, studentAnswers));
		set("surveyDate", survey.getSurveyTime());
		set("classSize", survey.getTeacher().getClassSize());
		set("school", survey.getTeacher().getSchool());
		set("schoolClass", survey.getTeacher().getSchoolClass());
		set("answerCount", new DBQuery().countStudentHealthSurveyListOfStudentBySurveyId(ctx, surveyId));
		set("riskCount", MiscUtils.getRiskCount(ctx,survey));
		Map<String, Object> actions = new HashMap<>();
		actions
				.put("share",
						MapUtil
								.put("code", "share")
									.put("title", "分享调查")
									.put("icon", "add")
									.put("linkToUrl", WechatAppViewBizService.makeStudentViewSurveyUrl(ctx, surveyId))
									.into_map());
		actions
		.put("download",
				MapUtil
						.put("code", "download")
							.put("title", "导出Excel")
							.put("icon", "add")
							.put("linkToUrl", WechatAppViewBizService.makeExportSurveyUrl(ctx, surveyId))
							.into_map());
		set("actions", actions);
	}

	protected List<Map<String, Object>> getNoReplyList(CustomHealthUserContextImpl ctx, ClassDailyHealthSurvey currentSurvey,
			SmartList<StudentHealthSurvey> studentAnswers) throws Exception {
		ClassDailyHealthSurvey previousSurvey = new DBQuery()
				.findClassDailyHealthSurveyWhichLastTime(ctx, currentSurvey.getTeacher().getId(),
						currentSurvey.getSurveyTime());
		if (previousSurvey == null || previousSurvey.getStudentHealthSurveyList() == null||previousSurvey.getStudentHealthSurveyList().isEmpty()) {
			return Collections.emptyList();
		}
		List<String> replied = Optional.ofNullable(studentAnswers
				.stream()
					.map(StudentHealthSurvey::getStudent)
					.map(Student::getStudentName)
					.collect(Collectors.toList())).orElse(new ArrayList<String>());
		
		List<Student> previousRepliedStudents= previousSurvey
				.getStudentHealthSurveyList()
					.stream()
					.map(StudentHealthSurvey::getStudent).collect(Collectors.toList());
		if(previousRepliedStudents==null||previousRepliedStudents.isEmpty()) {
			return Collections.emptyList();
		}
		
		return previousRepliedStudents.stream().filter(s->s.getStudentName()!=null&&!replied.contains(s.getStudentName())).map(student->MapUtil.put("id", student.getId()).put("name", student.getStudentName()).into_map())
					.collect(Collectors.toList());
	}

	protected List<StudentHealthSurvey> buildInfoList(CustomHealthUserContextImpl ctx,
			SmartList<StudentHealthSurvey> studentSurveys) {
		if (studentSurveys == null || studentSurveys.isEmpty()) {
			return Collections.emptyList();
		}
		List<StudentHealthSurvey> submitted = studentSurveys.stream().filter(s->SurveyStatus.SUBMITTE.equals(s.getSurveyStatus().getId()))
		.sorted((o1, o2) -> o2.getAnswerTime().compareTo(o1.getAnswerTime())).collect(Collectors.toList());
		submitted.forEach(s -> {
			s
					.addItemToValueMap(HealthCustomConstants.LINK_TO_URL,
							WechatAppViewBizService.makeViewStudentSurveyDetailUrl(ctx, s.getId()));
			s.addItemToValueMap("code", s.getId());
			s.addItemToValueMap("title", s.getClassDailyHealthSurvey().getName());
			s.addItemToValueMap("linkToUrl", WechatAppViewBizService.makeViewStudentSurveyDetailUrl(ctx, s.getId()));

			s.addItemToValueMap("surveyTime", s.getAnswerTime());
			try {
				List<Student> students = MiscUtils.collectReferencedObjectWithType(ctx, s, Student.class);
				Optional.ofNullable(students).ifPresent(studentList->ctx.getDAOGroup().getStudentDAO().enhanceList(studentList));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			s.addItemToValueMap("studentName", Optional.ofNullable(s.getStudent()).map(Student::getStudentName).orElse(""));
			List<Map<String, String>> infoList = new ArrayList<>();
			s.getStudentDailyAnswerList().forEach(ans -> {
				List<DailySurveyQuestion> questions;
				try {
					questions = MiscUtils.collectReferencedObjectWithType(userContext, ans, DailySurveyQuestion.class);
					ctx.getDAOGroup().getDailySurveyQuestionDAO().enhanceList(questions);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Object answerText = QuestionType.SINGLE_SELECT.equals(ans.getQuestion().getQuestionType().getId())
						? ans.getQuestion().propertyOf("option" + ans.getAnswer())
						: ans.getAnswer();
				infoList
						.add(MapUtil
								.put("title", ans.getQuestion().getTopic())
									.put("value", answerText)
									.into_map(String.class));
				infoList.add(Collections.emptyMap());
			});
//			infoList.remove(infoList.size() - 1);
			s.addItemToValueMap("infoList", infoList);
		});
		return submitted;
	}
	
	protected void enhanceAnswer(CustomHealthUserContextImpl ctx,StudentDailyAnswer answer) throws Exception {
		List<DailySurveyQuestion> questions = MiscUtils.collectReferencedObjectWithType(ctx, answer, DailySurveyQuestion.class);
		ctx.getDAOGroup().getDailySurveyQuestionDAO().enhanceList(questions);
		
	}
}
