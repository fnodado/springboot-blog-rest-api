package net.ripe.blog.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.blog.entity.Post;
import net.ripe.blog.exception.ResourceNotFoundException;
import net.ripe.blog.mapper.PostMapper;
import net.ripe.blog.payload.PostDto;
import net.ripe.blog.payload.PostResponse;
import net.ripe.blog.repository.PostRepository;
import net.ripe.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        //create logic for sorting
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        System.out.println(sort);

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(PostMapper::mapToDto).collect(Collectors.toList());

        //create PostResponse object
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
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
