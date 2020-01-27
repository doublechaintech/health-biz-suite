
package com.doublechaintech.health.wechatminiappidentify;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class WechatMiniappIdentifyManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public WechatMiniappIdentifyManagerException(String string) {
		super(string);
	}
	public WechatMiniappIdentifyManagerException(Message message) {
		super(message);
	}
	public WechatMiniappIdentifyManagerException(List<Message> messageList) {
		super(messageList);
	}

}









