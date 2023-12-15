package com.example.filmotheque.dal.avis;


import com.example.filmotheque.bo.*;
import com.example.filmotheque.dal.FilmDAO;
import com.example.filmotheque.dal.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AvisDAOImpl implements AvisDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private UserDAO userDAO;
    private FilmDAO filmDAO;

    @Autowired
    public AvisDAOImpl(UserDAO userDAO, FilmDAO filmDAO) {
        this.userDAO = userDAO;
        this.filmDAO = filmDAO;
    }


    //=====================METHODES=========================

    //on commence par faire un rowMapper personnalisé
    private class AvisRowMapper implements RowMapper<Avis> {
        @Override
        public Avis mapRow(ResultSet rs, int rowNum) throws SQLException {
            Optional<Film> optionalFilm = filmDAO.findFilmById(rs.getInt("id_film"));
            Film film = optionalFilm.get();
            User user = userDAO.getUserById(rs.getInt("id_membre"));
            Avis avis = new Avis(
                    rs.getInt("id"),
                    rs.getInt("note"),
                    rs.getString("commentaire"),
                    user,
                    film
            );
            return avis;
        }
    }

    @Override
    public List<Avis> getListAvisByFilm(int idFilm) {
        String sql = "SELECT AVIS.id, note, commentaire, id_membre, id_film FROM AVIS" +
                " JOIN MEMBRE m ON m.id = id_membre" +
                " JOIN FILM f ON f.id = id_film" +
                " WHERE id_film=?";
        List<Avis> listAvis = jdbcTemplate.query(sql, new AvisRowMapper(),
                idFilm);
        return listAvis;
    }

    @Override
    public Avis getOneAvisByFilmAndUserId(int idUser, int idFilm) {
        String sql = "SELECT AVIS.id, note, commentaire, id_membre, id_film FROM AVIS" +
                " JOIN MEMBRE m ON m.id = id_membre" +
                " JOIN FILM f ON f.id = id_film" +
                " WHERE id_membre=?" +
                " AND id_film=?";
        Avis avis = jdbcTemplate.queryForObject(sql, new AvisRowMapper(),
                idUser, idFilm);
        return avis;
    }


    @Override
    public void addAvis(Avis avis) {
        String sql = "INSERT INTO AVIS (note, commentaire, id_membre, id_film)" +
                " VALUES (?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, avis.getNote(), avis.getCommentaire(), avis.getUser().getId(),
                    avis.getFilm().getId());
            System.out.println(" depuis la DAO :Commentaire inséré avec succès !");
        } catch (DataAccessException e) {
            System.err.println("DAO dit : Erreur lors de l'insertion du commentaire :/ : " + e.getMessage());
            throw e;
        }

    }
}


