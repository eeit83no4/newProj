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
<!-- -------------jquerycss--------------- -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/htmleaf-demo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<!-- ------------------------------------- -->
<style>
 #storename{
 width:450px;
 margin-top: 20px;
 font-size: 24px;
 }
#f1{
line-height:30px;
padding:20px;
border:2px orange solid;
margin-right:50px;
float:left;
margin-top: 5%;
margin-left:25%;
}
#f2{
line-height:50px;
padding:20px;
border:2px orange solid;
float:left;
margin-top: 5%;
}
 #d1 { 
 top:0px;
 width:450px;
 height:400px;
 border: 3px green double; 
 overflow:scroll;
 font-size: 24px;
 padding: 12px;
 }
 dl{
 margin: 6px;
 }
 dt{
 margin-top:-12px;
 margin-left: 10px;
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
 font-size: 18px;
 }
 #BT{
 font-size: 18px;
 }
 a{
 }
</style>
</head>

<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>

	<form id="f1" action="/projHY20160201/CreateGroutservlet.controller" method="post">
	<div id="storename">
		店名:<span style="font-size: 24px" name="group_name">${store.store_name}</span><br>
		電話:<span style="font-size: 24px" name="phone">${store.phone}</span><br>
		地址:<span style="font-size: 24px" name="address">${store.address}</span><br>
		<label style="font-size: 24px;margin-top: 10px;" for="getall"><input type="checkbox" id=getall>所有商品</label>
		<input type="button" style="float:right;" id='BT' class="btn btn-default" value="設定團購">
	</div>
	<div id="d1"></div>
    </form>
    
<!--     <form id="f2" style="border: 2px green double; width: 450px; height:300px;" > -->
<!--     <div id="imgs"></div> -->
<!--     </form> -->
  
    </div>
	<jsp:include page="/footer.jsp"/>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

<!-- ------------------------------------------------- -->
<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="../js/jquery-2.1.1.min.js"><\/script>')</script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<!-- -------------------------------------------- -->

<script>
		$(function(){		
			var i=1;
			var x=1;
			<c:forEach var="itemno" items='${itemnos}'>
				$("#d1").append('<dl><input id="ck'+${itemno}+'" type="checkbox" style="WIDTH: 20px; HEIGHT: 20px" name="ckbox" value="'+${itemno}+'"/><span style="font-size: 24px;" data-toggle="collapse" data-parent="#" href="#its'+i+'">${itemnames[itemno]}</span><div id="its'+i+'" class="panel-collapse collapse"><p id="it'+i+'" class="panel-body"></p></div><dl/>');
				$("#it"+i).append('<dt class="pan el-body" style="font-size: 18px;">SIZE</dt><dd id="size'+i+'">');
// 				$("#imgs").append('<img src="<c:url value="/userOrder/showPicAction.action?itemno="/>${itemno}" height="160" width="120"/>');
// 				$('#imgZone').attr('src','<c:url value="/userOrder/showPicAction.action?itemno="/>${itemno}');
				<c:forEach var="sizepriceList" items='${sizeprices[itemno]}'>
					$("#size"+i).append('<span>${sizepriceList.key}</span><span class="text">${sizepriceList.value}</span><br>');
				</c:forEach>
				<c:forEach var="class2List" items='${class2Lists}'>
					<c:forEach var="class2" items='${class2List[itemno]}'>
						$('#it'+i).append('<dt><span>${class2}</span><dd id="c2'+x+'">');
						<c:forEach var="class3List" items='${class3Lists}'>
							<c:forEach var="class3" items='${class3List[itemno][class2]}'>
							$('#c2'+x).append('<span>${class3}</span>');
							</c:forEach>
						</c:forEach>
					x++;
					</c:forEach>
				</c:forEach>
				i++;
			</c:forEach>
		});
		
		$('#getall').click(function(){
			$('#getall').change(function(){			
				var b = $(this).prop('checked');
				$(':checkbox').prop('checked',b);
			});
		});
		
		$('#BT').click(function(){
// 			var xml = new XMLHttpRequest();
			var itemno=[];
			$(":input[name='ckbox']:checked").each(function(){
					console.log($(this).val());
				itemno.push($(this).val());
				console.log(itemno);
			})
// 			console.log(${newstoreno});
			if(itemno !=0){
				location.href="/projHY20160201/CreateGroupservlet.controller?itemno="+ itemno + "&store_no="+'${store_no}';	
			}else{
				alert("請至少選一樣商品")
			}
// 				xml.open("get", "/projHY20160201/CreateGroupservlet.controller?itemno="+ itemno + "&store_no="+${store_no}, true);//傳值給StoreServlet
// 				location.href="/projHY20160201/SetGroup/SetGroup.controller?newstoreno="+ ${newstoreno};
// 				xml.send();	
// 				xxx();
		});
		
// 		$(':checkbox[name="ckbox"]').checked(function(){
// 			if($("#"${itemno}).attr('checked')){
// 				console.log(aaaaaaa);
// 			}else{
// 				console.log(bbbbb);
// 			}
// 		});

// 		function xxx(){
// 			var xml = new XMLHttpRequest();
// 			xml.open("get", "/projHY20160201/SetGroup.controller?newstoreno="+${newstoreno}, true);
// 			xml.send();	
// 		};
		//插入圖片
// 		$(document).ready(function() {
// 		  $('.collapse.in').prev('.panel-heading').addClass('active');
// 		  $('#accordion, #bs-collapse')
// 		    .on('show.bs.collapse', function(a) {
// 		      $(a.target).prev('.panel-heading').addClass('active');
// 		    })
// 		    .on('hide.bs.collapse', function(a) {
// 		      $(a.target).prev('.panel-heading').removeClass('active');
// 		    });
// 		});
		
		
</script>
</body>
</html>