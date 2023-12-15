package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.Film;
import com.example.filmotheque.bo.Participant;

import java.util.List;
import java.util.Optional;


public interface FilmService {

    Optional<Film> findFilmById(int id);


    List<Film> getListFilms();

    void addFilm(Film film);


    List<Participant> findActeurs(int idFilm);
}
