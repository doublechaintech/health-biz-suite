
package com.doublechaintech.health.user;
import com.doublechaintech.health.EntityNotFoundException;
public class UserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String string) {
		super(string);
	}

}

