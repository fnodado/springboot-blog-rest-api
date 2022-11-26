package net.ripe.blog.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    //title should not be empty
    //title should at least have 2 chars
    @NotEmpty
    @Size(min=2, message = "Post title should have at least 2 characters")
    private String title;

    //post description should not be null
    //post description should at least 10 chars
    @NotEmpty
    @Size(min=10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;

}
