package net.ripe.blog.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Comment body should not be empty")
    @Size(min=10, message = "Comment body should have at least 10 characters")
    private String body;


}
