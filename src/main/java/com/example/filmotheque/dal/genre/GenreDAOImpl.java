package com.example.filmotheque.dal.genre;

import com.example.filmotheque.bo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class GenreDAOImpl implements GenreDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //=============================METHODES======================================

    public int getNbGenres() {
        int nbGenres = jdbcTemplate.queryForObject("SELECT count(*) from GENRE", Integer.class);
        return nbGenres;
    }

    @Override
    public List<Genre> getlistGenresFromDB() {
        List<Genre> listGenres = jdbcTemplate.query("SELECT id, libelle FROM GENRE", new BeanPropertyRowMapper<>(Genre.class));
        listGenres.forEach(System.out::println);
        return listGenres;
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = jdbcTemplate.queryForObject("SELECT *  FROM GENRE WHERE id=?", new RowMapper<Genre>() {
                    @Override
                    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Genre(rs.getInt(1), rs.getString(2));
                    }
                },
                2);
        return genre;
    }
}
