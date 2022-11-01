package backend.pretest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdviser {

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBaseNotFoundException(BaseNotFoundException e) {

        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BaseBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBaseBadRequestException(BaseBadRequestException e) {

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleBaseForbiddenException(BaseForbiddenException e) {

        ErrorResponse response = new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BaseInternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleBaseInternalServerErrorException(BaseInternalServerErrorException e) {

        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record ErrorResponse(LocalDateTime timestamp, int status, String message) {
        public ErrorResponse(int status, String message) {
            this(LocalDateTime.now(), status, message);
        }
    }

}
