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
<h2 class="text-primary">부서정보 수정</h2>


<form action="deptUpdate.do" method="post" name="frm">

<input type="hidden" name="deptno" value="${dept.deptno }">
<!-- 받아온 deptno를 숨겨두는 1형. 읽게만 가능하게한2형 -->

<table class="table table-bordered">
<tr>
 <td>부서코드</td><td>${dept.deptno }
<%--  <td><input type="number" name="deptno"
 value="${dept.deptno }" readonly="readonly"> --%></td></tr>
 
 <td>부서명</td><td><input type="text" name="dname"
 required="required" value="${dept.dname }"
 autofocus="autofocus"></td></tr>

 <td>근무지</td><td><input type="text" name="loc"
 required="required" value="${dept.loc }"></td></tr>
 
 <tr><td colspan="2" align="center">
 <input type="submit" class="btn btn-success" value="확인">

</table>
</form>

<a href="deptList.do" class="btn btn-info">부서목록</a>

</div>
</body>
</html>