<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<style>
 #storename{
 margin-top: 10px;
 }
 form { 
 top:0px;
 width:450px;
 height:500px;
 border: 3px green double; 
 overflow:scroll;
 }
 dt{
 margin-left: 40px;
 }
 dd{
 margin-left: 60px;
 }
 .pricetext{
 width: 24px;
 }
</style>
</head>

<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<div>
		店名:<input type="text" name="group_name" value="${store.store_name}"><br>
		電話:<input type="text" name="phone" value="${store.phone}"><br>
		地址:<input type="text" name="address" value="${store.address}">
	</div>
	
	<form id="f1" class="col-md-5"></form>
	</div>
	<div class="col-md-2"></div>
	<div>
	<form class="col-md-5">
	
	</from>
	</div>
	<div class="col-md-2">
	<input type="submit" value="發起團購">
	</div>
	<jsp:include page="/footer.jsp"/>
</div>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>

		$(function(){
			
			
			<c:forEach var="itemno" items='${itemnos}'>	
				console.log('${itemnames[itemno]}');
				<c:forEach var="sizepriceList" items='${sizeprices[itemno]}'>
					console.log('${sizepriceList.key}');
					console.log('${sizepriceList.value}');
				</c:forEach>
				
				<c:forEach var="class2List" items='${class2Lists}'>
					<c:forEach var="class2" items='${class2List[itemno]}'>
						console.log('${class2}');
						<c:forEach var="class3List" items='${class3Lists}'>
							<c:forEach var="class3List" items='${class3List[itemno][class2]}'>
							console.log('${class3List}');
							</c:forEach>
						</c:forEach>
					</c:forEach>
				</c:forEach>
				
			</c:forEach>
			
			var i=1;
			var x=1;
			<c:forEach var="itemno" items='${itemnos}'>
			$("#f1").append('<dl><input type="checkbox" name="item'+i+'"/><span id="it'+i+'">${itemnames[itemno]}</span><dl/>');
			$("#it"+i).append('<dt><input type="checkbox" name="item'+i+'"/><span id="size'+i+'">SIZE</span><dt/>');
				<c:forEach var="sizepriceList" items='${sizeprices[itemno]}'>
				$("#size"+i).append('<input type="checkbox" name="size"/><span>${sizepriceList.key}</span><input type="text" name="size" class="pricetext" value="${sizepriceList.value}"/>');
				</c:forEach>
				
				<c:forEach var="class2List" items='${class2Lists}'>
					<c:forEach var="class2" items='${class2List[itemno]}'>
						$('#it'+i).append('<dt><input type="checkbox" name="c2'+x+'"/><span>${class2}</span><dt/><dd  id="c2'+x+'"><dt/>');
						<c:forEach var="class3List" items='${class3Lists}'>
							$('#c2'+x).append('<input type="checkbox"/><span>${class3List[itemno][class2]}</span>');	
						</c:forEach>
					x++;
					</c:forEach>
				</c:forEach>
				i++;
		</c:forEach>
		})

</script>
</body>
</html>