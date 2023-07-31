package com.school.stu.util;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GenericResponse {

    private String message;

    public GenericResponse(String message){
        this.message = message;
    }
}
