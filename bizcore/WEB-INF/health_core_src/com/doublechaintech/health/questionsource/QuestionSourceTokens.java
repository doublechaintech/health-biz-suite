
package com.doublechaintech.health.questionsource;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class QuestionSourceTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="questionSource";
	
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
	protected QuestionSourceTokens(){
		//ensure not initialized outside the class
	}
	public  static  QuestionSourceTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		QuestionSourceTokens tokens = new QuestionSourceTokens(options);
		return tokens;
		
	}
	protected QuestionSourceTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public QuestionSourceTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static QuestionSourceTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected QuestionSourceTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static QuestionSourceTokens start(){
		return new QuestionSourceTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static QuestionSourceTokens allTokens(){
		
		return start()
			.withPlatform()
			.withClassQuestionList();
	
	}
	public static QuestionSourceTokens withoutListsTokens(){
		
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
	
	public QuestionSourceTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public QuestionSourceTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String CLASS_QUESTION_LIST = "classQuestionList";
	public String getClassQuestionList(){
		return CLASS_QUESTION_LIST;
	}
	public QuestionSourceTokens withClassQuestionList(){		
		addSimpleOptions(CLASS_QUESTION_LIST);
		return this;
	}
	public QuestionSourceTokens analyzeClassQuestionList(){		
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
	public QuestionSourceTokens extractMoreFromClassQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classQuestionListSortCounter = 0;
	public QuestionSourceTokens sortClassQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_QUESTION_LIST,classQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classQuestionListSearchCounter = 0;
	public QuestionSourceTokens searchClassQuestionListWith(String field, String verb, String value){		
		
		withClassQuestionList();
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionSourceTokens searchAllTextOfClassQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public QuestionSourceTokens rowsPerPageOfClassQuestionList(int rowsPerPage){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public QuestionSourceTokens currentPageNumberOfClassQuestionList(int currentPageNumber){		
		addSimpleOptions(CLASS_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public QuestionSourceTokens retainColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public QuestionSourceTokens excludeColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  QuestionSourceTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfClassQuestionList(verb, value);	
		return this;
	}
}

