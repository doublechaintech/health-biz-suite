
package com.doublechaintech.health.province;

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
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.location.Location;

@JsonSerialize(using = ProvinceSerializer.class)
public class Province extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String CITY_LIST                                = "cityList"          ;
	public static final String LOCATION_LIST                            = "locationList"      ;

	public static final String INTERNAL_TYPE="Province";
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
	protected		Platform            	mPlatform           ;
	protected		DateTime            	mCreateTime         ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<City>     	mCityList           ;
	protected		SmartList<Location> 	mLocationList       ;
	
		
	public 	Province(){
		// lazy load for all the properties
	}
	public 	static Province withId(String id){
		Province province = new Province();
		province.setId(id);
		province.setVersion(Integer.MAX_VALUE);
		return province;
	}
	public 	static Province refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
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
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(CITY_LIST.equals(property)){
			List<BaseEntity> list = getCityList().stream().map(item->item).collect(Collectors.toList());
			return list;
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
	public Province updateId(String id){
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
	public Province updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Province updatePlatform(Platform platform){
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
	public Province updateCreateTime(DateTime createTime){
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
	public Province updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<City> getCityList(){
		if(this.mCityList == null){
			this.mCityList = new SmartList<City>();
			this.mCityList.setListInternalName (CITY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mCityList;	
	}
	public  void setCityList(SmartList<City> cityList){
		for( City city:cityList){
			city.setProvince(this);
		}

		this.mCityList = cityList;
		this.mCityList.setListInternalName (CITY_LIST );
		
	}
	
	public  void addCity(City city){
		city.setProvince(this);
		getCityList().add(city);
	}
	public  void addCityList(SmartList<City> cityList){
		for( City city:cityList){
			city.setProvince(this);
		}
		getCityList().addAll(cityList);
	}
	public  void mergeCityList(SmartList<City> cityList){
		if(cityList==null){
			return;
		}
		if(cityList.isEmpty()){
			return;
		}
		addCityList( cityList );
		
	}
	public  City removeCity(City cityIndex){
		
		int index = getCityList().indexOf(cityIndex);
        if(index < 0){
        	String message = "City("+cityIndex.getId()+") with version='"+cityIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        City city = getCityList().get(index);        
        // city.clearProvince(); //disconnect with Province
        city.clearFromAll(); //disconnect with Province
		
		boolean result = getCityList().planToRemove(city);
        if(!result){
        	String message = "City("+cityIndex.getId()+") with version='"+cityIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return city;
        
	
	}
	//断舍离
	public  void breakWithCity(City city){
		
		if(city == null){
			return;
		}
		city.setProvince(null);
		//getCityList().remove();
	
	}
	
	public  boolean hasCity(City city){
	
		return getCityList().contains(city);
  
	}
	
	public void copyCityFrom(City city) {

		City cityInList = findTheCity(city);
		City newCity = new City();
		cityInList.copyTo(newCity);
		newCity.setVersion(0);//will trigger copy
		getCityList().add(newCity);
		addItemToFlexiableObject(COPIED_CHILD, newCity);
	}
	
	public  City findTheCity(City city){
		
		int index =  getCityList().indexOf(city);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "City("+city.getId()+") with version='"+city.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getCityList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpCityList(){
		getCityList().clear();
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
			location.setProvince(this);
		}

		this.mLocationList = locationList;
		this.mLocationList.setListInternalName (LOCATION_LIST );
		
	}
	
	public  void addLocation(Location location){
		location.setProvince(this);
		getLocationList().add(location);
	}
	public  void addLocationList(SmartList<Location> locationList){
		for( Location location:locationList){
			location.setProvince(this);
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
        // location.clearProvince(); //disconnect with Province
        location.clearFromAll(); //disconnect with Province
		
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
		location.setProvince(null);
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

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getCityList(), internalType);
		collectFromList(this, entityList, getLocationList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getCityList());
		listOfList.add( getLocationList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, CITY_LIST, getCityList());
		if(!getCityList().isEmpty()){
			appendKeyValuePair(result, "cityCount", getCityList().getTotalCount());
			appendKeyValuePair(result, "cityCurrentPageNumber", getCityList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, LOCATION_LIST, getLocationList());
		if(!getLocationList().isEmpty()){
			appendKeyValuePair(result, "locationCount", getLocationList().getTotalCount());
			appendKeyValuePair(result, "locationCurrentPageNumber", getLocationList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Province){
		
		
			Province dest =(Province)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setPlatform(getPlatform());
			dest.setCreateTime(getCreateTime());
			dest.setVersion(getVersion());
			dest.setCityList(getCityList());
			dest.setLocationList(getLocationList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Province){
		
			
			Province dest =(Province)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergePlatform(getPlatform());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());
			dest.mergeCityList(getCityList());
			dest.mergeLocationList(getLocationList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Province){
		
			
			Province dest =(Province)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getPlatform(), getCreateTime(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Province{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
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

