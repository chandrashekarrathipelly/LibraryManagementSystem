package com.example.Library.Controllers;

import org.springframework.web.bind.annotation.*;

import com.example.Library.DTO.StudentDTO;
import com.example.Library.Entities.Student;
import com.example.Library.Services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
// @RequestBody
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping()
    public Student saveStudentDetails(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("/{id}")
    public Student  getMethodName(@PathVariable long id) {
        return studentService.getStudentDetailsById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletedStudent(@PathVariable long id){
        return studentService.deletedStudentUsingId(id);
    }

    @PutMapping("/update/{id}")
    public Student putMethodName(@PathVariable long id, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(studentDTO,id);
        
        return null;
    }

    
    
}
