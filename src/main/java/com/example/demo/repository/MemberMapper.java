package com.example.demo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.MemberVO;

@Repository
public interface MemberMapper {
    public int insertMember(MemberVO member);
    
	public MemberVO checkMember(@Param("id") String id, @Param("pw") String pw);
}