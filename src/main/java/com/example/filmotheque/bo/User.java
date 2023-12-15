package com.example.filmotheque.bo;

import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("MEMBRE")
public class User {
    private Integer id;
    private String pseudo;
    private String motDePasse;
    private Boolean isAdmin;

    private List<Avis> listAvis;



    //===================CONSTRUCTEURS=========================
    public User() {
    }

    public User(Integer id, String pseudo, String motDePasse, Boolean isAdmin) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.isAdmin = isAdmin;
    }

    public User(Integer id, String pseudo, String motDePasse, Boolean isAdmin, List<Avis> listAvis) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.isAdmin = isAdmin;
        this.listAvis = listAvis;
    }

    // ===============================GETTERS SETTERS ==========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }


    public List<Avis> getListAvis() {
        return listAvis;
    }

    public void setListAvis(List<Avis> listAvis) {
        this.listAvis = listAvis;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", isAdmin=" + isAdmin +
                ", listAvis=" + listAvis +
                '}';
    }
}

