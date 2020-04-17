package com.example.demo.pg.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PersonDao {

    List<Map<String, Object>> queryPerson(String value);

    List<Map<String, Object>> queryPersonArray(@Param("value") List value);
}
