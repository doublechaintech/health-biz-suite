
package com.doublechaintech.health.district;

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


import com.doublechaintech.health.city.City;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;

import com.doublechaintech.health.city.CandidateCity;
import com.doublechaintech.health.platform.CandidatePlatform;

import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;






public class DistrictManagerImpl extends CustomHealthCheckerManager implements DistrictManager, BusinessHandler{

  


	private static final String SERVICE_TYPE = "District";
	@Override
	public DistrictDAO daoOf(HealthUserContext userContext) {
		return districtDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws DistrictManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new DistrictManagerException(message);

	}



 	protected District saveDistrict(HealthUserContext userContext, District district, String [] tokensExpr) throws Exception{	
 		//return getDistrictDAO().save(district, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveDistrict(userContext, district, tokens);
 	}
 	
 	protected District saveDistrictDetail(HealthUserContext userContext, District district) throws Exception{	

 		
 		return saveDistrict(userContext, district, allTokens());
 	}
 	
 	public District loadDistrict(HealthUserContext userContext, String districtId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).throwExceptionIfHasErrors( DistrictManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		District district = loadDistrict( userContext, districtId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,district, tokens);
 	}
 	
 	
 	 public District searchDistrict(HealthUserContext userContext, String districtId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).throwExceptionIfHasErrors( DistrictManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		District district = loadDistrict( userContext, districtId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,district, tokens);
 	}
 	
 	

 	protected District present(HealthUserContext userContext, District district, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,district,tokens);
		
		
		District  districtToPresent = districtDaoOf(userContext).present(district, tokens);
		
		List<BaseEntity> entityListToNaming = districtToPresent.collectRefercencesFromLists();
		districtDaoOf(userContext).alias(entityListToNaming);
		
		return  districtToPresent;
		
		
	}
 
 	
 	
 	public District loadDistrictDetail(HealthUserContext userContext, String districtId) throws Exception{	
 		District district = loadDistrict( userContext, districtId, allTokens());
 		return present(userContext,district, allTokens());
		
 	}
 	
 	public Object view(HealthUserContext userContext, String districtId) throws Exception{	
 		District district = loadDistrict( userContext, districtId, viewTokens());
 		return present(userContext,district, allTokens());
		
 	}
 	protected District saveDistrict(HealthUserContext userContext, District district, Map<String,Object>tokens) throws Exception{	
 		return districtDaoOf(userContext).save(district, tokens);
 	}
 	protected District loadDistrict(HealthUserContext userContext, String districtId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).throwExceptionIfHasErrors( DistrictManagerException.class);

 
 		return districtDaoOf(userContext).load(districtId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(HealthUserContext userContext, District district, Map<String, Object> tokens){
		super.addActions(userContext, district, tokens);
		
		addAction(userContext, district, tokens,"@create","createDistrict","createDistrict/","main","primary");
		addAction(userContext, district, tokens,"@update","updateDistrict","updateDistrict/"+district.getId()+"/","main","primary");
		addAction(userContext, district, tokens,"@copy","cloneDistrict","cloneDistrict/"+district.getId()+"/","main","primary");
		
		addAction(userContext, district, tokens,"district.transfer_to_city","transferToAnotherCity","transferToAnotherCity/"+district.getId()+"/","main","primary");
		addAction(userContext, district, tokens,"district.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+district.getId()+"/","main","primary");
		addAction(userContext, district, tokens,"district.addLocation","addLocation","addLocation/"+district.getId()+"/","locationList","primary");
		addAction(userContext, district, tokens,"district.removeLocation","removeLocation","removeLocation/"+district.getId()+"/","locationList","primary");
		addAction(userContext, district, tokens,"district.updateLocation","updateLocation","updateLocation/"+district.getId()+"/","locationList","primary");
		addAction(userContext, district, tokens,"district.copyLocationFrom","copyLocationFrom","copyLocationFrom/"+district.getId()+"/","locationList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(HealthUserContext userContext, District district, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public District createDistrict(HealthUserContext userContext, String name,String cityId,String platformId) throws Exception
	//public District createDistrict(HealthUserContext userContext,String name, String cityId, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfDistrict(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);


		District district=createNewDistrict();	

		district.setName(name);
			
		City city = loadCity(userContext, cityId,emptyOptions());
		district.setCity(city);
		
		
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		district.setPlatform(platform);
		
		
		district.setCreateTime(userContext.now());

		district = saveDistrict(userContext, district, emptyOptions());
		
		onNewInstanceCreated(userContext, district);
		return district;


	}
	protected District createNewDistrict()
	{

		return new District();
	}

	protected void checkParamsForUpdatingDistrict(HealthUserContext userContext,String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).checkVersionOfDistrict( districtVersion);
		

		if(District.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfDistrict(parseString(newValueExpr));
		
			
		}		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);


	}



	public District clone(HealthUserContext userContext, String fromDistrictId) throws Exception{

		return districtDaoOf(userContext).clone(fromDistrictId, this.allTokens());
	}

	public District internalSaveDistrict(HealthUserContext userContext, District district) throws Exception
	{
		return internalSaveDistrict(userContext, district, allTokens());

	}
	public District internalSaveDistrict(HealthUserContext userContext, District district, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingDistrict(userContext, districtId, districtVersion, property, newValueExpr, tokensExpr);


		synchronized(district){
			//will be good when the district loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to District.
			if (district.isChanged()){
			
			}
			district = saveDistrict(userContext, district, options);
			return district;

		}

	}

	public District updateDistrict(HealthUserContext userContext,String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDistrict(userContext, districtId, districtVersion, property, newValueExpr, tokensExpr);



		District district = loadDistrict(userContext, districtId, allTokens());
		if(district.getVersion() != districtVersion){
			String message = "The target version("+district.getVersion()+") is not equals to version("+districtVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(district){
			//will be good when the district loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to District.
			
			district.changeProperty(property, newValueExpr);
			district = saveDistrict(userContext, district, tokens().done());
			return present(userContext,district, mergedAllTokens(tokensExpr));
			//return saveDistrict(userContext, district, tokens().done());
		}

	}

	public District updateDistrictProperty(HealthUserContext userContext,String districtId, int districtVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingDistrict(userContext, districtId, districtVersion, property, newValueExpr, tokensExpr);

		District district = loadDistrict(userContext, districtId, allTokens());
		if(district.getVersion() != districtVersion){
			String message = "The target version("+district.getVersion()+") is not equals to version("+districtVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(district){
			//will be good when the district loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to District.

			district.changeProperty(property, newValueExpr);
			
			district = saveDistrict(userContext, district, tokens().done());
			return present(userContext,district, mergedAllTokens(tokensExpr));
			//return saveDistrict(userContext, district, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected DistrictTokens tokens(){
		return DistrictTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return DistrictTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortLocationListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return DistrictTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherCity(HealthUserContext userContext, String districtId, String anotherCityId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfDistrict(districtId);
 		checkerOf(userContext).checkIdOfCity(anotherCityId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

 	}
 	public District transferToAnotherCity(HealthUserContext userContext, String districtId, String anotherCityId) throws Exception
 	{
 		checkParamsForTransferingAnotherCity(userContext, districtId,anotherCityId);
 
		District district = loadDistrict(userContext, districtId, allTokens());	
		synchronized(district){
			//will be good when the district loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			City city = loadCity(userContext, anotherCityId, emptyOptions());		
			district.updateCity(city);		
			district = saveDistrict(userContext, district, emptyOptions());
			
			return present(userContext,district, allTokens());
			
		}

 	}

	


	public CandidateCity requestCandidateCity(HealthUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateCity result = new CandidateCity();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<City> candidateList = cityDaoOf(userContext).requestCandidateCityForDistrict(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(HealthUserContext userContext, String districtId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfDistrict(districtId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

 	}
 	public District transferToAnotherPlatform(HealthUserContext userContext, String districtId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, districtId,anotherPlatformId);
 
		District district = loadDistrict(userContext, districtId, allTokens());	
		synchronized(district){
			//will be good when the district loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			district.updatePlatform(platform);		
			district = saveDistrict(userContext, district, emptyOptions());
			
			return present(userContext,district, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForDistrict(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected City loadCity(HealthUserContext userContext, String newCityId, Map<String,Object> options) throws Exception
 	{

 		return cityDaoOf(userContext).load(newCityId, options);
 	}
 	


	

 	protected Platform loadPlatform(HealthUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(HealthUserContext userContext, String districtId, int districtVersion) throws Exception {
		//deleteInternal(userContext, districtId, districtVersion);
	}
	protected void deleteInternal(HealthUserContext userContext,
			String districtId, int districtVersion) throws Exception{

		districtDaoOf(userContext).delete(districtId, districtVersion);
	}

	public District forgetByAll(HealthUserContext userContext, String districtId, int districtVersion) throws Exception {
		return forgetByAllInternal(userContext, districtId, districtVersion);
	}
	protected District forgetByAllInternal(HealthUserContext userContext,
			String districtId, int districtVersion) throws Exception{

		return districtDaoOf(userContext).disconnectFromAll(districtId, districtVersion);
	}




	public int deleteAll(HealthUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new DistrictManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(HealthUserContext userContext) throws Exception{
		return districtDaoOf(userContext).deleteAll();
	}


	//disconnect District with province in Location
	protected District breakWithLocationByProvince(HealthUserContext userContext, String districtId, String provinceId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			District district = loadDistrict(userContext, districtId, allTokens());

			synchronized(district){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				districtDaoOf(userContext).planToRemoveLocationListWithProvince(district, provinceId, this.emptyOptions());

				district = saveDistrict(userContext, district, tokens().withLocationList().done());
				return district;
			}
	}






	protected void checkParamsForAddingLocation(HealthUserContext userContext, String districtId, String name, String address, String provinceId, BigDecimal latitude, BigDecimal longitude,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfDistrict(districtId);

		
		checkerOf(userContext).checkNameOfLocation(name);
		
		checkerOf(userContext).checkAddressOfLocation(address);
		
		checkerOf(userContext).checkProvinceIdOfLocation(provinceId);
		
		checkerOf(userContext).checkLatitudeOfLocation(latitude);
		
		checkerOf(userContext).checkLongitudeOfLocation(longitude);
	
		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);


	}
	public  District addLocation(HealthUserContext userContext, String districtId, String name, String address, String provinceId, BigDecimal latitude, BigDecimal longitude, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingLocation(userContext,districtId,name, address, provinceId, latitude, longitude,tokensExpr);

		Location location = createLocation(userContext,name, address, provinceId, latitude, longitude);

		District district = loadDistrict(userContext, districtId, emptyOptions());
		synchronized(district){
			//Will be good when the district loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			district.addLocation( location );
			district = saveDistrict(userContext, district, tokens().withLocationList().done());
			
			userContext.getManagerGroup().getLocationManager().onNewInstanceCreated(userContext, location);
			return present(userContext,district, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingLocationProperties(HealthUserContext userContext, String districtId,String id,String name,String address,BigDecimal latitude,BigDecimal longitude,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfDistrict(districtId);
		checkerOf(userContext).checkIdOfLocation(id);

		checkerOf(userContext).checkNameOfLocation( name);
		checkerOf(userContext).checkAddressOfLocation( address);
		checkerOf(userContext).checkLatitudeOfLocation( latitude);
		checkerOf(userContext).checkLongitudeOfLocation( longitude);

		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

	}
	public  District updateLocationProperties(HealthUserContext userContext, String districtId, String id,String name,String address,BigDecimal latitude,BigDecimal longitude, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingLocationProperties(userContext,districtId,id,name,address,latitude,longitude,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withLocationListList()
				.searchLocationListWith(Location.ID_PROPERTY, "is", id).done();

		District districtToUpdate = loadDistrict(userContext, districtId, options);

		if(districtToUpdate.getLocationList().isEmpty()){
			throw new DistrictManagerException("Location is NOT FOUND with id: '"+id+"'");
		}

		Location item = districtToUpdate.getLocationList().first();

		item.updateName( name );
		item.updateAddress( address );
		item.updateLatitude( latitude );
		item.updateLongitude( longitude );


		//checkParamsForAddingLocation(userContext,districtId,name, code, used,tokensExpr);
		District district = saveDistrict(userContext, districtToUpdate, tokens().withLocationList().done());
		synchronized(district){
			return present(userContext,district, mergedAllTokens(tokensExpr));
		}
	}


	protected Location createLocation(HealthUserContext userContext, String name, String address, String provinceId, BigDecimal latitude, BigDecimal longitude) throws Exception{

		Location location = new Location();
		
		
		location.setName(name);		
		location.setAddress(address);		
		Province  province = new Province();
		province.setId(provinceId);		
		location.setProvince(province);		
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

	protected void checkParamsForRemovingLocationList(HealthUserContext userContext, String districtId,
			String locationIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfDistrict(districtId);
		for(String locationIdItem: locationIds){
			checkerOf(userContext).checkIdOfLocation(locationIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

	}
	public  District removeLocationList(HealthUserContext userContext, String districtId,
			String locationIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingLocationList(userContext, districtId,  locationIds, tokensExpr);


			District district = loadDistrict(userContext, districtId, allTokens());
			synchronized(district){
				//Will be good when the district loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				districtDaoOf(userContext).planToRemoveLocationList(district, locationIds, allTokens());
				district = saveDistrict(userContext, district, tokens().withLocationList().done());
				deleteRelationListInGraph(userContext, district.getLocationList());
				return present(userContext,district, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingLocation(HealthUserContext userContext, String districtId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfDistrict( districtId);
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkVersionOfLocation(locationVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

	}
	public  District removeLocation(HealthUserContext userContext, String districtId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingLocation(userContext,districtId, locationId, locationVersion,tokensExpr);

		Location location = createIndexedLocation(locationId, locationVersion);
		District district = loadDistrict(userContext, districtId, allTokens());
		synchronized(district){
			//Will be good when the district loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			district.removeLocation( location );
			district = saveDistrict(userContext, district, tokens().withLocationList().done());
			deleteRelationInGraph(userContext, location);
			return present(userContext,district, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingLocation(HealthUserContext userContext, String districtId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfDistrict( districtId);
		checkerOf(userContext).checkIdOfLocation(locationId);
		checkerOf(userContext).checkVersionOfLocation(locationVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

	}
	public  District copyLocationFrom(HealthUserContext userContext, String districtId,
		String locationId, int locationVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingLocation(userContext,districtId, locationId, locationVersion,tokensExpr);

		Location location = createIndexedLocation(locationId, locationVersion);
		District district = loadDistrict(userContext, districtId, allTokens());
		synchronized(district){
			//Will be good when the district loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			district.copyLocationFrom( location );
			district = saveDistrict(userContext, district, tokens().withLocationList().done());
			
			userContext.getManagerGroup().getLocationManager().onNewInstanceCreated(userContext, (Location)district.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,district, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingLocation(HealthUserContext userContext, String districtId, String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfDistrict(districtId);
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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(DistrictManagerException.class);

	}

	public  District updateLocation(HealthUserContext userContext, String districtId, String locationId, int locationVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingLocation(userContext, districtId, locationId, locationVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withLocationList().searchLocationListWith(Location.ID_PROPERTY, "eq", locationId).done();



		District district = loadDistrict(userContext, districtId, loadTokens);

		synchronized(district){
			//Will be good when the district loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//district.removeLocation( location );
			//make changes to AcceleraterAccount.
			Location locationIndex = createIndexedLocation(locationId, locationVersion);

			Location location = district.findTheLocation(locationIndex);
			if(location == null){
				throw new DistrictManagerException(location+" is NOT FOUND" );
			}

			location.changeProperty(property, newValueExpr);
			
			district = saveDistrict(userContext, district, tokens().withLocationList().done());
			return present(userContext,district, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(HealthUserContext userContext, District newCreated) throws Exception{
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, District.INTERNAL_TYPE);
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


