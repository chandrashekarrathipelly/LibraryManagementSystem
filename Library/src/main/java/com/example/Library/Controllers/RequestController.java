package com.example.Library.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.Library.DTO.RequestDTO;
import com.example.Library.Entities.Request;
import com.example.Library.Services.RequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping("/request")
    public Request postMethodName(@RequestBody RequestDTO requestDTO) {
        return requestService.createRequest(requestDTO);
    }

    @DeleteMapping("/request/{id}")
    public String deleteRequest(@PathVariable long id) {
        requestService.deleteRequest(id);
        return "Request Deleted Successfully";
    }

    @GetMapping("/request/{id}")
    public Request getRequestById(@PathVariable long id) {
        return requestService.getRequestById(id);
    }
}
