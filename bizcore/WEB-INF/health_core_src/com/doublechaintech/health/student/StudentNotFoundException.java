
package com.doublechaintech.health.student;
import com.doublechaintech.health.EntityNotFoundException;
public class StudentNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public StudentNotFoundException(String string) {
		super(string);
	}

}

