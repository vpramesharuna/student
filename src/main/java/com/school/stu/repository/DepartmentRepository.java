package com.school.stu.repository;

import com.school.stu.entity.Department;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

    public Department findByDepartmentName(String deptName);
}
