
package com.doublechaintech.health.location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.province.Province;

public class LocationMapper extends BaseRowMapper<Location>{
	
	protected Location internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Location location = getLocation();		
		 		
 		setId(location, rs, rowNumber); 		
 		setName(location, rs, rowNumber); 		
 		setAddress(location, rs, rowNumber); 		
 		setDistrict(location, rs, rowNumber); 		
 		setProvince(location, rs, rowNumber); 		
 		setLatitude(location, rs, rowNumber); 		
 		setLongitude(location, rs, rowNumber); 		
 		setVersion(location, rs, rowNumber);

		return location;
	}
	
	protected Location getLocation(){
		return new Location();
	}		
		
	protected void setId(Location location, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(LocationTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		location.setId(id);
	}
		
	protected void setName(Location location, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(LocationTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		location.setName(name);
	}
		
	protected void setAddress(Location location, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String address = rs.getString(LocationTable.COLUMN_ADDRESS);
		
		if(address == null){
			//do nothing when nothing found in database
			return;
		}
		
		location.setAddress(address);
	}
		 		
 	protected void setDistrict(Location location, ResultSet rs, int rowNumber) throws SQLException{
 		String districtId = rs.getString(LocationTable.COLUMN_DISTRICT);
 		if( districtId == null){
 			return;
 		}
 		if( districtId.isEmpty()){
 			return;
 		}
 		District district = location.getDistrict();
 		if( district != null ){
 			//if the root object 'location' already have the property, just set the id for it;
 			district.setId(districtId);
 			
 			return;
 		}
 		location.setDistrict(createEmptyDistrict(districtId));
 	}
 	 		
 	protected void setProvince(Location location, ResultSet rs, int rowNumber) throws SQLException{
 		String provinceId = rs.getString(LocationTable.COLUMN_PROVINCE);
 		if( provinceId == null){
 			return;
 		}
 		if( provinceId.isEmpty()){
 			return;
 		}
 		Province province = location.getProvince();
 		if( province != null ){
 			//if the root object 'location' already have the property, just set the id for it;
 			province.setId(provinceId);
 			
 			return;
 		}
 		location.setProvince(createEmptyProvince(provinceId));
 	}
 	
	protected void setLatitude(Location location, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal latitude = rs.getBigDecimal(LocationTable.COLUMN_LATITUDE);
		
		if(latitude == null){
			//do nothing when nothing found in database
			return;
		}
		
		location.setLatitude(latitude);
	}
		
	protected void setLongitude(Location location, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		BigDecimal longitude = rs.getBigDecimal(LocationTable.COLUMN_LONGITUDE);
		
		if(longitude == null){
			//do nothing when nothing found in database
			return;
		}
		
		location.setLongitude(longitude);
	}
		
	protected void setVersion(Location location, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(LocationTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		location.setVersion(version);
	}
		
		

 	protected District  createEmptyDistrict(String districtId){
 		District district = new District();
 		district.setId(districtId);
 		district.setVersion(Integer.MAX_VALUE);
 		return district;
 	}
 	
 	protected Province  createEmptyProvince(String provinceId){
 		Province province = new Province();
 		province.setId(provinceId);
 		province.setVersion(Integer.MAX_VALUE);
 		return province;
 	}
 	
}


