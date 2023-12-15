/*
package com.example.filmotheque.dal;

import com.example.filmotheque.bo.Film;
import com.example.filmotheque.bo.Genre;
import com.example.filmotheque.bo.Participant;
import com.example.filmotheque.bo.Roles;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static com.example.filmotheque.bo.Roles.ACTEUR;
import static com.example.filmotheque.bo.Roles.REALISATEUR;


@Repository
public class FilmsDAOMock {


    //=============================================GENRES==========================================
    private static final Genre SF = new Genre(2, "SciFi");
    private static final Genre Comedie = new Genre(5, "Comédie");

    private static final Genre Drame = new Genre(1, "Drame");
    private static final Genre Action = new Genre(3, "Action");

    private final List<Genre> listGenres = Arrays.asList(
            new Genre(2, "SciFi"),
            new Genre(5, "Comédie"),
            new Genre(1, "Drame"),
            new Genre(3, "Action"));

    @ModelAttribute("genres")
    public List<Genre> getListGenres() {
        System.out.println("--------------> coucou depuis la dao : ListGenres");
        return listGenres;

    }


    public static List<Participant> allParticipants = new ArrayList<>();

    static {
        Participant steven = new Participant(1, "Steven", "Spielberg", new Roles[]{REALISATEUR});
        Participant richard = new Participant(2, "Richard", "Attenborough", new Roles[]{ACTEUR});
        Participant Jeff = new Participant(3, "Jeff", "Goldblum", new Roles[]{REALISATEUR});
        Participant David = new Participant(4, "David", "Cronenberg", new Roles[]{REALISATEUR});
        Participant Danny = new Participant(5, "Danny", "Boon", new Roles[]{REALISATEUR, ACTEUR});

        Participant Geena = new Participant(6, "Geena", "Davis", new Roles[]{ACTEUR});
        Participant Mark = new Participant(7, "Mark", "Rylance", new Roles[]{ACTEUR});
        Participant Ruby = new Participant(8, "Ruby", "Barnhill", new Roles[]{ACTEUR});

        Participant Kad = new Participant(9, "Kad", "Merad", new Roles[]{ACTEUR});

        // on push les participants dans la liste
        allParticipants.add(steven);
        allParticipants.add(richard);
        allParticipants.add(Jeff);
        allParticipants.add(Mark);
        allParticipants.add(Ruby);
        allParticipants.add(Kad);
        allParticipants.add(richard);
        allParticipants.add(Danny);
        allParticipants.add(David);
        allParticipants.add(Geena);

    }


    //=======================================FILMS======================================================

    //on crée une nouvelle liste pour mettre tous les films
    public static List<Film> createListFilms() {
        List<Film> allFilms = new ArrayList<>();


        Film filmJurrassic = new Film(1, "Jurassic Park", " Le film raconte l'histoire d'un milliardaire et son équipe de\n" +
                "    généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.", 1993, 123, new ArrayList<>(Arrays.asList(allParticipants.get(0), allParticipants.get(1))), SF);


        Film filmTheFly = new Film(2, "The Fly", "Il s'agit de l'adaptation cinématographique de la nouvelle éponyme de\n" +
                "    l'auteur George Langelaan.", 1993, 95, new ArrayList<Participant>(), SF);

        Film filmTheBFG = new Film(3, "The BFG", " Le Bon Gros Géant est un géant bien différent des autres habitants du pays des géants.", 2016, 95, new ArrayList<>((Arrays.asList(allParticipants.get(3), allParticipants.get(5), allParticipants.get(4)))), Comedie);

        Film filmBienvenue = new Film(4, "Bienvenue chez les Ch'tis", " Philippe Abrams est directeur de la poste de Salon-de-Provence. Il est\n" +
                "    marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui\n" +
                "    faire plaisir, Philippe fraude afin d'obtenir une mutation sur la Côte d'Azur.\n" +
                "    Mais il est démasqué: il sera muté à Bergues, petite ville du Nord.", 2008, 106, new ArrayList<>((Arrays.asList(allParticipants.get(5), allParticipants.get(6), allParticipants.get(7)))), Comedie);

        // on push les films dans la liste
        allFilms.add(filmBienvenue);
        allFilms.add(filmJurrassic);
        allFilms.add(filmTheBFG);
        allFilms.add(filmTheFly);
        return allFilms;
    }


    // ==============================METHODES=======================================

    private static List<Film> allFilms = createListFilms(); // Initialise la liste avec les films créés
    List<Film> listFilms = new ArrayList<>();


    // pour ajouter un film depuis le formulaire
    @Override
    public void addFilm(Film film) {
        System.out.println("---------->coucou depuis la DAO : la méthode addFilm");
        allFilms.add(film);
    }

    // pour retourner la liste des films
    @Override
    public List<Film> getListFilms() {
        return listFilms;
    }

    // pour afficher le catalogue
    @Override
    public List<Film> findAllFilms() {
        System.out.println("-------------->coucou depuis DAO - la méthode findAllFilms");
        return new ArrayList<>(allFilms);
    }


    @Override
    public List<Participant> getListParticipants() {
        return allParticipants;
    }

    @Override
    public Participant getParticipantById(Integer partcipantId) {
        for (Participant participant : allParticipants) {
            if (participant.getId()==(partcipantId)) {
                return participant;
            }
        }
        return null;
    }
    @Override
    public Genre getGenreById(Integer genreId) {
        for (Genre genre : listGenres) {
            if (genre.getId()==(genreId)) {
                return genre;
            }
        }
        return null;
}

    // pour choper un film avec son id
    @Override
    public Optional<Film> findFilmById(int id) {
        return allFilms.stream().filter(film -> film.getId() == id).findFirst();
    }

}
*/
