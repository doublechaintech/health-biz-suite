package com.doublechaintech.health.teacher;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class TeacherSerializer extends HealthObjectPlainCustomSerializer<Teacher>{

	@Override
	public void serialize(Teacher teacher, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, teacher, provider);
		
	}
}


