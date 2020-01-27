
package com.doublechaintech.health.studenthealthsurvey;
import com.doublechaintech.health.AccessKey;


public class StudentHealthSurveyTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="student_health_survey_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_STUDENT = "student";
	static final String COLUMN_ANSWER_TIME = "answer_time";
	static final String COLUMN_SURVEY_STATUS = "survey_status";
	static final String COLUMN_TEACHER = "teacher";
	static final String COLUMN_CLASS_DAILY_HEALTH_SURVEY = "class_daily_health_survey";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_LAST_UPDATE_TIME = "last_update_time";
	static final String COLUMN_CHANGE_REQUEST = "change_request";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_STUDENT,COLUMN_ANSWER_TIME,COLUMN_SURVEY_STATUS,COLUMN_TEACHER,COLUMN_CLASS_DAILY_HEALTH_SURVEY,COLUMN_CREATE_TIME,COLUMN_LAST_UPDATE_TIME,COLUMN_CHANGE_REQUEST,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_STUDENT,COLUMN_ANSWER_TIME,COLUMN_SURVEY_STATUS,COLUMN_TEACHER,COLUMN_CLASS_DAILY_HEALTH_SURVEY,COLUMN_CREATE_TIME,COLUMN_LAST_UPDATE_TIME,COLUMN_CHANGE_REQUEST};
	
	
}


