package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.HealthUserContext;

public class StudentHealthSurveyCustomProcessor extends StudentHealthSurveyProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, StudentHealthSurvey event ){
		
		userContext.log("StudentHealthSurveyCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





