/*
	ChangeRequestBaseHandler 最基础的Handler，如果这些ChangeRequest都按照Event规则来处理，
	那么有这个ChangeRequestBaseHandler对于大多数系统就足够了。
*/
package  com.doublechaintech.health.changerequest;
import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthCheckerManager;
public class ChangeRequestChainProcessor extends CustomHealthCheckerManager{
	
	protected void preProcess(HealthUserContext userContext, ChangeRequest request, String beanName){
		
	}
	public void process(HealthUserContext userContext, ChangeRequest request, String beanName){
		preProcess(userContext, request, beanName);
		processInternal(userContext, request, beanName);
		postProcess(userContext, request, beanName);
	}
	protected void postProcess(HealthUserContext userContext, ChangeRequest request, String beanName){
		
	}
	protected void processInternal(HealthUserContext userContext, ChangeRequest request, String beanName){
		
	}
	
}




