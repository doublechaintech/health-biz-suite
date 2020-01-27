
package com.doublechaintech.health.questionsource;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class QuestionSourceManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public QuestionSourceManagerException(String string) {
		super(string);
	}
	public QuestionSourceManagerException(Message message) {
		super(message);
	}
	public QuestionSourceManagerException(List<Message> messageList) {
		super(messageList);
	}

}


