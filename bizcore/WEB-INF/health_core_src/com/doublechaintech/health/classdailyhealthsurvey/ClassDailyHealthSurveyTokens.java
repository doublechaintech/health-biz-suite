
package com.doublechaintech.health.classdailyhealthsurvey;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class ClassDailyHealthSurveyTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="classDailyHealthSurvey";
	
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
	protected ClassDailyHealthSurveyTokens(){
		//ensure not initialized outside the class
	}
	public  static  ClassDailyHealthSurveyTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ClassDailyHealthSurveyTokens tokens = new ClassDailyHealthSurveyTokens(options);
		return tokens;
		
	}
	protected ClassDailyHealthSurveyTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ClassDailyHealthSurveyTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ClassDailyHealthSurveyTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ClassDailyHealthSurveyTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ClassDailyHealthSurveyTokens start(){
		return new ClassDailyHealthSurveyTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ClassDailyHealthSurveyTokens allTokens(){
		
		return start()
			.withTeacher()
			.withCreator()
			.withChangeRequest()
			.withDailySurveyQuestionList()
			.withStudentHealthSurveyList();
	
	}
	public static ClassDailyHealthSurveyTokens withoutListsTokens(){
		
		return start()
			.withTeacher()
			.withCreator()
			.withChangeRequest();
	
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
	
	public ClassDailyHealthSurveyTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String TEACHER = "teacher";
	public String getTeacher(){
		return TEACHER;
	}
	public ClassDailyHealthSurveyTokens withTeacher(){		
		addSimpleOptions(TEACHER);
		return this;
	}
	
	
	protected static final String CREATOR = "creator";
	public String getCreator(){
		return CREATOR;
	}
	public ClassDailyHealthSurveyTokens withCreator(){		
		addSimpleOptions(CREATOR);
		return this;
	}
	
	
	protected static final String CHANGEREQUEST = "changeRequest";
	public String getChangeRequest(){
		return CHANGEREQUEST;
	}
	public ClassDailyHealthSurveyTokens withChangeRequest(){		
		addSimpleOptions(CHANGEREQUEST);
		return this;
	}
	
	
	protected static final String DAILY_SURVEY_QUESTION_LIST = "dailySurveyQuestionList";
	public String getDailySurveyQuestionList(){
		return DAILY_SURVEY_QUESTION_LIST;
	}
	public ClassDailyHealthSurveyTokens withDailySurveyQuestionList(){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST);
		return this;
	}
	public ClassDailyHealthSurveyTokens analyzeDailySurveyQuestionList(){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeDailySurveyQuestionListEnabled(){		
		
		if(checkOptions(this.options(), DAILY_SURVEY_QUESTION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ClassDailyHealthSurveyTokens extractMoreFromDailySurveyQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int dailySurveyQuestionListSortCounter = 0;
	public ClassDailyHealthSurveyTokens sortDailySurveyQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int dailySurveyQuestionListSearchCounter = 0;
	public ClassDailyHealthSurveyTokens searchDailySurveyQuestionListWith(String field, String verb, String value){		
		
		withDailySurveyQuestionList();
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ClassDailyHealthSurveyTokens searchAllTextOfDailySurveyQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ClassDailyHealthSurveyTokens rowsPerPageOfDailySurveyQuestionList(int rowsPerPage){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ClassDailyHealthSurveyTokens currentPageNumberOfDailySurveyQuestionList(int currentPageNumber){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ClassDailyHealthSurveyTokens retainColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public ClassDailyHealthSurveyTokens excludeColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_HEALTH_SURVEY_LIST = "studentHealthSurveyList";
	public String getStudentHealthSurveyList(){
		return STUDENT_HEALTH_SURVEY_LIST;
	}
	public ClassDailyHealthSurveyTokens withStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST);
		return this;
	}
	public ClassDailyHealthSurveyTokens analyzeStudentHealthSurveyList(){		
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
	public ClassDailyHealthSurveyTokens extractMoreFromStudentHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentHealthSurveyListSortCounter = 0;
	public ClassDailyHealthSurveyTokens sortStudentHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentHealthSurveyListSearchCounter = 0;
	public ClassDailyHealthSurveyTokens searchStudentHealthSurveyListWith(String field, String verb, String value){		
		
		withStudentHealthSurveyList();
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ClassDailyHealthSurveyTokens searchAllTextOfStudentHealthSurveyList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ClassDailyHealthSurveyTokens rowsPerPageOfStudentHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ClassDailyHealthSurveyTokens currentPageNumberOfStudentHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ClassDailyHealthSurveyTokens retainColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public ClassDailyHealthSurveyTokens excludeColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ClassDailyHealthSurveyTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfDailySurveyQuestionList(verb, value);	
		searchAllTextOfStudentHealthSurveyList(verb, value);	
		return this;
	}
}

