
package com.doublechaintech.health.surveystatus;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.Password;

import com.doublechaintech.health.*;
import com.doublechaintech.health.HealthUserContextImpl;
import com.doublechaintech.health.iamservice.*;
import com.doublechaintech.health.services.IamService;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.terapico.uccaf.BaseUserContext;


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.platform.CandidatePlatform;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;






public class SurveyStatusManagerImpl extends CustomHealthCheckerManager implements SurveyStatusManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "SurveyStatus";
	@Override
	public SurveyStatusDAO daoOf(HealthUserContext userContext) {
		return surveyStatusDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws SurveyStatusManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new SurveyStatusManagerException(message);

	}



 	protected SurveyStatus saveSurveyStatus(HealthUserContext userContext, SurveyStatus surveyStatus, String [] tokensExpr) throws Exception{	
 		//return getSurveyStatusDAO().save(surveyStatus, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSurveyStatus(userContext, surveyStatus, tokens);
 	}
 	
 	protected SurveyStatus saveSurveyStatusDetail(HealthUserContext userContext, SurveyStatus surveyStatus) throws Exception{	

 		
 		return saveSurveyStatus(userContext, surveyStatus, allTokens());
 	}
 	
 	public SurveyStatus loadSurveyStatus(HealthUserContext userContext, String surveyStatusId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).throwExceptionIfHasErrors( SurveyStatusManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SurveyStatus surveyStatus = loadSurveyStatus( userContext, surveyStatusId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,surveyStatus, tokens);
 	}
 	
 	
 	 public SurveyStatus searchSurveyStatus(HealthUserContext userContext, String surveyStatusId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).throwExceptionIfHasErrors( SurveyStatusManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SurveyStatus surveyStatus = loadSurveyStatus( userContext, surveyStatusId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,surveyStatus, tokens);
 	}
 	
 	

 	protected SurveyStatus present(HealthUserContext userContext, SurveyStatus surveyStatus, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,surveyStatus,tokens);
		
		
		SurveyStatus  surveyStatusToPresent = surveyStatusDaoOf(userContext).present(surveyStatus, tokens);
		
		List<BaseEntity> entityListToNaming = surveyStatusToPresent.collectRefercencesFromLists();
		surveyStatusDaoOf(userContext).alias(entityListToNaming);
		
		return  surveyStatusToPresent;
		
		
	}
 
 	
 	
 	public SurveyStatus loadSurveyStatusDetail(HealthUserContext userContext, String surveyStatusId) throws Exception{	
 		SurveyStatus surveyStatus = loadSurveyStatus( userContext, surveyStatusId, allTokens());
 		return present(userContext,surveyStatus, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String surveyStatusId) throws Exception{	
 		SurveyStatus surveyStatus = loadSurveyStatus( userContext, surveyStatusId, viewTokens());
 		return present(userContext,surveyStatus, allTokens());
		
 	}
 	protected SurveyStatus saveSurveyStatus(HealthUserContext userContext, SurveyStatus surveyStatus, Map<String,Object>tokens) throws Exception{	
 		return surveyStatusDaoOf(userContext).save(surveyStatus, tokens);
 	}
 	protected SurveyStatus loadSurveyStatus(HealthUserContext userContext, String surveyStatusId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).throwExceptionIfHasErrors( SurveyStatusManagerException.class);

 
 		return surveyStatusDaoOf(userContext).load(surveyStatusId, tokens);
 	}

	
	

	public SurveyStatus loadSurveyStatusWithCode(HealthUserContext userContext, String code, Map<String,Object>tokens) throws Exception{	
 		return surveyStatusDaoOf(userContext).loadByCode(code, tokens);
 	}

	 


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, SurveyStatus surveyStatus, Map<String, Object> tokens){
		super.addActions(userContext, surveyStatus, tokens);
		
		addAction(userContext, surveyStatus, tokens,"@create","createSurveyStatus","createSurveyStatus/","main","primary");
		addAction(userContext, surveyStatus, tokens,"@update","updateSurveyStatus","updateSurveyStatus/"+surveyStatus.getId()+"/","main","primary");
		addAction(userContext, surveyStatus, tokens,"@copy","cloneSurveyStatus","cloneSurveyStatus/"+surveyStatus.getId()+"/","main","primary");
		
		addAction(userContext, surveyStatus, tokens,"survey_status.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+surveyStatus.getId()+"/","main","primary");
		addAction(userContext, surveyStatus, tokens,"survey_status.addStudentHealthSurvey","addStudentHealthSurvey","addStudentHealthSurvey/"+surveyStatus.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, surveyStatus, tokens,"survey_status.removeStudentHealthSurvey","removeStudentHealthSurvey","removeStudentHealthSurvey/"+surveyStatus.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, surveyStatus, tokens,"survey_status.updateStudentHealthSurvey","updateStudentHealthSurvey","updateStudentHealthSurvey/"+surveyStatus.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, surveyStatus, tokens,"survey_status.copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom/"+surveyStatus.getId()+"/","studentHealthSurveyList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, SurveyStatus surveyStatus, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public SurveyStatus createSurveyStatus(HealthUserContext userContext, String name,String code,String platformId) throws Exception
	//public SurveyStatus createSurveyStatus(HealthUserContext userContext,String name, String code, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfSurveyStatus(name);
		checkerOf(userContext).checkCodeOfSurveyStatus(code);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);


		SurveyStatus surveyStatus=createNewSurveyStatus();	

		surveyStatus.setName(name);
		surveyStatus.setCode(code);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		surveyStatus.setPlatform(platform);
		
		

		surveyStatus = saveSurveyStatus(userContext, surveyStatus, emptyOptions());
		
		onNewInstanceCreated(userContext, surveyStatus);
		return surveyStatus;


	}
	protected SurveyStatus createNewSurveyStatus()
	{

		return new SurveyStatus();
	}

	protected void checkParamsForUpdatingSurveyStatus(HealthUserContext userContext,String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).checkVersionOfSurveyStatus( surveyStatusVersion);
		

		if(SurveyStatus.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfSurveyStatus(parseString(newValueExpr));
		
			
		}
		if(SurveyStatus.CODE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCodeOfSurveyStatus(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);


	}



	public SurveyStatus clone(HealthUserContext userContext, String fromSurveyStatusId) throws Exception{

		return surveyStatusDaoOf(userContext).clone(fromSurveyStatusId, this.allTokens());
	}

	public SurveyStatus internalSaveSurveyStatus(HealthUserContext userContext, SurveyStatus surveyStatus) throws Exception
	{
		return internalSaveSurveyStatus(userContext, surveyStatus, allTokens());

	}
	public SurveyStatus internalSaveSurveyStatus(HealthUserContext userContext, SurveyStatus surveyStatus, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingSurveyStatus(userContext, surveyStatusId, surveyStatusVersion, property, newValueExpr, tokensExpr);


		synchronized(surveyStatus){
			//will be good when the surveyStatus loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SurveyStatus.
			if (surveyStatus.isChanged()){
			
			}
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, options);
			return surveyStatus;

		}

	}

	public SurveyStatus updateSurveyStatus(HealthUserContext userContext,String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSurveyStatus(userContext, surveyStatusId, surveyStatusVersion, property, newValueExpr, tokensExpr);



		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());
		if(surveyStatus.getVersion() != surveyStatusVersion){
			String message = "The target version("+surveyStatus.getVersion()+") is not equals to version("+surveyStatusVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(surveyStatus){
			//will be good when the surveyStatus loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SurveyStatus.
			
			surveyStatus.changeProperty(property, newValueExpr);
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().done());
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
			//return saveSurveyStatus(userContext, surveyStatus, tokens().done());
		}

	}

	public SurveyStatus updateSurveyStatusProperty(HealthUserContext userContext,String surveyStatusId, int surveyStatusVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSurveyStatus(userContext, surveyStatusId, surveyStatusVersion, property, newValueExpr, tokensExpr);

		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());
		if(surveyStatus.getVersion() != surveyStatusVersion){
			String message = "The target version("+surveyStatus.getVersion()+") is not equals to version("+surveyStatusVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(surveyStatus){
			//will be good when the surveyStatus loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SurveyStatus.

			surveyStatus.changeProperty(property, newValueExpr);
			
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().done());
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
			//return saveSurveyStatus(userContext, surveyStatus, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected SurveyStatusTokens tokens(){
		return SurveyStatusTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SurveyStatusTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortStudentHealthSurveyListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SurveyStatusTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String surveyStatusId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);

 	}
 	public SurveyStatus transferToAnotherPlatform(HealthUserContext userContext, String surveyStatusId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, surveyStatusId,anotherPlatformId);
 
		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());	
		synchronized(surveyStatus){
			//will be good when the surveyStatus loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			surveyStatus.updatePlatform(platform);		
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, emptyOptions());
			
			return present(userContext,surveyStatus, allTokens());
			
		}

 	}

	


	public CandidatePlatform requestCandidatePlatform(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForSurveyStatus(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String surveyStatusId, int surveyStatusVersion) throws Exception {
		//deleteInternal(userContext, surveyStatusId, surveyStatusVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String surveyStatusId, int surveyStatusVersion) throws Exception{

		surveyStatusDaoOf(userContext).delete(surveyStatusId, surveyStatusVersion);
	}

	public SurveyStatus forgetByAll(HealthUserContext userContext, String surveyStatusId, int surveyStatusVersion) throws Exception {
		return forgetByAllInternal(userContext, surveyStatusId, surveyStatusVersion);
	}
	protected SurveyStatus forgetByAllInternal(HealthUserContext userContext,
			String surveyStatusId, int surveyStatusVersion) throws Exception{

		return surveyStatusDaoOf(userContext).disconnectFromAll(surveyStatusId, surveyStatusVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SurveyStatusManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return surveyStatusDaoOf(userContext).deleteAll();
	}


	//disconnect SurveyStatus with student in StudentHealthSurvey
	protected SurveyStatus breakWithStudentHealthSurveyByStudent(HealthUserContext userContext, String surveyStatusId, String studentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());

			synchronized(surveyStatus){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				surveyStatusDaoOf(userContext).planToRemoveStudentHealthSurveyListWithStudent(surveyStatus, studentId, this.emptyOptions());

				surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
				return surveyStatus;
			}
	}
	//disconnect SurveyStatus with teacher in StudentHealthSurvey
	protected SurveyStatus breakWithStudentHealthSurveyByTeacher(HealthUserContext userContext, String surveyStatusId, String teacherId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());

			synchronized(surveyStatus){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				surveyStatusDaoOf(userContext).planToRemoveStudentHealthSurveyListWithTeacher(surveyStatus, teacherId, this.emptyOptions());

				surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
				return surveyStatus;
			}
	}
	//disconnect SurveyStatus with class_daily_health_survey in StudentHealthSurvey
	protected SurveyStatus breakWithStudentHealthSurveyByClassDailyHealthSurvey(HealthUserContext userContext, String surveyStatusId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());

			synchronized(surveyStatus){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				surveyStatusDaoOf(userContext).planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(surveyStatus, classDailyHealthSurveyId, this.emptyOptions());

				surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
				return surveyStatus;
			}
	}
	//disconnect SurveyStatus with change_request in StudentHealthSurvey
	protected SurveyStatus breakWithStudentHealthSurveyByChangeRequest(HealthUserContext userContext, String surveyStatusId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());

			synchronized(surveyStatus){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				surveyStatusDaoOf(userContext).planToRemoveStudentHealthSurveyListWithChangeRequest(surveyStatus, changeRequestId, this.emptyOptions());

				surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
				return surveyStatus;
			}
	}






	protected void checkParamsForAddingStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentId, DateTime answerTime, String teacherId, String classDailyHealthSurveyId, String changeRequestId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);

		
		checkerOf(userContext).checkStudentIdOfStudentHealthSurvey(studentId);
		
		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
		
		checkerOf(userContext).checkTeacherIdOfStudentHealthSurvey(teacherId);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfStudentHealthSurvey(classDailyHealthSurveyId);
		
		checkerOf(userContext).checkChangeRequestIdOfStudentHealthSurvey(changeRequestId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);


	}
	public  SurveyStatus addStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentId, DateTime answerTime, String teacherId, String classDailyHealthSurveyId, String changeRequestId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentHealthSurvey(userContext,surveyStatusId,studentId, answerTime, teacherId, classDailyHealthSurveyId, changeRequestId,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createStudentHealthSurvey(userContext,studentId, answerTime, teacherId, classDailyHealthSurveyId, changeRequestId);

		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, emptyOptions());
		synchronized(surveyStatus){
			//Will be good when the surveyStatus loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			surveyStatus.addStudentHealthSurvey( studentHealthSurvey );
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, studentHealthSurvey);
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentHealthSurveyProperties(HealthUserContext userContext, String surveyStatusId,String id,DateTime answerTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(id);

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey( answerTime);

		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);

	}
	public  SurveyStatus updateStudentHealthSurveyProperties(HealthUserContext userContext, String surveyStatusId, String id,DateTime answerTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurveyProperties(userContext,surveyStatusId,id,answerTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentHealthSurveyListList()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "is", id).done();

		SurveyStatus surveyStatusToUpdate = loadSurveyStatus(userContext, surveyStatusId, options);

		if(surveyStatusToUpdate.getStudentHealthSurveyList().isEmpty()){
			throw new SurveyStatusManagerException("StudentHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		StudentHealthSurvey item = surveyStatusToUpdate.getStudentHealthSurveyList().first();

		item.updateAnswerTime( answerTime );


		//checkParamsForAddingStudentHealthSurvey(userContext,surveyStatusId,name, code, used,tokensExpr);
		SurveyStatus surveyStatus = saveSurveyStatus(userContext, surveyStatusToUpdate, tokens().withStudentHealthSurveyList().done());
		synchronized(surveyStatus){
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String teacherId, String classDailyHealthSurveyId, String changeRequestId) throws Exception{

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		
		
		Student  student = new Student();
		student.setId(studentId);		
		studentHealthSurvey.setStudent(student);		
		studentHealthSurvey.setAnswerTime(answerTime);		
		Teacher  teacher = new Teacher();
		teacher.setId(teacherId);		
		studentHealthSurvey.setTeacher(teacher);		
		ClassDailyHealthSurvey  classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(classDailyHealthSurveyId);		
		studentHealthSurvey.setClassDailyHealthSurvey(classDailyHealthSurvey);		
		studentHealthSurvey.setCreateTime(userContext.now());		
		studentHealthSurvey.setLastUpdateTime(userContext.now());		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		studentHealthSurvey.setChangeRequest(changeRequest);
	
		
		return studentHealthSurvey;


	}

	protected StudentHealthSurvey createIndexedStudentHealthSurvey(String id, int version){

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(id);
		studentHealthSurvey.setVersion(version);
		return studentHealthSurvey;

	}

	protected void checkParamsForRemovingStudentHealthSurveyList(HealthUserContext userContext, String surveyStatusId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		for(String studentHealthSurveyIdItem: studentHealthSurveyIds){
			checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);

	}
	public  SurveyStatus removeStudentHealthSurveyList(HealthUserContext userContext, String surveyStatusId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentHealthSurveyList(userContext, surveyStatusId,  studentHealthSurveyIds, tokensExpr);


			SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());
			synchronized(surveyStatus){
				//Will be good when the surveyStatus loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				surveyStatusDaoOf(userContext).planToRemoveStudentHealthSurveyList(surveyStatus, studentHealthSurveyIds, allTokens());
				surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
				deleteRelationListInGraph(userContext, surveyStatus.getStudentHealthSurveyList());
				return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSurveyStatus( surveyStatusId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);

	}
	public  SurveyStatus removeStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentHealthSurvey(userContext,surveyStatusId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());
		synchronized(surveyStatus){
			//Will be good when the surveyStatus loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			surveyStatus.removeStudentHealthSurvey( studentHealthSurvey );
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
			deleteRelationInGraph(userContext, studentHealthSurvey);
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSurveyStatus( surveyStatusId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);

	}
	public  SurveyStatus copyStudentHealthSurveyFrom(HealthUserContext userContext, String surveyStatusId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentHealthSurvey(userContext,surveyStatusId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, allTokens());
		synchronized(surveyStatus){
			//Will be good when the surveyStatus loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentHealthSurvey.updateLastUpdateTime(userContext.now());

			surveyStatus.copyStudentHealthSurveyFrom( studentHealthSurvey );
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, (StudentHealthSurvey)surveyStatus.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSurveyStatus(surveyStatusId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		

		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SurveyStatusManagerException.class);

	}

	public  SurveyStatus updateStudentHealthSurvey(HealthUserContext userContext, String surveyStatusId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentHealthSurvey(userContext, surveyStatusId, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentHealthSurveyList().searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "eq", studentHealthSurveyId).done();



		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, loadTokens);

		synchronized(surveyStatus){
			//Will be good when the surveyStatus loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//surveyStatus.removeStudentHealthSurvey( studentHealthSurvey );
			//make changes to AcceleraterAccount.
			StudentHealthSurvey studentHealthSurveyIndex = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);

			StudentHealthSurvey studentHealthSurvey = surveyStatus.findTheStudentHealthSurvey(studentHealthSurveyIndex);
			if(studentHealthSurvey == null){
				throw new SurveyStatusManagerException(studentHealthSurvey+" is NOT FOUND" );
			}

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			surveyStatus = saveSurveyStatus(userContext, surveyStatus, tokens().withStudentHealthSurveyList().done());
			return present(userContext,surveyStatus, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  SurveyStatus associateStudentHealthSurveyListToNewChangeRequest(HealthUserContext userContext, String surveyStatusId, String  studentHealthSurveyIds[], String name, String requestTypeId, String platformId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "oneof", this.joinArray("|", studentHealthSurveyIds)).done();

		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, options);

		ChangeRequest changeRequest = changeRequestManagerOf(userContext).createChangeRequest(userContext,  name, requestTypeId, platformId);

		for(StudentHealthSurvey studentHealthSurvey: surveyStatus.getStudentHealthSurveyList()) {
			//TODO: need to check if already associated
			studentHealthSurvey.updateChangeRequest(changeRequest);
		}
		return this.internalSaveSurveyStatus(userContext, surveyStatus);
	}
	*/

	public  SurveyStatus associateStudentHealthSurveyListToChangeRequest(HealthUserContext userContext, String surveyStatusId, String  studentHealthSurveyIds[], String changeRequestId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "oneof", this.joinArray("|", studentHealthSurveyIds)).done();

		SurveyStatus surveyStatus = loadSurveyStatus(userContext, surveyStatusId, options);

		ChangeRequest changeRequest = changeRequestManagerOf(userContext).loadChangeRequest(userContext,changeRequestId,new String[]{"none"} );

		for(StudentHealthSurvey studentHealthSurvey: surveyStatus.getStudentHealthSurveyList()) {
			//TODO: need to check if already associated
			studentHealthSurvey.updateChangeRequest(changeRequest);
		}
		return this.internalSaveSurveyStatus(userContext, surveyStatus);
	}


	public void onNewInstanceCreated(HealthUserContext userContext, SurveyStatus newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, SurveyStatus.INTERNAL_TYPE);
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


