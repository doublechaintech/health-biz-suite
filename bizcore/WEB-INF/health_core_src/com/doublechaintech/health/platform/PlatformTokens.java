
package com.doublechaintech.health.platform;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class PlatformTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="platform";
	
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
	protected PlatformTokens(){
		//ensure not initialized outside the class
	}
	public  static  PlatformTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PlatformTokens tokens = new PlatformTokens(options);
		return tokens;
		
	}
	protected PlatformTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PlatformTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PlatformTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PlatformTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PlatformTokens start(){
		return new PlatformTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PlatformTokens allTokens(){
		
		return start()
			.withProvinceList()
			.withCityList()
			.withDistrictList()
			.withTeacherList()
			.withStudentList()
			.withQuestionList()
			.withQuestionTypeList()
			.withSurveyStatusList()
			.withUserList()
			.withChangeRequestList()
			.withChangeRequestTypeList();
	
	}
	public static PlatformTokens withoutListsTokens(){
		
		return start();
	
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
	
	public PlatformTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PROVINCE_LIST = "provinceList";
	public String getProvinceList(){
		return PROVINCE_LIST;
	}
	public PlatformTokens withProvinceList(){		
		addSimpleOptions(PROVINCE_LIST);
		return this;
	}
	public PlatformTokens analyzeProvinceList(){		
		addSimpleOptions(PROVINCE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeProvinceListEnabled(){		
		
		if(checkOptions(this.options(), PROVINCE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromProvinceList(String idsSeperatedWithComma){		
		addSimpleOptions(PROVINCE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int provinceListSortCounter = 0;
	public PlatformTokens sortProvinceListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROVINCE_LIST,provinceListSortCounter++, field, descOrAsc);
		return this;
	}
	private int provinceListSearchCounter = 0;
	public PlatformTokens searchProvinceListWith(String field, String verb, String value){		
		
		withProvinceList();
		addSearchMoreOptions(PROVINCE_LIST,provinceListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfProvinceList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(PROVINCE_LIST,provinceListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfProvinceList(int rowsPerPage){		
		addSimpleOptions(PROVINCE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfProvinceList(int currentPageNumber){		
		addSimpleOptions(PROVINCE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfProvinceList(String[] columns){		
		addSimpleOptions(PROVINCE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfProvinceList(String[] columns){		
		addSimpleOptions(PROVINCE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CITY_LIST = "cityList";
	public String getCityList(){
		return CITY_LIST;
	}
	public PlatformTokens withCityList(){		
		addSimpleOptions(CITY_LIST);
		return this;
	}
	public PlatformTokens analyzeCityList(){		
		addSimpleOptions(CITY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeCityListEnabled(){		
		
		if(checkOptions(this.options(), CITY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromCityList(String idsSeperatedWithComma){		
		addSimpleOptions(CITY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int cityListSortCounter = 0;
	public PlatformTokens sortCityListWith(String field, String descOrAsc){		
		addSortMoreOptions(CITY_LIST,cityListSortCounter++, field, descOrAsc);
		return this;
	}
	private int cityListSearchCounter = 0;
	public PlatformTokens searchCityListWith(String field, String verb, String value){		
		
		withCityList();
		addSearchMoreOptions(CITY_LIST,cityListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfCityList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CITY_LIST,cityListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfCityList(int rowsPerPage){		
		addSimpleOptions(CITY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfCityList(int currentPageNumber){		
		addSimpleOptions(CITY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfCityList(String[] columns){		
		addSimpleOptions(CITY_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfCityList(String[] columns){		
		addSimpleOptions(CITY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String DISTRICT_LIST = "districtList";
	public String getDistrictList(){
		return DISTRICT_LIST;
	}
	public PlatformTokens withDistrictList(){		
		addSimpleOptions(DISTRICT_LIST);
		return this;
	}
	public PlatformTokens analyzeDistrictList(){		
		addSimpleOptions(DISTRICT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeDistrictListEnabled(){		
		
		if(checkOptions(this.options(), DISTRICT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromDistrictList(String idsSeperatedWithComma){		
		addSimpleOptions(DISTRICT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int districtListSortCounter = 0;
	public PlatformTokens sortDistrictListWith(String field, String descOrAsc){		
		addSortMoreOptions(DISTRICT_LIST,districtListSortCounter++, field, descOrAsc);
		return this;
	}
	private int districtListSearchCounter = 0;
	public PlatformTokens searchDistrictListWith(String field, String verb, String value){		
		
		withDistrictList();
		addSearchMoreOptions(DISTRICT_LIST,districtListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfDistrictList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(DISTRICT_LIST,districtListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfDistrictList(int rowsPerPage){		
		addSimpleOptions(DISTRICT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfDistrictList(int currentPageNumber){		
		addSimpleOptions(DISTRICT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfDistrictList(String[] columns){		
		addSimpleOptions(DISTRICT_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfDistrictList(String[] columns){		
		addSimpleOptions(DISTRICT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String TEACHER_LIST = "teacherList";
	public String getTeacherList(){
		return TEACHER_LIST;
	}
	public PlatformTokens withTeacherList(){		
		addSimpleOptions(TEACHER_LIST);
		return this;
	}
	public PlatformTokens analyzeTeacherList(){		
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
	public PlatformTokens extractMoreFromTeacherList(String idsSeperatedWithComma){		
		addSimpleOptions(TEACHER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int teacherListSortCounter = 0;
	public PlatformTokens sortTeacherListWith(String field, String descOrAsc){		
		addSortMoreOptions(TEACHER_LIST,teacherListSortCounter++, field, descOrAsc);
		return this;
	}
	private int teacherListSearchCounter = 0;
	public PlatformTokens searchTeacherListWith(String field, String verb, String value){		
		
		withTeacherList();
		addSearchMoreOptions(TEACHER_LIST,teacherListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfTeacherList(String verb, String value){	
		String field = "id|name|mobile|school|schoolClass";
		addSearchMoreOptions(TEACHER_LIST,teacherListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfTeacherList(int rowsPerPage){		
		addSimpleOptions(TEACHER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfTeacherList(int currentPageNumber){		
		addSimpleOptions(TEACHER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfTeacherList(String[] columns){		
		addSimpleOptions(TEACHER_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfTeacherList(String[] columns){		
		addSimpleOptions(TEACHER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_LIST = "studentList";
	public String getStudentList(){
		return STUDENT_LIST;
	}
	public PlatformTokens withStudentList(){		
		addSimpleOptions(STUDENT_LIST);
		return this;
	}
	public PlatformTokens analyzeStudentList(){		
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
	public PlatformTokens extractMoreFromStudentList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentListSortCounter = 0;
	public PlatformTokens sortStudentListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_LIST,studentListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentListSearchCounter = 0;
	public PlatformTokens searchStudentListWith(String field, String verb, String value){		
		
		withStudentList();
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfStudentList(String verb, String value){	
		String field = "id|studentName|studentNumber|guardianName|guardianMobile";
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfStudentList(int rowsPerPage){		
		addSimpleOptions(STUDENT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfStudentList(int currentPageNumber){		
		addSimpleOptions(STUDENT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String QUESTION_LIST = "questionList";
	public String getQuestionList(){
		return QUESTION_LIST;
	}
	public PlatformTokens withQuestionList(){		
		addSimpleOptions(QUESTION_LIST);
		return this;
	}
	public PlatformTokens analyzeQuestionList(){		
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
	public PlatformTokens extractMoreFromQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int questionListSortCounter = 0;
	public PlatformTokens sortQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(QUESTION_LIST,questionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int questionListSearchCounter = 0;
	public PlatformTokens searchQuestionListWith(String field, String verb, String value){		
		
		withQuestionList();
		addSearchMoreOptions(QUESTION_LIST,questionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(QUESTION_LIST,questionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfQuestionList(int rowsPerPage){		
		addSimpleOptions(QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfQuestionList(int currentPageNumber){		
		addSimpleOptions(QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfQuestionList(String[] columns){		
		addSimpleOptions(QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfQuestionList(String[] columns){		
		addSimpleOptions(QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String QUESTION_TYPE_LIST = "questionTypeList";
	public String getQuestionTypeList(){
		return QUESTION_TYPE_LIST;
	}
	public PlatformTokens withQuestionTypeList(){		
		addSimpleOptions(QUESTION_TYPE_LIST);
		return this;
	}
	public PlatformTokens analyzeQuestionTypeList(){		
		addSimpleOptions(QUESTION_TYPE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeQuestionTypeListEnabled(){		
		
		if(checkOptions(this.options(), QUESTION_TYPE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromQuestionTypeList(String idsSeperatedWithComma){		
		addSimpleOptions(QUESTION_TYPE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int questionTypeListSortCounter = 0;
	public PlatformTokens sortQuestionTypeListWith(String field, String descOrAsc){		
		addSortMoreOptions(QUESTION_TYPE_LIST,questionTypeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int questionTypeListSearchCounter = 0;
	public PlatformTokens searchQuestionTypeListWith(String field, String verb, String value){		
		
		withQuestionTypeList();
		addSearchMoreOptions(QUESTION_TYPE_LIST,questionTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfQuestionTypeList(String verb, String value){	
		String field = "id|name|code";
		addSearchMoreOptions(QUESTION_TYPE_LIST,questionTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfQuestionTypeList(int rowsPerPage){		
		addSimpleOptions(QUESTION_TYPE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfQuestionTypeList(int currentPageNumber){		
		addSimpleOptions(QUESTION_TYPE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfQuestionTypeList(String[] columns){		
		addSimpleOptions(QUESTION_TYPE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfQuestionTypeList(String[] columns){		
		addSimpleOptions(QUESTION_TYPE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String SURVEY_STATUS_LIST = "surveyStatusList";
	public String getSurveyStatusList(){
		return SURVEY_STATUS_LIST;
	}
	public PlatformTokens withSurveyStatusList(){		
		addSimpleOptions(SURVEY_STATUS_LIST);
		return this;
	}
	public PlatformTokens analyzeSurveyStatusList(){		
		addSimpleOptions(SURVEY_STATUS_LIST+".anaylze");
		return this;
	}
	public boolean analyzeSurveyStatusListEnabled(){		
		
		if(checkOptions(this.options(), SURVEY_STATUS_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromSurveyStatusList(String idsSeperatedWithComma){		
		addSimpleOptions(SURVEY_STATUS_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int surveyStatusListSortCounter = 0;
	public PlatformTokens sortSurveyStatusListWith(String field, String descOrAsc){		
		addSortMoreOptions(SURVEY_STATUS_LIST,surveyStatusListSortCounter++, field, descOrAsc);
		return this;
	}
	private int surveyStatusListSearchCounter = 0;
	public PlatformTokens searchSurveyStatusListWith(String field, String verb, String value){		
		
		withSurveyStatusList();
		addSearchMoreOptions(SURVEY_STATUS_LIST,surveyStatusListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfSurveyStatusList(String verb, String value){	
		String field = "id|name|code";
		addSearchMoreOptions(SURVEY_STATUS_LIST,surveyStatusListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfSurveyStatusList(int rowsPerPage){		
		addSimpleOptions(SURVEY_STATUS_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfSurveyStatusList(int currentPageNumber){		
		addSimpleOptions(SURVEY_STATUS_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfSurveyStatusList(String[] columns){		
		addSimpleOptions(SURVEY_STATUS_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfSurveyStatusList(String[] columns){		
		addSimpleOptions(SURVEY_STATUS_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String USER_LIST = "userList";
	public String getUserList(){
		return USER_LIST;
	}
	public PlatformTokens withUserList(){		
		addSimpleOptions(USER_LIST);
		return this;
	}
	public PlatformTokens analyzeUserList(){		
		addSimpleOptions(USER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeUserListEnabled(){		
		
		if(checkOptions(this.options(), USER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromUserList(String idsSeperatedWithComma){		
		addSimpleOptions(USER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int userListSortCounter = 0;
	public PlatformTokens sortUserListWith(String field, String descOrAsc){		
		addSortMoreOptions(USER_LIST,userListSortCounter++, field, descOrAsc);
		return this;
	}
	private int userListSearchCounter = 0;
	public PlatformTokens searchUserListWith(String field, String verb, String value){		
		
		withUserList();
		addSearchMoreOptions(USER_LIST,userListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfUserList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(USER_LIST,userListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfUserList(int rowsPerPage){		
		addSimpleOptions(USER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfUserList(int currentPageNumber){		
		addSimpleOptions(USER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfUserList(String[] columns){		
		addSimpleOptions(USER_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfUserList(String[] columns){		
		addSimpleOptions(USER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CHANGE_REQUEST_LIST = "changeRequestList";
	public String getChangeRequestList(){
		return CHANGE_REQUEST_LIST;
	}
	public PlatformTokens withChangeRequestList(){		
		addSimpleOptions(CHANGE_REQUEST_LIST);
		return this;
	}
	public PlatformTokens analyzeChangeRequestList(){		
		addSimpleOptions(CHANGE_REQUEST_LIST+".anaylze");
		return this;
	}
	public boolean analyzeChangeRequestListEnabled(){		
		
		if(checkOptions(this.options(), CHANGE_REQUEST_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromChangeRequestList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestListSortCounter = 0;
	public PlatformTokens sortChangeRequestListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestListSearchCounter = 0;
	public PlatformTokens searchChangeRequestListWith(String field, String verb, String value){		
		
		withChangeRequestList();
		addSearchMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfChangeRequestList(String verb, String value){	
		String field = "id|name|remoteIp";
		addSearchMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfChangeRequestList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfChangeRequestList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfChangeRequestList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfChangeRequestList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CHANGE_REQUEST_TYPE_LIST = "changeRequestTypeList";
	public String getChangeRequestTypeList(){
		return CHANGE_REQUEST_TYPE_LIST;
	}
	public PlatformTokens withChangeRequestTypeList(){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST);
		return this;
	}
	public PlatformTokens analyzeChangeRequestTypeList(){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeChangeRequestTypeListEnabled(){		
		
		if(checkOptions(this.options(), CHANGE_REQUEST_TYPE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromChangeRequestTypeList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestTypeListSortCounter = 0;
	public PlatformTokens sortChangeRequestTypeListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_TYPE_LIST,changeRequestTypeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestTypeListSearchCounter = 0;
	public PlatformTokens searchChangeRequestTypeListWith(String field, String verb, String value){		
		
		withChangeRequestTypeList();
		addSearchMoreOptions(CHANGE_REQUEST_TYPE_LIST,changeRequestTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfChangeRequestTypeList(String verb, String value){	
		String field = "id|name|code|icon|bindTypes|stepConfiguration";
		addSearchMoreOptions(CHANGE_REQUEST_TYPE_LIST,changeRequestTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfChangeRequestTypeList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfChangeRequestTypeList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfChangeRequestTypeList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfChangeRequestTypeList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PlatformTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfProvinceList(verb, value);	
		searchAllTextOfCityList(verb, value);	
		searchAllTextOfDistrictList(verb, value);	
		searchAllTextOfTeacherList(verb, value);	
		searchAllTextOfStudentList(verb, value);	
		searchAllTextOfQuestionList(verb, value);	
		searchAllTextOfQuestionTypeList(verb, value);	
		searchAllTextOfSurveyStatusList(verb, value);	
		searchAllTextOfUserList(verb, value);	
		searchAllTextOfChangeRequestList(verb, value);	
		searchAllTextOfChangeRequestTypeList(verb, value);	
		return this;
	}
}

