package com.example.forum_service.forum.controller;

import com.example.forum_service.forum.dto.AddPostDto;
import com.example.forum_service.forum.dto.PeriodDate;
import com.example.forum_service.forum.dto.PostCommentDto;
import com.example.forum_service.forum.model.Post;
import com.example.forum_service.forum.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;

    @PostMapping("/forum/post/{user}")
    public Post addPost(@PathVariable String user,
                        @RequestBody AddPostDto addPostDto) {
        return forumService.addPost(user, addPostDto);
    }

    @GetMapping("/forum/post/{postId}")
    public Post findPostById(@PathVariable String postId) {
        return forumService.findPostById(postId);
    }

    @PutMapping("/forum/post/{postId}/like")
    public void addLike(@PathVariable String postId) {
        forumService.addLike(postId);
    }

    @GetMapping("/forum/posts/author/{user}")
    public Iterable<Post> findPostsByAuthor(@PathVariable String user) {
        return forumService.findPostsByAuthor(user);
    }

    @PutMapping("/forum/post/{postId}/comment/{user}")
    public Post addComment(@PathVariable String postId,
                           @PathVariable String user,
                           @RequestBody String message) {
        return forumService.addComment(postId, new PostCommentDto(user, message));
    }

    @DeleteMapping("/forum/post/{postId}")
    public Post deleteForum(@PathVariable String postId) {
        return forumService.deletePostById(postId);
    }

    @PostMapping("/forum/posts/tags")
    public Iterable<Post> findPostsByTag(Set<String> tags) {
        return forumService.findPostsByTags(tags);
    }

    @PostMapping("/posts/period")
    public Iterable<Post> findPostsByPeriod(@RequestBody PeriodDate period) {
        return forumService.findPostsByPeriod(period);
    }

    @PutMapping("/forum/post/{postId}")
    public Post updatePost(@PathVariable String postId,
                           @RequestBody AddPostDto addPostDto) {
        return forumService.updatePost(postId, addPostDto);
    }


}
