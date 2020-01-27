package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.HealthUserContext;

public class GuardianCustomProcessor extends GuardianProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, Guardian event ){
		
		userContext.log("GuardianCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





