package com.tectutor.magic_bookstore_app.repository;

import com.tectutor.magic_bookstore_app.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findByAdminNameAndAdminPassword(String name, String password);
    Admin findByAdminName(String name);
}
