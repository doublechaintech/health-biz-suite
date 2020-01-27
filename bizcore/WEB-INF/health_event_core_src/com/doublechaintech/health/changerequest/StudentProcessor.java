package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.HealthUserContext;

public class StudentProcessor extends ChangeRequestChainProcessor{
	
	
	protected void processInternal(HealthUserContext userContext, ChangeRequest request, String beanName){
		request.getStudentList().forEach(event->{
			
			handleSingleEvent(userContext,request,event);
		});
	}
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, Student event ){
		
		userContext.log("StudentProcessor\t"+ event +" from processor");
		
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


