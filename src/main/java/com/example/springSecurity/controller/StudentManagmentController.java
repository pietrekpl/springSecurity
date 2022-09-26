package com.example.springSecurity.controller;

import com.example.springSecurity.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/management/students")
public class StudentManagmentController {


    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James"),
            new Student(2L, "Jimmy"),
            new Student(3L, "Jack")
    );

    @GetMapping()
    public List<Student>getAllStudents(){
        log.info("GetAllStudents()");
        return STUDENTS;
    }

    @PostMapping()
    public void addNewStudent(@RequestBody Student student){
        // no need actual implementation, secure api endpoints purpose only
        log.info("Student added: {}", student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") long id){
        // no need actual implementation, secure api endpoints purpose only
        log.info("Student deleted: {}", id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable("id") long id){
        // no need actual implementation, secure api endpoints purpose only
        log.info("Student updated: {}", student);
    }


}
