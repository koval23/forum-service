package com.example.forum_service.forum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostCommentDto {

    private String user;
    private String message;
    private LocalDate dateCreated = LocalDate.now();
    private int likes = 0;

    public PostCommentDto(String user, String message) {
        this.user = user;
        this.message = message;
    }
}
