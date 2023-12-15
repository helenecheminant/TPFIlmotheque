package com.example.filmotheque.controllers;

import com.example.filmotheque.bll.services.GenreService;
import com.example.filmotheque.bo.Genre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component //Définir le converter en tant que bean Spring
public class StringToGenreConverter implements Converter<String, Genre> {

    private GenreService service;

    @Autowired
    public void setGenreService(GenreService service) {
        this.service = service;
    }

    @Override
    public Genre convert(String id) {
        Integer theid = Integer.parseInt(id);
        return service.findGenreById(theid);
    }

}

