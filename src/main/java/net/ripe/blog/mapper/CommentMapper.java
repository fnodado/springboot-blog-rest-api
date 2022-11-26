package net.ripe.blog.mapper;

import net.ripe.blog.entity.Comment;
import net.ripe.blog.payload.CommentDto;
import org.modelmapper.ModelMapper;

public class CommentMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static CommentDto mapToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }

    public static Comment mapToJpa(CommentDto dto) {
        return mapper.map(dto, Comment.class);
    }
}
