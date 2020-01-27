
package com.doublechaintech.health.classdailyhealthsurvey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.health.BaseRowMapper;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.wechatuser.WechatUser;

public class ClassDailyHealthSurveyMapper extends BaseRowMapper<ClassDailyHealthSurvey>{
	
	protected ClassDailyHealthSurvey internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		ClassDailyHealthSurvey classDailyHealthSurvey = getClassDailyHealthSurvey();		
		 		
 		setId(classDailyHealthSurvey, rs, rowNumber); 		
 		setName(classDailyHealthSurvey, rs, rowNumber); 		
 		setSchoolClass(classDailyHealthSurvey, rs, rowNumber); 		
 		setSurveyTime(classDailyHealthSurvey, rs, rowNumber); 		
 		setCreator(classDailyHealthSurvey, rs, rowNumber); 		
 		setCq(classDailyHealthSurvey, rs, rowNumber); 		
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
		 		
 	protected void setSchoolClass(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String schoolClassId = rs.getString(ClassDailyHealthSurveyTable.COLUMN_SCHOOL_CLASS);
 		if( schoolClassId == null){
 			return;
 		}
 		if( schoolClassId.isEmpty()){
 			return;
 		}
 		SchoolClass schoolClass = classDailyHealthSurvey.getSchoolClass();
 		if( schoolClass != null ){
 			//if the root object 'classDailyHealthSurvey' already have the property, just set the id for it;
 			schoolClass.setId(schoolClassId);
 			
 			return;
 		}
 		classDailyHealthSurvey.setSchoolClass(createEmptySchoolClass(schoolClassId));
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
 		String wechatUserId = rs.getString(ClassDailyHealthSurveyTable.COLUMN_CREATOR);
 		if( wechatUserId == null){
 			return;
 		}
 		if( wechatUserId.isEmpty()){
 			return;
 		}
 		WechatUser wechatUser = classDailyHealthSurvey.getCreator();
 		if( wechatUser != null ){
 			//if the root object 'classDailyHealthSurvey' already have the property, just set the id for it;
 			wechatUser.setId(wechatUserId);
 			
 			return;
 		}
 		classDailyHealthSurvey.setCreator(createEmptyCreator(wechatUserId));
 	}
 	 		
 	protected void setCq(ClassDailyHealthSurvey classDailyHealthSurvey, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(ClassDailyHealthSurveyTable.COLUMN_CQ);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = classDailyHealthSurvey.getCq();
 		if( changeRequest != null ){
 			//if the root object 'classDailyHealthSurvey' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		classDailyHealthSurvey.setCq(createEmptyCq(changeRequestId));
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
		
		

 	protected SchoolClass  createEmptySchoolClass(String schoolClassId){
 		SchoolClass schoolClass = new SchoolClass();
 		schoolClass.setId(schoolClassId);
 		schoolClass.setVersion(Integer.MAX_VALUE);
 		return schoolClass;
 	}
 	
 	protected WechatUser  createEmptyCreator(String wechatUserId){
 		WechatUser wechatUser = new WechatUser();
 		wechatUser.setId(wechatUserId);
 		wechatUser.setVersion(Integer.MAX_VALUE);
 		return wechatUser;
 	}
 	
 	protected ChangeRequest  createEmptyCq(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


