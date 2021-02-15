package com.ch.mybatis.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionChk extends HandlerInterceptorAdapter {
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session= request.getSession();
		if(session ==null || 
			session.getAttribute("id")==null||
			session.getAttribute("id").equals("")) {
			//순서 중요
			response.sendRedirect("loginForm.do");
		    return false; 
		    
		    //로그인 안 한 애들은 메인을 쓸 수 없게 막기
		    //크롬 브라우저 닫기=세션 없어짐 . 로그인 풀리고 그런거
		} return true;
	}

}
