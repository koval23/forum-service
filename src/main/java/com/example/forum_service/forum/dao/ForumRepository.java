package com.example.forum_service.forum.dao;


import com.example.forum_service.forum.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDate;

public interface ForumRepository extends MongoRepository<Post, String> {

    Optional<Post> findByAuthor(String author);

    List<Post> findPostsByAuthor(String author);

    @Query("{'tags': { $in: ?0 }}")
    Iterable<Post> findPostsByTags(Set<String> tags);

    @Query("{'createdAt': { $gte: ?0, $lte: ?1 }}")
    Iterable<Post> findPostsByPeriod(LocalDate fromDate, LocalDate toDate);

}
