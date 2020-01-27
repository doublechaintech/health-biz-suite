package com.doublechaintech.health.surveystatus;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class SurveyStatusSerializer extends HealthObjectPlainCustomSerializer<SurveyStatus>{

	@Override
	public void serialize(SurveyStatus surveyStatus, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, surveyStatus, provider);
		
	}
}


