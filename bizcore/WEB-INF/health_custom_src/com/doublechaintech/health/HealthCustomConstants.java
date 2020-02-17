package com.doublechaintech.health;

import com.terapico.caf.viewpage.SerializeScope;

public class HealthCustomConstants extends HealthBaseConstants {
	public static final String ROOT_PLATFORM_ID = "P000001";
	public static final String ROOT_USER_DOMAIN_ID = "UD000001";
	
	public static final String LINK_TO_URL="linkToUrl";

	public static final SerializeScope ACTION_SCOPE = SerializeScope
			.INCLUDE()
				.field("id")
				.field("code")
				.field("title")
				.field("icon")
				.field("linkToUrl");
									
									
}
