package net.ripe.blog.mapper;

import net.ripe.blog.entity.Post;
import net.ripe.blog.payload.PostDto;
import org.modelmapper.ModelMapper;

public class PostMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);
    }

    public static Post mapToJpa(PostDto dto) {
        return mapper.map(dto, Post.class);
    }
}
