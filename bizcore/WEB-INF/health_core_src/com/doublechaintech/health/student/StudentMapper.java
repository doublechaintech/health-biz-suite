
package com.doublechaintech.health.student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.guardian.Guardian;

public class StudentMapper extends BaseRowMapper<Student>{
	
	protected Student internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Student student = getStudent();		
		 		
 		setId(student, rs, rowNumber); 		
 		setName(student, rs, rowNumber); 		
 		setGender(student, rs, rowNumber); 		
 		setGuardian(student, rs, rowNumber); 		
 		setSchoolClass(student, rs, rowNumber); 		
 		setStudentId(student, rs, rowNumber); 		
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
		
	protected void setName(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(StudentTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setName(name);
	}
		
	protected void setGender(Student student, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String gender = rs.getString(StudentTable.COLUMN_GENDER);
		if(gender == null){
			//do nothing when nothing found in database
			return;
		}
		
		student.setGender(gender);
	}
		 		
 	protected void setGuardian(Student student, ResultSet rs, int rowNumber) throws SQLException{
 		String guardianId = rs.getString(StudentTable.COLUMN_GUARDIAN);
 		if( guardianId == null){
 			return;
 		}
 		if( guardianId.isEmpty()){
 			return;
 		}
 		Guardian guardian = student.getGuardian();
 		if( guardian != null ){
 			//if the root object 'student' already have the property, just set the id for it;
 			guardian.setId(guardianId);
 			
 			return;
 		}
 		student.setGuardian(createEmptyGuardian(guardianId));
 	}
 	 		
 	protected void setSchoolClass(Student student, ResultSet rs, int rowNumber) throws SQLException{
 		String schoolClassId = rs.getString(StudentTable.COLUMN_SCHOOL_CLASS);
 		if( schoolClassId == null){
 			return;
 		}
 		if( schoolClassId.isEmpty()){
 			return;
 		}
 		SchoolClass schoolClass = student.getSchoolClass();
 		if( schoolClass != null ){
 			//if the root object 'student' already have the property, just set the id for it;
 			schoolClass.setId(schoolClassId);
 			
 			return;
 		}
 		student.setSchoolClass(createEmptySchoolClass(schoolClassId));
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
		
		

 	protected Guardian  createEmptyGuardian(String guardianId){
 		Guardian guardian = new Guardian();
 		guardian.setId(guardianId);
 		guardian.setVersion(Integer.MAX_VALUE);
 		return guardian;
 	}
 	
 	protected SchoolClass  createEmptySchoolClass(String schoolClassId){
 		SchoolClass schoolClass = new SchoolClass();
 		schoolClass.setId(schoolClassId);
 		schoolClass.setVersion(Integer.MAX_VALUE);
 		return schoolClass;
 	}
 	
 	protected ChangeRequest  createEmptyCq(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


