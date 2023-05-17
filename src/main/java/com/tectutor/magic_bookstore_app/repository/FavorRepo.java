package com.tectutor.magic_bookstore_app.repository;

import com.tectutor.magic_bookstore_app.model.Favourite;
import com.tectutor.magic_bookstore_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorRepo extends JpaRepository<Favourite, Integer> {
    List<Favourite> findAllByUser(User user);
}
