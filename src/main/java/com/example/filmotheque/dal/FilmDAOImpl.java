package com.example.filmotheque.dal;

import com.example.filmotheque.bo.Film;
import com.example.filmotheque.bo.Genre;
import com.example.filmotheque.bo.Participant;
import com.example.filmotheque.dal.genre.GenreDAO;
import com.example.filmotheque.dal.participant.ParticipantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmDAOImpl implements FilmDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private GenreDAO genreDao;
    private ParticipantDAO participantDAO;

    @Autowired
    public FilmDAOImpl(GenreDAO genreDao, ParticipantDAO participantDAO) {
        this.genreDao = genreDao;
        this.participantDAO = participantDAO;
    }


    //===================METHODES======================


    // On fait un rowMapper personnalisé
    private class FilmRowMapper implements RowMapper<Film> {
        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Optional<Participant> optRealisateur = participantDAO.getParticipantById(rs.getInt(7));
            Participant realisateur = optRealisateur.get();
            Optional<Genre> optGenre = Optional.ofNullable(genreDao.getGenreById(rs.getInt(6)));
            Genre genre = optGenre.get();

            Film film = new Film(rs.getInt(1), rs.getString(2), // titre
                    rs.getInt(3), // annee-sortie
                    rs.getInt(4), // duree
                    rs.getString(5),// annee
                    realisateur, // realisateur
                    genre// genre
            );

            // map acteurs (Utilisation d'une methode dans ParticipantDAOIMpl => requete sql pour récupérer la liste des acteurs du film)
          //  List<Participant> acteurs = participantDAO.getParticipantById(film.getId());
           // film.setActeurs(acteurs);

            return film;
        }
    }

    @Override
    public List<Participant> findActeursByFilm(int idFilm) {
        List<Participant> listActeurs= new ArrayList<>();

        String sql = "SELECT p.id as id_acteur , prenom, nom " +
                "FROM acteurs a " +
                "JOIN participants p ON p.id =  a.id_participant " +
                "WHERE a.id_film = ?";
        try {
            listActeurs = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Participant.class),
                    idFilm ); // PARAMETRE
        } catch (Exception e) {
            System.out.println("Erreur DAO!! getListActeursByFilm : ");
            e.printStackTrace();
        }
        return listActeurs;
    }

    @Override
    public List<Film> getListFilmsFromDB() {
        String sql = "SELECT FILM.id, titre, annee_sortie, duree, synopsis,  id_genre, id_realisateur FROM FILM" +
                " JOIN GENRE g ON g.id = id_genre " +
                "JOIN PARTICIPANT p ON p.id = id_realisateur";
        List<Film> listFilms = jdbcTemplate.query(sql, new FilmRowMapper());
        return listFilms;
    }

    @Override
    public void addFilm(Film film) {
        String sql = "INSERT INTO FILM (titre, duree, annee_sortie, synopsis, id_genre, id_realisateur)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, film.getTitre(), film.getDuree(), film.getAnnee_sortie(),
                    film.getSynopsis(), film.getGenre().getId(), film.getRealisateur().getId());
            System.out.println("Film inséré avec succès !");
        } catch (DataAccessException e) {
            System.err.println("Erreur lors de l'insertion d'un nouveau film : " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Optional<Film> findFilmById(int id) {
        String sql = "SELECT FILM.id, titre, annee_sortie, duree, synopsis, id_genre, id_realisateur FROM FILM" +
                " JOIN GENRE g ON g.id = id_genre " +
                "JOIN PARTICIPANT p ON p.id = id_realisateur" +
                " WHERE FILM.id=?";
        Optional<Film> optFilm = Optional.empty();

        try {
            optFilm = Optional.ofNullable(jdbcTemplate.queryForObject(sql, new FilmRowMapper(), id));
        } catch (DataAccessException dae) {
            System.out.println("erreur sur le findFilmById");
            dae.printStackTrace();
        }
        return optFilm;
    }
}

