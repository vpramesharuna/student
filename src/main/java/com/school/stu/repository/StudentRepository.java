package com.school.stu.repository;

import com.school.stu.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    public void deleteById(UUID uuid);


}
