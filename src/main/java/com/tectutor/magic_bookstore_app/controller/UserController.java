package com.tectutor.magic_bookstore_app.controller;

import com.tectutor.magic_bookstore_app.model.User;
import com.tectutor.magic_bookstore_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService uService;

    @PostMapping("/insert")
    public User insertAdministrator(@RequestBody User newUser) {
        return uService.insertUser(newUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        User obj = uService.login(user);
        if (obj != null) {
            session.setAttribute("user", obj);
            return "Welcome back, " + obj.getUserName();
        } else return "Invalid username or password";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "You have been successfully logout";
    }
}
