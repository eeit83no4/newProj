<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<style>
#includeDiv{
	border:3px solid green;
	width:500px;
	margin:0 auto;
}
#leftDiv{
	border:1px solid gray;
	margin:5px;	
}
.required{
	color:red;
}
#nextBT{
	text-align:center;
}
</style>
</head>
<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<div id='includeDiv'>
		<!--  -->
		<div id='leftDiv'>
			<h3>店家資訊</h3>
			
			<form action='<c:url value="/newStore/createStoreServlet.controller"/>' method='post'>
				<span>店家名稱：</span><input type='text' name='storeName' required="required" size="35"><span class='required'>${noStoreName}(必填)</span><br/>
				<span>店家電話：</span><input type='text' name='storePhone' size="35"><br/>
				<span>店家地址：</span><input type='text' name='storeAdd' size="35"><br/>
				<span>店家類型：</span>
				<label><input type='checkbox' name='storeClass' checked='checked' value='便當類'>便當類</label>
				<label><input type='checkbox' name='storeClass' value='飲料類'>飲料類</label>
				<label><input type='checkbox' name='storeClass' value='甜點類'>甜點類</label>
				<label><input type='checkbox' name='storeClass' value='下午茶'>下午茶</label>
				<label><input type='checkbox' name='storeClass' value='其他'>其他</label><br/>
				<div id='nextBT'>
					<input type='submit' value='下一步'>
				</div>
			</form>
		</div>
		
	</div>
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>
</body>
</html>