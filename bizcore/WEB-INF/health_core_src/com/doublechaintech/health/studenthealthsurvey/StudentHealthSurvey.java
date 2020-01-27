
package com.doublechaintech.health.studenthealthsurvey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;

@JsonSerialize(using = StudentHealthSurveySerializer.class)
public class StudentHealthSurvey extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String STUDENT_PROPERTY               = "student"           ;
	public static final String ANSWER_TIME_PROPERTY           = "answerTime"        ;
	public static final String SURVEY_STATUS_PROPERTY         = "surveyStatus"      ;
	public static final String SCHOOL_CLASS_PROPERTY          = "schoolClass"       ;
	public static final String CLASS_DAILY_HEALTH_SURVEY_PROPERTY = "classDailyHealthSurvey";
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String CQ_PROPERTY                    = "cq"                ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_DAILY_ANSWER_LIST                = "studentDailyAnswerList";

	public static final String INTERNAL_TYPE="StudentHealthSurvey";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getId();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		Student             	mStudent            ;
	protected		DateTime            	mAnswerTime         ;
	protected		SurveyStatus        	mSurveyStatus       ;
	protected		SchoolClass         	mSchoolClass        ;
	protected		ClassDailyHealthSurvey	mClassDailyHealthSurvey;
	protected		DateTime            	mCreateTime         ;
	protected		DateTime            	mLastUpdateTime     ;
	protected		ChangeRequest       	mCq                 ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<StudentDailyAnswer>	mStudentDailyAnswerList;
	
		
	public 	StudentHealthSurvey(){
		// lazy load for all the properties
	}
	public 	static StudentHealthSurvey withId(String id){
		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurvey.setId(id);
		studentHealthSurvey.setVersion(Integer.MAX_VALUE);
		return studentHealthSurvey;
	}
	public 	static StudentHealthSurvey refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setStudent( null );
		setSurveyStatus( null );
		setSchoolClass( null );
		setClassDailyHealthSurvey( null );
		setCq( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(ANSWER_TIME_PROPERTY.equals(property)){
			changeAnswerTimeProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			changeLastUpdateTimeProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeAnswerTimeProperty(String newValueExpr){
		DateTime oldValue = getAnswerTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateAnswerTime(newValue);
		this.onChangeProperty(ANSWER_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeCreateTimeProperty(String newValueExpr){
		DateTime oldValue = getCreateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCreateTime(newValue);
		this.onChangeProperty(CREATE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeLastUpdateTimeProperty(String newValueExpr){
		DateTime oldValue = getLastUpdateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLastUpdateTime(newValue);
		this.onChangeProperty(LAST_UPDATE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(STUDENT_PROPERTY.equals(property)){
			return getStudent();
		}
		if(ANSWER_TIME_PROPERTY.equals(property)){
			return getAnswerTime();
		}
		if(SURVEY_STATUS_PROPERTY.equals(property)){
			return getSurveyStatus();
		}
		if(SCHOOL_CLASS_PROPERTY.equals(property)){
			return getSchoolClass();
		}
		if(CLASS_DAILY_HEALTH_SURVEY_PROPERTY.equals(property)){
			return getClassDailyHealthSurvey();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			return getLastUpdateTime();
		}
		if(CQ_PROPERTY.equals(property)){
			return getCq();
		}
		if(STUDENT_DAILY_ANSWER_LIST.equals(property)){
			List<BaseEntity> list = getStudentDailyAnswerList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}

    		//other property not include here
		return super.propertyOf(property);
	}
    
    


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public StudentHealthSurvey updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setStudent(Student student){
		this.mStudent = student;;
	}
	public Student getStudent(){
		return this.mStudent;
	}
	public StudentHealthSurvey updateStudent(Student student){
		this.mStudent = student;;
		this.changed = true;
		return this;
	}
	public void mergeStudent(Student student){
		if(student != null) { setStudent(student);}
	}
	
	
	public void clearStudent(){
		setStudent ( null );
		this.changed = true;
	}
	
	public void setAnswerTime(DateTime answerTime){
		this.mAnswerTime = answerTime;;
	}
	public DateTime getAnswerTime(){
		return this.mAnswerTime;
	}
	public StudentHealthSurvey updateAnswerTime(DateTime answerTime){
		this.mAnswerTime = answerTime;;
		this.changed = true;
		return this;
	}
	public void mergeAnswerTime(DateTime answerTime){
		setAnswerTime(answerTime);
	}
	
	
	public void setSurveyStatus(SurveyStatus surveyStatus){
		this.mSurveyStatus = surveyStatus;;
	}
	public SurveyStatus getSurveyStatus(){
		return this.mSurveyStatus;
	}
	public StudentHealthSurvey updateSurveyStatus(SurveyStatus surveyStatus){
		this.mSurveyStatus = surveyStatus;;
		this.changed = true;
		return this;
	}
	public void mergeSurveyStatus(SurveyStatus surveyStatus){
		if(surveyStatus != null) { setSurveyStatus(surveyStatus);}
	}
	
	
	public void clearSurveyStatus(){
		setSurveyStatus ( null );
		this.changed = true;
	}
	
	public void setSchoolClass(SchoolClass schoolClass){
		this.mSchoolClass = schoolClass;;
	}
	public SchoolClass getSchoolClass(){
		return this.mSchoolClass;
	}
	public StudentHealthSurvey updateSchoolClass(SchoolClass schoolClass){
		this.mSchoolClass = schoolClass;;
		this.changed = true;
		return this;
	}
	public void mergeSchoolClass(SchoolClass schoolClass){
		if(schoolClass != null) { setSchoolClass(schoolClass);}
	}
	
	
	public void clearSchoolClass(){
		setSchoolClass ( null );
		this.changed = true;
	}
	
	public void setClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		this.mClassDailyHealthSurvey = classDailyHealthSurvey;;
	}
	public ClassDailyHealthSurvey getClassDailyHealthSurvey(){
		return this.mClassDailyHealthSurvey;
	}
	public StudentHealthSurvey updateClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		this.mClassDailyHealthSurvey = classDailyHealthSurvey;;
		this.changed = true;
		return this;
	}
	public void mergeClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		if(classDailyHealthSurvey != null) { setClassDailyHealthSurvey(classDailyHealthSurvey);}
	}
	
	
	public void clearClassDailyHealthSurvey(){
		setClassDailyHealthSurvey ( null );
		this.changed = true;
	}
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public StudentHealthSurvey updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
	}
	public DateTime getLastUpdateTime(){
		return this.mLastUpdateTime;
	}
	public StudentHealthSurvey updateLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
		this.changed = true;
		return this;
	}
	public void mergeLastUpdateTime(DateTime lastUpdateTime){
		setLastUpdateTime(lastUpdateTime);
	}
	
	
	public void setCq(ChangeRequest cq){
		this.mCq = cq;;
	}
	public ChangeRequest getCq(){
		return this.mCq;
	}
	public StudentHealthSurvey updateCq(ChangeRequest cq){
		this.mCq = cq;;
		this.changed = true;
		return this;
	}
	public void mergeCq(ChangeRequest cq){
		if(cq != null) { setCq(cq);}
	}
	
	
	public void clearCq(){
		setCq ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public StudentHealthSurvey updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<StudentDailyAnswer> getStudentDailyAnswerList(){
		if(this.mStudentDailyAnswerList == null){
			this.mStudentDailyAnswerList = new SmartList<StudentDailyAnswer>();
			this.mStudentDailyAnswerList.setListInternalName (STUDENT_DAILY_ANSWER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentDailyAnswerList;	
	}
	public  void setStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		for( StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList){
			studentDailyAnswer.setStudentHealthSurvey(this);
		}

		this.mStudentDailyAnswerList = studentDailyAnswerList;
		this.mStudentDailyAnswerList.setListInternalName (STUDENT_DAILY_ANSWER_LIST );
		
	}
	
	public  void addStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		studentDailyAnswer.setStudentHealthSurvey(this);
		getStudentDailyAnswerList().add(studentDailyAnswer);
	}
	public  void addStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		for( StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList){
			studentDailyAnswer.setStudentHealthSurvey(this);
		}
		getStudentDailyAnswerList().addAll(studentDailyAnswerList);
	}
	public  void mergeStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		if(studentDailyAnswerList==null){
			return;
		}
		if(studentDailyAnswerList.isEmpty()){
			return;
		}
		addStudentDailyAnswerList( studentDailyAnswerList );
		
	}
	public  StudentDailyAnswer removeStudentDailyAnswer(StudentDailyAnswer studentDailyAnswerIndex){
		
		int index = getStudentDailyAnswerList().indexOf(studentDailyAnswerIndex);
        if(index < 0){
        	String message = "StudentDailyAnswer("+studentDailyAnswerIndex.getId()+") with version='"+studentDailyAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        StudentDailyAnswer studentDailyAnswer = getStudentDailyAnswerList().get(index);        
        // studentDailyAnswer.clearStudentHealthSurvey(); //disconnect with StudentHealthSurvey
        studentDailyAnswer.clearFromAll(); //disconnect with StudentHealthSurvey
		
		boolean result = getStudentDailyAnswerList().planToRemove(studentDailyAnswer);
        if(!result){
        	String message = "StudentDailyAnswer("+studentDailyAnswerIndex.getId()+") with version='"+studentDailyAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return studentDailyAnswer;
        
	
	}
	//断舍离
	public  void breakWithStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		
		if(studentDailyAnswer == null){
			return;
		}
		studentDailyAnswer.setStudentHealthSurvey(null);
		//getStudentDailyAnswerList().remove();
	
	}
	
	public  boolean hasStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
	
		return getStudentDailyAnswerList().contains(studentDailyAnswer);
  
	}
	
	public void copyStudentDailyAnswerFrom(StudentDailyAnswer studentDailyAnswer) {

		StudentDailyAnswer studentDailyAnswerInList = findTheStudentDailyAnswer(studentDailyAnswer);
		StudentDailyAnswer newStudentDailyAnswer = new StudentDailyAnswer();
		studentDailyAnswerInList.copyTo(newStudentDailyAnswer);
		newStudentDailyAnswer.setVersion(0);//will trigger copy
		getStudentDailyAnswerList().add(newStudentDailyAnswer);
		addItemToFlexiableObject(COPIED_CHILD, newStudentDailyAnswer);
	}
	
	public  StudentDailyAnswer findTheStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		
		int index =  getStudentDailyAnswerList().indexOf(studentDailyAnswer);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "StudentDailyAnswer("+studentDailyAnswer.getId()+") with version='"+studentDailyAnswer.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentDailyAnswerList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentDailyAnswerList(){
		getStudentDailyAnswerList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getStudent(), internalType);
		addToEntityList(this, entityList, getSurveyStatus(), internalType);
		addToEntityList(this, entityList, getSchoolClass(), internalType);
		addToEntityList(this, entityList, getClassDailyHealthSurvey(), internalType);
		addToEntityList(this, entityList, getCq(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentDailyAnswerList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentDailyAnswerList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, STUDENT_PROPERTY, getStudent());
		appendKeyValuePair(result, ANSWER_TIME_PROPERTY, getAnswerTime());
		appendKeyValuePair(result, SURVEY_STATUS_PROPERTY, getSurveyStatus());
		appendKeyValuePair(result, SCHOOL_CLASS_PROPERTY, getSchoolClass());
		appendKeyValuePair(result, CLASS_DAILY_HEALTH_SURVEY_PROPERTY, getClassDailyHealthSurvey());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, CQ_PROPERTY, getCq());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerList());
		if(!getStudentDailyAnswerList().isEmpty()){
			appendKeyValuePair(result, "studentDailyAnswerCount", getStudentDailyAnswerList().getTotalCount());
			appendKeyValuePair(result, "studentDailyAnswerCurrentPageNumber", getStudentDailyAnswerList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentHealthSurvey){
		
		
			StudentHealthSurvey dest =(StudentHealthSurvey)baseDest;
		
			dest.setId(getId());
			dest.setStudent(getStudent());
			dest.setAnswerTime(getAnswerTime());
			dest.setSurveyStatus(getSurveyStatus());
			dest.setSchoolClass(getSchoolClass());
			dest.setClassDailyHealthSurvey(getClassDailyHealthSurvey());
			dest.setCreateTime(getCreateTime());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setCq(getCq());
			dest.setVersion(getVersion());
			dest.setStudentDailyAnswerList(getStudentDailyAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentHealthSurvey){
		
			
			StudentHealthSurvey dest =(StudentHealthSurvey)baseDest;
		
			dest.mergeId(getId());
			dest.mergeStudent(getStudent());
			dest.mergeAnswerTime(getAnswerTime());
			dest.mergeSurveyStatus(getSurveyStatus());
			dest.mergeSchoolClass(getSchoolClass());
			dest.mergeClassDailyHealthSurvey(getClassDailyHealthSurvey());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergeCq(getCq());
			dest.mergeVersion(getVersion());
			dest.mergeStudentDailyAnswerList(getStudentDailyAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentHealthSurvey){
		
			
			StudentHealthSurvey dest =(StudentHealthSurvey)baseDest;
		
			dest.mergeId(getId());
			dest.mergeAnswerTime(getAnswerTime());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getStudent(), getAnswerTime(), getSurveyStatus(), getSchoolClass(), getClassDailyHealthSurvey(), getCreateTime(), getLastUpdateTime(), getCq(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("StudentHealthSurvey{");
		stringBuilder.append("\tid='"+getId()+"';");
		if(getStudent() != null ){
 			stringBuilder.append("\tstudent='Student("+getStudent().getId()+")';");
 		}
		stringBuilder.append("\tanswerTime='"+getAnswerTime()+"';");
		if(getSurveyStatus() != null ){
 			stringBuilder.append("\tsurveyStatus='SurveyStatus("+getSurveyStatus().getId()+")';");
 		}
		if(getSchoolClass() != null ){
 			stringBuilder.append("\tschoolClass='SchoolClass("+getSchoolClass().getId()+")';");
 		}
		if(getClassDailyHealthSurvey() != null ){
 			stringBuilder.append("\tclassDailyHealthSurvey='ClassDailyHealthSurvey("+getClassDailyHealthSurvey().getId()+")';");
 		}
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tlastUpdateTime='"+getLastUpdateTime()+"';");
		if(getCq() != null ){
 			stringBuilder.append("\tcq='ChangeRequest("+getCq().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

