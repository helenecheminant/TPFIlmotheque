package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.User;

public interface UserService {
    User getUserById(Integer id);

    User getUserByName(String pseudo);
}
