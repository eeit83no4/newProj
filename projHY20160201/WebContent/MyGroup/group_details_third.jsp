<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css" /> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script language=JavaScript src="${pageContext.request.contextPath}/js/FileSaver.min.js"></script>
<title>Insert title here</title>

</head>

<body class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	
<!-------------------------------------內容寫在下面 --------------------------------------------->
<div align="right">
<button class="btn btn-link"><Img Src="images/excel.png">匯出Excel</button>
</div>	

<div id="div_excel">
<form>
	<table>
	<table class="table table-hover table-bordered table table-condensed">
	<tr class="info">
		<th><strong>截止時間</th>
		<th><strong>團購名稱</th>
		<th><strong>店家</th>
		<th><strong>商品名稱</th>		
		<th><strong>備註</th>
		<th><strong>原價</th>
		<th><strong>數量</th>
		<th><strong>實付金額</th>
		<th><strong>實付小計</th>
	</tr>
	</thead>
	<tbody id="tb">


	</tbody>
</table>
</form>
</div>



<!-------------------------------------內容寫在上面 --------------------------------------------->
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>

<!-------------------------------------Javascript--------------------------------------------->

<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<script language="JavaScript"> 
	
	$(function(){
// 		console.log('${select[0].店家}');
		var sum=0;
		$.each(${select}, function(index, bean){
			var cell1 = $("<td></td>").text(bean.截止時間);
			var cell2 = $("<td></td>").text(bean.團購名稱);
			var cell3 = $("<td></td>").text(bean.店家);
			var cell4 = $("<td></td>").text(bean.商品名稱);
			var cell5 = $("<td></td>").text(bean.備註);
			var cell6 = $("<td></td>").text(bean.原價);
			var cell7 = $("<td></td>").text(bean.數量);
			var cell8 = $("<td></td>").text(bean.實付金額);
			var cell9 = $("<td></td>").text(bean.實付小計);
			sum=sum+bean.實付小計;
			var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9]);
			$('#tb').append(row);
		});
// 		console.log(sum);
		$('#tb').append("<tr><td colspan='9' class='text-right'>"+"<b>總計:</b>&nbsp&nbsp&nbsp"+sum+"</td></tr>");
		
		$(function () {
		    $('Button').click(function () {
		        var blob = new Blob([document.getElementById('div_excel').innerHTML], {
		            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		        });
		        var strFile = "Group.xls";
		        saveAs(blob, strFile);
		        return false;
		    });
		});
	
      });
	
</script>

</body>
</html>