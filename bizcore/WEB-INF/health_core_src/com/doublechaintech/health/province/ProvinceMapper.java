
package com.doublechaintech.health.province;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;

public class ProvinceMapper extends BaseRowMapper<Province>{
	
	protected Province internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Province province = getProvince();		
		 		
 		setId(province, rs, rowNumber); 		
 		setName(province, rs, rowNumber); 		
 		setPlatform(province, rs, rowNumber); 		
 		setCreateTime(province, rs, rowNumber); 		
 		setVersion(province, rs, rowNumber);

		return province;
	}
	
	protected Province getProvince(){
		return new Province();
	}		
		
	protected void setId(Province province, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(ProvinceTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		province.setId(id);
	}
		
	protected void setName(Province province, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(ProvinceTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		province.setName(name);
	}
		 		
 	protected void setPlatform(Province province, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(ProvinceTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = province.getPlatform();
 		if( platform != null ){
 			//if the root object 'province' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		province.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setCreateTime(Province province, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date createTime = rs.getTimestamp(ProvinceTable.COLUMN_CREATE_TIME);
		
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		province.setCreateTime(convertToDateTime(createTime));
	}
		
	protected void setVersion(Province province, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(ProvinceTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		province.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


