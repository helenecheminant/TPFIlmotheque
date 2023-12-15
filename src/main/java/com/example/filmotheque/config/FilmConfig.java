package com.example.filmotheque.config;

import com.example.filmotheque.bll.services.GenreService;
import com.example.filmotheque.bo.Genre;
import com.example.filmotheque.controllers.StringToParticipantListConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

//@Configuration
public class FilmConfig {
   // @Bean
    @ApplicationScope
    public List<Genre> genres (GenreService service){
        return service.getListGenres();
    }

    //@Bean
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToParticipantListConverter());
    }

}
