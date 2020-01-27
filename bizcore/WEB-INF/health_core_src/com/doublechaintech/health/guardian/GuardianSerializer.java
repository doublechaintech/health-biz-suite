package com.doublechaintech.health.guardian;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class GuardianSerializer extends HealthObjectPlainCustomSerializer<Guardian>{

	@Override
	public void serialize(Guardian guardian, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, guardian, provider);
		
	}
}


