package academy.devDojo.springEssentials.controller.handle;

import academy.devDojo.springEssentials.exceptions.BadRequestException;
import academy.devDojo.springEssentials.exceptions.BadRequestExceptionDetails;
import academy.devDojo.springEssentials.exceptions.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandle {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, check the Documentation")
                        .details(badRequestException.getMessage())
                        .message(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleBadRequestException(MethodArgumentNotValidException exception){
        var fieldErrors = exception.getBindingResult().getFieldErrors();
        var fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        var fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, invalid fields")
                        .details(exception.getMessage())
                        .message(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
