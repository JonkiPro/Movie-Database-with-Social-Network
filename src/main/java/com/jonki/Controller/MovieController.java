package com.jonki.Controller;

import com.jonki.DTO.MovieDTO;
import com.jonki.Entity.Movie;
import com.jonki.Entity.User;
import com.jonki.Service.AuthorizationService;
import com.jonki.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/new")
    public String newMovie_step1(final HttpServletRequest request,
                                 final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if(!authorizationService.isLogged()) {
            return "redirect:/";
        }

        return "addNewMovie_CheckTitle";
    }

    @PostMapping("/new/checkTitle")
    public String changeTitle(final HttpServletRequest request,
                              final Model model) {
        List<Movie> listMovies = movieService.findMovieByTitle(request.getParameter("title"));

        if(request.getParameter("title").length() == 0) {
            model.addAttribute("errorTitle", true);
            return "addNewMovie_CheckTitle";
        }

        if(listMovies != null && listMovies.size() > 0) {
            model.addAttribute("showInfo", true);
            model.addAttribute("listMovies", listMovies);
            model.addAttribute("text", "Several movies found:");
        } else {
            model.addAttribute("showInfo", true);
            model.addAttribute("text", "No title found!");
        }
        model.addAttribute("title", request.getParameter("title"));

        return "addNewMovie_CheckTitle";
    }

    @PostMapping("/new/movieInfo")
    public String addNewMovie(final HttpServletRequest request,
                              final Model model) {
        model.addAttribute(new MovieDTO());
        model.addAttribute("title", request.getParameter("title"));

        return "addNewMovie_MovieInfo";
    }

    @PostMapping("/new/success")
    public String addNewMovie(@ModelAttribute("movieDTO") MovieDTO movieDTO,
                              final HttpSession session) {
        movieDTO.setIDAuthor(((User) session.getAttribute("user")).getId());

        movieService.addNewMovie(movieDTO);

        return "addNewMovie_AddedSuccessfully";
    }
}
