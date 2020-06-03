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
        List<String> collect = stringList.stream().limit(2).collect(Collectors.toList());
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
        List<String> strings = stringList.stream().filter((item) -> item.contains("a")).collect(Collectors.toList());
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

        tList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
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
    }
}
