
package com.doublechaintech.health.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;

public class UserMapper extends BaseRowMapper<User>{
	
	protected User internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		User user = getUser();		
		 		
 		setId(user, rs, rowNumber); 		
 		setName(user, rs, rowNumber); 		
 		setAvatar(user, rs, rowNumber); 		
 		setAddress(user, rs, rowNumber); 		
 		setCreateTime(user, rs, rowNumber); 		
 		setPlatform(user, rs, rowNumber); 		
 		setVersion(user, rs, rowNumber);

		return user;
	}
	
	protected User getUser(){
		return new User();
	}		
		
	protected void setId(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(UserTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setId(id);
	}
		
	protected void setName(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(UserTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setName(name);
	}
		
	protected void setAvatar(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String avatar = rs.getString(UserTable.COLUMN_AVATAR);
		if(avatar == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setAvatar(avatar);
	}
		 		
 	protected void setAddress(User user, ResultSet rs, int rowNumber) throws SQLException{
 		String locationId = rs.getString(UserTable.COLUMN_ADDRESS);
 		if( locationId == null){
 			return;
 		}
 		if( locationId.isEmpty()){
 			return;
 		}
 		Location location = user.getAddress();
 		if( location != null ){
 			//if the root object 'user' already have the property, just set the id for it;
 			location.setId(locationId);
 			
 			return;
 		}
 		user.setAddress(createEmptyAddress(locationId));
 	}
 	
	protected void setCreateTime(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(UserTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(User user, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(UserTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = user.getPlatform();
 		if( platform != null ){
 			//if the root object 'user' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		user.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(User user, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(UserTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		user.setVersion(version);
	}
		
		

 	protected Location  createEmptyAddress(String locationId){
 		Location location = new Location();
 		location.setId(locationId);
 		location.setVersion(Integer.MAX_VALUE);
 		return location;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


