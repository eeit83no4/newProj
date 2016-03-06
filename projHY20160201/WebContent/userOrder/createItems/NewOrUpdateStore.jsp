<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<form>
				<span>店家名稱：</span><input type='text' required="required" size="35"><span class='required'>(必填)</span><br/>
				<span>店家電話：</span><input type='text' size="35"><br/>
				<span>店家地址：</span><input type='text' size="35"><br/>
				<span>店家類型：</span>
				<label><input type='checkbox' name='sotreClass' value='便當類'>便當類</label>
				<label><input type='checkbox' name='sotreClass' value='飲料類'>飲料類</label>
				<label><input type='checkbox' name='sotreClass' value='甜點類'>甜點類</label>
				<label><input type='checkbox' name='sotreClass' value='下午茶'>下午茶</label>
				<label><input type='checkbox' name='sotreClass' value='其他'>其他</label><br/>
				<input type='button' value='新增店家'><input type='button' value='修改店家'>
			</form>
		</div>
		
	</div>
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>
</body>
</html>