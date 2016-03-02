<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>

<!-------------------------------------內容寫在下面 --------------------------------------------->
<br>
<br>

<!-- <div class="container col-md-2 "> -->
<!--   <div class="btn-group-vertical"> -->
<!--     <button type="button" class="btn btn-primary btn-lg">團購維護</button> -->
<!--     <button type="button" class="btn btn-primary btn-lg" onclick="goStore()">店家維護</button> -->
<!--     <button type="button" class="btn btn-primary btn-lg" onclick="goAdmin('adm')">管理員維護</button> -->
<!--   </div> -->
<!-- </div> -->

<div class="container col-md-2">
  <ul class="nav nav-pills nav-stacked">
    <li class="active"><a href="#">團購維護</a></li>
    <li><a href="#">店家維護</a></li>
    <li><a href="#">管理員維護</a></li>
  </ul>
</div>

<div class="col-md-10">
	<form>
		<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>團購編號</th>
				<th>截止時間</th>
				<th>團購名稱</th>
				<th>店家</th>
				<th>發起人</th>
				<th>訂單狀態</th>
				<th>修改團購</th>
				<th>刪除團購</th>
			</tr>	
		</thead>
		<tbody id="tb_group">
		</tbody>
		</table>
	</form>
	
	<form>
		<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>店家編號</th>
				<th>店家名稱</th>
				<th>修改店家</th>
				<th>刪除店家</th>
			</tr>	
		</thead>
		<tbody id="tb_store">
		</tbody>
		</table>
	</form>
	
	<form>
		<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>員工編號</th>
				<th>員工部門</th>
				<th>員工姓名</th>
				<th>管理員資格</th>
			</tr>	
		</thead>
		<tbody id="tb_admin">
		</tbody>
		</table>
	</form>
	
</div>	


<!-------------------------------------內容寫在上面 --------------------------------------------->
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>

<!--------------------------------- javaScript ------------------------------------->
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<script language="JavaScript">

$(function(){
	$.each(${all_order}, function(index, bean){
		var cell1 = $("<td></td>").text(bean.團購編號);
		var cell2 = $("<td></td>").text(bean.截止時間);
		var cell3 = $("<td></td>").text(bean.團購名稱);
		var cell4 = $("<td></td>").text(bean.店家);
		var cell5 = $("<td></td>").text(bean.發起人);
		var cell6 = $("<td></td>").text(bean.訂單狀態);
		var cell7 = $("<td></td>").append('<input type="button" value="修改" class="btn btn-default btn-xs" onclick="go('+bean.團購編號+')">');
		var cell8 = $("<td></td>").append('<input type="button" value="刪除" class="btn btn-default btn-xs" onclick="go('+bean.團購編號+')">');
		
		var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8]);
		$('#tb_group').append(row);			

	});
	
	$.each(${all_store}, function(index, bean){
		var cell1 = $("<td></td>").text(bean.店家編號);
		var cell2 = $("<td></td>").text(bean.店家名稱);
		var cell3 = $("<td></td>").append('<input type="button" value="修改" class="btn btn-default btn-xs" onclick="go('+bean.店家編號+')">');
		var cell4 = $("<td></td>").append('<input type="button" value="刪除" class="btn btn-default btn-xs" onclick="go('+bean.店家編號+')">');
		
		var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4]);
		$('#tb_store').append(row);
	});
	
	$.each(${all_store}, function(index, bean){
		var cell1 = $("<td></td>").text(bean.員工編號);
		var cell2 = $("<td></td>").text(bean.員工部門);
		var cell3 = $("<td></td>").text(bean.員工姓名);
		var cell4 = $("<td></td>").text(bean.管理員資格);
		
		var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4]);
		$('#tb_admin').append(row);
	});
	
	
});

function go(groupno){
	location.href='<c:url value="/MyGroup/group_detail.controller?xxx='+groupno+'"/>';
}
// function goStore(){
// 	location.href='<c:url value="/admin.controller?prodaction=店家維護"/>';
// }


</script>


</body>
</html>