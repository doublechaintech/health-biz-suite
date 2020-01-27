
package com.doublechaintech.health.changerequest;
import com.doublechaintech.health.CommonTokens;
import java.util.Map;
public class ChangeRequestTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="changeRequest";
	
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
	protected ChangeRequestTokens(){
		//ensure not initialized outside the class
	}
	public  static  ChangeRequestTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ChangeRequestTokens tokens = new ChangeRequestTokens(options);
		return tokens;
		
	}
	protected ChangeRequestTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ChangeRequestTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ChangeRequestTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ChangeRequestTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ChangeRequestTokens start(){
		return new ChangeRequestTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ChangeRequestTokens allTokens(){
		
		return start()
			.withRequestType()
			.withPlatform()
			.withSchoolClassList()
			.withTeacherList()
			.withGuardianList()
			.withClassQuestionList()
			.withClassDailyHealthSurveyList()
			.withStudentList()
			.withStudentHealthSurveyList()
			.withStudentDailyAnswerList();
	
	}
	public static ChangeRequestTokens withoutListsTokens(){
		
		return start()
			.withRequestType()
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
	
	public ChangeRequestTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String REQUESTTYPE = "requestType";
	public String getRequestType(){
		return REQUESTTYPE;
	}
	public ChangeRequestTokens withRequestType(){		
		addSimpleOptions(REQUESTTYPE);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ChangeRequestTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String SCHOOL_CLASS_LIST = "schoolClassList";
	public String getSchoolClassList(){
		return SCHOOL_CLASS_LIST;
	}
	public ChangeRequestTokens withSchoolClassList(){		
		addSimpleOptions(SCHOOL_CLASS_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeSchoolClassList(){		
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
	public ChangeRequestTokens extractMoreFromSchoolClassList(String idsSeperatedWithComma){		
		addSimpleOptions(SCHOOL_CLASS_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int schoolClassListSortCounter = 0;
	public ChangeRequestTokens sortSchoolClassListWith(String field, String descOrAsc){		
		addSortMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSortCounter++, field, descOrAsc);
		return this;
	}
	private int schoolClassListSearchCounter = 0;
	public ChangeRequestTokens searchSchoolClassListWith(String field, String verb, String value){		
		
		withSchoolClassList();
		addSearchMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfSchoolClassList(String verb, String value){	
		String field = "id|name|schoole";
		addSearchMoreOptions(SCHOOL_CLASS_LIST,schoolClassListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfSchoolClassList(int rowsPerPage){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfSchoolClassList(int currentPageNumber){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfSchoolClassList(String[] columns){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfSchoolClassList(String[] columns){		
		addSimpleOptions(SCHOOL_CLASS_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String TEACHER_LIST = "teacherList";
	public String getTeacherList(){
		return TEACHER_LIST;
	}
	public ChangeRequestTokens withTeacherList(){		
		addSimpleOptions(TEACHER_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeTeacherList(){		
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
	public ChangeRequestTokens extractMoreFromTeacherList(String idsSeperatedWithComma){		
		addSimpleOptions(TEACHER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int teacherListSortCounter = 0;
	public ChangeRequestTokens sortTeacherListWith(String field, String descOrAsc){		
		addSortMoreOptions(TEACHER_LIST,teacherListSortCounter++, field, descOrAsc);
		return this;
	}
	private int teacherListSearchCounter = 0;
	public ChangeRequestTokens searchTeacherListWith(String field, String verb, String value){		
		
		withTeacherList();
		addSearchMoreOptions(TEACHER_LIST,teacherListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfTeacherList(String verb, String value){	
		String field = "id|name|mobile|schoole";
		addSearchMoreOptions(TEACHER_LIST,teacherListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfTeacherList(int rowsPerPage){		
		addSimpleOptions(TEACHER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfTeacherList(int currentPageNumber){		
		addSimpleOptions(TEACHER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfTeacherList(String[] columns){		
		addSimpleOptions(TEACHER_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfTeacherList(String[] columns){		
		addSimpleOptions(TEACHER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String GUARDIAN_LIST = "guardianList";
	public String getGuardianList(){
		return GUARDIAN_LIST;
	}
	public ChangeRequestTokens withGuardianList(){		
		addSimpleOptions(GUARDIAN_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeGuardianList(){		
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
	public ChangeRequestTokens extractMoreFromGuardianList(String idsSeperatedWithComma){		
		addSimpleOptions(GUARDIAN_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int guardianListSortCounter = 0;
	public ChangeRequestTokens sortGuardianListWith(String field, String descOrAsc){		
		addSortMoreOptions(GUARDIAN_LIST,guardianListSortCounter++, field, descOrAsc);
		return this;
	}
	private int guardianListSearchCounter = 0;
	public ChangeRequestTokens searchGuardianListWith(String field, String verb, String value){		
		
		withGuardianList();
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfGuardianList(String verb, String value){	
		String field = "id|name|mobile";
		addSearchMoreOptions(GUARDIAN_LIST,guardianListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfGuardianList(int rowsPerPage){		
		addSimpleOptions(GUARDIAN_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfGuardianList(int currentPageNumber){		
		addSimpleOptions(GUARDIAN_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfGuardianList(String[] columns){		
		addSimpleOptions(GUARDIAN_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CLASS_QUESTION_LIST = "classQuestionList";
	public String getClassQuestionList(){
		return CLASS_QUESTION_LIST;
	}
	public ChangeRequestTokens withClassQuestionList(){		
		addSimpleOptions(CLASS_QUESTION_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeClassQuestionList(){		
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
	public ChangeRequestTokens extractMoreFromClassQuestionList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_QUESTION_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classQuestionListSortCounter = 0;
	public ChangeRequestTokens sortClassQuestionListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_QUESTION_LIST,classQuestionListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classQuestionListSearchCounter = 0;
	public ChangeRequestTokens searchClassQuestionListWith(String field, String verb, String value){		
		
		withClassQuestionList();
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfClassQuestionList(String verb, String value){	
		String field = "id|topic|optionA|optionB|optionC|optionD";
		addSearchMoreOptions(CLASS_QUESTION_LIST,classQuestionListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfClassQuestionList(int rowsPerPage){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfClassQuestionList(int currentPageNumber){		
		addSimpleOptions(CLASS_QUESTION_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfClassQuestionList(String[] columns){		
		addSimpleOptions(CLASS_QUESTION_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CLASS_DAILY_HEALTH_SURVEY_LIST = "classDailyHealthSurveyList";
	public String getClassDailyHealthSurveyList(){
		return CLASS_DAILY_HEALTH_SURVEY_LIST;
	}
	public ChangeRequestTokens withClassDailyHealthSurveyList(){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeClassDailyHealthSurveyList(){		
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
	public ChangeRequestTokens extractMoreFromClassDailyHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int classDailyHealthSurveyListSortCounter = 0;
	public ChangeRequestTokens sortClassDailyHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int classDailyHealthSurveyListSearchCounter = 0;
	public ChangeRequestTokens searchClassDailyHealthSurveyListWith(String field, String verb, String value){		
		
		withClassDailyHealthSurveyList();
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfClassDailyHealthSurveyList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(CLASS_DAILY_HEALTH_SURVEY_LIST,classDailyHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfClassDailyHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfClassDailyHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfClassDailyHealthSurveyList(String[] columns){		
		addSimpleOptions(CLASS_DAILY_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_LIST = "studentList";
	public String getStudentList(){
		return STUDENT_LIST;
	}
	public ChangeRequestTokens withStudentList(){		
		addSimpleOptions(STUDENT_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeStudentList(){		
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
	public ChangeRequestTokens extractMoreFromStudentList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentListSortCounter = 0;
	public ChangeRequestTokens sortStudentListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_LIST,studentListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentListSearchCounter = 0;
	public ChangeRequestTokens searchStudentListWith(String field, String verb, String value){		
		
		withStudentList();
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfStudentList(String verb, String value){	
		String field = "id|name|gender|studentId";
		addSearchMoreOptions(STUDENT_LIST,studentListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfStudentList(int rowsPerPage){		
		addSimpleOptions(STUDENT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfStudentList(int currentPageNumber){		
		addSimpleOptions(STUDENT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfStudentList(String[] columns){		
		addSimpleOptions(STUDENT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_HEALTH_SURVEY_LIST = "studentHealthSurveyList";
	public String getStudentHealthSurveyList(){
		return STUDENT_HEALTH_SURVEY_LIST;
	}
	public ChangeRequestTokens withStudentHealthSurveyList(){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeStudentHealthSurveyList(){		
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
	public ChangeRequestTokens extractMoreFromStudentHealthSurveyList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentHealthSurveyListSortCounter = 0;
	public ChangeRequestTokens sortStudentHealthSurveyListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentHealthSurveyListSearchCounter = 0;
	public ChangeRequestTokens searchStudentHealthSurveyListWith(String field, String verb, String value){		
		
		withStudentHealthSurveyList();
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfStudentHealthSurveyList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(STUDENT_HEALTH_SURVEY_LIST,studentHealthSurveyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfStudentHealthSurveyList(int rowsPerPage){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfStudentHealthSurveyList(int currentPageNumber){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfStudentHealthSurveyList(String[] columns){		
		addSimpleOptions(STUDENT_HEALTH_SURVEY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String STUDENT_DAILY_ANSWER_LIST = "studentDailyAnswerList";
	public String getStudentDailyAnswerList(){
		return STUDENT_DAILY_ANSWER_LIST;
	}
	public ChangeRequestTokens withStudentDailyAnswerList(){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeStudentDailyAnswerList(){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeStudentDailyAnswerListEnabled(){		
		
		if(checkOptions(this.options(), STUDENT_DAILY_ANSWER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ChangeRequestTokens extractMoreFromStudentDailyAnswerList(String idsSeperatedWithComma){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int studentDailyAnswerListSortCounter = 0;
	public ChangeRequestTokens sortStudentDailyAnswerListWith(String field, String descOrAsc){		
		addSortMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSortCounter++, field, descOrAsc);
		return this;
	}
	private int studentDailyAnswerListSearchCounter = 0;
	public ChangeRequestTokens searchStudentDailyAnswerListWith(String field, String verb, String value){		
		
		withStudentDailyAnswerList();
		addSearchMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfStudentDailyAnswerList(String verb, String value){	
		String field = "id|answer";
		addSearchMoreOptions(STUDENT_DAILY_ANSWER_LIST,studentDailyAnswerListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfStudentDailyAnswerList(int rowsPerPage){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfStudentDailyAnswerList(int currentPageNumber){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfStudentDailyAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfStudentDailyAnswerList(String[] columns){		
		addSimpleOptions(STUDENT_DAILY_ANSWER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ChangeRequestTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfSchoolClassList(verb, value);	
		searchAllTextOfTeacherList(verb, value);	
		searchAllTextOfGuardianList(verb, value);	
		searchAllTextOfClassQuestionList(verb, value);	
		searchAllTextOfClassDailyHealthSurveyList(verb, value);	
		searchAllTextOfStudentList(verb, value);	
		searchAllTextOfStudentHealthSurveyList(verb, value);	
		searchAllTextOfStudentDailyAnswerList(verb, value);	
		return this;
	}
}

