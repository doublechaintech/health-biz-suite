
package com.doublechaintech.health.classdailyhealthsurvey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.user.User;

public class ClassDailyHealthSurveyMapper extends BaseRowMapper<ClassDailyHealthSurvey>{
	
	protected ClassDailyHealthSurvey internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ClassDailyHealthSurvey classDailyHealthSurvey = getClassDailyHealthSurvey();		
		 		
 		setId(classDailyHealthSurvey, rs, rowNumber); 		
 		setName(classDailyHealthSurvey, rs, rowNumber); 		
 		setTeacher(classDailyHealthSurvey, rs, rowNumber); 		
 		setSurveyTime(classDailyHealthSurvey, rs, rowNumber); 		
 		setCreator(classDailyHealthSurvey, rs, rowNumber); 		
 		setDownloadUrl(classDailyHealthSurvey, rs, rowNumber); 		
 		setChangeRequest(classDailyHealthSurvey, rs, rowNumber); 		
 		setVersion(classDailyHealthSurvey, rs, rowNumber);

		return classDailyHealthSurvey;
	}
	
	protected ClassDailyHealthSurvey getClassDailyHealthSurvey(){
		return new ClassDailyHealthSurvey();
	}		
		
	protected void setId(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(ClassDailyHealthSurveyTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		classDailyHealthSurvey.setId(id);
	}
		
	protected void setName(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(ClassDailyHealthSurveyTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		classDailyHealthSurvey.setName(name);
	}
		 		
 	protected void setTeacher(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String teacherId = rs.getString(ClassDailyHealthSurveyTable.COLUMN_TEACHER);
 		if( teacherId == null){
 			return;
 		}
 		if( teacherId.isEmpty()){
 			return;
 		}
 		Teacher teacher = classDailyHealthSurvey.getTeacher();
 		if( teacher != null ){
 			//if the root object 'classDailyHealthSurvey' already have the property, just set the id for it;
 			teacher.setId(teacherId);
 			
 			return;
 		}
 		classDailyHealthSurvey.setTeacher(createEmptyTeacher(teacherId));
 	}
 	
	protected void setSurveyTime(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Date surveyTime = rs.getTimestamp(ClassDailyHealthSurveyTable.COLUMN_SURVEY_TIME);
		
		if(surveyTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		classDailyHealthSurvey.setSurveyTime(convertToDateTime(surveyTime));
	}
		 		
 	protected void setCreator(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String userId = rs.getString(ClassDailyHealthSurveyTable.COLUMN_CREATOR);
 		if( userId == null){
 			return;
 		}
 		if( userId.isEmpty()){
 			return;
 		}
 		User user = classDailyHealthSurvey.getCreator();
 		if( user != null ){
 			//if the root object 'classDailyHealthSurvey' already have the property, just set the id for it;
 			user.setId(userId);
 			
 			return;
 		}
 		classDailyHealthSurvey.setCreator(createEmptyCreator(userId));
 	}
 	
	protected void setDownloadUrl(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String downloadUrl = rs.getString(ClassDailyHealthSurveyTable.COLUMN_DOWNLOAD_URL);
		
		if(downloadUrl == null){
			//do nothing when nothing found in database
			return;
		}
		
		classDailyHealthSurvey.setDownloadUrl(downloadUrl);
	}
		 		
 	protected void setChangeRequest(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(ClassDailyHealthSurveyTable.COLUMN_CHANGE_REQUEST);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = classDailyHealthSurvey.getChangeRequest();
 		if( changeRequest != null ){
 			//if the root object 'classDailyHealthSurvey' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		classDailyHealthSurvey.setChangeRequest(createEmptyChangeRequest(changeRequestId));
 	}
 	
	protected void setVersion(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(ClassDailyHealthSurveyTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		classDailyHealthSurvey.setVersion(version);
	}
		
		

 	protected Teacher  createEmptyTeacher(String teacherId){
 		Teacher teacher = new Teacher();
 		teacher.setId(teacherId);
 		teacher.setVersion(Integer.MAX_VALUE);
 		return teacher;
 	}
 	
 	protected User  createEmptyCreator(String userId){
 		User user = new User();
 		user.setId(userId);
 		user.setVersion(Integer.MAX_VALUE);
 		return user;
 	}
 	
 	protected ChangeRequest  createEmptyChangeRequest(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


