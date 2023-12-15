package com.example.filmotheque.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("GENRE")
public class Genre {

    @Id
    private int id;
    @Column("libelle")
    private String libelle;

    public Genre(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Genre() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Genre [id=" + id + ", libelle=" + libelle + "]";
    }
}
