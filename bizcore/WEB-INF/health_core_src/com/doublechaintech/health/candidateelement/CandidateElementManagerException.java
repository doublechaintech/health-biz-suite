
package com.doublechaintech.health.candidateelement;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class CandidateElementManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public CandidateElementManagerException(String string) {
		super(string);
	}
	public CandidateElementManagerException(Message message) {
		super(message);
	}
	public CandidateElementManagerException(List<Message> messageList) {
		super(messageList);
	}

}


