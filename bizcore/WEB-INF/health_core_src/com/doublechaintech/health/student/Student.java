
package com.doublechaintech.health.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

@JsonSerialize(using = StudentSerializer.class)
public class Student extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String STUDENT_NAME_PROPERTY          = "studentName"       ;
	public static final String STUDENT_NUMBER_PROPERTY        = "studentNumber"     ;
	public static final String STUDENT_AVATAR_PROPERTY        = "studentAvatar"     ;
	public static final String GUARDIAN_NAME_PROPERTY         = "guardianName"      ;
	public static final String GUARDIAN_MOBILE_PROPERTY       = "guardianMobile"    ;
	public static final String ADDRESS_PROPERTY               = "address"           ;
	public static final String USER_PROPERTY                  = "user"              ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_HEALTH_SURVEY_LIST               = "studentHealthSurveyList";
	public static final String HEALTH_SURVEY_REPORT_LIST                = "healthSurveyReportList";

	public static final String INTERNAL_TYPE="Student";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getStudentName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mStudentName        ;
	protected		String              	mStudentNumber      ;
	protected		String              	mStudentAvatar      ;
	protected		String              	mGuardianName       ;
	protected		String              	mGuardianMobile     ;
	protected		Location            	mAddress            ;
	protected		User                	mUser               ;
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<StudentHealthSurvey>	mStudentHealthSurveyList;
	protected		SmartList<HealthSurveyReport>	mHealthSurveyReportList;
	
		
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
		setAddress( null );
		setUser( null );
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(STUDENT_NAME_PROPERTY.equals(property)){
			changeStudentNameProperty(newValueExpr);
		}
		if(STUDENT_NUMBER_PROPERTY.equals(property)){
			changeStudentNumberProperty(newValueExpr);
		}
		if(STUDENT_AVATAR_PROPERTY.equals(property)){
			changeStudentAvatarProperty(newValueExpr);
		}
		if(GUARDIAN_NAME_PROPERTY.equals(property)){
			changeGuardianNameProperty(newValueExpr);
		}
		if(GUARDIAN_MOBILE_PROPERTY.equals(property)){
			changeGuardianMobileProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
		}

      
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
			
			
			
	protected void changeStudentAvatarProperty(String newValueExpr){
	
		String oldValue = getStudentAvatar();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStudentAvatar(newValue);
		this.onChangeProperty(STUDENT_AVATAR_PROPERTY, oldValue, newValue);
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
     	
		if(STUDENT_NAME_PROPERTY.equals(property)){
			return getStudentName();
		}
		if(STUDENT_NUMBER_PROPERTY.equals(property)){
			return getStudentNumber();
		}
		if(STUDENT_AVATAR_PROPERTY.equals(property)){
			return getStudentAvatar();
		}
		if(GUARDIAN_NAME_PROPERTY.equals(property)){
			return getGuardianName();
		}
		if(GUARDIAN_MOBILE_PROPERTY.equals(property)){
			return getGuardianMobile();
		}
		if(ADDRESS_PROPERTY.equals(property)){
			return getAddress();
		}
		if(USER_PROPERTY.equals(property)){
			return getUser();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(STUDENT_HEALTH_SURVEY_LIST.equals(property)){
			List<BaseEntity> list = getStudentHealthSurveyList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(HEALTH_SURVEY_REPORT_LIST.equals(property)){
			List<BaseEntity> list = getHealthSurveyReportList().stream().map(item->item).collect(Collectors.toList());
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
	
	
	public void setStudentName(String studentName){
		this.mStudentName = trimString(studentName);;
	}
	public String getStudentName(){
		return this.mStudentName;
	}
	public Student updateStudentName(String studentName){
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
	public Student updateStudentNumber(String studentNumber){
		this.mStudentNumber = trimString(studentNumber);;
		this.changed = true;
		return this;
	}
	public void mergeStudentNumber(String studentNumber){
		if(studentNumber != null) { setStudentNumber(studentNumber);}
	}
	
	
	public void setStudentAvatar(String studentAvatar){
		this.mStudentAvatar = trimString(encodeUrl(studentAvatar));;
	}
	public String getStudentAvatar(){
		return this.mStudentAvatar;
	}
	public Student updateStudentAvatar(String studentAvatar){
		this.mStudentAvatar = trimString(encodeUrl(studentAvatar));;
		this.changed = true;
		return this;
	}
	public void mergeStudentAvatar(String studentAvatar){
		if(studentAvatar != null) { setStudentAvatar(studentAvatar);}
	}
	
	
	public void setGuardianName(String guardianName){
		this.mGuardianName = trimString(guardianName);;
	}
	public String getGuardianName(){
		return this.mGuardianName;
	}
	public Student updateGuardianName(String guardianName){
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
	public Student updateGuardianMobile(String guardianMobile){
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
	
		
	public void setAddress(Location address){
		this.mAddress = address;;
	}
	public Location getAddress(){
		return this.mAddress;
	}
	public Student updateAddress(Location address){
		this.mAddress = address;;
		this.changed = true;
		return this;
	}
	public void mergeAddress(Location address){
		if(address != null) { setAddress(address);}
	}
	
	
	public void clearAddress(){
		setAddress ( null );
		this.changed = true;
	}
	
	public void setUser(User user){
		this.mUser = user;;
	}
	public User getUser(){
		return this.mUser;
	}
	public Student updateUser(User user){
		this.mUser = user;;
		this.changed = true;
		return this;
	}
	public void mergeUser(User user){
		if(user != null) { setUser(user);}
	}
	
	
	public void clearUser(){
		setUser ( null );
		this.changed = true;
	}
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public Student updateCreateTime(DateTime createTime){
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
	public Student updatePlatform(Platform platform){
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
	
	
	


	public  SmartList<HealthSurveyReport> getHealthSurveyReportList(){
		if(this.mHealthSurveyReportList == null){
			this.mHealthSurveyReportList = new SmartList<HealthSurveyReport>();
			this.mHealthSurveyReportList.setListInternalName (HEALTH_SURVEY_REPORT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mHealthSurveyReportList;	
	}
	public  void setHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList){
		for( HealthSurveyReport healthSurveyReport:healthSurveyReportList){
			healthSurveyReport.setStudent(this);
		}

		this.mHealthSurveyReportList = healthSurveyReportList;
		this.mHealthSurveyReportList.setListInternalName (HEALTH_SURVEY_REPORT_LIST );
		
	}
	
	public  void addHealthSurveyReport(HealthSurveyReport healthSurveyReport){
		healthSurveyReport.setStudent(this);
		getHealthSurveyReportList().add(healthSurveyReport);
	}
	public  void addHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList){
		for( HealthSurveyReport healthSurveyReport:healthSurveyReportList){
			healthSurveyReport.setStudent(this);
		}
		getHealthSurveyReportList().addAll(healthSurveyReportList);
	}
	public  void mergeHealthSurveyReportList(SmartList<HealthSurveyReport> healthSurveyReportList){
		if(healthSurveyReportList==null){
			return;
		}
		if(healthSurveyReportList.isEmpty()){
			return;
		}
		addHealthSurveyReportList( healthSurveyReportList );
		
	}
	public  HealthSurveyReport removeHealthSurveyReport(HealthSurveyReport healthSurveyReportIndex){
		
		int index = getHealthSurveyReportList().indexOf(healthSurveyReportIndex);
        if(index < 0){
        	String message = "HealthSurveyReport("+healthSurveyReportIndex.getId()+") with version='"+healthSurveyReportIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        HealthSurveyReport healthSurveyReport = getHealthSurveyReportList().get(index);        
        // healthSurveyReport.clearStudent(); //disconnect with Student
        healthSurveyReport.clearFromAll(); //disconnect with Student
		
		boolean result = getHealthSurveyReportList().planToRemove(healthSurveyReport);
        if(!result){
        	String message = "HealthSurveyReport("+healthSurveyReportIndex.getId()+") with version='"+healthSurveyReportIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return healthSurveyReport;
        
	
	}
	//断舍离
	public  void breakWithHealthSurveyReport(HealthSurveyReport healthSurveyReport){
		
		if(healthSurveyReport == null){
			return;
		}
		healthSurveyReport.setStudent(null);
		//getHealthSurveyReportList().remove();
	
	}
	
	public  boolean hasHealthSurveyReport(HealthSurveyReport healthSurveyReport){
	
		return getHealthSurveyReportList().contains(healthSurveyReport);
  
	}
	
	public void copyHealthSurveyReportFrom(HealthSurveyReport healthSurveyReport) {

		HealthSurveyReport healthSurveyReportInList = findTheHealthSurveyReport(healthSurveyReport);
		HealthSurveyReport newHealthSurveyReport = new HealthSurveyReport();
		healthSurveyReportInList.copyTo(newHealthSurveyReport);
		newHealthSurveyReport.setVersion(0);//will trigger copy
		getHealthSurveyReportList().add(newHealthSurveyReport);
		addItemToFlexiableObject(COPIED_CHILD, newHealthSurveyReport);
	}
	
	public  HealthSurveyReport findTheHealthSurveyReport(HealthSurveyReport healthSurveyReport){
		
		int index =  getHealthSurveyReportList().indexOf(healthSurveyReport);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "HealthSurveyReport("+healthSurveyReport.getId()+") with version='"+healthSurveyReport.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getHealthSurveyReportList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpHealthSurveyReportList(){
		getHealthSurveyReportList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getAddress(), internalType);
		addToEntityList(this, entityList, getUser(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentHealthSurveyList(), internalType);
		collectFromList(this, entityList, getHealthSurveyReportList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentHealthSurveyList());
		listOfList.add( getHealthSurveyReportList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, STUDENT_NAME_PROPERTY, getStudentName());
		appendKeyValuePair(result, STUDENT_NUMBER_PROPERTY, getStudentNumber());
		appendKeyValuePair(result, STUDENT_AVATAR_PROPERTY, getStudentAvatar());
		appendKeyValuePair(result, GUARDIAN_NAME_PROPERTY, getGuardianName());
		appendKeyValuePair(result, GUARDIAN_MOBILE_PROPERTY, getMaskedGuardianMobile());
		appendKeyValuePair(result, ADDRESS_PROPERTY, getAddress());
		appendKeyValuePair(result, USER_PROPERTY, getUser());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyList());
		if(!getStudentHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "studentHealthSurveyCount", getStudentHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "studentHealthSurveyCurrentPageNumber", getStudentHealthSurveyList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, HEALTH_SURVEY_REPORT_LIST, getHealthSurveyReportList());
		if(!getHealthSurveyReportList().isEmpty()){
			appendKeyValuePair(result, "healthSurveyReportCount", getHealthSurveyReportList().getTotalCount());
			appendKeyValuePair(result, "healthSurveyReportCurrentPageNumber", getHealthSurveyReportList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Student){
		
		
			Student dest =(Student)baseDest;
		
			dest.setId(getId());
			dest.setStudentName(getStudentName());
			dest.setStudentNumber(getStudentNumber());
			dest.setStudentAvatar(getStudentAvatar());
			dest.setGuardianName(getGuardianName());
			dest.setGuardianMobile(getGuardianMobile());
			dest.setAddress(getAddress());
			dest.setUser(getUser());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setStudentHealthSurveyList(getStudentHealthSurveyList());
			dest.setHealthSurveyReportList(getHealthSurveyReportList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Student){
		
			
			Student dest =(Student)baseDest;
		
			dest.mergeId(getId());
			dest.mergeStudentName(getStudentName());
			dest.mergeStudentNumber(getStudentNumber());
			dest.mergeStudentAvatar(getStudentAvatar());
			dest.mergeGuardianName(getGuardianName());
			dest.mergeGuardianMobile(getGuardianMobile());
			dest.mergeAddress(getAddress());
			dest.mergeUser(getUser());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeStudentHealthSurveyList(getStudentHealthSurveyList());
			dest.mergeHealthSurveyReportList(getHealthSurveyReportList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Student){
		
			
			Student dest =(Student)baseDest;
		
			dest.mergeId(getId());
			dest.mergeStudentName(getStudentName());
			dest.mergeStudentNumber(getStudentNumber());
			dest.mergeStudentAvatar(getStudentAvatar());
			dest.mergeGuardianName(getGuardianName());
			dest.mergeGuardianMobile(getGuardianMobile());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getStudentName(), getStudentNumber(), getStudentAvatar(), getGuardianName(), getGuardianMobile(), getAddress(), getUser(), getCreateTime(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Student{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tstudentName='"+getStudentName()+"';");
		stringBuilder.append("\tstudentNumber='"+getStudentNumber()+"';");
		stringBuilder.append("\tstudentAvatar='"+getStudentAvatar()+"';");
		stringBuilder.append("\tguardianName='"+getGuardianName()+"';");
		stringBuilder.append("\tguardianMobile='"+getGuardianMobile()+"';");
		if(getAddress() != null ){
 			stringBuilder.append("\taddress='Location("+getAddress().getId()+")';");
 		}
		if(getUser() != null ){
 			stringBuilder.append("\tuser='User("+getUser().getId()+")';");
 		}
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

