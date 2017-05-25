package com.jonki.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "id_author")
    private Long IDAuthor;

    @Column(name = "title")
    private String title;

    @Column(name = "other_titles")
    private String otherTitles;

    @Column(name = "director")
    private String director;

    @Column(name = "writers")
    private String writers;

    @Column(name = "budget")
    private String budget;

    @Column(name = "box_office")
    private String boxOffice;

    @Column(name = "official_site")
    private String officialSite;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "type")
    private String type;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "genres")
    private String genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIDAuthor() {
        return IDAuthor;
    }

    public void setIDAuthor(Long IDAuthor) {
        this.IDAuthor = IDAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOtherTitles() {
        return otherTitles;
    }

    public void setOtherTitles(String otherTitles) {
        this.otherTitles = otherTitles;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
