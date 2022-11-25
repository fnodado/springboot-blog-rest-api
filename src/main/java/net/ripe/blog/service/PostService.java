package net.ripe.blog.service;

import net.ripe.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto updatedPost, Long id);

    void deletePostById(Long id);
}
