package com.example.Library.DTO;
import com.example.Library.Entities.Student;
import com.example.Library.Enum.StudentStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentDTO {
    String name;
    String email;
    String branch;
    String contact;
    StudentStatus status;

    public Student convertToStudent(StudentDTO studentDTO){
        Student student =new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setBranch(studentDTO.getBranch());
        student.setContact(studentDTO.getContact());
        student.setStatus(studentDTO.getStatus());
        return student;
    }

}
