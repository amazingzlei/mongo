package com.example.demo;

import com.example.demo.springdata.pojo.Comment;
import com.example.demo.springdata.service.CommentService;
import com.example.demo.template.pojo.Book;
import com.example.demo.template.service.CommentService2;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentService2 commentService2;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        List<Comment> commentList = commentService.findByContent("冬天");
        System.out.println(commentList);
        System.out.println(commentService.findAll());
        System.out.println(commentService.findFirstByArticleid("100001"));
    }

    @Test
    void test01() {
        System.out.println("--------------------------------");
        List<Comment> commentList = commentService2.findByContent("冬天");
        for (Comment comment : commentList
        ) {
            System.out.println(comment);
        }
        System.out.println("--------------------------------");
    }

    @Test
    void test02() {
        System.out.println("----------------------------------");
        commentService2.update();
        System.out.println("----------------------------------");
    }

    @Test
    void test03() {
        System.out.println("-----------------------------------");
        commentService2.save();
        System.out.println("------------------------------------");
    }

    @Test
    void test04() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + "开始-----------");
        Criteria criteria = Criteria.where("_id").is(1);

        Book book = mongoTemplate.findById(1, Book.class);
        System.out.println(Thread.currentThread().getName() + ":" + book);
        if (book.getProduct_available() > 0) {
            Thread.sleep(2000);
            Query query1 = new Query();
            query1.addCriteria(criteria);

            Update update = new Update();
            update.inc("product_available", -1);
            mongoTemplate.upsert(query1, update, "book");

        }
        System.out.println(Thread.currentThread().getName() + "结束-----------");


    }

    @Test
    void test05(){



    }


}
