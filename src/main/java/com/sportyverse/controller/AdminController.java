package com.sportyverse.controller;

import com.sportyverse.dto.AdminDto;
import com.sportyverse.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService adminService;

    @GetMapping("verify/{email}/{password}")
    public AdminDto verifyAdmin(@PathVariable("email") String email,
                                @PathVariable("password") String password){
        AdminDto adminDto = adminService.verifyByEmailAndPassword(email, password);
        return adminDto;
    }

}
