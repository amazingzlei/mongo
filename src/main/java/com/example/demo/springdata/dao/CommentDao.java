package com.example.demo.springdata.dao;

import com.example.demo.springdata.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment, String> {

    List<Comment> findByContentLike(String content);

    @Override
    List<Comment> findAll();

    List<Comment> findFirstByArticleid(String id);


}
