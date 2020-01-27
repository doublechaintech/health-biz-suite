
package com.doublechaintech.health.teacher;

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
import com.doublechaintech.health.schoolclass.SchoolClass;

import com.doublechaintech.health.platform.CandidatePlatform;
import com.doublechaintech.health.changerequest.CandidateChangeRequest;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;






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
		addAction(userContext, teacher, tokens,"teacher.transfer_to_cq","transferToAnotherCq","transferToAnotherCq/"+teacher.getId()+"/","main","primary");
		addAction(userContext, teacher, tokens,"teacher.addSchoolClass","addSchoolClass","addSchoolClass/"+teacher.getId()+"/","schoolClassList","primary");
		addAction(userContext, teacher, tokens,"teacher.removeSchoolClass","removeSchoolClass","removeSchoolClass/"+teacher.getId()+"/","schoolClassList","primary");
		addAction(userContext, teacher, tokens,"teacher.updateSchoolClass","updateSchoolClass","updateSchoolClass/"+teacher.getId()+"/","schoolClassList","primary");
		addAction(userContext, teacher, tokens,"teacher.copySchoolClassFrom","copySchoolClassFrom","copySchoolClassFrom/"+teacher.getId()+"/","schoolClassList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Teacher teacher, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Teacher createTeacher(HealthUserContext userContext, String name,String mobile,String schoole,String platformId,String cqId) throws Exception
	//public Teacher createTeacher(HealthUserContext userContext,String name, String mobile, String schoole, String platformId, String cqId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfTeacher(name);
		checkerOf(userContext).checkMobileOfTeacher(mobile);
		checkerOf(userContext).checkSchooleOfTeacher(schoole);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


		Teacher teacher=createNewTeacher();	

		teacher.setName(name);
		teacher.setMobile(mobile);
		teacher.setSchoole(schoole);
		teacher.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		teacher.setPlatform(platform);
		
		
			
		ChangeRequest cq = loadChangeRequest(userContext, cqId,emptyOptions());
		teacher.setCq(cq);
		
		

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
		if(Teacher.SCHOOLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchooleOfTeacher(parseString(newValueExpr));
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
		.sortSchoolClassListWith("id","desc")
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

 	protected void checkParamsForTransferingAnotherCq(HealthUserContext userContext, String teacherId, String anotherCqId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfTeacher(teacherId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherCqId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

 	}
 	public Teacher transferToAnotherCq(HealthUserContext userContext, String teacherId, String anotherCqId) throws Exception
 	{
 		checkParamsForTransferingAnotherCq(userContext, teacherId,anotherCqId);
 
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());	
		synchronized(teacher){
			//will be good when the teacher loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest cq = loadChangeRequest(userContext, anotherCqId, emptyOptions());		
			teacher.updateCq(cq);		
			teacher = saveTeacher(userContext, teacher, emptyOptions());
			
			return present(userContext,teacher, allTokens());
			
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
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForTeacher(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ChangeRequest loadChangeRequest(HealthUserContext userContext, String newCqId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newCqId, options);
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


	//disconnect Teacher with platform in SchoolClass
	protected Teacher breakWithSchoolClassByPlatform(HealthUserContext userContext, String teacherId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveSchoolClassListWithPlatform(teacher, platformId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
				return teacher;
			}
	}
	//disconnect Teacher with cq in SchoolClass
	protected Teacher breakWithSchoolClassByCq(HealthUserContext userContext, String teacherId, String cqId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());

			synchronized(teacher){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				teacherDaoOf(userContext).planToRemoveSchoolClassListWithCq(teacher, cqId, this.emptyOptions());

				teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
				return teacher;
			}
	}






	protected void checkParamsForAddingSchoolClass(HealthUserContext userContext, String teacherId, String name, String platformId, String schoole, String cqId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfTeacher(teacherId);

		
		checkerOf(userContext).checkNameOfSchoolClass(name);
		
		checkerOf(userContext).checkPlatformIdOfSchoolClass(platformId);
		
		checkerOf(userContext).checkSchooleOfSchoolClass(schoole);
		
		checkerOf(userContext).checkCqIdOfSchoolClass(cqId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);


	}
	public  Teacher addSchoolClass(HealthUserContext userContext, String teacherId, String name, String platformId, String schoole, String cqId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingSchoolClass(userContext,teacherId,name, platformId, schoole, cqId,tokensExpr);

		SchoolClass schoolClass = createSchoolClass(userContext,name, platformId, schoole, cqId);

		Teacher teacher = loadTeacher(userContext, teacherId, emptyOptions());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.addSchoolClass( schoolClass );
			teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
			
			userContext.getManagerGroup().getSchoolClassManager().onNewInstanceCreated(userContext, schoolClass);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSchoolClassProperties(HealthUserContext userContext, String teacherId,String id,String name,String schoole,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfSchoolClass(id);

		checkerOf(userContext).checkNameOfSchoolClass( name);
		checkerOf(userContext).checkSchooleOfSchoolClass( schoole);

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher updateSchoolClassProperties(HealthUserContext userContext, String teacherId, String id,String name,String schoole, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSchoolClassProperties(userContext,teacherId,id,name,schoole,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSchoolClassListList()
				.searchSchoolClassListWith(SchoolClass.ID_PROPERTY, "is", id).done();

		Teacher teacherToUpdate = loadTeacher(userContext, teacherId, options);

		if(teacherToUpdate.getSchoolClassList().isEmpty()){
			throw new TeacherManagerException("SchoolClass is NOT FOUND with id: '"+id+"'");
		}

		SchoolClass item = teacherToUpdate.getSchoolClassList().first();

		item.updateName( name );
		item.updateSchoole( schoole );


		//checkParamsForAddingSchoolClass(userContext,teacherId,name, code, used,tokensExpr);
		Teacher teacher = saveTeacher(userContext, teacherToUpdate, tokens().withSchoolClassList().done());
		synchronized(teacher){
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}
	}


	protected SchoolClass createSchoolClass(HealthUserContext userContext, String name, String platformId, String schoole, String cqId) throws Exception{

		SchoolClass schoolClass = new SchoolClass();
		
		
		schoolClass.setName(name);		
		schoolClass.setCreateTime(userContext.now());		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		schoolClass.setPlatform(platform);		
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

	protected void checkParamsForRemovingSchoolClassList(HealthUserContext userContext, String teacherId,
			String schoolClassIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfTeacher(teacherId);
		for(String schoolClassIdItem: schoolClassIds){
			checkerOf(userContext).checkIdOfSchoolClass(schoolClassIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeSchoolClassList(HealthUserContext userContext, String teacherId,
			String schoolClassIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingSchoolClassList(userContext, teacherId,  schoolClassIds, tokensExpr);


			Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
			synchronized(teacher){
				//Will be good when the teacher loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				teacherDaoOf(userContext).planToRemoveSchoolClassList(teacher, schoolClassIds, allTokens());
				teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
				deleteRelationListInGraph(userContext, teacher.getSchoolClassList());
				return present(userContext,teacher, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingSchoolClass(HealthUserContext userContext, String teacherId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher removeSchoolClass(HealthUserContext userContext, String teacherId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingSchoolClass(userContext,teacherId, schoolClassId, schoolClassVersion,tokensExpr);

		SchoolClass schoolClass = createIndexedSchoolClass(schoolClassId, schoolClassVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			teacher.removeSchoolClass( schoolClass );
			teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
			deleteRelationInGraph(userContext, schoolClass);
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingSchoolClass(HealthUserContext userContext, String teacherId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfTeacher( teacherId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}
	public  Teacher copySchoolClassFrom(HealthUserContext userContext, String teacherId,
		String schoolClassId, int schoolClassVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingSchoolClass(userContext,teacherId, schoolClassId, schoolClassVersion,tokensExpr);

		SchoolClass schoolClass = createIndexedSchoolClass(schoolClassId, schoolClassVersion);
		Teacher teacher = loadTeacher(userContext, teacherId, allTokens());
		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			teacher.copySchoolClassFrom( schoolClass );
			teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
			
			userContext.getManagerGroup().getSchoolClassManager().onNewInstanceCreated(userContext, (SchoolClass)teacher.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,teacher, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingSchoolClass(HealthUserContext userContext, String teacherId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfTeacher(teacherId);
		checkerOf(userContext).checkIdOfSchoolClass(schoolClassId);
		checkerOf(userContext).checkVersionOfSchoolClass(schoolClassVersion);
		

		if(SchoolClass.NAME_PROPERTY.equals(property)){
			checkerOf(userContext).checkNameOfSchoolClass(parseString(newValueExpr));
		}
		
		if(SchoolClass.SCHOOLE_PROPERTY.equals(property)){
			checkerOf(userContext).checkSchooleOfSchoolClass(parseString(newValueExpr));
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(TeacherManagerException.class);

	}

	public  Teacher updateSchoolClass(HealthUserContext userContext, String teacherId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingSchoolClass(userContext, teacherId, schoolClassId, schoolClassVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withSchoolClassList().searchSchoolClassListWith(SchoolClass.ID_PROPERTY, "eq", schoolClassId).done();



		Teacher teacher = loadTeacher(userContext, teacherId, loadTokens);

		synchronized(teacher){
			//Will be good when the teacher loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//teacher.removeSchoolClass( schoolClass );
			//make changes to AcceleraterAccount.
			SchoolClass schoolClassIndex = createIndexedSchoolClass(schoolClassId, schoolClassVersion);

			SchoolClass schoolClass = teacher.findTheSchoolClass(schoolClassIndex);
			if(schoolClass == null){
				throw new TeacherManagerException(schoolClass+" is NOT FOUND" );
			}

			schoolClass.changeProperty(property, newValueExpr);
			
			teacher = saveTeacher(userContext, teacher, tokens().withSchoolClassList().done());
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


