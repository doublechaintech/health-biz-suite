
package com.doublechaintech.health.district;

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


import com.doublechaintech.health.city.City;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;

import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.platform.PlatformDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class DistrictJDBCTemplateDAO extends HealthBaseDAOImpl implements DistrictDAO{
 
 	
 	private  CityDAO  cityDAO;
 	public void setCityDAO(CityDAO cityDAO){
	 	this.cityDAO = cityDAO;
 	}
 	public CityDAO getCityDAO(){
	 	return this.cityDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  LocationDAO  locationDAO;
 	public void setLocationDAO(LocationDAO pLocationDAO){
 	
 		if(pLocationDAO == null){
 			throw new IllegalStateException("Do not try to set locationDAO to null.");
 		}
	 	this.locationDAO = pLocationDAO;
 	}
 	public LocationDAO getLocationDAO(){
 		if(this.locationDAO == null){
 			throw new IllegalStateException("The locationDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.locationDAO;
 	}	
 	
			
		

	
	/*
	protected District load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalDistrict(accessKey, options);
	}
	*/
	
	public SmartList<District> loadAll() {
	    return this.loadAll(getDistrictMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public District load(String id,Map<String,Object> options) throws Exception{
		return loadInternalDistrict(DistrictTable.withId(id), options);
	}
	
	
	
	public District save(District district,Map<String,Object> options){
		
		String methodName="save(District district,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(district, methodName, "district");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalDistrict(district,options);
	}
	public District clone(String districtId, Map<String,Object> options) throws Exception{
	
		return clone(DistrictTable.withId(districtId),options);
	}
	
	protected District clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String districtId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		District newDistrict = loadInternalDistrict(accessKey, options);
		newDistrict.setVersion(0);
		
		
 		
 		if(isSaveLocationListEnabled(options)){
 			for(Location item: newDistrict.getLocationList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalDistrict(newDistrict,options);
		
		return newDistrict;
	}
	
	
	
	

	protected void throwIfHasException(String districtId,int version,int count) throws Exception{
		if (count == 1) {
			throw new DistrictVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new DistrictNotFoundException(
					"The " + this.getTableName() + "(" + districtId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String districtId, int version) throws Exception{
	
		String methodName="delete(String districtId, int version)";
		assertMethodArgumentNotNull(districtId, methodName, "districtId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{districtId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(districtId,version);
		}
		
	
	}
	
	
	
	
	

	public District disconnectFromAll(String districtId, int version) throws Exception{
	
		
		District district = loadInternalDistrict(DistrictTable.withId(districtId), emptyOptions());
		district.clearFromAll();
		this.saveDistrict(district);
		return district;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return DistrictTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "district";
	}
	@Override
	protected String getBeanName() {
		
		return "district";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return DistrictTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractCityEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, DistrictTokens.CITY);
 	}

 	protected boolean isSaveCityEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, DistrictTokens.CITY);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, DistrictTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, DistrictTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractLocationListEnabled(Map<String,Object> options){		
 		return checkOptions(options,DistrictTokens.LOCATION_LIST);
 	}
 	protected boolean isAnalyzeLocationListEnabled(Map<String,Object> options){		 		
 		return DistrictTokens.of(options).analyzeLocationListEnabled();
 	}
	
	protected boolean isSaveLocationListEnabled(Map<String,Object> options){
		return checkOptions(options, DistrictTokens.LOCATION_LIST);
		
 	}
 	
		

	

	protected DistrictMapper getDistrictMapper(){
		return new DistrictMapper();
	}

	
	
	protected District extractDistrict(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			District district = loadSingleObject(accessKey, getDistrictMapper());
			return district;
		}catch(EmptyResultDataAccessException e){
			throw new DistrictNotFoundException("District("+accessKey+") is not found!");
		}

	}

	
	

	protected District loadInternalDistrict(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		District district = extractDistrict(accessKey, loadOptions);
 	
 		if(isExtractCityEnabled(loadOptions)){
	 		extractCity(district, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(district, loadOptions);
 		}
 
		
		if(isExtractLocationListEnabled(loadOptions)){
	 		extractLocationList(district, loadOptions);
 		}	
 		if(isAnalyzeLocationListEnabled(loadOptions)){
	 		analyzeLocationList(district, loadOptions);
 		}
 		
		
		return district;
		
	}

	 

 	protected District extractCity(District district, Map<String,Object> options) throws Exception{

		if(district.getCity() == null){
			return district;
		}
		String cityId = district.getCity().getId();
		if( cityId == null){
			return district;
		}
		City city = getCityDAO().load(cityId,options);
		if(city != null){
			district.setCity(city);
		}
		
 		
 		return district;
 	}
 		
  

 	protected District extractPlatform(District district, Map<String,Object> options) throws Exception{

		if(district.getPlatform() == null){
			return district;
		}
		String platformId = district.getPlatform().getId();
		if( platformId == null){
			return district;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			district.setPlatform(platform);
		}
		
 		
 		return district;
 	}
 		
 
		
	protected void enhanceLocationList(SmartList<Location> locationList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected District extractLocationList(District district, Map<String,Object> options){
		
		
		if(district == null){
			return null;
		}
		if(district.getId() == null){
			return district;
		}

		
		
		SmartList<Location> locationList = getLocationDAO().findLocationByDistrict(district.getId(),options);
		if(locationList != null){
			enhanceLocationList(locationList,options);
			district.setLocationList(locationList);
		}
		
		return district;
	
	}	
	
	protected District analyzeLocationList(District district, Map<String,Object> options){
		
		
		if(district == null){
			return null;
		}
		if(district.getId() == null){
			return district;
		}

		
		
		SmartList<Location> locationList = district.getLocationList();
		if(locationList != null){
			getLocationDAO().analyzeLocationByDistrict(locationList, district.getId(), options);
			
		}
		
		return district;
	
	}	
	
		
		
  	
 	public SmartList<District> findDistrictByCity(String cityId,Map<String,Object> options){
 	
  		SmartList<District> resultList = queryWith(DistrictTable.COLUMN_CITY, cityId, options, getDistrictMapper());
		// analyzeDistrictByCity(resultList, cityId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<District> findDistrictByCity(String cityId, int start, int count,Map<String,Object> options){
 		
 		SmartList<District> resultList =  queryWithRange(DistrictTable.COLUMN_CITY, cityId, options, getDistrictMapper(), start, count);
 		//analyzeDistrictByCity(resultList, cityId, options);
 		return resultList;
 		
 	}
 	public void analyzeDistrictByCity(SmartList<District> resultList, String cityId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(District.CITY_PROPERTY, cityId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//District.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("区/县");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(District.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(District.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countDistrictByCity(String cityId,Map<String,Object> options){

 		return countWith(DistrictTable.COLUMN_CITY, cityId, options);
 	}
 	@Override
	public Map<String, Integer> countDistrictByCityIds(String[] ids, Map<String, Object> options) {
		return countWithIds(DistrictTable.COLUMN_CITY, ids, options);
	}
 	
  	
 	public SmartList<District> findDistrictByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<District> resultList = queryWith(DistrictTable.COLUMN_PLATFORM, platformId, options, getDistrictMapper());
		// analyzeDistrictByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<District> findDistrictByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<District> resultList =  queryWithRange(DistrictTable.COLUMN_PLATFORM, platformId, options, getDistrictMapper(), start, count);
 		//analyzeDistrictByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeDistrictByPlatform(SmartList<District> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(District.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//District.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("区/县");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(District.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(District.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countDistrictByPlatform(String platformId,Map<String,Object> options){

 		return countWith(DistrictTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countDistrictByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(DistrictTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected District saveDistrict(District  district){
		
		if(!district.isChanged()){
			return district;
		}
		
		
		String SQL=this.getSaveDistrictSQL(district);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveDistrictParameters(district);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		district.incVersion();
		return district;
	
	}
	public SmartList<District> saveDistrictList(SmartList<District> districtList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitDistrictList(districtList);
		
		batchDistrictCreate((List<District>)lists[CREATE_LIST_INDEX]);
		
		batchDistrictUpdate((List<District>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(District district:districtList){
			if(district.isChanged()){
				district.incVersion();
			}
			
		
		}
		
		
		return districtList;
	}

	public SmartList<District> removeDistrictList(SmartList<District> districtList,Map<String,Object> options){
		
		
		super.removeList(districtList, options);
		
		return districtList;
		
		
	}
	
	protected List<Object[]> prepareDistrictBatchCreateArgs(List<District> districtList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(District district:districtList ){
			Object [] parameters = prepareDistrictCreateParameters(district);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareDistrictBatchUpdateArgs(List<District> districtList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(District district:districtList ){
			if(!district.isChanged()){
				continue;
			}
			Object [] parameters = prepareDistrictUpdateParameters(district);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchDistrictCreate(List<District> districtList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareDistrictBatchCreateArgs(districtList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchDistrictUpdate(List<District> districtList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareDistrictBatchUpdateArgs(districtList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitDistrictList(List<District> districtList){
		
		List<District> districtCreateList=new ArrayList<District>();
		List<District> districtUpdateList=new ArrayList<District>();
		
		for(District district: districtList){
			if(isUpdateRequest(district)){
				districtUpdateList.add( district);
				continue;
			}
			districtCreateList.add(district);
		}
		
		return new Object[]{districtCreateList,districtUpdateList};
	}
	
	protected boolean isUpdateRequest(District district){
 		return district.getVersion() > 0;
 	}
 	protected String getSaveDistrictSQL(District district){
 		if(isUpdateRequest(district)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveDistrictParameters(District district){
 		if(isUpdateRequest(district) ){
 			return prepareDistrictUpdateParameters(district);
 		}
 		return prepareDistrictCreateParameters(district);
 	}
 	protected Object[] prepareDistrictUpdateParameters(District district){
 		Object[] parameters = new Object[7];
 
 		parameters[0] = district.getName(); 	
 		if(district.getCity() != null){
 			parameters[1] = district.getCity().getId();
 		}
  	
 		if(district.getPlatform() != null){
 			parameters[2] = district.getPlatform().getId();
 		}
 
 		parameters[3] = district.getCreateTime();		
 		parameters[4] = district.nextVersion();
 		parameters[5] = district.getId();
 		parameters[6] = district.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareDistrictCreateParameters(District district){
		Object[] parameters = new Object[5];
		String newDistrictId=getNextId();
		district.setId(newDistrictId);
		parameters[0] =  district.getId();
 
 		parameters[1] = district.getName(); 	
 		if(district.getCity() != null){
 			parameters[2] = district.getCity().getId();
 		
 		}
 		 	
 		if(district.getPlatform() != null){
 			parameters[3] = district.getPlatform().getId();
 		
 		}
 		
 		parameters[4] = district.getCreateTime();		
 				
 		return parameters;
 	}
 	
	protected District saveInternalDistrict(District district, Map<String,Object> options){
		
		saveDistrict(district);
 	
 		if(isSaveCityEnabled(options)){
	 		saveCity(district, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(district, options);
 		}
 
		
		if(isSaveLocationListEnabled(options)){
	 		saveLocationList(district, options);
	 		//removeLocationList(district, options);
	 		//Not delete the record
	 		
 		}		
		
		return district;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected District saveCity(District district, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(district.getCity() == null){
 			return district;//do nothing when it is null
 		}
 		
 		getCityDAO().save(district.getCity(),options);
 		return district;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected District savePlatform(District district, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(district.getPlatform() == null){
 			return district;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(district.getPlatform(),options);
 		return district;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public District planToRemoveLocationList(District district, String locationIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.DISTRICT_PROPERTY, district.getId());
		key.put(Location.ID_PROPERTY, locationIds);
		
		SmartList<Location> externalLocationList = getLocationDAO().
				findLocationWithKey(key, options);
		if(externalLocationList == null){
			return district;
		}
		if(externalLocationList.isEmpty()){
			return district;
		}
		
		for(Location locationItem: externalLocationList){

			locationItem.clearFromAll();
		}
		
		
		SmartList<Location> locationList = district.getLocationList();		
		locationList.addAllToRemoveList(externalLocationList);
		return district;	
	
	}


	//disconnect District with province in Location
	public District planToRemoveLocationListWithProvince(District district, String provinceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.DISTRICT_PROPERTY, district.getId());
		key.put(Location.PROVINCE_PROPERTY, provinceId);
		
		SmartList<Location> externalLocationList = getLocationDAO().
				findLocationWithKey(key, options);
		if(externalLocationList == null){
			return district;
		}
		if(externalLocationList.isEmpty()){
			return district;
		}
		
		for(Location locationItem: externalLocationList){
			locationItem.clearProvince();
			locationItem.clearDistrict();
			
		}
		
		
		SmartList<Location> locationList = district.getLocationList();		
		locationList.addAllToRemoveList(externalLocationList);
		return district;
	}
	
	public int countLocationListWithProvince(String districtId, String provinceId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.DISTRICT_PROPERTY, districtId);
		key.put(Location.PROVINCE_PROPERTY, provinceId);
		
		int count = getLocationDAO().countLocationWithKey(key, options);
		return count;
	}
	

		
	protected District saveLocationList(District district, Map<String,Object> options){
		
		
		
		
		SmartList<Location> locationList = district.getLocationList();
		if(locationList == null){
			//null list means nothing
			return district;
		}
		SmartList<Location> mergedUpdateLocationList = new SmartList<Location>();
		
		
		mergedUpdateLocationList.addAll(locationList); 
		if(locationList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateLocationList.addAll(locationList.getToRemoveList());
			locationList.removeAll(locationList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getLocationDAO().saveLocationList(mergedUpdateLocationList,options);
		
		if(locationList.getToRemoveList() != null){
			locationList.removeAll(locationList.getToRemoveList());
		}
		
		
		return district;
	
	}
	
	protected District removeLocationList(District district, Map<String,Object> options){
	
	
		SmartList<Location> locationList = district.getLocationList();
		if(locationList == null){
			return district;
		}	
	
		SmartList<Location> toRemoveLocationList = locationList.getToRemoveList();
		
		if(toRemoveLocationList == null){
			return district;
		}
		if(toRemoveLocationList.isEmpty()){
			return district;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getLocationDAO().removeLocationList(toRemoveLocationList,options);
		
		return district;
	
	}
	
	

 	
 	
	
	
	
		

	public District present(District district,Map<String, Object> options){
	
		presentLocationList(district,options);

		return district;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected District presentLocationList(
			District district,
			Map<String, Object> options) {

		SmartList<Location> locationList = district.getLocationList();		
				SmartList<Location> newList= presentSubList(district.getId(),
				locationList,
				options,
				getLocationDAO()::countLocationByDistrict,
				getLocationDAO()::findLocationByDistrict
				);

		
		district.setLocationList(newList);
		

		return district;
	}			
		

	
    public SmartList<District> requestCandidateDistrictForLocation(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(DistrictTable.COLUMN_NAME, filterKey, pageNo, pageSize, getDistrictMapper());
    }
		

	protected String getTableName(){
		return DistrictTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<District> districtList) {		
		this.enhanceListInternal(districtList, this.getDistrictMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Location的district的LocationList
	public SmartList<Location> loadOurLocationList(HealthUserContext userContext, List<District> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.DISTRICT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Location> loadedObjs = userContext.getDAOGroup().getLocationDAO().findLocationWithKey(key, options);
		Map<String, List<Location>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getDistrict().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Location> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Location> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setLocationList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<District> districtList = ownerEntity.collectRefsWithType(District.INTERNAL_TYPE);
		this.enhanceList(districtList);
		
	}
	
	@Override
	public SmartList<District> findDistrictWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getDistrictMapper());

	}
	@Override
	public int countDistrictWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countDistrictWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<District> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getDistrictMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


