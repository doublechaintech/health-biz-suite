
package com.doublechaintech.health.teacher;

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
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

@JsonSerialize(using = TeacherSerializer.class)
public class Teacher extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String MOBILE_PROPERTY                = "mobile"            ;
	public static final String SCHOOL_PROPERTY                = "school"            ;
	public static final String SCHOOL_CLASS_PROPERTY          = "schoolClass"       ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String CQ_PROPERTY                    = "cq"                ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String CLASS_DAILY_HEALTH_SURVEY_LIST           = "classDailyHealthSurveyList";
	public static final String STUDENT_HEALTH_SURVEY_LIST               = "studentHealthSurveyList";

	public static final String INTERNAL_TYPE="Teacher";
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
	protected		String              	mMobile             ;
	protected		String              	mSchool             ;
	protected		String              	mSchoolClass        ;
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		ChangeRequest       	mCq                 ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<ClassDailyHealthSurvey>	mClassDailyHealthSurveyList;
	protected		SmartList<StudentHealthSurvey>	mStudentHealthSurveyList;
	
		
	public 	Teacher(){
		// lazy load for all the properties
	}
	public 	static Teacher withId(String id){
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setVersion(Integer.MAX_VALUE);
		return teacher;
	}
	public 	static Teacher refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );
		setCq( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(MOBILE_PROPERTY.equals(property)){
			changeMobileProperty(newValueExpr);
		}
		if(SCHOOL_PROPERTY.equals(property)){
			changeSchoolProperty(newValueExpr);
		}
		if(SCHOOL_CLASS_PROPERTY.equals(property)){
			changeSchoolClassProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
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
			
			
			
	protected void changeMobileProperty(String newValueExpr){
		String oldValue = getMobile();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateMobile(newValue);
		this.onChangeProperty(MOBILE_PROPERTY, oldValue, newValue);
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
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(MOBILE_PROPERTY.equals(property)){
			return getMobile();
		}
		if(SCHOOL_PROPERTY.equals(property)){
			return getSchool();
		}
		if(SCHOOL_CLASS_PROPERTY.equals(property)){
			return getSchoolClass();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(CQ_PROPERTY.equals(property)){
			return getCq();
		}
		if(CLASS_DAILY_HEALTH_SURVEY_LIST.equals(property)){
			List<BaseEntity> list = getClassDailyHealthSurveyList().stream().map(item->item).collect(Collectors.toList());
			return list;
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
	public Teacher updateId(String id){
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
	public Teacher updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setMobile(String mobile){
		this.mMobile = trimString(mobile);;
	}
	public String getMobile(){
		return this.mMobile;
	}
	public Teacher updateMobile(String mobile){
		this.mMobile = trimString(mobile);;
		this.changed = true;
		return this;
	}
	public void mergeMobile(String mobile){
		if(mobile != null) { setMobile(mobile);}
	}
	
	
	
	public String getMaskedMobile(){
		String mobilePhoneNumber = getMobile();
		return maskChinaMobileNumber(mobilePhoneNumber);
	}
	
		
	public void setSchool(String school){
		this.mSchool = trimString(school);;
	}
	public String getSchool(){
		return this.mSchool;
	}
	public Teacher updateSchool(String school){
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
	public Teacher updateSchoolClass(String schoolClass){
		this.mSchoolClass = trimString(schoolClass);;
		this.changed = true;
		return this;
	}
	public void mergeSchoolClass(String schoolClass){
		if(schoolClass != null) { setSchoolClass(schoolClass);}
	}
	
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public Teacher updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Teacher updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setCq(ChangeRequest cq){
		this.mCq = cq;;
	}
	public ChangeRequest getCq(){
		return this.mCq;
	}
	public Teacher updateCq(ChangeRequest cq){
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
	public Teacher updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<ClassDailyHealthSurvey> getClassDailyHealthSurveyList(){
		if(this.mClassDailyHealthSurveyList == null){
			this.mClassDailyHealthSurveyList = new SmartList<ClassDailyHealthSurvey>();
			this.mClassDailyHealthSurveyList.setListInternalName (CLASS_DAILY_HEALTH_SURVEY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mClassDailyHealthSurveyList;	
	}
	public  void setClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		for( ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList){
			classDailyHealthSurvey.setTeacher(this);
		}

		this.mClassDailyHealthSurveyList = classDailyHealthSurveyList;
		this.mClassDailyHealthSurveyList.setListInternalName (CLASS_DAILY_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		classDailyHealthSurvey.setTeacher(this);
		getClassDailyHealthSurveyList().add(classDailyHealthSurvey);
	}
	public  void addClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		for( ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList){
			classDailyHealthSurvey.setTeacher(this);
		}
		getClassDailyHealthSurveyList().addAll(classDailyHealthSurveyList);
	}
	public  void mergeClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		if(classDailyHealthSurveyList==null){
			return;
		}
		if(classDailyHealthSurveyList.isEmpty()){
			return;
		}
		addClassDailyHealthSurveyList( classDailyHealthSurveyList );
		
	}
	public  ClassDailyHealthSurvey removeClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurveyIndex){
		
		int index = getClassDailyHealthSurveyList().indexOf(classDailyHealthSurveyIndex);
        if(index < 0){
        	String message = "ClassDailyHealthSurvey("+classDailyHealthSurveyIndex.getId()+") with version='"+classDailyHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ClassDailyHealthSurvey classDailyHealthSurvey = getClassDailyHealthSurveyList().get(index);        
        // classDailyHealthSurvey.clearTeacher(); //disconnect with Teacher
        classDailyHealthSurvey.clearFromAll(); //disconnect with Teacher
		
		boolean result = getClassDailyHealthSurveyList().planToRemove(classDailyHealthSurvey);
        if(!result){
        	String message = "ClassDailyHealthSurvey("+classDailyHealthSurveyIndex.getId()+") with version='"+classDailyHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return classDailyHealthSurvey;
        
	
	}
	//断舍离
	public  void breakWithClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		
		if(classDailyHealthSurvey == null){
			return;
		}
		classDailyHealthSurvey.setTeacher(null);
		//getClassDailyHealthSurveyList().remove();
	
	}
	
	public  boolean hasClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
	
		return getClassDailyHealthSurveyList().contains(classDailyHealthSurvey);
  
	}
	
	public void copyClassDailyHealthSurveyFrom(ClassDailyHealthSurvey classDailyHealthSurvey) {

		ClassDailyHealthSurvey classDailyHealthSurveyInList = findTheClassDailyHealthSurvey(classDailyHealthSurvey);
		ClassDailyHealthSurvey newClassDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurveyInList.copyTo(newClassDailyHealthSurvey);
		newClassDailyHealthSurvey.setVersion(0);//will trigger copy
		getClassDailyHealthSurveyList().add(newClassDailyHealthSurvey);
		addItemToFlexiableObject(COPIED_CHILD, newClassDailyHealthSurvey);
	}
	
	public  ClassDailyHealthSurvey findTheClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		
		int index =  getClassDailyHealthSurveyList().indexOf(classDailyHealthSurvey);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ClassDailyHealthSurvey("+classDailyHealthSurvey.getId()+") with version='"+classDailyHealthSurvey.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getClassDailyHealthSurveyList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpClassDailyHealthSurveyList(){
		getClassDailyHealthSurveyList().clear();
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
			studentHealthSurvey.setTeacher(this);
		}

		this.mStudentHealthSurveyList = studentHealthSurveyList;
		this.mStudentHealthSurveyList.setListInternalName (STUDENT_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		studentHealthSurvey.setTeacher(this);
		getStudentHealthSurveyList().add(studentHealthSurvey);
	}
	public  void addStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		for( StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			studentHealthSurvey.setTeacher(this);
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
        // studentHealthSurvey.clearTeacher(); //disconnect with Teacher
        studentHealthSurvey.clearFromAll(); //disconnect with Teacher
		
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
		studentHealthSurvey.setTeacher(null);
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

		addToEntityList(this, entityList, getPlatform(), internalType);
		addToEntityList(this, entityList, getCq(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getClassDailyHealthSurveyList(), internalType);
		collectFromList(this, entityList, getStudentHealthSurveyList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getClassDailyHealthSurveyList());
		listOfList.add( getStudentHealthSurveyList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, MOBILE_PROPERTY, getMaskedMobile());
		appendKeyValuePair(result, SCHOOL_PROPERTY, getSchool());
		appendKeyValuePair(result, SCHOOL_CLASS_PROPERTY, getSchoolClass());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, CQ_PROPERTY, getCq());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveyList());
		if(!getClassDailyHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "classDailyHealthSurveyCount", getClassDailyHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "classDailyHealthSurveyCurrentPageNumber", getClassDailyHealthSurveyList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyList());
		if(!getStudentHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "studentHealthSurveyCount", getStudentHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "studentHealthSurveyCurrentPageNumber", getStudentHealthSurveyList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Teacher){
		
		
			Teacher dest =(Teacher)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setMobile(getMobile());
			dest.setSchool(getSchool());
			dest.setSchoolClass(getSchoolClass());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setCq(getCq());
			dest.setVersion(getVersion());
			dest.setClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.setStudentHealthSurveyList(getStudentHealthSurveyList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Teacher){
		
			
			Teacher dest =(Teacher)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeMobile(getMobile());
			dest.mergeSchool(getSchool());
			dest.mergeSchoolClass(getSchoolClass());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeCq(getCq());
			dest.mergeVersion(getVersion());
			dest.mergeClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.mergeStudentHealthSurveyList(getStudentHealthSurveyList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Teacher){
		
			
			Teacher dest =(Teacher)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeMobile(getMobile());
			dest.mergeSchool(getSchool());
			dest.mergeSchoolClass(getSchoolClass());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getMobile(), getSchool(), getSchoolClass(), getCreateTime(), getPlatform(), getCq(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Teacher{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tmobile='"+getMobile()+"';");
		stringBuilder.append("\tschool='"+getSchool()+"';");
		stringBuilder.append("\tschoolClass='"+getSchoolClass()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		if(getCq() != null ){
 			stringBuilder.append("\tcq='ChangeRequest("+getCq().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

