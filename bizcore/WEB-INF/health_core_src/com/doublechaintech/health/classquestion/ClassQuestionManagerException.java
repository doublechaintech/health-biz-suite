
package com.doublechaintech.health.classquestion;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class ClassQuestionManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public ClassQuestionManagerException(String string) {
		super(string);
	}
	public ClassQuestionManagerException(Message message) {
		super(message);
	}
	public ClassQuestionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


