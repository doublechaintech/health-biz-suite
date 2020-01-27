
package com.doublechaintech.health.platform;
import com.doublechaintech.health.EntityNotFoundException;

public class PlatformVersionChangedException extends PlatformManagerException {
	private static final long serialVersionUID = 1L;
	public PlatformVersionChangedException(String string) {
		super(string);
	}


}


