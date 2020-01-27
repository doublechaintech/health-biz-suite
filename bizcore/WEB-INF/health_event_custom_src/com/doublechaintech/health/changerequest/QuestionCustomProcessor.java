package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.HealthUserContext;

public class QuestionCustomProcessor extends QuestionProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, Question event ){
		
		userContext.log("QuestionCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





