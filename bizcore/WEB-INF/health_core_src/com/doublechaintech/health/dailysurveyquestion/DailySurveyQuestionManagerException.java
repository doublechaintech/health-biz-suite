
package com.doublechaintech.health.dailysurveyquestion;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class DailySurveyQuestionManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public DailySurveyQuestionManagerException(String string) {
		super(string);
	}
	public DailySurveyQuestionManagerException(Message message) {
		super(message);
	}
	public DailySurveyQuestionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


