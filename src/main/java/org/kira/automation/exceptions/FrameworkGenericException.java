package org.kira.automation.exceptions;

public class FrameworkGenericException extends RuntimeException {
    public FrameworkGenericException (String message) {
        super(message);
    }

    public FrameworkGenericException (String message, Exception exception) {
        super(message, exception);
    }

}
