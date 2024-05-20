package com.example.Library.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.RequestDTO;
import com.example.Library.Entities.Admin;
import com.example.Library.Entities.Book;
import com.example.Library.Entities.Request;
import com.example.Library.Entities.Student;
import com.example.Library.Enum.Request_status;
import com.example.Library.Repository.RequestRepository;

@Service
public class RequestService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RequestRepository requestRepository;

    public Request createRequest(RequestDTO requestDTO) {
        if (requestDTO.getBook_id_List().isEmpty() || requestDTO.getBook_id_List().size() > 3) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "In valid Book Count");
        }
        Student student = studentService.getStudentDetailsById(requestDTO.getStudent_id());

        List<Book> books = requestDTO.getBook_id_List().stream().map((x) -> {
            Book book = bookService.getBook(x);
            return book;
        }).collect(Collectors.toList());

        // List<Book> book = new ArrayList<>();
        // for(int i=0 ; i< requestDTO.getBook_id_List().size(); i++){
        // Book boo = bookService.gBook(requestDTO.getBook_id_List().get(i));
        // book.add(boo);
        // }
        List<Admin> admins = adminService.getAllAdmins();
        int smallest = admins.get(0).getRequest().size();
        Admin adminAlloted = admins.get(0);

        for (Admin admin : admins) {
            if (admin.getRequest().size() < smallest) {
                smallest = admin.getRequest().size();
                adminAlloted = admin;
            }
        }

        Request request = Request.builder()
                .admin(adminAlloted)
                .book(books)
                .req_status(Request_status.Pending)
                .student(student)
                .date(new Date().toString())
                .build();

        return requestRepository.save(request);
    }

    public void deleteRequest(long id) {
        requestRepository.deleteById(id);
    }

    public Request getRequestById(long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Request not present"));
    }

    public Request saveRequest(Request request){
        return this.requestRepository.save(request);
    }
}
