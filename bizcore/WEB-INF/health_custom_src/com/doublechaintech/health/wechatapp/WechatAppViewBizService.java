package com.doublechaintech.health.wechatapp;

import com.doublechaintech.health.user.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skynet.infrastructure.StorageService;
import com.terapico.caf.DateTime;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.utils.CollectionUtils;
import com.terapico.utils.DateTimeUtil;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.MapUtil;
import com.terapico.utils.RandomUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.Assert;

import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthException;
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
import com.doublechaintech.health.questiontype.QuestionType;
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
 * @author Jarry Zhou
 *
 */
public class WechatAppViewBizService extends BasicWechatAppViewBizService {
	private ChangeRequestCustomService changeRequestService;

	/**
	 * 处理请求：默认的客户端登录接口. 返回值：PRC_BY_DEFAULT: ;
	 */
	@Override
	protected int processRequestClientLogin(CustomHealthUserContextImpl ctx) throws Exception {
		LoginParam loginParam = ctx.getLoginParam();
		this.processClientLogin(ctx, loginParam);
		return PRC_BY_DEFAULT;
	}

	/**
	 * 处理请求：发布问卷. 返回值：PRC_BY_DEFAULT: ;
	 */
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
					.updateName(teacher.getSchoolClass() + "学生健康状况调查")
					.updateTeacher(Teacher.refById(ctx.getTeacherId()));
		SmartList<Question> questions = ctx.getDAOGroup().getQuestionDAO().findQuestionByPlatform(HealthCustomConstants.ROOT_PLATFORM_ID, 0, 4, QuestionTokens.all());
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
	protected void commonLog(CustomHealthUserContextImpl ctx, String eventCode, String title, String key1, String key2, String key3, Object detailInfo) {
		StringBuilder builder = new StringBuilder();
		ctx
				.log(builder
						.append("EventCode:")
							.append(eventCode)
							.append(" ")
							.append(title)
							.append(" ")
							.append(detailInfo)
							.append("keys:")
							.append(key1)
							.append(" ")
							.append(key2)
							.append(" ")
							.append(key3)
							.toString());
	}

	@Override
	protected User onNewLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam, BaseLoginHandler loginHandler) throws Exception {
		String loginMethod = loginParam.getLoginMethod();
		if (BaseLoginHandler.WECHAT_APP.equals(loginMethod)) {
			String openId = (String) loginHandler.getProcessedLoginInfo(ctx).get("openId");
			String sessionKey = (String) loginHandler.getProcessedLoginInfo(ctx).get("sessionKey");

			User user = userManagerOf(ctx).createUser(ctx, openId, "avatar.jpg", HealthCustomConstants.ROOT_PLATFORM_ID);
			wechatLoginInfoManagerOf(ctx).createWechatLoginInfo(ctx, user.getId(), "", openId, sessionKey);

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
					.createSecUser(ctx, login, mobile, email, pwd, weixinOpenid, weixinAppid, accessToken, verificationCode, verificationCodeExpire, lastLoginTime, domainId);
			// 关联userApp
			String title = "我的";
			String secUserId = secUser.getId();
			String appIcon = "user";
			boolean fullAccess = true;
			String permission = "MRWXmrwx";
			String objectType = User.INTERNAL_TYPE;
			String objectId = user.getId();
			String location = "/link/desktop";
			userAppManagerOf(ctx).createUserApp(ctx, title, secUserId, appIcon, fullAccess, permission, objectType, objectId, location);

			return user;
		}

		this.throwsExceptionWithMessage(ctx, "找不到用户，登录失败");
		return null;
	}

	@Override
	protected int processRequestCustomerViewClassDetail(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
	}

	@Override
	protected int processRequestCustomerViewSurveyDetail(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
	}

	public Object loginForTest(CustomHealthUserContextImpl ctx, String formData) throws Exception {
		LoginParam logp = DebugUtil.getObjectMapper().readValue(formData, LoginParam.class);
		return clientLogin(ctx, logp);
	}

	@Override
	protected int processRequestCustomerAddClass(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
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

	@Override
	protected int processRequestCustomerAddSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		return 0;
	}

	protected ChangeRequest buildNewChangeRequest(String changeRequestType) {
		return new ChangeRequest()
				.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
					.updateRequestType(ChangeRequestType.refById(changeRequestType))
					.updateName(MiscUtils.getEnumValueByKey(ChangeRequestType.CODE_NAME_LIST, changeRequestType));
	}

	@Override
	protected int processRequestCustomerStudentViewSurvey(CustomHealthUserContextImpl ctx) throws Exception {

		String surveyId = ctx.getSurveyId();
		ClassDailyHealthSurvey survey = classDailyHealthSurveyManagerOf(ctx).loadClassDailyHealthSurvey(ctx, surveyId, ClassDailyHealthSurveyTokens.start().toArray());
		if (survey == null) {
			throw new HealthException("调查问卷" + surveyId + "不存在.");
		}
		StudentHealthSurvey submittedStudentSurvey = new DBQuery().findStudentHealthSurveyWhichIsSubmittedByStudentAndSurveyId(ctx, surveyId, ctx.getCurrentUserInfo().getId());
		if (submittedStudentSurvey != null) {
			ctx.setAlreadySubmitted(true);
		}
		StudentHealthSurvey unSubmittedStudentSurvey = new DBQuery().findStudentHealthSurveyWhichStudentBySurveyId(ctx, surveyId, ctx.getCurrentUserInfo().getId());
		// 如果没有当前学生的问卷实例，根据问卷模版创建问卷实例
		if(unSubmittedStudentSurvey==null) {
			unSubmittedStudentSurvey = createNewStudentHealthSurvey(ctx, survey);
		}
		ctx.setStudentSurveyId(unSubmittedStudentSurvey.getId());
		return PRC_BY_DEFAULT;
	}

	protected StudentHealthSurvey createNewStudentHealthSurvey(CustomHealthUserContextImpl ctx, ClassDailyHealthSurvey survey) throws Exception {
		ChangeRequest cr = buildNewChangeRequest(ChangeRequestType.FILL_SURVEY);
		SmartList<StudentHealthSurvey> mySurveys = new DBQuery().queryStudentHealthSurveyListOfUser(ctx, ctx.getCurrentUserInfo().getId());
		Student student = null;
		if (mySurveys == null || mySurveys.isEmpty()) {
			student = new Student()
					.updateStudentName(ctx.getStudentName())
						.updateUser(ctx.getCurrentUserInfo())
						.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
						.updateGuardianMobile("13333333333");
			studentManagerOf(ctx).internalSaveStudent(ctx, student);
		} else {
			student = mySurveys.first().getStudent();
		}
		StudentHealthSurvey studentSurvey = new StudentHealthSurvey()
				.updateAnswerTime(DateTime.now())
					.updateClassDailyHealthSurvey(survey)
					.updateSurveyStatus(SurveyStatus.refById(SurveyStatus.UN_SUBMITTED))
					.updateTeacher(Teacher.refById(survey.getTeacher().getId()))
					.updateStudent(student);
		cr.addStudentHealthSurvey(studentSurvey);
		ChangeRequest processed = getChangeRequestService().process(ctx, cr);
		return processed.getStudentHealthSurveyList().first();
	}

	/**
	 * 假设提交的是 { "answer":{ "questionID1":"A" "questionID2":"b" }, "studentName":"张三"
	 * }
	 */
	@Override
	protected int processRequestCustomerSubmitStudentSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		ctx.log("student name:"+ctx.getStudentName());
		ctx.log("answers:"+ctx.getAnswer());
		ctx.log("survey id"+ctx.getSurveyId());
		String studentSurveyId = ctx.getSurveyId();
		StudentHealthSurvey studentSurvey = studentHealthSurveyManagerOf(ctx).loadStudentHealthSurvey(ctx, studentSurveyId, StudentHealthSurveyTokens.start().toArray());
		Student student = Optional
				.ofNullable(new DBQuery().findStudentWhichNameIs(ctx, ctx.getStudentName(), ctx.getCurrentUserInfo().getId()))
					.orElse(new Student()
							.updateStudentName(ctx.getStudentName())
								.updateUser(ctx.getCurrentUserInfo())
								.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID)))
					.updateGuardianMobile("13333333333");
		studentSurvey.updateAnswerTime(DateTime.now()).updateStudent(student).updateSurveyStatus(SurveyStatus.refById(SurveyStatus.SUBMITTE));
		Map<String, String> answer = new ObjectMapper().readValue(ctx.getAnswer(), Map.class);
		validateAnswer(ctx,answer,studentSurvey);
		answer
				.forEach((questionId, ans) -> studentSurvey
						.addStudentDailyAnswer(new StudentDailyAnswer().updateAnswer(ans).updateQuestion(DailySurveyQuestion.refById(questionId))));
		ChangeRequest cr = buildNewChangeRequest(ChangeRequestType.FILL_SURVEY);
		cr.addStudentHealthSurvey(studentSurvey);
		checkerOf(ctx).checkAndFixStudentHealthSurvey(studentSurvey);
		checkerOf(ctx).throwExceptionIfHasErrors(HealthException.class);
		ChangeRequest processed = getChangeRequestService().process(ctx, cr);
		ctx.setStudentSurvey(processed.getStudentHealthSurveyList().first());
		ctx.setStudentSurveyId(processed.getStudentHealthSurveyList().first().getId());
		return PRC_BY_DEFAULT;
	}
	
	protected void validateAnswer(CustomHealthUserContextImpl ctx,Map<String, String> answers,StudentHealthSurvey studentSurvey) throws Exception {
		if(answers==null||answers.isEmpty()) {
			throw new HealthException("请填写完所有问题的答案！");
		}
		
		/**
		 * 验证是不是答的对的题目
		 */
		List<ClassDailyHealthSurvey> surveys = MiscUtils.collectReferencedObjectWithType(ctx, studentSurvey, ClassDailyHealthSurvey.class);
		classDailyHealthSurveyDaoOf(ctx).enhanceList(surveys);
		ClassDailyHealthSurvey survey = studentSurvey.getClassDailyHealthSurvey();
		classDailyHealthSurveyDaoOf(ctx).loadOurDailySurveyQuestionList(ctx, CollectionUtils.toList(survey), ClassDailyHealthSurveyTokens.all());
		SmartList<DailySurveyQuestion> questions = survey.getDailySurveyQuestionList();
		Map<String, List<DailySurveyQuestion>> questionMap = questions.stream().collect(Collectors.groupingBy(DailySurveyQuestion::getId));
		Integer answeredCount = Optional.ofNullable(answers).map(Map::size).orElse(0);
		ctx.log("answer count"+answeredCount);
		ctx.log("survey"+survey);
		ctx.log("questions:"+questions.size());
		if(answeredCount<questions.size()) {
			throw new HealthException("请填写所有问题的答案！");
		}else if(answeredCount>questions.size()) {
			throw new HealthException("请正确提交问题的答案！");
		}
		
		List<String> selectList = CollectionUtils.toList("A","B","C","D");
		for(String questionId:answers.keySet()) {
			List<DailySurveyQuestion> question = questionMap.get(questionId);
			String answer = answers.get(questionId);
			if(question==null||question.isEmpty()) {
				throw new HealthException("请提交正确的问题答案！");
			}
			DailySurveyQuestion q = question.get(0);
			if(QuestionType.SINGLE_SELECT.equals(question.get(0).getQuestionType().getId())) {
				must(selectList.contains(answer),"请提交正确的选项！");
			}else {
				must(answer!=null&&!answer.isEmpty(),"请填写所有问题的答案！");
			}
		}
		checkerOf(ctx).checkStudentNameOfStudent(ctx.getStudentName());
		checkerOf(ctx).throwExceptionIfHasErrors(HealthException.class);
	}

	public Object export(CustomHealthUserContextImpl ctx, String surveyId) throws Exception {
		ctx.forceRenderingAsJson();
		ClassDailyHealthSurvey survey = new DBQuery().findClassDailyHealthSurveyWhichId(ctx, surveyId);
		if (survey == null) {
			throw new HealthException("找不到调查问卷" + surveyId);
		}
		HSSFWorkbook excel = createExcel(survey);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			excel.write(bos);
		} finally {
			bos.close();
		}
		byte[] bytes = bos.toByteArray();
		StorageService storageService = (StorageService) ctx.getBean("storageService");
		String url = storageService.upload("jiyibao/" + survey.getName() + ".xls", bytes);
		survey.updateDownloadUrl(url);
		classDailyHealthSurveyManagerOf(ctx).internalSaveClassDailyHealthSurvey(ctx, survey);
		return MapUtil.put("url", survey.getDownloadUrl()).into_map();
	}

	public static String makeExportSurveyUrl(CustomHealthUserContextImpl ctx, String surveyId) {
		return makeUrl("export", surveyId);
	}

	protected HSSFWorkbook createExcel(ClassDailyHealthSurvey survey) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(survey.getName());

		Row titleRow = sheet.createRow(0);
		SmartList<DailySurveyQuestion> questionList = survey.getDailySurveyQuestionList();
		titleRow.createCell(0).setCellValue("日期");
		titleRow.createCell(1).setCellValue("班级");
		titleRow.createCell(2).setCellValue("姓名");
		Map<String, Integer> rowMap = new HashMap<>();
		for (int i = 0; i < questionList.size(); i++) {
			DailySurveyQuestion question = questionList.get(i);
			titleRow.createCell(i + 3).setCellValue(question.getTopic());
			rowMap.put(question.getId(), i + 3);
		}

		SmartList<StudentHealthSurvey> studentSurveyList = survey.getStudentHealthSurveyList();

		for (int i = 0; i < studentSurveyList.size(); i++) {
			Row row = sheet.createRow(i + 1);
			StudentHealthSurvey studentHealthSurvey = studentSurveyList.get(i);
			row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(studentHealthSurvey.getAnswerTime()));
			row.createCell(1).setCellValue(survey.getTeacher().getSchool() + survey.getTeacher().getSchoolClass());
			row.createCell(2).setCellValue(studentHealthSurvey.getStudent().getStudentName());
			SmartList<StudentDailyAnswer> answers = studentHealthSurvey.getStudentDailyAnswerList();
			for (StudentDailyAnswer as : answers) {
				DailySurveyQuestion question = as.getQuestion();
				Object answerText = QuestionType.SINGLE_SELECT.equals(question.getQuestionType().getId()) ? question.propertyOf("option" + as.getAnswer()) : as.getAnswer();
				row.createCell(rowMap.get(as.getQuestion().getId())).setCellValue((String) answerText);
			}
		}
		int columnCount = rowMap.size() + 3;
		int rowCount = studentSurveyList.size() + 1;
		for (int i = 0; i < columnCount; i++) {
			final int ii = i;
			int colMaxCellLength = IntStream.range(0, rowCount).map(j -> sheet.getRow(j).getCell(ii).getStringCellValue().getBytes().length * 256).max().orElse(30);
			sheet.setColumnWidth(i, colMaxCellLength);
		}
		return hssfWorkbook;
	}

	@Override
	protected int processRequestViewHomepage(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
	}

	@Override
	protected int processRequestCustomerViewStudentSurveyDetail(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
	}

	public ChangeRequestCustomService getChangeRequestService() {
		return changeRequestService;
	}

	public void setChangeRequestService(ChangeRequestCustomService changeRequestService) {
		this.changeRequestService = changeRequestService;
	}

	@Override
	protected int processRequestCustomerSwitchToTeacher(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
	}

	@Override
	protected int processRequestCustomerSwitchToStudent(CustomHealthUserContextImpl ctx) throws Exception {
		return PRC_BY_DEFAULT;
	}

	@Override
	protected int processRequestCustomerUpdateProfile(CustomHealthUserContextImpl ctx) throws Exception {
		User user = ctx.getCurrentUserInfo().updateName(ctx.getName()).updateAvatar(ctx.getAvatar());
		User savedUser = userManagerOf(ctx).internalSaveUser(ctx, user);
		cacheLoginedUser(ctx, ctx.getJwtKeyId(), ctx.getSecUser(), currentApp(ctx), savedUser);

		return StringUtils.equalsIgnoreCase(ctx.getUserType(), "guardian") ? PRC_BY_DEFAULT : PRC_SWITCHTOTEACHER;
	}
	
	protected void must(boolean condition,String message) throws HealthException {
		if(!condition) {
			throw new HealthException(message);
		}
	}

}