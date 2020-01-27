
package com.doublechaintech.health.studentdailyanswer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface StudentDailyAnswerManager extends BaseManager{

		

	public StudentDailyAnswer createStudentDailyAnswer(HealthUserContext userContext, String studentHealthSurveyId,String questionId,String answer,String cqId) throws Exception;	
	public StudentDailyAnswer updateStudentDailyAnswer(HealthUserContext userContext,String studentDailyAnswerId, int studentDailyAnswerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public StudentDailyAnswer loadStudentDailyAnswer(HealthUserContext userContext, String studentDailyAnswerId, String [] tokensExpr) throws Exception;
	public StudentDailyAnswer internalSaveStudentDailyAnswer(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer) throws Exception;
	public StudentDailyAnswer internalSaveStudentDailyAnswer(HealthUserContext userContext, StudentDailyAnswer studentDailyAnswer,Map<String,Object>option) throws Exception;
	
	public StudentDailyAnswer transferToAnotherStudentHealthSurvey(HealthUserContext userContext, String studentDailyAnswerId, String anotherStudentHealthSurveyId)  throws Exception;
 	public StudentDailyAnswer transferToAnotherQuestion(HealthUserContext userContext, String studentDailyAnswerId, String anotherQuestionId)  throws Exception;
 	public StudentDailyAnswer transferToAnotherCq(HealthUserContext userContext, String studentDailyAnswerId, String anotherCqId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String studentDailyAnswerId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, StudentDailyAnswer newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}

