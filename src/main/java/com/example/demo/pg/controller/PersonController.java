package com.example.demo.pg.controller;

import com.example.demo.pg.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping("/testPerson")
    @ResponseBody
    public void test(){
        System.out.println(personDao.queryPerson("1"));

        System.out.println(personDao.queryPersonArray(Arrays.asList("1","2")));
    }
}
