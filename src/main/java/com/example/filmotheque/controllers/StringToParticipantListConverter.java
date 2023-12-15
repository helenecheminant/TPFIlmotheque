package com.example.filmotheque.controllers;

import com.example.filmotheque.bll.services.ParticipantService;
import com.example.filmotheque.bo.Film;
import com.example.filmotheque.bo.Participant;
import com.example.filmotheque.dal.FilmDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToParticipantListConverter implements Converter<String, Participant> {

    private FilmDAO filmDAO;
    private ParticipantService participantService;

    @Autowired
    public void setParticipantService(FilmDAO filmDAO, ParticipantService participantService)
    {
        this.filmDAO = filmDAO;
        this.participantService = participantService;
    }
   // ======================METHODES============================

    @Override
    public Participant convert(String idParticipant) {
        System.out.println(idParticipant);

        Optional<Participant> optParticipant = participantService.getParticipantById(Integer.parseInt(idParticipant));
        return optParticipant.get();

    }


}
