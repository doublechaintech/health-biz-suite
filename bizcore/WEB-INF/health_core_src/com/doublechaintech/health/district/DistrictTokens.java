
package com.doublechaintech.health.district;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class DistrictTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="district";
	
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
	protected DistrictTokens(){
		//ensure not initialized outside the class
	}
	public  static  DistrictTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		DistrictTokens tokens = new DistrictTokens(options);
		return tokens;
		
	}
	protected DistrictTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public DistrictTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static DistrictTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected DistrictTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static DistrictTokens start(){
		return new DistrictTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static DistrictTokens allTokens(){
		
		return start()
			.withCity()
			.withPlatform()
			.withLocationList();
	
	}
	public static DistrictTokens withoutListsTokens(){
		
		return start()
			.withCity()
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
	
	public DistrictTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String CITY = "city";
	public String getCity(){
		return CITY;
	}
	public DistrictTokens withCity(){		
		addSimpleOptions(CITY);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public DistrictTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String LOCATION_LIST = "locationList";
	public String getLocationList(){
		return LOCATION_LIST;
	}
	public DistrictTokens withLocationList(){		
		addSimpleOptions(LOCATION_LIST);
		return this;
	}
	public DistrictTokens analyzeLocationList(){		
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
	public DistrictTokens extractMoreFromLocationList(String idsSeperatedWithComma){		
		addSimpleOptions(LOCATION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int locationListSortCounter = 0;
	public DistrictTokens sortLocationListWith(String field, String descOrAsc){		
		addSortMoreOptions(LOCATION_LIST,locationListSortCounter++, field, descOrAsc);
		return this;
	}
	private int locationListSearchCounter = 0;
	public DistrictTokens searchLocationListWith(String field, String verb, String value){		
		
		withLocationList();
		addSearchMoreOptions(LOCATION_LIST,locationListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public DistrictTokens searchAllTextOfLocationList(String verb, String value){	
		String field = "id|name|address";
		addSearchMoreOptions(LOCATION_LIST,locationListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public DistrictTokens rowsPerPageOfLocationList(int rowsPerPage){		
		addSimpleOptions(LOCATION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public DistrictTokens currentPageNumberOfLocationList(int currentPageNumber){		
		addSimpleOptions(LOCATION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public DistrictTokens retainColumnsOfLocationList(String[] columns){		
		addSimpleOptions(LOCATION_LIST+"RetainColumns",columns);
		return this;
	}
	public DistrictTokens excludeColumnsOfLocationList(String[] columns){		
		addSimpleOptions(LOCATION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  DistrictTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfLocationList(verb, value);	
		return this;
	}
}

