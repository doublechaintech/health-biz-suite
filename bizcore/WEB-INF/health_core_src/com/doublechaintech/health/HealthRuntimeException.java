
package com.doublechaintech.health;


public class HealthRuntimeException extends RuntimeException {
    static final long serialVersionUID = -1;

    public HealthRuntimeException() {
        super();
    }


    public HealthRuntimeException(String message) {
        super(message);
    }


    public HealthRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }


    public HealthRuntimeException(Throwable cause) {
        super(cause);
    }


   
}








