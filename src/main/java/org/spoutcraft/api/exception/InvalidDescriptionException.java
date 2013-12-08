package org.spoutcraft.api.exception;

public class InvalidDescriptionException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String message;
    private final Throwable cause;

    public InvalidDescriptionException(String message) {
        this(message, null);
    }

    public InvalidDescriptionException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final InvalidDescriptionException that = (InvalidDescriptionException) o;

        return !(cause != null ? !cause.equals(that.cause) : that.cause != null) && !(message != null ? !message.equals(that.message) : that.message != null);
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }
}
