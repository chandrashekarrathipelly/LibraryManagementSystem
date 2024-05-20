package com.example.Library.Controllers;

import com.example.Library.Entities.User;
import com.example.Library.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void registerUserDetails(@RequestBody User user){

        String encodePassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodePassword);

//        return
        userService.saveUser(user);

    }

}
