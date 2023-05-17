package com.tectutor.magic_bookstore_app.controller;

import com.tectutor.magic_bookstore_app.dto.AdminDTO;
import com.tectutor.magic_bookstore_app.model.Admin;
import com.tectutor.magic_bookstore_app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/insert")
    public AdminDTO insertAdministrator(@RequestBody AdminDTO adminDTO) {
        return adminService.insertAdmin(adminDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin, HttpSession session){
        Admin existingAdmin = adminService.login(admin);
        if(existingAdmin != null)   {
            session.setAttribute("admin", existingAdmin);
            return "Welcome back, Administrator";
        }
        else return "Invalid username or password";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "You have been successfully logout";
    }
}
