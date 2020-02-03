
package com.doublechaintech.health.user;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class UserTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="user";
	
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
	protected UserTokens(){
		//ensure not initialized outside the class
	}
	public  static  UserTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		UserTokens tokens = new UserTokens(options);
		return tokens;
		
	}
	protected UserTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public UserTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static UserTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected UserTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static UserTokens start(){
		return new UserTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static UserTokens allTokens(){
		
		return start()
			.withAddress()
			.withPlatform()
			.withTeacherList()
			.withStudentList()
			.withQuestionList()
			.withClassDailyHealthSurveyList()
			.withWechatLoginInfoList();
	
	}
	public static UserTokens withoutListsTokens(){
		
		return start()
			.withAddress()
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
	
	public UserTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String ADDRESS = "address";
	public String getAddress(){
		return ADDRESS;
	}
	public UserTokens withAddress(){		
		addSimpleOptions(ADDRESS);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public UserTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String TEACHER_LIST = "teacherList";
	public String getTeacherList(){
		return TEACHER_LIST;
	}
	public UserTokens withTeacherList(){		
		addSimpleOptions(TEACHER_LIST);
		return this;
	}
	public UserTokens analyzeTeacherList(){		
		addSimpleOptions(TEACHER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeTeacherListEnabled(){		
		
		if(checkOptions(this.options(), TEACHER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromTeacherList(String idsSeperatedWithComma){		
		addSimpleOptions(TEACHER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int teacherListSortCounter = 0;
	public UserTokens sortTeacherListWith(String field, String descOrAsc){		
		addSortMoreOptions(TEACHER_LIST,teacherListSortCounter++, field, descOrAsc);
		return this;
	}
	private int teacherListSearchCounter = 0;
	public UserTokens searchTeacherListWith(String field, String verb, String value){		
		
		withTeacherList();
		addSearchMoreOptions(TEACHER_LIST,teacherListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfTeacherList(String verb, String value){	
		String field = "id|name|mobile|school|schoolClass";
		addSearchMoreOptions(TEACHER_LIST,teacherListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfTeacherList(int rowsPerPage){		
		addSimpleOptions(TEACHER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfTeacherList(int currentPageNumber){		
		addSimpleOptions(TEACHER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfTeacherList(String[] columns){		
		addSimpleOptions(TEACHER_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfTeacherList(String[] columns){		
		addSimpleOptions(TEACHER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_LIST = "studentList";
	public String getStudentList(){
		return STUDENT_LIST;
	}
	public UserTokens withStudentList(){		
		addSimpleOptions(STUDENT_LIST);
		return this;
	}
	public UserTokens analyzeStudentList(){		
		addSimpleOptions(STUDENT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromStudentList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentListSortCounter = 0;
	public UserTokens sortStudentListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_LIST,studentListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentListSearchCounter = 0;
	public UserTokens searchStudentListWith(String field, String verb, String value){		
		
		withStudentList();
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfStudentList(String verb, String value){	
		String field = "id|studentName|studentNumber|guardianName|guardianMobile";
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfStudentList(int rowsPerPage){		
		addSimpleOptions(STUDENT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfStudentList(int currentPageNumber){		
		addSimpleOptions(STUDENT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String QUESTION_LIST = "questionList";
	public String getQuestionList(){
		return QUESTION_LIST;
	}
	public UserTokens withQuestionList(){		
		addSimpleOptions(QUESTION_LIST);
		return this;
	}
	public UserTokens analyzeQuestionList(){		
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
	public UserTokens extractMoreFromQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int questionListSortCounter = 0;
	public UserTokens sortQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(QUESTION_LIST,questionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int questionListSearchCounter = 0;
	public UserTokens searchQuestionListWith(String field, String verb, String value){		
		
		withQuestionList();
		addSearchMoreOptions(QUESTION_LIST,questionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(QUESTION_LIST,questionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfQuestionList(int rowsPerPage){		
		addSimpleOptions(QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfQuestionList(int currentPageNumber){		
		addSimpleOptions(QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfQuestionList(String[] columns){		
		addSimpleOptions(QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfQuestionList(String[] columns){		
		addSimpleOptions(QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CLASS_DAILY_HEALTH_SURVEY_LIST = "classDailyHealthSurveyList";
	public String getClassDailyHealthSurveyList(){
		return CLASS_DAILY_HEALTH_SURVEY_LIST;
	}
	public UserTokens withClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST);
		return this;
	}
	public UserTokens analyzeClassDailyHealthSurveyList(){		
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
	public UserTokens extractMoreFromClassDailyHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classDailyHealthSurveyListSortCounter = 0;
	public UserTokens sortClassDailyHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classDailyHealthSurveyListSearchCounter = 0;
	public UserTokens searchClassDailyHealthSurveyListWith(String field, String verb, String value){		
		
		withClassDailyHealthSurveyList();
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfClassDailyHealthSurveyList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfClassDailyHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfClassDailyHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String WECHAT_LOGIN_INFO_LIST = "wechatLoginInfoList";
	public String getWechatLoginInfoList(){
		return WECHAT_LOGIN_INFO_LIST;
	}
	public UserTokens withWechatLoginInfoList(){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST);
		return this;
	}
	public UserTokens analyzeWechatLoginInfoList(){		
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
	public UserTokens extractMoreFromWechatLoginInfoList(String idsSeperatedWithComma){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int wechatLoginInfoListSortCounter = 0;
	public UserTokens sortWechatLoginInfoListWith(String field, String descOrAsc){		
		addSortMoreOptions(WECHAT_LOGIN_INFO_LIST,wechatLoginInfoListSortCounter++, field, descOrAsc);
		return this;
	}
	private int wechatLoginInfoListSearchCounter = 0;
	public UserTokens searchWechatLoginInfoListWith(String field, String verb, String value){		
		
		withWechatLoginInfoList();
		addSearchMoreOptions(WECHAT_LOGIN_INFO_LIST,wechatLoginInfoListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfWechatLoginInfoList(String verb, String value){	
		String field = "id|appId|openId|sessionKey";
		addSearchMoreOptions(WECHAT_LOGIN_INFO_LIST,wechatLoginInfoListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfWechatLoginInfoList(int rowsPerPage){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfWechatLoginInfoList(int currentPageNumber){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfWechatLoginInfoList(String[] columns){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfWechatLoginInfoList(String[] columns){		
		addSimpleOptions(WECHAT_LOGIN_INFO_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  UserTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfTeacherList(verb, value);	
		searchAllTextOfStudentList(verb, value);	
		searchAllTextOfQuestionList(verb, value);	
		searchAllTextOfClassDailyHealthSurveyList(verb, value);	
		searchAllTextOfWechatLoginInfoList(verb, value);	
		return this;
	}
}

