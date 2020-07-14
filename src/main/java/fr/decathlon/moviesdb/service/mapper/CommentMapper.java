package fr.decathlon.moviesdb.service.mapper;


import fr.decathlon.moviesdb.domain.*;
import fr.decathlon.moviesdb.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {MovieMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "movie.id", target = "movieId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "movieId", target = "movie")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
