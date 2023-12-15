package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.Avis;

import java.util.List;

public interface AvisService {
    List<Avis> getListAvis(int idFilm);

    //récupérer un avis par user et pour un film
    Avis getOneAvisByFilmByUser(int idUser, int idFilm);

    //insérer un nouveau commentaire
     void addAvis(Avis avis);
}
