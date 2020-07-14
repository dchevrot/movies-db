package fr.decathlon.moviesdb.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.decathlon.moviesdb.domain.Comment} entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 1000)
    private String body;


    private Long movieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO that = (CommentDTO) o;
        return id.equals(that.id) &&
            body.equals(that.body) &&
            movieId.equals(that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, movieId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", body='" + getBody() + "'" +
            ", movieId=" + getMovieId() +
            "}";
    }
}
