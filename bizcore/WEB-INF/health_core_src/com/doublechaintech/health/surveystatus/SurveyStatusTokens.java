
package com.doublechaintech.health.surveystatus;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class SurveyStatusTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="surveyStatus";
	
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
	protected SurveyStatusTokens(){
		//ensure not initialized outside the class
	}
	public  static  SurveyStatusTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		SurveyStatusTokens tokens = new SurveyStatusTokens(options);
		return tokens;
		
	}
	protected SurveyStatusTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public SurveyStatusTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static SurveyStatusTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected SurveyStatusTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static SurveyStatusTokens start(){
		return new SurveyStatusTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static SurveyStatusTokens allTokens(){
		
		return start()
			.withPlatform()
			.withStudentHealthSurveyList();
	
	}
	public static SurveyStatusTokens withoutListsTokens(){
		
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
	
	public SurveyStatusTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public SurveyStatusTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String STUDENT_HEALTH_SURVEY_LIST = "studentHealthSurveyList";
	public String getStudentHealthSurveyList(){
		return STUDENT_HEALTH_SURVEY_LIST;
	}
	public SurveyStatusTokens withStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST);
		return this;
	}
	public SurveyStatusTokens analyzeStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentHealthSurveyListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_HEALTH_SURVEY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public SurveyStatusTokens extractMoreFromStudentHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentHealthSurveyListSortCounter = 0;
	public SurveyStatusTokens sortStudentHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentHealthSurveyListSearchCounter = 0;
	public SurveyStatusTokens searchStudentHealthSurveyListWith(String field, String verb, String value){		
		
		withStudentHealthSurveyList();
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SurveyStatusTokens searchAllTextOfStudentHealthSurveyList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public SurveyStatusTokens rowsPerPageOfStudentHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public SurveyStatusTokens currentPageNumberOfStudentHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public SurveyStatusTokens retainColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public SurveyStatusTokens excludeColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  SurveyStatusTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfStudentHealthSurveyList(verb, value);	
		return this;
	}
}

