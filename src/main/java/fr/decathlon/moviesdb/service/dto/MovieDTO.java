package fr.decathlon.moviesdb.service.dto;

import java.time.LocalDate;
import java.time.Duration;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.decathlon.moviesdb.domain.Movie} entity.
 */
public class MovieDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    private Integer year;

    private String rate;

    private LocalDate releaseDate;

    private Duration runtime;

    private String genre;

    private String director;

    private String writers;

    private String actors;

    private String plot;

    private String language;

    private String country;

    private String awards;

    private String posterLink;

    private String ratings;

    private Integer metascore;

    private Double imdbRating;

    private Integer imdbVotes;

    @NotNull
    private String imdbId;

    private String type;

    private LocalDate dvdReleaseDate;

    private Integer boxOffice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Duration getRuntime() {
        return runtime;
    }

    public void setRuntime(Duration runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public Integer getMetascore() {
        return metascore;
    }

    public void setMetascore(Integer metascore) {
        this.metascore = metascore;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Integer getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(Integer imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDvdReleaseDate() {
        return dvdReleaseDate;
    }

    public void setDvdReleaseDate(LocalDate dvdReleaseDate) {
        this.dvdReleaseDate = dvdReleaseDate;
    }

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieDTO)) {
            return false;
        }

        return id != null && id.equals(((MovieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", year=" + getYear() +
            ", rate='" + getRate() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", runtime='" + getRuntime() + "'" +
            ", genre='" + getGenre() + "'" +
            ", director='" + getDirector() + "'" +
            ", writers='" + getWriters() + "'" +
            ", actors='" + getActors() + "'" +
            ", plot='" + getPlot() + "'" +
            ", language='" + getLanguage() + "'" +
            ", country='" + getCountry() + "'" +
            ", awards='" + getAwards() + "'" +
            ", posterLink='" + getPosterLink() + "'" +
            ", ratings='" + getRatings() + "'" +
            ", metascore=" + getMetascore() +
            ", imdbRating=" + getImdbRating() +
            ", imdbVotes=" + getImdbVotes() +
            ", imdbId='" + getImdbId() + "'" +
            ", type='" + getType() + "'" +
            ", dvdReleaseDate='" + getDvdReleaseDate() + "'" +
            ", boxOffice=" + getBoxOffice() +
            "}";
    }
}
