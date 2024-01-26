package letsgo.vn.elearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EmptyFieldsException.class})
    public ResponseEntity<Object> emptyFieldsException(EmptyFieldsException e) {
        GlobalExceptionPayload globalExceptionPayload = new GlobalExceptionPayload(
                e.getMessage(),
                e,
                BAD_REQUEST
        );
        return new ResponseEntity<>(globalExceptionPayload, BAD_REQUEST);
    }

}
