package com.doublechaintech.health.schoolclass;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class SchoolClassSerializer extends HealthObjectPlainCustomSerializer<SchoolClass>{

	@Override
	public void serialize(SchoolClass schoolClass, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, schoolClass, provider);
		
	}
}


