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
 function idChk() {
	 if (!frm.id.value) {
		 alert ("아이디를 입력하고 중복체크 하세요");
		 return false;
	 }//data에 컨트롤러가 보내준 msg가 들어온다.
	 $.post("idChk.do","id="+frm.id.value,function(data){
		$('#disp').html(data);
		//.= 클래스  # =id
		//j쿼리에 있는 ajax를 사용한다. 아니 안 배웠지만 교안보고 해야되나
	 });
	
}

</script>
</head>
<body>

<div class="container" align="center">
 <h2 class="text-primary">회원가입</h2>
 
 <form action="join2.do" method="post" enctype="multipart/form-data"
onsubmit="return chk()" name="frm" ><!--  여긴 암호확인 이름이 frm -->
<table class="table table-bordered">
  
  <tr><td>아이디 <span class="glyphicon glyphicon-user"></span></td>
  
  <td><input type="text" name="id" required="required"
  autofocus="autofocus"><input type="button" onclick="idChk()"
  class="btn btn-info btn-sm" value="중복체크">
  <div id="disp" class="err"></div> </td></tr>
 <!-- 에러나면 ->header에 red글자색으로 경고 -->
  
  <tr><td>암호 <span class="glyphicon glyphicon-lock"></span></td>
 <td><input type="password" name="password" required="required"></td></tr>
 
   <tr><td>암호확인<span class="glyphicon glyphicon-lock"></span></td>
 <td><input type="password" name="password2" required="required"></td></tr>
 
   <tr><td>이름<span class="glyphicon glyphicon-user"></span></td>
 <td><input type="text" name="name" required="required"></td></tr>
 
   <tr><td>이메일<span class="glyphicon glyphicon-envelope"></span></td>
 <td><input type="email" name="email" required="required"></td></tr>
 
    <tr><td>사진<span class="glyphicon glyphicon-picture"></span></td>
 <td><input type="file" name="file" required="required"
  multiple="multiple"></td></tr>
<!-- 폼은 조인2로가고 사진에 멀티플이 있는거빼곤 같다  -->
  <tr><td colspan="2"><input type="submit" value="확인"
  class="btn btn-danger"></td></tr>
</table></form>
<a href="loginForm.do" class="btn btn-success">로그인</a>


</div>

</body>
</html>