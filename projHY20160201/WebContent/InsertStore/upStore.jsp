<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/GetStoreDataAction.action' />" method="get">
<!-- 	      <div style="display:none"></div>                  -->
		
		<input type="submit" name="sub" value="1" ><br />
		<input type="submit" name="sub" value="2"><br />
		<input type="submit" name="sub" value="3"><br />
		<input type="submit" name="sub" value="13"><br />
		<input type="submit" name="sub" value="新增" id="id"><br />
<%-- 		${data.storeClass}<br /> --%>
<%-- 		${data.storeName} --%>
		<input type="text" value="" id="name"/><br />
 		<div id="showBlock"></div>
 		${errors.store}${errors.phone}${errors.address}
<%-- 		<span id="sp" style="display:none">${data.storeClass}</span> --%>	
	</form>
	<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
	<script>
	var txtId = 1;
	function deltxt(id) {
	  	  for(i=1;i<4;i++){
	        $("#div"+id).remove();
	  	  }
	  }
		$(function(){
				
			   $('#name').val('${data.storeName}');	   
			   
			    var b="${data.storeClass}";
			    var c=b.split(",");
			    
			    var d="${data.storeClassAll}";
			    var e=d.split(",");
			    for (var i = 0; i < e.length; i++) {
				    $("#showBlock").append('<input type="checkbox" id="div' + txtId + '" name="storeClass' + txtId + '" /><input type="text" id="div' + txtId + '" name="cbtxt' + txtId + '" style="width: 50px" value="'+e[i]+'" /><input type="button" id="div' + txtId + '" value="del" onclick="deltxt('+txtId+')">');
				      txtId++;
				    }			  
			$('#id').click(function(){
// 				alert('a')
				location.href='/projHY20160201/GetStoreDataAction.action';
			}) 
			$(':submit').click(function(){				
// 			    for (var i = 0; i < c.length; i++) {
// 			    $("#showBlock").append('<input type="checkbox" id="div' + txtId + '" name="cbtxt' + txtId + '" /><input type="text" id="div' + txtId + '" name="cbtxt' + txtId + '" style="width: 50px" value="'+c[i]+'" /><input type="button" id="div' + txtId + '" value="del" onclick="deltxt('+txtId+')">');
// 			      txtId++;
// 			    }
			
				//console.log(data);
			})
		})
	</script>
	
</body>
</html>