
package com.doublechaintech.health.user;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class UserManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public UserManagerException(String string) {
		super(string);
	}
	public UserManagerException(Message message) {
		super(message);
	}
	public UserManagerException(List<Message> messageList) {
		super(messageList);
	}

}


