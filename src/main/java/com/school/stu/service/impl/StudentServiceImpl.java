package com.school.stu.service.impl;

import com.school.stu.controller.exception.RequestNotFoundException;
import com.school.stu.entity.Department;
import com.school.stu.entity.Student;
import com.school.stu.repository.DepartmentRepository;
import com.school.stu.repository.StudentRepository;
import com.school.stu.service.StudentService;
import com.school.stu.service.exception.StudentBusinessException;
import com.school.stu.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
@Autowired
    StudentRepository studentRepository;
@Autowired
    DepartmentRepository departmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        if(student.getId()==null){
            throw new RequestNotFoundException("Provided Data Id is null");
        }else{
            Optional<Student> stu = studentRepository.findById(student.getId());
            Department department = departmentRepository.findByDepartmentName(student.getDepartment().getDepartmentName());
            student.setDepartment(department);
            if(!stu.isEmpty()){
                studentRepository.save(student);
                return student;
            }else{
                return null;
            }
        }
    }

    @Override
    public GenericResponse deleteStudent(String id) {
        try{
            studentRepository.deleteById(UUID.fromString(id));
        }catch (StudentBusinessException ste){
            log.error(ste.getMessage());
            ste.printStackTrace();
        }
        return new GenericResponse("Successfully Deleted");
    }

    @Override
    public Student getByStudenId(String id) {
        Optional<Student> student = studentRepository.findById(UUID.fromString(id));
        if(!student.isEmpty())
            return student.get();
        else
            return null;
    }
}
