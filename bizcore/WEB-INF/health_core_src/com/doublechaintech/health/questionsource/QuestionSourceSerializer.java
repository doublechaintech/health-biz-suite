package com.doublechaintech.health.questionsource;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class QuestionSourceSerializer extends HealthObjectPlainCustomSerializer<QuestionSource>{

	@Override
	public void serialize(QuestionSource questionSource, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, questionSource, provider);
		
	}
}


