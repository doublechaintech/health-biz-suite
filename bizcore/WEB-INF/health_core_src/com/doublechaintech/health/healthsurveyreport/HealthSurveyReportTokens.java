
package com.doublechaintech.health.healthsurveyreport;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class HealthSurveyReportTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="healthSurveyReport";
	
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
	protected HealthSurveyReportTokens(){
		//ensure not initialized outside the class
	}
	public  static  HealthSurveyReportTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		HealthSurveyReportTokens tokens = new HealthSurveyReportTokens(options);
		return tokens;
		
	}
	protected HealthSurveyReportTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public HealthSurveyReportTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static HealthSurveyReportTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected HealthSurveyReportTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static HealthSurveyReportTokens start(){
		return new HealthSurveyReportTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static HealthSurveyReportTokens allTokens(){
		
		return start()
			.withStudent()
			.withTeacher()
			.withSurvey()
			.withStudentAnswerList();
	
	}
	public static HealthSurveyReportTokens withoutListsTokens(){
		
		return start()
			.withStudent()
			.withTeacher()
			.withSurvey();
	
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
	
	public HealthSurveyReportTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String STUDENT = "student";
	public String getStudent(){
		return STUDENT;
	}
	public HealthSurveyReportTokens withStudent(){		
		addSimpleOptions(STUDENT);
		return this;
	}
	
	
	protected static final String TEACHER = "teacher";
	public String getTeacher(){
		return TEACHER;
	}
	public HealthSurveyReportTokens withTeacher(){		
		addSimpleOptions(TEACHER);
		return this;
	}
	
	
	protected static final String SURVEY = "survey";
	public String getSurvey(){
		return SURVEY;
	}
	public HealthSurveyReportTokens withSurvey(){		
		addSimpleOptions(SURVEY);
		return this;
	}
	
	
	protected static final String STUDENT_ANSWER_LIST = "studentAnswerList";
	public String getStudentAnswerList(){
		return STUDENT_ANSWER_LIST;
	}
	public HealthSurveyReportTokens withStudentAnswerList(){		
		addSimpleOptions(STUDENT_ANSWER_LIST);
		return this;
	}
	public HealthSurveyReportTokens analyzeStudentAnswerList(){		
		addSimpleOptions(STUDENT_ANSWER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentAnswerListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_ANSWER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public HealthSurveyReportTokens extractMoreFromStudentAnswerList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_ANSWER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentAnswerListSortCounter = 0;
	public HealthSurveyReportTokens sortStudentAnswerListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_ANSWER_LIST,studentAnswerListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentAnswerListSearchCounter = 0;
	public HealthSurveyReportTokens searchStudentAnswerListWith(String field, String verb, String value){		
		
		withStudentAnswerList();
		addSearchMoreOptions(STUDENT_ANSWER_LIST,studentAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public HealthSurveyReportTokens searchAllTextOfStudentAnswerList(String verb, String value){	
		String field = "id|questionTopic|answer";
		addSearchMoreOptions(STUDENT_ANSWER_LIST,studentAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public HealthSurveyReportTokens rowsPerPageOfStudentAnswerList(int rowsPerPage){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public HealthSurveyReportTokens currentPageNumberOfStudentAnswerList(int currentPageNumber){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public HealthSurveyReportTokens retainColumnsOfStudentAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"RetainColumns",columns);
		return this;
	}
	public HealthSurveyReportTokens excludeColumnsOfStudentAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  HealthSurveyReportTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentAnswerList(verb, value);	
		return this;
	}
}

