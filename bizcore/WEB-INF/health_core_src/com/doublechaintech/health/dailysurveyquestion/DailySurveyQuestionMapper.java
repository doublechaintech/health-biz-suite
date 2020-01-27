
package com.doublechaintech.health.dailysurveyquestion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.classquestion.ClassQuestion;

public class DailySurveyQuestionMapper extends BaseRowMapper<DailySurveyQuestion>{
	
	protected DailySurveyQuestion internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		DailySurveyQuestion dailySurveyQuestion = getDailySurveyQuestion();		
		 		
 		setId(dailySurveyQuestion, rs, rowNumber); 		
 		setTopic(dailySurveyQuestion, rs, rowNumber); 		
 		setQuestionType(dailySurveyQuestion, rs, rowNumber); 		
 		setOptionA(dailySurveyQuestion, rs, rowNumber); 		
 		setOptionB(dailySurveyQuestion, rs, rowNumber); 		
 		setOptionC(dailySurveyQuestion, rs, rowNumber); 		
 		setOptionD(dailySurveyQuestion, rs, rowNumber); 		
 		setClassDailyHealthSurvey(dailySurveyQuestion, rs, rowNumber); 		
 		setClassQuestion(dailySurveyQuestion, rs, rowNumber); 		
 		setVersion(dailySurveyQuestion, rs, rowNumber);

		return dailySurveyQuestion;
	}
	
	protected DailySurveyQuestion getDailySurveyQuestion(){
		return new DailySurveyQuestion();
	}		
		
	protected void setId(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(DailySurveyQuestionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setId(id);
	}
		
	protected void setTopic(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String topic = rs.getString(DailySurveyQuestionTable.COLUMN_TOPIC);
		if(topic == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setTopic(topic);
	}
		 		
 	protected void setQuestionType(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String questionTypeId = rs.getString(DailySurveyQuestionTable.COLUMN_QUESTION_TYPE);
 		if( questionTypeId == null){
 			return;
 		}
 		if( questionTypeId.isEmpty()){
 			return;
 		}
 		QuestionType questionType = dailySurveyQuestion.getQuestionType();
 		if( questionType != null ){
 			//if the root object 'dailySurveyQuestion' already have the property, just set the id for it;
 			questionType.setId(questionTypeId);
 			
 			return;
 		}
 		dailySurveyQuestion.setQuestionType(createEmptyQuestionType(questionTypeId));
 	}
 	
	protected void setOptionA(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionA = rs.getString(DailySurveyQuestionTable.COLUMN_OPTION_A);
		if(optionA == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setOptionA(optionA);
	}
		
	protected void setOptionB(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionB = rs.getString(DailySurveyQuestionTable.COLUMN_OPTION_B);
		if(optionB == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setOptionB(optionB);
	}
		
	protected void setOptionC(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionC = rs.getString(DailySurveyQuestionTable.COLUMN_OPTION_C);
		if(optionC == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setOptionC(optionC);
	}
		
	protected void setOptionD(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionD = rs.getString(DailySurveyQuestionTable.COLUMN_OPTION_D);
		if(optionD == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setOptionD(optionD);
	}
		 		
 	protected void setClassDailyHealthSurvey(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String classDailyHealthSurveyId = rs.getString(DailySurveyQuestionTable.COLUMN_CLASS_DAILY_HEALTH_SURVEY);
 		if( classDailyHealthSurveyId == null){
 			return;
 		}
 		if( classDailyHealthSurveyId.isEmpty()){
 			return;
 		}
 		ClassDailyHealthSurvey classDailyHealthSurvey = dailySurveyQuestion.getClassDailyHealthSurvey();
 		if( classDailyHealthSurvey != null ){
 			//if the root object 'dailySurveyQuestion' already have the property, just set the id for it;
 			classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 			
 			return;
 		}
 		dailySurveyQuestion.setClassDailyHealthSurvey(createEmptyClassDailyHealthSurvey(classDailyHealthSurveyId));
 	}
 	 		
 	protected void setClassQuestion(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String classQuestionId = rs.getString(DailySurveyQuestionTable.COLUMN_CLASS_QUESTION);
 		if( classQuestionId == null){
 			return;
 		}
 		if( classQuestionId.isEmpty()){
 			return;
 		}
 		ClassQuestion classQuestion = dailySurveyQuestion.getClassQuestion();
 		if( classQuestion != null ){
 			//if the root object 'dailySurveyQuestion' already have the property, just set the id for it;
 			classQuestion.setId(classQuestionId);
 			
 			return;
 		}
 		dailySurveyQuestion.setClassQuestion(createEmptyClassQuestion(classQuestionId));
 	}
 	
	protected void setVersion(DailySurveyQuestion dailySurveyQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(DailySurveyQuestionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		dailySurveyQuestion.setVersion(version);
	}
		
		

 	protected QuestionType  createEmptyQuestionType(String questionTypeId){
 		QuestionType questionType = new QuestionType();
 		questionType.setId(questionTypeId);
 		questionType.setVersion(Integer.MAX_VALUE);
 		return questionType;
 	}
 	
 	protected ClassDailyHealthSurvey  createEmptyClassDailyHealthSurvey(String classDailyHealthSurveyId){
 		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
 		classDailyHealthSurvey.setId(classDailyHealthSurveyId);
 		classDailyHealthSurvey.setVersion(Integer.MAX_VALUE);
 		return classDailyHealthSurvey;
 	}
 	
 	protected ClassQuestion  createEmptyClassQuestion(String classQuestionId){
 		ClassQuestion classQuestion = new ClassQuestion();
 		classQuestion.setId(classQuestionId);
 		classQuestion.setVersion(Integer.MAX_VALUE);
 		return classQuestion;
 	}
 	
}


