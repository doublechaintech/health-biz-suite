
package com.doublechaintech.health.candidatecontainer;
//import com.doublechaintech.health.EntityNotFoundException;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.Message;
import java.util.List;

public class CandidateContainerManagerException extends HealthException {
	private static final long serialVersionUID = 1L;
	public CandidateContainerManagerException(String string) {
		super(string);
	}
	public CandidateContainerManagerException(Message message) {
		super(message);
	}
	public CandidateContainerManagerException(List<Message> messageList) {
		super(messageList);
	}

}


