
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
import com.doublechaintech.health.schoolclass.SchoolClass;

@JsonSerialize(using = TeacherSerializer.class)
public class Teacher extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String MOBILE_PROPERTY                = "mobile"            ;
	public static final String SCHOOLE_PROPERTY               = "schoole"           ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String CQ_PROPERTY                    = "cq"                ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String SCHOOL_CLASS_LIST                        = "schoolClassList"   ;

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
	protected		String              	mSchoole            ;
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		ChangeRequest       	mCq                 ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<SchoolClass>	mSchoolClassList    ;
	
		
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
		if(SCHOOLE_PROPERTY.equals(property)){
			changeSchooleProperty(newValueExpr);
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
			
			
			
	protected void changeSchooleProperty(String newValueExpr){
		String oldValue = getSchoole();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSchoole(newValue);
		this.onChangeProperty(SCHOOLE_PROPERTY, oldValue, newValue);
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
		if(SCHOOLE_PROPERTY.equals(property)){
			return getSchoole();
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
		if(SCHOOL_CLASS_LIST.equals(property)){
			List<BaseEntity> list = getSchoolClassList().stream().map(item->item).collect(Collectors.toList());
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
	
		
	public void setSchoole(String schoole){
		this.mSchoole = trimString(schoole);;
	}
	public String getSchoole(){
		return this.mSchoole;
	}
	public Teacher updateSchoole(String schoole){
		this.mSchoole = trimString(schoole);;
		this.changed = true;
		return this;
	}
	public void mergeSchoole(String schoole){
		if(schoole != null) { setSchoole(schoole);}
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
	
	

	public  SmartList<SchoolClass> getSchoolClassList(){
		if(this.mSchoolClassList == null){
			this.mSchoolClassList = new SmartList<SchoolClass>();
			this.mSchoolClassList.setListInternalName (SCHOOL_CLASS_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mSchoolClassList;	
	}
	public  void setSchoolClassList(SmartList<SchoolClass> schoolClassList){
		for( SchoolClass schoolClass:schoolClassList){
			schoolClass.setClassTeacher(this);
		}

		this.mSchoolClassList = schoolClassList;
		this.mSchoolClassList.setListInternalName (SCHOOL_CLASS_LIST );
		
	}
	
	public  void addSchoolClass(SchoolClass schoolClass){
		schoolClass.setClassTeacher(this);
		getSchoolClassList().add(schoolClass);
	}
	public  void addSchoolClassList(SmartList<SchoolClass> schoolClassList){
		for( SchoolClass schoolClass:schoolClassList){
			schoolClass.setClassTeacher(this);
		}
		getSchoolClassList().addAll(schoolClassList);
	}
	public  void mergeSchoolClassList(SmartList<SchoolClass> schoolClassList){
		if(schoolClassList==null){
			return;
		}
		if(schoolClassList.isEmpty()){
			return;
		}
		addSchoolClassList( schoolClassList );
		
	}
	public  SchoolClass removeSchoolClass(SchoolClass schoolClassIndex){
		
		int index = getSchoolClassList().indexOf(schoolClassIndex);
        if(index < 0){
        	String message = "SchoolClass("+schoolClassIndex.getId()+") with version='"+schoolClassIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        SchoolClass schoolClass = getSchoolClassList().get(index);        
        // schoolClass.clearClassTeacher(); //disconnect with ClassTeacher
        schoolClass.clearFromAll(); //disconnect with ClassTeacher
		
		boolean result = getSchoolClassList().planToRemove(schoolClass);
        if(!result){
        	String message = "SchoolClass("+schoolClassIndex.getId()+") with version='"+schoolClassIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return schoolClass;
        
	
	}
	//断舍离
	public  void breakWithSchoolClass(SchoolClass schoolClass){
		
		if(schoolClass == null){
			return;
		}
		schoolClass.setClassTeacher(null);
		//getSchoolClassList().remove();
	
	}
	
	public  boolean hasSchoolClass(SchoolClass schoolClass){
	
		return getSchoolClassList().contains(schoolClass);
  
	}
	
	public void copySchoolClassFrom(SchoolClass schoolClass) {

		SchoolClass schoolClassInList = findTheSchoolClass(schoolClass);
		SchoolClass newSchoolClass = new SchoolClass();
		schoolClassInList.copyTo(newSchoolClass);
		newSchoolClass.setVersion(0);//will trigger copy
		getSchoolClassList().add(newSchoolClass);
		addItemToFlexiableObject(COPIED_CHILD, newSchoolClass);
	}
	
	public  SchoolClass findTheSchoolClass(SchoolClass schoolClass){
		
		int index =  getSchoolClassList().indexOf(schoolClass);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "SchoolClass("+schoolClass.getId()+") with version='"+schoolClass.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getSchoolClassList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpSchoolClassList(){
		getSchoolClassList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);
		addToEntityList(this, entityList, getCq(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getSchoolClassList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getSchoolClassList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, MOBILE_PROPERTY, getMaskedMobile());
		appendKeyValuePair(result, SCHOOLE_PROPERTY, getSchoole());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, CQ_PROPERTY, getCq());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, SCHOOL_CLASS_LIST, getSchoolClassList());
		if(!getSchoolClassList().isEmpty()){
			appendKeyValuePair(result, "schoolClassCount", getSchoolClassList().getTotalCount());
			appendKeyValuePair(result, "schoolClassCurrentPageNumber", getSchoolClassList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Teacher){
		
		
			Teacher dest =(Teacher)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setMobile(getMobile());
			dest.setSchoole(getSchoole());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setCq(getCq());
			dest.setVersion(getVersion());
			dest.setSchoolClassList(getSchoolClassList());

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
			dest.mergeSchoole(getSchoole());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeCq(getCq());
			dest.mergeVersion(getVersion());
			dest.mergeSchoolClassList(getSchoolClassList());

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
			dest.mergeSchoole(getSchoole());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getMobile(), getSchoole(), getCreateTime(), getPlatform(), getCq(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Teacher{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tmobile='"+getMobile()+"';");
		stringBuilder.append("\tschoole='"+getSchoole()+"';");
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

