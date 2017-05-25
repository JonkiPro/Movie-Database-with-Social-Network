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

    @Autowired
    private MovieCRUDRepository movieCRUDRepository;

    @Override
    public void addNewMovie(final MovieDTO movieDTO) {
        movieCRUDRepository.save(new Mapped().mappedToMovie(movieDTO));
    }

    @Override
    public List<Movie> findMovieByTitle(final String title) {
        return movieCRUDRepository.findByTitleIgnoreCaseContaining(title);
    }
}
