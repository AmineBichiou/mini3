package com.amine.film.controllers;

import com.amine.film.entities.Film;
import com.amine.film.entities.Genre;
import com.amine.film.service.FilmService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FilmController {
    @Autowired
    FilmService filmService;
    @RequestMapping("/showCreate")
    public String showCreate( ModelMap ModelMap)
    {
        List<Genre> genres = filmService.getAllGenre();
        Film film = new Film();
        Genre genre = new Genre();
        genre = genres.get(0);
        film.setGenre(genre);
        ModelMap.addAttribute("film",film);
        ModelMap.addAttribute("mode", "new");
        ModelMap.addAttribute("genres", genres);
        return "createFilm";
    }
    /*@RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap)
    {
        List<Company> coms = avionService.getAllCompanys();
        Avion prod = new Avion();
        Company com = new Company();
        com = coms.get(0); // prendre la première catégorie de la liste
        prod.setCompany(com); //affedter une catégorie par défaut au produit pour éviter le pb avec une catégorie null
        modelMap.addAttribute("avion",prod);
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("companys", coms);
        return "formAvion";
    }*/
    /*@RequestMapping("/saveFilm")
    public String saveFilm(@ModelAttribute("film") Film film,
                              @RequestParam("date") String date,
                              ModelMap modelMap) throws
            ParseException
    {
//conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        film.setDateCreation(dateCreation);

        Film saveFilm = filmService.saveFilm(film);
        String msg ="Film enregistré avec Id "+saveFilm.getIdFilm();
        modelMap.addAttribute("msg", msg);
        /*return "createFilm";*/
        /*return "redirect:/ListeFilms";
    }*/
    @RequestMapping("/saveFilm")
    public String saveFilm(@Valid Film film, BindingResult bindingResult,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "4") int size)

    {
        if (bindingResult.hasErrors()) /*return "redirect:/ListeFilms"*/return "createFilm";
        filmService.saveFilm(film);
        return "redirect:/ListeFilms?page=" + (filmService.getAllFilmsParPage(page, size).getTotalPages() - 1) + "&size=" + size;
    }
    @RequestMapping("/ListeFilms")
    public String listeProduits(ModelMap modelMap,
                                @RequestParam (name="page",defaultValue = "0") int page,
                                @RequestParam (name="size", defaultValue = "4") int size)
    {
        Page<Film> prods = filmService.getAllFilmsParPage(page, size);
        modelMap.addAttribute("films", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        /*List<Film> fil = filmService.getAllFilm();
        modelMap.addAttribute("films", fil);*/
        return "listeFilms";
    }
    @RequestMapping("/")
    public String function (ModelMap modelMap,
                                @RequestParam (name="page",defaultValue = "0") int page,
                                @RequestParam (name="size", defaultValue = "4") int size)
    {
        Page<Film> prods = filmService.getAllFilmsParPage(page, size);
        modelMap.addAttribute("films", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        /*List<Film> fil = filmService.getAllFilm();
        modelMap.addAttribute("films", fil);*/
        return "listeFilms";
    }
    @RequestMapping("/supprimerFilm")
    public String supprimerFilm(@RequestParam("id") Long id,
                                   ModelMap modelMap,
                                   @RequestParam (name="page",defaultValue = "0") int page,
                                   @RequestParam (name="size", defaultValue = "4") int size)
    {
        filmService.deleteFilmById(id);
        Page<Film> prods = filmService.getAllFilmsParPage(page,
                size);
        modelMap.addAttribute("films", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeFilms";
    }
    @RequestMapping("/modifierFilm")
    public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Film f= filmService.getFilm(id);
        List<Genre> genres = filmService.getAllGenre();
        modelMap.addAttribute("film", f);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("genres", genres);
        return "createFilm";
    }
    /*@RequestMapping("/updateFilm")
    public String updateProduit(@ModelAttribute("film") Film film,
                                @RequestParam("date") String date,
    {
//conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        film.setDateCreation(dateCreation);

        filmService.updateFilm(film);
        List<Film> prods = filmService.getAllFilmsParPage();
        modelMap.addAttribute("films", prods);
        return "listeProduits";
    }*/



}
