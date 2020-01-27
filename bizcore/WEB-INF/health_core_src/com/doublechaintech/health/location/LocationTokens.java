
package com.doublechaintech.health.location;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class LocationTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="location";
	
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
	protected LocationTokens(){
		//ensure not initialized outside the class
	}
	public  static  LocationTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		LocationTokens tokens = new LocationTokens(options);
		return tokens;
		
	}
	protected LocationTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public LocationTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static LocationTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected LocationTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static LocationTokens start(){
		return new LocationTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static LocationTokens allTokens(){
		
		return start()
			.withDistrict()
			.withProvince()
			.withGuardianList()
			.withWechatUserList();
	
	}
	public static LocationTokens withoutListsTokens(){
		
		return start()
			.withDistrict()
			.withProvince();
	
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
	
	public LocationTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String DISTRICT = "district";
	public String getDistrict(){
		return DISTRICT;
	}
	public LocationTokens withDistrict(){		
		addSimpleOptions(DISTRICT);
		return this;
	}
	
	
	protected static final String PROVINCE = "province";
	public String getProvince(){
		return PROVINCE;
	}
	public LocationTokens withProvince(){		
		addSimpleOptions(PROVINCE);
		return this;
	}
	
	
	protected static final String GUARDIAN_LIST = "guardianList";
	public String getGuardianList(){
		return GUARDIAN_LIST;
	}
	public LocationTokens withGuardianList(){		
		addSimpleOptions(GUARDIAN_LIST);
		return this;
	}
	public LocationTokens analyzeGuardianList(){		
		addSimpleOptions(GUARDIAN_LIST+".anaylze");
		return this;
	}
	public boolean analyzeGuardianListEnabled(){		
		
		if(checkOptions(this.options(), GUARDIAN_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public LocationTokens extractMoreFromGuardianList(String idsSeperatedWithComma){		
		addSimpleOptions(GUARDIAN_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int guardianListSortCounter = 0;
	public LocationTokens sortGuardianListWith(String field, String descOrAsc){		
		addSortMoreOptions(GUARDIAN_LIST,guardianListSortCounter++, field, descOrAsc);
		return this;
	}
	private int guardianListSearchCounter = 0;
	public LocationTokens searchGuardianListWith(String field, String verb, String value){		
		
		withGuardianList();
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens searchAllTextOfGuardianList(String verb, String value){	
		String field = "id|name|mobile";
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens rowsPerPageOfGuardianList(int rowsPerPage){		
		addSimpleOptions(GUARDIAN_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public LocationTokens currentPageNumberOfGuardianList(int currentPageNumber){		
		addSimpleOptions(GUARDIAN_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public LocationTokens retainColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"RetainColumns",columns);
		return this;
	}
	public LocationTokens excludeColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String WECHAT_USER_LIST = "wechatUserList";
	public String getWechatUserList(){
		return WECHAT_USER_LIST;
	}
	public LocationTokens withWechatUserList(){		
		addSimpleOptions(WECHAT_USER_LIST);
		return this;
	}
	public LocationTokens analyzeWechatUserList(){		
		addSimpleOptions(WECHAT_USER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeWechatUserListEnabled(){		
		
		if(checkOptions(this.options(), WECHAT_USER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public LocationTokens extractMoreFromWechatUserList(String idsSeperatedWithComma){		
		addSimpleOptions(WECHAT_USER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int wechatUserListSortCounter = 0;
	public LocationTokens sortWechatUserListWith(String field, String descOrAsc){		
		addSortMoreOptions(WECHAT_USER_LIST,wechatUserListSortCounter++, field, descOrAsc);
		return this;
	}
	private int wechatUserListSearchCounter = 0;
	public LocationTokens searchWechatUserListWith(String field, String verb, String value){		
		
		withWechatUserList();
		addSearchMoreOptions(WECHAT_USER_LIST,wechatUserListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens searchAllTextOfWechatUserList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(WECHAT_USER_LIST,wechatUserListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens rowsPerPageOfWechatUserList(int rowsPerPage){		
		addSimpleOptions(WECHAT_USER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public LocationTokens currentPageNumberOfWechatUserList(int currentPageNumber){		
		addSimpleOptions(WECHAT_USER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public LocationTokens retainColumnsOfWechatUserList(String[] columns){		
		addSimpleOptions(WECHAT_USER_LIST+"RetainColumns",columns);
		return this;
	}
	public LocationTokens excludeColumnsOfWechatUserList(String[] columns){		
		addSimpleOptions(WECHAT_USER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  LocationTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfGuardianList(verb, value);	
		searchAllTextOfWechatUserList(verb, value);	
		return this;
	}
}

