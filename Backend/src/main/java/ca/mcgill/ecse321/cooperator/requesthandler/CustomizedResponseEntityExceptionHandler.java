/*
 * A PORTION OF THIS CODE WAS SOURCED FROM A SPRING BOOT TUTORIAL:
 * http://www.springboottutorial.com/spring-boot-validation-for-rest-services
 */
package ca.mcgill.ecse321.cooperator.requesthandler;

import java.util.Date;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), "Validation Failed", ex.getBindingResult().toString());
    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<Object> handleEntityNotFoundExceptions(EntityNotFoundException ex,
      WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @ExceptionHandler(EntityExistsException.class)
  public final ResponseEntity<Object> handleEntityExistsExceptions(EntityExistsException ex,
      WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.I_AM_A_TEAPOT);
  }

}
