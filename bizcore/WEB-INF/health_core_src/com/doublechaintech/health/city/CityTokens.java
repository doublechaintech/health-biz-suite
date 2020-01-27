
package com.doublechaintech.health.city;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class CityTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="city";
	
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
	protected CityTokens(){
		//ensure not initialized outside the class
	}
	public  static  CityTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		CityTokens tokens = new CityTokens(options);
		return tokens;
		
	}
	protected CityTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public CityTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static CityTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected CityTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static CityTokens start(){
		return new CityTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static CityTokens allTokens(){
		
		return start()
			.withProvince()
			.withPlatform()
			.withDistrictList();
	
	}
	public static CityTokens withoutListsTokens(){
		
		return start()
			.withProvince()
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
	
	public CityTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PROVINCE = "province";
	public String getProvince(){
		return PROVINCE;
	}
	public CityTokens withProvince(){		
		addSimpleOptions(PROVINCE);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public CityTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String DISTRICT_LIST = "districtList";
	public String getDistrictList(){
		return DISTRICT_LIST;
	}
	public CityTokens withDistrictList(){		
		addSimpleOptions(DISTRICT_LIST);
		return this;
	}
	public CityTokens analyzeDistrictList(){		
		addSimpleOptions(DISTRICT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeDistrictListEnabled(){		
		
		if(checkOptions(this.options(), DISTRICT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public CityTokens extractMoreFromDistrictList(String idsSeperatedWithComma){		
		addSimpleOptions(DISTRICT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int districtListSortCounter = 0;
	public CityTokens sortDistrictListWith(String field, String descOrAsc){		
		addSortMoreOptions(DISTRICT_LIST,districtListSortCounter++, field, descOrAsc);
		return this;
	}
	private int districtListSearchCounter = 0;
	public CityTokens searchDistrictListWith(String field, String verb, String value){		
		
		withDistrictList();
		addSearchMoreOptions(DISTRICT_LIST,districtListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public CityTokens searchAllTextOfDistrictList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(DISTRICT_LIST,districtListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public CityTokens rowsPerPageOfDistrictList(int rowsPerPage){		
		addSimpleOptions(DISTRICT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public CityTokens currentPageNumberOfDistrictList(int currentPageNumber){		
		addSimpleOptions(DISTRICT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public CityTokens retainColumnsOfDistrictList(String[] columns){		
		addSimpleOptions(DISTRICT_LIST+"RetainColumns",columns);
		return this;
	}
	public CityTokens excludeColumnsOfDistrictList(String[] columns){		
		addSimpleOptions(DISTRICT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  CityTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfDistrictList(verb, value);	
		return this;
	}
}

