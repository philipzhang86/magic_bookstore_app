package com.tectutor.magic_bookstore_app.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "books_info")
@Entity
public class Book {
    @Id
    private int id;
    @Column(length = 255)
    private String title;
    @Column(length = 255)
    private String author;
    private String publishCom;
    private double price;
    private int edition;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishCom() {
        return publishCom;
    }

    public void setPublishCom(String publishCom) {
        this.publishCom = publishCom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}
