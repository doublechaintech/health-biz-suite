
package com.doublechaintech.health.district;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.platform.Platform;

public class DistrictMapper extends BaseRowMapper<District>{
	
	protected District internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		District district = getDistrict();		
		 		
 		setId(district, rs, rowNumber); 		
 		setName(district, rs, rowNumber); 		
 		setCity(district, rs, rowNumber); 		
 		setPlatform(district, rs, rowNumber); 		
 		setCreateTime(district, rs, rowNumber); 		
 		setVersion(district, rs, rowNumber);

		return district;
	}
	
	protected District getDistrict(){
		return new District();
	}		
		
	protected void setId(District district, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(DistrictTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		district.setId(id);
	}
		
	protected void setName(District district, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(DistrictTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		district.setName(name);
	}
		 		
 	protected void setCity(District district, ResultSet rs, int rowNumber) throws SQLException{
 		String cityId = rs.getString(DistrictTable.COLUMN_CITY);
 		if( cityId == null){
 			return;
 		}
 		if( cityId.isEmpty()){
 			return;
 		}
 		City city = district.getCity();
 		if( city != null ){
 			//if the root object 'district' already have the property, just set the id for it;
 			city.setId(cityId);
 			
 			return;
 		}
 		district.setCity(createEmptyCity(cityId));
 	}
 	 		
 	protected void setPlatform(District district, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(DistrictTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = district.getPlatform();
 		if( platform != null ){
 			//if the root object 'district' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		district.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setCreateTime(District district, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date createTime = rs.getTimestamp(DistrictTable.COLUMN_CREATE_TIME);
		
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		district.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setVersion(District district, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(DistrictTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		district.setVersion(version);
	}
		
		

 	protected City  createEmptyCity(String cityId){
 		City city = new City();
 		city.setId(cityId);
 		city.setVersion(Integer.MAX_VALUE);
 		return city;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


