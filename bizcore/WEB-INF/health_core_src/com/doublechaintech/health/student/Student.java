
package com.doublechaintech.health.student;

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
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.guardian.Guardian;

@JsonSerialize(using = StudentSerializer.class)
public class Student extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String GENDER_PROPERTY                = "gender"            ;
	public static final String GUARDIAN_PROPERTY              = "guardian"          ;
	public static final String SCHOOL_CLASS_PROPERTY          = "schoolClass"       ;
	public static final String STUDENT_ID_PROPERTY            = "studentId"         ;
	public static final String CQ_PROPERTY                    = "cq"                ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_HEALTH_SURVEY_LIST               = "studentHealthSurveyList";

	public static final String INTERNAL_TYPE="Student";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		String              	mGender             ;
	protected		Guardian            	mGuardian           ;
	protected		SchoolClass         	mSchoolClass        ;
	protected		String              	mStudentId          ;
	protected		ChangeRequest       	mCq                 ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<StudentHealthSurvey>	mStudentHealthSurveyList;
	
		
	public 	Student(){
		// lazy load for all the properties
	}
	public 	static Student withId(String id){
		Student student = new Student();
		student.setId(id);
		student.setVersion(Integer.MAX_VALUE);
		return student;
	}
	public 	static Student refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setGuardian( null );
		setSchoolClass( null );
		setCq( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(GENDER_PROPERTY.equals(property)){
			changeGenderProperty(newValueExpr);
		}
		if(STUDENT_ID_PROPERTY.equals(property)){
			changeStudentIdProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeGenderProperty(String newValueExpr){
		String oldValue = getGender();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateGender(newValue);
		this.onChangeProperty(GENDER_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeStudentIdProperty(String newValueExpr){
		String oldValue = getStudentId();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStudentId(newValue);
		this.onChangeProperty(STUDENT_ID_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(GENDER_PROPERTY.equals(property)){
			return getGender();
		}
		if(GUARDIAN_PROPERTY.equals(property)){
			return getGuardian();
		}
		if(SCHOOL_CLASS_PROPERTY.equals(property)){
			return getSchoolClass();
		}
		if(STUDENT_ID_PROPERTY.equals(property)){
			return getStudentId();
		}
		if(CQ_PROPERTY.equals(property)){
			return getCq();
		}
		if(STUDENT_HEALTH_SURVEY_LIST.equals(property)){
			List<BaseEntity> list = getStudentHealthSurveyList().stream().map(item->item).collect(Collectors.toList());
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
	public Student updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public Student updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setGender(String gender){
		this.mGender = trimString(gender);;
	}
	public String getGender(){
		return this.mGender;
	}
	public Student updateGender(String gender){
		this.mGender = trimString(gender);;
		this.changed = true;
		return this;
	}
	public void mergeGender(String gender){
		if(gender != null) { setGender(gender);}
	}
	
	
	public void setGuardian(Guardian guardian){
		this.mGuardian = guardian;;
	}
	public Guardian getGuardian(){
		return this.mGuardian;
	}
	public Student updateGuardian(Guardian guardian){
		this.mGuardian = guardian;;
		this.changed = true;
		return this;
	}
	public void mergeGuardian(Guardian guardian){
		if(guardian != null) { setGuardian(guardian);}
	}
	
	
	public void clearGuardian(){
		setGuardian ( null );
		this.changed = true;
	}
	
	public void setSchoolClass(SchoolClass schoolClass){
		this.mSchoolClass = schoolClass;;
	}
	public SchoolClass getSchoolClass(){
		return this.mSchoolClass;
	}
	public Student updateSchoolClass(SchoolClass schoolClass){
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
	
	public void setStudentId(String studentId){
		this.mStudentId = trimString(studentId);;
	}
	public String getStudentId(){
		return this.mStudentId;
	}
	public Student updateStudentId(String studentId){
		this.mStudentId = trimString(studentId);;
		this.changed = true;
		return this;
	}
	public void mergeStudentId(String studentId){
		if(studentId != null) { setStudentId(studentId);}
	}
	
	
	public void clearStudentId(){
		setStudentId ( null );
		this.changed = true;
	}
	
	public void setCq(ChangeRequest cq){
		this.mCq = cq;;
	}
	public ChangeRequest getCq(){
		return this.mCq;
	}
	public Student updateCq(ChangeRequest cq){
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
	public Student updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<StudentHealthSurvey> getStudentHealthSurveyList(){
		if(this.mStudentHealthSurveyList == null){
			this.mStudentHealthSurveyList = new SmartList<StudentHealthSurvey>();
			this.mStudentHealthSurveyList.setListInternalName (STUDENT_HEALTH_SURVEY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentHealthSurveyList;	
	}
	public  void setStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		for( StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			studentHealthSurvey.setStudent(this);
		}

		this.mStudentHealthSurveyList = studentHealthSurveyList;
		this.mStudentHealthSurveyList.setListInternalName (STUDENT_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		studentHealthSurvey.setStudent(this);
		getStudentHealthSurveyList().add(studentHealthSurvey);
	}
	public  void addStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		for( StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			studentHealthSurvey.setStudent(this);
		}
		getStudentHealthSurveyList().addAll(studentHealthSurveyList);
	}
	public  void mergeStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		if(studentHealthSurveyList==null){
			return;
		}
		if(studentHealthSurveyList.isEmpty()){
			return;
		}
		addStudentHealthSurveyList( studentHealthSurveyList );
		
	}
	public  StudentHealthSurvey removeStudentHealthSurvey(StudentHealthSurvey studentHealthSurveyIndex){
		
		int index = getStudentHealthSurveyList().indexOf(studentHealthSurveyIndex);
        if(index < 0){
        	String message = "StudentHealthSurvey("+studentHealthSurveyIndex.getId()+") with version='"+studentHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        StudentHealthSurvey studentHealthSurvey = getStudentHealthSurveyList().get(index);        
        // studentHealthSurvey.clearStudent(); //disconnect with Student
        studentHealthSurvey.clearFromAll(); //disconnect with Student
		
		boolean result = getStudentHealthSurveyList().planToRemove(studentHealthSurvey);
        if(!result){
        	String message = "StudentHealthSurvey("+studentHealthSurveyIndex.getId()+") with version='"+studentHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return studentHealthSurvey;
        
	
	}
	//断舍离
	public  void breakWithStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		
		if(studentHealthSurvey == null){
			return;
		}
		studentHealthSurvey.setStudent(null);
		//getStudentHealthSurveyList().remove();
	
	}
	
	public  boolean hasStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
	
		return getStudentHealthSurveyList().contains(studentHealthSurvey);
  
	}
	
	public void copyStudentHealthSurveyFrom(StudentHealthSurvey studentHealthSurvey) {

		StudentHealthSurvey studentHealthSurveyInList = findTheStudentHealthSurvey(studentHealthSurvey);
		StudentHealthSurvey newStudentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurveyInList.copyTo(newStudentHealthSurvey);
		newStudentHealthSurvey.setVersion(0);//will trigger copy
		getStudentHealthSurveyList().add(newStudentHealthSurvey);
		addItemToFlexiableObject(COPIED_CHILD, newStudentHealthSurvey);
	}
	
	public  StudentHealthSurvey findTheStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		
		int index =  getStudentHealthSurveyList().indexOf(studentHealthSurvey);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "StudentHealthSurvey("+studentHealthSurvey.getId()+") with version='"+studentHealthSurvey.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentHealthSurveyList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentHealthSurveyList(){
		getStudentHealthSurveyList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getGuardian(), internalType);
		addToEntityList(this, entityList, getSchoolClass(), internalType);
		addToEntityList(this, entityList, getCq(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentHealthSurveyList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentHealthSurveyList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, GENDER_PROPERTY, getGender());
		appendKeyValuePair(result, GUARDIAN_PROPERTY, getGuardian());
		appendKeyValuePair(result, SCHOOL_CLASS_PROPERTY, getSchoolClass());
		appendKeyValuePair(result, STUDENT_ID_PROPERTY, getStudentId());
		appendKeyValuePair(result, CQ_PROPERTY, getCq());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyList());
		if(!getStudentHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "studentHealthSurveyCount", getStudentHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "studentHealthSurveyCurrentPageNumber", getStudentHealthSurveyList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Student){
		
		
			Student dest =(Student)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setGender(getGender());
			dest.setGuardian(getGuardian());
			dest.setSchoolClass(getSchoolClass());
			dest.setStudentId(getStudentId());
			dest.setCq(getCq());
			dest.setVersion(getVersion());
			dest.setStudentHealthSurveyList(getStudentHealthSurveyList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Student){
		
			
			Student dest =(Student)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeGender(getGender());
			dest.mergeGuardian(getGuardian());
			dest.mergeSchoolClass(getSchoolClass());
			dest.mergeStudentId(getStudentId());
			dest.mergeCq(getCq());
			dest.mergeVersion(getVersion());
			dest.mergeStudentHealthSurveyList(getStudentHealthSurveyList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Student){
		
			
			Student dest =(Student)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeGender(getGender());
			dest.mergeStudentId(getStudentId());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getGender(), getGuardian(), getSchoolClass(), getStudentId(), getCq(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Student{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tgender='"+getGender()+"';");
		if(getGuardian() != null ){
 			stringBuilder.append("\tguardian='Guardian("+getGuardian().getId()+")';");
 		}
		if(getSchoolClass() != null ){
 			stringBuilder.append("\tschoolClass='SchoolClass("+getSchoolClass().getId()+")';");
 		}
		stringBuilder.append("\tstudentId='"+getStudentId()+"';");
		if(getCq() != null ){
 			stringBuilder.append("\tcq='ChangeRequest("+getCq().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

