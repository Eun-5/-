package com.ch.ch08.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.ch08.model.Dept;
import com.ch.ch08.model.Emp;
import com.ch.ch08.service.DeptService;
import com.ch.ch08.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	private DeptService ds; //애도 필요하다 외래키라서
	@Autowired
	private EmpService es;
	
	@RequestMapping("empList")
	         //dept리스트서 넘어가는 emp가 deptno를 갖고왔다
	public String empList(int deptno,Model model) {
		Dept dept=ds.select(deptno);
		List<Emp> empList = es.list(deptno);
		
		model.addAttribute("dept",dept);
		//아 위에 Dept dept가 있구나. emp에는 부서명이 없으니.가져온거
		model.addAttribute("empList",empList);
		return "/emp/empList";
	}
	
	//애도 deptno 받아온다.
	@RequestMapping("empInsertform")
	public String empInsertform(int deptno,Model model) {
		List<Dept> deptList = ds.list();
		//부서정보 읽어오자
		List<Emp> empList = es.empList();
		//사원정보 읽어오자

		//입력할때는 emp를 불러올필요가 없지. 수정할때만
		//화면에서 입력해주니까. 그래서 list(deptno)가 아닌거
		
		model.addAttribute("deptno",deptno); //부서코드에 입력하자~
		model.addAttribute("deptList",deptList); 
		model.addAttribute("empList",empList); 
		return "/emp/empInsertform";
		
	}
	@RequestMapping(value="empChk",
			produces="text/html; charset=utf-8")
	@ResponseBody
	public String empChk(int empno) {
		String data="";
		Emp emp= es.select(empno);
		if(emp ==null)data = "사용 가능한 사번이야";
		else data = "이미 사용중인 사번이야";
		return data;
	}
	
	@RequestMapping("empInsert")
	public String empInsert(Emp emp,Model model) {
		int result=0;
		Emp emp2 =es.select(emp.getEmpno());
		if (emp2==null) result = es.insert(emp);
		else result = -1;
		
		model.addAttribute("emp",emp);
		model.addAttribute("result",result);
		
		return "/emp/empInsert";
		
	//emp테이블에 데이터가 들어오면 그거 읽어서
		//진짜로 데이터 있는지 확인해보장
	}
	
	@RequestMapping("empSelect")  //jsp보내주는게 model
	public String empSelect(int empno, Model model) {
		Emp emp = es.select(empno);
	model.addAttribute("emp",emp);
	return "/emp/empSelect";
	//사원 번호 검색해서 상세정보 불러오기
}
	@RequestMapping("empUpdateForm")
	public String empUpdateForm(int empno, Model model) {
		//0에서 1로 만드는게 입력, 1을 바꾸는게 수정
		Emp emp = es.select(empno);
		
		List<Dept> deptList = ds.list();
		List<Emp> empList = es.empList();
		model.addAttribute("deptList",deptList); //부서코드 선택
		model.addAttribute("empList",empList); //관지라 선택	
		model.addAttribute("emp",emp); //현재 수정하는 직원정보

		return "/emp/empUpdateForm";
	
	}
	@RequestMapping("empUpdate")
	public String empUpdate(Emp emp, Model model) {
		int result = es.update(emp);
		
		//xml다 수정했고 결과
		model.addAttribute("result",result);
		model.addAttribute("emp",emp);
		return "/emp/empUpdate";
		
	}
	@RequestMapping("empDelete")
	public String empDelete(int empno, Model model) {
		Emp emp =es.select(empno);
		int result=es.delete(empno);
		
		model.addAttribute("result",result);
		model.addAttribute("emp",emp); //empno결과를 emp에 전하기
		return "/emp/empDelete";
		
	}
	@RequestMapping("allEmpList")
	public String allEmpList(Model model) {
		List<Emp> list = es.allEmpList();
		model.addAttribute("list",list);
		return "/emp/allEmpList";
		
	}
}
