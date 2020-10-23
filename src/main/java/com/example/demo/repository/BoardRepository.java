package com.example.demo.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> getBoard(){
        return sqlSessionTemplate.selectList("board.getBoard", null);
    }
    public Object add(Map<String, Object> param){
        return sqlSessionTemplate.insert("board.add", param);
    }

}