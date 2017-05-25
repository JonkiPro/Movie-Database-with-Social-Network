package com.jonki.DAO;

import com.jonki.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MovieCRUDRepository extends JpaRepository<Movie, Long> {

    @Transactional(readOnly = true)
    public List<Movie> findByTitleIgnoreCaseContaining(String title);
}
