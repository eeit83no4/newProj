<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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

<div class="container col-md-2">
  <ul class="nav nav-pills nav-stacked">
    <li class="active"><a data-toggle="tab" href="#groupDiv">團購維護</a></li>
    <li><a data-toggle="tab" href="#storeDiv">店家維護</a></li>
    <li><a data-toggle="tab" href="#adminDiv">管理員維護</a></li>
  </ul>
</div>

<div class="col-md-10 tab-content">
<div id="groupDiv" class="tab-pane fade in active">
	<form>
		<table class="table table-hover table-bordered table table-condensed">
		<thead>
			<tr class="success">
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
</div>
<div id="storeDiv" class="tab-pane fade">	
	<form>
		<table class="table table-hover table-bordered table table-condensed">
		<thead>
			<tr class="success">
				<th>店家編號</th>
				<th>建立者</th>
				<th>店家名稱</th>
				<th>修改店家</th>
				<th>刪除店家</th>
			</tr>	
		</thead>
		<tbody id="tb_store">
		</tbody>
		</table>
	</form>
</div>
<div id="adminDiv" class="tab-pane fade">
	<form>
		<table class="table table-hover table-bordered table table-condensed">
		<thead>
			<tr class="success">
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
</div>	

<!-------------------------------------內容寫在上面 --------------------------------------------->
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>

<!--------------------------------- javaScript ------------------------------------->
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<script language="JavaScript">
var xml = new XMLHttpRequest();
$(function(){	
	//動態生成團購維護表格
	$.each(${all_order}, function(index, bean){
		var cell1 = $("<td></td>").text(bean.團購編號);
		var cell2 = $("<td></td>").text(bean.截止時間);
		var cell3 = $("<td></td>").text(bean.團購名稱);
		var cell4 = $("<td></td>").text(bean.店家);
		var cell5 = $("<td></td>").text(bean.發起人);
		var cell6 = $("<td></td>").text(bean.訂單狀態);
		var cell7 = $("<td></td>").append('<input type="button" value="修改" class="btn btn-default btn-xs" onclick="editGroup('+bean.團購編號+')">');
		var cell8 = $("<td></td>").append($('<input/>')
								  .attr('type','button')
								  .attr('value','刪除')
								  .attr('class','btn btn-default btn-xs')
								  .attr('onclick','if(confirm("確定要刪除 :'+bean.團購編號+'號  '+bean.團購名稱+' 嗎??"))deletGroup('+bean.團購編號+')'))
								  .attr('id',bean.團購編號);
		var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8]);
		$('#tb_group').append(row);			

	});
	
	//動態生成店家維護表格
	$.each(${all_store}, function(index, bean){
		var cell1 = $("<td></td>").text(bean.店家編號);
		var cell2 = $("<td></td>").text(bean.建立者);
		var cell3 = $("<td></td>").text(bean.店家名稱);
		var cell4 = $("<td></td>").append('<input type="button" value="修改" class="btn btn-default btn-xs" onclick="editStore('+bean.店家編號+')">');
		var cell5 = $("<td></td>").append('<input type="button" value="刪除" class="btn btn-default btn-xs" onclick="deleteStore('+bean.店家編號+')">');
		
		var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4, cell5]);
		$('#tb_store').append(row);
	});
	
	//動態生成管理員維護表格
	$.each(${all_admin}, function(index, bean){
		var cell1 = $("<td></td>").text(bean.員工編號);
		var cell2 = $("<td></td>").text(bean.員工部門);
		var cell3 = $("<td></td>").text(bean.員工姓名);
		if(bean.管理員資格 == 'A'){
			var cell4 = $("<td></td>").append('<input id="'+bean.員工編號+'"type="checkbox" checked>');
		}else{
			var cell4 = $("<td></td>").append('<input id="'+bean.員工編號+'"type="checkbox">');
		}		
		var row = $("<tr></tr>").append([cell1, cell2, cell3, cell4]);
		$('#tb_admin').append(row);
	});
	
	//管理員維護checkbox狀態寫回資料庫      //checkbox的id為員工編號
	 $('#tb_admin').on('click','input[type="checkbox"]',function(){
		 var user_id = $(this).attr("id"); //id即員工編號
		 if($(this).prop("checked")){
			xml.open("get", "/projHY20160201/admin.controller?prodaction=管理員維護&user_id="+user_id+"&auth=A", true);
			xml.send();
		}else{
			xml.open("get", "/projHY20160201/admin.controller?prodaction=管理員維護&user_id="+user_id+"&auth=B", true);
			xml.send();
		}		 
	 });

	
});

	//團購維護--刪除團購
	function deletGroup(groupno){
	$("#"+groupno).parent("tr").remove();
	xml.open("get", "/projHY20160201/admin.controller?prodaction=刪除團購&groupno="+groupno, true);
	xml.send();
	}	
	
	//團購維護--修改團購
	function editGroup(groupno){
		location.href='<c:url value="/MyGroup/group_detail.controller?xxx='+groupno+'"/>';
	}
	
	//團購維護--刪除店家
	function deleteStore(user_id){
	
	}
	//團購維護--修改店家
	function editStore(groupno){

		location.href='<c:url value="/insertStoreAction?sub='+groupno+'"/>';
	}


</script>


</body>
</html>