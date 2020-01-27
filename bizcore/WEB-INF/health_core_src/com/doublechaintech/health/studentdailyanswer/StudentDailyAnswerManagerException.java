
package com.doublechaintech.health.studentdailyanswer;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class StudentDailyAnswerManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public StudentDailyAnswerManagerException(String string) {
		super(string);
	}
	public StudentDailyAnswerManagerException(Message message) {
		super(message);
	}
	public StudentDailyAnswerManagerException(List<Message> messageList) {
		super(messageList);
	}

}


