<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function del(deptno) { //deptno가 많으니까 지정을 해줘야 
		                   //+deptno가 지정해준 넘버네
	var cf = confirm("정말로 삭제할꺼야?");
	if (cf) location.href="deleteDept.do?deptno=${dept.deptno }"+deptno;
	 else alert("삭제를 취소했어");
		} 
	</script>
</head>
<body>
<div class="container" align="center">
<h2 class="text-primary">부서목록</h2>
<table class="table table-striped">

 <tr>
 <td>부서코드</td>
 <td>부서명</td>
 <td>근무지</td>
 <td>수정여부</td>
 <td>삭제여부</td>
 </tr>
 
        <!-- 혹시 물건이 비어있으면 -->
 <c:if test="${empty deptList }">
 <tr><td colspan="5">부서정보가 없습니다</td></tr>
 </c:if>
 
 
       <!-- 비어있지 않으면 물건 꺼내기 시작 -->
 <c:if test="${not empty deptList }">
 
             <!--컨트롤러의 물건을 받아서  하나씩꺼내기(var) -->
 <c:forEach var="dept" items="${deptList }">
  <tr>
  <td>${dept.deptno }</td>
  <td><a class="btn btn-success btn-sm"
  href="empList.do?deptno=${dept.deptno }">
  ${dept.dname }</a></td>
  <!-- 사원 정보를 보고싶다 </a>안해서 에러 -->
  
  
  <td>${dept.loc }</td>
  <td><a class="btn btn-warning btn-sm"
        href="updateDeptForm.do?deptno=${dept.deptno }">수정</a></td>
  <!-- 그냥 가지말고 deptno도 가져가라~ 란 뜻 -->
  <td><a class="btn btn-danger btn-sm"
  onclick="del(${dept.deptno})">삭제</a></td>
  <!-- 삭제할 때 확인 눌러야 삭제되는 구조로 체인지 -->
  <!-- 여기서 번호를 받아 위로 보내는건가-->
 
  </tr>
 </c:forEach></c:if>

</table>

 <a class="btn btn-info" href="insertDeptForm.do">부서정보 입력</a>
 <a class="btn btn-default" href="allEmpList.do">전 직원 목록 </a>

</div>
</body>
</html>