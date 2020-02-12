
package com.doublechaintech.health.healthsurveyreport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;

public class HealthSurveyReportMapper extends BaseRowMapper<HealthSurveyReport>{
	
	protected HealthSurveyReport internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		HealthSurveyReport healthSurveyReport = getHealthSurveyReport();		
		 		
 		setId(healthSurveyReport, rs, rowNumber); 		
 		setSurveyName(healthSurveyReport, rs, rowNumber); 		
 		setSurveyTime(healthSurveyReport, rs, rowNumber); 		
 		setTeacherName(healthSurveyReport, rs, rowNumber); 		
 		setSchool(healthSurveyReport, rs, rowNumber); 		
 		setSchoolClass(healthSurveyReport, rs, rowNumber); 		
 		setStudentName(healthSurveyReport, rs, rowNumber); 		
 		setStudentNumber(healthSurveyReport, rs, rowNumber); 		
 		setGuardianName(healthSurveyReport, rs, rowNumber); 		
 		setGuardianMobile(healthSurveyReport, rs, rowNumber); 		
 		setStudent(healthSurveyReport, rs, rowNumber); 		
 		setTeacher(healthSurveyReport, rs, rowNumber); 		
 		setSurvey(healthSurveyReport, rs, rowNumber); 		
 		setVersion(healthSurveyReport, rs, rowNumber);

		return healthSurveyReport;
	}
	
	protected HealthSurveyReport getHealthSurveyReport(){
		return new HealthSurveyReport();
	}		
		
	protected void setId(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(HealthSurveyReportTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setId(id);
	}
		
	protected void setSurveyName(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String surveyName = rs.getString(HealthSurveyReportTable.COLUMN_SURVEY_NAME);
		
		if(surveyName == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setSurveyName(surveyName);
	}
		
	protected void setSurveyTime(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date surveyTime = rs.getTimestamp(HealthSurveyReportTable.COLUMN_SURVEY_TIME);
		
		if(surveyTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setSurveyTime(convertToDateTime(surveyTime));
	}
		
	protected void setTeacherName(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String teacherName = rs.getString(HealthSurveyReportTable.COLUMN_TEACHER_NAME);
		
		if(teacherName == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setTeacherName(teacherName);
	}
		
	protected void setSchool(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String school = rs.getString(HealthSurveyReportTable.COLUMN_SCHOOL);
		
		if(school == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setSchool(school);
	}
		
	protected void setSchoolClass(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String schoolClass = rs.getString(HealthSurveyReportTable.COLUMN_SCHOOL_CLASS);
		
		if(schoolClass == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setSchoolClass(schoolClass);
	}
		
	protected void setStudentName(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String studentName = rs.getString(HealthSurveyReportTable.COLUMN_STUDENT_NAME);
		
		if(studentName == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setStudentName(studentName);
	}
		
	protected void setStudentNumber(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String studentNumber = rs.getString(HealthSurveyReportTable.COLUMN_STUDENT_NUMBER);
		
		if(studentNumber == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setStudentNumber(studentNumber);
	}
		
	protected void setGuardianName(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String guardianName = rs.getString(HealthSurveyReportTable.COLUMN_GUARDIAN_NAME);
		
		if(guardianName == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setGuardianName(guardianName);
	}
		
	protected void setGuardianMobile(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String guardianMobile = rs.getString(HealthSurveyReportTable.COLUMN_GUARDIAN_MOBILE);
		
		if(guardianMobile == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setGuardianMobile(guardianMobile);
	}
		 		
 	protected void setStudent(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
 		String studentId = rs.getString(HealthSurveyReportTable.COLUMN_STUDENT);
 		if( studentId == null){
 			return;
 		}
 		if( studentId.isEmpty()){
 			return;
 		}
 		Student student = healthSurveyReport.getStudent();
 		if( student != null ){
 			//if the root object 'healthSurveyReport' already have the property, just set the id for it;
 			student.setId(studentId);
 			
 			return;
 		}
 		healthSurveyReport.setStudent(createEmptyStudent(studentId));
 	}
 	 		
 	protected void setTeacher(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
 		String teacherId = rs.getString(HealthSurveyReportTable.COLUMN_TEACHER);
 		if( teacherId == null){
 			return;
 		}
 		if( teacherId.isEmpty()){
 			return;
 		}
 		Teacher teacher = healthSurveyReport.getTeacher();
 		if( teacher != null ){
 			//if the root object 'healthSurveyReport' already have the property, just set the id for it;
 			teacher.setId(teacherId);
 			
 			return;
 		}
 		healthSurveyReport.setTeacher(createEmptyTeacher(teacherId));
 	}
 	 		
 	protected void setSurvey(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
 		String classDailyHealthSurveyId = rs.getString(HealthSurveyReportTable.COLUMN_SURVEY);
 		if( classDailyHealthSurveyId == null){
 			return;
 		}
 		if( classDailyHealthSurveyId.isEmpty()){
 			return;
 		}
 		ClassDailyHealthSurvey classDailyHealthSurvey = healthSurveyReport.getSurvey();
 		if( classDailyHealthSurvey != null ){
 			//if the root object 'healthSurveyReport' already have the property, just set the id for it;
 			classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 			
 			return;
 		}
 		healthSurveyReport.setSurvey(createEmptySurvey(classDailyHealthSurveyId));
 	}
 	
	protected void setVersion(HealthSurveyReport healthSurveyReport, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(HealthSurveyReportTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		healthSurveyReport.setVersion(version);
	}
		
		

 	protected Student  createEmptyStudent(String studentId){
 		Student student = new Student();
 		student.setId(studentId);
 		student.setVersion(Integer.MAX_VALUE);
 		return student;
 	}
 	
 	protected Teacher  createEmptyTeacher(String teacherId){
 		Teacher teacher = new Teacher();
 		teacher.setId(teacherId);
 		teacher.setVersion(Integer.MAX_VALUE);
 		return teacher;
 	}
 	
 	protected ClassDailyHealthSurvey  createEmptySurvey(String classDailyHealthSurveyId){
 		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
 		classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 		classDailyHealthSurvey.setVersion(Integer.MAX_VALUE);
 		return classDailyHealthSurvey;
 	}
 	
}


