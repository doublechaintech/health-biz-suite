
package com.doublechaintech.health.city;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.province.Province;

public class CityMapper extends BaseRowMapper<City>{
	
	protected City internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		City city = getCity();		
		 		
 		setId(city, rs, rowNumber); 		
 		setName(city, rs, rowNumber); 		
 		setProvince(city, rs, rowNumber); 		
 		setPlatform(city, rs, rowNumber); 		
 		setCreateTime(city, rs, rowNumber); 		
 		setVersion(city, rs, rowNumber);

		return city;
	}
	
	protected City getCity(){
		return new City();
	}		
		
	protected void setId(City city, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(CityTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		city.setId(id);
	}
		
	protected void setName(City city, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(CityTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		city.setName(name);
	}
		 		
 	protected void setProvince(City city, ResultSet rs, int rowNumber) throws SQLException{
 		String provinceId = rs.getString(CityTable.COLUMN_PROVINCE);
 		if( provinceId == null){
 			return;
 		}
 		if( provinceId.isEmpty()){
 			return;
 		}
 		Province province = city.getProvince();
 		if( province != null ){
 			//if the root object 'city' already have the property, just set the id for it;
 			province.setId(provinceId);
 			
 			return;
 		}
 		city.setProvince(createEmptyProvince(provinceId));
 	}
 	 		
 	protected void setPlatform(City city, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(CityTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = city.getPlatform();
 		if( platform != null ){
 			//if the root object 'city' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		city.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setCreateTime(City city, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(CityTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		city.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setVersion(City city, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(CityTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		city.setVersion(version);
	}
		
		

 	protected Province  createEmptyProvince(String provinceId){
 		Province province = new Province();
 		province.setId(provinceId);
 		province.setVersion(Integer.MAX_VALUE);
 		return province;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


