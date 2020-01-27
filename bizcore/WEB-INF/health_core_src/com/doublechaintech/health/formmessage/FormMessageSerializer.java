package com.doublechaintech.health.formmessage;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class FormMessageSerializer extends HealthObjectPlainCustomSerializer<FormMessage>{

	@Override
	public void serialize(FormMessage formMessage, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, formMessage, provider);
		
	}
}


