package com.example.springSecurity.controller;


import com.example.springSecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James"),
            new Student(2L, "Jimmy"),
            new Student(3L, "Jack")
    );

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") long id) {
        return STUDENTS.stream().filter(student -> id == (student.getId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("student with Id " + id + " not found"));
    }

}
