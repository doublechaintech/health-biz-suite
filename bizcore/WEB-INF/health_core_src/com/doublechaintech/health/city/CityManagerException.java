
package com.doublechaintech.health.city;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class CityManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public CityManagerException(String string) {
		super(string);
	}
	public CityManagerException(Message message) {
		super(message);
	}
	public CityManagerException(List<Message> messageList) {
		super(messageList);
	}

}


