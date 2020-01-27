package com.doublechaintech.health.studenthealthsurvey;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class StudentHealthSurveySerializer extends HealthObjectPlainCustomSerializer<StudentHealthSurvey>{

	@Override
	public void serialize(StudentHealthSurvey studentHealthSurvey, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, studentHealthSurvey, provider);
		
	}
}


