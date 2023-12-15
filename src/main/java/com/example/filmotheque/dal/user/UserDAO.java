package com.example.filmotheque.dal.user;

import com.example.filmotheque.bo.User;

public interface UserDAO {

    User getUserFromDB(String pseudo);

    User getUserById(Integer id);
}
