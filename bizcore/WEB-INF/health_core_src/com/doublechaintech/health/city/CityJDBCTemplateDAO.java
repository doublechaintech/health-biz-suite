
package com.doublechaintech.health.city;

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
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.district.District;

import com.doublechaintech.health.province.ProvinceDAO;
import com.doublechaintech.health.platform.PlatformDAO;
import com.doublechaintech.health.district.DistrictDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class CityJDBCTemplateDAO extends HealthBaseDAOImpl implements CityDAO{
 
 	
 	private  ProvinceDAO  provinceDAO;
 	public void setProvinceDAO(ProvinceDAO provinceDAO){
	 	this.provinceDAO = provinceDAO;
 	}
 	public ProvinceDAO getProvinceDAO(){
	 	return this.provinceDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  DistrictDAO  districtDAO;
 	public void setDistrictDAO(DistrictDAO pDistrictDAO){
 	
 		if(pDistrictDAO == null){
 			throw new IllegalStateException("Do not try to set districtDAO to null.");
 		}
	 	this.districtDAO = pDistrictDAO;
 	}
 	public DistrictDAO getDistrictDAO(){
 		if(this.districtDAO == null){
 			throw new IllegalStateException("The districtDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.districtDAO;
 	}	
 	
			
		

	
	/*
	protected City load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalCity(accessKey, options);
	}
	*/
	
	public SmartList<City> loadAll() {
	    return this.loadAll(getCityMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public City load(String id,Map<String,Object> options) throws Exception{
		return loadInternalCity(CityTable.withId(id), options);
	}
	
	
	
	public City save(City city,Map<String,Object> options){
		
		String methodName="save(City city,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(city, methodName, "city");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalCity(city,options);
	}
	public City clone(String cityId, Map<String,Object> options) throws Exception{
	
		return clone(CityTable.withId(cityId),options);
	}
	
	protected City clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String cityId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		City newCity = loadInternalCity(accessKey, options);
		newCity.setVersion(0);
		
		
 		
 		if(isSaveDistrictListEnabled(options)){
 			for(District item: newCity.getDistrictList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalCity(newCity,options);
		
		return newCity;
	}
	
	
	
	

	protected void throwIfHasException(String cityId,int version,int count) throws Exception{
		if (count == 1) {
			throw new CityVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new CityNotFoundException(
					"The " + this.getTableName() + "(" + cityId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String cityId, int version) throws Exception{
	
		String methodName="delete(String cityId, int version)";
		assertMethodArgumentNotNull(cityId, methodName, "cityId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{cityId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(cityId,version);
		}
		
	
	}
	
	
	
	
	

	public City disconnectFromAll(String cityId, int version) throws Exception{
	
		
		City city = loadInternalCity(CityTable.withId(cityId), emptyOptions());
		city.clearFromAll();
		this.saveCity(city);
		return city;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return CityTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "city";
	}
	@Override
	protected String getBeanName() {
		
		return "city";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return CityTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractProvinceEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, CityTokens.PROVINCE);
 	}

 	protected boolean isSaveProvinceEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, CityTokens.PROVINCE);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, CityTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, CityTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractDistrictListEnabled(Map<String,Object> options){		
 		return checkOptions(options,CityTokens.DISTRICT_LIST);
 	}
 	protected boolean isAnalyzeDistrictListEnabled(Map<String,Object> options){		 		
 		return CityTokens.of(options).analyzeDistrictListEnabled();
 	}
	
	protected boolean isSaveDistrictListEnabled(Map<String,Object> options){
		return checkOptions(options, CityTokens.DISTRICT_LIST);
		
 	}
 	
		

	

	protected CityMapper getCityMapper(){
		return new CityMapper();
	}

	
	
	protected City extractCity(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			City city = loadSingleObject(accessKey, getCityMapper());
			return city;
		}catch(EmptyResultDataAccessException e){
			throw new CityNotFoundException("City("+accessKey+") is not found!");
		}

	}

	
	

	protected City loadInternalCity(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		City city = extractCity(accessKey, loadOptions);
 	
 		if(isExtractProvinceEnabled(loadOptions)){
	 		extractProvince(city, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(city, loadOptions);
 		}
 
		
		if(isExtractDistrictListEnabled(loadOptions)){
	 		extractDistrictList(city, loadOptions);
 		}	
 		if(isAnalyzeDistrictListEnabled(loadOptions)){
	 		analyzeDistrictList(city, loadOptions);
 		}
 		
		
		return city;
		
	}

	 

 	protected City extractProvince(City city, Map<String,Object> options) throws Exception{

		if(city.getProvince() == null){
			return city;
		}
		String provinceId = city.getProvince().getId();
		if( provinceId == null){
			return city;
		}
		Province province = getProvinceDAO().load(provinceId,options);
		if(province != null){
			city.setProvince(province);
		}
		
 		
 		return city;
 	}
 		
  

 	protected City extractPlatform(City city, Map<String,Object> options) throws Exception{

		if(city.getPlatform() == null){
			return city;
		}
		String platformId = city.getPlatform().getId();
		if( platformId == null){
			return city;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			city.setPlatform(platform);
		}
		
 		
 		return city;
 	}
 		
 
		
	protected void enhanceDistrictList(SmartList<District> districtList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected City extractDistrictList(City city, Map<String,Object> options){
		
		
		if(city == null){
			return null;
		}
		if(city.getId() == null){
			return city;
		}

		
		
		SmartList<District> districtList = getDistrictDAO().findDistrictByCity(city.getId(),options);
		if(districtList != null){
			enhanceDistrictList(districtList,options);
			city.setDistrictList(districtList);
		}
		
		return city;
	
	}	
	
	protected City analyzeDistrictList(City city, Map<String,Object> options){
		
		
		if(city == null){
			return null;
		}
		if(city.getId() == null){
			return city;
		}

		
		
		SmartList<District> districtList = city.getDistrictList();
		if(districtList != null){
			getDistrictDAO().analyzeDistrictByCity(districtList, city.getId(), options);
			
		}
		
		return city;
	
	}	
	
		
		
  	
 	public SmartList<City> findCityByProvince(String provinceId,Map<String,Object> options){
 	
  		SmartList<City> resultList = queryWith(CityTable.COLUMN_PROVINCE, provinceId, options, getCityMapper());
		// analyzeCityByProvince(resultList, provinceId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<City> findCityByProvince(String provinceId, int start, int count,Map<String,Object> options){
 		
 		SmartList<City> resultList =  queryWithRange(CityTable.COLUMN_PROVINCE, provinceId, options, getCityMapper(), start, count);
 		//analyzeCityByProvince(resultList, provinceId, options);
 		return resultList;
 		
 	}
 	public void analyzeCityByProvince(SmartList<City> resultList, String provinceId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(City.PROVINCE_PROPERTY, provinceId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//City.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("城市");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(City.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(City.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countCityByProvince(String provinceId,Map<String,Object> options){

 		return countWith(CityTable.COLUMN_PROVINCE, provinceId, options);
 	}
 	@Override
	public Map<String, Integer> countCityByProvinceIds(String[] ids, Map<String, Object> options) {
		return countWithIds(CityTable.COLUMN_PROVINCE, ids, options);
	}
 	
  	
 	public SmartList<City> findCityByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<City> resultList = queryWith(CityTable.COLUMN_PLATFORM, platformId, options, getCityMapper());
		// analyzeCityByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<City> findCityByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<City> resultList =  queryWithRange(CityTable.COLUMN_PLATFORM, platformId, options, getCityMapper(), start, count);
 		//analyzeCityByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeCityByPlatform(SmartList<City> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(City.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//City.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("城市");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(City.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(City.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countCityByPlatform(String platformId,Map<String,Object> options){

 		return countWith(CityTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countCityByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(CityTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected City saveCity(City  city){
		
		if(!city.isChanged()){
			return city;
		}
		
		
		String SQL=this.getSaveCitySQL(city);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveCityParameters(city);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		city.incVersion();
		return city;
	
	}
	public SmartList<City> saveCityList(SmartList<City> cityList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitCityList(cityList);
		
		batchCityCreate((List<City>)lists[CREATE_LIST_INDEX]);
		
		batchCityUpdate((List<City>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(City city:cityList){
			if(city.isChanged()){
				city.incVersion();
			}
			
		
		}
		
		
		return cityList;
	}

	public SmartList<City> removeCityList(SmartList<City> cityList,Map<String,Object> options){
		
		
		super.removeList(cityList, options);
		
		return cityList;
		
		
	}
	
	protected List<Object[]> prepareCityBatchCreateArgs(List<City> cityList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(City city:cityList ){
			Object [] parameters = prepareCityCreateParameters(city);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareCityBatchUpdateArgs(List<City> cityList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(City city:cityList ){
			if(!city.isChanged()){
				continue;
			}
			Object [] parameters = prepareCityUpdateParameters(city);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchCityCreate(List<City> cityList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareCityBatchCreateArgs(cityList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchCityUpdate(List<City> cityList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareCityBatchUpdateArgs(cityList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitCityList(List<City> cityList){
		
		List<City> cityCreateList=new ArrayList<City>();
		List<City> cityUpdateList=new ArrayList<City>();
		
		for(City city: cityList){
			if(isUpdateRequest(city)){
				cityUpdateList.add( city);
				continue;
			}
			cityCreateList.add(city);
		}
		
		return new Object[]{cityCreateList,cityUpdateList};
	}
	
	protected boolean isUpdateRequest(City city){
 		return city.getVersion() > 0;
 	}
 	protected String getSaveCitySQL(City city){
 		if(isUpdateRequest(city)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveCityParameters(City city){
 		if(isUpdateRequest(city) ){
 			return prepareCityUpdateParameters(city);
 		}
 		return prepareCityCreateParameters(city);
 	}
 	protected Object[] prepareCityUpdateParameters(City city){
 		Object[] parameters = new Object[7];
 
 		
 		parameters[0] = city.getName();
 		 	
 		if(city.getProvince() != null){
 			parameters[1] = city.getProvince().getId();
 		}
  	
 		if(city.getPlatform() != null){
 			parameters[2] = city.getPlatform().getId();
 		}
 
 		
 		parameters[3] = city.getCreateTime();
 				
 		parameters[4] = city.nextVersion();
 		parameters[5] = city.getId();
 		parameters[6] = city.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareCityCreateParameters(City city){
		Object[] parameters = new Object[5];
		String newCityId=getNextId();
		city.setId(newCityId);
		parameters[0] =  city.getId();
 
 		
 		parameters[1] = city.getName();
 		 	
 		if(city.getProvince() != null){
 			parameters[2] = city.getProvince().getId();
 		
 		}
 		 	
 		if(city.getPlatform() != null){
 			parameters[3] = city.getPlatform().getId();
 		
 		}
 		
 		
 		parameters[4] = city.getCreateTime();
 				
 				
 		return parameters;
 	}
 	
	protected City saveInternalCity(City city, Map<String,Object> options){
		
		saveCity(city);
 	
 		if(isSaveProvinceEnabled(options)){
	 		saveProvince(city, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(city, options);
 		}
 
		
		if(isSaveDistrictListEnabled(options)){
	 		saveDistrictList(city, options);
	 		//removeDistrictList(city, options);
	 		//Not delete the record
	 		
 		}		
		
		return city;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected City saveProvince(City city, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(city.getProvince() == null){
 			return city;//do nothing when it is null
 		}
 		
 		getProvinceDAO().save(city.getProvince(),options);
 		return city;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected City savePlatform(City city, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(city.getPlatform() == null){
 			return city;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(city.getPlatform(),options);
 		return city;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public City planToRemoveDistrictList(City city, String districtIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.CITY_PROPERTY, city.getId());
		key.put(District.ID_PROPERTY, districtIds);
		
		SmartList<District> externalDistrictList = getDistrictDAO().
				findDistrictWithKey(key, options);
		if(externalDistrictList == null){
			return city;
		}
		if(externalDistrictList.isEmpty()){
			return city;
		}
		
		for(District districtItem: externalDistrictList){

			districtItem.clearFromAll();
		}
		
		
		SmartList<District> districtList = city.getDistrictList();		
		districtList.addAllToRemoveList(externalDistrictList);
		return city;	
	
	}


	//disconnect City with platform in District
	public City planToRemoveDistrictListWithPlatform(City city, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.CITY_PROPERTY, city.getId());
		key.put(District.PLATFORM_PROPERTY, platformId);
		
		SmartList<District> externalDistrictList = getDistrictDAO().
				findDistrictWithKey(key, options);
		if(externalDistrictList == null){
			return city;
		}
		if(externalDistrictList.isEmpty()){
			return city;
		}
		
		for(District districtItem: externalDistrictList){
			districtItem.clearPlatform();
			districtItem.clearCity();
			
		}
		
		
		SmartList<District> districtList = city.getDistrictList();		
		districtList.addAllToRemoveList(externalDistrictList);
		return city;
	}
	
	public int countDistrictListWithPlatform(String cityId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.CITY_PROPERTY, cityId);
		key.put(District.PLATFORM_PROPERTY, platformId);
		
		int count = getDistrictDAO().countDistrictWithKey(key, options);
		return count;
	}
	

		
	protected City saveDistrictList(City city, Map<String,Object> options){
		
		
		
		
		SmartList<District> districtList = city.getDistrictList();
		if(districtList == null){
			//null list means nothing
			return city;
		}
		SmartList<District> mergedUpdateDistrictList = new SmartList<District>();
		
		
		mergedUpdateDistrictList.addAll(districtList); 
		if(districtList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateDistrictList.addAll(districtList.getToRemoveList());
			districtList.removeAll(districtList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getDistrictDAO().saveDistrictList(mergedUpdateDistrictList,options);
		
		if(districtList.getToRemoveList() != null){
			districtList.removeAll(districtList.getToRemoveList());
		}
		
		
		return city;
	
	}
	
	protected City removeDistrictList(City city, Map<String,Object> options){
	
	
		SmartList<District> districtList = city.getDistrictList();
		if(districtList == null){
			return city;
		}	
	
		SmartList<District> toRemoveDistrictList = districtList.getToRemoveList();
		
		if(toRemoveDistrictList == null){
			return city;
		}
		if(toRemoveDistrictList.isEmpty()){
			return city;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getDistrictDAO().removeDistrictList(toRemoveDistrictList,options);
		
		return city;
	
	}
	
	

 	
 	
	
	
	
		

	public City present(City city,Map<String, Object> options){
	
		presentDistrictList(city,options);

		return city;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected City presentDistrictList(
			City city,
			Map<String, Object> options) {

		SmartList<District> districtList = city.getDistrictList();		
				SmartList<District> newList= presentSubList(city.getId(),
				districtList,
				options,
				getDistrictDAO()::countDistrictByCity,
				getDistrictDAO()::findDistrictByCity
				);

		
		city.setDistrictList(newList);
		

		return city;
	}			
		

	
    public SmartList<City> requestCandidateCityForDistrict(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(CityTable.COLUMN_NAME, filterKey, pageNo, pageSize, getCityMapper());
    }
		

	protected String getTableName(){
		return CityTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<City> cityList) {		
		this.enhanceListInternal(cityList, this.getCityMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:District的city的DistrictList
	public SmartList<District> loadOurDistrictList(HealthUserContext userContext, List<City> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(District.CITY_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<District> loadedObjs = userContext.getDAOGroup().getDistrictDAO().findDistrictWithKey(key, options);
		Map<String, List<District>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCity().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<District> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<District> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setDistrictList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<City> cityList = ownerEntity.collectRefsWithType(City.INTERNAL_TYPE);
		this.enhanceList(cityList);
		
	}
	
	@Override
	public SmartList<City> findCityWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getCityMapper());

	}
	@Override
	public int countCityWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countCityWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<City> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getCityMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	
	

}


