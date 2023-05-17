package com.tectutor.magic_bookstore_app.repository;

import com.tectutor.magic_bookstore_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminBookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String name);
}
