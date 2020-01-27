
package com.doublechaintech.health.student;
import com.doublechaintech.health.EntityNotFoundException;

public class StudentVersionChangedException extends StudentManagerException {
	private static final long serialVersionUID = 1L;
	public StudentVersionChangedException(String string) {
		super(string);
	}


}


