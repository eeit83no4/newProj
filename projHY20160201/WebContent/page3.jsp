<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<!-- 判斷使用者是否有登入 -->
<c:if test="${empty LoginOK}">
	<c:redirect url="index.jsp"/>
</c:if>
hello page3<br/>

<a href="signout.jsp">Sign out</a><br/>
<a href="welcome.jsp">welcome</a><br/>
<a href="index.jsp">首頁</a><br/>
<hr/>

<form>
	<span>學校</span>
	<input type="text" name="school"/><br/>
	<span>顏色</span>
	<input type="text" name="color"/><br/>	
	<input type="button" value="訂購" onclick="xxx()"/>
</form>





</body>
</html>