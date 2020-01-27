package com.doublechaintech.health.iamservice;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.doublechaintech.health.HealthUserContext;

public abstract class BaseIdentificationHandler implements IdentificationHandler{
	protected static final Map<String, Object> EO = new HashMap<>();
	
	protected String getVeriyCodeCacheKey(HealthUserContext userContext, String mobile) {
		return String.format("vcode:%s:%s", mobile, userContext.tokenId());
	}

	protected String getWehatSessionKeyCacheKey(HealthUserContext userContext, String appId, String openId) {
		return String.format("wechatSession:%s:%s:%s", openId, appId, userContext.tokenId());
	}

	protected String hashStringWithSHA256(String valueToHash, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			String textToHash = valueToHash+":"+salt;
			byte[] hash = digest.digest(textToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder stringBuilder = new StringBuilder();
		    for (byte b : hash) {
		        stringBuilder.append(String.format("%02X", b));
		    }
		    return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
		
	}

}






