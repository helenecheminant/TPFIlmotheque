package com.example.filmotheque.bll.services;

import com.example.filmotheque.bo.Genre;
import com.example.filmotheque.bo.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {

    List<Participant> getListParticipants();


    Optional<Participant> getParticipantById(int id);
}
