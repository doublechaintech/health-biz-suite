
package com.doublechaintech.health.question;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.questiontype.QuestionType;

public class QuestionMapper extends BaseRowMapper<Question>{
	
	protected Question internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Question question = getQuestion();		
		 		
 		setId(question, rs, rowNumber); 		
 		setTopic(question, rs, rowNumber); 		
 		setQuestionType(question, rs, rowNumber); 		
 		setOptionA(question, rs, rowNumber); 		
 		setOptionB(question, rs, rowNumber); 		
 		setOptionC(question, rs, rowNumber); 		
 		setOptionD(question, rs, rowNumber); 		
 		setPlatform(question, rs, rowNumber); 		
 		setVersion(question, rs, rowNumber);

		return question;
	}
	
	protected Question getQuestion(){
		return new Question();
	}		
		
	protected void setId(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(QuestionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setId(id);
	}
		
	protected void setTopic(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String topic = rs.getString(QuestionTable.COLUMN_TOPIC);
		if(topic == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setTopic(topic);
	}
		 		
 	protected void setQuestionType(Question question, ResultSet rs, int rowNumber) throws SQLException{
 		String questionTypeId = rs.getString(QuestionTable.COLUMN_QUESTION_TYPE);
 		if( questionTypeId == null){
 			return;
 		}
 		if( questionTypeId.isEmpty()){
 			return;
 		}
 		QuestionType questionType = question.getQuestionType();
 		if( questionType != null ){
 			//if the root object 'question' already have the property, just set the id for it;
 			questionType.setId(questionTypeId);
 			
 			return;
 		}
 		question.setQuestionType(createEmptyQuestionType(questionTypeId));
 	}
 	
	protected void setOptionA(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionA = rs.getString(QuestionTable.COLUMN_OPTION_A);
		if(optionA == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setOptionA(optionA);
	}
		
	protected void setOptionB(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionB = rs.getString(QuestionTable.COLUMN_OPTION_B);
		if(optionB == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setOptionB(optionB);
	}
		
	protected void setOptionC(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionC = rs.getString(QuestionTable.COLUMN_OPTION_C);
		if(optionC == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setOptionC(optionC);
	}
		
	protected void setOptionD(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionD = rs.getString(QuestionTable.COLUMN_OPTION_D);
		if(optionD == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setOptionD(optionD);
	}
		 		
 	protected void setPlatform(Question question, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(QuestionTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = question.getPlatform();
 		if( platform != null ){
 			//if the root object 'question' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		question.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(Question question, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(QuestionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		question.setVersion(version);
	}
		
		

 	protected QuestionType  createEmptyQuestionType(String questionTypeId){
 		QuestionType questionType = new QuestionType();
 		questionType.setId(questionTypeId);
 		questionType.setVersion(Integer.MAX_VALUE);
 		return questionType;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


