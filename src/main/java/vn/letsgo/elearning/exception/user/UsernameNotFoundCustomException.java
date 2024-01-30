package vn.letsgo.elearning.exception.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.letsgo.elearning.exception.HandleGlobalException;

@HandleGlobalException
public class UsernameNotFoundCustomException extends UsernameNotFoundException {
    public UsernameNotFoundCustomException(String msg) {
        super(msg);
    }

    public UsernameNotFoundCustomException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
