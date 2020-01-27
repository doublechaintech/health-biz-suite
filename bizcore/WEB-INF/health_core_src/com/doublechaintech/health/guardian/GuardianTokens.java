
package com.doublechaintech.health.guardian;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class GuardianTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="guardian";
	
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
	protected GuardianTokens(){
		//ensure not initialized outside the class
	}
	public  static  GuardianTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		GuardianTokens tokens = new GuardianTokens(options);
		return tokens;
		
	}
	protected GuardianTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public GuardianTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static GuardianTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected GuardianTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static GuardianTokens start(){
		return new GuardianTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static GuardianTokens allTokens(){
		
		return start()
			.withAddress()
			.withWechatUser()
			.withPlatform()
			.withCq()
			.withStudentList();
	
	}
	public static GuardianTokens withoutListsTokens(){
		
		return start()
			.withAddress()
			.withWechatUser()
			.withPlatform()
			.withCq();
	
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
	
	public GuardianTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String ADDRESS = "address";
	public String getAddress(){
		return ADDRESS;
	}
	public GuardianTokens withAddress(){		
		addSimpleOptions(ADDRESS);
		return this;
	}
	
	
	protected static final String WECHATUSER = "wechatUser";
	public String getWechatUser(){
		return WECHATUSER;
	}
	public GuardianTokens withWechatUser(){		
		addSimpleOptions(WECHATUSER);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public GuardianTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String CQ = "cq";
	public String getCq(){
		return CQ;
	}
	public GuardianTokens withCq(){		
		addSimpleOptions(CQ);
		return this;
	}
	
	
	protected static final String STUDENT_LIST = "studentList";
	public String getStudentList(){
		return STUDENT_LIST;
	}
	public GuardianTokens withStudentList(){		
		addSimpleOptions(STUDENT_LIST);
		return this;
	}
	public GuardianTokens analyzeStudentList(){		
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
	public GuardianTokens extractMoreFromStudentList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentListSortCounter = 0;
	public GuardianTokens sortStudentListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_LIST,studentListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentListSearchCounter = 0;
	public GuardianTokens searchStudentListWith(String field, String verb, String value){		
		
		withStudentList();
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public GuardianTokens searchAllTextOfStudentList(String verb, String value){	
		String field = "id|name|gender|studentId";
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public GuardianTokens rowsPerPageOfStudentList(int rowsPerPage){		
		addSimpleOptions(STUDENT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public GuardianTokens currentPageNumberOfStudentList(int currentPageNumber){		
		addSimpleOptions(STUDENT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public GuardianTokens retainColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"RetainColumns",columns);
		return this;
	}
	public GuardianTokens excludeColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  GuardianTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentList(verb, value);	
		return this;
	}
}

