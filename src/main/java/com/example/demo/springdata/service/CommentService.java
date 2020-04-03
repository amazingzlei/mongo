package com.example.demo.springdata.service;

import com.example.demo.springdata.dao.CommentDao;
import com.example.demo.springdata.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comment> findByContent(String content){
        return commentDao.findByContentLike(content);
    }

    public List<Comment> findAll(){
        return commentDao.findAll();
    }

    public List<Comment> findFirstByArticleid(String id){
        return commentDao.findFirstByArticleid(id);
    }
}
