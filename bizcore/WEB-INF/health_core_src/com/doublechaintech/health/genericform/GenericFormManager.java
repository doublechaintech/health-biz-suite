
package com.doublechaintech.health.genericform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface GenericFormManager extends BaseManager{

		

	public GenericForm createGenericForm(HealthUserContext userContext, String title,String description) throws Exception;	
	public GenericForm updateGenericForm(HealthUserContext userContext,String genericFormId, int genericFormVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public GenericForm loadGenericForm(HealthUserContext userContext, String genericFormId, String [] tokensExpr) throws Exception;
	public GenericForm internalSaveGenericForm(HealthUserContext userContext, GenericForm genericForm) throws Exception;
	public GenericForm internalSaveGenericForm(HealthUserContext userContext, GenericForm genericForm,Map<String,Object>option) throws Exception;
	


	public void delete(HealthUserContext userContext, String genericFormId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, GenericForm newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  FormMessageManager getFormMessageManager(HealthUserContext userContext, String genericFormId, String title, String level ,String [] tokensExpr)  throws Exception;
	
	public  GenericForm addFormMessage(HealthUserContext userContext, String genericFormId, String title, String level , String [] tokensExpr)  throws Exception;
	public  GenericForm removeFormMessage(HealthUserContext userContext, String genericFormId, String formMessageId, int formMessageVersion,String [] tokensExpr)  throws Exception;
	public  GenericForm updateFormMessage(HealthUserContext userContext, String genericFormId, String formMessageId, int formMessageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  FormFieldMessageManager getFormFieldMessageManager(HealthUserContext userContext, String genericFormId, String title, String parameterName, String level ,String [] tokensExpr)  throws Exception;
	
	public  GenericForm addFormFieldMessage(HealthUserContext userContext, String genericFormId, String title, String parameterName, String level , String [] tokensExpr)  throws Exception;
	public  GenericForm removeFormFieldMessage(HealthUserContext userContext, String genericFormId, String formFieldMessageId, int formFieldMessageVersion,String [] tokensExpr)  throws Exception;
	public  GenericForm updateFormFieldMessage(HealthUserContext userContext, String genericFormId, String formFieldMessageId, int formFieldMessageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  FormFieldManager getFormFieldManager(HealthUserContext userContext, String genericFormId, String label, String localeKey, String parameterName, String type, String placeholder, String defaultValue, String description, String fieldGroup, String minimumValue, String maximumValue, boolean required, boolean disabled, boolean customRendering, String candidateValues, String suggestValues ,String [] tokensExpr)  throws Exception;
	
	public  GenericForm addFormField(HealthUserContext userContext, String genericFormId, String label, String localeKey, String parameterName, String type, String placeholder, String defaultValue, String description, String fieldGroup, String minimumValue, String maximumValue, boolean required, boolean disabled, boolean customRendering, String candidateValues, String suggestValues , String [] tokensExpr)  throws Exception;
	public  GenericForm removeFormField(HealthUserContext userContext, String genericFormId, String formFieldId, int formFieldVersion,String [] tokensExpr)  throws Exception;
	public  GenericForm updateFormField(HealthUserContext userContext, String genericFormId, String formFieldId, int formFieldVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  FormActionManager getFormActionManager(HealthUserContext userContext, String genericFormId, String label, String localeKey, String actionKey, String level, String url ,String [] tokensExpr)  throws Exception;
	
	public  GenericForm addFormAction(HealthUserContext userContext, String genericFormId, String label, String localeKey, String actionKey, String level, String url , String [] tokensExpr)  throws Exception;
	public  GenericForm removeFormAction(HealthUserContext userContext, String genericFormId, String formActionId, int formActionVersion,String [] tokensExpr)  throws Exception;
	public  GenericForm updateFormAction(HealthUserContext userContext, String genericFormId, String formActionId, int formActionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


