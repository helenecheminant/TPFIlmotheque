package com.example.filmotheque.bo;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("AVIS")
public class Avis {
    @Id
    private int id;
    @Min(value = 0, message = "La note doit être d'au moins 0.")
    @Max(value = 10, message = "La note ne peut pas dépasser 10.")
    private int note;

    @NotBlank(message= "écrivez au moins un truc!")
    private String commentaire;

    private User user;

    private Film film;



    //=======================CONSTRUCTEURS==============================

    public Avis() {
    }

    public Avis(int id, int note, String commentaire, User user, Film film) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.user = user;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                ", user=" + user +
                ", film=" + film +
                '}';
    }
}
