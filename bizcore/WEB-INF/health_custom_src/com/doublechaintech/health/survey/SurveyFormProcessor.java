package com.doublechaintech.health.survey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.terapico.caf.DateTime;
import com.terapico.caf.form.ImageInfo;
import com.terapico.utils.CollectionUtils;
import com.doublechaintech.health.BaseHealthFormProcessor;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.student.StudentTokens;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;

public class SurveyFormProcessor extends BaseHealthFormProcessor {
	protected List<StudentDailyAnswer> answers;
	protected Date surveyDate;
	protected List<Object> student;
	protected String studentName;
//	protected String studentNumber;
//	protected List<ImageInfo> studentAvatar;
	protected Object candidateQuestionsValue;
	protected Object candidateStudentValue;
	
	private String surveyId;
	
	

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}


	public List<StudentDailyAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<StudentDailyAnswer> answers) {
		this.answers = answers;
	}

	public List<Object> getStudent() {
		return student;
	}

	public void setStudent(List<Object> student) {
		this.student = student;
	}

	public Date getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

//	public String getStudentNumber(){
//		return this.studentNumber;
//	}
//	public void setStudentNumber(String studentNumber) {
//		this.studentNumber = studentNumber;
//	}
//	public List<ImageInfo> getStudentAvatar(){
//		return this.studentAvatar;
//	}
//	public void setStudentAvatar(List<ImageInfo> studentAvatar) {
//		this.studentAvatar = studentAvatar;
//	}
	public Object getCandidateQuestionsValue() {
		return this.candidateQuestionsValue;
	}

	public void setCandidateQuestionsValue(Object candidateQuestionsValue) {
		this.candidateQuestionsValue = candidateQuestionsValue;
	}

	public Object getCandidateStudentValue() {
		return this.candidateStudentValue;
	}

	public void setCandidateStudentValue(Object candidateStudentValue) {
		this.candidateStudentValue = candidateStudentValue;
	}

	public SurveyFormProcessor initByRequest(HealthUserContext userContext, String requestBody) throws Exception {
		setUserContext(userContext);
		loadRequestBody(requestBody);
//		setQuestions(pickObjectListParams("questions"));
		setSurveyDate(pickDateParams("surveyDate", null));
//		setStudent(pickObjectListParams("student"));
		setStudentName(pickStringParams("studentName", null));
//		setStudentNumber(pickStringParams("studentNumber", null));
//		setStudentAvatar(pickImagesParams("studentAvatar"));
		return this;
	}

	public SurveyFormProcessor() {
		super();
	}

	public Map<String, Object> mapToUiForm(HealthUserContext userContext) {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) userContext;
		userContext.forceResponseXClassHeader("com.doublechaintech.health.survey.SurveyForm");
		Map<String, Object> result = new HashMap<>();
		result.put("title", "survey form");
		Map<String, Object> fields = new HashMap<>();
		result.put("fields", fields);
		fields.put("sceneCode", mapStringFieldIntoUiForm(true, "sceneCode", sceneCode));

		fields.put("surveyDate", mapDateFieldIntoUiForm(false, "surveyDate", DateTime.now()));

		getAnswers().forEach(answer -> {
			DailySurveyQuestion q = answer.getQuestion();
			if (QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId())) {
				fields
						.put(q.getId(), mapStringSelectionFieldIntoUiForm(q.getTopic(), q.getOptionA(), CollectionUtils
								.toList(q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD())));

			} else if (QuestionType.TEXT_INPUT.equals(q.getQuestionType().getId())) {
				fields.put(q.getId(), mapStringFieldIntoUiForm(q.getId(), ""));
			}
		});

		SmartList<Student> students = ctx
				.getDAOGroup()
					.getStudentDAO()
					.findStudentByUser(ctx.getCurrentUserInfo().getId(), StudentTokens.all());
//		if(students==null||students.isEmpty()) {
		fields
				.put("studentName", mapStringFieldIntoUiForm(false, "studentName",
						Optional.ofNullable(students.first()).map(Student::getStudentName).orElse("")));
//			fields.put("studentNumber", mapStringFieldIntoUiForm(false, "studentNumber", getStudentNumber() ));	
//			fields.put("studentAvatar", mapImagesFieldIntoUiForm(false, "studentAvatar", getStudentAvatar() ));	
//		}else {
//			fields.put("student", mapObjectListFieldIntoUiForm(false, "student", new ArrayList<>(students)));
//		}
		fields.put("surveyId", mapStringFieldIntoUiForm(true,"surveyId", getSurveyId()));
		mappingFormActions(result);
		return result;
	}

	public void verifyInputs() throws HealthException {
		HealthException e = new HealthException();
		boolean pass = true;
//		pass = verifyQuestionsInput(e) && pass;
		pass = verifySurveyDateInput(e) && pass;
		pass = verifyStudentInput(e) && pass;
		pass = verifyStudentNameInput(e) && pass;
//		pass = verifyStudentNumberInput(e) && pass;
//		pass = verifyStudentAvatarInput(e) && pass;
		if (!pass) {
			throw e;
		}
	}

	protected boolean canQuestionsBeNull() {
		return true;
	}

//	protected boolean verifyQuestionsInput(HealthException e) {
//		if (isEmpty(getQuestions())) {
//			if (canQuestionsBeNull()) {
//				return true;
//			}
//			addMessageToException(e, "请输入noname");
//			return false;
//		}
////		if (!verifyObjectListInput(getQuestions(), null, null)) {
////			addMessageToException(e, "noname的输入不正确");
////			return false;
////		}
//		return true;
//	}

	protected boolean canSurveyDateBeNull() {
		return true;
	}

	protected boolean verifySurveyDateInput(HealthException e) {
		if (isEmpty(getSurveyDate())) {
			if (canSurveyDateBeNull()) {
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyDateInput(getSurveyDate(), null, null)) {
			addMessageToException(e, "noname的值不正确");
			return false;
		}
		return true;
	}

	protected boolean canStudentBeNull() {
		return true;
	}

	protected boolean verifyStudentInput(HealthException e) {
		if (isEmpty(getStudent())) {
			if (canStudentBeNull()) {
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
//		if (!verifyObjectListInput(getStudent(), null, null)) {
//			addMessageToException(e, "noname的输入不正确");
//			return false;
//		}
		return true;
	}

	protected boolean canStudentNameBeNull() {
		return true;
	}

	protected boolean verifyStudentNameInput(HealthException e) {
		if (isEmpty(getStudentName())) {
			if (canStudentNameBeNull()) {
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyStringInput(getStudentName(), 0, 20)) {
			addMessageToException(e, "noname的范围是0~20");
			return false;
		}
		return true;
	}

	protected boolean canStudentNumberBeNull() {
		return true;
	}

//	protected boolean verifyStudentNumberInput(HealthException e) {
//		if (isEmpty(getStudentNumber())) {
//			if (canStudentNumberBeNull()) {
//				return true;
//			}
//			addMessageToException(e, "请输入noname");
//			return false;
//		}
//		if (!verifyStringInput(getStudentNumber(), 0, 99)) {
//			addMessageToException(e, "noname的范围是0~99");
//			return false;
//		}
//		return true;
//	}

	protected boolean canStudentAvatarBeNull() {
		return false;
	}

//	protected boolean verifyStudentAvatarInput(HealthException e) {
//		if (isEmpty(getStudentAvatar())) {
//			if (canStudentAvatarBeNull()) {
//				return true;
//			}
//			addMessageToException(e, "请输入noname");
//			return false;
//		}
//		if (!verifyImagesInput(getStudentAvatar(), canStudentAvatarBeNull(), null)) {
//			addMessageToException(e, "noname的值不正确");
//			return false;
//		}
//		return true;
//	}

	protected String unWrapperListImageInfoDataMember(List<ImageInfo> image) {
		if (image == null || image.isEmpty()) {
			return null;
		}
		return image.get(0).getImageUrl();
	}

}
