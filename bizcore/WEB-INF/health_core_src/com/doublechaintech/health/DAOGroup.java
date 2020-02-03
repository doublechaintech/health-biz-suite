package com.doublechaintech.health;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.platform.PlatformTokens;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.province.ProvinceTokens;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.city.CityTokens;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.district.DistrictDAO;
import com.doublechaintech.health.district.DistrictTokens;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.location.LocationTokens;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.teacher.TeacherTokens;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.student.StudentTokens;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.question.QuestionDAO;
import com.doublechaintech.health.question.QuestionTokens;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.questiontype.QuestionTypeTokens;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyDAO;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurveyTokens;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionDAO;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestionTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerDAO;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerTokens;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.surveystatus.SurveyStatusDAO;
import com.doublechaintech.health.surveystatus.SurveyStatusTokens;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportDAO;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReportTokens;
import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.studentanswer.StudentAnswerDAO;
import com.doublechaintech.health.studentanswer.StudentAnswerTokens;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.user.UserDAO;
import com.doublechaintech.health.user.UserTokens;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoDAO;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfoTokens;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.changerequest.ChangeRequestTokens;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.changerequesttype.ChangeRequestTypeDAO;
import com.doublechaintech.health.changerequesttype.ChangeRequestTypeTokens;
import com.doublechaintech.health.userdomain.UserDomain;
import com.doublechaintech.health.userdomain.UserDomainDAO;
import com.doublechaintech.health.userdomain.UserDomainTokens;
import com.doublechaintech.health.userwhitelist.UserWhiteList;
import com.doublechaintech.health.userwhitelist.UserWhiteListDAO;
import com.doublechaintech.health.userwhitelist.UserWhiteListTokens;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.secuser.SecUserDAO;
import com.doublechaintech.health.secuser.SecUserTokens;
import com.doublechaintech.health.userapp.UserApp;
import com.doublechaintech.health.userapp.UserAppDAO;
import com.doublechaintech.health.userapp.UserAppTokens;
import com.doublechaintech.health.quicklink.QuickLink;
import com.doublechaintech.health.quicklink.QuickLinkDAO;
import com.doublechaintech.health.quicklink.QuickLinkTokens;
import com.doublechaintech.health.listaccess.ListAccess;
import com.doublechaintech.health.listaccess.ListAccessDAO;
import com.doublechaintech.health.listaccess.ListAccessTokens;
import com.doublechaintech.health.objectaccess.ObjectAccess;
import com.doublechaintech.health.objectaccess.ObjectAccessDAO;
import com.doublechaintech.health.objectaccess.ObjectAccessTokens;
import com.doublechaintech.health.loginhistory.LoginHistory;
import com.doublechaintech.health.loginhistory.LoginHistoryDAO;
import com.doublechaintech.health.loginhistory.LoginHistoryTokens;
import com.doublechaintech.health.genericform.GenericForm;
import com.doublechaintech.health.genericform.GenericFormDAO;
import com.doublechaintech.health.genericform.GenericFormTokens;
import com.doublechaintech.health.formmessage.FormMessage;
import com.doublechaintech.health.formmessage.FormMessageDAO;
import com.doublechaintech.health.formmessage.FormMessageTokens;
import com.doublechaintech.health.formfieldmessage.FormFieldMessage;
import com.doublechaintech.health.formfieldmessage.FormFieldMessageDAO;
import com.doublechaintech.health.formfieldmessage.FormFieldMessageTokens;
import com.doublechaintech.health.formfield.FormField;
import com.doublechaintech.health.formfield.FormFieldDAO;
import com.doublechaintech.health.formfield.FormFieldTokens;
import com.doublechaintech.health.formaction.FormAction;
import com.doublechaintech.health.formaction.FormActionDAO;
import com.doublechaintech.health.formaction.FormActionTokens;
import com.doublechaintech.health.candidatecontainer.CandidateContainer;
import com.doublechaintech.health.candidatecontainer.CandidateContainerDAO;
import com.doublechaintech.health.candidatecontainer.CandidateContainerTokens;
import com.doublechaintech.health.candidateelement.CandidateElement;
import com.doublechaintech.health.candidateelement.CandidateElementDAO;
import com.doublechaintech.health.candidateelement.CandidateElementTokens;
import com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentify;
import com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentifyDAO;
import com.doublechaintech.health.wechatworkappidentify.WechatWorkappIdentifyTokens;
import com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentify;
import com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentifyDAO;
import com.doublechaintech.health.wechatminiappidentify.WechatMiniappIdentifyTokens;

public class DAOGroup {

	protected PlatformDAO platformDAO;

	protected ProvinceDAO provinceDAO;

	protected CityDAO cityDAO;

	protected DistrictDAO districtDAO;

	protected LocationDAO locationDAO;

	protected TeacherDAO teacherDAO;

	protected StudentDAO studentDAO;

	protected QuestionDAO questionDAO;

	protected QuestionTypeDAO questionTypeDAO;

	protected ClassDailyHealthSurveyDAO classDailyHealthSurveyDAO;

	protected DailySurveyQuestionDAO dailySurveyQuestionDAO;

	protected StudentHealthSurveyDAO studentHealthSurveyDAO;

	protected StudentDailyAnswerDAO studentDailyAnswerDAO;

	protected SurveyStatusDAO surveyStatusDAO;

	protected HealthSurveyReportDAO healthSurveyReportDAO;

	protected StudentAnswerDAO studentAnswerDAO;

	protected UserDAO userDAO;

	protected WechatLoginInfoDAO wechatLoginInfoDAO;

	protected ChangeRequestDAO changeRequestDAO;

	protected ChangeRequestTypeDAO changeRequestTypeDAO;

	protected UserDomainDAO userDomainDAO;

	protected UserWhiteListDAO userWhiteListDAO;

	protected SecUserDAO secUserDAO;

	protected UserAppDAO userAppDAO;

	protected QuickLinkDAO quickLinkDAO;

	protected ListAccessDAO listAccessDAO;

	protected ObjectAccessDAO objectAccessDAO;

	protected LoginHistoryDAO loginHistoryDAO;

	protected GenericFormDAO genericFormDAO;

	protected FormMessageDAO formMessageDAO;

	protected FormFieldMessageDAO formFieldMessageDAO;

	protected FormFieldDAO formFieldDAO;

	protected FormActionDAO formActionDAO;

	protected CandidateContainerDAO candidateContainerDAO;

	protected CandidateElementDAO candidateElementDAO;

	protected WechatWorkappIdentifyDAO wechatWorkappIdentifyDAO;

	protected WechatMiniappIdentifyDAO wechatMiniappIdentifyDAO;

	

	public PlatformDAO getPlatformDAO(){
		return this.platformDAO;
	}
	public void setPlatformDAO(PlatformDAO dao){
		this.platformDAO = dao;
	}


	public ProvinceDAO getProvinceDAO(){
		return this.provinceDAO;
	}
	public void setProvinceDAO(ProvinceDAO dao){
		this.provinceDAO = dao;
	}


	public CityDAO getCityDAO(){
		return this.cityDAO;
	}
	public void setCityDAO(CityDAO dao){
		this.cityDAO = dao;
	}


	public DistrictDAO getDistrictDAO(){
		return this.districtDAO;
	}
	public void setDistrictDAO(DistrictDAO dao){
		this.districtDAO = dao;
	}


	public LocationDAO getLocationDAO(){
		return this.locationDAO;
	}
	public void setLocationDAO(LocationDAO dao){
		this.locationDAO = dao;
	}


	public TeacherDAO getTeacherDAO(){
		return this.teacherDAO;
	}
	public void setTeacherDAO(TeacherDAO dao){
		this.teacherDAO = dao;
	}


	public StudentDAO getStudentDAO(){
		return this.studentDAO;
	}
	public void setStudentDAO(StudentDAO dao){
		this.studentDAO = dao;
	}


	public QuestionDAO getQuestionDAO(){
		return this.questionDAO;
	}
	public void setQuestionDAO(QuestionDAO dao){
		this.questionDAO = dao;
	}


	public QuestionTypeDAO getQuestionTypeDAO(){
		return this.questionTypeDAO;
	}
	public void setQuestionTypeDAO(QuestionTypeDAO dao){
		this.questionTypeDAO = dao;
	}


	public ClassDailyHealthSurveyDAO getClassDailyHealthSurveyDAO(){
		return this.classDailyHealthSurveyDAO;
	}
	public void setClassDailyHealthSurveyDAO(ClassDailyHealthSurveyDAO dao){
		this.classDailyHealthSurveyDAO = dao;
	}


	public DailySurveyQuestionDAO getDailySurveyQuestionDAO(){
		return this.dailySurveyQuestionDAO;
	}
	public void setDailySurveyQuestionDAO(DailySurveyQuestionDAO dao){
		this.dailySurveyQuestionDAO = dao;
	}


	public StudentHealthSurveyDAO getStudentHealthSurveyDAO(){
		return this.studentHealthSurveyDAO;
	}
	public void setStudentHealthSurveyDAO(StudentHealthSurveyDAO dao){
		this.studentHealthSurveyDAO = dao;
	}


	public StudentDailyAnswerDAO getStudentDailyAnswerDAO(){
		return this.studentDailyAnswerDAO;
	}
	public void setStudentDailyAnswerDAO(StudentDailyAnswerDAO dao){
		this.studentDailyAnswerDAO = dao;
	}


	public SurveyStatusDAO getSurveyStatusDAO(){
		return this.surveyStatusDAO;
	}
	public void setSurveyStatusDAO(SurveyStatusDAO dao){
		this.surveyStatusDAO = dao;
	}


	public HealthSurveyReportDAO getHealthSurveyReportDAO(){
		return this.healthSurveyReportDAO;
	}
	public void setHealthSurveyReportDAO(HealthSurveyReportDAO dao){
		this.healthSurveyReportDAO = dao;
	}


	public StudentAnswerDAO getStudentAnswerDAO(){
		return this.studentAnswerDAO;
	}
	public void setStudentAnswerDAO(StudentAnswerDAO dao){
		this.studentAnswerDAO = dao;
	}


	public UserDAO getUserDAO(){
		return this.userDAO;
	}
	public void setUserDAO(UserDAO dao){
		this.userDAO = dao;
	}


	public WechatLoginInfoDAO getWechatLoginInfoDAO(){
		return this.wechatLoginInfoDAO;
	}
	public void setWechatLoginInfoDAO(WechatLoginInfoDAO dao){
		this.wechatLoginInfoDAO = dao;
	}


	public ChangeRequestDAO getChangeRequestDAO(){
		return this.changeRequestDAO;
	}
	public void setChangeRequestDAO(ChangeRequestDAO dao){
		this.changeRequestDAO = dao;
	}


	public ChangeRequestTypeDAO getChangeRequestTypeDAO(){
		return this.changeRequestTypeDAO;
	}
	public void setChangeRequestTypeDAO(ChangeRequestTypeDAO dao){
		this.changeRequestTypeDAO = dao;
	}


	public UserDomainDAO getUserDomainDAO(){
		return this.userDomainDAO;
	}
	public void setUserDomainDAO(UserDomainDAO dao){
		this.userDomainDAO = dao;
	}


	public UserWhiteListDAO getUserWhiteListDAO(){
		return this.userWhiteListDAO;
	}
	public void setUserWhiteListDAO(UserWhiteListDAO dao){
		this.userWhiteListDAO = dao;
	}


	public SecUserDAO getSecUserDAO(){
		return this.secUserDAO;
	}
	public void setSecUserDAO(SecUserDAO dao){
		this.secUserDAO = dao;
	}


	public UserAppDAO getUserAppDAO(){
		return this.userAppDAO;
	}
	public void setUserAppDAO(UserAppDAO dao){
		this.userAppDAO = dao;
	}


	public QuickLinkDAO getQuickLinkDAO(){
		return this.quickLinkDAO;
	}
	public void setQuickLinkDAO(QuickLinkDAO dao){
		this.quickLinkDAO = dao;
	}


	public ListAccessDAO getListAccessDAO(){
		return this.listAccessDAO;
	}
	public void setListAccessDAO(ListAccessDAO dao){
		this.listAccessDAO = dao;
	}


	public ObjectAccessDAO getObjectAccessDAO(){
		return this.objectAccessDAO;
	}
	public void setObjectAccessDAO(ObjectAccessDAO dao){
		this.objectAccessDAO = dao;
	}


	public LoginHistoryDAO getLoginHistoryDAO(){
		return this.loginHistoryDAO;
	}
	public void setLoginHistoryDAO(LoginHistoryDAO dao){
		this.loginHistoryDAO = dao;
	}


	public GenericFormDAO getGenericFormDAO(){
		return this.genericFormDAO;
	}
	public void setGenericFormDAO(GenericFormDAO dao){
		this.genericFormDAO = dao;
	}


	public FormMessageDAO getFormMessageDAO(){
		return this.formMessageDAO;
	}
	public void setFormMessageDAO(FormMessageDAO dao){
		this.formMessageDAO = dao;
	}


	public FormFieldMessageDAO getFormFieldMessageDAO(){
		return this.formFieldMessageDAO;
	}
	public void setFormFieldMessageDAO(FormFieldMessageDAO dao){
		this.formFieldMessageDAO = dao;
	}


	public FormFieldDAO getFormFieldDAO(){
		return this.formFieldDAO;
	}
	public void setFormFieldDAO(FormFieldDAO dao){
		this.formFieldDAO = dao;
	}


	public FormActionDAO getFormActionDAO(){
		return this.formActionDAO;
	}
	public void setFormActionDAO(FormActionDAO dao){
		this.formActionDAO = dao;
	}


	public CandidateContainerDAO getCandidateContainerDAO(){
		return this.candidateContainerDAO;
	}
	public void setCandidateContainerDAO(CandidateContainerDAO dao){
		this.candidateContainerDAO = dao;
	}


	public CandidateElementDAO getCandidateElementDAO(){
		return this.candidateElementDAO;
	}
	public void setCandidateElementDAO(CandidateElementDAO dao){
		this.candidateElementDAO = dao;
	}


	public WechatWorkappIdentifyDAO getWechatWorkappIdentifyDAO(){
		return this.wechatWorkappIdentifyDAO;
	}
	public void setWechatWorkappIdentifyDAO(WechatWorkappIdentifyDAO dao){
		this.wechatWorkappIdentifyDAO = dao;
	}


	public WechatMiniappIdentifyDAO getWechatMiniappIdentifyDAO(){
		return this.wechatMiniappIdentifyDAO;
	}
	public void setWechatMiniappIdentifyDAO(WechatMiniappIdentifyDAO dao){
		this.wechatMiniappIdentifyDAO = dao;
	}


	private interface BasicLoader{
	    BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception;
	    void enhanceList(DAOGroup daoGoup, List list) throws Exception;
	    BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception;
	    BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception;
	}
	private static Map<String, BasicLoader> internalLoaderMap;
	static {
		internalLoaderMap = new HashMap<String, BasicLoader>();

		internalLoaderMap.put("Platform", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPlatformDAO().load(id, PlatformTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPlatformDAO().enhanceList((List<Platform>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPlatformDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPlatformDAO().present((Platform)data, tokens);
			}
		});

		internalLoaderMap.put("Province", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getProvinceDAO().load(id, ProvinceTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getProvinceDAO().enhanceList((List<Province>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getProvinceDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getProvinceDAO().present((Province)data, tokens);
			}
		});

		internalLoaderMap.put("City", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getCityDAO().load(id, CityTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getCityDAO().enhanceList((List<City>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCityDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCityDAO().present((City)data, tokens);
			}
		});

		internalLoaderMap.put("District", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getDistrictDAO().load(id, DistrictTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getDistrictDAO().enhanceList((List<District>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getDistrictDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getDistrictDAO().present((District)data, tokens);
			}
		});

		internalLoaderMap.put("Location", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getLocationDAO().load(id, LocationTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getLocationDAO().enhanceList((List<Location>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLocationDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLocationDAO().present((Location)data, tokens);
			}
		});

		internalLoaderMap.put("Teacher", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getTeacherDAO().load(id, TeacherTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getTeacherDAO().enhanceList((List<Teacher>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTeacherDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTeacherDAO().present((Teacher)data, tokens);
			}
		});

		internalLoaderMap.put("Student", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getStudentDAO().load(id, StudentTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getStudentDAO().enhanceList((List<Student>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentDAO().present((Student)data, tokens);
			}
		});

		internalLoaderMap.put("Question", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getQuestionDAO().load(id, QuestionTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getQuestionDAO().enhanceList((List<Question>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuestionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuestionDAO().present((Question)data, tokens);
			}
		});

		internalLoaderMap.put("QuestionType", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getQuestionTypeDAO().load(id, QuestionTypeTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getQuestionTypeDAO().enhanceList((List<QuestionType>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuestionTypeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuestionTypeDAO().present((QuestionType)data, tokens);
			}
		});

		internalLoaderMap.put("ClassDailyHealthSurvey", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getClassDailyHealthSurveyDAO().load(id, ClassDailyHealthSurveyTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getClassDailyHealthSurveyDAO().enhanceList((List<ClassDailyHealthSurvey>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getClassDailyHealthSurveyDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getClassDailyHealthSurveyDAO().present((ClassDailyHealthSurvey)data, tokens);
			}
		});

		internalLoaderMap.put("DailySurveyQuestion", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getDailySurveyQuestionDAO().load(id, DailySurveyQuestionTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getDailySurveyQuestionDAO().enhanceList((List<DailySurveyQuestion>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getDailySurveyQuestionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getDailySurveyQuestionDAO().present((DailySurveyQuestion)data, tokens);
			}
		});

		internalLoaderMap.put("StudentHealthSurvey", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getStudentHealthSurveyDAO().load(id, StudentHealthSurveyTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getStudentHealthSurveyDAO().enhanceList((List<StudentHealthSurvey>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentHealthSurveyDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentHealthSurveyDAO().present((StudentHealthSurvey)data, tokens);
			}
		});

		internalLoaderMap.put("StudentDailyAnswer", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getStudentDailyAnswerDAO().load(id, StudentDailyAnswerTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getStudentDailyAnswerDAO().enhanceList((List<StudentDailyAnswer>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentDailyAnswerDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentDailyAnswerDAO().present((StudentDailyAnswer)data, tokens);
			}
		});

		internalLoaderMap.put("SurveyStatus", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSurveyStatusDAO().load(id, SurveyStatusTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getSurveyStatusDAO().enhanceList((List<SurveyStatus>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSurveyStatusDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSurveyStatusDAO().present((SurveyStatus)data, tokens);
			}
		});

		internalLoaderMap.put("HealthSurveyReport", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getHealthSurveyReportDAO().load(id, HealthSurveyReportTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getHealthSurveyReportDAO().enhanceList((List<HealthSurveyReport>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getHealthSurveyReportDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getHealthSurveyReportDAO().present((HealthSurveyReport)data, tokens);
			}
		});

		internalLoaderMap.put("StudentAnswer", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getStudentAnswerDAO().load(id, StudentAnswerTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getStudentAnswerDAO().enhanceList((List<StudentAnswer>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentAnswerDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getStudentAnswerDAO().present((StudentAnswer)data, tokens);
			}
		});

		internalLoaderMap.put("User", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserDAO().load(id, UserTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserDAO().enhanceList((List<User>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDAO().present((User)data, tokens);
			}
		});

		internalLoaderMap.put("WechatLoginInfo", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getWechatLoginInfoDAO().load(id, WechatLoginInfoTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getWechatLoginInfoDAO().enhanceList((List<WechatLoginInfo>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatLoginInfoDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatLoginInfoDAO().present((WechatLoginInfo)data, tokens);
			}
		});

		internalLoaderMap.put("ChangeRequest", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getChangeRequestDAO().load(id, ChangeRequestTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getChangeRequestDAO().enhanceList((List<ChangeRequest>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestDAO().present((ChangeRequest)data, tokens);
			}
		});

		internalLoaderMap.put("ChangeRequestType", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().load(id, ChangeRequestTypeTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getChangeRequestTypeDAO().enhanceList((List<ChangeRequestType>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().present((ChangeRequestType)data, tokens);
			}
		});

		internalLoaderMap.put("UserDomain", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserDomainDAO().load(id, UserDomainTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserDomainDAO().enhanceList((List<UserDomain>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDomainDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDomainDAO().present((UserDomain)data, tokens);
			}
		});

		internalLoaderMap.put("UserWhiteList", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserWhiteListDAO().load(id, UserWhiteListTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserWhiteListDAO().enhanceList((List<UserWhiteList>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserWhiteListDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserWhiteListDAO().present((UserWhiteList)data, tokens);
			}
		});

		internalLoaderMap.put("SecUser", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSecUserDAO().load(id, SecUserTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getSecUserDAO().enhanceList((List<SecUser>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserDAO().present((SecUser)data, tokens);
			}
		});

		internalLoaderMap.put("UserApp", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserAppDAO().load(id, UserAppTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserAppDAO().enhanceList((List<UserApp>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserAppDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserAppDAO().present((UserApp)data, tokens);
			}
		});

		internalLoaderMap.put("QuickLink", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getQuickLinkDAO().load(id, QuickLinkTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getQuickLinkDAO().enhanceList((List<QuickLink>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuickLinkDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuickLinkDAO().present((QuickLink)data, tokens);
			}
		});

		internalLoaderMap.put("ListAccess", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getListAccessDAO().load(id, ListAccessTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getListAccessDAO().enhanceList((List<ListAccess>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getListAccessDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getListAccessDAO().present((ListAccess)data, tokens);
			}
		});

		internalLoaderMap.put("ObjectAccess", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getObjectAccessDAO().load(id, ObjectAccessTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getObjectAccessDAO().enhanceList((List<ObjectAccess>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getObjectAccessDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getObjectAccessDAO().present((ObjectAccess)data, tokens);
			}
		});

		internalLoaderMap.put("LoginHistory", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getLoginHistoryDAO().load(id, LoginHistoryTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getLoginHistoryDAO().enhanceList((List<LoginHistory>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLoginHistoryDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLoginHistoryDAO().present((LoginHistory)data, tokens);
			}
		});

		internalLoaderMap.put("GenericForm", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getGenericFormDAO().load(id, GenericFormTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getGenericFormDAO().enhanceList((List<GenericForm>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getGenericFormDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getGenericFormDAO().present((GenericForm)data, tokens);
			}
		});

		internalLoaderMap.put("FormMessage", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormMessageDAO().load(id, FormMessageTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getFormMessageDAO().enhanceList((List<FormMessage>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormMessageDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormMessageDAO().present((FormMessage)data, tokens);
			}
		});

		internalLoaderMap.put("FormFieldMessage", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormFieldMessageDAO().load(id, FormFieldMessageTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getFormFieldMessageDAO().enhanceList((List<FormFieldMessage>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldMessageDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldMessageDAO().present((FormFieldMessage)data, tokens);
			}
		});

		internalLoaderMap.put("FormField", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormFieldDAO().load(id, FormFieldTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getFormFieldDAO().enhanceList((List<FormField>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormFieldDAO().present((FormField)data, tokens);
			}
		});

		internalLoaderMap.put("FormAction", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getFormActionDAO().load(id, FormActionTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getFormActionDAO().enhanceList((List<FormAction>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormActionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getFormActionDAO().present((FormAction)data, tokens);
			}
		});

		internalLoaderMap.put("CandidateContainer", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getCandidateContainerDAO().load(id, CandidateContainerTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getCandidateContainerDAO().enhanceList((List<CandidateContainer>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateContainerDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateContainerDAO().present((CandidateContainer)data, tokens);
			}
		});

		internalLoaderMap.put("CandidateElement", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getCandidateElementDAO().load(id, CandidateElementTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getCandidateElementDAO().enhanceList((List<CandidateElement>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateElementDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateElementDAO().present((CandidateElement)data, tokens);
			}
		});

		internalLoaderMap.put("WechatWorkappIdentify", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getWechatWorkappIdentifyDAO().load(id, WechatWorkappIdentifyTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getWechatWorkappIdentifyDAO().enhanceList((List<WechatWorkappIdentify>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatWorkappIdentifyDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatWorkappIdentifyDAO().present((WechatWorkappIdentify)data, tokens);
			}
		});

		internalLoaderMap.put("WechatMiniappIdentify", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getWechatMiniappIdentifyDAO().load(id, WechatMiniappIdentifyTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getWechatMiniappIdentifyDAO().enhanceList((List<WechatMiniappIdentify>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatMiniappIdentifyDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatMiniappIdentifyDAO().present((WechatMiniappIdentify)data, tokens);
			}
		});

	}
	public BaseEntity loadBasicData(String type, String id){
	    BasicLoader loader = internalLoaderMap.get(type);
	    if (loader == null) {
	    	return null;
	    }
	    try{
	    	return loader.loadBasicData(this, id);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public BaseEntity loadBasicDataWithTokens(String type, String id, Map<String, Object> tokens){
	    BasicLoader loader = internalLoaderMap.get(type);
	    if (loader == null) {
	    	return null;
	    }
	    try{
	    	return loader.loadBasicDataWithToken(this, id, tokens);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public BaseEntity present(BaseEntity data, Map<String, Object> tokens){
	    BasicLoader loader = internalLoaderMap.get(data.getInternalType());
	    if (loader == null || data == null) {
	    	return null;
	    }
	    try{
	    	return loader.present(this, data, tokens);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public <T> void enhanceList(List list, Class<T> clazz) throws Exception{
	    BasicLoader loader = internalLoaderMap.get(clazz.getSimpleName());
	    if (loader == null) {
	    	return ;
	    }

	    loader.enhanceList(this, list);
	}
}

