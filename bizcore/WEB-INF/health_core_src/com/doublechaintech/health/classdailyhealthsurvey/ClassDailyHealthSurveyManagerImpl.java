
package com.doublechaintech.health.classdailyhealthsurvey;

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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.schoolclass.CandidateSchoolClass;
import com.doublechaintech.health.wechatuser.CandidateWechatUser;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.classquestion.ClassQuestion;






public class ClassDailyHealthSurveyManagerImpl extends CustomHealthCheckerManager implements ClassDailyHealthSurveyManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "ClassDailyHealthSurvey";
	@Override
	public ClassDailyHealthSurveyDAO daoOf(HealthUserContext userContext) {
		return classDailyHealthSurveyDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ClassDailyHealthSurveyManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ClassDailyHealthSurveyManagerException(message);

	}



 	protected ClassDailyHealthSurvey saveClassDailyHealthSurvey(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey, String [] tokensExpr) throws Exception{	
 		//return getClassDailyHealthSurveyDAO().save(classDailyHealthSurvey, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens);
 	}
 	
 	protected ClassDailyHealthSurvey saveClassDailyHealthSurveyDetail(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey) throws Exception{	

 		
 		return saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, allTokens());
 	}
 	
 	public ClassDailyHealthSurvey loadClassDailyHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).throwExceptionIfHasErrors( ClassDailyHealthSurveyManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey( userContext, classDailyHealthSurveyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,classDailyHealthSurvey, tokens);
 	}
 	
 	
 	 public ClassDailyHealthSurvey searchClassDailyHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).throwExceptionIfHasErrors( ClassDailyHealthSurveyManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey( userContext, classDailyHealthSurveyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,classDailyHealthSurvey, tokens);
 	}
 	
 	

 	protected ClassDailyHealthSurvey present(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,classDailyHealthSurvey,tokens);
		
		
		ClassDailyHealthSurvey  classDailyHealthSurveyToPresent = classDailyHealthSurveyDaoOf(userContext).present(classDailyHealthSurvey, tokens);
		
		List<BaseEntity> entityListToNaming = classDailyHealthSurveyToPresent.collectRefercencesFromLists();
		classDailyHealthSurveyDaoOf(userContext).alias(entityListToNaming);
		
		return  classDailyHealthSurveyToPresent;
		
		
	}
 
 	
 	
 	public ClassDailyHealthSurvey loadClassDailyHealthSurveyDetail(HealthUserContext userContext, String classDailyHealthSurveyId) throws Exception{	
 		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey( userContext, classDailyHealthSurveyId, allTokens());
 		return present(userContext,classDailyHealthSurvey, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String classDailyHealthSurveyId) throws Exception{	
 		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey( userContext, classDailyHealthSurveyId, viewTokens());
 		return present(userContext,classDailyHealthSurvey, allTokens());
		
 	}
 	protected ClassDailyHealthSurvey saveClassDailyHealthSurvey(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object>tokens) throws Exception{	
 		return classDailyHealthSurveyDaoOf(userContext).save(classDailyHealthSurvey, tokens);
 	}
 	protected ClassDailyHealthSurvey loadClassDailyHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).throwExceptionIfHasErrors( ClassDailyHealthSurveyManagerException.class);

 
 		return classDailyHealthSurveyDaoOf(userContext).load(classDailyHealthSurveyId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey, Map<String, Object> tokens){
		super.addActions(userContext, classDailyHealthSurvey, tokens);
		
		addAction(userContext, classDailyHealthSurvey, tokens,"@create","createClassDailyHealthSurvey","createClassDailyHealthSurvey/","main","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"@update","updateClassDailyHealthSurvey","updateClassDailyHealthSurvey/"+classDailyHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"@copy","cloneClassDailyHealthSurvey","cloneClassDailyHealthSurvey/"+classDailyHealthSurvey.getId()+"/","main","primary");
		
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.transfer_to_school_class","transferToAnotherSchoolClass","transferToAnotherSchoolClass/"+classDailyHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.transfer_to_creator","transferToAnotherCreator","transferToAnotherCreator/"+classDailyHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.transfer_to_cq","transferToAnotherCq","transferToAnotherCq/"+classDailyHealthSurvey.getId()+"/","main","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.addDailySurveyQuestion","addDailySurveyQuestion","addDailySurveyQuestion/"+classDailyHealthSurvey.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.removeDailySurveyQuestion","removeDailySurveyQuestion","removeDailySurveyQuestion/"+classDailyHealthSurvey.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.updateDailySurveyQuestion","updateDailySurveyQuestion","updateDailySurveyQuestion/"+classDailyHealthSurvey.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.copyDailySurveyQuestionFrom","copyDailySurveyQuestionFrom","copyDailySurveyQuestionFrom/"+classDailyHealthSurvey.getId()+"/","dailySurveyQuestionList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.addStudentHealthSurvey","addStudentHealthSurvey","addStudentHealthSurvey/"+classDailyHealthSurvey.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.removeStudentHealthSurvey","removeStudentHealthSurvey","removeStudentHealthSurvey/"+classDailyHealthSurvey.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.updateStudentHealthSurvey","updateStudentHealthSurvey","updateStudentHealthSurvey/"+classDailyHealthSurvey.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, classDailyHealthSurvey, tokens,"class_daily_health_survey.copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom/"+classDailyHealthSurvey.getId()+"/","studentHealthSurveyList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name,String schoolClassId,DateTime surveyTime,String creatorId,String cqId) throws Exception
	//public ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext,String name, String schoolClassId, DateTime surveyTime, String creatorId, String cqId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfClassDailyHealthSurvey(name);
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(surveyTime);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);


		ClassDailyHealthSurvey classDailyHealthSurvey=createNewClassDailyHealthSurvey();	

		classDailyHealthSurvey.setName(name);
			
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId,emptyOptions());
		classDailyHealthSurvey.setSchoolClass(schoolClass);
		
		
		classDailyHealthSurvey.setSurveyTime(surveyTime);
			
		WechatUser creator = loadWechatUser(userContext, creatorId,emptyOptions());
		classDailyHealthSurvey.setCreator(creator);
		
		
			
		ChangeRequest cq = loadChangeRequest(userContext, cqId,emptyOptions());
		classDailyHealthSurvey.setCq(cq);
		
		

		classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, emptyOptions());
		
		onNewInstanceCreated(userContext, classDailyHealthSurvey);
		return classDailyHealthSurvey;


	}
	protected ClassDailyHealthSurvey createNewClassDailyHealthSurvey()
	{

		return new ClassDailyHealthSurvey();
	}

	protected void checkParamsForUpdatingClassDailyHealthSurvey(HealthUserContext userContext,String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey( classDailyHealthSurveyVersion);
		

		if(ClassDailyHealthSurvey.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfClassDailyHealthSurvey(parseString(newValueExpr));
		}		

		
		if(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(parseTimestamp(newValueExpr));
		}		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);


	}



	public ClassDailyHealthSurvey clone(HealthUserContext userContext, String fromClassDailyHealthSurveyId) throws Exception{

		return classDailyHealthSurveyDaoOf(userContext).clone(fromClassDailyHealthSurveyId, this.allTokens());
	}

	public ClassDailyHealthSurvey internalSaveClassDailyHealthSurvey(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey) throws Exception
	{
		return internalSaveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, allTokens());

	}
	public ClassDailyHealthSurvey internalSaveClassDailyHealthSurvey(HealthUserContext userContext, ClassDailyHealthSurvey classDailyHealthSurvey, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr, tokensExpr);


		synchronized(classDailyHealthSurvey){
			//will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ClassDailyHealthSurvey.
			if (classDailyHealthSurvey.isChanged()){
			
			}
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, options);
			return classDailyHealthSurvey;

		}

	}

	public ClassDailyHealthSurvey updateClassDailyHealthSurvey(HealthUserContext userContext,String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr, tokensExpr);



		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
		if(classDailyHealthSurvey.getVersion() != classDailyHealthSurveyVersion){
			String message = "The target version("+classDailyHealthSurvey.getVersion()+") is not equals to version("+classDailyHealthSurveyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(classDailyHealthSurvey){
			//will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ClassDailyHealthSurvey.
			
			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().done());
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
			//return saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().done());
		}

	}

	public ClassDailyHealthSurvey updateClassDailyHealthSurveyProperty(HealthUserContext userContext,String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr, tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
		if(classDailyHealthSurvey.getVersion() != classDailyHealthSurveyVersion){
			String message = "The target version("+classDailyHealthSurvey.getVersion()+") is not equals to version("+classDailyHealthSurveyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(classDailyHealthSurvey){
			//will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ClassDailyHealthSurvey.

			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().done());
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
			//return saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ClassDailyHealthSurveyTokens tokens(){
		return ClassDailyHealthSurveyTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ClassDailyHealthSurveyTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortDailySurveyQuestionListWith("id","desc")
		.sortStudentHealthSurveyListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ClassDailyHealthSurveyTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherSchoolClass(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherSchoolClassId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
 		checkerOf(userContext).checkIdOfSchoolClass(anotherSchoolClassId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

 	}
 	public ClassDailyHealthSurvey transferToAnotherSchoolClass(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherSchoolClassId) throws Exception
 	{
 		checkParamsForTransferingAnotherSchoolClass(userContext, classDailyHealthSurveyId,anotherSchoolClassId);
 
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());	
		synchronized(classDailyHealthSurvey){
			//will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SchoolClass schoolClass = loadSchoolClass(userContext, anotherSchoolClassId, emptyOptions());		
			classDailyHealthSurvey.updateSchoolClass(schoolClass);		
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, emptyOptions());
			
			return present(userContext,classDailyHealthSurvey, allTokens());
			
		}

 	}

	


	public CandidateSchoolClass requestCandidateSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSchoolClass result = new CandidateSchoolClass();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SchoolClass> candidateList = schoolClassDaoOf(userContext).requestCandidateSchoolClassForClassDailyHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCreator(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherCreatorId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
 		checkerOf(userContext).checkIdOfWechatUser(anotherCreatorId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

 	}
 	public ClassDailyHealthSurvey transferToAnotherCreator(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherCreatorId) throws Exception
 	{
 		checkParamsForTransferingAnotherCreator(userContext, classDailyHealthSurveyId,anotherCreatorId);
 
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());	
		synchronized(classDailyHealthSurvey){
			//will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			WechatUser creator = loadWechatUser(userContext, anotherCreatorId, emptyOptions());		
			classDailyHealthSurvey.updateCreator(creator);		
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, emptyOptions());
			
			return present(userContext,classDailyHealthSurvey, allTokens());
			
		}

 	}

	


	public CandidateWechatUser requestCandidateCreator(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateWechatUser result = new CandidateWechatUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<WechatUser> candidateList = wechatUserDaoOf(userContext).requestCandidateWechatUserForClassDailyHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCq(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherCqId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherCqId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

 	}
 	public ClassDailyHealthSurvey transferToAnotherCq(HealthUserContext userContext, String classDailyHealthSurveyId, String anotherCqId) throws Exception
 	{
 		checkParamsForTransferingAnotherCq(userContext, classDailyHealthSurveyId,anotherCqId);
 
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());	
		synchronized(classDailyHealthSurvey){
			//will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest cq = loadChangeRequest(userContext, anotherCqId, emptyOptions());		
			classDailyHealthSurvey.updateCq(cq);		
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, emptyOptions());
			
			return present(userContext,classDailyHealthSurvey, allTokens());
			
		}

 	}

	


	public CandidateChangeRequest requestCandidateCq(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequest result = new CandidateChangeRequest();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForClassDailyHealthSurvey(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected WechatUser loadWechatUser(HealthUserContext userContext, String newCreatorId, Map<String,Object> options) throws Exception
 	{

 		return wechatUserDaoOf(userContext).load(newCreatorId, options);
 	}
 	


	

 	protected SchoolClass loadSchoolClass(HealthUserContext userContext, String newSchoolClassId, Map<String,Object> options) throws Exception
 	{

 		return schoolClassDaoOf(userContext).load(newSchoolClassId, options);
 	}
 	


	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newCqId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newCqId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion) throws Exception {
		//deleteInternal(userContext, classDailyHealthSurveyId, classDailyHealthSurveyVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String classDailyHealthSurveyId, int classDailyHealthSurveyVersion) throws Exception{

		classDailyHealthSurveyDaoOf(userContext).delete(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
	}

	public ClassDailyHealthSurvey forgetByAll(HealthUserContext userContext, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion) throws Exception {
		return forgetByAllInternal(userContext, classDailyHealthSurveyId, classDailyHealthSurveyVersion);
	}
	protected ClassDailyHealthSurvey forgetByAllInternal(HealthUserContext userContext,
			String classDailyHealthSurveyId, int classDailyHealthSurveyVersion) throws Exception{

		return classDailyHealthSurveyDaoOf(userContext).disconnectFromAll(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ClassDailyHealthSurveyManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return classDailyHealthSurveyDaoOf(userContext).deleteAll();
	}


	//disconnect ClassDailyHealthSurvey with question_type in DailySurveyQuestion
	protected ClassDailyHealthSurvey breakWithDailySurveyQuestionByQuestionType(HealthUserContext userContext, String classDailyHealthSurveyId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());

			synchronized(classDailyHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classDailyHealthSurveyDaoOf(userContext).planToRemoveDailySurveyQuestionListWithQuestionType(classDailyHealthSurvey, questionTypeId, this.emptyOptions());

				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
				return classDailyHealthSurvey;
			}
	}
	//disconnect ClassDailyHealthSurvey with class_question in DailySurveyQuestion
	protected ClassDailyHealthSurvey breakWithDailySurveyQuestionByClassQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String classQuestionId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());

			synchronized(classDailyHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classDailyHealthSurveyDaoOf(userContext).planToRemoveDailySurveyQuestionListWithClassQuestion(classDailyHealthSurvey, classQuestionId, this.emptyOptions());

				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
				return classDailyHealthSurvey;
			}
	}
	//disconnect ClassDailyHealthSurvey with student in StudentHealthSurvey
	protected ClassDailyHealthSurvey breakWithStudentHealthSurveyByStudent(HealthUserContext userContext, String classDailyHealthSurveyId, String studentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());

			synchronized(classDailyHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classDailyHealthSurveyDaoOf(userContext).planToRemoveStudentHealthSurveyListWithStudent(classDailyHealthSurvey, studentId, this.emptyOptions());

				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
				return classDailyHealthSurvey;
			}
	}
	//disconnect ClassDailyHealthSurvey with survey_status in StudentHealthSurvey
	protected ClassDailyHealthSurvey breakWithStudentHealthSurveyBySurveyStatus(HealthUserContext userContext, String classDailyHealthSurveyId, String surveyStatusId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());

			synchronized(classDailyHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classDailyHealthSurveyDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSurveyStatus(classDailyHealthSurvey, surveyStatusId, this.emptyOptions());

				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
				return classDailyHealthSurvey;
			}
	}
	//disconnect ClassDailyHealthSurvey with school_class in StudentHealthSurvey
	protected ClassDailyHealthSurvey breakWithStudentHealthSurveyBySchoolClass(HealthUserContext userContext, String classDailyHealthSurveyId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());

			synchronized(classDailyHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classDailyHealthSurveyDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSchoolClass(classDailyHealthSurvey, schoolClassId, this.emptyOptions());

				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
				return classDailyHealthSurvey;
			}
	}
	//disconnect ClassDailyHealthSurvey with cq in StudentHealthSurvey
	protected ClassDailyHealthSurvey breakWithStudentHealthSurveyByCq(HealthUserContext userContext, String classDailyHealthSurveyId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());

			synchronized(classDailyHealthSurvey){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				classDailyHealthSurveyDaoOf(userContext).planToRemoveStudentHealthSurveyListWithCq(classDailyHealthSurvey, cqId, this.emptyOptions());

				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
				return classDailyHealthSurvey;
			}
	}






	protected void checkParamsForAddingDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classQuestionId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);

		
		checkerOf(userContext).checkTopicOfDailySurveyQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfDailySurveyQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion(optionD);
		
		checkerOf(userContext).checkClassQuestionIdOfDailySurveyQuestion(classQuestionId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);


	}
	public  ClassDailyHealthSurvey addDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classQuestionId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingDailySurveyQuestion(userContext,classDailyHealthSurveyId,topic, questionTypeId, optionA, optionB, optionC, optionD, classQuestionId,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createDailySurveyQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD, classQuestionId);

		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, emptyOptions());
		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			classDailyHealthSurvey.addDailySurveyQuestion( dailySurveyQuestion );
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
			
			userContext.getManagerGroup().getDailySurveyQuestionManager().onNewInstanceCreated(userContext, dailySurveyQuestion);
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingDailySurveyQuestionProperties(HealthUserContext userContext, String classDailyHealthSurveyId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(id);

		checkerOf(userContext).checkTopicOfDailySurveyQuestion( topic);
		checkerOf(userContext).checkOptionAOfDailySurveyQuestion( optionA);
		checkerOf(userContext).checkOptionBOfDailySurveyQuestion( optionB);
		checkerOf(userContext).checkOptionCOfDailySurveyQuestion( optionC);
		checkerOf(userContext).checkOptionDOfDailySurveyQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey updateDailySurveyQuestionProperties(HealthUserContext userContext, String classDailyHealthSurveyId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDailySurveyQuestionProperties(userContext,classDailyHealthSurveyId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withDailySurveyQuestionListList()
				.searchDailySurveyQuestionListWith(DailySurveyQuestion.ID_PROPERTY, "is", id).done();

		ClassDailyHealthSurvey classDailyHealthSurveyToUpdate = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, options);

		if(classDailyHealthSurveyToUpdate.getDailySurveyQuestionList().isEmpty()){
			throw new ClassDailyHealthSurveyManagerException("DailySurveyQuestion is NOT FOUND with id: '"+id+"'");
		}

		DailySurveyQuestion item = classDailyHealthSurveyToUpdate.getDailySurveyQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingDailySurveyQuestion(userContext,classDailyHealthSurveyId,name, code, used,tokensExpr);
		ClassDailyHealthSurvey classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurveyToUpdate, tokens().withDailySurveyQuestionList().done());
		synchronized(classDailyHealthSurvey){
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}
	}


	protected DailySurveyQuestion createDailySurveyQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String classQuestionId) throws Exception{

		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		
		
		dailySurveyQuestion.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		dailySurveyQuestion.setQuestionType(questionType);		
		dailySurveyQuestion.setOptionA(optionA);		
		dailySurveyQuestion.setOptionB(optionB);		
		dailySurveyQuestion.setOptionC(optionC);		
		dailySurveyQuestion.setOptionD(optionD);		
		ClassQuestion  classQuestion = new ClassQuestion();
		classQuestion.setId(classQuestionId);		
		dailySurveyQuestion.setClassQuestion(classQuestion);
	
		
		return dailySurveyQuestion;


	}

	protected DailySurveyQuestion createIndexedDailySurveyQuestion(String id, int version){

		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		dailySurveyQuestion.setId(id);
		dailySurveyQuestion.setVersion(version);
		return dailySurveyQuestion;

	}

	protected void checkParamsForRemovingDailySurveyQuestionList(HealthUserContext userContext, String classDailyHealthSurveyId,
			String dailySurveyQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		for(String dailySurveyQuestionIdItem: dailySurveyQuestionIds){
			checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey removeDailySurveyQuestionList(HealthUserContext userContext, String classDailyHealthSurveyId,
			String dailySurveyQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingDailySurveyQuestionList(userContext, classDailyHealthSurveyId,  dailySurveyQuestionIds, tokensExpr);


			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
			synchronized(classDailyHealthSurvey){
				//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				classDailyHealthSurveyDaoOf(userContext).planToRemoveDailySurveyQuestionList(classDailyHealthSurvey, dailySurveyQuestionIds, allTokens());
				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
				deleteRelationListInGraph(userContext, classDailyHealthSurvey.getDailySurveyQuestionList());
				return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey( classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey removeDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingDailySurveyQuestion(userContext,classDailyHealthSurveyId, dailySurveyQuestionId, dailySurveyQuestionVersion,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			classDailyHealthSurvey.removeDailySurveyQuestion( dailySurveyQuestion );
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
			deleteRelationInGraph(userContext, dailySurveyQuestion);
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey( classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey copyDailySurveyQuestionFrom(HealthUserContext userContext, String classDailyHealthSurveyId,
		String dailySurveyQuestionId, int dailySurveyQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingDailySurveyQuestion(userContext,classDailyHealthSurveyId, dailySurveyQuestionId, dailySurveyQuestionVersion,tokensExpr);

		DailySurveyQuestion dailySurveyQuestion = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			classDailyHealthSurvey.copyDailySurveyQuestionFrom( dailySurveyQuestion );
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
			
			userContext.getManagerGroup().getDailySurveyQuestionManager().onNewInstanceCreated(userContext, (DailySurveyQuestion)classDailyHealthSurvey.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfDailySurveyQuestion(dailySurveyQuestionId);
		checkerOf(userContext).checkVersionOfDailySurveyQuestion(dailySurveyQuestionVersion);
		

		if(DailySurveyQuestion.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfDailySurveyQuestion(parseString(newValueExpr));
		}
		
		if(DailySurveyQuestion.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfDailySurveyQuestion(parseString(newValueExpr));
		}
		
		if(DailySurveyQuestion.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfDailySurveyQuestion(parseString(newValueExpr));
		}
		
		if(DailySurveyQuestion.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfDailySurveyQuestion(parseString(newValueExpr));
		}
		
		if(DailySurveyQuestion.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfDailySurveyQuestion(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}

	public  ClassDailyHealthSurvey updateDailySurveyQuestion(HealthUserContext userContext, String classDailyHealthSurveyId, String dailySurveyQuestionId, int dailySurveyQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingDailySurveyQuestion(userContext, classDailyHealthSurveyId, dailySurveyQuestionId, dailySurveyQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withDailySurveyQuestionList().searchDailySurveyQuestionListWith(DailySurveyQuestion.ID_PROPERTY, "eq", dailySurveyQuestionId).done();



		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, loadTokens);

		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//classDailyHealthSurvey.removeDailySurveyQuestion( dailySurveyQuestion );
			//make changes to AcceleraterAccount.
			DailySurveyQuestion dailySurveyQuestionIndex = createIndexedDailySurveyQuestion(dailySurveyQuestionId, dailySurveyQuestionVersion);

			DailySurveyQuestion dailySurveyQuestion = classDailyHealthSurvey.findTheDailySurveyQuestion(dailySurveyQuestionIndex);
			if(dailySurveyQuestion == null){
				throw new ClassDailyHealthSurveyManagerException(dailySurveyQuestion+" is NOT FOUND" );
			}

			dailySurveyQuestion.changeProperty(property, newValueExpr);
			
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withDailySurveyQuestionList().done());
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);

		
		checkerOf(userContext).checkStudentIdOfStudentHealthSurvey(studentId);
		
		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
		
		checkerOf(userContext).checkSurveyStatusIdOfStudentHealthSurvey(surveyStatusId);
		
		checkerOf(userContext).checkSchoolClassIdOfStudentHealthSurvey(schoolClassId);
		
		checkerOf(userContext).checkCqIdOfStudentHealthSurvey(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);


	}
	public  ClassDailyHealthSurvey addStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentHealthSurvey(userContext,classDailyHealthSurveyId,studentId, answerTime, surveyStatusId, schoolClassId, cqId,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createStudentHealthSurvey(userContext,studentId, answerTime, surveyStatusId, schoolClassId, cqId);

		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, emptyOptions());
		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			classDailyHealthSurvey.addStudentHealthSurvey( studentHealthSurvey );
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, studentHealthSurvey);
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentHealthSurveyProperties(HealthUserContext userContext, String classDailyHealthSurveyId,String id,DateTime answerTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(id);

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey( answerTime);

		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey updateStudentHealthSurveyProperties(HealthUserContext userContext, String classDailyHealthSurveyId, String id,DateTime answerTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurveyProperties(userContext,classDailyHealthSurveyId,id,answerTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentHealthSurveyListList()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "is", id).done();

		ClassDailyHealthSurvey classDailyHealthSurveyToUpdate = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, options);

		if(classDailyHealthSurveyToUpdate.getStudentHealthSurveyList().isEmpty()){
			throw new ClassDailyHealthSurveyManagerException("StudentHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		StudentHealthSurvey item = classDailyHealthSurveyToUpdate.getStudentHealthSurveyList().first();

		item.updateAnswerTime( answerTime );


		//checkParamsForAddingStudentHealthSurvey(userContext,classDailyHealthSurveyId,name, code, used,tokensExpr);
		ClassDailyHealthSurvey classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurveyToUpdate, tokens().withStudentHealthSurveyList().done());
		synchronized(classDailyHealthSurvey){
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String cqId) throws Exception{

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		
		
		Student  student = new Student();
		student.setId(studentId);		
		studentHealthSurvey.setStudent(student);		
		studentHealthSurvey.setAnswerTime(answerTime);		
		SurveyStatus  surveyStatus = new SurveyStatus();
		surveyStatus.setId(surveyStatusId);		
		studentHealthSurvey.setSurveyStatus(surveyStatus);		
		SchoolClass  schoolClass = new SchoolClass();
		schoolClass.setId(schoolClassId);		
		studentHealthSurvey.setSchoolClass(schoolClass);		
		studentHealthSurvey.setCreateTime(userContext.now());		
		studentHealthSurvey.setLastUpdateTime(userContext.now());		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		studentHealthSurvey.setCq(cq);
	
		
		return studentHealthSurvey;


	}

	protected StudentHealthSurvey createIndexedStudentHealthSurvey(String id, int version){

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(id);
		studentHealthSurvey.setVersion(version);
		return studentHealthSurvey;

	}

	protected void checkParamsForRemovingStudentHealthSurveyList(HealthUserContext userContext, String classDailyHealthSurveyId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		for(String studentHealthSurveyIdItem: studentHealthSurveyIds){
			checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey removeStudentHealthSurveyList(HealthUserContext userContext, String classDailyHealthSurveyId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentHealthSurveyList(userContext, classDailyHealthSurveyId,  studentHealthSurveyIds, tokensExpr);


			ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
			synchronized(classDailyHealthSurvey){
				//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				classDailyHealthSurveyDaoOf(userContext).planToRemoveStudentHealthSurveyList(classDailyHealthSurvey, studentHealthSurveyIds, allTokens());
				classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
				deleteRelationListInGraph(userContext, classDailyHealthSurvey.getStudentHealthSurveyList());
				return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey( classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey removeStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentHealthSurvey(userContext,classDailyHealthSurveyId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			classDailyHealthSurvey.removeStudentHealthSurvey( studentHealthSurvey );
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
			deleteRelationInGraph(userContext, studentHealthSurvey);
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey( classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}
	public  ClassDailyHealthSurvey copyStudentHealthSurveyFrom(HealthUserContext userContext, String classDailyHealthSurveyId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentHealthSurvey(userContext,classDailyHealthSurveyId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, allTokens());
		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentHealthSurvey.updateLastUpdateTime(userContext.now());

			classDailyHealthSurvey.copyStudentHealthSurveyFrom( studentHealthSurvey );
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, (StudentHealthSurvey)classDailyHealthSurvey.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		

		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ClassDailyHealthSurveyManagerException.class);

	}

	public  ClassDailyHealthSurvey updateStudentHealthSurvey(HealthUserContext userContext, String classDailyHealthSurveyId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentHealthSurvey(userContext, classDailyHealthSurveyId, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentHealthSurveyList().searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "eq", studentHealthSurveyId).done();



		ClassDailyHealthSurvey classDailyHealthSurvey = loadClassDailyHealthSurvey(userContext, classDailyHealthSurveyId, loadTokens);

		synchronized(classDailyHealthSurvey){
			//Will be good when the classDailyHealthSurvey loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//classDailyHealthSurvey.removeStudentHealthSurvey( studentHealthSurvey );
			//make changes to AcceleraterAccount.
			StudentHealthSurvey studentHealthSurveyIndex = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);

			StudentHealthSurvey studentHealthSurvey = classDailyHealthSurvey.findTheStudentHealthSurvey(studentHealthSurveyIndex);
			if(studentHealthSurvey == null){
				throw new ClassDailyHealthSurveyManagerException(studentHealthSurvey+" is NOT FOUND" );
			}

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			classDailyHealthSurvey = saveClassDailyHealthSurvey(userContext, classDailyHealthSurvey, tokens().withStudentHealthSurveyList().done());
			return present(userContext,classDailyHealthSurvey, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, ClassDailyHealthSurvey newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ClassDailyHealthSurvey.INTERNAL_TYPE);
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


