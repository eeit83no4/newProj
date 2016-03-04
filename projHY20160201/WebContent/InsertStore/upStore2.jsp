<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="home">
<div id="wrap">
	<jsp:include page="/header.jsp"/>
	
	<form action="<c:url value='/insertStoreAction.action' />" method="get">
	
	
		店家名稱: <input type="text" value="草莓店" id="name" name="store" placeholder="店家名稱" /><br />
		電話: <input type="text" value="093435737" id="phone" name="phone" placeholder="電話" /><br />		
		地址: <input type="text" value="大安區" id="phone" name="address" placeholder="地址" /><br />			
 		類型: <div id="showBlock" ></div> 		
	
		<input type="submit" value="新增店家" name="submit" id="id">
		
		
  		<input type="submit" name="sub" value="1"><br />
	</form>
	<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script>
	var txtId = 1;
		$(function(){
				

				//長出店家類型 名稱
			$('#name').val('${data.storeName}');	 
			    var b="${data.storeClass}";
			    var c=b.split(",");			    
			    var d="${data.storeClassAll}";
			    var e=d.split(",");
			    for (var i = 0; i < e.length; i++) {
				    $("#showBlock").append('<input type="checkbox" id="div' + txtId + '" name="storeClass"  value="'+e[i]+'" />'+e[i]+'');
				    txtId++;
				    }
				for(var i = 0; i < c.length; i++){
					for (var y = 0; y < e.length; y++) {
						if(c[i] == (e[y])){
							$('input[value="'+c[i]+'"]').prop("checked",true);
								break;
							}					
						}
					}
		})
		
		
		
		
		
	</script>
	<jsp:include page="/footer.jsp"/>
</div>
</body>
</html>