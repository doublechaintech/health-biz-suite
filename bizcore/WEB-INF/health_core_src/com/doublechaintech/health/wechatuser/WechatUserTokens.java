
package com.doublechaintech.health.wechatuser;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class WechatUserTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="wechatUser";
	
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
	protected WechatUserTokens(){
		//ensure not initialized outside the class
	}
	public  static  WechatUserTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		WechatUserTokens tokens = new WechatUserTokens(options);
		return tokens;
		
	}
	protected WechatUserTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public WechatUserTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static WechatUserTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected WechatUserTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static WechatUserTokens start(){
		return new WechatUserTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static WechatUserTokens allTokens(){
		
		return start()
			.withAddress()
			.withUserType()
			.withPlatform()
			.withGuardianList()
			.withClassQuestionList()
			.withClassDailyHealthSurveyList()
			.withWechatLoginInfoList();
	
	}
	public static WechatUserTokens withoutListsTokens(){
		
		return start()
			.withAddress()
			.withUserType()
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
	
	public WechatUserTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String ADDRESS = "address";
	public String getAddress(){
		return ADDRESS;
	}
	public WechatUserTokens withAddress(){		
		addSimpleOptions(ADDRESS);
		return this;
	}
	
	
	protected static final String USERTYPE = "userType";
	public String getUserType(){
		return USERTYPE;
	}
	public WechatUserTokens withUserType(){		
		addSimpleOptions(USERTYPE);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public WechatUserTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String GUARDIAN_LIST = "guardianList";
	public String getGuardianList(){
		return GUARDIAN_LIST;
	}
	public WechatUserTokens withGuardianList(){		
		addSimpleOptions(GUARDIAN_LIST);
		return this;
	}
	public WechatUserTokens analyzeGuardianList(){		
		addSimpleOptions(GUARDIAN_LIST+".anaylze");
		return this;
	}
	public boolean analyzeGuardianListEnabled(){		
		
		if(checkOptions(this.options(), GUARDIAN_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public WechatUserTokens extractMoreFromGuardianList(String idsSeperatedWithComma){		
		addSimpleOptions(GUARDIAN_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int guardianListSortCounter = 0;
	public WechatUserTokens sortGuardianListWith(String field, String descOrAsc){		
		addSortMoreOptions(GUARDIAN_LIST,guardianListSortCounter++, field, descOrAsc);
		return this;
	}
	private int guardianListSearchCounter = 0;
	public WechatUserTokens searchGuardianListWith(String field, String verb, String value){		
		
		withGuardianList();
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens searchAllTextOfGuardianList(String verb, String value){	
		String field = "id|name|mobile";
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens rowsPerPageOfGuardianList(int rowsPerPage){		
		addSimpleOptions(GUARDIAN_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public WechatUserTokens currentPageNumberOfGuardianList(int currentPageNumber){		
		addSimpleOptions(GUARDIAN_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public WechatUserTokens retainColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"RetainColumns",columns);
		return this;
	}
	public WechatUserTokens excludeColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CLASS_QUESTION_LIST = "classQuestionList";
	public String getClassQuestionList(){
		return CLASS_QUESTION_LIST;
	}
	public WechatUserTokens withClassQuestionList(){		
		addSimpleOptions(CLASS_QUESTION_LIST);
		return this;
	}
	public WechatUserTokens analyzeClassQuestionList(){		
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
	public WechatUserTokens extractMoreFromClassQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classQuestionListSortCounter = 0;
	public WechatUserTokens sortClassQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_QUESTION_LIST,classQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classQuestionListSearchCounter = 0;
	public WechatUserTokens searchClassQuestionListWith(String field, String verb, String value){		
		
		withClassQuestionList();
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens searchAllTextOfClassQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens rowsPerPageOfClassQuestionList(int rowsPerPage){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public WechatUserTokens currentPageNumberOfClassQuestionList(int currentPageNumber){		
		addSimpleOptions(CLASS_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public WechatUserTokens retainColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public WechatUserTokens excludeColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CLASS_DAILY_HEALTH_SURVEY_LIST = "classDailyHealthSurveyList";
	public String getClassDailyHealthSurveyList(){
		return CLASS_DAILY_HEALTH_SURVEY_LIST;
	}
	public WechatUserTokens withClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST);
		return this;
	}
	public WechatUserTokens analyzeClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeClassDailyHealthSurveyListEnabled(){		
		
		if(checkOptions(this.options(), CLASS_DAILY_HEALTH_SURVEY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public WechatUserTokens extractMoreFromClassDailyHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classDailyHealthSurveyListSortCounter = 0;
	public WechatUserTokens sortClassDailyHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classDailyHealthSurveyListSearchCounter = 0;
	public WechatUserTokens searchClassDailyHealthSurveyListWith(String field, String verb, String value){		
		
		withClassDailyHealthSurveyList();
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens searchAllTextOfClassDailyHealthSurveyList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens rowsPerPageOfClassDailyHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public WechatUserTokens currentPageNumberOfClassDailyHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public WechatUserTokens retainColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public WechatUserTokens excludeColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String WECHAT_LOGIN_INFO_LIST = "wechatLoginInfoList";
	public String getWechatLoginInfoList(){
		return WECHAT_LOGIN_INFO_LIST;
	}
	public WechatUserTokens withWechatLoginInfoList(){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST);
		return this;
	}
	public WechatUserTokens analyzeWechatLoginInfoList(){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+".anaylze");
		return this;
	}
	public boolean analyzeWechatLoginInfoListEnabled(){		
		
		if(checkOptions(this.options(), WECHAT_LOGIN_INFO_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public WechatUserTokens extractMoreFromWechatLoginInfoList(String idsSeperatedWithComma){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int wechatLoginInfoListSortCounter = 0;
	public WechatUserTokens sortWechatLoginInfoListWith(String field, String descOrAsc){		
		addSortMoreOptions(WECHAT_LOGIN_INFO_LIST,wechatLoginInfoListSortCounter++, field, descOrAsc);
		return this;
	}
	private int wechatLoginInfoListSearchCounter = 0;
	public WechatUserTokens searchWechatLoginInfoListWith(String field, String verb, String value){		
		
		withWechatLoginInfoList();
		addSearchMoreOptions(WECHAT_LOGIN_INFO_LIST,wechatLoginInfoListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens searchAllTextOfWechatLoginInfoList(String verb, String value){	
		String field = "id|appId|openId|sessionKey";
		addSearchMoreOptions(WECHAT_LOGIN_INFO_LIST,wechatLoginInfoListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public WechatUserTokens rowsPerPageOfWechatLoginInfoList(int rowsPerPage){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public WechatUserTokens currentPageNumberOfWechatLoginInfoList(int currentPageNumber){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public WechatUserTokens retainColumnsOfWechatLoginInfoList(String[] columns){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"RetainColumns",columns);
		return this;
	}
	public WechatUserTokens excludeColumnsOfWechatLoginInfoList(String[] columns){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  WechatUserTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfGuardianList(verb, value);	
		searchAllTextOfClassQuestionList(verb, value);	
		searchAllTextOfClassDailyHealthSurveyList(verb, value);	
		searchAllTextOfWechatLoginInfoList(verb, value);	
		return this;
	}
}

