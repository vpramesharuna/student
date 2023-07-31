package com.school.stu.service.impl;

import com.school.stu.entity.Department;
import com.school.stu.entity.Student;
import com.school.stu.repository.DepartmentRepository;
import com.school.stu.service.DepartmentService;
import com.school.stu.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public GenericResponse deleteDepartment(String id) {
        departmentRepository.deleteById(UUID.fromString(id));
        return new GenericResponse("Successfully Department Deleted");
    }

    @Override
    public Department getDepartmentById(String id) {
        Optional<Department> department = departmentRepository.findById(UUID.fromString(id));
        if(!department.isEmpty())
            return department.get();
        else
            return null;
    }
}
