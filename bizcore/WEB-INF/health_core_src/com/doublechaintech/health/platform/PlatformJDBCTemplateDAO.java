
package com.doublechaintech.health.platform;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import com.doublechaintech.health.HealthBaseDAOImpl;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.AccessKey;
import com.doublechaintech.health.DateKey;
import com.doublechaintech.health.StatsInfo;
import com.doublechaintech.health.StatsItem;

import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthUserContext;


import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.questionsource.QuestionSource;
import com.doublechaintech.health.usertype.UserType;

import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.surveystatus.SurveyStatusDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;
import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.usertype.UserTypeDAO;
import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.guardian.GuardianDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.questionsource.QuestionSourceDAO;
import com.doublechaintech.health.question.QuestionDAO;
import com.doublechaintech.health.district.DistrictDAO;
import com.doublechaintech.health.changerequesttype.ChangeRequestTypeDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class PlatformJDBCTemplateDAO extends HealthBaseDAOImpl implements PlatformDAO{


			
		
	
  	private  ProvinceDAO  provinceDAO;
 	public void setProvinceDAO(ProvinceDAO pProvinceDAO){
 	
 		if(pProvinceDAO == null){
 			throw new IllegalStateException("Do not try to set provinceDAO to null.");
 		}
	 	this.provinceDAO = pProvinceDAO;
 	}
 	public ProvinceDAO getProvinceDAO(){
 		if(this.provinceDAO == null){
 			throw new IllegalStateException("The provinceDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.provinceDAO;
 	}	
 	
			
		
	
  	private  CityDAO  cityDAO;
 	public void setCityDAO(CityDAO pCityDAO){
 	
 		if(pCityDAO == null){
 			throw new IllegalStateException("Do not try to set cityDAO to null.");
 		}
	 	this.cityDAO = pCityDAO;
 	}
 	public CityDAO getCityDAO(){
 		if(this.cityDAO == null){
 			throw new IllegalStateException("The cityDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.cityDAO;
 	}	
 	
			
		
	
  	private  DistrictDAO  districtDAO;
 	public void setDistrictDAO(DistrictDAO pDistrictDAO){
 	
 		if(pDistrictDAO == null){
 			throw new IllegalStateException("Do not try to set districtDAO to null.");
 		}
	 	this.districtDAO = pDistrictDAO;
 	}
 	public DistrictDAO getDistrictDAO(){
 		if(this.districtDAO == null){
 			throw new IllegalStateException("The districtDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.districtDAO;
 	}	
 	
			
		
	
  	private  SchoolClassDAO  schoolClassDAO;
 	public void setSchoolClassDAO(SchoolClassDAO pSchoolClassDAO){
 	
 		if(pSchoolClassDAO == null){
 			throw new IllegalStateException("Do not try to set schoolClassDAO to null.");
 		}
	 	this.schoolClassDAO = pSchoolClassDAO;
 	}
 	public SchoolClassDAO getSchoolClassDAO(){
 		if(this.schoolClassDAO == null){
 			throw new IllegalStateException("The schoolClassDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.schoolClassDAO;
 	}	
 	
			
		
	
  	private  TeacherDAO  teacherDAO;
 	public void setTeacherDAO(TeacherDAO pTeacherDAO){
 	
 		if(pTeacherDAO == null){
 			throw new IllegalStateException("Do not try to set teacherDAO to null.");
 		}
	 	this.teacherDAO = pTeacherDAO;
 	}
 	public TeacherDAO getTeacherDAO(){
 		if(this.teacherDAO == null){
 			throw new IllegalStateException("The teacherDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.teacherDAO;
 	}	
 	
			
		
	
  	private  GuardianDAO  guardianDAO;
 	public void setGuardianDAO(GuardianDAO pGuardianDAO){
 	
 		if(pGuardianDAO == null){
 			throw new IllegalStateException("Do not try to set guardianDAO to null.");
 		}
	 	this.guardianDAO = pGuardianDAO;
 	}
 	public GuardianDAO getGuardianDAO(){
 		if(this.guardianDAO == null){
 			throw new IllegalStateException("The guardianDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.guardianDAO;
 	}	
 	
			
		
	
  	private  QuestionDAO  questionDAO;
 	public void setQuestionDAO(QuestionDAO pQuestionDAO){
 	
 		if(pQuestionDAO == null){
 			throw new IllegalStateException("Do not try to set questionDAO to null.");
 		}
	 	this.questionDAO = pQuestionDAO;
 	}
 	public QuestionDAO getQuestionDAO(){
 		if(this.questionDAO == null){
 			throw new IllegalStateException("The questionDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.questionDAO;
 	}	
 	
			
		
	
  	private  QuestionTypeDAO  questionTypeDAO;
 	public void setQuestionTypeDAO(QuestionTypeDAO pQuestionTypeDAO){
 	
 		if(pQuestionTypeDAO == null){
 			throw new IllegalStateException("Do not try to set questionTypeDAO to null.");
 		}
	 	this.questionTypeDAO = pQuestionTypeDAO;
 	}
 	public QuestionTypeDAO getQuestionTypeDAO(){
 		if(this.questionTypeDAO == null){
 			throw new IllegalStateException("The questionTypeDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.questionTypeDAO;
 	}	
 	
			
		
	
  	private  QuestionSourceDAO  questionSourceDAO;
 	public void setQuestionSourceDAO(QuestionSourceDAO pQuestionSourceDAO){
 	
 		if(pQuestionSourceDAO == null){
 			throw new IllegalStateException("Do not try to set questionSourceDAO to null.");
 		}
	 	this.questionSourceDAO = pQuestionSourceDAO;
 	}
 	public QuestionSourceDAO getQuestionSourceDAO(){
 		if(this.questionSourceDAO == null){
 			throw new IllegalStateException("The questionSourceDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.questionSourceDAO;
 	}	
 	
			
		
	
  	private  SurveyStatusDAO  surveyStatusDAO;
 	public void setSurveyStatusDAO(SurveyStatusDAO pSurveyStatusDAO){
 	
 		if(pSurveyStatusDAO == null){
 			throw new IllegalStateException("Do not try to set surveyStatusDAO to null.");
 		}
	 	this.surveyStatusDAO = pSurveyStatusDAO;
 	}
 	public SurveyStatusDAO getSurveyStatusDAO(){
 		if(this.surveyStatusDAO == null){
 			throw new IllegalStateException("The surveyStatusDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.surveyStatusDAO;
 	}	
 	
			
		
	
  	private  WechatUserDAO  wechatUserDAO;
 	public void setWechatUserDAO(WechatUserDAO pWechatUserDAO){
 	
 		if(pWechatUserDAO == null){
 			throw new IllegalStateException("Do not try to set wechatUserDAO to null.");
 		}
	 	this.wechatUserDAO = pWechatUserDAO;
 	}
 	public WechatUserDAO getWechatUserDAO(){
 		if(this.wechatUserDAO == null){
 			throw new IllegalStateException("The wechatUserDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.wechatUserDAO;
 	}	
 	
			
		
	
  	private  UserTypeDAO  userTypeDAO;
 	public void setUserTypeDAO(UserTypeDAO pUserTypeDAO){
 	
 		if(pUserTypeDAO == null){
 			throw new IllegalStateException("Do not try to set userTypeDAO to null.");
 		}
	 	this.userTypeDAO = pUserTypeDAO;
 	}
 	public UserTypeDAO getUserTypeDAO(){
 		if(this.userTypeDAO == null){
 			throw new IllegalStateException("The userTypeDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userTypeDAO;
 	}	
 	
			
		
	
  	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO pChangeRequestDAO){
 	
 		if(pChangeRequestDAO == null){
 			throw new IllegalStateException("Do not try to set changeRequestDAO to null.");
 		}
	 	this.changeRequestDAO = pChangeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
 		if(this.changeRequestDAO == null){
 			throw new IllegalStateException("The changeRequestDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.changeRequestDAO;
 	}	
 	
			
		
	
  	private  ChangeRequestTypeDAO  changeRequestTypeDAO;
 	public void setChangeRequestTypeDAO(ChangeRequestTypeDAO pChangeRequestTypeDAO){
 	
 		if(pChangeRequestTypeDAO == null){
 			throw new IllegalStateException("Do not try to set changeRequestTypeDAO to null.");
 		}
	 	this.changeRequestTypeDAO = pChangeRequestTypeDAO;
 	}
 	public ChangeRequestTypeDAO getChangeRequestTypeDAO(){
 		if(this.changeRequestTypeDAO == null){
 			throw new IllegalStateException("The changeRequestTypeDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.changeRequestTypeDAO;
 	}	
 	
			
		

	
	/*
	protected Platform load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(accessKey, options);
	}
	*/
	
	public SmartList<Platform> loadAll() {
	    return this.loadAll(getPlatformMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Platform load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(PlatformTable.withId(id), options);
	}
	
	
	
	public Platform save(Platform platform,Map<String,Object> options){
		
		String methodName="save(Platform platform,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(platform, methodName, "platform");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPlatform(platform,options);
	}
	public Platform clone(String platformId, Map<String,Object> options) throws Exception{
	
		return clone(PlatformTable.withId(platformId),options);
	}
	
	protected Platform clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String platformId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Platform newPlatform = loadInternalPlatform(accessKey, options);
		newPlatform.setVersion(0);
		
		
 		
 		if(isSaveProvinceListEnabled(options)){
 			for(Province item: newPlatform.getProvinceList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveCityListEnabled(options)){
 			for(City item: newPlatform.getCityList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveDistrictListEnabled(options)){
 			for(District item: newPlatform.getDistrictList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveSchoolClassListEnabled(options)){
 			for(SchoolClass item: newPlatform.getSchoolClassList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveTeacherListEnabled(options)){
 			for(Teacher item: newPlatform.getTeacherList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveGuardianListEnabled(options)){
 			for(Guardian item: newPlatform.getGuardianList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveQuestionListEnabled(options)){
 			for(Question item: newPlatform.getQuestionList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveQuestionTypeListEnabled(options)){
 			for(QuestionType item: newPlatform.getQuestionTypeList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveQuestionSourceListEnabled(options)){
 			for(QuestionSource item: newPlatform.getQuestionSourceList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveSurveyStatusListEnabled(options)){
 			for(SurveyStatus item: newPlatform.getSurveyStatusList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWechatUserListEnabled(options)){
 			for(WechatUser item: newPlatform.getWechatUserList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveUserTypeListEnabled(options)){
 			for(UserType item: newPlatform.getUserTypeList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveChangeRequestListEnabled(options)){
 			for(ChangeRequest item: newPlatform.getChangeRequestList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveChangeRequestTypeListEnabled(options)){
 			for(ChangeRequestType item: newPlatform.getChangeRequestTypeList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPlatform(newPlatform,options);
		
		return newPlatform;
	}
	
	
	
	

	protected void throwIfHasException(String platformId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PlatformVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PlatformNotFoundException(
					"The " + this.getTableName() + "(" + platformId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String platformId, int version) throws Exception{
	
		String methodName="delete(String platformId, int version)";
		assertMethodArgumentNotNull(platformId, methodName, "platformId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{platformId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(platformId,version);
		}
		
	
	}
	
	
	
	
	

	public Platform disconnectFromAll(String platformId, int version) throws Exception{
	
		
		Platform platform = loadInternalPlatform(PlatformTable.withId(platformId), emptyOptions());
		platform.clearFromAll();
		this.savePlatform(platform);
		return platform;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PlatformTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "platform";
	}
	@Override
	protected String getBeanName() {
		
		return "platform";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PlatformTokens.checkOptions(options, optionToCheck);
	
	}


		
	
	protected boolean isExtractProvinceListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PROVINCE_LIST);
 	}
 	protected boolean isAnalyzeProvinceListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeProvinceListEnabled();
 	}
	
	protected boolean isSaveProvinceListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.PROVINCE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractCityListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.CITY_LIST);
 	}
 	protected boolean isAnalyzeCityListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeCityListEnabled();
 	}
	
	protected boolean isSaveCityListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.CITY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractDistrictListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.DISTRICT_LIST);
 	}
 	protected boolean isAnalyzeDistrictListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeDistrictListEnabled();
 	}
	
	protected boolean isSaveDistrictListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.DISTRICT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractSchoolClassListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.SCHOOL_CLASS_LIST);
 	}
 	protected boolean isAnalyzeSchoolClassListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeSchoolClassListEnabled();
 	}
	
	protected boolean isSaveSchoolClassListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.SCHOOL_CLASS_LIST);
		
 	}
 	
		
	
	protected boolean isExtractTeacherListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.TEACHER_LIST);
 	}
 	protected boolean isAnalyzeTeacherListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeTeacherListEnabled();
 	}
	
	protected boolean isSaveTeacherListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.TEACHER_LIST);
		
 	}
 	
		
	
	protected boolean isExtractGuardianListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.GUARDIAN_LIST);
 	}
 	protected boolean isAnalyzeGuardianListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeGuardianListEnabled();
 	}
	
	protected boolean isSaveGuardianListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.GUARDIAN_LIST);
		
 	}
 	
		
	
	protected boolean isExtractQuestionListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.QUESTION_LIST);
 	}
 	protected boolean isAnalyzeQuestionListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeQuestionListEnabled();
 	}
	
	protected boolean isSaveQuestionListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.QUESTION_LIST);
		
 	}
 	
		
	
	protected boolean isExtractQuestionTypeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.QUESTION_TYPE_LIST);
 	}
 	protected boolean isAnalyzeQuestionTypeListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeQuestionTypeListEnabled();
 	}
	
	protected boolean isSaveQuestionTypeListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.QUESTION_TYPE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractQuestionSourceListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.QUESTION_SOURCE_LIST);
 	}
 	protected boolean isAnalyzeQuestionSourceListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeQuestionSourceListEnabled();
 	}
	
	protected boolean isSaveQuestionSourceListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.QUESTION_SOURCE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractSurveyStatusListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.SURVEY_STATUS_LIST);
 	}
 	protected boolean isAnalyzeSurveyStatusListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeSurveyStatusListEnabled();
 	}
	
	protected boolean isSaveSurveyStatusListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.SURVEY_STATUS_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWechatUserListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.WECHAT_USER_LIST);
 	}
 	protected boolean isAnalyzeWechatUserListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeWechatUserListEnabled();
 	}
	
	protected boolean isSaveWechatUserListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.WECHAT_USER_LIST);
		
 	}
 	
		
	
	protected boolean isExtractUserTypeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.USER_TYPE_LIST);
 	}
 	protected boolean isAnalyzeUserTypeListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeUserTypeListEnabled();
 	}
	
	protected boolean isSaveUserTypeListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.USER_TYPE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractChangeRequestListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.CHANGE_REQUEST_LIST);
 	}
 	protected boolean isAnalyzeChangeRequestListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeChangeRequestListEnabled();
 	}
	
	protected boolean isSaveChangeRequestListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.CHANGE_REQUEST_LIST);
		
 	}
 	
		
	
	protected boolean isExtractChangeRequestTypeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.CHANGE_REQUEST_TYPE_LIST);
 	}
 	protected boolean isAnalyzeChangeRequestTypeListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeChangeRequestTypeListEnabled();
 	}
	
	protected boolean isSaveChangeRequestTypeListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.CHANGE_REQUEST_TYPE_LIST);
		
 	}
 	
		

	

	protected PlatformMapper getPlatformMapper(){
		return new PlatformMapper();
	}

	
	
	protected Platform extractPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Platform platform = loadSingleObject(accessKey, getPlatformMapper());
			return platform;
		}catch(EmptyResultDataAccessException e){
			throw new PlatformNotFoundException("Platform("+accessKey+") is not found!");
		}

	}

	
	

	protected Platform loadInternalPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Platform platform = extractPlatform(accessKey, loadOptions);

		
		if(isExtractProvinceListEnabled(loadOptions)){
	 		extractProvinceList(platform, loadOptions);
 		}	
 		if(isAnalyzeProvinceListEnabled(loadOptions)){
	 		analyzeProvinceList(platform, loadOptions);
 		}
 		
		
		if(isExtractCityListEnabled(loadOptions)){
	 		extractCityList(platform, loadOptions);
 		}	
 		if(isAnalyzeCityListEnabled(loadOptions)){
	 		analyzeCityList(platform, loadOptions);
 		}
 		
		
		if(isExtractDistrictListEnabled(loadOptions)){
	 		extractDistrictList(platform, loadOptions);
 		}	
 		if(isAnalyzeDistrictListEnabled(loadOptions)){
	 		analyzeDistrictList(platform, loadOptions);
 		}
 		
		
		if(isExtractSchoolClassListEnabled(loadOptions)){
	 		extractSchoolClassList(platform, loadOptions);
 		}	
 		if(isAnalyzeSchoolClassListEnabled(loadOptions)){
	 		analyzeSchoolClassList(platform, loadOptions);
 		}
 		
		
		if(isExtractTeacherListEnabled(loadOptions)){
	 		extractTeacherList(platform, loadOptions);
 		}	
 		if(isAnalyzeTeacherListEnabled(loadOptions)){
	 		analyzeTeacherList(platform, loadOptions);
 		}
 		
		
		if(isExtractGuardianListEnabled(loadOptions)){
	 		extractGuardianList(platform, loadOptions);
 		}	
 		if(isAnalyzeGuardianListEnabled(loadOptions)){
	 		analyzeGuardianList(platform, loadOptions);
 		}
 		
		
		if(isExtractQuestionListEnabled(loadOptions)){
	 		extractQuestionList(platform, loadOptions);
 		}	
 		if(isAnalyzeQuestionListEnabled(loadOptions)){
	 		analyzeQuestionList(platform, loadOptions);
 		}
 		
		
		if(isExtractQuestionTypeListEnabled(loadOptions)){
	 		extractQuestionTypeList(platform, loadOptions);
 		}	
 		if(isAnalyzeQuestionTypeListEnabled(loadOptions)){
	 		analyzeQuestionTypeList(platform, loadOptions);
 		}
 		
		
		if(isExtractQuestionSourceListEnabled(loadOptions)){
	 		extractQuestionSourceList(platform, loadOptions);
 		}	
 		if(isAnalyzeQuestionSourceListEnabled(loadOptions)){
	 		analyzeQuestionSourceList(platform, loadOptions);
 		}
 		
		
		if(isExtractSurveyStatusListEnabled(loadOptions)){
	 		extractSurveyStatusList(platform, loadOptions);
 		}	
 		if(isAnalyzeSurveyStatusListEnabled(loadOptions)){
	 		analyzeSurveyStatusList(platform, loadOptions);
 		}
 		
		
		if(isExtractWechatUserListEnabled(loadOptions)){
	 		extractWechatUserList(platform, loadOptions);
 		}	
 		if(isAnalyzeWechatUserListEnabled(loadOptions)){
	 		analyzeWechatUserList(platform, loadOptions);
 		}
 		
		
		if(isExtractUserTypeListEnabled(loadOptions)){
	 		extractUserTypeList(platform, loadOptions);
 		}	
 		if(isAnalyzeUserTypeListEnabled(loadOptions)){
	 		analyzeUserTypeList(platform, loadOptions);
 		}
 		
		
		if(isExtractChangeRequestListEnabled(loadOptions)){
	 		extractChangeRequestList(platform, loadOptions);
 		}	
 		if(isAnalyzeChangeRequestListEnabled(loadOptions)){
	 		analyzeChangeRequestList(platform, loadOptions);
 		}
 		
		
		if(isExtractChangeRequestTypeListEnabled(loadOptions)){
	 		extractChangeRequestTypeList(platform, loadOptions);
 		}	
 		if(isAnalyzeChangeRequestTypeListEnabled(loadOptions)){
	 		analyzeChangeRequestTypeList(platform, loadOptions);
 		}
 		
		
		return platform;
		
	}

	
		
	protected void enhanceProvinceList(SmartList<Province> provinceList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractProvinceList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Province> provinceList = getProvinceDAO().findProvinceByPlatform(platform.getId(),options);
		if(provinceList != null){
			enhanceProvinceList(provinceList,options);
			platform.setProvinceList(provinceList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeProvinceList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Province> provinceList = platform.getProvinceList();
		if(provinceList != null){
			getProvinceDAO().analyzeProvinceByPlatform(provinceList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceCityList(SmartList<City> cityList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractCityList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<City> cityList = getCityDAO().findCityByPlatform(platform.getId(),options);
		if(cityList != null){
			enhanceCityList(cityList,options);
			platform.setCityList(cityList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeCityList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<City> cityList = platform.getCityList();
		if(cityList != null){
			getCityDAO().analyzeCityByPlatform(cityList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceDistrictList(SmartList<District> districtList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractDistrictList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<District> districtList = getDistrictDAO().findDistrictByPlatform(platform.getId(),options);
		if(districtList != null){
			enhanceDistrictList(districtList,options);
			platform.setDistrictList(districtList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeDistrictList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<District> districtList = platform.getDistrictList();
		if(districtList != null){
			getDistrictDAO().analyzeDistrictByPlatform(districtList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractSchoolClassList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<SchoolClass> schoolClassList = getSchoolClassDAO().findSchoolClassByPlatform(platform.getId(),options);
		if(schoolClassList != null){
			enhanceSchoolClassList(schoolClassList,options);
			platform.setSchoolClassList(schoolClassList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeSchoolClassList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();
		if(schoolClassList != null){
			getSchoolClassDAO().analyzeSchoolClassByPlatform(schoolClassList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractTeacherList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Teacher> teacherList = getTeacherDAO().findTeacherByPlatform(platform.getId(),options);
		if(teacherList != null){
			enhanceTeacherList(teacherList,options);
			platform.setTeacherList(teacherList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeTeacherList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Teacher> teacherList = platform.getTeacherList();
		if(teacherList != null){
			getTeacherDAO().analyzeTeacherByPlatform(teacherList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceGuardianList(SmartList<Guardian> guardianList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractGuardianList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Guardian> guardianList = getGuardianDAO().findGuardianByPlatform(platform.getId(),options);
		if(guardianList != null){
			enhanceGuardianList(guardianList,options);
			platform.setGuardianList(guardianList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeGuardianList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Guardian> guardianList = platform.getGuardianList();
		if(guardianList != null){
			getGuardianDAO().analyzeGuardianByPlatform(guardianList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceQuestionList(SmartList<Question> questionList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractQuestionList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Question> questionList = getQuestionDAO().findQuestionByPlatform(platform.getId(),options);
		if(questionList != null){
			enhanceQuestionList(questionList,options);
			platform.setQuestionList(questionList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeQuestionList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Question> questionList = platform.getQuestionList();
		if(questionList != null){
			getQuestionDAO().analyzeQuestionByPlatform(questionList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceQuestionTypeList(SmartList<QuestionType> questionTypeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractQuestionTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<QuestionType> questionTypeList = getQuestionTypeDAO().findQuestionTypeByPlatform(platform.getId(),options);
		if(questionTypeList != null){
			enhanceQuestionTypeList(questionTypeList,options);
			platform.setQuestionTypeList(questionTypeList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeQuestionTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<QuestionType> questionTypeList = platform.getQuestionTypeList();
		if(questionTypeList != null){
			getQuestionTypeDAO().analyzeQuestionTypeByPlatform(questionTypeList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceQuestionSourceList(SmartList<QuestionSource> questionSourceList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractQuestionSourceList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<QuestionSource> questionSourceList = getQuestionSourceDAO().findQuestionSourceByPlatform(platform.getId(),options);
		if(questionSourceList != null){
			enhanceQuestionSourceList(questionSourceList,options);
			platform.setQuestionSourceList(questionSourceList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeQuestionSourceList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<QuestionSource> questionSourceList = platform.getQuestionSourceList();
		if(questionSourceList != null){
			getQuestionSourceDAO().analyzeQuestionSourceByPlatform(questionSourceList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceSurveyStatusList(SmartList<SurveyStatus> surveyStatusList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractSurveyStatusList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<SurveyStatus> surveyStatusList = getSurveyStatusDAO().findSurveyStatusByPlatform(platform.getId(),options);
		if(surveyStatusList != null){
			enhanceSurveyStatusList(surveyStatusList,options);
			platform.setSurveyStatusList(surveyStatusList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeSurveyStatusList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<SurveyStatus> surveyStatusList = platform.getSurveyStatusList();
		if(surveyStatusList != null){
			getSurveyStatusDAO().analyzeSurveyStatusByPlatform(surveyStatusList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceWechatUserList(SmartList<WechatUser> wechatUserList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractWechatUserList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<WechatUser> wechatUserList = getWechatUserDAO().findWechatUserByPlatform(platform.getId(),options);
		if(wechatUserList != null){
			enhanceWechatUserList(wechatUserList,options);
			platform.setWechatUserList(wechatUserList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeWechatUserList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();
		if(wechatUserList != null){
			getWechatUserDAO().analyzeWechatUserByPlatform(wechatUserList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceUserTypeList(SmartList<UserType> userTypeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractUserTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<UserType> userTypeList = getUserTypeDAO().findUserTypeByPlatform(platform.getId(),options);
		if(userTypeList != null){
			enhanceUserTypeList(userTypeList,options);
			platform.setUserTypeList(userTypeList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeUserTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<UserType> userTypeList = platform.getUserTypeList();
		if(userTypeList != null){
			getUserTypeDAO().analyzeUserTypeByPlatform(userTypeList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractChangeRequestList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequest> changeRequestList = getChangeRequestDAO().findChangeRequestByPlatform(platform.getId(),options);
		if(changeRequestList != null){
			enhanceChangeRequestList(changeRequestList,options);
			platform.setChangeRequestList(changeRequestList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeChangeRequestList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();
		if(changeRequestList != null){
			getChangeRequestDAO().analyzeChangeRequestByPlatform(changeRequestList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractChangeRequestTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequestType> changeRequestTypeList = getChangeRequestTypeDAO().findChangeRequestTypeByPlatform(platform.getId(),options);
		if(changeRequestTypeList != null){
			enhanceChangeRequestTypeList(changeRequestTypeList,options);
			platform.setChangeRequestTypeList(changeRequestTypeList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeChangeRequestTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();
		if(changeRequestTypeList != null){
			getChangeRequestTypeDAO().analyzeChangeRequestTypeByPlatform(changeRequestTypeList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
		
 	
		
		
		

	

	protected Platform savePlatform(Platform  platform){
		
		if(!platform.isChanged()){
			return platform;
		}
		
		
		String SQL=this.getSavePlatformSQL(platform);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePlatformParameters(platform);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		platform.incVersion();
		return platform;
	
	}
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPlatformList(platformList);
		
		batchPlatformCreate((List<Platform>)lists[CREATE_LIST_INDEX]);
		
		batchPlatformUpdate((List<Platform>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Platform platform:platformList){
			if(platform.isChanged()){
				platform.incVersion();
			}
			
		
		}
		
		
		return platformList;
	}

	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		
		
		super.removeList(platformList, options);
		
		return platformList;
		
		
	}
	
	protected List<Object[]> preparePlatformBatchCreateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			Object [] parameters = preparePlatformCreateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePlatformBatchUpdateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			if(!platform.isChanged()){
				continue;
			}
			Object [] parameters = preparePlatformUpdateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPlatformCreate(List<Platform> platformList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePlatformBatchCreateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPlatformUpdate(List<Platform> platformList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePlatformBatchUpdateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPlatformList(List<Platform> platformList){
		
		List<Platform> platformCreateList=new ArrayList<Platform>();
		List<Platform> platformUpdateList=new ArrayList<Platform>();
		
		for(Platform platform: platformList){
			if(isUpdateRequest(platform)){
				platformUpdateList.add( platform);
				continue;
			}
			platformCreateList.add(platform);
		}
		
		return new Object[]{platformCreateList,platformUpdateList};
	}
	
	protected boolean isUpdateRequest(Platform platform){
 		return platform.getVersion() > 0;
 	}
 	protected String getSavePlatformSQL(Platform platform){
 		if(isUpdateRequest(platform)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePlatformParameters(Platform platform){
 		if(isUpdateRequest(platform) ){
 			return preparePlatformUpdateParameters(platform);
 		}
 		return preparePlatformCreateParameters(platform);
 	}
 	protected Object[] preparePlatformUpdateParameters(Platform platform){
 		Object[] parameters = new Object[5];
 
 		parameters[0] = platform.getName();
 		parameters[1] = platform.getDescription();		
 		parameters[2] = platform.nextVersion();
 		parameters[3] = platform.getId();
 		parameters[4] = platform.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePlatformCreateParameters(Platform platform){
		Object[] parameters = new Object[3];
		String newPlatformId=getNextId();
		platform.setId(newPlatformId);
		parameters[0] =  platform.getId();
 
 		parameters[1] = platform.getName();
 		parameters[2] = platform.getDescription();		
 				
 		return parameters;
 	}
 	
	protected Platform saveInternalPlatform(Platform platform, Map<String,Object> options){
		
		savePlatform(platform);

		
		if(isSaveProvinceListEnabled(options)){
	 		saveProvinceList(platform, options);
	 		//removeProvinceList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveCityListEnabled(options)){
	 		saveCityList(platform, options);
	 		//removeCityList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveDistrictListEnabled(options)){
	 		saveDistrictList(platform, options);
	 		//removeDistrictList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveSchoolClassListEnabled(options)){
	 		saveSchoolClassList(platform, options);
	 		//removeSchoolClassList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveTeacherListEnabled(options)){
	 		saveTeacherList(platform, options);
	 		//removeTeacherList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveGuardianListEnabled(options)){
	 		saveGuardianList(platform, options);
	 		//removeGuardianList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveQuestionListEnabled(options)){
	 		saveQuestionList(platform, options);
	 		//removeQuestionList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveQuestionTypeListEnabled(options)){
	 		saveQuestionTypeList(platform, options);
	 		//removeQuestionTypeList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveQuestionSourceListEnabled(options)){
	 		saveQuestionSourceList(platform, options);
	 		//removeQuestionSourceList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveSurveyStatusListEnabled(options)){
	 		saveSurveyStatusList(platform, options);
	 		//removeSurveyStatusList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveWechatUserListEnabled(options)){
	 		saveWechatUserList(platform, options);
	 		//removeWechatUserList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveUserTypeListEnabled(options)){
	 		saveUserTypeList(platform, options);
	 		//removeUserTypeList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveChangeRequestListEnabled(options)){
	 		saveChangeRequestList(platform, options);
	 		//removeChangeRequestList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveChangeRequestTypeListEnabled(options)){
	 		saveChangeRequestTypeList(platform, options);
	 		//removeChangeRequestTypeList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		return platform;
		
	}
	
	
	
	//======================================================================================
	

	
	public Platform planToRemoveProvinceList(Platform platform, String provinceIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Province.PLATFORM_PROPERTY, platform.getId());
		key.put(Province.ID_PROPERTY, provinceIds);
		
		SmartList<Province> externalProvinceList = getProvinceDAO().
				findProvinceWithKey(key, options);
		if(externalProvinceList == null){
			return platform;
		}
		if(externalProvinceList.isEmpty()){
			return platform;
		}
		
		for(Province provinceItem: externalProvinceList){

			provinceItem.clearFromAll();
		}
		
		
		SmartList<Province> provinceList = platform.getProvinceList();		
		provinceList.addAllToRemoveList(externalProvinceList);
		return platform;	
	
	}


	public Platform planToRemoveCityList(Platform platform, String cityIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PLATFORM_PROPERTY, platform.getId());
		key.put(City.ID_PROPERTY, cityIds);
		
		SmartList<City> externalCityList = getCityDAO().
				findCityWithKey(key, options);
		if(externalCityList == null){
			return platform;
		}
		if(externalCityList.isEmpty()){
			return platform;
		}
		
		for(City cityItem: externalCityList){

			cityItem.clearFromAll();
		}
		
		
		SmartList<City> cityList = platform.getCityList();		
		cityList.addAllToRemoveList(externalCityList);
		return platform;	
	
	}


	//disconnect Platform with province in City
	public Platform planToRemoveCityListWithProvince(Platform platform, String provinceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PLATFORM_PROPERTY, platform.getId());
		key.put(City.PROVINCE_PROPERTY, provinceId);
		
		SmartList<City> externalCityList = getCityDAO().
				findCityWithKey(key, options);
		if(externalCityList == null){
			return platform;
		}
		if(externalCityList.isEmpty()){
			return platform;
		}
		
		for(City cityItem: externalCityList){
			cityItem.clearProvince();
			cityItem.clearPlatform();
			
		}
		
		
		SmartList<City> cityList = platform.getCityList();		
		cityList.addAllToRemoveList(externalCityList);
		return platform;
	}
	
	public int countCityListWithProvince(String platformId, String provinceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PLATFORM_PROPERTY, platformId);
		key.put(City.PROVINCE_PROPERTY, provinceId);
		
		int count = getCityDAO().countCityWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveDistrictList(Platform platform, String districtIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.PLATFORM_PROPERTY, platform.getId());
		key.put(District.ID_PROPERTY, districtIds);
		
		SmartList<District> externalDistrictList = getDistrictDAO().
				findDistrictWithKey(key, options);
		if(externalDistrictList == null){
			return platform;
		}
		if(externalDistrictList.isEmpty()){
			return platform;
		}
		
		for(District districtItem: externalDistrictList){

			districtItem.clearFromAll();
		}
		
		
		SmartList<District> districtList = platform.getDistrictList();		
		districtList.addAllToRemoveList(externalDistrictList);
		return platform;	
	
	}


	//disconnect Platform with city in District
	public Platform planToRemoveDistrictListWithCity(Platform platform, String cityId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.PLATFORM_PROPERTY, platform.getId());
		key.put(District.CITY_PROPERTY, cityId);
		
		SmartList<District> externalDistrictList = getDistrictDAO().
				findDistrictWithKey(key, options);
		if(externalDistrictList == null){
			return platform;
		}
		if(externalDistrictList.isEmpty()){
			return platform;
		}
		
		for(District districtItem: externalDistrictList){
			districtItem.clearCity();
			districtItem.clearPlatform();
			
		}
		
		
		SmartList<District> districtList = platform.getDistrictList();		
		districtList.addAllToRemoveList(externalDistrictList);
		return platform;
	}
	
	public int countDistrictListWithCity(String platformId, String cityId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.PLATFORM_PROPERTY, platformId);
		key.put(District.CITY_PROPERTY, cityId);
		
		int count = getDistrictDAO().countDistrictWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveSchoolClassList(Platform platform, String schoolClassIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.PLATFORM_PROPERTY, platform.getId());
		key.put(SchoolClass.ID_PROPERTY, schoolClassIds);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return platform;
		}
		if(externalSchoolClassList.isEmpty()){
			return platform;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){

			schoolClassItem.clearFromAll();
		}
		
		
		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return platform;	
	
	}


	//disconnect Platform with class_teacher in SchoolClass
	public Platform planToRemoveSchoolClassListWithClassTeacher(Platform platform, String classTeacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.PLATFORM_PROPERTY, platform.getId());
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, classTeacherId);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return platform;
		}
		if(externalSchoolClassList.isEmpty()){
			return platform;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){
			schoolClassItem.clearClassTeacher();
			schoolClassItem.clearPlatform();
			
		}
		
		
		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return platform;
	}
	
	public int countSchoolClassListWithClassTeacher(String platformId, String classTeacherId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.PLATFORM_PROPERTY, platformId);
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, classTeacherId);
		
		int count = getSchoolClassDAO().countSchoolClassWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with cq in SchoolClass
	public Platform planToRemoveSchoolClassListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.PLATFORM_PROPERTY, platform.getId());
		key.put(SchoolClass.CQ_PROPERTY, cqId);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return platform;
		}
		if(externalSchoolClassList.isEmpty()){
			return platform;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){
			schoolClassItem.clearCq();
			schoolClassItem.clearPlatform();
			
		}
		
		
		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return platform;
	}
	
	public int countSchoolClassListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.PLATFORM_PROPERTY, platformId);
		key.put(SchoolClass.CQ_PROPERTY, cqId);
		
		int count = getSchoolClassDAO().countSchoolClassWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveTeacherList(Platform platform, String teacherIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.PLATFORM_PROPERTY, platform.getId());
		key.put(Teacher.ID_PROPERTY, teacherIds);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return platform;
		}
		if(externalTeacherList.isEmpty()){
			return platform;
		}
		
		for(Teacher teacherItem: externalTeacherList){

			teacherItem.clearFromAll();
		}
		
		
		SmartList<Teacher> teacherList = platform.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return platform;	
	
	}


	//disconnect Platform with cq in Teacher
	public Platform planToRemoveTeacherListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.PLATFORM_PROPERTY, platform.getId());
		key.put(Teacher.CQ_PROPERTY, cqId);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return platform;
		}
		if(externalTeacherList.isEmpty()){
			return platform;
		}
		
		for(Teacher teacherItem: externalTeacherList){
			teacherItem.clearCq();
			teacherItem.clearPlatform();
			
		}
		
		
		SmartList<Teacher> teacherList = platform.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return platform;
	}
	
	public int countTeacherListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.PLATFORM_PROPERTY, platformId);
		key.put(Teacher.CQ_PROPERTY, cqId);
		
		int count = getTeacherDAO().countTeacherWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveGuardianList(Platform platform, String guardianIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platform.getId());
		key.put(Guardian.ID_PROPERTY, guardianIds);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return platform;
		}
		if(externalGuardianList.isEmpty()){
			return platform;
		}
		
		for(Guardian guardianItem: externalGuardianList){

			guardianItem.clearFromAll();
		}
		
		
		SmartList<Guardian> guardianList = platform.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return platform;	
	
	}


	//disconnect Platform with address in Guardian
	public Platform planToRemoveGuardianListWithAddress(Platform platform, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platform.getId());
		key.put(Guardian.ADDRESS_PROPERTY, addressId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return platform;
		}
		if(externalGuardianList.isEmpty()){
			return platform;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearAddress();
			guardianItem.clearPlatform();
			
		}
		
		
		SmartList<Guardian> guardianList = platform.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return platform;
	}
	
	public int countGuardianListWithAddress(String platformId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		key.put(Guardian.ADDRESS_PROPERTY, addressId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with wechat_user in Guardian
	public Platform planToRemoveGuardianListWithWechatUser(Platform platform, String wechatUserId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platform.getId());
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return platform;
		}
		if(externalGuardianList.isEmpty()){
			return platform;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearWechatUser();
			guardianItem.clearPlatform();
			
		}
		
		
		SmartList<Guardian> guardianList = platform.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return platform;
	}
	
	public int countGuardianListWithWechatUser(String platformId, String wechatUserId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		key.put(Guardian.WECHAT_USER_PROPERTY, wechatUserId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with cq in Guardian
	public Platform planToRemoveGuardianListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platform.getId());
		key.put(Guardian.CQ_PROPERTY, cqId);
		
		SmartList<Guardian> externalGuardianList = getGuardianDAO().
				findGuardianWithKey(key, options);
		if(externalGuardianList == null){
			return platform;
		}
		if(externalGuardianList.isEmpty()){
			return platform;
		}
		
		for(Guardian guardianItem: externalGuardianList){
			guardianItem.clearCq();
			guardianItem.clearPlatform();
			
		}
		
		
		SmartList<Guardian> guardianList = platform.getGuardianList();		
		guardianList.addAllToRemoveList(externalGuardianList);
		return platform;
	}
	
	public int countGuardianListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, platformId);
		key.put(Guardian.CQ_PROPERTY, cqId);
		
		int count = getGuardianDAO().countGuardianWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveQuestionList(Platform platform, String questionIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platform.getId());
		key.put(Question.ID_PROPERTY, questionIds);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return platform;
		}
		if(externalQuestionList.isEmpty()){
			return platform;
		}
		
		for(Question questionItem: externalQuestionList){

			questionItem.clearFromAll();
		}
		
		
		SmartList<Question> questionList = platform.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return platform;	
	
	}


	//disconnect Platform with question_type in Question
	public Platform planToRemoveQuestionListWithQuestionType(Platform platform, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platform.getId());
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return platform;
		}
		if(externalQuestionList.isEmpty()){
			return platform;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearQuestionType();
			questionItem.clearPlatform();
			
		}
		
		
		SmartList<Question> questionList = platform.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return platform;
	}
	
	public int countQuestionListWithQuestionType(String platformId, String questionTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platformId);
		key.put(Question.QUESTION_TYPE_PROPERTY, questionTypeId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveQuestionTypeList(Platform platform, String questionTypeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(QuestionType.PLATFORM_PROPERTY, platform.getId());
		key.put(QuestionType.ID_PROPERTY, questionTypeIds);
		
		SmartList<QuestionType> externalQuestionTypeList = getQuestionTypeDAO().
				findQuestionTypeWithKey(key, options);
		if(externalQuestionTypeList == null){
			return platform;
		}
		if(externalQuestionTypeList.isEmpty()){
			return platform;
		}
		
		for(QuestionType questionTypeItem: externalQuestionTypeList){

			questionTypeItem.clearFromAll();
		}
		
		
		SmartList<QuestionType> questionTypeList = platform.getQuestionTypeList();		
		questionTypeList.addAllToRemoveList(externalQuestionTypeList);
		return platform;	
	
	}


	public Platform planToRemoveQuestionSourceList(Platform platform, String questionSourceIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(QuestionSource.PLATFORM_PROPERTY, platform.getId());
		key.put(QuestionSource.ID_PROPERTY, questionSourceIds);
		
		SmartList<QuestionSource> externalQuestionSourceList = getQuestionSourceDAO().
				findQuestionSourceWithKey(key, options);
		if(externalQuestionSourceList == null){
			return platform;
		}
		if(externalQuestionSourceList.isEmpty()){
			return platform;
		}
		
		for(QuestionSource questionSourceItem: externalQuestionSourceList){

			questionSourceItem.clearFromAll();
		}
		
		
		SmartList<QuestionSource> questionSourceList = platform.getQuestionSourceList();		
		questionSourceList.addAllToRemoveList(externalQuestionSourceList);
		return platform;	
	
	}


	public Platform planToRemoveSurveyStatusList(Platform platform, String surveyStatusIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SurveyStatus.PLATFORM_PROPERTY, platform.getId());
		key.put(SurveyStatus.ID_PROPERTY, surveyStatusIds);
		
		SmartList<SurveyStatus> externalSurveyStatusList = getSurveyStatusDAO().
				findSurveyStatusWithKey(key, options);
		if(externalSurveyStatusList == null){
			return platform;
		}
		if(externalSurveyStatusList.isEmpty()){
			return platform;
		}
		
		for(SurveyStatus surveyStatusItem: externalSurveyStatusList){

			surveyStatusItem.clearFromAll();
		}
		
		
		SmartList<SurveyStatus> surveyStatusList = platform.getSurveyStatusList();		
		surveyStatusList.addAllToRemoveList(externalSurveyStatusList);
		return platform;	
	
	}


	public Platform planToRemoveWechatUserList(Platform platform, String wechatUserIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.PLATFORM_PROPERTY, platform.getId());
		key.put(WechatUser.ID_PROPERTY, wechatUserIds);
		
		SmartList<WechatUser> externalWechatUserList = getWechatUserDAO().
				findWechatUserWithKey(key, options);
		if(externalWechatUserList == null){
			return platform;
		}
		if(externalWechatUserList.isEmpty()){
			return platform;
		}
		
		for(WechatUser wechatUserItem: externalWechatUserList){

			wechatUserItem.clearFromAll();
		}
		
		
		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();		
		wechatUserList.addAllToRemoveList(externalWechatUserList);
		return platform;	
	
	}


	//disconnect Platform with address in WechatUser
	public Platform planToRemoveWechatUserListWithAddress(Platform platform, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.PLATFORM_PROPERTY, platform.getId());
		key.put(WechatUser.ADDRESS_PROPERTY, addressId);
		
		SmartList<WechatUser> externalWechatUserList = getWechatUserDAO().
				findWechatUserWithKey(key, options);
		if(externalWechatUserList == null){
			return platform;
		}
		if(externalWechatUserList.isEmpty()){
			return platform;
		}
		
		for(WechatUser wechatUserItem: externalWechatUserList){
			wechatUserItem.clearAddress();
			wechatUserItem.clearPlatform();
			
		}
		
		
		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();		
		wechatUserList.addAllToRemoveList(externalWechatUserList);
		return platform;
	}
	
	public int countWechatUserListWithAddress(String platformId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.PLATFORM_PROPERTY, platformId);
		key.put(WechatUser.ADDRESS_PROPERTY, addressId);
		
		int count = getWechatUserDAO().countWechatUserWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with user_type in WechatUser
	public Platform planToRemoveWechatUserListWithUserType(Platform platform, String userTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.PLATFORM_PROPERTY, platform.getId());
		key.put(WechatUser.USER_TYPE_PROPERTY, userTypeId);
		
		SmartList<WechatUser> externalWechatUserList = getWechatUserDAO().
				findWechatUserWithKey(key, options);
		if(externalWechatUserList == null){
			return platform;
		}
		if(externalWechatUserList.isEmpty()){
			return platform;
		}
		
		for(WechatUser wechatUserItem: externalWechatUserList){
			wechatUserItem.clearUserType();
			wechatUserItem.clearPlatform();
			
		}
		
		
		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();		
		wechatUserList.addAllToRemoveList(externalWechatUserList);
		return platform;
	}
	
	public int countWechatUserListWithUserType(String platformId, String userTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.PLATFORM_PROPERTY, platformId);
		key.put(WechatUser.USER_TYPE_PROPERTY, userTypeId);
		
		int count = getWechatUserDAO().countWechatUserWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveUserTypeList(Platform platform, String userTypeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserType.PLATFORM_PROPERTY, platform.getId());
		key.put(UserType.ID_PROPERTY, userTypeIds);
		
		SmartList<UserType> externalUserTypeList = getUserTypeDAO().
				findUserTypeWithKey(key, options);
		if(externalUserTypeList == null){
			return platform;
		}
		if(externalUserTypeList.isEmpty()){
			return platform;
		}
		
		for(UserType userTypeItem: externalUserTypeList){

			userTypeItem.clearFromAll();
		}
		
		
		SmartList<UserType> userTypeList = platform.getUserTypeList();		
		userTypeList.addAllToRemoveList(externalUserTypeList);
		return platform;	
	
	}


	public Platform planToRemoveChangeRequestList(Platform platform, String changeRequestIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, platform.getId());
		key.put(ChangeRequest.ID_PROPERTY, changeRequestIds);
		
		SmartList<ChangeRequest> externalChangeRequestList = getChangeRequestDAO().
				findChangeRequestWithKey(key, options);
		if(externalChangeRequestList == null){
			return platform;
		}
		if(externalChangeRequestList.isEmpty()){
			return platform;
		}
		
		for(ChangeRequest changeRequestItem: externalChangeRequestList){

			changeRequestItem.clearFromAll();
		}
		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();		
		changeRequestList.addAllToRemoveList(externalChangeRequestList);
		return platform;	
	
	}


	//disconnect Platform with request_type in ChangeRequest
	public Platform planToRemoveChangeRequestListWithRequestType(Platform platform, String requestTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, platform.getId());
		key.put(ChangeRequest.REQUEST_TYPE_PROPERTY, requestTypeId);
		
		SmartList<ChangeRequest> externalChangeRequestList = getChangeRequestDAO().
				findChangeRequestWithKey(key, options);
		if(externalChangeRequestList == null){
			return platform;
		}
		if(externalChangeRequestList.isEmpty()){
			return platform;
		}
		
		for(ChangeRequest changeRequestItem: externalChangeRequestList){
			changeRequestItem.clearRequestType();
			changeRequestItem.clearPlatform();
			
		}
		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();		
		changeRequestList.addAllToRemoveList(externalChangeRequestList);
		return platform;
	}
	
	public int countChangeRequestListWithRequestType(String platformId, String requestTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, platformId);
		key.put(ChangeRequest.REQUEST_TYPE_PROPERTY, requestTypeId);
		
		int count = getChangeRequestDAO().countChangeRequestWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveChangeRequestTypeList(Platform platform, String changeRequestTypeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestType.PLATFORM_PROPERTY, platform.getId());
		key.put(ChangeRequestType.ID_PROPERTY, changeRequestTypeIds);
		
		SmartList<ChangeRequestType> externalChangeRequestTypeList = getChangeRequestTypeDAO().
				findChangeRequestTypeWithKey(key, options);
		if(externalChangeRequestTypeList == null){
			return platform;
		}
		if(externalChangeRequestTypeList.isEmpty()){
			return platform;
		}
		
		for(ChangeRequestType changeRequestTypeItem: externalChangeRequestTypeList){

			changeRequestTypeItem.clearFromAll();
		}
		
		
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();		
		changeRequestTypeList.addAllToRemoveList(externalChangeRequestTypeList);
		return platform;	
	
	}



		
	protected Platform saveProvinceList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Province> provinceList = platform.getProvinceList();
		if(provinceList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Province> mergedUpdateProvinceList = new SmartList<Province>();
		
		
		mergedUpdateProvinceList.addAll(provinceList); 
		if(provinceList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateProvinceList.addAll(provinceList.getToRemoveList());
			provinceList.removeAll(provinceList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getProvinceDAO().saveProvinceList(mergedUpdateProvinceList,options);
		
		if(provinceList.getToRemoveList() != null){
			provinceList.removeAll(provinceList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeProvinceList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Province> provinceList = platform.getProvinceList();
		if(provinceList == null){
			return platform;
		}	
	
		SmartList<Province> toRemoveProvinceList = provinceList.getToRemoveList();
		
		if(toRemoveProvinceList == null){
			return platform;
		}
		if(toRemoveProvinceList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getProvinceDAO().removeProvinceList(toRemoveProvinceList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveCityList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<City> cityList = platform.getCityList();
		if(cityList == null){
			//null list means nothing
			return platform;
		}
		SmartList<City> mergedUpdateCityList = new SmartList<City>();
		
		
		mergedUpdateCityList.addAll(cityList); 
		if(cityList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateCityList.addAll(cityList.getToRemoveList());
			cityList.removeAll(cityList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getCityDAO().saveCityList(mergedUpdateCityList,options);
		
		if(cityList.getToRemoveList() != null){
			cityList.removeAll(cityList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeCityList(Platform platform, Map<String,Object> options){
	
	
		SmartList<City> cityList = platform.getCityList();
		if(cityList == null){
			return platform;
		}	
	
		SmartList<City> toRemoveCityList = cityList.getToRemoveList();
		
		if(toRemoveCityList == null){
			return platform;
		}
		if(toRemoveCityList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getCityDAO().removeCityList(toRemoveCityList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveDistrictList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<District> districtList = platform.getDistrictList();
		if(districtList == null){
			//null list means nothing
			return platform;
		}
		SmartList<District> mergedUpdateDistrictList = new SmartList<District>();
		
		
		mergedUpdateDistrictList.addAll(districtList); 
		if(districtList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateDistrictList.addAll(districtList.getToRemoveList());
			districtList.removeAll(districtList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getDistrictDAO().saveDistrictList(mergedUpdateDistrictList,options);
		
		if(districtList.getToRemoveList() != null){
			districtList.removeAll(districtList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeDistrictList(Platform platform, Map<String,Object> options){
	
	
		SmartList<District> districtList = platform.getDistrictList();
		if(districtList == null){
			return platform;
		}	
	
		SmartList<District> toRemoveDistrictList = districtList.getToRemoveList();
		
		if(toRemoveDistrictList == null){
			return platform;
		}
		if(toRemoveDistrictList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getDistrictDAO().removeDistrictList(toRemoveDistrictList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveSchoolClassList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();
		if(schoolClassList == null){
			//null list means nothing
			return platform;
		}
		SmartList<SchoolClass> mergedUpdateSchoolClassList = new SmartList<SchoolClass>();
		
		
		mergedUpdateSchoolClassList.addAll(schoolClassList); 
		if(schoolClassList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateSchoolClassList.addAll(schoolClassList.getToRemoveList());
			schoolClassList.removeAll(schoolClassList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getSchoolClassDAO().saveSchoolClassList(mergedUpdateSchoolClassList,options);
		
		if(schoolClassList.getToRemoveList() != null){
			schoolClassList.removeAll(schoolClassList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeSchoolClassList(Platform platform, Map<String,Object> options){
	
	
		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();
		if(schoolClassList == null){
			return platform;
		}	
	
		SmartList<SchoolClass> toRemoveSchoolClassList = schoolClassList.getToRemoveList();
		
		if(toRemoveSchoolClassList == null){
			return platform;
		}
		if(toRemoveSchoolClassList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getSchoolClassDAO().removeSchoolClassList(toRemoveSchoolClassList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveTeacherList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Teacher> teacherList = platform.getTeacherList();
		if(teacherList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Teacher> mergedUpdateTeacherList = new SmartList<Teacher>();
		
		
		mergedUpdateTeacherList.addAll(teacherList); 
		if(teacherList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateTeacherList.addAll(teacherList.getToRemoveList());
			teacherList.removeAll(teacherList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getTeacherDAO().saveTeacherList(mergedUpdateTeacherList,options);
		
		if(teacherList.getToRemoveList() != null){
			teacherList.removeAll(teacherList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeTeacherList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Teacher> teacherList = platform.getTeacherList();
		if(teacherList == null){
			return platform;
		}	
	
		SmartList<Teacher> toRemoveTeacherList = teacherList.getToRemoveList();
		
		if(toRemoveTeacherList == null){
			return platform;
		}
		if(toRemoveTeacherList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getTeacherDAO().removeTeacherList(toRemoveTeacherList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveGuardianList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Guardian> guardianList = platform.getGuardianList();
		if(guardianList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Guardian> mergedUpdateGuardianList = new SmartList<Guardian>();
		
		
		mergedUpdateGuardianList.addAll(guardianList); 
		if(guardianList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateGuardianList.addAll(guardianList.getToRemoveList());
			guardianList.removeAll(guardianList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getGuardianDAO().saveGuardianList(mergedUpdateGuardianList,options);
		
		if(guardianList.getToRemoveList() != null){
			guardianList.removeAll(guardianList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeGuardianList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Guardian> guardianList = platform.getGuardianList();
		if(guardianList == null){
			return platform;
		}	
	
		SmartList<Guardian> toRemoveGuardianList = guardianList.getToRemoveList();
		
		if(toRemoveGuardianList == null){
			return platform;
		}
		if(toRemoveGuardianList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getGuardianDAO().removeGuardianList(toRemoveGuardianList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveQuestionList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Question> questionList = platform.getQuestionList();
		if(questionList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Question> mergedUpdateQuestionList = new SmartList<Question>();
		
		
		mergedUpdateQuestionList.addAll(questionList); 
		if(questionList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateQuestionList.addAll(questionList.getToRemoveList());
			questionList.removeAll(questionList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getQuestionDAO().saveQuestionList(mergedUpdateQuestionList,options);
		
		if(questionList.getToRemoveList() != null){
			questionList.removeAll(questionList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeQuestionList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Question> questionList = platform.getQuestionList();
		if(questionList == null){
			return platform;
		}	
	
		SmartList<Question> toRemoveQuestionList = questionList.getToRemoveList();
		
		if(toRemoveQuestionList == null){
			return platform;
		}
		if(toRemoveQuestionList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getQuestionDAO().removeQuestionList(toRemoveQuestionList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveQuestionTypeList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<QuestionType> questionTypeList = platform.getQuestionTypeList();
		if(questionTypeList == null){
			//null list means nothing
			return platform;
		}
		SmartList<QuestionType> mergedUpdateQuestionTypeList = new SmartList<QuestionType>();
		
		
		mergedUpdateQuestionTypeList.addAll(questionTypeList); 
		if(questionTypeList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateQuestionTypeList.addAll(questionTypeList.getToRemoveList());
			questionTypeList.removeAll(questionTypeList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getQuestionTypeDAO().saveQuestionTypeList(mergedUpdateQuestionTypeList,options);
		
		if(questionTypeList.getToRemoveList() != null){
			questionTypeList.removeAll(questionTypeList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeQuestionTypeList(Platform platform, Map<String,Object> options){
	
	
		SmartList<QuestionType> questionTypeList = platform.getQuestionTypeList();
		if(questionTypeList == null){
			return platform;
		}	
	
		SmartList<QuestionType> toRemoveQuestionTypeList = questionTypeList.getToRemoveList();
		
		if(toRemoveQuestionTypeList == null){
			return platform;
		}
		if(toRemoveQuestionTypeList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getQuestionTypeDAO().removeQuestionTypeList(toRemoveQuestionTypeList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveQuestionSourceList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<QuestionSource> questionSourceList = platform.getQuestionSourceList();
		if(questionSourceList == null){
			//null list means nothing
			return platform;
		}
		SmartList<QuestionSource> mergedUpdateQuestionSourceList = new SmartList<QuestionSource>();
		
		
		mergedUpdateQuestionSourceList.addAll(questionSourceList); 
		if(questionSourceList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateQuestionSourceList.addAll(questionSourceList.getToRemoveList());
			questionSourceList.removeAll(questionSourceList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getQuestionSourceDAO().saveQuestionSourceList(mergedUpdateQuestionSourceList,options);
		
		if(questionSourceList.getToRemoveList() != null){
			questionSourceList.removeAll(questionSourceList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeQuestionSourceList(Platform platform, Map<String,Object> options){
	
	
		SmartList<QuestionSource> questionSourceList = platform.getQuestionSourceList();
		if(questionSourceList == null){
			return platform;
		}	
	
		SmartList<QuestionSource> toRemoveQuestionSourceList = questionSourceList.getToRemoveList();
		
		if(toRemoveQuestionSourceList == null){
			return platform;
		}
		if(toRemoveQuestionSourceList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getQuestionSourceDAO().removeQuestionSourceList(toRemoveQuestionSourceList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveSurveyStatusList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<SurveyStatus> surveyStatusList = platform.getSurveyStatusList();
		if(surveyStatusList == null){
			//null list means nothing
			return platform;
		}
		SmartList<SurveyStatus> mergedUpdateSurveyStatusList = new SmartList<SurveyStatus>();
		
		
		mergedUpdateSurveyStatusList.addAll(surveyStatusList); 
		if(surveyStatusList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateSurveyStatusList.addAll(surveyStatusList.getToRemoveList());
			surveyStatusList.removeAll(surveyStatusList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getSurveyStatusDAO().saveSurveyStatusList(mergedUpdateSurveyStatusList,options);
		
		if(surveyStatusList.getToRemoveList() != null){
			surveyStatusList.removeAll(surveyStatusList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeSurveyStatusList(Platform platform, Map<String,Object> options){
	
	
		SmartList<SurveyStatus> surveyStatusList = platform.getSurveyStatusList();
		if(surveyStatusList == null){
			return platform;
		}	
	
		SmartList<SurveyStatus> toRemoveSurveyStatusList = surveyStatusList.getToRemoveList();
		
		if(toRemoveSurveyStatusList == null){
			return platform;
		}
		if(toRemoveSurveyStatusList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getSurveyStatusDAO().removeSurveyStatusList(toRemoveSurveyStatusList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveWechatUserList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();
		if(wechatUserList == null){
			//null list means nothing
			return platform;
		}
		SmartList<WechatUser> mergedUpdateWechatUserList = new SmartList<WechatUser>();
		
		
		mergedUpdateWechatUserList.addAll(wechatUserList); 
		if(wechatUserList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateWechatUserList.addAll(wechatUserList.getToRemoveList());
			wechatUserList.removeAll(wechatUserList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getWechatUserDAO().saveWechatUserList(mergedUpdateWechatUserList,options);
		
		if(wechatUserList.getToRemoveList() != null){
			wechatUserList.removeAll(wechatUserList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeWechatUserList(Platform platform, Map<String,Object> options){
	
	
		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();
		if(wechatUserList == null){
			return platform;
		}	
	
		SmartList<WechatUser> toRemoveWechatUserList = wechatUserList.getToRemoveList();
		
		if(toRemoveWechatUserList == null){
			return platform;
		}
		if(toRemoveWechatUserList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getWechatUserDAO().removeWechatUserList(toRemoveWechatUserList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveUserTypeList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<UserType> userTypeList = platform.getUserTypeList();
		if(userTypeList == null){
			//null list means nothing
			return platform;
		}
		SmartList<UserType> mergedUpdateUserTypeList = new SmartList<UserType>();
		
		
		mergedUpdateUserTypeList.addAll(userTypeList); 
		if(userTypeList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateUserTypeList.addAll(userTypeList.getToRemoveList());
			userTypeList.removeAll(userTypeList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getUserTypeDAO().saveUserTypeList(mergedUpdateUserTypeList,options);
		
		if(userTypeList.getToRemoveList() != null){
			userTypeList.removeAll(userTypeList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeUserTypeList(Platform platform, Map<String,Object> options){
	
	
		SmartList<UserType> userTypeList = platform.getUserTypeList();
		if(userTypeList == null){
			return platform;
		}	
	
		SmartList<UserType> toRemoveUserTypeList = userTypeList.getToRemoveList();
		
		if(toRemoveUserTypeList == null){
			return platform;
		}
		if(toRemoveUserTypeList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getUserTypeDAO().removeUserTypeList(toRemoveUserTypeList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveChangeRequestList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();
		if(changeRequestList == null){
			//null list means nothing
			return platform;
		}
		SmartList<ChangeRequest> mergedUpdateChangeRequestList = new SmartList<ChangeRequest>();
		
		
		mergedUpdateChangeRequestList.addAll(changeRequestList); 
		if(changeRequestList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateChangeRequestList.addAll(changeRequestList.getToRemoveList());
			changeRequestList.removeAll(changeRequestList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getChangeRequestDAO().saveChangeRequestList(mergedUpdateChangeRequestList,options);
		
		if(changeRequestList.getToRemoveList() != null){
			changeRequestList.removeAll(changeRequestList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeChangeRequestList(Platform platform, Map<String,Object> options){
	
	
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();
		if(changeRequestList == null){
			return platform;
		}	
	
		SmartList<ChangeRequest> toRemoveChangeRequestList = changeRequestList.getToRemoveList();
		
		if(toRemoveChangeRequestList == null){
			return platform;
		}
		if(toRemoveChangeRequestList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getChangeRequestDAO().removeChangeRequestList(toRemoveChangeRequestList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveChangeRequestTypeList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();
		if(changeRequestTypeList == null){
			//null list means nothing
			return platform;
		}
		SmartList<ChangeRequestType> mergedUpdateChangeRequestTypeList = new SmartList<ChangeRequestType>();
		
		
		mergedUpdateChangeRequestTypeList.addAll(changeRequestTypeList); 
		if(changeRequestTypeList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateChangeRequestTypeList.addAll(changeRequestTypeList.getToRemoveList());
			changeRequestTypeList.removeAll(changeRequestTypeList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getChangeRequestTypeDAO().saveChangeRequestTypeList(mergedUpdateChangeRequestTypeList,options);
		
		if(changeRequestTypeList.getToRemoveList() != null){
			changeRequestTypeList.removeAll(changeRequestTypeList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeChangeRequestTypeList(Platform platform, Map<String,Object> options){
	
	
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();
		if(changeRequestTypeList == null){
			return platform;
		}	
	
		SmartList<ChangeRequestType> toRemoveChangeRequestTypeList = changeRequestTypeList.getToRemoveList();
		
		if(toRemoveChangeRequestTypeList == null){
			return platform;
		}
		if(toRemoveChangeRequestTypeList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getChangeRequestTypeDAO().removeChangeRequestTypeList(toRemoveChangeRequestTypeList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		

	public Platform present(Platform platform,Map<String, Object> options){
	
		presentProvinceList(platform,options);
		presentCityList(platform,options);
		presentDistrictList(platform,options);
		presentSchoolClassList(platform,options);
		presentTeacherList(platform,options);
		presentGuardianList(platform,options);
		presentQuestionList(platform,options);
		presentQuestionTypeList(platform,options);
		presentQuestionSourceList(platform,options);
		presentSurveyStatusList(platform,options);
		presentWechatUserList(platform,options);
		presentUserTypeList(platform,options);
		presentChangeRequestList(platform,options);
		presentChangeRequestTypeList(platform,options);

		return platform;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentProvinceList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Province> provinceList = platform.getProvinceList();		
				SmartList<Province> newList= presentSubList(platform.getId(),
				provinceList,
				options,
				getProvinceDAO()::countProvinceByPlatform,
				getProvinceDAO()::findProvinceByPlatform
				);

		
		platform.setProvinceList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentCityList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<City> cityList = platform.getCityList();		
				SmartList<City> newList= presentSubList(platform.getId(),
				cityList,
				options,
				getCityDAO()::countCityByPlatform,
				getCityDAO()::findCityByPlatform
				);

		
		platform.setCityList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentDistrictList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<District> districtList = platform.getDistrictList();		
				SmartList<District> newList= presentSubList(platform.getId(),
				districtList,
				options,
				getDistrictDAO()::countDistrictByPlatform,
				getDistrictDAO()::findDistrictByPlatform
				);

		
		platform.setDistrictList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentSchoolClassList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<SchoolClass> schoolClassList = platform.getSchoolClassList();		
				SmartList<SchoolClass> newList= presentSubList(platform.getId(),
				schoolClassList,
				options,
				getSchoolClassDAO()::countSchoolClassByPlatform,
				getSchoolClassDAO()::findSchoolClassByPlatform
				);

		
		platform.setSchoolClassList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentTeacherList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Teacher> teacherList = platform.getTeacherList();		
				SmartList<Teacher> newList= presentSubList(platform.getId(),
				teacherList,
				options,
				getTeacherDAO()::countTeacherByPlatform,
				getTeacherDAO()::findTeacherByPlatform
				);

		
		platform.setTeacherList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentGuardianList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Guardian> guardianList = platform.getGuardianList();		
				SmartList<Guardian> newList= presentSubList(platform.getId(),
				guardianList,
				options,
				getGuardianDAO()::countGuardianByPlatform,
				getGuardianDAO()::findGuardianByPlatform
				);

		
		platform.setGuardianList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentQuestionList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Question> questionList = platform.getQuestionList();		
				SmartList<Question> newList= presentSubList(platform.getId(),
				questionList,
				options,
				getQuestionDAO()::countQuestionByPlatform,
				getQuestionDAO()::findQuestionByPlatform
				);

		
		platform.setQuestionList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentQuestionTypeList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<QuestionType> questionTypeList = platform.getQuestionTypeList();		
				SmartList<QuestionType> newList= presentSubList(platform.getId(),
				questionTypeList,
				options,
				getQuestionTypeDAO()::countQuestionTypeByPlatform,
				getQuestionTypeDAO()::findQuestionTypeByPlatform
				);

		
		platform.setQuestionTypeList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentQuestionSourceList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<QuestionSource> questionSourceList = platform.getQuestionSourceList();		
				SmartList<QuestionSource> newList= presentSubList(platform.getId(),
				questionSourceList,
				options,
				getQuestionSourceDAO()::countQuestionSourceByPlatform,
				getQuestionSourceDAO()::findQuestionSourceByPlatform
				);

		
		platform.setQuestionSourceList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentSurveyStatusList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<SurveyStatus> surveyStatusList = platform.getSurveyStatusList();		
				SmartList<SurveyStatus> newList= presentSubList(platform.getId(),
				surveyStatusList,
				options,
				getSurveyStatusDAO()::countSurveyStatusByPlatform,
				getSurveyStatusDAO()::findSurveyStatusByPlatform
				);

		
		platform.setSurveyStatusList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentWechatUserList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<WechatUser> wechatUserList = platform.getWechatUserList();		
				SmartList<WechatUser> newList= presentSubList(platform.getId(),
				wechatUserList,
				options,
				getWechatUserDAO()::countWechatUserByPlatform,
				getWechatUserDAO()::findWechatUserByPlatform
				);

		
		platform.setWechatUserList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentUserTypeList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<UserType> userTypeList = platform.getUserTypeList();		
				SmartList<UserType> newList= presentSubList(platform.getId(),
				userTypeList,
				options,
				getUserTypeDAO()::countUserTypeByPlatform,
				getUserTypeDAO()::findUserTypeByPlatform
				);

		
		platform.setUserTypeList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentChangeRequestList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();		
				SmartList<ChangeRequest> newList= presentSubList(platform.getId(),
				changeRequestList,
				options,
				getChangeRequestDAO()::countChangeRequestByPlatform,
				getChangeRequestDAO()::findChangeRequestByPlatform
				);

		
		platform.setChangeRequestList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentChangeRequestTypeList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();		
				SmartList<ChangeRequestType> newList= presentSubList(platform.getId(),
				changeRequestTypeList,
				options,
				getChangeRequestTypeDAO()::countChangeRequestTypeByPlatform,
				getChangeRequestTypeDAO()::findChangeRequestTypeByPlatform
				);

		
		platform.setChangeRequestTypeList(newList);
		

		return platform;
	}			
		

	
    public SmartList<Platform> requestCandidatePlatformForProvince(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForCity(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForDistrict(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForGuardian(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForQuestion(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForQuestionType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForQuestionSource(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForSurveyStatus(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForWechatUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForUserType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForChangeRequest(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForChangeRequestType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		

	protected String getTableName(){
		return PlatformTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Platform> platformList) {		
		this.enhanceListInternal(platformList, this.getPlatformMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Province的platform的ProvinceList
	public SmartList<Province> loadOurProvinceList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Province.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Province> loadedObjs = userContext.getDAOGroup().getProvinceDAO().findProvinceWithKey(key, options);
		Map<String, List<Province>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Province> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Province> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setProvinceList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:City的platform的CityList
	public SmartList<City> loadOurCityList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<City> loadedObjs = userContext.getDAOGroup().getCityDAO().findCityWithKey(key, options);
		Map<String, List<City>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<City> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<City> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setCityList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:District的platform的DistrictList
	public SmartList<District> loadOurDistrictList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<District> loadedObjs = userContext.getDAOGroup().getDistrictDAO().findDistrictWithKey(key, options);
		Map<String, List<District>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<District> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<District> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setDistrictList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:SchoolClass的platform的SchoolClassList
	public SmartList<SchoolClass> loadOurSchoolClassList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<SchoolClass> loadedObjs = userContext.getDAOGroup().getSchoolClassDAO().findSchoolClassWithKey(key, options);
		Map<String, List<SchoolClass>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<SchoolClass> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<SchoolClass> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setSchoolClassList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Teacher的platform的TeacherList
	public SmartList<Teacher> loadOurTeacherList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Teacher> loadedObjs = userContext.getDAOGroup().getTeacherDAO().findTeacherWithKey(key, options);
		Map<String, List<Teacher>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Teacher> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Teacher> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setTeacherList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Guardian的platform的GuardianList
	public SmartList<Guardian> loadOurGuardianList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Guardian.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Guardian> loadedObjs = userContext.getDAOGroup().getGuardianDAO().findGuardianWithKey(key, options);
		Map<String, List<Guardian>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Guardian> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Guardian> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setGuardianList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Question的platform的QuestionList
	public SmartList<Question> loadOurQuestionList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Question> loadedObjs = userContext.getDAOGroup().getQuestionDAO().findQuestionWithKey(key, options);
		Map<String, List<Question>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Question> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Question> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setQuestionList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:QuestionType的platform的QuestionTypeList
	public SmartList<QuestionType> loadOurQuestionTypeList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(QuestionType.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<QuestionType> loadedObjs = userContext.getDAOGroup().getQuestionTypeDAO().findQuestionTypeWithKey(key, options);
		Map<String, List<QuestionType>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<QuestionType> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<QuestionType> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setQuestionTypeList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:QuestionSource的platform的QuestionSourceList
	public SmartList<QuestionSource> loadOurQuestionSourceList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(QuestionSource.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<QuestionSource> loadedObjs = userContext.getDAOGroup().getQuestionSourceDAO().findQuestionSourceWithKey(key, options);
		Map<String, List<QuestionSource>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<QuestionSource> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<QuestionSource> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setQuestionSourceList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:SurveyStatus的platform的SurveyStatusList
	public SmartList<SurveyStatus> loadOurSurveyStatusList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SurveyStatus.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<SurveyStatus> loadedObjs = userContext.getDAOGroup().getSurveyStatusDAO().findSurveyStatusWithKey(key, options);
		Map<String, List<SurveyStatus>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<SurveyStatus> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<SurveyStatus> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setSurveyStatusList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:WechatUser的platform的WechatUserList
	public SmartList<WechatUser> loadOurWechatUserList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WechatUser> loadedObjs = userContext.getDAOGroup().getWechatUserDAO().findWechatUserWithKey(key, options);
		Map<String, List<WechatUser>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<WechatUser> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<WechatUser> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setWechatUserList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:UserType的platform的UserTypeList
	public SmartList<UserType> loadOurUserTypeList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserType.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<UserType> loadedObjs = userContext.getDAOGroup().getUserTypeDAO().findUserTypeWithKey(key, options);
		Map<String, List<UserType>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<UserType> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<UserType> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setUserTypeList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequest的platform的ChangeRequestList
	public SmartList<ChangeRequest> loadOurChangeRequestList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ChangeRequest> loadedObjs = userContext.getDAOGroup().getChangeRequestDAO().findChangeRequestWithKey(key, options);
		Map<String, List<ChangeRequest>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ChangeRequest> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ChangeRequest> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setChangeRequestList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestType的platform的ChangeRequestTypeList
	public SmartList<ChangeRequestType> loadOurChangeRequestTypeList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestType.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ChangeRequestType> loadedObjs = userContext.getDAOGroup().getChangeRequestTypeDAO().findChangeRequestTypeWithKey(key, options);
		Map<String, List<ChangeRequestType>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ChangeRequestType> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ChangeRequestType> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setChangeRequestTypeList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Platform> platformList = ownerEntity.collectRefsWithType(Platform.INTERNAL_TYPE);
		this.enhanceList(platformList);
		
	}
	
	@Override
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPlatformMapper());

	}
	@Override
	public int countPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Platform> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPlatformMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	
    
	public Map<String, Integer> countBySql(String sql, Object[] params) {
		if (params == null || params.length == 0) {
			return new HashMap<>();
		}
		List<Map<String, Object>> result = this.getJdbcTemplateObject().queryForList(sql, params);
		if (result == null || result.isEmpty()) {
			return new HashMap<>();
		}
		Map<String, Integer> cntMap = new HashMap<>();
		for (Map<String, Object> data : result) {
			String key = (String) data.get("id");
			Number value = (Number) data.get("count");
			cntMap.put(key, value.intValue());
		}
		this.logSQLAndParameters("countBySql", sql, params, cntMap.size() + " Counts");
		return cntMap;
	}

	public Integer singleCountBySql(String sql, Object[] params) {
		Integer cnt = this.getJdbcTemplateObject().queryForObject(sql, params, Integer.class);
		logSQLAndParameters("singleCountBySql", sql, params, cnt + "");
		return cnt;
	}

	public BigDecimal summaryBySql(String sql, Object[] params) {
		BigDecimal cnt = this.getJdbcTemplateObject().queryForObject(sql, params, BigDecimal.class);
		logSQLAndParameters("summaryBySql", sql, params, cnt + "");
		return cnt == null ? BigDecimal.ZERO : cnt;
	}

	public <T> List<T> queryForList(String sql, Object[] params, Class<T> claxx) {
		List<T> result = this.getJdbcTemplateObject().queryForList(sql, params, claxx);
		logSQLAndParameters("queryForList", sql, params, result.size() + " items");
		return result;
	}

	public Map<String, Object> queryForMap(String sql, Object[] params) throws DataAccessException {
		Map<String, Object> result = null;
		try {
			result = this.getJdbcTemplateObject().queryForMap(sql, params);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// 空结果，返回null
		}
		logSQLAndParameters("queryForObject", sql, params, result == null ? "not found" : String.valueOf(result));
		return result;
	}

	public <T> T queryForObject(String sql, Object[] params, Class<T> claxx) throws DataAccessException {
		T result = null;
		try {
			result = this.getJdbcTemplateObject().queryForObject(sql, params, claxx);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// 空结果，返回null
		}
		logSQLAndParameters("queryForObject", sql, params, result == null ? "not found" : String.valueOf(result));
		return result;
	}

	public List<Map<String, Object>> queryAsMapList(String sql, Object[] params) {
		List<Map<String, Object>> result = getJdbcTemplateObject().queryForList(sql, params);
		logSQLAndParameters("queryAsMapList", sql, params, result.size() + " items");
		return result;
	}

	public synchronized int updateBySql(String sql, Object[] params) {
		int result = getJdbcTemplateObject().update(sql, params);
		logSQLAndParameters("updateBySql", sql, params, result + " items");
		return result;
	}

	public void execSqlWithRowCallback(String sql, Object[] args, RowCallbackHandler callback) {
		getJdbcTemplateObject().query(sql, args, callback);
	}

	public void executeSql(String sql) {
		logSQLAndParameters("executeSql", sql, new Object[] {}, "");
		getJdbcTemplateObject().execute(sql);
	}


}


