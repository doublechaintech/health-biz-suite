
package com.doublechaintech.health.formmessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface FormMessageManager extends BaseManager{

		

	public FormMessage createFormMessage(HealthUserContext userContext, String title,String formId,String level) throws Exception;	
	public FormMessage updateFormMessage(HealthUserContext userContext,String formMessageId, int formMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormMessage loadFormMessage(HealthUserContext userContext, String formMessageId, String [] tokensExpr) throws Exception;
	public FormMessage internalSaveFormMessage(HealthUserContext userContext, FormMessage formMessage) throws Exception;
	public FormMessage internalSaveFormMessage(HealthUserContext userContext, FormMessage formMessage,Map<String,Object>option) throws Exception;
	
	public FormMessage transferToAnotherForm(HealthUserContext userContext, String formMessageId, String anotherFormId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String formMessageId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, FormMessage newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


