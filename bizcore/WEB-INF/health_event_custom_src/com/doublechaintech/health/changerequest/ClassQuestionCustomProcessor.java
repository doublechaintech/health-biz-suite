package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.HealthUserContext;

public class ClassQuestionCustomProcessor extends ClassQuestionProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, ClassQuestion event ){
		
		userContext.log("ClassQuestionCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





