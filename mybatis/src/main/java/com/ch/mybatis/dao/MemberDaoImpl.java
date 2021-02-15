package com.ch.mybatis.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSessionTemplate sst;


	public Member select(String id) {
		
		return sst.selectOne("memberns.select",id);
		           //저기가서 아이디 읽어오자
		//한 건이라 one. 여럿이면 selectlist다.
	}


	public int insert(Member member) {
		
		return sst.insert("memberns.insert",member);
	}


	
	public int update(Member member) {
		return sst.update("memberns.update",member);	
	} //아니 여기 인서트라 되있었는데도 됐다고?? 뭐임대체



	public int delete(String id) {
		return sst.update("memberns.delete",id);
	}//0이면 실패 1이면 성공. 이런 결과를 알려주는게 리턴인데.


	
	public void insertPhoto(List<MemberPhoto> photos) {
		sst.insert("memberns.insertPhoto",photos);
		//void 지금 행동을 하게한 객체에게 처리결과를 보고하지 않는다. 처리하고 끝이다.
	}


	public List<MemberPhoto> listPhoto(String id) {
		return sst.selectList("memberns.listPhoto",id);
	}

}
