package com.example.forum_service.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddPostDto {

    private String title;
    private String content;
    private List<String> tags;

}
