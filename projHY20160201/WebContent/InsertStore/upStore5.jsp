<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鴻揚科技有限公司-團購系統</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/InsertStore/js/table.css" />
</head>
<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<form action="<c:url value='/insertStoreAction.action' />" method="get">
	<input type="text" value="" id="id" name="sub"/>
	
	${StoreNo}
	${StoreNo}
	${StoreNo}
	${StoreNo}
	
	</form>
	
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
	
	<script>
	$(function(){
// 		$('#id').val('${StoreNo}')
		var storeNo = '${StoreNo}';
// 		alert('${StoreNo}')

		var formData=new FormData($('form')[0]);

		location.href='/projHY20160201/insertStoreAction.action?sub='+storeNo;
	})
	
	</script>
</div>
</body>
</html>