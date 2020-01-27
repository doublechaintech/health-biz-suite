
package com.doublechaintech.health.schoolclass;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class SchoolClassTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="schoolClass";
	
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
	protected SchoolClassTokens(){
		//ensure not initialized outside the class
	}
	public  static  SchoolClassTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		SchoolClassTokens tokens = new SchoolClassTokens(options);
		return tokens;
		
	}
	protected SchoolClassTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public SchoolClassTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static SchoolClassTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected SchoolClassTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static SchoolClassTokens start(){
		return new SchoolClassTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static SchoolClassTokens allTokens(){
		
		return start()
			.withClassTeacher()
			.withPlatform()
			.withCq()
			.withClassDailyHealthSurveyList()
			.withStudentList()
			.withStudentHealthSurveyList();
	
	}
	public static SchoolClassTokens withoutListsTokens(){
		
		return start()
			.withClassTeacher()
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
	
	public SchoolClassTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String CLASSTEACHER = "classTeacher";
	public String getClassTeacher(){
		return CLASSTEACHER;
	}
	public SchoolClassTokens withClassTeacher(){		
		addSimpleOptions(CLASSTEACHER);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public SchoolClassTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String CQ = "cq";
	public String getCq(){
		return CQ;
	}
	public SchoolClassTokens withCq(){		
		addSimpleOptions(CQ);
		return this;
	}
	
	
	protected static final String CLASS_DAILY_HEALTH_SURVEY_LIST = "classDailyHealthSurveyList";
	public String getClassDailyHealthSurveyList(){
		return CLASS_DAILY_HEALTH_SURVEY_LIST;
	}
	public SchoolClassTokens withClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST);
		return this;
	}
	public SchoolClassTokens analyzeClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeClassDailyHealthSurveyListEnabled(){		
		
		if(checkOptions(this.options(), CLASS_DAILY_HEALTH_SURVEY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public SchoolClassTokens extractMoreFromClassDailyHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classDailyHealthSurveyListSortCounter = 0;
	public SchoolClassTokens sortClassDailyHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classDailyHealthSurveyListSearchCounter = 0;
	public SchoolClassTokens searchClassDailyHealthSurveyListWith(String field, String verb, String value){		
		
		withClassDailyHealthSurveyList();
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SchoolClassTokens searchAllTextOfClassDailyHealthSurveyList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SchoolClassTokens rowsPerPageOfClassDailyHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public SchoolClassTokens currentPageNumberOfClassDailyHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public SchoolClassTokens retainColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public SchoolClassTokens excludeColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_LIST = "studentList";
	public String getStudentList(){
		return STUDENT_LIST;
	}
	public SchoolClassTokens withStudentList(){		
		addSimpleOptions(STUDENT_LIST);
		return this;
	}
	public SchoolClassTokens analyzeStudentList(){		
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
	public SchoolClassTokens extractMoreFromStudentList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentListSortCounter = 0;
	public SchoolClassTokens sortStudentListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_LIST,studentListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentListSearchCounter = 0;
	public SchoolClassTokens searchStudentListWith(String field, String verb, String value){		
		
		withStudentList();
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SchoolClassTokens searchAllTextOfStudentList(String verb, String value){	
		String field = "id|name|gender|studentId";
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SchoolClassTokens rowsPerPageOfStudentList(int rowsPerPage){		
		addSimpleOptions(STUDENT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public SchoolClassTokens currentPageNumberOfStudentList(int currentPageNumber){		
		addSimpleOptions(STUDENT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public SchoolClassTokens retainColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"RetainColumns",columns);
		return this;
	}
	public SchoolClassTokens excludeColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_HEALTH_SURVEY_LIST = "studentHealthSurveyList";
	public String getStudentHealthSurveyList(){
		return STUDENT_HEALTH_SURVEY_LIST;
	}
	public SchoolClassTokens withStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST);
		return this;
	}
	public SchoolClassTokens analyzeStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentHealthSurveyListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_HEALTH_SURVEY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public SchoolClassTokens extractMoreFromStudentHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentHealthSurveyListSortCounter = 0;
	public SchoolClassTokens sortStudentHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentHealthSurveyListSearchCounter = 0;
	public SchoolClassTokens searchStudentHealthSurveyListWith(String field, String verb, String value){		
		
		withStudentHealthSurveyList();
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SchoolClassTokens searchAllTextOfStudentHealthSurveyList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SchoolClassTokens rowsPerPageOfStudentHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public SchoolClassTokens currentPageNumberOfStudentHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public SchoolClassTokens retainColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public SchoolClassTokens excludeColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  SchoolClassTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfClassDailyHealthSurveyList(verb, value);	
		searchAllTextOfStudentList(verb, value);	
		searchAllTextOfStudentHealthSurveyList(verb, value);	
		return this;
	}
}

