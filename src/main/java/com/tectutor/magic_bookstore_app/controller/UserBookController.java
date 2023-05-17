package com.tectutor.magic_bookstore_app.controller;

import com.tectutor.magic_bookstore_app.model.Book;
import com.tectutor.magic_bookstore_app.model.User;
import com.tectutor.magic_bookstore_app.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user/book")
@RestController
public class UserBookController {
    @Autowired
    UserBookService bookServ;

    @GetMapping("/showBook")
    public ResponseEntity<?> showBooks(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Book> books = bookServ.getAllBooks();
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Books not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/author/{name}")
    public ResponseEntity<?> searchBookByAuthor(@PathVariable String name, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Book> books = bookServ.getBookByAuthor(name);
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Books not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<?> searchBookByTitle(@PathVariable String title, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Book> books = bookServ.getBookByTitle(title);
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Books not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/publisher/{publisher}")
    public ResponseEntity<?> searchBookByPublisher(@PathVariable String publisher, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Book> books = bookServ.getBookByPublisher(publisher);
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Books not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<?> searchBookById(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Book book = bookServ.getBookById(id);
            if (book != null) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search/price")///search/price?minPrice=10&maxPrice=20
    public ResponseEntity<?> searchBookByPrice(@RequestParam double minPrice, @RequestParam double maxPrice,
                                               HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Book> books = bookServ.searchByPrice(minPrice, maxPrice);
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Books not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/sort/bookPrice")
    public ResponseEntity<?> sortBooks(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Book> books = bookServ.sortBookByPrice();
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Books not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/favor/{bookId}")
    public String addFavourite(@PathVariable int bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (bookServ.addBookToFavourite(user.getUserId(), bookId)) {
            return "You have been successfully add your favourite book";
        } else {
            return "Failed to add book";
        }
    }

    @GetMapping("/favor/getAll")
    public List<Book> getFavorBooks(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return bookServ.getFavouriteBooks(user.getUserId());
    }
}
