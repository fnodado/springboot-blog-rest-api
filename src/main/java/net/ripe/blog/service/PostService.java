package net.ripe.blog.service;

import net.ripe.blog.payload.PostDto;
import net.ripe.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto updatedPost, Long id);

    void deletePostById(Long id);
}
