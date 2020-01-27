
package com.doublechaintech.health.guardian;

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
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.wechatuser.WechatUser;

@JsonSerialize(using = GuardianSerializer.class)
public class Guardian extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String MOBILE_PROPERTY                = "mobile"            ;
	public static final String ADDRESS_PROPERTY               = "address"           ;
	public static final String WECHAT_USER_PROPERTY           = "wechatUser"        ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String CQ_PROPERTY                    = "cq"                ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_LIST                             = "studentList"       ;

	public static final String INTERNAL_TYPE="Guardian";
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
	protected		Location            	mAddress            ;
	protected		WechatUser          	mWechatUser         ;
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		ChangeRequest       	mCq                 ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Student>  	mStudentList        ;
	
		
	public 	Guardian(){
		// lazy load for all the properties
	}
	public 	static Guardian withId(String id){
		Guardian guardian = new Guardian();
		guardian.setId(id);
		guardian.setVersion(Integer.MAX_VALUE);
		return guardian;
	}
	public 	static Guardian refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setAddress( null );
		setWechatUser( null );
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
		if(ADDRESS_PROPERTY.equals(property)){
			return getAddress();
		}
		if(WECHAT_USER_PROPERTY.equals(property)){
			return getWechatUser();
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
		if(STUDENT_LIST.equals(property)){
			List<BaseEntity> list = getStudentList().stream().map(item->item).collect(Collectors.toList());
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
	public Guardian updateId(String id){
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
	public Guardian updateName(String name){
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
	public Guardian updateMobile(String mobile){
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
	
		
	public void setAddress(Location address){
		this.mAddress = address;;
	}
	public Location getAddress(){
		return this.mAddress;
	}
	public Guardian updateAddress(Location address){
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
	
	public void setWechatUser(WechatUser wechatUser){
		this.mWechatUser = wechatUser;;
	}
	public WechatUser getWechatUser(){
		return this.mWechatUser;
	}
	public Guardian updateWechatUser(WechatUser wechatUser){
		this.mWechatUser = wechatUser;;
		this.changed = true;
		return this;
	}
	public void mergeWechatUser(WechatUser wechatUser){
		if(wechatUser != null) { setWechatUser(wechatUser);}
	}
	
	
	public void clearWechatUser(){
		setWechatUser ( null );
		this.changed = true;
	}
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public Guardian updateCreateTime(DateTime createTime){
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
	public Guardian updatePlatform(Platform platform){
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
	public Guardian updateCq(ChangeRequest cq){
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
	public Guardian updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Student> getStudentList(){
		if(this.mStudentList == null){
			this.mStudentList = new SmartList<Student>();
			this.mStudentList.setListInternalName (STUDENT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentList;	
	}
	public  void setStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setGuardian(this);
		}

		this.mStudentList = studentList;
		this.mStudentList.setListInternalName (STUDENT_LIST );
		
	}
	
	public  void addStudent(Student student){
		student.setGuardian(this);
		getStudentList().add(student);
	}
	public  void addStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setGuardian(this);
		}
		getStudentList().addAll(studentList);
	}
	public  void mergeStudentList(SmartList<Student> studentList){
		if(studentList==null){
			return;
		}
		if(studentList.isEmpty()){
			return;
		}
		addStudentList( studentList );
		
	}
	public  Student removeStudent(Student studentIndex){
		
		int index = getStudentList().indexOf(studentIndex);
        if(index < 0){
        	String message = "Student("+studentIndex.getId()+") with version='"+studentIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Student student = getStudentList().get(index);        
        // student.clearGuardian(); //disconnect with Guardian
        student.clearFromAll(); //disconnect with Guardian
		
		boolean result = getStudentList().planToRemove(student);
        if(!result){
        	String message = "Student("+studentIndex.getId()+") with version='"+studentIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return student;
        
	
	}
	//断舍离
	public  void breakWithStudent(Student student){
		
		if(student == null){
			return;
		}
		student.setGuardian(null);
		//getStudentList().remove();
	
	}
	
	public  boolean hasStudent(Student student){
	
		return getStudentList().contains(student);
  
	}
	
	public void copyStudentFrom(Student student) {

		Student studentInList = findTheStudent(student);
		Student newStudent = new Student();
		studentInList.copyTo(newStudent);
		newStudent.setVersion(0);//will trigger copy
		getStudentList().add(newStudent);
		addItemToFlexiableObject(COPIED_CHILD, newStudent);
	}
	
	public  Student findTheStudent(Student student){
		
		int index =  getStudentList().indexOf(student);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Student("+student.getId()+") with version='"+student.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentList(){
		getStudentList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getAddress(), internalType);
		addToEntityList(this, entityList, getWechatUser(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);
		addToEntityList(this, entityList, getCq(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, MOBILE_PROPERTY, getMaskedMobile());
		appendKeyValuePair(result, ADDRESS_PROPERTY, getAddress());
		appendKeyValuePair(result, WECHAT_USER_PROPERTY, getWechatUser());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, CQ_PROPERTY, getCq());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_LIST, getStudentList());
		if(!getStudentList().isEmpty()){
			appendKeyValuePair(result, "studentCount", getStudentList().getTotalCount());
			appendKeyValuePair(result, "studentCurrentPageNumber", getStudentList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Guardian){
		
		
			Guardian dest =(Guardian)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setMobile(getMobile());
			dest.setAddress(getAddress());
			dest.setWechatUser(getWechatUser());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setCq(getCq());
			dest.setVersion(getVersion());
			dest.setStudentList(getStudentList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Guardian){
		
			
			Guardian dest =(Guardian)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeMobile(getMobile());
			dest.mergeAddress(getAddress());
			dest.mergeWechatUser(getWechatUser());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeCq(getCq());
			dest.mergeVersion(getVersion());
			dest.mergeStudentList(getStudentList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Guardian){
		
			
			Guardian dest =(Guardian)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeMobile(getMobile());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getMobile(), getAddress(), getWechatUser(), getCreateTime(), getPlatform(), getCq(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Guardian{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tmobile='"+getMobile()+"';");
		if(getAddress() != null ){
 			stringBuilder.append("\taddress='Location("+getAddress().getId()+")';");
 		}
		if(getWechatUser() != null ){
 			stringBuilder.append("\twechatUser='WechatUser("+getWechatUser().getId()+")';");
 		}
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

