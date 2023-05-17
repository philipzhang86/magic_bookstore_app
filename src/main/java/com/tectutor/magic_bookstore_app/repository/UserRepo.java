package com.tectutor.magic_bookstore_app.repository;

import com.tectutor.magic_bookstore_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserName(String name);
}
