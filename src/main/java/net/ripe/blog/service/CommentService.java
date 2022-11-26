package net.ripe.blog.service;

import net.ripe.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createPost(long id, CommentDto commentDto);

    List<CommentDto> getAllComments(long id);

    CommentDto updateComment(CommentDto updatedComment, Long postId, Long commentId);

    void deleteCommentById(Long postId, Long commentId);

    CommentDto getCommentById(Long postId, Long commentId);
}
