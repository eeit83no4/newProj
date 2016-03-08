<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<p>haha</p>
<p>${item}</p>
<a href='<c:url value="/userOrder/showItemsAction.action?groupno="/>1'><p>aa</p></a>
</div>
<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script>
// 	$(function(){
		
// 		$.ajax({
// 			"method":"GET",
// 			"url":'<c:url value="/userOrder/showItems.controller"/>',
// // 			"dataType":"json",											
// 			"success":function(data){
// 				console.log('OK');
// 			}
// 		});	
		

		
// 	});

	
	

</script>
</body>
</html>