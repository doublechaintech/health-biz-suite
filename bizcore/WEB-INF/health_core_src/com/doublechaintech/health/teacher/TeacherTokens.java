
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
			.withClassDailyHealthSurveyList()
			.withStudentHealthSurveyList();
	
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
	
	
	protected static final String CLASS_DAILY_HEALTH_SURVEY_LIST = "classDailyHealthSurveyList";
	public String getClassDailyHealthSurveyList(){
		return CLASS_DAILY_HEALTH_SURVEY_LIST;
	}
	public TeacherTokens withClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST);
		return this;
	}
	public TeacherTokens analyzeClassDailyHealthSurveyList(){		
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
	public TeacherTokens extractMoreFromClassDailyHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classDailyHealthSurveyListSortCounter = 0;
	public TeacherTokens sortClassDailyHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classDailyHealthSurveyListSearchCounter = 0;
	public TeacherTokens searchClassDailyHealthSurveyListWith(String field, String verb, String value){		
		
		withClassDailyHealthSurveyList();
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public TeacherTokens searchAllTextOfClassDailyHealthSurveyList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public TeacherTokens rowsPerPageOfClassDailyHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public TeacherTokens currentPageNumberOfClassDailyHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public TeacherTokens retainColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public TeacherTokens excludeColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_HEALTH_SURVEY_LIST = "studentHealthSurveyList";
	public String getStudentHealthSurveyList(){
		return STUDENT_HEALTH_SURVEY_LIST;
	}
	public TeacherTokens withStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST);
		return this;
	}
	public TeacherTokens analyzeStudentHealthSurveyList(){		
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
	public TeacherTokens extractMoreFromStudentHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentHealthSurveyListSortCounter = 0;
	public TeacherTokens sortStudentHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentHealthSurveyListSearchCounter = 0;
	public TeacherTokens searchStudentHealthSurveyListWith(String field, String verb, String value){		
		
		withStudentHealthSurveyList();
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public TeacherTokens searchAllTextOfStudentHealthSurveyList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public TeacherTokens rowsPerPageOfStudentHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public TeacherTokens currentPageNumberOfStudentHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public TeacherTokens retainColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public TeacherTokens excludeColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  TeacherTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfClassDailyHealthSurveyList(verb, value);	
		searchAllTextOfStudentHealthSurveyList(verb, value);	
		return this;
	}
}

