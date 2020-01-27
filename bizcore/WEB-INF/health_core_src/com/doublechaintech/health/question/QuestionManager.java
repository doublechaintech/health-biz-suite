
package com.doublechaintech.health.question;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface QuestionManager extends BaseManager{

		

	public Question createQuestion(HealthUserContext userContext, String topic,String questionTypeId,String optionA,String optionB,String optionC,String optionD,String platformId) throws Exception;	
	public Question updateQuestion(HealthUserContext userContext,String questionId, int questionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Question loadQuestion(HealthUserContext userContext, String questionId, String [] tokensExpr) throws Exception;
	public Question internalSaveQuestion(HealthUserContext userContext, Question question) throws Exception;
	public Question internalSaveQuestion(HealthUserContext userContext, Question question,Map<String,Object>option) throws Exception;
	
	public Question transferToAnotherQuestionType(HealthUserContext userContext, String questionId, String anotherQuestionTypeId)  throws Exception;
 	public Question transferToAnotherPlatform(HealthUserContext userContext, String questionId, String anotherPlatformId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String questionId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, Question newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


