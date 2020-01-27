
package com.doublechaintech.health.studenthealthsurvey;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class StudentHealthSurveyTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="studentHealthSurvey";
	
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
	protected StudentHealthSurveyTokens(){
		//ensure not initialized outside the class
	}
	public  static  StudentHealthSurveyTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		StudentHealthSurveyTokens tokens = new StudentHealthSurveyTokens(options);
		return tokens;
		
	}
	protected StudentHealthSurveyTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public StudentHealthSurveyTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static StudentHealthSurveyTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected StudentHealthSurveyTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static StudentHealthSurveyTokens start(){
		return new StudentHealthSurveyTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static StudentHealthSurveyTokens allTokens(){
		
		return start()
			.withStudent()
			.withSurveyStatus()
			.withSchoolClass()
			.withClassDailyHealthSurvey()
			.withCq()
			.withStudentDailyAnswerList();
	
	}
	public static StudentHealthSurveyTokens withoutListsTokens(){
		
		return start()
			.withStudent()
			.withSurveyStatus()
			.withSchoolClass()
			.withClassDailyHealthSurvey()
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
	
	public StudentHealthSurveyTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String STUDENT = "student";
	public String getStudent(){
		return STUDENT;
	}
	public StudentHealthSurveyTokens withStudent(){		
		addSimpleOptions(STUDENT);
		return this;
	}
	
	
	protected static final String SURVEYSTATUS = "surveyStatus";
	public String getSurveyStatus(){
		return SURVEYSTATUS;
	}
	public StudentHealthSurveyTokens withSurveyStatus(){		
		addSimpleOptions(SURVEYSTATUS);
		return this;
	}
	
	
	protected static final String SCHOOLCLASS = "schoolClass";
	public String getSchoolClass(){
		return SCHOOLCLASS;
	}
	public StudentHealthSurveyTokens withSchoolClass(){		
		addSimpleOptions(SCHOOLCLASS);
		return this;
	}
	
	
	protected static final String CLASSDAILYHEALTHSURVEY = "classDailyHealthSurvey";
	public String getClassDailyHealthSurvey(){
		return CLASSDAILYHEALTHSURVEY;
	}
	public StudentHealthSurveyTokens withClassDailyHealthSurvey(){		
		addSimpleOptions(CLASSDAILYHEALTHSURVEY);
		return this;
	}
	
	
	protected static final String CQ = "cq";
	public String getCq(){
		return CQ;
	}
	public StudentHealthSurveyTokens withCq(){		
		addSimpleOptions(CQ);
		return this;
	}
	
	
	protected static final String STUDENT_DAILY_ANSWER_LIST = "studentDailyAnswerList";
	public String getStudentDailyAnswerList(){
		return STUDENT_DAILY_ANSWER_LIST;
	}
	public StudentHealthSurveyTokens withStudentDailyAnswerList(){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST);
		return this;
	}
	public StudentHealthSurveyTokens analyzeStudentDailyAnswerList(){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentDailyAnswerListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_DAILY_ANSWER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public StudentHealthSurveyTokens extractMoreFromStudentDailyAnswerList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentDailyAnswerListSortCounter = 0;
	public StudentHealthSurveyTokens sortStudentDailyAnswerListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentDailyAnswerListSearchCounter = 0;
	public StudentHealthSurveyTokens searchStudentDailyAnswerListWith(String field, String verb, String value){		
		
		withStudentDailyAnswerList();
		addSearchMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public StudentHealthSurveyTokens searchAllTextOfStudentDailyAnswerList(String verb, String value){	
		String field = "id|answer";
		addSearchMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public StudentHealthSurveyTokens rowsPerPageOfStudentDailyAnswerList(int rowsPerPage){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public StudentHealthSurveyTokens currentPageNumberOfStudentDailyAnswerList(int currentPageNumber){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public StudentHealthSurveyTokens retainColumnsOfStudentDailyAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"RetainColumns",columns);
		return this;
	}
	public StudentHealthSurveyTokens excludeColumnsOfStudentDailyAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  StudentHealthSurveyTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentDailyAnswerList(verb, value);	
		return this;
	}
}

