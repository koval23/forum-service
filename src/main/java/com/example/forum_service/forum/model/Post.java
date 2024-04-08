package com.example.forum_service.forum.model;

import com.example.forum_service.forum.dto.PostCommentDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "post")
public class Post {

    @Id
    private String id = UUID.randomUUID().toString();
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    @Setter
    private LocalDate dateCreated = LocalDate.now();
    @Setter
    private List<String> tags;
    @Setter
    private int likes = 0;
    @Setter
    private List<PostCommentDto> comments = new ArrayList<>();

    public Post(String title, String content, String author, List<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
    }

    public void addLike() {
        setLikes(getLikes() + 1);
    }
}
