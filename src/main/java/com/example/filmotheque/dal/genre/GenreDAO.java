package com.example.filmotheque.dal.genre;

import com.example.filmotheque.bo.Genre;

import java.util.List;

public interface GenreDAO {

    int getNbGenres();

   // List<Genre> getListGenres();

    List<Genre> getlistGenresFromDB();

    Genre getGenreById(int id);

}
