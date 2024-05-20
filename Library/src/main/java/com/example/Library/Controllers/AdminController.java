package com.example.Library.Controllers;

import java.util.List;
import java.util.Optional;

import com.example.Library.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.DTO.AdminDTO;
import com.example.Library.DTO.StatusUpdateDto;
import com.example.Library.Entities.Admin;
import com.example.Library.Entities.Request;
import com.example.Library.Services.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController()
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public Admin addAdmin(@RequestBody AdminDTO asAdminDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return adminService.addAdmin(asAdminDTO,user);
    }

    @PutMapping("/admin/update/{id}")
    public Admin upAdmin(@PathVariable long id,@RequestBody AdminDTO adminDTO ){
        return adminService.update(id,adminDTO);
    }

    @GetMapping("/admin/{id}")
    public Optional<Admin>  getMethodName(@RequestParam long id) {
        return adminService.getAdminDetailsById(id);
    }

    @DeleteMapping("/admin/{id}")
    public String admin(@PathVariable long id){
        return adminService.delted(id);
    }

    @GetMapping("/admins")
    public List<Admin> getAllAdmins(){
       return adminService.getAllAdmins();
    }

    @PostMapping("/admin/request")
    public Request updateRequest(@RequestBody StatusUpdateDto statusUpdateDto){
        return this.adminService.statusUpdate(statusUpdateDto);
    }

    @GetMapping("/admin/requests/{id}")
    public List<Request> getRequests(@PathVariable long id) {
        return adminService.getRequests(id);
    }
    
}
