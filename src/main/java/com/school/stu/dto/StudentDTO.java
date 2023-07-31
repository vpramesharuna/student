package com.school.stu.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentDTO {
    private String name;
    private String age;
    private String department;
}