package vn.letsgo.elearning.exception.user;


import vn.letsgo.elearning.exception.HandleGlobalException;

@HandleGlobalException
public class NotAuthenticatedException extends IllegalStateException {

    public NotAuthenticatedException() {
        super("User is not authenticated!");
    }

    public NotAuthenticatedException(String message) {
        super(message);
    }

    public NotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

}
