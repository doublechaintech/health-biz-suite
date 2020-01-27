
package com.doublechaintech.health.student;

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
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;

import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyDAO;
import com.doublechaintech.health.guardian.GuardianDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class StudentJDBCTemplateDAO extends HealthBaseDAOImpl implements StudentDAO{
 
 	
 	private  SchoolClassDAO  schoolClassDAO;
 	public void setSchoolClassDAO(SchoolClassDAO schoolClassDAO){
	 	this.schoolClassDAO = schoolClassDAO;
 	}
 	public SchoolClassDAO getSchoolClassDAO(){
	 	return this.schoolClassDAO;
 	}
 
 	
 	private  GuardianDAO  guardianDAO;
 	public void setGuardianDAO(GuardianDAO guardianDAO){
	 	this.guardianDAO = guardianDAO;
 	}
 	public GuardianDAO getGuardianDAO(){
	 	return this.guardianDAO;
 	}
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
 	}


			
		
	
  	private  StudentHealthSurveyDAO  studentHealthSurveyDAO;
 	public void setStudentHealthSurveyDAO(StudentHealthSurveyDAO pStudentHealthSurveyDAO){
 	
 		if(pStudentHealthSurveyDAO == null){
 			throw new IllegalStateException("Do not try to set studentHealthSurveyDAO to null.");
 		}
	 	this.studentHealthSurveyDAO = pStudentHealthSurveyDAO;
 	}
 	public StudentHealthSurveyDAO getStudentHealthSurveyDAO(){
 		if(this.studentHealthSurveyDAO == null){
 			throw new IllegalStateException("The studentHealthSurveyDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.studentHealthSurveyDAO;
 	}	
 	
			
		

	
	/*
	protected Student load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalStudent(accessKey, options);
	}
	*/
	
	public SmartList<Student> loadAll() {
	    return this.loadAll(getStudentMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Student load(String id,Map<String,Object> options) throws Exception{
		return loadInternalStudent(StudentTable.withId(id), options);
	}
	
	
	
	public Student save(Student student,Map<String,Object> options){
		
		String methodName="save(Student student,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(student, methodName, "student");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalStudent(student,options);
	}
	public Student clone(String studentId, Map<String,Object> options) throws Exception{
	
		return clone(StudentTable.withId(studentId),options);
	}
	
	protected Student clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String studentId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Student newStudent = loadInternalStudent(accessKey, options);
		newStudent.setVersion(0);
		
		
 		
 		if(isSaveStudentHealthSurveyListEnabled(options)){
 			for(StudentHealthSurvey item: newStudent.getStudentHealthSurveyList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalStudent(newStudent,options);
		
		return newStudent;
	}
	
	
	
	

	protected void throwIfHasException(String studentId,int version,int count) throws Exception{
		if (count == 1) {
			throw new StudentVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new StudentNotFoundException(
					"The " + this.getTableName() + "(" + studentId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String studentId, int version) throws Exception{
	
		String methodName="delete(String studentId, int version)";
		assertMethodArgumentNotNull(studentId, methodName, "studentId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{studentId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(studentId,version);
		}
		
	
	}
	
	
	
	
	

	public Student disconnectFromAll(String studentId, int version) throws Exception{
	
		
		Student student = loadInternalStudent(StudentTable.withId(studentId), emptyOptions());
		student.clearFromAll();
		this.saveStudent(student);
		return student;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return StudentTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "student";
	}
	@Override
	protected String getBeanName() {
		
		return "student";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return StudentTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractGuardianEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentTokens.GUARDIAN);
 	}

 	protected boolean isSaveGuardianEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentTokens.GUARDIAN);
 	}
 	

 	
  

 	protected boolean isExtractSchoolClassEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentTokens.SCHOOLCLASS);
 	}

 	protected boolean isSaveSchoolClassEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentTokens.SCHOOLCLASS);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, StudentTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, StudentTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractStudentHealthSurveyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,StudentTokens.STUDENT_HEALTH_SURVEY_LIST);
 	}
 	protected boolean isAnalyzeStudentHealthSurveyListEnabled(Map<String,Object> options){		 		
 		return StudentTokens.of(options).analyzeStudentHealthSurveyListEnabled();
 	}
	
	protected boolean isSaveStudentHealthSurveyListEnabled(Map<String,Object> options){
		return checkOptions(options, StudentTokens.STUDENT_HEALTH_SURVEY_LIST);
		
 	}
 	
		

	

	protected StudentMapper getStudentMapper(){
		return new StudentMapper();
	}

	
	
	protected Student extractStudent(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Student student = loadSingleObject(accessKey, getStudentMapper());
			return student;
		}catch(EmptyResultDataAccessException e){
			throw new StudentNotFoundException("Student("+accessKey+") is not found!");
		}

	}

	
	

	protected Student loadInternalStudent(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Student student = extractStudent(accessKey, loadOptions);
 	
 		if(isExtractGuardianEnabled(loadOptions)){
	 		extractGuardian(student, loadOptions);
 		}
  	
 		if(isExtractSchoolClassEnabled(loadOptions)){
	 		extractSchoolClass(student, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(student, loadOptions);
 		}
 
		
		if(isExtractStudentHealthSurveyListEnabled(loadOptions)){
	 		extractStudentHealthSurveyList(student, loadOptions);
 		}	
 		if(isAnalyzeStudentHealthSurveyListEnabled(loadOptions)){
	 		analyzeStudentHealthSurveyList(student, loadOptions);
 		}
 		
		
		return student;
		
	}

	 

 	protected Student extractGuardian(Student student, Map<String,Object> options) throws Exception{

		if(student.getGuardian() == null){
			return student;
		}
		String guardianId = student.getGuardian().getId();
		if( guardianId == null){
			return student;
		}
		Guardian guardian = getGuardianDAO().load(guardianId,options);
		if(guardian != null){
			student.setGuardian(guardian);
		}
		
 		
 		return student;
 	}
 		
  

 	protected Student extractSchoolClass(Student student, Map<String,Object> options) throws Exception{

		if(student.getSchoolClass() == null){
			return student;
		}
		String schoolClassId = student.getSchoolClass().getId();
		if( schoolClassId == null){
			return student;
		}
		SchoolClass schoolClass = getSchoolClassDAO().load(schoolClassId,options);
		if(schoolClass != null){
			student.setSchoolClass(schoolClass);
		}
		
 		
 		return student;
 	}
 		
  

 	protected Student extractCq(Student student, Map<String,Object> options) throws Exception{

		if(student.getCq() == null){
			return student;
		}
		String cqId = student.getCq().getId();
		if( cqId == null){
			return student;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			student.setCq(cq);
		}
		
 		
 		return student;
 	}
 		
 
		
	protected void enhanceStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Student extractStudentHealthSurveyList(Student student, Map<String,Object> options){
		
		
		if(student == null){
			return null;
		}
		if(student.getId() == null){
			return student;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = getStudentHealthSurveyDAO().findStudentHealthSurveyByStudent(student.getId(),options);
		if(studentHealthSurveyList != null){
			enhanceStudentHealthSurveyList(studentHealthSurveyList,options);
			student.setStudentHealthSurveyList(studentHealthSurveyList);
		}
		
		return student;
	
	}	
	
	protected Student analyzeStudentHealthSurveyList(Student student, Map<String,Object> options){
		
		
		if(student == null){
			return null;
		}
		if(student.getId() == null){
			return student;
		}

		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();
		if(studentHealthSurveyList != null){
			getStudentHealthSurveyDAO().analyzeStudentHealthSurveyByStudent(studentHealthSurveyList, student.getId(), options);
			
		}
		
		return student;
	
	}	
	
		
		
  	
 	public SmartList<Student> findStudentByGuardian(String guardianId,Map<String,Object> options){
 	
  		SmartList<Student> resultList = queryWith(StudentTable.COLUMN_GUARDIAN, guardianId, options, getStudentMapper());
		// analyzeStudentByGuardian(resultList, guardianId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Student> findStudentByGuardian(String guardianId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Student> resultList =  queryWithRange(StudentTable.COLUMN_GUARDIAN, guardianId, options, getStudentMapper(), start, count);
 		//analyzeStudentByGuardian(resultList, guardianId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentByGuardian(SmartList<Student> resultList, String guardianId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Student.GUARDIAN_PROPERTY, guardianId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentByGuardian(String guardianId,Map<String,Object> options){

 		return countWith(StudentTable.COLUMN_GUARDIAN, guardianId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentByGuardianIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentTable.COLUMN_GUARDIAN, ids, options);
	}
 	
  	
 	public SmartList<Student> findStudentBySchoolClass(String schoolClassId,Map<String,Object> options){
 	
  		SmartList<Student> resultList = queryWith(StudentTable.COLUMN_SCHOOL_CLASS, schoolClassId, options, getStudentMapper());
		// analyzeStudentBySchoolClass(resultList, schoolClassId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Student> findStudentBySchoolClass(String schoolClassId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Student> resultList =  queryWithRange(StudentTable.COLUMN_SCHOOL_CLASS, schoolClassId, options, getStudentMapper(), start, count);
 		//analyzeStudentBySchoolClass(resultList, schoolClassId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentBySchoolClass(SmartList<Student> resultList, String schoolClassId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Student.SCHOOL_CLASS_PROPERTY, schoolClassId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentBySchoolClass(String schoolClassId,Map<String,Object> options){

 		return countWith(StudentTable.COLUMN_SCHOOL_CLASS, schoolClassId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentBySchoolClassIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentTable.COLUMN_SCHOOL_CLASS, ids, options);
	}
 	
  	
 	public SmartList<Student> findStudentByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<Student> resultList = queryWith(StudentTable.COLUMN_CQ, changeRequestId, options, getStudentMapper());
		// analyzeStudentByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Student> findStudentByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Student> resultList =  queryWithRange(StudentTable.COLUMN_CQ, changeRequestId, options, getStudentMapper(), start, count);
 		//analyzeStudentByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeStudentByCq(SmartList<Student> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Student.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countStudentByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(StudentTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countStudentByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(StudentTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected Student saveStudent(Student  student){
		
		if(!student.isChanged()){
			return student;
		}
		
		
		String SQL=this.getSaveStudentSQL(student);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveStudentParameters(student);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		student.incVersion();
		return student;
	
	}
	public SmartList<Student> saveStudentList(SmartList<Student> studentList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitStudentList(studentList);
		
		batchStudentCreate((List<Student>)lists[CREATE_LIST_INDEX]);
		
		batchStudentUpdate((List<Student>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Student student:studentList){
			if(student.isChanged()){
				student.incVersion();
			}
			
		
		}
		
		
		return studentList;
	}

	public SmartList<Student> removeStudentList(SmartList<Student> studentList,Map<String,Object> options){
		
		
		super.removeList(studentList, options);
		
		return studentList;
		
		
	}
	
	protected List<Object[]> prepareStudentBatchCreateArgs(List<Student> studentList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Student student:studentList ){
			Object [] parameters = prepareStudentCreateParameters(student);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareStudentBatchUpdateArgs(List<Student> studentList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Student student:studentList ){
			if(!student.isChanged()){
				continue;
			}
			Object [] parameters = prepareStudentUpdateParameters(student);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchStudentCreate(List<Student> studentList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareStudentBatchCreateArgs(studentList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchStudentUpdate(List<Student> studentList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareStudentBatchUpdateArgs(studentList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitStudentList(List<Student> studentList){
		
		List<Student> studentCreateList=new ArrayList<Student>();
		List<Student> studentUpdateList=new ArrayList<Student>();
		
		for(Student student: studentList){
			if(isUpdateRequest(student)){
				studentUpdateList.add( student);
				continue;
			}
			studentCreateList.add(student);
		}
		
		return new Object[]{studentCreateList,studentUpdateList};
	}
	
	protected boolean isUpdateRequest(Student student){
 		return student.getVersion() > 0;
 	}
 	protected String getSaveStudentSQL(Student student){
 		if(isUpdateRequest(student)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveStudentParameters(Student student){
 		if(isUpdateRequest(student) ){
 			return prepareStudentUpdateParameters(student);
 		}
 		return prepareStudentCreateParameters(student);
 	}
 	protected Object[] prepareStudentUpdateParameters(Student student){
 		Object[] parameters = new Object[9];
 
 		parameters[0] = student.getName();
 		parameters[1] = student.getGender(); 	
 		if(student.getGuardian() != null){
 			parameters[2] = student.getGuardian().getId();
 		}
  	
 		if(student.getSchoolClass() != null){
 			parameters[3] = student.getSchoolClass().getId();
 		}
 
 		parameters[4] = student.getStudentId(); 	
 		if(student.getCq() != null){
 			parameters[5] = student.getCq().getId();
 		}
 		
 		parameters[6] = student.nextVersion();
 		parameters[7] = student.getId();
 		parameters[8] = student.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareStudentCreateParameters(Student student){
		Object[] parameters = new Object[7];
		String newStudentId=getNextId();
		student.setId(newStudentId);
		parameters[0] =  student.getId();
 
 		parameters[1] = student.getName();
 		parameters[2] = student.getGender(); 	
 		if(student.getGuardian() != null){
 			parameters[3] = student.getGuardian().getId();
 		
 		}
 		 	
 		if(student.getSchoolClass() != null){
 			parameters[4] = student.getSchoolClass().getId();
 		
 		}
 		
 		parameters[5] = student.getStudentId(); 	
 		if(student.getCq() != null){
 			parameters[6] = student.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Student saveInternalStudent(Student student, Map<String,Object> options){
		
		saveStudent(student);
 	
 		if(isSaveGuardianEnabled(options)){
	 		saveGuardian(student, options);
 		}
  	
 		if(isSaveSchoolClassEnabled(options)){
	 		saveSchoolClass(student, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(student, options);
 		}
 
		
		if(isSaveStudentHealthSurveyListEnabled(options)){
	 		saveStudentHealthSurveyList(student, options);
	 		//removeStudentHealthSurveyList(student, options);
	 		//Not delete the record
	 		
 		}		
		
		return student;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Student saveGuardian(Student student, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(student.getGuardian() == null){
 			return student;//do nothing when it is null
 		}
 		
 		getGuardianDAO().save(student.getGuardian(),options);
 		return student;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Student saveSchoolClass(Student student, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(student.getSchoolClass() == null){
 			return student;//do nothing when it is null
 		}
 		
 		getSchoolClassDAO().save(student.getSchoolClass(),options);
 		return student;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Student saveCq(Student student, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(student.getCq() == null){
 			return student;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(student.getCq(),options);
 		return student;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Student planToRemoveStudentHealthSurveyList(Student student, String studentHealthSurveyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, student.getId());
		key.put(StudentHealthSurvey.ID_PROPERTY, studentHealthSurveyIds);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return student;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return student;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){

			studentHealthSurveyItem.clearFromAll();
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return student;	
	
	}


	//disconnect Student with survey_status in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithSurveyStatus(Student student, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, student.getId());
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return student;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return student;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSurveyStatus();
			studentHealthSurveyItem.clearStudent();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return student;
	}
	
	public int countStudentHealthSurveyListWithSurveyStatus(String studentId, String surveyStatusId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		key.put(StudentHealthSurvey.SURVEY_STATUS_PROPERTY, surveyStatusId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Student with school_class in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithSchoolClass(Student student, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, student.getId());
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return student;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return student;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearSchoolClass();
			studentHealthSurveyItem.clearStudent();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return student;
	}
	
	public int countStudentHealthSurveyListWithSchoolClass(String studentId, String schoolClassId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		key.put(StudentHealthSurvey.SCHOOL_CLASS_PROPERTY, schoolClassId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Student with class_daily_health_survey in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithClassDailyHealthSurvey(Student student, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, student.getId());
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return student;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return student;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearClassDailyHealthSurvey();
			studentHealthSurveyItem.clearStudent();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return student;
	}
	
	public int countStudentHealthSurveyListWithClassDailyHealthSurvey(String studentId, String classDailyHealthSurveyId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		key.put(StudentHealthSurvey.CLASS_DAILY_HEALTH_SURVEY_PROPERTY, classDailyHealthSurveyId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	
	//disconnect Student with cq in StudentHealthSurvey
	public Student planToRemoveStudentHealthSurveyListWithCq(Student student, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, student.getId());
		key.put(StudentHealthSurvey.CQ_PROPERTY, cqId);
		
		SmartList<StudentHealthSurvey> externalStudentHealthSurveyList = getStudentHealthSurveyDAO().
				findStudentHealthSurveyWithKey(key, options);
		if(externalStudentHealthSurveyList == null){
			return student;
		}
		if(externalStudentHealthSurveyList.isEmpty()){
			return student;
		}
		
		for(StudentHealthSurvey studentHealthSurveyItem: externalStudentHealthSurveyList){
			studentHealthSurveyItem.clearCq();
			studentHealthSurveyItem.clearStudent();
			
		}
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();		
		studentHealthSurveyList.addAllToRemoveList(externalStudentHealthSurveyList);
		return student;
	}
	
	public int countStudentHealthSurveyListWithCq(String studentId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, studentId);
		key.put(StudentHealthSurvey.CQ_PROPERTY, cqId);
		
		int count = getStudentHealthSurveyDAO().countStudentHealthSurveyWithKey(key, options);
		return count;
	}
	

		
	protected Student saveStudentHealthSurveyList(Student student, Map<String,Object> options){
		
		
		
		
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			//null list means nothing
			return student;
		}
		SmartList<StudentHealthSurvey> mergedUpdateStudentHealthSurveyList = new SmartList<StudentHealthSurvey>();
		
		
		mergedUpdateStudentHealthSurveyList.addAll(studentHealthSurveyList); 
		if(studentHealthSurveyList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateStudentHealthSurveyList.addAll(studentHealthSurveyList.getToRemoveList());
			studentHealthSurveyList.removeAll(studentHealthSurveyList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getStudentHealthSurveyDAO().saveStudentHealthSurveyList(mergedUpdateStudentHealthSurveyList,options);
		
		if(studentHealthSurveyList.getToRemoveList() != null){
			studentHealthSurveyList.removeAll(studentHealthSurveyList.getToRemoveList());
		}
		
		
		return student;
	
	}
	
	protected Student removeStudentHealthSurveyList(Student student, Map<String,Object> options){
	
	
		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();
		if(studentHealthSurveyList == null){
			return student;
		}	
	
		SmartList<StudentHealthSurvey> toRemoveStudentHealthSurveyList = studentHealthSurveyList.getToRemoveList();
		
		if(toRemoveStudentHealthSurveyList == null){
			return student;
		}
		if(toRemoveStudentHealthSurveyList.isEmpty()){
			return student;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getStudentHealthSurveyDAO().removeStudentHealthSurveyList(toRemoveStudentHealthSurveyList,options);
		
		return student;
	
	}
	
	

 	
 	
	
	
	
		

	public Student present(Student student,Map<String, Object> options){
	
		presentStudentHealthSurveyList(student,options);

		return student;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Student presentStudentHealthSurveyList(
			Student student,
			Map<String, Object> options) {

		SmartList<StudentHealthSurvey> studentHealthSurveyList = student.getStudentHealthSurveyList();		
				SmartList<StudentHealthSurvey> newList= presentSubList(student.getId(),
				studentHealthSurveyList,
				options,
				getStudentHealthSurveyDAO()::countStudentHealthSurveyByStudent,
				getStudentHealthSurveyDAO()::findStudentHealthSurveyByStudent
				);

		
		student.setStudentHealthSurveyList(newList);
		

		return student;
	}			
		

	
    public SmartList<Student> requestCandidateStudentForStudentHealthSurvey(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(StudentTable.COLUMN_NAME, filterKey, pageNo, pageSize, getStudentMapper());
    }
		

	protected String getTableName(){
		return StudentTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Student> studentList) {		
		this.enhanceListInternal(studentList, this.getStudentMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:StudentHealthSurvey的student的StudentHealthSurveyList
	public SmartList<StudentHealthSurvey> loadOurStudentHealthSurveyList(HealthUserContext userContext, List<Student> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(StudentHealthSurvey.STUDENT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<StudentHealthSurvey> loadedObjs = userContext.getDAOGroup().getStudentHealthSurveyDAO().findStudentHealthSurveyWithKey(key, options);
		Map<String, List<StudentHealthSurvey>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getStudent().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<StudentHealthSurvey> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<StudentHealthSurvey> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setStudentHealthSurveyList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Student> studentList = ownerEntity.collectRefsWithType(Student.INTERNAL_TYPE);
		this.enhanceList(studentList);
		
	}
	
	@Override
	public SmartList<Student> findStudentWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getStudentMapper());

	}
	@Override
	public int countStudentWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countStudentWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Student> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getStudentMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


