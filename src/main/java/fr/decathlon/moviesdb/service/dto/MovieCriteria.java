package fr.decathlon.moviesdb.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.DurationFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link fr.decathlon.moviesdb.domain.Movie} entity. This class is used
 * in {@link fr.decathlon.moviesdb.web.rest.MovieResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /movies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MovieCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter year;

    private StringFilter rate;

    private LocalDateFilter releaseDate;

    private DurationFilter runtime;

    private StringFilter genre;

    private StringFilter director;

    private StringFilter writers;

    private StringFilter actors;

    private StringFilter plot;

    private StringFilter language;

    private StringFilter country;

    private StringFilter awards;

    private StringFilter posterLink;

    private StringFilter ratings;

    private IntegerFilter metascore;

    private DoubleFilter imdbRating;

    private IntegerFilter imdbVotes;

    private StringFilter imdbId;

    private StringFilter type;

    private LocalDateFilter dvdReleaseDate;

    private IntegerFilter boxOffice;

    public MovieCriteria() {
    }

    public MovieCriteria(MovieCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.releaseDate = other.releaseDate == null ? null : other.releaseDate.copy();
        this.runtime = other.runtime == null ? null : other.runtime.copy();
        this.genre = other.genre == null ? null : other.genre.copy();
        this.director = other.director == null ? null : other.director.copy();
        this.writers = other.writers == null ? null : other.writers.copy();
        this.actors = other.actors == null ? null : other.actors.copy();
        this.plot = other.plot == null ? null : other.plot.copy();
        this.language = other.language == null ? null : other.language.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.awards = other.awards == null ? null : other.awards.copy();
        this.posterLink = other.posterLink == null ? null : other.posterLink.copy();
        this.ratings = other.ratings == null ? null : other.ratings.copy();
        this.metascore = other.metascore == null ? null : other.metascore.copy();
        this.imdbRating = other.imdbRating == null ? null : other.imdbRating.copy();
        this.imdbVotes = other.imdbVotes == null ? null : other.imdbVotes.copy();
        this.imdbId = other.imdbId == null ? null : other.imdbId.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.dvdReleaseDate = other.dvdReleaseDate == null ? null : other.dvdReleaseDate.copy();
        this.boxOffice = other.boxOffice == null ? null : other.boxOffice.copy();
    }

    @Override
    public MovieCriteria copy() {
        return new MovieCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public IntegerFilter getYear() {
        return year;
    }

    public void setYear(IntegerFilter year) {
        this.year = year;
    }

    public StringFilter getRate() {
        return rate;
    }

    public void setRate(StringFilter rate) {
        this.rate = rate;
    }

    public LocalDateFilter getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateFilter releaseDate) {
        this.releaseDate = releaseDate;
    }

    public DurationFilter getRuntime() {
        return runtime;
    }

    public void setRuntime(DurationFilter runtime) {
        this.runtime = runtime;
    }

    public StringFilter getGenre() {
        return genre;
    }

    public void setGenre(StringFilter genre) {
        this.genre = genre;
    }

    public StringFilter getDirector() {
        return director;
    }

    public void setDirector(StringFilter director) {
        this.director = director;
    }

    public StringFilter getWriters() {
        return writers;
    }

    public void setWriters(StringFilter writers) {
        this.writers = writers;
    }

    public StringFilter getActors() {
        return actors;
    }

    public void setActors(StringFilter actors) {
        this.actors = actors;
    }

    public StringFilter getPlot() {
        return plot;
    }

    public void setPlot(StringFilter plot) {
        this.plot = plot;
    }

    public StringFilter getLanguage() {
        return language;
    }

    public void setLanguage(StringFilter language) {
        this.language = language;
    }

    public StringFilter getCountry() {
        return country;
    }

    public void setCountry(StringFilter country) {
        this.country = country;
    }

    public StringFilter getAwards() {
        return awards;
    }

    public void setAwards(StringFilter awards) {
        this.awards = awards;
    }

    public StringFilter getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(StringFilter posterLink) {
        this.posterLink = posterLink;
    }

    public StringFilter getRatings() {
        return ratings;
    }

    public void setRatings(StringFilter ratings) {
        this.ratings = ratings;
    }

    public IntegerFilter getMetascore() {
        return metascore;
    }

    public void setMetascore(IntegerFilter metascore) {
        this.metascore = metascore;
    }

    public DoubleFilter getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(DoubleFilter imdbRating) {
        this.imdbRating = imdbRating;
    }

    public IntegerFilter getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(IntegerFilter imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public StringFilter getImdbId() {
        return imdbId;
    }

    public void setImdbId(StringFilter imdbId) {
        this.imdbId = imdbId;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public LocalDateFilter getDvdReleaseDate() {
        return dvdReleaseDate;
    }

    public void setDvdReleaseDate(LocalDateFilter dvdReleaseDate) {
        this.dvdReleaseDate = dvdReleaseDate;
    }

    public IntegerFilter getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(IntegerFilter boxOffice) {
        this.boxOffice = boxOffice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MovieCriteria that = (MovieCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(year, that.year) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(releaseDate, that.releaseDate) &&
            Objects.equals(runtime, that.runtime) &&
            Objects.equals(genre, that.genre) &&
            Objects.equals(director, that.director) &&
            Objects.equals(writers, that.writers) &&
            Objects.equals(actors, that.actors) &&
            Objects.equals(plot, that.plot) &&
            Objects.equals(language, that.language) &&
            Objects.equals(country, that.country) &&
            Objects.equals(awards, that.awards) &&
            Objects.equals(posterLink, that.posterLink) &&
            Objects.equals(ratings, that.ratings) &&
            Objects.equals(metascore, that.metascore) &&
            Objects.equals(imdbRating, that.imdbRating) &&
            Objects.equals(imdbVotes, that.imdbVotes) &&
            Objects.equals(imdbId, that.imdbId) &&
            Objects.equals(type, that.type) &&
            Objects.equals(dvdReleaseDate, that.dvdReleaseDate) &&
            Objects.equals(boxOffice, that.boxOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        year,
        rate,
        releaseDate,
        runtime,
        genre,
        director,
        writers,
        actors,
        plot,
        language,
        country,
        awards,
        posterLink,
        ratings,
        metascore,
        imdbRating,
        imdbVotes,
        imdbId,
        type,
        dvdReleaseDate,
        boxOffice
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (year != null ? "year=" + year + ", " : "") +
                (rate != null ? "rate=" + rate + ", " : "") +
                (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") +
                (runtime != null ? "runtime=" + runtime + ", " : "") +
                (genre != null ? "genre=" + genre + ", " : "") +
                (director != null ? "director=" + director + ", " : "") +
                (writers != null ? "writers=" + writers + ", " : "") +
                (actors != null ? "actors=" + actors + ", " : "") +
                (plot != null ? "plot=" + plot + ", " : "") +
                (language != null ? "language=" + language + ", " : "") +
                (country != null ? "country=" + country + ", " : "") +
                (awards != null ? "awards=" + awards + ", " : "") +
                (posterLink != null ? "posterLink=" + posterLink + ", " : "") +
                (ratings != null ? "ratings=" + ratings + ", " : "") +
                (metascore != null ? "metascore=" + metascore + ", " : "") +
                (imdbRating != null ? "imdbRating=" + imdbRating + ", " : "") +
                (imdbVotes != null ? "imdbVotes=" + imdbVotes + ", " : "") +
                (imdbId != null ? "imdbId=" + imdbId + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (dvdReleaseDate != null ? "dvdReleaseDate=" + dvdReleaseDate + ", " : "") +
                (boxOffice != null ? "boxOffice=" + boxOffice + ", " : "") +
            "}";
    }

}
