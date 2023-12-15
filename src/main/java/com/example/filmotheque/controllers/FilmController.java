package com.example.filmotheque.controllers;

import com.example.filmotheque.bll.services.*;
import com.example.filmotheque.bo.*;

import com.example.filmotheque.security.MembreUserDetail;
import com.example.filmotheque.security.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;


@Controller
public class FilmController {

    private FilmService filmService; // on définit les services
    private GenreService genreService;

    private UserDetailsService userDetailsService;//class JAVA

    private UserService userService;

    private AvisService avisService;
    private ParticipantService participantService;

    public FilmController(FilmService FilmService, UserService userService, UserDetailsService userDetailsService, AvisService avisService, GenreService genreService, ParticipantService participantService) { // on les importe ici
        this.filmService = FilmService;
        this.genreService = genreService;
        this.avisService = avisService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.participantService = participantService;


    }

    //===================GESTION ROUTES ET PAGES===========================

    @GetMapping({"/", "/catalogFilms"})
    public String catalogFilms(Model model) { // appelle le controler

        List<Film> films = filmService.getListFilms();
        model.addAttribute("films", films);
        return "catalogFilms"; // on retourne le nom de la page html
    }


    @GetMapping("/film/details{id}")
    public String showDetail(Model model, int id) {

        Genre genre = genreService.findGenreById(id);
        Optional<Film> film = filmService.findFilmById(id);
        if (film.isEmpty()) {
            System.out.println("film n'a pas été trouvé!!");
            return "error";
        }
        List<Avis> listAvis = avisService.getListAvis(id);
        if (listAvis.isEmpty()) {
            model.addAttribute("film", film.get());
            model.addAttribute("genre", genre);
            return "FilmDetail";
        }

        model.addAttribute("film", film.get());
        model.addAttribute("genre", genre);
        model.addAttribute("avisList", listAvis);
        System.out.println(listAvis);
        return "FilmDetail";
    }


    //=================GESTION FORMULAIRES==================================


    //============FILMS===========================
    @GetMapping("/nouveauFilmForm")
    public String afficherFormulaire(Model model) {
        System.out.println(" ---> en get");
        model.addAttribute("film", new Film());

        List<Genre> listGenres = genreService.getListGenres();
        model.addAttribute("genres", listGenres);

        List<Participant> listParticipants = participantService.getListParticipants();
        model.addAttribute("participants", listParticipants);
        return "nouveauFilmForm";
    }

    @PostMapping("/nouveauFilmForm")
    public String addFilm(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Erreur(s) de validation détectée(s):");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "nouveauFilmForm";
        }

        filmService.addFilm(film);
        model.addAttribute("successMessage", "Le film a été ajouté à la liste avec succès.");
        return "redirect:/catalogFilms";

    }

    //====================AVIS===========================

    @GetMapping("/nouveauAvisForm/film/{id}")
    public String afficherFormAvis(Model model, @PathVariable("id") Integer filmId) {
        System.out.println("dans le GET du AVISFORM");
        model.addAttribute("avis", new Avis());
        model.addAttribute("filmId", filmId);

//on récupère le contexte de l'appli par le sécurityContextHolder, et le user Connnecé
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("user", user);
        return "nouveauAvisForm";
    }


    @PostMapping(value = "/nouveauAvisForm/film/{id}")
    public String addAvis(@PathVariable("id") Integer filmId,
                          Principal principal,
                          @Valid @ModelAttribute("avis") Avis avis,
                          BindingResult bindingResult,
                          Model model) {
        System.out.println("dans le POST du Form AVIS-------------> filmId et customUser");
        System.out.println(filmId);

        String userConnected = principal.getName();
        System.out.println("--->userLog");
        System.out.println(userConnected);


        // on relie le user connecté à l'avis:
        Avis nouvelAvis = new Avis();
        User user = userService.getUserByName(userConnected);
        nouvelAvis.setUser(user);


        if (bindingResult.hasErrors()) {
            System.out.println("Erreur(s) de validation détectée(s):");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "nouveauAvisForm";
        }
        System.out.println("------------------>Controler : AVIS en cours d'ajout");
        avisService.addAvis(avis);
        model.addAttribute("successMessage", "Votre avis a été ajouté à la liste avec succès.");
        return "redirect:/FilmDetail";

    }
}
