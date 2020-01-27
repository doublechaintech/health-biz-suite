
package com.doublechaintech.health.teacher;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class TeacherTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="teacher";
	
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
	protected TeacherTokens(){
		//ensure not initialized outside the class
	}
	public  static  TeacherTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		TeacherTokens tokens = new TeacherTokens(options);
		return tokens;
		
	}
	protected TeacherTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public TeacherTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static TeacherTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected TeacherTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static TeacherTokens start(){
		return new TeacherTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static TeacherTokens allTokens(){
		
		return start()
			.withPlatform()
			.withCq()
			.withSchoolClassList();
	
	}
	public static TeacherTokens withoutListsTokens(){
		
		return start()
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
	
	public TeacherTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public TeacherTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String CQ = "cq";
	public String getCq(){
		return CQ;
	}
	public TeacherTokens withCq(){		
		addSimpleOptions(CQ);
		return this;
	}
	
	
	protected static final String SCHOOL_CLASS_LIST = "schoolClassList";
	public String getSchoolClassList(){
		return SCHOOL_CLASS_LIST;
	}
	public TeacherTokens withSchoolClassList(){		
		addSimpleOptions(SCHOOL_CLASS_LIST);
		return this;
	}
	public TeacherTokens analyzeSchoolClassList(){		
		addSimpleOptions(SCHOOL_CLASS_LIST+".anaylze");
		return this;
	}
	public boolean analyzeSchoolClassListEnabled(){		
		
		if(checkOptions(this.options(), SCHOOL_CLASS_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public TeacherTokens extractMoreFromSchoolClassList(String idsSeperatedWithComma){		
		addSimpleOptions(SCHOOL_CLASS_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int schoolClassListSortCounter = 0;
	public TeacherTokens sortSchoolClassListWith(String field, String descOrAsc){		
		addSortMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSortCounter++, field, descOrAsc);
		return this;
	}
	private int schoolClassListSearchCounter = 0;
	public TeacherTokens searchSchoolClassListWith(String field, String verb, String value){		
		
		withSchoolClassList();
		addSearchMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public TeacherTokens searchAllTextOfSchoolClassList(String verb, String value){	
		String field = "id|name|schoole";
		addSearchMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public TeacherTokens rowsPerPageOfSchoolClassList(int rowsPerPage){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public TeacherTokens currentPageNumberOfSchoolClassList(int currentPageNumber){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public TeacherTokens retainColumnsOfSchoolClassList(String[] columns){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"RetainColumns",columns);
		return this;
	}
	public TeacherTokens excludeColumnsOfSchoolClassList(String[] columns){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  TeacherTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfSchoolClassList(verb, value);	
		return this;
	}
}

