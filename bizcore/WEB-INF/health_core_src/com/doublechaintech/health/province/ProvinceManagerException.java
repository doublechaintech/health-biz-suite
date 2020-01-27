
package com.doublechaintech.health.province;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class ProvinceManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public ProvinceManagerException(String string) {
		super(string);
	}
	public ProvinceManagerException(Message message) {
		super(message);
	}
	public ProvinceManagerException(List<Message> messageList) {
		super(messageList);
	}

}


