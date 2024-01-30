package vn.letsgo.elearning.exception.user;

import vn.letsgo.elearning.exception.HandleGlobalException;

@HandleGlobalException
public class UsernameAlreadyExistsException extends RuntimeException {

    private final String username;

    public UsernameAlreadyExistsException(String username) {
        super("Username '" + username + "' already exists.");
        this.username = username;
    }

    public UsernameAlreadyExistsException() {
        this("Username already exists.");
    }

    public UsernameAlreadyExistsException(String message, Throwable cause, String username) {
        super(message, cause);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
