
package com.doublechaintech.health.surveystatus;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class SurveyStatusManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public SurveyStatusManagerException(String string) {
		super(string);
	}
	public SurveyStatusManagerException(Message message) {
		super(message);
	}
	public SurveyStatusManagerException(List<Message> messageList) {
		super(messageList);
	}

}


