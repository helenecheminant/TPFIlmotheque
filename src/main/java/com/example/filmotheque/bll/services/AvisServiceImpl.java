package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.Avis;
import com.example.filmotheque.bo.User;
import com.example.filmotheque.dal.avis.AvisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AvisServiceImpl implements AvisService {


    private AvisDAO avisDAO;

    @Autowired
    public AvisServiceImpl(AvisDAO avisDAO) {
        this.avisDAO = avisDAO;
    }


    //=======================METHODES=============================

    //récupérer tous les avis d'un film

    @Override
    public List<Avis> getListAvis(int idFilm) {
        return avisDAO.getListAvisByFilm(idFilm);

    }

    @Override
    //récupérer un avis par user et pour un film
    public Avis getOneAvisByFilmByUser(int idUser, int idFilm) {
        return avisDAO.getOneAvisByFilmAndUserId(idUser, idFilm);
    }


    @Override
    //insérer un nouveau commentaire
    public void addAvis(Avis avis) {
        User user = avis.getUser();
        if (user != null) {
            Integer userId = user.getId();
            System.out.println("->depuis le AVIS SERVICE" + userId);
        } else {
            // Gerer si le user a déjà posté un avis sur le film en question
        }
        avisDAO.addAvis(avis);
    }
}
