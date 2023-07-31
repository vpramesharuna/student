package com.school.stu.controller;

import com.school.stu.entity.Department;
import com.school.stu.entity.Student;
import com.school.stu.repository.StudentRepository;
import com.school.stu.service.exception.StudentBusinessException;
import com.school.stu.util.GenericResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class StudentControllerTest {

    public static final String API_V_1_0_STUDENT = "/api/v1.0/student";

    public static final String API_V_1_0_UPSTUDENT = "/api/v1.0/updatestudent";

    public static final String API_V_1_0_DELSTUDENT = "/api/v1.0/deletestudent";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    public void cleanup(){
        studentRepository.deleteAll();
    }

    @Test
    public void givenStudentRequestObject_whenStudentSaved_thenReturnSavedStudent(){
        Student stu = createValidStudent();

        ResponseEntity<Object> responseEntity = testRestTemplate.postForEntity(API_V_1_0_STUDENT, stu, Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenStudentRequestObject_whenStudentSaved_thenReturnSavedStudentToDatabase(){
        Student stu = createValidStudent();
        testRestTemplate.postForEntity(API_V_1_0_STUDENT, stu, Object.class);

        assertThat(studentRepository.count()).isEqualTo(1);
    }

    @Test
    public void givenStudentRequestObject_whenStudentSaved_thenReturnSuccessMessage(){
        Student stu = createValidStudent();
        ResponseEntity<GenericResponse> responseEntity = testRestTemplate.postForEntity(API_V_1_0_STUDENT, stu, GenericResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
       // assertThat(responseEntity.getBody().getMessage()).isEqualTo("Success");
    }

    @Test
    public void givenNoData_whenStudentDataRetrieved_thenReturnAllStudentsData(){
        ResponseEntity<Object[]> responseEntity = testRestTemplate.getForEntity(API_V_1_0_STUDENT, Object[].class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenStudentData_whenStudentFoundAndUpdated_thenReturnUpdatedStudentData(){
        Student stu = createValidStudent();
        stu.setId(UUID.fromString("cbc03d41-277e-44f0-acc7-7b8fe2ba7630"));
        HttpEntity<Student> student1 = new HttpEntity<>(stu);
        ResponseEntity<GenericResponse> responseEntity = testRestTemplate.exchange(API_V_1_0_UPSTUDENT,HttpMethod.PUT, student1, GenericResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenStudentData_whenStudentFoundAndDeleted_thenReturnSuccessMsg(){
        Student stu = createValidStudent();
        stu.setId(UUID.fromString("cbc03d41-277e-44f0-acc7-7b8fe2ba7630"));
        HttpEntity<Student> httpEntity= new HttpEntity<>(stu);
        ResponseEntity<GenericResponse> responseEntity = testRestTemplate.exchange(API_V_1_0_DELSTUDENT, HttpMethod.DELETE, httpEntity, GenericResponse.class);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Successfully Deleted");
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
