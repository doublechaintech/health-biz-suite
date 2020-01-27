
package com.doublechaintech.health.wechatuser;
import com.doublechaintech.health.EntityNotFoundException;
public class WechatUserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public WechatUserNotFoundException(String string) {
		super(string);
	}

}

