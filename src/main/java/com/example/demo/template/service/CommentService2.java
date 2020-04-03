package com.example.demo.template.service;

import com.example.demo.springdata.pojo.Comment;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CommentService2 {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> findByContent(String content){
        // 设置条件
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("articleid").is("100000"),
        Criteria.where("likenum").gt(1500)).andOperator(Criteria.where("state").is("1"));

        // 设置投影查询
        Query query = new Query();
        query.fields().exclude("nickname").exclude("userid");

        // 排序
        List<Sort.Order> orderList = new ArrayList<>();
        orderList.add(new Sort.Order(Sort.Direction.DESC, "userid"));
        orderList.add(new Sort.Order(Sort.Direction.DESC, "likenum"));

        Sort sort = Sort.by(orderList);

        query.addCriteria(criteria);
        query.with(sort);
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        return comments;
    }

    public void update(){
        long count = mongoTemplate.count(new Query(), "comment");
        System.out.println(count);


        Query query = new Query();
        Update update = new Update();
        update.set("nickname","zenglei");
        update.inc("lickname");



        UpdateResult upsert = mongoTemplate.updateMulti(query, update, "comment");
        System.out.println(upsert.getMatchedCount());
        System.out.println(upsert.getUpsertedId());

    }

    public void save(){
        Comment comment1 = new Comment();
        comment1.setNickname("张三");
        comment1.setLikenum(Long.valueOf(2500));
        comment1.setArticleid("100005");
        comment1.setUserid("1003");
        comment1.setContent("今天不是个好日子");
        comment1.setCreatedatetime(LocalDateTime.now());
        comment1.setState("1");

        Comment comment2 = new Comment();
        comment2.setNickname("李四");
        comment2.setLikenum(Long.valueOf(2700));
        comment2.setArticleid("100005");
        comment2.setUserid("1003");
        comment2.setContent("今天天气真好");
        comment2.setCreatedatetime(LocalDateTime.now());
        comment2.setState("1");

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        Collection<Comment> commentCollection = mongoTemplate.insertAll(commentList);
        for (Comment comment:commentCollection
             ) {
            System.out.println(comment);
        }

    }


//    public BathUpdateOptions getBathUpdateOptions(Comment comment){
//        BathUpdateOptions options = new BathUpdateOptions();
//        Query query = new Query();
//        //查询条件
//        options.setQuery(query);
//        //mongodb 默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出来多条记录全部更新。
//        options.setMulti(true);
//        Update update = new Update();
//        //更新内容
//        update.set("nickname", comment.getNickname());
//        options.setUpdate(update);
//        return options;
//    }
//
//    public void bathUpdate(List<BathUpdateOptions> bups){
//        BathUpdateUtil.bathUpdate(mongoTemplate, Comment.class, bups);
//    }
//
//    public void bathUpdateMongoDB (Set<String> liveKeys){
//        //将直播播放次数入mongoDB
//        List<BathUpdateOptions> bupsList = new ArrayList<BathUpdateOptions>();
//        for (String id : liveKeys) {
//            Comment comment = new Comment();
//            //设置一些更新条件 此处省略
//            comment.setNickname("zenglei");
//            BathUpdateOptions options = getBathUpdateOptions(comment);
//            bupsList.add(options);
//            if(bupsList.size() >= 1000){
//                // 当数据量较大时先执行部分
//                bathUpdate(bupsList);
//                bupsList = new ArrayList<BathUpdateOptions>();
//            }
//        }
//        //TODO 更新liveList剩余少于1000的数据
////        logger.info("bupsList : {}",bupsList.size());
//        if (bupsList != null && bupsList.size() > 0) {
//            bathUpdate(bupsList);
//        }
//    }
}
