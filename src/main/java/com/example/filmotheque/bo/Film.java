package com.example.filmotheque.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Film {
    @Id
    private int id;
    @NotBlank(message ="titre obligatoire")
    @Size(min = 2, max= 30, message="la taille doit etre {min} et {max}")
    private String titre;
    private int annee_sortie;

    @Min(value = 5, message = "La durée doit être d'au moins 5 minutes")
    private int duree;
    private String synopsis;

    @NotNull(message = "Le réalisateur est obligatoire!")
    private Participant realisateur; // foreign key

    private Genre genre; // foreign key

    private List<Participant> acteurs = new ArrayList<>();

    private List<Avis> listAvis = new ArrayList<>();



    //===================CONSTRUCTEURS=============================

    public Film() {

    }

    public List<Participant> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Participant> acteurs) {
        this.acteurs = acteurs;
    }

    public Film(int id, String titre, int annee_sortie, int duree, String synopsis, Participant realisateur, Genre genre, List<Participant> acteurs) {
        this.id = id;
        this.titre = titre;
        this.annee_sortie = annee_sortie;
        this.duree = duree;
        this.synopsis = synopsis;
        this.realisateur = realisateur;
        this.genre = genre;
        this.acteurs = acteurs;
    }

    public Film(int id, String titre, int annee_sortie, int duree, String synopsis, Participant realisateur, Genre genre) {
        this.id = id;
        this.titre = titre;
        this.annee_sortie = annee_sortie;
        this.duree = duree;
        this.synopsis = synopsis;
        this.realisateur = realisateur;
        this.genre = genre;
    }

    public Film(int id, String titre, int annee_sortie, int duree, String synopsis, Participant realisateur, Genre genre, List<Participant> acteurs, List<Avis> listAvis) {
        this.id = id;
        this.titre = titre;
        this.annee_sortie = annee_sortie;
        this.duree = duree;
        this.synopsis = synopsis;
        this.realisateur = realisateur;
        this.genre = genre;
        this.acteurs = acteurs;
        this.listAvis = listAvis;
    }

    //===============GETTERS SETTERS ==========================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee_sortie() {
        return annee_sortie;
    }

    public void setAnnee_sortie(int annee_sortie) {
        this.annee_sortie = annee_sortie;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Participant getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public List<Avis> getListAvis() {
        return listAvis;
    }

    public void setListAvis(List<Avis> listAvis) {
        this.listAvis = listAvis;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", annee_sortie=" + annee_sortie +
                ", duree=" + duree +
                ", synopsis='" + synopsis + '\'' +
                ", realisateur=" + realisateur +
                ", genre=" + genre +
                ", acteurs=" + acteurs +
                ", listAvis=" + listAvis +
                '}';
    }
}