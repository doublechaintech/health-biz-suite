
package com.doublechaintech.health.wechatuser;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class WechatUserManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public WechatUserManagerException(String string) {
		super(string);
	}
	public WechatUserManagerException(Message message) {
		super(message);
	}
	public WechatUserManagerException(List<Message> messageList) {
		super(messageList);
	}

}


