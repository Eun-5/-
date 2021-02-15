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

<c:if test="${result > 0 }">
 <script type="text/javascript">
  alert("삭제 성공했어");
  location.href="deptList.do";
 </script>
</c:if>

<c:if test="${result == 0 }">
 <script type="text/javascript">
  alert("삭제 실패했어. 다시 확인해줄래?");
  history.go(-1);
 </script>
</c:if>

</body>
</html>