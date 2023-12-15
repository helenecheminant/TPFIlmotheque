package com.example.filmotheque.bll.services;


import com.example.filmotheque.bo.Participant;
import com.example.filmotheque.dal.FilmDAO;
import com.example.filmotheque.dal.participant.ParticipantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    private FilmDAO filmDao;
    private ParticipantDAO participantDAO;

    @Autowired
    public ParticipantServiceImpl(FilmDAO filmDao, ParticipantDAO participantDAO) {
        this.filmDao = filmDao;
        this.participantDAO = participantDAO;
    }


    //====================METHODES================================

    @Override
    public List<Participant> getListParticipants() {
        List<Participant> listParticipants = participantDAO.getListParticipantsFromDB();
        return listParticipants;
    }


    @Override
    public Optional<Participant> getParticipantById(int id) {
        Optional<Participant> optParticipant= participantDAO.getParticipantById(id);
        return optParticipant;
    }
}
