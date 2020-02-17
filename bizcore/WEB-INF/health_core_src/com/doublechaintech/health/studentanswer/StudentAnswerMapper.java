
package com.doublechaintech.health.studentanswer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

public class StudentAnswerMapper extends BaseRowMapper<StudentAnswer>{
	
	protected StudentAnswer internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		StudentAnswer studentAnswer = getStudentAnswer();		
		 		
 		setId(studentAnswer, rs, rowNumber); 		
 		setHealthSurveyReport(studentAnswer, rs, rowNumber); 		
 		setDailyAnswer(studentAnswer, rs, rowNumber); 		
 		setQuestionTopic(studentAnswer, rs, rowNumber); 		
 		setAnswer(studentAnswer, rs, rowNumber); 		
 		setVersion(studentAnswer, rs, rowNumber);

		return studentAnswer;
	}
	
	protected StudentAnswer getStudentAnswer(){
		return new StudentAnswer();
	}		
		
	protected void setId(StudentAnswer studentAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(StudentAnswerTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentAnswer.setId(id);
	}
		 		
 	protected void setHealthSurveyReport(StudentAnswer studentAnswer, ResultSet rs, int rowNumber) throws SQLException{
 		String healthSurveyReportId = rs.getString(StudentAnswerTable.COLUMN_HEALTH_SURVEY_REPORT);
 		if( healthSurveyReportId == null){
 			return;
 		}
 		if( healthSurveyReportId.isEmpty()){
 			return;
 		}
 		HealthSurveyReport healthSurveyReport = studentAnswer.getHealthSurveyReport();
 		if( healthSurveyReport != null ){
 			//if the root object 'studentAnswer' already have the property, just set the id for it;
 			healthSurveyReport.setId(healthSurveyReportId);
 			
 			return;
 		}
 		studentAnswer.setHealthSurveyReport(createEmptyHealthSurveyReport(healthSurveyReportId));
 	}
 	 		
 	protected void setDailyAnswer(StudentAnswer studentAnswer, ResultSet rs, int rowNumber) throws SQLException{
 		String studentDailyAnswerId = rs.getString(StudentAnswerTable.COLUMN_DAILY_ANSWER);
 		if( studentDailyAnswerId == null){
 			return;
 		}
 		if( studentDailyAnswerId.isEmpty()){
 			return;
 		}
 		StudentDailyAnswer studentDailyAnswer = studentAnswer.getDailyAnswer();
 		if( studentDailyAnswer != null ){
 			//if the root object 'studentAnswer' already have the property, just set the id for it;
 			studentDailyAnswer.setId(studentDailyAnswerId);
 			
 			return;
 		}
 		studentAnswer.setDailyAnswer(createEmptyDailyAnswer(studentDailyAnswerId));
 	}
 	
	protected void setQuestionTopic(StudentAnswer studentAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String questionTopic = rs.getString(StudentAnswerTable.COLUMN_QUESTION_TOPIC);
		
		if(questionTopic == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentAnswer.setQuestionTopic(questionTopic);
	}
		
	protected void setAnswer(StudentAnswer studentAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String answer = rs.getString(StudentAnswerTable.COLUMN_ANSWER);
		
		if(answer == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentAnswer.setAnswer(answer);
	}
		
	protected void setVersion(StudentAnswer studentAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(StudentAnswerTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentAnswer.setVersion(version);
	}
		
		

 	protected HealthSurveyReport  createEmptyHealthSurveyReport(String healthSurveyReportId){
 		HealthSurveyReport healthSurveyReport = new HealthSurveyReport();
 		healthSurveyReport.setId(healthSurveyReportId);
 		healthSurveyReport.setVersion(Integer.MAX_VALUE);
 		return healthSurveyReport;
 	}
 	
 	protected StudentDailyAnswer  createEmptyDailyAnswer(String studentDailyAnswerId){
 		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
 		studentDailyAnswer.setId(studentDailyAnswerId);
 		studentDailyAnswer.setVersion(Integer.MAX_VALUE);
 		return studentDailyAnswer;
 	}
 	
}


