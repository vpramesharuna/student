package com.school.stu.repository;

import com.school.stu.entity.Department;
import com.school.stu.entity.Student;
import com.school.stu.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup(){
        studentRepository.deleteAll();
        student = createStubbedStudentEntity();
    }

    @DisplayName("This test case tests the basic flow or happy path for saving Student in -> in-memory database")
    @Test
    public void givenStudentObject_whenSavedStudentObject_thenReturnSavedStudent(){
        Student studentLocal = studentRepository.save(student);
        assertThat(studentLocal).isNotNull();
        assertThat(studentLocal.getId()).isNotNull();

    }

    @DisplayName("This test case tests the basic flow or happy path for getting all the students list -> in-memory database")
    @Test
    public void givenNoData_whenFetchedDataFromDB_thenReturnListOfStudents(){
        List<Student> studentLocal = studentRepository.findAll();
        assertThat(studentLocal).isNotNull();
        assertThat(studentLocal.size()).isEqualTo(0);

    }


    public static Student createStubbedStudentEntity() {
        Department department = new Department();
        department.setDepartmentName("Testing");
        Student student = Student.builder().Id(UUID.randomUUID())
                .name("Ramesh")
                .age("30")
                .department(department)
                .build();
        return student;
    }

}
