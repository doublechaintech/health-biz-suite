package com.doublechaintech.health.platform;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class PlatformSerializer extends HealthObjectPlainCustomSerializer<Platform>{

	@Override
	public void serialize(Platform platform, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, platform, provider);
		
	}
}


