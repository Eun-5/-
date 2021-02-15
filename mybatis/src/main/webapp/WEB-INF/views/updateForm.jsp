<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
    <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function chk() {
	if (frm.password.value !=frm.password2.value) {
		alert("암호와 암호확인이 다릅니다");
		frm.password.focus(); //커서를 패스워드에 가게하기
		frm.passwordvalue=""; //패스워드 입력한거 삭제하기
		return false; //action을 하지 말아요
	}
}

</script>
</head>
<body>

<div class="container" align="center">
 <h2 class="text-primary">정보 수정</h2>
 
 <form action="update.do" method="post" enctype="multipart/form-data"
onsubmit="return chk()" name="frm" >

<input type="hidden" name="id" value="${member.id }">
<!-- 못 고치게 감추자 -->

<table class="table table-bordered">
  
  <tr><td>아이디 <span class="glyphicon glyphicon-user"></span></td>
  <td>${member.id }</td></tr>

  
  <tr><td>암호 <span class="glyphicon glyphicon-lock"></span></td>
 <td><input type="password" name="password" required="required"></td></tr>
 
   <tr><td>암호확인<span class="glyphicon glyphicon-lock"></span></td>
 <td><input type="password" name="password2" required="required"></td></tr>
 
   <tr><td>이름<span class="glyphicon glyphicon-user"></span></td>
 <td><input type="text" name="name" value="${member.name }"
 required="required"></td></tr>
 
   <tr><td>이메일<span class="glyphicon glyphicon-envelope"></span></td>
 <td><input type="email" name="email" value="${member.email }"
  required="required"></td></tr>
 
    <tr><td>사진<span class="glyphicon glyphicon-picture"></span></td>
 <td><input type="file" name="file"> 
 ${member.fileName }</td></tr>
<!--  수정이 안되고 value가 안됨.지금 파일이름은 보여주는데 파일 재선택 해야됨
리콰이어 없앰 -->
 
  <tr><td colspan="2"><input type="submit" value="확인"
  class="btn btn-danger"></td></tr>
</table></form>
<a href="main.do" class="btn btn-success">메인</a>


</div>

</body>
</html>