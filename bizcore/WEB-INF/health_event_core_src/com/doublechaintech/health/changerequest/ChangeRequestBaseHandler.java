/*
	ChangeRequestBaseHandler 最基础的Handler，如果这些ChangeRequest都按照Event规则来处理，
	那么有这个ChangeRequestBaseHandler对于大多数系统就足够了。
*/
package  com.doublechaintech.health.changerequest;
import java.util.Arrays;

import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthCheckerManager;
import com.doublechaintech.health.changerequest.ChangeRequest;
public class ChangeRequestBaseHandler extends CustomHealthCheckerManager{
	
	private static final String SUB_EVENT_NAMES[]={"teacherProcessor","questionProcessor","classDailyHealthSurveyProcessor","studentHealthSurveyProcessor"};
	
	public ChangeRequest handle(HealthUserContext userContext, ChangeRequest request) throws Exception {
		
		checkerOf(userContext).checkAndFixChangeRequest(request);
		checkIfComplyWithSpec(userContext,request);
		userContext.log("ChangeRequestBaseHandler\t"+request.toString());
		Arrays.stream(SUB_EVENT_NAMES).forEach(beanName->handleByChainNode(userContext,request,beanName));
		return request;
		
	}
	protected void checkIfComplyWithSpec(HealthUserContext userContext, ChangeRequest request){
		//empty code to let sub class implementation
	}
	protected void handleByChainNode(HealthUserContext userContext, ChangeRequest request, String beanName){
		ChangeRequestChainProcessor processor =(ChangeRequestChainProcessor) userContext.getBean(beanName);
		processor.process(userContext,request,beanName);
	}
	
}




