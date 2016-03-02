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
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<script language=JavaScript src="${pageContext.request.contextPath}/js/FileSaver.min.js"></script>

<title>Display</title>
<style>
#uppertable th{
 	font-weight:bold; 
	text-align:left; 
}
#uppertable td{
	padding: 8px;
}

.borderless td, .borderless th {
   border: none  !important;
}
</style>

</head>

<body class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>

<!-------------------------------------內容寫在下面 --------------------------------------------->
<br>
<div class="row">
	<div class="col-md-10">
		<table id="uppertable" class="table borderless">
		<c:forEach var="attr" items="${detailUpper}">
			<tr><th style="width:10%;">團購名稱:</th> <td style="width:30%;">${attr[0]}</td> <th style="width:12%;">公告事項:</th> <td style="width:30%;">${attr[2]}</td></tr>
			<tr><th>店家名稱:</th> <td>${attr[1]}</td> <th>目前累積數量:</th> <td>${attr[4]}</td></tr>
			<tr><th>發起人:</th> <td>${attr[6]}</td>  <th>目前累積金額:</th> <td>${attr[3]}</td></tr>
			<tr><th>電話:</th> <td>${attr[5]}</td><th>剩餘時間:</th> <td>${EndDay}(${attr[8]})<a href="<c:url value='/xxx.controller?prodaction=已完成的團購&gn=1'/>"></td></tr>
			<td><input type="button" id ="excelbtn" value="匯出Excel"></td>
			
		</c:forEach>
		</table>
	</div>
	
	<div class="col-md-2" style="text-align:right;">
		<c:if test='${status == "進行中"}'>
			<c:if test='${EndSec > 0}'>
				<input type="button" style="margin:3px" class="btn btn-default" name="" value="修改團購設定" id=""><br>
			</c:if>
		
			<c:if test='${EndSec < 0}'>
				<input type="button" style="margin:3px" class="btn btn-default" name="" value="訂購完成" id=""><br>
				<input type="button" style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id=""><br>
			</c:if>
		</c:if>
	</div>
	
</div>

<br>



<div class="col-md-12">
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#table_ByItem">按件統計</a></li>
    <li><a data-toggle="tab" href="#table_ByUser">按人統計</a></li>
    <li><a data-toggle="tab" href="#table_Detail">明細列表</a></li>
  </ul>

<div class="tab-content">
    <div id="table_ByItem" class="tab-pane fade in active">
      <table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>商品名稱</th>
				<th>數量</th>
				<th>單價</th>
				<th>已付數</th>
				<th>訂購者細項</th>		
			</tr>
		</thead>
	<tbody id="tb1"></tbody>		
	</table>
    </div>
    
    <div id="table_ByUser" class="tab-pane fade">
      <table class="table table-hover table-bordered">
		<thead>
		<tr>
			<th>員工編號</th>
			<th>訂購人</th>
			<th>數量</th>
			<th>總價</th>
			<th>計算後總價</th>
			<th>商品</th>
		</tr>
		</thead>	
	<tbody id="tb2"></tbody>		
	</table>
    </div>
    
    <div id="table_Detail" class="tab-pane fade">
	<table class="table table-hover table-bordered">
		<thead>
		<tr>
			<th>員工編號</th>
			<th>訂購人</th>
			<th>商品名稱</th>
			<th>數量</th>
			<th>單價</th>
			<th>計算後</th>			
			<th>付款狀態</th>
			<th>備註</th>
			<th>訂購時間</th>
		</tr>
		</thead>
	<tbody id="tb3"></tbody>
	</table>
    </div>

</div>
</div>


<!-------------------------------------內容寫在上面 --------------------------------------------->
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>

<!--------------------------------- javaScript ------------------------------------->
<script>     	  
	   
	   $(function(){			   
		    
		   $.each(${detail_ByItem}, function(index, ByItem){
			   var aa = new Array();
			   var bb = new Array();
			   var index = 4;
			   for(var j = 0; j<ByItem.length; j++){
				   if(j<4){
				    aa[j] = $("<td></td>").text(ByItem[j]);	
				   }else{
					   if(ByItem[j+2]==null){
						   bb[index] = $("<span>").append(ByItem[j]+'*'+ByItem[j+1]+'<br/>');
					   }else{
						   bb[index] = $("<span>").append(ByItem[j]+'*'+ByItem[j+1]+'    '+ByItem[j+2]+'<br/>');
					   }
					j=j+2;
					index++;
				   }
			   }
			   aa[4]=$("<td></td>").append(bb);
			   
			   var row = $("<tr></tr>").append(aa);			   
			   $('#tb1').append(row);								
			});
		   
		   $.each(${detail_ByUser}, function(index, ByUser){
			   var aa = new Array();
			   var bb = new Array(ByUser.length-5);
			   var index=0;
			   for(var j = 0; j<ByUser.length; j++){
				   if(j<5){
					   aa[j] = $("<td></td>").text(ByUser[j]);					   
				    }else{
				    	if(ByUser[j+2]==null){
				    		bb[index] = $("<span>").append(ByUser[j]+'*'+ByUser[j+1]+'<br/>');
				    	}else{
				    		bb[index] = $("<span>").append(ByUser[j]+'*'+ByUser[j+1]+'    '+ByUser[j+2]+'<br/>');
				    	}				    	
				    	j=j+2;
				    	index++;
				    }					
			   }	
			   aa[5]=$("<td></td>").append(bb);
			   
			   var row = $("<tr></tr>").append(aa);
			   $('#tb2').append(row);								
			});
		   
		   
		   $.each(${detail_Detail}, function(index, Detail){
			   var aa = new Array(Detail.length);
			   for(var j = 0; j<Detail.length; j++){	  
				    aa[j] = $("<td></td>").text(Detail[j]);	
			   }
			   var row = $("<tr></tr>").append(aa);			   
			   $('#tb3').append(row);								
			});
		   
		   $(function () {
			    $('#excelbtn').click(function () {
			        var blob = new Blob([document.getElementById('table_ByItem').innerHTML], {
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