package com.amine.film.restcontroller;

import com.amine.film.entities.Film;
import com.amine.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin

public class FilmRestController {
    @Autowired
    public FilmService filmService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Film> getAllFilm(){
        return filmService.getAllFilm();
    }
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Film getFilmById(@PathVariable("id") Long id) {
        return filmService.getFilm(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Film createFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void deleteFilm(@PathVariable("id") Long id)
    {
        filmService.deleteFilmById(id);
    }
    @RequestMapping(value="/filmgenre/{idGenre}",method = RequestMethod.GET)
    public List<Film> getFilmByGenreIdGenre(@PathVariable("idGenre") Long idGenre) {
        return filmService.findByGenreIdGenre(idGenre);
    }
    @RequestMapping(value="/FilmByName/{nom}",method = RequestMethod.GET)
    public List<Film> findByNomFilmContains(@PathVariable("nom") String nom) {
        return filmService.findByNomFilmContains(nom);
    }
}
