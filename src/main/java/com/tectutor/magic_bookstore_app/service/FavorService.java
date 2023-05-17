package com.tectutor.magic_bookstore_app.service;

import com.tectutor.magic_bookstore_app.model.Favourite;
import com.tectutor.magic_bookstore_app.model.User;
import com.tectutor.magic_bookstore_app.repository.FavorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorService {
    @Autowired
    private FavorRepo favorRepo;

    public List<Favourite> getFavouritesByUser(User user) {
        return favorRepo.findAllByUser(user);
    }

    public void save(Favourite favor){
        favorRepo.save(favor);
    }
}
