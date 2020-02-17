
package com.doublechaintech.health.teacher;

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
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.user.CandidateUser;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.user.User;






public class TeacherManagerImpl extends CustomHealthCheckerManager implements TeacherManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Teacher";
	@Override
	public TeacherDAO daoOf(HealthUserContext userContext) {
		return teacherDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws TeacherManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new TeacherManagerException(message);

	}



 	protected Teacher saveTeacher(HealthUserContext userContext, Teacher teacher, String [] tokensExpr) throws Exception{	
 		//return getTeacherDAO().save(teacher, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveTeacher(userContext, teacher, tokens);
 	}
 	
 	protected Teacher saveTeacherDetail(HealthUserContext userContext, Teacher teacher) throws Exception{	

 		
 		return saveTeacher(userContext, teacher, allTokens());
 	}
 	
 	public Teacher loadTeacher(HealthUserContext userContext, String teacherId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).throwExceptionIfHasErrors( TeacherManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Teacher teacher = loadTeacher( userContext, teacherId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,teacher, tokens);
 	}
 	
 	
 	 public Teacher searchTeacher(HealthUserContext userContext, String teacherId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).throwExceptionIfHasErrors( TeacherManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Teacher teacher = loadTeacher( userContext, teacherId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,teacher, tokens);
 	}
 	
 	

 	protected Teacher present(HealthUserContext userContext, Teacher teacher, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,teacher,tokens);
		
		
		Teacher  teacherToPresent = teacherDaoOf(userContext).present(teacher, tokens);
		
		List<BaseEntity> entityListToNaming = teacherToPresent.collectRefercencesFromLists();
		teacherDaoOf(userContext).alias(entityListToNaming);
		
		return  teacherToPresent;
		
		
	}
 
 	
 	
 	public Teacher loadTeacherDetail(HealthUserContext userContext, String teacherId) throws Exception{	
 		Teacher teacher = loadTeacher( userContext, teacherId, allTokens());
 		return present(userContext,teacher, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String teacherId) throws Exception{	
 		Teacher teacher = loadTeacher( userContext, teacherId, viewTokens());
 		return present(userContext,teacher, allTokens());
		
 	}
 	protected Teacher saveTeacher(HealthUserContext userContext, Teacher teacher, Map<String,Object>tokens) throws Exception{	
 		return teacherDaoOf(userContext).save(teacher, tokens);
 	}
 	protected Teacher loadTeacher(HealthUserContext userContext, String teacherId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).throwExceptionIfHasErrors( TeacherManagerException.class);

 
 		return teacherDaoOf(userContext).load(teacherId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Teacher teacher, Map<String, Object> tokens){
		super.addActions(userContext, teacher, tokens);
		
		addAction(userContext, teacher, tokens,"@create","createTeacher","createTeacher/","main","primary");
		addAction(userContext, teacher, tokens,"@update","updateTeacher","updateTeacher/"+teacher.getId()+"/","main","primary");
		addAction(userContext, teacher, tokens,"@copy","cloneTeacher","cloneTeacher/"+teacher.getId()+"/","main","primary");
		
		addAction(userContext, teacher, tokens,"teacher.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+teacher.getId()+"/","main","primary");
		addAction(userContext, teacher, tokens,"teacher.transfer_to_user","transferToAnotherUser","transferToAnotherUser/"+teacher.getId()+"/","main","primary");
		addAction(userContext, teacher, tokens,"teacher.transfer_to_change_request","transferToAnotherChangeRequest","transferToAnotherChangeRequest/"+teacher.getId()+"/","main","primary");
		addAction(userContext, teacher, tokens,"teacher.addClassDailyHealthSurvey","addClassDailyHealthSurvey","addClassDailyHealthSurvey/"+teacher.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.removeClassDailyHealthSurvey","removeClassDailyHealthSurvey","removeClassDailyHealthSurvey/"+teacher.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.updateClassDailyHealthSurvey","updateClassDailyHealthSurvey","updateClassDailyHealthSurvey/"+teacher.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom/"+teacher.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.addStudentHealthSurvey","addStudentHealthSurvey","addStudentHealthSurvey/"+teacher.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.removeStudentHealthSurvey","removeStudentHealthSurvey","removeStudentHealthSurvey/"+teacher.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.updateStudentHealthSurvey","updateStudentHealthSurvey","updateStudentHealthSurvey/"+teacher.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom/"+teacher.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, teacher, tokens,"teacher.addHealthSurveyReport","addHealthSurveyReport","addHealthSurveyReport/"+teacher.getId()+"/","healthSurveyReportList","primary");
		addAction(userContext, teacher, tokens,"teacher.removeHealthSurveyReport","removeHealthSurveyReport","removeHealthSurveyReport/"+teacher.getId()+"/","healthSurveyReportList","primary");
		addAction(userContext, teacher, tokens,"teacher.updateHealthSurveyReport","updateHealthSurveyReport","updateHealthSurveyReport/"+teacher.getId()+"/","healthSurveyReportList","primary");
		addAction(userContext, teacher, tokens,"teacher.copyHealthSurveyReportFrom","copyHealthSurveyReportFrom","copyHealthSurveyReportFrom/"+teacher.getId()+"/","healthSurveyReportList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Teacher teacher, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Teacher createTeacher(HealthUserContext userContext, String name,String mobile,String school,String schoolClass,int classSize,String platformId,String userId,String changeRequestId) throws Exception
	//public Teacher createTeacher(HealthUserContext userContext,String name, String mobile, String school, String schoolClass, int classSize, String platformId, String userId, String changeRequestId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfTeacher(name);
		checkerOf(userContext).checkMobileOfTeacher(mobile);
		checkerOf(userContext).checkSchoolOfTeacher(school);
		checkerOf(userContext).checkSchoolClassOfTeacher(schoolClass);
		checkerOf(userContext).checkClassSizeOfTeacher(classSize);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


		Teacher teacher=createNewTeacher();	

		teacher.setName(name);
		teacher.setMobile(mobile);
		teacher.setSchool(school);
		teacher.setSchoolClass(schoolClass);
		teacher.setClassSize(classSize);
		teacher.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		teacher.setPlatform(platform);
		
		
			
		User user = loadUser(userContext, userId,emptyOptions());
		teacher.setUser(user);
		
		
		if(isValidIdentifier(changeRequestId)){	
			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId,emptyOptions());
			teacher.setChangeRequest(changeRequest);
		}
		

		teacher = saveTeacher(userContext, teacher, emptyOptions());
		
		onNewInstanceCreated(userContext, teacher);
		return teacher;


	}
	protected Teacher createNewTeacher()
	{

		return new Teacher();
	}

	protected void checkParamsForUpdatingTeacher(HealthUserContext userContext,String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkVersionOfTeacher( teacherVersion);
		

		if(Teacher.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfTeacher(parseString(newValueExpr));
		
			
		}
		if(Teacher.MOBILE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkMobileOfTeacher(parseString(newValueExpr));
		
			
		}
		if(Teacher.SCHOOL_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSchoolOfTeacher(parseString(newValueExpr));
		
			
		}
		if(Teacher.SCHOOL_CLASS_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSchoolClassOfTeacher(parseString(newValueExpr));
		
			
		}
		if(Teacher.CLASS_SIZE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkClassSizeOfTeacher(parseInt(newValueExpr));
		
			
		}		

				

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


	}



	public Teacher clone(HealthUserContext userContext, String fromTeacherId) throws Exception{

		return teacherDaoOf(userContext).clone(fromTeacherId, this.allTokens());
	}

	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher) throws Exception
	{
		return internalSaveTeacher(userContext, teacher, allTokens());

	}
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingTeacher(userContext, teacherId, teacherVersion, property, newValueExpr, tokensExpr);


		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Teacher.
			if (teacher.isChanged()){
			
			}
			teacher = saveTeacher(userContext, teacher, options);
			return teacher;

		}

	}

	public Teacher updateTeacher(HealthUserContext userContext,String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTeacher(userContext, teacherId, teacherVersion, property, newValueExpr, tokensExpr);



		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		if(teacher.getVersion() != teacherVersion){
			String message = "The target version("+teacher.getVersion()+") is not equals to version("+teacherVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Teacher.
			
			teacher.changeProperty(property, newValueExpr);
			teacher = saveTeacher(userContext, teacher, tokens().done());
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
			//return saveTeacher(userContext, teacher, tokens().done());
		}

	}

	public Teacher updateTeacherProperty(HealthUserContext userContext,String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingTeacher(userContext, teacherId, teacherVersion, property, newValueExpr, tokensExpr);

		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		if(teacher.getVersion() != teacherVersion){
			String message = "The target version("+teacher.getVersion()+") is not equals to version("+teacherVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Teacher.

			teacher.changeProperty(property, newValueExpr);
			
			teacher = saveTeacher(userContext, teacher, tokens().done());
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
			//return saveTeacher(userContext, teacher, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected TeacherTokens tokens(){
		return TeacherTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return TeacherTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortClassDailyHealthSurveyListWith("id","desc")
		.sortStudentHealthSurveyListWith("id","desc")
		.sortHealthSurveyReportListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return TeacherTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String teacherId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfTeacher(teacherId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

 	}
 	public Teacher transferToAnotherPlatform(HealthUserContext userContext, String teacherId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, teacherId,anotherPlatformId);
 
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());	
		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			teacher.updatePlatform(platform);		
			teacher = saveTeacher(userContext, teacher, emptyOptions());
			
			return present(userContext,teacher, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForTeacher(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherUser(HealthUserContext userContext, String teacherId, String anotherUserId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfTeacher(teacherId);
 		checkerOf(userContext).checkIdOfUser(anotherUserId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

 	}
 	public Teacher transferToAnotherUser(HealthUserContext userContext, String teacherId, String anotherUserId) throws Exception
 	{
 		checkParamsForTransferingAnotherUser(userContext, teacherId,anotherUserId);
 
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());	
		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			User user = loadUser(userContext, anotherUserId, emptyOptions());		
			teacher.updateUser(user);		
			teacher = saveTeacher(userContext, teacher, emptyOptions());
			
			return present(userContext,teacher, allTokens());
			
		}

 	}

	


	public CandidateUser requestCandidateUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateUser result = new CandidateUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<User> candidateList = userDaoOf(userContext).requestCandidateUserForTeacher(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherChangeRequest(HealthUserContext userContext, String teacherId, String anotherChangeRequestId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfTeacher(teacherId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherChangeRequestId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

 	}
 	public Teacher transferToAnotherChangeRequest(HealthUserContext userContext, String teacherId, String anotherChangeRequestId) throws Exception
 	{
 		checkParamsForTransferingAnotherChangeRequest(userContext, teacherId,anotherChangeRequestId);
 
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());	
		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest changeRequest = loadChangeRequest(userContext, anotherChangeRequestId, emptyOptions());		
			teacher.updateChangeRequest(changeRequest);		
			teacher = saveTeacher(userContext, teacher, emptyOptions());
			
			return present(userContext,teacher, allTokens());
			
		}

 	}

	


	public CandidateChangeRequest requestCandidateChangeRequest(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForTeacher(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newChangeRequestId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newChangeRequestId, options);
 	}
 	


	

 	protected User loadUser(HealthUserContext userContext, String newUserId, Map<String,Object> options) throws Exception
 	{

 		return userDaoOf(userContext).load(newUserId, options);
 	}
 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String teacherId, int teacherVersion) throws Exception {
		//deleteInternal(userContext, teacherId, teacherVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String teacherId, int teacherVersion) throws Exception{

		teacherDaoOf(userContext).delete(teacherId, teacherVersion);
	}

	public Teacher forgetByAll(HealthUserContext userContext, String teacherId, int teacherVersion) throws Exception {
		return forgetByAllInternal(userContext, teacherId, teacherVersion);
	}
	protected Teacher forgetByAllInternal(HealthUserContext userContext,
			String teacherId, int teacherVersion) throws Exception{

		return teacherDaoOf(userContext).disconnectFromAll(teacherId, teacherVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new TeacherManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return teacherDaoOf(userContext).deleteAll();
	}


	//disconnect Teacher with creator in ClassDailyHealthSurvey
	protected Teacher breakWithClassDailyHealthSurveyByCreator(HealthUserContext userContext, String teacherId, String creatorId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithCreator(teacher, creatorId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
				return teacher;
			}
	}
	//disconnect Teacher with change_request in ClassDailyHealthSurvey
	protected Teacher breakWithClassDailyHealthSurveyByChangeRequest(HealthUserContext userContext, String teacherId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithChangeRequest(teacher, changeRequestId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
				return teacher;
			}
	}
	//disconnect Teacher with student in StudentHealthSurvey
	protected Teacher breakWithStudentHealthSurveyByStudent(HealthUserContext userContext, String teacherId, String studentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveStudentHealthSurveyListWithStudent(teacher, studentId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
				return teacher;
			}
	}
	//disconnect Teacher with survey_status in StudentHealthSurvey
	protected Teacher breakWithStudentHealthSurveyBySurveyStatus(HealthUserContext userContext, String teacherId, String surveyStatusId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSurveyStatus(teacher, surveyStatusId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
				return teacher;
			}
	}
	//disconnect Teacher with class_daily_health_survey in StudentHealthSurvey
	protected Teacher breakWithStudentHealthSurveyByClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(teacher, classDailyHealthSurveyId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
				return teacher;
			}
	}
	//disconnect Teacher with change_request in StudentHealthSurvey
	protected Teacher breakWithStudentHealthSurveyByChangeRequest(HealthUserContext userContext, String teacherId, String changeRequestId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveStudentHealthSurveyListWithChangeRequest(teacher, changeRequestId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
				return teacher;
			}
	}
	//disconnect Teacher with student in HealthSurveyReport
	protected Teacher breakWithHealthSurveyReportByStudent(HealthUserContext userContext, String teacherId, String studentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveHealthSurveyReportListWithStudent(teacher, studentId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
				return teacher;
			}
	}
	//disconnect Teacher with survey in HealthSurveyReport
	protected Teacher breakWithHealthSurveyReportBySurvey(HealthUserContext userContext, String teacherId, String surveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveHealthSurveyReportListWithSurvey(teacher, surveyId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
				return teacher;
			}
	}






	protected void checkParamsForAddingClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String name, DateTime surveyTime, String creatorId, String downloadUrl, String changeRequestId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTeacher(teacherId);

		
		checkerOf(userContext).checkNameOfClassDailyHealthSurvey(name);
		
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(surveyTime);
		
		checkerOf(userContext).checkCreatorIdOfClassDailyHealthSurvey(creatorId);
		
		checkerOf(userContext).checkDownloadUrlOfClassDailyHealthSurvey(downloadUrl);
		
		checkerOf(userContext).checkChangeRequestIdOfClassDailyHealthSurvey(changeRequestId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


	}
	public  Teacher addClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String name, DateTime surveyTime, String creatorId, String downloadUrl, String changeRequestId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassDailyHealthSurvey(userContext,teacherId,name, surveyTime, creatorId, downloadUrl, changeRequestId,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createClassDailyHealthSurvey(userContext,name, surveyTime, creatorId, downloadUrl, changeRequestId);

		Teacher teacher = loadTeacher(userContext, teacherId, emptyOptions());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.addClassDailyHealthSurvey( classDailyHealthSurvey );
			teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, classDailyHealthSurvey);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassDailyHealthSurveyProperties(HealthUserContext userContext, String teacherId,String id,String name,DateTime surveyTime,String downloadUrl,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(id);

		checkerOf(userContext).checkNameOfClassDailyHealthSurvey( name);
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey( surveyTime);
		checkerOf(userContext).checkDownloadUrlOfClassDailyHealthSurvey( downloadUrl);

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher updateClassDailyHealthSurveyProperties(HealthUserContext userContext, String teacherId, String id,String name,DateTime surveyTime,String downloadUrl, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurveyProperties(userContext,teacherId,id,name,surveyTime,downloadUrl,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassDailyHealthSurveyListList()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "is", id).done();

		Teacher teacherToUpdate = loadTeacher(userContext, teacherId, options);

		if(teacherToUpdate.getClassDailyHealthSurveyList().isEmpty()){
			throw new TeacherManagerException("ClassDailyHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		ClassDailyHealthSurvey item = teacherToUpdate.getClassDailyHealthSurveyList().first();

		item.updateName( name );
		item.updateSurveyTime( surveyTime );
		item.updateDownloadUrl( downloadUrl );


		//checkParamsForAddingClassDailyHealthSurvey(userContext,teacherId,name, code, used,tokensExpr);
		Teacher teacher = saveTeacher(userContext, teacherToUpdate, tokens().withClassDailyHealthSurveyList().done());
		synchronized(teacher){
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name, DateTime surveyTime, String creatorId, String downloadUrl, String changeRequestId) throws Exception{

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		
		
		classDailyHealthSurvey.setName(name);		
		classDailyHealthSurvey.setSurveyTime(surveyTime);		
		User  creator = new User();
		creator.setId(creatorId);		
		classDailyHealthSurvey.setCreator(creator);		
		classDailyHealthSurvey.setDownloadUrl(downloadUrl);		
		ChangeRequest  changeRequest = new ChangeRequest();
		changeRequest.setId(changeRequestId);		
		classDailyHealthSurvey.setChangeRequest(changeRequest);
	
		
		return classDailyHealthSurvey;


	}

	protected ClassDailyHealthSurvey createIndexedClassDailyHealthSurvey(String id, int version){

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(id);
		classDailyHealthSurvey.setVersion(version);
		return classDailyHealthSurvey;

	}

	protected void checkParamsForRemovingClassDailyHealthSurveyList(HealthUserContext userContext, String teacherId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		for(String classDailyHealthSurveyIdItem: classDailyHealthSurveyIds){
			checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeClassDailyHealthSurveyList(HealthUserContext userContext, String teacherId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassDailyHealthSurveyList(userContext, teacherId,  classDailyHealthSurveyIds, tokensExpr);


			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
			synchronized(teacher){
				//Will be good when the teacher loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				teacherDaoOf(userContext).planToRemoveClassDailyHealthSurveyList(teacher, classDailyHealthSurveyIds, allTokens());
				teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
				deleteRelationListInGraph(userContext, teacher.getClassDailyHealthSurveyList());
				return present(userContext,teacher, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassDailyHealthSurvey(HealthUserContext userContext, String teacherId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeClassDailyHealthSurvey(HealthUserContext userContext, String teacherId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassDailyHealthSurvey(userContext,teacherId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
			deleteRelationInGraph(userContext, classDailyHealthSurvey);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassDailyHealthSurvey(HealthUserContext userContext, String teacherId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher copyClassDailyHealthSurveyFrom(HealthUserContext userContext, String teacherId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassDailyHealthSurvey(userContext,teacherId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			teacher.copyClassDailyHealthSurveyFrom( classDailyHealthSurvey );
			teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, (ClassDailyHealthSurvey)teacher.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		

		if(ClassDailyHealthSurvey.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfClassDailyHealthSurvey(parseString(newValueExpr));
		
		}
		
		if(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(parseTimestamp(newValueExpr));
		
		}
		
		if(ClassDailyHealthSurvey.DOWNLOAD_URL_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDownloadUrlOfClassDailyHealthSurvey(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}

	public  Teacher updateClassDailyHealthSurvey(HealthUserContext userContext, String teacherId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassDailyHealthSurvey(userContext, teacherId, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassDailyHealthSurveyList().searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "eq", classDailyHealthSurveyId).done();



		Teacher teacher = loadTeacher(userContext, teacherId, loadTokens);

		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//teacher.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			//make changes to AcceleraterAccount.
			ClassDailyHealthSurvey classDailyHealthSurveyIndex = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);

			ClassDailyHealthSurvey classDailyHealthSurvey = teacher.findTheClassDailyHealthSurvey(classDailyHealthSurveyIndex);
			if(classDailyHealthSurvey == null){
				throw new TeacherManagerException(classDailyHealthSurvey+" is NOT FOUND" );
			}

			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			
			teacher = saveTeacher(userContext, teacher, tokens().withClassDailyHealthSurveyList().done());
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  Teacher associateClassDailyHealthSurveyListToNewChangeRequest(HealthUserContext userContext, String teacherId, String  classDailyHealthSurveyIds[], String name, String requestTypeId, String platformId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "oneof", this.joinArray("|", classDailyHealthSurveyIds)).done();

		Teacher teacher = loadTeacher(userContext, teacherId, options);

		ChangeRequest changeRequest = changeRequestManagerOf(userContext).createChangeRequest(userContext,  name, requestTypeId, platformId);

		for(ClassDailyHealthSurvey classDailyHealthSurvey: teacher.getClassDailyHealthSurveyList()) {
			//TODO: need to check if already associated
			classDailyHealthSurvey.updateChangeRequest(changeRequest);
		}
		return this.internalSaveTeacher(userContext, teacher);
	}
	*/

	public  Teacher associateClassDailyHealthSurveyListToChangeRequest(HealthUserContext userContext, String teacherId, String  classDailyHealthSurveyIds[], String changeRequestId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "oneof", this.joinArray("|", classDailyHealthSurveyIds)).done();

		Teacher teacher = loadTeacher(userContext, teacherId, options);

		ChangeRequest changeRequest = changeRequestManagerOf(userContext).loadChangeRequest(userContext,changeRequestId,new String[]{"none"} );

		for(ClassDailyHealthSurvey classDailyHealthSurvey: teacher.getClassDailyHealthSurveyList()) {
			//TODO: need to check if already associated
			classDailyHealthSurvey.updateChangeRequest(changeRequest);
		}
		return this.internalSaveTeacher(userContext, teacher);
	}


	protected void checkParamsForAddingStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String changeRequestId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTeacher(teacherId);

		
		checkerOf(userContext).checkStudentIdOfStudentHealthSurvey(studentId);
		
		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
		
		checkerOf(userContext).checkSurveyStatusIdOfStudentHealthSurvey(surveyStatusId);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfStudentHealthSurvey(classDailyHealthSurveyId);
		
		checkerOf(userContext).checkChangeRequestIdOfStudentHealthSurvey(changeRequestId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


	}
	public  Teacher addStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String changeRequestId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentHealthSurvey(userContext,teacherId,studentId, answerTime, surveyStatusId, classDailyHealthSurveyId, changeRequestId,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createStudentHealthSurvey(userContext,studentId, answerTime, surveyStatusId, classDailyHealthSurveyId, changeRequestId);

		Teacher teacher = loadTeacher(userContext, teacherId, emptyOptions());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.addStudentHealthSurvey( studentHealthSurvey );
			teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, studentHealthSurvey);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentHealthSurveyProperties(HealthUserContext userContext, String teacherId,String id,DateTime answerTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(id);

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey( answerTime);

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher updateStudentHealthSurveyProperties(HealthUserContext userContext, String teacherId, String id,DateTime answerTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurveyProperties(userContext,teacherId,id,answerTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentHealthSurveyListList()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "is", id).done();

		Teacher teacherToUpdate = loadTeacher(userContext, teacherId, options);

		if(teacherToUpdate.getStudentHealthSurveyList().isEmpty()){
			throw new TeacherManagerException("StudentHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		StudentHealthSurvey item = teacherToUpdate.getStudentHealthSurveyList().first();

		item.updateAnswerTime( answerTime );


		//checkParamsForAddingStudentHealthSurvey(userContext,teacherId,name, code, used,tokensExpr);
		Teacher teacher = saveTeacher(userContext, teacherToUpdate, tokens().withStudentHealthSurveyList().done());
		synchronized(teacher){
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String changeRequestId) throws Exception{

		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		
		
		Student  student = new Student();
		student.setId(studentId);		
		studentHealthSurvey.setStudent(student);		
		studentHealthSurvey.setAnswerTime(answerTime);		
		SurveyStatus  surveyStatus = new SurveyStatus();
		surveyStatus.setId(surveyStatusId);		
		studentHealthSurvey.setSurveyStatus(surveyStatus);		
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

	protected void checkParamsForRemovingStudentHealthSurveyList(HealthUserContext userContext, String teacherId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		for(String studentHealthSurveyIdItem: studentHealthSurveyIds){
			checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeStudentHealthSurveyList(HealthUserContext userContext, String teacherId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentHealthSurveyList(userContext, teacherId,  studentHealthSurveyIds, tokensExpr);


			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
			synchronized(teacher){
				//Will be good when the teacher loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				teacherDaoOf(userContext).planToRemoveStudentHealthSurveyList(teacher, studentHealthSurveyIds, allTokens());
				teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
				deleteRelationListInGraph(userContext, teacher.getStudentHealthSurveyList());
				return present(userContext,teacher, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentHealthSurvey(HealthUserContext userContext, String teacherId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeStudentHealthSurvey(HealthUserContext userContext, String teacherId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentHealthSurvey(userContext,teacherId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.removeStudentHealthSurvey( studentHealthSurvey );
			teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
			deleteRelationInGraph(userContext, studentHealthSurvey);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentHealthSurvey(HealthUserContext userContext, String teacherId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher copyStudentHealthSurveyFrom(HealthUserContext userContext, String teacherId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentHealthSurvey(userContext,teacherId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentHealthSurvey.updateLastUpdateTime(userContext.now());

			teacher.copyStudentHealthSurveyFrom( studentHealthSurvey );
			teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, (StudentHealthSurvey)teacher.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		

		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}

	public  Teacher updateStudentHealthSurvey(HealthUserContext userContext, String teacherId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentHealthSurvey(userContext, teacherId, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentHealthSurveyList().searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "eq", studentHealthSurveyId).done();



		Teacher teacher = loadTeacher(userContext, teacherId, loadTokens);

		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//teacher.removeStudentHealthSurvey( studentHealthSurvey );
			//make changes to AcceleraterAccount.
			StudentHealthSurvey studentHealthSurveyIndex = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);

			StudentHealthSurvey studentHealthSurvey = teacher.findTheStudentHealthSurvey(studentHealthSurveyIndex);
			if(studentHealthSurvey == null){
				throw new TeacherManagerException(studentHealthSurvey+" is NOT FOUND" );
			}

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			teacher = saveTeacher(userContext, teacher, tokens().withStudentHealthSurveyList().done());
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}
	/*
	public  Teacher associateStudentHealthSurveyListToNewChangeRequest(HealthUserContext userContext, String teacherId, String  studentHealthSurveyIds[], String name, String requestTypeId, String platformId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "oneof", this.joinArray("|", studentHealthSurveyIds)).done();

		Teacher teacher = loadTeacher(userContext, teacherId, options);

		ChangeRequest changeRequest = changeRequestManagerOf(userContext).createChangeRequest(userContext,  name, requestTypeId, platformId);

		for(StudentHealthSurvey studentHealthSurvey: teacher.getStudentHealthSurveyList()) {
			//TODO: need to check if already associated
			studentHealthSurvey.updateChangeRequest(changeRequest);
		}
		return this.internalSaveTeacher(userContext, teacher);
	}
	*/

	public  Teacher associateStudentHealthSurveyListToChangeRequest(HealthUserContext userContext, String teacherId, String  studentHealthSurveyIds[], String changeRequestId, String [] tokensExpr) throws Exception {



		Map<String, Object> options = tokens()
				.allTokens()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "oneof", this.joinArray("|", studentHealthSurveyIds)).done();

		Teacher teacher = loadTeacher(userContext, teacherId, options);

		ChangeRequest changeRequest = changeRequestManagerOf(userContext).loadChangeRequest(userContext,changeRequestId,new String[]{"none"} );

		for(StudentHealthSurvey studentHealthSurvey: teacher.getStudentHealthSurveyList()) {
			//TODO: need to check if already associated
			studentHealthSurvey.updateChangeRequest(changeRequest);
		}
		return this.internalSaveTeacher(userContext, teacher);
	}


	protected void checkParamsForAddingHealthSurveyReport(HealthUserContext userContext, String teacherId, String surveyName, DateTime surveyTime, String teacherName, String school, String schoolClass, String studentName, String studentNumber, String guardianName, String guardianMobile, String studentId, String surveyId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTeacher(teacherId);

		
		checkerOf(userContext).checkSurveyNameOfHealthSurveyReport(surveyName);
		
		checkerOf(userContext).checkSurveyTimeOfHealthSurveyReport(surveyTime);
		
		checkerOf(userContext).checkTeacherNameOfHealthSurveyReport(teacherName);
		
		checkerOf(userContext).checkSchoolOfHealthSurveyReport(school);
		
		checkerOf(userContext).checkSchoolClassOfHealthSurveyReport(schoolClass);
		
		checkerOf(userContext).checkStudentNameOfHealthSurveyReport(studentName);
		
		checkerOf(userContext).checkStudentNumberOfHealthSurveyReport(studentNumber);
		
		checkerOf(userContext).checkGuardianNameOfHealthSurveyReport(guardianName);
		
		checkerOf(userContext).checkGuardianMobileOfHealthSurveyReport(guardianMobile);
		
		checkerOf(userContext).checkStudentIdOfHealthSurveyReport(studentId);
		
		checkerOf(userContext).checkSurveyIdOfHealthSurveyReport(surveyId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


	}
	public  Teacher addHealthSurveyReport(HealthUserContext userContext, String teacherId, String surveyName, DateTime surveyTime, String teacherName, String school, String schoolClass, String studentName, String studentNumber, String guardianName, String guardianMobile, String studentId, String surveyId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingHealthSurveyReport(userContext,teacherId,surveyName, surveyTime, teacherName, school, schoolClass, studentName, studentNumber, guardianName, guardianMobile, studentId, surveyId,tokensExpr);

		HealthSurveyReport healthSurveyReport = createHealthSurveyReport(userContext,surveyName, surveyTime, teacherName, school, schoolClass, studentName, studentNumber, guardianName, guardianMobile, studentId, surveyId);

		Teacher teacher = loadTeacher(userContext, teacherId, emptyOptions());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.addHealthSurveyReport( healthSurveyReport );
			teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
			
			userContext.getManagerGroup().getHealthSurveyReportManager().onNewInstanceCreated(userContext, healthSurveyReport);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingHealthSurveyReportProperties(HealthUserContext userContext, String teacherId,String id,String surveyName,DateTime surveyTime,String teacherName,String school,String schoolClass,String studentName,String studentNumber,String guardianName,String guardianMobile,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfHealthSurveyReport(id);

		checkerOf(userContext).checkSurveyNameOfHealthSurveyReport( surveyName);
		checkerOf(userContext).checkSurveyTimeOfHealthSurveyReport( surveyTime);
		checkerOf(userContext).checkTeacherNameOfHealthSurveyReport( teacherName);
		checkerOf(userContext).checkSchoolOfHealthSurveyReport( school);
		checkerOf(userContext).checkSchoolClassOfHealthSurveyReport( schoolClass);
		checkerOf(userContext).checkStudentNameOfHealthSurveyReport( studentName);
		checkerOf(userContext).checkStudentNumberOfHealthSurveyReport( studentNumber);
		checkerOf(userContext).checkGuardianNameOfHealthSurveyReport( guardianName);
		checkerOf(userContext).checkGuardianMobileOfHealthSurveyReport( guardianMobile);

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher updateHealthSurveyReportProperties(HealthUserContext userContext, String teacherId, String id,String surveyName,DateTime surveyTime,String teacherName,String school,String schoolClass,String studentName,String studentNumber,String guardianName,String guardianMobile, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingHealthSurveyReportProperties(userContext,teacherId,id,surveyName,surveyTime,teacherName,school,schoolClass,studentName,studentNumber,guardianName,guardianMobile,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withHealthSurveyReportListList()
				.searchHealthSurveyReportListWith(HealthSurveyReport.ID_PROPERTY, "is", id).done();

		Teacher teacherToUpdate = loadTeacher(userContext, teacherId, options);

		if(teacherToUpdate.getHealthSurveyReportList().isEmpty()){
			throw new TeacherManagerException("HealthSurveyReport is NOT FOUND with id: '"+id+"'");
		}

		HealthSurveyReport item = teacherToUpdate.getHealthSurveyReportList().first();

		item.updateSurveyName( surveyName );
		item.updateSurveyTime( surveyTime );
		item.updateTeacherName( teacherName );
		item.updateSchool( school );
		item.updateSchoolClass( schoolClass );
		item.updateStudentName( studentName );
		item.updateStudentNumber( studentNumber );
		item.updateGuardianName( guardianName );
		item.updateGuardianMobile( guardianMobile );


		//checkParamsForAddingHealthSurveyReport(userContext,teacherId,name, code, used,tokensExpr);
		Teacher teacher = saveTeacher(userContext, teacherToUpdate, tokens().withHealthSurveyReportList().done());
		synchronized(teacher){
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}


	protected HealthSurveyReport createHealthSurveyReport(HealthUserContext userContext, String surveyName, DateTime surveyTime, String teacherName, String school, String schoolClass, String studentName, String studentNumber, String guardianName, String guardianMobile, String studentId, String surveyId) throws Exception{

		HealthSurveyReport healthSurveyReport = new HealthSurveyReport();
		
		
		healthSurveyReport.setSurveyName(surveyName);		
		healthSurveyReport.setSurveyTime(surveyTime);		
		healthSurveyReport.setTeacherName(teacherName);		
		healthSurveyReport.setSchool(school);		
		healthSurveyReport.setSchoolClass(schoolClass);		
		healthSurveyReport.setStudentName(studentName);		
		healthSurveyReport.setStudentNumber(studentNumber);		
		healthSurveyReport.setGuardianName(guardianName);		
		healthSurveyReport.setGuardianMobile(guardianMobile);		
		Student  student = new Student();
		student.setId(studentId);		
		healthSurveyReport.setStudent(student);		
		ClassDailyHealthSurvey  survey = new ClassDailyHealthSurvey();
		survey.setId(surveyId);		
		healthSurveyReport.setSurvey(survey);
	
		
		return healthSurveyReport;


	}

	protected HealthSurveyReport createIndexedHealthSurveyReport(String id, int version){

		HealthSurveyReport healthSurveyReport = new HealthSurveyReport();
		healthSurveyReport.setId(id);
		healthSurveyReport.setVersion(version);
		return healthSurveyReport;

	}

	protected void checkParamsForRemovingHealthSurveyReportList(HealthUserContext userContext, String teacherId,
			String healthSurveyReportIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		for(String healthSurveyReportIdItem: healthSurveyReportIds){
			checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeHealthSurveyReportList(HealthUserContext userContext, String teacherId,
			String healthSurveyReportIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingHealthSurveyReportList(userContext, teacherId,  healthSurveyReportIds, tokensExpr);


			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
			synchronized(teacher){
				//Will be good when the teacher loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				teacherDaoOf(userContext).planToRemoveHealthSurveyReportList(teacher, healthSurveyReportIds, allTokens());
				teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
				deleteRelationListInGraph(userContext, teacher.getHealthSurveyReportList());
				return present(userContext,teacher, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingHealthSurveyReport(HealthUserContext userContext, String teacherId,
		String healthSurveyReportId, int healthSurveyReportVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).checkVersionOfHealthSurveyReport(healthSurveyReportVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeHealthSurveyReport(HealthUserContext userContext, String teacherId,
		String healthSurveyReportId, int healthSurveyReportVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingHealthSurveyReport(userContext,teacherId, healthSurveyReportId, healthSurveyReportVersion,tokensExpr);

		HealthSurveyReport healthSurveyReport = createIndexedHealthSurveyReport(healthSurveyReportId, healthSurveyReportVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.removeHealthSurveyReport( healthSurveyReport );
			teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
			deleteRelationInGraph(userContext, healthSurveyReport);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingHealthSurveyReport(HealthUserContext userContext, String teacherId,
		String healthSurveyReportId, int healthSurveyReportVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).checkVersionOfHealthSurveyReport(healthSurveyReportVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher copyHealthSurveyReportFrom(HealthUserContext userContext, String teacherId,
		String healthSurveyReportId, int healthSurveyReportVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingHealthSurveyReport(userContext,teacherId, healthSurveyReportId, healthSurveyReportVersion,tokensExpr);

		HealthSurveyReport healthSurveyReport = createIndexedHealthSurveyReport(healthSurveyReportId, healthSurveyReportVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			teacher.copyHealthSurveyReportFrom( healthSurveyReport );
			teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
			
			userContext.getManagerGroup().getHealthSurveyReportManager().onNewInstanceCreated(userContext, (HealthSurveyReport)teacher.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingHealthSurveyReport(HealthUserContext userContext, String teacherId, String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfHealthSurveyReport(healthSurveyReportId);
		checkerOf(userContext).checkVersionOfHealthSurveyReport(healthSurveyReportVersion);
		

		if(HealthSurveyReport.SURVEY_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSurveyNameOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.SURVEY_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSurveyTimeOfHealthSurveyReport(parseTimestamp(newValueExpr));
		
		}
		
		if(HealthSurveyReport.TEACHER_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTeacherNameOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.SCHOOL_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSchoolOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.SCHOOL_CLASS_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSchoolClassOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.STUDENT_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkStudentNameOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.STUDENT_NUMBER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkStudentNumberOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.GUARDIAN_NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkGuardianNameOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
		if(HealthSurveyReport.GUARDIAN_MOBILE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkGuardianMobileOfHealthSurveyReport(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}

	public  Teacher updateHealthSurveyReport(HealthUserContext userContext, String teacherId, String healthSurveyReportId, int healthSurveyReportVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingHealthSurveyReport(userContext, teacherId, healthSurveyReportId, healthSurveyReportVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withHealthSurveyReportList().searchHealthSurveyReportListWith(HealthSurveyReport.ID_PROPERTY, "eq", healthSurveyReportId).done();



		Teacher teacher = loadTeacher(userContext, teacherId, loadTokens);

		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//teacher.removeHealthSurveyReport( healthSurveyReport );
			//make changes to AcceleraterAccount.
			HealthSurveyReport healthSurveyReportIndex = createIndexedHealthSurveyReport(healthSurveyReportId, healthSurveyReportVersion);

			HealthSurveyReport healthSurveyReport = teacher.findTheHealthSurveyReport(healthSurveyReportIndex);
			if(healthSurveyReport == null){
				throw new TeacherManagerException(healthSurveyReport+" is NOT FOUND" );
			}

			healthSurveyReport.changeProperty(property, newValueExpr);
			
			teacher = saveTeacher(userContext, teacher, tokens().withHealthSurveyReportList().done());
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, Teacher newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Teacher.INTERNAL_TYPE);
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


