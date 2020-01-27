package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.HealthUserContext;

public class TeacherCustomProcessor extends TeacherProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, Teacher event ){
		
		userContext.log("TeacherCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





