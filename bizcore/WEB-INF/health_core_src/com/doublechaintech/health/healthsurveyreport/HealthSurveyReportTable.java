
package com.doublechaintech.health.healthsurveyreport;
import com.doublechaintech.health.AccessKey;


public class HealthSurveyReportTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="health_survey_report_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_SURVEY_NAME = "survey_name";
	static final String COLUMN_SURVEY_TIME = "survey_time";
	static final String COLUMN_TEACHER_NAME = "teacher_name";
	static final String COLUMN_SCHOOL = "school";
	static final String COLUMN_SCHOOL_CLASS = "school_class";
	static final String COLUMN_STUDENT_NAME = "student_name";
	static final String COLUMN_STUDENT_NUMBER = "student_number";
	static final String COLUMN_GUARDIAN_NAME = "guardian_name";
	static final String COLUMN_GUARDIAN_MOBILE = "guardian_mobile";
	static final String COLUMN_STUDENT = "student";
	static final String COLUMN_TEACHER = "teacher";
	static final String COLUMN_SURVEY = "survey";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_SURVEY_NAME,COLUMN_SURVEY_TIME,COLUMN_TEACHER_NAME,COLUMN_SCHOOL,COLUMN_SCHOOL_CLASS,COLUMN_STUDENT_NAME,COLUMN_STUDENT_NUMBER,COLUMN_GUARDIAN_NAME,COLUMN_GUARDIAN_MOBILE,COLUMN_STUDENT,COLUMN_TEACHER,COLUMN_SURVEY,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_SURVEY_NAME,COLUMN_SURVEY_TIME,COLUMN_TEACHER_NAME,COLUMN_SCHOOL,COLUMN_SCHOOL_CLASS,COLUMN_STUDENT_NAME,COLUMN_STUDENT_NUMBER,COLUMN_GUARDIAN_NAME,COLUMN_GUARDIAN_MOBILE,COLUMN_STUDENT,COLUMN_TEACHER,COLUMN_SURVEY};
	
	
}


