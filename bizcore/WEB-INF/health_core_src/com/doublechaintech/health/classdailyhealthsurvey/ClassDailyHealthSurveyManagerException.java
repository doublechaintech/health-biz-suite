
package com.doublechaintech.health.classdailyhealthsurvey;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class ClassDailyHealthSurveyManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public ClassDailyHealthSurveyManagerException(String string) {
		super(string);
	}
	public ClassDailyHealthSurveyManagerException(Message message) {
		super(message);
	}
	public ClassDailyHealthSurveyManagerException(List<Message> messageList) {
		super(messageList);
	}

}


