package com.tectutor.magic_bookstore_app.service;

import com.tectutor.magic_bookstore_app.model.Book;
import com.tectutor.magic_bookstore_app.model.Favourite;
import com.tectutor.magic_bookstore_app.model.User;
import com.tectutor.magic_bookstore_app.repository.UserBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBookService {
    @Autowired
    private UserBookRepo userBookRepo;
    @Autowired
    private UserService userSev;

    private FavorService favorServ;

    @Autowired
    public void setFavorService(FavorService favorServ) {
        this.favorServ = favorServ;
    }
    public List<Book> getAllBooks() {
        return userBookRepo.findAll();
    }

    public List<Book> getBookByAuthor(String authorName) {
        return userBookRepo.findByAuthor(authorName);
    }

    public List<Book> getBookByTitle(String title) {
        return userBookRepo.findByTitle(title);
    }

    public List<Book> getBookByPublisher(String publisher) {
        return userBookRepo.findByPublishCom(publisher);
    }

    public Book getBookById(int id) {
        Optional<Book> bookOpt = userBookRepo.findById(id);
        return bookOpt.orElse(null);//If a value is present, returns the value, otherwise returns other.
    }

    public List<Book> searchByPrice(double minPrice, double maxPrice) {
        return userBookRepo.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Book> sortBookByPrice() {
        return userBookRepo.findByOrderByPriceAsc();
    }

    public boolean addBookToFavourite(int userId, int bookId) {
        User user = userSev.searchById(userId);
        Book book = getBookById(bookId);
        if (user != null && book != null) {
            Favourite favourite = new Favourite();
            favourite.setUser(user);
            favourite.setBook(book);
            favorServ.save(favourite);
            return true;
        }
        return false;
    }

    public List<Book> getFavouriteBooks(int userId) {
        User user = userSev.searchById(userId);
        System.out.println(user);
        if (user != null) {
            List<Favourite> favourites = user.getFavourites();
            List<Book> favouriteBooks = favourites.stream()
                    .map(Favourite::getBook)
                    .collect(Collectors.toList());
            return favouriteBooks;
        }
        return null;
    }
}
