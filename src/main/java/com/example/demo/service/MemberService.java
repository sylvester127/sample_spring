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
		String id = "";
		String password = "";
		
		id = member.getId();
		password = member.getPassword();
		
		if(!id.equals("") && !password.equals("")) {
			result = mapper.joinMember(member);
		} else {
			result = -1;
		}
		
		return result;
	}

	public boolean checkMember(String id, String password) {
		boolean result = false;

		if(mapper.checkMember(id, password) != null) {
			result = true;
		}
		
		return result;
	}

}
