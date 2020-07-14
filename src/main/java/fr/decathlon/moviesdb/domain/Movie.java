package fr.decathlon.moviesdb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Duration;
import java.util.Objects;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "year")
    private Integer year;

    @Column(name = "rate")
    private String rate;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "runtime")
    private Duration runtime;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "writers")
    private String writers;

    @Column(name = "actors")
    private String actors;

    @Column(name = "plot")
    private String plot;

    @Column(name = "language")
    private String language;

    @Column(name = "country")
    private String country;

    @Column(name = "awards")
    private String awards;

    @Column(name = "poster_link")
    private String posterLink;

    @Column(name = "ratings")
    private String ratings;

    @Column(name = "metascore")
    private Integer metascore;

    @Column(name = "imdb_rating")
    private Double imdbRating;

    @Column(name = "imdb_votes")
    private Integer imdbVotes;

    @NotNull
    @Column(name = "imdb_id", nullable = false, unique = true)
    private String imdbId;

    @Column(name = "type")
    private String type;

    @Column(name = "dvd_release_date")
    private LocalDate dvdReleaseDate;

    @Column(name = "box_office")
    private Integer boxOffice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public Movie year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public Movie rate(String rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Movie releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Duration getRuntime() {
        return runtime;
    }

    public Movie runtime(Duration runtime) {
        this.runtime = runtime;
        return this;
    }

    public void setRuntime(Duration runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public Movie genre(String genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public Movie director(String director) {
        this.director = director;
        return this;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriters() {
        return writers;
    }

    public Movie writers(String writers) {
        this.writers = writers;
        return this;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getActors() {
        return actors;
    }

    public Movie actors(String actors) {
        this.actors = actors;
        return this;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public Movie plot(String plot) {
        this.plot = plot;
        return this;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public Movie language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public Movie country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public Movie awards(String awards) {
        this.awards = awards;
        return this;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public Movie posterLink(String posterLink) {
        this.posterLink = posterLink;
        return this;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getRatings() {
        return ratings;
    }

    public Movie ratings(String ratings) {
        this.ratings = ratings;
        return this;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public Integer getMetascore() {
        return metascore;
    }

    public Movie metascore(Integer metascore) {
        this.metascore = metascore;
        return this;
    }

    public void setMetascore(Integer metascore) {
        this.metascore = metascore;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public Movie imdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
        return this;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Integer getImdbVotes() {
        return imdbVotes;
    }

    public Movie imdbVotes(Integer imdbVotes) {
        this.imdbVotes = imdbVotes;
        return this;
    }

    public void setImdbVotes(Integer imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public Movie imdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public Movie type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDvdReleaseDate() {
        return dvdReleaseDate;
    }

    public Movie dvdReleaseDate(LocalDate dvdReleaseDate) {
        this.dvdReleaseDate = dvdReleaseDate;
        return this;
    }

    public void setDvdReleaseDate(LocalDate dvdReleaseDate) {
        this.dvdReleaseDate = dvdReleaseDate;
    }

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public Movie boxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
        return this;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }
        return id != null && id.equals(((Movie) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Movie{" +
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
