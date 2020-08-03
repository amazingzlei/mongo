package com.example.demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class JsoupTest {

    @Test
    public void test01() throws IOException {
        Connection connect = Jsoup.connect("https://www.jianshu.com/p/cbd5713a8f26");
        Document document = connect.get();
//        System.out.println(document.title());
        Elements div = document.select("div");
        System.out.println(div);
//        System.out.println(document);
    }

    @Test
    public void test02() throws Exception {
        Document document = Jsoup.parse(new File("E:\\下载\\星空下\\WXWork Files\\WXWork Files\\File\\2020-04\\fh-ui-doc@2.1.0\\doc\\index.html"), "utf-8");
        Elements img = document.select("img[src]");

        img.stream().forEach((item)-> System.out.println(item.attr("src")));

    }

    /**
     * 消除不信任的HTML 即消除XSS攻击
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
//        Document document = Jsoup.parse(new File("E:\\下载\\星空下\\WXWork Files\\WXWork Files\\File\\2020-04\\fh-ui-doc@2.1.0\\doc\\index.html"), "utf-8");
//        String clean = Jsoup.clean(document.html(), Whitelist.basic());

        String value = "<a href='http://www.baidu'>baidu</>";
        String clean = Jsoup.clean(value, Whitelist.none());

        System.out.println(clean);

    }


    /**Whitelist的基本方法有四种：

     none：只保留了文本；

     simpleText：简单的文本属性b, em, i, strong, u。

     basic：a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q,small,strike, strong, sub, sup, u, ul。

     basicWithImages：a, b, blockquote, br, cite, code, dd, dl,dt, em, i, li, ol, p, pre, q, small, strike, strong, sub, sup, u, ul、img、src。

     Relaxed：a, b, blockquote,br, caption, cite, code, col, colgroup, dd, dl, dt, em, h1, h2, h3, h4, h5, h6,i, img, li, ol, p, pre, q, small, strike, strong, sub, sup, table, tbody, td,tfoot, th, thead, tr, u, ul。

     四种所包含的属性越来越多，如果还不满足，可以使用addAttributes、addEnforcedAttribute、addProtocols、addTags。但是这个添加要谨慎。
     */


}
