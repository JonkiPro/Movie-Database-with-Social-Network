package com.jonki.ServiceImpl;

import com.jonki.DAO.MovieCRUDRepository;
import com.jonki.DTO.MovieDTO;
import com.jonki.Entity.Movie;
import com.jonki.Service.MovieService;
import com.jonki.Validator.Mapped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private MovieCRUDRepository movieCRUDRepository;

    @Autowired
    public MovieServiceImpl(MovieCRUDRepository movieCRUDRepository) {
        this.movieCRUDRepository = movieCRUDRepository;
    }

    @Override
    public void addNewMovie(final MovieDTO movieDTO) {
        movieCRUDRepository.save(new Mapped().mappedToMovie(movieDTO));
    }

    @Override
    public List<Movie> findMovieByTitle(final String title) {
        return movieCRUDRepository.findByTitleIgnoreCaseContaining(title);
    }

    @Override
    public List<Movie> findMovieByIDAuthor(final Long IDAuthor) {
        return movieCRUDRepository.findByIDAuthor(IDAuthor);
    }

    @Override
    public Long countAddedMoviesByIdAuthor(final Long IDAuthor) {
        return movieCRUDRepository.countByIDAuthor(IDAuthor);
    }
}
