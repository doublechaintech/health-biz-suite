
package com.doublechaintech.health;
import java.util.Map;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.user.User;
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

public class BeanFactoryImpl{


	public Platform createPlatform(Map<String,Object> options){
		return new Platform();
	}


	public Province createProvince(Map<String,Object> options){
		return new Province();
	}


	public City createCity(Map<String,Object> options){
		return new City();
	}


	public District createDistrict(Map<String,Object> options){
		return new District();
	}


	public Location createLocation(Map<String,Object> options){
		return new Location();
	}


	public Teacher createTeacher(Map<String,Object> options){
		return new Teacher();
	}


	public Student createStudent(Map<String,Object> options){
		return new Student();
	}


	public Question createQuestion(Map<String,Object> options){
		return new Question();
	}


	public QuestionType createQuestionType(Map<String,Object> options){
		return new QuestionType();
	}


	public ClassDailyHealthSurvey createClassDailyHealthSurvey(Map<String,Object> options){
		return new ClassDailyHealthSurvey();
	}


	public DailySurveyQuestion createDailySurveyQuestion(Map<String,Object> options){
		return new DailySurveyQuestion();
	}


	public StudentHealthSurvey createStudentHealthSurvey(Map<String,Object> options){
		return new StudentHealthSurvey();
	}


	public StudentDailyAnswer createStudentDailyAnswer(Map<String,Object> options){
		return new StudentDailyAnswer();
	}


	public SurveyStatus createSurveyStatus(Map<String,Object> options){
		return new SurveyStatus();
	}


	public User createUser(Map<String,Object> options){
		return new User();
	}


	public WechatLoginInfo createWechatLoginInfo(Map<String,Object> options){
		return new WechatLoginInfo();
	}


	public ChangeRequest createChangeRequest(Map<String,Object> options){
		return new ChangeRequest();
	}


	public ChangeRequestType createChangeRequestType(Map<String,Object> options){
		return new ChangeRequestType();
	}


	public UserDomain createUserDomain(Map<String,Object> options){
		return new UserDomain();
	}


	public UserWhiteList createUserWhiteList(Map<String,Object> options){
		return new UserWhiteList();
	}


	public SecUser createSecUser(Map<String,Object> options){
		return new SecUser();
	}


	public UserApp createUserApp(Map<String,Object> options){
		return new UserApp();
	}


	public QuickLink createQuickLink(Map<String,Object> options){
		return new QuickLink();
	}


	public ListAccess createListAccess(Map<String,Object> options){
		return new ListAccess();
	}


	public ObjectAccess createObjectAccess(Map<String,Object> options){
		return new ObjectAccess();
	}


	public LoginHistory createLoginHistory(Map<String,Object> options){
		return new LoginHistory();
	}


	public GenericForm createGenericForm(Map<String,Object> options){
		return new GenericForm();
	}


	public FormMessage createFormMessage(Map<String,Object> options){
		return new FormMessage();
	}


	public FormFieldMessage createFormFieldMessage(Map<String,Object> options){
		return new FormFieldMessage();
	}


	public FormField createFormField(Map<String,Object> options){
		return new FormField();
	}


	public FormAction createFormAction(Map<String,Object> options){
		return new FormAction();
	}


	public CandidateContainer createCandidateContainer(Map<String,Object> options){
		return new CandidateContainer();
	}


	public CandidateElement createCandidateElement(Map<String,Object> options){
		return new CandidateElement();
	}


	public WechatWorkappIdentify createWechatWorkappIdentify(Map<String,Object> options){
		return new WechatWorkappIdentify();
	}


	public WechatMiniappIdentify createWechatMiniappIdentify(Map<String,Object> options){
		return new WechatMiniappIdentify();
	}





}







