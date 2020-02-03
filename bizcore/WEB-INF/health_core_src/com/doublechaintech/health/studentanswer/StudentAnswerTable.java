
package com.doublechaintech.health.studentanswer;
import com.doublechaintech.health.AccessKey;


public class StudentAnswerTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="student_answer_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_HEALTH_SURVEY_REPORT = "health_survey_report";
	static final String COLUMN_DAILY_ANSWER = "daily_answer";
	static final String COLUMN_QUESTION_TOPIC = "question_topic";
	static final String COLUMN_ANSWER = "answer";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_HEALTH_SURVEY_REPORT,COLUMN_DAILY_ANSWER,COLUMN_QUESTION_TOPIC,COLUMN_ANSWER,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_HEALTH_SURVEY_REPORT,COLUMN_DAILY_ANSWER,COLUMN_QUESTION_TOPIC,COLUMN_ANSWER};
	
	
}


