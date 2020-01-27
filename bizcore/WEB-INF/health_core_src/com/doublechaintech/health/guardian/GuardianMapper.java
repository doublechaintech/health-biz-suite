
package com.doublechaintech.health.guardian;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.wechatuser.WechatUser;

public class GuardianMapper extends BaseRowMapper<Guardian>{
	
	protected Guardian internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Guardian guardian = getGuardian();		
		 		
 		setId(guardian, rs, rowNumber); 		
 		setName(guardian, rs, rowNumber); 		
 		setMobile(guardian, rs, rowNumber); 		
 		setAddress(guardian, rs, rowNumber); 		
 		setWechatUser(guardian, rs, rowNumber); 		
 		setCreateTime(guardian, rs, rowNumber); 		
 		setPlatform(guardian, rs, rowNumber); 		
 		setCq(guardian, rs, rowNumber); 		
 		setVersion(guardian, rs, rowNumber);

		return guardian;
	}
	
	protected Guardian getGuardian(){
		return new Guardian();
	}		
		
	protected void setId(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(GuardianTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		guardian.setId(id);
	}
		
	protected void setName(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(GuardianTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		guardian.setName(name);
	}
		
	protected void setMobile(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String mobile = rs.getString(GuardianTable.COLUMN_MOBILE);
		if(mobile == null){
			//do nothing when nothing found in database
			return;
		}
		
		guardian.setMobile(mobile);
	}
		 		
 	protected void setAddress(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
 		String locationId = rs.getString(GuardianTable.COLUMN_ADDRESS);
 		if( locationId == null){
 			return;
 		}
 		if( locationId.isEmpty()){
 			return;
 		}
 		Location location = guardian.getAddress();
 		if( location != null ){
 			//if the root object 'guardian' already have the property, just set the id for it;
 			location.setId(locationId);
 			
 			return;
 		}
 		guardian.setAddress(createEmptyAddress(locationId));
 	}
 	 		
 	protected void setWechatUser(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
 		String wechatUserId = rs.getString(GuardianTable.COLUMN_WECHAT_USER);
 		if( wechatUserId == null){
 			return;
 		}
 		if( wechatUserId.isEmpty()){
 			return;
 		}
 		WechatUser wechatUser = guardian.getWechatUser();
 		if( wechatUser != null ){
 			//if the root object 'guardian' already have the property, just set the id for it;
 			wechatUser.setId(wechatUserId);
 			
 			return;
 		}
 		guardian.setWechatUser(createEmptyWechatUser(wechatUserId));
 	}
 	
	protected void setCreateTime(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(GuardianTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		guardian.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(GuardianTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = guardian.getPlatform();
 		if( platform != null ){
 			//if the root object 'guardian' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		guardian.setPlatform(createEmptyPlatform(platformId));
 	}
 	 		
 	protected void setCq(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(GuardianTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = guardian.getCq();
 		if( changeRequest != null ){
 			//if the root object 'guardian' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		guardian.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(Guardian guardian, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(GuardianTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		guardian.setVersion(version);
	}
		
		

 	protected Location  createEmptyAddress(String locationId){
 		Location location = new Location();
 		location.setId(locationId);
 		location.setVersion(Integer.MAX_VALUE);
 		return location;
 	}
 	
 	protected WechatUser  createEmptyWechatUser(String wechatUserId){
 		WechatUser wechatUser = new WechatUser();
 		wechatUser.setId(wechatUserId);
 		wechatUser.setVersion(Integer.MAX_VALUE);
 		return wechatUser;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
 	protected ChangeRequest  createEmptyCq(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


