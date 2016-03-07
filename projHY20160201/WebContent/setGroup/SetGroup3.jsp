<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	
<style>
.btn {
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: normal;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

.btn-primary {
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
}

.tt {
	border-left: 0px;
	border-bottom: 0px;
}

#LefttDiv {
	border: 2px solid #F8F8FF;
	width: auto;
}
#Table1{
width:500px;
float: right;

}


#Div1 {
	font-family:monospace;
	margin:auto;
	font-size: xx-large;
	text-align: center;
	font-weight: bolder;
	width: 600px;
	
}

#Divin {
	width: 300px;
	float: right;
}

#Divna {
	border: 2px solid #00AAAA;
	width: 300px;
	float: left;
	margin: 0px;
}

#inem {
 	height:60px;
	text-align: center;
	border: 1px solid #00AAAA;
	width: auto;
	}
#inem1{
 height:100px;
 overflow:scroll;
}

#inem2 {
	border: 2px solid #00AAAA;
	width: auto;
}

#but {
	margin-left: 350px;
	margin-right: 400px;
	width: 1px;
	hight: 1px;
}

td, th {
	font-size: 15px;
	font-family: monospace;
	font-weight: bold;
	margin: 0px;
	border: 3px solid #AAAAAA;
	width: 1200px;

}

#Tex1 {
	width: 700px;
}

#Td1 {
	color: red;
}


<!-- -------------------------吉----------米----------------   -->


 #storename{
 margin-left:-100px;
 margin-top: 10px;
 }
 #f1 { 
 top:0px;
 width:350px;
 height:500px;
 margin-left:-100px;
 border: 3px green double; 
 overflow:scroll;s
 float: left;
 }
 dt{
 margin-left: 40px;
 font-size: 24px;
 }
 dd{
 margin-left: 60px;
 font-size: 24px;
 }
 .text{
 width: 48px;
 font-size: 24px;
 }
 span{
 margin-right: 12px;
 font-size: 24px;
 }
</style>

<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">

</style>

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/themes/hot-sneaks/jquery-ui.css"
	rel="stylesheet">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
	
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>


<style>
article, aside, figure, figcaption, footer, header, hgroup, menu, nav,
	section {
	display: block;
}
</style>
</head>
<body class="home">

	<div id="wrap">
		<!-- 	載入導覽列 -->
		<jsp:include page="/header.jsp" />
		<div class="tt" id="Div1">設 定 揪 團</div>
<!-- ---------------------------------------吉米--------------------------------------------- -->		
	<form action="/projHY20160201/CreateGroutservlet.controller" method="post">
	<div id="storename">        
		<span style="font-size: 24px" name="group_name">店名:${store.store_name}</span><br>
		<span style="font-size: 24px" name="phone">電話:${store.phone}</span><br>
		<span style="font-size: 24px" name="address">地址:${store.address}</span>
	</div>
	
	<div id="f1" style="font-size: 18px;" class="col-md-5"></div>
	
    </form>

	
<!-- ---------------------------------------------------------------------------------------- -->


				<table id="Table1">
					<tr style="border-bottom: 0px">
						<td id=Td1 style="border-bottom: 0px">團購名稱:</td>
						<td class="tt"><input type="text" id="Tex1" ></td>
					</tr>
					<tr style="border-bottom: 0px">
						<td style="border-bottom: 0px">訂購店家:</td>
						<td class="tt">${sname}</td>
					</tr>
					<tr style="border-bottom: 0px">
						<td style="border-bottom: 0px">訂餐起止:</td>
						<td class="tt"><span>截止日期:</span><input id="enddate"
							 type="datetime-local"/></td>
					</tr>
					<tr>
						<td style="border-bottom: 0px;">邀請人員:</td>
						<td class="tt">
							<div id="Divna">

								<div id="inem">
									想邀請人: <span>依照部門</span> <select id="select1">
									<option>所有部門</option>
									
									<c:forEach var="bean"
											items="${dep}">
											<option value="${bean}">${bean}</option>

										</c:forEach>


									</select>
								</div>
								<div id="inem1">
									<c:forEach var="bean" items="${emdep}">										
										<c:if test='${bean[0]!=1}'>
											<label><input type="checkbox" name="inp"
											value="${bean[0]}*${bean[1]} " />${bean[1]}</label>
											<br/>										
										</c:if>										
									</c:forEach>
								</div>
							</div>

							<div id="Divin">
								<div id="inem">已邀請:</div>
								<div id="inem2">
							    
								</div>
															
							</div>

							<div id="but">
								<input type="button" class='btn btn-primary' id="inser"
									value="加入" /> <br> <br> <input type="reset"
									class='btn btn-primary' id="clear" value="取消" />
							</div>
						</td>
					</tr>

					<tr>
						<td>公告:</td>
						<td style="border-left: 0px" ><input type="text" id="Tex2"
							name="ann"></td>
					</tr>
				</table>
			
			<input type="button" class='btn btn-primary' id="save" value="送出">
	
			
			
			<input type="button" class='btn btn-primary' value="新增共同管理員"
				data-title="Edit" data-toggle="modal" data-target="#newadmin">


		<!--   ------------------------ 我         是         彈         跳         視      窗  --------------------------      -->

		<div class="modal fade" id="newadmin" tabindex="-1" role="dialog"
			aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</button>
						<h4 class="modal-title custom_align" id="Heading">選擇共同管理者</h4>
					</div>

					<form action="" method="post">
						<div class="modal-body" id="inadmin"></div>
						<div class="modal-footer ">
							<button type="button" class="btn btn-warning btn-lg" 
							 style="width: 100%;"  id="setadmin">
								<span class="glyphicon glyphicon-ok-sign"></span>確認
							</button>
						</div>
					</form>
				</div>

				<!-- /.modal-content -->
			</div>

			<!-- /.modal-dialog -->
		</div>
		<!-- 	-------------彈跳視窗結束----------------------------------------        -->
		<script>
		
			var userIds = [];//已經存過去的，比對用
			var realUser=null;//已經存過去的，傳值用
			//-------------加入團員
		  $("#inser").click(function() {
				var name = null;//已邀請區塊
				var name2 = null;//共同管理員

				$(':checkbox[name="inp"]:checked').each(function() {
					var userid = parseInt(($(this).val()).split('*')[0]);//員工編號
				    var username = ($(this).val()).split('*')[1];//員工姓名						
					userIds.push(userid);//儲存員工編號
					if(realUser==null){
						realUser=userid;
					}else{
						realUser=realUser+','+userid;
					}
					if (name == null) {
						name = "<div><input type='checkbox' name='userKKK' disabled='disabled' checked='checked' value='"+userid+'*'+username+"'/>"+ username+ "</div>";       
						name2 ="<label><input type='checkbox' name='adminDiv' value='"+userid+'*'+username+"'>"+ username+ "<br></label>";
					}else {	
						name = name + "<div><input type='checkbox' name='userKKK' disabled='disabled' checked='checked' value='"+userid+'*'+username+"'/>"+ username+ "</div>";
					    name2 = name2+ "<label><input type='checkbox' name='adminDiv' value='"+userid+'*'+username+"'>"+ username+"</label><br>";
					}
					console.log('empty')
					$(this).prop('disabled',true);					
				});
			
				console.log('=============');
				$("#inem2").append(name)//已邀請區塊
				$("#inadmin").append(name2)//共同管理員
			});

			//-------------取消
			$("#clear").click(function() {
				$("#inem2").empty();
				userIds = [];
				//document.getElementById("inser").value = "加入";
				$("#inem1").empty();
				$("#inadmin").empty();
				realUser=null;
				<c:forEach var="bean" items="${emdep}">					
					$("#inem1").append('<label><input type="checkbox" name="inp" value='+'${bean[0]}'+'*'+'${bean[1]}'+'>'+'${bean[1]}'+'</label><br>')
								
				</c:forEach>

			});
//-------------------------------------------------------------------------------------------------------	
			
//------------------------------------------------------------------------------------------------------------------
	
	

//--------------------依部門分類------------------------------------------------------
			$("#select1").change(function() {
				var holdUser='1';//主揪				
				if($(this).val()=='所有部門'){
					$("#inem1").empty();					
					<c:forEach var="bean" items="${emdep}">
						var i=0;
						$.each(userIds,function(no,vo){
							//排除已邀請的
							if('${bean[0]}'==vo){
								console.log("vo="+vo);
								console.log("holdUser="+holdUser);
								i=1;
							}
						})
						//排除主揪自己
						if(i==0&&'${bean[0]}'!=holdUser){
							$("#inem1").append('<label><input type="checkbox" name="inp" value='+'${bean[0]}'+'*'+'${bean[1]}'+'>'+'${bean[1]}'+'</label><br>')
						}						
					</c:forEach>				
				}else{
					$(":selected").each(function() {				
						var depp= $(this).val();				
						console.log(depp);					
						$.ajax({
							"type":"POST",
							"url":'<c:url value="/SetGroup/showDepEmp.controller"/>',
							"data":{"dep_id":depp},		
							"success":function(data){
								console.log('*************'+data);		
								$("#inem1").empty();
								var i=0;
								$.each(data,function(index,value){//(1,2,3,4,5)									
									$.each(userIds,function(no,vo){//[1,2]
										//排除已邀請的
										if(value[0]==vo){
											i=1;
										}
									})
									//排除主揪自己
									if(i==0&&value[0]!=holdUser){
										$("#inem1").append('<label><input type="checkbox" name="inp" value='+value[0]+'*'+value[1]+'>'+value[1]+'</label><br>')										
									}
									i=0;
								})
							}					
						});
					});					
				}				
			});
			
 							

			//---------------部門分類結束-----------------------------------------------------
			
			
			//---------------------共同管理員----------------------------------------
			var adminIds = null;
			$('#setadmin').click(function() {
				 adminIds = null;
				$(':checkbox[name="adminDiv"]:checked').each(function() {
					var adminid = parseInt(($(this).val()).split('*')[0]);//員工編號
					if(adminIds==null){
						adminIds=adminid;
					}else{
						adminIds=adminIds+','+adminid;
					}
				})
				console.log("ad--"+adminIds);
			});
			
			
			var arr=[];
// 			var enddate = ;
			
		    
			$('#save').click(function() {
				var enddate = $("#enddate").val();;
				var groupna=$("#Tex1").val();
				var ann=$("#Tex2").val();
				arr.push({'store_name':'${sname}' ,'admin_id':adminIds,'user_Ids':realUser,'groupna':groupna,'ann':ann,'enddate':enddate});
				if(!$.isEmptyObject(arr)){
					var jsonString=JSON.stringify(arr);
					console.log(jsonString);
					$.ajax({
						"type":"post",
						"url":'<c:url value="/insertGroupServlet.controller"/>',
						"data":{jsonString},											
						"success":function(){
							alert("55555555");
						}
					});	
				
				};
			
			});
			
			
<!--  -----------------------------------吉米---------------------------------   -->			

$(function(){		
	var i=1;
	var x=1;
	<c:forEach var="itemno" items='${itemnos}'>
		$("#f1").append('<dl><input type="checkbox" name="checkbox" value="'+${itemno}+'"/><span>${itemnames[itemno]}<span/><span id="it'+i+'"></span><dl/>');
		$("#it"+i).append('<dt><span>SIZE<span/><dd id="size'+i+'">');
		<c:forEach var="sizepriceList" items='${sizeprices[itemno]}'>
			$("#size"+i).append('<span>${sizepriceList.key}<span/><span class="text">${sizepriceList.value}<span/><br>');
		</c:forEach>
		<c:forEach var="class2List" items='${class2Lists}'>
			<c:forEach var="class2" items='${class2List[itemno]}'>
				$('#it'+i).append('<dt><span>${class2}<span/><dd id="c2'+x+'">');
				<c:forEach var="class3List" items='${class3Lists}'>
					<c:forEach var="class3" items='${class3List[itemno][class2]}'>
					$('#c2'+x).append('<span>${class3}<span/>');
					</c:forEach>
				</c:forEach>
			x++;
			</c:forEach>
		</c:forEach>
		i++;
	</c:forEach>
})


$('#save').click(function(){
	var xml = new XMLHttpRequest();
	var itemno=[];
	$(":input:checkbox:checked").each(function(){
			console.log($(this).val());
		itemno.push($(this).val());
		console.log(itemno);
	})
	
	xml.onreadystatechange = function(){
		if (xml.readyState == 4) {
			if (xml.status == 200){
				
			}
		}
	}
//			var storeno=${store_no};
		
		xml.open("get", "/projHY20160201/CreateGroupservlet.controller?itemno="+ itemno + "&store_no="+${store_no}, true);//傳值給StoreServlet
		xml.send();	
})
		</script>
		<!-- 	載入底部 -->
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>