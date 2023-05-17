package com.tectutor.magic_bookstore_app.controller;

import com.tectutor.magic_bookstore_app.model.Admin;
import com.tectutor.magic_bookstore_app.model.Book;
import com.tectutor.magic_bookstore_app.service.AdminBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/admin/book")
@RestController
public class AdminBookController {
    @Autowired
    AdminBookService bookServ;

    @PostMapping("/insert")
    public ResponseEntity<?> insertBook(@RequestBody Book book, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {

            return new ResponseEntity<>(bookServ.insertBook(book), HttpStatus.OK);
        } else {
            
            return new ResponseEntity<>("Admin not logged in", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/insert2")
    public Book insertBook2(@RequestBody Book book, HttpSession session) {
        return bookServ.insertBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            if (bookServ.deleteBook(id)) {
                return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Admin not logged in", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book newBook, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            Book oldBook = bookServ.updateBook(id, newBook);
            if (oldBook != null) {
                return new ResponseEntity<>(oldBook, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Admin not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<?> searchBookById(@PathVariable int id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            Book book = bookServ.getBookById(id);
            if (book != null) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Admin not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/author/{name}")
    public ResponseEntity<?> searchBookByName(@PathVariable String name, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            List<Book> books = bookServ.getBookByName(name);
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Admin not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/showbooks")
    public ResponseEntity<?> showBooks(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            List<Book> books = bookServ.getAllBooks();
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Admin not logged in", HttpStatus.UNAUTHORIZED);
    }
}
