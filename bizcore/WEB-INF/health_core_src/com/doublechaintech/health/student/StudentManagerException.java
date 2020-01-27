
package com.doublechaintech.health.student;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class StudentManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public StudentManagerException(String string) {
		super(string);
	}
	public StudentManagerException(Message message) {
		super(message);
	}
	public StudentManagerException(List<Message> messageList) {
		super(messageList);
	}

}


