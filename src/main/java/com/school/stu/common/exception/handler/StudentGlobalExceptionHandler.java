package com.school.stu.common.exception.handler;

import com.school.stu.common.errormsg.ErrorResponse;
import com.school.stu.controller.exception.RequestNotFoundException;
import com.school.stu.service.exception.StudentBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class StudentGlobalExceptionHandler {

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleStudentRequestNotFoundException(
            RequestNotFoundException studentRequestNotFoundException,
            WebRequest request
    ){
        log.error("Failed to find the requested Student data ", studentRequestNotFoundException);
        return buildErrorResponse(studentRequestNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(StudentBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleStudentBusinessException(
            StudentBusinessException studentBusinessException,
            WebRequest webRequest
    ){
        log.error("Failed to save the requested student, exception is {} ", studentBusinessException);
        return buildErrorResponse(studentBusinessException, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception exception,
            WebRequest request){
        log.error("Unknown error occurred", exception);
        return buildErrorResponse(
                exception,
                "Unknown error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable
    Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request){
        return buildGeneralErrorResponse(ex,statusCode,request);
    }


    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        return buildErrorResponse(
                exception,
                exception.getMessage(),
                httpStatus,
                request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        Integer errorCode = Integer.valueOf(httpStatus.value());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorReason(message);
        errorResponse.setErrorSource(exception.getStackTrace().toString());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private ResponseEntity<Object> buildGeneralErrorResponse(
            Exception exception,HttpStatusCode status, WebRequest request
    ){
        Integer errorCode = Integer.valueOf(status.value());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorReason(exception.getMessage());
        errorResponse.setErrorSource(exception.getStackTrace().toString());
        return ResponseEntity.status(status).body(errorResponse);
    }

}