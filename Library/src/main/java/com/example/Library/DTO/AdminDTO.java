package com.example.Library.DTO;

import com.example.Library.Entities.Admin;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AdminDTO {
    String name;
    String email;

    public Admin convertToAdmin(){
        Admin admin=new Admin();
        admin.setEmail(this.email);
        admin.setName(this.name);
        return admin;
    }
}
