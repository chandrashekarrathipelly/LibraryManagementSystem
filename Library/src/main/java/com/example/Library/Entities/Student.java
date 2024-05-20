package com.example.Library.Entities;

import java.util.List;

import com.example.Library.Enum.StudentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long student_id;
    String name;
    String email;
    String branch;
    String contact;
    StudentStatus status;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    @JsonIgnoreProperties({"password", "student", "authorities","admin"})
    User user;


    @OneToMany(mappedBy = "student")
    List<Request> request;
}
