package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.HealthUserContext;

public class StudentDailyAnswerCustomProcessor extends StudentDailyAnswerProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, StudentDailyAnswer event ){
		
		userContext.log("StudentDailyAnswerCustomProcessor\t"+ event +" from processor");
		try {
			studentDailyAnswerManagerOf(userContext).internalSaveStudentDailyAnswer(userContext, event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}











