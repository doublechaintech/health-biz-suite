
package com.doublechaintech.health.studenthealthsurvey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;

public class StudentHealthSurveyMapper extends BaseRowMapper<StudentHealthSurvey>{
	
	protected StudentHealthSurvey internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		StudentHealthSurvey studentHealthSurvey = getStudentHealthSurvey();		
		 		
 		setId(studentHealthSurvey, rs, rowNumber); 		
 		setStudent(studentHealthSurvey, rs, rowNumber); 		
 		setAnswerTime(studentHealthSurvey, rs, rowNumber); 		
 		setSurveyStatus(studentHealthSurvey, rs, rowNumber); 		
 		setSchoolClass(studentHealthSurvey, rs, rowNumber); 		
 		setClassDailyHealthSurvey(studentHealthSurvey, rs, rowNumber); 		
 		setCreateTime(studentHealthSurvey, rs, rowNumber); 		
 		setLastUpdateTime(studentHealthSurvey, rs, rowNumber); 		
 		setCq(studentHealthSurvey, rs, rowNumber); 		
 		setVersion(studentHealthSurvey, rs, rowNumber);

		return studentHealthSurvey;
	}
	
	protected StudentHealthSurvey getStudentHealthSurvey(){
		return new StudentHealthSurvey();
	}		
		
	protected void setId(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(StudentHealthSurveyTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentHealthSurvey.setId(id);
	}
		 		
 	protected void setStudent(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String studentId = rs.getString(StudentHealthSurveyTable.COLUMN_STUDENT);
 		if( studentId == null){
 			return;
 		}
 		if( studentId.isEmpty()){
 			return;
 		}
 		Student student = studentHealthSurvey.getStudent();
 		if( student != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			student.setId(studentId);
 			
 			return;
 		}
 		studentHealthSurvey.setStudent(createEmptyStudent(studentId));
 	}
 	
	protected void setAnswerTime(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date answerTime = rs.getTimestamp(StudentHealthSurveyTable.COLUMN_ANSWER_TIME);
		if(answerTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentHealthSurvey.setAnswerTime(convertToDateTime(answerTime));
	}
		 		
 	protected void setSurveyStatus(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String surveyStatusId = rs.getString(StudentHealthSurveyTable.COLUMN_SURVEY_STATUS);
 		if( surveyStatusId == null){
 			return;
 		}
 		if( surveyStatusId.isEmpty()){
 			return;
 		}
 		SurveyStatus surveyStatus = studentHealthSurvey.getSurveyStatus();
 		if( surveyStatus != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			surveyStatus.setId(surveyStatusId);
 			
 			return;
 		}
 		studentHealthSurvey.setSurveyStatus(createEmptySurveyStatus(surveyStatusId));
 	}
 	 		
 	protected void setSchoolClass(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String schoolClassId = rs.getString(StudentHealthSurveyTable.COLUMN_SCHOOL_CLASS);
 		if( schoolClassId == null){
 			return;
 		}
 		if( schoolClassId.isEmpty()){
 			return;
 		}
 		SchoolClass schoolClass = studentHealthSurvey.getSchoolClass();
 		if( schoolClass != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			schoolClass.setId(schoolClassId);
 			
 			return;
 		}
 		studentHealthSurvey.setSchoolClass(createEmptySchoolClass(schoolClassId));
 	}
 	 		
 	protected void setClassDailyHealthSurvey(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String classDailyHealthSurveyId = rs.getString(StudentHealthSurveyTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY);
 		if( classDailyHealthSurveyId == null){
 			return;
 		}
 		if( classDailyHealthSurveyId.isEmpty()){
 			return;
 		}
 		ClassDailyHealthSurvey classDailyHealthSurvey = studentHealthSurvey.getClassDailyHealthSurvey();
 		if( classDailyHealthSurvey != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 			
 			return;
 		}
 		studentHealthSurvey.setClassDailyHealthSurvey(createEmptyClassDailyHealthSurvey(classDailyHealthSurveyId));
 	}
 	
	protected void setCreateTime(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(StudentHealthSurveyTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentHealthSurvey.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setLastUpdateTime(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(StudentHealthSurveyTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentHealthSurvey.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setCq(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(StudentHealthSurveyTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = studentHealthSurvey.getCq();
 		if( changeRequest != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		studentHealthSurvey.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(StudentHealthSurveyTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentHealthSurvey.setVersion(version);
	}
		
		

 	protected Student  createEmptyStudent(String studentId){
 		Student student = new Student();
 		student.setId(studentId);
 		student.setVersion(Integer.MAX_VALUE);
 		return student;
 	}
 	
 	protected SurveyStatus  createEmptySurveyStatus(String surveyStatusId){
 		SurveyStatus surveyStatus = new SurveyStatus();
 		surveyStatus.setId(surveyStatusId);
 		surveyStatus.setVersion(Integer.MAX_VALUE);
 		return surveyStatus;
 	}
 	
 	protected SchoolClass  createEmptySchoolClass(String schoolClassId){
 		SchoolClass schoolClass = new SchoolClass();
 		schoolClass.setId(schoolClassId);
 		schoolClass.setVersion(Integer.MAX_VALUE);
 		return schoolClass;
 	}
 	
 	protected ClassDailyHealthSurvey  createEmptyClassDailyHealthSurvey(String classDailyHealthSurveyId){
 		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
 		classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 		classDailyHealthSurvey.setVersion(Integer.MAX_VALUE);
 		return classDailyHealthSurvey;
 	}
 	
 	protected ChangeRequest  createEmptyCq(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


