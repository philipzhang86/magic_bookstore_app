package com.tectutor.magic_bookstore_app.service;

import com.tectutor.magic_bookstore_app.model.User;
import com.tectutor.magic_bookstore_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User insertUser(User newUser) {
        return userRepo.save(newUser);
    }

    public User login(User user) {
        User foundUser = userRepo.findByUserName(user.getUserName());
        if (foundUser != null && foundUser.getUserPassword().equals(user.getUserPassword())) return foundUser;
        return null;
    }

    public User searchById(int userId) {
        Optional<User> opt = userRepo.findById(userId);
        return opt.orElse(null);
    }


}
