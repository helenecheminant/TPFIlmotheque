package com.example.filmotheque.dal.user;
import com.example.filmotheque.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //======================METHODES========================

    @Override
    public User getUserFromDB(String pseudo) {
        User user = jdbcTemplate.queryForObject("SELECT id, pseudo, motDePasse, admin FROM MEMBRE " +
                        " WHERE pseudo = ? ", new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(rs.getInt("id"),
                                rs.getString("pseudo"),
                                rs.getString("motDePasse"),
                                rs.getBoolean("admin"));
                    }
                },
                pseudo);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = jdbcTemplate.queryForObject("SELECT id, pseudo, motDePasse, admin FROM MEMBRE " +
                        " WHERE id = ? ", new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(rs.getInt("id"),
                                rs.getString("pseudo"),
                                rs.getString("motDePasse"),
                                rs.getBoolean("admin"));
                    }
                },
                id);
        return user;
    }


}
