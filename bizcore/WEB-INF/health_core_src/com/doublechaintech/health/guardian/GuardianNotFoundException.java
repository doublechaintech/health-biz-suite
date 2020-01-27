
package com.doublechaintech.health.guardian;
import com.doublechaintech.health.EntityNotFoundException;
public class GuardianNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public GuardianNotFoundException(String string) {
		super(string);
	}

}

