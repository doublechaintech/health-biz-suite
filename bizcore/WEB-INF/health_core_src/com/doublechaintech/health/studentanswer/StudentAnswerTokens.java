
package com.doublechaintech.health.studentanswer;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class StudentAnswerTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="studentAnswer";
	
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
	protected StudentAnswerTokens(){
		//ensure not initialized outside the class
	}
	public  static  StudentAnswerTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		StudentAnswerTokens tokens = new StudentAnswerTokens(options);
		return tokens;
		
	}
	protected StudentAnswerTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public StudentAnswerTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static StudentAnswerTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected StudentAnswerTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static StudentAnswerTokens start(){
		return new StudentAnswerTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static StudentAnswerTokens allTokens(){
		
		return start()
			.withHealthSurveyReport()
			.withDailyAnswer();
	
	}
	public static StudentAnswerTokens withoutListsTokens(){
		
		return start()
			.withHealthSurveyReport()
			.withDailyAnswer();
	
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
	
	public StudentAnswerTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String HEALTHSURVEYREPORT = "healthSurveyReport";
	public String getHealthSurveyReport(){
		return HEALTHSURVEYREPORT;
	}
	public StudentAnswerTokens withHealthSurveyReport(){		
		addSimpleOptions(HEALTHSURVEYREPORT);
		return this;
	}
	
	
	protected static final String DAILYANSWER = "dailyAnswer";
	public String getDailyAnswer(){
		return DAILYANSWER;
	}
	public StudentAnswerTokens withDailyAnswer(){		
		addSimpleOptions(DAILYANSWER);
		return this;
	}
	
	
	
	public  StudentAnswerTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

