
package com.doublechaintech.health.changerequest;
import com.doublechaintech.health.EntityNotFoundException;

public class ChangeRequestVersionChangedException extends ChangeRequestManagerException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestVersionChangedException(String string) {
		super(string);
	}


}


