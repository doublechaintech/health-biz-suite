
package com.doublechaintech.health.changerequest;

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


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.classquestion.ClassQuestion;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.changerequesttype.CandidateChangeRequestType;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.questionsource.QuestionSource;






public class ChangeRequestManagerImpl extends CustomHealthCheckerManager implements ChangeRequestManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "ChangeRequest";
	@Override
	public ChangeRequestDAO daoOf(HealthUserContext userContext) {
		return changeRequestDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ChangeRequestManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ChangeRequestManagerException(message);

	}



 	protected ChangeRequest saveChangeRequest(HealthUserContext userContext, ChangeRequest changeRequest, String [] tokensExpr) throws Exception{	
 		//return getChangeRequestDAO().save(changeRequest, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveChangeRequest(userContext, changeRequest, tokens);
 	}
 	
 	protected ChangeRequest saveChangeRequestDetail(HealthUserContext userContext, ChangeRequest changeRequest) throws Exception{	

 		
 		return saveChangeRequest(userContext, changeRequest, allTokens());
 	}
 	
 	public ChangeRequest loadChangeRequest(HealthUserContext userContext, String changeRequestId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequest, tokens);
 	}
 	
 	
 	 public ChangeRequest searchChangeRequest(HealthUserContext userContext, String changeRequestId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequest, tokens);
 	}
 	
 	

 	protected ChangeRequest present(HealthUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,changeRequest,tokens);
		
		
		ChangeRequest  changeRequestToPresent = changeRequestDaoOf(userContext).present(changeRequest, tokens);
		
		List<BaseEntity> entityListToNaming = changeRequestToPresent.collectRefercencesFromLists();
		changeRequestDaoOf(userContext).alias(entityListToNaming);
		
		return  changeRequestToPresent;
		
		
	}
 
 	
 	
 	public ChangeRequest loadChangeRequestDetail(HealthUserContext userContext, String changeRequestId) throws Exception{	
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, allTokens());
 		return present(userContext,changeRequest, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String changeRequestId) throws Exception{	
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, viewTokens());
 		return present(userContext,changeRequest, allTokens());
		
 	}
 	protected ChangeRequest saveChangeRequest(HealthUserContext userContext, ChangeRequest changeRequest, Map<String,Object>tokens) throws Exception{	
 		return changeRequestDaoOf(userContext).save(changeRequest, tokens);
 	}
 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String changeRequestId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 
 		return changeRequestDaoOf(userContext).load(changeRequestId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens){
		super.addActions(userContext, changeRequest, tokens);
		
		addAction(userContext, changeRequest, tokens,"@create","createChangeRequest","createChangeRequest/","main","primary");
		addAction(userContext, changeRequest, tokens,"@update","updateChangeRequest","updateChangeRequest/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"@copy","cloneChangeRequest","cloneChangeRequest/"+changeRequest.getId()+"/","main","primary");
		
		addAction(userContext, changeRequest, tokens,"change_request.transfer_to_request_type","transferToAnotherRequestType","transferToAnotherRequestType/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"change_request.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addSchoolClass","addSchoolClass","addSchoolClass/"+changeRequest.getId()+"/","schoolClassList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeSchoolClass","removeSchoolClass","removeSchoolClass/"+changeRequest.getId()+"/","schoolClassList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateSchoolClass","updateSchoolClass","updateSchoolClass/"+changeRequest.getId()+"/","schoolClassList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copySchoolClassFrom","copySchoolClassFrom","copySchoolClassFrom/"+changeRequest.getId()+"/","schoolClassList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addTeacher","addTeacher","addTeacher/"+changeRequest.getId()+"/","teacherList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeTeacher","removeTeacher","removeTeacher/"+changeRequest.getId()+"/","teacherList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateTeacher","updateTeacher","updateTeacher/"+changeRequest.getId()+"/","teacherList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyTeacherFrom","copyTeacherFrom","copyTeacherFrom/"+changeRequest.getId()+"/","teacherList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addGuardian","addGuardian","addGuardian/"+changeRequest.getId()+"/","guardianList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeGuardian","removeGuardian","removeGuardian/"+changeRequest.getId()+"/","guardianList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateGuardian","updateGuardian","updateGuardian/"+changeRequest.getId()+"/","guardianList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyGuardianFrom","copyGuardianFrom","copyGuardianFrom/"+changeRequest.getId()+"/","guardianList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addClassQuestion","addClassQuestion","addClassQuestion/"+changeRequest.getId()+"/","classQuestionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeClassQuestion","removeClassQuestion","removeClassQuestion/"+changeRequest.getId()+"/","classQuestionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateClassQuestion","updateClassQuestion","updateClassQuestion/"+changeRequest.getId()+"/","classQuestionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyClassQuestionFrom","copyClassQuestionFrom","copyClassQuestionFrom/"+changeRequest.getId()+"/","classQuestionList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addClassDailyHealthSurvey","addClassDailyHealthSurvey","addClassDailyHealthSurvey/"+changeRequest.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeClassDailyHealthSurvey","removeClassDailyHealthSurvey","removeClassDailyHealthSurvey/"+changeRequest.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateClassDailyHealthSurvey","updateClassDailyHealthSurvey","updateClassDailyHealthSurvey/"+changeRequest.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom/"+changeRequest.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addStudent","addStudent","addStudent/"+changeRequest.getId()+"/","studentList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeStudent","removeStudent","removeStudent/"+changeRequest.getId()+"/","studentList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateStudent","updateStudent","updateStudent/"+changeRequest.getId()+"/","studentList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyStudentFrom","copyStudentFrom","copyStudentFrom/"+changeRequest.getId()+"/","studentList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addStudentHealthSurvey","addStudentHealthSurvey","addStudentHealthSurvey/"+changeRequest.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeStudentHealthSurvey","removeStudentHealthSurvey","removeStudentHealthSurvey/"+changeRequest.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateStudentHealthSurvey","updateStudentHealthSurvey","updateStudentHealthSurvey/"+changeRequest.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom/"+changeRequest.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addStudentDailyAnswer","addStudentDailyAnswer","addStudentDailyAnswer/"+changeRequest.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeStudentDailyAnswer","removeStudentDailyAnswer","removeStudentDailyAnswer/"+changeRequest.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateStudentDailyAnswer","updateStudentDailyAnswer","updateStudentDailyAnswer/"+changeRequest.getId()+"/","studentDailyAnswerList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyStudentDailyAnswerFrom","copyStudentDailyAnswerFrom","copyStudentDailyAnswerFrom/"+changeRequest.getId()+"/","studentDailyAnswerList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ChangeRequest createChangeRequest(HealthUserContext userContext, String name,String requestTypeId,String platformId) throws Exception
	//public ChangeRequest createChangeRequest(HealthUserContext userContext,String name, String requestTypeId, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfChangeRequest(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


		ChangeRequest changeRequest=createNewChangeRequest();	

		changeRequest.setName(name);
		changeRequest.setCreateTime(userContext.now());
		changeRequest.setRemoteIp(userContext.getRemoteIP());
			
		ChangeRequestType requestType = loadChangeRequestType(userContext, requestTypeId,emptyOptions());
		changeRequest.setRequestType(requestType);
		
		
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		changeRequest.setPlatform(platform);
		
		

		changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
		
		onNewInstanceCreated(userContext, changeRequest);
		return changeRequest;


	}
	protected ChangeRequest createNewChangeRequest()
	{

		return new ChangeRequest();
	}

	protected void checkParamsForUpdatingChangeRequest(HealthUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest( changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfChangeRequest(parseString(newValueExpr));
		}		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}



	public ChangeRequest clone(HealthUserContext userContext, String fromChangeRequestId) throws Exception{

		return changeRequestDaoOf(userContext).clone(fromChangeRequestId, this.allTokens());
	}

	public ChangeRequest internalSaveChangeRequest(HealthUserContext userContext, ChangeRequest changeRequest) throws Exception
	{
		return internalSaveChangeRequest(userContext, changeRequest, allTokens());

	}
	public ChangeRequest internalSaveChangeRequest(HealthUserContext userContext, ChangeRequest changeRequest, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);


		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			if (changeRequest.isChanged()){
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			}
			changeRequest = saveChangeRequest(userContext, changeRequest, options);
			return changeRequest;

		}

	}

	public ChangeRequest updateChangeRequest(HealthUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		if(changeRequest.getVersion() != changeRequestVersion){
			String message = "The target version("+changeRequest.getVersion()+") is not equals to version("+changeRequestVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			changeRequest.changeProperty(property, newValueExpr);
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			//return saveChangeRequest(userContext, changeRequest, tokens().done());
		}

	}

	public ChangeRequest updateChangeRequestProperty(HealthUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		if(changeRequest.getVersion() != changeRequestVersion){
			String message = "The target version("+changeRequest.getVersion()+") is not equals to version("+changeRequestVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.

			changeRequest.changeProperty(property, newValueExpr);
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			//return saveChangeRequest(userContext, changeRequest, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ChangeRequestTokens tokens(){
		return ChangeRequestTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ChangeRequestTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortSchoolClassListWith("id","desc")
		.sortTeacherListWith("id","desc")
		.sortGuardianListWith("id","desc")
		.sortClassQuestionListWith("id","desc")
		.sortClassDailyHealthSurveyListWith("id","desc")
		.sortStudentListWith("id","desc")
		.sortStudentHealthSurveyListWith("id","desc")
		.sortStudentDailyAnswerListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ChangeRequestTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherRequestType(HealthUserContext userContext, String changeRequestId, String anotherRequestTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
 		checkerOf(userContext).checkIdOfChangeRequestType(anotherRequestTypeId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

 	}
 	public ChangeRequest transferToAnotherRequestType(HealthUserContext userContext, String changeRequestId, String anotherRequestTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherRequestType(userContext, changeRequestId,anotherRequestTypeId);
 
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());	
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequestType requestType = loadChangeRequestType(userContext, anotherRequestTypeId, emptyOptions());		
			changeRequest.updateRequestType(requestType);		
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
			
			return present(userContext,changeRequest, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherRequestTypeWithCode(HealthUserContext userContext, String changeRequestId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
 		checkerOf(userContext).checkCodeOfChangeRequestType( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

 	}

 	public ChangeRequest transferToAnotherRequestTypeWithCode(HealthUserContext userContext, String changeRequestId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherRequestTypeWithCode(userContext, changeRequestId,anotherCode);
 		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequestType requestType = loadChangeRequestTypeWithCode(userContext, anotherCode, emptyOptions());
			changeRequest.updateRequestType(requestType);
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());

			return present(userContext,changeRequest, allTokens());

		}
 	}

	 


	public CandidateChangeRequestType requestCandidateRequestType(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequestType result = new CandidateChangeRequestType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequestType> candidateList = changeRequestTypeDaoOf(userContext).requestCandidateChangeRequestTypeForChangeRequest(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String changeRequestId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

 	}
 	public ChangeRequest transferToAnotherPlatform(HealthUserContext userContext, String changeRequestId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, changeRequestId,anotherPlatformId);
 
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());	
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			changeRequest.updatePlatform(platform);		
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
			
			return present(userContext,changeRequest, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForChangeRequest(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ChangeRequestType loadChangeRequestType(HealthUserContext userContext, String newRequestTypeId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestTypeDaoOf(userContext).load(newRequestTypeId, options);
 	}
 	
 	protected ChangeRequestType loadChangeRequestTypeWithCode(HealthUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return changeRequestTypeDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String changeRequestId, int changeRequestVersion) throws Exception {
		//deleteInternal(userContext, changeRequestId, changeRequestVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String changeRequestId, int changeRequestVersion) throws Exception{

		changeRequestDaoOf(userContext).delete(changeRequestId, changeRequestVersion);
	}

	public ChangeRequest forgetByAll(HealthUserContext userContext, String changeRequestId, int changeRequestVersion) throws Exception {
		return forgetByAllInternal(userContext, changeRequestId, changeRequestVersion);
	}
	protected ChangeRequest forgetByAllInternal(HealthUserContext userContext,
			String changeRequestId, int changeRequestVersion) throws Exception{

		return changeRequestDaoOf(userContext).disconnectFromAll(changeRequestId, changeRequestVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ChangeRequestManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return changeRequestDaoOf(userContext).deleteAll();
	}


	//disconnect ChangeRequest with class_teacher in SchoolClass
	protected ChangeRequest breakWithSchoolClassByClassTeacher(HealthUserContext userContext, String changeRequestId, String classTeacherId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveSchoolClassListWithClassTeacher(changeRequest, classTeacherId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with platform in SchoolClass
	protected ChangeRequest breakWithSchoolClassByPlatform(HealthUserContext userContext, String changeRequestId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveSchoolClassListWithPlatform(changeRequest, platformId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with platform in Teacher
	protected ChangeRequest breakWithTeacherByPlatform(HealthUserContext userContext, String changeRequestId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveTeacherListWithPlatform(changeRequest, platformId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTeacherList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with address in Guardian
	protected ChangeRequest breakWithGuardianByAddress(HealthUserContext userContext, String changeRequestId, String addressId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveGuardianListWithAddress(changeRequest, addressId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with wechat_user in Guardian
	protected ChangeRequest breakWithGuardianByWechatUser(HealthUserContext userContext, String changeRequestId, String wechatUserId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveGuardianListWithWechatUser(changeRequest, wechatUserId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with platform in Guardian
	protected ChangeRequest breakWithGuardianByPlatform(HealthUserContext userContext, String changeRequestId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveGuardianListWithPlatform(changeRequest, platformId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with question_type in ClassQuestion
	protected ChangeRequest breakWithClassQuestionByQuestionType(HealthUserContext userContext, String changeRequestId, String questionTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveClassQuestionListWithQuestionType(changeRequest, questionTypeId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with question_source in ClassQuestion
	protected ChangeRequest breakWithClassQuestionByQuestionSource(HealthUserContext userContext, String changeRequestId, String questionSourceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveClassQuestionListWithQuestionSource(changeRequest, questionSourceId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with creator in ClassQuestion
	protected ChangeRequest breakWithClassQuestionByCreator(HealthUserContext userContext, String changeRequestId, String creatorId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveClassQuestionListWithCreator(changeRequest, creatorId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with school_class in ClassDailyHealthSurvey
	protected ChangeRequest breakWithClassDailyHealthSurveyBySchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithSchoolClass(changeRequest, schoolClassId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with creator in ClassDailyHealthSurvey
	protected ChangeRequest breakWithClassDailyHealthSurveyByCreator(HealthUserContext userContext, String changeRequestId, String creatorId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithCreator(changeRequest, creatorId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with guardian in Student
	protected ChangeRequest breakWithStudentByGuardian(HealthUserContext userContext, String changeRequestId, String guardianId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentListWithGuardian(changeRequest, guardianId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with school_class in Student
	protected ChangeRequest breakWithStudentBySchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentListWithSchoolClass(changeRequest, schoolClassId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with student_id in Student
	protected ChangeRequest breakWithStudentByStudentId(HealthUserContext userContext, String changeRequestId, String studentIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentListWithStudentId(changeRequest, studentIdId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with student in StudentHealthSurvey
	protected ChangeRequest breakWithStudentHealthSurveyByStudent(HealthUserContext userContext, String changeRequestId, String studentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentHealthSurveyListWithStudent(changeRequest, studentId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with survey_status in StudentHealthSurvey
	protected ChangeRequest breakWithStudentHealthSurveyBySurveyStatus(HealthUserContext userContext, String changeRequestId, String surveyStatusId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSurveyStatus(changeRequest, surveyStatusId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with school_class in StudentHealthSurvey
	protected ChangeRequest breakWithStudentHealthSurveyBySchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSchoolClass(changeRequest, schoolClassId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with class_daily_health_survey in StudentHealthSurvey
	protected ChangeRequest breakWithStudentHealthSurveyByClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(changeRequest, classDailyHealthSurveyId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with student_health_survey in StudentDailyAnswer
	protected ChangeRequest breakWithStudentDailyAnswerByStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentDailyAnswerListWithStudentHealthSurvey(changeRequest, studentHealthSurveyId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
				return changeRequest;
			}
	}
	//disconnect ChangeRequest with question in StudentDailyAnswer
	protected ChangeRequest breakWithStudentDailyAnswerByQuestion(HealthUserContext userContext, String changeRequestId, String questionId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveStudentDailyAnswerListWithQuestion(changeRequest, questionId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
				return changeRequest;
			}
	}






	protected void checkParamsForAddingSchoolClass(HealthUserContext userContext, String changeRequestId, String name, String classTeacherId, String platformId, String schoole,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkNameOfSchoolClass(name);
		
		checkerOf(userContext).checkClassTeacherIdOfSchoolClass(classTeacherId);
		
		checkerOf(userContext).checkPlatformIdOfSchoolClass(platformId);
		
		checkerOf(userContext).checkSchooleOfSchoolClass(schoole);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addSchoolClass(HealthUserContext userContext, String changeRequestId, String name, String classTeacherId, String platformId, String schoole, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingSchoolClass(userContext,changeRequestId,name, classTeacherId, platformId, schoole,tokensExpr);

		SchoolClass schoolClass = createSchoolClass(userContext,name, classTeacherId, platformId, schoole);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addSchoolClass( schoolClass );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
			
			userContext.getManagerGroup().getSchoolClassManager().onNewInstanceCreated(userContext, schoolClass);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSchoolClassProperties(HealthUserContext userContext, String changeRequestId,String id,String name,String schoole,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfSchoolClass(id);

		checkerOf(userContext).checkNameOfSchoolClass( name);
		checkerOf(userContext).checkSchooleOfSchoolClass( schoole);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateSchoolClassProperties(HealthUserContext userContext, String changeRequestId, String id,String name,String schoole, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSchoolClassProperties(userContext,changeRequestId,id,name,schoole,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSchoolClassListList()
				.searchSchoolClassListWith(SchoolClass.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getSchoolClassList().isEmpty()){
			throw new ChangeRequestManagerException("SchoolClass is NOT FOUND with id: '"+id+"'");
		}

		SchoolClass item = changeRequestToUpdate.getSchoolClassList().first();

		item.updateName( name );
		item.updateSchoole( schoole );


		//checkParamsForAddingSchoolClass(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withSchoolClassList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected SchoolClass createSchoolClass(HealthUserContext userContext, String name, String classTeacherId, String platformId, String schoole) throws Exception{

		SchoolClass schoolClass = new SchoolClass();
		
		
		schoolClass.setName(name);		
		Teacher  classTeacher = new Teacher();
		classTeacher.setId(classTeacherId);		
		schoolClass.setClassTeacher(classTeacher);		
		schoolClass.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		schoolClass.setPlatform(platform);		
		schoolClass.setSchoole(schoole);
	
		
		return schoolClass;


	}

	protected SchoolClass createIndexedSchoolClass(String id, int version){

		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setId(id);
		schoolClass.setVersion(version);
		return schoolClass;

	}

	protected void checkParamsForRemovingSchoolClassList(HealthUserContext userContext, String changeRequestId,
			String schoolClassIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String schoolClassIdItem: schoolClassIds){
			checkerOf(userContext).checkIdOfSchoolClass(schoolClassIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeSchoolClassList(HealthUserContext userContext, String changeRequestId,
			String schoolClassIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingSchoolClassList(userContext, changeRequestId,  schoolClassIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveSchoolClassList(changeRequest, schoolClassIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
				deleteRelationListInGraph(userContext, changeRequest.getSchoolClassList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingSchoolClass(HealthUserContext userContext, String changeRequestId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeSchoolClass(HealthUserContext userContext, String changeRequestId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingSchoolClass(userContext,changeRequestId, schoolClassId, schoolClassVersion,tokensExpr);

		SchoolClass schoolClass = createIndexedSchoolClass(schoolClassId, schoolClassVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeSchoolClass( schoolClass );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
			deleteRelationInGraph(userContext, schoolClass);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingSchoolClass(HealthUserContext userContext, String changeRequestId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copySchoolClassFrom(HealthUserContext userContext, String changeRequestId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingSchoolClass(userContext,changeRequestId, schoolClassId, schoolClassVersion,tokensExpr);

		SchoolClass schoolClass = createIndexedSchoolClass(schoolClassId, schoolClassVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copySchoolClassFrom( schoolClass );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
			
			userContext.getManagerGroup().getSchoolClassManager().onNewInstanceCreated(userContext, (SchoolClass)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingSchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		

		if(SchoolClass.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfSchoolClass(parseString(newValueExpr));
		}
		
		if(SchoolClass.SCHOOLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchooleOfSchoolClass(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateSchoolClass(HealthUserContext userContext, String changeRequestId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingSchoolClass(userContext, changeRequestId, schoolClassId, schoolClassVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withSchoolClassList().searchSchoolClassListWith(SchoolClass.ID_PROPERTY, "eq", schoolClassId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeSchoolClass( schoolClass );
			//make changes to AcceleraterAccount.
			SchoolClass schoolClassIndex = createIndexedSchoolClass(schoolClassId, schoolClassVersion);

			SchoolClass schoolClass = changeRequest.findTheSchoolClass(schoolClassIndex);
			if(schoolClass == null){
				throw new ChangeRequestManagerException(schoolClass+" is NOT FOUND" );
			}

			schoolClass.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withSchoolClassList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingTeacher(HealthUserContext userContext, String changeRequestId, String name, String mobile, String schoole, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkNameOfTeacher(name);
		
		checkerOf(userContext).checkMobileOfTeacher(mobile);
		
		checkerOf(userContext).checkSchooleOfTeacher(schoole);
		
		checkerOf(userContext).checkPlatformIdOfTeacher(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addTeacher(HealthUserContext userContext, String changeRequestId, String name, String mobile, String schoole, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingTeacher(userContext,changeRequestId,name, mobile, schoole, platformId,tokensExpr);

		Teacher teacher = createTeacher(userContext,name, mobile, schoole, platformId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addTeacher( teacher );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTeacherList().done());
			
			userContext.getManagerGroup().getTeacherManager().onNewInstanceCreated(userContext, teacher);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingTeacherProperties(HealthUserContext userContext, String changeRequestId,String id,String name,String mobile,String schoole,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfTeacher(id);

		checkerOf(userContext).checkNameOfTeacher( name);
		checkerOf(userContext).checkMobileOfTeacher( mobile);
		checkerOf(userContext).checkSchooleOfTeacher( schoole);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateTeacherProperties(HealthUserContext userContext, String changeRequestId, String id,String name,String mobile,String schoole, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTeacherProperties(userContext,changeRequestId,id,name,mobile,schoole,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withTeacherListList()
				.searchTeacherListWith(Teacher.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getTeacherList().isEmpty()){
			throw new ChangeRequestManagerException("Teacher is NOT FOUND with id: '"+id+"'");
		}

		Teacher item = changeRequestToUpdate.getTeacherList().first();

		item.updateName( name );
		item.updateMobile( mobile );
		item.updateSchoole( schoole );


		//checkParamsForAddingTeacher(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withTeacherList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected Teacher createTeacher(HealthUserContext userContext, String name, String mobile, String schoole, String platformId) throws Exception{

		Teacher teacher = new Teacher();
		
		
		teacher.setName(name);		
		teacher.setMobile(mobile);		
		teacher.setSchoole(schoole);		
		teacher.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		teacher.setPlatform(platform);
	
		
		return teacher;


	}

	protected Teacher createIndexedTeacher(String id, int version){

		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setVersion(version);
		return teacher;

	}

	protected void checkParamsForRemovingTeacherList(HealthUserContext userContext, String changeRequestId,
			String teacherIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String teacherIdItem: teacherIds){
			checkerOf(userContext).checkIdOfTeacher(teacherIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeTeacherList(HealthUserContext userContext, String changeRequestId,
			String teacherIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingTeacherList(userContext, changeRequestId,  teacherIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveTeacherList(changeRequest, teacherIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTeacherList().done());
				deleteRelationListInGraph(userContext, changeRequest.getTeacherList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingTeacher(HealthUserContext userContext, String changeRequestId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkVersionOfTeacher(teacherVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeTeacher(HealthUserContext userContext, String changeRequestId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingTeacher(userContext,changeRequestId, teacherId, teacherVersion,tokensExpr);

		Teacher teacher = createIndexedTeacher(teacherId, teacherVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeTeacher( teacher );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTeacherList().done());
			deleteRelationInGraph(userContext, teacher);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingTeacher(HealthUserContext userContext, String changeRequestId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkVersionOfTeacher(teacherVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyTeacherFrom(HealthUserContext userContext, String changeRequestId,
		String teacherId, int teacherVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingTeacher(userContext,changeRequestId, teacherId, teacherVersion,tokensExpr);

		Teacher teacher = createIndexedTeacher(teacherId, teacherVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copyTeacherFrom( teacher );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTeacherList().done());
			
			userContext.getManagerGroup().getTeacherManager().onNewInstanceCreated(userContext, (Teacher)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingTeacher(HealthUserContext userContext, String changeRequestId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateTeacher(HealthUserContext userContext, String changeRequestId, String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingTeacher(userContext, changeRequestId, teacherId, teacherVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withTeacherList().searchTeacherListWith(Teacher.ID_PROPERTY, "eq", teacherId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeTeacher( teacher );
			//make changes to AcceleraterAccount.
			Teacher teacherIndex = createIndexedTeacher(teacherId, teacherVersion);

			Teacher teacher = changeRequest.findTheTeacher(teacherIndex);
			if(teacher == null){
				throw new ChangeRequestManagerException(teacher+" is NOT FOUND" );
			}

			teacher.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withTeacherList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingGuardian(HealthUserContext userContext, String changeRequestId, String name, String mobile, String addressId, String wechatUserId, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkNameOfGuardian(name);
		
		checkerOf(userContext).checkMobileOfGuardian(mobile);
		
		checkerOf(userContext).checkAddressIdOfGuardian(addressId);
		
		checkerOf(userContext).checkWechatUserIdOfGuardian(wechatUserId);
		
		checkerOf(userContext).checkPlatformIdOfGuardian(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addGuardian(HealthUserContext userContext, String changeRequestId, String name, String mobile, String addressId, String wechatUserId, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingGuardian(userContext,changeRequestId,name, mobile, addressId, wechatUserId, platformId,tokensExpr);

		Guardian guardian = createGuardian(userContext,name, mobile, addressId, wechatUserId, platformId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addGuardian( guardian );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
			
			userContext.getManagerGroup().getGuardianManager().onNewInstanceCreated(userContext, guardian);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingGuardianProperties(HealthUserContext userContext, String changeRequestId,String id,String name,String mobile,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfGuardian(id);

		checkerOf(userContext).checkNameOfGuardian( name);
		checkerOf(userContext).checkMobileOfGuardian( mobile);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateGuardianProperties(HealthUserContext userContext, String changeRequestId, String id,String name,String mobile, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingGuardianProperties(userContext,changeRequestId,id,name,mobile,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withGuardianListList()
				.searchGuardianListWith(Guardian.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getGuardianList().isEmpty()){
			throw new ChangeRequestManagerException("Guardian is NOT FOUND with id: '"+id+"'");
		}

		Guardian item = changeRequestToUpdate.getGuardianList().first();

		item.updateName( name );
		item.updateMobile( mobile );


		//checkParamsForAddingGuardian(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withGuardianList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected Guardian createGuardian(HealthUserContext userContext, String name, String mobile, String addressId, String wechatUserId, String platformId) throws Exception{

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
		Platform  platform = new Platform();
		platform.setId(platformId);		
		guardian.setPlatform(platform);
	
		
		return guardian;


	}

	protected Guardian createIndexedGuardian(String id, int version){

		Guardian guardian = new Guardian();
		guardian.setId(id);
		guardian.setVersion(version);
		return guardian;

	}

	protected void checkParamsForRemovingGuardianList(HealthUserContext userContext, String changeRequestId,
			String guardianIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String guardianIdItem: guardianIds){
			checkerOf(userContext).checkIdOfGuardian(guardianIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeGuardianList(HealthUserContext userContext, String changeRequestId,
			String guardianIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingGuardianList(userContext, changeRequestId,  guardianIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveGuardianList(changeRequest, guardianIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
				deleteRelationListInGraph(userContext, changeRequest.getGuardianList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingGuardian(HealthUserContext userContext, String changeRequestId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeGuardian(HealthUserContext userContext, String changeRequestId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingGuardian(userContext,changeRequestId, guardianId, guardianVersion,tokensExpr);

		Guardian guardian = createIndexedGuardian(guardianId, guardianVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeGuardian( guardian );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
			deleteRelationInGraph(userContext, guardian);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingGuardian(HealthUserContext userContext, String changeRequestId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyGuardianFrom(HealthUserContext userContext, String changeRequestId,
		String guardianId, int guardianVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingGuardian(userContext,changeRequestId, guardianId, guardianVersion,tokensExpr);

		Guardian guardian = createIndexedGuardian(guardianId, guardianVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copyGuardianFrom( guardian );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
			
			userContext.getManagerGroup().getGuardianManager().onNewInstanceCreated(userContext, (Guardian)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingGuardian(HealthUserContext userContext, String changeRequestId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfGuardian(guardianId);
		checkerOf(userContext).checkVersionOfGuardian(guardianVersion);
		

		if(Guardian.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfGuardian(parseString(newValueExpr));
		}
		
		if(Guardian.MOBILE_PROPERTY.equals(property)){
			checkerOf(userContext).checkMobileOfGuardian(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateGuardian(HealthUserContext userContext, String changeRequestId, String guardianId, int guardianVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingGuardian(userContext, changeRequestId, guardianId, guardianVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withGuardianList().searchGuardianListWith(Guardian.ID_PROPERTY, "eq", guardianId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeGuardian( guardian );
			//make changes to AcceleraterAccount.
			Guardian guardianIndex = createIndexedGuardian(guardianId, guardianVersion);

			Guardian guardian = changeRequest.findTheGuardian(guardianIndex);
			if(guardian == null){
				throw new ChangeRequestManagerException(guardian+" is NOT FOUND" );
			}

			guardian.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withGuardianList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingClassQuestion(HealthUserContext userContext, String changeRequestId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkTopicOfClassQuestion(topic);
		
		checkerOf(userContext).checkQuestionTypeIdOfClassQuestion(questionTypeId);
		
		checkerOf(userContext).checkOptionAOfClassQuestion(optionA);
		
		checkerOf(userContext).checkOptionBOfClassQuestion(optionB);
		
		checkerOf(userContext).checkOptionCOfClassQuestion(optionC);
		
		checkerOf(userContext).checkOptionDOfClassQuestion(optionD);
		
		checkerOf(userContext).checkQuestionSourceIdOfClassQuestion(questionSourceId);
		
		checkerOf(userContext).checkCreatorIdOfClassQuestion(creatorId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addClassQuestion(HealthUserContext userContext, String changeRequestId, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassQuestion(userContext,changeRequestId,topic, questionTypeId, optionA, optionB, optionC, optionD, questionSourceId, creatorId,tokensExpr);

		ClassQuestion classQuestion = createClassQuestion(userContext,topic, questionTypeId, optionA, optionB, optionC, optionD, questionSourceId, creatorId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addClassQuestion( classQuestion );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, classQuestion);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassQuestionProperties(HealthUserContext userContext, String changeRequestId,String id,String topic,String optionA,String optionB,String optionC,String optionD,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfClassQuestion(id);

		checkerOf(userContext).checkTopicOfClassQuestion( topic);
		checkerOf(userContext).checkOptionAOfClassQuestion( optionA);
		checkerOf(userContext).checkOptionBOfClassQuestion( optionB);
		checkerOf(userContext).checkOptionCOfClassQuestion( optionC);
		checkerOf(userContext).checkOptionDOfClassQuestion( optionD);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateClassQuestionProperties(HealthUserContext userContext, String changeRequestId, String id,String topic,String optionA,String optionB,String optionC,String optionD, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassQuestionProperties(userContext,changeRequestId,id,topic,optionA,optionB,optionC,optionD,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassQuestionListList()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getClassQuestionList().isEmpty()){
			throw new ChangeRequestManagerException("ClassQuestion is NOT FOUND with id: '"+id+"'");
		}

		ClassQuestion item = changeRequestToUpdate.getClassQuestionList().first();

		item.updateTopic( topic );
		item.updateOptionA( optionA );
		item.updateOptionB( optionB );
		item.updateOptionC( optionC );
		item.updateOptionD( optionD );


		//checkParamsForAddingClassQuestion(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withClassQuestionList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassQuestion createClassQuestion(HealthUserContext userContext, String topic, String questionTypeId, String optionA, String optionB, String optionC, String optionD, String questionSourceId, String creatorId) throws Exception{

		ClassQuestion classQuestion = new ClassQuestion();
		
		
		classQuestion.setTopic(topic);		
		QuestionType  questionType = new QuestionType();
		questionType.setId(questionTypeId);		
		classQuestion.setQuestionType(questionType);		
		classQuestion.setOptionA(optionA);		
		classQuestion.setOptionB(optionB);		
		classQuestion.setOptionC(optionC);		
		classQuestion.setOptionD(optionD);		
		QuestionSource  questionSource = new QuestionSource();
		questionSource.setId(questionSourceId);		
		classQuestion.setQuestionSource(questionSource);		
		WechatUser  creator = new WechatUser();
		creator.setId(creatorId);		
		classQuestion.setCreator(creator);
	
		
		return classQuestion;


	}

	protected ClassQuestion createIndexedClassQuestion(String id, int version){

		ClassQuestion classQuestion = new ClassQuestion();
		classQuestion.setId(id);
		classQuestion.setVersion(version);
		return classQuestion;

	}

	protected void checkParamsForRemovingClassQuestionList(HealthUserContext userContext, String changeRequestId,
			String classQuestionIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String classQuestionIdItem: classQuestionIds){
			checkerOf(userContext).checkIdOfClassQuestion(classQuestionIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeClassQuestionList(HealthUserContext userContext, String changeRequestId,
			String classQuestionIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassQuestionList(userContext, changeRequestId,  classQuestionIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveClassQuestionList(changeRequest, classQuestionIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
				deleteRelationListInGraph(userContext, changeRequest.getClassQuestionList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassQuestion(HealthUserContext userContext, String changeRequestId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeClassQuestion(HealthUserContext userContext, String changeRequestId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassQuestion(userContext,changeRequestId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeClassQuestion( classQuestion );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
			deleteRelationInGraph(userContext, classQuestion);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassQuestion(HealthUserContext userContext, String changeRequestId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyClassQuestionFrom(HealthUserContext userContext, String changeRequestId,
		String classQuestionId, int classQuestionVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassQuestion(userContext,changeRequestId, classQuestionId, classQuestionVersion,tokensExpr);

		ClassQuestion classQuestion = createIndexedClassQuestion(classQuestionId, classQuestionVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copyClassQuestionFrom( classQuestion );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
			
			userContext.getManagerGroup().getClassQuestionManager().onNewInstanceCreated(userContext, (ClassQuestion)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassQuestion(HealthUserContext userContext, String changeRequestId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfClassQuestion(classQuestionId);
		checkerOf(userContext).checkVersionOfClassQuestion(classQuestionVersion);
		

		if(ClassQuestion.TOPIC_PROPERTY.equals(property)){
			checkerOf(userContext).checkTopicOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_A_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionAOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_B_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionBOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_C_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionCOfClassQuestion(parseString(newValueExpr));
		}
		
		if(ClassQuestion.OPTION_D_PROPERTY.equals(property)){
			checkerOf(userContext).checkOptionDOfClassQuestion(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateClassQuestion(HealthUserContext userContext, String changeRequestId, String classQuestionId, int classQuestionVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassQuestion(userContext, changeRequestId, classQuestionId, classQuestionVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassQuestionList().searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "eq", classQuestionId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeClassQuestion( classQuestion );
			//make changes to AcceleraterAccount.
			ClassQuestion classQuestionIndex = createIndexedClassQuestion(classQuestionId, classQuestionVersion);

			ClassQuestion classQuestion = changeRequest.findTheClassQuestion(classQuestionIndex);
			if(classQuestion == null){
				throw new ChangeRequestManagerException(classQuestion+" is NOT FOUND" );
			}

			classQuestion.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassQuestionList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  ChangeRequest associateClassQuestionListToNewCreator(HealthUserContext userContext, String changeRequestId, String  classQuestionIds[], String name, String avatar, String addressId, String userTypeId, String platformId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "oneof", this.joinArray("|", classQuestionIds)).done();

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, options);

		WechatUser creator = wechatUserManagerOf(userContext).createWechatUser(userContext,  name,  avatar, addressId, userTypeId, platformId);

		for(ClassQuestion classQuestion: changeRequest.getClassQuestionList()) {
			//TODO: need to check if already associated
			classQuestion.updateCreator(creator);
		}
		return this.internalSaveChangeRequest(userContext, changeRequest);
	}
	*/

	public  ChangeRequest associateClassQuestionListToCreator(HealthUserContext userContext, String changeRequestId, String  classQuestionIds[], String creatorId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassQuestionListWith(ClassQuestion.ID_PROPERTY, "oneof", this.joinArray("|", classQuestionIds)).done();

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, options);

		WechatUser creator = wechatUserManagerOf(userContext).loadWechatUser(userContext,creatorId,new String[]{"none"} );

		for(ClassQuestion classQuestion: changeRequest.getClassQuestionList()) {
			//TODO: need to check if already associated
			classQuestion.updateCreator(creator);
		}
		return this.internalSaveChangeRequest(userContext, changeRequest);
	}


	protected void checkParamsForAddingClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String name, String schoolClassId, DateTime surveyTime, String creatorId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkNameOfClassDailyHealthSurvey(name);
		
		checkerOf(userContext).checkSchoolClassIdOfClassDailyHealthSurvey(schoolClassId);
		
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(surveyTime);
		
		checkerOf(userContext).checkCreatorIdOfClassDailyHealthSurvey(creatorId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String name, String schoolClassId, DateTime surveyTime, String creatorId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassDailyHealthSurvey(userContext,changeRequestId,name, schoolClassId, surveyTime, creatorId,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createClassDailyHealthSurvey(userContext,name, schoolClassId, surveyTime, creatorId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addClassDailyHealthSurvey( classDailyHealthSurvey );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, classDailyHealthSurvey);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassDailyHealthSurveyProperties(HealthUserContext userContext, String changeRequestId,String id,String name,DateTime surveyTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(id);

		checkerOf(userContext).checkNameOfClassDailyHealthSurvey( name);
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey( surveyTime);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateClassDailyHealthSurveyProperties(HealthUserContext userContext, String changeRequestId, String id,String name,DateTime surveyTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurveyProperties(userContext,changeRequestId,id,name,surveyTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassDailyHealthSurveyListList()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getClassDailyHealthSurveyList().isEmpty()){
			throw new ChangeRequestManagerException("ClassDailyHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		ClassDailyHealthSurvey item = changeRequestToUpdate.getClassDailyHealthSurveyList().first();

		item.updateName( name );
		item.updateSurveyTime( surveyTime );


		//checkParamsForAddingClassDailyHealthSurvey(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withClassDailyHealthSurveyList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name, String schoolClassId, DateTime surveyTime, String creatorId) throws Exception{

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		
		
		classDailyHealthSurvey.setName(name);		
		SchoolClass  schoolClass = new SchoolClass();
		schoolClass.setId(schoolClassId);		
		classDailyHealthSurvey.setSchoolClass(schoolClass);		
		classDailyHealthSurvey.setSurveyTime(surveyTime);		
		WechatUser  creator = new WechatUser();
		creator.setId(creatorId);		
		classDailyHealthSurvey.setCreator(creator);
	
		
		return classDailyHealthSurvey;


	}

	protected ClassDailyHealthSurvey createIndexedClassDailyHealthSurvey(String id, int version){

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(id);
		classDailyHealthSurvey.setVersion(version);
		return classDailyHealthSurvey;

	}

	protected void checkParamsForRemovingClassDailyHealthSurveyList(HealthUserContext userContext, String changeRequestId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String classDailyHealthSurveyIdItem: classDailyHealthSurveyIds){
			checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeClassDailyHealthSurveyList(HealthUserContext userContext, String changeRequestId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassDailyHealthSurveyList(userContext, changeRequestId,  classDailyHealthSurveyIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveClassDailyHealthSurveyList(changeRequest, classDailyHealthSurveyIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
				deleteRelationListInGraph(userContext, changeRequest.getClassDailyHealthSurveyList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassDailyHealthSurvey(userContext,changeRequestId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
			deleteRelationInGraph(userContext, classDailyHealthSurvey);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyClassDailyHealthSurveyFrom(HealthUserContext userContext, String changeRequestId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassDailyHealthSurvey(userContext,changeRequestId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copyClassDailyHealthSurveyFrom( classDailyHealthSurvey );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, (ClassDailyHealthSurvey)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		

		if(ClassDailyHealthSurvey.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfClassDailyHealthSurvey(parseString(newValueExpr));
		}
		
		if(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateClassDailyHealthSurvey(HealthUserContext userContext, String changeRequestId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassDailyHealthSurvey(userContext, changeRequestId, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassDailyHealthSurveyList().searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "eq", classDailyHealthSurveyId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			//make changes to AcceleraterAccount.
			ClassDailyHealthSurvey classDailyHealthSurveyIndex = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);

			ClassDailyHealthSurvey classDailyHealthSurvey = changeRequest.findTheClassDailyHealthSurvey(classDailyHealthSurveyIndex);
			if(classDailyHealthSurvey == null){
				throw new ChangeRequestManagerException(classDailyHealthSurvey+" is NOT FOUND" );
			}

			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withClassDailyHealthSurveyList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingStudent(HealthUserContext userContext, String changeRequestId, String name, String gender, String guardianId, String schoolClassId, String studentId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkNameOfStudent(name);
		
		checkerOf(userContext).checkGenderOfStudent(gender);
		
		checkerOf(userContext).checkGuardianIdOfStudent(guardianId);
		
		checkerOf(userContext).checkSchoolClassIdOfStudent(schoolClassId);
		
		checkerOf(userContext).checkStudentIdOfStudent(studentId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addStudent(HealthUserContext userContext, String changeRequestId, String name, String gender, String guardianId, String schoolClassId, String studentId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudent(userContext,changeRequestId,name, gender, guardianId, schoolClassId, studentId,tokensExpr);

		Student student = createStudent(userContext,name, gender, guardianId, schoolClassId, studentId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addStudent( student );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, student);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentProperties(HealthUserContext userContext, String changeRequestId,String id,String name,String gender,String studentId,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfStudent(id);

		checkerOf(userContext).checkNameOfStudent( name);
		checkerOf(userContext).checkGenderOfStudent( gender);
		checkerOf(userContext).checkStudentIdOfStudent( studentId);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateStudentProperties(HealthUserContext userContext, String changeRequestId, String id,String name,String gender,String studentId, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentProperties(userContext,changeRequestId,id,name,gender,studentId,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentListList()
				.searchStudentListWith(Student.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getStudentList().isEmpty()){
			throw new ChangeRequestManagerException("Student is NOT FOUND with id: '"+id+"'");
		}

		Student item = changeRequestToUpdate.getStudentList().first();

		item.updateName( name );
		item.updateGender( gender );
		item.updateStudentId( studentId );


		//checkParamsForAddingStudent(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withStudentList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected Student createStudent(HealthUserContext userContext, String name, String gender, String guardianId, String schoolClassId, String studentId) throws Exception{

		Student student = new Student();
		
		
		student.setName(name);		
		student.setGender(gender);		
		Guardian  guardian = new Guardian();
		guardian.setId(guardianId);		
		student.setGuardian(guardian);		
		SchoolClass  schoolClass = new SchoolClass();
		schoolClass.setId(schoolClassId);		
		student.setSchoolClass(schoolClass);		
		student.setStudentId(studentId);
	
		
		return student;


	}

	protected Student createIndexedStudent(String id, int version){

		Student student = new Student();
		student.setId(id);
		student.setVersion(version);
		return student;

	}

	protected void checkParamsForRemovingStudentList(HealthUserContext userContext, String changeRequestId,
			String studentIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String studentIdItem: studentIds){
			checkerOf(userContext).checkIdOfStudent(studentIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeStudentList(HealthUserContext userContext, String changeRequestId,
			String studentIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentList(userContext, changeRequestId,  studentIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveStudentList(changeRequest, studentIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
				deleteRelationListInGraph(userContext, changeRequest.getStudentList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudent(HealthUserContext userContext, String changeRequestId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeStudent(HealthUserContext userContext, String changeRequestId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudent(userContext,changeRequestId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeStudent( student );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
			deleteRelationInGraph(userContext, student);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudent(HealthUserContext userContext, String changeRequestId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyStudentFrom(HealthUserContext userContext, String changeRequestId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudent(userContext,changeRequestId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copyStudentFrom( student );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, (Student)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudent(HealthUserContext userContext, String changeRequestId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		

		if(Student.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfStudent(parseString(newValueExpr));
		}
		
		if(Student.GENDER_PROPERTY.equals(property)){
			checkerOf(userContext).checkGenderOfStudent(parseString(newValueExpr));
		}
		
		if(Student.STUDENT_ID_PROPERTY.equals(property)){
			checkerOf(userContext).checkStudentIdOfStudent(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateStudent(HealthUserContext userContext, String changeRequestId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudent(userContext, changeRequestId, studentId, studentVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentList().searchStudentListWith(Student.ID_PROPERTY, "eq", studentId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeStudent( student );
			//make changes to AcceleraterAccount.
			Student studentIndex = createIndexedStudent(studentId, studentVersion);

			Student student = changeRequest.findTheStudent(studentIndex);
			if(student == null){
				throw new ChangeRequestManagerException(student+" is NOT FOUND" );
			}

			student.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkStudentIdOfStudentHealthSurvey(studentId);
		
		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
		
		checkerOf(userContext).checkSurveyStatusIdOfStudentHealthSurvey(surveyStatusId);
		
		checkerOf(userContext).checkSchoolClassIdOfStudentHealthSurvey(schoolClassId);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfStudentHealthSurvey(classDailyHealthSurveyId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentHealthSurvey(userContext,changeRequestId,studentId, answerTime, surveyStatusId, schoolClassId, classDailyHealthSurveyId,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createStudentHealthSurvey(userContext,studentId, answerTime, surveyStatusId, schoolClassId, classDailyHealthSurveyId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addStudentHealthSurvey( studentHealthSurvey );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, studentHealthSurvey);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentHealthSurveyProperties(HealthUserContext userContext, String changeRequestId,String id,DateTime answerTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(id);

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey( answerTime);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateStudentHealthSurveyProperties(HealthUserContext userContext, String changeRequestId, String id,DateTime answerTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurveyProperties(userContext,changeRequestId,id,answerTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentHealthSurveyListList()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getStudentHealthSurveyList().isEmpty()){
			throw new ChangeRequestManagerException("StudentHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		StudentHealthSurvey item = changeRequestToUpdate.getStudentHealthSurveyList().first();

		item.updateAnswerTime( answerTime );


		//checkParamsForAddingStudentHealthSurvey(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withStudentHealthSurveyList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String schoolClassId, String classDailyHealthSurveyId) throws Exception{

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
		ClassDailyHealthSurvey  classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(classDailyHealthSurveyId);		
		studentHealthSurvey.setClassDailyHealthSurvey(classDailyHealthSurvey);		
		studentHealthSurvey.setCreateTime(userContext.now());		
		studentHealthSurvey.setLastUpdateTime(userContext.now());
	
		
		return studentHealthSurvey;


	}

	protected StudentHealthSurvey createIndexedStudentHealthSurvey(String id, int version){

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(id);
		studentHealthSurvey.setVersion(version);
		return studentHealthSurvey;

	}

	protected void checkParamsForRemovingStudentHealthSurveyList(HealthUserContext userContext, String changeRequestId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String studentHealthSurveyIdItem: studentHealthSurveyIds){
			checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeStudentHealthSurveyList(HealthUserContext userContext, String changeRequestId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentHealthSurveyList(userContext, changeRequestId,  studentHealthSurveyIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveStudentHealthSurveyList(changeRequest, studentHealthSurveyIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
				deleteRelationListInGraph(userContext, changeRequest.getStudentHealthSurveyList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentHealthSurvey(HealthUserContext userContext, String changeRequestId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeStudentHealthSurvey(HealthUserContext userContext, String changeRequestId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentHealthSurvey(userContext,changeRequestId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeStudentHealthSurvey( studentHealthSurvey );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
			deleteRelationInGraph(userContext, studentHealthSurvey);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentHealthSurvey(HealthUserContext userContext, String changeRequestId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyStudentHealthSurveyFrom(HealthUserContext userContext, String changeRequestId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentHealthSurvey(userContext,changeRequestId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentHealthSurvey.updateLastUpdateTime(userContext.now());

			changeRequest.copyStudentHealthSurveyFrom( studentHealthSurvey );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, (StudentHealthSurvey)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		

		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateStudentHealthSurvey(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentHealthSurvey(userContext, changeRequestId, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentHealthSurveyList().searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "eq", studentHealthSurveyId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeStudentHealthSurvey( studentHealthSurvey );
			//make changes to AcceleraterAccount.
			StudentHealthSurvey studentHealthSurveyIndex = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);

			StudentHealthSurvey studentHealthSurvey = changeRequest.findTheStudentHealthSurvey(studentHealthSurveyIndex);
			if(studentHealthSurvey == null){
				throw new ChangeRequestManagerException(studentHealthSurvey+" is NOT FOUND" );
			}

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentHealthSurveyList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, String questionId, String answer,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkStudentHealthSurveyIdOfStudentDailyAnswer(studentHealthSurveyId);
		
		checkerOf(userContext).checkQuestionIdOfStudentDailyAnswer(questionId);
		
		checkerOf(userContext).checkAnswerOfStudentDailyAnswer(answer);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentHealthSurveyId, String questionId, String answer, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentDailyAnswer(userContext,changeRequestId,studentHealthSurveyId, questionId, answer,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createStudentDailyAnswer(userContext,studentHealthSurveyId, questionId, answer);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addStudentDailyAnswer( studentDailyAnswer );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
			
			userContext.getManagerGroup().getStudentDailyAnswerManager().onNewInstanceCreated(userContext, studentDailyAnswer);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentDailyAnswerProperties(HealthUserContext userContext, String changeRequestId,String id,String answer,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(id);

		checkerOf(userContext).checkAnswerOfStudentDailyAnswer( answer);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateStudentDailyAnswerProperties(HealthUserContext userContext, String changeRequestId, String id,String answer, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentDailyAnswerProperties(userContext,changeRequestId,id,answer,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentDailyAnswerListList()
				.searchStudentDailyAnswerListWith(StudentDailyAnswer.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getStudentDailyAnswerList().isEmpty()){
			throw new ChangeRequestManagerException("StudentDailyAnswer is NOT FOUND with id: '"+id+"'");
		}

		StudentDailyAnswer item = changeRequestToUpdate.getStudentDailyAnswerList().first();

		item.updateAnswer( answer );


		//checkParamsForAddingStudentDailyAnswer(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withStudentDailyAnswerList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentDailyAnswer createStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId, String questionId, String answer) throws Exception{

		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		
		
		StudentHealthSurvey  studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(studentHealthSurveyId);		
		studentDailyAnswer.setStudentHealthSurvey(studentHealthSurvey);		
		DailySurveyQuestion  question = new DailySurveyQuestion();
		question.setId(questionId);		
		studentDailyAnswer.setQuestion(question);		
		studentDailyAnswer.setAnswer(answer);		
		studentDailyAnswer.setCreateTime(userContext.now());		
		studentDailyAnswer.setLastUpdateTime(userContext.now());
	
		
		return studentDailyAnswer;


	}

	protected StudentDailyAnswer createIndexedStudentDailyAnswer(String id, int version){

		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		studentDailyAnswer.setId(id);
		studentDailyAnswer.setVersion(version);
		return studentDailyAnswer;

	}

	protected void checkParamsForRemovingStudentDailyAnswerList(HealthUserContext userContext, String changeRequestId,
			String studentDailyAnswerIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String studentDailyAnswerIdItem: studentDailyAnswerIds){
			checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeStudentDailyAnswerList(HealthUserContext userContext, String changeRequestId,
			String studentDailyAnswerIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentDailyAnswerList(userContext, changeRequestId,  studentDailyAnswerIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveStudentDailyAnswerList(changeRequest, studentDailyAnswerIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
				deleteRelationListInGraph(userContext, changeRequest.getStudentDailyAnswerList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentDailyAnswer(HealthUserContext userContext, String changeRequestId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeStudentDailyAnswer(HealthUserContext userContext, String changeRequestId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentDailyAnswer(userContext,changeRequestId, studentDailyAnswerId, studentDailyAnswerVersion,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeStudentDailyAnswer( studentDailyAnswer );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
			deleteRelationInGraph(userContext, studentDailyAnswer);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentDailyAnswer(HealthUserContext userContext, String changeRequestId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyStudentDailyAnswerFrom(HealthUserContext userContext, String changeRequestId,
		String studentDailyAnswerId, int studentDailyAnswerVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentDailyAnswer(userContext,changeRequestId, studentDailyAnswerId, studentDailyAnswerVersion,tokensExpr);

		StudentDailyAnswer studentDailyAnswer = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentDailyAnswer.updateLastUpdateTime(userContext.now());

			changeRequest.copyStudentDailyAnswerFrom( studentDailyAnswer );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
			
			userContext.getManagerGroup().getStudentDailyAnswerManager().onNewInstanceCreated(userContext, (StudentDailyAnswer)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfStudentDailyAnswer(studentDailyAnswerId);
		checkerOf(userContext).checkVersionOfStudentDailyAnswer(studentDailyAnswerVersion);
		

		if(StudentDailyAnswer.ANSWER_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerOfStudentDailyAnswer(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateStudentDailyAnswer(HealthUserContext userContext, String changeRequestId, String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentDailyAnswer(userContext, changeRequestId, studentDailyAnswerId, studentDailyAnswerVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentDailyAnswerList().searchStudentDailyAnswerListWith(StudentDailyAnswer.ID_PROPERTY, "eq", studentDailyAnswerId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeStudentDailyAnswer( studentDailyAnswer );
			//make changes to AcceleraterAccount.
			StudentDailyAnswer studentDailyAnswerIndex = createIndexedStudentDailyAnswer(studentDailyAnswerId, studentDailyAnswerVersion);

			StudentDailyAnswer studentDailyAnswer = changeRequest.findTheStudentDailyAnswer(studentDailyAnswerIndex);
			if(studentDailyAnswer == null){
				throw new ChangeRequestManagerException(studentDailyAnswer+" is NOT FOUND" );
			}

			studentDailyAnswer.changeProperty(property, newValueExpr);
			studentDailyAnswer.updateLastUpdateTime(userContext.now());
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withStudentDailyAnswerList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, ChangeRequest newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ChangeRequest.INTERNAL_TYPE);
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


