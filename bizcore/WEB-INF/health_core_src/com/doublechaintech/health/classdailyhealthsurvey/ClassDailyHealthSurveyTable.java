
package com.doublechaintech.health.classdailyhealthsurvey;
import com.doublechaintech.health.AccessKey;


public class ClassDailyHealthSurveyTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="class_daily_health_survey_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_TEACHER = "teacher";
	static final String COLUMN_SURVEY_TIME = "survey_time";
	static final String COLUMN_CREATOR = "creator";
	static final String COLUMN_CQ = "cq";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_TEACHER,COLUMN_SURVEY_TIME,COLUMN_CREATOR,COLUMN_CQ,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_TEACHER,COLUMN_SURVEY_TIME,COLUMN_CREATOR,COLUMN_CQ};
	
	
}


