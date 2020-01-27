
package com.doublechaintech.health.schoolclass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;

public class SchoolClassMapper extends BaseRowMapper<SchoolClass>{
	
	protected SchoolClass internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		SchoolClass schoolClass = getSchoolClass();		
		 		
 		setId(schoolClass, rs, rowNumber); 		
 		setName(schoolClass, rs, rowNumber); 		
 		setClassTeacher(schoolClass, rs, rowNumber); 		
 		setCreateTime(schoolClass, rs, rowNumber); 		
 		setPlatform(schoolClass, rs, rowNumber); 		
 		setSchoole(schoolClass, rs, rowNumber); 		
 		setCq(schoolClass, rs, rowNumber); 		
 		setVersion(schoolClass, rs, rowNumber);

		return schoolClass;
	}
	
	protected SchoolClass getSchoolClass(){
		return new SchoolClass();
	}		
		
	protected void setId(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(SchoolClassTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		schoolClass.setId(id);
	}
		
	protected void setName(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(SchoolClassTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		schoolClass.setName(name);
	}
		 		
 	protected void setClassTeacher(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
 		String teacherId = rs.getString(SchoolClassTable.COLUMN_CLASS_TEACHER);
 		if( teacherId == null){
 			return;
 		}
 		if( teacherId.isEmpty()){
 			return;
 		}
 		Teacher teacher = schoolClass.getClassTeacher();
 		if( teacher != null ){
 			//if the root object 'schoolClass' already have the property, just set the id for it;
 			teacher.setId(teacherId);
 			
 			return;
 		}
 		schoolClass.setClassTeacher(createEmptyClassTeacher(teacherId));
 	}
 	
	protected void setCreateTime(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(SchoolClassTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		schoolClass.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(SchoolClassTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = schoolClass.getPlatform();
 		if( platform != null ){
 			//if the root object 'schoolClass' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		schoolClass.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setSchoole(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String schoole = rs.getString(SchoolClassTable.COLUMN_SCHOOLE);
		if(schoole == null){
			//do nothing when nothing found in database
			return;
		}
		
		schoolClass.setSchoole(schoole);
	}
		 		
 	protected void setCq(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(SchoolClassTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = schoolClass.getCq();
 		if( changeRequest != null ){
 			//if the root object 'schoolClass' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		schoolClass.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(SchoolClass schoolClass, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(SchoolClassTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		schoolClass.setVersion(version);
	}
		
		

 	protected Teacher  createEmptyClassTeacher(String teacherId){
 		Teacher teacher = new Teacher();
 		teacher.setId(teacherId);
 		teacher.setVersion(Integer.MAX_VALUE);
 		return teacher;
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


