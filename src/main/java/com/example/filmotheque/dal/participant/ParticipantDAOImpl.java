package com.example.filmotheque.dal.participant;

import com.example.filmotheque.bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //=======================METHODES====================================

    @Override
    public List<Participant> getListParticipantsFromDB() {
        List<Participant> listParticipants = jdbcTemplate.query("SELECT id, prenom, nom FROM PARTICIPANT", new BeanPropertyRowMapper<>(Participant.class));
        System.out.println("Liste des participants :  ");
       // listParticipants.forEach(System.out::println);
        return listParticipants;
    }

    @Override
    public Optional<Participant> getParticipantById(int id) {
        String sql = "SELECT id, nom, prenom FROM PARTICIPANT WHERE id=?";
        Optional<Participant> optParticipant = Optional.of(new Participant());

        try {
            Participant participant = jdbcTemplate.queryForObject(sql,
                    (ResultSet rs, int rowNum) ->
                            new Participant(rs.getInt(1), rs.getString(2), rs.getString(3)), id);
            optParticipant = optParticipant.of(participant);
        } catch (DataAccessException dae) {
            System.out.println("erreur sur le getParticipantById");
            dae.printStackTrace();
        }
       // System.out.println("participant = " + optParticipant);
        return optParticipant;
    }
}



