package com.example.demo.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.UserVO;
import com.example.demo.repository.UserMapper;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    
    public int insertUser(UserVO user){
		int result = 0;
		UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
		
		try {
			result = mapper.insertUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();		// UserId not null 관련 예외(ErrMsg) 발생 시, 예외 처리 필요
			return result;
		}
		return result;
	}
    
    
//    public UserVO loginUser(UserVO user){
//		UserVO result=null;
//		
//		UserMapper mapper=sqlSessionTemplate.getMapper(UserMapper.class);
//	
//		try {
//			result=mapper.loginUser(user);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();		// UserId not null 관련 예외(ErrMsg) 발생 시, 예외 처리 필요
//			return result;
//		}
//		return result;
//	}
    
    public UserVO checkUser(String id, String pw) {
    	UserVO result = null;
    	
    	UserMapper mapper=sqlSessionTemplate.getMapper(UserMapper.class);
    	
    	result = mapper.checkUser(id, pw);
    	return result;
    }

}
