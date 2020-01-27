
package com.doublechaintech.health.quicklink;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class QuickLinkManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public QuickLinkManagerException(String string) {
		super(string);
	}
	public QuickLinkManagerException(Message message) {
		super(message);
	}
	public QuickLinkManagerException(List<Message> messageList) {
		super(messageList);
	}

}


