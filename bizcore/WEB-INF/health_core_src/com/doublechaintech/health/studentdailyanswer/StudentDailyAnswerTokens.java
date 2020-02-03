
package com.doublechaintech.health.studentdailyanswer;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class StudentDailyAnswerTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="studentDailyAnswer";
	
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
	protected StudentDailyAnswerTokens(){
		//ensure not initialized outside the class
	}
	public  static  StudentDailyAnswerTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		StudentDailyAnswerTokens tokens = new StudentDailyAnswerTokens(options);
		return tokens;
		
	}
	protected StudentDailyAnswerTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public StudentDailyAnswerTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static StudentDailyAnswerTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected StudentDailyAnswerTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static StudentDailyAnswerTokens start(){
		return new StudentDailyAnswerTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static StudentDailyAnswerTokens allTokens(){
		
		return start()
			.withStudentHealthSurvey()
			.withQuestion()
			.withChangeRequest()
			.withStudentAnswerList();
	
	}
	public static StudentDailyAnswerTokens withoutListsTokens(){
		
		return start()
			.withStudentHealthSurvey()
			.withQuestion()
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
	
	public StudentDailyAnswerTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String STUDENTHEALTHSURVEY = "studentHealthSurvey";
	public String getStudentHealthSurvey(){
		return STUDENTHEALTHSURVEY;
	}
	public StudentDailyAnswerTokens withStudentHealthSurvey(){		
		addSimpleOptions(STUDENTHEALTHSURVEY);
		return this;
	}
	
	
	protected static final String QUESTION = "question";
	public String getQuestion(){
		return QUESTION;
	}
	public StudentDailyAnswerTokens withQuestion(){		
		addSimpleOptions(QUESTION);
		return this;
	}
	
	
	protected static final String CHANGEREQUEST = "changeRequest";
	public String getChangeRequest(){
		return CHANGEREQUEST;
	}
	public StudentDailyAnswerTokens withChangeRequest(){		
		addSimpleOptions(CHANGEREQUEST);
		return this;
	}
	
	
	protected static final String STUDENT_ANSWER_LIST = "studentAnswerList";
	public String getStudentAnswerList(){
		return STUDENT_ANSWER_LIST;
	}
	public StudentDailyAnswerTokens withStudentAnswerList(){		
		addSimpleOptions(STUDENT_ANSWER_LIST);
		return this;
	}
	public StudentDailyAnswerTokens analyzeStudentAnswerList(){		
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
	public StudentDailyAnswerTokens extractMoreFromStudentAnswerList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_ANSWER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentAnswerListSortCounter = 0;
	public StudentDailyAnswerTokens sortStudentAnswerListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_ANSWER_LIST,studentAnswerListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentAnswerListSearchCounter = 0;
	public StudentDailyAnswerTokens searchStudentAnswerListWith(String field, String verb, String value){		
		
		withStudentAnswerList();
		addSearchMoreOptions(STUDENT_ANSWER_LIST,studentAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public StudentDailyAnswerTokens searchAllTextOfStudentAnswerList(String verb, String value){	
		String field = "id|questionTopic|answer";
		addSearchMoreOptions(STUDENT_ANSWER_LIST,studentAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public StudentDailyAnswerTokens rowsPerPageOfStudentAnswerList(int rowsPerPage){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public StudentDailyAnswerTokens currentPageNumberOfStudentAnswerList(int currentPageNumber){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public StudentDailyAnswerTokens retainColumnsOfStudentAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"RetainColumns",columns);
		return this;
	}
	public StudentDailyAnswerTokens excludeColumnsOfStudentAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_ANSWER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  StudentDailyAnswerTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentAnswerList(verb, value);	
		return this;
	}
}

