package com.doublechaintech.health.wechatuser;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class WechatUserSerializer extends HealthObjectPlainCustomSerializer<WechatUser>{

	@Override
	public void serialize(WechatUser wechatUser, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, wechatUser, provider);
		
	}
}


