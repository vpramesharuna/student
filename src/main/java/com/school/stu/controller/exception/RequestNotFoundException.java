package com.school.stu.controller.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException {

    String errorMessage;
    public RequestNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
