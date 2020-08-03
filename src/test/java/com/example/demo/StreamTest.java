package com.example.demo;

import com.example.demo.springdata.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class StreamTest {
    @Test
    public void test01() {
        List<String> stringList = Arrays.asList("java", "javascript", "css");
        List<String> collect = stringList.stream().skip(0).limit(2).collect(Collectors.toList());
        System.out.println(collect);

        Comment comment = new Comment();
        System.out.println(comment);
    }

    /**
     * 过滤元素
     */
    @Test
    public void test02() {
        List<String> stringList = Arrays.asList("java", "javascript", "css");
        List<String> strings = stringList.stream().filter((item) -> item.contains("css")

        ).collect(Collectors.toList());
        System.out.println(strings);
    }

    @Test
    public void test03() {
        List<String> stringList = Arrays.asList("java", "javascript", "css");

        List<String> collect = stringList.stream().sorted().collect(Collectors.toList());

        List<String> collect02 = stringList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println(collect02);
    }

    @Test
    public void test04() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "赃", "李四");

        List<String> collect02 = stringList.stream().sorted(Collator.getInstance(Locale.CHINA)).collect(Collectors.toList());

        System.out.println(collect02);
    }

    @Test
    public void test05() {
        List<Integer> tList = Arrays.asList(5, 2, 56, 78, 24, 98, 63);
        List<Integer> integers = new ArrayList<>();

        tList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        tList.stream().forEach((item)->{
            if(item > 50){
                integers.add(item);
            }
        });
        System.out.println(integers);
    }

    @Test
    public void test06() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "赃", "李四");
        Optional<String> max = stringList.stream().max(Comparator.reverseOrder());
        System.out.println(max.get());
    }

    @Test
    public void test07() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "赃", "李四");
        long count = stringList.stream().count();
        System.out.println(count);
    }

    @Test
    public void test08() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "赃", "李四");
        List<String> collect = stringList.stream().map((item) -> {
            String str = item.concat("aaa");
            return str;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test09() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "赃", "李四");
        boolean anyMatch = stringList.stream().anyMatch((item) -> item.contains("赃"));
        System.out.println(anyMatch);
    }

    @Test
    public void test10() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "赃", "李四");
        boolean anyMatch = stringList.stream().allMatch((item) -> item.contains("赃"));
        System.out.println(anyMatch);
    }

    @Test
    public void test11() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "张三", "李四");
        Set<String> collect = stringList.stream().collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void test12() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "张三", "李四");
        // toMap的第三个参数表示如果键冲突时,后者覆盖前者
        Map<String, String> collect = stringList.stream().collect(Collectors.toMap(s -> s, s -> s, (oldValue,newValue)->newValue));
        System.out.println(collect);
    }

    @Test
    public void test13() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "张三", "李四");
        // toMap的第三个参数表示如果键冲突时,后者覆盖前者
        Optional<String> any = stringList.parallelStream().findAny();
        System.out.println(any.get());
    }

    @Test
    public void test14() {
        List<String> stringList = Arrays.asList("阿卓", "张三", "张三", "李四");
        // toMap的第三个参数表示如果键冲突时,后者覆盖前者
        Optional<String> any = stringList.parallelStream().findFirst();
        System.out.println(any.get());

        List<Comment> comments = new ArrayList<>();


        Comment comment = new Comment();
        comment.setNickname("1");
        comment.setLikenum(2L);

        Comment comment2 = new Comment();
        comment2.setNickname("2");
        comment2.setLikenum(3L);

        comments.add(comment);
        comments.add(comment2);

        List<Comment> studentsSortName = comments.stream().sorted(Comparator.comparing(Comment::getLikenum).reversed()).collect(Collectors.toList());
        System.out.println(studentsSortName);
    }

    @Test
    public void test15(){
        Comment comment1 = new Comment();
        comment1.setLikenum(5L);
        Comment comment2 = new Comment();
        comment2.setLikenum(5L);
        Comment comment3 = new Comment();
        comment3.setLikenum(5L);
        Comment comment4 = new Comment();
        comment4.setLikenum(5L);
        Comment comment5 = new Comment();
        comment5.setLikenum(5L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        comments.add(comment5);
        long sum = comments.stream().mapToLong((item) -> item.getLikenum()).sum();
        System.out.println(sum);
    }
}
