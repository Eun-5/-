package com.ch.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.mybatis.dao.MemberDao;
import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao md;

	
	public Member select(String id) {
	 
		return md.select(id);
	}


		public int insert(Member member) {
		return md.insert(member);
	}



		public int update(Member member) {
			
			return md.update(member);
		}


		
		public int delete(String id) {
			return md.delete(id);
		}


		  //return은 반환형이 void가 아닐때 존재한다
		public void insertPhoto(List<MemberPhoto> photos) {
			md.insertPhoto(photos);
			//return->result 인데 의미가없어서. 시퀸스고 받는애없어서
			
		}


		public List<MemberPhoto> listPhoto(String id) {
			return md.listPhoto(id);
		}

}
