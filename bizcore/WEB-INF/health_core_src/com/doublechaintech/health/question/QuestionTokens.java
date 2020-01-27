
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
			.withPlatform();
	
	}
	public static QuestionTokens withoutListsTokens(){
		
		return start()
			.withQuestionType()
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
	
	
	
	public  QuestionTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

