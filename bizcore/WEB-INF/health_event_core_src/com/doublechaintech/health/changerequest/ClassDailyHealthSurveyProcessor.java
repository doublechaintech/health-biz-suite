package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.HealthUserContext;

public class ClassDailyHealthSurveyProcessor extends ChangeRequestChainProcessor{
	
	
	protected void processInternal(HealthUserContext userContext, ChangeRequest request, String beanName){
		request.getClassDailyHealthSurveyList().forEach(event->{
			
			handleSingleEvent(userContext,request,event);
		});
	}
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, ClassDailyHealthSurvey event ){
		
		userContext.log("ClassDailyHealthSurveyProcessor\t"+ event +" from processor");
		
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


