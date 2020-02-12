
package com.doublechaintech.health.location;

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
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;

@JsonSerialize(using = LocationSerializer.class)
public class Location extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String ADDRESS_PROPERTY               = "address"           ;
	public static final String DISTRICT_PROPERTY              = "district"          ;
	public static final String PROVINCE_PROPERTY              = "province"          ;
	public static final String LATITUDE_PROPERTY              = "latitude"          ;
	public static final String LONGITUDE_PROPERTY             = "longitude"         ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_LIST                             = "studentList"       ;

	public static final String INTERNAL_TYPE="Location";
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
	protected		String              	mAddress            ;
	protected		District            	mDistrict           ;
	protected		Province            	mProvince           ;
	protected		BigDecimal          	mLatitude           ;
	protected		BigDecimal          	mLongitude          ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Student>  	mStudentList        ;
	
		
	public 	Location(){
		// lazy load for all the properties
	}
	public 	static Location withId(String id){
		Location location = new Location();
		location.setId(id);
		location.setVersion(Integer.MAX_VALUE);
		return location;
	}
	public 	static Location refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setDistrict( null );
		setProvince( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(ADDRESS_PROPERTY.equals(property)){
			changeAddressProperty(newValueExpr);
		}
		if(LATITUDE_PROPERTY.equals(property)){
			changeLatitudeProperty(newValueExpr);
		}
		if(LONGITUDE_PROPERTY.equals(property)){
			changeLongitudeProperty(newValueExpr);
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
			
			
			
	protected void changeAddressProperty(String newValueExpr){
	
		String oldValue = getAddress();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateAddress(newValue);
		this.onChangeProperty(ADDRESS_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeLatitudeProperty(String newValueExpr){
	
		BigDecimal oldValue = getLatitude();
		BigDecimal newValue = parseBigDecimal(newValueExpr);
		if(equalsBigDecimal(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLatitude(newValue);
		this.onChangeProperty(LATITUDE_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeLongitudeProperty(String newValueExpr){
	
		BigDecimal oldValue = getLongitude();
		BigDecimal newValue = parseBigDecimal(newValueExpr);
		if(equalsBigDecimal(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLongitude(newValue);
		this.onChangeProperty(LONGITUDE_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(ADDRESS_PROPERTY.equals(property)){
			return getAddress();
		}
		if(DISTRICT_PROPERTY.equals(property)){
			return getDistrict();
		}
		if(PROVINCE_PROPERTY.equals(property)){
			return getProvince();
		}
		if(LATITUDE_PROPERTY.equals(property)){
			return getLatitude();
		}
		if(LONGITUDE_PROPERTY.equals(property)){
			return getLongitude();
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
	public Location updateId(String id){
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
	public Location updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setAddress(String address){
		this.mAddress = trimString(address);;
	}
	public String getAddress(){
		return this.mAddress;
	}
	public Location updateAddress(String address){
		this.mAddress = trimString(address);;
		this.changed = true;
		return this;
	}
	public void mergeAddress(String address){
		if(address != null) { setAddress(address);}
	}
	
	
	public void setDistrict(District district){
		this.mDistrict = district;;
	}
	public District getDistrict(){
		return this.mDistrict;
	}
	public Location updateDistrict(District district){
		this.mDistrict = district;;
		this.changed = true;
		return this;
	}
	public void mergeDistrict(District district){
		if(district != null) { setDistrict(district);}
	}
	
	
	public void clearDistrict(){
		setDistrict ( null );
		this.changed = true;
	}
	
	public void setProvince(Province province){
		this.mProvince = province;;
	}
	public Province getProvince(){
		return this.mProvince;
	}
	public Location updateProvince(Province province){
		this.mProvince = province;;
		this.changed = true;
		return this;
	}
	public void mergeProvince(Province province){
		if(province != null) { setProvince(province);}
	}
	
	
	public void clearProvince(){
		setProvince ( null );
		this.changed = true;
	}
	
	public void setLatitude(BigDecimal latitude){
		this.mLatitude = latitude;;
	}
	public BigDecimal getLatitude(){
		return this.mLatitude;
	}
	public Location updateLatitude(BigDecimal latitude){
		this.mLatitude = latitude;;
		this.changed = true;
		return this;
	}
	public void mergeLatitude(BigDecimal latitude){
		setLatitude(latitude);
	}
	
	
	public void setLongitude(BigDecimal longitude){
		this.mLongitude = longitude;;
	}
	public BigDecimal getLongitude(){
		return this.mLongitude;
	}
	public Location updateLongitude(BigDecimal longitude){
		this.mLongitude = longitude;;
		this.changed = true;
		return this;
	}
	public void mergeLongitude(BigDecimal longitude){
		setLongitude(longitude);
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Location updateVersion(int version){
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
			student.setAddress(this);
		}

		this.mStudentList = studentList;
		this.mStudentList.setListInternalName (STUDENT_LIST );
		
	}
	
	public  void addStudent(Student student){
		student.setAddress(this);
		getStudentList().add(student);
	}
	public  void addStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setAddress(this);
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
        // student.clearAddress(); //disconnect with Address
        student.clearFromAll(); //disconnect with Address
		
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
		student.setAddress(null);
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

		addToEntityList(this, entityList, getDistrict(), internalType);
		addToEntityList(this, entityList, getProvince(), internalType);

		
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
		appendKeyValuePair(result, ADDRESS_PROPERTY, getAddress());
		appendKeyValuePair(result, DISTRICT_PROPERTY, getDistrict());
		appendKeyValuePair(result, PROVINCE_PROPERTY, getProvince());
		appendKeyValuePair(result, LATITUDE_PROPERTY, getLatitude());
		appendKeyValuePair(result, LONGITUDE_PROPERTY, getLongitude());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_LIST, getStudentList());
		if(!getStudentList().isEmpty()){
			appendKeyValuePair(result, "studentCount", getStudentList().getTotalCount());
			appendKeyValuePair(result, "studentCurrentPageNumber", getStudentList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Location){
		
		
			Location dest =(Location)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setAddress(getAddress());
			dest.setDistrict(getDistrict());
			dest.setProvince(getProvince());
			dest.setLatitude(getLatitude());
			dest.setLongitude(getLongitude());
			dest.setVersion(getVersion());
			dest.setStudentList(getStudentList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Location){
		
			
			Location dest =(Location)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAddress(getAddress());
			dest.mergeDistrict(getDistrict());
			dest.mergeProvince(getProvince());
			dest.mergeLatitude(getLatitude());
			dest.mergeLongitude(getLongitude());
			dest.mergeVersion(getVersion());
			dest.mergeStudentList(getStudentList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Location){
		
			
			Location dest =(Location)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAddress(getAddress());
			dest.mergeLatitude(getLatitude());
			dest.mergeLongitude(getLongitude());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getAddress(), getDistrict(), getProvince(), getLatitude(), getLongitude(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Location{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\taddress='"+getAddress()+"';");
		if(getDistrict() != null ){
 			stringBuilder.append("\tdistrict='District("+getDistrict().getId()+")';");
 		}
		if(getProvince() != null ){
 			stringBuilder.append("\tprovince='Province("+getProvince().getId()+")';");
 		}
		stringBuilder.append("\tlatitude='"+getLatitude()+"';");
		stringBuilder.append("\tlongitude='"+getLongitude()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

