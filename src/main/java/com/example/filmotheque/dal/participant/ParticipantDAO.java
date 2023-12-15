package com.example.filmotheque.dal.participant;

import com.example.filmotheque.bo.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantDAO {

    List<Participant> getListParticipantsFromDB();

    Optional<Participant> getParticipantById(int id);
}
