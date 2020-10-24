package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.MemberVO;
import com.example.demo.repository.MemberMapper;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberMapper mapper;

	public int joinMember(MemberVO member) {
		int result = 0;
		
		try {
			result = mapper.joinMember(member);
		} catch (Exception e) {
			e.printStackTrace(); // UserId not null 관련 예외(ErrMsg) 발생 시, 예외 처리 필요
		}
		return result;
	}

	public boolean checkMember(String id, String pw) {
		boolean result = false;

		if(mapper.checkMember(id, pw) != null) {
			result = true;
		}
		
		return result;
	}

}
