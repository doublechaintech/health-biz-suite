package com.doublechaintech.health.wechatapp;

import com.doublechaintech.health.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skynet.infrastructure.StorageService;
import com.terapico.caf.BlobObject;
import com.terapico.caf.DateTime;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.utils.DateTimeUtil;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.MapUtil;
import com.terapico.utils.RandomUtil;
import com.terapico.utils.TextUtil;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

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
					.findQuestionByPlatform(HealthCustomConstants.ROOT_PLATFORM_ID, 0, 4, QuestionTokens.all());
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
	protected void commonLog(CustomHealthUserContextImpl ctx, String eventCode, String title, String key1,
			String key2, String key3, Object detailInfo) {
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
	protected User onNewLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam, BaseLoginHandler loginHandler)
			throws Exception {
		String loginMethod = loginParam.getLoginMethod();
		if (BaseLoginHandler.WECHAT_APP.equals(loginMethod)) {
			String openId = (String) loginHandler.getProcessedLoginInfo(ctx).get("openId");
			String sessionKey = (String) loginHandler.getProcessedLoginInfo(ctx).get("sessionKey");
			
			User user = userManagerOf(ctx)
					.createUser(ctx, openId, "avatar.jpg", HealthCustomConstants.ROOT_PLATFORM_ID);
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

//	public Object addStudent(CustomHealthUserContextImpl ctx) throws Exception {
//		String studentAvatar = ctx.getStudentAvatar();
//		String studentName = ctx.getStudentName();
//		String studentNumber = ctx.getStudentNumber();
//		ChangeRequest cr = new ChangeRequest()
//				.updateRequestType(ChangeRequestType.refById(ChangeRequestType.ADD_STUDENT));
//		cr
//				.addStudent(new Student()
//						.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
//							.updateStudentNumber(studentNumber)
//							.updateStudentName(studentName));
//		getChangeRequestService().process(ctx, cr);
//		return PRC_BY_DEFAULT;
//	}


	protected ChangeRequest buildNewChangeRequest(String changeRequestType) {
		return new ChangeRequest()
				.updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))
					.updateRequestType(ChangeRequestType.refById(changeRequestType))
					.updateName(MiscUtils.getEnumValueByKey(ChangeRequestType.CODE_NAME_LIST, changeRequestType));
	}

	@Override
	protected int processRequestCustomerStudentViewSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		StudentHealthSurvey studentSurvey = new DBQuery()
				.findStudentHealthSurveyWhichStudentBySurveyId(ctx, ctx.getSurveyId(),
						ctx.getCurrentUserInfo().getId());
		// 如果没有当前学生的问卷实例，根据问卷模版创建问卷实例
		if (studentSurvey == null) {
			return PRC_BY_DEFAULT;
		}
		// 如果有当前学生的问卷实例，那么只做展示
		ctx.setStudentSurvey(studentSurvey);
		return PRC_SUBMITTED;
	}

	@Override
	protected int processRequestCustomerSubmitStudentSurvey(CustomHealthUserContextImpl ctx) throws Exception {
		ChangeRequest cr = buildNewChangeRequest(ChangeRequestType.FILL_SURVEY);
		Student student = Optional.ofNullable(new DBQuery().findStudentWhichNameIs(ctx, ctx.getStudentName(),ctx.getCurrentUserInfo().getId())).orElse(new Student().updateStudentName(ctx.getStudentName()).updateUser(ctx.getCurrentUserInfo()).updatePlatform(Platform.refById(HealthCustomConstants.ROOT_PLATFORM_ID))).updateGuardianMobile("13333333333");
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
		checkerOf(ctx).checkAndFixStudentHealthSurvey(studentSurvey);
		checkerOf(ctx).throwExceptionIfHasErrors(HealthException.class);
		cr.addStudentHealthSurvey(studentSurvey);
		ChangeRequest processed = getChangeRequestService().process(ctx, cr);
		ctx.setStudentSurvey(processed.getStudentHealthSurveyList().first());
		ctx.setStudentSurveyId(processed.getStudentHealthSurveyList().first().getId());
		return PRC_BY_DEFAULT;
	}
	
	
	public Object export(CustomHealthUserContextImpl ctx,String surveyId) throws Exception {
		ctx.forceRenderingAsJson();
		ClassDailyHealthSurvey survey = new DBQuery().findClassDailyHealthSurveyWhichId(ctx, surveyId);
		if(survey==null) {
			throw new HealthException("找不到调查问卷"+surveyId);
		}
		if(!TextUtil.isBlank(survey.getDownloadUrl())) {
			return MapUtil.put("url", survey.getDownloadUrl()).into_map();
		}
		HSSFWorkbook excel = createExcel(ctx,survey);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			excel.write(bos);
		} finally {
		    bos.close();
		}
		byte[] bytes = bos.toByteArray();
		StorageService storageService = (StorageService) ctx.getBean("storageService");
		String url = storageService.upload("jiyibao/"+survey.getName()+".xls",bytes);
		survey.updateDownloadUrl(url);
		classDailyHealthSurveyManagerOf(ctx).internalSaveClassDailyHealthSurvey(ctx, survey);
//		BlobObject blobObject = new BlobObject();
//		blobObject.setData(bytes);
//		blobObject.addHeader("content-type", "application/octet-stream;charset=utf-8;");
////		classDailyHealthSurveyManagerOf(ctx).internalSaveClassDailyHealthSurvey(ctx, survey);
		return  MapUtil.put("url", survey.getDownloadUrl()).into_map();
	}
	
	public static String makeExportSurveyUrl(CustomHealthUserContextImpl ctx, String surveyId){
		return makeUrl("export", surveyId);
	}
	
	protected HSSFWorkbook createExcel(CustomHealthUserContextImpl ctx,ClassDailyHealthSurvey survey ) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(survey.getName());
		
//		Row head = sheet.createRow(0);
//		head.createCell(0).setCellValue(survey.getName());
		Row titleRow = sheet.createRow(0);
		SmartList<DailySurveyQuestion> questionList = survey.getDailySurveyQuestionList();
		titleRow.createCell(0).setCellValue("日期");
		titleRow.createCell(1).setCellValue("班级");
		titleRow.createCell(2).setCellValue("姓名");
		Map<String,Integer> rowMap = new HashMap<>();
		for (int i = 0; i < questionList.size(); i++) {
			DailySurveyQuestion question = questionList.get(i);
			titleRow.createCell(i+3).setCellValue(question.getTopic());
			rowMap.put(question.getId(),i+3);
		}
		
		SmartList<StudentHealthSurvey> studentSurveyList = survey.getStudentHealthSurveyList();
		
		
		for (int i = 0; i < studentSurveyList.size(); i++) {
			Row row = sheet.createRow(i+1);
			StudentHealthSurvey studentHealthSurvey = studentSurveyList.get(i);
			row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(studentHealthSurvey.getAnswerTime()));
			row.createCell(1).setCellValue(survey.getTeacher().getSchool()+survey.getTeacher().getSchoolClass());
			row.createCell(2).setCellValue(studentHealthSurvey.getStudent().getStudentName());
			SmartList<StudentDailyAnswer> answers = studentHealthSurvey.getStudentDailyAnswerList();
			for (StudentDailyAnswer as : answers) {
				DailySurveyQuestion question = as.getQuestion();
				Object answerText = QuestionType.SINGLE_SELECT.equals(question.getQuestionType().getId())?question.propertyOf("option"+as.getAnswer()):as.getAnswer();
				row.createCell(rowMap.get(as.getQuestion().getId())).setCellValue((String)answerText);
			}
		}
		for (int i = 0; i < rowMap.size()+3; i++) {
			sheet.autoSizeColumn(i);
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
		return PRC_BY_DEFAULT;
	}
	
	

}