
package com.doublechaintech.health.usertype;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class UserTypeTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="userType";
	
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
	protected UserTypeTokens(){
		//ensure not initialized outside the class
	}
	public  static  UserTypeTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		UserTypeTokens tokens = new UserTypeTokens(options);
		return tokens;
		
	}
	protected UserTypeTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public UserTypeTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static UserTypeTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected UserTypeTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static UserTypeTokens start(){
		return new UserTypeTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static UserTypeTokens allTokens(){
		
		return start()
			.withPlatform()
			.withWechatUserList();
	
	}
	public static UserTypeTokens withoutListsTokens(){
		
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
	
	public UserTypeTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public UserTypeTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String WECHAT_USER_LIST = "wechatUserList";
	public String getWechatUserList(){
		return WECHAT_USER_LIST;
	}
	public UserTypeTokens withWechatUserList(){		
		addSimpleOptions(WECHAT_USER_LIST);
		return this;
	}
	public UserTypeTokens analyzeWechatUserList(){		
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
	public UserTypeTokens extractMoreFromWechatUserList(String idsSeperatedWithComma){		
		addSimpleOptions(WECHAT_USER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int wechatUserListSortCounter = 0;
	public UserTypeTokens sortWechatUserListWith(String field, String descOrAsc){		
		addSortMoreOptions(WECHAT_USER_LIST,wechatUserListSortCounter++, field, descOrAsc);
		return this;
	}
	private int wechatUserListSearchCounter = 0;
	public UserTypeTokens searchWechatUserListWith(String field, String verb, String value){		
		
		withWechatUserList();
		addSearchMoreOptions(WECHAT_USER_LIST,wechatUserListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTypeTokens searchAllTextOfWechatUserList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(WECHAT_USER_LIST,wechatUserListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTypeTokens rowsPerPageOfWechatUserList(int rowsPerPage){		
		addSimpleOptions(WECHAT_USER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTypeTokens currentPageNumberOfWechatUserList(int currentPageNumber){		
		addSimpleOptions(WECHAT_USER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTypeTokens retainColumnsOfWechatUserList(String[] columns){		
		addSimpleOptions(WECHAT_USER_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTypeTokens excludeColumnsOfWechatUserList(String[] columns){		
		addSimpleOptions(WECHAT_USER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  UserTypeTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfWechatUserList(verb, value);	
		return this;
	}
}

