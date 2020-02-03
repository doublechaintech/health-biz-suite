package com.doublechaintech.health.wechatapp;

import com.doublechaintech.health.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terapico.caf.DateTime;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.utils.DateTimeUtil;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.RandomUtil;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.MiscUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.changerequest.ChangeRequestCustomService;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyTokens;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.question.QuestionTokens;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherFormProcessor;
import com.doublechaintech.health.teacher.TeacherTokens;

/**
 * 此类负责：所有的业务逻辑，例如所有的过滤规则，计算规则
 * 
 * @author clariones
 *
 */
public class WechatAppViewBizService extends BasicWechatAppViewBizService {
	private ChangeRequestCustomService changeRequestService;

	// 处理请求：默认的客户端登录接口. 返回值：PRC_BY_DEFAULT: ;
	@Override
	protected int processRequestClientLogin(CustomHealthUserContextImpl ctx) throws Exception {
		LoginParam loginParam = ctx.getLoginParam();
		this.processClientLogin(ctx, loginParam);
		return PRC_BY_DEFAULT;
	}

	// 处理请求：查看首页. 返回值：PRC_BY_DEFAULT: ;
	@Override
	protected int processRequestViewHomepage(CustomHealthUserContextImpl ctx) throws Exception {
		// TODO
		return PRC_BY_DEFAULT;
	}

	// 处理请求：发布问卷. 返回值：PRC_BY_DEFAULT: ;
	@Override
	protected int processRequestCustomerPublishSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		String surveyDate = ctx.getSurveyDate();
		DateTime date = DateTime.fromDate(Optional.ofNullable(DateTimeUtil.getDate(surveyDate)).orElse(ctx.now()));
		Teacher teacher = teacherManagerOf(ctx).loadTeacher(ctx, ctx.getTeacherId(), TeacherTokens.start().toArray());
		ChangeRequest cr = new ChangeRequest()
				.updateVersion(0)
					.updateRequestType(ChangeRequestType.refById(ChangeRequestType.PUBLISH_SURVEY))
					.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
					.updateName("test");
		ClassDailyHealthSurvey survey = new ClassDailyHealthSurvey()
				.updateCreator(ctx.getCurrentUserInfo())
					.updateSurveyTime(date)
					.updateName(teacher.getSchoolClass() + "学生健康状况调查" + new SimpleDateFormat("yyyy-MM-dd").format(date))
					.updateTeacher(Teacher.refById(ctx.getTeacherId()));
		SmartList<Question> questions = ctx
				.getDAOGroup()
					.getQuestionDAO()
					.findQuestionByPlatform(HealthCustomConstants.ROOT_PLATFORM_ID, 0, 3, QuestionTokens.all());
		questions
				.stream()
					.map(q -> new DailySurveyQuestion()
							.updateOptionA(q.getOptionA())
								.updateOptionB(q.getOptionB())
								.updateOptionC(q.getOptionC())
								.updateOptionD(q.getOptionD())
								.updateTopic(q.getTopic())
								.updateQuestionType(q.getQuestionType())
								.updateSurveyQuestion(q)

					)
					.forEach(survey::addDailySurveyQuestion);
		cr.addClassDailyHealthSurvey(survey);

		ChangeRequest processed = getChangeRequestService().process(ctx, cr);
		ctx.setSurveyId(processed.getClassDailyHealthSurveyList().first().getId());
		return PRC_BY_DEFAULT;
	}

	@Override
	protected void commonLog(CustomHealthUserContextImpl ctx, String eventCode, String title, String key1, String key2,
			String key3, Object detailInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	protected User onNewLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam, BaseLoginHandler loginHandler)
			throws Exception {
		String loginMethod = loginParam.getLoginMethod();
		if (BaseLoginHandler.WECHAT_WORK_APP.equals(loginMethod)) {
			String openId = (String) loginHandler.getProcessedLoginInfo(ctx).get("openId");
//			WxCpUser userInfo = this.getWxCpService().getUserService().getById(userId);
//			if (userInfo == null) {
//				this.throwsExceptionWithMessage(ctx, "找不到用户，登录失败");
//				return null;
//			}

			User user = userManagerOf(ctx)
					.createUser(ctx, "name", "avatar", null, HealthCustomConstants.ROOT_PLATFORM_ID);
			wechatLoginInfoManagerOf(ctx).createWechatLoginInfo(ctx, user.getId(), null, openId, null);

			// 创建secUser
			String login = user.getId();
			String mobile = null;
			String email = null;
			String pwd = RandomUtil.randomNum(6);
			String weixinOpenid = openId;
			String weixinAppid = null;
			String accessToken = null;
			int verificationCode = 888888;
			DateTime verificationCodeExpire = ctx.now();
			DateTime lastLoginTime = ctx.now();
			String domainId = HealthCustomConstants.ROOT_USER_DOMAIN_ID;
			SecUser secUser = secUserManagerOf(ctx)
					.createSecUser(ctx, login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken,
							verificationCode, verificationCodeExpire, lastLoginTime, domainId);
			// 关联userApp
			String title = "我的";
			String secUserId = secUser.getId();
			String appIcon = "user";
			boolean fullAccess = true;
			String permission = "MRWXmrwx";
			String objectType = User.INTERNAL_TYPE;
			String objectId = user.getId();
			String location = "/link/desktop";
			userAppManagerOf(ctx)
					.createUserApp(ctx, title, secUserId, appIcon, fullAccess, permission, objectType, objectId,
							location);

			return user;
		}

		this.throwsExceptionWithMessage(ctx, "找不到用户，登录失败");
		return null;
	}

	@Override
	protected void afterLogined(CustomHealthUserContextImpl ctx, User loginTarget) {
		// TODO Auto-generated method stub
		super.afterLogined(ctx, loginTarget);
	}

	@Override
	protected int processRequestTeacherLogin(CustomHealthUserContextImpl ctx) throws Exception {
		User user = this.processClientLogin(ctx, ctx.getLoginParam());

		return PRC_BY_DEFAULT;
	}

	@Override
	protected int processRequestCustomerViewClassDetail(CustomHealthUserContextImpl ctx) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int processRequestCustomerViewSurveyDetail(CustomHealthUserContextImpl ctx) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object loginForTest(CustomHealthUserContextImpl ctx, String formData) throws Exception {
		LoginParam logp = DebugUtil.getObjectMapper().readValue(formData, LoginParam.class);
		return clientLogin(ctx, logp);
	}

	@Override
	protected int processRequestCustomerAddClass(CustomHealthUserContextImpl ctx) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int processRequestCustomerSubmitClass(CustomHealthUserContextImpl ctx) throws Exception {
		TeacherFormProcessor inputFormData = (TeacherFormProcessor) ctx.getInputFormData();
		Integer classSize = inputFormData.getClassSize();
		String school = inputFormData.getSchool();
		String schoolClass = inputFormData.getSchoolClass();
		String name = inputFormData.getName();
		String mobile = inputFormData.getMobile();
		ChangeRequest cr = buildNewChangeRequest(ChangeRequestType.ADD_CLASS);
		cr
				.addTeacher(new Teacher()
						.updateClassSize(classSize)
							.updateSchool(school)
							.updateSchoolClass(schoolClass)
							.updateName(name)
							.updateMobile(mobile)
							.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
							.updateUser(ctx.getCurrentUserInfo()));
		ChangeRequest processed = getChangeRequestService().process(ctx, cr);
		ctx.setTeacherId(processed.getTeacherList().first().getId());
		return PRC_BY_DEFAULT;
	}

	public ChangeRequestCustomService getChangeRequestService() {
		return changeRequestService;
	}

	public void setChangeRequestService(ChangeRequestCustomService changeRequestService) {
		this.changeRequestService = changeRequestService;
	}

	@Override
	protected int processRequestCustomerAddSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		return 0;
	}

	public Object addStudent(CustomHealthUserContextImpl ctx) throws Exception {
		String studentAvatar = ctx.getStudentAvatar();
		String studentName = ctx.getStudentName();
		String studentNumber = ctx.getStudentNumber();
		ChangeRequest cr = new ChangeRequest()
				.updateRequestType(ChangeRequestType.refById(ChangeRequestType.ADD_STUDENT));
		cr
				.addStudent(new Student()
						.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
							.updateStudentNumber(studentNumber)
							.updateStudentName(studentName));
		getChangeRequestService().process(ctx, cr);
		return PRC_BY_DEFAULT;
	}

	@Override
	protected int processRequestStudentLogin(CustomHealthUserContextImpl ctx) throws Exception {
		LoginParam loginParam = ctx.getLoginParam();
		this.processClientLogin(ctx, loginParam);
		SmartList<StudentHealthSurvey> surveyImplList = studentHealthSurveyDaoOf(ctx)
				.findStudentHealthSurveyByClassDailyHealthSurvey(ctx.getSurveyId(), StudentHealthSurveyTokens.all());
		StudentHealthSurvey studentSurvey = surveyImplList == null ? null : surveyImplList.first();
		if (studentSurvey == null) {
			return PRC_BY_DEFAULT;
		}
		ctx.setStudentSurvey(studentSurvey);
		return PRC_SUBMITTED;
	}

	protected ChangeRequest buildNewChangeRequest(String changeRequestType) {
		return new ChangeRequest()
				.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
					.updateRequestType(ChangeRequestType.refById(changeRequestType))
					.updateName(MiscUtils.getEnumValueByKey(ChangeRequestType.CODE_NAME_LIST, changeRequestType));
	}

	@Override
	protected int processRequestCustomerStudentViewSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		ClassDailyHealthSurvey survey = classDailyHealthSurveyManagerOf(ctx)
				.loadClassDailyHealthSurvey(ctx, ctx.getSurveyId(), ClassDailyHealthSurveyTokens.start().toArray());
		StudentHealthSurvey studentSurvey = new DBQuery()
				.findStudentHealthSurveyWhichStudentBySurveyId(ctx, ctx.getSurveyId(),
						ctx.getCurrentUserInfo().getId());
		// 如果没有当前学生的问卷实例，根据问卷模版创建问卷实例
		if (studentSurvey == null) {
//			ChangeRequest cr = buildNewChangeRequest(ChangeRequestType.FILL_SURVEY);
//			cr
//					.addStudentHealthSurvey(new StudentHealthSurvey()
//							.updateClassDailyHealthSurvey(ClassDailyHealthSurvey.refById(ctx.getSurveyId()))
//								.updateSurveyStatus(SurveyStatus.refById(SurveyStatus.UN_SUBMITTED))
//								.updateTeacher(Teacher.refById(survey.getTeacher().getId()))
//								.updateStudent(null));
//			getChangeRequestService().process(ctx, cr);
			return PRC_BY_DEFAULT;
		}
		// 如果有当前学生的问卷实例，那么只做展示
		ctx.setStudentSurvey(studentSurvey);

		return PRC_SUBMITTED;
	}

	@Override
	protected int processRequestCustomerSubmitStudentSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		ChangeRequest cr = buildNewChangeRequest(ChangeRequestType.FILL_SURVEY);
		Student student = Optional.ofNullable(new DBQuery().findStudentWhichNameIs(ctx, ctx.getStudentName())).orElse(new Student().updateStudentName(ctx.getStudentName()));
		ClassDailyHealthSurvey survey = classDailyHealthSurveyManagerOf(ctx).loadClassDailyHealthSurvey(ctx, ctx.getSurveyId(), ClassDailyHealthSurveyTokens.start().toArray());
		List<Teacher> teacherList = MiscUtils.collectReferencedObjectWithType(ctx, survey, Teacher.class);
		teacherDaoOf(ctx).enhanceList(teacherList);
		StudentHealthSurvey studentSurvey = new StudentHealthSurvey()
				.updateAnswerTime(DateTime.now())
					.updateClassDailyHealthSurvey(ClassDailyHealthSurvey.refById(ctx.getSurveyId()))
					.updateStudent(student)
					.updateSurveyStatus(SurveyStatus.refById(SurveyStatus.SUBMITTE))
					.updateTeacher(survey.getTeacher());
		
		/**
		 * 假设提交的是
		 * {
		 * 	"answer":{
		 * 		"questionID1":"A"
		 * 		"questionID2":"b"
		 * 	},
		 * 	"studentName":"张三"
		 * }
		 */
		Map<String,String> answer =  new ObjectMapper().readValue(ctx.getAnswer(),Map.class);
		answer.forEach((questionId,ans)->{
			studentSurvey.addStudentDailyAnswer(new StudentDailyAnswer().updateAnswer(ans).updateQuestion(DailySurveyQuestion.refById(questionId)));
		});
		
		cr.addStudentHealthSurvey(studentSurvey);
		ChangeRequest processed = getChangeRequestService().process(ctx, cr);
		ctx.setStudentSurvey(processed.getStudentHealthSurveyList().first());
		return PRC_BY_DEFAULT;
	}

}