
package com.doublechaintech.health.question;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class QuestionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="question";
	
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
	protected QuestionTokens(){
		//ensure not initialized outside the class
	}
	public  static  QuestionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		QuestionTokens tokens = new QuestionTokens(options);
		return tokens;
		
	}
	protected QuestionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public QuestionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static QuestionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected QuestionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static QuestionTokens start(){
		return new QuestionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static QuestionTokens allTokens(){
		
		return start()
			.withQuestionType()
			.withPlatform()
			.withCreator()
			.withCq()
			.withDailySurveyQuestionList();
	
	}
	public static QuestionTokens withoutListsTokens(){
		
		return start()
			.withQuestionType()
			.withPlatform()
			.withCreator()
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
	
	public QuestionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String QUESTIONTYPE = "questionType";
	public String getQuestionType(){
		return QUESTIONTYPE;
	}
	public QuestionTokens withQuestionType(){		
		addSimpleOptions(QUESTIONTYPE);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public QuestionTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String CREATOR = "creator";
	public String getCreator(){
		return CREATOR;
	}
	public QuestionTokens withCreator(){		
		addSimpleOptions(CREATOR);
		return this;
	}
	
	
	protected static final String CQ = "cq";
	public String getCq(){
		return CQ;
	}
	public QuestionTokens withCq(){		
		addSimpleOptions(CQ);
		return this;
	}
	
	
	protected static final String DAILY_SURVEY_QUESTION_LIST = "dailySurveyQuestionList";
	public String getDailySurveyQuestionList(){
		return DAILY_SURVEY_QUESTION_LIST;
	}
	public QuestionTokens withDailySurveyQuestionList(){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST);
		return this;
	}
	public QuestionTokens analyzeDailySurveyQuestionList(){		
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
	public QuestionTokens extractMoreFromDailySurveyQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int dailySurveyQuestionListSortCounter = 0;
	public QuestionTokens sortDailySurveyQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int dailySurveyQuestionListSearchCounter = 0;
	public QuestionTokens searchDailySurveyQuestionListWith(String field, String verb, String value){		
		
		withDailySurveyQuestionList();
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTokens searchAllTextOfDailySurveyQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTokens rowsPerPageOfDailySurveyQuestionList(int rowsPerPage){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public QuestionTokens currentPageNumberOfDailySurveyQuestionList(int currentPageNumber){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public QuestionTokens retainColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public QuestionTokens excludeColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  QuestionTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfDailySurveyQuestionList(verb, value);	
		return this;
	}
}

