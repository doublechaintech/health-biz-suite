
package com.doublechaintech.health.classquestion;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class ClassQuestionTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="classQuestion";
	
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
	protected ClassQuestionTokens(){
		//ensure not initialized outside the class
	}
	public  static  ClassQuestionTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ClassQuestionTokens tokens = new ClassQuestionTokens(options);
		return tokens;
		
	}
	protected ClassQuestionTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ClassQuestionTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ClassQuestionTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ClassQuestionTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ClassQuestionTokens start(){
		return new ClassQuestionTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ClassQuestionTokens allTokens(){
		
		return start()
			.withQuestionType()
			.withQuestionSource()
			.withCreator()
			.withCq()
			.withDailySurveyQuestionList();
	
	}
	public static ClassQuestionTokens withoutListsTokens(){
		
		return start()
			.withQuestionType()
			.withQuestionSource()
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
	
	public ClassQuestionTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String QUESTIONTYPE = "questionType";
	public String getQuestionType(){
		return QUESTIONTYPE;
	}
	public ClassQuestionTokens withQuestionType(){		
		addSimpleOptions(QUESTIONTYPE);
		return this;
	}
	
	
	protected static final String QUESTIONSOURCE = "questionSource";
	public String getQuestionSource(){
		return QUESTIONSOURCE;
	}
	public ClassQuestionTokens withQuestionSource(){		
		addSimpleOptions(QUESTIONSOURCE);
		return this;
	}
	
	
	protected static final String CREATOR = "creator";
	public String getCreator(){
		return CREATOR;
	}
	public ClassQuestionTokens withCreator(){		
		addSimpleOptions(CREATOR);
		return this;
	}
	
	
	protected static final String CQ = "cq";
	public String getCq(){
		return CQ;
	}
	public ClassQuestionTokens withCq(){		
		addSimpleOptions(CQ);
		return this;
	}
	
	
	protected static final String DAILY_SURVEY_QUESTION_LIST = "dailySurveyQuestionList";
	public String getDailySurveyQuestionList(){
		return DAILY_SURVEY_QUESTION_LIST;
	}
	public ClassQuestionTokens withDailySurveyQuestionList(){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST);
		return this;
	}
	public ClassQuestionTokens analyzeDailySurveyQuestionList(){		
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
	public ClassQuestionTokens extractMoreFromDailySurveyQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int dailySurveyQuestionListSortCounter = 0;
	public ClassQuestionTokens sortDailySurveyQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int dailySurveyQuestionListSearchCounter = 0;
	public ClassQuestionTokens searchDailySurveyQuestionListWith(String field, String verb, String value){		
		
		withDailySurveyQuestionList();
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ClassQuestionTokens searchAllTextOfDailySurveyQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(DAILY_SURVEY_QUESTION_LIST,dailySurveyQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ClassQuestionTokens rowsPerPageOfDailySurveyQuestionList(int rowsPerPage){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ClassQuestionTokens currentPageNumberOfDailySurveyQuestionList(int currentPageNumber){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ClassQuestionTokens retainColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public ClassQuestionTokens excludeColumnsOfDailySurveyQuestionList(String[] columns){		
		addSimpleOptions(DAILY_SURVEY_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ClassQuestionTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfDailySurveyQuestionList(verb, value);	
		return this;
	}
}

