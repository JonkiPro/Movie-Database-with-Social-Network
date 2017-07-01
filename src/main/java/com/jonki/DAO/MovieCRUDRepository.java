package com.jonki.DAO;

import com.jonki.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MovieCRUDRepository extends JpaRepository<Movie, Long> {

    @Transactional(readOnly = true)
    public List<Movie> findByTitleIgnoreCaseContaining(String title);

    @Transactional(readOnly = true)
    @Modifying(clearAutomatically = true)
    @Query("SELECT e FROM Movie e WHERE e.IDAuthor = :IDAuthor")
    public List<Movie> findByIDAuthor(@Param(("IDAuthor")) Long IDAuthor);

    @Query("SELECT COUNT(e) FROM Movie e WHERE e.IDAuthor = :IDAuthor")
    public Long countByIDAuthor(@Param("IDAuthor") Long IDAuthor);
}