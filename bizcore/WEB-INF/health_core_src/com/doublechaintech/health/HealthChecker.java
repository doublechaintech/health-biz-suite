
package com.doublechaintech.health;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
public class HealthChecker extends BaseChecker{

	
	public HealthChecker() {
		this.messageList = new ArrayList<Message>();
	}
	

	public static final String  ID_OF_PLATFORM ="platform.id";
	public HealthChecker checkIdOfPlatform(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PLATFORM ="platform.name";
	public HealthChecker checkNameOfPlatform(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_PLATFORM ="platform.description";
	public HealthChecker checkDescriptionOfPlatform(String description)
	{		
	 	checkLongtext(description,0, 1048576,DESCRIPTION_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PLATFORM ="platform.version";
	public HealthChecker checkVersionOfPlatform(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  ID_OF_PROVINCE ="province.id";
	public HealthChecker checkIdOfProvince(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PROVINCE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PROVINCE ="province.name";
	public HealthChecker checkNameOfProvince(String name)
	{		
	 	checkStringLengthRange(name,1, 50,NAME_OF_PROVINCE ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_PROVINCE ="province.platform";
	public HealthChecker checkPlatformIdOfProvince(String platformId)
	{		
	 	checkIdOfProvince(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PROVINCE ="province.version";
	public HealthChecker checkVersionOfProvince(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PROVINCE ); 		
		
		return this;
	}	

	public static final String  ID_OF_CITY ="city.id";
	public HealthChecker checkIdOfCity(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CITY ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CITY ="city.name";
	public HealthChecker checkNameOfCity(String name)
	{		
	 	checkStringLengthRange(name,1, 50,NAME_OF_CITY ); 		
		
		return this;
	}	

	public static final String  PROVINCE_OF_CITY ="city.province";
	public HealthChecker checkProvinceIdOfCity(String provinceId)
	{		
	 	checkIdOfCity(provinceId ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_CITY ="city.platform";
	public HealthChecker checkPlatformIdOfCity(String platformId)
	{		
	 	checkIdOfCity(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CITY ="city.version";
	public HealthChecker checkVersionOfCity(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CITY ); 		
		
		return this;
	}	

	public static final String  ID_OF_DISTRICT ="district.id";
	public HealthChecker checkIdOfDistrict(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_DISTRICT ); 		
		
		return this;
	}	

	public static final String  NAME_OF_DISTRICT ="district.name";
	public HealthChecker checkNameOfDistrict(String name)
	{		
	 	checkStringLengthRange(name,1, 50,NAME_OF_DISTRICT ); 		
		
		return this;
	}	

	public static final String  CITY_OF_DISTRICT ="district.city";
	public HealthChecker checkCityIdOfDistrict(String cityId)
	{		
	 	checkIdOfDistrict(cityId ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_DISTRICT ="district.platform";
	public HealthChecker checkPlatformIdOfDistrict(String platformId)
	{		
	 	checkIdOfDistrict(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_DISTRICT ="district.version";
	public HealthChecker checkVersionOfDistrict(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_DISTRICT ); 		
		
		return this;
	}	

	public static final String  ID_OF_LOCATION ="location.id";
	public HealthChecker checkIdOfLocation(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_LOCATION ); 		
		
		return this;
	}	

	public static final String  NAME_OF_LOCATION ="location.name";
	public HealthChecker checkNameOfLocation(String name)
	{		
	 	checkStringLengthRange(name,2, 16,NAME_OF_LOCATION ); 		
		
		return this;
	}	

	public static final String  ADDRESS_OF_LOCATION ="location.address";
	public HealthChecker checkAddressOfLocation(String address)
	{		
	 	checkStringLengthRange(address,1, 100,ADDRESS_OF_LOCATION ); 		
		
		return this;
	}	

	public static final String  DISTRICT_OF_LOCATION ="location.district";
	public HealthChecker checkDistrictIdOfLocation(String districtId)
	{		
	 	checkIdOfLocation(districtId ); 		
		
		return this;
	}	

	public static final String  PROVINCE_OF_LOCATION ="location.province";
	public HealthChecker checkProvinceIdOfLocation(String provinceId)
	{		
	 	checkIdOfLocation(provinceId ); 		
		
		return this;
	}	

	public static final String  LATITUDE_OF_LOCATION ="location.latitude";
	public HealthChecker checkLatitudeOfLocation(BigDecimal latitude)
	{		
	 	checkBigDecimalRange(latitude,-180.00, 180.00,LATITUDE_OF_LOCATION ); 		
		
		return this;
	}	

	public static final String  LONGITUDE_OF_LOCATION ="location.longitude";
	public HealthChecker checkLongitudeOfLocation(BigDecimal longitude)
	{		
	 	checkBigDecimalRange(longitude,-180.00, 180.00,LONGITUDE_OF_LOCATION ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LOCATION ="location.version";
	public HealthChecker checkVersionOfLocation(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LOCATION ); 		
		
		return this;
	}	

	public static final String  ID_OF_TEACHER ="teacher.id";
	public HealthChecker checkIdOfTeacher(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  NAME_OF_TEACHER ="teacher.name";
	public HealthChecker checkNameOfTeacher(String name)
	{		
	 	checkStringLengthRange(name,1, 20,NAME_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  MOBILE_OF_TEACHER ="teacher.mobile";
	public HealthChecker checkMobileOfTeacher(String mobile)
	{		
	 	checkChinaMobilePhone(mobile,5, 44,MOBILE_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  SCHOOL_OF_TEACHER ="teacher.school";
	public HealthChecker checkSchoolOfTeacher(String school)
	{		
	 	checkStringLengthRange(school,1, 99,SCHOOL_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  SCHOOL_CLASS_OF_TEACHER ="teacher.school_class";
	public HealthChecker checkSchoolClassOfTeacher(String schoolClass)
	{		
	 	checkStringLengthRange(schoolClass,1, 99,SCHOOL_CLASS_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  CLASS_SIZE_OF_TEACHER ="teacher.class_size";
	public HealthChecker checkClassSizeOfTeacher(int classSize)
	{		
	 	checkIntegerRange(classSize,1, 999,CLASS_SIZE_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_TEACHER ="teacher.platform";
	public HealthChecker checkPlatformIdOfTeacher(String platformId)
	{		
	 	checkIdOfTeacher(platformId ); 		
		
		return this;
	}	

	public static final String  USER_OF_TEACHER ="teacher.user";
	public HealthChecker checkUserIdOfTeacher(String userId)
	{		
	 	checkIdOfTeacher(userId ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_TEACHER ="teacher.change_request";
	public HealthChecker checkChangeRequestIdOfTeacher(String changeRequestId)
	{		if(isValidIdentifier(changeRequestId)){
	 	checkIdOfTeacher(changeRequestId ); 		
		}
		return this;
	}	

	public static final String  VERSION_OF_TEACHER ="teacher.version";
	public HealthChecker checkVersionOfTeacher(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_TEACHER ); 		
		
		return this;
	}	

	public static final String  ID_OF_STUDENT ="student.id";
	public HealthChecker checkIdOfStudent(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  STUDENT_NAME_OF_STUDENT ="student.student_name";
	public HealthChecker checkStudentNameOfStudent(String studentName)
	{		
	 	checkStringLengthRange(studentName,0, 20,STUDENT_NAME_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  STUDENT_NUMBER_OF_STUDENT ="student.student_number";
	public HealthChecker checkStudentNumberOfStudent(String studentNumber)
	{		
	 	checkStringLengthRange(studentNumber,0, 99,STUDENT_NUMBER_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  STUDENT_AVATAR_OF_STUDENT ="student.student_avatar";
	public HealthChecker checkStudentAvatarOfStudent(String studentAvatar)
	{		
	 	checkImage(studentAvatar,0, 512,STUDENT_AVATAR_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  GUARDIAN_NAME_OF_STUDENT ="student.guardian_name";
	public HealthChecker checkGuardianNameOfStudent(String guardianName)
	{		
	 	checkStringLengthRange(guardianName,0, 20,GUARDIAN_NAME_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  GUARDIAN_MOBILE_OF_STUDENT ="student.guardian_mobile";
	public HealthChecker checkGuardianMobileOfStudent(String guardianMobile)
	{		
	 	checkChinaMobilePhone(guardianMobile,5, 44,GUARDIAN_MOBILE_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  ADDRESS_OF_STUDENT ="student.address";
	public HealthChecker checkAddressIdOfStudent(String addressId)
	{		if(isValidIdentifier(addressId)){
	 	checkIdOfStudent(addressId ); 		
		}
		return this;
	}	

	public static final String  USER_OF_STUDENT ="student.user";
	public HealthChecker checkUserIdOfStudent(String userId)
	{		
	 	checkIdOfStudent(userId ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_STUDENT ="student.platform";
	public HealthChecker checkPlatformIdOfStudent(String platformId)
	{		
	 	checkIdOfStudent(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_STUDENT ="student.version";
	public HealthChecker checkVersionOfStudent(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_STUDENT ); 		
		
		return this;
	}	

	public static final String  ID_OF_QUESTION ="question.id";
	public HealthChecker checkIdOfQuestion(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  TOPIC_OF_QUESTION ="question.topic";
	public HealthChecker checkTopicOfQuestion(String topic)
	{		
	 	checkStringLengthRange(topic,1, 50,TOPIC_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  QUESTION_TYPE_OF_QUESTION ="question.question_type";
	public HealthChecker checkQuestionTypeIdOfQuestion(String questionTypeId)
	{		
	 	checkIdOfQuestion(questionTypeId ); 		
		
		return this;
	}	

	public static final String  OPTION_A_OF_QUESTION ="question.option_a";
	public HealthChecker checkOptionAOfQuestion(String optionA)
	{		
	 	checkStringLengthRange(optionA,0, 99,OPTION_A_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  OPTION_B_OF_QUESTION ="question.option_b";
	public HealthChecker checkOptionBOfQuestion(String optionB)
	{		
	 	checkStringLengthRange(optionB,0, 99,OPTION_B_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  OPTION_C_OF_QUESTION ="question.option_c";
	public HealthChecker checkOptionCOfQuestion(String optionC)
	{		
	 	checkStringLengthRange(optionC,0, 99,OPTION_C_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  OPTION_D_OF_QUESTION ="question.option_d";
	public HealthChecker checkOptionDOfQuestion(String optionD)
	{		
	 	checkStringLengthRange(optionD,0, 99,OPTION_D_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_QUESTION ="question.platform";
	public HealthChecker checkPlatformIdOfQuestion(String platformId)
	{		
	 	checkIdOfQuestion(platformId ); 		
		
		return this;
	}	

	public static final String  CREATOR_OF_QUESTION ="question.creator";
	public HealthChecker checkCreatorIdOfQuestion(String creatorId)
	{		if(isValidIdentifier(creatorId)){
	 	checkIdOfQuestion(creatorId ); 		
		}
		return this;
	}	

	public static final String  CQ_OF_QUESTION ="question.cq";
	public HealthChecker checkCqIdOfQuestion(String cqId)
	{		if(isValidIdentifier(cqId)){
	 	checkIdOfQuestion(cqId ); 		
		}
		return this;
	}	

	public static final String  VERSION_OF_QUESTION ="question.version";
	public HealthChecker checkVersionOfQuestion(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_QUESTION ); 		
		
		return this;
	}	

	public static final String  ID_OF_QUESTION_TYPE ="question_type.id";
	public HealthChecker checkIdOfQuestionType(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_QUESTION_TYPE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_QUESTION_TYPE ="question_type.name";
	public HealthChecker checkNameOfQuestionType(String name)
	{		
	 	checkStringLengthRange(name,1, 12,NAME_OF_QUESTION_TYPE ); 		
		
		return this;
	}	

	public static final String  CODE_OF_QUESTION_TYPE ="question_type.code";
	public HealthChecker checkCodeOfQuestionType(String code)
	{		
	 	checkStringLengthRange(code,5, 52,CODE_OF_QUESTION_TYPE ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_QUESTION_TYPE ="question_type.platform";
	public HealthChecker checkPlatformIdOfQuestionType(String platformId)
	{		
	 	checkIdOfQuestionType(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_QUESTION_TYPE ="question_type.version";
	public HealthChecker checkVersionOfQuestionType(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_QUESTION_TYPE ); 		
		
		return this;
	}	

	public static final String  ID_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.id";
	public HealthChecker checkIdOfClassDailyHealthSurvey(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CLASS_DAILY_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.name";
	public HealthChecker checkNameOfClassDailyHealthSurvey(String name)
	{		
	 	checkStringLengthRange(name,6, 88,NAME_OF_CLASS_DAILY_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  TEACHER_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.teacher";
	public HealthChecker checkTeacherIdOfClassDailyHealthSurvey(String teacherId)
	{		
	 	checkIdOfClassDailyHealthSurvey(teacherId ); 		
		
		return this;
	}	

	public static final String  SURVEY_TIME_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.survey_time";
	public HealthChecker checkSurveyTimeOfClassDailyHealthSurvey(DateTime surveyTime)
	{		
	 	checkDateTime(surveyTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T23:59:59"),SURVEY_TIME_OF_CLASS_DAILY_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  CREATOR_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.creator";
	public HealthChecker checkCreatorIdOfClassDailyHealthSurvey(String creatorId)
	{		
	 	checkIdOfClassDailyHealthSurvey(creatorId ); 		
		
		return this;
	}	

	public static final String  DOWNLOAD_URL_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.download_url";
	public HealthChecker checkDownloadUrlOfClassDailyHealthSurvey(String downloadUrl)
	{		
	 	checkUrl(downloadUrl,0, 99999,DOWNLOAD_URL_OF_CLASS_DAILY_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.change_request";
	public HealthChecker checkChangeRequestIdOfClassDailyHealthSurvey(String changeRequestId)
	{		if(isValidIdentifier(changeRequestId)){
	 	checkIdOfClassDailyHealthSurvey(changeRequestId ); 		
		}
		return this;
	}	

	public static final String  VERSION_OF_CLASS_DAILY_HEALTH_SURVEY ="class_daily_health_survey.version";
	public HealthChecker checkVersionOfClassDailyHealthSurvey(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CLASS_DAILY_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  ID_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.id";
	public HealthChecker checkIdOfDailySurveyQuestion(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  TOPIC_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.topic";
	public HealthChecker checkTopicOfDailySurveyQuestion(String topic)
	{		
	 	checkStringLengthRange(topic,1, 50,TOPIC_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  QUESTION_TYPE_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.question_type";
	public HealthChecker checkQuestionTypeIdOfDailySurveyQuestion(String questionTypeId)
	{		
	 	checkIdOfDailySurveyQuestion(questionTypeId ); 		
		
		return this;
	}	

	public static final String  OPTION_A_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.option_a";
	public HealthChecker checkOptionAOfDailySurveyQuestion(String optionA)
	{		
	 	checkStringLengthRange(optionA,0, 99,OPTION_A_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  OPTION_B_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.option_b";
	public HealthChecker checkOptionBOfDailySurveyQuestion(String optionB)
	{		
	 	checkStringLengthRange(optionB,0, 99,OPTION_B_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  OPTION_C_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.option_c";
	public HealthChecker checkOptionCOfDailySurveyQuestion(String optionC)
	{		
	 	checkStringLengthRange(optionC,0, 99,OPTION_C_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  OPTION_D_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.option_d";
	public HealthChecker checkOptionDOfDailySurveyQuestion(String optionD)
	{		
	 	checkStringLengthRange(optionD,0, 99,OPTION_D_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  CLASS_DAILY_HEALTH_SURVEY_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.class_daily_health_survey";
	public HealthChecker checkClassDailyHealthSurveyIdOfDailySurveyQuestion(String classDailyHealthSurveyId)
	{		
	 	checkIdOfDailySurveyQuestion(classDailyHealthSurveyId ); 		
		
		return this;
	}	

	public static final String  SURVEY_QUESTION_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.survey_question";
	public HealthChecker checkSurveyQuestionIdOfDailySurveyQuestion(String surveyQuestionId)
	{		
	 	checkIdOfDailySurveyQuestion(surveyQuestionId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_DAILY_SURVEY_QUESTION ="daily_survey_question.version";
	public HealthChecker checkVersionOfDailySurveyQuestion(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_DAILY_SURVEY_QUESTION ); 		
		
		return this;
	}	

	public static final String  ID_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.id";
	public HealthChecker checkIdOfStudentHealthSurvey(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_STUDENT_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  STUDENT_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.student";
	public HealthChecker checkStudentIdOfStudentHealthSurvey(String studentId)
	{		
	 	checkIdOfStudentHealthSurvey(studentId ); 		
		
		return this;
	}	

	public static final String  ANSWER_TIME_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.answer_time";
	public HealthChecker checkAnswerTimeOfStudentHealthSurvey(DateTime answerTime)
	{		
	 	checkDateTime(answerTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T23:59:59"),ANSWER_TIME_OF_STUDENT_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  SURVEY_STATUS_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.survey_status";
	public HealthChecker checkSurveyStatusIdOfStudentHealthSurvey(String surveyStatusId)
	{		
	 	checkIdOfStudentHealthSurvey(surveyStatusId ); 		
		
		return this;
	}	

	public static final String  TEACHER_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.teacher";
	public HealthChecker checkTeacherIdOfStudentHealthSurvey(String teacherId)
	{		
	 	checkIdOfStudentHealthSurvey(teacherId ); 		
		
		return this;
	}	

	public static final String  CLASS_DAILY_HEALTH_SURVEY_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.class_daily_health_survey";
	public HealthChecker checkClassDailyHealthSurveyIdOfStudentHealthSurvey(String classDailyHealthSurveyId)
	{		
	 	checkIdOfStudentHealthSurvey(classDailyHealthSurveyId ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.change_request";
	public HealthChecker checkChangeRequestIdOfStudentHealthSurvey(String changeRequestId)
	{		if(isValidIdentifier(changeRequestId)){
	 	checkIdOfStudentHealthSurvey(changeRequestId ); 		
		}
		return this;
	}	

	public static final String  VERSION_OF_STUDENT_HEALTH_SURVEY ="student_health_survey.version";
	public HealthChecker checkVersionOfStudentHealthSurvey(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_STUDENT_HEALTH_SURVEY ); 		
		
		return this;
	}	

	public static final String  ID_OF_STUDENT_DAILY_ANSWER ="student_daily_answer.id";
	public HealthChecker checkIdOfStudentDailyAnswer(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_STUDENT_DAILY_ANSWER ); 		
		
		return this;
	}	

	public static final String  STUDENT_HEALTH_SURVEY_OF_STUDENT_DAILY_ANSWER ="student_daily_answer.student_health_survey";
	public HealthChecker checkStudentHealthSurveyIdOfStudentDailyAnswer(String studentHealthSurveyId)
	{		
	 	checkIdOfStudentDailyAnswer(studentHealthSurveyId ); 		
		
		return this;
	}	

	public static final String  QUESTION_OF_STUDENT_DAILY_ANSWER ="student_daily_answer.question";
	public HealthChecker checkQuestionIdOfStudentDailyAnswer(String questionId)
	{		
	 	checkIdOfStudentDailyAnswer(questionId ); 		
		
		return this;
	}	

	public static final String  ANSWER_OF_STUDENT_DAILY_ANSWER ="student_daily_answer.answer";
	public HealthChecker checkAnswerOfStudentDailyAnswer(String answer)
	{		
	 	checkStringLengthRange(answer,1, 99,ANSWER_OF_STUDENT_DAILY_ANSWER ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_STUDENT_DAILY_ANSWER ="student_daily_answer.version";
	public HealthChecker checkVersionOfStudentDailyAnswer(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_STUDENT_DAILY_ANSWER ); 		
		
		return this;
	}	

	public static final String  ID_OF_SURVEY_STATUS ="survey_status.id";
	public HealthChecker checkIdOfSurveyStatus(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_SURVEY_STATUS ); 		
		
		return this;
	}	

	public static final String  NAME_OF_SURVEY_STATUS ="survey_status.name";
	public HealthChecker checkNameOfSurveyStatus(String name)
	{		
	 	checkStringLengthRange(name,1, 12,NAME_OF_SURVEY_STATUS ); 		
		
		return this;
	}	

	public static final String  CODE_OF_SURVEY_STATUS ="survey_status.code";
	public HealthChecker checkCodeOfSurveyStatus(String code)
	{		
	 	checkStringLengthRange(code,2, 48,CODE_OF_SURVEY_STATUS ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_SURVEY_STATUS ="survey_status.platform";
	public HealthChecker checkPlatformIdOfSurveyStatus(String platformId)
	{		
	 	checkIdOfSurveyStatus(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SURVEY_STATUS ="survey_status.version";
	public HealthChecker checkVersionOfSurveyStatus(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SURVEY_STATUS ); 		
		
		return this;
	}	

	public static final String  ID_OF_HEALTH_SURVEY_REPORT ="health_survey_report.id";
	public HealthChecker checkIdOfHealthSurveyReport(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  SURVEY_NAME_OF_HEALTH_SURVEY_REPORT ="health_survey_report.survey_name";
	public HealthChecker checkSurveyNameOfHealthSurveyReport(String surveyName)
	{		
	 	checkStringLengthRange(surveyName,6, 88,SURVEY_NAME_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  SURVEY_TIME_OF_HEALTH_SURVEY_REPORT ="health_survey_report.survey_time";
	public HealthChecker checkSurveyTimeOfHealthSurveyReport(DateTime surveyTime)
	{		
	 	checkDateTime(surveyTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T23:59:59"),SURVEY_TIME_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  TEACHER_NAME_OF_HEALTH_SURVEY_REPORT ="health_survey_report.teacher_name";
	public HealthChecker checkTeacherNameOfHealthSurveyReport(String teacherName)
	{		
	 	checkStringLengthRange(teacherName,1, 20,TEACHER_NAME_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  SCHOOL_OF_HEALTH_SURVEY_REPORT ="health_survey_report.school";
	public HealthChecker checkSchoolOfHealthSurveyReport(String school)
	{		
	 	checkStringLengthRange(school,2, 16,SCHOOL_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  SCHOOL_CLASS_OF_HEALTH_SURVEY_REPORT ="health_survey_report.school_class";
	public HealthChecker checkSchoolClassOfHealthSurveyReport(String schoolClass)
	{		
	 	checkStringLengthRange(schoolClass,1, 99,SCHOOL_CLASS_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  STUDENT_NAME_OF_HEALTH_SURVEY_REPORT ="health_survey_report.student_name";
	public HealthChecker checkStudentNameOfHealthSurveyReport(String studentName)
	{		
	 	checkStringLengthRange(studentName,1, 20,STUDENT_NAME_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  STUDENT_NUMBER_OF_HEALTH_SURVEY_REPORT ="health_survey_report.student_number";
	public HealthChecker checkStudentNumberOfHealthSurveyReport(String studentNumber)
	{		
	 	checkStringLengthRange(studentNumber,1, 3,STUDENT_NUMBER_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  GUARDIAN_NAME_OF_HEALTH_SURVEY_REPORT ="health_survey_report.guardian_name";
	public HealthChecker checkGuardianNameOfHealthSurveyReport(String guardianName)
	{		
	 	checkStringLengthRange(guardianName,1, 20,GUARDIAN_NAME_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  GUARDIAN_MOBILE_OF_HEALTH_SURVEY_REPORT ="health_survey_report.guardian_mobile";
	public HealthChecker checkGuardianMobileOfHealthSurveyReport(String guardianMobile)
	{		
	 	checkChinaMobilePhone(guardianMobile,5, 44,GUARDIAN_MOBILE_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  STUDENT_OF_HEALTH_SURVEY_REPORT ="health_survey_report.student";
	public HealthChecker checkStudentIdOfHealthSurveyReport(String studentId)
	{		
	 	checkIdOfHealthSurveyReport(studentId ); 		
		
		return this;
	}	

	public static final String  TEACHER_OF_HEALTH_SURVEY_REPORT ="health_survey_report.teacher";
	public HealthChecker checkTeacherIdOfHealthSurveyReport(String teacherId)
	{		
	 	checkIdOfHealthSurveyReport(teacherId ); 		
		
		return this;
	}	

	public static final String  SURVEY_OF_HEALTH_SURVEY_REPORT ="health_survey_report.survey";
	public HealthChecker checkSurveyIdOfHealthSurveyReport(String surveyId)
	{		
	 	checkIdOfHealthSurveyReport(surveyId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_HEALTH_SURVEY_REPORT ="health_survey_report.version";
	public HealthChecker checkVersionOfHealthSurveyReport(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_HEALTH_SURVEY_REPORT ); 		
		
		return this;
	}	

	public static final String  ID_OF_STUDENT_ANSWER ="student_answer.id";
	public HealthChecker checkIdOfStudentAnswer(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_STUDENT_ANSWER ); 		
		
		return this;
	}	

	public static final String  HEALTH_SURVEY_REPORT_OF_STUDENT_ANSWER ="student_answer.health_survey_report";
	public HealthChecker checkHealthSurveyReportIdOfStudentAnswer(String healthSurveyReportId)
	{		
	 	checkIdOfStudentAnswer(healthSurveyReportId ); 		
		
		return this;
	}	

	public static final String  DAILY_ANSWER_OF_STUDENT_ANSWER ="student_answer.daily_answer";
	public HealthChecker checkDailyAnswerIdOfStudentAnswer(String dailyAnswerId)
	{		
	 	checkIdOfStudentAnswer(dailyAnswerId ); 		
		
		return this;
	}	

	public static final String  QUESTION_TOPIC_OF_STUDENT_ANSWER ="student_answer.question_topic";
	public HealthChecker checkQuestionTopicOfStudentAnswer(String questionTopic)
	{		
	 	checkStringLengthRange(questionTopic,1, 99,QUESTION_TOPIC_OF_STUDENT_ANSWER ); 		
		
		return this;
	}	

	public static final String  ANSWER_OF_STUDENT_ANSWER ="student_answer.answer";
	public HealthChecker checkAnswerOfStudentAnswer(String answer)
	{		
	 	checkStringLengthRange(answer,1, 99,ANSWER_OF_STUDENT_ANSWER ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_STUDENT_ANSWER ="student_answer.version";
	public HealthChecker checkVersionOfStudentAnswer(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_STUDENT_ANSWER ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER ="user.id";
	public HealthChecker checkIdOfUser(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER ); 		
		
		return this;
	}	

	public static final String  NAME_OF_USER ="user.name";
	public HealthChecker checkNameOfUser(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_USER ); 		
		
		return this;
	}	

	public static final String  AVATAR_OF_USER ="user.avatar";
	public HealthChecker checkAvatarOfUser(String avatar)
	{		
	 	checkImage(avatar,0, 512,AVATAR_OF_USER ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_USER ="user.platform";
	public HealthChecker checkPlatformIdOfUser(String platformId)
	{		
	 	checkIdOfUser(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER ="user.version";
	public HealthChecker checkVersionOfUser(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER ); 		
		
		return this;
	}	

	public static final String  ID_OF_WECHAT_LOGIN_INFO ="wechat_login_info.id";
	public HealthChecker checkIdOfWechatLoginInfo(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_WECHAT_LOGIN_INFO ); 		
		
		return this;
	}	

	public static final String  USER_OF_WECHAT_LOGIN_INFO ="wechat_login_info.user";
	public HealthChecker checkUserIdOfWechatLoginInfo(String userId)
	{		
	 	checkIdOfWechatLoginInfo(userId ); 		
		
		return this;
	}	

	public static final String  APP_ID_OF_WECHAT_LOGIN_INFO ="wechat_login_info.app_id";
	public HealthChecker checkAppIdOfWechatLoginInfo(String appId)
	{		
	 	checkStringLengthRange(appId,0, 100,APP_ID_OF_WECHAT_LOGIN_INFO ); 		
		
		return this;
	}	

	public static final String  OPEN_ID_OF_WECHAT_LOGIN_INFO ="wechat_login_info.open_id";
	public HealthChecker checkOpenIdOfWechatLoginInfo(String openId)
	{		
	 	checkStringLengthRange(openId,1, 100,OPEN_ID_OF_WECHAT_LOGIN_INFO ); 		
		
		return this;
	}	

	public static final String  SESSION_KEY_OF_WECHAT_LOGIN_INFO ="wechat_login_info.session_key";
	public HealthChecker checkSessionKeyOfWechatLoginInfo(String sessionKey)
	{		
	 	checkStringLengthRange(sessionKey,1, 200,SESSION_KEY_OF_WECHAT_LOGIN_INFO ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_WECHAT_LOGIN_INFO ="wechat_login_info.version";
	public HealthChecker checkVersionOfWechatLoginInfo(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_WECHAT_LOGIN_INFO ); 		
		
		return this;
	}	

	public static final String  ID_OF_CHANGE_REQUEST ="change_request.id";
	public HealthChecker checkIdOfChangeRequest(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CHANGE_REQUEST ="change_request.name";
	public HealthChecker checkNameOfChangeRequest(String name)
	{		
	 	checkStringLengthRange(name,1, 50,NAME_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  REQUEST_TYPE_OF_CHANGE_REQUEST ="change_request.request_type";
	public HealthChecker checkRequestTypeIdOfChangeRequest(String requestTypeId)
	{		
	 	checkIdOfChangeRequest(requestTypeId ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_CHANGE_REQUEST ="change_request.platform";
	public HealthChecker checkPlatformIdOfChangeRequest(String platformId)
	{		
	 	checkIdOfChangeRequest(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CHANGE_REQUEST ="change_request.version";
	public HealthChecker checkVersionOfChangeRequest(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  ID_OF_CHANGE_REQUEST_TYPE ="change_request_type.id";
	public HealthChecker checkIdOfChangeRequestType(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CHANGE_REQUEST_TYPE ="change_request_type.name";
	public HealthChecker checkNameOfChangeRequestType(String name)
	{		
	 	checkStringLengthRange(name,2, 16,NAME_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  CODE_OF_CHANGE_REQUEST_TYPE ="change_request_type.code";
	public HealthChecker checkCodeOfChangeRequestType(String code)
	{		
	 	checkStringLengthRange(code,4, 56,CODE_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  ICON_OF_CHANGE_REQUEST_TYPE ="change_request_type.icon";
	public HealthChecker checkIconOfChangeRequestType(String icon)
	{		
	 	checkStringLengthRange(icon,1, 16,ICON_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  DISPLAY_ORDER_OF_CHANGE_REQUEST_TYPE ="change_request_type.display_order";
	public HealthChecker checkDisplayOrderOfChangeRequestType(int displayOrder)
	{		
	 	checkIntegerRange(displayOrder,0, 4,DISPLAY_ORDER_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  BIND_TYPES_OF_CHANGE_REQUEST_TYPE ="change_request_type.bind_types";
	public HealthChecker checkBindTypesOfChangeRequestType(String bindTypes)
	{		
	 	checkLongtext(bindTypes,0, 1048576,BIND_TYPES_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  STEP_CONFIGURATION_OF_CHANGE_REQUEST_TYPE ="change_request_type.step_configuration";
	public HealthChecker checkStepConfigurationOfChangeRequestType(String stepConfiguration)
	{		
	 	checkLongtext(stepConfiguration,0, 1048576,STEP_CONFIGURATION_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_CHANGE_REQUEST_TYPE ="change_request_type.platform";
	public HealthChecker checkPlatformIdOfChangeRequestType(String platformId)
	{		
	 	checkIdOfChangeRequestType(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CHANGE_REQUEST_TYPE ="change_request_type.version";
	public HealthChecker checkVersionOfChangeRequestType(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_DOMAIN ="user_domain.id";
	public HealthChecker checkIdOfUserDomain(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  NAME_OF_USER_DOMAIN ="user_domain.name";
	public HealthChecker checkNameOfUserDomain(String name)
	{		
	 	checkStringLengthRange(name,2, 16,NAME_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_DOMAIN ="user_domain.version";
	public HealthChecker checkVersionOfUserDomain(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_WHITE_LIST ="user_white_list.id";
	public HealthChecker checkIdOfUserWhiteList(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  USER_IDENTITY_OF_USER_WHITE_LIST ="user_white_list.user_identity";
	public HealthChecker checkUserIdentityOfUserWhiteList(String userIdentity)
	{		
	 	checkStringLengthRange(userIdentity,1, 40,USER_IDENTITY_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  USER_SPECIAL_FUNCTIONS_OF_USER_WHITE_LIST ="user_white_list.user_special_functions";
	public HealthChecker checkUserSpecialFunctionsOfUserWhiteList(String userSpecialFunctions)
	{		
	 	checkStringLengthRange(userSpecialFunctions,1, 200,USER_SPECIAL_FUNCTIONS_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_USER_WHITE_LIST ="user_white_list.domain";
	public HealthChecker checkDomainIdOfUserWhiteList(String domainId)
	{		
	 	checkIdOfUserWhiteList(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_WHITE_LIST ="user_white_list.version";
	public HealthChecker checkVersionOfUserWhiteList(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  ID_OF_SEC_USER ="sec_user.id";
	public HealthChecker checkIdOfSecUser(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  LOGIN_OF_SEC_USER ="sec_user.login";
	public HealthChecker checkLoginOfSecUser(String login)
	{		
	 	checkStringLengthRange(login,0, 256,LOGIN_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  MOBILE_OF_SEC_USER ="sec_user.mobile";
	public HealthChecker checkMobileOfSecUser(String mobile)
	{		
	 	checkChinaMobilePhone(mobile,0, 11,MOBILE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  EMAIL_OF_SEC_USER ="sec_user.email";
	public HealthChecker checkEmailOfSecUser(String email)
	{		
	 	checkEmail(email,0, 256,EMAIL_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  PWD_OF_SEC_USER ="sec_user.pwd";
	public HealthChecker checkPwdOfSecUser(String pwd)
	{		
	 	checkPassword(pwd,3, 28,PWD_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  WEIXIN_OPENID_OF_SEC_USER ="sec_user.weixin_openid";
	public HealthChecker checkWeixinOpenidOfSecUser(String weixinOpenid)
	{		
	 	checkStringLengthRange(weixinOpenid,0, 128,WEIXIN_OPENID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  WEIXIN_APPID_OF_SEC_USER ="sec_user.weixin_appid";
	public HealthChecker checkWeixinAppidOfSecUser(String weixinAppid)
	{		
	 	checkStringLengthRange(weixinAppid,0, 128,WEIXIN_APPID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  ACCESS_TOKEN_OF_SEC_USER ="sec_user.access_token";
	public HealthChecker checkAccessTokenOfSecUser(String accessToken)
	{		
	 	checkStringLengthRange(accessToken,0, 128,ACCESS_TOKEN_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  VERIFICATION_CODE_OF_SEC_USER ="sec_user.verification_code";
	public HealthChecker checkVerificationCodeOfSecUser(int verificationCode)
	{		
	 	checkIntegerRange(verificationCode,0, 9999999,VERIFICATION_CODE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  VERIFICATION_CODE_EXPIRE_OF_SEC_USER ="sec_user.verification_code_expire";
	public HealthChecker checkVerificationCodeExpireOfSecUser(DateTime verificationCodeExpire)
	{		
	 	checkDateTime(verificationCodeExpire,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T09:09:09"),VERIFICATION_CODE_EXPIRE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_SEC_USER ="sec_user.last_login_time";
	public HealthChecker checkLastLoginTimeOfSecUser(DateTime lastLoginTime)
	{
		if(lastLoginTime == null) {
			return this;
		}
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T09:09:09"),LAST_LOGIN_TIME_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_SEC_USER ="sec_user.domain";
	public HealthChecker checkDomainIdOfSecUser(String domainId)
	{		
	 	checkIdOfSecUser(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SEC_USER ="sec_user.version";
	public HealthChecker checkVersionOfSecUser(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_APP ="user_app.id";
	public HealthChecker checkIdOfUserApp(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_USER_APP ="user_app.title";
	public HealthChecker checkTitleOfUserApp(String title)
	{		
	 	checkStringLengthRange(title,1, 300,TITLE_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_USER_APP ="user_app.sec_user";
	public HealthChecker checkSecUserIdOfUserApp(String secUserId)
	{		
	 	checkIdOfUserApp(secUserId ); 		
		
		return this;
	}	

	public static final String  APP_ICON_OF_USER_APP ="user_app.app_icon";
	public HealthChecker checkAppIconOfUserApp(String appIcon)
	{		
	 	checkStringLengthRange(appIcon,2, 36,APP_ICON_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  FULL_ACCESS_OF_USER_APP ="user_app.full_access";
	public HealthChecker checkFullAccessOfUserApp(boolean fullAccess)
	{		
	 	checkBooleanRange(fullAccess,0, true,FULL_ACCESS_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  PERMISSION_OF_USER_APP ="user_app.permission";
	public HealthChecker checkPermissionOfUserApp(String permission)
	{		
	 	checkStringLengthRange(permission,2, 16,PERMISSION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  OBJECT_TYPE_OF_USER_APP ="user_app.object_type";
	public HealthChecker checkObjectTypeOfUserApp(String objectType)
	{		
	 	checkStringLengthRange(objectType,1, 100,OBJECT_TYPE_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  OBJECT_ID_OF_USER_APP ="user_app.object_id";
	public HealthChecker checkObjectIdOfUserApp(String objectId)
	{		
	 	checkStringLengthRange(objectId,4, 40,OBJECT_ID_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  LOCATION_OF_USER_APP ="user_app.location";
	public HealthChecker checkLocationOfUserApp(String location)
	{		
	 	checkStringLengthRange(location,4, 48,LOCATION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_APP ="user_app.version";
	public HealthChecker checkVersionOfUserApp(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  ID_OF_QUICK_LINK ="quick_link.id";
	public HealthChecker checkIdOfQuickLink(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  NAME_OF_QUICK_LINK ="quick_link.name";
	public HealthChecker checkNameOfQuickLink(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  ICON_OF_QUICK_LINK ="quick_link.icon";
	public HealthChecker checkIconOfQuickLink(String icon)
	{		
	 	checkStringLengthRange(icon,1, 200,ICON_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  IMAGE_PATH_OF_QUICK_LINK ="quick_link.image_path";
	public HealthChecker checkImagePathOfQuickLink(String imagePath)
	{		
	 	checkImage(imagePath,0, 512,IMAGE_PATH_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  LINK_TARGET_OF_QUICK_LINK ="quick_link.link_target";
	public HealthChecker checkLinkTargetOfQuickLink(String linkTarget)
	{		
	 	checkStringLengthRange(linkTarget,1, 200,LINK_TARGET_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  APP_OF_QUICK_LINK ="quick_link.app";
	public HealthChecker checkAppIdOfQuickLink(String appId)
	{		
	 	checkIdOfQuickLink(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_QUICK_LINK ="quick_link.version";
	public HealthChecker checkVersionOfQuickLink(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  ID_OF_LIST_ACCESS ="list_access.id";
	public HealthChecker checkIdOfListAccess(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  NAME_OF_LIST_ACCESS ="list_access.name";
	public HealthChecker checkNameOfListAccess(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  INTERNAL_NAME_OF_LIST_ACCESS ="list_access.internal_name";
	public HealthChecker checkInternalNameOfListAccess(String internalName)
	{		
	 	checkStringLengthRange(internalName,1, 200,INTERNAL_NAME_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  READ_PERMISSION_OF_LIST_ACCESS ="list_access.read_permission";
	public HealthChecker checkReadPermissionOfListAccess(boolean readPermission)
	{		
	 	checkBooleanRange(readPermission,0, true,READ_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  CREATE_PERMISSION_OF_LIST_ACCESS ="list_access.create_permission";
	public HealthChecker checkCreatePermissionOfListAccess(boolean createPermission)
	{		
	 	checkBooleanRange(createPermission,0, true,CREATE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  DELETE_PERMISSION_OF_LIST_ACCESS ="list_access.delete_permission";
	public HealthChecker checkDeletePermissionOfListAccess(boolean deletePermission)
	{		
	 	checkBooleanRange(deletePermission,0, true,DELETE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  UPDATE_PERMISSION_OF_LIST_ACCESS ="list_access.update_permission";
	public HealthChecker checkUpdatePermissionOfListAccess(boolean updatePermission)
	{		
	 	checkBooleanRange(updatePermission,0, true,UPDATE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  EXECUTION_PERMISSION_OF_LIST_ACCESS ="list_access.execution_permission";
	public HealthChecker checkExecutionPermissionOfListAccess(boolean executionPermission)
	{		
	 	checkBooleanRange(executionPermission,0, true,EXECUTION_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  APP_OF_LIST_ACCESS ="list_access.app";
	public HealthChecker checkAppIdOfListAccess(String appId)
	{		
	 	checkIdOfListAccess(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LIST_ACCESS ="list_access.version";
	public HealthChecker checkVersionOfListAccess(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  ID_OF_OBJECT_ACCESS ="object_access.id";
	public HealthChecker checkIdOfObjectAccess(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  NAME_OF_OBJECT_ACCESS ="object_access.name";
	public HealthChecker checkNameOfObjectAccess(String name)
	{		
	 	checkStringLengthRange(name,2, 28,NAME_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  OBJECT_TYPE_OF_OBJECT_ACCESS ="object_access.object_type";
	public HealthChecker checkObjectTypeOfObjectAccess(String objectType)
	{		
	 	checkStringLengthRange(objectType,5, 112,OBJECT_TYPE_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST1_OF_OBJECT_ACCESS ="object_access.list1";
	public HealthChecker checkList1OfObjectAccess(String list1)
	{		
	 	checkStringLengthRange(list1,5, 80,LIST1_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST2_OF_OBJECT_ACCESS ="object_access.list2";
	public HealthChecker checkList2OfObjectAccess(String list2)
	{		
	 	checkStringLengthRange(list2,5, 80,LIST2_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST3_OF_OBJECT_ACCESS ="object_access.list3";
	public HealthChecker checkList3OfObjectAccess(String list3)
	{		
	 	checkStringLengthRange(list3,5, 80,LIST3_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST4_OF_OBJECT_ACCESS ="object_access.list4";
	public HealthChecker checkList4OfObjectAccess(String list4)
	{		
	 	checkStringLengthRange(list4,5, 80,LIST4_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST5_OF_OBJECT_ACCESS ="object_access.list5";
	public HealthChecker checkList5OfObjectAccess(String list5)
	{		
	 	checkStringLengthRange(list5,5, 80,LIST5_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST6_OF_OBJECT_ACCESS ="object_access.list6";
	public HealthChecker checkList6OfObjectAccess(String list6)
	{		
	 	checkStringLengthRange(list6,5, 80,LIST6_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST7_OF_OBJECT_ACCESS ="object_access.list7";
	public HealthChecker checkList7OfObjectAccess(String list7)
	{		
	 	checkStringLengthRange(list7,5, 80,LIST7_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST8_OF_OBJECT_ACCESS ="object_access.list8";
	public HealthChecker checkList8OfObjectAccess(String list8)
	{		
	 	checkStringLengthRange(list8,5, 80,LIST8_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  LIST9_OF_OBJECT_ACCESS ="object_access.list9";
	public HealthChecker checkList9OfObjectAccess(String list9)
	{		
	 	checkStringLengthRange(list9,5, 80,LIST9_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  APP_OF_OBJECT_ACCESS ="object_access.app";
	public HealthChecker checkAppIdOfObjectAccess(String appId)
	{		
	 	checkIdOfObjectAccess(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_OBJECT_ACCESS ="object_access.version";
	public HealthChecker checkVersionOfObjectAccess(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_OBJECT_ACCESS ); 		
		
		return this;
	}	

	public static final String  ID_OF_LOGIN_HISTORY ="login_history.id";
	public HealthChecker checkIdOfLoginHistory(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  FROM_IP_OF_LOGIN_HISTORY ="login_history.from_ip";
	public HealthChecker checkFromIpOfLoginHistory(String fromIp)
	{		
	 	checkStringLengthRange(fromIp,5, 44,FROM_IP_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_LOGIN_HISTORY ="login_history.description";
	public HealthChecker checkDescriptionOfLoginHistory(String description)
	{		
	 	checkStringLengthRange(description,2, 16,DESCRIPTION_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_LOGIN_HISTORY ="login_history.sec_user";
	public HealthChecker checkSecUserIdOfLoginHistory(String secUserId)
	{		
	 	checkIdOfLoginHistory(secUserId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LOGIN_HISTORY ="login_history.version";
	public HealthChecker checkVersionOfLoginHistory(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  ID_OF_GENERIC_FORM ="generic_form.id";
	public HealthChecker checkIdOfGenericForm(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_GENERIC_FORM ="generic_form.title";
	public HealthChecker checkTitleOfGenericForm(String title)
	{		
	 	checkStringLengthRange(title,2, 20,TITLE_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_GENERIC_FORM ="generic_form.description";
	public HealthChecker checkDescriptionOfGenericForm(String description)
	{		
	 	checkStringLengthRange(description,4, 48,DESCRIPTION_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_GENERIC_FORM ="generic_form.version";
	public HealthChecker checkVersionOfGenericForm(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_GENERIC_FORM ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_MESSAGE ="form_message.id";
	public HealthChecker checkIdOfFormMessage(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_FORM_MESSAGE ="form_message.title";
	public HealthChecker checkTitleOfFormMessage(String title)
	{		
	 	checkStringLengthRange(title,2, 24,TITLE_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_MESSAGE ="form_message.form";
	public HealthChecker checkFormIdOfFormMessage(String formId)
	{		
	 	checkIdOfFormMessage(formId ); 		
		
		return this;
	}	

	public static final String  LEVEL_OF_FORM_MESSAGE ="form_message.level";
	public HealthChecker checkLevelOfFormMessage(String level)
	{		
	 	checkStringLengthRange(level,2, 28,LEVEL_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_MESSAGE ="form_message.version";
	public HealthChecker checkVersionOfFormMessage(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_MESSAGE ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_FIELD_MESSAGE ="form_field_message.id";
	public HealthChecker checkIdOfFormFieldMessage(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_FORM_FIELD_MESSAGE ="form_field_message.title";
	public HealthChecker checkTitleOfFormFieldMessage(String title)
	{		
	 	checkStringLengthRange(title,2, 16,TITLE_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  PARAMETER_NAME_OF_FORM_FIELD_MESSAGE ="form_field_message.parameter_name";
	public HealthChecker checkParameterNameOfFormFieldMessage(String parameterName)
	{		
	 	checkStringLengthRange(parameterName,2, 16,PARAMETER_NAME_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_FIELD_MESSAGE ="form_field_message.form";
	public HealthChecker checkFormIdOfFormFieldMessage(String formId)
	{		
	 	checkIdOfFormFieldMessage(formId ); 		
		
		return this;
	}	

	public static final String  LEVEL_OF_FORM_FIELD_MESSAGE ="form_field_message.level";
	public HealthChecker checkLevelOfFormFieldMessage(String level)
	{		
	 	checkStringLengthRange(level,2, 28,LEVEL_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_FIELD_MESSAGE ="form_field_message.version";
	public HealthChecker checkVersionOfFormFieldMessage(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_FIELD_MESSAGE ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_FIELD ="form_field.id";
	public HealthChecker checkIdOfFormField(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  LABEL_OF_FORM_FIELD ="form_field.label";
	public HealthChecker checkLabelOfFormField(String label)
	{		
	 	checkStringLengthRange(label,1, 12,LABEL_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  LOCALE_KEY_OF_FORM_FIELD ="form_field.locale_key";
	public HealthChecker checkLocaleKeyOfFormField(String localeKey)
	{		
	 	checkStringLengthRange(localeKey,1, 44,LOCALE_KEY_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  PARAMETER_NAME_OF_FORM_FIELD ="form_field.parameter_name";
	public HealthChecker checkParameterNameOfFormField(String parameterName)
	{		
	 	checkStringLengthRange(parameterName,2, 16,PARAMETER_NAME_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  TYPE_OF_FORM_FIELD ="form_field.type";
	public HealthChecker checkTypeOfFormField(String type)
	{		
	 	checkStringLengthRange(type,1, 36,TYPE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_FIELD ="form_field.form";
	public HealthChecker checkFormIdOfFormField(String formId)
	{		
	 	checkIdOfFormField(formId ); 		
		
		return this;
	}	

	public static final String  PLACEHOLDER_OF_FORM_FIELD ="form_field.placeholder";
	public HealthChecker checkPlaceholderOfFormField(String placeholder)
	{		
	 	checkStringLengthRange(placeholder,4, 48,PLACEHOLDER_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  DEFAULT_VALUE_OF_FORM_FIELD ="form_field.default_value";
	public HealthChecker checkDefaultValueOfFormField(String defaultValue)
	{		
	 	checkStringLengthRange(defaultValue,1, 12,DEFAULT_VALUE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_FORM_FIELD ="form_field.description";
	public HealthChecker checkDescriptionOfFormField(String description)
	{		
	 	checkStringLengthRange(description,4, 48,DESCRIPTION_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  FIELD_GROUP_OF_FORM_FIELD ="form_field.field_group";
	public HealthChecker checkFieldGroupOfFormField(String fieldGroup)
	{		
	 	checkStringLengthRange(fieldGroup,2, 16,FIELD_GROUP_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  MINIMUM_VALUE_OF_FORM_FIELD ="form_field.minimum_value";
	public HealthChecker checkMinimumValueOfFormField(String minimumValue)
	{		
	 	checkStringLengthRange(minimumValue,4, 60,MINIMUM_VALUE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  MAXIMUM_VALUE_OF_FORM_FIELD ="form_field.maximum_value";
	public HealthChecker checkMaximumValueOfFormField(String maximumValue)
	{		
	 	checkStringLengthRange(maximumValue,5, 72,MAXIMUM_VALUE_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  REQUIRED_OF_FORM_FIELD ="form_field.required";
	public HealthChecker checkRequiredOfFormField(boolean required)
	{		
	 	checkBooleanRange(required,0, true|false,REQUIRED_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  DISABLED_OF_FORM_FIELD ="form_field.disabled";
	public HealthChecker checkDisabledOfFormField(boolean disabled)
	{		
	 	checkBooleanRange(disabled,0, true|false,DISABLED_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  CUSTOM_RENDERING_OF_FORM_FIELD ="form_field.custom_rendering";
	public HealthChecker checkCustomRenderingOfFormField(boolean customRendering)
	{		
	 	checkBooleanRange(customRendering,0, false,CUSTOM_RENDERING_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  CANDIDATE_VALUES_OF_FORM_FIELD ="form_field.candidate_values";
	public HealthChecker checkCandidateValuesOfFormField(String candidateValues)
	{
		if(candidateValues == null) {
			return this;
		}
		
	 	checkStringLengthRange(candidateValues,0, 12,CANDIDATE_VALUES_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  SUGGEST_VALUES_OF_FORM_FIELD ="form_field.suggest_values";
	public HealthChecker checkSuggestValuesOfFormField(String suggestValues)
	{
		if(suggestValues == null) {
			return this;
		}
		
	 	checkStringLengthRange(suggestValues,0, 12,SUGGEST_VALUES_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_FIELD ="form_field.version";
	public HealthChecker checkVersionOfFormField(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_FIELD ); 		
		
		return this;
	}	

	public static final String  ID_OF_FORM_ACTION ="form_action.id";
	public HealthChecker checkIdOfFormAction(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  LABEL_OF_FORM_ACTION ="form_action.label";
	public HealthChecker checkLabelOfFormAction(String label)
	{		
	 	checkStringLengthRange(label,1, 8,LABEL_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  LOCALE_KEY_OF_FORM_ACTION ="form_action.locale_key";
	public HealthChecker checkLocaleKeyOfFormAction(String localeKey)
	{		
	 	checkStringLengthRange(localeKey,2, 16,LOCALE_KEY_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  ACTION_KEY_OF_FORM_ACTION ="form_action.action_key";
	public HealthChecker checkActionKeyOfFormAction(String actionKey)
	{		
	 	checkStringLengthRange(actionKey,2, 24,ACTION_KEY_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  LEVEL_OF_FORM_ACTION ="form_action.level";
	public HealthChecker checkLevelOfFormAction(String level)
	{		
	 	checkStringLengthRange(level,3, 28,LEVEL_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  URL_OF_FORM_ACTION ="form_action.url";
	public HealthChecker checkUrlOfFormAction(String url)
	{		
	 	checkStringLengthRange(url,11, 168,URL_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  FORM_OF_FORM_ACTION ="form_action.form";
	public HealthChecker checkFormIdOfFormAction(String formId)
	{		
	 	checkIdOfFormAction(formId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_FORM_ACTION ="form_action.version";
	public HealthChecker checkVersionOfFormAction(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_FORM_ACTION ); 		
		
		return this;
	}	

	public static final String  ID_OF_CANDIDATE_CONTAINER ="candidate_container.id";
	public HealthChecker checkIdOfCandidateContainer(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CANDIDATE_CONTAINER ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CANDIDATE_CONTAINER ="candidate_container.name";
	public HealthChecker checkNameOfCandidateContainer(String name)
	{		
	 	checkStringLengthRange(name,2, 28,NAME_OF_CANDIDATE_CONTAINER ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CANDIDATE_CONTAINER ="candidate_container.version";
	public HealthChecker checkVersionOfCandidateContainer(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CANDIDATE_CONTAINER ); 		
		
		return this;
	}	

	public static final String  ID_OF_CANDIDATE_ELEMENT ="candidate_element.id";
	public HealthChecker checkIdOfCandidateElement(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CANDIDATE_ELEMENT ="candidate_element.name";
	public HealthChecker checkNameOfCandidateElement(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  TYPE_OF_CANDIDATE_ELEMENT ="candidate_element.type";
	public HealthChecker checkTypeOfCandidateElement(String type)
	{		
	 	checkStringLengthRange(type,1, 200,TYPE_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  IMAGE_OF_CANDIDATE_ELEMENT ="candidate_element.image";
	public HealthChecker checkImageOfCandidateElement(String image)
	{		
	 	checkImage(image,0, 512,IMAGE_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  CONTAINER_OF_CANDIDATE_ELEMENT ="candidate_element.container";
	public HealthChecker checkContainerIdOfCandidateElement(String containerId)
	{		
	 	checkIdOfCandidateElement(containerId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CANDIDATE_ELEMENT ="candidate_element.version";
	public HealthChecker checkVersionOfCandidateElement(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  ID_OF_WECHAT_WORKAPP_IDENTIFY ="wechat_workapp_identify.id";
	public HealthChecker checkIdOfWechatWorkappIdentify(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_WECHAT_WORKAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  CORP_ID_OF_WECHAT_WORKAPP_IDENTIFY ="wechat_workapp_identify.corp_id";
	public HealthChecker checkCorpIdOfWechatWorkappIdentify(String corpId)
	{		
	 	checkStringLengthRange(corpId,0, 100,CORP_ID_OF_WECHAT_WORKAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  USER_ID_OF_WECHAT_WORKAPP_IDENTIFY ="wechat_workapp_identify.user_id";
	public HealthChecker checkUserIdOfWechatWorkappIdentify(String userId)
	{		
	 	checkStringLengthRange(userId,1, 100,USER_ID_OF_WECHAT_WORKAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_WECHAT_WORKAPP_IDENTIFY ="wechat_workapp_identify.sec_user";
	public HealthChecker checkSecUserIdOfWechatWorkappIdentify(String secUserId)
	{		
	 	checkIdOfWechatWorkappIdentify(secUserId ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_WECHAT_WORKAPP_IDENTIFY ="wechat_workapp_identify.last_login_time";
	public HealthChecker checkLastLoginTimeOfWechatWorkappIdentify(DateTime lastLoginTime)
	{
		if(lastLoginTime == null) {
			return this;
		}
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2100-01-01T00:00:00"),LAST_LOGIN_TIME_OF_WECHAT_WORKAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_WECHAT_WORKAPP_IDENTIFY ="wechat_workapp_identify.version";
	public HealthChecker checkVersionOfWechatWorkappIdentify(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_WECHAT_WORKAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  ID_OF_WECHAT_MINIAPP_IDENTIFY ="wechat_miniapp_identify.id";
	public HealthChecker checkIdOfWechatMiniappIdentify(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_WECHAT_MINIAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  OPEN_ID_OF_WECHAT_MINIAPP_IDENTIFY ="wechat_miniapp_identify.open_id";
	public HealthChecker checkOpenIdOfWechatMiniappIdentify(String openId)
	{		
	 	checkStringLengthRange(openId,5, 128,OPEN_ID_OF_WECHAT_MINIAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  APP_ID_OF_WECHAT_MINIAPP_IDENTIFY ="wechat_miniapp_identify.app_id";
	public HealthChecker checkAppIdOfWechatMiniappIdentify(String appId)
	{		
	 	checkStringLengthRange(appId,5, 128,APP_ID_OF_WECHAT_MINIAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_WECHAT_MINIAPP_IDENTIFY ="wechat_miniapp_identify.sec_user";
	public HealthChecker checkSecUserIdOfWechatMiniappIdentify(String secUserId)
	{		
	 	checkIdOfWechatMiniappIdentify(secUserId ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_WECHAT_MINIAPP_IDENTIFY ="wechat_miniapp_identify.last_login_time";
	public HealthChecker checkLastLoginTimeOfWechatMiniappIdentify(DateTime lastLoginTime)
	{
		if(lastLoginTime == null) {
			return this;
		}
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2100-01-01T00:00:00"),LAST_LOGIN_TIME_OF_WECHAT_MINIAPP_IDENTIFY ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_WECHAT_MINIAPP_IDENTIFY ="wechat_miniapp_identify.version";
	public HealthChecker checkVersionOfWechatMiniappIdentify(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_WECHAT_MINIAPP_IDENTIFY ); 		
		
		return this;
	}	
}









