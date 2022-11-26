package net.ripe.blog.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.ripe.blog.payload.CommentDto;
import net.ripe.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId,@Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createPost(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable("postId") long postId){
        return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") Long postId, @PathVariable("id") Long commentId,@Valid @RequestBody CommentDto updatedComment) {
        return new ResponseEntity<>(commentService.updateComment(updatedComment, postId, commentId), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId, @PathVariable("id") Long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comment entity deleted successfully.", HttpStatus.OK);
    }

}
