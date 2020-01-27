
package com.doublechaintech.health.schoolclass;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class SchoolClassManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public SchoolClassManagerException(String string) {
		super(string);
	}
	public SchoolClassManagerException(Message message) {
		super(message);
	}
	public SchoolClassManagerException(List<Message> messageList) {
		super(messageList);
	}

}


