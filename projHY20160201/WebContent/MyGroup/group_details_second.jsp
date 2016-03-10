<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css" /> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Insert title here</title>
</head>

<body class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	
<!-------------------------------------內容寫在下面 --------------------------------------------->
<hr>
<form>
	<table class="table table-hover table-bordered table table-condensed">
	<thead>
	<tr class="info">
		<th><strong>截止時間</th>
		<th><strong>團購編號</th>
		<th><strong>團購名稱</th>
		<th><strong>店家</th>
		<th><strong>發起人</th>
		<th><strong>參與人數</th>
		<th><strong>總額</th>
		<th><strong>訂單明細</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="bean" items="${select}">				
		<tr>
<%-- 			<td><fmt:formatDate value="${bean[0]}" pattern="yyyy-MM-dd EEEE"/></td> --%>
			<td>${bean[0]}</td>
			<td>${bean[6]}</td>
			<td>${bean[1]}</td>
			<td>${bean[2]}</td>
			<td>${bean[3]}</td>
			<td>${bean[4]}</td>
			<td>${bean[5]}</td>
			<td><input type="button" class="btn btn-default btn-xs" value="查詢" onclick="go(${bean[6]})"></td> 
		</tr>
	</c:forEach>	
	</tbody>
</table>
</form>

<!-------------------------------------內容寫在上面 --------------------------------------------->
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>


<!-------------------------------------Javascript--------------------------------------------->
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<script>

	function go(groupno){
		console.log(groupno);
		location.href='<c:url value="/MyGroup/group_detail.controller?xxx="/>'+groupno;
	}
	$(function(){
		
		console.log('${select[1]}');
	

      });

</script>

</body>
</html>