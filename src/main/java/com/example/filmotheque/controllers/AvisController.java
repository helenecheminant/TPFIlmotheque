package com.example.filmotheque.controllers;


import com.example.filmotheque.bll.services.AvisService;
import com.example.filmotheque.bll.services.FilmService;
import com.example.filmotheque.bll.services.GenreService;
import com.example.filmotheque.bo.Avis;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AvisController {

    private FilmService filmService;
    private AvisService avisService;
    private UserDetailsService userDetailsService;

    public AvisController(FilmService filmService, AvisService avisService,
                          UserDetailsService userDetailsService) {
        this.filmService = filmService;
        this.avisService = avisService;
        this.userDetailsService = userDetailsService;
    }



    @PostMapping("/avis")
    public String avis() {
        // Logique de gestion de l'inscription ici
        return "avis"; // Ou le nom de la vue correspondante
    }
}
