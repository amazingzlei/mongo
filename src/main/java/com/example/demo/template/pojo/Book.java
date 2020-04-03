package com.example.demo.template.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
@Data
public class Book {
    @Id
    private String id;
    private String product_name;
    private String category;
    private Long product_total;
    private Long product_available;
}
