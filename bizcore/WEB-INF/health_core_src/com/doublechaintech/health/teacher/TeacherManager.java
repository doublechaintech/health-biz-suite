
package com.doublechaintech.health.teacher;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface TeacherManager extends BaseManager{

		

	public Teacher createTeacher(HealthUserContext userContext, String name,String mobile,String schoole,String platformId,String cqId) throws Exception;	
	public Teacher updateTeacher(HealthUserContext userContext,String teacherId, int teacherVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Teacher loadTeacher(HealthUserContext userContext, String teacherId, String [] tokensExpr) throws Exception;
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher) throws Exception;
	public Teacher internalSaveTeacher(HealthUserContext userContext, Teacher teacher,Map<String,Object>option) throws Exception;
	
	public Teacher transferToAnotherPlatform(HealthUserContext userContext, String teacherId, String anotherPlatformId)  throws Exception;
 	public Teacher transferToAnotherCq(HealthUserContext userContext, String teacherId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String teacherId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Teacher newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  SchoolClassManager getSchoolClassManager(HealthUserContext userContext, String teacherId, String name, String platformId, String schoole, String cqId ,String [] tokensExpr)  throws Exception;
	
	public  Teacher addSchoolClass(HealthUserContext userContext, String teacherId, String name, String platformId, String schoole, String cqId , String [] tokensExpr)  throws Exception;
	public  Teacher removeSchoolClass(HealthUserContext userContext, String teacherId, String schoolClassId, int schoolClassVersion,String [] tokensExpr)  throws Exception;
	public  Teacher updateSchoolClass(HealthUserContext userContext, String teacherId, String schoolClassId, int schoolClassVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


