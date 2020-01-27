
package com.doublechaintech.health.teacher;

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


import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;

import com.doublechaintech.health.changerequest.ChangeRequestDAO;
import com.doublechaintech.health.schoolclass.SchoolClassDAO;
import com.doublechaintech.health.platform.PlatformDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class TeacherJDBCTemplateDAO extends HealthBaseDAOImpl implements TeacherDAO{
 
 	
 	private  ChangeRequestDAO  changeRequestDAO;
 	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
	 	return this.changeRequestDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
 	
			
		

	
	/*
	protected Teacher load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalTeacher(accessKey, options);
	}
	*/
	
	public SmartList<Teacher> loadAll() {
	    return this.loadAll(getTeacherMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Teacher load(String id,Map<String,Object> options) throws Exception{
		return loadInternalTeacher(TeacherTable.withId(id), options);
	}
	
	
	
	public Teacher save(Teacher teacher,Map<String,Object> options){
		
		String methodName="save(Teacher teacher,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(teacher, methodName, "teacher");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalTeacher(teacher,options);
	}
	public Teacher clone(String teacherId, Map<String,Object> options) throws Exception{
	
		return clone(TeacherTable.withId(teacherId),options);
	}
	
	protected Teacher clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String teacherId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Teacher newTeacher = loadInternalTeacher(accessKey, options);
		newTeacher.setVersion(0);
		
		
 		
 		if(isSaveSchoolClassListEnabled(options)){
 			for(SchoolClass item: newTeacher.getSchoolClassList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalTeacher(newTeacher,options);
		
		return newTeacher;
	}
	
	
	
	

	protected void throwIfHasException(String teacherId,int version,int count) throws Exception{
		if (count == 1) {
			throw new TeacherVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new TeacherNotFoundException(
					"The " + this.getTableName() + "(" + teacherId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String teacherId, int version) throws Exception{
	
		String methodName="delete(String teacherId, int version)";
		assertMethodArgumentNotNull(teacherId, methodName, "teacherId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{teacherId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(teacherId,version);
		}
		
	
	}
	
	
	
	
	

	public Teacher disconnectFromAll(String teacherId, int version) throws Exception{
	
		
		Teacher teacher = loadInternalTeacher(TeacherTable.withId(teacherId), emptyOptions());
		teacher.clearFromAll();
		this.saveTeacher(teacher);
		return teacher;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return TeacherTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "teacher";
	}
	@Override
	protected String getBeanName() {
		
		return "teacher";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return TeacherTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TeacherTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TeacherTokens.PLATFORM);
 	}
 	

 	
  

 	protected boolean isExtractCqEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, TeacherTokens.CQ);
 	}

 	protected boolean isSaveCqEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, TeacherTokens.CQ);
 	}
 	

 	
 
		
	
	protected boolean isExtractSchoolClassListEnabled(Map<String,Object> options){		
 		return checkOptions(options,TeacherTokens.SCHOOL_CLASS_LIST);
 	}
 	protected boolean isAnalyzeSchoolClassListEnabled(Map<String,Object> options){		 		
 		return TeacherTokens.of(options).analyzeSchoolClassListEnabled();
 	}
	
	protected boolean isSaveSchoolClassListEnabled(Map<String,Object> options){
		return checkOptions(options, TeacherTokens.SCHOOL_CLASS_LIST);
		
 	}
 	
		

	

	protected TeacherMapper getTeacherMapper(){
		return new TeacherMapper();
	}

	
	
	protected Teacher extractTeacher(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Teacher teacher = loadSingleObject(accessKey, getTeacherMapper());
			return teacher;
		}catch(EmptyResultDataAccessException e){
			throw new TeacherNotFoundException("Teacher("+accessKey+") is not found!");
		}

	}

	
	

	protected Teacher loadInternalTeacher(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Teacher teacher = extractTeacher(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(teacher, loadOptions);
 		}
  	
 		if(isExtractCqEnabled(loadOptions)){
	 		extractCq(teacher, loadOptions);
 		}
 
		
		if(isExtractSchoolClassListEnabled(loadOptions)){
	 		extractSchoolClassList(teacher, loadOptions);
 		}	
 		if(isAnalyzeSchoolClassListEnabled(loadOptions)){
	 		analyzeSchoolClassList(teacher, loadOptions);
 		}
 		
		
		return teacher;
		
	}

	 

 	protected Teacher extractPlatform(Teacher teacher, Map<String,Object> options) throws Exception{

		if(teacher.getPlatform() == null){
			return teacher;
		}
		String platformId = teacher.getPlatform().getId();
		if( platformId == null){
			return teacher;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			teacher.setPlatform(platform);
		}
		
 		
 		return teacher;
 	}
 		
  

 	protected Teacher extractCq(Teacher teacher, Map<String,Object> options) throws Exception{

		if(teacher.getCq() == null){
			return teacher;
		}
		String cqId = teacher.getCq().getId();
		if( cqId == null){
			return teacher;
		}
		ChangeRequest cq = getChangeRequestDAO().load(cqId,options);
		if(cq != null){
			teacher.setCq(cq);
		}
		
 		
 		return teacher;
 	}
 		
 
		
	protected void enhanceSchoolClassList(SmartList<SchoolClass> schoolClassList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Teacher extractSchoolClassList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<SchoolClass> schoolClassList = getSchoolClassDAO().findSchoolClassByClassTeacher(teacher.getId(),options);
		if(schoolClassList != null){
			enhanceSchoolClassList(schoolClassList,options);
			teacher.setSchoolClassList(schoolClassList);
		}
		
		return teacher;
	
	}	
	
	protected Teacher analyzeSchoolClassList(Teacher teacher, Map<String,Object> options){
		
		
		if(teacher == null){
			return null;
		}
		if(teacher.getId() == null){
			return teacher;
		}

		
		
		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();
		if(schoolClassList != null){
			getSchoolClassDAO().analyzeSchoolClassByClassTeacher(schoolClassList, teacher.getId(), options);
			
		}
		
		return teacher;
	
	}	
	
		
		
  	
 	public SmartList<Teacher> findTeacherByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Teacher> resultList = queryWith(TeacherTable.COLUMN_PLATFORM, platformId, options, getTeacherMapper());
		// analyzeTeacherByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Teacher> findTeacherByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Teacher> resultList =  queryWithRange(TeacherTable.COLUMN_PLATFORM, platformId, options, getTeacherMapper(), start, count);
 		//analyzeTeacherByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeTeacherByPlatform(SmartList<Teacher> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Teacher.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Teacher.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("老师");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Teacher.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Teacher.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTeacherByPlatform(String platformId,Map<String,Object> options){

 		return countWith(TeacherTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countTeacherByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TeacherTable.COLUMN_PLATFORM, ids, options);
	}
 	
  	
 	public SmartList<Teacher> findTeacherByCq(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<Teacher> resultList = queryWith(TeacherTable.COLUMN_CQ, changeRequestId, options, getTeacherMapper());
		// analyzeTeacherByCq(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Teacher> findTeacherByCq(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Teacher> resultList =  queryWithRange(TeacherTable.COLUMN_CQ, changeRequestId, options, getTeacherMapper(), start, count);
 		//analyzeTeacherByCq(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeTeacherByCq(SmartList<Teacher> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Teacher.CQ_PROPERTY, changeRequestId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Teacher.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("老师");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Teacher.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Teacher.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countTeacherByCq(String changeRequestId,Map<String,Object> options){

 		return countWith(TeacherTable.COLUMN_CQ, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countTeacherByCqIds(String[] ids, Map<String, Object> options) {
		return countWithIds(TeacherTable.COLUMN_CQ, ids, options);
	}
 	
 	
		
		
		

	

	protected Teacher saveTeacher(Teacher  teacher){
		
		if(!teacher.isChanged()){
			return teacher;
		}
		
		
		String SQL=this.getSaveTeacherSQL(teacher);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveTeacherParameters(teacher);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		teacher.incVersion();
		return teacher;
	
	}
	public SmartList<Teacher> saveTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitTeacherList(teacherList);
		
		batchTeacherCreate((List<Teacher>)lists[CREATE_LIST_INDEX]);
		
		batchTeacherUpdate((List<Teacher>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Teacher teacher:teacherList){
			if(teacher.isChanged()){
				teacher.incVersion();
			}
			
		
		}
		
		
		return teacherList;
	}

	public SmartList<Teacher> removeTeacherList(SmartList<Teacher> teacherList,Map<String,Object> options){
		
		
		super.removeList(teacherList, options);
		
		return teacherList;
		
		
	}
	
	protected List<Object[]> prepareTeacherBatchCreateArgs(List<Teacher> teacherList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Teacher teacher:teacherList ){
			Object [] parameters = prepareTeacherCreateParameters(teacher);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareTeacherBatchUpdateArgs(List<Teacher> teacherList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Teacher teacher:teacherList ){
			if(!teacher.isChanged()){
				continue;
			}
			Object [] parameters = prepareTeacherUpdateParameters(teacher);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchTeacherCreate(List<Teacher> teacherList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareTeacherBatchCreateArgs(teacherList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchTeacherUpdate(List<Teacher> teacherList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareTeacherBatchUpdateArgs(teacherList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitTeacherList(List<Teacher> teacherList){
		
		List<Teacher> teacherCreateList=new ArrayList<Teacher>();
		List<Teacher> teacherUpdateList=new ArrayList<Teacher>();
		
		for(Teacher teacher: teacherList){
			if(isUpdateRequest(teacher)){
				teacherUpdateList.add( teacher);
				continue;
			}
			teacherCreateList.add(teacher);
		}
		
		return new Object[]{teacherCreateList,teacherUpdateList};
	}
	
	protected boolean isUpdateRequest(Teacher teacher){
 		return teacher.getVersion() > 0;
 	}
 	protected String getSaveTeacherSQL(Teacher teacher){
 		if(isUpdateRequest(teacher)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveTeacherParameters(Teacher teacher){
 		if(isUpdateRequest(teacher) ){
 			return prepareTeacherUpdateParameters(teacher);
 		}
 		return prepareTeacherCreateParameters(teacher);
 	}
 	protected Object[] prepareTeacherUpdateParameters(Teacher teacher){
 		Object[] parameters = new Object[9];
 
 		parameters[0] = teacher.getName();
 		parameters[1] = teacher.getMobile();
 		parameters[2] = teacher.getSchoole();
 		parameters[3] = teacher.getCreateTime(); 	
 		if(teacher.getPlatform() != null){
 			parameters[4] = teacher.getPlatform().getId();
 		}
  	
 		if(teacher.getCq() != null){
 			parameters[5] = teacher.getCq().getId();
 		}
 		
 		parameters[6] = teacher.nextVersion();
 		parameters[7] = teacher.getId();
 		parameters[8] = teacher.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareTeacherCreateParameters(Teacher teacher){
		Object[] parameters = new Object[7];
		String newTeacherId=getNextId();
		teacher.setId(newTeacherId);
		parameters[0] =  teacher.getId();
 
 		parameters[1] = teacher.getName();
 		parameters[2] = teacher.getMobile();
 		parameters[3] = teacher.getSchoole();
 		parameters[4] = teacher.getCreateTime(); 	
 		if(teacher.getPlatform() != null){
 			parameters[5] = teacher.getPlatform().getId();
 		
 		}
 		 	
 		if(teacher.getCq() != null){
 			parameters[6] = teacher.getCq().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Teacher saveInternalTeacher(Teacher teacher, Map<String,Object> options){
		
		saveTeacher(teacher);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(teacher, options);
 		}
  	
 		if(isSaveCqEnabled(options)){
	 		saveCq(teacher, options);
 		}
 
		
		if(isSaveSchoolClassListEnabled(options)){
	 		saveSchoolClassList(teacher, options);
	 		//removeSchoolClassList(teacher, options);
	 		//Not delete the record
	 		
 		}		
		
		return teacher;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Teacher savePlatform(Teacher teacher, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(teacher.getPlatform() == null){
 			return teacher;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(teacher.getPlatform(),options);
 		return teacher;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected Teacher saveCq(Teacher teacher, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(teacher.getCq() == null){
 			return teacher;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(teacher.getCq(),options);
 		return teacher;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Teacher planToRemoveSchoolClassList(Teacher teacher, String schoolClassIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, teacher.getId());
		key.put(SchoolClass.ID_PROPERTY, schoolClassIds);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return teacher;
		}
		if(externalSchoolClassList.isEmpty()){
			return teacher;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){

			schoolClassItem.clearFromAll();
		}
		
		
		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return teacher;	
	
	}


	//disconnect Teacher with platform in SchoolClass
	public Teacher planToRemoveSchoolClassListWithPlatform(Teacher teacher, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, teacher.getId());
		key.put(SchoolClass.PLATFORM_PROPERTY, platformId);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return teacher;
		}
		if(externalSchoolClassList.isEmpty()){
			return teacher;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){
			schoolClassItem.clearPlatform();
			schoolClassItem.clearClassTeacher();
			
		}
		
		
		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return teacher;
	}
	
	public int countSchoolClassListWithPlatform(String teacherId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, teacherId);
		key.put(SchoolClass.PLATFORM_PROPERTY, platformId);
		
		int count = getSchoolClassDAO().countSchoolClassWithKey(key, options);
		return count;
	}
	
	//disconnect Teacher with cq in SchoolClass
	public Teacher planToRemoveSchoolClassListWithCq(Teacher teacher, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, teacher.getId());
		key.put(SchoolClass.CQ_PROPERTY, cqId);
		
		SmartList<SchoolClass> externalSchoolClassList = getSchoolClassDAO().
				findSchoolClassWithKey(key, options);
		if(externalSchoolClassList == null){
			return teacher;
		}
		if(externalSchoolClassList.isEmpty()){
			return teacher;
		}
		
		for(SchoolClass schoolClassItem: externalSchoolClassList){
			schoolClassItem.clearCq();
			schoolClassItem.clearClassTeacher();
			
		}
		
		
		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();		
		schoolClassList.addAllToRemoveList(externalSchoolClassList);
		return teacher;
	}
	
	public int countSchoolClassListWithCq(String teacherId, String cqId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, teacherId);
		key.put(SchoolClass.CQ_PROPERTY, cqId);
		
		int count = getSchoolClassDAO().countSchoolClassWithKey(key, options);
		return count;
	}
	

		
	protected Teacher saveSchoolClassList(Teacher teacher, Map<String,Object> options){
		
		
		
		
		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();
		if(schoolClassList == null){
			//null list means nothing
			return teacher;
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
		
		
		return teacher;
	
	}
	
	protected Teacher removeSchoolClassList(Teacher teacher, Map<String,Object> options){
	
	
		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();
		if(schoolClassList == null){
			return teacher;
		}	
	
		SmartList<SchoolClass> toRemoveSchoolClassList = schoolClassList.getToRemoveList();
		
		if(toRemoveSchoolClassList == null){
			return teacher;
		}
		if(toRemoveSchoolClassList.isEmpty()){
			return teacher;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getSchoolClassDAO().removeSchoolClassList(toRemoveSchoolClassList,options);
		
		return teacher;
	
	}
	
	

 	
 	
	
	
	
		

	public Teacher present(Teacher teacher,Map<String, Object> options){
	
		presentSchoolClassList(teacher,options);

		return teacher;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Teacher presentSchoolClassList(
			Teacher teacher,
			Map<String, Object> options) {

		SmartList<SchoolClass> schoolClassList = teacher.getSchoolClassList();		
				SmartList<SchoolClass> newList= presentSubList(teacher.getId(),
				schoolClassList,
				options,
				getSchoolClassDAO()::countSchoolClassByClassTeacher,
				getSchoolClassDAO()::findSchoolClassByClassTeacher
				);

		
		teacher.setSchoolClassList(newList);
		

		return teacher;
	}			
		

	
    public SmartList<Teacher> requestCandidateTeacherForSchoolClass(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(TeacherTable.COLUMN_NAME, filterKey, pageNo, pageSize, getTeacherMapper());
    }
		

	protected String getTableName(){
		return TeacherTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Teacher> teacherList) {		
		this.enhanceListInternal(teacherList, this.getTeacherMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:SchoolClass的classTeacher的SchoolClassList
	public SmartList<SchoolClass> loadOurSchoolClassList(HealthUserContext userContext, List<Teacher> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SchoolClass.CLASS_TEACHER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<SchoolClass> loadedObjs = userContext.getDAOGroup().getSchoolClassDAO().findSchoolClassWithKey(key, options);
		Map<String, List<SchoolClass>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getClassTeacher().getId()));
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
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Teacher> teacherList = ownerEntity.collectRefsWithType(Teacher.INTERNAL_TYPE);
		this.enhanceList(teacherList);
		
	}
	
	@Override
	public SmartList<Teacher> findTeacherWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getTeacherMapper());

	}
	@Override
	public int countTeacherWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countTeacherWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Teacher> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getTeacherMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


