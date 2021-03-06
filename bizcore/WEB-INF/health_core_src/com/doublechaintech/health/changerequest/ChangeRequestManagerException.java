
package com.doublechaintech.health.changerequest;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class ChangeRequestManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestManagerException(String string) {
		super(string);
	}
	public ChangeRequestManagerException(Message message) {
		super(message);
	}
	public ChangeRequestManagerException(List<Message> messageList) {
		super(messageList);
	}

}


