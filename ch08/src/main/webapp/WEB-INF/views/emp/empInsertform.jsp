<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 function empChk() {
	 if(!frm.empno.value) {
		 alert("사번 입력 후에 체크해줘");
		 frm.empno.focus();
		 return false;
	 }
	$.post('empChk.do','empno='+frm.empno.value, function (data) {
		$('#empChk').html(data);
	});
}

</script>
</head>
<body>

<div class="container" align="center">
<h2 class="text-primary">직원 정보 입력</h2>

<form action="empInsert.do" method="post" name="frm">
<table class="table table-bordered">

 <tr><td>사번</td><td><input type="number" name="empno"
 required="required" autofocus="autofocus">
 
 <!--  -->
 <input type="button" value="중복체크" onclick="empChk()"
 class="btn btn-warning">
 <div id="empChk" class="err"></div></td></tr>
<!--  -->
 
<tr><td>이름</td><td><input type="text" name="ename"
 required="required"></td></tr>
 
<tr><td>업무</td><td><input type="text" name="job"
 required="required"></td></tr>
 
<tr><td>관리자 사번</td><td>
<select name="mgr">
 <c:forEach var="emp" items="${empList }">
   <option value="${emp.empno }">
     ${emp.ename } (${emp.empno })</option>
<!--  관리자 목록을 주르륵 내주고 한 명 선택하기 -->
 </c:forEach>
</select>
</td></tr>
 
<tr><td>입사일</td><td><input type="date" name="hiredate"
 required="required"></td></tr>
 
<tr><td>급여</td><td><input type="number" name="sal"
 required="required"></td></tr>
 
<tr><td>COMM</td><td><input type="number" name="comm"
 required="required"></td></tr>
 
<tr><td>부서코드</td><td>
 <select name="deptno">
   <c:forEach var="dept" items="${deptList }">
   <!--      부서목록 전부 가져오고. 골라주기 
이름(넘버) . 들어가는건(=value) 넘버--> 
   
   <c:if test="${deptno==dept.deptno }"> 
     <option value="${dept.deptno }"selected="selected" >
      ${dept.dname }(${dept.deptno })</option> 
   </c:if>
    <!-- 보는 직원과 부서코드가 같으면, 그것이 선택된체로 띄워주기 -->   
      
      <!--  다르면 평범하게 띄움. 스스로 선택하기. 애 생략되면 변경을 못햄-->
   <c:if test="${deptno!=dept.deptno }"> 
   <option value="${dept.deptno }">
      ${dept.dname }(${dept.deptno })</option> 
   </c:if>
   
   </c:forEach>
    </select>
</td></tr>
 
 <tr><td colspan="2" align="center"><input type="submit" 
 value="확인" class="btn btn-danger"></td></tr>
 

</table></form>
<a href="empList.do?deptno=${deptno }" class="btn btn-info">직원목록</a>
</div>
</body>
</html>