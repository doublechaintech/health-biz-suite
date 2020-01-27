
package com.doublechaintech.health.schoolclass;

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
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.changerequest.CandidateChangeRequest;
import com.doublechaintech.health.teacher.CandidateTeacher;

import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.wechatuser.WechatUser;






public class SchoolClassManagerImpl extends CustomHealthCheckerManager implements SchoolClassManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "SchoolClass";
	@Override
	public SchoolClassDAO daoOf(HealthUserContext userContext) {
		return schoolClassDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws SchoolClassManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new SchoolClassManagerException(message);

	}



 	protected SchoolClass saveSchoolClass(HealthUserContext userContext, SchoolClass schoolClass, String [] tokensExpr) throws Exception{	
 		//return getSchoolClassDAO().save(schoolClass, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSchoolClass(userContext, schoolClass, tokens);
 	}
 	
 	protected SchoolClass saveSchoolClassDetail(HealthUserContext userContext, SchoolClass schoolClass) throws Exception{	

 		
 		return saveSchoolClass(userContext, schoolClass, allTokens());
 	}
 	
 	public SchoolClass loadSchoolClass(HealthUserContext userContext, String schoolClassId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).throwExceptionIfHasErrors( SchoolClassManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SchoolClass schoolClass = loadSchoolClass( userContext, schoolClassId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,schoolClass, tokens);
 	}
 	
 	
 	 public SchoolClass searchSchoolClass(HealthUserContext userContext, String schoolClassId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).throwExceptionIfHasErrors( SchoolClassManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SchoolClass schoolClass = loadSchoolClass( userContext, schoolClassId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,schoolClass, tokens);
 	}
 	
 	

 	protected SchoolClass present(HealthUserContext userContext, SchoolClass schoolClass, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,schoolClass,tokens);
		
		
		SchoolClass  schoolClassToPresent = schoolClassDaoOf(userContext).present(schoolClass, tokens);
		
		List<BaseEntity> entityListToNaming = schoolClassToPresent.collectRefercencesFromLists();
		schoolClassDaoOf(userContext).alias(entityListToNaming);
		
		return  schoolClassToPresent;
		
		
	}
 
 	
 	
 	public SchoolClass loadSchoolClassDetail(HealthUserContext userContext, String schoolClassId) throws Exception{	
 		SchoolClass schoolClass = loadSchoolClass( userContext, schoolClassId, allTokens());
 		return present(userContext,schoolClass, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String schoolClassId) throws Exception{	
 		SchoolClass schoolClass = loadSchoolClass( userContext, schoolClassId, viewTokens());
 		return present(userContext,schoolClass, allTokens());
		
 	}
 	protected SchoolClass saveSchoolClass(HealthUserContext userContext, SchoolClass schoolClass, Map<String,Object>tokens) throws Exception{	
 		return schoolClassDaoOf(userContext).save(schoolClass, tokens);
 	}
 	protected SchoolClass loadSchoolClass(HealthUserContext userContext, String schoolClassId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).throwExceptionIfHasErrors( SchoolClassManagerException.class);

 
 		return schoolClassDaoOf(userContext).load(schoolClassId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, SchoolClass schoolClass, Map<String, Object> tokens){
		super.addActions(userContext, schoolClass, tokens);
		
		addAction(userContext, schoolClass, tokens,"@create","createSchoolClass","createSchoolClass/","main","primary");
		addAction(userContext, schoolClass, tokens,"@update","updateSchoolClass","updateSchoolClass/"+schoolClass.getId()+"/","main","primary");
		addAction(userContext, schoolClass, tokens,"@copy","cloneSchoolClass","cloneSchoolClass/"+schoolClass.getId()+"/","main","primary");
		
		addAction(userContext, schoolClass, tokens,"school_class.transfer_to_class_teacher","transferToAnotherClassTeacher","transferToAnotherClassTeacher/"+schoolClass.getId()+"/","main","primary");
		addAction(userContext, schoolClass, tokens,"school_class.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+schoolClass.getId()+"/","main","primary");
		addAction(userContext, schoolClass, tokens,"school_class.transfer_to_cq","transferToAnotherCq","transferToAnotherCq/"+schoolClass.getId()+"/","main","primary");
		addAction(userContext, schoolClass, tokens,"school_class.addClassDailyHealthSurvey","addClassDailyHealthSurvey","addClassDailyHealthSurvey/"+schoolClass.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.removeClassDailyHealthSurvey","removeClassDailyHealthSurvey","removeClassDailyHealthSurvey/"+schoolClass.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.updateClassDailyHealthSurvey","updateClassDailyHealthSurvey","updateClassDailyHealthSurvey/"+schoolClass.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom","copyClassDailyHealthSurveyFrom/"+schoolClass.getId()+"/","classDailyHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.addStudent","addStudent","addStudent/"+schoolClass.getId()+"/","studentList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.removeStudent","removeStudent","removeStudent/"+schoolClass.getId()+"/","studentList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.updateStudent","updateStudent","updateStudent/"+schoolClass.getId()+"/","studentList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.copyStudentFrom","copyStudentFrom","copyStudentFrom/"+schoolClass.getId()+"/","studentList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.addStudentHealthSurvey","addStudentHealthSurvey","addStudentHealthSurvey/"+schoolClass.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.removeStudentHealthSurvey","removeStudentHealthSurvey","removeStudentHealthSurvey/"+schoolClass.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.updateStudentHealthSurvey","updateStudentHealthSurvey","updateStudentHealthSurvey/"+schoolClass.getId()+"/","studentHealthSurveyList","primary");
		addAction(userContext, schoolClass, tokens,"school_class.copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom","copyStudentHealthSurveyFrom/"+schoolClass.getId()+"/","studentHealthSurveyList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, SchoolClass schoolClass, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public SchoolClass createSchoolClass(HealthUserContext userContext, String name,String classTeacherId,String platformId,String schoole,String cqId) throws Exception
	//public SchoolClass createSchoolClass(HealthUserContext userContext,String name, String classTeacherId, String platformId, String schoole, String cqId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfSchoolClass(name);
		checkerOf(userContext).checkSchooleOfSchoolClass(schoole);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);


		SchoolClass schoolClass=createNewSchoolClass();	

		schoolClass.setName(name);
			
		Teacher classTeacher = loadTeacher(userContext, classTeacherId,emptyOptions());
		schoolClass.setClassTeacher(classTeacher);
		
		
		schoolClass.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		schoolClass.setPlatform(platform);
		
		
		schoolClass.setSchoole(schoole);
			
		ChangeRequest cq = loadChangeRequest(userContext, cqId,emptyOptions());
		schoolClass.setCq(cq);
		
		

		schoolClass = saveSchoolClass(userContext, schoolClass, emptyOptions());
		
		onNewInstanceCreated(userContext, schoolClass);
		return schoolClass;


	}
	protected SchoolClass createNewSchoolClass()
	{

		return new SchoolClass();
	}

	protected void checkParamsForUpdatingSchoolClass(HealthUserContext userContext,String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass( schoolClassVersion);
		

		if(SchoolClass.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfSchoolClass(parseString(newValueExpr));
		}		

				

		
		if(SchoolClass.SCHOOLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchooleOfSchoolClass(parseString(newValueExpr));
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);


	}



	public SchoolClass clone(HealthUserContext userContext, String fromSchoolClassId) throws Exception{

		return schoolClassDaoOf(userContext).clone(fromSchoolClassId, this.allTokens());
	}

	public SchoolClass internalSaveSchoolClass(HealthUserContext userContext, SchoolClass schoolClass) throws Exception
	{
		return internalSaveSchoolClass(userContext, schoolClass, allTokens());

	}
	public SchoolClass internalSaveSchoolClass(HealthUserContext userContext, SchoolClass schoolClass, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingSchoolClass(userContext, schoolClassId, schoolClassVersion, property, newValueExpr, tokensExpr);


		synchronized(schoolClass){
			//will be good when the schoolClass loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SchoolClass.
			if (schoolClass.isChanged()){
			
			}
			schoolClass = saveSchoolClass(userContext, schoolClass, options);
			return schoolClass;

		}

	}

	public SchoolClass updateSchoolClass(HealthUserContext userContext,String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSchoolClass(userContext, schoolClassId, schoolClassVersion, property, newValueExpr, tokensExpr);



		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		if(schoolClass.getVersion() != schoolClassVersion){
			String message = "The target version("+schoolClass.getVersion()+") is not equals to version("+schoolClassVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(schoolClass){
			//will be good when the schoolClass loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SchoolClass.
			
			schoolClass.changeProperty(property, newValueExpr);
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().done());
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
			//return saveSchoolClass(userContext, schoolClass, tokens().done());
		}

	}

	public SchoolClass updateSchoolClassProperty(HealthUserContext userContext,String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSchoolClass(userContext, schoolClassId, schoolClassVersion, property, newValueExpr, tokensExpr);

		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		if(schoolClass.getVersion() != schoolClassVersion){
			String message = "The target version("+schoolClass.getVersion()+") is not equals to version("+schoolClassVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(schoolClass){
			//will be good when the schoolClass loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SchoolClass.

			schoolClass.changeProperty(property, newValueExpr);
			
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().done());
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
			//return saveSchoolClass(userContext, schoolClass, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected SchoolClassTokens tokens(){
		return SchoolClassTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SchoolClassTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortClassDailyHealthSurveyListWith("id","desc")
		.sortStudentListWith("id","desc")
		.sortStudentHealthSurveyListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SchoolClassTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherClassTeacher(HealthUserContext userContext, String schoolClassId, String anotherClassTeacherId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
 		checkerOf(userContext).checkIdOfTeacher(anotherClassTeacherId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

 	}
 	public SchoolClass transferToAnotherClassTeacher(HealthUserContext userContext, String schoolClassId, String anotherClassTeacherId) throws Exception
 	{
 		checkParamsForTransferingAnotherClassTeacher(userContext, schoolClassId,anotherClassTeacherId);
 
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());	
		synchronized(schoolClass){
			//will be good when the schoolClass loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Teacher classTeacher = loadTeacher(userContext, anotherClassTeacherId, emptyOptions());		
			schoolClass.updateClassTeacher(classTeacher);		
			schoolClass = saveSchoolClass(userContext, schoolClass, emptyOptions());
			
			return present(userContext,schoolClass, allTokens());
			
		}

 	}

	


	public CandidateTeacher requestCandidateClassTeacher(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateTeacher result = new CandidateTeacher();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Teacher> candidateList = teacherDaoOf(userContext).requestCandidateTeacherForSchoolClass(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String schoolClassId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

 	}
 	public SchoolClass transferToAnotherPlatform(HealthUserContext userContext, String schoolClassId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, schoolClassId,anotherPlatformId);
 
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());	
		synchronized(schoolClass){
			//will be good when the schoolClass loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			schoolClass.updatePlatform(platform);		
			schoolClass = saveSchoolClass(userContext, schoolClass, emptyOptions());
			
			return present(userContext,schoolClass, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForSchoolClass(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherCq(HealthUserContext userContext, String schoolClassId, String anotherCqId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherCqId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

 	}
 	public SchoolClass transferToAnotherCq(HealthUserContext userContext, String schoolClassId, String anotherCqId) throws Exception
 	{
 		checkParamsForTransferingAnotherCq(userContext, schoolClassId,anotherCqId);
 
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());	
		synchronized(schoolClass){
			//will be good when the schoolClass loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest cq = loadChangeRequest(userContext, anotherCqId, emptyOptions());		
			schoolClass.updateCq(cq);		
			schoolClass = saveSchoolClass(userContext, schoolClass, emptyOptions());
			
			return present(userContext,schoolClass, allTokens());
			
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
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForSchoolClass(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Teacher loadTeacher(HealthUserContext userContext, String newClassTeacherId, Map<String,Object> options) throws Exception
 	{

 		return teacherDaoOf(userContext).load(newClassTeacherId, options);
 	}
 	


	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newCqId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newCqId, options);
 	}
 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String schoolClassId, int schoolClassVersion) throws Exception {
		//deleteInternal(userContext, schoolClassId, schoolClassVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String schoolClassId, int schoolClassVersion) throws Exception{

		schoolClassDaoOf(userContext).delete(schoolClassId, schoolClassVersion);
	}

	public SchoolClass forgetByAll(HealthUserContext userContext, String schoolClassId, int schoolClassVersion) throws Exception {
		return forgetByAllInternal(userContext, schoolClassId, schoolClassVersion);
	}
	protected SchoolClass forgetByAllInternal(HealthUserContext userContext,
			String schoolClassId, int schoolClassVersion) throws Exception{

		return schoolClassDaoOf(userContext).disconnectFromAll(schoolClassId, schoolClassVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SchoolClassManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return schoolClassDaoOf(userContext).deleteAll();
	}


	//disconnect SchoolClass with creator in ClassDailyHealthSurvey
	protected SchoolClass breakWithClassDailyHealthSurveyByCreator(HealthUserContext userContext, String schoolClassId, String creatorId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithCreator(schoolClass, creatorId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with cq in ClassDailyHealthSurvey
	protected SchoolClass breakWithClassDailyHealthSurveyByCq(HealthUserContext userContext, String schoolClassId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveClassDailyHealthSurveyListWithCq(schoolClass, cqId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with guardian in Student
	protected SchoolClass breakWithStudentByGuardian(HealthUserContext userContext, String schoolClassId, String guardianId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentListWithGuardian(schoolClass, guardianId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with student_id in Student
	protected SchoolClass breakWithStudentByStudentId(HealthUserContext userContext, String schoolClassId, String studentIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentListWithStudentId(schoolClass, studentIdId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with cq in Student
	protected SchoolClass breakWithStudentByCq(HealthUserContext userContext, String schoolClassId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentListWithCq(schoolClass, cqId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with student in StudentHealthSurvey
	protected SchoolClass breakWithStudentHealthSurveyByStudent(HealthUserContext userContext, String schoolClassId, String studentId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentHealthSurveyListWithStudent(schoolClass, studentId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with survey_status in StudentHealthSurvey
	protected SchoolClass breakWithStudentHealthSurveyBySurveyStatus(HealthUserContext userContext, String schoolClassId, String surveyStatusId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentHealthSurveyListWithSurveyStatus(schoolClass, surveyStatusId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with class_daily_health_survey in StudentHealthSurvey
	protected SchoolClass breakWithStudentHealthSurveyByClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String classDailyHealthSurveyId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(schoolClass, classDailyHealthSurveyId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
				return schoolClass;
			}
	}
	//disconnect SchoolClass with cq in StudentHealthSurvey
	protected SchoolClass breakWithStudentHealthSurveyByCq(HealthUserContext userContext, String schoolClassId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());

			synchronized(schoolClass){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				schoolClassDaoOf(userContext).planToRemoveStudentHealthSurveyListWithCq(schoolClass, cqId, this.emptyOptions());

				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
				return schoolClass;
			}
	}






	protected void checkParamsForAddingClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String name, DateTime surveyTime, String creatorId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);

		
		checkerOf(userContext).checkNameOfClassDailyHealthSurvey(name);
		
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(surveyTime);
		
		checkerOf(userContext).checkCreatorIdOfClassDailyHealthSurvey(creatorId);
		
		checkerOf(userContext).checkCqIdOfClassDailyHealthSurvey(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);


	}
	public  SchoolClass addClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String name, DateTime surveyTime, String creatorId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingClassDailyHealthSurvey(userContext,schoolClassId,name, surveyTime, creatorId, cqId,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createClassDailyHealthSurvey(userContext,name, surveyTime, creatorId, cqId);

		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, emptyOptions());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			schoolClass.addClassDailyHealthSurvey( classDailyHealthSurvey );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, classDailyHealthSurvey);
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingClassDailyHealthSurveyProperties(HealthUserContext userContext, String schoolClassId,String id,String name,DateTime surveyTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(id);

		checkerOf(userContext).checkNameOfClassDailyHealthSurvey( name);
		checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey( surveyTime);

		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass updateClassDailyHealthSurveyProperties(HealthUserContext userContext, String schoolClassId, String id,String name,DateTime surveyTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingClassDailyHealthSurveyProperties(userContext,schoolClassId,id,name,surveyTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withClassDailyHealthSurveyListList()
				.searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "is", id).done();

		SchoolClass schoolClassToUpdate = loadSchoolClass(userContext, schoolClassId, options);

		if(schoolClassToUpdate.getClassDailyHealthSurveyList().isEmpty()){
			throw new SchoolClassManagerException("ClassDailyHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		ClassDailyHealthSurvey item = schoolClassToUpdate.getClassDailyHealthSurveyList().first();

		item.updateName( name );
		item.updateSurveyTime( surveyTime );


		//checkParamsForAddingClassDailyHealthSurvey(userContext,schoolClassId,name, code, used,tokensExpr);
		SchoolClass schoolClass = saveSchoolClass(userContext, schoolClassToUpdate, tokens().withClassDailyHealthSurveyList().done());
		synchronized(schoolClass){
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}
	}


	protected ClassDailyHealthSurvey createClassDailyHealthSurvey(HealthUserContext userContext, String name, DateTime surveyTime, String creatorId, String cqId) throws Exception{

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		
		
		classDailyHealthSurvey.setName(name);		
		classDailyHealthSurvey.setSurveyTime(surveyTime);		
		WechatUser  creator = new WechatUser();
		creator.setId(creatorId);		
		classDailyHealthSurvey.setCreator(creator);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		classDailyHealthSurvey.setCq(cq);
	
		
		return classDailyHealthSurvey;


	}

	protected ClassDailyHealthSurvey createIndexedClassDailyHealthSurvey(String id, int version){

		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(id);
		classDailyHealthSurvey.setVersion(version);
		return classDailyHealthSurvey;

	}

	protected void checkParamsForRemovingClassDailyHealthSurveyList(HealthUserContext userContext, String schoolClassId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		for(String classDailyHealthSurveyIdItem: classDailyHealthSurveyIds){
			checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass removeClassDailyHealthSurveyList(HealthUserContext userContext, String schoolClassId,
			String classDailyHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingClassDailyHealthSurveyList(userContext, schoolClassId,  classDailyHealthSurveyIds, tokensExpr);


			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
			synchronized(schoolClass){
				//Will be good when the schoolClass loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				schoolClassDaoOf(userContext).planToRemoveClassDailyHealthSurveyList(schoolClass, classDailyHealthSurveyIds, allTokens());
				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
				deleteRelationListInGraph(userContext, schoolClass.getClassDailyHealthSurveyList());
				return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSchoolClass( schoolClassId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass removeClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingClassDailyHealthSurvey(userContext,schoolClassId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			schoolClass.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
			deleteRelationInGraph(userContext, classDailyHealthSurvey);
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSchoolClass( schoolClassId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass copyClassDailyHealthSurveyFrom(HealthUserContext userContext, String schoolClassId,
		String classDailyHealthSurveyId, int classDailyHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingClassDailyHealthSurvey(userContext,schoolClassId, classDailyHealthSurveyId, classDailyHealthSurveyVersion,tokensExpr);

		ClassDailyHealthSurvey classDailyHealthSurvey = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			schoolClass.copyClassDailyHealthSurveyFrom( classDailyHealthSurvey );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
			
			userContext.getManagerGroup().getClassDailyHealthSurveyManager().onNewInstanceCreated(userContext, (ClassDailyHealthSurvey)schoolClass.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkIdOfClassDailyHealthSurvey(classDailyHealthSurveyId);
		checkerOf(userContext).checkVersionOfClassDailyHealthSurvey(classDailyHealthSurveyVersion);
		

		if(ClassDailyHealthSurvey.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfClassDailyHealthSurvey(parseString(newValueExpr));
		}
		
		if(ClassDailyHealthSurvey.SURVEY_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkSurveyTimeOfClassDailyHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}

	public  SchoolClass updateClassDailyHealthSurvey(HealthUserContext userContext, String schoolClassId, String classDailyHealthSurveyId, int classDailyHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingClassDailyHealthSurvey(userContext, schoolClassId, classDailyHealthSurveyId, classDailyHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withClassDailyHealthSurveyList().searchClassDailyHealthSurveyListWith(ClassDailyHealthSurvey.ID_PROPERTY, "eq", classDailyHealthSurveyId).done();



		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, loadTokens);

		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//schoolClass.removeClassDailyHealthSurvey( classDailyHealthSurvey );
			//make changes to AcceleraterAccount.
			ClassDailyHealthSurvey classDailyHealthSurveyIndex = createIndexedClassDailyHealthSurvey(classDailyHealthSurveyId, classDailyHealthSurveyVersion);

			ClassDailyHealthSurvey classDailyHealthSurvey = schoolClass.findTheClassDailyHealthSurvey(classDailyHealthSurveyIndex);
			if(classDailyHealthSurvey == null){
				throw new SchoolClassManagerException(classDailyHealthSurvey+" is NOT FOUND" );
			}

			classDailyHealthSurvey.changeProperty(property, newValueExpr);
			
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withClassDailyHealthSurveyList().done());
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingStudent(HealthUserContext userContext, String schoolClassId, String name, String gender, String guardianId, String studentId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);

		
		checkerOf(userContext).checkNameOfStudent(name);
		
		checkerOf(userContext).checkGenderOfStudent(gender);
		
		checkerOf(userContext).checkGuardianIdOfStudent(guardianId);
		
		checkerOf(userContext).checkStudentIdOfStudent(studentId);
		
		checkerOf(userContext).checkCqIdOfStudent(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);


	}
	public  SchoolClass addStudent(HealthUserContext userContext, String schoolClassId, String name, String gender, String guardianId, String studentId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudent(userContext,schoolClassId,name, gender, guardianId, studentId, cqId,tokensExpr);

		Student student = createStudent(userContext,name, gender, guardianId, studentId, cqId);

		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, emptyOptions());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			schoolClass.addStudent( student );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, student);
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentProperties(HealthUserContext userContext, String schoolClassId,String id,String name,String gender,String studentId,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkIdOfStudent(id);

		checkerOf(userContext).checkNameOfStudent( name);
		checkerOf(userContext).checkGenderOfStudent( gender);
		checkerOf(userContext).checkStudentIdOfStudent( studentId);

		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass updateStudentProperties(HealthUserContext userContext, String schoolClassId, String id,String name,String gender,String studentId, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentProperties(userContext,schoolClassId,id,name,gender,studentId,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentListList()
				.searchStudentListWith(Student.ID_PROPERTY, "is", id).done();

		SchoolClass schoolClassToUpdate = loadSchoolClass(userContext, schoolClassId, options);

		if(schoolClassToUpdate.getStudentList().isEmpty()){
			throw new SchoolClassManagerException("Student is NOT FOUND with id: '"+id+"'");
		}

		Student item = schoolClassToUpdate.getStudentList().first();

		item.updateName( name );
		item.updateGender( gender );
		item.updateStudentId( studentId );


		//checkParamsForAddingStudent(userContext,schoolClassId,name, code, used,tokensExpr);
		SchoolClass schoolClass = saveSchoolClass(userContext, schoolClassToUpdate, tokens().withStudentList().done());
		synchronized(schoolClass){
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}
	}


	protected Student createStudent(HealthUserContext userContext, String name, String gender, String guardianId, String studentId, String cqId) throws Exception{

		Student student = new Student();
		
		
		student.setName(name);		
		student.setGender(gender);		
		Guardian  guardian = new Guardian();
		guardian.setId(guardianId);		
		student.setGuardian(guardian);		
		student.setStudentId(studentId);		
		ChangeRequest  cq = new ChangeRequest();
		cq.setId(cqId);		
		student.setCq(cq);
	
		
		return student;


	}

	protected Student createIndexedStudent(String id, int version){

		Student student = new Student();
		student.setId(id);
		student.setVersion(version);
		return student;

	}

	protected void checkParamsForRemovingStudentList(HealthUserContext userContext, String schoolClassId,
			String studentIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		for(String studentIdItem: studentIds){
			checkerOf(userContext).checkIdOfStudent(studentIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass removeStudentList(HealthUserContext userContext, String schoolClassId,
			String studentIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentList(userContext, schoolClassId,  studentIds, tokensExpr);


			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
			synchronized(schoolClass){
				//Will be good when the schoolClass loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				schoolClassDaoOf(userContext).planToRemoveStudentList(schoolClass, studentIds, allTokens());
				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
				deleteRelationListInGraph(userContext, schoolClass.getStudentList());
				return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudent(HealthUserContext userContext, String schoolClassId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSchoolClass( schoolClassId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass removeStudent(HealthUserContext userContext, String schoolClassId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudent(userContext,schoolClassId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			schoolClass.removeStudent( student );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
			deleteRelationInGraph(userContext, student);
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudent(HealthUserContext userContext, String schoolClassId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSchoolClass( schoolClassId);
		checkerOf(userContext).checkIdOfStudent(studentId);
		checkerOf(userContext).checkVersionOfStudent(studentVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass copyStudentFrom(HealthUserContext userContext, String schoolClassId,
		String studentId, int studentVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudent(userContext,schoolClassId, studentId, studentVersion,tokensExpr);

		Student student = createIndexedStudent(studentId, studentVersion);
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			schoolClass.copyStudentFrom( student );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
			
			userContext.getManagerGroup().getStudentManager().onNewInstanceCreated(userContext, (Student)schoolClass.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudent(HealthUserContext userContext, String schoolClassId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}

	public  SchoolClass updateStudent(HealthUserContext userContext, String schoolClassId, String studentId, int studentVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudent(userContext, schoolClassId, studentId, studentVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentList().searchStudentListWith(Student.ID_PROPERTY, "eq", studentId).done();



		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, loadTokens);

		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//schoolClass.removeStudent( student );
			//make changes to AcceleraterAccount.
			Student studentIndex = createIndexedStudent(studentId, studentVersion);

			Student student = schoolClass.findTheStudent(studentIndex);
			if(student == null){
				throw new SchoolClassManagerException(student+" is NOT FOUND" );
			}

			student.changeProperty(property, newValueExpr);
			
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentList().done());
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);

		
		checkerOf(userContext).checkStudentIdOfStudentHealthSurvey(studentId);
		
		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(answerTime);
		
		checkerOf(userContext).checkSurveyStatusIdOfStudentHealthSurvey(surveyStatusId);
		
		checkerOf(userContext).checkClassDailyHealthSurveyIdOfStudentHealthSurvey(classDailyHealthSurveyId);
		
		checkerOf(userContext).checkCqIdOfStudentHealthSurvey(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);


	}
	public  SchoolClass addStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingStudentHealthSurvey(userContext,schoolClassId,studentId, answerTime, surveyStatusId, classDailyHealthSurveyId, cqId,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createStudentHealthSurvey(userContext,studentId, answerTime, surveyStatusId, classDailyHealthSurveyId, cqId);

		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, emptyOptions());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			schoolClass.addStudentHealthSurvey( studentHealthSurvey );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, studentHealthSurvey);
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingStudentHealthSurveyProperties(HealthUserContext userContext, String schoolClassId,String id,DateTime answerTime,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(id);

		checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey( answerTime);

		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass updateStudentHealthSurveyProperties(HealthUserContext userContext, String schoolClassId, String id,DateTime answerTime, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingStudentHealthSurveyProperties(userContext,schoolClassId,id,answerTime,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withStudentHealthSurveyListList()
				.searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "is", id).done();

		SchoolClass schoolClassToUpdate = loadSchoolClass(userContext, schoolClassId, options);

		if(schoolClassToUpdate.getStudentHealthSurveyList().isEmpty()){
			throw new SchoolClassManagerException("StudentHealthSurvey is NOT FOUND with id: '"+id+"'");
		}

		StudentHealthSurvey item = schoolClassToUpdate.getStudentHealthSurveyList().first();

		item.updateAnswerTime( answerTime );


		//checkParamsForAddingStudentHealthSurvey(userContext,schoolClassId,name, code, used,tokensExpr);
		SchoolClass schoolClass = saveSchoolClass(userContext, schoolClassToUpdate, tokens().withStudentHealthSurveyList().done());
		synchronized(schoolClass){
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}
	}


	protected StudentHealthSurvey createStudentHealthSurvey(HealthUserContext userContext, String studentId, DateTime answerTime, String surveyStatusId, String classDailyHealthSurveyId, String cqId) throws Exception{

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

	protected void checkParamsForRemovingStudentHealthSurveyList(HealthUserContext userContext, String schoolClassId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		for(String studentHealthSurveyIdItem: studentHealthSurveyIds){
			checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass removeStudentHealthSurveyList(HealthUserContext userContext, String schoolClassId,
			String studentHealthSurveyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingStudentHealthSurveyList(userContext, schoolClassId,  studentHealthSurveyIds, tokensExpr);


			SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
			synchronized(schoolClass){
				//Will be good when the schoolClass loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				schoolClassDaoOf(userContext).planToRemoveStudentHealthSurveyList(schoolClass, studentHealthSurveyIds, allTokens());
				schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
				deleteRelationListInGraph(userContext, schoolClass.getStudentHealthSurveyList());
				return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingStudentHealthSurvey(HealthUserContext userContext, String schoolClassId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSchoolClass( schoolClassId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass removeStudentHealthSurvey(HealthUserContext userContext, String schoolClassId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingStudentHealthSurvey(userContext,schoolClassId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			schoolClass.removeStudentHealthSurvey( studentHealthSurvey );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
			deleteRelationInGraph(userContext, studentHealthSurvey);
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingStudentHealthSurvey(HealthUserContext userContext, String schoolClassId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfSchoolClass( schoolClassId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}
	public  SchoolClass copyStudentHealthSurveyFrom(HealthUserContext userContext, String schoolClassId,
		String studentHealthSurveyId, int studentHealthSurveyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingStudentHealthSurvey(userContext,schoolClassId, studentHealthSurveyId, studentHealthSurveyVersion,tokensExpr);

		StudentHealthSurvey studentHealthSurvey = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);
		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, allTokens());
		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			studentHealthSurvey.updateLastUpdateTime(userContext.now());

			schoolClass.copyStudentHealthSurveyFrom( studentHealthSurvey );
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
			
			userContext.getManagerGroup().getStudentHealthSurveyManager().onNewInstanceCreated(userContext, (StudentHealthSurvey)schoolClass.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkIdOfStudentHealthSurvey(studentHealthSurveyId);
		checkerOf(userContext).checkVersionOfStudentHealthSurvey(studentHealthSurveyVersion);
		

		if(StudentHealthSurvey.ANSWER_TIME_PROPERTY.equals(property)){
			checkerOf(userContext).checkAnswerTimeOfStudentHealthSurvey(parseTimestamp(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SchoolClassManagerException.class);

	}

	public  SchoolClass updateStudentHealthSurvey(HealthUserContext userContext, String schoolClassId, String studentHealthSurveyId, int studentHealthSurveyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingStudentHealthSurvey(userContext, schoolClassId, studentHealthSurveyId, studentHealthSurveyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withStudentHealthSurveyList().searchStudentHealthSurveyListWith(StudentHealthSurvey.ID_PROPERTY, "eq", studentHealthSurveyId).done();



		SchoolClass schoolClass = loadSchoolClass(userContext, schoolClassId, loadTokens);

		synchronized(schoolClass){
			//Will be good when the schoolClass loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//schoolClass.removeStudentHealthSurvey( studentHealthSurvey );
			//make changes to AcceleraterAccount.
			StudentHealthSurvey studentHealthSurveyIndex = createIndexedStudentHealthSurvey(studentHealthSurveyId, studentHealthSurveyVersion);

			StudentHealthSurvey studentHealthSurvey = schoolClass.findTheStudentHealthSurvey(studentHealthSurveyIndex);
			if(studentHealthSurvey == null){
				throw new SchoolClassManagerException(studentHealthSurvey+" is NOT FOUND" );
			}

			studentHealthSurvey.changeProperty(property, newValueExpr);
			studentHealthSurvey.updateLastUpdateTime(userContext.now());
			schoolClass = saveSchoolClass(userContext, schoolClass, tokens().withStudentHealthSurveyList().done());
			return present(userContext,schoolClass, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, SchoolClass newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, SchoolClass.INTERNAL_TYPE);
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


