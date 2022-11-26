package net.ripe.blog.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.blog.entity.Comment;
import net.ripe.blog.entity.Post;
import net.ripe.blog.exception.BlogApiException;
import net.ripe.blog.exception.ResourceNotFoundException;
import net.ripe.blog.mapper.CommentMapper;
import net.ripe.blog.payload.CommentDto;
import net.ripe.blog.repository.CommentRepository;
import net.ripe.blog.repository.PostRepository;
import net.ripe.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    @Override
    public CommentDto createPost(long id, CommentDto commentDto) {
        Comment comment = CommentMapper.mapToJpa(commentDto);

        //retrieve post
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        //set post to comment entity
        comment.setPost(post);

        //save comment entity to db
        return CommentMapper.mapToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getAllComments(long id) {
        return commentRepository.findByPostId(id).stream().map(CommentMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to a post");
        }

        return CommentMapper.mapToDto(comment);
    }


    @Override
    public CommentDto updateComment(CommentDto updatedComment, Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to a post");
        }
        comment.setBody(updatedComment.getBody());
        comment.setName(updatedComment.getName());
        comment.setEmail(updatedComment.getEmail());

        return CommentMapper.mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to a post");
        } else {
            commentRepository.delete(commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId)));
        }
    }
}
