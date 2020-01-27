
package com.doublechaintech.health.usertype;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;

public class UserTypeMapper extends BaseRowMapper<UserType>{
	
	protected UserType internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		UserType userType = getUserType();		
		 		
 		setId(userType, rs, rowNumber); 		
 		setName(userType, rs, rowNumber); 		
 		setCode(userType, rs, rowNumber); 		
 		setPlatform(userType, rs, rowNumber); 		
 		setVersion(userType, rs, rowNumber);

		return userType;
	}
	
	protected UserType getUserType(){
		return new UserType();
	}		
		
	protected void setId(UserType userType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(UserTypeTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		userType.setId(id);
	}
		
	protected void setName(UserType userType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(UserTypeTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		userType.setName(name);
	}
		
	protected void setCode(UserType userType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String code = rs.getString(UserTypeTable.COLUMN_CODE);
		if(code == null){
			//do nothing when nothing found in database
			return;
		}
		
		userType.setCode(code);
	}
		 		
 	protected void setPlatform(UserType userType, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(UserTypeTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = userType.getPlatform();
 		if( platform != null ){
 			//if the root object 'userType' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		userType.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(UserType userType, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(UserTypeTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		userType.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


