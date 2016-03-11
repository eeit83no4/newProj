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
<!-- 	<div class="col-md-1"></div> -->
	<div class="col-md-10">
		<table id="uppertable" class="table borderless">
		<c:forEach var="attr" items="${detailUpper}">
			<tr><th style="width:5%;"><span class="glyphicon glyphicon-heart"></span>&nbsp團購名稱:</th> <td style="width:5%;">${attr[0]}</td> 
				<th style="width:5%;"><span class="glyphicon glyphicon-list-alt"></span>&nbsp公告事項:</th> <td style="width:5%;">${attr[2]}</td></tr>
			<tr><th><span class="glyphicon glyphicon-home"></span>&nbsp店家名稱:</th> <td>${attr[1]}</td> 
				<th><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp當前累積數量:</th> <td>${attr[4]}</td></tr>
			<tr><th><span class="glyphicon glyphicon-user"></span>&nbsp發起人:</th> <td>${attr[6]}</td>  
				<th><span class="glyphicon glyphicon-usd"></span>&nbsp當前累積金額:</th> <td>${attr[3]}</td></tr>
			<tr><th><span class="glyphicon glyphicon-phone-alt"></span>&nbsp電話:</th> <td>${attr[5]}</td>
				<th><span class="glyphicon glyphicon-time"></span>&nbsp剩餘時間:</th> <td id="reasonid">${EndDay}(${attr[8]})
			<c:if test='${attr[10] != null}'>(${attr[10]})</c:if>
			<c:if test='${group_status >= 1 && EndSec > 0}'>
					<input type="button" style="margin:3px" class="btn btn-default-xs btn-xs" value="立即截止"  onclick="go3(${group_no})">
			</c:if>
			<c:if test='${group_status == 0 && EndSec > 0}'>
					<input type="button" style="margin:3px" disabled class="btn btn-default btn-xs" value="立即截止"  onclick="go3(${group_no})">
			</c:if> 
<%-- 			<c:if test='${failure!=null}'> --%>
<%-- 					${failure} --%>
<%-- 			</c:if>  --%>
				
			  <c:if test='${EndSec <= 0 && status == "進行中"}'>
<!-- 			  	<form action=""> -->
<!-- 					<td class="tt"><input id="enddate"	 type="datetime-local"/></td> -->
					<input type="button" value="重設時間" class="btn btn-default" id="resetTimeBtn" data-toggle="modal" data-target="#myModal_Time">
<!-- 			 	</form> -->
			  </c:if>
			</td>

			</tr>
			
		</c:forEach>
		</table>
	</div>
	
	<div class="col-md-2" style="text-align:right;">
		<c:if test='${status == "進行中"}'>
			<c:if test='${EndSec > 0}'>
				<input type="button" style="margin:3px" class="btn btn-default" name="" value="修改團購設定" id="edit_Btn" data-toggle="modal" data-target="#myModal_edit"><br>		
			</c:if>
			<c:if test='${EndSec <= 0}'>
				<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="修改團購設定" id="edit_Btn" data-toggle="modal" data-target="#myModal_edit"><br>		
			</c:if>
			<c:if test='${group_status >= 1}'>
				 <c:if test='${EndSec <= 0}'>
					<input type="button" style="margin:3px" class="btn btn-default" name="" value="訂購完成" id="succBtn" onclick="go1(${group_no})"><br/>
					<input type="button" style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id="failBtn" data-toggle="modal" data-target="#myModal"><br/>
				</c:if>
				<c:if test='${EndSec > 0}'>
					<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購完成" id="succBtn" onclick="go2(${group_no})"><br/>
					<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id="failBtn" data-toggle="modal" data-target="#myModal"><br/>
				</c:if>
			</c:if>
		
			<c:if test='${group_status == 0}'> 
				<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購完成" id="succBtn" onclick="go3(${group_no})"><br/>
				<input type="button" disabled style="margin:3px" class="btn btn-default" name="" value="訂購失敗" id="failBtn" data-toggle="modal" data-target="#myModal"><br/>
			</c:if>
		</c:if>
		<c:if test='${status != "進行中"}'>
			<input type="button" style="margin:3px" class="btn btn-default" name="" value="複製團購" data-toggle="modal" data-target="#myModal_copy"><br/>
		
		</c:if>
	</div>
	
</div>
<br>



<div align="right">
<button id ="excelbtn" class="btn btn-link"><Img Src="../images/excel.png">匯出Excel</button>
</div>

<div class="col-md-12">
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#table_ByItem" id="table1btn">按件統計</a></li>
    <li><a data-toggle="tab" href="#table_ByUser" id="table2btn">按人統計</a></li>
    <li><a data-toggle="tab" href="#table_Detail" id="table3btn">明細列表</a></li>
  </ul>

<div class="tab-content">
    <div id="table_ByItem" class="tab-pane fade in active">
      <table class="table table-hover table-bordered">
		<thead>
			<tr class="info">
				<th><strong>商品名稱</th>
				<th><strong>數量</th>
				<th><strong>單價</th>
				<th><strong>訂購者細項</th>		
			</tr>
		</thead>
	<tbody id="tb1"></tbody>		
	</table>
    </div>
    
    <div id="table_ByUser" class="tab-pane fade">
      <table class="table table-hover table-bordered">
		<thead>
		<tr class="info">
			<th><strong>付款狀態</th>
			<th><strong>員工編號</th>
			<th><strong>訂購人</th>
			<th><strong>數量</th>
			<th><strong>總價</th>
			<th><strong>計算後總價</th>
			<th><strong>商品細項</th>			
		</tr>
		</thead>	
	<tbody id="tb2"></tbody>		
	</table>
    </div>
    
    <div id="table_Detail" class="tab-pane fade">
	<table class="table table-hover table-bordered">
		<thead>
		<tr class="info">
<!-- 			<th><strong>付款狀態</th> -->
			<th><strong>員工編號</th>
			<th><strong>訂購人</th>
			<th><strong>商品名稱</th>
			<th><strong>數量</th>
			<th><strong>單價</th>
			<th><strong>計算後</th>
			<th><strong>備註</th>
			<th><strong>訂購時間</th>
			<th><strong>刪除</th>
		</tr>
		</thead>
	<tbody id="tb3"></tbody>
	</table>
    </div>
</div>
</div>



<!-------------------------------------內容寫在上面 --------------------------------------------->
	<!-------------------------- 載入底部 ------------------------ -->
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
  
  <!------------------------ 重新設定團購時間視窗 ------------------------ -->
	<div class="modal fade" id="myModal_Time" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header label-danger">
          <h4 class="modal-title"><b>設定截止時間</h4>
        </div>        
		<input id="enddate"	 type="datetime-local"/>
        <div class="modal-footer">
        	<button type="submit" id="resetBtn" class="btn btn-default" data-dismiss="modal">送出</button>
        	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </div>
    </div>
  </div>
  
  <!--------------------------  複製團購視窗 ------------------------ -->
	<div class="modal fade" id="myModal_copy" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header label-danger">
          <h4 class="modal-title"><b>設定新團購</h4>
        </div>
        <div class="modal-body" align="center">
			<h5>團購名稱:</h5><textarea id="name_new" style="width:200px;height:30px;"></textarea>
        </div>
        <div class="modal-body" align="center">
			<h5>公告:</h5><textarea id="ann_new" style="width:200px;height:30px;"></textarea>
        </div>
        <div class="modal-body" align="center">
			<h5>截止時間:</h5><input id="enddate2"	 type="datetime-local"/>
        </div>  
                    
        <div class="modal-footer">
        	<button type="button" id="copyBtn" class="btn btn-default" data-dismiss="modal">送出</button>
        	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </div>
    </div>
  </div>
  
   <!--------------------------  修改團購設定 ------------------------ -->
	<div class="modal fade" id="myModal_edit" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header label-danger">
          <h4 class="modal-title"><b>修改團購設定</h4>
        </div>
        <div class="modal-body" align="center">
			<h5>團購名稱:</h5><textarea id="name_new2" style="width:200px;height:30px;"></textarea>
        </div>
        <div class="modal-body" align="center">
			<h5>公告:</h5><textarea id="ann_new2" style="width:200px;height:30px;"></textarea>
        </div>
        <div class="modal-body" align="center">
			<span>運費:</span>
			<label><input type="radio" name="gender" id="havemon">是</label>
			<label><input type="radio" name="gender" id="havemon2">否</label>
			<div id="money">
							
			</div>
	
        </div> 
                    
        <div class="modal-footer">
        	<button type="button" id="editBtn" class="btn btn-default" data-dismiss="modal">送出</button>
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

// function go2(groupno2){		
// 		location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=failed&xxx="/>'+groupno2;				
// }

function go3(groupno3){
	location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=end&xxx="/>'+groupno3;		
}

	   
	   
	   
	   $(function(){			   
		   gettable1();
		   gettable2();
		   gettable3();
         //----------------excel下載-------------------------------
			$('#excelbtn').click(function () {
			   var blob = new Blob([document.getElementById('table_ByUser').innerHTML], {
			       type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
			   });
			   var strFile = "Group.xls";
			   saveAs(blob, strFile);
			   return false;
			});
         
		//----------------訂購失敗按鈕-------------------------------
		$('#failreasonbtn').on('click',function(){
			var failreason=$('#failreason').val();
			location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=訂購失敗&failure='+failreason+'&xxx="/>'+${group_no};
			$('#failBtn').prop("disabled",true);
		});	
		
		//----------------重設時間按鈕-------------------------------
		$('#resetBtn').on('click',function(){
			var enddate=$('#enddate').val();
			location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=重設時間&enddate='+enddate+'&xxx="/>'+${group_no};	
			$('#resetTimeBtn').prop("disabled",true);
		});	
		
		//----------------複製團購按鈕-------------------------------
		$('#copyBtn').on('click',function(){
			var enddate2=$('#enddate2').val();
			var name_new=$('#name_new').val();
			var ann_new=$('#ann_new').val();
			location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=複製團購&name_new='+name_new+'&ann_new='+ann_new+'&enddate2='+enddate2+'&xxx="/>'+${group_no};	
		});	
		
		 //----------------運     費 ------------------------------------------------------------
		
	      $("#havemon").click(function(){
	    	  $("#money").append('<span>'+'金額:'+'</span>'+'<input type="text" id="gold" style="width:50px">')
	    	  
	    	  $("#money").append(  	      	
	  	    	    '<label><input type="radio" checked name="gender" value="人頭分攤" id="ship_head">'+'人頭分攤'+'</label>'+
	 	  			'<label><input type="radio" name="gender" value="主揪自己吸收">'+'主揪自己吸收'+'</label>')
	  				$(this).prop('disabled',true);	
	      })
			$("#havemon2").click(function(){
			$("#havemon").prop('disabled',false);
			 $("#money").empty(); 	 
				
				
			})
		
		
		//------------------重新設定團購----------------------------------------
		$('#editBtn').on('click',function(){
			var name_new2=$('#name_new2').val();
			var ann_new2=$('#ann_new2').val();
			var gold=$('#gold').val();		
			var ship_head=$('#ship_head').val();
			var gold_final=null;
			$('input:radio[name="gender"]').each(function(){
				if($(this).prop('checked')){
					gold_final = $(this).val() + '(' + gold + ')';
				}
			})			
			alert("成功");
			location.href='<c:url value="/module.controller.group/MyGroupServlet_3.controller?prodaction=重新設定團購&name_new2='+name_new2+'&ann_new2='+ann_new2+'&gold='+gold_final+'&xxx="/>'+${group_no};	
		});
		
		
		
		
// 		if($("#failreason").val()!=null){
// 			$("#reasonid").append($("#failreason").val());
// 			console.log($("#failreason").val());
// 		}

	});
	//----------------抓表1-------------------------------
	function gettable1() {
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
	}
	//----------------抓表2-------------------------------
	function gettable2() {	
	   $.each(${detail_ByUser}, function(index, ByUser){
		   var aa = new Array();
		   var bb = new Array();
		   var killer=0;
		   for(var j = 0; j<ByUser.length-1; j++){
			   　if(j<5){
				   if(j==0){
// 					   console.log(ByUser);
					   if(ByUser[ByUser.length-1]=='y'){
					   		aa[0] = $("<td></td>").append("<input type='checkbox' value='"+ByUser[0]+"'checked />");
				   	   }else{
				   			aa[0] = $("<td></td>").append("<input type='checkbox' value='"+ByUser[0]+"'/>");
				   	   }
				   }
				   aa[j+1] = $("<td></td>").text(ByUser[j]);
			    }else{
			    	if(ByUser[j+2]==null){
			    		bb[killer] = $("<span>").append(ByUser[j]+'*'+ByUser[j+1]+'<br/>');
			    	}else{
			    		bb[killer] = $("<span>").append(ByUser[j]+'*'+ByUser[j+1]+'		'+ByUser[j+2]+'<br/>');
			    	}
			    	j=j+2;
			    	killer++;
			    }
		   }
		   aa[6]=$("<td></td>").append(bb);
		   
		   var row = $("<tr></tr>").append(aa);
		   $('#tb2').append(row);								
		});
	}
	 //----------------抓表3-------------------------------
	function gettable3() {
	   $.each(${detail_Detail}, function(index, Detail){
		   var aa = new Array(Detail.length);
// 		   if(Detail[9]=='y'){
// 			   aa[0] =  $("<td></td>").append("<input type='checkbox' value='"+Detail[8]+"'checked />");
// 		   }else{
// 			   aa[0] =  $("<td></td>").append("<input type='checkbox' value='"+Detail[8]+"'/>");
// 		   }
		   for(var j = 0; j<Detail.length-2; j++){
			   if(j<Detail.length-3){
			    aa[j] = $("<td></td>").text(Detail[j]);
			   }else if(j==Detail.length-3 && ${LoginOK.user_id}==Detail[0]){			   
				   aa[j] = $("<td></td>").append($('<input/>')
			  				  .attr('type','button')
			  				  .attr('value','刪除')
			    			  .attr('class','btn btn-default btn-xs')
			  				  .attr('onclick','if(confirm("確定要刪除 :'+Detail[1]+'訂購的 '+Detail[2]+' 嗎??"))deleteOrder('+Detail[j]+","+'${group_no}'+')'))
			  				  .attr('id',"odid"+Detail[j]);//id為order_detail的主鍵
			   }else if(j==Detail.length-3){			   
				   aa[j] = $("<td></td>").append($('<input/>')
			  				  .attr('type','button')
			  				  .prop('disabled',true)
			  				  .attr('value','刪除')
			    			  .attr('class','btn btn-default btn-xs')
			  				  .attr('onclick','if(confirm("確定要刪除 :'+Detail[1]+'訂購的 '+Detail[2]+' 嗎??"))deleteOrder('+Detail[j]+","+'${group_no}'+')'))
			  				  .attr('id',"odid"+Detail[j]);//id為order_detail的主鍵			  				  
			   }
		   }
		   
		   var row = $("<tr></tr>").append(aa);			   
		   $('#tb3').append(row);								
		});
	}
		 
		 //----------------表2付款狀態回傳到資料庫-------------------------------
		 	$('#tb2').on('click','input[type="checkbox"]',function(){
				 var user_id = $(this).val(); //員工編號
				 if($(this).prop("checked")){
					xml.open("get", "/projHY20160201/module.controller.group/MyGroupServlet.payStatus?prodaction=表2付款狀態修改&group_no="+${group_no}+"&user_id="+user_id+"&pay_status=y", true);
					xml.send();
				}else{
					xml.open("get", "/projHY20160201/module.controller.group/MyGroupServlet.payStatus?prodaction=表2付款狀態修改&group_no="+${group_no}+"&user_id="+user_id+"&pay_status=n", true);
					xml.send();
				}		 
			 });

		 //----------------表3付款狀態回傳到資料庫-------------------------------
			 $('#tb3').on('click','input[type="checkbox"]',function(){
				 var detail_no = $(this).val(); //order_detail主鍵
				 if($(this).prop("checked")){
					xml.open("get", "/projHY20160201/module.controller.group/MyGroupServlet.payStatus?prodaction=表3付款狀態修改&detail_no="+detail_no+"&pay_status=y", true);
					xml.send();
				}else{
					xml.open("get", "/projHY20160201/module.controller.group/MyGroupServlet.payStatus?prodaction=表3付款狀態修改&detail_no="+detail_no+"&pay_status=n", true);
					xml.send();
				}		 
			 });
		//----------------明細列表刪除訂購-------------------------------
			function deleteOrder(detail_no,groupno){
// 				$("#odid"+detail_no).parent("tr").remove();
// 				xml.open("get", "/projHY20160201/module.controller.group/MyGroupServlet.deleteOrder?detail_no="+detail_no+"&groupno="+groupno, true);//傳值給StoreServlet
// 				xml.send();
				location.href="/projHY20160201/module.controller.group/MyGroupServlet.deleteOrder?detail_no="+detail_no+"&groupno="+groupno;
			}
			 
	   

		 
	   
		//----------------按人、明細button按下就刷新-------------------------------
		
// 		$('#table2btn').click(function(){
// 			$('#tb2').empty();//先清空再將新資料放回
// 			gettable2();
// 			xml.open("get", "/projHY20160201/MyGroup/group_detail.controller4?xxx="+${group_no}, true);
// 			xml.send();
// 			location.href='<c:url value="/MyGroup/group_detail.controller?xxx="/>'+groupno;
			
// 		})
		
// 		$("#table3btn").click(function(){
// 			$('#tb3').empty();//先清空再將新資料放回
// 			gettable3();
// 			xml.open("get", "/projHY20160201/MyGroup/group_detail.controller4?xxx="+${group_no}, true);
// 			xml.send();
// 		})
		

</script>
</body>
</html>