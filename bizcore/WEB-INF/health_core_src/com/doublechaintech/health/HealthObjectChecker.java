package com.doublechaintech.health;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
public class HealthObjectChecker extends HealthChecker{

	Set<BaseEntity> checkedObjectSet;
	
	protected void markAsChecked(BaseEntity baseEntity) {
		if(checkedObjectSet==null) {
			checkedObjectSet =  new HashSet<BaseEntity>();
		}
		checkedObjectSet.add(baseEntity);
		
		
	}
	
	protected boolean isChecked(BaseEntity baseEntity) {
		if(checkedObjectSet==null) {
			return false;
			
		}
		return checkedObjectSet.contains(baseEntity);
	}
	@FunctionalInterface
	public interface CheckerParameterFunction<P1> {
		HealthChecker apply(P1 valueToCheck);
	}
	@FunctionalInterface
	public interface AssignParameterFunction {
		HealthObjectChecker apply(BaseEntity targetEntity);
	}
	
	protected boolean isReferenceObject(BaseEntity target) {
		
		if(target.getId()==null) {
			return false;
		}
		if(target.getId().isEmpty()) {
			return false;
		}
		if(target.getVersion() > 0) {
			return false;
		}
		
		return true;
		
	}
	protected boolean isObjectForCreate(BaseEntity target) {
		if(target.getVersion() > 0) {
			return false;
		}
		if(target.getId()==null) {
			return true;
		}
		if(!target.getId().isEmpty()) {
			return false;
		}
		
		
		return true;
		
	}
	protected void setEntityProperty(BaseEntity targetEntity, String property, Object value) {
		if(!targetEntity.isChanged()) {
			return;
		}
		try {
			targetEntity.setPropertyOf(property, value);
		} catch (Exception e) {
			throw new IllegalArgumentException(concat("set property <",property,"> with value ",value.toString()," of ",targetEntity.toString()," failed"));
		}
		
	}
	
	public <T> HealthObjectChecker commonObjectPropertyAssign(BaseEntity target, String propertyName, AssignParameterFunction assigmentFunction) {
		assigmentFunction.apply(target);
		return this;
	}
	public <T> HealthObjectChecker commonObjectPropertyCheck(BaseEntity target, String propertyName, CheckerParameterFunction<T> checkerFunction) {
		
		
		if(!target.isChanged()) {
			return this;
		}
		
		if(isReferenceObject(target)&&!propertyName.equals("id")) {
			//this is an object reference, so all other properties except id check will be ignored
			//id will be checked in this case
			return this; //with an Id, but version is 0 regard as refencer
		}
		if(isObjectForCreate(target)&&propertyName.equals("id")) {
			// ignore check id for new object to create
			return this;
		}
		pushPosition(propertyName);
		T valueToCheck=(T)target.propertyOf(propertyName);
		checkerFunction.apply(valueToCheck);
		popPosition();
		
		return this;
	}
	public  HealthChecker commonObjectElementCheck(BaseEntity target, String propertyName, CheckerParameterFunction<BaseEntity> checkerFunction) {
		
		pushPosition(propertyName);
		checkerFunction.apply(target);
		popPosition();
		return this;
	}
	protected String wrapArrayIndex(int andIncrement) {
		return "["+andIncrement+"]";
	}
	protected String concat(String ...args) {
		
		return Arrays.asList(args).stream().collect(Collectors.joining(""));
		
	}
	// use like commonObjectPropertyCheck(changeRequestAsBaseEntity,"name",this::checkNameOfChangeRequest);

	public HealthObjectChecker checkAndFixPlatform(BaseEntity platformAsBaseEntity){

		if( isChecked(platformAsBaseEntity) ){
			return this;
		}
		markAsChecked(platformAsBaseEntity);
		commonObjectPropertyCheck(platformAsBaseEntity,"id",this::checkIdOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"name",this::checkNameOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"description",this::checkDescriptionOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"version",this::checkVersionOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"provinceList",this::checkProvinceListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"cityList",this::checkCityListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"districtList",this::checkDistrictListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"teacherList",this::checkTeacherListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"studentList",this::checkStudentListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"questionList",this::checkQuestionListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"questionTypeList",this::checkQuestionTypeListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"surveyStatusList",this::checkSurveyStatusListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"userList",this::checkUserListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"changeRequestList",this::checkChangeRequestListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"changeRequestTypeList",this::checkChangeRequestTypeListOfPlatform);
		return this;

	}

	public HealthObjectChecker checkAndFixProvince(BaseEntity provinceAsBaseEntity){

		if( isChecked(provinceAsBaseEntity) ){
			return this;
		}
		markAsChecked(provinceAsBaseEntity);
		commonObjectPropertyCheck(provinceAsBaseEntity,"id",this::checkIdOfProvince);
		commonObjectPropertyCheck(provinceAsBaseEntity,"name",this::checkNameOfProvince);
		commonObjectPropertyCheck(provinceAsBaseEntity,"platform",this::checkPlatformOfProvince);
		commonObjectPropertyAssign(provinceAsBaseEntity,"createTime",this::assignCreateTimeOfProvince);
		commonObjectPropertyCheck(provinceAsBaseEntity,"version",this::checkVersionOfProvince);
		commonObjectPropertyCheck(provinceAsBaseEntity,"cityList",this::checkCityListOfProvince);
		commonObjectPropertyCheck(provinceAsBaseEntity,"locationList",this::checkLocationListOfProvince);
		return this;

	}

	public HealthObjectChecker checkAndFixCity(BaseEntity cityAsBaseEntity){

		if( isChecked(cityAsBaseEntity) ){
			return this;
		}
		markAsChecked(cityAsBaseEntity);
		commonObjectPropertyCheck(cityAsBaseEntity,"id",this::checkIdOfCity);
		commonObjectPropertyCheck(cityAsBaseEntity,"name",this::checkNameOfCity);
		commonObjectPropertyCheck(cityAsBaseEntity,"province",this::checkProvinceOfCity);
		commonObjectPropertyCheck(cityAsBaseEntity,"platform",this::checkPlatformOfCity);
		commonObjectPropertyAssign(cityAsBaseEntity,"createTime",this::assignCreateTimeOfCity);
		commonObjectPropertyCheck(cityAsBaseEntity,"version",this::checkVersionOfCity);
		commonObjectPropertyCheck(cityAsBaseEntity,"districtList",this::checkDistrictListOfCity);
		return this;

	}

	public HealthObjectChecker checkAndFixDistrict(BaseEntity districtAsBaseEntity){

		if( isChecked(districtAsBaseEntity) ){
			return this;
		}
		markAsChecked(districtAsBaseEntity);
		commonObjectPropertyCheck(districtAsBaseEntity,"id",this::checkIdOfDistrict);
		commonObjectPropertyCheck(districtAsBaseEntity,"name",this::checkNameOfDistrict);
		commonObjectPropertyCheck(districtAsBaseEntity,"city",this::checkCityOfDistrict);
		commonObjectPropertyCheck(districtAsBaseEntity,"platform",this::checkPlatformOfDistrict);
		commonObjectPropertyAssign(districtAsBaseEntity,"createTime",this::assignCreateTimeOfDistrict);
		commonObjectPropertyCheck(districtAsBaseEntity,"version",this::checkVersionOfDistrict);
		commonObjectPropertyCheck(districtAsBaseEntity,"locationList",this::checkLocationListOfDistrict);
		return this;

	}

	public HealthObjectChecker checkAndFixLocation(BaseEntity locationAsBaseEntity){

		if( isChecked(locationAsBaseEntity) ){
			return this;
		}
		markAsChecked(locationAsBaseEntity);
		commonObjectPropertyCheck(locationAsBaseEntity,"id",this::checkIdOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"name",this::checkNameOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"address",this::checkAddressOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"district",this::checkDistrictOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"province",this::checkProvinceOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"latitude",this::checkLatitudeOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"longitude",this::checkLongitudeOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"version",this::checkVersionOfLocation);
		commonObjectPropertyCheck(locationAsBaseEntity,"studentList",this::checkStudentListOfLocation);
		return this;

	}

	public HealthObjectChecker checkAndFixTeacher(BaseEntity teacherAsBaseEntity){

		if( isChecked(teacherAsBaseEntity) ){
			return this;
		}
		markAsChecked(teacherAsBaseEntity);
		commonObjectPropertyCheck(teacherAsBaseEntity,"id",this::checkIdOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"name",this::checkNameOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"mobile",this::checkMobileOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"school",this::checkSchoolOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"schoolClass",this::checkSchoolClassOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"classSize",this::checkClassSizeOfTeacher);
		commonObjectPropertyAssign(teacherAsBaseEntity,"createTime",this::assignCreateTimeOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"platform",this::checkPlatformOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"user",this::checkUserOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"changeRequest",this::checkChangeRequestOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"version",this::checkVersionOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"classDailyHealthSurveyList",this::checkClassDailyHealthSurveyListOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"studentHealthSurveyList",this::checkStudentHealthSurveyListOfTeacher);
		commonObjectPropertyCheck(teacherAsBaseEntity,"healthSurveyReportList",this::checkHealthSurveyReportListOfTeacher);
		return this;

	}

	public HealthObjectChecker checkAndFixStudent(BaseEntity studentAsBaseEntity){

		if( isChecked(studentAsBaseEntity) ){
			return this;
		}
		markAsChecked(studentAsBaseEntity);
		commonObjectPropertyCheck(studentAsBaseEntity,"id",this::checkIdOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"studentName",this::checkStudentNameOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"studentNumber",this::checkStudentNumberOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"studentAvatar",this::checkStudentAvatarOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"guardianName",this::checkGuardianNameOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"guardianMobile",this::checkGuardianMobileOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"address",this::checkAddressOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"user",this::checkUserOfStudent);
		commonObjectPropertyAssign(studentAsBaseEntity,"createTime",this::assignCreateTimeOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"platform",this::checkPlatformOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"version",this::checkVersionOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"studentHealthSurveyList",this::checkStudentHealthSurveyListOfStudent);
		commonObjectPropertyCheck(studentAsBaseEntity,"healthSurveyReportList",this::checkHealthSurveyReportListOfStudent);
		return this;

	}

	public HealthObjectChecker checkAndFixQuestion(BaseEntity questionAsBaseEntity){

		if( isChecked(questionAsBaseEntity) ){
			return this;
		}
		markAsChecked(questionAsBaseEntity);
		commonObjectPropertyCheck(questionAsBaseEntity,"id",this::checkIdOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"topic",this::checkTopicOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"questionType",this::checkQuestionTypeOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"optionA",this::checkOptionAOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"optionB",this::checkOptionBOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"optionC",this::checkOptionCOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"optionD",this::checkOptionDOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"platform",this::checkPlatformOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"creator",this::checkCreatorOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"cq",this::checkCqOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"version",this::checkVersionOfQuestion);
		commonObjectPropertyCheck(questionAsBaseEntity,"dailySurveyQuestionList",this::checkDailySurveyQuestionListOfQuestion);
		return this;

	}

	public HealthObjectChecker checkAndFixQuestionType(BaseEntity questionTypeAsBaseEntity){

		if( isChecked(questionTypeAsBaseEntity) ){
			return this;
		}
		markAsChecked(questionTypeAsBaseEntity);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"id",this::checkIdOfQuestionType);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"name",this::checkNameOfQuestionType);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"code",this::checkCodeOfQuestionType);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"platform",this::checkPlatformOfQuestionType);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"version",this::checkVersionOfQuestionType);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"questionList",this::checkQuestionListOfQuestionType);
		commonObjectPropertyCheck(questionTypeAsBaseEntity,"dailySurveyQuestionList",this::checkDailySurveyQuestionListOfQuestionType);
		return this;

	}

	public HealthObjectChecker checkAndFixClassDailyHealthSurvey(BaseEntity classDailyHealthSurveyAsBaseEntity){

		if( isChecked(classDailyHealthSurveyAsBaseEntity) ){
			return this;
		}
		markAsChecked(classDailyHealthSurveyAsBaseEntity);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"id",this::checkIdOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"name",this::checkNameOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"teacher",this::checkTeacherOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"surveyTime",this::checkSurveyTimeOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"creator",this::checkCreatorOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"downloadUrl",this::checkDownloadUrlOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"changeRequest",this::checkChangeRequestOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"version",this::checkVersionOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"dailySurveyQuestionList",this::checkDailySurveyQuestionListOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"studentHealthSurveyList",this::checkStudentHealthSurveyListOfClassDailyHealthSurvey);
		commonObjectPropertyCheck(classDailyHealthSurveyAsBaseEntity,"healthSurveyReportList",this::checkHealthSurveyReportListOfClassDailyHealthSurvey);
		return this;

	}

	public HealthObjectChecker checkAndFixDailySurveyQuestion(BaseEntity dailySurveyQuestionAsBaseEntity){

		if( isChecked(dailySurveyQuestionAsBaseEntity) ){
			return this;
		}
		markAsChecked(dailySurveyQuestionAsBaseEntity);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"id",this::checkIdOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"topic",this::checkTopicOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"questionType",this::checkQuestionTypeOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"optionA",this::checkOptionAOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"optionB",this::checkOptionBOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"optionC",this::checkOptionCOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"optionD",this::checkOptionDOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"classDailyHealthSurvey",this::checkClassDailyHealthSurveyOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"surveyQuestion",this::checkSurveyQuestionOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"version",this::checkVersionOfDailySurveyQuestion);
		commonObjectPropertyCheck(dailySurveyQuestionAsBaseEntity,"studentDailyAnswerList",this::checkStudentDailyAnswerListOfDailySurveyQuestion);
		return this;

	}

	public HealthObjectChecker checkAndFixStudentHealthSurvey(BaseEntity studentHealthSurveyAsBaseEntity){

		if( isChecked(studentHealthSurveyAsBaseEntity) ){
			return this;
		}
		markAsChecked(studentHealthSurveyAsBaseEntity);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"id",this::checkIdOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"student",this::checkStudentOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"answerTime",this::checkAnswerTimeOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"surveyStatus",this::checkSurveyStatusOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"teacher",this::checkTeacherOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"classDailyHealthSurvey",this::checkClassDailyHealthSurveyOfStudentHealthSurvey);
		commonObjectPropertyAssign(studentHealthSurveyAsBaseEntity,"createTime",this::assignCreateTimeOfStudentHealthSurvey);
		commonObjectPropertyAssign(studentHealthSurveyAsBaseEntity,"lastUpdateTime",this::assignLastUpdateTimeOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"changeRequest",this::checkChangeRequestOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"version",this::checkVersionOfStudentHealthSurvey);
		commonObjectPropertyCheck(studentHealthSurveyAsBaseEntity,"studentDailyAnswerList",this::checkStudentDailyAnswerListOfStudentHealthSurvey);
		return this;

	}

	public HealthObjectChecker checkAndFixStudentDailyAnswer(BaseEntity studentDailyAnswerAsBaseEntity){

		if( isChecked(studentDailyAnswerAsBaseEntity) ){
			return this;
		}
		markAsChecked(studentDailyAnswerAsBaseEntity);
		commonObjectPropertyCheck(studentDailyAnswerAsBaseEntity,"id",this::checkIdOfStudentDailyAnswer);
		commonObjectPropertyCheck(studentDailyAnswerAsBaseEntity,"studentHealthSurvey",this::checkStudentHealthSurveyOfStudentDailyAnswer);
		commonObjectPropertyCheck(studentDailyAnswerAsBaseEntity,"question",this::checkQuestionOfStudentDailyAnswer);
		commonObjectPropertyCheck(studentDailyAnswerAsBaseEntity,"answer",this::checkAnswerOfStudentDailyAnswer);
		commonObjectPropertyAssign(studentDailyAnswerAsBaseEntity,"createTime",this::assignCreateTimeOfStudentDailyAnswer);
		commonObjectPropertyAssign(studentDailyAnswerAsBaseEntity,"lastUpdateTime",this::assignLastUpdateTimeOfStudentDailyAnswer);
		commonObjectPropertyCheck(studentDailyAnswerAsBaseEntity,"version",this::checkVersionOfStudentDailyAnswer);
		commonObjectPropertyCheck(studentDailyAnswerAsBaseEntity,"studentAnswerList",this::checkStudentAnswerListOfStudentDailyAnswer);
		return this;

	}

	public HealthObjectChecker checkAndFixSurveyStatus(BaseEntity surveyStatusAsBaseEntity){

		if( isChecked(surveyStatusAsBaseEntity) ){
			return this;
		}
		markAsChecked(surveyStatusAsBaseEntity);
		commonObjectPropertyCheck(surveyStatusAsBaseEntity,"id",this::checkIdOfSurveyStatus);
		commonObjectPropertyCheck(surveyStatusAsBaseEntity,"name",this::checkNameOfSurveyStatus);
		commonObjectPropertyCheck(surveyStatusAsBaseEntity,"code",this::checkCodeOfSurveyStatus);
		commonObjectPropertyCheck(surveyStatusAsBaseEntity,"platform",this::checkPlatformOfSurveyStatus);
		commonObjectPropertyCheck(surveyStatusAsBaseEntity,"version",this::checkVersionOfSurveyStatus);
		commonObjectPropertyCheck(surveyStatusAsBaseEntity,"studentHealthSurveyList",this::checkStudentHealthSurveyListOfSurveyStatus);
		return this;

	}

	public HealthObjectChecker checkAndFixHealthSurveyReport(BaseEntity healthSurveyReportAsBaseEntity){

		if( isChecked(healthSurveyReportAsBaseEntity) ){
			return this;
		}
		markAsChecked(healthSurveyReportAsBaseEntity);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"id",this::checkIdOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"surveyName",this::checkSurveyNameOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"surveyTime",this::checkSurveyTimeOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"teacherName",this::checkTeacherNameOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"school",this::checkSchoolOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"schoolClass",this::checkSchoolClassOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"studentName",this::checkStudentNameOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"studentNumber",this::checkStudentNumberOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"guardianName",this::checkGuardianNameOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"guardianMobile",this::checkGuardianMobileOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"student",this::checkStudentOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"teacher",this::checkTeacherOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"survey",this::checkSurveyOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"version",this::checkVersionOfHealthSurveyReport);
		commonObjectPropertyCheck(healthSurveyReportAsBaseEntity,"studentAnswerList",this::checkStudentAnswerListOfHealthSurveyReport);
		return this;

	}

	public HealthObjectChecker checkAndFixStudentAnswer(BaseEntity studentAnswerAsBaseEntity){

		if( isChecked(studentAnswerAsBaseEntity) ){
			return this;
		}
		markAsChecked(studentAnswerAsBaseEntity);
		commonObjectPropertyCheck(studentAnswerAsBaseEntity,"id",this::checkIdOfStudentAnswer);
		commonObjectPropertyCheck(studentAnswerAsBaseEntity,"healthSurveyReport",this::checkHealthSurveyReportOfStudentAnswer);
		commonObjectPropertyCheck(studentAnswerAsBaseEntity,"dailyAnswer",this::checkDailyAnswerOfStudentAnswer);
		commonObjectPropertyCheck(studentAnswerAsBaseEntity,"questionTopic",this::checkQuestionTopicOfStudentAnswer);
		commonObjectPropertyCheck(studentAnswerAsBaseEntity,"answer",this::checkAnswerOfStudentAnswer);
		commonObjectPropertyCheck(studentAnswerAsBaseEntity,"version",this::checkVersionOfStudentAnswer);
		return this;

	}

	public HealthObjectChecker checkAndFixUser(BaseEntity userAsBaseEntity){

		if( isChecked(userAsBaseEntity) ){
			return this;
		}
		markAsChecked(userAsBaseEntity);
		commonObjectPropertyCheck(userAsBaseEntity,"id",this::checkIdOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"name",this::checkNameOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"avatar",this::checkAvatarOfUser);
		commonObjectPropertyAssign(userAsBaseEntity,"createTime",this::assignCreateTimeOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"platform",this::checkPlatformOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"version",this::checkVersionOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"teacherList",this::checkTeacherListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"studentList",this::checkStudentListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"questionList",this::checkQuestionListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"classDailyHealthSurveyList",this::checkClassDailyHealthSurveyListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"wechatLoginInfoList",this::checkWechatLoginInfoListOfUser);
		return this;

	}

	public HealthObjectChecker checkAndFixWechatLoginInfo(BaseEntity wechatLoginInfoAsBaseEntity){

		if( isChecked(wechatLoginInfoAsBaseEntity) ){
			return this;
		}
		markAsChecked(wechatLoginInfoAsBaseEntity);
		commonObjectPropertyCheck(wechatLoginInfoAsBaseEntity,"id",this::checkIdOfWechatLoginInfo);
		commonObjectPropertyCheck(wechatLoginInfoAsBaseEntity,"user",this::checkUserOfWechatLoginInfo);
		commonObjectPropertyCheck(wechatLoginInfoAsBaseEntity,"appId",this::checkAppIdOfWechatLoginInfo);
		commonObjectPropertyCheck(wechatLoginInfoAsBaseEntity,"openId",this::checkOpenIdOfWechatLoginInfo);
		commonObjectPropertyCheck(wechatLoginInfoAsBaseEntity,"sessionKey",this::checkSessionKeyOfWechatLoginInfo);
		commonObjectPropertyAssign(wechatLoginInfoAsBaseEntity,"lastUpdateTime",this::assignLastUpdateTimeOfWechatLoginInfo);
		commonObjectPropertyCheck(wechatLoginInfoAsBaseEntity,"version",this::checkVersionOfWechatLoginInfo);
		return this;

	}

	public HealthObjectChecker checkAndFixChangeRequest(BaseEntity changeRequestAsBaseEntity){

		if( isChecked(changeRequestAsBaseEntity) ){
			return this;
		}
		markAsChecked(changeRequestAsBaseEntity);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"id",this::checkIdOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"name",this::checkNameOfChangeRequest);
		commonObjectPropertyAssign(changeRequestAsBaseEntity,"createTime",this::assignCreateTimeOfChangeRequest);
		commonObjectPropertyAssign(changeRequestAsBaseEntity,"remoteIp",this::assignRemoteIpOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"requestType",this::checkRequestTypeOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"platform",this::checkPlatformOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"version",this::checkVersionOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"teacherList",this::checkTeacherListOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"questionList",this::checkQuestionListOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"classDailyHealthSurveyList",this::checkClassDailyHealthSurveyListOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"studentHealthSurveyList",this::checkStudentHealthSurveyListOfChangeRequest);
		return this;

	}

	public HealthObjectChecker checkAndFixChangeRequestType(BaseEntity changeRequestTypeAsBaseEntity){

		if( isChecked(changeRequestTypeAsBaseEntity) ){
			return this;
		}
		markAsChecked(changeRequestTypeAsBaseEntity);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"id",this::checkIdOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"name",this::checkNameOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"code",this::checkCodeOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"icon",this::checkIconOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"displayOrder",this::checkDisplayOrderOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"bindTypes",this::checkBindTypesOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"stepConfiguration",this::checkStepConfigurationOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"platform",this::checkPlatformOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"version",this::checkVersionOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"changeRequestList",this::checkChangeRequestListOfChangeRequestType);
		return this;

	}

	public HealthObjectChecker checkAndFixUserDomain(BaseEntity userDomainAsBaseEntity){

		if( isChecked(userDomainAsBaseEntity) ){
			return this;
		}
		markAsChecked(userDomainAsBaseEntity);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"id",this::checkIdOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"name",this::checkNameOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"version",this::checkVersionOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"userWhiteListList",this::checkUserWhiteListListOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"secUserList",this::checkSecUserListOfUserDomain);
		return this;

	}

	public HealthObjectChecker checkAndFixUserWhiteList(BaseEntity userWhiteListAsBaseEntity){

		if( isChecked(userWhiteListAsBaseEntity) ){
			return this;
		}
		markAsChecked(userWhiteListAsBaseEntity);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"id",this::checkIdOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"userIdentity",this::checkUserIdentityOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"userSpecialFunctions",this::checkUserSpecialFunctionsOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"domain",this::checkDomainOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"version",this::checkVersionOfUserWhiteList);
		return this;

	}

	public HealthObjectChecker checkAndFixSecUser(BaseEntity secUserAsBaseEntity){

		if( isChecked(secUserAsBaseEntity) ){
			return this;
		}
		markAsChecked(secUserAsBaseEntity);
		commonObjectPropertyCheck(secUserAsBaseEntity,"id",this::checkIdOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"login",this::checkLoginOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"mobile",this::checkMobileOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"email",this::checkEmailOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"pwd",this::checkPwdOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"weixinOpenid",this::checkWeixinOpenidOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"weixinAppid",this::checkWeixinAppidOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"accessToken",this::checkAccessTokenOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"verificationCode",this::checkVerificationCodeOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"verificationCodeExpire",this::checkVerificationCodeExpireOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"domain",this::checkDomainOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"version",this::checkVersionOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"userAppList",this::checkUserAppListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"loginHistoryList",this::checkLoginHistoryListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"wechatWorkappIdentifyList",this::checkWechatWorkappIdentifyListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"wechatMiniappIdentifyList",this::checkWechatMiniappIdentifyListOfSecUser);
		return this;

	}

	public HealthObjectChecker checkAndFixUserApp(BaseEntity userAppAsBaseEntity){

		if( isChecked(userAppAsBaseEntity) ){
			return this;
		}
		markAsChecked(userAppAsBaseEntity);
		commonObjectPropertyCheck(userAppAsBaseEntity,"id",this::checkIdOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"title",this::checkTitleOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"secUser",this::checkSecUserOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"appIcon",this::checkAppIconOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"fullAccess",this::checkFullAccessOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"permission",this::checkPermissionOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectType",this::checkObjectTypeOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectId",this::checkObjectIdOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"location",this::checkLocationOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"version",this::checkVersionOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"quickLinkList",this::checkQuickLinkListOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"listAccessList",this::checkListAccessListOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectAccessList",this::checkObjectAccessListOfUserApp);
		return this;

	}

	public HealthObjectChecker checkAndFixQuickLink(BaseEntity quickLinkAsBaseEntity){

		if( isChecked(quickLinkAsBaseEntity) ){
			return this;
		}
		markAsChecked(quickLinkAsBaseEntity);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"id",this::checkIdOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"name",this::checkNameOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"icon",this::checkIconOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"imagePath",this::checkImagePathOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"linkTarget",this::checkLinkTargetOfQuickLink);
		commonObjectPropertyAssign(quickLinkAsBaseEntity,"createTime",this::assignCreateTimeOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"app",this::checkAppOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"version",this::checkVersionOfQuickLink);
		return this;

	}

	public HealthObjectChecker checkAndFixListAccess(BaseEntity listAccessAsBaseEntity){

		if( isChecked(listAccessAsBaseEntity) ){
			return this;
		}
		markAsChecked(listAccessAsBaseEntity);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"id",this::checkIdOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"name",this::checkNameOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"internalName",this::checkInternalNameOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"readPermission",this::checkReadPermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"createPermission",this::checkCreatePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"deletePermission",this::checkDeletePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"updatePermission",this::checkUpdatePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"executionPermission",this::checkExecutionPermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"app",this::checkAppOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"version",this::checkVersionOfListAccess);
		return this;

	}

	public HealthObjectChecker checkAndFixObjectAccess(BaseEntity objectAccessAsBaseEntity){

		if( isChecked(objectAccessAsBaseEntity) ){
			return this;
		}
		markAsChecked(objectAccessAsBaseEntity);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"id",this::checkIdOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"name",this::checkNameOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"objectType",this::checkObjectTypeOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list1",this::checkList1OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list2",this::checkList2OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list3",this::checkList3OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list4",this::checkList4OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list5",this::checkList5OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list6",this::checkList6OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list7",this::checkList7OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list8",this::checkList8OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"list9",this::checkList9OfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"app",this::checkAppOfObjectAccess);
		commonObjectPropertyCheck(objectAccessAsBaseEntity,"version",this::checkVersionOfObjectAccess);
		return this;

	}

	public HealthObjectChecker checkAndFixLoginHistory(BaseEntity loginHistoryAsBaseEntity){

		if( isChecked(loginHistoryAsBaseEntity) ){
			return this;
		}
		markAsChecked(loginHistoryAsBaseEntity);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"id",this::checkIdOfLoginHistory);
		commonObjectPropertyAssign(loginHistoryAsBaseEntity,"loginTime",this::assignLoginTimeOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"fromIp",this::checkFromIpOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"description",this::checkDescriptionOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"secUser",this::checkSecUserOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"version",this::checkVersionOfLoginHistory);
		return this;

	}

	public HealthObjectChecker checkAndFixGenericForm(BaseEntity genericFormAsBaseEntity){

		if( isChecked(genericFormAsBaseEntity) ){
			return this;
		}
		markAsChecked(genericFormAsBaseEntity);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"id",this::checkIdOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"title",this::checkTitleOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"description",this::checkDescriptionOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"version",this::checkVersionOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formMessageList",this::checkFormMessageListOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formFieldMessageList",this::checkFormFieldMessageListOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formFieldList",this::checkFormFieldListOfGenericForm);
		commonObjectPropertyCheck(genericFormAsBaseEntity,"formActionList",this::checkFormActionListOfGenericForm);
		return this;

	}

	public HealthObjectChecker checkAndFixFormMessage(BaseEntity formMessageAsBaseEntity){

		if( isChecked(formMessageAsBaseEntity) ){
			return this;
		}
		markAsChecked(formMessageAsBaseEntity);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"id",this::checkIdOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"title",this::checkTitleOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"form",this::checkFormOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"level",this::checkLevelOfFormMessage);
		commonObjectPropertyCheck(formMessageAsBaseEntity,"version",this::checkVersionOfFormMessage);
		return this;

	}

	public HealthObjectChecker checkAndFixFormFieldMessage(BaseEntity formFieldMessageAsBaseEntity){

		if( isChecked(formFieldMessageAsBaseEntity) ){
			return this;
		}
		markAsChecked(formFieldMessageAsBaseEntity);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"id",this::checkIdOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"title",this::checkTitleOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"parameterName",this::checkParameterNameOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"form",this::checkFormOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"level",this::checkLevelOfFormFieldMessage);
		commonObjectPropertyCheck(formFieldMessageAsBaseEntity,"version",this::checkVersionOfFormFieldMessage);
		return this;

	}

	public HealthObjectChecker checkAndFixFormField(BaseEntity formFieldAsBaseEntity){

		if( isChecked(formFieldAsBaseEntity) ){
			return this;
		}
		markAsChecked(formFieldAsBaseEntity);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"id",this::checkIdOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"label",this::checkLabelOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"localeKey",this::checkLocaleKeyOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"parameterName",this::checkParameterNameOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"type",this::checkTypeOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"form",this::checkFormOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"placeholder",this::checkPlaceholderOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"defaultValue",this::checkDefaultValueOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"description",this::checkDescriptionOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"fieldGroup",this::checkFieldGroupOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"minimumValue",this::checkMinimumValueOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"maximumValue",this::checkMaximumValueOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"required",this::checkRequiredOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"disabled",this::checkDisabledOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"customRendering",this::checkCustomRenderingOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"candidateValues",this::checkCandidateValuesOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"suggestValues",this::checkSuggestValuesOfFormField);
		commonObjectPropertyCheck(formFieldAsBaseEntity,"version",this::checkVersionOfFormField);
		return this;

	}

	public HealthObjectChecker checkAndFixFormAction(BaseEntity formActionAsBaseEntity){

		if( isChecked(formActionAsBaseEntity) ){
			return this;
		}
		markAsChecked(formActionAsBaseEntity);
		commonObjectPropertyCheck(formActionAsBaseEntity,"id",this::checkIdOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"label",this::checkLabelOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"localeKey",this::checkLocaleKeyOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"actionKey",this::checkActionKeyOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"level",this::checkLevelOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"url",this::checkUrlOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"form",this::checkFormOfFormAction);
		commonObjectPropertyCheck(formActionAsBaseEntity,"version",this::checkVersionOfFormAction);
		return this;

	}

	public HealthObjectChecker checkAndFixCandidateContainer(BaseEntity candidateContainerAsBaseEntity){

		if( isChecked(candidateContainerAsBaseEntity) ){
			return this;
		}
		markAsChecked(candidateContainerAsBaseEntity);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"id",this::checkIdOfCandidateContainer);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"name",this::checkNameOfCandidateContainer);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"version",this::checkVersionOfCandidateContainer);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"candidateElementList",this::checkCandidateElementListOfCandidateContainer);
		return this;

	}

	public HealthObjectChecker checkAndFixCandidateElement(BaseEntity candidateElementAsBaseEntity){

		if( isChecked(candidateElementAsBaseEntity) ){
			return this;
		}
		markAsChecked(candidateElementAsBaseEntity);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"id",this::checkIdOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"name",this::checkNameOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"type",this::checkTypeOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"image",this::checkImageOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"container",this::checkContainerOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"version",this::checkVersionOfCandidateElement);
		return this;

	}

	public HealthObjectChecker checkAndFixWechatWorkappIdentify(BaseEntity wechatWorkappIdentifyAsBaseEntity){

		if( isChecked(wechatWorkappIdentifyAsBaseEntity) ){
			return this;
		}
		markAsChecked(wechatWorkappIdentifyAsBaseEntity);
		commonObjectPropertyCheck(wechatWorkappIdentifyAsBaseEntity,"id",this::checkIdOfWechatWorkappIdentify);
		commonObjectPropertyCheck(wechatWorkappIdentifyAsBaseEntity,"corpId",this::checkCorpIdOfWechatWorkappIdentify);
		commonObjectPropertyCheck(wechatWorkappIdentifyAsBaseEntity,"userId",this::checkUserIdOfWechatWorkappIdentify);
		commonObjectPropertyCheck(wechatWorkappIdentifyAsBaseEntity,"secUser",this::checkSecUserOfWechatWorkappIdentify);
		commonObjectPropertyAssign(wechatWorkappIdentifyAsBaseEntity,"createTime",this::assignCreateTimeOfWechatWorkappIdentify);
		commonObjectPropertyCheck(wechatWorkappIdentifyAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfWechatWorkappIdentify);
		commonObjectPropertyCheck(wechatWorkappIdentifyAsBaseEntity,"version",this::checkVersionOfWechatWorkappIdentify);
		return this;

	}

	public HealthObjectChecker checkAndFixWechatMiniappIdentify(BaseEntity wechatMiniappIdentifyAsBaseEntity){

		if( isChecked(wechatMiniappIdentifyAsBaseEntity) ){
			return this;
		}
		markAsChecked(wechatMiniappIdentifyAsBaseEntity);
		commonObjectPropertyCheck(wechatMiniappIdentifyAsBaseEntity,"id",this::checkIdOfWechatMiniappIdentify);
		commonObjectPropertyCheck(wechatMiniappIdentifyAsBaseEntity,"openId",this::checkOpenIdOfWechatMiniappIdentify);
		commonObjectPropertyCheck(wechatMiniappIdentifyAsBaseEntity,"appId",this::checkAppIdOfWechatMiniappIdentify);
		commonObjectPropertyCheck(wechatMiniappIdentifyAsBaseEntity,"secUser",this::checkSecUserOfWechatMiniappIdentify);
		commonObjectPropertyAssign(wechatMiniappIdentifyAsBaseEntity,"createTime",this::assignCreateTimeOfWechatMiniappIdentify);
		commonObjectPropertyCheck(wechatMiniappIdentifyAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfWechatMiniappIdentify);
		commonObjectPropertyCheck(wechatMiniappIdentifyAsBaseEntity,"version",this::checkVersionOfWechatMiniappIdentify);
		return this;

	}


	public HealthObjectChecker checkProvinceListOfPlatform(List<BaseEntity> provinceList){
		AtomicInteger index = new AtomicInteger();
		provinceList.stream().forEach(province->
			commonObjectElementCheck(province,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixProvince));
		return this;
	}

	public HealthObjectChecker checkCityListOfPlatform(List<BaseEntity> cityList){
		AtomicInteger index = new AtomicInteger();
		cityList.stream().forEach(city->
			commonObjectElementCheck(city,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixCity));
		return this;
	}

	public HealthObjectChecker checkDistrictListOfPlatform(List<BaseEntity> districtList){
		AtomicInteger index = new AtomicInteger();
		districtList.stream().forEach(district->
			commonObjectElementCheck(district,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixDistrict));
		return this;
	}

	public HealthObjectChecker checkTeacherListOfPlatform(List<BaseEntity> teacherList){
		AtomicInteger index = new AtomicInteger();
		teacherList.stream().forEach(teacher->
			commonObjectElementCheck(teacher,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixTeacher));
		return this;
	}

	public HealthObjectChecker checkStudentListOfPlatform(List<BaseEntity> studentList){
		AtomicInteger index = new AtomicInteger();
		studentList.stream().forEach(student->
			commonObjectElementCheck(student,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudent));
		return this;
	}

	public HealthObjectChecker checkQuestionListOfPlatform(List<BaseEntity> questionList){
		AtomicInteger index = new AtomicInteger();
		questionList.stream().forEach(question->
			commonObjectElementCheck(question,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuestion));
		return this;
	}

	public HealthObjectChecker checkQuestionTypeListOfPlatform(List<BaseEntity> questionTypeList){
		AtomicInteger index = new AtomicInteger();
		questionTypeList.stream().forEach(questionType->
			commonObjectElementCheck(questionType,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuestionType));
		return this;
	}

	public HealthObjectChecker checkSurveyStatusListOfPlatform(List<BaseEntity> surveyStatusList){
		AtomicInteger index = new AtomicInteger();
		surveyStatusList.stream().forEach(surveyStatus->
			commonObjectElementCheck(surveyStatus,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixSurveyStatus));
		return this;
	}

	public HealthObjectChecker checkUserListOfPlatform(List<BaseEntity> userList){
		AtomicInteger index = new AtomicInteger();
		userList.stream().forEach(user->
			commonObjectElementCheck(user,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUser));
		return this;
	}

	public HealthObjectChecker checkChangeRequestListOfPlatform(List<BaseEntity> changeRequestList){
		AtomicInteger index = new AtomicInteger();
		changeRequestList.stream().forEach(changeRequest->
			commonObjectElementCheck(changeRequest,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequest));
		return this;
	}

	public HealthObjectChecker checkChangeRequestTypeListOfPlatform(List<BaseEntity> changeRequestTypeList){
		AtomicInteger index = new AtomicInteger();
		changeRequestTypeList.stream().forEach(changeRequestType->
			commonObjectElementCheck(changeRequestType,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequestType));
		return this;
	}

	public HealthObjectChecker checkCityListOfProvince(List<BaseEntity> cityList){
		AtomicInteger index = new AtomicInteger();
		cityList.stream().forEach(city->
			commonObjectElementCheck(city,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixCity));
		return this;
	}

	public HealthObjectChecker checkLocationListOfProvince(List<BaseEntity> locationList){
		AtomicInteger index = new AtomicInteger();
		locationList.stream().forEach(location->
			commonObjectElementCheck(location,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixLocation));
		return this;
	}

	public static final String PLATFORM_OF_PROVINCE = "province.platform";


	public HealthObjectChecker checkPlatformOfProvince(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_PROVINCE);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkDistrictListOfCity(List<BaseEntity> districtList){
		AtomicInteger index = new AtomicInteger();
		districtList.stream().forEach(district->
			commonObjectElementCheck(district,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixDistrict));
		return this;
	}

	public static final String PROVINCE_OF_CITY = "city.province";


	public HealthObjectChecker checkProvinceOfCity(BaseEntity provinceAsBaseEntity){

		if(provinceAsBaseEntity == null){
			checkBaseEntityReference(provinceAsBaseEntity,true,PROVINCE_OF_CITY);
			return this;
		}
		checkAndFixProvince(provinceAsBaseEntity);
		return this;
	}


	public static final String PLATFORM_OF_CITY = "city.platform";


	public HealthObjectChecker checkPlatformOfCity(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CITY);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkLocationListOfDistrict(List<BaseEntity> locationList){
		AtomicInteger index = new AtomicInteger();
		locationList.stream().forEach(location->
			commonObjectElementCheck(location,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixLocation));
		return this;
	}

	public static final String CITY_OF_DISTRICT = "district.city";


	public HealthObjectChecker checkCityOfDistrict(BaseEntity cityAsBaseEntity){

		if(cityAsBaseEntity == null){
			checkBaseEntityReference(cityAsBaseEntity,true,CITY_OF_DISTRICT);
			return this;
		}
		checkAndFixCity(cityAsBaseEntity);
		return this;
	}


	public static final String PLATFORM_OF_DISTRICT = "district.platform";


	public HealthObjectChecker checkPlatformOfDistrict(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_DISTRICT);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentListOfLocation(List<BaseEntity> studentList){
		AtomicInteger index = new AtomicInteger();
		studentList.stream().forEach(student->
			commonObjectElementCheck(student,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudent));
		return this;
	}

	public static final String DISTRICT_OF_LOCATION = "location.district";


	public HealthObjectChecker checkDistrictOfLocation(BaseEntity districtAsBaseEntity){

		if(districtAsBaseEntity == null){
			checkBaseEntityReference(districtAsBaseEntity,true,DISTRICT_OF_LOCATION);
			return this;
		}
		checkAndFixDistrict(districtAsBaseEntity);
		return this;
	}


	public static final String PROVINCE_OF_LOCATION = "location.province";


	public HealthObjectChecker checkProvinceOfLocation(BaseEntity provinceAsBaseEntity){

		if(provinceAsBaseEntity == null){
			checkBaseEntityReference(provinceAsBaseEntity,true,PROVINCE_OF_LOCATION);
			return this;
		}
		checkAndFixProvince(provinceAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkClassDailyHealthSurveyListOfTeacher(List<BaseEntity> classDailyHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		classDailyHealthSurveyList.stream().forEach(classDailyHealthSurvey->
			commonObjectElementCheck(classDailyHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixClassDailyHealthSurvey));
		return this;
	}

	public HealthObjectChecker checkStudentHealthSurveyListOfTeacher(List<BaseEntity> studentHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		studentHealthSurveyList.stream().forEach(studentHealthSurvey->
			commonObjectElementCheck(studentHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentHealthSurvey));
		return this;
	}

	public HealthObjectChecker checkHealthSurveyReportListOfTeacher(List<BaseEntity> healthSurveyReportList){
		AtomicInteger index = new AtomicInteger();
		healthSurveyReportList.stream().forEach(healthSurveyReport->
			commonObjectElementCheck(healthSurveyReport,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixHealthSurveyReport));
		return this;
	}

	public static final String PLATFORM_OF_TEACHER = "teacher.platform";


	public HealthObjectChecker checkPlatformOfTeacher(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_TEACHER);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public static final String USER_OF_TEACHER = "teacher.user";


	public HealthObjectChecker checkUserOfTeacher(BaseEntity userAsBaseEntity){

		if(userAsBaseEntity == null){
			checkBaseEntityReference(userAsBaseEntity,true,USER_OF_TEACHER);
			return this;
		}
		checkAndFixUser(userAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_TEACHER = "teacher.change_request";


	public HealthObjectChecker checkChangeRequestOfTeacher(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,false,CHANGE_REQUEST_OF_TEACHER);
			return this;
		}
		checkAndFixChangeRequest(changeRequestAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentHealthSurveyListOfStudent(List<BaseEntity> studentHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		studentHealthSurveyList.stream().forEach(studentHealthSurvey->
			commonObjectElementCheck(studentHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentHealthSurvey));
		return this;
	}

	public HealthObjectChecker checkHealthSurveyReportListOfStudent(List<BaseEntity> healthSurveyReportList){
		AtomicInteger index = new AtomicInteger();
		healthSurveyReportList.stream().forEach(healthSurveyReport->
			commonObjectElementCheck(healthSurveyReport,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixHealthSurveyReport));
		return this;
	}

	public static final String ADDRESS_OF_STUDENT = "student.address";


	public HealthObjectChecker checkAddressOfStudent(BaseEntity addressAsBaseEntity){

		if(addressAsBaseEntity == null){
			checkBaseEntityReference(addressAsBaseEntity,false,ADDRESS_OF_STUDENT);
			return this;
		}
		checkAndFixLocation(addressAsBaseEntity);
		return this;
	}


	public static final String USER_OF_STUDENT = "student.user";


	public HealthObjectChecker checkUserOfStudent(BaseEntity userAsBaseEntity){

		if(userAsBaseEntity == null){
			checkBaseEntityReference(userAsBaseEntity,true,USER_OF_STUDENT);
			return this;
		}
		checkAndFixUser(userAsBaseEntity);
		return this;
	}


	public static final String PLATFORM_OF_STUDENT = "student.platform";


	public HealthObjectChecker checkPlatformOfStudent(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_STUDENT);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkDailySurveyQuestionListOfQuestion(List<BaseEntity> dailySurveyQuestionList){
		AtomicInteger index = new AtomicInteger();
		dailySurveyQuestionList.stream().forEach(dailySurveyQuestion->
			commonObjectElementCheck(dailySurveyQuestion,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixDailySurveyQuestion));
		return this;
	}

	public static final String QUESTION_TYPE_OF_QUESTION = "question.question_type";


	public HealthObjectChecker checkQuestionTypeOfQuestion(BaseEntity questionTypeAsBaseEntity){

		if(questionTypeAsBaseEntity == null){
			checkBaseEntityReference(questionTypeAsBaseEntity,true,QUESTION_TYPE_OF_QUESTION);
			return this;
		}
		checkAndFixQuestionType(questionTypeAsBaseEntity);
		return this;
	}


	public static final String PLATFORM_OF_QUESTION = "question.platform";


	public HealthObjectChecker checkPlatformOfQuestion(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_QUESTION);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public static final String CREATOR_OF_QUESTION = "question.creator";


	public HealthObjectChecker checkCreatorOfQuestion(BaseEntity creatorAsBaseEntity){

		if(creatorAsBaseEntity == null){
			checkBaseEntityReference(creatorAsBaseEntity,false,CREATOR_OF_QUESTION);
			return this;
		}
		checkAndFixUser(creatorAsBaseEntity);
		return this;
	}


	public static final String CQ_OF_QUESTION = "question.cq";


	public HealthObjectChecker checkCqOfQuestion(BaseEntity cqAsBaseEntity){

		if(cqAsBaseEntity == null){
			checkBaseEntityReference(cqAsBaseEntity,false,CQ_OF_QUESTION);
			return this;
		}
		checkAndFixChangeRequest(cqAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkQuestionListOfQuestionType(List<BaseEntity> questionList){
		AtomicInteger index = new AtomicInteger();
		questionList.stream().forEach(question->
			commonObjectElementCheck(question,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuestion));
		return this;
	}

	public HealthObjectChecker checkDailySurveyQuestionListOfQuestionType(List<BaseEntity> dailySurveyQuestionList){
		AtomicInteger index = new AtomicInteger();
		dailySurveyQuestionList.stream().forEach(dailySurveyQuestion->
			commonObjectElementCheck(dailySurveyQuestion,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixDailySurveyQuestion));
		return this;
	}

	public static final String PLATFORM_OF_QUESTION_TYPE = "question_type.platform";


	public HealthObjectChecker checkPlatformOfQuestionType(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_QUESTION_TYPE);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkDailySurveyQuestionListOfClassDailyHealthSurvey(List<BaseEntity> dailySurveyQuestionList){
		AtomicInteger index = new AtomicInteger();
		dailySurveyQuestionList.stream().forEach(dailySurveyQuestion->
			commonObjectElementCheck(dailySurveyQuestion,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixDailySurveyQuestion));
		return this;
	}

	public HealthObjectChecker checkStudentHealthSurveyListOfClassDailyHealthSurvey(List<BaseEntity> studentHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		studentHealthSurveyList.stream().forEach(studentHealthSurvey->
			commonObjectElementCheck(studentHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentHealthSurvey));
		return this;
	}

	public HealthObjectChecker checkHealthSurveyReportListOfClassDailyHealthSurvey(List<BaseEntity> healthSurveyReportList){
		AtomicInteger index = new AtomicInteger();
		healthSurveyReportList.stream().forEach(healthSurveyReport->
			commonObjectElementCheck(healthSurveyReport,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixHealthSurveyReport));
		return this;
	}

	public static final String TEACHER_OF_CLASS_DAILY_HEALTH_SURVEY = "class_daily_health_survey.teacher";


	public HealthObjectChecker checkTeacherOfClassDailyHealthSurvey(BaseEntity teacherAsBaseEntity){

		if(teacherAsBaseEntity == null){
			checkBaseEntityReference(teacherAsBaseEntity,true,TEACHER_OF_CLASS_DAILY_HEALTH_SURVEY);
			return this;
		}
		checkAndFixTeacher(teacherAsBaseEntity);
		return this;
	}


	public static final String CREATOR_OF_CLASS_DAILY_HEALTH_SURVEY = "class_daily_health_survey.creator";


	public HealthObjectChecker checkCreatorOfClassDailyHealthSurvey(BaseEntity creatorAsBaseEntity){

		if(creatorAsBaseEntity == null){
			checkBaseEntityReference(creatorAsBaseEntity,true,CREATOR_OF_CLASS_DAILY_HEALTH_SURVEY);
			return this;
		}
		checkAndFixUser(creatorAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_CLASS_DAILY_HEALTH_SURVEY = "class_daily_health_survey.change_request";


	public HealthObjectChecker checkChangeRequestOfClassDailyHealthSurvey(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,false,CHANGE_REQUEST_OF_CLASS_DAILY_HEALTH_SURVEY);
			return this;
		}
		checkAndFixChangeRequest(changeRequestAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentDailyAnswerListOfDailySurveyQuestion(List<BaseEntity> studentDailyAnswerList){
		AtomicInteger index = new AtomicInteger();
		studentDailyAnswerList.stream().forEach(studentDailyAnswer->
			commonObjectElementCheck(studentDailyAnswer,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentDailyAnswer));
		return this;
	}

	public static final String QUESTION_TYPE_OF_DAILY_SURVEY_QUESTION = "daily_survey_question.question_type";


	public HealthObjectChecker checkQuestionTypeOfDailySurveyQuestion(BaseEntity questionTypeAsBaseEntity){

		if(questionTypeAsBaseEntity == null){
			checkBaseEntityReference(questionTypeAsBaseEntity,true,QUESTION_TYPE_OF_DAILY_SURVEY_QUESTION);
			return this;
		}
		checkAndFixQuestionType(questionTypeAsBaseEntity);
		return this;
	}


	public static final String CLASS_DAILY_HEALTH_SURVEY_OF_DAILY_SURVEY_QUESTION = "daily_survey_question.class_daily_health_survey";


	public HealthObjectChecker checkClassDailyHealthSurveyOfDailySurveyQuestion(BaseEntity classDailyHealthSurveyAsBaseEntity){

		if(classDailyHealthSurveyAsBaseEntity == null){
			checkBaseEntityReference(classDailyHealthSurveyAsBaseEntity,true,CLASS_DAILY_HEALTH_SURVEY_OF_DAILY_SURVEY_QUESTION);
			return this;
		}
		checkAndFixClassDailyHealthSurvey(classDailyHealthSurveyAsBaseEntity);
		return this;
	}


	public static final String SURVEY_QUESTION_OF_DAILY_SURVEY_QUESTION = "daily_survey_question.survey_question";


	public HealthObjectChecker checkSurveyQuestionOfDailySurveyQuestion(BaseEntity surveyQuestionAsBaseEntity){

		if(surveyQuestionAsBaseEntity == null){
			checkBaseEntityReference(surveyQuestionAsBaseEntity,true,SURVEY_QUESTION_OF_DAILY_SURVEY_QUESTION);
			return this;
		}
		checkAndFixQuestion(surveyQuestionAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentDailyAnswerListOfStudentHealthSurvey(List<BaseEntity> studentDailyAnswerList){
		AtomicInteger index = new AtomicInteger();
		studentDailyAnswerList.stream().forEach(studentDailyAnswer->
			commonObjectElementCheck(studentDailyAnswer,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentDailyAnswer));
		return this;
	}

	public static final String STUDENT_OF_STUDENT_HEALTH_SURVEY = "student_health_survey.student";


	public HealthObjectChecker checkStudentOfStudentHealthSurvey(BaseEntity studentAsBaseEntity){

		if(studentAsBaseEntity == null){
			checkBaseEntityReference(studentAsBaseEntity,true,STUDENT_OF_STUDENT_HEALTH_SURVEY);
			return this;
		}
		checkAndFixStudent(studentAsBaseEntity);
		return this;
	}


	public static final String SURVEY_STATUS_OF_STUDENT_HEALTH_SURVEY = "student_health_survey.survey_status";


	public HealthObjectChecker checkSurveyStatusOfStudentHealthSurvey(BaseEntity surveyStatusAsBaseEntity){

		if(surveyStatusAsBaseEntity == null){
			checkBaseEntityReference(surveyStatusAsBaseEntity,true,SURVEY_STATUS_OF_STUDENT_HEALTH_SURVEY);
			return this;
		}
		checkAndFixSurveyStatus(surveyStatusAsBaseEntity);
		return this;
	}


	public static final String TEACHER_OF_STUDENT_HEALTH_SURVEY = "student_health_survey.teacher";


	public HealthObjectChecker checkTeacherOfStudentHealthSurvey(BaseEntity teacherAsBaseEntity){

		if(teacherAsBaseEntity == null){
			checkBaseEntityReference(teacherAsBaseEntity,true,TEACHER_OF_STUDENT_HEALTH_SURVEY);
			return this;
		}
		checkAndFixTeacher(teacherAsBaseEntity);
		return this;
	}


	public static final String CLASS_DAILY_HEALTH_SURVEY_OF_STUDENT_HEALTH_SURVEY = "student_health_survey.class_daily_health_survey";


	public HealthObjectChecker checkClassDailyHealthSurveyOfStudentHealthSurvey(BaseEntity classDailyHealthSurveyAsBaseEntity){

		if(classDailyHealthSurveyAsBaseEntity == null){
			checkBaseEntityReference(classDailyHealthSurveyAsBaseEntity,true,CLASS_DAILY_HEALTH_SURVEY_OF_STUDENT_HEALTH_SURVEY);
			return this;
		}
		checkAndFixClassDailyHealthSurvey(classDailyHealthSurveyAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_STUDENT_HEALTH_SURVEY = "student_health_survey.change_request";


	public HealthObjectChecker checkChangeRequestOfStudentHealthSurvey(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,false,CHANGE_REQUEST_OF_STUDENT_HEALTH_SURVEY);
			return this;
		}
		checkAndFixChangeRequest(changeRequestAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentAnswerListOfStudentDailyAnswer(List<BaseEntity> studentAnswerList){
		AtomicInteger index = new AtomicInteger();
		studentAnswerList.stream().forEach(studentAnswer->
			commonObjectElementCheck(studentAnswer,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentAnswer));
		return this;
	}

	public static final String STUDENT_HEALTH_SURVEY_OF_STUDENT_DAILY_ANSWER = "student_daily_answer.student_health_survey";


	public HealthObjectChecker checkStudentHealthSurveyOfStudentDailyAnswer(BaseEntity studentHealthSurveyAsBaseEntity){

		if(studentHealthSurveyAsBaseEntity == null){
			checkBaseEntityReference(studentHealthSurveyAsBaseEntity,true,STUDENT_HEALTH_SURVEY_OF_STUDENT_DAILY_ANSWER);
			return this;
		}
		checkAndFixStudentHealthSurvey(studentHealthSurveyAsBaseEntity);
		return this;
	}


	public static final String QUESTION_OF_STUDENT_DAILY_ANSWER = "student_daily_answer.question";


	public HealthObjectChecker checkQuestionOfStudentDailyAnswer(BaseEntity questionAsBaseEntity){

		if(questionAsBaseEntity == null){
			checkBaseEntityReference(questionAsBaseEntity,true,QUESTION_OF_STUDENT_DAILY_ANSWER);
			return this;
		}
		checkAndFixDailySurveyQuestion(questionAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentHealthSurveyListOfSurveyStatus(List<BaseEntity> studentHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		studentHealthSurveyList.stream().forEach(studentHealthSurvey->
			commonObjectElementCheck(studentHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentHealthSurvey));
		return this;
	}

	public static final String PLATFORM_OF_SURVEY_STATUS = "survey_status.platform";


	public HealthObjectChecker checkPlatformOfSurveyStatus(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_SURVEY_STATUS);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkStudentAnswerListOfHealthSurveyReport(List<BaseEntity> studentAnswerList){
		AtomicInteger index = new AtomicInteger();
		studentAnswerList.stream().forEach(studentAnswer->
			commonObjectElementCheck(studentAnswer,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentAnswer));
		return this;
	}

	public static final String STUDENT_OF_HEALTH_SURVEY_REPORT = "health_survey_report.student";


	public HealthObjectChecker checkStudentOfHealthSurveyReport(BaseEntity studentAsBaseEntity){

		if(studentAsBaseEntity == null){
			checkBaseEntityReference(studentAsBaseEntity,true,STUDENT_OF_HEALTH_SURVEY_REPORT);
			return this;
		}
		checkAndFixStudent(studentAsBaseEntity);
		return this;
	}


	public static final String TEACHER_OF_HEALTH_SURVEY_REPORT = "health_survey_report.teacher";


	public HealthObjectChecker checkTeacherOfHealthSurveyReport(BaseEntity teacherAsBaseEntity){

		if(teacherAsBaseEntity == null){
			checkBaseEntityReference(teacherAsBaseEntity,true,TEACHER_OF_HEALTH_SURVEY_REPORT);
			return this;
		}
		checkAndFixTeacher(teacherAsBaseEntity);
		return this;
	}


	public static final String SURVEY_OF_HEALTH_SURVEY_REPORT = "health_survey_report.survey";


	public HealthObjectChecker checkSurveyOfHealthSurveyReport(BaseEntity surveyAsBaseEntity){

		if(surveyAsBaseEntity == null){
			checkBaseEntityReference(surveyAsBaseEntity,true,SURVEY_OF_HEALTH_SURVEY_REPORT);
			return this;
		}
		checkAndFixClassDailyHealthSurvey(surveyAsBaseEntity);
		return this;
	}


	public static final String HEALTH_SURVEY_REPORT_OF_STUDENT_ANSWER = "student_answer.health_survey_report";


	public HealthObjectChecker checkHealthSurveyReportOfStudentAnswer(BaseEntity healthSurveyReportAsBaseEntity){

		if(healthSurveyReportAsBaseEntity == null){
			checkBaseEntityReference(healthSurveyReportAsBaseEntity,true,HEALTH_SURVEY_REPORT_OF_STUDENT_ANSWER);
			return this;
		}
		checkAndFixHealthSurveyReport(healthSurveyReportAsBaseEntity);
		return this;
	}


	public static final String DAILY_ANSWER_OF_STUDENT_ANSWER = "student_answer.daily_answer";


	public HealthObjectChecker checkDailyAnswerOfStudentAnswer(BaseEntity dailyAnswerAsBaseEntity){

		if(dailyAnswerAsBaseEntity == null){
			checkBaseEntityReference(dailyAnswerAsBaseEntity,true,DAILY_ANSWER_OF_STUDENT_ANSWER);
			return this;
		}
		checkAndFixStudentDailyAnswer(dailyAnswerAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkTeacherListOfUser(List<BaseEntity> teacherList){
		AtomicInteger index = new AtomicInteger();
		teacherList.stream().forEach(teacher->
			commonObjectElementCheck(teacher,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixTeacher));
		return this;
	}

	public HealthObjectChecker checkStudentListOfUser(List<BaseEntity> studentList){
		AtomicInteger index = new AtomicInteger();
		studentList.stream().forEach(student->
			commonObjectElementCheck(student,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudent));
		return this;
	}

	public HealthObjectChecker checkQuestionListOfUser(List<BaseEntity> questionList){
		AtomicInteger index = new AtomicInteger();
		questionList.stream().forEach(question->
			commonObjectElementCheck(question,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuestion));
		return this;
	}

	public HealthObjectChecker checkClassDailyHealthSurveyListOfUser(List<BaseEntity> classDailyHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		classDailyHealthSurveyList.stream().forEach(classDailyHealthSurvey->
			commonObjectElementCheck(classDailyHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixClassDailyHealthSurvey));
		return this;
	}

	public HealthObjectChecker checkWechatLoginInfoListOfUser(List<BaseEntity> wechatLoginInfoList){
		AtomicInteger index = new AtomicInteger();
		wechatLoginInfoList.stream().forEach(wechatLoginInfo->
			commonObjectElementCheck(wechatLoginInfo,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWechatLoginInfo));
		return this;
	}

	public static final String PLATFORM_OF_USER = "user.platform";


	public HealthObjectChecker checkPlatformOfUser(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_USER);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public static final String USER_OF_WECHAT_LOGIN_INFO = "wechat_login_info.user";


	public HealthObjectChecker checkUserOfWechatLoginInfo(BaseEntity userAsBaseEntity){

		if(userAsBaseEntity == null){
			checkBaseEntityReference(userAsBaseEntity,true,USER_OF_WECHAT_LOGIN_INFO);
			return this;
		}
		checkAndFixUser(userAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkTeacherListOfChangeRequest(List<BaseEntity> teacherList){
		AtomicInteger index = new AtomicInteger();
		teacherList.stream().forEach(teacher->
			commonObjectElementCheck(teacher,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixTeacher));
		return this;
	}

	public HealthObjectChecker checkQuestionListOfChangeRequest(List<BaseEntity> questionList){
		AtomicInteger index = new AtomicInteger();
		questionList.stream().forEach(question->
			commonObjectElementCheck(question,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuestion));
		return this;
	}

	public HealthObjectChecker checkClassDailyHealthSurveyListOfChangeRequest(List<BaseEntity> classDailyHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		classDailyHealthSurveyList.stream().forEach(classDailyHealthSurvey->
			commonObjectElementCheck(classDailyHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixClassDailyHealthSurvey));
		return this;
	}

	public HealthObjectChecker checkStudentHealthSurveyListOfChangeRequest(List<BaseEntity> studentHealthSurveyList){
		AtomicInteger index = new AtomicInteger();
		studentHealthSurveyList.stream().forEach(studentHealthSurvey->
			commonObjectElementCheck(studentHealthSurvey,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixStudentHealthSurvey));
		return this;
	}

	public static final String REQUEST_TYPE_OF_CHANGE_REQUEST = "change_request.request_type";


	public HealthObjectChecker checkRequestTypeOfChangeRequest(BaseEntity requestTypeAsBaseEntity){

		if(requestTypeAsBaseEntity == null){
			checkBaseEntityReference(requestTypeAsBaseEntity,true,REQUEST_TYPE_OF_CHANGE_REQUEST);
			return this;
		}
		checkAndFixChangeRequestType(requestTypeAsBaseEntity);
		return this;
	}


	public static final String PLATFORM_OF_CHANGE_REQUEST = "change_request.platform";


	public HealthObjectChecker checkPlatformOfChangeRequest(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CHANGE_REQUEST);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkChangeRequestListOfChangeRequestType(List<BaseEntity> changeRequestList){
		AtomicInteger index = new AtomicInteger();
		changeRequestList.stream().forEach(changeRequest->
			commonObjectElementCheck(changeRequest,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequest));
		return this;
	}

	public static final String PLATFORM_OF_CHANGE_REQUEST_TYPE = "change_request_type.platform";


	public HealthObjectChecker checkPlatformOfChangeRequestType(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CHANGE_REQUEST_TYPE);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkUserWhiteListListOfUserDomain(List<BaseEntity> userWhiteListList){
		AtomicInteger index = new AtomicInteger();
		userWhiteListList.stream().forEach(userWhiteList->
			commonObjectElementCheck(userWhiteList,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUserWhiteList));
		return this;
	}

	public HealthObjectChecker checkSecUserListOfUserDomain(List<BaseEntity> secUserList){
		AtomicInteger index = new AtomicInteger();
		secUserList.stream().forEach(secUser->
			commonObjectElementCheck(secUser,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixSecUser));
		return this;
	}

	public static final String DOMAIN_OF_USER_WHITE_LIST = "user_white_list.domain";


	public HealthObjectChecker checkDomainOfUserWhiteList(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_USER_WHITE_LIST);
			return this;
		}
		checkAndFixUserDomain(domainAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkUserAppListOfSecUser(List<BaseEntity> userAppList){
		AtomicInteger index = new AtomicInteger();
		userAppList.stream().forEach(userApp->
			commonObjectElementCheck(userApp,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUserApp));
		return this;
	}

	public HealthObjectChecker checkLoginHistoryListOfSecUser(List<BaseEntity> loginHistoryList){
		AtomicInteger index = new AtomicInteger();
		loginHistoryList.stream().forEach(loginHistory->
			commonObjectElementCheck(loginHistory,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixLoginHistory));
		return this;
	}

	public HealthObjectChecker checkWechatWorkappIdentifyListOfSecUser(List<BaseEntity> wechatWorkappIdentifyList){
		AtomicInteger index = new AtomicInteger();
		wechatWorkappIdentifyList.stream().forEach(wechatWorkappIdentify->
			commonObjectElementCheck(wechatWorkappIdentify,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWechatWorkappIdentify));
		return this;
	}

	public HealthObjectChecker checkWechatMiniappIdentifyListOfSecUser(List<BaseEntity> wechatMiniappIdentifyList){
		AtomicInteger index = new AtomicInteger();
		wechatMiniappIdentifyList.stream().forEach(wechatMiniappIdentify->
			commonObjectElementCheck(wechatMiniappIdentify,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWechatMiniappIdentify));
		return this;
	}

	public static final String DOMAIN_OF_SEC_USER = "sec_user.domain";


	public HealthObjectChecker checkDomainOfSecUser(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_SEC_USER);
			return this;
		}
		checkAndFixUserDomain(domainAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkQuickLinkListOfUserApp(List<BaseEntity> quickLinkList){
		AtomicInteger index = new AtomicInteger();
		quickLinkList.stream().forEach(quickLink->
			commonObjectElementCheck(quickLink,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuickLink));
		return this;
	}

	public HealthObjectChecker checkListAccessListOfUserApp(List<BaseEntity> listAccessList){
		AtomicInteger index = new AtomicInteger();
		listAccessList.stream().forEach(listAccess->
			commonObjectElementCheck(listAccess,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixListAccess));
		return this;
	}

	public HealthObjectChecker checkObjectAccessListOfUserApp(List<BaseEntity> objectAccessList){
		AtomicInteger index = new AtomicInteger();
		objectAccessList.stream().forEach(objectAccess->
			commonObjectElementCheck(objectAccess,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixObjectAccess));
		return this;
	}

	public static final String SEC_USER_OF_USER_APP = "user_app.sec_user";


	public HealthObjectChecker checkSecUserOfUserApp(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_USER_APP);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public static final String APP_OF_QUICK_LINK = "quick_link.app";


	public HealthObjectChecker checkAppOfQuickLink(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_QUICK_LINK);
			return this;
		}
		checkAndFixUserApp(appAsBaseEntity);
		return this;
	}


	public static final String APP_OF_LIST_ACCESS = "list_access.app";


	public HealthObjectChecker checkAppOfListAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_LIST_ACCESS);
			return this;
		}
		checkAndFixUserApp(appAsBaseEntity);
		return this;
	}


	public static final String APP_OF_OBJECT_ACCESS = "object_access.app";


	public HealthObjectChecker checkAppOfObjectAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_OBJECT_ACCESS);
			return this;
		}
		checkAndFixUserApp(appAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_LOGIN_HISTORY = "login_history.sec_user";


	public HealthObjectChecker checkSecUserOfLoginHistory(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_LOGIN_HISTORY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkFormMessageListOfGenericForm(List<BaseEntity> formMessageList){
		AtomicInteger index = new AtomicInteger();
		formMessageList.stream().forEach(formMessage->
			commonObjectElementCheck(formMessage,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixFormMessage));
		return this;
	}

	public HealthObjectChecker checkFormFieldMessageListOfGenericForm(List<BaseEntity> formFieldMessageList){
		AtomicInteger index = new AtomicInteger();
		formFieldMessageList.stream().forEach(formFieldMessage->
			commonObjectElementCheck(formFieldMessage,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixFormFieldMessage));
		return this;
	}

	public HealthObjectChecker checkFormFieldListOfGenericForm(List<BaseEntity> formFieldList){
		AtomicInteger index = new AtomicInteger();
		formFieldList.stream().forEach(formField->
			commonObjectElementCheck(formField,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixFormField));
		return this;
	}

	public HealthObjectChecker checkFormActionListOfGenericForm(List<BaseEntity> formActionList){
		AtomicInteger index = new AtomicInteger();
		formActionList.stream().forEach(formAction->
			commonObjectElementCheck(formAction,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixFormAction));
		return this;
	}

	public static final String FORM_OF_FORM_MESSAGE = "form_message.form";


	public HealthObjectChecker checkFormOfFormMessage(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_MESSAGE);
			return this;
		}
		checkAndFixGenericForm(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_FIELD_MESSAGE = "form_field_message.form";


	public HealthObjectChecker checkFormOfFormFieldMessage(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_FIELD_MESSAGE);
			return this;
		}
		checkAndFixGenericForm(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_FIELD = "form_field.form";


	public HealthObjectChecker checkFormOfFormField(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_FIELD);
			return this;
		}
		checkAndFixGenericForm(formAsBaseEntity);
		return this;
	}


	public static final String FORM_OF_FORM_ACTION = "form_action.form";


	public HealthObjectChecker checkFormOfFormAction(BaseEntity formAsBaseEntity){

		if(formAsBaseEntity == null){
			checkBaseEntityReference(formAsBaseEntity,true,FORM_OF_FORM_ACTION);
			return this;
		}
		checkAndFixGenericForm(formAsBaseEntity);
		return this;
	}


	public HealthObjectChecker checkCandidateElementListOfCandidateContainer(List<BaseEntity> candidateElementList){
		AtomicInteger index = new AtomicInteger();
		candidateElementList.stream().forEach(candidateElement->
			commonObjectElementCheck(candidateElement,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixCandidateElement));
		return this;
	}

	public static final String CONTAINER_OF_CANDIDATE_ELEMENT = "candidate_element.container";


	public HealthObjectChecker checkContainerOfCandidateElement(BaseEntity containerAsBaseEntity){

		if(containerAsBaseEntity == null){
			checkBaseEntityReference(containerAsBaseEntity,true,CONTAINER_OF_CANDIDATE_ELEMENT);
			return this;
		}
		checkAndFixCandidateContainer(containerAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_WECHAT_WORKAPP_IDENTIFY = "wechat_workapp_identify.sec_user";


	public HealthObjectChecker checkSecUserOfWechatWorkappIdentify(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_WECHAT_WORKAPP_IDENTIFY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_WECHAT_MINIAPP_IDENTIFY = "wechat_miniapp_identify.sec_user";


	public HealthObjectChecker checkSecUserOfWechatMiniappIdentify(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_WECHAT_MINIAPP_IDENTIFY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}

	public HealthObjectChecker assignCreateTimeOfProvince(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfCity(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfDistrict(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfTeacher(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfStudent(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfStudentHealthSurvey(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignLastUpdateTimeOfStudentHealthSurvey(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"lastUpdateTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfStudentDailyAnswer(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignLastUpdateTimeOfStudentDailyAnswer(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"lastUpdateTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfUser(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignLastUpdateTimeOfWechatLoginInfo(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"lastUpdateTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfChangeRequest(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignRemoteIpOfChangeRequest(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"remoteIp",userContext.getRemoteIP());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfQuickLink(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignLoginTimeOfLoginHistory(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"loginTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfWechatWorkappIdentify(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public HealthObjectChecker assignCreateTimeOfWechatMiniappIdentify(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}

}

