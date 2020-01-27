
package com.doublechaintech.health.province;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class ProvinceTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="province";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected ProvinceTokens(){
		//ensure not initialized outside the class
	}
	public  static  ProvinceTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ProvinceTokens tokens = new ProvinceTokens(options);
		return tokens;
		
	}
	protected ProvinceTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ProvinceTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ProvinceTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ProvinceTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ProvinceTokens start(){
		return new ProvinceTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ProvinceTokens allTokens(){
		
		return start()
			.withPlatform()
			.withCityList()
			.withLocationList();
	
	}
	public static ProvinceTokens withoutListsTokens(){
		
		return start()
			.withPlatform();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}
	
	public ProvinceTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ProvinceTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String CITY_LIST = "cityList";
	public String getCityList(){
		return CITY_LIST;
	}
	public ProvinceTokens withCityList(){		
		addSimpleOptions(CITY_LIST);
		return this;
	}
	public ProvinceTokens analyzeCityList(){		
		addSimpleOptions(CITY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeCityListEnabled(){		
		
		if(checkOptions(this.options(), CITY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ProvinceTokens extractMoreFromCityList(String idsSeperatedWithComma){		
		addSimpleOptions(CITY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int cityListSortCounter = 0;
	public ProvinceTokens sortCityListWith(String field, String descOrAsc){		
		addSortMoreOptions(CITY_LIST,cityListSortCounter++, field, descOrAsc);
		return this;
	}
	private int cityListSearchCounter = 0;
	public ProvinceTokens searchCityListWith(String field, String verb, String value){		
		
		withCityList();
		addSearchMoreOptions(CITY_LIST,cityListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProvinceTokens searchAllTextOfCityList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CITY_LIST,cityListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProvinceTokens rowsPerPageOfCityList(int rowsPerPage){		
		addSimpleOptions(CITY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProvinceTokens currentPageNumberOfCityList(int currentPageNumber){		
		addSimpleOptions(CITY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProvinceTokens retainColumnsOfCityList(String[] columns){		
		addSimpleOptions(CITY_LIST+"RetainColumns",columns);
		return this;
	}
	public ProvinceTokens excludeColumnsOfCityList(String[] columns){		
		addSimpleOptions(CITY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String LOCATION_LIST = "locationList";
	public String getLocationList(){
		return LOCATION_LIST;
	}
	public ProvinceTokens withLocationList(){		
		addSimpleOptions(LOCATION_LIST);
		return this;
	}
	public ProvinceTokens analyzeLocationList(){		
		addSimpleOptions(LOCATION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeLocationListEnabled(){		
		
		if(checkOptions(this.options(), LOCATION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ProvinceTokens extractMoreFromLocationList(String idsSeperatedWithComma){		
		addSimpleOptions(LOCATION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int locationListSortCounter = 0;
	public ProvinceTokens sortLocationListWith(String field, String descOrAsc){		
		addSortMoreOptions(LOCATION_LIST,locationListSortCounter++, field, descOrAsc);
		return this;
	}
	private int locationListSearchCounter = 0;
	public ProvinceTokens searchLocationListWith(String field, String verb, String value){		
		
		withLocationList();
		addSearchMoreOptions(LOCATION_LIST,locationListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProvinceTokens searchAllTextOfLocationList(String verb, String value){	
		String field = "id|name|address";
		addSearchMoreOptions(LOCATION_LIST,locationListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProvinceTokens rowsPerPageOfLocationList(int rowsPerPage){		
		addSimpleOptions(LOCATION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProvinceTokens currentPageNumberOfLocationList(int currentPageNumber){		
		addSimpleOptions(LOCATION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProvinceTokens retainColumnsOfLocationList(String[] columns){		
		addSimpleOptions(LOCATION_LIST+"RetainColumns",columns);
		return this;
	}
	public ProvinceTokens excludeColumnsOfLocationList(String[] columns){		
		addSimpleOptions(LOCATION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ProvinceTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfCityList(verb, value);	
		searchAllTextOfLocationList(verb, value);	
		return this;
	}
}

