
package com.doublechaintech.health.teacher;
import com.doublechaintech.health.AccessKey;


public class TeacherTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="teacher_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_MOBILE = "mobile";
	static final String COLUMN_SCHOOL = "school";
	static final String COLUMN_SCHOOL_CLASS = "school_class";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_PLATFORM = "platform";
	static final String COLUMN_CQ = "cq";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_MOBILE,COLUMN_SCHOOL,COLUMN_SCHOOL_CLASS,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_CQ,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_MOBILE,COLUMN_SCHOOL,COLUMN_SCHOOL_CLASS,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_CQ};
	
	
}


