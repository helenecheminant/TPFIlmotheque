package com.example.filmotheque.bll.services;

import java.util.List;
import java.util.Optional;

import com.example.filmotheque.bo.Film;
import com.example.filmotheque.bo.Participant;
import com.example.filmotheque.dal.FilmDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FilmServiceImpl implements FilmService {

    private FilmDAO filmDao;

    @Autowired
    public FilmServiceImpl(FilmDAO filmDao) {
        this.filmDao = filmDao;
    }


    // =================METHODES===============================


    // récupérer un film
    @Override
    public Optional<Film> findFilmById(int id) {
        return filmDao.findFilmById(id);
    }


    //récupérer tous les films
    @Override
    public List<Film> getListFilms() {
       return filmDao.getListFilmsFromDB();
    }

    // ajouter un film
    @Override
    public void addFilm(Film film) {
        /*int newId = getNextFilmId();
        film.setId(newId);*/
        filmDao.addFilm(film);
        System.out.println("coucou depuis le filmService methode addFilm");
    }
    // génère le nouvel identifiant en fonction de la taille actuelle de la liste
    private int getNextFilmId() {
        return filmDao.getListFilmsFromDB().size() + 1;
    }


    @Override
    public List<Participant> findActeurs(int idFilm){
        return filmDao.findActeursByFilm(idFilm);
    }


}

