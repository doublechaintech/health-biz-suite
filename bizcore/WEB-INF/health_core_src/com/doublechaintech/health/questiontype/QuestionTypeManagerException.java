
package com.doublechaintech.health.questiontype;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class QuestionTypeManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public QuestionTypeManagerException(String string) {
		super(string);
	}
	public QuestionTypeManagerException(Message message) {
		super(message);
	}
	public QuestionTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


