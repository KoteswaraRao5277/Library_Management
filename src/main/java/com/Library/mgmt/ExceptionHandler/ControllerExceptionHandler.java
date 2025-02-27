package com.Library.mgmt.ExceptionHandler;

import com.Library.mgmt.Exception.BookException;
import com.Library.mgmt.Exception.TxnException;
import com.Library.mgmt.Exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
@ControllerAdvice
public class ControllerExceptionHandler {
    // any exceptions will be coming, at that time how to handle it,,, without interrupting business logic

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException e){
        return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Object> handleTxnException(TxnException txnException){
        return new ResponseEntity<>(txnException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookException.class)
    public ResponseEntity<Object> handleBookException(BookException txnExcetion){
        return new ResponseEntity<>(txnExcetion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> handleUserException(UserException txnExcetion){
        return new ResponseEntity<>(txnExcetion.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
