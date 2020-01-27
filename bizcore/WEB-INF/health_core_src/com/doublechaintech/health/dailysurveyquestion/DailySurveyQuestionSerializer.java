package com.doublechaintech.health.dailysurveyquestion;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.health.HealthObjectPlainCustomSerializer;
public class DailySurveyQuestionSerializer extends HealthObjectPlainCustomSerializer<DailySurveyQuestion>{

	@Override
	public void serialize(DailySurveyQuestion dailySurveyQuestion, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, dailySurveyQuestion, provider);
		
	}
}


