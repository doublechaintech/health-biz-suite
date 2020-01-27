
package com.doublechaintech.health.teacher;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class TeacherManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public TeacherManagerException(String string) {
		super(string);
	}
	public TeacherManagerException(Message message) {
		super(message);
	}
	public TeacherManagerException(List<Message> messageList) {
		super(messageList);
	}

}


