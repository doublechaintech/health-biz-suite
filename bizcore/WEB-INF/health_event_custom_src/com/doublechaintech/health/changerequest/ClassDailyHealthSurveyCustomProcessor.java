package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.HealthUserContext;

public class ClassDailyHealthSurveyCustomProcessor extends ClassDailyHealthSurveyProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, ClassDailyHealthSurvey event ){
		
		userContext.log("ClassDailyHealthSurveyCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





