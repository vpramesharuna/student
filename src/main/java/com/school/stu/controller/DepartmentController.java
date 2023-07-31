package com.school.stu.controller;

import com.school.stu.controller.exception.RequestNotFoundException;
import com.school.stu.entity.Department;
import com.school.stu.service.DepartmentService;
import com.school.stu.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>{@code DepartmentController} class acts as the implementor of the
 * {@link com.school.stu.service DepartmentService} where we have
 * currently implemented the register method which handles the end point for
 * currently implemented the registerDepartment method which handles the end point for
 * {@code POST api/v1.0/department : Registers a new department} <em>this creates a new
 * student</em> who is trying to create a student details in our <h2>School Department</h2>
 * using our {@code DepartmentService} which are exposed from
 * to help a new Department register for a <h2>School Department</h2>
 *</p>
 **/
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Slf4j
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("api/v1.0/department")
    public GenericResponse createDepartment(@RequestBody Department department){
        checkifDepartmentDTOIsNullAndThrowError(department);
        departmentService.saveDepartment(department);
        return new GenericResponse("Success");
    }
    @GetMapping("api/v1.0/department")
    public List<Department> getDepartmentList(){
        List<Department> departmentList = departmentService.getAllDepartment();
        return departmentList;
    }

    @CrossOrigin
    @GetMapping("api/v1.0/departmentById")
    public Department getDepartment(@RequestParam("id") String id){
        Department department = departmentService.getDepartmentById(id);
        return department;
    }
    @PutMapping("api/v1.0/updatedepartment")
    public Department updateStudent(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("api/v1.0/deletedepartment")
    public GenericResponse deleteStudent(@RequestParam("id") String id){
        return departmentService.deleteDepartment(id);
    }

    private void checkifDepartmentDTOIsNullAndThrowError(Department department) {
        if(department==null){
            throw  new RequestNotFoundException("Department is not found");
        }
    }

}
