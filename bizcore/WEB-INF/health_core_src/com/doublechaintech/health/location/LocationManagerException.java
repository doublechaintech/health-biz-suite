
package com.doublechaintech.health.location;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class LocationManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public LocationManagerException(String string) {
		super(string);
	}
	public LocationManagerException(Message message) {
		super(message);
	}
	public LocationManagerException(List<Message> messageList) {
		super(messageList);
	}

}


