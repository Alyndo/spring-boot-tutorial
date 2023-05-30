package com.alwyn.techie.exception;

import com.alwyn.techie.dto.ExceptionResponseRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static com.alwyn.techie.exception.ErrorCode.CD_NOT_FOUND_1;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponseRecord> handleNotFoundExceptions(NotFoundException exception, WebRequest webRequest){

        log.error("handleNotFoundExceptions", exception);

        ExceptionResponseRecord exceptionResponseRecord = new ExceptionResponseRecord(LocalDateTime.now(),
                exception.getMessage(), CD_NOT_FOUND_1);

        return new ResponseEntity<>(exceptionResponseRecord, HttpStatus.NOT_FOUND);
    }
}
