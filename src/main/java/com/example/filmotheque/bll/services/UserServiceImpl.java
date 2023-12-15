package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.User;
import com.example.filmotheque.dal.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl (UserDAO userDAO) { this.userDAO = userDAO; }



    //==================METHODES============================

    @Override
    public User getUserById(Integer id) {
        User user = userDAO.getUserById(id);
        return user;
    }


    @Override
    public User getUserByName(String pseudo) {
        User user = userDAO.getUserFromDB(pseudo);
        return user;
    }
}
