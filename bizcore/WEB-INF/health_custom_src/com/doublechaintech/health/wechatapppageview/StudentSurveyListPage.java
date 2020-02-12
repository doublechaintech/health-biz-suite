package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.CollectionUtils;
import com.terapico.utils.MapUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherTokens;
import com.doublechaintech.health.wechatapp.DBQuery;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class StudentSurveyListPage extends BaseViewPage {
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
				.field("surveyAnswerList",
						SerializeScope
								.INCLUDE()
									.field("id")
									.field("code")
									.field("title")
									.field("answerTime")
									.field("linkToUrl")
									.field("surveyTime")
									.field("studentName")
									.field("infoList", SerializeScope.INCLUDE().field("title").field("value")));

	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "student survey list";
		}
		return pageTitle;
	}

	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName) throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		String userId = ctx.getCurrentUserInfo().getId();
		SmartList<StudentHealthSurvey> surveyList = new DBQuery().queryStudentHealthSurveyListOfUser(ctx, userId);
		setPageTitle("问卷历史");
		List<ClassDailyHealthSurvey> surveys = MiscUtils
				.collectReferencedObjectWithType(ctx, surveyList, ClassDailyHealthSurvey.class);
		ctx.getDAOGroup().getClassDailyHealthSurveyDAO().enhanceList(surveys);
		if (surveyList != null) {
			surveyList.forEach(s -> {
				s
						.addItemToValueMap(HealthCustomConstants.LINK_TO_URL,
								WechatAppViewBizService.makeViewStudentSurveyDetailUrl(ctx, s.getId()));
				s.addItemToValueMap("code", s.getId());
				s.addItemToValueMap("title", s.getClassDailyHealthSurvey().getName());
				s
						.addItemToValueMap("linkToUrl",
								WechatAppViewBizService.makeViewStudentSurveyDetailUrl(ctx, s.getId()));

				s.addItemToValueMap("surveyTime", s.getClassDailyHealthSurvey().getSurveyTime());
				s.addItemToValueMap("studentName", s.getStudent().getStudentName());
				List<Map<String, String>> infoList = new ArrayList<>();
				s.getStudentDailyAnswerList().forEach(ans -> {
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
				infoList.remove(infoList.size() - 1);
				s.addItemToValueMap("infoList", infoList);
			});
		}
		set("surveyAnswerList", surveyList);
		set(X_EMPTY_MESSAGE, "没有提交任何问卷");
	}
}
