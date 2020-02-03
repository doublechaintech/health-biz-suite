
package com.doublechaintech.health.healthsurveyreport;

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
import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;

@JsonSerialize(using = HealthSurveyReportSerializer.class)
public class HealthSurveyReport extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String SURVEY_NAME_PROPERTY           = "surveyName"        ;
	public static final String SURVEY_TIME_PROPERTY           = "surveyTime"        ;
	public static final String TEACHER_NAME_PROPERTY          = "teacherName"       ;
	public static final String SCHOOL_PROPERTY                = "school"            ;
	public static final String SCHOOL_CLASS_PROPERTY          = "schoolClass"       ;
	public static final String STUDENT_NAME_PROPERTY          = "studentName"       ;
	public static final String STUDENT_NUMBER_PROPERTY        = "studentNumber"     ;
	public static final String GUARDIAN_NAME_PROPERTY         = "guardianName"      ;
	public static final String GUARDIAN_MOBILE_PROPERTY       = "guardianMobile"    ;
	public static final String STUDENT_PROPERTY               = "student"           ;
	public static final String TEACHER_PROPERTY               = "teacher"           ;
	public static final String SURVEY_PROPERTY                = "survey"            ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_ANSWER_LIST                      = "studentAnswerList" ;

	public static final String INTERNAL_TYPE="HealthSurveyReport";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getSurveyName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mSurveyName         ;
	protected		DateTime            	mSurveyTime         ;
	protected		String              	mTeacherName        ;
	protected		String              	mSchool             ;
	protected		String              	mSchoolClass        ;
	protected		String              	mStudentName        ;
	protected		String              	mStudentNumber      ;
	protected		String              	mGuardianName       ;
	protected		String              	mGuardianMobile     ;
	protected		Student             	mStudent            ;
	protected		Teacher             	mTeacher            ;
	protected		ClassDailyHealthSurvey	mSurvey             ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<StudentAnswer>	mStudentAnswerList  ;
	
		
	public 	HealthSurveyReport(){
		// lazy load for all the properties
	}
	public 	static HealthSurveyReport withId(String id){
		HealthSurveyReport healthSurveyReport = new HealthSurveyReport();
		healthSurveyReport.setId(id);
		healthSurveyReport.setVersion(Integer.MAX_VALUE);
		return healthSurveyReport;
	}
	public 	static HealthSurveyReport refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setStudent( null );
		setTeacher( null );
		setSurvey( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(SURVEY_NAME_PROPERTY.equals(property)){
			changeSurveyNameProperty(newValueExpr);
		}
		if(SURVEY_TIME_PROPERTY.equals(property)){
			changeSurveyTimeProperty(newValueExpr);
		}
		if(TEACHER_NAME_PROPERTY.equals(property)){
			changeTeacherNameProperty(newValueExpr);
		}
		if(SCHOOL_PROPERTY.equals(property)){
			changeSchoolProperty(newValueExpr);
		}
		if(SCHOOL_CLASS_PROPERTY.equals(property)){
			changeSchoolClassProperty(newValueExpr);
		}
		if(STUDENT_NAME_PROPERTY.equals(property)){
			changeStudentNameProperty(newValueExpr);
		}
		if(STUDENT_NUMBER_PROPERTY.equals(property)){
			changeStudentNumberProperty(newValueExpr);
		}
		if(GUARDIAN_NAME_PROPERTY.equals(property)){
			changeGuardianNameProperty(newValueExpr);
		}
		if(GUARDIAN_MOBILE_PROPERTY.equals(property)){
			changeGuardianMobileProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeSurveyNameProperty(String newValueExpr){
		String oldValue = getSurveyName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSurveyName(newValue);
		this.onChangeProperty(SURVEY_NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeSurveyTimeProperty(String newValueExpr){
		DateTime oldValue = getSurveyTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSurveyTime(newValue);
		this.onChangeProperty(SURVEY_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeTeacherNameProperty(String newValueExpr){
		String oldValue = getTeacherName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateTeacherName(newValue);
		this.onChangeProperty(TEACHER_NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeSchoolProperty(String newValueExpr){
		String oldValue = getSchool();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSchool(newValue);
		this.onChangeProperty(SCHOOL_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeSchoolClassProperty(String newValueExpr){
		String oldValue = getSchoolClass();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSchoolClass(newValue);
		this.onChangeProperty(SCHOOL_CLASS_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeStudentNameProperty(String newValueExpr){
		String oldValue = getStudentName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStudentName(newValue);
		this.onChangeProperty(STUDENT_NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeStudentNumberProperty(String newValueExpr){
		String oldValue = getStudentNumber();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStudentNumber(newValue);
		this.onChangeProperty(STUDENT_NUMBER_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeGuardianNameProperty(String newValueExpr){
		String oldValue = getGuardianName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateGuardianName(newValue);
		this.onChangeProperty(GUARDIAN_NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeGuardianMobileProperty(String newValueExpr){
		String oldValue = getGuardianMobile();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateGuardianMobile(newValue);
		this.onChangeProperty(GUARDIAN_MOBILE_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(SURVEY_NAME_PROPERTY.equals(property)){
			return getSurveyName();
		}
		if(SURVEY_TIME_PROPERTY.equals(property)){
			return getSurveyTime();
		}
		if(TEACHER_NAME_PROPERTY.equals(property)){
			return getTeacherName();
		}
		if(SCHOOL_PROPERTY.equals(property)){
			return getSchool();
		}
		if(SCHOOL_CLASS_PROPERTY.equals(property)){
			return getSchoolClass();
		}
		if(STUDENT_NAME_PROPERTY.equals(property)){
			return getStudentName();
		}
		if(STUDENT_NUMBER_PROPERTY.equals(property)){
			return getStudentNumber();
		}
		if(GUARDIAN_NAME_PROPERTY.equals(property)){
			return getGuardianName();
		}
		if(GUARDIAN_MOBILE_PROPERTY.equals(property)){
			return getGuardianMobile();
		}
		if(STUDENT_PROPERTY.equals(property)){
			return getStudent();
		}
		if(TEACHER_PROPERTY.equals(property)){
			return getTeacher();
		}
		if(SURVEY_PROPERTY.equals(property)){
			return getSurvey();
		}
		if(STUDENT_ANSWER_LIST.equals(property)){
			List<BaseEntity> list = getStudentAnswerList().stream().map(item->item).collect(Collectors.toList());
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
	public HealthSurveyReport updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setSurveyName(String surveyName){
		this.mSurveyName = trimString(surveyName);;
	}
	public String getSurveyName(){
		return this.mSurveyName;
	}
	public HealthSurveyReport updateSurveyName(String surveyName){
		this.mSurveyName = trimString(surveyName);;
		this.changed = true;
		return this;
	}
	public void mergeSurveyName(String surveyName){
		if(surveyName != null) { setSurveyName(surveyName);}
	}
	
	
	public void setSurveyTime(DateTime surveyTime){
		this.mSurveyTime = surveyTime;;
	}
	public DateTime getSurveyTime(){
		return this.mSurveyTime;
	}
	public HealthSurveyReport updateSurveyTime(DateTime surveyTime){
		this.mSurveyTime = surveyTime;;
		this.changed = true;
		return this;
	}
	public void mergeSurveyTime(DateTime surveyTime){
		setSurveyTime(surveyTime);
	}
	
	
	public void setTeacherName(String teacherName){
		this.mTeacherName = trimString(teacherName);;
	}
	public String getTeacherName(){
		return this.mTeacherName;
	}
	public HealthSurveyReport updateTeacherName(String teacherName){
		this.mTeacherName = trimString(teacherName);;
		this.changed = true;
		return this;
	}
	public void mergeTeacherName(String teacherName){
		if(teacherName != null) { setTeacherName(teacherName);}
	}
	
	
	public void setSchool(String school){
		this.mSchool = trimString(school);;
	}
	public String getSchool(){
		return this.mSchool;
	}
	public HealthSurveyReport updateSchool(String school){
		this.mSchool = trimString(school);;
		this.changed = true;
		return this;
	}
	public void mergeSchool(String school){
		if(school != null) { setSchool(school);}
	}
	
	
	public void setSchoolClass(String schoolClass){
		this.mSchoolClass = trimString(schoolClass);;
	}
	public String getSchoolClass(){
		return this.mSchoolClass;
	}
	public HealthSurveyReport updateSchoolClass(String schoolClass){
		this.mSchoolClass = trimString(schoolClass);;
		this.changed = true;
		return this;
	}
	public void mergeSchoolClass(String schoolClass){
		if(schoolClass != null) { setSchoolClass(schoolClass);}
	}
	
	
	public void setStudentName(String studentName){
		this.mStudentName = trimString(studentName);;
	}
	public String getStudentName(){
		return this.mStudentName;
	}
	public HealthSurveyReport updateStudentName(String studentName){
		this.mStudentName = trimString(studentName);;
		this.changed = true;
		return this;
	}
	public void mergeStudentName(String studentName){
		if(studentName != null) { setStudentName(studentName);}
	}
	
	
	public void setStudentNumber(String studentNumber){
		this.mStudentNumber = trimString(studentNumber);;
	}
	public String getStudentNumber(){
		return this.mStudentNumber;
	}
	public HealthSurveyReport updateStudentNumber(String studentNumber){
		this.mStudentNumber = trimString(studentNumber);;
		this.changed = true;
		return this;
	}
	public void mergeStudentNumber(String studentNumber){
		if(studentNumber != null) { setStudentNumber(studentNumber);}
	}
	
	
	public void setGuardianName(String guardianName){
		this.mGuardianName = trimString(guardianName);;
	}
	public String getGuardianName(){
		return this.mGuardianName;
	}
	public HealthSurveyReport updateGuardianName(String guardianName){
		this.mGuardianName = trimString(guardianName);;
		this.changed = true;
		return this;
	}
	public void mergeGuardianName(String guardianName){
		if(guardianName != null) { setGuardianName(guardianName);}
	}
	
	
	public void setGuardianMobile(String guardianMobile){
		this.mGuardianMobile = trimString(guardianMobile);;
	}
	public String getGuardianMobile(){
		return this.mGuardianMobile;
	}
	public HealthSurveyReport updateGuardianMobile(String guardianMobile){
		this.mGuardianMobile = trimString(guardianMobile);;
		this.changed = true;
		return this;
	}
	public void mergeGuardianMobile(String guardianMobile){
		if(guardianMobile != null) { setGuardianMobile(guardianMobile);}
	}
	
	
	
	public String getMaskedGuardianMobile(){
		String mobilePhoneNumber = getGuardianMobile();
		return maskChinaMobileNumber(mobilePhoneNumber);
	}
	
		
	public void setStudent(Student student){
		this.mStudent = student;;
	}
	public Student getStudent(){
		return this.mStudent;
	}
	public HealthSurveyReport updateStudent(Student student){
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
	
	public void setTeacher(Teacher teacher){
		this.mTeacher = teacher;;
	}
	public Teacher getTeacher(){
		return this.mTeacher;
	}
	public HealthSurveyReport updateTeacher(Teacher teacher){
		this.mTeacher = teacher;;
		this.changed = true;
		return this;
	}
	public void mergeTeacher(Teacher teacher){
		if(teacher != null) { setTeacher(teacher);}
	}
	
	
	public void clearTeacher(){
		setTeacher ( null );
		this.changed = true;
	}
	
	public void setSurvey(ClassDailyHealthSurvey survey){
		this.mSurvey = survey;;
	}
	public ClassDailyHealthSurvey getSurvey(){
		return this.mSurvey;
	}
	public HealthSurveyReport updateSurvey(ClassDailyHealthSurvey survey){
		this.mSurvey = survey;;
		this.changed = true;
		return this;
	}
	public void mergeSurvey(ClassDailyHealthSurvey survey){
		if(survey != null) { setSurvey(survey);}
	}
	
	
	public void clearSurvey(){
		setSurvey ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public HealthSurveyReport updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<StudentAnswer> getStudentAnswerList(){
		if(this.mStudentAnswerList == null){
			this.mStudentAnswerList = new SmartList<StudentAnswer>();
			this.mStudentAnswerList.setListInternalName (STUDENT_ANSWER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentAnswerList;	
	}
	public  void setStudentAnswerList(SmartList<StudentAnswer> studentAnswerList){
		for( StudentAnswer studentAnswer:studentAnswerList){
			studentAnswer.setHealthSurveyReport(this);
		}

		this.mStudentAnswerList = studentAnswerList;
		this.mStudentAnswerList.setListInternalName (STUDENT_ANSWER_LIST );
		
	}
	
	public  void addStudentAnswer(StudentAnswer studentAnswer){
		studentAnswer.setHealthSurveyReport(this);
		getStudentAnswerList().add(studentAnswer);
	}
	public  void addStudentAnswerList(SmartList<StudentAnswer> studentAnswerList){
		for( StudentAnswer studentAnswer:studentAnswerList){
			studentAnswer.setHealthSurveyReport(this);
		}
		getStudentAnswerList().addAll(studentAnswerList);
	}
	public  void mergeStudentAnswerList(SmartList<StudentAnswer> studentAnswerList){
		if(studentAnswerList==null){
			return;
		}
		if(studentAnswerList.isEmpty()){
			return;
		}
		addStudentAnswerList( studentAnswerList );
		
	}
	public  StudentAnswer removeStudentAnswer(StudentAnswer studentAnswerIndex){
		
		int index = getStudentAnswerList().indexOf(studentAnswerIndex);
        if(index < 0){
        	String message = "StudentAnswer("+studentAnswerIndex.getId()+") with version='"+studentAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        StudentAnswer studentAnswer = getStudentAnswerList().get(index);        
        // studentAnswer.clearHealthSurveyReport(); //disconnect with HealthSurveyReport
        studentAnswer.clearFromAll(); //disconnect with HealthSurveyReport
		
		boolean result = getStudentAnswerList().planToRemove(studentAnswer);
        if(!result){
        	String message = "StudentAnswer("+studentAnswerIndex.getId()+") with version='"+studentAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return studentAnswer;
        
	
	}
	//断舍离
	public  void breakWithStudentAnswer(StudentAnswer studentAnswer){
		
		if(studentAnswer == null){
			return;
		}
		studentAnswer.setHealthSurveyReport(null);
		//getStudentAnswerList().remove();
	
	}
	
	public  boolean hasStudentAnswer(StudentAnswer studentAnswer){
	
		return getStudentAnswerList().contains(studentAnswer);
  
	}
	
	public void copyStudentAnswerFrom(StudentAnswer studentAnswer) {

		StudentAnswer studentAnswerInList = findTheStudentAnswer(studentAnswer);
		StudentAnswer newStudentAnswer = new StudentAnswer();
		studentAnswerInList.copyTo(newStudentAnswer);
		newStudentAnswer.setVersion(0);//will trigger copy
		getStudentAnswerList().add(newStudentAnswer);
		addItemToFlexiableObject(COPIED_CHILD, newStudentAnswer);
	}
	
	public  StudentAnswer findTheStudentAnswer(StudentAnswer studentAnswer){
		
		int index =  getStudentAnswerList().indexOf(studentAnswer);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "StudentAnswer("+studentAnswer.getId()+") with version='"+studentAnswer.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentAnswerList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentAnswerList(){
		getStudentAnswerList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getStudent(), internalType);
		addToEntityList(this, entityList, getTeacher(), internalType);
		addToEntityList(this, entityList, getSurvey(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentAnswerList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentAnswerList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, SURVEY_NAME_PROPERTY, getSurveyName());
		appendKeyValuePair(result, SURVEY_TIME_PROPERTY, getSurveyTime());
		appendKeyValuePair(result, TEACHER_NAME_PROPERTY, getTeacherName());
		appendKeyValuePair(result, SCHOOL_PROPERTY, getSchool());
		appendKeyValuePair(result, SCHOOL_CLASS_PROPERTY, getSchoolClass());
		appendKeyValuePair(result, STUDENT_NAME_PROPERTY, getStudentName());
		appendKeyValuePair(result, STUDENT_NUMBER_PROPERTY, getStudentNumber());
		appendKeyValuePair(result, GUARDIAN_NAME_PROPERTY, getGuardianName());
		appendKeyValuePair(result, GUARDIAN_MOBILE_PROPERTY, getMaskedGuardianMobile());
		appendKeyValuePair(result, STUDENT_PROPERTY, getStudent());
		appendKeyValuePair(result, TEACHER_PROPERTY, getTeacher());
		appendKeyValuePair(result, SURVEY_PROPERTY, getSurvey());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_ANSWER_LIST, getStudentAnswerList());
		if(!getStudentAnswerList().isEmpty()){
			appendKeyValuePair(result, "studentAnswerCount", getStudentAnswerList().getTotalCount());
			appendKeyValuePair(result, "studentAnswerCurrentPageNumber", getStudentAnswerList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof HealthSurveyReport){
		
		
			HealthSurveyReport dest =(HealthSurveyReport)baseDest;
		
			dest.setId(getId());
			dest.setSurveyName(getSurveyName());
			dest.setSurveyTime(getSurveyTime());
			dest.setTeacherName(getTeacherName());
			dest.setSchool(getSchool());
			dest.setSchoolClass(getSchoolClass());
			dest.setStudentName(getStudentName());
			dest.setStudentNumber(getStudentNumber());
			dest.setGuardianName(getGuardianName());
			dest.setGuardianMobile(getGuardianMobile());
			dest.setStudent(getStudent());
			dest.setTeacher(getTeacher());
			dest.setSurvey(getSurvey());
			dest.setVersion(getVersion());
			dest.setStudentAnswerList(getStudentAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof HealthSurveyReport){
		
			
			HealthSurveyReport dest =(HealthSurveyReport)baseDest;
		
			dest.mergeId(getId());
			dest.mergeSurveyName(getSurveyName());
			dest.mergeSurveyTime(getSurveyTime());
			dest.mergeTeacherName(getTeacherName());
			dest.mergeSchool(getSchool());
			dest.mergeSchoolClass(getSchoolClass());
			dest.mergeStudentName(getStudentName());
			dest.mergeStudentNumber(getStudentNumber());
			dest.mergeGuardianName(getGuardianName());
			dest.mergeGuardianMobile(getGuardianMobile());
			dest.mergeStudent(getStudent());
			dest.mergeTeacher(getTeacher());
			dest.mergeSurvey(getSurvey());
			dest.mergeVersion(getVersion());
			dest.mergeStudentAnswerList(getStudentAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof HealthSurveyReport){
		
			
			HealthSurveyReport dest =(HealthSurveyReport)baseDest;
		
			dest.mergeId(getId());
			dest.mergeSurveyName(getSurveyName());
			dest.mergeSurveyTime(getSurveyTime());
			dest.mergeTeacherName(getTeacherName());
			dest.mergeSchool(getSchool());
			dest.mergeSchoolClass(getSchoolClass());
			dest.mergeStudentName(getStudentName());
			dest.mergeStudentNumber(getStudentNumber());
			dest.mergeGuardianName(getGuardianName());
			dest.mergeGuardianMobile(getGuardianMobile());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getSurveyName(), getSurveyTime(), getTeacherName(), getSchool(), getSchoolClass(), getStudentName(), getStudentNumber(), getGuardianName(), getGuardianMobile(), getStudent(), getTeacher(), getSurvey(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("HealthSurveyReport{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tsurveyName='"+getSurveyName()+"';");
		stringBuilder.append("\tsurveyTime='"+getSurveyTime()+"';");
		stringBuilder.append("\tteacherName='"+getTeacherName()+"';");
		stringBuilder.append("\tschool='"+getSchool()+"';");
		stringBuilder.append("\tschoolClass='"+getSchoolClass()+"';");
		stringBuilder.append("\tstudentName='"+getStudentName()+"';");
		stringBuilder.append("\tstudentNumber='"+getStudentNumber()+"';");
		stringBuilder.append("\tguardianName='"+getGuardianName()+"';");
		stringBuilder.append("\tguardianMobile='"+getGuardianMobile()+"';");
		if(getStudent() != null ){
 			stringBuilder.append("\tstudent='Student("+getStudent().getId()+")';");
 		}
		if(getTeacher() != null ){
 			stringBuilder.append("\tteacher='Teacher("+getTeacher().getId()+")';");
 		}
		if(getSurvey() != null ){
 			stringBuilder.append("\tsurvey='ClassDailyHealthSurvey("+getSurvey().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

