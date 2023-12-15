package com.example.filmotheque.bll.services;


import com.example.filmotheque.bo.Genre;
import com.example.filmotheque.dal.genre.GenreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {


    private GenreDAO genreDAO;

    // on injecte le filmDAO dans le constructeur
    @Autowired
    public GenreServiceImpl( GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }


    //========================METHODES==============================
    @Override
    public List<Genre> getListGenres() {
        List<Genre> listGenres = genreDAO.getlistGenresFromDB();
        return listGenres;
    }


    @Override
    public Genre findGenreById(Integer id) {
        return genreDAO.getGenreById(id);
    }

}
