package com.ch.mybatis.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;
import com.ch.mybatis.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService ms;
	
	@RequestMapping("joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@RequestMapping("joinForm2")
	public String joinForm2() {
		return "joinForm2";
	}

	
	@RequestMapping(value="idChk",
			produces="text/html;charset=utf-8")
	@ResponseBody //jsp로 가지말고 바로 본문 전달. 퀵배달
	public String idChk(String id) {
		//아이디 체크에서 아이디 입력한게 넘어가서 id
		String msg = "";
		
		//jsp를 전달하는게 model의 역할인데 안 쓴다니까 생략
		Member member = ms.select(id);
		if(member==null)msg="사용 가능한 아이디야";
		else msg= "이미 사용중인 아이디야";
		return msg;
	}
	
	@RequestMapping("join")
	public String join(Member member,Model model,HttpSession session) throws IOException {
		int result=0;
		//member는 화면서 입력한 데이터, mem은 테이블서 읽은 id
		Member mem=ms.select(member.getId());
		
		if(mem==null) {
			String fileName = member.getFile().getOriginalFilename();
			                                      //파일명 안바꿀것
			member.setFileName(fileName);
			String real=session.getServletContext()
					.getRealPath("/resources/upload");
			FileOutputStream fos=new FileOutputStream(
					new File(real+"/"+fileName));
			fos.write(member.getFile().getBytes());
			fos.close();
			
			result=ms.insert(member);
		}else result=-1; //이미 있는데 왜입력해. 란뜻 
		
		model.addAttribute("result",result);
		return "join";
	}
	
	
	@RequestMapping("join2")
	public String join2(Member member,Model model,HttpSession session,
			MultipartHttpServletRequest mr) throws IOException {
		
		int result = 0;
		Member mem=ms.select(member.getId());
		if (mem ==null) {
			String real=session.getServletContext()
					.getRealPath("/resources/upload");			
			//여기까진 같은데 그림파일 여럿 한번에가 문제다
			
			List<MultipartFile>list = mr.getFiles("file");
			//사진 여러개 가진 객체(택배박스)를 저장
			List<MemberPhoto> photos=new ArrayList<MemberPhoto>();
			
			for (MultipartFile mf:list) {
				MemberPhoto mp = new MemberPhoto();
			 //여러사진 하나씩 뽑아서 폴더에 넣는 작업	
				String fileName = mf.getOriginalFilename();
				mp.setFileName(fileName);
				mp.setId(member.getId());
				 //DB에 저장하기위해. 그림이름과 id저장  x 여럿
				//택배 여러개 동시 배송을 위해 상품명과 구입한 ㅅ 이름 알려주기
				
				photos.add(mp);  
				//실제 upload폴더에 저장하는 작업
				
				FileOutputStream fos = new FileOutputStream(
						new File(real+"/"+fileName));
				fos.write(mf.getBytes());
				fos.close();
				
				member.setFileName(fileName);
				//member에 마지막 그림 저장. 에러날까봐하는거고 없어도 뭐
			}
			//여기로 return하는거임. 성공하면 insert서 처리하니까 없어도
			result = ms.insert(member); //회원정보와 마지막사진 1장저장
			ms.insertPhoto(photos); 
			//따로 저장해야함 사진과id만 여러개 저장
			
			
		} else result = -1; //값 없을때만 입력하자
		model.addAttribute("result",result);
		return "join"; //리턴은 조인이다.
	}
	
	@RequestMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping("login")
	public String login(Member member,Model model,HttpSession session) {
		int result=0;
		Member mem=ms.select(member.getId());
		if(mem==null || mem.getDel().equals("y"))
			result=-1;
			//멤버의 id서 셀렉트하고 멤버가0이거나 삭제상태면. 없는id로
		else if(mem.getPassword().equals(member.getPassword())) {
			result = 1;
			//data패스워드와      입력한 페스워드가 같다면
			 //성공
		session.setAttribute("id", member.getId());
		}
		model.addAttribute("result",result);
		return "login";
	}
	
	@RequestMapping("main")
	public String main(Model model,HttpSession session) {
		String id=(String)session.getAttribute("id");
		Member member = null;
		if(id != null && !id.equals(""))
			//값이 있고  비어있지도 않으면
		 member = ms.select(id);
		model.addAttribute("member",member);
		return "main";
		
	}
	@RequestMapping("view")
	public String view(Model model,HttpSession session) {
		
		String id=(String)session.getAttribute("id");
		Member member = ms.select(id);
		//아이디 조회하는거
		model.addAttribute("member",member);
		return "view";
	}
	@RequestMapping("view2")
	public String view2(Model model,HttpSession session) {
		
		String id=(String)session.getAttribute("id");
		Member member = ms.select(id);
		List<MemberPhoto> list=ms.listPhoto(id);
		
		//물건 지정 끝나고 다시 여기로 돌아와서 물건 담기 시작
		model.addAttribute("member",member);
		model.addAttribute("list",list);
		
		return "view2";
	}
	
	@RequestMapping("updateForm")
	public String updateForm(Model model,HttpSession session) {
		String id=(String)session.getAttribute("id");
		Member member = ms.select(id);
		model.addAttribute("member",member); //아이디 찾아가기
		return "updateForm";
	}
	
	@RequestMapping("update")
	public String update(Member member,Model model,HttpSession session) throws IOException {
		int result=0;
		String fileName = member.getFile().getOriginalFilename();
	//join복붙. 아이디는 당연히 있을테니 체크할 필요 없그
		
	
		if(fileName != null && !fileName.equals("")) {
			//파일있을때만 한다~ 변경됐으면 이하 ㄱ
			
			member.setFileName(fileName);
			String real=session.getServletContext()
					.getRealPath("/resources/upload");
			FileOutputStream fos=new FileOutputStream(
					new File(real+"/"+fileName));
			fos.write(member.getFile().getBytes());
			fos.close();
		}else result=-1; //그대로 쓸꺼니까 저장ㄴㄴ. 
		result=ms.update(member);
		
		model.addAttribute("result",result);
		return "update";
	}
	@RequestMapping("delete")
	public String delete(Model model,HttpSession session) {
	 String id=(String)session.getAttribute("id");
	 int result = ms.delete(id);
	 
	 if(result>0) session.invalidate(); //세션0으로 만듬
	 model.addAttribute("result",result);
	
	return "delete";
}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	
}
