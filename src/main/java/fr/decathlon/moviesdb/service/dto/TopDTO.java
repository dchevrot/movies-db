package fr.decathlon.moviesdb.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for returning top commented movies
 */
public class TopDTO implements Serializable {

    private Long movie_id;

    private Integer totalComments;

    private int rank;

    public TopDTO(Long movie_id, Integer totalComments, int rank) {
        this.movie_id = movie_id;
        this.totalComments = totalComments;
        this.rank = rank;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopDTO)) return false;
        TopDTO topDTO = (TopDTO) o;
        return rank == topDTO.rank &&
            Objects.equals(movie_id, topDTO.movie_id) &&
            Objects.equals(totalComments, topDTO.totalComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie_id, totalComments, rank);
    }

    @Override
    public String toString() {
        return "TopDTO{" +
            "movie_id=" + movie_id +
            ", totalComments=" + totalComments +
            ", rank=" + rank +
            '}';
    }
}
