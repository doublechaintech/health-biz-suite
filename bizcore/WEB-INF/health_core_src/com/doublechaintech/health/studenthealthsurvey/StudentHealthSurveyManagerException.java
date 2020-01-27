
package com.doublechaintech.health.studenthealthsurvey;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class StudentHealthSurveyManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public StudentHealthSurveyManagerException(String string) {
		super(string);
	}
	public StudentHealthSurveyManagerException(Message message) {
		super(message);
	}
	public StudentHealthSurveyManagerException(List<Message> messageList) {
		super(messageList);
	}

}


