
package com.doublechaintech.health.user;
import com.doublechaintech.health.EntityNotFoundException;

public class UserVersionChangedException extends UserManagerException {
	private static final long serialVersionUID = 1L;
	public UserVersionChangedException(String string) {
		super(string);
	}


}


