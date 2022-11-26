package net.ripe.blog.repository;

import net.ripe.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//No need to add @Repository annotation because internally SimpleJpaRepository implemented JpaRepository
// with @Repository annotation
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long id);

}
