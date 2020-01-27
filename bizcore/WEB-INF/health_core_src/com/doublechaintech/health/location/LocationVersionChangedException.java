
package com.doublechaintech.health.location;
import com.doublechaintech.health.EntityNotFoundException;

public class LocationVersionChangedException extends LocationManagerException {
	private static final long serialVersionUID = 1L;
	public LocationVersionChangedException(String string) {
		super(string);
	}


}


