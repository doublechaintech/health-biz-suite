
package com.doublechaintech.health.province;

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
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.location.Location;

import com.doublechaintech.health.city.CityDAO;
import com.doublechaintech.health.location.LocationDAO;
import com.doublechaintech.health.platform.PlatformDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class ProvinceJDBCTemplateDAO extends HealthBaseDAOImpl implements ProvinceDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  CityDAO  cityDAO;
 	public void setCityDAO(CityDAO pCityDAO){
 	
 		if(pCityDAO == null){
 			throw new IllegalStateException("Do not try to set cityDAO to null.");
 		}
	 	this.cityDAO = pCityDAO;
 	}
 	public CityDAO getCityDAO(){
 		if(this.cityDAO == null){
 			throw new IllegalStateException("The cityDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.cityDAO;
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
	protected Province load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalProvince(accessKey, options);
	}
	*/
	
	public SmartList<Province> loadAll() {
	    return this.loadAll(getProvinceMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Province load(String id,Map<String,Object> options) throws Exception{
		return loadInternalProvince(ProvinceTable.withId(id), options);
	}
	
	
	
	public Province save(Province province,Map<String,Object> options){
		
		String methodName="save(Province province,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(province, methodName, "province");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalProvince(province,options);
	}
	public Province clone(String provinceId, Map<String,Object> options) throws Exception{
	
		return clone(ProvinceTable.withId(provinceId),options);
	}
	
	protected Province clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String provinceId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Province newProvince = loadInternalProvince(accessKey, options);
		newProvince.setVersion(0);
		
		
 		
 		if(isSaveCityListEnabled(options)){
 			for(City item: newProvince.getCityList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveLocationListEnabled(options)){
 			for(Location item: newProvince.getLocationList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalProvince(newProvince,options);
		
		return newProvince;
	}
	
	
	
	

	protected void throwIfHasException(String provinceId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ProvinceVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ProvinceNotFoundException(
					"The " + this.getTableName() + "(" + provinceId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String provinceId, int version) throws Exception{
	
		String methodName="delete(String provinceId, int version)";
		assertMethodArgumentNotNull(provinceId, methodName, "provinceId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{provinceId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(provinceId,version);
		}
		
	
	}
	
	
	
	
	

	public Province disconnectFromAll(String provinceId, int version) throws Exception{
	
		
		Province province = loadInternalProvince(ProvinceTable.withId(provinceId), emptyOptions());
		province.clearFromAll();
		this.saveProvince(province);
		return province;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ProvinceTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "province";
	}
	@Override
	protected String getBeanName() {
		
		return "province";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ProvinceTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ProvinceTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ProvinceTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractCityListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProvinceTokens.CITY_LIST);
 	}
 	protected boolean isAnalyzeCityListEnabled(Map<String,Object> options){		 		
 		return ProvinceTokens.of(options).analyzeCityListEnabled();
 	}
	
	protected boolean isSaveCityListEnabled(Map<String,Object> options){
		return checkOptions(options, ProvinceTokens.CITY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractLocationListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProvinceTokens.LOCATION_LIST);
 	}
 	protected boolean isAnalyzeLocationListEnabled(Map<String,Object> options){		 		
 		return ProvinceTokens.of(options).analyzeLocationListEnabled();
 	}
	
	protected boolean isSaveLocationListEnabled(Map<String,Object> options){
		return checkOptions(options, ProvinceTokens.LOCATION_LIST);
		
 	}
 	
		

	

	protected ProvinceMapper getProvinceMapper(){
		return new ProvinceMapper();
	}

	
	
	protected Province extractProvince(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Province province = loadSingleObject(accessKey, getProvinceMapper());
			return province;
		}catch(EmptyResultDataAccessException e){
			throw new ProvinceNotFoundException("Province("+accessKey+") is not found!");
		}

	}

	
	

	protected Province loadInternalProvince(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Province province = extractProvince(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(province, loadOptions);
 		}
 
		
		if(isExtractCityListEnabled(loadOptions)){
	 		extractCityList(province, loadOptions);
 		}	
 		if(isAnalyzeCityListEnabled(loadOptions)){
	 		analyzeCityList(province, loadOptions);
 		}
 		
		
		if(isExtractLocationListEnabled(loadOptions)){
	 		extractLocationList(province, loadOptions);
 		}	
 		if(isAnalyzeLocationListEnabled(loadOptions)){
	 		analyzeLocationList(province, loadOptions);
 		}
 		
		
		return province;
		
	}

	 

 	protected Province extractPlatform(Province province, Map<String,Object> options) throws Exception{

		if(province.getPlatform() == null){
			return province;
		}
		String platformId = province.getPlatform().getId();
		if( platformId == null){
			return province;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			province.setPlatform(platform);
		}
		
 		
 		return province;
 	}
 		
 
		
	protected void enhanceCityList(SmartList<City> cityList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Province extractCityList(Province province, Map<String,Object> options){
		
		
		if(province == null){
			return null;
		}
		if(province.getId() == null){
			return province;
		}

		
		
		SmartList<City> cityList = getCityDAO().findCityByProvince(province.getId(),options);
		if(cityList != null){
			enhanceCityList(cityList,options);
			province.setCityList(cityList);
		}
		
		return province;
	
	}	
	
	protected Province analyzeCityList(Province province, Map<String,Object> options){
		
		
		if(province == null){
			return null;
		}
		if(province.getId() == null){
			return province;
		}

		
		
		SmartList<City> cityList = province.getCityList();
		if(cityList != null){
			getCityDAO().analyzeCityByProvince(cityList, province.getId(), options);
			
		}
		
		return province;
	
	}	
	
		
	protected void enhanceLocationList(SmartList<Location> locationList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Province extractLocationList(Province province, Map<String,Object> options){
		
		
		if(province == null){
			return null;
		}
		if(province.getId() == null){
			return province;
		}

		
		
		SmartList<Location> locationList = getLocationDAO().findLocationByProvince(province.getId(),options);
		if(locationList != null){
			enhanceLocationList(locationList,options);
			province.setLocationList(locationList);
		}
		
		return province;
	
	}	
	
	protected Province analyzeLocationList(Province province, Map<String,Object> options){
		
		
		if(province == null){
			return null;
		}
		if(province.getId() == null){
			return province;
		}

		
		
		SmartList<Location> locationList = province.getLocationList();
		if(locationList != null){
			getLocationDAO().analyzeLocationByProvince(locationList, province.getId(), options);
			
		}
		
		return province;
	
	}	
	
		
		
  	
 	public SmartList<Province> findProvinceByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Province> resultList = queryWith(ProvinceTable.COLUMN_PLATFORM, platformId, options, getProvinceMapper());
		// analyzeProvinceByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Province> findProvinceByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Province> resultList =  queryWithRange(ProvinceTable.COLUMN_PLATFORM, platformId, options, getProvinceMapper(), start, count);
 		//analyzeProvinceByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeProvinceByPlatform(SmartList<Province> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Province.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Province.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("省");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Province.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Province.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countProvinceByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ProvinceTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countProvinceByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ProvinceTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected Province saveProvince(Province  province){
		
		if(!province.isChanged()){
			return province;
		}
		
		
		String SQL=this.getSaveProvinceSQL(province);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveProvinceParameters(province);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		province.incVersion();
		return province;
	
	}
	public SmartList<Province> saveProvinceList(SmartList<Province> provinceList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitProvinceList(provinceList);
		
		batchProvinceCreate((List<Province>)lists[CREATE_LIST_INDEX]);
		
		batchProvinceUpdate((List<Province>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Province province:provinceList){
			if(province.isChanged()){
				province.incVersion();
			}
			
		
		}
		
		
		return provinceList;
	}

	public SmartList<Province> removeProvinceList(SmartList<Province> provinceList,Map<String,Object> options){
		
		
		super.removeList(provinceList, options);
		
		return provinceList;
		
		
	}
	
	protected List<Object[]> prepareProvinceBatchCreateArgs(List<Province> provinceList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Province province:provinceList ){
			Object [] parameters = prepareProvinceCreateParameters(province);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareProvinceBatchUpdateArgs(List<Province> provinceList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Province province:provinceList ){
			if(!province.isChanged()){
				continue;
			}
			Object [] parameters = prepareProvinceUpdateParameters(province);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchProvinceCreate(List<Province> provinceList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareProvinceBatchCreateArgs(provinceList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchProvinceUpdate(List<Province> provinceList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareProvinceBatchUpdateArgs(provinceList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitProvinceList(List<Province> provinceList){
		
		List<Province> provinceCreateList=new ArrayList<Province>();
		List<Province> provinceUpdateList=new ArrayList<Province>();
		
		for(Province province: provinceList){
			if(isUpdateRequest(province)){
				provinceUpdateList.add( province);
				continue;
			}
			provinceCreateList.add(province);
		}
		
		return new Object[]{provinceCreateList,provinceUpdateList};
	}
	
	protected boolean isUpdateRequest(Province province){
 		return province.getVersion() > 0;
 	}
 	protected String getSaveProvinceSQL(Province province){
 		if(isUpdateRequest(province)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveProvinceParameters(Province province){
 		if(isUpdateRequest(province) ){
 			return prepareProvinceUpdateParameters(province);
 		}
 		return prepareProvinceCreateParameters(province);
 	}
 	protected Object[] prepareProvinceUpdateParameters(Province province){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = province.getName(); 	
 		if(province.getPlatform() != null){
 			parameters[1] = province.getPlatform().getId();
 		}
 
 		parameters[2] = province.getCreateTime();		
 		parameters[3] = province.nextVersion();
 		parameters[4] = province.getId();
 		parameters[5] = province.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareProvinceCreateParameters(Province province){
		Object[] parameters = new Object[4];
		String newProvinceId=getNextId();
		province.setId(newProvinceId);
		parameters[0] =  province.getId();
 
 		parameters[1] = province.getName(); 	
 		if(province.getPlatform() != null){
 			parameters[2] = province.getPlatform().getId();
 		
 		}
 		
 		parameters[3] = province.getCreateTime();		
 				
 		return parameters;
 	}
 	
	protected Province saveInternalProvince(Province province, Map<String,Object> options){
		
		saveProvince(province);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(province, options);
 		}
 
		
		if(isSaveCityListEnabled(options)){
	 		saveCityList(province, options);
	 		//removeCityList(province, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveLocationListEnabled(options)){
	 		saveLocationList(province, options);
	 		//removeLocationList(province, options);
	 		//Not delete the record
	 		
 		}		
		
		return province;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Province savePlatform(Province province, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(province.getPlatform() == null){
 			return province;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(province.getPlatform(),options);
 		return province;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Province planToRemoveCityList(Province province, String cityIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PROVINCE_PROPERTY, province.getId());
		key.put(City.ID_PROPERTY, cityIds);
		
		SmartList<City> externalCityList = getCityDAO().
				findCityWithKey(key, options);
		if(externalCityList == null){
			return province;
		}
		if(externalCityList.isEmpty()){
			return province;
		}
		
		for(City cityItem: externalCityList){

			cityItem.clearFromAll();
		}
		
		
		SmartList<City> cityList = province.getCityList();		
		cityList.addAllToRemoveList(externalCityList);
		return province;	
	
	}


	//disconnect Province with platform in City
	public Province planToRemoveCityListWithPlatform(Province province, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PROVINCE_PROPERTY, province.getId());
		key.put(City.PLATFORM_PROPERTY, platformId);
		
		SmartList<City> externalCityList = getCityDAO().
				findCityWithKey(key, options);
		if(externalCityList == null){
			return province;
		}
		if(externalCityList.isEmpty()){
			return province;
		}
		
		for(City cityItem: externalCityList){
			cityItem.clearPlatform();
			cityItem.clearProvince();
			
		}
		
		
		SmartList<City> cityList = province.getCityList();		
		cityList.addAllToRemoveList(externalCityList);
		return province;
	}
	
	public int countCityListWithPlatform(String provinceId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PROVINCE_PROPERTY, provinceId);
		key.put(City.PLATFORM_PROPERTY, platformId);
		
		int count = getCityDAO().countCityWithKey(key, options);
		return count;
	}
	
	public Province planToRemoveLocationList(Province province, String locationIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.PROVINCE_PROPERTY, province.getId());
		key.put(Location.ID_PROPERTY, locationIds);
		
		SmartList<Location> externalLocationList = getLocationDAO().
				findLocationWithKey(key, options);
		if(externalLocationList == null){
			return province;
		}
		if(externalLocationList.isEmpty()){
			return province;
		}
		
		for(Location locationItem: externalLocationList){

			locationItem.clearFromAll();
		}
		
		
		SmartList<Location> locationList = province.getLocationList();		
		locationList.addAllToRemoveList(externalLocationList);
		return province;	
	
	}


	//disconnect Province with district in Location
	public Province planToRemoveLocationListWithDistrict(Province province, String districtId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.PROVINCE_PROPERTY, province.getId());
		key.put(Location.DISTRICT_PROPERTY, districtId);
		
		SmartList<Location> externalLocationList = getLocationDAO().
				findLocationWithKey(key, options);
		if(externalLocationList == null){
			return province;
		}
		if(externalLocationList.isEmpty()){
			return province;
		}
		
		for(Location locationItem: externalLocationList){
			locationItem.clearDistrict();
			locationItem.clearProvince();
			
		}
		
		
		SmartList<Location> locationList = province.getLocationList();		
		locationList.addAllToRemoveList(externalLocationList);
		return province;
	}
	
	public int countLocationListWithDistrict(String provinceId, String districtId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.PROVINCE_PROPERTY, provinceId);
		key.put(Location.DISTRICT_PROPERTY, districtId);
		
		int count = getLocationDAO().countLocationWithKey(key, options);
		return count;
	}
	

		
	protected Province saveCityList(Province province, Map<String,Object> options){
		
		
		
		
		SmartList<City> cityList = province.getCityList();
		if(cityList == null){
			//null list means nothing
			return province;
		}
		SmartList<City> mergedUpdateCityList = new SmartList<City>();
		
		
		mergedUpdateCityList.addAll(cityList); 
		if(cityList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateCityList.addAll(cityList.getToRemoveList());
			cityList.removeAll(cityList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getCityDAO().saveCityList(mergedUpdateCityList,options);
		
		if(cityList.getToRemoveList() != null){
			cityList.removeAll(cityList.getToRemoveList());
		}
		
		
		return province;
	
	}
	
	protected Province removeCityList(Province province, Map<String,Object> options){
	
	
		SmartList<City> cityList = province.getCityList();
		if(cityList == null){
			return province;
		}	
	
		SmartList<City> toRemoveCityList = cityList.getToRemoveList();
		
		if(toRemoveCityList == null){
			return province;
		}
		if(toRemoveCityList.isEmpty()){
			return province;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getCityDAO().removeCityList(toRemoveCityList,options);
		
		return province;
	
	}
	
	

 	
 	
	
	
	
		
	protected Province saveLocationList(Province province, Map<String,Object> options){
		
		
		
		
		SmartList<Location> locationList = province.getLocationList();
		if(locationList == null){
			//null list means nothing
			return province;
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
		
		
		return province;
	
	}
	
	protected Province removeLocationList(Province province, Map<String,Object> options){
	
	
		SmartList<Location> locationList = province.getLocationList();
		if(locationList == null){
			return province;
		}	
	
		SmartList<Location> toRemoveLocationList = locationList.getToRemoveList();
		
		if(toRemoveLocationList == null){
			return province;
		}
		if(toRemoveLocationList.isEmpty()){
			return province;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getLocationDAO().removeLocationList(toRemoveLocationList,options);
		
		return province;
	
	}
	
	

 	
 	
	
	
	
		

	public Province present(Province province,Map<String, Object> options){
	
		presentCityList(province,options);
		presentLocationList(province,options);

		return province;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Province presentCityList(
			Province province,
			Map<String, Object> options) {

		SmartList<City> cityList = province.getCityList();		
				SmartList<City> newList= presentSubList(province.getId(),
				cityList,
				options,
				getCityDAO()::countCityByProvince,
				getCityDAO()::findCityByProvince
				);

		
		province.setCityList(newList);
		

		return province;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Province presentLocationList(
			Province province,
			Map<String, Object> options) {

		SmartList<Location> locationList = province.getLocationList();		
				SmartList<Location> newList= presentSubList(province.getId(),
				locationList,
				options,
				getLocationDAO()::countLocationByProvince,
				getLocationDAO()::findLocationByProvince
				);

		
		province.setLocationList(newList);
		

		return province;
	}			
		

	
    public SmartList<Province> requestCandidateProvinceForCity(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProvinceTable.COLUMN_NAME, filterKey, pageNo, pageSize, getProvinceMapper());
    }
		
    public SmartList<Province> requestCandidateProvinceForLocation(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProvinceTable.COLUMN_NAME, filterKey, pageNo, pageSize, getProvinceMapper());
    }
		

	protected String getTableName(){
		return ProvinceTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Province> provinceList) {		
		this.enhanceListInternal(provinceList, this.getProvinceMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:City的province的CityList
	public SmartList<City> loadOurCityList(HealthUserContext userContext, List<Province> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(City.PROVINCE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<City> loadedObjs = userContext.getDAOGroup().getCityDAO().findCityWithKey(key, options);
		Map<String, List<City>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProvince().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<City> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<City> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setCityList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Location的province的LocationList
	public SmartList<Location> loadOurLocationList(HealthUserContext userContext, List<Province> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Location.PROVINCE_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Location> loadedObjs = userContext.getDAOGroup().getLocationDAO().findLocationWithKey(key, options);
		Map<String, List<Location>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProvince().getId()));
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
		List<Province> provinceList = ownerEntity.collectRefsWithType(Province.INTERNAL_TYPE);
		this.enhanceList(provinceList);
		
	}
	
	@Override
	public SmartList<Province> findProvinceWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getProvinceMapper());

	}
	@Override
	public int countProvinceWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countProvinceWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Province> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getProvinceMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


