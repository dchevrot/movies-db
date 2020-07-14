package fr.decathlon.moviesdb.service.dto;

import fr.decathlon.moviesdb.service.MovieServiceMessages;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * A DTO for creating the {@link fr.decathlon.moviesdb.domain.Movie} entity.
 */
public class MovieCreationDTO {

    @NotNull
    @Size(min = 3, max = 200, message = MovieServiceMessages.MOVIE_CREATION_TITLE_SIZE_CONSTRAINT_MSG)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MovieCreationDTO{" +
            "title='" + title + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCreationDTO)) return false;
        MovieCreationDTO that = (MovieCreationDTO) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
