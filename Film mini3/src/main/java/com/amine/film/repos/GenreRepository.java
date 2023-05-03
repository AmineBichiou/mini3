package com.amine.film.repos;

import com.amine.film.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository  extends JpaRepository<Genre, Long> {
}
