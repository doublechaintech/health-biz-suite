package com.doublechaintech.health.questiontype;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class QuestionTypeSerializer extends HealthObjectPlainCustomSerializer<QuestionType>{

	@Override
	public void serialize(QuestionType questionType, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, questionType, provider);
		
	}
}


