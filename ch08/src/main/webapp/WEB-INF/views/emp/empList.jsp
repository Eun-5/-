<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	$(function() {
		$('#disp').load("deptList.do");
	}); //제이 쿼리이이잇 안배웠는데요
</script>
</head>
<body>
<div class="container" align="center">
<h2 class="text-primary">${dept.dname }직원목록</h2>
<table class="table table-striped">

<tr>
 <td>사번</td>
 <td>이름</td>
 <td>업무</td>
 <td>입사일</td>
 </tr>
 
       
 <c:if test="${empty empList }">
 <tr><td colspan="4">직원이 없어</td></tr>
 </c:if>
       
 <c:if test="${not empty empList }">   
 <c:forEach var="emp" items="${empList }">
  
  <tr>
    <td>${emp.empno }</td>
    <td><a href="empSelect.do?empno=${emp.empno }"
    class="btn btn-warning btn-sm"> ${emp.ename }</a></td>
    <td>${emp.job }</td>
    <td>${emp.hiredate }</td>
    
  </c:forEach>
 </c:if>
 
 </table>
 
  <a href="deptList.do" class="btn btn-info">부서 목록 </a>
  
  <!-- 지금 보고있는 부서에 직원을 추가하자 -->
  <a href="empInsertform.do?deptno=${dept.deptno }"
  class="btn btn-warning">직원 정보 입력 </a>
  <div id="disp"></div>
</div>
</body>
</html>