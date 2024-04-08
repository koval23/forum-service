package com.example.forum_service.forum.service;

import com.example.forum_service.forum.dto.AddPostDto;
import com.example.forum_service.forum.dto.PeriodDate;
import com.example.forum_service.forum.dto.PostCommentDto;
import com.example.forum_service.forum.model.Post;

import java.util.Set;

public interface ForumService {

    Post addPost(String user, AddPostDto addPostDto);

    Post findPostById(String postId);

    void addLike(String postId);

    Iterable<Post> findPostsByAuthor(String user);

    Post addComment(String postId, PostCommentDto postCommentDto);

    Post deletePostById(String postId);

    Iterable<Post> findPostsByTags(Set<String> tags);

    Iterable<Post> findPostsByPeriod(PeriodDate periodDate);

    Post updatePost(String postId, AddPostDto addPostDto);

}
