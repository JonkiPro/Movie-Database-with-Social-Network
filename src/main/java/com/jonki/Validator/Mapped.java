package com.jonki.Validator;

import com.jonki.DTO.MovieDTO;
import com.jonki.Entity.Movie;

public class Mapped {

    public Movie mappedToMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setIDAuthor(movieDTO.getIDAuthor());
        movie.setOtherTitles(movieDTO.getOtherTitles());
        movie.setTitle(movieDTO.getTitle());
        movie.setDirector(movieDTO.getDirector());
        movie.setWriters(movieDTO.getWriters());
        movie.setBudget(movieDTO.getBudget());
        movie.setBoxOffice(movieDTO.getBoxOffice());
        movie.setOfficialSite(movieDTO.getOfficialSite());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setType(movieDTO.getType());

        if (movieDTO.getCountry() != null) {
            for (int i = 0; i < movieDTO.getCountry().size(); ++i) {
                if (i == 0) {
                    movie.setCountry(movieDTO.getCountry().get(0));
                } else {
                    movie.setCountry(movie.getCountry() + ", " + movieDTO.getCountry().get(i));
                }
            }
        }

        if (movieDTO.getLanguage() != null) {
            for (int i = 0; i < movieDTO.getLanguage().size(); ++i) {
                if (i == 0) {
                    movie.setLanguage(movieDTO.getLanguage().get(0));
                } else {
                    movie.setLanguage(movie.getLanguage() + ", " + movieDTO.getLanguage().get(i));
                }
            }
        }

        if (movieDTO.getGenres() != null) {
            for (int i = 0; i < movieDTO.getGenres().size(); ++i) {
                if (i == 0) {
                    movie.setGenres(movieDTO.getGenres().get(0));
                } else {
                    movie.setGenres(movie.getGenres() + ", " + movieDTO.getGenres().get(i));
                }
            }
        }

        return movie;
    }
}
