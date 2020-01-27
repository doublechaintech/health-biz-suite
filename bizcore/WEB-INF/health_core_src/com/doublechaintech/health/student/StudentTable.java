
package com.doublechaintech.health.student;
import com.doublechaintech.health.AccessKey;


public class StudentTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="student_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_STUDENT_NAME = "student_name";
	static final String COLUMN_STUDENT_ID = "student_id";
	static final String COLUMN_GUARDIAN_NAME = "guardian_name";
	static final String COLUMN_GUARDIAN_MOBILE = "guardian_mobile";
	static final String COLUMN_ADDRESS = "address";
	static final String COLUMN_USER = "user";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_PLATFORM = "platform";
	static final String COLUMN_CQ = "cq";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_STUDENT_NAME,COLUMN_STUDENT_ID,COLUMN_GUARDIAN_NAME,COLUMN_GUARDIAN_MOBILE,COLUMN_ADDRESS,COLUMN_USER,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_CQ,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_STUDENT_NAME,COLUMN_STUDENT_ID,COLUMN_GUARDIAN_NAME,COLUMN_GUARDIAN_MOBILE,COLUMN_ADDRESS,COLUMN_USER,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_CQ};
	
	
}


