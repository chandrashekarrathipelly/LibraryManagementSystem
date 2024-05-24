package com.example.Library.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.Library.Entities.Request;
import com.example.Library.Entities.User;
import com.example.Library.Enum.Request_status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.StudentDTO;
import com.example.Library.Entities.Student;
import com.example.Library.Repository.StudentRepository;

@Service
public class StudentService {
  @Autowired
  StudentRepository studentrepository;

  public Student addStudent(StudentDTO studentDTO, User user) {
    Student student = studentDTO.convertToStudent(user);
    return this.studentrepository.save(student);
  }

  public Student getStudentDetailsById(long id) {
    return studentrepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Student with Id " + id + "not found"));
  }

  public Student updateStudent(StudentDTO studentDTO, long id) {
    Optional<Student> student = studentrepository.findById(id);
    if (student.isPresent()) {
      Student student2 = student.get();

      if (studentDTO.getBranch() != null) {
        student2.setBranch(studentDTO.getBranch());
      }
      if (studentDTO.getContact() != null) {
        student2.setContact(studentDTO.getContact());
      }

      if (studentDTO.getName() != null) {
        student2.setName(studentDTO.getName());
      }
      if (studentDTO.getStatus() != null) {
        student2.setStatus(studentDTO.getStatus());
      }
      this.studentrepository.save(student2);
      return student2;
    }
    throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Student not found");
  }

  public String deletedStudentUsingId(long id) {
    studentrepository.deleteById(id);
    return "Student Deleted Successfully";
  }
  
  public List<Request> getPendingRequestByStudentId(long id){
   Student student = studentrepository.findById(id).orElseThrow(()-> new ResponseStatusException(
           HttpStatusCode.valueOf(404),"Student with id"+id+"not found"));

   List<Request> studentRequestList = student.getRequest();
    ArrayList<Request> pendingStudentRequestList = new ArrayList<>();

    for(Request request: studentRequestList){
      if (request.getReq_status().equals(Request_status.Pending)){
        pendingStudentRequestList.add(request);
      }
    }
    return  pendingStudentRequestList;
  }

  public List<Request> getAcceptedRequestByStudentId(long id){
    Student student = studentrepository.findById(id).orElseThrow(()-> new ResponseStatusException(
            HttpStatusCode.valueOf(404),"Student with id"+id+"not found"));
 
    List<Request> studentRequestList = student.getRequest();
     ArrayList<Request> pendingStudentRequestList = new ArrayList<>();
 
     for(Request request: studentRequestList){
       if (request.getReq_status().equals(Request_status.Accepted)){
         pendingStudentRequestList.add(request);
       }
     }
     return  pendingStudentRequestList;
   }

}
