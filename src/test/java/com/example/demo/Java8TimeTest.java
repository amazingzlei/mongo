package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class Java8TimeTest {

    @Test
    public void test(){
        // 年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println("-------------------------------");

        // 时分秒
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println("-------------------------------");

        // 年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println("-------------------------------");

        // 格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = dateTimeFormatter.format(localDateTime);
        System.out.println(format);
        System.out.println("-------------------------------");

        // 解析
        LocalDate localDate1 = LocalDate.of(2017,3,17);
        //BASIC_ISO_DATE格式，20111203
        String str = localDate1.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(str);
        System.out.println("-------------------------------");

        String str2 = localDate1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(str2);
        System.out.println("-------------------------------");

        LocalDate localDate2 = LocalDate.parse("20200604", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate2);
    }

    @Test
    public void test02(){
        //关注与年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);  //2020-06-04

        System.out.println(localDate.getYear()); //2020，年
        System.out.println(localDate.getMonthValue()); //6，月
        System.out.println(localDate.getDayOfMonth()); //4，日
        System.out.println(localDate.getDayOfWeek()); //MONDAY,星期几
        System.out.println(localDate.lengthOfMonth()); //30,返回当前月份的长度
        System.out.println(localDate.isLeapYear());//true,是否是闰年

        System.out.println("------------------");

        LocalDate localDate2 = LocalDate.of(2017,4,1);
        System.out.println(localDate2); //2017-04-01

        System.out.println("------------------");

        //MonthDay关注月份和日
        LocalDate localDate3 = LocalDate.of(2017,3,25);
        MonthDay monthDay = MonthDay.of(localDate3.getMonth(),localDate3.getDayOfMonth());
        System.out.println(monthDay); //--03-25

        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2014,3,25));
        System.out.println(monthDay2); //--03-25

        if(monthDay.equals(monthDay2)){
            System.out.println("equal");
        }else{
            System.out.println("not equal");
        }

        //关注与时分秒
        LocalTime time = LocalTime.now();
        System.out.println(time); //22:30:01.512
        System.out.println(time.getHour()); //22,时
        System.out.println(time.getMinute()); //30，分
        System.out.println(time.getSecond());  //01，秒

        LocalTime time2 = time.plusHours(3).plusMinutes(40);
        System.out.println(time2);  //02:10:01.512


        //当前日期的二周后
        LocalDate localDate1 = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(localDate1); //2020-08-04
        System.out.println("............");

        //当前时间的二个月之前
        LocalDate localDate4 = localDate.minus(2,ChronoUnit.MONTHS);
        System.out.println(localDate4);//2020-04-04
        System.out.println("..............");

        Clock clock = Clock.systemDefaultZone(); //当前时区的时刻
        System.out.println(clock.millis()); //获得当前的毫秒数,1498529786982


        LocalDate localDate5 = LocalDate.now();
        LocalDate localDate6 = LocalDate.of(2017,4,12);

        System.out.println(localDate5.isBefore(localDate6));  //判断时间在什么时间之前
        System.out.println(localDate5.isAfter(localDate6)); //判断时间在什么时间之后
        System.out.println(localDate5.isEqual(localDate6)); //判断时间和什么时间相等
        System.out.println("..............");
    }

    @Test
    public void test4(){
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(l);

        Long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime time2 =LocalDateTime.ofEpochSecond(timestamp/1000,0,ZoneOffset.ofHours(8));
        System.out.println(time2.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("-------------");
    }
}
