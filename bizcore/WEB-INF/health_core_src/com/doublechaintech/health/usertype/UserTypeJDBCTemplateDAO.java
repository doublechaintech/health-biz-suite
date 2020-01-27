
package com.doublechaintech.health.usertype;

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
import com.doublechaintech.health.wechatuser.WechatUser;

import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.wechatuser.WechatUserDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class UserTypeJDBCTemplateDAO extends HealthBaseDAOImpl implements UserTypeDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  WechatUserDAO  wechatUserDAO;
 	public void setWechatUserDAO(WechatUserDAO pWechatUserDAO){
 	
 		if(pWechatUserDAO == null){
 			throw new IllegalStateException("Do not try to set wechatUserDAO to null.");
 		}
	 	this.wechatUserDAO = pWechatUserDAO;
 	}
 	public WechatUserDAO getWechatUserDAO(){
 		if(this.wechatUserDAO == null){
 			throw new IllegalStateException("The wechatUserDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.wechatUserDAO;
 	}	
 	
			
		

	
	/*
	protected UserType load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalUserType(accessKey, options);
	}
	*/
	
	public SmartList<UserType> loadAll() {
	    return this.loadAll(getUserTypeMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public UserType load(String id,Map<String,Object> options) throws Exception{
		return loadInternalUserType(UserTypeTable.withId(id), options);
	}
	
	
	
	public UserType loadByCode(String code,Map<String,Object> options) throws Exception{
		return loadInternalUserType(UserTypeTable.withCode( code), options);
	}
	
	
	public UserType save(UserType userType,Map<String,Object> options){
		
		String methodName="save(UserType userType,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(userType, methodName, "userType");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalUserType(userType,options);
	}
	public UserType clone(String userTypeId, Map<String,Object> options) throws Exception{
	
		return clone(UserTypeTable.withId(userTypeId),options);
	}
	
	protected UserType clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String userTypeId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		UserType newUserType = loadInternalUserType(accessKey, options);
		newUserType.setVersion(0);
		
		
 		
 		if(isSaveWechatUserListEnabled(options)){
 			for(WechatUser item: newUserType.getWechatUserList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalUserType(newUserType,options);
		
		return newUserType;
	}
	
	
	
	public UserType cloneByCode(String code,Map<String,Object> options) throws Exception{
		return clone(UserTypeTable.withCode( code), options);
	}
	
	
	

	protected void throwIfHasException(String userTypeId,int version,int count) throws Exception{
		if (count == 1) {
			throw new UserTypeVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new UserTypeNotFoundException(
					"The " + this.getTableName() + "(" + userTypeId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String userTypeId, int version) throws Exception{
	
		String methodName="delete(String userTypeId, int version)";
		assertMethodArgumentNotNull(userTypeId, methodName, "userTypeId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{userTypeId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(userTypeId,version);
		}
		
	
	}
	
	
	
	
	

	public UserType disconnectFromAll(String userTypeId, int version) throws Exception{
	
		
		UserType userType = loadInternalUserType(UserTypeTable.withId(userTypeId), emptyOptions());
		userType.clearFromAll();
		this.saveUserType(userType);
		return userType;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return UserTypeTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "user_type";
	}
	@Override
	protected String getBeanName() {
		
		return "userType";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return UserTypeTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, UserTypeTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, UserTypeTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractWechatUserListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTypeTokens.WECHAT_USER_LIST);
 	}
 	protected boolean isAnalyzeWechatUserListEnabled(Map<String,Object> options){		 		
 		return UserTypeTokens.of(options).analyzeWechatUserListEnabled();
 	}
	
	protected boolean isSaveWechatUserListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTypeTokens.WECHAT_USER_LIST);
		
 	}
 	
		

	

	protected UserTypeMapper getUserTypeMapper(){
		return new UserTypeMapper();
	}

	
	
	protected UserType extractUserType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			UserType userType = loadSingleObject(accessKey, getUserTypeMapper());
			return userType;
		}catch(EmptyResultDataAccessException e){
			throw new UserTypeNotFoundException("UserType("+accessKey+") is not found!");
		}

	}

	
	

	protected UserType loadInternalUserType(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		UserType userType = extractUserType(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(userType, loadOptions);
 		}
 
		
		if(isExtractWechatUserListEnabled(loadOptions)){
	 		extractWechatUserList(userType, loadOptions);
 		}	
 		if(isAnalyzeWechatUserListEnabled(loadOptions)){
	 		analyzeWechatUserList(userType, loadOptions);
 		}
 		
		
		return userType;
		
	}

	 

 	protected UserType extractPlatform(UserType userType, Map<String,Object> options) throws Exception{

		if(userType.getPlatform() == null){
			return userType;
		}
		String platformId = userType.getPlatform().getId();
		if( platformId == null){
			return userType;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			userType.setPlatform(platform);
		}
		
 		
 		return userType;
 	}
 		
 
		
	protected void enhanceWechatUserList(SmartList<WechatUser> wechatUserList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected UserType extractWechatUserList(UserType userType, Map<String,Object> options){
		
		
		if(userType == null){
			return null;
		}
		if(userType.getId() == null){
			return userType;
		}

		
		
		SmartList<WechatUser> wechatUserList = getWechatUserDAO().findWechatUserByUserType(userType.getId(),options);
		if(wechatUserList != null){
			enhanceWechatUserList(wechatUserList,options);
			userType.setWechatUserList(wechatUserList);
		}
		
		return userType;
	
	}	
	
	protected UserType analyzeWechatUserList(UserType userType, Map<String,Object> options){
		
		
		if(userType == null){
			return null;
		}
		if(userType.getId() == null){
			return userType;
		}

		
		
		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();
		if(wechatUserList != null){
			getWechatUserDAO().analyzeWechatUserByUserType(wechatUserList, userType.getId(), options);
			
		}
		
		return userType;
	
	}	
	
		
		
  	
 	public SmartList<UserType> findUserTypeByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<UserType> resultList = queryWith(UserTypeTable.COLUMN_PLATFORM, platformId, options, getUserTypeMapper());
		// analyzeUserTypeByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<UserType> findUserTypeByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<UserType> resultList =  queryWithRange(UserTypeTable.COLUMN_PLATFORM, platformId, options, getUserTypeMapper(), start, count);
 		//analyzeUserTypeByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeUserTypeByPlatform(SmartList<UserType> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countUserTypeByPlatform(String platformId,Map<String,Object> options){

 		return countWith(UserTypeTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countUserTypeByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(UserTypeTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected UserType saveUserType(UserType  userType){
		
		if(!userType.isChanged()){
			return userType;
		}
		
		
		String SQL=this.getSaveUserTypeSQL(userType);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveUserTypeParameters(userType);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		userType.incVersion();
		return userType;
	
	}
	public SmartList<UserType> saveUserTypeList(SmartList<UserType> userTypeList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitUserTypeList(userTypeList);
		
		batchUserTypeCreate((List<UserType>)lists[CREATE_LIST_INDEX]);
		
		batchUserTypeUpdate((List<UserType>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(UserType userType:userTypeList){
			if(userType.isChanged()){
				userType.incVersion();
			}
			
		
		}
		
		
		return userTypeList;
	}

	public SmartList<UserType> removeUserTypeList(SmartList<UserType> userTypeList,Map<String,Object> options){
		
		
		super.removeList(userTypeList, options);
		
		return userTypeList;
		
		
	}
	
	protected List<Object[]> prepareUserTypeBatchCreateArgs(List<UserType> userTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(UserType userType:userTypeList ){
			Object [] parameters = prepareUserTypeCreateParameters(userType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareUserTypeBatchUpdateArgs(List<UserType> userTypeList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(UserType userType:userTypeList ){
			if(!userType.isChanged()){
				continue;
			}
			Object [] parameters = prepareUserTypeUpdateParameters(userType);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchUserTypeCreate(List<UserType> userTypeList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareUserTypeBatchCreateArgs(userTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchUserTypeUpdate(List<UserType> userTypeList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareUserTypeBatchUpdateArgs(userTypeList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitUserTypeList(List<UserType> userTypeList){
		
		List<UserType> userTypeCreateList=new ArrayList<UserType>();
		List<UserType> userTypeUpdateList=new ArrayList<UserType>();
		
		for(UserType userType: userTypeList){
			if(isUpdateRequest(userType)){
				userTypeUpdateList.add( userType);
				continue;
			}
			userTypeCreateList.add(userType);
		}
		
		return new Object[]{userTypeCreateList,userTypeUpdateList};
	}
	
	protected boolean isUpdateRequest(UserType userType){
 		return userType.getVersion() > 0;
 	}
 	protected String getSaveUserTypeSQL(UserType userType){
 		if(isUpdateRequest(userType)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveUserTypeParameters(UserType userType){
 		if(isUpdateRequest(userType) ){
 			return prepareUserTypeUpdateParameters(userType);
 		}
 		return prepareUserTypeCreateParameters(userType);
 	}
 	protected Object[] prepareUserTypeUpdateParameters(UserType userType){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = userType.getName();
 		parameters[1] = userType.getCode(); 	
 		if(userType.getPlatform() != null){
 			parameters[2] = userType.getPlatform().getId();
 		}
 		
 		parameters[3] = userType.nextVersion();
 		parameters[4] = userType.getId();
 		parameters[5] = userType.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareUserTypeCreateParameters(UserType userType){
		Object[] parameters = new Object[4];
		String newUserTypeId=getNextId();
		userType.setId(newUserTypeId);
		parameters[0] =  userType.getId();
 
 		parameters[1] = userType.getName();
 		parameters[2] = userType.getCode(); 	
 		if(userType.getPlatform() != null){
 			parameters[3] = userType.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected UserType saveInternalUserType(UserType userType, Map<String,Object> options){
		
		saveUserType(userType);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(userType, options);
 		}
 
		
		if(isSaveWechatUserListEnabled(options)){
	 		saveWechatUserList(userType, options);
	 		//removeWechatUserList(userType, options);
	 		//Not delete the record
	 		
 		}		
		
		return userType;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected UserType savePlatform(UserType userType, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(userType.getPlatform() == null){
 			return userType;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(userType.getPlatform(),options);
 		return userType;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public UserType planToRemoveWechatUserList(UserType userType, String wechatUserIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.USER_TYPE_PROPERTY, userType.getId());
		key.put(WechatUser.ID_PROPERTY, wechatUserIds);
		
		SmartList<WechatUser> externalWechatUserList = getWechatUserDAO().
				findWechatUserWithKey(key, options);
		if(externalWechatUserList == null){
			return userType;
		}
		if(externalWechatUserList.isEmpty()){
			return userType;
		}
		
		for(WechatUser wechatUserItem: externalWechatUserList){

			wechatUserItem.clearFromAll();
		}
		
		
		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();		
		wechatUserList.addAllToRemoveList(externalWechatUserList);
		return userType;	
	
	}


	//disconnect UserType with address in WechatUser
	public UserType planToRemoveWechatUserListWithAddress(UserType userType, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.USER_TYPE_PROPERTY, userType.getId());
		key.put(WechatUser.ADDRESS_PROPERTY, addressId);
		
		SmartList<WechatUser> externalWechatUserList = getWechatUserDAO().
				findWechatUserWithKey(key, options);
		if(externalWechatUserList == null){
			return userType;
		}
		if(externalWechatUserList.isEmpty()){
			return userType;
		}
		
		for(WechatUser wechatUserItem: externalWechatUserList){
			wechatUserItem.clearAddress();
			wechatUserItem.clearUserType();
			
		}
		
		
		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();		
		wechatUserList.addAllToRemoveList(externalWechatUserList);
		return userType;
	}
	
	public int countWechatUserListWithAddress(String userTypeId, String addressId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.USER_TYPE_PROPERTY, userTypeId);
		key.put(WechatUser.ADDRESS_PROPERTY, addressId);
		
		int count = getWechatUserDAO().countWechatUserWithKey(key, options);
		return count;
	}
	
	//disconnect UserType with platform in WechatUser
	public UserType planToRemoveWechatUserListWithPlatform(UserType userType, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.USER_TYPE_PROPERTY, userType.getId());
		key.put(WechatUser.PLATFORM_PROPERTY, platformId);
		
		SmartList<WechatUser> externalWechatUserList = getWechatUserDAO().
				findWechatUserWithKey(key, options);
		if(externalWechatUserList == null){
			return userType;
		}
		if(externalWechatUserList.isEmpty()){
			return userType;
		}
		
		for(WechatUser wechatUserItem: externalWechatUserList){
			wechatUserItem.clearPlatform();
			wechatUserItem.clearUserType();
			
		}
		
		
		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();		
		wechatUserList.addAllToRemoveList(externalWechatUserList);
		return userType;
	}
	
	public int countWechatUserListWithPlatform(String userTypeId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.USER_TYPE_PROPERTY, userTypeId);
		key.put(WechatUser.PLATFORM_PROPERTY, platformId);
		
		int count = getWechatUserDAO().countWechatUserWithKey(key, options);
		return count;
	}
	

		
	protected UserType saveWechatUserList(UserType userType, Map<String,Object> options){
		
		
		
		
		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();
		if(wechatUserList == null){
			//null list means nothing
			return userType;
		}
		SmartList<WechatUser> mergedUpdateWechatUserList = new SmartList<WechatUser>();
		
		
		mergedUpdateWechatUserList.addAll(wechatUserList); 
		if(wechatUserList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateWechatUserList.addAll(wechatUserList.getToRemoveList());
			wechatUserList.removeAll(wechatUserList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getWechatUserDAO().saveWechatUserList(mergedUpdateWechatUserList,options);
		
		if(wechatUserList.getToRemoveList() != null){
			wechatUserList.removeAll(wechatUserList.getToRemoveList());
		}
		
		
		return userType;
	
	}
	
	protected UserType removeWechatUserList(UserType userType, Map<String,Object> options){
	
	
		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();
		if(wechatUserList == null){
			return userType;
		}	
	
		SmartList<WechatUser> toRemoveWechatUserList = wechatUserList.getToRemoveList();
		
		if(toRemoveWechatUserList == null){
			return userType;
		}
		if(toRemoveWechatUserList.isEmpty()){
			return userType;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getWechatUserDAO().removeWechatUserList(toRemoveWechatUserList,options);
		
		return userType;
	
	}
	
	

 	
 	
	
	
	
		

	public UserType present(UserType userType,Map<String, Object> options){
	
		presentWechatUserList(userType,options);

		return userType;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected UserType presentWechatUserList(
			UserType userType,
			Map<String, Object> options) {

		SmartList<WechatUser> wechatUserList = userType.getWechatUserList();		
				SmartList<WechatUser> newList= presentSubList(userType.getId(),
				wechatUserList,
				options,
				getWechatUserDAO()::countWechatUserByUserType,
				getWechatUserDAO()::findWechatUserByUserType
				);

		
		userType.setWechatUserList(newList);
		

		return userType;
	}			
		

	
    public SmartList<UserType> requestCandidateUserTypeForWechatUser(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTypeTable.COLUMN_NAME, filterKey, pageNo, pageSize, getUserTypeMapper());
    }
		

	protected String getTableName(){
		return UserTypeTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<UserType> userTypeList) {		
		this.enhanceListInternal(userTypeList, this.getUserTypeMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:WechatUser的userType的WechatUserList
	public SmartList<WechatUser> loadOurWechatUserList(HealthUserContext userContext, List<UserType> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WechatUser.USER_TYPE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WechatUser> loadedObjs = userContext.getDAOGroup().getWechatUserDAO().findWechatUserWithKey(key, options);
		Map<String, List<WechatUser>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getUserType().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<WechatUser> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<WechatUser> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setWechatUserList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<UserType> userTypeList = ownerEntity.collectRefsWithType(UserType.INTERNAL_TYPE);
		this.enhanceList(userTypeList);
		
	}
	
	@Override
	public SmartList<UserType> findUserTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getUserTypeMapper());

	}
	@Override
	public int countUserTypeWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countUserTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<UserType> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getUserTypeMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


