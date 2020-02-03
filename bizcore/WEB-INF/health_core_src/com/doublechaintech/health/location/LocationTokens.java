
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
			.withStudentList()
			.withUserList();
	
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
	
	
	protected static final String STUDENT_LIST = "studentList";
	public String getStudentList(){
		return STUDENT_LIST;
	}
	public LocationTokens withStudentList(){		
		addSimpleOptions(STUDENT_LIST);
		return this;
	}
	public LocationTokens analyzeStudentList(){		
		addSimpleOptions(STUDENT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public LocationTokens extractMoreFromStudentList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentListSortCounter = 0;
	public LocationTokens sortStudentListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_LIST,studentListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentListSearchCounter = 0;
	public LocationTokens searchStudentListWith(String field, String verb, String value){		
		
		withStudentList();
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens searchAllTextOfStudentList(String verb, String value){	
		String field = "id|studentName|studentNumber|guardianName|guardianMobile";
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens rowsPerPageOfStudentList(int rowsPerPage){		
		addSimpleOptions(STUDENT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public LocationTokens currentPageNumberOfStudentList(int currentPageNumber){		
		addSimpleOptions(STUDENT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public LocationTokens retainColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"RetainColumns",columns);
		return this;
	}
	public LocationTokens excludeColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String USER_LIST = "userList";
	public String getUserList(){
		return USER_LIST;
	}
	public LocationTokens withUserList(){		
		addSimpleOptions(USER_LIST);
		return this;
	}
	public LocationTokens analyzeUserList(){		
		addSimpleOptions(USER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeUserListEnabled(){		
		
		if(checkOptions(this.options(), USER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public LocationTokens extractMoreFromUserList(String idsSeperatedWithComma){		
		addSimpleOptions(USER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int userListSortCounter = 0;
	public LocationTokens sortUserListWith(String field, String descOrAsc){		
		addSortMoreOptions(USER_LIST,userListSortCounter++, field, descOrAsc);
		return this;
	}
	private int userListSearchCounter = 0;
	public LocationTokens searchUserListWith(String field, String verb, String value){		
		
		withUserList();
		addSearchMoreOptions(USER_LIST,userListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens searchAllTextOfUserList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(USER_LIST,userListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public LocationTokens rowsPerPageOfUserList(int rowsPerPage){		
		addSimpleOptions(USER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public LocationTokens currentPageNumberOfUserList(int currentPageNumber){		
		addSimpleOptions(USER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public LocationTokens retainColumnsOfUserList(String[] columns){		
		addSimpleOptions(USER_LIST+"RetainColumns",columns);
		return this;
	}
	public LocationTokens excludeColumnsOfUserList(String[] columns){		
		addSimpleOptions(USER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  LocationTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentList(verb, value);	
		searchAllTextOfUserList(verb, value);	
		return this;
	}
}

