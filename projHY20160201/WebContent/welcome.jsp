<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
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
<h1>hello ${LoginOK.user_id}</h1>

<a href="index.jsp">首頁</a><br/>
<a href="page3.jsp">page3</a><br/>
<a href="signout.jsp">sing out</a><br/>
<a href="StorePage/SelectStoreName.jsp">store</a><br/>
<a href="MyGroup/index.jsp">MyGroupIndex</a><br/>
 
 

</body>
</html>