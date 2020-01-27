package com.doublechaintech.health.quicklink;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class QuickLinkSerializer extends HealthObjectPlainCustomSerializer<QuickLink>{

	@Override
	public void serialize(QuickLink quickLink, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, quickLink, provider);
		
	}
}


