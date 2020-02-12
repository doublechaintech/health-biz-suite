
package com.doublechaintech.health.studentdailyanswer;
import com.doublechaintech.health.AccessKey;


public class StudentDailyAnswerTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="student_daily_answer_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_STUDENT_HEALTH_SURVEY = "student_health_survey";
	static final String COLUMN_QUESTION = "question";
	static final String COLUMN_ANSWER = "answer";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_LAST_UPDATE_TIME = "last_update_time";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_STUDENT_HEALTH_SURVEY,COLUMN_QUESTION,COLUMN_ANSWER,COLUMN_CREATE_TIME,COLUMN_LAST_UPDATE_TIME,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_STUDENT_HEALTH_SURVEY,COLUMN_QUESTION,COLUMN_ANSWER,COLUMN_CREATE_TIME,COLUMN_LAST_UPDATE_TIME};
	
	
}


