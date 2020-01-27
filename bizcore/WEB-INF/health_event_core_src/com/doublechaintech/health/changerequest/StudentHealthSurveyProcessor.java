package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.HealthUserContext;

public class StudentHealthSurveyProcessor extends ChangeRequestChainProcessor{
	
	
	protected void processInternal(HealthUserContext userContext, ChangeRequest request, String beanName){
		request.getStudentHealthSurveyList().forEach(event->{
			
			handleSingleEvent(userContext,request,event);
		});
	}
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, StudentHealthSurvey event ){
		
		userContext.log("StudentHealthSurveyProcessor\t"+ event +" from processor");
		
		/*
		try {
				Account a1 = accountManagerOf(userContext)
						.loadAccount(userContext, event.getAccount().getId(), new String[] {});
				a1.updateName(event.getName());
				accountManagerOf(userContext).internalSaveAccount(userContext, a1);
		} catch (Exception e) {
				
				e.printStackTrace();
		}*/
	}
	
}


