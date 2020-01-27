
package com.doublechaintech.health.usertype;
import com.doublechaintech.health.EntityNotFoundException;

public class UserTypeVersionChangedException extends UserTypeManagerException {
	private static final long serialVersionUID = 1L;
	public UserTypeVersionChangedException(String string) {
		super(string);
	}


}


