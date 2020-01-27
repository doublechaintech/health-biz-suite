
package com.doublechaintech.health.wechatuser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.usertype.UserType;

public class WechatUserMapper extends BaseRowMapper<WechatUser>{
	
	protected WechatUser internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		WechatUser wechatUser = getWechatUser();		
		 		
 		setId(wechatUser, rs, rowNumber); 		
 		setName(wechatUser, rs, rowNumber); 		
 		setAvatar(wechatUser, rs, rowNumber); 		
 		setAddress(wechatUser, rs, rowNumber); 		
 		setUserType(wechatUser, rs, rowNumber); 		
 		setCreateTime(wechatUser, rs, rowNumber); 		
 		setPlatform(wechatUser, rs, rowNumber); 		
 		setVersion(wechatUser, rs, rowNumber);

		return wechatUser;
	}
	
	protected WechatUser getWechatUser(){
		return new WechatUser();
	}		
		
	protected void setId(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(WechatUserTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		wechatUser.setId(id);
	}
		
	protected void setName(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(WechatUserTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		wechatUser.setName(name);
	}
		
	protected void setAvatar(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String avatar = rs.getString(WechatUserTable.COLUMN_AVATAR);
		if(avatar == null){
			//do nothing when nothing found in database
			return;
		}
		
		wechatUser.setAvatar(avatar);
	}
		 		
 	protected void setAddress(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
 		String locationId = rs.getString(WechatUserTable.COLUMN_ADDRESS);
 		if( locationId == null){
 			return;
 		}
 		if( locationId.isEmpty()){
 			return;
 		}
 		Location location = wechatUser.getAddress();
 		if( location != null ){
 			//if the root object 'wechatUser' already have the property, just set the id for it;
 			location.setId(locationId);
 			
 			return;
 		}
 		wechatUser.setAddress(createEmptyAddress(locationId));
 	}
 	 		
 	protected void setUserType(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
 		String userTypeId = rs.getString(WechatUserTable.COLUMN_USER_TYPE);
 		if( userTypeId == null){
 			return;
 		}
 		if( userTypeId.isEmpty()){
 			return;
 		}
 		UserType userType = wechatUser.getUserType();
 		if( userType != null ){
 			//if the root object 'wechatUser' already have the property, just set the id for it;
 			userType.setId(userTypeId);
 			
 			return;
 		}
 		wechatUser.setUserType(createEmptyUserType(userTypeId));
 	}
 	
	protected void setCreateTime(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(WechatUserTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		wechatUser.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(WechatUserTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = wechatUser.getPlatform();
 		if( platform != null ){
 			//if the root object 'wechatUser' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		wechatUser.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(WechatUser wechatUser, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(WechatUserTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		wechatUser.setVersion(version);
	}
		
		

 	protected Location  createEmptyAddress(String locationId){
 		Location location = new Location();
 		location.setId(locationId);
 		location.setVersion(Integer.MAX_VALUE);
 		return location;
 	}
 	
 	protected UserType  createEmptyUserType(String userTypeId){
 		UserType userType = new UserType();
 		userType.setId(userTypeId);
 		userType.setVersion(Integer.MAX_VALUE);
 		return userType;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


