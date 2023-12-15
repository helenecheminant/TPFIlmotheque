package com.example.filmotheque.dal;
import com.example.filmotheque.bo.Film;
import com.example.filmotheque.bo.Genre;
import com.example.filmotheque.bo.Participant;

import java.util.List;
import java.util.Optional;



public interface FilmDAO {
    // pour ajouter un film depuis le formulaire
    void addFilm(Film film);

    Optional<Film> findFilmById(int id);


    List<Participant> findActeursByFilm(int idFilm);

    List<Film> getListFilmsFromDB();
}
