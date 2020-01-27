
package com.doublechaintech.health.district;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class DistrictManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public DistrictManagerException(String string) {
		super(string);
	}
	public DistrictManagerException(Message message) {
		super(message);
	}
	public DistrictManagerException(List<Message> messageList) {
		super(messageList);
	}

}


