package me.dio.exceptionHandler;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalException (IllegalArgumentException bussException) {
        return new ResponseEntity<>(bussException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement (NoSuchElementException noSuchElementException) {
        return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable throwable) {
        logger.error(throwable.getMessage());
        return  new ResponseEntity<>("Erro no servidor, veja os logs", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
