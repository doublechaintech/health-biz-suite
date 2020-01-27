
package com.doublechaintech.health.studenthealthsurvey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
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
 		setTeacher(studentHealthSurvey, rs, rowNumber); 		
 		setClassDailyHealthSurvey(studentHealthSurvey, rs, rowNumber); 		
 		setCreateTime(studentHealthSurvey, rs, rowNumber); 		
 		setLastUpdateTime(studentHealthSurvey, rs, rowNumber); 		
 		setChangeRequest(studentHealthSurvey, rs, rowNumber); 		
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
 	 		
 	protected void setTeacher(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String teacherId = rs.getString(StudentHealthSurveyTable.COLUMN_TEACHER);
 		if( teacherId == null){
 			return;
 		}
 		if( teacherId.isEmpty()){
 			return;
 		}
 		Teacher teacher = studentHealthSurvey.getTeacher();
 		if( teacher != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			teacher.setId(teacherId);
 			
 			return;
 		}
 		studentHealthSurvey.setTeacher(createEmptyTeacher(teacherId));
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
		 		
 	protected void setChangeRequest(StudentHealthSurvey studentHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(StudentHealthSurveyTable.COLUMN_CHANGE_REQUEST);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = studentHealthSurvey.getChangeRequest();
 		if( changeRequest != null ){
 			//if the root object 'studentHealthSurvey' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		studentHealthSurvey.setChangeRequest(createEmptyChangeRequest(changeRequestId));
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
 	
 	protected Teacher  createEmptyTeacher(String teacherId){
 		Teacher teacher = new Teacher();
 		teacher.setId(teacherId);
 		teacher.setVersion(Integer.MAX_VALUE);
 		return teacher;
 	}
 	
 	protected ClassDailyHealthSurvey  createEmptyClassDailyHealthSurvey(String classDailyHealthSurveyId){
 		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
 		classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 		classDailyHealthSurvey.setVersion(Integer.MAX_VALUE);
 		return classDailyHealthSurvey;
 	}
 	
 	protected ChangeRequest  createEmptyChangeRequest(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


