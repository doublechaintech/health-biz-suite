package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.HealthUserContext;

public class StudentCustomProcessor extends StudentProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, Student event ){
		
		userContext.log("StudentCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





