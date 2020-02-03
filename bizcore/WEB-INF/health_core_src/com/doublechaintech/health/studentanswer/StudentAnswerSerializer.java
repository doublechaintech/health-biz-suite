package com.doublechaintech.health.studentanswer;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class StudentAnswerSerializer extends HealthObjectPlainCustomSerializer<StudentAnswer>{

	@Override
	public void serialize(StudentAnswer studentAnswer, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, studentAnswer, provider);
		
	}
}


