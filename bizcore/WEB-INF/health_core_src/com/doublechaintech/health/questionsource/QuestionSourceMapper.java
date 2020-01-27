
package com.doublechaintech.health.questionsource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;

public class QuestionSourceMapper extends BaseRowMapper<QuestionSource>{
	
	protected QuestionSource internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		QuestionSource questionSource = getQuestionSource();		
		 		
 		setId(questionSource, rs, rowNumber); 		
 		setName(questionSource, rs, rowNumber); 		
 		setCode(questionSource, rs, rowNumber); 		
 		setPlatform(questionSource, rs, rowNumber); 		
 		setVersion(questionSource, rs, rowNumber);

		return questionSource;
	}
	
	protected QuestionSource getQuestionSource(){
		return new QuestionSource();
	}		
		
	protected void setId(QuestionSource questionSource, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(QuestionSourceTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionSource.setId(id);
	}
		
	protected void setName(QuestionSource questionSource, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(QuestionSourceTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionSource.setName(name);
	}
		
	protected void setCode(QuestionSource questionSource, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String code = rs.getString(QuestionSourceTable.COLUMN_CODE);
		if(code == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionSource.setCode(code);
	}
		 		
 	protected void setPlatform(QuestionSource questionSource, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(QuestionSourceTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = questionSource.getPlatform();
 		if( platform != null ){
 			//if the root object 'questionSource' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		questionSource.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(QuestionSource questionSource, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(QuestionSourceTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		questionSource.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


