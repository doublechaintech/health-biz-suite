
package com.doublechaintech.health.userdomain;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class UserDomainManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public UserDomainManagerException(String string) {
		super(string);
	}
	public UserDomainManagerException(Message message) {
		super(message);
	}
	public UserDomainManagerException(List<Message> messageList) {
		super(messageList);
	}

}


