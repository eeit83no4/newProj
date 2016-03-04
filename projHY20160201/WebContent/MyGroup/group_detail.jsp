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
			<tr><th>電話:</th> <td>${attr[5]}</td><th>剩餘時間:</th> <td>${EndDay}(${attr[8]})<c:if test='${group_status >= 1}'><c:if test='${EndSec > 0}'><input type="button" style="margin:3px" class="btn btn-default" value="立即截止"  onclick="go3(${group_no})"></c:if></c:if>
																		<c:if test='${group_status == 0}'><c:if test='${EndSec > 0}'><input type="button" style="margin:3px" disabled class="btn btn-default" value="立即截止"  onclick="go3(${group_no})"></c:if></c:if> 
																		</td></tr>
			
		</c:forEach>
		</table>
	</div>
	
	<div class="col-md-2" style="text-align:right;">
		<c:if test='${status == "進行中"}'>
<!-- 				<input type="button" style="margin:3px" class="btn btn-default" name="" value="修改團購設定" id="editBtn"><br>		 -->
			<c:if test='${group_status >= 1}'>
				 <c:if test='${EndSec < 0}'>
					<input type="button" style="margin:3px" class="btn btn-default" name="" value="訂購完成" id="succBtn" onclick="go1(${group_no})"><br/>
					<input type="button" style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id="failBtn" data-toggle="modal" data-target="#myModal"><br/>
				</c:if>
				<c:if test='${EndSec > 0}'>
					<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購完成" id="succBtn" onclick="go1(${group_no})"><br/>
					<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id="failBtn" data-toggle="modal" data-target="#myModal"><br/>
				</c:if>
			</c:if>
		
			<c:if test='${group_status == 0}'> 
				<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購完成" id="succBtn" onclick="go1(${group_no})"><br/>
				<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id="failBtn" data-toggle="modal" data-target="#myModal"><br/>
			</c:if>
		</c:if>
	</div>
	
</div>
<br>



<div align="right">
<button id ="excelbtn" class="btn btn-link"><Img Src="../images/excel.png">匯出Excel</button>
</div>

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
			<tr class="info">
				<th>商品名稱</th>
				<th>數量</th>
				<th>單價</th>
				<th>訂購者細項</th>		
			</tr>
		</thead>
	<tbody id="tb1"></tbody>		
	</table>
    </div>
    
    <div id="table_ByUser" class="tab-pane fade">
      <table class="table table-hover table-bordered">
		<thead>
		<tr class="info">
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
		<tr class="info">
			<th>員工編號</th>
			<th>訂購人</th>
			<th>商品名稱</th>
			<th>數量</th>
			<th>單價</th>
			<th>計算後</th>
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
    <!-- 	訂購失敗理由視窗 -->
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header label-danger">
          <h4 class="modal-title"><b>輸入訂購失敗原因</h4>
        </div>        
        <div class="modal-body" align="center">
			<textarea id="failreason" style="width:200px;height:100px;"></textarea>
        </div>        
        <div class="modal-footer">
        	<button type="button" id="failreasonbtn" class="btn btn-default" data-dismiss="modal" onclick="go2(${group_no})">送出</button>
        	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </div>
    </div>
  </div>
</div>
	
</div>

<!--------------------------------- javaScript ------------------------------------->
<script>
var xml = new XMLHttpRequest();

//----------------完成團購按鈕-------------------------------
function go1(groupno){
	location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=sucess&xxx="/>'+groupno;	
}









// 把錯誤彈跳視窗的text值抓到後用帶參數的方式傳給go2，之後就交給你了 > <






<!--------------------------------- 訂購失敗的方法(上) ------------------------------------->
function go2(groupno2){
			
		
		location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=failed&xxx="/>'+groupno2;
		
		
}

<!--------------------------------- 訂購失敗的方法(下) ------------------------------------->

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//----------------立即截止按鈕-------------------------------	
function go3(groupno3){
	location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=end&xxx="/>'+groupno3;		
}

	   
	   
	   
	   $(function(){			   
		 //----------------抓表1-------------------------------
		   $.each(${detail_ByItem}, function(index, ByItem){
			   var aa = new Array();
			   var bb = new Array();
			   var index = 4;
			   for(var j = 0; j<ByItem.length; j++){
				   if(j<3){
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
		   
		 //----------------抓表2-------------------------------
		   $.each(${detail_ByUser}, function(index, ByUser){
			   var aa = new Array();
			   var bb = new Array();
			   var killer=0;
			   for(var j = 0; j<ByUser.length; j++){
				   if(j<5){
					   aa[j] = $("<td></td>").text(ByUser[j]);
// 					   console.log('first'+j);
				    }else{
// 				    	console.log('else'+j);
				    	if(ByUser[j+2]==null){
				    		bb[killer] = $("<span>").append(ByUser[j]+'*'+ByUser[j+1]+'<br/>');
				    	}else{
				    		bb[killer] = $("<span>").append(ByUser[j]+'*'+ByUser[j+1]+'    '+ByUser[j+2]+'<br/>');
				    	}				    	
				    	j=j+2;
				    	killer++;
				    }					
			   }	
			   aa[5]=$("<td></td>").append(bb);
			   
			   var row = $("<tr></tr>").append(aa);
			   $('#tb2').append(row);								
			});
		   
		 //----------------抓表3-------------------------------
		   $.each(${detail_Detail}, function(index, Detail){
			   var aa = new Array(Detail.length);
			   for(var j = 0; j<Detail.length; j++){	  
				    aa[j] = $("<td></td>").text(Detail[j]);	
			   }
			   var row = $("<tr></tr>").append(aa);			   
			   $('#tb3').append(row);								
			});
		   
		   
         //----------------excel下載-------------------------------
			$('#excelbtn').click(function () {
			   var blob = new Blob([document.getElementById('table_Detail').innerHTML], {
			       type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
			   });
			   var strFile = "Group.xls";
			   saveAs(blob, strFile);
			   return false;
			});
         
		//----------------訂購失敗按鈕-------------------------------
		$('#failreasonbtn').on('click',function(){			
			var aaa=$('#failreason').val();
			xml.open("get", "/projHY20160201/module.controller.group/MyGroupServlet_3.controller?prodaction=訂購失敗&failure="+aaa, true);
			xml.send();
			$('#failBtn').prop("disabled",true);
		});

	});

</script>
</body>
</html>