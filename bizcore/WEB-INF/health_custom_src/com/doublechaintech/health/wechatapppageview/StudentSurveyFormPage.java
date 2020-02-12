package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.CollectionUtils;
import com.terapico.utils.MapUtil;
import com.terapico.utils.TextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.survey.SurveyFormProcessor;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.wechatapp.DBQuery;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;
import com.skynet.sql.core.DBQueryBuilder;

public class StudentSurveyFormPage extends BaseViewPage {
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope
			.INCLUDE()
				.field("title")
				.field("popup")
				.field("toast", SerializeScope.EXCLUDE())
				.field("refreshAction")
				.field("actions",SerializeScope.INCLUDE().field("submit", HealthCustomConstants.ACTION_SCOPE))
				.field("actionList")
				.field("form")
				.field("surveyId")
				.field("surveyDate")
				.field("classSize")
				.field("school")
				.field("schoolClass")
//				.field("answerCount")
//				.field("riskCount")
//				.field("questions",SerializeScope.INCLUDE().field("id").field("title").field("choices",SerializeScope.INCLUDE().field("id").field("text")));
	.field("questionList", MiscUtils
			.buildEntityScope(DailySurveyQuestion.ID_PROPERTY, "type","title","placeholder")
				.field("candidateValues",
						SerializeScope.INCLUDE().field("id").field("name")));

	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "student survey form";
		}
		return pageTitle;
	}

	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName)throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl)userContext;
		ClassDailyHealthSurvey survey = ctx.getManagerGroup().getClassDailyHealthSurveyManager().loadClassDailyHealthSurvey(ctx, ctx.getSurveyId(), ClassDailyHealthSurveyTokens.start().withDailySurveyQuestionList().toArray());
		List<Object> result = new ArrayList<>();
		List<DailySurveyQuestion> questions = MiscUtils.constructQuestionList(survey.getDailySurveyQuestionList());
//		survey.getDailySurveyQuestionList().forEach(q->{
//			try {
//				List<Map<String, Object>> selections = buildChoices(q);
//				Map<String, Object> data = MapUtil.put("id", q.getId())
//					.put("title", q.getTopic())
//					.put("choices", selections)
//					.into_map();
//				
//				result.add(data);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		});
		List<Teacher> teachers = HealthBaseUtils.collectReferencedObjectWithType(ctx, survey, Teacher.class);
		ctx.getDAOGroup().getTeacherDAO().enhanceList(teachers);
		setPageTitle("尚未实现");
		set("questionList",questions);
		Map<String, Object> actions = new HashMap<>();
		actions
				.put("submit",
						MapUtil
								.put("code", "submit")
									.put("title", "提交")
									.put("icon", "submit")
									.put("linkToUrl", WechatAppViewBizService.makeSubmitStudentSurveyUrl(ctx,"surveyId","answer","studentName"))
									.into_map());
		set("surveyId",ctx.getSurveyId());
		set("actions", actions);
		
		set("surveyDate", survey.getSurveyTime());
		set("classSize", survey.getTeacher().getClassSize());
		set("school",survey.getTeacher().getSchool());
		set("schoolClass",survey.getTeacher().getSchoolClass());
		set("answerCount",0);
		set("riskCount",0);
	}
//	protected List<Map<String, Object>> buildChoices(DailySurveyQuestion question) {
//		List<Map<String, Object>> result = new ArrayList<>();
//		if(!TextUtil.isBlank(question.getOptionA())) {
//			result.add(MapUtil.put("id", "A").put("text",  question.getOptionA()).into_map());
//		}
//		if(!TextUtil.isBlank(question.getOptionB())) {
//			result.add(MapUtil.put("id", "B").put("text",  question.getOptionB()).into_map());
//		}
//		if(!TextUtil.isBlank(question.getOptionC())) {
//			result.add(MapUtil.put("id", "C").put("text",  question.getOptionC()).into_map());
//		}
//		if(!TextUtil.isBlank(question.getOptionD())) {
//			result.add(MapUtil.put("id", "D").put("text",  question.getOptionD()).into_map());
//		}
//		return result;
//	}
}
