package net.ripe.blog.exception;


import net.ripe.blog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

//@ControllerAdvice used to handle exceptions globally
@ControllerAdvice
public class GlobalExceptionHandler  {

    //handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                        WebRequest webRequest){
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessages,
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(BlogApiException ex,
                                                                        WebRequest webRequest){
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessages,
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //handle specific exceptions

    //global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex,
                                                                        WebRequest webRequest){
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessages,
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //exception for @Valid not valid arguments
    // should extends ResponseEntityExceptionHandler
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error->{
//            String fieldName = ((FieldError)error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationRequestException(MethodArgumentNotValidException ex,
                                                                        WebRequest webRequest){
        List <String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.add("[field name-> " +fieldName + "] : " + message);
        });

        ErrorDetails errorDetails = new ErrorDetails(new Date(), errors,
                webRequest.getDescription(false));


        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    //exception for @Valid not valid arguments

}
