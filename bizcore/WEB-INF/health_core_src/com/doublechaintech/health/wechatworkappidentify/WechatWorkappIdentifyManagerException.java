
package com.doublechaintech.health.wechatworkappidentify;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class WechatWorkappIdentifyManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public WechatWorkappIdentifyManagerException(String string) {
		super(string);
	}
	public WechatWorkappIdentifyManagerException(Message message) {
		super(message);
	}
	public WechatWorkappIdentifyManagerException(List<Message> messageList) {
		super(messageList);
	}

}


