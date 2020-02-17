
package com.doublechaintech.health.candidateelement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.BaseManager;
import com.doublechaintech.health.SmartList;

public interface CandidateElementManager extends BaseManager{

		

	public CandidateElement createCandidateElement(HealthUserContext userContext, String name,String type,String image,String containerId) throws Exception;	
	public CandidateElement updateCandidateElement(HealthUserContext userContext,String candidateElementId, int candidateElementVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public CandidateElement loadCandidateElement(HealthUserContext userContext, String candidateElementId, String [] tokensExpr) throws Exception;
	public CandidateElement internalSaveCandidateElement(HealthUserContext userContext, CandidateElement candidateElement) throws Exception;
	public CandidateElement internalSaveCandidateElement(HealthUserContext userContext, CandidateElement candidateElement,Map<String,Object>option) throws Exception;
	
	public CandidateElement transferToAnotherContainer(HealthUserContext userContext, String candidateElementId, String anotherContainerId)  throws Exception;
 

	public void delete(HealthUserContext userContext, String candidateElementId, int version) throws Exception;
	public int deleteAll(HealthUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(HealthUserContext userContext, CandidateElement newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


