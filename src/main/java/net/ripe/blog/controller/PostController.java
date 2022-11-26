package net.ripe.blog.controller;

import lombok.AllArgsConstructor;
import net.ripe.blog.payload.PostDto;
import net.ripe.blog.payload.PostResponse;
import net.ripe.blog.service.PostService;
import net.ripe.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                        @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY, required =false)String sortBy,
                                                   @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_PAGE_SORT_DIR, required =false)String sortDir){
        return new ResponseEntity<>(postService.getAllPost(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto updatedPost, @PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.updatePost(updatedPost, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}
