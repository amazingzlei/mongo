package com.example.demo.template.controller;

import com.example.demo.template.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("test")
    @ResponseBody
    public void test(){
        System.out.println("----------主线程开始-------------");
        Thread thread01 = new Thread(new MyThread());
        thread01.start();

        Thread thread1 = new Thread(new MyThread());
        thread1.start();

        Thread thread2 = new Thread(new MyThread());
        thread2.start();

        Thread thread3 = new Thread(new MyThread());
        thread3.start();

        Thread thread4 = new Thread(new MyThread());
        thread4.start();

        Thread thread5 = new Thread(new MyThread());
        thread5.start();

        Thread thread6 = new Thread(new MyThread());
        thread6.start();
        System.out.println("----------主线程结束-------------");
    }

    class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + "开始-----------");
            Criteria criteria = Criteria.where("_id").is(1);

            Book book = mongoTemplate.findById(1, Book.class);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + ":" + book);
            if (book.getProduct_available() > 0) {
                Query query1 = new Query();
                query1.addCriteria(criteria);

                Update update = new Update();
                update.inc("product_available", -1);
                mongoTemplate.upsert(query1, update, "book");

            }
            System.out.println(Thread.currentThread().getId() + "结束-----------");
        }
    }
}
