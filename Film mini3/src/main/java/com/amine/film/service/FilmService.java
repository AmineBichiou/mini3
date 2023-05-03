package com.amine.film.service;

import com.amine.film.entities.Film;
import com.amine.film.entities.Genre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {
    Film saveFilm(Film f);
    Film updateFilm(Film f);
    void deleteFilm(Film f);
    void deleteFilmById(Long id);
    Film getFilm(Long id);
    List<Film> getAllFilm();
    List<Genre> getAllGenre();
    Page<Film> getAllFilmsParPage(int page, int size);
    List<Film> findByNomFilm(String nom);
    List<Film> findByNomFilmContains(String nom);
    List<Film> findByGenre (Genre genre);
    List<Film> findByGenreIdGenre(Long id);
    List<Film> findByOrderByNomFilmAsc();
    List<Film> trierFilmNomsRate();
}
