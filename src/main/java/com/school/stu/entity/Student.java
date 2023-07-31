package com.school.stu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue
    private UUID Id;
    private String name;
    private String age;
    private String course;
    private String specialization;
    private String percentage;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
}
