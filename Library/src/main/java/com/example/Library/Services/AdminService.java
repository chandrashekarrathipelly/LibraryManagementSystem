package com.example.Library.Services;

import java.util.List;
import java.util.Optional;

import com.example.Library.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.AdminDTO;
import com.example.Library.DTO.StatusUpdateDto;
import com.example.Library.Entities.Admin;
import com.example.Library.Entities.Request;
import com.example.Library.Entities.Transactions;
import com.example.Library.Enum.Request_status;
import com.example.Library.Enum.StausUpdateEnum;
import com.example.Library.Enum.Transaction_status;
import com.example.Library.Repository.AdminRepository;
import com.example.Library.Repository.RequestRepository;
import com.example.Library.Repository.TransactionsRepository;



@Service
public class AdminService {
   @Autowired
    AdminRepository adminRepository;
    @Autowired 
    RequestRepository requestRepository;
    @Autowired 
    TransactionsRepository transactionsRepository;

    public Admin addAdmin(AdminDTO adminDTO, User user){
        Admin admin= adminDTO.convertToAdmin(user);
        return adminRepository.save(admin);
    }

    public Optional<Admin> getAdminDetailsById(long id){
        return adminRepository.findById(id);
    }

    public Request statusUpdate(StatusUpdateDto statusUpdateDto){
       Request request= requestRepository.findById(statusUpdateDto.getRequest_Id()).orElseThrow( () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Request not Found"));
       if(!(request.getAdmin().getAdmin_id()==statusUpdateDto.getAdmin_id())){
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "This request doesn't belong to current admin");
       }
       if(request.getReq_status().equals(Request_status.Pending)){
           
        if(statusUpdateDto.getStatus().equals(StausUpdateEnum.Approved)){
            request.setReq_status(Request_status.Accepted);
           }else{
            request.setReq_status(Request_status.NotAccepted);
           }
           Transactions transaction=Transactions.builder()
           .request(request)
           .status(request.getReq_status().equals(Request_status.Accepted)? Transaction_status.Completed : Transaction_status.Rejected)
           .build();
           transactionsRepository.save(transaction);
           
          return requestRepository.save(request);
       }
       throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Request is already"+request.getReq_status().name());
    }

    public String delted(long id){
        adminRepository.deleteById(id);
        return "deleted successfully";
    }

    public Admin update(long id,AdminDTO adminDTO){
        Optional<Admin> admin=adminRepository.findById(id);
        if(admin.isPresent()){
            Admin admin2=admin.get();
            if(adminDTO.getName()!=null){
                admin2.setName(adminDTO.getName());
            }
            this.adminRepository.save(admin2);
            return admin2;
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Admin not found");
    }

    public List<Admin> getAllAdmins() {
       return adminRepository.findAll();
    }

    public List<Request> getRequests(long id) {
       Admin admin = this.adminRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404),"Admin not found"));
       return admin.getRequest();
    }
}
