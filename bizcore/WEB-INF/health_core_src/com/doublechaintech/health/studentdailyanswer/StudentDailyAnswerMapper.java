
package com.doublechaintech.health.studentdailyanswer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

public class StudentDailyAnswerMapper extends BaseRowMapper<StudentDailyAnswer>{
	
	protected StudentDailyAnswer internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		StudentDailyAnswer studentDailyAnswer = getStudentDailyAnswer();		
		 		
 		setId(studentDailyAnswer, rs, rowNumber); 		
 		setStudentHealthSurvey(studentDailyAnswer, rs, rowNumber); 		
 		setQuestion(studentDailyAnswer, rs, rowNumber); 		
 		setAnswer(studentDailyAnswer, rs, rowNumber); 		
 		setCreateTime(studentDailyAnswer, rs, rowNumber); 		
 		setLastUpdateTime(studentDailyAnswer, rs, rowNumber); 		
 		setCq(studentDailyAnswer, rs, rowNumber); 		
 		setVersion(studentDailyAnswer, rs, rowNumber);

		return studentDailyAnswer;
	}
	
	protected StudentDailyAnswer getStudentDailyAnswer(){
		return new StudentDailyAnswer();
	}		
		
	protected void setId(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(StudentDailyAnswerTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentDailyAnswer.setId(id);
	}
		 		
 	protected void setStudentHealthSurvey(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
 		String studentHealthSurveyId = rs.getString(StudentDailyAnswerTable.COLUMN_STUDENT_HEALTH_SURVEY);
 		if( studentHealthSurveyId == null){
 			return;
 		}
 		if( studentHealthSurveyId.isEmpty()){
 			return;
 		}
 		StudentHealthSurvey studentHealthSurvey = studentDailyAnswer.getStudentHealthSurvey();
 		if( studentHealthSurvey != null ){
 			//if the root object 'studentDailyAnswer' already have the property, just set the id for it;
 			studentHealthSurvey.setId(studentHealthSurveyId);
 			
 			return;
 		}
 		studentDailyAnswer.setStudentHealthSurvey(createEmptyStudentHealthSurvey(studentHealthSurveyId));
 	}
 	 		
 	protected void setQuestion(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
 		String dailySurveyQuestionId = rs.getString(StudentDailyAnswerTable.COLUMN_QUESTION);
 		if( dailySurveyQuestionId == null){
 			return;
 		}
 		if( dailySurveyQuestionId.isEmpty()){
 			return;
 		}
 		DailySurveyQuestion dailySurveyQuestion = studentDailyAnswer.getQuestion();
 		if( dailySurveyQuestion != null ){
 			//if the root object 'studentDailyAnswer' already have the property, just set the id for it;
 			dailySurveyQuestion.setId(dailySurveyQuestionId);
 			
 			return;
 		}
 		studentDailyAnswer.setQuestion(createEmptyQuestion(dailySurveyQuestionId));
 	}
 	
	protected void setAnswer(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String answer = rs.getString(StudentDailyAnswerTable.COLUMN_ANSWER);
		if(answer == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentDailyAnswer.setAnswer(answer);
	}
		
	protected void setCreateTime(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(StudentDailyAnswerTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentDailyAnswer.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setLastUpdateTime(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date lastUpdateTime = rs.getTimestamp(StudentDailyAnswerTable.COLUMN_LAST_UPDATE_TIME);
		if(lastUpdateTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentDailyAnswer.setLastUpdateTime(convertToDateTime(lastUpdateTime));
	}
		 		
 	protected void setCq(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(StudentDailyAnswerTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = studentDailyAnswer.getCq();
 		if( changeRequest != null ){
 			//if the root object 'studentDailyAnswer' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		studentDailyAnswer.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(StudentDailyAnswer studentDailyAnswer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(StudentDailyAnswerTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		studentDailyAnswer.setVersion(version);
	}
		
		

 	protected StudentHealthSurvey  createEmptyStudentHealthSurvey(String studentHealthSurveyId){
 		StudentHealthSurvey studentHealthSurvey = new StudentHealthSurvey();
 		studentHealthSurvey.setId(studentHealthSurveyId);
 		studentHealthSurvey.setVersion(Integer.MAX_VALUE);
 		return studentHealthSurvey;
 	}
 	
 	protected DailySurveyQuestion  createEmptyQuestion(String dailySurveyQuestionId){
 		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
 		dailySurveyQuestion.setId(dailySurveyQuestionId);
 		dailySurveyQuestion.setVersion(Integer.MAX_VALUE);
 		return dailySurveyQuestion;
 	}
 	
 	protected ChangeRequest  createEmptyCq(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


