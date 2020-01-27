
package com.doublechaintech.health.city;
import com.doublechaintech.health.EntityNotFoundException;

public class CityVersionChangedException extends CityManagerException {
	private static final long serialVersionUID = 1L;
	public CityVersionChangedException(String string) {
		super(string);
	}


}


