package backend.pretest.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdviser {

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBaseNotFoundException(BaseNotFoundException e) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BaseBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBaseBadRequestException(BaseBadRequestException e) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BaseForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleBaseForbiddenException(BaseForbiddenException e) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BaseInternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleBaseInternalServerErrorException(BaseInternalServerErrorException e) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    public static class ErrorResponse {
        private LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String message;
    }

}
