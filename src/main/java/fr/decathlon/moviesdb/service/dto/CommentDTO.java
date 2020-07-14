package fr.decathlon.moviesdb.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link fr.decathlon.moviesdb.domain.Comment} entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 1000)
    private String body;

    @NotNull
    private Long movieId;

    private LocalDateTime creationDate;

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(body, that.body) &&
            Objects.equals(movieId, that.movieId) &&
            Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, movieId, creationDate);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + id +
            ", body='" + body + '\'' +
            ", movieId=" + movieId +
            ", creationDate=" + creationDate +
            '}';
    }
}
