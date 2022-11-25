package net.ripe.blog.mapper;

import net.ripe.blog.entity.Post;
import net.ripe.blog.payload.PostDto;

public class PostMapper {

    public static PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        dto.setTitle(post.getTitle());

        return dto;
    }

    public static Post mapToJpa(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setContent(dto.getContent());
        post.setDescription((dto.getDescription()));
        post.setTitle(dto.getTitle());

        return post;
    }
}
