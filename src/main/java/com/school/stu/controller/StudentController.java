package com.school.stu.controller;

import com.school.stu.common.errormsg.ErrorMessages;
import com.school.stu.controller.exception.RequestNotFoundException;
import com.school.stu.entity.Student;
import jakarta.websocket.server.PathParam;
import org.apache.commons.lang3.ObjectUtils;
import com.school.stu.service.StudentService;
import com.school.stu.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
    * <p>{@code StudentController} class acts as the implementor of the
    * {@link com.school.stu.service.StudentService} where we have
    * currently implemented the registerStudent method which handles the end point for
    * {@code POST api/v1.0/student : Registers a new Student} <em>this creates a new
    * student</em> who is trying to create a student details in our <h2>School</h2>
    * using our {@code StudentService} which are exposed from
    * to help a new Student register for a <h2>School</h2>
    *</p>
 **/
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("api/v1.0/student")
    public GenericResponse createStudent(@RequestBody Student student){
       checkifStudentDTOIsNullAndThrowError(student);
       studentService.saveStudent(student);
       return new GenericResponse("Success");
    }

    @GetMapping("api/v1.0/student")
    public List<Student> getStudent(){
        List<Student> studentList = studentService.getAllStudent();
        return studentList;
    }

    @GetMapping("api/v1.0/student/{id}")
    public Student getStudent(@PathParam("id") String id){
        Student student = studentService.getByStudenId(id);
        return student;
    }

    @PutMapping("api/v1.0/updatestudent")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("api/v1.0/deletestudent")
    public GenericResponse deleteStudent(@RequestParam String id){
        return studentService.deleteStudent(id);
    }
    private void checkifStudentDTOIsNullAndThrowError(Student student) {
        if(Objects.isNull(student) || checkAllNullObject(student)){
            log.error("Student Data should not be Null, cannot allow progress", student);
            throw new RequestNotFoundException
                    (ErrorMessages.ERROR_PLEASE_PROVIDE_STUDENT_REGISTER_DATA.getErrorValue());
        }
    }
    private boolean checkAllNullObject(Student student) {
        boolean isAllNull = ObjectUtils.allNull(student.getName(),
                student.getAge(),
                student.getDepartment());
        return isAllNull;
    }
}
