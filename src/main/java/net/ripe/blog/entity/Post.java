package net.ripe.blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="content", nullable = false)
    private String content;

    //List allows duplicates
    //Set doesn't allow duplicates
    // cascade <-> child relationship if CRUD is trigered. e.g if post is deleted, along with that comment is also delete
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}
