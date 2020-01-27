
package com.doublechaintech.health.questiontype;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class QuestionTypeTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="questionType";
	
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
	protected QuestionTypeTokens(){
		//ensure not initialized outside the class
	}
	public  static  QuestionTypeTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		QuestionTypeTokens tokens = new QuestionTypeTokens(options);
		return tokens;
		
	}
	protected QuestionTypeTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public QuestionTypeTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static QuestionTypeTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected QuestionTypeTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static QuestionTypeTokens start(){
		return new QuestionTypeTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static QuestionTypeTokens allTokens(){
		
		return start()
			.withPlatform()
			.withQuestionList()
			.withClassQuestionList()
			.withDailySurveyQuestionList();
	
	}
	public static QuestionTypeTokens withoutListsTokens(){
		
		return start()
			.withPlatform();
	
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
	
	public QuestionTypeTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public QuestionTypeTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String QUESTION_LIST = "questionList";
	public String getQuestionList(){
		return QUESTION_LIST;
	}
	public QuestionTypeTokens withQuestionList(){		
		addSimpleOptions(QUESTION_LIST);
		return this;
	}
	public QuestionTypeTokens analyzeQuestionList(){		
		addSimpleOptions(QUESTION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeQuestionListEnabled(){		
		
		if(checkOptions(this.options(), QUESTION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public QuestionTypeTokens extractMoreFromQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int questionListSortCounter = 0;
	public QuestionTypeTokens sortQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(QUESTION_LIST,questionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int questionListSearchCounter = 0;
	public QuestionTypeTokens searchQuestionListWith(String field, String verb, String value){		
		
		withQuestionList();
		addSearchMoreOptions(QUESTION_LIST,questionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTypeTokens searchAllTextOfQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(QUESTION_LIST,questionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTypeTokens rowsPerPageOfQuestionList(int rowsPerPage){		
		addSimpleOptions(QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public QuestionTypeTokens currentPageNumberOfQuestionList(int currentPageNumber){		
		addSimpleOptions(QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public QuestionTypeTokens retainColumnsOfQuestionList(String[] columns){		
		addSimpleOptions(QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public QuestionTypeTokens excludeColumnsOfQuestionList(String[] columns){		
		addSimpleOptions(QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CLASS_QUESTION_LIST = "classQuestionList";
	public String getClassQuestionList(){
		return CLASS_QUESTION_LIST;
	}
	public QuestionTypeTokens withClassQuestionList(){		
		addSimpleOptions(CLASS_QUESTION_LIST);
		return this;
	}
	public QuestionTypeTokens analyzeClassQuestionList(){		
		addSimpleOptions(CLASS_QUESTION_LIST+".anaylze");
		return this;
	}
	public boolean analyzeClassQuestionListEnabled(){		
		
		if(checkOptions(this.options(), CLASS_QUESTION_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public QuestionTypeTokens extractMoreFromClassQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classQuestionListSortCounter = 0;
	public QuestionTypeTokens sortClassQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_QUESTION_LIST,classQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classQuestionListSearchCounter = 0;
	public QuestionTypeTokens searchClassQuestionListWith(String field, String verb, String value){		
		
		withClassQuestionList();
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTypeTokens searchAllTextOfClassQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTypeTokens rowsPerPageOfClassQuestionList(int rowsPerPage){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public QuestionTypeTokens currentPageNumberOfClassQuestionList(int currentPageNumber){		
		addSimpleOptions(CLASS_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public QuestionTypeTokens retainColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public QuestionTypeTokens excludeColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String DAILY_SURVEY_QUESTION_LIST = "dailySurveyQuestionList";
	public String getDailySurveyQuestionList(){
		return DAILY_SURVEY_QUESTION_LIST;
	}
	public QuestionTypeTokens withDailySurveyQuestionList(){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST);
		return this;
	}
	public QuestionTypeTokens analyzeDailySurveyQuestionList(){		
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
	public QuestionTypeTokens extractMoreFromDailySurveyQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int dailySurveyQuestionListSortCounter = 0;
	public QuestionTypeTokens sortDailySurveyQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int dailySurveyQuestionListSearchCounter = 0;
	public QuestionTypeTokens searchDailySurveyQuestionListWith(String field, String verb, String value){		
		
		withDailySurveyQuestionList();
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTypeTokens searchAllTextOfDailySurveyQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionTypeTokens rowsPerPageOfDailySurveyQuestionList(int rowsPerPage){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public QuestionTypeTokens currentPageNumberOfDailySurveyQuestionList(int currentPageNumber){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public QuestionTypeTokens retainColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public QuestionTypeTokens excludeColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  QuestionTypeTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfQuestionList(verb, value);	
		searchAllTextOfClassQuestionList(verb, value);	
		searchAllTextOfDailySurveyQuestionList(verb, value);	
		return this;
	}
}

