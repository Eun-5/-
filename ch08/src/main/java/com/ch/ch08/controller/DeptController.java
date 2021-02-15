package com.ch.ch08.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.ch08.model.Dept;
import com.ch.ch08.service.DeptService;

@Controller
public class DeptController {
	@Autowired
	private DeptService ds;
//모델->dao->서비스->컨트롤러
	
	@RequestMapping("deptList")
	public String deptList(Model model) {
		List<Dept> deptList= ds.list();
	//보낼것 찾기
		
		//택배이름,안에든거  ->리턴 보낼곳
		model.addAttribute("deptList",deptList);
	return "/dept/deptList";	
	
	
/*	@RequestMapping("당근묶음")
	public 물건 당근묶음(야채 야채) {
		묶음들이<당근> 당근묶음= ds.야채보관소();
		야채.addAttribute("당근묶음",당근묶음);
		return "/축제관리인/당근묶음";	
	}
	*/
}
	

	@RequestMapping("insertDeptForm")
	public String insertDeptForm(Model model) {
	return "/dept/insertDeptForm";	
}
	@RequestMapping("deptInsert")  //dept화면서 입력한 부서정보
	public String deptInsert(Dept dept,Model model) {
		//부서코드가 중복되었는지 체크. dt 테이블에 저장된 부서정보
		Dept dt=ds.select(dept.getDeptno());
		int result=0;
		if(dt == null)result =ds.insert(dept);
		else result = -1;
		model.addAttribute("result",result);
		return "/dept/deptInsert";	
	}
	
	@RequestMapping(value="deptNoChk",
			produces="text/html;charset=utf-8")
	//텍스트는 html이고 문자는 한글이여
	
	@ ResponseBody //jsp보내지말고 바로 보여줘
	public String deptNoChk(int deptno) {
		String data="";
		Dept dept = ds.select(deptno);
		
		if(dept ==null) data="사용 가능한 부서코드야";
		else data ="이미 사용 중인 부서코드야";
		return data;
	}
	
	@RequestMapping("updateDeptForm")
	public String updateDeptForm(int deptno,Model model) {
		Dept dept=ds.select(deptno);
		model.addAttribute("dept",dept);
		//보내주는게 addatt
		return "/dept/updateDeptForm";	
	}
	
	@RequestMapping("deptUpdate")
	public String deptUpdate(Dept dept,Model model) {
		int result = ds.update(dept);
		
		model.addAttribute("result",result);
		 //결과 받아오는거로 종료
		return "/dept/deptUpdate";	
	}
	
	@RequestMapping("deleteDept")
	public String deleteDept(int deptno,Model model) {
		//deptno를 보냈으니까 개를 받아서 쓰자.
		int result = ds.delete(deptno);

		model.addAttribute("result",result);
		//결과는 1이겠지~~
		return "/dept/deleteDept";	
	}
	
	
}