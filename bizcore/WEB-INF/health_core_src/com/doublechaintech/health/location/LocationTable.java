
package com.doublechaintech.health.location;
import com.doublechaintech.health.AccessKey;


public class LocationTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="location_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_ADDRESS = "address";
	static final String COLUMN_DISTRICT = "district";
	static final String COLUMN_PROVINCE = "province";
	static final String COLUMN_LATITUDE = "latitude";
	static final String COLUMN_LONGITUDE = "longitude";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_ADDRESS,COLUMN_DISTRICT,COLUMN_PROVINCE,COLUMN_LATITUDE,COLUMN_LONGITUDE,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_ADDRESS,COLUMN_DISTRICT,COLUMN_PROVINCE,COLUMN_LATITUDE,COLUMN_LONGITUDE};
	
	
}


