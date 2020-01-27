
package com.doublechaintech.health.schoolclass;
import com.doublechaintech.health.EntityNotFoundException;

public class SchoolClassVersionChangedException extends SchoolClassManagerException {
	private static final long serialVersionUID = 1L;
	public SchoolClassVersionChangedException(String string) {
		super(string);
	}


}


