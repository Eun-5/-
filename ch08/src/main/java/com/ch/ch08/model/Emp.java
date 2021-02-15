package com.ch.ch08.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Emp {
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private int sal;
	private Integer comm;
	private int deptno;
//emana이라고 서서 에러. 오타는 무서운 거야. 다들 복붙을 하자
	//integer null값 공란표시위해.int는 null값이 0으로 들감
	
	private String mgrName;
	//추가했담. xml에도 추가합시다
	
	//join용 부서정보
	private Dept dept;
}
