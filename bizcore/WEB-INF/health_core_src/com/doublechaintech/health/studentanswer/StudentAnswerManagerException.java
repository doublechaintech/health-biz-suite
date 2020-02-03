
package com.doublechaintech.health.studentanswer;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class StudentAnswerManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public StudentAnswerManagerException(String string) {
		super(string);
	}
	public StudentAnswerManagerException(Message message) {
		super(message);
	}
	public StudentAnswerManagerException(List<Message> messageList) {
		super(messageList);
	}

}


