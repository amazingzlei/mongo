package com.example.demo.template.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "test")
public class BokeComment {
    @Id
    private String id;
    private String content;
    @Field(name = "comments")
    private List<CommentContent> comments;
}
