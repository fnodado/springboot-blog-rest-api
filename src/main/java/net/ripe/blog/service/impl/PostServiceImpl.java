package net.ripe.blog.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.blog.entity.Post;
import net.ripe.blog.exception.ResourceNotFoundException;
import net.ripe.blog.mapper.PostMapper;
import net.ripe.blog.payload.PostDto;
import net.ripe.blog.repository.PostRepository;
import net.ripe.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        return PostMapper.mapToDto(postRepository.save(PostMapper.mapToJpa(postDto)));
    }

    @Override
    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream().map(PostMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        return PostMapper.mapToDto(postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id)));
    }

    @Override
    public PostDto updatePost(PostDto updatedPost, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(updatedPost.getTitle());
        post.setDescription(updatedPost.getDescription());
        post.setContent(updatedPost.getContent());

        Post newPost = postRepository.save(post);
        return PostMapper.mapToDto(newPost);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id)));
    }
}
