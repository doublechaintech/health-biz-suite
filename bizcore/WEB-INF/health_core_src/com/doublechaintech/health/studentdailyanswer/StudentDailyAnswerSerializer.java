package com.doublechaintech.health.studentdailyanswer;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class StudentDailyAnswerSerializer extends HealthObjectPlainCustomSerializer<StudentDailyAnswer>{

	@Override
	public void serialize(StudentDailyAnswer studentDailyAnswer, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, studentDailyAnswer, provider);
		
	}
}


