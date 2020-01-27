package com.doublechaintech.health.iamservice;

import com.doublechaintech.health.HealthUserContext;

public interface IdentificationHandler {

	/** 完成认证 */
	LoginResult authenticate(HealthUserContext userContext, LoginContext loginContext);
	/** 绑定用户 */
	void bindWithSecUser(HealthUserContext userContext, LoginContext loginContext) throws Exception;
}






