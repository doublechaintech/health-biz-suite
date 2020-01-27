
package com.doublechaintech.health.questiontype;
import com.doublechaintech.health.EntityNotFoundException;

public class QuestionTypeVersionChangedException extends QuestionTypeManagerException {
	private static final long serialVersionUID = 1L;
	public QuestionTypeVersionChangedException(String string) {
		super(string);
	}


}


