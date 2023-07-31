package com.school.stu.service;

import com.school.stu.entity.Department;
import com.school.stu.entity.Student;
import com.school.stu.repository.StudentRepository;
import com.school.stu.service.StudentService;
import com.school.stu.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("dev")
public class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService = new StudentServiceImpl(studentRepository);

    @Test
    public void givenStudentRequestObject_whenStudentSaved_thenReturn() {
        Student student = createValidStudent();
        given(studentRepository.save(student)).willReturn(student);
        Student savedStudent = studentService.saveStudent(student);
        System.out.println(savedStudent);
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("Ramesh");
    }

    @Test
    public void givenNoData_whenDataFetchedFromDB_thenReturnListOfStudents() {
        List<Student> studentList = getStudentList();
        given(studentRepository.findAll()).willReturn(studentList);
        List<Student> savedStudentList = studentService.getAllStudent();
        assertThat(savedStudentList).isNotNull();
        assertThat(savedStudentList.size()).isEqualTo(1);
    }

    private static List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(createValidStudent());
        return studentList;
    }

    @Test
    public void givenStudentRequestObject_whenUpdatedStudentSaved_thenReturn() {
        Student student = createValidStudent();
        student.setName("Ramesh1");
        student.setId(UUID.fromString("cbc03d41-277e-44f0-acc7-7b8fe2ba7630"));
        given(studentService.updateStudent(student)).willReturn(student);
        Student savedStudent = studentService.updateStudent(student);
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("Ramesh1");
    }

    private static Student createValidStudent() {
        Student  stu = new Student();
        stu.setName("Ramesh");
        Department department = new Department();
        department.setDepartmentName("Testing");
        stu.setDepartment(department);
        stu.setAge("Ramesh");
        return stu;
    }
}
