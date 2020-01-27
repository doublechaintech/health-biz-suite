
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
	static final String COLUMN_NAME = "name";
	static final String COLUMN_GENDER = "gender";
	static final String COLUMN_GUARDIAN = "guardian";
	static final String COLUMN_SCHOOL_CLASS = "school_class";
	static final String COLUMN_STUDENT_ID = "student_id";
	static final String COLUMN_CQ = "cq";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_GENDER,COLUMN_GUARDIAN,COLUMN_SCHOOL_CLASS,COLUMN_STUDENT_ID,COLUMN_CQ,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_GENDER,COLUMN_GUARDIAN,COLUMN_SCHOOL_CLASS,COLUMN_STUDENT_ID,COLUMN_CQ};
	
	
}


