package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getListGenres();
    public Genre findGenreById(Integer id);
}
