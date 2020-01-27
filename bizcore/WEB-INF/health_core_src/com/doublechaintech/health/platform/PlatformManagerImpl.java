
package com.doublechaintech.health.platform;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Password;

import com.doublechaintech.health.*;
import com.doublechaintech.health.HealthUserContextImpl;
import com.doublechaintech.health.iamservice.*;
import com.doublechaintech.health.services.IamService;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.terapico.uccaf.BaseUserContext;


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


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.usertype.UserType;






public class PlatformManagerImpl extends CustomHealthCheckerManager implements PlatformManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Platform";
	@Override
	public PlatformDAO daoOf(HealthUserContext userContext) {
		return platformDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws PlatformManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new PlatformManagerException(message);

	}



 	protected Platform savePlatform(HealthUserContext userContext, Platform platform, String [] tokensExpr) throws Exception{	
 		//return getPlatformDAO().save(platform, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePlatform(userContext, platform, tokens);
 	}
 	
 	protected Platform savePlatformDetail(HealthUserContext userContext, Platform platform) throws Exception{	

 		
 		return savePlatform(userContext, platform, allTokens());
 	}
 	
 	public Platform loadPlatform(HealthUserContext userContext, String platformId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).throwExceptionIfHasErrors( PlatformManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	
 	 public Platform searchPlatform(HealthUserContext userContext, String platformId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).throwExceptionIfHasErrors( PlatformManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	

 	protected Platform present(HealthUserContext userContext, Platform platform, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,platform,tokens);
		
		
		Platform  platformToPresent = platformDaoOf(userContext).present(platform, tokens);
		
		List<BaseEntity> entityListToNaming = platformToPresent.collectRefercencesFromLists();
		platformDaoOf(userContext).alias(entityListToNaming);
		
		return  platformToPresent;
		
		
	}
 
 	
 	
 	public Platform loadPlatformDetail(HealthUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, allTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, viewTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	protected Platform savePlatform(HealthUserContext userContext, Platform platform, Map<String,Object>tokens) throws Exception{	
 		return platformDaoOf(userContext).save(platform, tokens);
 	}
 	protected Platform loadPlatform(HealthUserContext userContext, String platformId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).throwExceptionIfHasErrors( PlatformManagerException.class);

 
 		return platformDaoOf(userContext).load(platformId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Platform platform, Map<String, Object> tokens){
		super.addActions(userContext, platform, tokens);
		
		addAction(userContext, platform, tokens,"@create","createPlatform","createPlatform/","main","primary");
		addAction(userContext, platform, tokens,"@update","updatePlatform","updatePlatform/"+platform.getId()+"/","main","primary");
		addAction(userContext, platform, tokens,"@copy","clonePlatform","clonePlatform/"+platform.getId()+"/","main","primary");
		
		addAction(userContext, platform, tokens,"platform.addProvince","addProvince","addProvince/"+platform.getId()+"/","provinceList","primary");
		addAction(userContext, platform, tokens,"platform.removeProvince","removeProvince","removeProvince/"+platform.getId()+"/","provinceList","primary");
		addAction(userContext, platform, tokens,"platform.updateProvince","updateProvince","updateProvince/"+platform.getId()+"/","provinceList","primary");
		addAction(userContext, platform, tokens,"platform.copyProvinceFrom","copyProvinceFrom","copyProvinceFrom/"+platform.getId()+"/","provinceList","primary");
		addAction(userContext, platform, tokens,"platform.addCity","addCity","addCity/"+platform.getId()+"/","cityList","primary");
		addAction(userContext, platform, tokens,"platform.removeCity","removeCity","removeCity/"+platform.getId()+"/","cityList","primary");
		addAction(userContext, platform, tokens,"platform.updateCity","updateCity","updateCity/"+platform.getId()+"/","cityList","primary");
		addAction(userContext, platform, tokens,"platform.copyCityFrom","copyCityFrom","copyCityFrom/"+platform.getId()+"/","cityList","primary");
		addAction(userContext, platform, tokens,"platform.addDistrict","addDistrict","addDistrict/"+platform.getId()+"/","districtList","primary");
		addAction(userContext, platform, tokens,"platform.removeDistrict","removeDistrict","removeDistrict/"+platform.getId()+"/","districtList","primary");
		addAction(userContext, platform, tokens,"platform.updateDistrict","updateDistrict","updateDistrict/"+platform.getId()+"/","districtList","primary");
		addAction(userContext, platform, tokens,"platform.copyDistrictFrom","copyDistrictFrom","copyDistrictFrom/"+platform.getId()+"/","districtList","primary");
		addAction(userContext, platform, tokens,"platform.addSchoolClass","addSchoolClass","addSchoolClass/"+platform.getId()+"/","schoolClassList","primary");
		addAction(userContext, platform, tokens,"platform.removeSchoolClass","removeSchoolClass","removeSchoolClass/"+platform.getId()+"/","schoolClassList","primary");
		addAction(userContext, platform, tokens,"platform.updateSchoolClass","updateSchoolClass","updateSchoolClass/"+platform.getId()+"/","schoolClassList","primary");
		addAction(userContext, platform, tokens,"platform.copySchoolClassFrom","copySchoolClassFrom","copySchoolClassFrom/"+platform.getId()+"/","schoolClassList","primary");
		addAction(userContext, platform, tokens,"platform.addTeacher","addTeacher","addTeacher/"+platform.getId()+"/","teacherList","primary");
		addAction(userContext, platform, tokens,"platform.removeTeacher","removeTeacher","removeTeacher/"+platform.getId()+"/","teacherList","primary");
		addAction(userContext, platform, tokens,"platform.updateTeacher","updateTeacher","updateTeacher/"+platform.getId()+"/","teacherList","primary");
		addAction(userContext, platform, tokens,"platform.copyTeacherFrom","copyTeacherFrom","copyTeacherFrom/"+platform.getId()+"/","teacherList","primary");
		addAction(userContext, platform, tokens,"platform.addGuardian","addGuardian","addGuardian/"+platform.getId()+"/","guardianList","primary");
		addAction(userContext, platform, tokens,"platform.removeGuardian","removeGuardian","removeGuardian/"+platform.getId()+"/","guardianList","primary");
		addAction(userContext, platform, tokens,"platform.updateGuardian","updateGuardian","updateGuardian/"+platform.getId()+"/","guardianList","primary");
		addAction(userContext, platform, tokens,"platform.copyGuardianFrom","copyGuardianFrom","copyGuardianFrom/"+platform.getId()+"/","guardianList","primary");
		addAction(userContext, platform, tokens,"platform.addQuestion","addQuestion","addQuestion/"+platform.getId()+"/","questionList","primary");
		addAction(userContext, platform, tokens,"platform.removeQuestion","removeQuestion","removeQuestion/"+platform.getId()+"/","questionList","primary");
		addAction(userContext, platform, tokens,"platform.updateQuestion","updateQuestion","updateQuestion/"+platform.getId()+"/","questionList","primary");
		addAction(userContext, platform, tokens,"platform.copyQuestionFrom","copyQuestionFrom","copyQuestionFrom/"+platform.getId()+"/","questionList","primary");
		addAction(userContext, platform, tokens,"platform.addQuestionType","addQuestionType","addQuestionType/"+platform.getId()+"/","questionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.removeQuestionType","removeQuestionType","removeQuestionType/"+platform.getId()+"/","questionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.updateQuestionType","updateQuestionType","updateQuestionType/"+platform.getId()+"/","questionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.copyQuestionTypeFrom","copyQuestionTypeFrom","copyQuestionTypeFrom/"+platform.getId()+"/","questionTypeList","primary");
		addAction(userContext, platform, tokens,"platform.addQuestionSource","addQuestionSource","addQuestionSource/"+platform.getId()+"/","questionSourceList","primary");
		addAction(userContext, platform, tokens,"platform.removeQuestionSource","removeQuestionSource","removeQuestionSource/"+platform.getId()+"/","questionSourceList","primary");
		addAction(userContext, platform, tokens,"platform.updateQuestionSource","updateQuestionSource","updateQuestionSource/"+platform.getId()+"/","questionSourceList","primary");
		addAction(userContext, platform, tokens,"platform.copyQuestionSourceFrom","copyQuestionSourceFrom","copyQuestionSourceFrom/"+platform.getId()+"/","questionSourceList","primary");
		addAction(userContext, platform, tokens,"platform.addSurveyStatus","addSurveyStatus","addSurveyStatus/"+platform.getId()+"/","surveyStatusList","primary");
		addAction(userContext, platform, tokens,"platform.removeSurveyStatus","removeSurveyStatus","removeSurveyStatus/"+platform.getId()+"/","surveyStatusList","primary");
		addAction(userContext, platform, tokens,"platform.updateSurveyStatus","updateSurveyStatus","updateSurveyStatus/"+platform.getId()+"/","surveyStatusList","primary");
		addAction(userContext, platform, tokens,"platform.copySurveyStatusFrom","copySurveyStatusFrom","copySurveyStatusFrom/"+platform.getId()+"/","surveyStatusList","primary");
		addAction(userContext, platform, tokens,"platform.addWechatUser","addWechatUser","addWechatUser/"+platform.getId()+"/","wechatUserList","primary");
		addAction(userContext, platform, tokens,"platform.removeWechatUser","removeWechatUser","removeWechatUser/"+platform.getId()+"/","wechatUserList","primary");
		addAction(userContext, platform, tokens,"platform.updateWechatUser","updateWechatUser","updateWechatUser/"+platform.getId()+"/","wechatUserList","primary");
		addAction(userContext, platform, tokens,"platform.copyWechatUserFrom","copyWechatUserFrom","copyWechatUserFrom/"+platform.getId()+"/","wechatUserList","primary");
		addAction(userContext, platform, tokens,"platform.addUserType","addUserType","addUserType/"+platform.getId()+"/","userTypeList","primary");
		addAction(userContext, platform, tokens,"platform.removeUserType","removeUserType","removeUserType/"+platform.getId()+"/","userTypeList","primary");
		addAction(userContext, platform, tokens,"platform.updateUserType","updateUserType","updateUserType/"+platform.getId()+"/","userTypeList","primary");
		addAction(userContext, platform, tokens,"platform.copyUserTypeFrom","copyUserTypeFrom","copyUserTypeFrom/"+platform.getId()+"/","userTypeList","primary");
		addAction(userContext, platform, tokens,"platform.addChangeRequest","addChangeRequest","addChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.removeChangeRequest","removeChangeRequest","removeChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.updateChangeRequest","updateChangeRequest","updateChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.copyChangeRequestFrom","copyChangeRequestFrom","copyChangeRequestFrom/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.addChangeRequestType","addChangeRequestType","addChangeRequestType/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.removeChangeRequestType","removeChangeRequestType","removeChangeRequestType/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.updateChangeRequestType","updateChangeRequestType","updateChangeRequestType/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.copyChangeRequestTypeFrom","copyChangeRequestTypeFrom","copyChangeRequestTypeFrom/"+platform.getId()+"/","changeRequestTypeList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Platform platform, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Platform createPlatform(HealthUserContext userContext, String name,String description) throws Exception
	//public Platform createPlatform(HealthUserContext userContext,String name, String description) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfPlatform(name);
		checkerOf(userContext).checkDescriptionOfPlatform(description);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


		Platform platform=createNewPlatform();	

		platform.setName(name);
		platform.setDescription(description);

		platform = savePlatform(userContext, platform, emptyOptions());
		
		onNewInstanceCreated(userContext, platform);
		return platform;


	}
	protected Platform createNewPlatform()
	{

		return new Platform();
	}

	protected void checkParamsForUpdatingPlatform(HealthUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkVersionOfPlatform( platformVersion);
		

		if(Platform.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfPlatform(parseString(newValueExpr));
		}
		if(Platform.DESCRIPTION_PROPERTY.equals(property)){
			checkerOf(userContext).checkDescriptionOfPlatform(parseString(newValueExpr));
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}



	public Platform clone(HealthUserContext userContext, String fromPlatformId) throws Exception{

		return platformDaoOf(userContext).clone(fromPlatformId, this.allTokens());
	}

	public Platform internalSavePlatform(HealthUserContext userContext, Platform platform) throws Exception
	{
		return internalSavePlatform(userContext, platform, allTokens());

	}
	public Platform internalSavePlatform(HealthUserContext userContext, Platform platform, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);


		synchronized(platform){
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			if (platform.isChanged()){
			
			}
			platform = savePlatform(userContext, platform, options);
			return platform;

		}

	}

	public Platform updatePlatform(HealthUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);



		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}

	public Platform updatePlatformProperty(HealthUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);

		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.

			platform.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected PlatformTokens tokens(){
		return PlatformTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PlatformTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortProvinceListWith("id","desc")
		.sortCityListWith("id","desc")
		.sortDistrictListWith("id","desc")
		.sortSchoolClassListWith("id","desc")
		.sortTeacherListWith("id","desc")
		.sortGuardianListWith("id","desc")
		.sortQuestionListWith("id","desc")
		.sortQuestionTypeListWith("id","desc")
		.sortQuestionSourceListWith("id","desc")
		.sortSurveyStatusListWith("id","desc")
		.sortWechatUserListWith("id","desc")
		.sortUserTypeListWith("id","desc")
		.sortChangeRequestListWith("id","desc")
		.sortChangeRequestTypeListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PlatformTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String platformId, int platformVersion) throws Exception {
		//deleteInternal(userContext, platformId, platformVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String platformId, int platformVersion) throws Exception{

		platformDaoOf(userContext).delete(platformId, platformVersion);
	}

	public Platform forgetByAll(HealthUserContext userContext, String platformId, int platformVersion) throws Exception {
		return forgetByAllInternal(userContext, platformId, platformVersion);
	}
	protected Platform forgetByAllInternal(HealthUserContext userContext,
			String platformId, int platformVersion) throws Exception{

		return platformDaoOf(userContext).disconnectFromAll(platformId, platformVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PlatformManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return platformDaoOf(userContext).deleteAll();
	}


	//disconnect Platform with province in City
	protected Platform breakWithCityByProvince(HealthUserContext userContext, String platformId, String provinceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveCityListWithProvince(platform, provinceId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withCityList().done());
				return platform;
			}
	}
	//disconnect Platform with city in District
	protected Platform breakWithDistrictByCity(HealthUserContext userContext, String platformId, String cityId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveDistrictListWithCity(platform, cityId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withDistrictList().done());
				return platform;
			}
	}
	//disconnect Platform with class_teacher in SchoolClass
	protected Platform breakWithSchoolClassByClassTeacher(HealthUserContext userContext, String platformId, String classTeacherId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveSchoolClassListWithClassTeacher(platform, classTeacherId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
				return platform;
			}
	}
	//disconnect Platform with cq in SchoolClass
	protected Platform breakWithSchoolClassByCq(HealthUserContext userContext, String platformId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveSchoolClassListWithCq(platform, cqId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
				return platform;
			}
	}
	//disconnect Platform with cq in Teacher
	protected Platform breakWithTeacherByCq(HealthUserContext userContext, String platformId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveTeacherListWithCq(platform, cqId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withTeacherList().done());
				return platform;
			}
	}
	//disconnect Platform with address in Guardian
	protected Platform breakWithGuardianByAddress(HealthUserContext userContext, String platformId, String addressId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveGuardianListWithAddress(platform, addressId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
				return platform;
			}
	}
	//disconnect Platform with wechat_user in Guardian
	protected Platform breakWithGuardianByWechatUser(HealthUserContext userContext, String platformId, String wechatUserId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveGuardianListWithWechatUser(platform, wechatUserId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
				return platform;
			}
	}
	//disconnect Platform with cq in Guardian
	protected Platform breakWithGuardianByCq(HealthUserContext userContext, String platformId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveGuardianListWithCq(platform, cqId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
				return platform;
			}
	}
	//disconnect Platform with question_type in Question
	protected Platform breakWithQuestionByQuestionType(HealthUserContext userContext, String platformId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveQuestionListWithQuestionType(platform, questionTypeId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withQuestionList().done());
				return platform;
			}
	}
	//disconnect Platform with address in WechatUser
	protected Platform breakWithWechatUserByAddress(HealthUserContext userContext, String platformId, String addressId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveWechatUserListWithAddress(platform, addressId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
				return platform;
			}
	}
	//disconnect Platform with user_type in WechatUser
	protected Platform breakWithWechatUserByUserType(HealthUserContext userContext, String platformId, String userTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveWechatUserListWithUserType(platform, userTypeId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
				return platform;
			}
	}
	//disconnect Platform with request_type in ChangeRequest
	protected Platform breakWithChangeRequestByRequestType(HealthUserContext userContext, String platformId, String requestTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveChangeRequestListWithRequestType(platform, requestTypeId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
				return platform;
			}
	}






	protected void checkParamsForAddingProvince(HealthUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfProvince(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addProvince(HealthUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingProvince(userContext,platformId,name,tokensExpr);

		Province province = createProvince(userContext,name);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addProvince( province );
			platform = savePlatform(userContext, platform, tokens().withProvinceList().done());
			
			userContext.getManagerGroup().getProvinceManager().onNewInstanceCreated(userContext, province);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingProvinceProperties(HealthUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfProvince(id);

		checkerOf(userContext).checkNameOfProvince( name);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateProvinceProperties(HealthUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingProvinceProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withProvinceListList()
				.searchProvinceListWith(Province.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getProvinceList().isEmpty()){
			throw new PlatformManagerException("Province is NOT FOUND with id: '"+id+"'");
		}

		Province item = platformToUpdate.getProvinceList().first();

		item.updateName( name );


		//checkParamsForAddingProvince(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withProvinceList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected Province createProvince(HealthUserContext userContext, String name) throws Exception{

		Province province = new Province();
		
		
		province.setName(name);		
		province.setCreateTime(userContext.now());
	
		
		return province;


	}

	protected Province createIndexedProvince(String id, int version){

		Province province = new Province();
		province.setId(id);
		province.setVersion(version);
		return province;

	}

	protected void checkParamsForRemovingProvinceList(HealthUserContext userContext, String platformId,
			String provinceIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String provinceIdItem: provinceIds){
			checkerOf(userContext).checkIdOfProvince(provinceIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeProvinceList(HealthUserContext userContext, String platformId,
			String provinceIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingProvinceList(userContext, platformId,  provinceIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveProvinceList(platform, provinceIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withProvinceList().done());
				deleteRelationListInGraph(userContext, platform.getProvinceList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingProvince(HealthUserContext userContext, String platformId,
		String provinceId, int provinceVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkVersionOfProvince(provinceVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeProvince(HealthUserContext userContext, String platformId,
		String provinceId, int provinceVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingProvince(userContext,platformId, provinceId, provinceVersion,tokensExpr);

		Province province = createIndexedProvince(provinceId, provinceVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeProvince( province );
			platform = savePlatform(userContext, platform, tokens().withProvinceList().done());
			deleteRelationInGraph(userContext, province);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingProvince(HealthUserContext userContext, String platformId,
		String provinceId, int provinceVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkVersionOfProvince(provinceVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyProvinceFrom(HealthUserContext userContext, String platformId,
		String provinceId, int provinceVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingProvince(userContext,platformId, provinceId, provinceVersion,tokensExpr);

		Province province = createIndexedProvince(provinceId, provinceVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyProvinceFrom( province );
			platform = savePlatform(userContext, platform, tokens().withProvinceList().done());
			
			userContext.getManagerGroup().getProvinceManager().onNewInstanceCreated(userContext, (Province)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingProvince(HealthUserContext userContext, String platformId, String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkVersionOfProvince(provinceVersion);
		

		if(Province.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfProvince(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateProvince(HealthUserContext userContext, String platformId, String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingProvince(userContext, platformId, provinceId, provinceVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withProvinceList().searchProvinceListWith(Province.ID_PROPERTY, "eq", provinceId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeProvince( province );
			//make changes to AcceleraterAccount.
			Province provinceIndex = createIndexedProvince(provinceId, provinceVersion);

			Province province = platform.findTheProvince(provinceIndex);
			if(province == null){
				throw new PlatformManagerException(province+" is NOT FOUND" );
			}

			province.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withProvinceList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingCity(HealthUserContext userContext, String platformId, String name, String provinceId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfCity(name);
		
		checkerOf(userContext).checkProvinceIdOfCity(provinceId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addCity(HealthUserContext userContext, String platformId, String name, String provinceId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingCity(userContext,platformId,name, provinceId,tokensExpr);

		City city = createCity(userContext,name, provinceId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addCity( city );
			platform = savePlatform(userContext, platform, tokens().withCityList().done());
			
			userContext.getManagerGroup().getCityManager().onNewInstanceCreated(userContext, city);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingCityProperties(HealthUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfCity(id);

		checkerOf(userContext).checkNameOfCity( name);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateCityProperties(HealthUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCityProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withCityListList()
				.searchCityListWith(City.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getCityList().isEmpty()){
			throw new PlatformManagerException("City is NOT FOUND with id: '"+id+"'");
		}

		City item = platformToUpdate.getCityList().first();

		item.updateName( name );


		//checkParamsForAddingCity(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withCityList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected City createCity(HealthUserContext userContext, String name, String provinceId) throws Exception{

		City city = new City();
		
		
		city.setName(name);		
		Province  province = new Province();
		province.setId(provinceId);		
		city.setProvince(province);		
		city.setCreateTime(userContext.now());
	
		
		return city;


	}

	protected City createIndexedCity(String id, int version){

		City city = new City();
		city.setId(id);
		city.setVersion(version);
		return city;

	}

	protected void checkParamsForRemovingCityList(HealthUserContext userContext, String platformId,
			String cityIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String cityIdItem: cityIds){
			checkerOf(userContext).checkIdOfCity(cityIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeCityList(HealthUserContext userContext, String platformId,
			String cityIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingCityList(userContext, platformId,  cityIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveCityList(platform, cityIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withCityList().done());
				deleteRelationListInGraph(userContext, platform.getCityList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingCity(HealthUserContext userContext, String platformId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfCity(cityId);
		checkerOf(userContext).checkVersionOfCity(cityVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeCity(HealthUserContext userContext, String platformId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingCity(userContext,platformId, cityId, cityVersion,tokensExpr);

		City city = createIndexedCity(cityId, cityVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeCity( city );
			platform = savePlatform(userContext, platform, tokens().withCityList().done());
			deleteRelationInGraph(userContext, city);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingCity(HealthUserContext userContext, String platformId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfCity(cityId);
		checkerOf(userContext).checkVersionOfCity(cityVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyCityFrom(HealthUserContext userContext, String platformId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingCity(userContext,platformId, cityId, cityVersion,tokensExpr);

		City city = createIndexedCity(cityId, cityVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyCityFrom( city );
			platform = savePlatform(userContext, platform, tokens().withCityList().done());
			
			userContext.getManagerGroup().getCityManager().onNewInstanceCreated(userContext, (City)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingCity(HealthUserContext userContext, String platformId, String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfCity(cityId);
		checkerOf(userContext).checkVersionOfCity(cityVersion);
		

		if(City.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfCity(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateCity(HealthUserContext userContext, String platformId, String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingCity(userContext, platformId, cityId, cityVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withCityList().searchCityListWith(City.ID_PROPERTY, "eq", cityId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeCity( city );
			//make changes to AcceleraterAccount.
			City cityIndex = createIndexedCity(cityId, cityVersion);

			City city = platform.findTheCity(cityIndex);
			if(city == null){
				throw new PlatformManagerException(city+" is NOT FOUND" );
			}

			city.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withCityList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingDistrict(HealthUserContext userContext, String platformId, String name, String cityId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfDistrict(name);
		
		checkerOf(userContext).checkCityIdOfDistrict(cityId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addDistrict(HealthUserContext userContext, String platformId, String name, String cityId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingDistrict(userContext,platformId,name, cityId,tokensExpr);

		District district = createDistrict(userContext,name, cityId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addDistrict( district );
			platform = savePlatform(userContext, platform, tokens().withDistrictList().done());
			
			userContext.getManagerGroup().getDistrictManager().onNewInstanceCreated(userContext, district);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingDistrictProperties(HealthUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfDistrict(id);

		checkerOf(userContext).checkNameOfDistrict( name);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateDistrictProperties(HealthUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDistrictProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withDistrictListList()
				.searchDistrictListWith(District.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getDistrictList().isEmpty()){
			throw new PlatformManagerException("District is NOT FOUND with id: '"+id+"'");
		}

		District item = platformToUpdate.getDistrictList().first();

		item.updateName( name );


		//checkParamsForAddingDistrict(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withDistrictList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected District createDistrict(HealthUserContext userContext, String name, String cityId) throws Exception{

		District district = new District();
		
		
		district.setName(name);		
		City  city = new City();
		city.setId(cityId);		
		district.setCity(city);		
		district.setCreateTime(userContext.now());
	
		
		return district;


	}

	protected District createIndexedDistrict(String id, int version){

		District district = new District();
		district.setId(id);
		district.setVersion(version);
		return district;

	}

	protected void checkParamsForRemovingDistrictList(HealthUserContext userContext, String platformId,
			String districtIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String districtIdItem: districtIds){
			checkerOf(userContext).checkIdOfDistrict(districtIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeDistrictList(HealthUserContext userContext, String platformId,
			String districtIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingDistrictList(userContext, platformId,  districtIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveDistrictList(platform, districtIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withDistrictList().done());
				deleteRelationListInGraph(userContext, platform.getDistrictList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingDistrict(HealthUserContext userContext, String platformId,
		String districtId, int districtVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).checkVersionOfDistrict(districtVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeDistrict(HealthUserContext userContext, String platformId,
		String districtId, int districtVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingDistrict(userContext,platformId, districtId, districtVersion,tokensExpr);

		District district = createIndexedDistrict(districtId, districtVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeDistrict( district );
			platform = savePlatform(userContext, platform, tokens().withDistrictList().done());
			deleteRelationInGraph(userContext, district);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingDistrict(HealthUserContext userContext, String platformId,
		String districtId, int districtVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).checkVersionOfDistrict(districtVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyDistrictFrom(HealthUserContext userContext, String platformId,
		String districtId, int districtVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingDistrict(userContext,platformId, districtId, districtVersion,tokensExpr);

		District district = createIndexedDistrict(districtId, districtVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyDistrictFrom( district );
			platform = savePlatform(userContext, platform, tokens().withDistrictList().done());
			
			userContext.getManagerGroup().getDistrictManager().onNewInstanceCreated(userContext, (District)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingDistrict(HealthUserContext userContext, String platformId, String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).checkVersionOfDistrict(districtVersion);
		

		if(District.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfDistrict(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateDistrict(HealthUserContext userContext, String platformId, String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingDistrict(userContext, platformId, districtId, districtVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withDistrictList().searchDistrictListWith(District.ID_PROPERTY, "eq", districtId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeDistrict( district );
			//make changes to AcceleraterAccount.
			District districtIndex = createIndexedDistrict(districtId, districtVersion);

			District district = platform.findTheDistrict(districtIndex);
			if(district == null){
				throw new PlatformManagerException(district+" is NOT FOUND" );
			}

			district.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withDistrictList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingSchoolClass(HealthUserContext userContext, String platformId, String name, String classTeacherId, String schoole, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfSchoolClass(name);
		
		checkerOf(userContext).checkClassTeacherIdOfSchoolClass(classTeacherId);
		
		checkerOf(userContext).checkSchooleOfSchoolClass(schoole);
		
		checkerOf(userContext).checkCqIdOfSchoolClass(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addSchoolClass(HealthUserContext userContext, String platformId, String name, String classTeacherId, String schoole, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingSchoolClass(userContext,platformId,name, classTeacherId, schoole, cqId,tokensExpr);

		SchoolClass schoolClass = createSchoolClass(userContext,name, classTeacherId, schoole, cqId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addSchoolClass( schoolClass );
			platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
			
			userContext.getManagerGroup().getSchoolClassManager().onNewInstanceCreated(userContext, schoolClass);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSchoolClassProperties(HealthUserContext userContext, String platformId,String id,String name,String schoole,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfSchoolClass(id);

		checkerOf(userContext).checkNameOfSchoolClass( name);
		checkerOf(userContext).checkSchooleOfSchoolClass( schoole);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateSchoolClassProperties(HealthUserContext userContext, String platformId, String id,String name,String schoole, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSchoolClassProperties(userContext,platformId,id,name,schoole,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSchoolClassListList()
				.searchSchoolClassListWith(SchoolClass.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getSchoolClassList().isEmpty()){
			throw new PlatformManagerException("SchoolClass is NOT FOUND with id: '"+id+"'");
		}

		SchoolClass item = platformToUpdate.getSchoolClassList().first();

		item.updateName( name );
		item.updateSchoole( schoole );


		//checkParamsForAddingSchoolClass(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withSchoolClassList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected SchoolClass createSchoolClass(HealthUserContext userContext, String name, String classTeacherId, String schoole, String cqId) throws Exception{

		SchoolClass schoolClass = new SchoolClass();
		
		
		schoolClass.setName(name);		
		Teacher  classTeacher = new Teacher();
		classTeacher.setId(classTeacherId);		
		schoolClass.setClassTeacher(classTeacher);		
		schoolClass.setCreateTime(userContext.now());		
		schoolClass.setSchoole(schoole);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		schoolClass.setCq(cq);
	
		
		return schoolClass;


	}

	protected SchoolClass createIndexedSchoolClass(String id, int version){

		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setId(id);
		schoolClass.setVersion(version);
		return schoolClass;

	}

	protected void checkParamsForRemovingSchoolClassList(HealthUserContext userContext, String platformId,
			String schoolClassIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String schoolClassIdItem: schoolClassIds){
			checkerOf(userContext).checkIdOfSchoolClass(schoolClassIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeSchoolClassList(HealthUserContext userContext, String platformId,
			String schoolClassIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingSchoolClassList(userContext, platformId,  schoolClassIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveSchoolClassList(platform, schoolClassIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
				deleteRelationListInGraph(userContext, platform.getSchoolClassList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingSchoolClass(HealthUserContext userContext, String platformId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeSchoolClass(HealthUserContext userContext, String platformId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingSchoolClass(userContext,platformId, schoolClassId, schoolClassVersion,tokensExpr);

		SchoolClass schoolClass = createIndexedSchoolClass(schoolClassId, schoolClassVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeSchoolClass( schoolClass );
			platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
			deleteRelationInGraph(userContext, schoolClass);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingSchoolClass(HealthUserContext userContext, String platformId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copySchoolClassFrom(HealthUserContext userContext, String platformId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingSchoolClass(userContext,platformId, schoolClassId, schoolClassVersion,tokensExpr);

		SchoolClass schoolClass = createIndexedSchoolClass(schoolClassId, schoolClassVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copySchoolClassFrom( schoolClass );
			platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
			
			userContext.getManagerGroup().getSchoolClassManager().onNewInstanceCreated(userContext, (SchoolClass)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingSchoolClass(HealthUserContext userContext, String platformId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		

		if(SchoolClass.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfSchoolClass(parseString(newValueExpr));
		}
		
		if(SchoolClass.SCHOOLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchooleOfSchoolClass(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateSchoolClass(HealthUserContext userContext, String platformId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingSchoolClass(userContext, platformId, schoolClassId, schoolClassVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withSchoolClassList().searchSchoolClassListWith(SchoolClass.ID_PROPERTY, "eq", schoolClassId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeSchoolClass( schoolClass );
			//make changes to AcceleraterAccount.
			SchoolClass schoolClassIndex = createIndexedSchoolClass(schoolClassId, schoolClassVersion);

			SchoolClass schoolClass = platform.findTheSchoolClass(schoolClassIndex);
			if(schoolClass == null){
				throw new PlatformManagerException(schoolClass+" is NOT FOUND" );
			}

			schoolClass.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withSchoolClassList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingTeacher(HealthUserContext userContext, String platformId, String name, String mobile, String schoole, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfTeacher(name);
		
		checkerOf(userContext).checkMobileOfTeacher(mobile);
		
		checkerOf(userContext).checkSchooleOfTeacher(schoole);
		
		checkerOf(userContext).checkCqIdOfTeacher(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addTeacher(HealthUserContext userContext, String platformId, String name, String mobile, String schoole, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingTeacher(userContext,platformId,name, mobile, schoole, cqId,tokensExpr);

		Teacher teacher = createTeacher(userContext,name, mobile, schoole, cqId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addTeacher( teacher );
			platform = savePlatform(userContext, platform, tokens().withTeacherList().done());
			
			userContext.getManagerGroup().getTeacherManager().onNewInstanceCreated(userContext, teacher);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTeacherProperties(HealthUserContext userContext, String platformId,String id,String name,String mobile,String schoole,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfTeacher(id);

		checkerOf(userContext).checkNameOfTeacher( name);
		checkerOf(userContext).checkMobileOfTeacher( mobile);
		checkerOf(userContext).checkSchooleOfTeacher( schoole);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateTeacherProperties(HealthUserContext userContext, String platformId, String id,String name,String mobile,String schoole, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTeacherProperties(userContext,platformId,id,name,mobile,schoole,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTeacherListList()
				.searchTeacherListWith(Teacher.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getTeacherList().isEmpty()){
			throw new PlatformManagerException("Teacher is NOT FOUND with id: '"+id+"'");
		}

		Teacher item = platformToUpdate.getTeacherList().first();

		item.updateName( name );
		item.updateMobile( mobile );
		item.updateSchoole( schoole );


		//checkParamsForAddingTeacher(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withTeacherList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected Teacher createTeacher(HealthUserContext userContext, String name, String mobile, String schoole, String cqId) throws Exception{

		Teacher teacher = new Teacher();
		
		
		teacher.setName(name);		
		teacher.setMobile(mobile);		
		teacher.setSchoole(schoole);		
		teacher.setCreateTime(userContext.now());		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		teacher.setCq(cq);
	
		
		return teacher;


	}

	protected Teacher createIndexedTeacher(String id, int version){

		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setVersion(version);
		return teacher;

	}

	protected void checkParamsForRemovingTeacherList(HealthUserContext userContext, String platformId,
			String teacherIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String teacherIdItem: teacherIds){
			checkerOf(userContext).checkIdOfTeacher(teacherIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeTeacherList(HealthUserContext userContext, String platformId,
			String teacherIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingTeacherList(userContext, platformId,  teacherIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveTeacherList(platform, teacherIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withTeacherList().done());
				deleteRelationListInGraph(userContext, platform.getTeacherList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingTeacher(HealthUserContext userContext, String platformId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkVersionOfTeacher(teacherVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeTeacher(HealthUserContext userContext, String platformId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingTeacher(userContext,platformId, teacherId, teacherVersion,tokensExpr);

		Teacher teacher = createIndexedTeacher(teacherId, teacherVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeTeacher( teacher );
			platform = savePlatform(userContext, platform, tokens().withTeacherList().done());
			deleteRelationInGraph(userContext, teacher);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingTeacher(HealthUserContext userContext, String platformId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkVersionOfTeacher(teacherVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyTeacherFrom(HealthUserContext userContext, String platformId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingTeacher(userContext,platformId, teacherId, teacherVersion,tokensExpr);

		Teacher teacher = createIndexedTeacher(teacherId, teacherVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyTeacherFrom( teacher );
			platform = savePlatform(userContext, platform, tokens().withTeacherList().done());
			
			userContext.getManagerGroup().getTeacherManager().onNewInstanceCreated(userContext, (Teacher)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingTeacher(HealthUserContext userContext, String platformId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkVersionOfTeacher(teacherVersion);
		

		if(Teacher.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfTeacher(parseString(newValueExpr));
		}
		
		if(Teacher.MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkMobileOfTeacher(parseString(newValueExpr));
		}
		
		if(Teacher.SCHOOLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchooleOfTeacher(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateTeacher(HealthUserContext userContext, String platformId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingTeacher(userContext, platformId, teacherId, teacherVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withTeacherList().searchTeacherListWith(Teacher.ID_PROPERTY, "eq", teacherId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeTeacher( teacher );
			//make changes to AcceleraterAccount.
			Teacher teacherIndex = createIndexedTeacher(teacherId, teacherVersion);

			Teacher teacher = platform.findTheTeacher(teacherIndex);
			if(teacher == null){
				throw new PlatformManagerException(teacher+" is NOT FOUND" );
			}

			teacher.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withTeacherList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingGuardian(HealthUserContext userContext, String platformId, String name, String mobile, String addressId, String wechatUserId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfGuardian(name);
		
		checkerOf(userContext).checkMobileOfGuardian(mobile);
		
		checkerOf(userContext).checkAddressIdOfGuardian(addressId);
		
		checkerOf(userContext).checkWechatUserIdOfGuardian(wechatUserId);
		
		checkerOf(userContext).checkCqIdOfGuardian(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addGuardian(HealthUserContext userContext, String platformId, String name, String mobile, String addressId, String wechatUserId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingGuardian(userContext,platformId,name, mobile, addressId, wechatUserId, cqId,tokensExpr);

		Guardian guardian = createGuardian(userContext,name, mobile, addressId, wechatUserId, cqId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addGuardian( guardian );
			platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
			
			userContext.getManagerGroup().getGuardianManager().onNewInstanceCreated(userContext, guardian);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingGuardianProperties(HealthUserContext userContext, String platformId,String id,String name,String mobile,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfGuardian(id);

		checkerOf(userContext).checkNameOfGuardian( name);
		checkerOf(userContext).checkMobileOfGuardian( mobile);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateGuardianProperties(HealthUserContext userContext, String platformId, String id,String name,String mobile, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingGuardianProperties(userContext,platformId,id,name,mobile,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withGuardianListList()
				.searchGuardianListWith(Guardian.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getGuardianList().isEmpty()){
			throw new PlatformManagerException("Guardian is NOT FOUND with id: '"+id+"'");
		}

		Guardian item = platformToUpdate.getGuardianList().first();

		item.updateName( name );
		item.updateMobile( mobile );


		//checkParamsForAddingGuardian(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withGuardianList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected Guardian createGuardian(HealthUserContext userContext, String name, String mobile, String addressId, String wechatUserId, String cqId) throws Exception{

		Guardian guardian = new Guardian();
		
		
		guardian.setName(name);		
		guardian.setMobile(mobile);		
		Location  address = new Location();
		address.setId(addressId);		
		guardian.setAddress(address);		
		WechatUser  wechatUser = new WechatUser();
		wechatUser.setId(wechatUserId);		
		guardian.setWechatUser(wechatUser);		
		guardian.setCreateTime(userContext.now());		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		guardian.setCq(cq);
	
		
		return guardian;


	}

	protected Guardian createIndexedGuardian(String id, int version){

		Guardian guardian = new Guardian();
		guardian.setId(id);
		guardian.setVersion(version);
		return guardian;

	}

	protected void checkParamsForRemovingGuardianList(HealthUserContext userContext, String platformId,
			String guardianIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String guardianIdItem: guardianIds){
			checkerOf(userContext).checkIdOfGuardian(guardianIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeGuardianList(HealthUserContext userContext, String platformId,
			String guardianIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingGuardianList(userContext, platformId,  guardianIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveGuardianList(platform, guardianIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
				deleteRelationListInGraph(userContext, platform.getGuardianList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingGuardian(HealthUserContext userContext, String platformId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeGuardian(HealthUserContext userContext, String platformId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingGuardian(userContext,platformId, guardianId, guardianVersion,tokensExpr);

		Guardian guardian = createIndexedGuardian(guardianId, guardianVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeGuardian( guardian );
			platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
			deleteRelationInGraph(userContext, guardian);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingGuardian(HealthUserContext userContext, String platformId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyGuardianFrom(HealthUserContext userContext, String platformId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingGuardian(userContext,platformId, guardianId, guardianVersion,tokensExpr);

		Guardian guardian = createIndexedGuardian(guardianId, guardianVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyGuardianFrom( guardian );
			platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
			
			userContext.getManagerGroup().getGuardianManager().onNewInstanceCreated(userContext, (Guardian)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingGuardian(HealthUserContext userContext, String platformId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		

		if(Guardian.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfGuardian(parseString(newValueExpr));
		}
		
		if(Guardian.MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkMobileOfGuardian(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateGuardian(HealthUserContext userContext, String platformId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingGuardian(userContext, platformId, guardianId, guardianVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withGuardianList().searchGuardianListWith(Guardian.ID_PROPERTY, "eq", guardianId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeGuardian( guardian );
			//make changes to AcceleraterAccount.
			Guardian guardianIndex = createIndexedGuardian(guardianId, guardianVersion);

			Guardian guardian = platform.findTheGuardian(guardianIndex);
			if(guardian == null){
				throw new PlatformManagerException(guardian+" is NOT FOUND" );
			}

			guardian.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withGuardianList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingQuestion(HealthUserContext userContext, String platformId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkTopicOfQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfQuestion(optionD);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addQuestion(HealthUserContext userContext, String platformId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingQuestion(userContext,platformId,topic, questionTypeId, optionA, optionB, optionC, optionD,tokensExpr);

		Question question = createQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addQuestion( question );
			platform = savePlatform(userContext, platform, tokens().withQuestionList().done());
			
			userContext.getManagerGroup().getQuestionManager().onNewInstanceCreated(userContext, question);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingQuestionProperties(HealthUserContext userContext, String platformId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfQuestion(id);

		checkerOf(userContext).checkTopicOfQuestion( topic);
		checkerOf(userContext).checkOptionAOfQuestion( optionA);
		checkerOf(userContext).checkOptionBOfQuestion( optionB);
		checkerOf(userContext).checkOptionCOfQuestion( optionC);
		checkerOf(userContext).checkOptionDOfQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateQuestionProperties(HealthUserContext userContext, String platformId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionProperties(userContext,platformId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withQuestionListList()
				.searchQuestionListWith(Question.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getQuestionList().isEmpty()){
			throw new PlatformManagerException("Question is NOT FOUND with id: '"+id+"'");
		}

		Question item = platformToUpdate.getQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingQuestion(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withQuestionList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected Question createQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD) throws Exception{

		Question question = new Question();
		
		
		question.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		question.setQuestionType(questionType);		
		question.setOptionA(optionA);		
		question.setOptionB(optionB);		
		question.setOptionC(optionC);		
		question.setOptionD(optionD);
	
		
		return question;


	}

	protected Question createIndexedQuestion(String id, int version){

		Question question = new Question();
		question.setId(id);
		question.setVersion(version);
		return question;

	}

	protected void checkParamsForRemovingQuestionList(HealthUserContext userContext, String platformId,
			String questionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String questionIdItem: questionIds){
			checkerOf(userContext).checkIdOfQuestion(questionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeQuestionList(HealthUserContext userContext, String platformId,
			String questionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingQuestionList(userContext, platformId,  questionIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveQuestionList(platform, questionIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withQuestionList().done());
				deleteRelationListInGraph(userContext, platform.getQuestionList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingQuestion(HealthUserContext userContext, String platformId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeQuestion(HealthUserContext userContext, String platformId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingQuestion(userContext,platformId, questionId, questionVersion,tokensExpr);

		Question question = createIndexedQuestion(questionId, questionVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeQuestion( question );
			platform = savePlatform(userContext, platform, tokens().withQuestionList().done());
			deleteRelationInGraph(userContext, question);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingQuestion(HealthUserContext userContext, String platformId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyQuestionFrom(HealthUserContext userContext, String platformId,
		String questionId, int questionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingQuestion(userContext,platformId, questionId, questionVersion,tokensExpr);

		Question question = createIndexedQuestion(questionId, questionVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyQuestionFrom( question );
			platform = savePlatform(userContext, platform, tokens().withQuestionList().done());
			
			userContext.getManagerGroup().getQuestionManager().onNewInstanceCreated(userContext, (Question)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingQuestion(HealthUserContext userContext, String platformId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfQuestion(questionId);
		checkerOf(userContext).checkVersionOfQuestion(questionVersion);
		

		if(Question.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfQuestion(parseString(newValueExpr));
		}
		
		if(Question.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfQuestion(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateQuestion(HealthUserContext userContext, String platformId, String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingQuestion(userContext, platformId, questionId, questionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withQuestionList().searchQuestionListWith(Question.ID_PROPERTY, "eq", questionId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeQuestion( question );
			//make changes to AcceleraterAccount.
			Question questionIndex = createIndexedQuestion(questionId, questionVersion);

			Question question = platform.findTheQuestion(questionIndex);
			if(question == null){
				throw new PlatformManagerException(question+" is NOT FOUND" );
			}

			question.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withQuestionList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingQuestionType(HealthUserContext userContext, String platformId, String name, String code,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfQuestionType(name);
		
		checkerOf(userContext).checkCodeOfQuestionType(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addQuestionType(HealthUserContext userContext, String platformId, String name, String code, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingQuestionType(userContext,platformId,name, code,tokensExpr);

		QuestionType questionType = createQuestionType(userContext,name, code);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addQuestionType( questionType );
			platform = savePlatform(userContext, platform, tokens().withQuestionTypeList().done());
			
			userContext.getManagerGroup().getQuestionTypeManager().onNewInstanceCreated(userContext, questionType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingQuestionTypeProperties(HealthUserContext userContext, String platformId,String id,String name,String code,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfQuestionType(id);

		checkerOf(userContext).checkNameOfQuestionType( name);
		checkerOf(userContext).checkCodeOfQuestionType( code);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateQuestionTypeProperties(HealthUserContext userContext, String platformId, String id,String name,String code, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionTypeProperties(userContext,platformId,id,name,code,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withQuestionTypeListList()
				.searchQuestionTypeListWith(QuestionType.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getQuestionTypeList().isEmpty()){
			throw new PlatformManagerException("QuestionType is NOT FOUND with id: '"+id+"'");
		}

		QuestionType item = platformToUpdate.getQuestionTypeList().first();

		item.updateName( name );
		item.updateCode( code );


		//checkParamsForAddingQuestionType(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withQuestionTypeList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected QuestionType createQuestionType(HealthUserContext userContext, String name, String code) throws Exception{

		QuestionType questionType = new QuestionType();
		
		
		questionType.setName(name);		
		questionType.setCode(code);
	
		
		return questionType;


	}

	protected QuestionType createIndexedQuestionType(String id, int version){

		QuestionType questionType = new QuestionType();
		questionType.setId(id);
		questionType.setVersion(version);
		return questionType;

	}

	protected void checkParamsForRemovingQuestionTypeList(HealthUserContext userContext, String platformId,
			String questionTypeIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String questionTypeIdItem: questionTypeIds){
			checkerOf(userContext).checkIdOfQuestionType(questionTypeIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeQuestionTypeList(HealthUserContext userContext, String platformId,
			String questionTypeIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingQuestionTypeList(userContext, platformId,  questionTypeIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveQuestionTypeList(platform, questionTypeIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withQuestionTypeList().done());
				deleteRelationListInGraph(userContext, platform.getQuestionTypeList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingQuestionType(HealthUserContext userContext, String platformId,
		String questionTypeId, int questionTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkVersionOfQuestionType(questionTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeQuestionType(HealthUserContext userContext, String platformId,
		String questionTypeId, int questionTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingQuestionType(userContext,platformId, questionTypeId, questionTypeVersion,tokensExpr);

		QuestionType questionType = createIndexedQuestionType(questionTypeId, questionTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeQuestionType( questionType );
			platform = savePlatform(userContext, platform, tokens().withQuestionTypeList().done());
			deleteRelationInGraph(userContext, questionType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingQuestionType(HealthUserContext userContext, String platformId,
		String questionTypeId, int questionTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkVersionOfQuestionType(questionTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyQuestionTypeFrom(HealthUserContext userContext, String platformId,
		String questionTypeId, int questionTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingQuestionType(userContext,platformId, questionTypeId, questionTypeVersion,tokensExpr);

		QuestionType questionType = createIndexedQuestionType(questionTypeId, questionTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyQuestionTypeFrom( questionType );
			platform = savePlatform(userContext, platform, tokens().withQuestionTypeList().done());
			
			userContext.getManagerGroup().getQuestionTypeManager().onNewInstanceCreated(userContext, (QuestionType)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingQuestionType(HealthUserContext userContext, String platformId, String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfQuestionType(questionTypeId);
		checkerOf(userContext).checkVersionOfQuestionType(questionTypeVersion);
		

		if(QuestionType.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfQuestionType(parseString(newValueExpr));
		}
		
		if(QuestionType.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfQuestionType(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateQuestionType(HealthUserContext userContext, String platformId, String questionTypeId, int questionTypeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingQuestionType(userContext, platformId, questionTypeId, questionTypeVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withQuestionTypeList().searchQuestionTypeListWith(QuestionType.ID_PROPERTY, "eq", questionTypeId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeQuestionType( questionType );
			//make changes to AcceleraterAccount.
			QuestionType questionTypeIndex = createIndexedQuestionType(questionTypeId, questionTypeVersion);

			QuestionType questionType = platform.findTheQuestionType(questionTypeIndex);
			if(questionType == null){
				throw new PlatformManagerException(questionType+" is NOT FOUND" );
			}

			questionType.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withQuestionTypeList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingQuestionSource(HealthUserContext userContext, String platformId, String name, String code,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfQuestionSource(name);
		
		checkerOf(userContext).checkCodeOfQuestionSource(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addQuestionSource(HealthUserContext userContext, String platformId, String name, String code, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingQuestionSource(userContext,platformId,name, code,tokensExpr);

		QuestionSource questionSource = createQuestionSource(userContext,name, code);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addQuestionSource( questionSource );
			platform = savePlatform(userContext, platform, tokens().withQuestionSourceList().done());
			
			userContext.getManagerGroup().getQuestionSourceManager().onNewInstanceCreated(userContext, questionSource);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingQuestionSourceProperties(HealthUserContext userContext, String platformId,String id,String name,String code,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfQuestionSource(id);

		checkerOf(userContext).checkNameOfQuestionSource( name);
		checkerOf(userContext).checkCodeOfQuestionSource( code);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateQuestionSourceProperties(HealthUserContext userContext, String platformId, String id,String name,String code, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingQuestionSourceProperties(userContext,platformId,id,name,code,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withQuestionSourceListList()
				.searchQuestionSourceListWith(QuestionSource.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getQuestionSourceList().isEmpty()){
			throw new PlatformManagerException("QuestionSource is NOT FOUND with id: '"+id+"'");
		}

		QuestionSource item = platformToUpdate.getQuestionSourceList().first();

		item.updateName( name );
		item.updateCode( code );


		//checkParamsForAddingQuestionSource(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withQuestionSourceList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected QuestionSource createQuestionSource(HealthUserContext userContext, String name, String code) throws Exception{

		QuestionSource questionSource = new QuestionSource();
		
		
		questionSource.setName(name);		
		questionSource.setCode(code);
	
		
		return questionSource;


	}

	protected QuestionSource createIndexedQuestionSource(String id, int version){

		QuestionSource questionSource = new QuestionSource();
		questionSource.setId(id);
		questionSource.setVersion(version);
		return questionSource;

	}

	protected void checkParamsForRemovingQuestionSourceList(HealthUserContext userContext, String platformId,
			String questionSourceIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String questionSourceIdItem: questionSourceIds){
			checkerOf(userContext).checkIdOfQuestionSource(questionSourceIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeQuestionSourceList(HealthUserContext userContext, String platformId,
			String questionSourceIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingQuestionSourceList(userContext, platformId,  questionSourceIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveQuestionSourceList(platform, questionSourceIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withQuestionSourceList().done());
				deleteRelationListInGraph(userContext, platform.getQuestionSourceList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingQuestionSource(HealthUserContext userContext, String platformId,
		String questionSourceId, int questionSourceVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).checkVersionOfQuestionSource(questionSourceVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeQuestionSource(HealthUserContext userContext, String platformId,
		String questionSourceId, int questionSourceVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingQuestionSource(userContext,platformId, questionSourceId, questionSourceVersion,tokensExpr);

		QuestionSource questionSource = createIndexedQuestionSource(questionSourceId, questionSourceVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeQuestionSource( questionSource );
			platform = savePlatform(userContext, platform, tokens().withQuestionSourceList().done());
			deleteRelationInGraph(userContext, questionSource);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingQuestionSource(HealthUserContext userContext, String platformId,
		String questionSourceId, int questionSourceVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).checkVersionOfQuestionSource(questionSourceVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyQuestionSourceFrom(HealthUserContext userContext, String platformId,
		String questionSourceId, int questionSourceVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingQuestionSource(userContext,platformId, questionSourceId, questionSourceVersion,tokensExpr);

		QuestionSource questionSource = createIndexedQuestionSource(questionSourceId, questionSourceVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyQuestionSourceFrom( questionSource );
			platform = savePlatform(userContext, platform, tokens().withQuestionSourceList().done());
			
			userContext.getManagerGroup().getQuestionSourceManager().onNewInstanceCreated(userContext, (QuestionSource)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingQuestionSource(HealthUserContext userContext, String platformId, String questionSourceId, int questionSourceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfQuestionSource(questionSourceId);
		checkerOf(userContext).checkVersionOfQuestionSource(questionSourceVersion);
		

		if(QuestionSource.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfQuestionSource(parseString(newValueExpr));
		}
		
		if(QuestionSource.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfQuestionSource(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateQuestionSource(HealthUserContext userContext, String platformId, String questionSourceId, int questionSourceVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingQuestionSource(userContext, platformId, questionSourceId, questionSourceVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withQuestionSourceList().searchQuestionSourceListWith(QuestionSource.ID_PROPERTY, "eq", questionSourceId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeQuestionSource( questionSource );
			//make changes to AcceleraterAccount.
			QuestionSource questionSourceIndex = createIndexedQuestionSource(questionSourceId, questionSourceVersion);

			QuestionSource questionSource = platform.findTheQuestionSource(questionSourceIndex);
			if(questionSource == null){
				throw new PlatformManagerException(questionSource+" is NOT FOUND" );
			}

			questionSource.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withQuestionSourceList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingSurveyStatus(HealthUserContext userContext, String platformId, String name, String code,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfSurveyStatus(name);
		
		checkerOf(userContext).checkCodeOfSurveyStatus(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addSurveyStatus(HealthUserContext userContext, String platformId, String name, String code, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingSurveyStatus(userContext,platformId,name, code,tokensExpr);

		SurveyStatus surveyStatus = createSurveyStatus(userContext,name, code);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addSurveyStatus( surveyStatus );
			platform = savePlatform(userContext, platform, tokens().withSurveyStatusList().done());
			
			userContext.getManagerGroup().getSurveyStatusManager().onNewInstanceCreated(userContext, surveyStatus);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSurveyStatusProperties(HealthUserContext userContext, String platformId,String id,String name,String code,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfSurveyStatus(id);

		checkerOf(userContext).checkNameOfSurveyStatus( name);
		checkerOf(userContext).checkCodeOfSurveyStatus( code);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateSurveyStatusProperties(HealthUserContext userContext, String platformId, String id,String name,String code, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSurveyStatusProperties(userContext,platformId,id,name,code,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSurveyStatusListList()
				.searchSurveyStatusListWith(SurveyStatus.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getSurveyStatusList().isEmpty()){
			throw new PlatformManagerException("SurveyStatus is NOT FOUND with id: '"+id+"'");
		}

		SurveyStatus item = platformToUpdate.getSurveyStatusList().first();

		item.updateName( name );
		item.updateCode( code );


		//checkParamsForAddingSurveyStatus(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withSurveyStatusList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected SurveyStatus createSurveyStatus(HealthUserContext userContext, String name, String code) throws Exception{

		SurveyStatus surveyStatus = new SurveyStatus();
		
		
		surveyStatus.setName(name);		
		surveyStatus.setCode(code);
	
		
		return surveyStatus;


	}

	protected SurveyStatus createIndexedSurveyStatus(String id, int version){

		SurveyStatus surveyStatus = new SurveyStatus();
		surveyStatus.setId(id);
		surveyStatus.setVersion(version);
		return surveyStatus;

	}

	protected void checkParamsForRemovingSurveyStatusList(HealthUserContext userContext, String platformId,
			String surveyStatusIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String surveyStatusIdItem: surveyStatusIds){
			checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeSurveyStatusList(HealthUserContext userContext, String platformId,
			String surveyStatusIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingSurveyStatusList(userContext, platformId,  surveyStatusIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveSurveyStatusList(platform, surveyStatusIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withSurveyStatusList().done());
				deleteRelationListInGraph(userContext, platform.getSurveyStatusList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingSurveyStatus(HealthUserContext userContext, String platformId,
		String surveyStatusId, int surveyStatusVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).checkVersionOfSurveyStatus(surveyStatusVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeSurveyStatus(HealthUserContext userContext, String platformId,
		String surveyStatusId, int surveyStatusVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingSurveyStatus(userContext,platformId, surveyStatusId, surveyStatusVersion,tokensExpr);

		SurveyStatus surveyStatus = createIndexedSurveyStatus(surveyStatusId, surveyStatusVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeSurveyStatus( surveyStatus );
			platform = savePlatform(userContext, platform, tokens().withSurveyStatusList().done());
			deleteRelationInGraph(userContext, surveyStatus);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingSurveyStatus(HealthUserContext userContext, String platformId,
		String surveyStatusId, int surveyStatusVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).checkVersionOfSurveyStatus(surveyStatusVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copySurveyStatusFrom(HealthUserContext userContext, String platformId,
		String surveyStatusId, int surveyStatusVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingSurveyStatus(userContext,platformId, surveyStatusId, surveyStatusVersion,tokensExpr);

		SurveyStatus surveyStatus = createIndexedSurveyStatus(surveyStatusId, surveyStatusVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copySurveyStatusFrom( surveyStatus );
			platform = savePlatform(userContext, platform, tokens().withSurveyStatusList().done());
			
			userContext.getManagerGroup().getSurveyStatusManager().onNewInstanceCreated(userContext, (SurveyStatus)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingSurveyStatus(HealthUserContext userContext, String platformId, String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).checkVersionOfSurveyStatus(surveyStatusVersion);
		

		if(SurveyStatus.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfSurveyStatus(parseString(newValueExpr));
		}
		
		if(SurveyStatus.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfSurveyStatus(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateSurveyStatus(HealthUserContext userContext, String platformId, String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingSurveyStatus(userContext, platformId, surveyStatusId, surveyStatusVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withSurveyStatusList().searchSurveyStatusListWith(SurveyStatus.ID_PROPERTY, "eq", surveyStatusId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeSurveyStatus( surveyStatus );
			//make changes to AcceleraterAccount.
			SurveyStatus surveyStatusIndex = createIndexedSurveyStatus(surveyStatusId, surveyStatusVersion);

			SurveyStatus surveyStatus = platform.findTheSurveyStatus(surveyStatusIndex);
			if(surveyStatus == null){
				throw new PlatformManagerException(surveyStatus+" is NOT FOUND" );
			}

			surveyStatus.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withSurveyStatusList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingWechatUser(HealthUserContext userContext, String platformId, String name, String avatar, String addressId, String userTypeId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfWechatUser(name);
		
		checkerOf(userContext).checkAvatarOfWechatUser(avatar);
		
		checkerOf(userContext).checkAddressIdOfWechatUser(addressId);
		
		checkerOf(userContext).checkUserTypeIdOfWechatUser(userTypeId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addWechatUser(HealthUserContext userContext, String platformId, String name, String avatar, String addressId, String userTypeId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingWechatUser(userContext,platformId,name, avatar, addressId, userTypeId,tokensExpr);

		WechatUser wechatUser = createWechatUser(userContext,name, avatar, addressId, userTypeId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addWechatUser( wechatUser );
			platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
			
			userContext.getManagerGroup().getWechatUserManager().onNewInstanceCreated(userContext, wechatUser);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingWechatUserProperties(HealthUserContext userContext, String platformId,String id,String name,String avatar,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfWechatUser(id);

		checkerOf(userContext).checkNameOfWechatUser( name);
		checkerOf(userContext).checkAvatarOfWechatUser( avatar);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateWechatUserProperties(HealthUserContext userContext, String platformId, String id,String name,String avatar, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWechatUserProperties(userContext,platformId,id,name,avatar,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withWechatUserListList()
				.searchWechatUserListWith(WechatUser.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getWechatUserList().isEmpty()){
			throw new PlatformManagerException("WechatUser is NOT FOUND with id: '"+id+"'");
		}

		WechatUser item = platformToUpdate.getWechatUserList().first();

		item.updateName( name );
		item.updateAvatar( avatar );


		//checkParamsForAddingWechatUser(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withWechatUserList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected WechatUser createWechatUser(HealthUserContext userContext, String name, String avatar, String addressId, String userTypeId) throws Exception{

		WechatUser wechatUser = new WechatUser();
		
		
		wechatUser.setName(name);		
		wechatUser.setAvatar(avatar);		
		Location  address = new Location();
		address.setId(addressId);		
		wechatUser.setAddress(address);		
		UserType  userType = new UserType();
		userType.setId(userTypeId);		
		wechatUser.setUserType(userType);		
		wechatUser.setCreateTime(userContext.now());
	
		
		return wechatUser;


	}

	protected WechatUser createIndexedWechatUser(String id, int version){

		WechatUser wechatUser = new WechatUser();
		wechatUser.setId(id);
		wechatUser.setVersion(version);
		return wechatUser;

	}

	protected void checkParamsForRemovingWechatUserList(HealthUserContext userContext, String platformId,
			String wechatUserIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String wechatUserIdItem: wechatUserIds){
			checkerOf(userContext).checkIdOfWechatUser(wechatUserIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeWechatUserList(HealthUserContext userContext, String platformId,
			String wechatUserIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingWechatUserList(userContext, platformId,  wechatUserIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveWechatUserList(platform, wechatUserIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
				deleteRelationListInGraph(userContext, platform.getWechatUserList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingWechatUser(HealthUserContext userContext, String platformId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser(wechatUserVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeWechatUser(HealthUserContext userContext, String platformId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingWechatUser(userContext,platformId, wechatUserId, wechatUserVersion,tokensExpr);

		WechatUser wechatUser = createIndexedWechatUser(wechatUserId, wechatUserVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeWechatUser( wechatUser );
			platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
			deleteRelationInGraph(userContext, wechatUser);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingWechatUser(HealthUserContext userContext, String platformId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser(wechatUserVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyWechatUserFrom(HealthUserContext userContext, String platformId,
		String wechatUserId, int wechatUserVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingWechatUser(userContext,platformId, wechatUserId, wechatUserVersion,tokensExpr);

		WechatUser wechatUser = createIndexedWechatUser(wechatUserId, wechatUserVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyWechatUserFrom( wechatUser );
			platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
			
			userContext.getManagerGroup().getWechatUserManager().onNewInstanceCreated(userContext, (WechatUser)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingWechatUser(HealthUserContext userContext, String platformId, String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfWechatUser(wechatUserId);
		checkerOf(userContext).checkVersionOfWechatUser(wechatUserVersion);
		

		if(WechatUser.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfWechatUser(parseString(newValueExpr));
		}
		
		if(WechatUser.AVATAR_PROPERTY.equals(property)){
			checkerOf(userContext).checkAvatarOfWechatUser(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateWechatUser(HealthUserContext userContext, String platformId, String wechatUserId, int wechatUserVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingWechatUser(userContext, platformId, wechatUserId, wechatUserVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withWechatUserList().searchWechatUserListWith(WechatUser.ID_PROPERTY, "eq", wechatUserId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeWechatUser( wechatUser );
			//make changes to AcceleraterAccount.
			WechatUser wechatUserIndex = createIndexedWechatUser(wechatUserId, wechatUserVersion);

			WechatUser wechatUser = platform.findTheWechatUser(wechatUserIndex);
			if(wechatUser == null){
				throw new PlatformManagerException(wechatUser+" is NOT FOUND" );
			}

			wechatUser.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withWechatUserList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingUserType(HealthUserContext userContext, String platformId, String name, String code,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfUserType(name);
		
		checkerOf(userContext).checkCodeOfUserType(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addUserType(HealthUserContext userContext, String platformId, String name, String code, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingUserType(userContext,platformId,name, code,tokensExpr);

		UserType userType = createUserType(userContext,name, code);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addUserType( userType );
			platform = savePlatform(userContext, platform, tokens().withUserTypeList().done());
			
			userContext.getManagerGroup().getUserTypeManager().onNewInstanceCreated(userContext, userType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserTypeProperties(HealthUserContext userContext, String platformId,String id,String name,String code,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfUserType(id);

		checkerOf(userContext).checkNameOfUserType( name);
		checkerOf(userContext).checkCodeOfUserType( code);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateUserTypeProperties(HealthUserContext userContext, String platformId, String id,String name,String code, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserTypeProperties(userContext,platformId,id,name,code,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserTypeListList()
				.searchUserTypeListWith(UserType.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getUserTypeList().isEmpty()){
			throw new PlatformManagerException("UserType is NOT FOUND with id: '"+id+"'");
		}

		UserType item = platformToUpdate.getUserTypeList().first();

		item.updateName( name );
		item.updateCode( code );


		//checkParamsForAddingUserType(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withUserTypeList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected UserType createUserType(HealthUserContext userContext, String name, String code) throws Exception{

		UserType userType = new UserType();
		
		
		userType.setName(name);		
		userType.setCode(code);
	
		
		return userType;


	}

	protected UserType createIndexedUserType(String id, int version){

		UserType userType = new UserType();
		userType.setId(id);
		userType.setVersion(version);
		return userType;

	}

	protected void checkParamsForRemovingUserTypeList(HealthUserContext userContext, String platformId,
			String userTypeIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String userTypeIdItem: userTypeIds){
			checkerOf(userContext).checkIdOfUserType(userTypeIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeUserTypeList(HealthUserContext userContext, String platformId,
			String userTypeIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingUserTypeList(userContext, platformId,  userTypeIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveUserTypeList(platform, userTypeIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withUserTypeList().done());
				deleteRelationListInGraph(userContext, platform.getUserTypeList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingUserType(HealthUserContext userContext, String platformId,
		String userTypeId, int userTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).checkVersionOfUserType(userTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeUserType(HealthUserContext userContext, String platformId,
		String userTypeId, int userTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingUserType(userContext,platformId, userTypeId, userTypeVersion,tokensExpr);

		UserType userType = createIndexedUserType(userTypeId, userTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeUserType( userType );
			platform = savePlatform(userContext, platform, tokens().withUserTypeList().done());
			deleteRelationInGraph(userContext, userType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingUserType(HealthUserContext userContext, String platformId,
		String userTypeId, int userTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).checkVersionOfUserType(userTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyUserTypeFrom(HealthUserContext userContext, String platformId,
		String userTypeId, int userTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingUserType(userContext,platformId, userTypeId, userTypeVersion,tokensExpr);

		UserType userType = createIndexedUserType(userTypeId, userTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyUserTypeFrom( userType );
			platform = savePlatform(userContext, platform, tokens().withUserTypeList().done());
			
			userContext.getManagerGroup().getUserTypeManager().onNewInstanceCreated(userContext, (UserType)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingUserType(HealthUserContext userContext, String platformId, String userTypeId, int userTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfUserType(userTypeId);
		checkerOf(userContext).checkVersionOfUserType(userTypeVersion);
		

		if(UserType.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfUserType(parseString(newValueExpr));
		}
		
		if(UserType.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfUserType(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateUserType(HealthUserContext userContext, String platformId, String userTypeId, int userTypeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingUserType(userContext, platformId, userTypeId, userTypeVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withUserTypeList().searchUserTypeListWith(UserType.ID_PROPERTY, "eq", userTypeId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeUserType( userType );
			//make changes to AcceleraterAccount.
			UserType userTypeIndex = createIndexedUserType(userTypeId, userTypeVersion);

			UserType userType = platform.findTheUserType(userTypeIndex);
			if(userType == null){
				throw new PlatformManagerException(userType+" is NOT FOUND" );
			}

			userType.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withUserTypeList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingChangeRequest(HealthUserContext userContext, String platformId, String name, String requestTypeId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfChangeRequest(name);
		
		checkerOf(userContext).checkRequestTypeIdOfChangeRequest(requestTypeId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addChangeRequest(HealthUserContext userContext, String platformId, String name, String requestTypeId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequest(userContext,platformId,name, requestTypeId,tokensExpr);

		ChangeRequest changeRequest = createChangeRequest(userContext,name, requestTypeId);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addChangeRequest( changeRequest );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, changeRequest);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestProperties(HealthUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequest(id);

		checkerOf(userContext).checkNameOfChangeRequest( name);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateChangeRequestProperties(HealthUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestListList()
				.searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getChangeRequestList().isEmpty()){
			throw new PlatformManagerException("ChangeRequest is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequest item = platformToUpdate.getChangeRequestList().first();

		item.updateName( name );


		//checkParamsForAddingChangeRequest(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withChangeRequestList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequest createChangeRequest(HealthUserContext userContext, String name, String requestTypeId) throws Exception{

		ChangeRequest changeRequest = new ChangeRequest();
		
		
		changeRequest.setName(name);		
		changeRequest.setCreateTime(userContext.now());		
		changeRequest.setRemoteIp(userContext.getRemoteIP());		
		ChangeRequestType  requestType = new ChangeRequestType();
		requestType.setId(requestTypeId);		
		changeRequest.setRequestType(requestType);
	
		
		return changeRequest;


	}

	protected ChangeRequest createIndexedChangeRequest(String id, int version){

		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(id);
		changeRequest.setVersion(version);
		return changeRequest;

	}

	protected void checkParamsForRemovingChangeRequestList(HealthUserContext userContext, String platformId,
			String changeRequestIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String changeRequestIdItem: changeRequestIds){
			checkerOf(userContext).checkIdOfChangeRequest(changeRequestIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequestList(HealthUserContext userContext, String platformId,
			String changeRequestIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestList(userContext, platformId,  changeRequestIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveChangeRequestList(platform, changeRequestIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
				deleteRelationListInGraph(userContext, platform.getChangeRequestList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequest(HealthUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequest(HealthUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequest(userContext,platformId, changeRequestId, changeRequestVersion,tokensExpr);

		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeChangeRequest( changeRequest );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			deleteRelationInGraph(userContext, changeRequest);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequest(HealthUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyChangeRequestFrom(HealthUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequest(userContext,platformId, changeRequestId, changeRequestVersion,tokensExpr);

		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			changeRequest.updateRemoteIp(userContext.getRemoteIP());

			platform.copyChangeRequestFrom( changeRequest );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, (ChangeRequest)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequest(HealthUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfChangeRequest(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateChangeRequest(HealthUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequest(userContext, platformId, changeRequestId, changeRequestVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestList().searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "eq", changeRequestId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeChangeRequest( changeRequest );
			//make changes to AcceleraterAccount.
			ChangeRequest changeRequestIndex = createIndexedChangeRequest(changeRequestId, changeRequestVersion);

			ChangeRequest changeRequest = platform.findTheChangeRequest(changeRequestIndex);
			if(changeRequest == null){
				throw new PlatformManagerException(changeRequest+" is NOT FOUND" );
			}

			changeRequest.changeProperty(property, newValueExpr);
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingChangeRequestType(HealthUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfChangeRequestType(name);
		
		checkerOf(userContext).checkCodeOfChangeRequestType(code);
		
		checkerOf(userContext).checkIconOfChangeRequestType(icon);
		
		checkerOf(userContext).checkDisplayOrderOfChangeRequestType(displayOrder);
		
		checkerOf(userContext).checkBindTypesOfChangeRequestType(bindTypes);
		
		checkerOf(userContext).checkStepConfigurationOfChangeRequestType(stepConfiguration);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addChangeRequestType(HealthUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequestType(userContext,platformId,name, code, icon, displayOrder, bindTypes, stepConfiguration,tokensExpr);

		ChangeRequestType changeRequestType = createChangeRequestType(userContext,name, code, icon, displayOrder, bindTypes, stepConfiguration);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addChangeRequestType( changeRequestType );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			
			userContext.getManagerGroup().getChangeRequestTypeManager().onNewInstanceCreated(userContext, changeRequestType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestTypeProperties(HealthUserContext userContext, String platformId,String id,String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(id);

		checkerOf(userContext).checkNameOfChangeRequestType( name);
		checkerOf(userContext).checkCodeOfChangeRequestType( code);
		checkerOf(userContext).checkIconOfChangeRequestType( icon);
		checkerOf(userContext).checkDisplayOrderOfChangeRequestType( displayOrder);
		checkerOf(userContext).checkBindTypesOfChangeRequestType( bindTypes);
		checkerOf(userContext).checkStepConfigurationOfChangeRequestType( stepConfiguration);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateChangeRequestTypeProperties(HealthUserContext userContext, String platformId, String id,String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestTypeProperties(userContext,platformId,id,name,code,icon,displayOrder,bindTypes,stepConfiguration,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestTypeListList()
				.searchChangeRequestTypeListWith(ChangeRequestType.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getChangeRequestTypeList().isEmpty()){
			throw new PlatformManagerException("ChangeRequestType is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequestType item = platformToUpdate.getChangeRequestTypeList().first();

		item.updateName( name );
		item.updateCode( code );
		item.updateIcon( icon );
		item.updateDisplayOrder( displayOrder );
		item.updateBindTypes( bindTypes );
		item.updateStepConfiguration( stepConfiguration );


		//checkParamsForAddingChangeRequestType(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withChangeRequestTypeList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequestType createChangeRequestType(HealthUserContext userContext, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration) throws Exception{

		ChangeRequestType changeRequestType = new ChangeRequestType();
		
		
		changeRequestType.setName(name);		
		changeRequestType.setCode(code);		
		changeRequestType.setIcon(icon);		
		changeRequestType.setDisplayOrder(displayOrder);		
		changeRequestType.setBindTypes(bindTypes);		
		changeRequestType.setStepConfiguration(stepConfiguration);
	
		
		return changeRequestType;


	}

	protected ChangeRequestType createIndexedChangeRequestType(String id, int version){

		ChangeRequestType changeRequestType = new ChangeRequestType();
		changeRequestType.setId(id);
		changeRequestType.setVersion(version);
		return changeRequestType;

	}

	protected void checkParamsForRemovingChangeRequestTypeList(HealthUserContext userContext, String platformId,
			String changeRequestTypeIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String changeRequestTypeIdItem: changeRequestTypeIds){
			checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequestTypeList(HealthUserContext userContext, String platformId,
			String changeRequestTypeIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestTypeList(userContext, platformId,  changeRequestTypeIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveChangeRequestTypeList(platform, changeRequestTypeIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
				deleteRelationListInGraph(userContext, platform.getChangeRequestTypeList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequestType(HealthUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType(changeRequestTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequestType(HealthUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequestType(userContext,platformId, changeRequestTypeId, changeRequestTypeVersion,tokensExpr);

		ChangeRequestType changeRequestType = createIndexedChangeRequestType(changeRequestTypeId, changeRequestTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeChangeRequestType( changeRequestType );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			deleteRelationInGraph(userContext, changeRequestType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequestType(HealthUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType(changeRequestTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyChangeRequestTypeFrom(HealthUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequestType(userContext,platformId, changeRequestTypeId, changeRequestTypeVersion,tokensExpr);

		ChangeRequestType changeRequestType = createIndexedChangeRequestType(changeRequestTypeId, changeRequestTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyChangeRequestTypeFrom( changeRequestType );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			
			userContext.getManagerGroup().getChangeRequestTypeManager().onNewInstanceCreated(userContext, (ChangeRequestType)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequestType(HealthUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType(changeRequestTypeVersion);
		

		if(ChangeRequestType.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfChangeRequestType(parseString(newValueExpr));
		}
		
		if(ChangeRequestType.CODE_PROPERTY.equals(property)){
			checkerOf(userContext).checkCodeOfChangeRequestType(parseString(newValueExpr));
		}
		
		if(ChangeRequestType.ICON_PROPERTY.equals(property)){
			checkerOf(userContext).checkIconOfChangeRequestType(parseString(newValueExpr));
		}
		
		if(ChangeRequestType.DISPLAY_ORDER_PROPERTY.equals(property)){
			checkerOf(userContext).checkDisplayOrderOfChangeRequestType(parseInt(newValueExpr));
		}
		
		if(ChangeRequestType.BIND_TYPES_PROPERTY.equals(property)){
			checkerOf(userContext).checkBindTypesOfChangeRequestType(parseString(newValueExpr));
		}
		
		if(ChangeRequestType.STEP_CONFIGURATION_PROPERTY.equals(property)){
			checkerOf(userContext).checkStepConfigurationOfChangeRequestType(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateChangeRequestType(HealthUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequestType(userContext, platformId, changeRequestTypeId, changeRequestTypeVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestTypeList().searchChangeRequestTypeListWith(ChangeRequestType.ID_PROPERTY, "eq", changeRequestTypeId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeChangeRequestType( changeRequestType );
			//make changes to AcceleraterAccount.
			ChangeRequestType changeRequestTypeIndex = createIndexedChangeRequestType(changeRequestTypeId, changeRequestTypeVersion);

			ChangeRequestType changeRequestType = platform.findTheChangeRequestType(changeRequestTypeIndex);
			if(changeRequestType == null){
				throw new PlatformManagerException(changeRequestType+" is NOT FOUND" );
			}

			changeRequestType.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, Platform newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

	// -----------------------------------//  登录部分处理 \\-----------------------------------
	// 手机号+短信验证码 登录
	public Object loginByMobile(HealthUserContextImpl userContext, String mobile, String verifyCode) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByMobile");
		LoginData loginData = new LoginData();
		loginData.setMobile(mobile);
		loginData.setVerifyCode(verifyCode);

		LoginContext loginContext = LoginContext.of(LoginMethod.MOBILE, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 账号+密码登录
	public Object loginByPassword(HealthUserContextImpl userContext, String loginId, Password password) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(), "loginByPassword");
		LoginData loginData = new LoginData();
		loginData.setLoginId(loginId);
		loginData.setPassword(password.getClearTextPassword());

		LoginContext loginContext = LoginContext.of(LoginMethod.PASSWORD, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 微信小程序登录
	public Object loginByWechatMiniProgram(HealthUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 企业微信小程序登录
	public Object loginByWechatWorkMiniProgram(HealthUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(HealthBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatWorkMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_WORK_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 调用登录处理
	protected Object processLoginRequest(HealthUserContextImpl userContext, LoginContext loginContext) throws Exception {
		IamService iamService = (IamService) userContext.getBean("iamService");
		LoginResult loginResult = iamService.doLogin(userContext, loginContext, this);
		// 根据登录结果
		if (!loginResult.isAuthenticated()) {
			throw new Exception(loginResult.getMessage());
		}
		if (loginResult.isSuccess()) {
			return onLoginSuccess(userContext, loginResult);
		}
		if (loginResult.isNewUser()) {
			throw new Exception("请联系你的上级,先为你创建账号,然后再来登录.");
		}
		return new LoginForm();
	}
	
	@Override
	public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
			throws IllegalAccessException {
		HealthUserContextImpl userContext = (HealthUserContextImpl)baseUserContext;
		IamService iamService = (IamService) userContext.getBean("iamService");
		Map<String, Object> loginInfo = iamService.getCachedLoginInfo(userContext);
		
		SecUser secUser = iamService.tryToLoadSecUser(userContext, loginInfo);
		UserApp userApp = iamService.tryToLoadUserApp(userContext, loginInfo);
		if (userApp != null) {
			userApp.setSecUser(secUser);
		}
		afterSecUserAppLoadedWhenCheckAccess(userContext, loginInfo, secUser, userApp);
		if (!isMethodNeedLogin(userContext, methodName, parameters)) {
			return accessOK();
		}
		
		return super.checkAccess(baseUserContext, methodName, parameters);
	}
	
	// 判断哪些接口需要登录后才能执行. 默认除了loginBy开头的,其他都要登录
	protected boolean isMethodNeedLogin(HealthUserContextImpl userContext, String methodName, Object[] parameters) {
		if (methodName.startsWith("loginBy")) {
			return false;
		}
		if (methodName.startsWith("logout")) {
			return false;
		}
		return true;
	}

	// 在checkAccess中加载了secUser和userApp后会调用此方法,用于定制化的用户数据加载. 默认什么也不做
	protected void afterSecUserAppLoadedWhenCheckAccess(HealthUserContextImpl userContext, Map<String, Object> loginInfo,
			SecUser secUser, UserApp userApp) throws IllegalAccessException{
	}



	protected Object onLoginSuccess(HealthUserContext userContext, LoginResult loginResult) throws Exception {
		// by default, return the view of this object
		UserApp userApp = loginResult.getLoginContext().getLoginTarget().getUserApp();
		return this.view(userContext, userApp.getObjectId());
	}

	public void onAuthenticationFailed(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, failed is failed, nothing can do
	}
	public void onAuthenticateNewUserLogged(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, should create a account and bind with sec user, BUT, I don't know how to 
		// create new object as of generate this method. It depends on business logical. So,
		throw new Exception("请重载函数onAuthenticateNewUserLogged()以处理新用户登录");
	}
	public void onAuthenticateUserLogged(HealthUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, find the correct user-app
		SecUser secUser = loginResult.getLoginContext().getLoginTarget().getSecUser();
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Platform.INTERNAL_TYPE);
		SmartList<UserApp> userApps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, EO);
		if (userApps == null || userApps.isEmpty()) {
			throw new Exception("您的账号未关联销售人员,请联系客服处理账号异常.");
		}
		UserApp userApp = userApps.first();
		userApp.setSecUser(secUser);
		loginResult.getLoginContext().getLoginTarget().setUserApp(userApp);
	}
	// -----------------------------------\\  登录部分处理 //-----------------------------------
}


