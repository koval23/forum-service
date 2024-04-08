package com.example.forum_service.forum.service;

import com.example.forum_service.forum.dao.ForumRepository;
import com.example.forum_service.forum.dto.AddPostDto;
import com.example.forum_service.forum.dto.PeriodDate;
import com.example.forum_service.forum.dto.PostCommentDto;
import com.example.forum_service.forum.dto.exceptions.PostNotFoundException;
import com.example.forum_service.forum.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

    private final ForumRepository forumRepository;
    private final ModelMapper modelMapper;

    @Override
    public Post addPost(String user, AddPostDto addPostDto) {
        Optional<Post> postOptional = forumRepository.findByAuthor(user);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            Post newPost = new Post(
                    addPostDto.getTitle(),
                    addPostDto.getContent(),
                    user,
                    addPostDto.getTags());
            forumRepository.save(newPost);
            return newPost;
        }
    }

    @Override
    public Post findPostById(String postId) {
        Optional<Post> postOptional = forumRepository.findById(postId);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public void addLike(String postId) {
        if (postId != null) {
            forumRepository.findById(postId)
                    .ifPresent(post -> {
                        post.addLike();
                        forumRepository.save(post);
                    });
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public Iterable<Post> findPostsByAuthor(String user) {
        if (user != null) {
            return forumRepository.findPostsByAuthor(user);
        }
        return new ArrayList<Post>();
    }

    @Override
    public Post addComment(String postId, PostCommentDto postCommentDto) {
        return Optional.ofNullable(postId)
                .flatMap(forumRepository::findById)
                .map(post -> {
                    post.getComments().add(postCommentDto);
                    return forumRepository.save(post);
                })
                .orElseThrow(PostNotFoundException::new);
    }

    @Override
    public Post deletePostById(String postId) {
        return Optional.ofNullable(postId)
                .flatMap(forumRepository::findById)
                .map(post -> {
                    forumRepository.deleteById(postId);
                    return post;
                })
                .orElseThrow(PostNotFoundException::new);
    }

    @Override
    public Iterable<Post> findPostsByTags(Set<String> tags) {
        return forumRepository.findPostsByTags(tags);
    }

    @Override
    public Iterable<Post> findPostsByPeriod(PeriodDate periodDate) {
        LocalDate date = LocalDate.parse(periodDate.getDateFrom());
        LocalDate to = LocalDate.parse(periodDate.getDateTo());

        return forumRepository.findPostsByPeriod(date, to);
    }

    @Override
    public Post updatePost(String postId, AddPostDto addPostDto) {
        Post post = forumRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        if (addPostDto.getContent() != null) {
            post.setContent(addPostDto.getContent());
        }
        if (addPostDto.getTags().isEmpty()) {
            post.setTags(addPostDto.getTags());
        }
        if (addPostDto.getTitle() != null) {
            post.setTitle(addPostDto.getTitle());
        }
        forumRepository.save(post);
        return post;
    }
}
