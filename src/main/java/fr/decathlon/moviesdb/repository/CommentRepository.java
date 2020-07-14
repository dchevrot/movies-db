package fr.decathlon.moviesdb.repository;

import fr.decathlon.moviesdb.domain.Comment;

import fr.decathlon.moviesdb.service.dto.TopDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Spring Data  repository for the Comment entity.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value="select new TopDTO(c.movie_id, count(c.id) totalComments, ROW_NUMBER () OVER (ORDER BY count(c.id) desc) rank) from comment c where c.creation_date between :startDate and :endDate group by c.movie_id")
    Page<TopDTO> top(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
