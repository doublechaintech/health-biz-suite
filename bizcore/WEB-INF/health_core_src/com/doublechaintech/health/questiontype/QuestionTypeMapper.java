
package com.doublechaintech.health.questiontype;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;

public class QuestionTypeMapper extends BaseRowMapper<QuestionType>{
	
	protected QuestionType internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		QuestionType questionType = getQuestionType();		
		 		
 		setId(questionType, rs, rowNumber); 		
 		setName(questionType, rs, rowNumber); 		
 		setCode(questionType, rs, rowNumber); 		
 		setPlatform(questionType, rs, rowNumber); 		
 		setVersion(questionType, rs, rowNumber);

		return questionType;
	}
	
	protected QuestionType getQuestionType(){
		return new QuestionType();
	}		
		
	protected void setId(QuestionType questionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(QuestionTypeTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionType.setId(id);
	}
		
	protected void setName(QuestionType questionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(QuestionTypeTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionType.setName(name);
	}
		
	protected void setCode(QuestionType questionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String code = rs.getString(QuestionTypeTable.COLUMN_CODE);
		
		if(code == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionType.setCode(code);
	}
		 		
 	protected void setPlatform(QuestionType questionType, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(QuestionTypeTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = questionType.getPlatform();
 		if( platform != null ){
 			//if the root object 'questionType' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		questionType.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(QuestionType questionType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(QuestionTypeTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionType.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


