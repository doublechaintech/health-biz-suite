
package com.doublechaintech.health.teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;

public class TeacherMapper extends BaseRowMapper<Teacher>{
	
	protected Teacher internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Teacher teacher = getTeacher();		
		 		
 		setId(teacher, rs, rowNumber); 		
 		setName(teacher, rs, rowNumber); 		
 		setMobile(teacher, rs, rowNumber); 		
 		setSchoole(teacher, rs, rowNumber); 		
 		setCreateTime(teacher, rs, rowNumber); 		
 		setPlatform(teacher, rs, rowNumber); 		
 		setCq(teacher, rs, rowNumber); 		
 		setVersion(teacher, rs, rowNumber);

		return teacher;
	}
	
	protected Teacher getTeacher(){
		return new Teacher();
	}		
		
	protected void setId(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(TeacherTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		teacher.setId(id);
	}
		
	protected void setName(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(TeacherTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		teacher.setName(name);
	}
		
	protected void setMobile(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String mobile = rs.getString(TeacherTable.COLUMN_MOBILE);
		if(mobile == null){
			//do nothing when nothing found in database
			return;
		}
		
		teacher.setMobile(mobile);
	}
		
	protected void setSchoole(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String schoole = rs.getString(TeacherTable.COLUMN_SCHOOLE);
		if(schoole == null){
			//do nothing when nothing found in database
			return;
		}
		
		teacher.setSchoole(schoole);
	}
		
	protected void setCreateTime(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(TeacherTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		teacher.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(TeacherTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = teacher.getPlatform();
 		if( platform != null ){
 			//if the root object 'teacher' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		teacher.setPlatform(createEmptyPlatform(platformId));
 	}
 	 		
 	protected void setCq(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(TeacherTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = teacher.getCq();
 		if( changeRequest != null ){
 			//if the root object 'teacher' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		teacher.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(Teacher teacher, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(TeacherTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		teacher.setVersion(version);
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


