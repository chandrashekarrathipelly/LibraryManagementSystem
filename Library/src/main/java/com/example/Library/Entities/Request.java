package com.example.Library.Entities;

import java.util.List;
import com.example.Library.Enum.Request_status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long req_id;

    String date;

    @Enumerated(value = EnumType.STRING)
    Request_status req_status;

    @OneToOne(mappedBy = "request")
    Transactions transactions;

    @ManyToOne()
    @JsonIgnoreProperties("request")
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    Student student;

    @ManyToOne()
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    @JsonIgnoreProperties("request")
    Admin admin;

    @ManyToMany()
    @JoinTable(name = "Req_Book", joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "req_id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"))
    @JsonIgnoreProperties("request")
    List<Book> book;

}
