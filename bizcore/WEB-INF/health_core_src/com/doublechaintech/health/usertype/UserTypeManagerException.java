
package com.doublechaintech.health.usertype;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class UserTypeManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public UserTypeManagerException(String string) {
		super(string);
	}
	public UserTypeManagerException(Message message) {
		super(message);
	}
	public UserTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


