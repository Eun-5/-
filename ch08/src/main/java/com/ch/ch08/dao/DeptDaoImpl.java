package com.ch.ch08.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.ch08.model.Dept;

@Repository
public class DeptDaoImpl implements DeptDao{
	@Autowired
	private SqlSessionTemplate sst;
	//자동으로 연결해람

	
	public List<Dept> list() {
		return sst.selectList("deptns.list");
		//애도 전화연결. 재료 동봉해서
	}


	public Dept select(int deptno) {
		return sst.selectOne("deptns.select",deptno);
		//xml에서 읽어오자 ->만들어야햄
	}


	public int insert(Dept dept) {
		return sst.insert("deptns.insert",dept);
	}


	
	public int update(Dept dept) {
		return sst.update("deptns.update",dept);
	}


	
	public int delete(int deptno) {
		return sst.delete("deptns.delete",deptno);
	}
}

