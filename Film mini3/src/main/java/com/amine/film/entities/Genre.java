package com.amine.film.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenre;
    private String nomGenre;
    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Film> Film;

    /*public Genre() {

    }


    public Long getIdGen() {
        return idGen;
    }

    public String getNomGen() {
        return nomGen;
    }

    public String getDescriptionGen() {
        return descriptionGen;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setIdGen(Long idGen) {
        this.idGen = idGen;
    }

    public void setNomGen(String nomGen) {
        this.nomGen = nomGen;
    }

    public void setDescriptionGen(String descriptionGen) {
        this.descriptionGen = descriptionGen;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Genre(String nomGen, String descriptionGen, List<Film> films) {
        super();
        this.nomGen = nomGen;
        this.descriptionGen = descriptionGen;
        this.films=films;
    }*/

}
