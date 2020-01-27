
package com.doublechaintech.health.surveystatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;

public class SurveyStatusMapper extends BaseRowMapper<SurveyStatus>{
	
	protected SurveyStatus internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		SurveyStatus surveyStatus = getSurveyStatus();		
		 		
 		setId(surveyStatus, rs, rowNumber); 		
 		setName(surveyStatus, rs, rowNumber); 		
 		setCode(surveyStatus, rs, rowNumber); 		
 		setPlatform(surveyStatus, rs, rowNumber); 		
 		setVersion(surveyStatus, rs, rowNumber);

		return surveyStatus;
	}
	
	protected SurveyStatus getSurveyStatus(){
		return new SurveyStatus();
	}		
		
	protected void setId(SurveyStatus surveyStatus, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(SurveyStatusTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		surveyStatus.setId(id);
	}
		
	protected void setName(SurveyStatus surveyStatus, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(SurveyStatusTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		surveyStatus.setName(name);
	}
		
	protected void setCode(SurveyStatus surveyStatus, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String code = rs.getString(SurveyStatusTable.COLUMN_CODE);
		if(code == null){
			//do nothing when nothing found in database
			return;
		}
		
		surveyStatus.setCode(code);
	}
		 		
 	protected void setPlatform(SurveyStatus surveyStatus, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(SurveyStatusTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = surveyStatus.getPlatform();
 		if( platform != null ){
 			//if the root object 'surveyStatus' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		surveyStatus.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(SurveyStatus surveyStatus, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(SurveyStatusTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		surveyStatus.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


