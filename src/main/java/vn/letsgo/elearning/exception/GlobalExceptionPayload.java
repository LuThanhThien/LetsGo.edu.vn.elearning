package vn.letsgo.elearning.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class GlobalExceptionPayload {
    private final String message;

    private final HttpStatus httpStatus;

    private final LocalDateTime timeStamp = LocalDateTime.now();
}
