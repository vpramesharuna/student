package com.school.stu.service;

import com.school.stu.entity.Student;
import com.school.stu.util.GenericResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    public Student saveStudent(Student student);

    public List<Student> getAllStudent();

    public Student updateStudent(Student student);

    public GenericResponse deleteStudent(String id);

    public Student getByStudenId(String id);
}
