
package com.doublechaintech.health.district;

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
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;

@JsonSerialize(using = DistrictSerializer.class)
public class District extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CITY_PROPERTY                  = "city"              ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String LOCATION_LIST                            = "locationList"      ;

	public static final String INTERNAL_TYPE="District";
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
	protected		City                	mCity               ;
	protected		Platform            	mPlatform           ;
	protected		DateTime            	mCreateTime         ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Location> 	mLocationList       ;
	
		
	public 	District(){
		// lazy load for all the properties
	}
	public 	static District withId(String id){
		District district = new District();
		district.setId(id);
		district.setVersion(Integer.MAX_VALUE);
		return district;
	}
	public 	static District refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setCity( null );
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
		if(CITY_PROPERTY.equals(property)){
			return getCity();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(LOCATION_LIST.equals(property)){
			List<BaseEntity> list = getLocationList().stream().map(item->item).collect(Collectors.toList());
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
	public District updateId(String id){
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
	public District updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setCity(City city){
		this.mCity = city;;
	}
	public City getCity(){
		return this.mCity;
	}
	public District updateCity(City city){
		this.mCity = city;;
		this.changed = true;
		return this;
	}
	public void mergeCity(City city){
		if(city != null) { setCity(city);}
	}
	
	
	public void clearCity(){
		setCity ( null );
		this.changed = true;
	}
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public District updatePlatform(Platform platform){
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
	public District updateCreateTime(DateTime createTime){
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
	public District updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Location> getLocationList(){
		if(this.mLocationList == null){
			this.mLocationList = new SmartList<Location>();
			this.mLocationList.setListInternalName (LOCATION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mLocationList;	
	}
	public  void setLocationList(SmartList<Location> locationList){
		for( Location location:locationList){
			location.setDistrict(this);
		}

		this.mLocationList = locationList;
		this.mLocationList.setListInternalName (LOCATION_LIST );
		
	}
	
	public  void addLocation(Location location){
		location.setDistrict(this);
		getLocationList().add(location);
	}
	public  void addLocationList(SmartList<Location> locationList){
		for( Location location:locationList){
			location.setDistrict(this);
		}
		getLocationList().addAll(locationList);
	}
	public  void mergeLocationList(SmartList<Location> locationList){
		if(locationList==null){
			return;
		}
		if(locationList.isEmpty()){
			return;
		}
		addLocationList( locationList );
		
	}
	public  Location removeLocation(Location locationIndex){
		
		int index = getLocationList().indexOf(locationIndex);
        if(index < 0){
        	String message = "Location("+locationIndex.getId()+") with version='"+locationIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Location location = getLocationList().get(index);        
        // location.clearDistrict(); //disconnect with District
        location.clearFromAll(); //disconnect with District
		
		boolean result = getLocationList().planToRemove(location);
        if(!result){
        	String message = "Location("+locationIndex.getId()+") with version='"+locationIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return location;
        
	
	}
	//断舍离
	public  void breakWithLocation(Location location){
		
		if(location == null){
			return;
		}
		location.setDistrict(null);
		//getLocationList().remove();
	
	}
	
	public  boolean hasLocation(Location location){
	
		return getLocationList().contains(location);
  
	}
	
	public void copyLocationFrom(Location location) {

		Location locationInList = findTheLocation(location);
		Location newLocation = new Location();
		locationInList.copyTo(newLocation);
		newLocation.setVersion(0);//will trigger copy
		getLocationList().add(newLocation);
		addItemToFlexiableObject(COPIED_CHILD, newLocation);
	}
	
	public  Location findTheLocation(Location location){
		
		int index =  getLocationList().indexOf(location);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Location("+location.getId()+") with version='"+location.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getLocationList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpLocationList(){
		getLocationList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getCity(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getLocationList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getLocationList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CITY_PROPERTY, getCity());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, LOCATION_LIST, getLocationList());
		if(!getLocationList().isEmpty()){
			appendKeyValuePair(result, "locationCount", getLocationList().getTotalCount());
			appendKeyValuePair(result, "locationCurrentPageNumber", getLocationList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof District){
		
		
			District dest =(District)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCity(getCity());
			dest.setPlatform(getPlatform());
			dest.setCreateTime(getCreateTime());
			dest.setVersion(getVersion());
			dest.setLocationList(getLocationList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof District){
		
			
			District dest =(District)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCity(getCity());
			dest.mergePlatform(getPlatform());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());
			dest.mergeLocationList(getLocationList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof District){
		
			
			District dest =(District)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCity(), getPlatform(), getCreateTime(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("District{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getCity() != null ){
 			stringBuilder.append("\tcity='City("+getCity().getId()+")';");
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

