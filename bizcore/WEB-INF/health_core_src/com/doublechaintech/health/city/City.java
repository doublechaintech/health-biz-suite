
package com.doublechaintech.health.city;

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
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.district.District;

@JsonSerialize(using = CitySerializer.class)
public class City extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String PROVINCE_PROPERTY              = "province"          ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String DISTRICT_LIST                            = "districtList"      ;

	public static final String INTERNAL_TYPE="City";
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
	protected		Province            	mProvince           ;
	protected		Platform            	mPlatform           ;
	protected		DateTime            	mCreateTime         ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<District> 	mDistrictList       ;
	
		
	public 	City(){
		// lazy load for all the properties
	}
	public 	static City withId(String id){
		City city = new City();
		city.setId(id);
		city.setVersion(Integer.MAX_VALUE);
		return city;
	}
	public 	static City refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setProvince( null );
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
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
		if(PROVINCE_PROPERTY.equals(property)){
			return getProvince();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(DISTRICT_LIST.equals(property)){
			List<BaseEntity> list = getDistrictList().stream().map(item->item).collect(Collectors.toList());
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
	public City updateId(String id){
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
	public City updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setProvince(Province province){
		this.mProvince = province;;
	}
	public Province getProvince(){
		return this.mProvince;
	}
	public City updateProvince(Province province){
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
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public City updatePlatform(Platform platform){
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
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public City updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public City updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<District> getDistrictList(){
		if(this.mDistrictList == null){
			this.mDistrictList = new SmartList<District>();
			this.mDistrictList.setListInternalName (DISTRICT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mDistrictList;	
	}
	public  void setDistrictList(SmartList<District> districtList){
		for( District district:districtList){
			district.setCity(this);
		}

		this.mDistrictList = districtList;
		this.mDistrictList.setListInternalName (DISTRICT_LIST );
		
	}
	
	public  void addDistrict(District district){
		district.setCity(this);
		getDistrictList().add(district);
	}
	public  void addDistrictList(SmartList<District> districtList){
		for( District district:districtList){
			district.setCity(this);
		}
		getDistrictList().addAll(districtList);
	}
	public  void mergeDistrictList(SmartList<District> districtList){
		if(districtList==null){
			return;
		}
		if(districtList.isEmpty()){
			return;
		}
		addDistrictList( districtList );
		
	}
	public  District removeDistrict(District districtIndex){
		
		int index = getDistrictList().indexOf(districtIndex);
        if(index < 0){
        	String message = "District("+districtIndex.getId()+") with version='"+districtIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        District district = getDistrictList().get(index);        
        // district.clearCity(); //disconnect with City
        district.clearFromAll(); //disconnect with City
		
		boolean result = getDistrictList().planToRemove(district);
        if(!result){
        	String message = "District("+districtIndex.getId()+") with version='"+districtIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return district;
        
	
	}
	//断舍离
	public  void breakWithDistrict(District district){
		
		if(district == null){
			return;
		}
		district.setCity(null);
		//getDistrictList().remove();
	
	}
	
	public  boolean hasDistrict(District district){
	
		return getDistrictList().contains(district);
  
	}
	
	public void copyDistrictFrom(District district) {

		District districtInList = findTheDistrict(district);
		District newDistrict = new District();
		districtInList.copyTo(newDistrict);
		newDistrict.setVersion(0);//will trigger copy
		getDistrictList().add(newDistrict);
		addItemToFlexiableObject(COPIED_CHILD, newDistrict);
	}
	
	public  District findTheDistrict(District district){
		
		int index =  getDistrictList().indexOf(district);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "District("+district.getId()+") with version='"+district.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getDistrictList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpDistrictList(){
		getDistrictList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getProvince(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getDistrictList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getDistrictList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, PROVINCE_PROPERTY, getProvince());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, DISTRICT_LIST, getDistrictList());
		if(!getDistrictList().isEmpty()){
			appendKeyValuePair(result, "districtCount", getDistrictList().getTotalCount());
			appendKeyValuePair(result, "districtCurrentPageNumber", getDistrictList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof City){
		
		
			City dest =(City)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setProvince(getProvince());
			dest.setPlatform(getPlatform());
			dest.setCreateTime(getCreateTime());
			dest.setVersion(getVersion());
			dest.setDistrictList(getDistrictList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof City){
		
			
			City dest =(City)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeProvince(getProvince());
			dest.mergePlatform(getPlatform());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());
			dest.mergeDistrictList(getDistrictList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof City){
		
			
			City dest =(City)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getProvince(), getPlatform(), getCreateTime(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("City{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getProvince() != null ){
 			stringBuilder.append("\tprovince='Province("+getProvince().getId()+")';");
 		}
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

