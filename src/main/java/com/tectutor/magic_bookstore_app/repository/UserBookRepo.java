package com.tectutor.magic_bookstore_app.repository;

import com.tectutor.magic_bookstore_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String name);

    List<Book> findByTitle(String title);

    List<Book> findByPublishCom(String publisher);

    List<Book> findByPriceBetween(double minPrice, double maxPrice);

    List<Book> findByOrderByPriceAsc();
}
