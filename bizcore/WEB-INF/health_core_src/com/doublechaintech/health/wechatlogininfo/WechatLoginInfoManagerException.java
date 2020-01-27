
package com.doublechaintech.health.wechatlogininfo;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class WechatLoginInfoManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public WechatLoginInfoManagerException(String string) {
		super(string);
	}
	public WechatLoginInfoManagerException(Message message) {
		super(message);
	}
	public WechatLoginInfoManagerException(List<Message> messageList) {
		super(messageList);
	}

}


