<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<div class="container" align="center">
<h2 class="text-primary">직원 정보 입력</h2>

<form action="empUpdate.do" method="post" name="frm">
 <input type="hidden" name="empno" value="${emp.empno }"> 

<table class="table table-bordered">

<!-- 받아오고 감추고. 애는 보여만주기 -->
 <tr><td>사번</td><td>${emp.empno }</td></tr>
<tr><td>이름</td><td><input type="text" name="ename"
 required="required" value="${emp.ename }"></td></tr>
 
<tr><td>업무</td><td><input type="text" name="job"
 required="required" value="${emp.job }"></td></tr>
 
<tr><td>관리자 사번</td><td>
<select name="mgr">
 
 <!-- 업데이트 컨트롤ㄹ의 현재사번이 emp니까 foreach는 다른이름쓰기 -->
 
 <c:forEach var="e" items="${empList }">
 <!-- 원래사번 보여주려면 c if 로 물어야햄 -->
  <c:if test="${emp.mgr==e.empno }">
   <option value="${e.empno }" selected="selected">
     ${e.ename } (${e.empno })</option>
  
  </c:if>
 
  <c:if test="${emp.mgr!=e.empno }">
  
  

   <option value="${e.empno }">
     ${e.ename } (${e.empno })</option>
<!--  관리자 목록을 주르륵 내주고 한 명 선택하기 -->
</c:if>
 </c:forEach>
</select>
</td></tr>
 
<tr><td>입사일</td><td><input type="date" name="hiredate"
 required="required" value="${emp.hiredate }"></td></tr>
 
<tr><td>급여</td><td><input type="number" name="sal"
 required="required" value="${emp.sal }"></td></tr>
 
<tr><td>COMM</td><td><input type="number" name="comm"
 required="required" value="${emp.comm }"></td></tr>
 
<tr><td>부서코드</td><td>
 <select name="deptno">
   <c:forEach var="dept" items="${deptList }">
   <!--      부서목록 전부 가져오고. 골라주기 
이름(넘버) . 들어가는건(=value) 넘버--> 
   
   <c:if test="${emp.deptno==dept.deptno }"> 
     <option value="${dept.deptno }"selected="selected" >
      ${dept.dname }(${dept.deptno })</option> 
   </c:if>
    <!-- 보는 직원과 부서코드가 같으면, 그것이 선택된체로 띄워주기 -->   
      
      <!--  다르면 평범하게 띄움. 스스로 선택하기. 애 생략되면 변경을 못햄-->
   <c:if test="${emp.deptno!=dept.deptno }"> 
   <option value="${dept.deptno }">
      ${dept.dname }(${dept.deptno })</option> 
   </c:if>
   
   </c:forEach>
    </select>
</td></tr>
 
 <tr><td colspan="2" align="center"><input type="submit" 
 value="확인" class="btn btn-danger"></td></tr>
 

</table></form>
<a href="empSelect.do?empno=${emp.empno }" class="btn btn-info">직원정보</a>
</div>
</body>
</html>