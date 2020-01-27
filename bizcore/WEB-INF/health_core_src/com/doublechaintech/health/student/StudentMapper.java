
package com.doublechaintech.health.student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.user.User;

public class StudentMapper extends BaseRowMapper<Student>{
	
	protected Student internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Student student = getStudent();		
		 		
 		setId(student, rs, rowNumber); 		
 		setStudentName(student, rs, rowNumber); 		
 		setStudentId(student, rs, rowNumber); 		
 		setGuardianName(student, rs, rowNumber); 		
 		setGuardianMobile(student, rs, rowNumber); 		
 		setAddress(student, rs, rowNumber); 		
 		setUser(student, rs, rowNumber); 		
 		setCreateTime(student, rs, rowNumber); 		
 		setPlatform(student, rs, rowNumber); 		
 		setCq(student, rs, rowNumber); 		
 		setVersion(student, rs, rowNumber);

		return student;
	}
	
	protected Student getStudent(){
		return new Student();
	}		
		
	protected void setId(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(StudentTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setId(id);
	}
		
	protected void setStudentName(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String studentName = rs.getString(StudentTable.COLUMN_STUDENT_NAME);
		if(studentName == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setStudentName(studentName);
	}
		
	protected void setStudentId(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String studentId = rs.getString(StudentTable.COLUMN_STUDENT_ID);
		if(studentId == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setStudentId(studentId);
	}
		
	protected void setGuardianName(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String guardianName = rs.getString(StudentTable.COLUMN_GUARDIAN_NAME);
		if(guardianName == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setGuardianName(guardianName);
	}
		
	protected void setGuardianMobile(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String guardianMobile = rs.getString(StudentTable.COLUMN_GUARDIAN_MOBILE);
		if(guardianMobile == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setGuardianMobile(guardianMobile);
	}
		 		
 	protected void setAddress(Student student, ResultSet rs, int rowNumber) throws SQLException{
 		String locationId = rs.getString(StudentTable.COLUMN_ADDRESS);
 		if( locationId == null){
 			return;
 		}
 		if( locationId.isEmpty()){
 			return;
 		}
 		Location location = student.getAddress();
 		if( location != null ){
 			//if the root object 'student' already have the property, just set the id for it;
 			location.setId(locationId);
 			
 			return;
 		}
 		student.setAddress(createEmptyAddress(locationId));
 	}
 	 		
 	protected void setUser(Student student, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(StudentTable.COLUMN_USER);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = student.getUser();
 		if( user != null ){
 			//if the root object 'student' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		student.setUser(createEmptyUser(userId));
 	}
 	
	protected void setCreateTime(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date createTime = rs.getTimestamp(StudentTable.COLUMN_CREATE_TIME);
		if(createTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setCreateTime(convertToDateTime(createTime));
	}
		 		
 	protected void setPlatform(Student student, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(StudentTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = student.getPlatform();
 		if( platform != null ){
 			//if the root object 'student' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		student.setPlatform(createEmptyPlatform(platformId));
 	}
 	 		
 	protected void setCq(Student student, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(StudentTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = student.getCq();
 		if( changeRequest != null ){
 			//if the root object 'student' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		student.setCq(createEmptyCq(changeRequestId));
 	}
 	
	protected void setVersion(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(StudentTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setVersion(version);
	}
		
		

 	protected Location  createEmptyAddress(String locationId){
 		Location location = new Location();
 		location.setId(locationId);
 		location.setVersion(Integer.MAX_VALUE);
 		return location;
 	}
 	
 	protected User  createEmptyUser(String userId){
 		User user = new User();
 		user.setId(userId);
 		user.setVersion(Integer.MAX_VALUE);
 		return user;
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


