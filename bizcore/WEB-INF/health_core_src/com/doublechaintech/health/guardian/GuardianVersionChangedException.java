
package com.doublechaintech.health.guardian;
import com.doublechaintech.health.EntityNotFoundException;

public class GuardianVersionChangedException extends GuardianManagerException {
	private static final long serialVersionUID = 1L;
	public GuardianVersionChangedException(String string) {
		super(string);
	}


}


