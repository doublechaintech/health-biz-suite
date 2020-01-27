
package com.doublechaintech.health.usertype;
import com.doublechaintech.health.EntityNotFoundException;
public class UserTypeNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserTypeNotFoundException(String string) {
		super(string);
	}

}

