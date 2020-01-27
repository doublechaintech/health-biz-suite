
package com.doublechaintech.health.changerequesttype;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class ChangeRequestTypeManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestTypeManagerException(String string) {
		super(string);
	}
	public ChangeRequestTypeManagerException(Message message) {
		super(message);
	}
	public ChangeRequestTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


