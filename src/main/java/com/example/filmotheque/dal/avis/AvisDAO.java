package com.example.filmotheque.dal.avis;

import com.example.filmotheque.bo.Avis;

import java.util.List;

public interface AvisDAO {


    List<Avis> getListAvisByFilm(int idFilm);

    Avis getOneAvisByFilmAndUserId(int idUser, int idFilm);

    void addAvis(Avis avis);
}
