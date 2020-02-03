package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.MapUtil;

import java.util.HashMap;
import java.util.Map;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.question.QuestionTokens;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherTokens;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class AddSurveyPage extends BaseViewPage {
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
				.field("questionList",
						SerializeScope
								.INCLUDE()
									.field("id")
									.field(Question.TOPIC_PROPERTY)
									.field(Question.QUESTION_TYPE_PROPERTY)
									.field(Question.OPTION_A_PROPERTY)
									.field(Question.OPTION_B_PROPERTY)
									.field(Question.OPTION_C_PROPERTY)
									.field(Question.OPTION_D_PROPERTY)
									.field(Question.QUESTION_TYPE_PROPERTY)
						)
				.field("surveyDate")

	;

	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "add survey";
		}
		return pageTitle;
	}

	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName) throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		String teacherId = ctx.getTeacherId();
		Teacher teacher = ctx
				.getManagerGroup()
					.getTeacherManager()
					.loadTeacher(ctx, teacherId, TeacherTokens.start().toArray());
		SmartList<Question> questions = ctx
				.getDAOGroup()
					.getQuestionDAO()
					.findQuestionByPlatform(HealthCustomConstants.ROOT_PLATFORM_ID, 0, 3, QuestionTokens.all());
		set("questionList", questions);

		Map<String, Object> actions = new HashMap<>();
		actions
				.put("addClass",
						MapUtil
								.put("code", "publishSurvey")
									.put("title", "发布问卷")
									.put("icon", "add")
									.put("linkToUrl", WechatAppViewBizService.makePublishSurveyUrl(ctx, teacherId,":surveyDate"))
									.into_map());
		set("actions", actions);
		set("title",teacher.getSchoolClass() + "学生健康状况调查表");
		setPageTitle("创建调查问卷");
	}
}
