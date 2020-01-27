
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
			.withSchoolClassList()
			.withTeacherList()
			.withGuardianList()
			.withQuestionList()
			.withQuestionTypeList()
			.withQuestionSourceList()
			.withSurveyStatusList()
			.withWechatUserList()
			.withUserTypeList()
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
	
	
		
	protected static final String SCHOOL_CLASS_LIST = "schoolClassList";
	public String getSchoolClassList(){
		return SCHOOL_CLASS_LIST;
	}
	public PlatformTokens withSchoolClassList(){		
		addSimpleOptions(SCHOOL_CLASS_LIST);
		return this;
	}
	public PlatformTokens analyzeSchoolClassList(){		
		addSimpleOptions(SCHOOL_CLASS_LIST+".anaylze");
		return this;
	}
	public boolean analyzeSchoolClassListEnabled(){		
		
		if(checkOptions(this.options(), SCHOOL_CLASS_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromSchoolClassList(String idsSeperatedWithComma){		
		addSimpleOptions(SCHOOL_CLASS_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int schoolClassListSortCounter = 0;
	public PlatformTokens sortSchoolClassListWith(String field, String descOrAsc){		
		addSortMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSortCounter++, field, descOrAsc);
		return this;
	}
	private int schoolClassListSearchCounter = 0;
	public PlatformTokens searchSchoolClassListWith(String field, String verb, String value){		
		
		withSchoolClassList();
		addSearchMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfSchoolClassList(String verb, String value){	
		String field = "id|name|schoole";
		addSearchMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfSchoolClassList(int rowsPerPage){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfSchoolClassList(int currentPageNumber){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfSchoolClassList(String[] columns){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfSchoolClassList(String[] columns){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"ExcludeColumns",columns);
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
		String field = "id|name|mobile|schoole";
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
	
	
		
	protected static final String GUARDIAN_LIST = "guardianList";
	public String getGuardianList(){
		return GUARDIAN_LIST;
	}
	public PlatformTokens withGuardianList(){		
		addSimpleOptions(GUARDIAN_LIST);
		return this;
	}
	public PlatformTokens analyzeGuardianList(){		
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
	public PlatformTokens extractMoreFromGuardianList(String idsSeperatedWithComma){		
		addSimpleOptions(GUARDIAN_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int guardianListSortCounter = 0;
	public PlatformTokens sortGuardianListWith(String field, String descOrAsc){		
		addSortMoreOptions(GUARDIAN_LIST,guardianListSortCounter++, field, descOrAsc);
		return this;
	}
	private int guardianListSearchCounter = 0;
	public PlatformTokens searchGuardianListWith(String field, String verb, String value){		
		
		withGuardianList();
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfGuardianList(String verb, String value){	
		String field = "id|name|mobile";
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfGuardianList(int rowsPerPage){		
		addSimpleOptions(GUARDIAN_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfGuardianList(int currentPageNumber){		
		addSimpleOptions(GUARDIAN_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"ExcludeColumns",columns);
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
	
	
		
	protected static final String QUESTION_SOURCE_LIST = "questionSourceList";
	public String getQuestionSourceList(){
		return QUESTION_SOURCE_LIST;
	}
	public PlatformTokens withQuestionSourceList(){		
		addSimpleOptions(QUESTION_SOURCE_LIST);
		return this;
	}
	public PlatformTokens analyzeQuestionSourceList(){		
		addSimpleOptions(QUESTION_SOURCE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeQuestionSourceListEnabled(){		
		
		if(checkOptions(this.options(), QUESTION_SOURCE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromQuestionSourceList(String idsSeperatedWithComma){		
		addSimpleOptions(QUESTION_SOURCE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int questionSourceListSortCounter = 0;
	public PlatformTokens sortQuestionSourceListWith(String field, String descOrAsc){		
		addSortMoreOptions(QUESTION_SOURCE_LIST,questionSourceListSortCounter++, field, descOrAsc);
		return this;
	}
	private int questionSourceListSearchCounter = 0;
	public PlatformTokens searchQuestionSourceListWith(String field, String verb, String value){		
		
		withQuestionSourceList();
		addSearchMoreOptions(QUESTION_SOURCE_LIST,questionSourceListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfQuestionSourceList(String verb, String value){	
		String field = "id|name|code";
		addSearchMoreOptions(QUESTION_SOURCE_LIST,questionSourceListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfQuestionSourceList(int rowsPerPage){		
		addSimpleOptions(QUESTION_SOURCE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfQuestionSourceList(int currentPageNumber){		
		addSimpleOptions(QUESTION_SOURCE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfQuestionSourceList(String[] columns){		
		addSimpleOptions(QUESTION_SOURCE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfQuestionSourceList(String[] columns){		
		addSimpleOptions(QUESTION_SOURCE_LIST+"ExcludeColumns",columns);
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
	
	
		
	protected static final String WECHAT_USER_LIST = "wechatUserList";
	public String getWechatUserList(){
		return WECHAT_USER_LIST;
	}
	public PlatformTokens withWechatUserList(){		
		addSimpleOptions(WECHAT_USER_LIST);
		return this;
	}
	public PlatformTokens analyzeWechatUserList(){		
		addSimpleOptions(WECHAT_USER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeWechatUserListEnabled(){		
		
		if(checkOptions(this.options(), WECHAT_USER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromWechatUserList(String idsSeperatedWithComma){		
		addSimpleOptions(WECHAT_USER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int wechatUserListSortCounter = 0;
	public PlatformTokens sortWechatUserListWith(String field, String descOrAsc){		
		addSortMoreOptions(WECHAT_USER_LIST,wechatUserListSortCounter++, field, descOrAsc);
		return this;
	}
	private int wechatUserListSearchCounter = 0;
	public PlatformTokens searchWechatUserListWith(String field, String verb, String value){		
		
		withWechatUserList();
		addSearchMoreOptions(WECHAT_USER_LIST,wechatUserListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfWechatUserList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(WECHAT_USER_LIST,wechatUserListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfWechatUserList(int rowsPerPage){		
		addSimpleOptions(WECHAT_USER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfWechatUserList(int currentPageNumber){		
		addSimpleOptions(WECHAT_USER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfWechatUserList(String[] columns){		
		addSimpleOptions(WECHAT_USER_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfWechatUserList(String[] columns){		
		addSimpleOptions(WECHAT_USER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String USER_TYPE_LIST = "userTypeList";
	public String getUserTypeList(){
		return USER_TYPE_LIST;
	}
	public PlatformTokens withUserTypeList(){		
		addSimpleOptions(USER_TYPE_LIST);
		return this;
	}
	public PlatformTokens analyzeUserTypeList(){		
		addSimpleOptions(USER_TYPE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeUserTypeListEnabled(){		
		
		if(checkOptions(this.options(), USER_TYPE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromUserTypeList(String idsSeperatedWithComma){		
		addSimpleOptions(USER_TYPE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int userTypeListSortCounter = 0;
	public PlatformTokens sortUserTypeListWith(String field, String descOrAsc){		
		addSortMoreOptions(USER_TYPE_LIST,userTypeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int userTypeListSearchCounter = 0;
	public PlatformTokens searchUserTypeListWith(String field, String verb, String value){		
		
		withUserTypeList();
		addSearchMoreOptions(USER_TYPE_LIST,userTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfUserTypeList(String verb, String value){	
		String field = "id|name|code";
		addSearchMoreOptions(USER_TYPE_LIST,userTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfUserTypeList(int rowsPerPage){		
		addSimpleOptions(USER_TYPE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfUserTypeList(int currentPageNumber){		
		addSimpleOptions(USER_TYPE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfUserTypeList(String[] columns){		
		addSimpleOptions(USER_TYPE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfUserTypeList(String[] columns){		
		addSimpleOptions(USER_TYPE_LIST+"ExcludeColumns",columns);
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
		searchAllTextOfSchoolClassList(verb, value);	
		searchAllTextOfTeacherList(verb, value);	
		searchAllTextOfGuardianList(verb, value);	
		searchAllTextOfQuestionList(verb, value);	
		searchAllTextOfQuestionTypeList(verb, value);	
		searchAllTextOfQuestionSourceList(verb, value);	
		searchAllTextOfSurveyStatusList(verb, value);	
		searchAllTextOfWechatUserList(verb, value);	
		searchAllTextOfUserTypeList(verb, value);	
		searchAllTextOfChangeRequestList(verb, value);	
		searchAllTextOfChangeRequestTypeList(verb, value);	
		return this;
	}
}

