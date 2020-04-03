package com.example.demo.template.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "comment")
public class Comment {
    // 主键标识，该属性的值会自动对应mongodb的主键字段"_id"，如果该属性名就叫“id”,则该注解可以省略，否则必须写
    @Id
    private String id;
    private Date publishtime;
    private String articleid;
    // 该属性对应mongodb的字段的名字，如果一致，则无需该注解
    @Field("content")
    private String content;
    private String userid;
    private String nicaname;
    private LocalDateTime createdatetime;
    private Long likeNum;
    private String state;
    private String parentid;
}
