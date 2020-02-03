
/*
	本类暂时没有很复杂的代码，这个类用于保留以后智能化推断代码
*/

package  com.doublechaintech.health.changerequest;


import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.HealthUserContext;

public class AddClassHandler extends ChangeRequestBaseHandler{
	@Override	
	protected void checkIfComplyWithSpec(HealthUserContext userContext, ChangeRequest request){
		super.checkIfComplyWithSpec(userContext,request);
	}
}

