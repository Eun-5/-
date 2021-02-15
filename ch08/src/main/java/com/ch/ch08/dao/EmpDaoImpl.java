package com.ch.ch08.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.ch08.model.Emp;

@Repository
public class EmpDaoImpl implements EmpDao{
	@Autowired
	private SqlSessionTemplate sst;


	public List<Emp> list(int deptno) {
		return sst.selectList("empns.list",deptno);
	}


	
	public List<Emp> empList() {
		return sst.selectList("empns.empList");
		//전달할꼐 없다 = 결과가 1이야
	}



	
	public Emp select(int empno) {
		return sst.selectOne("empns.select", empno);
	}
 //넘버 가지고 찾아보자


	public int insert(Emp emp) {
		return sst.insert("empns.insert", emp);
	}



	public int update(Emp emp) {
		return sst.update("empns.update", emp);
	}



	public int delete(int empno) {
		return sst.delete("empns.delete", empno);
	}



	public List<Emp> allEmpList() {
		return sst.selectList("empns.allEmpList");
		//리스트 전부. 원이 아니야
	}


}
