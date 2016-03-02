<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>InsertStore</h3>	
							
<form action="<c:url value='/insertStore.action' />" method="get">
<!-- <div> -->
<table>
	<tr>
		<td>店家 : </td>
		<td><input type="text" name="store" value="雞排店" id="Store"></td>
		<td>${errors.username}</td>
	</tr>
	<tr>
		<td>類型一 : </td>
		<td><input type="checkbox" name="storeClass" value="便當類" >便當類</td>
		<td>${errors.password}</td>
	</tr>
		<tr>
		<td>類型二 : </td>
		<td><input type="checkbox" name="storeClass" value="飲料類" >飲料類</td>
		<td>${errors.password}</td>
	</tr>
	<tr>		
		<td>類型三 : </td>
		<td><input type="checkbox" name="storeClass" value="甜點類" >甜點類</td>
		<td>${errors.password}</td>
	</tr>	
	<tr>
		<td>　</td>
		<td align="right"><input type="submit" value="按鈕" id="id"></td>
	</tr>	
	
</table>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script>
		$(function() {		
			
		$('#id').click(function(){			
// 			$('#StoreClass').val($('#StoreClass').val()+","+$('#StoreClass').val()+","+$('#StoreClass').val());
			console.log($('#Store').val());
			console.log($('#StoreClass').val());
		})		
	})
	
</script>
<!-- </div> -->
</form>
</body>
</html>