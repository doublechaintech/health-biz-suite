
package com.doublechaintech.health.question;
import com.doublechaintech.health.EntityNotFoundException;

public class QuestionVersionChangedException extends QuestionManagerException {
	private static final long serialVersionUID = 1L;
	public QuestionVersionChangedException(String string) {
		super(string);
	}


}


