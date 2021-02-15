<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${result>0 }">
		<script type="text/javascript">
			alert("로그인성공");
			location.href = "main.do"
		</script>
	</c:if>
	
	<c:if test="${result==0 }">
		<script type="text/javascript">
			alert("암호가 달라");
			history.back(); //go(-1)이랑 같어
		</script>
	</c:if>
	
	<c:if test="${result==-1 }">
		<script type="text/javascript">
			alert("없는 아이디야");
			history.back();
		</script>
	</c:if>

</body>
</html>