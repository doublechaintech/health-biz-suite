
package com.doublechaintech.health.formaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface FormActionManager extends BaseManager{

		

	public FormAction createFormAction(HealthUserContext userContext, String label,String localeKey,String actionKey,String level,String url,String formId) throws Exception;	
	public FormAction updateFormAction(HealthUserContext userContext,String formActionId, int formActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormAction loadFormAction(HealthUserContext userContext, String formActionId, String [] tokensExpr) throws Exception;
	public FormAction internalSaveFormAction(HealthUserContext userContext, FormAction formAction) throws Exception;
	public FormAction internalSaveFormAction(HealthUserContext userContext, FormAction formAction,Map<String,Object>option) throws Exception;
	
	public FormAction transferToAnotherForm(HealthUserContext userContext, String formActionId, String anotherFormId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String formActionId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, FormAction newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


