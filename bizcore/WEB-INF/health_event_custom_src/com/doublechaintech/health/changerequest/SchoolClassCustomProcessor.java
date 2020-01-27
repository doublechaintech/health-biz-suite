package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.schoolclass.SchoolClass;
import com.doublechaintech.health.HealthUserContext;

public class SchoolClassCustomProcessor extends SchoolClassProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, SchoolClass event ){
		
		userContext.log("SchoolClassCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





