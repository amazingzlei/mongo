package com.example.demo;

import com.example.demo.springdata.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class StreamTest {
    @Test
    public void test01(){
        List<String> stringList = Arrays.asList("java", "javascript", "css");
        List<String> collect = stringList.stream().limit(2).collect(Collectors.toList());
        System.out.println(collect);

        Comment comment = new Comment();
        System.out.println(comment);
    }
}
