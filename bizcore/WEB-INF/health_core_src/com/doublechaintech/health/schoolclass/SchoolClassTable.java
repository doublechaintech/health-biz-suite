
package com.doublechaintech.health.schoolclass;
import com.doublechaintech.health.AccessKey;


public class SchoolClassTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="school_class_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_CLASS_TEACHER = "class_teacher";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_PLATFORM = "platform";
	static final String COLUMN_SCHOOLE = "schoole";
	static final String COLUMN_CQ = "cq";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_CLASS_TEACHER,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_SCHOOLE,COLUMN_CQ,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_CLASS_TEACHER,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_SCHOOLE,COLUMN_CQ};
	
	
}


