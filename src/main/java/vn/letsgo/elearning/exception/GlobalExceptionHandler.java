package vn.letsgo.elearning.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.letsgo.elearning.exception.GlobalExceptionPayload;
import vn.letsgo.elearning.exception.HandleGlobalException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGlobalException(Exception e) throws Exception {
        if (isAnnotatedWithHandleGlobalException(e)) {
            GlobalExceptionPayload globalExceptionPayload = new GlobalExceptionPayload(
                    e.getMessage(),
                    BAD_REQUEST
            );
            return new ResponseEntity<>(globalExceptionPayload, BAD_REQUEST);
        } else {
            // Handle other exceptions or rethrow if necessary
            throw e;
        }
    }

    private boolean isAnnotatedWithHandleGlobalException(Exception e) {
        return e.getClass().isAnnotationPresent(HandleGlobalException.class);
    }
}
