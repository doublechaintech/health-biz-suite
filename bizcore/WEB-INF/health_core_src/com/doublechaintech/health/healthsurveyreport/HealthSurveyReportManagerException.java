
package com.doublechaintech.health.healthsurveyreport;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class HealthSurveyReportManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public HealthSurveyReportManagerException(String string) {
		super(string);
	}
	public HealthSurveyReportManagerException(Message message) {
		super(message);
	}
	public HealthSurveyReportManagerException(List<Message> messageList) {
		super(messageList);
	}

}


