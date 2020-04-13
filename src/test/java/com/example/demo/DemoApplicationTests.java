package com.example.demo;

import com.example.demo.springdata.pojo.Comment;
import com.example.demo.springdata.service.CommentService;
import com.example.demo.template.pojo.BokeComment;
import com.example.demo.template.pojo.Book;
import com.example.demo.template.service.CommentService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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

        Criteria criteria = Criteria.where("product_name").is("Samsung S3");
        Query query = new Query();
        query.addCriteria(criteria);

        Update update = new Update();
        update.inc("product_available", -1);

        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        // 是否返回新的数据
        findAndModifyOptions.returnNew(true);

        Book book = mongoTemplate.findAndModify(query, update, findAndModifyOptions, Book.class);
        System.out.println(book);

    }

    @Test
    public void test06(){
        // 对内嵌文档使用条件查询
        Criteria criteria = new Criteria();
        Criteria criteria1 = Criteria.where("author").is("joe").and("score").gt(5);
        criteria.and("comments").elemMatch(criteria1);
        Query query = new Query();
        query.addCriteria(criteria);

        List<BokeComment> bokeComments = mongoTemplate.find(query, BokeComment.class);
        System.out.println(bokeComments);
    }

    @Test
    public void test07(){

        // 管道查询 等同于  db.comment.aggregate({$project:{articleid:{$concat:['articleid',"哈哈"]}}})
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.project("articleid").and("articleid").concat("哈哈"));

        AggregationResults<Comment> comment = mongoTemplate.aggregate(aggregation, "comment", Comment.class);

        for (Comment com:comment
             ) {
            System.out.println(com);
        }
    }

    @Test
    public void test08(){
        // 等同于db.comment.aggregate({$project:{articleid:1}},{$group:{_id:"$articleid",likenum:{$sum:1}}},{$sort:{count:-1}},{$limit:2})
        Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.project("articleid"),
                    Aggregation.group("articleid").count().as("likenum"),
                    Aggregation.sort(Sort.Direction.DESC, "likenum"),
                    Aggregation.limit(2)
        );

        AggregationResults<Comment> comment = mongoTemplate.aggregate(aggregation, "comment", Comment.class);

        for (Comment com:comment
        ) {
            System.out.println(com);
        }

    }

    @Test
    public void test09(){
        // 等同于db.comment.aggregate({$project:{articleid:1,_id:0,likenum:1}},{$group:{_id:"$articleid",likenum:{$max:"$likenum"}}})
        // 按articleid分组，取，每组likenum最大的文档
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("articleid","likenum"),
                Aggregation.group("articleid").max("likenum").as("likenum")
        );

        AggregationResults<Comment> comment = mongoTemplate.aggregate(aggregation, "comment", Comment.class);

        for (Comment com:comment
        ) {
            System.out.println(com);
        }

    }

    @Test
    public void test10(){

        Query query = new Query();

        // 等同于db.comment.distinct("articleid")
        List<String> commentList = mongoTemplate.findDistinct(query, "articleid", "comment", String.class);

        for (String comment: commentList
             ) {
            System.out.println(comment);
        }
    }

    @Test
    /**
     * 连接mongo副本集
     */
    public void test11(){
        Query query = new Query();
        List<Book> books = mongoTemplate.find(query, Book.class);
        System.out.println(books);
    }
}
