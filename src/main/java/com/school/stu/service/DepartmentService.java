package com.school.stu.service;

import com.school.stu.dto.DepartmentDTO;
import com.school.stu.entity.Department;
import com.school.stu.util.GenericResponse;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> getAllDepartment();

    public Department updateDepartment(Department department);

    public GenericResponse deleteDepartment(String id);

    public Department getDepartmentById(String id);
}
