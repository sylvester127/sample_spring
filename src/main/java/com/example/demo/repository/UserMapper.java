package com.example.demo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.UserVO;
import java.util.List;

@Repository
public interface UserMapper {
    public int insertUser(UserVO user);

	public UserVO checkUser(@Param("id") String id, @Param("pw") String pw);
	
//  public UserVO loginUser(UserVO user);
    
//    //사용자 수  
//    public int userCount() throws Exception;
//        
//    //사용자 목록  
//    public List<UserVO> userList() throws Exception;
//        
//    //사용자 상세
//    public UserVO userDetail(int userId) throws Exception;
//        
//    //회원가입 or 로그인  
//    public int userInsert(UserVO user) throws Exception;
//        
//    //회원정보 수정
//    public int userUpdate(UserVO user) throws Exception;
//        
//    //회원탈퇴
//    public int userDelete(int userId) throws Exception;
 
}
