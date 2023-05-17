package com.tectutor.magic_bookstore_app.service;

import com.tectutor.magic_bookstore_app.model.Book;
import com.tectutor.magic_bookstore_app.repository.AdminBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminBookService {
    @Autowired
    private AdminBookRepo adminBookRepo;

    public Book insertBook(Book book) {
        return adminBookRepo.save(book);
    }

    public boolean deleteBook(int id) {
        Optional<Book> result = adminBookRepo.findById(id);
        if (result.isPresent()) {
            adminBookRepo.deleteById(id);
            return true;
        } else return false;
    }

    public Book updateBook(int id, Book newBook) {
        Optional<Book> oldBookOpt = adminBookRepo.findById(id);
        if (oldBookOpt.isPresent()) {
            Book oldBook = oldBookOpt.get();
            oldBook.setTitle(newBook.getTitle());
            oldBook.setAuthor(newBook.getAuthor());
            oldBook.setPublishCom(newBook.getPublishCom());
            oldBook.setPrice(newBook.getPrice());
            oldBook.setEdition(newBook.getEdition());
            adminBookRepo.save(oldBook);
            return oldBook;
        } else return null;
    }

    public Book getBookById(int id) {
        Optional<Book> bookOpt = adminBookRepo.findById(id);
        return bookOpt.orElse(null);//If a value is present, returns the value, otherwise returns other.
    }

    public List<Book> getBookByName(String name) {
        return adminBookRepo.findByAuthor(name);
    }

    public List<Book> getAllBooks() {
        return adminBookRepo.findAll();
    }
}
