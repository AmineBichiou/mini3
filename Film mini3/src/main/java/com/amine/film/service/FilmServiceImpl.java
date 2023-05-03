package com.amine.film.service;

import com.amine.film.entities.Film;
import com.amine.film.entities.Genre;
import com.amine.film.repos.FilmRepository;
import com.amine.film.repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    FilmRepository FilmRepository;
    @Autowired
    GenreRepository GenreRepository;
    @Override
    public Film saveFilm(Film f) {

        return FilmRepository.save(f);
    }

    @Override
    public Film updateFilm(Film f) {
        return FilmRepository.save(f);
    }

    @Override
    public void deleteFilm(Film f) {
        FilmRepository.delete(f);

    }

    @Override
    public void deleteFilmById(Long id) {
        FilmRepository.deleteById(id);

    }

    @Override
    public Film getFilm(Long id) {
        return FilmRepository.findById(id).get();
    }

    @Override
    public List<Film> getAllFilm() {
        return FilmRepository.findAll();
    }
    public Page<Film> getAllFilmsParPage(int page, int size) {
        return FilmRepository.findAll(PageRequest.of(page, size));
    }
    @Override
    public List<Film> findByNomFilm(String nom) {
        return FilmRepository.findByNomFilm(nom);
    }

    @Override
    public List<Film> findByNomFilmContains(String nom) {
        return FilmRepository.findByNomFilmContains(nom);
    }



    @Override
    public List<Film> findByGenre(Genre Genre) {
        return FilmRepository.findByGenre(Genre);
    }

    @Override
    public List<Film> findByGenreIdGenre(Long id) {
        return FilmRepository.findByGenreIdGenre(id);
    }

    @Override
    public List<Film> findByOrderByNomFilmAsc() {
        return FilmRepository.findByOrderByNomFilmAsc();
    }

    @Override
    public List<Film> trierFilmNomsRate() {
        return FilmRepository.trierFilmNomsRate();
    }
    @Override
    public List<Genre> getAllGenre() {
        return GenreRepository.findAll();
    }
}
