package com.school.stu.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

public class StudentBusinessException extends RuntimeException {

    public StudentBusinessException(String message){
        super(message);
    }

    public StudentBusinessException(String message, Throwable cause){
        super(message, cause);
    }

}