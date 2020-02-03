
package com.doublechaintech.health.studentanswer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface StudentAnswerManager extends BaseManager{

		

	public StudentAnswer createStudentAnswer(HealthUserContext userContext, String healthSurveyReportId,String dailyAnswerId,String questionTopic,String answer) throws Exception;	
	public StudentAnswer updateStudentAnswer(HealthUserContext userContext,String studentAnswerId, int studentAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public StudentAnswer loadStudentAnswer(HealthUserContext userContext, String studentAnswerId, String [] tokensExpr) throws Exception;
	public StudentAnswer internalSaveStudentAnswer(HealthUserContext userContext, StudentAnswer studentAnswer) throws Exception;
	public StudentAnswer internalSaveStudentAnswer(HealthUserContext userContext, StudentAnswer studentAnswer,Map<String,Object>option) throws Exception;
	
	public StudentAnswer transferToAnotherHealthSurveyReport(HealthUserContext userContext, String studentAnswerId, String anotherHealthSurveyReportId)  throws Exception;
 	public StudentAnswer transferToAnotherDailyAnswer(HealthUserContext userContext, String studentAnswerId, String anotherDailyAnswerId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String studentAnswerId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, StudentAnswer newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


