
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


import com.doublechaintech.health.city.City;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.user.User;

import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.questiontype.QuestionTypeDAO;
import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.student.StudentDAO;
import com.doublechaintech.health.teacher.TeacherDAO;
import com.doublechaintech.health.surveystatus.SurveyStatusDAO;
import com.doublechaintech.health.question.QuestionDAO;
import com.doublechaintech.health.district.DistrictDAO;
import com.doublechaintech.health.user.UserDAO;
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
 	
			
		
	
  	private  StudentDAO  studentDAO;
 	public void setStudentDAO(StudentDAO pStudentDAO){
 	
 		if(pStudentDAO == null){
 			throw new IllegalStateException("Do not try to set studentDAO to null.");
 		}
	 	this.studentDAO = pStudentDAO;
 	}
 	public StudentDAO getStudentDAO(){
 		if(this.studentDAO == null){
 			throw new IllegalStateException("The studentDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentDAO;
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
 	
			
		
	
  	private  UserDAO  userDAO;
 	public void setUserDAO(UserDAO pUserDAO){
 	
 		if(pUserDAO == null){
 			throw new IllegalStateException("Do not try to set userDAO to null.");
 		}
	 	this.userDAO = pUserDAO;
 	}
 	public UserDAO getUserDAO(){
 		if(this.userDAO == null){
 			throw new IllegalStateException("The userDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userDAO;
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
		
 		
 		if(isSaveTeacherListEnabled(options)){
 			for(Teacher item: newPlatform.getTeacherList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveStudentListEnabled(options)){
 			for(Student item: newPlatform.getStudentList()){
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
		
 		
 		if(isSaveSurveyStatusListEnabled(options)){
 			for(SurveyStatus item: newPlatform.getSurveyStatusList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveUserListEnabled(options)){
 			for(User item: newPlatform.getUserList()){
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
 	
		
	
	protected boolean isExtractTeacherListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.TEACHER_LIST);
 	}
 	protected boolean isAnalyzeTeacherListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeTeacherListEnabled();
 	}
	
	protected boolean isSaveTeacherListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.TEACHER_LIST);
		
 	}
 	
		
	
	protected boolean isExtractStudentListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.STUDENT_LIST);
 	}
 	protected boolean isAnalyzeStudentListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeStudentListEnabled();
 	}
	
	protected boolean isSaveStudentListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.STUDENT_LIST);
		
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
 	
		
	
	protected boolean isExtractSurveyStatusListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.SURVEY_STATUS_LIST);
 	}
 	protected boolean isAnalyzeSurveyStatusListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeSurveyStatusListEnabled();
 	}
	
	protected boolean isSaveSurveyStatusListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.SURVEY_STATUS_LIST);
		
 	}
 	
		
	
	protected boolean isExtractUserListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.USER_LIST);
 	}
 	protected boolean isAnalyzeUserListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeUserListEnabled();
 	}
	
	protected boolean isSaveUserListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.USER_LIST);
		
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
 		
		
		if(isExtractTeacherListEnabled(loadOptions)){
	 		extractTeacherList(platform, loadOptions);
 		}	
 		if(isAnalyzeTeacherListEnabled(loadOptions)){
	 		analyzeTeacherList(platform, loadOptions);
 		}
 		
		
		if(isExtractStudentListEnabled(loadOptions)){
	 		extractStudentList(platform, loadOptions);
 		}	
 		if(isAnalyzeStudentListEnabled(loadOptions)){
	 		analyzeStudentList(platform, loadOptions);
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
 		
		
		if(isExtractSurveyStatusListEnabled(loadOptions)){
	 		extractSurveyStatusList(platform, loadOptions);
 		}	
 		if(isAnalyzeSurveyStatusListEnabled(loadOptions)){
	 		analyzeSurveyStatusList(platform, loadOptions);
 		}
 		
		
		if(isExtractUserListEnabled(loadOptions)){
	 		extractUserList(platform, loadOptions);
 		}	
 		if(isAnalyzeUserListEnabled(loadOptions)){
	 		analyzeUserList(platform, loadOptions);
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
	
		
	protected void enhanceStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractStudentList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Student> studentList = getStudentDAO().findStudentByPlatform(platform.getId(),options);
		if(studentList != null){
			enhanceStudentList(studentList,options);
			platform.setStudentList(studentList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeStudentList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Student> studentList = platform.getStudentList();
		if(studentList != null){
			getStudentDAO().analyzeStudentByPlatform(studentList, platform.getId(), options);
			
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
	
		
	protected void enhanceUserList(SmartList<User> userList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractUserList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<User> userList = getUserDAO().findUserByPlatform(platform.getId(),options);
		if(userList != null){
			enhanceUserList(userList,options);
			platform.setUserList(userList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeUserList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<User> userList = platform.getUserList();
		if(userList != null){
			getUserDAO().analyzeUserByPlatform(userList, platform.getId(), options);
			
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
		
		if(isSaveTeacherListEnabled(options)){
	 		saveTeacherList(platform, options);
	 		//removeTeacherList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveStudentListEnabled(options)){
	 		saveStudentList(platform, options);
	 		//removeStudentList(platform, options);
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
		
		if(isSaveSurveyStatusListEnabled(options)){
	 		saveSurveyStatusList(platform, options);
	 		//removeSurveyStatusList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveUserListEnabled(options)){
	 		saveUserList(platform, options);
	 		//removeUserList(platform, options);
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


	//disconnect Platform with change_request in Teacher
	public Platform planToRemoveTeacherListWithChangeRequest(Platform platform, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.PLATFORM_PROPERTY, platform.getId());
		key.put(Teacher.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Teacher> externalTeacherList = getTeacherDAO().
				findTeacherWithKey(key, options);
		if(externalTeacherList == null){
			return platform;
		}
		if(externalTeacherList.isEmpty()){
			return platform;
		}
		
		for(Teacher teacherItem: externalTeacherList){
			teacherItem.clearChangeRequest();
			teacherItem.clearPlatform();
			
		}
		
		
		SmartList<Teacher> teacherList = platform.getTeacherList();		
		teacherList.addAllToRemoveList(externalTeacherList);
		return platform;
	}
	
	public int countTeacherListWithChangeRequest(String platformId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Teacher.PLATFORM_PROPERTY, platformId);
		key.put(Teacher.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getTeacherDAO().countTeacherWithKey(key, options);
		return count;
	}
	
	public Platform planToRemoveStudentList(Platform platform, String studentIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platform.getId());
		key.put(Student.ID_PROPERTY, studentIds);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return platform;
		}
		if(externalStudentList.isEmpty()){
			return platform;
		}
		
		for(Student studentItem: externalStudentList){

			studentItem.clearFromAll();
		}
		
		
		SmartList<Student> studentList = platform.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return platform;	
	
	}


	//disconnect Platform with student_id in Student
	public Platform planToRemoveStudentListWithStudentId(Platform platform, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platform.getId());
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return platform;
		}
		if(externalStudentList.isEmpty()){
			return platform;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearStudentId();
			studentItem.clearPlatform();
			
		}
		
		
		SmartList<Student> studentList = platform.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return platform;
	}
	
	public int countStudentListWithStudentId(String platformId, String studentIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platformId);
		key.put(Student.STUDENT_ID_PROPERTY, studentIdId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with address in Student
	public Platform planToRemoveStudentListWithAddress(Platform platform, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platform.getId());
		key.put(Student.ADDRESS_PROPERTY, addressId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return platform;
		}
		if(externalStudentList.isEmpty()){
			return platform;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearAddress();
			studentItem.clearPlatform();
			
		}
		
		
		SmartList<Student> studentList = platform.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return platform;
	}
	
	public int countStudentListWithAddress(String platformId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platformId);
		key.put(Student.ADDRESS_PROPERTY, addressId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with user in Student
	public Platform planToRemoveStudentListWithUser(Platform platform, String userId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platform.getId());
		key.put(Student.USER_PROPERTY, userId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return platform;
		}
		if(externalStudentList.isEmpty()){
			return platform;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearUser();
			studentItem.clearPlatform();
			
		}
		
		
		SmartList<Student> studentList = platform.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return platform;
	}
	
	public int countStudentListWithUser(String platformId, String userId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platformId);
		key.put(Student.USER_PROPERTY, userId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with change_request in Student
	public Platform planToRemoveStudentListWithChangeRequest(Platform platform, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platform.getId());
		key.put(Student.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		SmartList<Student> externalStudentList = getStudentDAO().
				findStudentWithKey(key, options);
		if(externalStudentList == null){
			return platform;
		}
		if(externalStudentList.isEmpty()){
			return platform;
		}
		
		for(Student studentItem: externalStudentList){
			studentItem.clearChangeRequest();
			studentItem.clearPlatform();
			
		}
		
		
		SmartList<Student> studentList = platform.getStudentList();		
		studentList.addAllToRemoveList(externalStudentList);
		return platform;
	}
	
	public int countStudentListWithChangeRequest(String platformId, String changeRequestId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, platformId);
		key.put(Student.CHANGE_REQUEST_PROPERTY, changeRequestId);
		
		int count = getStudentDAO().countStudentWithKey(key, options);
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
	
	//disconnect Platform with creator in Question
	public Platform planToRemoveQuestionListWithCreator(Platform platform, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platform.getId());
		key.put(Question.CREATOR_PROPERTY, creatorId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return platform;
		}
		if(externalQuestionList.isEmpty()){
			return platform;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearCreator();
			questionItem.clearPlatform();
			
		}
		
		
		SmartList<Question> questionList = platform.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return platform;
	}
	
	public int countQuestionListWithCreator(String platformId, String creatorId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platformId);
		key.put(Question.CREATOR_PROPERTY, creatorId);
		
		int count = getQuestionDAO().countQuestionWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with cq in Question
	public Platform planToRemoveQuestionListWithCq(Platform platform, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platform.getId());
		key.put(Question.CQ_PROPERTY, cqId);
		
		SmartList<Question> externalQuestionList = getQuestionDAO().
				findQuestionWithKey(key, options);
		if(externalQuestionList == null){
			return platform;
		}
		if(externalQuestionList.isEmpty()){
			return platform;
		}
		
		for(Question questionItem: externalQuestionList){
			questionItem.clearCq();
			questionItem.clearPlatform();
			
		}
		
		
		SmartList<Question> questionList = platform.getQuestionList();		
		questionList.addAllToRemoveList(externalQuestionList);
		return platform;
	}
	
	public int countQuestionListWithCq(String platformId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Question.PLATFORM_PROPERTY, platformId);
		key.put(Question.CQ_PROPERTY, cqId);
		
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


	public Platform planToRemoveUserList(Platform platform, String userIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.PLATFORM_PROPERTY, platform.getId());
		key.put(User.ID_PROPERTY, userIds);
		
		SmartList<User> externalUserList = getUserDAO().
				findUserWithKey(key, options);
		if(externalUserList == null){
			return platform;
		}
		if(externalUserList.isEmpty()){
			return platform;
		}
		
		for(User userItem: externalUserList){

			userItem.clearFromAll();
		}
		
		
		SmartList<User> userList = platform.getUserList();		
		userList.addAllToRemoveList(externalUserList);
		return platform;	
	
	}


	//disconnect Platform with address in User
	public Platform planToRemoveUserListWithAddress(Platform platform, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.PLATFORM_PROPERTY, platform.getId());
		key.put(User.ADDRESS_PROPERTY, addressId);
		
		SmartList<User> externalUserList = getUserDAO().
				findUserWithKey(key, options);
		if(externalUserList == null){
			return platform;
		}
		if(externalUserList.isEmpty()){
			return platform;
		}
		
		for(User userItem: externalUserList){
			userItem.clearAddress();
			userItem.clearPlatform();
			
		}
		
		
		SmartList<User> userList = platform.getUserList();		
		userList.addAllToRemoveList(externalUserList);
		return platform;
	}
	
	public int countUserListWithAddress(String platformId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.PLATFORM_PROPERTY, platformId);
		key.put(User.ADDRESS_PROPERTY, addressId);
		
		int count = getUserDAO().countUserWithKey(key, options);
		return count;
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
	
	

 	
 	
	
	
	
		
	protected Platform saveStudentList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Student> studentList = platform.getStudentList();
		if(studentList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Student> mergedUpdateStudentList = new SmartList<Student>();
		
		
		mergedUpdateStudentList.addAll(studentList); 
		if(studentList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentList.addAll(studentList.getToRemoveList());
			studentList.removeAll(studentList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentDAO().saveStudentList(mergedUpdateStudentList,options);
		
		if(studentList.getToRemoveList() != null){
			studentList.removeAll(studentList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeStudentList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Student> studentList = platform.getStudentList();
		if(studentList == null){
			return platform;
		}	
	
		SmartList<Student> toRemoveStudentList = studentList.getToRemoveList();
		
		if(toRemoveStudentList == null){
			return platform;
		}
		if(toRemoveStudentList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentDAO().removeStudentList(toRemoveStudentList,options);
		
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
	
	

 	
 	
	
	
	
		
	protected Platform saveUserList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<User> userList = platform.getUserList();
		if(userList == null){
			//null list means nothing
			return platform;
		}
		SmartList<User> mergedUpdateUserList = new SmartList<User>();
		
		
		mergedUpdateUserList.addAll(userList); 
		if(userList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateUserList.addAll(userList.getToRemoveList());
			userList.removeAll(userList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getUserDAO().saveUserList(mergedUpdateUserList,options);
		
		if(userList.getToRemoveList() != null){
			userList.removeAll(userList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeUserList(Platform platform, Map<String,Object> options){
	
	
		SmartList<User> userList = platform.getUserList();
		if(userList == null){
			return platform;
		}	
	
		SmartList<User> toRemoveUserList = userList.getToRemoveList();
		
		if(toRemoveUserList == null){
			return platform;
		}
		if(toRemoveUserList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getUserDAO().removeUserList(toRemoveUserList,options);
		
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
		presentTeacherList(platform,options);
		presentStudentList(platform,options);
		presentQuestionList(platform,options);
		presentQuestionTypeList(platform,options);
		presentSurveyStatusList(platform,options);
		presentUserList(platform,options);
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
 	protected Platform presentStudentList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Student> studentList = platform.getStudentList();		
				SmartList<Student> newList= presentSubList(platform.getId(),
				studentList,
				options,
				getStudentDAO()::countStudentByPlatform,
				getStudentDAO()::findStudentByPlatform
				);

		
		platform.setStudentList(newList);
		

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
 	protected Platform presentUserList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<User> userList = platform.getUserList();		
				SmartList<User> newList= presentSubList(platform.getId(),
				userList,
				options,
				getUserDAO()::countUserByPlatform,
				getUserDAO()::findUserByPlatform
				);

		
		platform.setUserList(newList);
		

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
		
    public SmartList<Platform> requestCandidatePlatformForTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForStudent(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
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
		
    public SmartList<Platform> requestCandidatePlatformForSurveyStatus(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
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
	
	// 需要一个加载引用我的对象的enhance方法:Student的platform的StudentList
	public SmartList<Student> loadOurStudentList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Student.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Student> loadedObjs = userContext.getDAOGroup().getStudentDAO().findStudentWithKey(key, options);
		Map<String, List<Student>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Student> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Student> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentList(loadedSmartList);
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
	
	// 需要一个加载引用我的对象的enhance方法:User的platform的UserList
	public SmartList<User> loadOurUserList(HealthUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<User> loadedObjs = userContext.getDAOGroup().getUserDAO().findUserWithKey(key, options);
		Map<String, List<User>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<User> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<User> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setUserList(loadedSmartList);
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


