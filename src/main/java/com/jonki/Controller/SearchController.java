package com.jonki.Controller;

import com.jonki.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String searchMovies(@RequestParam(value = "search", required = false, defaultValue = "") final String search,
                               final Model model) {
        model.addAttribute("search", search);
        model.addAttribute("listMovies", movieService.findMovieByTitle(search));

        return "search";
    }
}
