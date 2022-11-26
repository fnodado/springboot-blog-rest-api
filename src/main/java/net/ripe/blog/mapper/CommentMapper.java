package net.ripe.blog.mapper;

import net.ripe.blog.entity.Comment;
import net.ripe.blog.entity.Post;
import net.ripe.blog.payload.CommentDto;
import net.ripe.blog.payload.PostDto;

public class CommentMapper {

    public static CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        dto.setName(comment.getName());
        return dto;
    }

    public static Comment mapToJpa(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setBody(dto.getBody());
        comment.setEmail((dto.getEmail()));
        comment.setName(dto.getName());

        return comment;
    }
}
