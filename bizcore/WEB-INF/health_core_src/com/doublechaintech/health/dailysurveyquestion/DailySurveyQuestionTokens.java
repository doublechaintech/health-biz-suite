
package com.doublechaintech.health.dailysurveyquestion;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class DailySurveyQuestionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="dailySurveyQuestion";
	
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
	protected DailySurveyQuestionTokens(){
		//ensure not initialized outside the class
	}
	public  static  DailySurveyQuestionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		DailySurveyQuestionTokens tokens = new DailySurveyQuestionTokens(options);
		return tokens;
		
	}
	protected DailySurveyQuestionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public DailySurveyQuestionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static DailySurveyQuestionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected DailySurveyQuestionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static DailySurveyQuestionTokens start(){
		return new DailySurveyQuestionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static DailySurveyQuestionTokens allTokens(){
		
		return start()
			.withQuestionType()
			.withClassDailyHealthSurvey()
			.withSurveyQuestion()
			.withStudentDailyAnswerList();
	
	}
	public static DailySurveyQuestionTokens withoutListsTokens(){
		
		return start()
			.withQuestionType()
			.withClassDailyHealthSurvey()
			.withSurveyQuestion();
	
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
	
	public DailySurveyQuestionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String QUESTIONTYPE = "questionType";
	public String getQuestionType(){
		return QUESTIONTYPE;
	}
	public DailySurveyQuestionTokens withQuestionType(){		
		addSimpleOptions(QUESTIONTYPE);
		return this;
	}
	
	
	protected static final String CLASSDAILYHEALTHSURVEY = "classDailyHealthSurvey";
	public String getClassDailyHealthSurvey(){
		return CLASSDAILYHEALTHSURVEY;
	}
	public DailySurveyQuestionTokens withClassDailyHealthSurvey(){		
		addSimpleOptions(CLASSDAILYHEALTHSURVEY);
		return this;
	}
	
	
	protected static final String SURVEYQUESTION = "surveyQuestion";
	public String getSurveyQuestion(){
		return SURVEYQUESTION;
	}
	public DailySurveyQuestionTokens withSurveyQuestion(){		
		addSimpleOptions(SURVEYQUESTION);
		return this;
	}
	
	
	protected static final String STUDENT_DAILY_ANSWER_LIST = "studentDailyAnswerList";
	public String getStudentDailyAnswerList(){
		return STUDENT_DAILY_ANSWER_LIST;
	}
	public DailySurveyQuestionTokens withStudentDailyAnswerList(){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST);
		return this;
	}
	public DailySurveyQuestionTokens analyzeStudentDailyAnswerList(){		
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
	public DailySurveyQuestionTokens extractMoreFromStudentDailyAnswerList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentDailyAnswerListSortCounter = 0;
	public DailySurveyQuestionTokens sortStudentDailyAnswerListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentDailyAnswerListSearchCounter = 0;
	public DailySurveyQuestionTokens searchStudentDailyAnswerListWith(String field, String verb, String value){		
		
		withStudentDailyAnswerList();
		addSearchMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public DailySurveyQuestionTokens searchAllTextOfStudentDailyAnswerList(String verb, String value){	
		String field = "id|answer";
		addSearchMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public DailySurveyQuestionTokens rowsPerPageOfStudentDailyAnswerList(int rowsPerPage){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public DailySurveyQuestionTokens currentPageNumberOfStudentDailyAnswerList(int currentPageNumber){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public DailySurveyQuestionTokens retainColumnsOfStudentDailyAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"RetainColumns",columns);
		return this;
	}
	public DailySurveyQuestionTokens excludeColumnsOfStudentDailyAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  DailySurveyQuestionTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentDailyAnswerList(verb, value);	
		return this;
	}
}

