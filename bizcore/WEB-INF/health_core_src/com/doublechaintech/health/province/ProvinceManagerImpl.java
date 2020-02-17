
package com.doublechaintech.health.province;

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
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.location.Location;

import com.doublechaintech.health.platform.CandidatePlatform;

import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.district.District;






public class ProvinceManagerImpl extends CustomHealthCheckerManager implements ProvinceManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "Province";
	@Override
	public ProvinceDAO daoOf(HealthUserContext userContext) {
		return provinceDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ProvinceManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ProvinceManagerException(message);

	}



 	protected Province saveProvince(HealthUserContext userContext, Province province, String [] tokensExpr) throws Exception{	
 		//return getProvinceDAO().save(province, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveProvince(userContext, province, tokens);
 	}
 	
 	protected Province saveProvinceDetail(HealthUserContext userContext, Province province) throws Exception{	

 		
 		return saveProvince(userContext, province, allTokens());
 	}
 	
 	public Province loadProvince(HealthUserContext userContext, String provinceId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).throwExceptionIfHasErrors( ProvinceManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Province province = loadProvince( userContext, provinceId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,province, tokens);
 	}
 	
 	
 	 public Province searchProvince(HealthUserContext userContext, String provinceId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).throwExceptionIfHasErrors( ProvinceManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Province province = loadProvince( userContext, provinceId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,province, tokens);
 	}
 	
 	

 	protected Province present(HealthUserContext userContext, Province province, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,province,tokens);
		
		
		Province  provinceToPresent = provinceDaoOf(userContext).present(province, tokens);
		
		List<BaseEntity> entityListToNaming = provinceToPresent.collectRefercencesFromLists();
		provinceDaoOf(userContext).alias(entityListToNaming);
		
		return  provinceToPresent;
		
		
	}
 
 	
 	
 	public Province loadProvinceDetail(HealthUserContext userContext, String provinceId) throws Exception{	
 		Province province = loadProvince( userContext, provinceId, allTokens());
 		return present(userContext,province, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String provinceId) throws Exception{	
 		Province province = loadProvince( userContext, provinceId, viewTokens());
 		return present(userContext,province, allTokens());
		
 	}
 	protected Province saveProvince(HealthUserContext userContext, Province province, Map<String,Object>tokens) throws Exception{	
 		return provinceDaoOf(userContext).save(province, tokens);
 	}
 	protected Province loadProvince(HealthUserContext userContext, String provinceId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).throwExceptionIfHasErrors( ProvinceManagerException.class);

 
 		return provinceDaoOf(userContext).load(provinceId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Province province, Map<String, Object> tokens){
		super.addActions(userContext, province, tokens);
		
		addAction(userContext, province, tokens,"@create","createProvince","createProvince/","main","primary");
		addAction(userContext, province, tokens,"@update","updateProvince","updateProvince/"+province.getId()+"/","main","primary");
		addAction(userContext, province, tokens,"@copy","cloneProvince","cloneProvince/"+province.getId()+"/","main","primary");
		
		addAction(userContext, province, tokens,"province.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+province.getId()+"/","main","primary");
		addAction(userContext, province, tokens,"province.addCity","addCity","addCity/"+province.getId()+"/","cityList","primary");
		addAction(userContext, province, tokens,"province.removeCity","removeCity","removeCity/"+province.getId()+"/","cityList","primary");
		addAction(userContext, province, tokens,"province.updateCity","updateCity","updateCity/"+province.getId()+"/","cityList","primary");
		addAction(userContext, province, tokens,"province.copyCityFrom","copyCityFrom","copyCityFrom/"+province.getId()+"/","cityList","primary");
		addAction(userContext, province, tokens,"province.addLocation","addLocation","addLocation/"+province.getId()+"/","locationList","primary");
		addAction(userContext, province, tokens,"province.removeLocation","removeLocation","removeLocation/"+province.getId()+"/","locationList","primary");
		addAction(userContext, province, tokens,"province.updateLocation","updateLocation","updateLocation/"+province.getId()+"/","locationList","primary");
		addAction(userContext, province, tokens,"province.copyLocationFrom","copyLocationFrom","copyLocationFrom/"+province.getId()+"/","locationList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, Province province, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Province createProvince(HealthUserContext userContext, String name,String platformId) throws Exception
	//public Province createProvince(HealthUserContext userContext,String name, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfProvince(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);


		Province province=createNewProvince();	

		province.setName(name);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		province.setPlatform(platform);
		
		
		province.setCreateTime(userContext.now());

		province = saveProvince(userContext, province, emptyOptions());
		
		onNewInstanceCreated(userContext, province);
		return province;


	}
	protected Province createNewProvince()
	{

		return new Province();
	}

	protected void checkParamsForUpdatingProvince(HealthUserContext userContext,String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkVersionOfProvince( provinceVersion);
		

		if(Province.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfProvince(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);


	}



	public Province clone(HealthUserContext userContext, String fromProvinceId) throws Exception{

		return provinceDaoOf(userContext).clone(fromProvinceId, this.allTokens());
	}

	public Province internalSaveProvince(HealthUserContext userContext, Province province) throws Exception
	{
		return internalSaveProvince(userContext, province, allTokens());

	}
	public Province internalSaveProvince(HealthUserContext userContext, Province province, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingProvince(userContext, provinceId, provinceVersion, property, newValueExpr, tokensExpr);


		synchronized(province){
			//will be good when the province loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Province.
			if (province.isChanged()){
			
			}
			province = saveProvince(userContext, province, options);
			return province;

		}

	}

	public Province updateProvince(HealthUserContext userContext,String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingProvince(userContext, provinceId, provinceVersion, property, newValueExpr, tokensExpr);



		Province province = loadProvince(userContext, provinceId, allTokens());
		if(province.getVersion() != provinceVersion){
			String message = "The target version("+province.getVersion()+") is not equals to version("+provinceVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(province){
			//will be good when the province loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Province.
			
			province.changeProperty(property, newValueExpr);
			province = saveProvince(userContext, province, tokens().done());
			return present(userContext,province, mergedAllTokens(tokensExpr));
			//return saveProvince(userContext, province, tokens().done());
		}

	}

	public Province updateProvinceProperty(HealthUserContext userContext,String provinceId, int provinceVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingProvince(userContext, provinceId, provinceVersion, property, newValueExpr, tokensExpr);

		Province province = loadProvince(userContext, provinceId, allTokens());
		if(province.getVersion() != provinceVersion){
			String message = "The target version("+province.getVersion()+") is not equals to version("+provinceVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(province){
			//will be good when the province loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Province.

			province.changeProperty(property, newValueExpr);
			
			province = saveProvince(userContext, province, tokens().done());
			return present(userContext,province, mergedAllTokens(tokensExpr));
			//return saveProvince(userContext, province, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ProvinceTokens tokens(){
		return ProvinceTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ProvinceTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortCityListWith("id","desc")
		.sortLocationListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ProvinceTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String provinceId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfProvince(provinceId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

 	}
 	public Province transferToAnotherPlatform(HealthUserContext userContext, String provinceId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, provinceId,anotherPlatformId);
 
		Province province = loadProvince(userContext, provinceId, allTokens());	
		synchronized(province){
			//will be good when the province loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			province.updatePlatform(platform);		
			province = saveProvince(userContext, province, emptyOptions());
			
			return present(userContext,province, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForProvince(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String provinceId, int provinceVersion) throws Exception {
		//deleteInternal(userContext, provinceId, provinceVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String provinceId, int provinceVersion) throws Exception{

		provinceDaoOf(userContext).delete(provinceId, provinceVersion);
	}

	public Province forgetByAll(HealthUserContext userContext, String provinceId, int provinceVersion) throws Exception {
		return forgetByAllInternal(userContext, provinceId, provinceVersion);
	}
	protected Province forgetByAllInternal(HealthUserContext userContext,
			String provinceId, int provinceVersion) throws Exception{

		return provinceDaoOf(userContext).disconnectFromAll(provinceId, provinceVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ProvinceManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return provinceDaoOf(userContext).deleteAll();
	}


	//disconnect Province with platform in City
	protected Province breakWithCityByPlatform(HealthUserContext userContext, String provinceId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Province province = loadProvince(userContext, provinceId, allTokens());

			synchronized(province){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				provinceDaoOf(userContext).planToRemoveCityListWithPlatform(province, platformId, this.emptyOptions());

				province = saveProvince(userContext, province, tokens().withCityList().done());
				return province;
			}
	}
	//disconnect Province with district in Location
	protected Province breakWithLocationByDistrict(HealthUserContext userContext, String provinceId, String districtId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Province province = loadProvince(userContext, provinceId, allTokens());

			synchronized(province){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				provinceDaoOf(userContext).planToRemoveLocationListWithDistrict(province, districtId, this.emptyOptions());

				province = saveProvince(userContext, province, tokens().withLocationList().done());
				return province;
			}
	}






	protected void checkParamsForAddingCity(HealthUserContext userContext, String provinceId, String name, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProvince(provinceId);

		
		checkerOf(userContext).checkNameOfCity(name);
		
		checkerOf(userContext).checkPlatformIdOfCity(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);


	}
	public  Province addCity(HealthUserContext userContext, String provinceId, String name, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingCity(userContext,provinceId,name, platformId,tokensExpr);

		City city = createCity(userContext,name, platformId);

		Province province = loadProvince(userContext, provinceId, emptyOptions());
		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			province.addCity( city );
			province = saveProvince(userContext, province, tokens().withCityList().done());
			
			userContext.getManagerGroup().getCityManager().onNewInstanceCreated(userContext, city);
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingCityProperties(HealthUserContext userContext, String provinceId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkIdOfCity(id);

		checkerOf(userContext).checkNameOfCity( name);

		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province updateCityProperties(HealthUserContext userContext, String provinceId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCityProperties(userContext,provinceId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withCityListList()
				.searchCityListWith(City.ID_PROPERTY, "is", id).done();

		Province provinceToUpdate = loadProvince(userContext, provinceId, options);

		if(provinceToUpdate.getCityList().isEmpty()){
			throw new ProvinceManagerException("City is NOT FOUND with id: '"+id+"'");
		}

		City item = provinceToUpdate.getCityList().first();

		item.updateName( name );


		//checkParamsForAddingCity(userContext,provinceId,name, code, used,tokensExpr);
		Province province = saveProvince(userContext, provinceToUpdate, tokens().withCityList().done());
		synchronized(province){
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}
	}


	protected City createCity(HealthUserContext userContext, String name, String platformId) throws Exception{

		City city = new City();
		
		
		city.setName(name);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		city.setPlatform(platform);		
		city.setCreateTime(userContext.now());
	
		
		return city;


	}

	protected City createIndexedCity(String id, int version){

		City city = new City();
		city.setId(id);
		city.setVersion(version);
		return city;

	}

	protected void checkParamsForRemovingCityList(HealthUserContext userContext, String provinceId,
			String cityIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProvince(provinceId);
		for(String cityIdItem: cityIds){
			checkerOf(userContext).checkIdOfCity(cityIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province removeCityList(HealthUserContext userContext, String provinceId,
			String cityIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingCityList(userContext, provinceId,  cityIds, tokensExpr);


			Province province = loadProvince(userContext, provinceId, allTokens());
			synchronized(province){
				//Will be good when the province loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				provinceDaoOf(userContext).planToRemoveCityList(province, cityIds, allTokens());
				province = saveProvince(userContext, province, tokens().withCityList().done());
				deleteRelationListInGraph(userContext, province.getCityList());
				return present(userContext,province, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingCity(HealthUserContext userContext, String provinceId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProvince( provinceId);
		checkerOf(userContext).checkIdOfCity(cityId);
		checkerOf(userContext).checkVersionOfCity(cityVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province removeCity(HealthUserContext userContext, String provinceId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingCity(userContext,provinceId, cityId, cityVersion,tokensExpr);

		City city = createIndexedCity(cityId, cityVersion);
		Province province = loadProvince(userContext, provinceId, allTokens());
		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			province.removeCity( city );
			province = saveProvince(userContext, province, tokens().withCityList().done());
			deleteRelationInGraph(userContext, city);
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingCity(HealthUserContext userContext, String provinceId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProvince( provinceId);
		checkerOf(userContext).checkIdOfCity(cityId);
		checkerOf(userContext).checkVersionOfCity(cityVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province copyCityFrom(HealthUserContext userContext, String provinceId,
		String cityId, int cityVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingCity(userContext,provinceId, cityId, cityVersion,tokensExpr);

		City city = createIndexedCity(cityId, cityVersion);
		Province province = loadProvince(userContext, provinceId, allTokens());
		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			province.copyCityFrom( city );
			province = saveProvince(userContext, province, tokens().withCityList().done());
			
			userContext.getManagerGroup().getCityManager().onNewInstanceCreated(userContext, (City)province.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingCity(HealthUserContext userContext, String provinceId, String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkIdOfCity(cityId);
		checkerOf(userContext).checkVersionOfCity(cityVersion);
		

		if(City.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfCity(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}

	public  Province updateCity(HealthUserContext userContext, String provinceId, String cityId, int cityVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingCity(userContext, provinceId, cityId, cityVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withCityList().searchCityListWith(City.ID_PROPERTY, "eq", cityId).done();



		Province province = loadProvince(userContext, provinceId, loadTokens);

		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//province.removeCity( city );
			//make changes to AcceleraterAccount.
			City cityIndex = createIndexedCity(cityId, cityVersion);

			City city = province.findTheCity(cityIndex);
			if(city == null){
				throw new ProvinceManagerException(city+" is NOT FOUND" );
			}

			city.changeProperty(property, newValueExpr);
			
			province = saveProvince(userContext, province, tokens().withCityList().done());
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingLocation(HealthUserContext userContext, String provinceId, String name, String address, String districtId, BigDecimal latitude, BigDecimal longitude,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProvince(provinceId);

		
		checkerOf(userContext).checkNameOfLocation(name);
		
		checkerOf(userContext).checkAddressOfLocation(address);
		
		checkerOf(userContext).checkDistrictIdOfLocation(districtId);
		
		checkerOf(userContext).checkLatitudeOfLocation(latitude);
		
		checkerOf(userContext).checkLongitudeOfLocation(longitude);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);


	}
	public  Province addLocation(HealthUserContext userContext, String provinceId, String name, String address, String districtId, BigDecimal latitude, BigDecimal longitude, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingLocation(userContext,provinceId,name, address, districtId, latitude, longitude,tokensExpr);

		Location location = createLocation(userContext,name, address, districtId, latitude, longitude);

		Province province = loadProvince(userContext, provinceId, emptyOptions());
		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			province.addLocation( location );
			province = saveProvince(userContext, province, tokens().withLocationList().done());
			
			userContext.getManagerGroup().getLocationManager().onNewInstanceCreated(userContext, location);
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingLocationProperties(HealthUserContext userContext, String provinceId,String id,String name,String address,BigDecimal latitude,BigDecimal longitude,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkIdOfLocation(id);

		checkerOf(userContext).checkNameOfLocation( name);
		checkerOf(userContext).checkAddressOfLocation( address);
		checkerOf(userContext).checkLatitudeOfLocation( latitude);
		checkerOf(userContext).checkLongitudeOfLocation( longitude);

		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province updateLocationProperties(HealthUserContext userContext, String provinceId, String id,String name,String address,BigDecimal latitude,BigDecimal longitude, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingLocationProperties(userContext,provinceId,id,name,address,latitude,longitude,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withLocationListList()
				.searchLocationListWith(Location.ID_PROPERTY, "is", id).done();

		Province provinceToUpdate = loadProvince(userContext, provinceId, options);

		if(provinceToUpdate.getLocationList().isEmpty()){
			throw new ProvinceManagerException("Location is NOT FOUND with id: '"+id+"'");
		}

		Location item = provinceToUpdate.getLocationList().first();

		item.updateName( name );
		item.updateAddress( address );
		item.updateLatitude( latitude );
		item.updateLongitude( longitude );


		//checkParamsForAddingLocation(userContext,provinceId,name, code, used,tokensExpr);
		Province province = saveProvince(userContext, provinceToUpdate, tokens().withLocationList().done());
		synchronized(province){
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}
	}


	protected Location createLocation(HealthUserContext userContext, String name, String address, String districtId, BigDecimal latitude, BigDecimal longitude) throws Exception{

		Location location = new Location();
		
		
		location.setName(name);		
		location.setAddress(address);		
		District  district = new District();
		district.setId(districtId);		
		location.setDistrict(district);		
		location.setLatitude(latitude);		
		location.setLongitude(longitude);
	
		
		return location;


	}

	protected Location createIndexedLocation(String id, int version){

		Location location = new Location();
		location.setId(id);
		location.setVersion(version);
		return location;

	}

	protected void checkParamsForRemovingLocationList(HealthUserContext userContext, String provinceId,
			String locationIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProvince(provinceId);
		for(String locationIdItem: locationIds){
			checkerOf(userContext).checkIdOfLocation(locationIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province removeLocationList(HealthUserContext userContext, String provinceId,
			String locationIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingLocationList(userContext, provinceId,  locationIds, tokensExpr);


			Province province = loadProvince(userContext, provinceId, allTokens());
			synchronized(province){
				//Will be good when the province loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				provinceDaoOf(userContext).planToRemoveLocationList(province, locationIds, allTokens());
				province = saveProvince(userContext, province, tokens().withLocationList().done());
				deleteRelationListInGraph(userContext, province.getLocationList());
				return present(userContext,province, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingLocation(HealthUserContext userContext, String provinceId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProvince( provinceId);
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkVersionOfLocation(locationVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province removeLocation(HealthUserContext userContext, String provinceId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingLocation(userContext,provinceId, locationId, locationVersion,tokensExpr);

		Location location = createIndexedLocation(locationId, locationVersion);
		Province province = loadProvince(userContext, provinceId, allTokens());
		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			province.removeLocation( location );
			province = saveProvince(userContext, province, tokens().withLocationList().done());
			deleteRelationInGraph(userContext, location);
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingLocation(HealthUserContext userContext, String provinceId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProvince( provinceId);
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkVersionOfLocation(locationVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}
	public  Province copyLocationFrom(HealthUserContext userContext, String provinceId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingLocation(userContext,provinceId, locationId, locationVersion,tokensExpr);

		Location location = createIndexedLocation(locationId, locationVersion);
		Province province = loadProvince(userContext, provinceId, allTokens());
		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			province.copyLocationFrom( location );
			province = saveProvince(userContext, province, tokens().withLocationList().done());
			
			userContext.getManagerGroup().getLocationManager().onNewInstanceCreated(userContext, (Location)province.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingLocation(HealthUserContext userContext, String provinceId, String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProvince(provinceId);
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkVersionOfLocation(locationVersion);
		

		if(Location.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfLocation(parseString(newValueExpr));
		
		}
		
		if(Location.ADDRESS_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAddressOfLocation(parseString(newValueExpr));
		
		}
		
		if(Location.LATITUDE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLatitudeOfLocation(parseBigDecimal(newValueExpr));
		
		}
		
		if(Location.LONGITUDE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLongitudeOfLocation(parseBigDecimal(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProvinceManagerException.class);

	}

	public  Province updateLocation(HealthUserContext userContext, String provinceId, String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingLocation(userContext, provinceId, locationId, locationVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withLocationList().searchLocationListWith(Location.ID_PROPERTY, "eq", locationId).done();



		Province province = loadProvince(userContext, provinceId, loadTokens);

		synchronized(province){
			//Will be good when the province loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//province.removeLocation( location );
			//make changes to AcceleraterAccount.
			Location locationIndex = createIndexedLocation(locationId, locationVersion);

			Location location = province.findTheLocation(locationIndex);
			if(location == null){
				throw new ProvinceManagerException(location+" is NOT FOUND" );
			}

			location.changeProperty(property, newValueExpr);
			
			province = saveProvince(userContext, province, tokens().withLocationList().done());
			return present(userContext,province, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, Province newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Province.INTERNAL_TYPE);
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


