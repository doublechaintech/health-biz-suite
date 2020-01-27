
package com.doublechaintech.health.classquestion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;

public class ClassQuestionMapper extends BaseRowMapper<ClassQuestion>{
	
	protected ClassQuestion internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ClassQuestion classQuestion = getClassQuestion();		
		 		
 		setId(classQuestion, rs, rowNumber); 		
 		setTopic(classQuestion, rs, rowNumber); 		
 		setQuestionType(classQuestion, rs, rowNumber); 		
 		setOptionA(classQuestion, rs, rowNumber); 		
 		setOptionB(classQuestion, rs, rowNumber); 		
 		setOptionC(classQuestion, rs, rowNumber); 		
 		setOptionD(classQuestion, rs, rowNumber); 		
 		setQuestionSource(classQuestion, rs, rowNumber); 		
 		setCreator(classQuestion, rs, rowNumber); 		
 		setCq(classQuestion, rs, rowNumber); 		
 		setVersion(classQuestion, rs, rowNumber);

		return classQuestion;
	}
	
	protected ClassQuestion getClassQuestion(){
		return new ClassQuestion();
	}		
		
	protected void setId(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(ClassQuestionTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setId(id);
	}
		
	protected void setTopic(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String topic = rs.getString(ClassQuestionTable.COLUMN_TOPIC);
		if(topic == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setTopic(topic);
	}
		 		
 	protected void setQuestionType(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String questionTypeId = rs.getString(ClassQuestionTable.COLUMN_QUESTION_TYPE);
 		if( questionTypeId == null){
 			return;
 		}
 		if( questionTypeId.isEmpty()){
 			return;
 		}
 		QuestionType questionType = classQuestion.getQuestionType();
 		if( questionType != null ){
 			//if the root object 'classQuestion' already have the property, just set the id for it;
 			questionType.setId(questionTypeId);
 			
 			return;
 		}
 		classQuestion.setQuestionType(createEmptyQuestionType(questionTypeId));
 	}
 	
	protected void setOptionA(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionA = rs.getString(ClassQuestionTable.COLUMN_OPTION_A);
		if(optionA == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setOptionA(optionA);
	}
		
	protected void setOptionB(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionB = rs.getString(ClassQuestionTable.COLUMN_OPTION_B);
		if(optionB == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setOptionB(optionB);
	}
		
	protected void setOptionC(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionC = rs.getString(ClassQuestionTable.COLUMN_OPTION_C);
		if(optionC == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setOptionC(optionC);
	}
		
	protected void setOptionD(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String optionD = rs.getString(ClassQuestionTable.COLUMN_OPTION_D);
		if(optionD == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setOptionD(optionD);
	}
		 		
 	protected void setQuestionSource(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String questionSourceId = rs.getString(ClassQuestionTable.COLUMN_QUESTION_SOURCE);
 		if( questionSourceId == null){
 			return;
 		}
 		if( questionSourceId.isEmpty()){
 			return;
 		}
 		QuestionSource questionSource = classQuestion.getQuestionSource();
 		if( questionSource != null ){
 			//if the root object 'classQuestion' already have the property, just set the id for it;
 			questionSource.setId(questionSourceId);
 			
 			return;
 		}
 		classQuestion.setQuestionSource(createEmptyQuestionSource(questionSourceId));
 	}
 	 		
 	protected void setCreator(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String wechatUserId = rs.getString(ClassQuestionTable.COLUMN_CREATOR);
 		if( wechatUserId == null){
 			return;
 		}
 		if( wechatUserId.isEmpty()){
 			return;
 		}
 		WechatUser wechatUser = classQuestion.getCreator();
 		if( wechatUser != null ){
 			//if the root object 'classQuestion' already have the property, just set the id for it;
 			wechatUser.setId(wechatUserId);
 			
 			return;
 		}
 		classQuestion.setCreator(createEmptyCreator(wechatUserId));
 	}
 	 		
 	protected void setCq(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(ClassQuestionTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = classQuestion.getCq();
 		if( changeRequest != null ){
 			//if the root object 'classQuestion' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		classQuestion.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(ClassQuestion classQuestion, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(ClassQuestionTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		classQuestion.setVersion(version);
	}
		
		

 	protected QuestionType  createEmptyQuestionType(String questionTypeId){
 		QuestionType questionType = new QuestionType();
 		questionType.setId(questionTypeId);
 		questionType.setVersion(Integer.MAX_VALUE);
 		return questionType;
 	}
 	
 	protected QuestionSource  createEmptyQuestionSource(String questionSourceId){
 		QuestionSource questionSource = new QuestionSource();
 		questionSource.setId(questionSourceId);
 		questionSource.setVersion(Integer.MAX_VALUE);
 		return questionSource;
 	}
 	
 	protected WechatUser  createEmptyCreator(String wechatUserId){
 		WechatUser wechatUser = new WechatUser();
 		wechatUser.setId(wechatUserId);
 		wechatUser.setVersion(Integer.MAX_VALUE);
 		return wechatUser;
 	}
 	
 	protected ChangeRequest  createEmptyCq(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


