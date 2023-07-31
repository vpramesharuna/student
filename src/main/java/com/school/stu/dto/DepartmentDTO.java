package com.school.stu.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDTO {
    private String departmentName;
    private String description;
}
