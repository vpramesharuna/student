package com.school.stu.controller;

import com.school.stu.dto.DepartmentDTO;
import com.school.stu.entity.Department;
import com.school.stu.entity.Student;
import com.school.stu.repository.DepartmentRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentControllerTest {
    public static final String API_V_1_0_DEPARTMENT = "/api/v1.0/department";

    public static final String API_V_1_0_UPDEPARTMENT = "/api/v1.0/updatedepartment";

    public static final String API_V_1_0_DELDEPARTMENT = "/api/v1.0/deletedepartment";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    public void cleanup(){
        departmentRepository.deleteAll();
    }
    @Test
    public void givenDepartmentRequestObject_whenDepartmentSaved_thenReturnSavedDepartment(){
        Department department= createValidDepartment();

        ResponseEntity<Object> responseEntity = testRestTemplate.postForEntity(API_V_1_0_DEPARTMENT, department, Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenDepartmentRequestObject_whenDepartmentSaved_thenReturnSavedDepartmentToDatabase(){
        Department department= createValidDepartment();
        testRestTemplate.postForEntity(API_V_1_0_DEPARTMENT, department, Object.class);

        assertThat(departmentRepository.count()).isEqualTo(1);
    }

    @Test
    public void givenNoData_whenAllDepartments_thenReturnAllDepartments(){
        ResponseEntity<Object[]> responseEntity = testRestTemplate.getForEntity(API_V_1_0_DEPARTMENT, Object[].class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Arrays.stream(responseEntity.getBody()).toList().size()).isEqualTo(0);
    }

    @Test
    public void givenDepartmentData_whenDepartmentFoundAndUpdated_thenReturnUpdatedDepartmentData(){
        Department department = createValidDepartment();
        department.setId(UUID.fromString("cbc03d41-277e-44f0-acc7-7b8fe2ba7630"));
        HttpEntity<Department> departmentHttpEntity = new HttpEntity<>(department);
        ResponseEntity<GenericResponse> responseEntity = testRestTemplate.exchange(API_V_1_0_UPDEPARTMENT, HttpMethod.PUT, departmentHttpEntity, GenericResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenStudentData_whenStudentFoundAndDeleted_thenReturnSuccessMsg(){
        Department department = createValidDepartment();
        department.setId(UUID.fromString("cbc03d41-277e-44f0-acc7-7b8fe2ba7630"));
        HttpEntity<Department> departmentHttpEntity = new HttpEntity<>(department);
        ResponseEntity<GenericResponse> responseEntity = testRestTemplate.exchange(API_V_1_0_DELDEPARTMENT, HttpMethod.DELETE, departmentHttpEntity, GenericResponse.class);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Successfully Department Deleted");
    }
    private Department createValidDepartment() {
        Department department = Department.builder()
                .departmentName("Testing")
                .description("Testing on the items")
                .build();
        return department;
    }

}
