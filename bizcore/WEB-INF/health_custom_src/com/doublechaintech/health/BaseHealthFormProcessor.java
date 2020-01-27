package com.doublechaintech.health;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.terapico.caf.form.ImageInfo;
import com.terapico.utils.DebugUtil;

public class BaseHealthFormProcessor extends BaseFormProcessor{
	protected HealthUserContext userContext;
	
	public HealthUserContext getUserContext() {
		return userContext;
	}
	public void setUserContext(HealthUserContext userContext) {
		this.userContext = userContext;
	}
	protected void addMessageToException(HealthException e, String msg) {
		Message message = new Message();
		message.setBody(msg);
		e.addErrorMessage(message);
	}
	public Map<String, Object> mapToUiForm(HealthUserContext userContext) {
		return null; 
	}
}





















