package com.doublechaintech.health.changerequesttype;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class ChangeRequestTypeSerializer extends HealthObjectPlainCustomSerializer<ChangeRequestType>{

	@Override
	public void serialize(ChangeRequestType changeRequestType, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, changeRequestType, provider);
		
	}
}


