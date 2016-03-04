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
 font-size: 24px;
 }
 #f1 { 
 top:0px;
 width:450px;
 height:500px;
 border: 3px green double; 
 overflow:scroll;
 font-size: 24px;
 }
 dt{
 margin-left: 40px;
 font-size: 24px;
 }
 dd{
 margin-left: 60px;
 font-size: 24px;
 }
 .text{
 width: 48px;
 font-size: 24px;
 }
 span{
 margin-right: 12px;
 font-size: 24px;
 }
</style>
</head>

<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>

	<form action="/projHY20160201/CreateGroutservlet.controller" method="post">
	<div id="storename">
		店名:<span style="font-size: 24px" name="group_name">${store.store_name}</span><br>
		電話:<span style="font-size: 24px" name="phone">${store.phone}</span><br>
		地址:<span style="font-size: 24px" name="address">${store.address}</span>
	</div>
	
	<div id="f1" class="col-md-5"></div>
	
	<div><input type="button" id='BT' value="發起團購"></div>

    </form>

	<div class="col-md-1"></div><!-- 空格用 -->
<!-- 	<div><form id="f2" class="col-md-3"></from></div> -->

</div>
	<jsp:include page="/footer.jsp"/>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
		$(function(){		
			var i=1;
			var x=1;
			<c:forEach var="itemno" items='${itemnos}'>
				$("#f1").append('<dl><input type="checkbox" name="checkbox" value="'+${itemno}+'"/><span>${itemnames[itemno]}<span/><span id="it'+i+'"></span><dl/>');
				$("#it"+i).append('<dt><span>SIZE<span/><dd id="size'+i+'">');
				<c:forEach var="sizepriceList" items='${sizeprices[itemno]}'>
					$("#size"+i).append('<span>${sizepriceList.key}<span/><span class="text">${sizepriceList.value}<span/><br>');
				</c:forEach>
				<c:forEach var="class2List" items='${class2Lists}'>
					<c:forEach var="class2" items='${class2List[itemno]}'>
						$('#it'+i).append('<dt><span>${class2}<span/><dd id="c2'+x+'">');
						<c:forEach var="class3List" items='${class3Lists}'>
							<c:forEach var="class3" items='${class3List[itemno][class2]}'>
							$('#c2'+x).append('<span>${class3}<span/>');
							</c:forEach>
						</c:forEach>
					x++;
					</c:forEach>
				</c:forEach>
				i++;
			</c:forEach>
		})

		
		$('#BT').click(function(){
			var xml = new XMLHttpRequest();
			var itemno=[];
			$(":input:checkbox:checked").each(function(){
					console.log($(this).val());
				itemno.push($(this).val());
				console.log(itemno);
			})
			
			xml.onreadystatechange = function(){
				if (xml.readyState == 4) {
					if (xml.status == 200){
						
					}
				}
			}
// 				var storeno=${store_no};
				
				xml.open("get", "/projHY20160201/CreateGroupservlet.controller?itemno="+ itemno + "&store_no="+${store_no}, true);//傳值給StoreServlet
				xml.send();	
		})
	
		
		
		
</script>
</body>
</html>