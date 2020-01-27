package com.doublechaintech.health;


import com.terapico.caf.viewpage.SerializeScope;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.questionsource.QuestionSource;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.usertype.UserType;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.userdomain.UserDomain;
import com.doublechaintech.health.userwhitelist.UserWhiteList;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.doublechaintech.health.quicklink.QuickLink;
import com.doublechaintech.health.listaccess.ListAccess;
import com.doublechaintech.health.objectaccess.ObjectAccess;
import com.doublechaintech.health.loginhistory.LoginHistory;
import com.doublechaintech.health.genericform.GenericForm;
import com.doublechaintech.health.formmessage.FormMessage;
import com.doublechaintech.health.formfieldmessage.FormFieldMessage;
import com.doublechaintech.health.formfield.FormField;
import com.doublechaintech.health.formaction.FormAction;
import com.doublechaintech.health.candidatecontainer.CandidateContainer;
import com.doublechaintech.health.candidateelement.CandidateElement;
import com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentify;
import com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentify;


public class HealthBaseViewScope {

	protected static SerializeScope PlatformBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		;
	/** 用于Platform的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformSummaryScope() {
		return PlatformBaseSummaryScope;
	}

	protected static SerializeScope ProvinceBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Province.ID_PROPERTY)
		.field(Province.NAME_PROPERTY)
		.field(Province.CREATE_TIME_PROPERTY)
		;
	/** 用于Province的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getProvinceSummaryScope() {
		return ProvinceBaseSummaryScope;
	}

	protected static SerializeScope CityBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(City.ID_PROPERTY)
		.field(City.NAME_PROPERTY)
		.field(City.CREATE_TIME_PROPERTY)
		;
	/** 用于City的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCitySummaryScope() {
		return CityBaseSummaryScope;
	}

	protected static SerializeScope DistrictBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(District.ID_PROPERTY)
		.field(District.NAME_PROPERTY)
		.field(District.CREATE_TIME_PROPERTY)
		;
	/** 用于District的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getDistrictSummaryScope() {
		return DistrictBaseSummaryScope;
	}

	protected static SerializeScope LocationBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Location.ID_PROPERTY)
		.field(Location.NAME_PROPERTY)
		.field(Location.ADDRESS_PROPERTY)
		.field(Location.LATITUDE_PROPERTY)
		.field(Location.LONGITUDE_PROPERTY)
		;
	/** 用于Location的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLocationSummaryScope() {
		return LocationBaseSummaryScope;
	}

	protected static SerializeScope SchoolClassBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SchoolClass.ID_PROPERTY)
		.field(SchoolClass.NAME_PROPERTY)
		.field(SchoolClass.CREATE_TIME_PROPERTY)
		.field(SchoolClass.SCHOOLE_PROPERTY)
		;
	/** 用于SchoolClass的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSchoolClassSummaryScope() {
		return SchoolClassBaseSummaryScope;
	}

	protected static SerializeScope TeacherBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Teacher.ID_PROPERTY)
		.field(Teacher.NAME_PROPERTY)
		.field(Teacher.MOBILE_PROPERTY)
		.field(Teacher.SCHOOLE_PROPERTY)
		.field(Teacher.CREATE_TIME_PROPERTY)
		;
	/** 用于Teacher的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getTeacherSummaryScope() {
		return TeacherBaseSummaryScope;
	}

	protected static SerializeScope GuardianBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Guardian.ID_PROPERTY)
		.field(Guardian.NAME_PROPERTY)
		.field(Guardian.MOBILE_PROPERTY)
		.field(Guardian.CREATE_TIME_PROPERTY)
		;
	/** 用于Guardian的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGuardianSummaryScope() {
		return GuardianBaseSummaryScope;
	}

	protected static SerializeScope QuestionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Question.ID_PROPERTY)
		.field(Question.TOPIC_PROPERTY)
		.field(Question.OPTION_A_PROPERTY)
		.field(Question.OPTION_B_PROPERTY)
		.field(Question.OPTION_C_PROPERTY)
		.field(Question.OPTION_D_PROPERTY)
		;
	/** 用于Question的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuestionSummaryScope() {
		return QuestionBaseSummaryScope;
	}

	protected static SerializeScope QuestionTypeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionType.ID_PROPERTY)
		.field(QuestionType.NAME_PROPERTY)
		.field(QuestionType.CODE_PROPERTY)
		;
	/** 用于QuestionType的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuestionTypeSummaryScope() {
		return QuestionTypeBaseSummaryScope;
	}

	protected static SerializeScope QuestionSourceBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionSource.ID_PROPERTY)
		.field(QuestionSource.NAME_PROPERTY)
		.field(QuestionSource.CODE_PROPERTY)
		;
	/** 用于QuestionSource的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuestionSourceSummaryScope() {
		return QuestionSourceBaseSummaryScope;
	}

	protected static SerializeScope ClassQuestionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassQuestion.ID_PROPERTY)
		.field(ClassQuestion.TOPIC_PROPERTY)
		.field(ClassQuestion.OPTION_A_PROPERTY)
		.field(ClassQuestion.OPTION_B_PROPERTY)
		.field(ClassQuestion.OPTION_C_PROPERTY)
		.field(ClassQuestion.OPTION_D_PROPERTY)
		;
	/** 用于ClassQuestion的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getClassQuestionSummaryScope() {
		return ClassQuestionBaseSummaryScope;
	}

	protected static SerializeScope ClassDailyHealthSurveyBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassDailyHealthSurvey.ID_PROPERTY)
		.field(ClassDailyHealthSurvey.NAME_PROPERTY)
		.field(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY)
		;
	/** 用于ClassDailyHealthSurvey的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getClassDailyHealthSurveySummaryScope() {
		return ClassDailyHealthSurveyBaseSummaryScope;
	}

	protected static SerializeScope DailySurveyQuestionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(DailySurveyQuestion.ID_PROPERTY)
		.field(DailySurveyQuestion.TOPIC_PROPERTY)
		.field(DailySurveyQuestion.OPTION_A_PROPERTY)
		.field(DailySurveyQuestion.OPTION_B_PROPERTY)
		.field(DailySurveyQuestion.OPTION_C_PROPERTY)
		.field(DailySurveyQuestion.OPTION_D_PROPERTY)
		;
	/** 用于DailySurveyQuestion的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getDailySurveyQuestionSummaryScope() {
		return DailySurveyQuestionBaseSummaryScope;
	}

	protected static SerializeScope StudentBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Student.ID_PROPERTY)
		.field(Student.NAME_PROPERTY)
		.field(Student.GENDER_PROPERTY)
		.field(Student.STUDENT_ID_PROPERTY)
		;
	/** 用于Student的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getStudentSummaryScope() {
		return StudentBaseSummaryScope;
	}

	protected static SerializeScope StudentHealthSurveyBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentHealthSurvey.ID_PROPERTY)
		.field(StudentHealthSurvey.ANSWER_TIME_PROPERTY)
		.field(StudentHealthSurvey.CREATE_TIME_PROPERTY)
		.field(StudentHealthSurvey.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于StudentHealthSurvey的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getStudentHealthSurveySummaryScope() {
		return StudentHealthSurveyBaseSummaryScope;
	}

	protected static SerializeScope StudentDailyAnswerBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentDailyAnswer.ID_PROPERTY)
		.field(StudentDailyAnswer.ANSWER_PROPERTY)
		.field(StudentDailyAnswer.CREATE_TIME_PROPERTY)
		.field(StudentDailyAnswer.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于StudentDailyAnswer的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getStudentDailyAnswerSummaryScope() {
		return StudentDailyAnswerBaseSummaryScope;
	}

	protected static SerializeScope SurveyStatusBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SurveyStatus.ID_PROPERTY)
		.field(SurveyStatus.NAME_PROPERTY)
		.field(SurveyStatus.CODE_PROPERTY)
		;
	/** 用于SurveyStatus的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSurveyStatusSummaryScope() {
		return SurveyStatusBaseSummaryScope;
	}

	protected static SerializeScope WechatUserBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatUser.ID_PROPERTY)
		.field(WechatUser.NAME_PROPERTY)
		.field(WechatUser.AVATAR_PROPERTY)
		.field(WechatUser.CREATE_TIME_PROPERTY)
		;
	/** 用于WechatUser的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatUserSummaryScope() {
		return WechatUserBaseSummaryScope;
	}

	protected static SerializeScope UserTypeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserType.ID_PROPERTY)
		.field(UserType.NAME_PROPERTY)
		.field(UserType.CODE_PROPERTY)
		;
	/** 用于UserType的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserTypeSummaryScope() {
		return UserTypeBaseSummaryScope;
	}

	protected static SerializeScope WechatLoginInfoBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatLoginInfo.ID_PROPERTY)
		.field(WechatLoginInfo.APP_ID_PROPERTY)
		.field(WechatLoginInfo.OPEN_ID_PROPERTY)
		.field(WechatLoginInfo.SESSION_KEY_PROPERTY)
		.field(WechatLoginInfo.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于WechatLoginInfo的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatLoginInfoSummaryScope() {
		return WechatLoginInfoBaseSummaryScope;
	}

	protected static SerializeScope ChangeRequestBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		;
	/** 用于ChangeRequest的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSummaryScope() {
		return ChangeRequestBaseSummaryScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		;
	/** 用于ChangeRequestType的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeSummaryScope() {
		return ChangeRequestTypeBaseSummaryScope;
	}

	protected static SerializeScope UserDomainBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSummaryScope() {
		return UserDomainBaseSummaryScope;
	}

	protected static SerializeScope UserWhiteListBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSummaryScope() {
		return UserWhiteListBaseSummaryScope;
	}

	protected static SerializeScope SecUserBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于SecUser的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserSummaryScope() {
		return SecUserBaseSummaryScope;
	}

	protected static SerializeScope UserAppBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppSummaryScope() {
		return UserAppBaseSummaryScope;
	}

	protected static SerializeScope QuickLinkBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		;
	/** 用于QuickLink的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkSummaryScope() {
		return QuickLinkBaseSummaryScope;
	}

	protected static SerializeScope ListAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessSummaryScope() {
		return ListAccessBaseSummaryScope;
	}

	protected static SerializeScope ObjectAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		;
	/** 用于ObjectAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessSummaryScope() {
		return ObjectAccessBaseSummaryScope;
	}

	protected static SerializeScope LoginHistoryBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySummaryScope() {
		return LoginHistoryBaseSummaryScope;
	}

	protected static SerializeScope GenericFormBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		;
	/** 用于GenericForm的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGenericFormSummaryScope() {
		return GenericFormBaseSummaryScope;
	}

	protected static SerializeScope FormMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormMessageSummaryScope() {
		return FormMessageBaseSummaryScope;
	}

	protected static SerializeScope FormFieldMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageSummaryScope() {
		return FormFieldMessageBaseSummaryScope;
	}

	protected static SerializeScope FormFieldBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldSummaryScope() {
		return FormFieldBaseSummaryScope;
	}

	protected static SerializeScope FormActionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		;
	/** 用于FormAction的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormActionSummaryScope() {
		return FormActionBaseSummaryScope;
	}

	protected static SerializeScope CandidateContainerBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		;
	/** 用于CandidateContainer的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerSummaryScope() {
		return CandidateContainerBaseSummaryScope;
	}

	protected static SerializeScope CandidateElementBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		;
	/** 用于CandidateElement的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementSummaryScope() {
		return CandidateElementBaseSummaryScope;
	}

	protected static SerializeScope WechatWorkappIdentifyBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentify.ID_PROPERTY)
		.field(WechatWorkappIdentify.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentify.USER_ID_PROPERTY)
		.field(WechatWorkappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentify的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentifySummaryScope() {
		return WechatWorkappIdentifyBaseSummaryScope;
	}

	protected static SerializeScope WechatMiniappIdentifyBaseSummaryScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentify.ID_PROPERTY)
		.field(WechatMiniappIdentify.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentify.APP_ID_PROPERTY)
		.field(WechatMiniappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentify的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentifySummaryScope() {
		return WechatMiniappIdentifyBaseSummaryScope;
	}

	protected static SerializeScope PlatformBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		;
	/** 用于Platform的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformSecondaryListItemScope() {
		return PlatformBaseSecondaryListItemScope;
	}

	protected static SerializeScope ProvinceBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Province.ID_PROPERTY)
		.field(Province.NAME_PROPERTY)
		.field(Province.CREATE_TIME_PROPERTY)
		;
	/** 用于Province的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getProvinceSecondaryListItemScope() {
		return ProvinceBaseSecondaryListItemScope;
	}

	protected static SerializeScope CityBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(City.ID_PROPERTY)
		.field(City.NAME_PROPERTY)
		.field(City.CREATE_TIME_PROPERTY)
		;
	/** 用于City的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCitySecondaryListItemScope() {
		return CityBaseSecondaryListItemScope;
	}

	protected static SerializeScope DistrictBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(District.ID_PROPERTY)
		.field(District.NAME_PROPERTY)
		.field(District.CREATE_TIME_PROPERTY)
		;
	/** 用于District的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getDistrictSecondaryListItemScope() {
		return DistrictBaseSecondaryListItemScope;
	}

	protected static SerializeScope LocationBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Location.ID_PROPERTY)
		.field(Location.NAME_PROPERTY)
		.field(Location.ADDRESS_PROPERTY)
		.field(Location.LATITUDE_PROPERTY)
		.field(Location.LONGITUDE_PROPERTY)
		;
	/** 用于Location的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLocationSecondaryListItemScope() {
		return LocationBaseSecondaryListItemScope;
	}

	protected static SerializeScope SchoolClassBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SchoolClass.ID_PROPERTY)
		.field(SchoolClass.NAME_PROPERTY)
		.field(SchoolClass.CREATE_TIME_PROPERTY)
		.field(SchoolClass.SCHOOLE_PROPERTY)
		;
	/** 用于SchoolClass的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSchoolClassSecondaryListItemScope() {
		return SchoolClassBaseSecondaryListItemScope;
	}

	protected static SerializeScope TeacherBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Teacher.ID_PROPERTY)
		.field(Teacher.NAME_PROPERTY)
		.field(Teacher.MOBILE_PROPERTY)
		.field(Teacher.SCHOOLE_PROPERTY)
		.field(Teacher.CREATE_TIME_PROPERTY)
		;
	/** 用于Teacher的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getTeacherSecondaryListItemScope() {
		return TeacherBaseSecondaryListItemScope;
	}

	protected static SerializeScope GuardianBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Guardian.ID_PROPERTY)
		.field(Guardian.NAME_PROPERTY)
		.field(Guardian.MOBILE_PROPERTY)
		.field(Guardian.CREATE_TIME_PROPERTY)
		;
	/** 用于Guardian的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGuardianSecondaryListItemScope() {
		return GuardianBaseSecondaryListItemScope;
	}

	protected static SerializeScope QuestionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Question.ID_PROPERTY)
		.field(Question.TOPIC_PROPERTY)
		.field(Question.OPTION_A_PROPERTY)
		.field(Question.OPTION_B_PROPERTY)
		.field(Question.OPTION_C_PROPERTY)
		.field(Question.OPTION_D_PROPERTY)
		;
	/** 用于Question的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuestionSecondaryListItemScope() {
		return QuestionBaseSecondaryListItemScope;
	}

	protected static SerializeScope QuestionTypeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionType.ID_PROPERTY)
		.field(QuestionType.NAME_PROPERTY)
		.field(QuestionType.CODE_PROPERTY)
		;
	/** 用于QuestionType的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuestionTypeSecondaryListItemScope() {
		return QuestionTypeBaseSecondaryListItemScope;
	}

	protected static SerializeScope QuestionSourceBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionSource.ID_PROPERTY)
		.field(QuestionSource.NAME_PROPERTY)
		.field(QuestionSource.CODE_PROPERTY)
		;
	/** 用于QuestionSource的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuestionSourceSecondaryListItemScope() {
		return QuestionSourceBaseSecondaryListItemScope;
	}

	protected static SerializeScope ClassQuestionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassQuestion.ID_PROPERTY)
		.field(ClassQuestion.TOPIC_PROPERTY)
		.field(ClassQuestion.OPTION_A_PROPERTY)
		.field(ClassQuestion.OPTION_B_PROPERTY)
		.field(ClassQuestion.OPTION_C_PROPERTY)
		.field(ClassQuestion.OPTION_D_PROPERTY)
		;
	/** 用于ClassQuestion的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getClassQuestionSecondaryListItemScope() {
		return ClassQuestionBaseSecondaryListItemScope;
	}

	protected static SerializeScope ClassDailyHealthSurveyBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassDailyHealthSurvey.ID_PROPERTY)
		.field(ClassDailyHealthSurvey.NAME_PROPERTY)
		.field(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY)
		;
	/** 用于ClassDailyHealthSurvey的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getClassDailyHealthSurveySecondaryListItemScope() {
		return ClassDailyHealthSurveyBaseSecondaryListItemScope;
	}

	protected static SerializeScope DailySurveyQuestionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(DailySurveyQuestion.ID_PROPERTY)
		.field(DailySurveyQuestion.TOPIC_PROPERTY)
		.field(DailySurveyQuestion.OPTION_A_PROPERTY)
		.field(DailySurveyQuestion.OPTION_B_PROPERTY)
		.field(DailySurveyQuestion.OPTION_C_PROPERTY)
		.field(DailySurveyQuestion.OPTION_D_PROPERTY)
		;
	/** 用于DailySurveyQuestion的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getDailySurveyQuestionSecondaryListItemScope() {
		return DailySurveyQuestionBaseSecondaryListItemScope;
	}

	protected static SerializeScope StudentBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Student.ID_PROPERTY)
		.field(Student.NAME_PROPERTY)
		.field(Student.GENDER_PROPERTY)
		.field(Student.STUDENT_ID_PROPERTY)
		;
	/** 用于Student的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getStudentSecondaryListItemScope() {
		return StudentBaseSecondaryListItemScope;
	}

	protected static SerializeScope StudentHealthSurveyBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentHealthSurvey.ID_PROPERTY)
		.field(StudentHealthSurvey.ANSWER_TIME_PROPERTY)
		.field(StudentHealthSurvey.CREATE_TIME_PROPERTY)
		.field(StudentHealthSurvey.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于StudentHealthSurvey的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getStudentHealthSurveySecondaryListItemScope() {
		return StudentHealthSurveyBaseSecondaryListItemScope;
	}

	protected static SerializeScope StudentDailyAnswerBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentDailyAnswer.ID_PROPERTY)
		.field(StudentDailyAnswer.ANSWER_PROPERTY)
		.field(StudentDailyAnswer.CREATE_TIME_PROPERTY)
		.field(StudentDailyAnswer.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于StudentDailyAnswer的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getStudentDailyAnswerSecondaryListItemScope() {
		return StudentDailyAnswerBaseSecondaryListItemScope;
	}

	protected static SerializeScope SurveyStatusBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SurveyStatus.ID_PROPERTY)
		.field(SurveyStatus.NAME_PROPERTY)
		.field(SurveyStatus.CODE_PROPERTY)
		;
	/** 用于SurveyStatus的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSurveyStatusSecondaryListItemScope() {
		return SurveyStatusBaseSecondaryListItemScope;
	}

	protected static SerializeScope WechatUserBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatUser.ID_PROPERTY)
		.field(WechatUser.NAME_PROPERTY)
		.field(WechatUser.AVATAR_PROPERTY)
		.field(WechatUser.CREATE_TIME_PROPERTY)
		;
	/** 用于WechatUser的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatUserSecondaryListItemScope() {
		return WechatUserBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserTypeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserType.ID_PROPERTY)
		.field(UserType.NAME_PROPERTY)
		.field(UserType.CODE_PROPERTY)
		;
	/** 用于UserType的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserTypeSecondaryListItemScope() {
		return UserTypeBaseSecondaryListItemScope;
	}

	protected static SerializeScope WechatLoginInfoBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatLoginInfo.ID_PROPERTY)
		.field(WechatLoginInfo.APP_ID_PROPERTY)
		.field(WechatLoginInfo.OPEN_ID_PROPERTY)
		.field(WechatLoginInfo.SESSION_KEY_PROPERTY)
		.field(WechatLoginInfo.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于WechatLoginInfo的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatLoginInfoSecondaryListItemScope() {
		return WechatLoginInfoBaseSecondaryListItemScope;
	}

	protected static SerializeScope ChangeRequestBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		;
	/** 用于ChangeRequest的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSecondaryListItemScope() {
		return ChangeRequestBaseSecondaryListItemScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		;
	/** 用于ChangeRequestType的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeSecondaryListItemScope() {
		return ChangeRequestTypeBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserDomainBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSecondaryListItemScope() {
		return UserDomainBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSecondaryListItemScope() {
		return UserWhiteListBaseSecondaryListItemScope;
	}

	protected static SerializeScope SecUserBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于SecUser的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserSecondaryListItemScope() {
		return SecUserBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserAppBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppSecondaryListItemScope() {
		return UserAppBaseSecondaryListItemScope;
	}

	protected static SerializeScope QuickLinkBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		;
	/** 用于QuickLink的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkSecondaryListItemScope() {
		return QuickLinkBaseSecondaryListItemScope;
	}

	protected static SerializeScope ListAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessSecondaryListItemScope() {
		return ListAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope ObjectAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		;
	/** 用于ObjectAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessSecondaryListItemScope() {
		return ObjectAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySecondaryListItemScope() {
		return LoginHistoryBaseSecondaryListItemScope;
	}

	protected static SerializeScope GenericFormBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		;
	/** 用于GenericForm的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGenericFormSecondaryListItemScope() {
		return GenericFormBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormMessageSecondaryListItemScope() {
		return FormMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormFieldMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageSecondaryListItemScope() {
		return FormFieldMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormFieldBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldSecondaryListItemScope() {
		return FormFieldBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormActionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		;
	/** 用于FormAction的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormActionSecondaryListItemScope() {
		return FormActionBaseSecondaryListItemScope;
	}

	protected static SerializeScope CandidateContainerBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		;
	/** 用于CandidateContainer的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerSecondaryListItemScope() {
		return CandidateContainerBaseSecondaryListItemScope;
	}

	protected static SerializeScope CandidateElementBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		;
	/** 用于CandidateElement的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementSecondaryListItemScope() {
		return CandidateElementBaseSecondaryListItemScope;
	}

	protected static SerializeScope WechatWorkappIdentifyBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentify.ID_PROPERTY)
		.field(WechatWorkappIdentify.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentify.USER_ID_PROPERTY)
		.field(WechatWorkappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentify的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentifySecondaryListItemScope() {
		return WechatWorkappIdentifyBaseSecondaryListItemScope;
	}

	protected static SerializeScope WechatMiniappIdentifyBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentify.ID_PROPERTY)
		.field(WechatMiniappIdentify.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentify.APP_ID_PROPERTY)
		.field(WechatMiniappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentify的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentifySecondaryListItemScope() {
		return WechatMiniappIdentifyBaseSecondaryListItemScope;
	}

	protected static SerializeScope PlatformBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		.field(Platform.PROVINCE_LIST, getProvinceSecondaryListItemScope())
		.field(Platform.CITY_LIST, getCitySecondaryListItemScope())
		.field(Platform.DISTRICT_LIST, getDistrictSecondaryListItemScope())
		.field(Platform.SCHOOL_CLASS_LIST, getSchoolClassSecondaryListItemScope())
		.field(Platform.TEACHER_LIST, getTeacherSecondaryListItemScope())
		.field(Platform.GUARDIAN_LIST, getGuardianSecondaryListItemScope())
		.field(Platform.QUESTION_LIST, getQuestionSecondaryListItemScope())
		.field(Platform.QUESTION_TYPE_LIST, getQuestionTypeSecondaryListItemScope())
		.field(Platform.QUESTION_SOURCE_LIST, getQuestionSourceSecondaryListItemScope())
		.field(Platform.SURVEY_STATUS_LIST, getSurveyStatusSecondaryListItemScope())
		.field(Platform.WECHAT_USER_LIST, getWechatUserSecondaryListItemScope())
		.field(Platform.USER_TYPE_LIST, getUserTypeSecondaryListItemScope())
		.field(Platform.CHANGE_REQUEST_LIST, getChangeRequestSecondaryListItemScope())
		.field(Platform.CHANGE_REQUEST_TYPE_LIST, getChangeRequestTypeSecondaryListItemScope())
		;
	/** 用于Platform对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformListItemScope() {
		return PlatformBaseListItemScope;
	}

	protected static SerializeScope ProvinceBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Province.ID_PROPERTY)
		.field(Province.NAME_PROPERTY)
		.field(Province.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Province.CREATE_TIME_PROPERTY)
		.field(Province.CITY_LIST, getCitySecondaryListItemScope())
		.field(Province.LOCATION_LIST, getLocationSecondaryListItemScope())
		;
	/** 用于Province对象的列表时需要序列化的属性列表 */
	public static SerializeScope getProvinceListItemScope() {
		return ProvinceBaseListItemScope;
	}

	protected static SerializeScope CityBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(City.ID_PROPERTY)
		.field(City.NAME_PROPERTY)
		.field(City.PROVINCE_PROPERTY, getProvinceSummaryScope())
		.field(City.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(City.CREATE_TIME_PROPERTY)
		.field(City.DISTRICT_LIST, getDistrictSecondaryListItemScope())
		;
	/** 用于City对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCityListItemScope() {
		return CityBaseListItemScope;
	}

	protected static SerializeScope DistrictBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(District.ID_PROPERTY)
		.field(District.NAME_PROPERTY)
		.field(District.CITY_PROPERTY, getCitySummaryScope())
		.field(District.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(District.CREATE_TIME_PROPERTY)
		.field(District.LOCATION_LIST, getLocationSecondaryListItemScope())
		;
	/** 用于District对象的列表时需要序列化的属性列表 */
	public static SerializeScope getDistrictListItemScope() {
		return DistrictBaseListItemScope;
	}

	protected static SerializeScope LocationBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Location.ID_PROPERTY)
		.field(Location.NAME_PROPERTY)
		.field(Location.ADDRESS_PROPERTY)
		.field(Location.DISTRICT_PROPERTY, getDistrictSummaryScope())
		.field(Location.PROVINCE_PROPERTY, getProvinceSummaryScope())
		.field(Location.LATITUDE_PROPERTY)
		.field(Location.LONGITUDE_PROPERTY)
		.field(Location.GUARDIAN_LIST, getGuardianSecondaryListItemScope())
		.field(Location.WECHAT_USER_LIST, getWechatUserSecondaryListItemScope())
		;
	/** 用于Location对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLocationListItemScope() {
		return LocationBaseListItemScope;
	}

	protected static SerializeScope SchoolClassBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SchoolClass.ID_PROPERTY)
		.field(SchoolClass.NAME_PROPERTY)
		.field(SchoolClass.CLASS_TEACHER_PROPERTY, getTeacherSummaryScope())
		.field(SchoolClass.CREATE_TIME_PROPERTY)
		.field(SchoolClass.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(SchoolClass.SCHOOLE_PROPERTY)
		.field(SchoolClass.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(SchoolClass.CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveySecondaryListItemScope())
		.field(SchoolClass.STUDENT_LIST, getStudentSecondaryListItemScope())
		.field(SchoolClass.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveySecondaryListItemScope())
		;
	/** 用于SchoolClass对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSchoolClassListItemScope() {
		return SchoolClassBaseListItemScope;
	}

	protected static SerializeScope TeacherBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Teacher.ID_PROPERTY)
		.field(Teacher.NAME_PROPERTY)
		.field(Teacher.MOBILE_PROPERTY)
		.field(Teacher.SCHOOLE_PROPERTY)
		.field(Teacher.CREATE_TIME_PROPERTY)
		.field(Teacher.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Teacher.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(Teacher.SCHOOL_CLASS_LIST, getSchoolClassSecondaryListItemScope())
		;
	/** 用于Teacher对象的列表时需要序列化的属性列表 */
	public static SerializeScope getTeacherListItemScope() {
		return TeacherBaseListItemScope;
	}

	protected static SerializeScope GuardianBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Guardian.ID_PROPERTY)
		.field(Guardian.NAME_PROPERTY)
		.field(Guardian.MOBILE_PROPERTY)
		.field(Guardian.ADDRESS_PROPERTY, getLocationSummaryScope())
		.field(Guardian.WECHAT_USER_PROPERTY, getWechatUserSummaryScope())
		.field(Guardian.CREATE_TIME_PROPERTY)
		.field(Guardian.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Guardian.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(Guardian.STUDENT_LIST, getStudentSecondaryListItemScope())
		;
	/** 用于Guardian对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGuardianListItemScope() {
		return GuardianBaseListItemScope;
	}

	protected static SerializeScope QuestionBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Question.ID_PROPERTY)
		.field(Question.TOPIC_PROPERTY)
		.field(Question.QUESTION_TYPE_PROPERTY, getQuestionTypeSummaryScope())
		.field(Question.OPTION_A_PROPERTY)
		.field(Question.OPTION_B_PROPERTY)
		.field(Question.OPTION_C_PROPERTY)
		.field(Question.OPTION_D_PROPERTY)
		.field(Question.PLATFORM_PROPERTY, getPlatformSummaryScope())
		;
	/** 用于Question对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuestionListItemScope() {
		return QuestionBaseListItemScope;
	}

	protected static SerializeScope QuestionTypeBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionType.ID_PROPERTY)
		.field(QuestionType.NAME_PROPERTY)
		.field(QuestionType.CODE_PROPERTY)
		.field(QuestionType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(QuestionType.QUESTION_LIST, getQuestionSecondaryListItemScope())
		.field(QuestionType.CLASS_QUESTION_LIST, getClassQuestionSecondaryListItemScope())
		.field(QuestionType.DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionSecondaryListItemScope())
		;
	/** 用于QuestionType对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuestionTypeListItemScope() {
		return QuestionTypeBaseListItemScope;
	}

	protected static SerializeScope QuestionSourceBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionSource.ID_PROPERTY)
		.field(QuestionSource.NAME_PROPERTY)
		.field(QuestionSource.CODE_PROPERTY)
		.field(QuestionSource.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(QuestionSource.CLASS_QUESTION_LIST, getClassQuestionSecondaryListItemScope())
		;
	/** 用于QuestionSource对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuestionSourceListItemScope() {
		return QuestionSourceBaseListItemScope;
	}

	protected static SerializeScope ClassQuestionBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassQuestion.ID_PROPERTY)
		.field(ClassQuestion.TOPIC_PROPERTY)
		.field(ClassQuestion.QUESTION_TYPE_PROPERTY, getQuestionTypeSummaryScope())
		.field(ClassQuestion.OPTION_A_PROPERTY)
		.field(ClassQuestion.OPTION_B_PROPERTY)
		.field(ClassQuestion.OPTION_C_PROPERTY)
		.field(ClassQuestion.OPTION_D_PROPERTY)
		.field(ClassQuestion.QUESTION_SOURCE_PROPERTY, getQuestionSourceSummaryScope())
		.field(ClassQuestion.CREATOR_PROPERTY, getWechatUserSummaryScope())
		.field(ClassQuestion.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(ClassQuestion.DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionSecondaryListItemScope())
		;
	/** 用于ClassQuestion对象的列表时需要序列化的属性列表 */
	public static SerializeScope getClassQuestionListItemScope() {
		return ClassQuestionBaseListItemScope;
	}

	protected static SerializeScope ClassDailyHealthSurveyBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassDailyHealthSurvey.ID_PROPERTY)
		.field(ClassDailyHealthSurvey.NAME_PROPERTY)
		.field(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, getSchoolClassSummaryScope())
		.field(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY)
		.field(ClassDailyHealthSurvey.CREATOR_PROPERTY, getWechatUserSummaryScope())
		.field(ClassDailyHealthSurvey.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(ClassDailyHealthSurvey.DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionSecondaryListItemScope())
		.field(ClassDailyHealthSurvey.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveySecondaryListItemScope())
		;
	/** 用于ClassDailyHealthSurvey对象的列表时需要序列化的属性列表 */
	public static SerializeScope getClassDailyHealthSurveyListItemScope() {
		return ClassDailyHealthSurveyBaseListItemScope;
	}

	protected static SerializeScope DailySurveyQuestionBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(DailySurveyQuestion.ID_PROPERTY)
		.field(DailySurveyQuestion.TOPIC_PROPERTY)
		.field(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, getQuestionTypeSummaryScope())
		.field(DailySurveyQuestion.OPTION_A_PROPERTY)
		.field(DailySurveyQuestion.OPTION_B_PROPERTY)
		.field(DailySurveyQuestion.OPTION_C_PROPERTY)
		.field(DailySurveyQuestion.OPTION_D_PROPERTY)
		.field(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, getClassDailyHealthSurveySummaryScope())
		.field(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, getClassQuestionSummaryScope())
		.field(DailySurveyQuestion.STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerSecondaryListItemScope())
		;
	/** 用于DailySurveyQuestion对象的列表时需要序列化的属性列表 */
	public static SerializeScope getDailySurveyQuestionListItemScope() {
		return DailySurveyQuestionBaseListItemScope;
	}

	protected static SerializeScope StudentBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Student.ID_PROPERTY)
		.field(Student.NAME_PROPERTY)
		.field(Student.GENDER_PROPERTY)
		.field(Student.GUARDIAN_PROPERTY, getGuardianSummaryScope())
		.field(Student.SCHOOL_CLASS_PROPERTY, getSchoolClassSummaryScope())
		.field(Student.STUDENT_ID_PROPERTY)
		.field(Student.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(Student.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveySecondaryListItemScope())
		;
	/** 用于Student对象的列表时需要序列化的属性列表 */
	public static SerializeScope getStudentListItemScope() {
		return StudentBaseListItemScope;
	}

	protected static SerializeScope StudentHealthSurveyBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentHealthSurvey.ID_PROPERTY)
		.field(StudentHealthSurvey.STUDENT_PROPERTY, getStudentSummaryScope())
		.field(StudentHealthSurvey.ANSWER_TIME_PROPERTY)
		.field(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, getSurveyStatusSummaryScope())
		.field(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, getSchoolClassSummaryScope())
		.field(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, getClassDailyHealthSurveySummaryScope())
		.field(StudentHealthSurvey.CREATE_TIME_PROPERTY)
		.field(StudentHealthSurvey.LAST_UPDATE_TIME_PROPERTY)
		.field(StudentHealthSurvey.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(StudentHealthSurvey.STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerSecondaryListItemScope())
		;
	/** 用于StudentHealthSurvey对象的列表时需要序列化的属性列表 */
	public static SerializeScope getStudentHealthSurveyListItemScope() {
		return StudentHealthSurveyBaseListItemScope;
	}

	protected static SerializeScope StudentDailyAnswerBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentDailyAnswer.ID_PROPERTY)
		.field(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, getStudentHealthSurveySummaryScope())
		.field(StudentDailyAnswer.QUESTION_PROPERTY, getDailySurveyQuestionSummaryScope())
		.field(StudentDailyAnswer.ANSWER_PROPERTY)
		.field(StudentDailyAnswer.CREATE_TIME_PROPERTY)
		.field(StudentDailyAnswer.LAST_UPDATE_TIME_PROPERTY)
		.field(StudentDailyAnswer.CQ_PROPERTY, getChangeRequestSummaryScope())
		;
	/** 用于StudentDailyAnswer对象的列表时需要序列化的属性列表 */
	public static SerializeScope getStudentDailyAnswerListItemScope() {
		return StudentDailyAnswerBaseListItemScope;
	}

	protected static SerializeScope SurveyStatusBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SurveyStatus.ID_PROPERTY)
		.field(SurveyStatus.NAME_PROPERTY)
		.field(SurveyStatus.CODE_PROPERTY)
		.field(SurveyStatus.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(SurveyStatus.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveySecondaryListItemScope())
		;
	/** 用于SurveyStatus对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSurveyStatusListItemScope() {
		return SurveyStatusBaseListItemScope;
	}

	protected static SerializeScope WechatUserBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatUser.ID_PROPERTY)
		.field(WechatUser.NAME_PROPERTY)
		.field(WechatUser.AVATAR_PROPERTY)
		.field(WechatUser.ADDRESS_PROPERTY, getLocationSummaryScope())
		.field(WechatUser.USER_TYPE_PROPERTY, getUserTypeSummaryScope())
		.field(WechatUser.CREATE_TIME_PROPERTY)
		.field(WechatUser.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(WechatUser.GUARDIAN_LIST, getGuardianSecondaryListItemScope())
		.field(WechatUser.CLASS_QUESTION_LIST, getClassQuestionSecondaryListItemScope())
		.field(WechatUser.CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveySecondaryListItemScope())
		.field(WechatUser.WECHAT_LOGIN_INFO_LIST, getWechatLoginInfoSecondaryListItemScope())
		;
	/** 用于WechatUser对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatUserListItemScope() {
		return WechatUserBaseListItemScope;
	}

	protected static SerializeScope UserTypeBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserType.ID_PROPERTY)
		.field(UserType.NAME_PROPERTY)
		.field(UserType.CODE_PROPERTY)
		.field(UserType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(UserType.WECHAT_USER_LIST, getWechatUserSecondaryListItemScope())
		;
	/** 用于UserType对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserTypeListItemScope() {
		return UserTypeBaseListItemScope;
	}

	protected static SerializeScope WechatLoginInfoBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatLoginInfo.ID_PROPERTY)
		.field(WechatLoginInfo.WECHAT_USER_PROPERTY, getWechatUserSummaryScope())
		.field(WechatLoginInfo.APP_ID_PROPERTY)
		.field(WechatLoginInfo.OPEN_ID_PROPERTY)
		.field(WechatLoginInfo.SESSION_KEY_PROPERTY)
		.field(WechatLoginInfo.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于WechatLoginInfo对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatLoginInfoListItemScope() {
		return WechatLoginInfoBaseListItemScope;
	}

	protected static SerializeScope ChangeRequestBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		.field(ChangeRequest.REQUEST_TYPE_PROPERTY, getChangeRequestTypeSummaryScope())
		.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequest.SCHOOL_CLASS_LIST, getSchoolClassSecondaryListItemScope())
		.field(ChangeRequest.TEACHER_LIST, getTeacherSecondaryListItemScope())
		.field(ChangeRequest.GUARDIAN_LIST, getGuardianSecondaryListItemScope())
		.field(ChangeRequest.CLASS_QUESTION_LIST, getClassQuestionSecondaryListItemScope())
		.field(ChangeRequest.CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveySecondaryListItemScope())
		.field(ChangeRequest.STUDENT_LIST, getStudentSecondaryListItemScope())
		.field(ChangeRequest.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveySecondaryListItemScope())
		.field(ChangeRequest.STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerSecondaryListItemScope())
		;
	/** 用于ChangeRequest对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestListItemScope() {
		return ChangeRequestBaseListItemScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		.field(ChangeRequestType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequestType.CHANGE_REQUEST_LIST, getChangeRequestSecondaryListItemScope())
		;
	/** 用于ChangeRequestType对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeListItemScope() {
		return ChangeRequestTypeBaseListItemScope;
	}

	protected static SerializeScope UserDomainBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListSecondaryListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserSecondaryListItemScope())
		;
	/** 用于UserDomain对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainListItemScope() {
		return UserDomainBaseListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListListItemScope() {
		return UserWhiteListBaseListItemScope;
	}

	protected static SerializeScope SecUserBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.USER_APP_LIST, getUserAppSecondaryListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistorySecondaryListItemScope())
		.field(SecUser.WECHAT_WORKAPP_IDENTIFY_LIST, getWechatWorkappIdentifySecondaryListItemScope())
		.field(SecUser.WECHAT_MINIAPP_IDENTIFY_LIST, getWechatMiniappIdentifySecondaryListItemScope())
		;
	/** 用于SecUser对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserListItemScope() {
		return SecUserBaseListItemScope;
	}

	protected static SerializeScope UserAppBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.QUICK_LINK_LIST, getQuickLinkSecondaryListItemScope())
		.field(UserApp.LIST_ACCESS_LIST, getListAccessSecondaryListItemScope())
		.field(UserApp.OBJECT_ACCESS_LIST, getObjectAccessSecondaryListItemScope())
		;
	/** 用于UserApp对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppListItemScope() {
		return UserAppBaseListItemScope;
	}

	protected static SerializeScope QuickLinkBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		.field(QuickLink.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于QuickLink对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkListItemScope() {
		return QuickLinkBaseListItemScope;
	}

	protected static SerializeScope ListAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessListItemScope() {
		return ListAccessBaseListItemScope;
	}

	protected static SerializeScope ObjectAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		.field(ObjectAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ObjectAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessListItemScope() {
		return ObjectAccessBaseListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryListItemScope() {
		return LoginHistoryBaseListItemScope;
	}

	protected static SerializeScope GenericFormBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		.field(GenericForm.FORM_MESSAGE_LIST, getFormMessageSecondaryListItemScope())
		.field(GenericForm.FORM_FIELD_MESSAGE_LIST, getFormFieldMessageSecondaryListItemScope())
		.field(GenericForm.FORM_FIELD_LIST, getFormFieldSecondaryListItemScope())
		.field(GenericForm.FORM_ACTION_LIST, getFormActionSecondaryListItemScope())
		;
	/** 用于GenericForm对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGenericFormListItemScope() {
		return GenericFormBaseListItemScope;
	}

	protected static SerializeScope FormMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormMessageListItemScope() {
		return FormMessageBaseListItemScope;
	}

	protected static SerializeScope FormFieldMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageListItemScope() {
		return FormFieldMessageBaseListItemScope;
	}

	protected static SerializeScope FormFieldBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldListItemScope() {
		return FormFieldBaseListItemScope;
	}

	protected static SerializeScope FormActionBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		.field(FormAction.FORM_PROPERTY, getGenericFormSummaryScope())
		;
	/** 用于FormAction对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormActionListItemScope() {
		return FormActionBaseListItemScope;
	}

	protected static SerializeScope CandidateContainerBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		.field(CandidateContainer.CANDIDATE_ELEMENT_LIST, getCandidateElementSecondaryListItemScope())
		;
	/** 用于CandidateContainer对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerListItemScope() {
		return CandidateContainerBaseListItemScope;
	}

	protected static SerializeScope CandidateElementBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		.field(CandidateElement.CONTAINER_PROPERTY, getCandidateContainerSummaryScope())
		;
	/** 用于CandidateElement对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementListItemScope() {
		return CandidateElementBaseListItemScope;
	}

	protected static SerializeScope WechatWorkappIdentifyBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentify.ID_PROPERTY)
		.field(WechatWorkappIdentify.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentify.USER_ID_PROPERTY)
		.field(WechatWorkappIdentify.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatWorkappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentify对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentifyListItemScope() {
		return WechatWorkappIdentifyBaseListItemScope;
	}

	protected static SerializeScope WechatMiniappIdentifyBaseListItemScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentify.ID_PROPERTY)
		.field(WechatMiniappIdentify.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentify.APP_ID_PROPERTY)
		.field(WechatMiniappIdentify.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatMiniappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentify对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentifyListItemScope() {
		return WechatMiniappIdentifyBaseListItemScope;
	}

	protected static SerializeScope PlatformBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.DESCRIPTION_PROPERTY)
		.field(Platform.PROVINCE_LIST, getProvinceListItemScope())
		.field(Platform.CITY_LIST, getCityListItemScope())
		.field(Platform.DISTRICT_LIST, getDistrictListItemScope())
		.field(Platform.SCHOOL_CLASS_LIST, getSchoolClassListItemScope())
		.field(Platform.TEACHER_LIST, getTeacherListItemScope())
		.field(Platform.GUARDIAN_LIST, getGuardianListItemScope())
		.field(Platform.QUESTION_LIST, getQuestionListItemScope())
		.field(Platform.QUESTION_TYPE_LIST, getQuestionTypeListItemScope())
		.field(Platform.QUESTION_SOURCE_LIST, getQuestionSourceListItemScope())
		.field(Platform.SURVEY_STATUS_LIST, getSurveyStatusListItemScope())
		.field(Platform.WECHAT_USER_LIST, getWechatUserListItemScope())
		.field(Platform.USER_TYPE_LIST, getUserTypeListItemScope())
		.field(Platform.CHANGE_REQUEST_LIST, getChangeRequestListItemScope())
		.field(Platform.CHANGE_REQUEST_TYPE_LIST, getChangeRequestTypeListItemScope())
		;
	/** 用于Platform对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformDetailScope() {
		return PlatformBaseDetailScope;
	}

	protected static SerializeScope ProvinceBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Province.ID_PROPERTY)
		.field(Province.NAME_PROPERTY)
		.field(Province.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Province.CREATE_TIME_PROPERTY)
		.field(Province.CITY_LIST, getCityListItemScope())
		.field(Province.LOCATION_LIST, getLocationListItemScope())
		;
	/** 用于Province对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getProvinceDetailScope() {
		return ProvinceBaseDetailScope;
	}

	protected static SerializeScope CityBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(City.ID_PROPERTY)
		.field(City.NAME_PROPERTY)
		.field(City.PROVINCE_PROPERTY, getProvinceSummaryScope())
		.field(City.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(City.CREATE_TIME_PROPERTY)
		.field(City.DISTRICT_LIST, getDistrictListItemScope())
		;
	/** 用于City对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCityDetailScope() {
		return CityBaseDetailScope;
	}

	protected static SerializeScope DistrictBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(District.ID_PROPERTY)
		.field(District.NAME_PROPERTY)
		.field(District.CITY_PROPERTY, getCitySummaryScope())
		.field(District.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(District.CREATE_TIME_PROPERTY)
		.field(District.LOCATION_LIST, getLocationListItemScope())
		;
	/** 用于District对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getDistrictDetailScope() {
		return DistrictBaseDetailScope;
	}

	protected static SerializeScope LocationBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Location.ID_PROPERTY)
		.field(Location.NAME_PROPERTY)
		.field(Location.ADDRESS_PROPERTY)
		.field(Location.DISTRICT_PROPERTY, getDistrictSummaryScope())
		.field(Location.PROVINCE_PROPERTY, getProvinceSummaryScope())
		.field(Location.LATITUDE_PROPERTY)
		.field(Location.LONGITUDE_PROPERTY)
		.field(Location.GUARDIAN_LIST, getGuardianListItemScope())
		.field(Location.WECHAT_USER_LIST, getWechatUserListItemScope())
		;
	/** 用于Location对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLocationDetailScope() {
		return LocationBaseDetailScope;
	}

	protected static SerializeScope SchoolClassBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SchoolClass.ID_PROPERTY)
		.field(SchoolClass.NAME_PROPERTY)
		.field(SchoolClass.CLASS_TEACHER_PROPERTY, getTeacherSummaryScope())
		.field(SchoolClass.CREATE_TIME_PROPERTY)
		.field(SchoolClass.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(SchoolClass.SCHOOLE_PROPERTY)
		.field(SchoolClass.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(SchoolClass.CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveyListItemScope())
		.field(SchoolClass.STUDENT_LIST, getStudentListItemScope())
		.field(SchoolClass.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyListItemScope())
		;
	/** 用于SchoolClass对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSchoolClassDetailScope() {
		return SchoolClassBaseDetailScope;
	}

	protected static SerializeScope TeacherBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Teacher.ID_PROPERTY)
		.field(Teacher.NAME_PROPERTY)
		.field(Teacher.MOBILE_PROPERTY)
		.field(Teacher.SCHOOLE_PROPERTY)
		.field(Teacher.CREATE_TIME_PROPERTY)
		.field(Teacher.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Teacher.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(Teacher.SCHOOL_CLASS_LIST, getSchoolClassListItemScope())
		;
	/** 用于Teacher对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getTeacherDetailScope() {
		return TeacherBaseDetailScope;
	}

	protected static SerializeScope GuardianBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Guardian.ID_PROPERTY)
		.field(Guardian.NAME_PROPERTY)
		.field(Guardian.MOBILE_PROPERTY)
		.field(Guardian.ADDRESS_PROPERTY, getLocationSummaryScope())
		.field(Guardian.WECHAT_USER_PROPERTY, getWechatUserSummaryScope())
		.field(Guardian.CREATE_TIME_PROPERTY)
		.field(Guardian.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Guardian.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(Guardian.STUDENT_LIST, getStudentListItemScope())
		;
	/** 用于Guardian对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGuardianDetailScope() {
		return GuardianBaseDetailScope;
	}

	protected static SerializeScope QuestionBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Question.ID_PROPERTY)
		.field(Question.TOPIC_PROPERTY)
		.field(Question.QUESTION_TYPE_PROPERTY, getQuestionTypeSummaryScope())
		.field(Question.OPTION_A_PROPERTY)
		.field(Question.OPTION_B_PROPERTY)
		.field(Question.OPTION_C_PROPERTY)
		.field(Question.OPTION_D_PROPERTY)
		.field(Question.PLATFORM_PROPERTY, getPlatformSummaryScope())
		;
	/** 用于Question对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuestionDetailScope() {
		return QuestionBaseDetailScope;
	}

	protected static SerializeScope QuestionTypeBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionType.ID_PROPERTY)
		.field(QuestionType.NAME_PROPERTY)
		.field(QuestionType.CODE_PROPERTY)
		.field(QuestionType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(QuestionType.QUESTION_LIST, getQuestionListItemScope())
		.field(QuestionType.CLASS_QUESTION_LIST, getClassQuestionListItemScope())
		.field(QuestionType.DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionListItemScope())
		;
	/** 用于QuestionType对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuestionTypeDetailScope() {
		return QuestionTypeBaseDetailScope;
	}

	protected static SerializeScope QuestionSourceBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuestionSource.ID_PROPERTY)
		.field(QuestionSource.NAME_PROPERTY)
		.field(QuestionSource.CODE_PROPERTY)
		.field(QuestionSource.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(QuestionSource.CLASS_QUESTION_LIST, getClassQuestionListItemScope())
		;
	/** 用于QuestionSource对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuestionSourceDetailScope() {
		return QuestionSourceBaseDetailScope;
	}

	protected static SerializeScope ClassQuestionBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassQuestion.ID_PROPERTY)
		.field(ClassQuestion.TOPIC_PROPERTY)
		.field(ClassQuestion.QUESTION_TYPE_PROPERTY, getQuestionTypeSummaryScope())
		.field(ClassQuestion.OPTION_A_PROPERTY)
		.field(ClassQuestion.OPTION_B_PROPERTY)
		.field(ClassQuestion.OPTION_C_PROPERTY)
		.field(ClassQuestion.OPTION_D_PROPERTY)
		.field(ClassQuestion.QUESTION_SOURCE_PROPERTY, getQuestionSourceSummaryScope())
		.field(ClassQuestion.CREATOR_PROPERTY, getWechatUserSummaryScope())
		.field(ClassQuestion.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(ClassQuestion.DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionListItemScope())
		;
	/** 用于ClassQuestion对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getClassQuestionDetailScope() {
		return ClassQuestionBaseDetailScope;
	}

	protected static SerializeScope ClassDailyHealthSurveyBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ClassDailyHealthSurvey.ID_PROPERTY)
		.field(ClassDailyHealthSurvey.NAME_PROPERTY)
		.field(ClassDailyHealthSurvey.SCHOOL_CLASS_PROPERTY, getSchoolClassSummaryScope())
		.field(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY)
		.field(ClassDailyHealthSurvey.CREATOR_PROPERTY, getWechatUserSummaryScope())
		.field(ClassDailyHealthSurvey.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(ClassDailyHealthSurvey.DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionListItemScope())
		.field(ClassDailyHealthSurvey.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyListItemScope())
		;
	/** 用于ClassDailyHealthSurvey对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getClassDailyHealthSurveyDetailScope() {
		return ClassDailyHealthSurveyBaseDetailScope;
	}

	protected static SerializeScope DailySurveyQuestionBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(DailySurveyQuestion.ID_PROPERTY)
		.field(DailySurveyQuestion.TOPIC_PROPERTY)
		.field(DailySurveyQuestion.QUESTION_TYPE_PROPERTY, getQuestionTypeSummaryScope())
		.field(DailySurveyQuestion.OPTION_A_PROPERTY)
		.field(DailySurveyQuestion.OPTION_B_PROPERTY)
		.field(DailySurveyQuestion.OPTION_C_PROPERTY)
		.field(DailySurveyQuestion.OPTION_D_PROPERTY)
		.field(DailySurveyQuestion.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, getClassDailyHealthSurveySummaryScope())
		.field(DailySurveyQuestion.CLASS_QUESTION_PROPERTY, getClassQuestionSummaryScope())
		.field(DailySurveyQuestion.STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerListItemScope())
		;
	/** 用于DailySurveyQuestion对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getDailySurveyQuestionDetailScope() {
		return DailySurveyQuestionBaseDetailScope;
	}

	protected static SerializeScope StudentBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(Student.ID_PROPERTY)
		.field(Student.NAME_PROPERTY)
		.field(Student.GENDER_PROPERTY)
		.field(Student.GUARDIAN_PROPERTY, getGuardianSummaryScope())
		.field(Student.SCHOOL_CLASS_PROPERTY, getSchoolClassSummaryScope())
		.field(Student.STUDENT_ID_PROPERTY)
		.field(Student.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(Student.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyListItemScope())
		;
	/** 用于Student对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getStudentDetailScope() {
		return StudentBaseDetailScope;
	}

	protected static SerializeScope StudentHealthSurveyBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentHealthSurvey.ID_PROPERTY)
		.field(StudentHealthSurvey.STUDENT_PROPERTY, getStudentSummaryScope())
		.field(StudentHealthSurvey.ANSWER_TIME_PROPERTY)
		.field(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, getSurveyStatusSummaryScope())
		.field(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, getSchoolClassSummaryScope())
		.field(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, getClassDailyHealthSurveySummaryScope())
		.field(StudentHealthSurvey.CREATE_TIME_PROPERTY)
		.field(StudentHealthSurvey.LAST_UPDATE_TIME_PROPERTY)
		.field(StudentHealthSurvey.CQ_PROPERTY, getChangeRequestSummaryScope())
		.field(StudentHealthSurvey.STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerListItemScope())
		;
	/** 用于StudentHealthSurvey对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getStudentHealthSurveyDetailScope() {
		return StudentHealthSurveyBaseDetailScope;
	}

	protected static SerializeScope StudentDailyAnswerBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(StudentDailyAnswer.ID_PROPERTY)
		.field(StudentDailyAnswer.STUDENT_HEALTH_SURVEY_PROPERTY, getStudentHealthSurveySummaryScope())
		.field(StudentDailyAnswer.QUESTION_PROPERTY, getDailySurveyQuestionSummaryScope())
		.field(StudentDailyAnswer.ANSWER_PROPERTY)
		.field(StudentDailyAnswer.CREATE_TIME_PROPERTY)
		.field(StudentDailyAnswer.LAST_UPDATE_TIME_PROPERTY)
		.field(StudentDailyAnswer.CQ_PROPERTY, getChangeRequestSummaryScope())
		;
	/** 用于StudentDailyAnswer对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getStudentDailyAnswerDetailScope() {
		return StudentDailyAnswerBaseDetailScope;
	}

	protected static SerializeScope SurveyStatusBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SurveyStatus.ID_PROPERTY)
		.field(SurveyStatus.NAME_PROPERTY)
		.field(SurveyStatus.CODE_PROPERTY)
		.field(SurveyStatus.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(SurveyStatus.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyListItemScope())
		;
	/** 用于SurveyStatus对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSurveyStatusDetailScope() {
		return SurveyStatusBaseDetailScope;
	}

	protected static SerializeScope WechatUserBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatUser.ID_PROPERTY)
		.field(WechatUser.NAME_PROPERTY)
		.field(WechatUser.AVATAR_PROPERTY)
		.field(WechatUser.ADDRESS_PROPERTY, getLocationSummaryScope())
		.field(WechatUser.USER_TYPE_PROPERTY, getUserTypeSummaryScope())
		.field(WechatUser.CREATE_TIME_PROPERTY)
		.field(WechatUser.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(WechatUser.GUARDIAN_LIST, getGuardianListItemScope())
		.field(WechatUser.CLASS_QUESTION_LIST, getClassQuestionListItemScope())
		.field(WechatUser.CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveyListItemScope())
		.field(WechatUser.WECHAT_LOGIN_INFO_LIST, getWechatLoginInfoListItemScope())
		;
	/** 用于WechatUser对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatUserDetailScope() {
		return WechatUserBaseDetailScope;
	}

	protected static SerializeScope UserTypeBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserType.ID_PROPERTY)
		.field(UserType.NAME_PROPERTY)
		.field(UserType.CODE_PROPERTY)
		.field(UserType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(UserType.WECHAT_USER_LIST, getWechatUserListItemScope())
		;
	/** 用于UserType对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserTypeDetailScope() {
		return UserTypeBaseDetailScope;
	}

	protected static SerializeScope WechatLoginInfoBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatLoginInfo.ID_PROPERTY)
		.field(WechatLoginInfo.WECHAT_USER_PROPERTY, getWechatUserSummaryScope())
		.field(WechatLoginInfo.APP_ID_PROPERTY)
		.field(WechatLoginInfo.OPEN_ID_PROPERTY)
		.field(WechatLoginInfo.SESSION_KEY_PROPERTY)
		.field(WechatLoginInfo.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于WechatLoginInfo对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatLoginInfoDetailScope() {
		return WechatLoginInfoBaseDetailScope;
	}

	protected static SerializeScope ChangeRequestBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		.field(ChangeRequest.REQUEST_TYPE_PROPERTY, getChangeRequestTypeSummaryScope())
		.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequest.SCHOOL_CLASS_LIST, getSchoolClassListItemScope())
		.field(ChangeRequest.TEACHER_LIST, getTeacherListItemScope())
		.field(ChangeRequest.GUARDIAN_LIST, getGuardianListItemScope())
		.field(ChangeRequest.CLASS_QUESTION_LIST, getClassQuestionListItemScope())
		.field(ChangeRequest.CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveyListItemScope())
		.field(ChangeRequest.STUDENT_LIST, getStudentListItemScope())
		.field(ChangeRequest.STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyListItemScope())
		.field(ChangeRequest.STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerListItemScope())
		;
	/** 用于ChangeRequest对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestDetailScope() {
		return ChangeRequestBaseDetailScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		.field(ChangeRequestType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequestType.CHANGE_REQUEST_LIST, getChangeRequestListItemScope())
		;
	/** 用于ChangeRequestType对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeDetailScope() {
		return ChangeRequestTypeBaseDetailScope;
	}

	protected static SerializeScope UserDomainBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserListItemScope())
		;
	/** 用于UserDomain对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainDetailScope() {
		return UserDomainBaseDetailScope;
	}

	protected static SerializeScope UserWhiteListBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListDetailScope() {
		return UserWhiteListBaseDetailScope;
	}

	protected static SerializeScope SecUserBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.USER_APP_LIST, getUserAppListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistoryListItemScope())
		.field(SecUser.WECHAT_WORKAPP_IDENTIFY_LIST, getWechatWorkappIdentifyListItemScope())
		.field(SecUser.WECHAT_MINIAPP_IDENTIFY_LIST, getWechatMiniappIdentifyListItemScope())
		;
	/** 用于SecUser对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserDetailScope() {
		return SecUserBaseDetailScope;
	}

	protected static SerializeScope UserAppBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.QUICK_LINK_LIST, getQuickLinkListItemScope())
		.field(UserApp.LIST_ACCESS_LIST, getListAccessListItemScope())
		.field(UserApp.OBJECT_ACCESS_LIST, getObjectAccessListItemScope())
		;
	/** 用于UserApp对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppDetailScope() {
		return UserAppBaseDetailScope;
	}

	protected static SerializeScope QuickLinkBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		.field(QuickLink.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于QuickLink对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkDetailScope() {
		return QuickLinkBaseDetailScope;
	}

	protected static SerializeScope ListAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessDetailScope() {
		return ListAccessBaseDetailScope;
	}

	protected static SerializeScope ObjectAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		.field(ObjectAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ObjectAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessDetailScope() {
		return ObjectAccessBaseDetailScope;
	}

	protected static SerializeScope LoginHistoryBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryDetailScope() {
		return LoginHistoryBaseDetailScope;
	}

	protected static SerializeScope GenericFormBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		.field(GenericForm.FORM_MESSAGE_LIST, getFormMessageListItemScope())
		.field(GenericForm.FORM_FIELD_MESSAGE_LIST, getFormFieldMessageListItemScope())
		.field(GenericForm.FORM_FIELD_LIST, getFormFieldListItemScope())
		.field(GenericForm.FORM_ACTION_LIST, getFormActionListItemScope())
		;
	/** 用于GenericForm对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGenericFormDetailScope() {
		return GenericFormBaseDetailScope;
	}

	protected static SerializeScope FormMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormMessageDetailScope() {
		return FormMessageBaseDetailScope;
	}

	protected static SerializeScope FormFieldMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageDetailScope() {
		return FormFieldMessageBaseDetailScope;
	}

	protected static SerializeScope FormFieldBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldDetailScope() {
		return FormFieldBaseDetailScope;
	}

	protected static SerializeScope FormActionBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		.field(FormAction.FORM_PROPERTY, getGenericFormSummaryScope())
		;
	/** 用于FormAction对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormActionDetailScope() {
		return FormActionBaseDetailScope;
	}

	protected static SerializeScope CandidateContainerBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		.field(CandidateContainer.CANDIDATE_ELEMENT_LIST, getCandidateElementListItemScope())
		;
	/** 用于CandidateContainer对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerDetailScope() {
		return CandidateContainerBaseDetailScope;
	}

	protected static SerializeScope CandidateElementBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		.field(CandidateElement.CONTAINER_PROPERTY, getCandidateContainerSummaryScope())
		;
	/** 用于CandidateElement对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementDetailScope() {
		return CandidateElementBaseDetailScope;
	}

	protected static SerializeScope WechatWorkappIdentifyBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentify.ID_PROPERTY)
		.field(WechatWorkappIdentify.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentify.USER_ID_PROPERTY)
		.field(WechatWorkappIdentify.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatWorkappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentify对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentifyDetailScope() {
		return WechatWorkappIdentifyBaseDetailScope;
	}

	protected static SerializeScope WechatMiniappIdentifyBaseDetailScope = SerializeScope.INCLUDE()
		.field(HealthBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentify.ID_PROPERTY)
		.field(WechatMiniappIdentify.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentify.APP_ID_PROPERTY)
		.field(WechatMiniappIdentify.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatMiniappIdentify.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentify.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentify对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentifyDetailScope() {
		return WechatMiniappIdentifyBaseDetailScope;
	}

	

}






