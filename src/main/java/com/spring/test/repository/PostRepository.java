package com.spring.test.repository;

import com.spring.test.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByTitleContains(String title);

}
