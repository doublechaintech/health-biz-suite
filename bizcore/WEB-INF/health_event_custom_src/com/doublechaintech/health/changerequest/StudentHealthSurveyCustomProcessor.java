package  com.doublechaintech.health.changerequest;

import java.util.Arrays;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.HealthUserContext;

public class StudentHealthSurveyCustomProcessor extends StudentHealthSurveyProcessor{
	
	
	
	protected void handleSingleEvent(HealthUserContext userContext, ChangeRequest request, StudentHealthSurvey event ){
		
		userContext.log("StudentHealthSurveyCustomProcessor\t"+ event +" from processor");
		
		event.getStudentDailyAnswerList().forEach(answer->{try {
			studentDailyAnswerManagerOf(userContext).internalSaveStudentDailyAnswer(userContext, answer);
		studentManagerOf(userContext).internalSaveStudent(userContext, event.getStudent());
		} catch (Exception e) {
			e.printStackTrace();
		}});
	}
	
}





