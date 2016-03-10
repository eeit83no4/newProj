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

#Div1 {
	color: #D87093;
	font-size: xx-large;
	text-align: center;
	font-weight: bolder;
	width: auto;
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
	text-align: center;
	border: 1px solid #00AAAA;
	width: auto;
}
#inem1 {
	 overflow:scroll;
	 height:400px;
}
#inem2 {
	border: 2px solid #00AAAA;
	width: auto;

	 height:250px;
	 overflow:scroll;
}
#money{
    margin-top: 250px;



}

#but {
	margin-left: 350px;
	margin-right: 400px;
	width: 1px;
	hight: 1px;
}

td, th {
	font-size: 15px;
	font-weight: bold;
	margin: 0px;
	border: 3px solid #AAAAAA;
	width: 1200px;
	border:
}

#Tex1 {
	width: 700px;
}

#Td1 {
	color: red;
}
</style>

<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">

</style>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>


<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/themes/hot-sneaks/jquery-ui.css"
	rel="stylesheet">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>

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
		<form action="<c:url value=''/>" method="post">
			<div id="LefttDiv">

				<table id="Table1">
					<tr style="border-bottom: 0px">
						<td id=Td1 style="border-bottom: 0px">團購名稱:</td>
						<td class="tt"><input type="text" id="Tex1" required="required"></td>
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
										<!-- 排除主揪  -->
										<c:set var='userID' value='${LoginOK.user_id}'/>										
										<c:if test='${bean[0]!=userID}'>
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
								
								
								<div id="admin1">共同管理員:</div>
								<div id="admin2">
							    
							
								</div>
								
															
							</div>

							<div id="but">
								<input type="button" class='btn btn-primary' id="inser"
									value="加入" /> <br> <br> <input type="reset"
									class='btn btn-primary' id="clear" value="取消" />
									
							</div>
							<div id="money">							 							
							<label><input type="radio" name="gender" id="havemon">是</label>
							<label><input type="radio" name="gender" id="havemon2">否</label>
				             <span>運費:</span><input type="text" id="gold" style="width:50px"><br>
				             </div>
				             
				             <div id="money2">
							
							</div>
							
						</td>
					</tr>

					<tr>
						<td>公告:</td>
						<td style="border-left: 0px" ><input type="text" id="Tex2"
							style=" width:800px"  name="ann"></td>
					</tr>
	
					
					
				</table>
			</div>
			<input type="button" class='btn btn-primary' id="save" value="送出">
			<input type="button" class='btn btn-primary' value="新增共同管理員"
				data-title="Edit" data-toggle="modal" data-target="#newadmin">
		</form>

		<!--   ------------------------ 我         是         彈         跳         視      窗  --------------------------      -->

		<div class="modal fade" id="newadmin" tabindex="-1" role="dialog"
			aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
										
					<h4 class="modal-title custom_align" id="Heading">選擇共同管理者</h4>
					</div>

					<form action="" method="post">
						<div class="modal-body" id="inadmin"></div>
						<div class="modal-footer ">
							<button type="button" class="btn btn-warning btn-lg" class="close" 
							aria-hidden="true" style="width: 100%;" data-dismiss="modal" id="setadmin">
								<span class="glyphicon glyphicon-ok-sign" ></span>確認
								
								
<!-- 								<button type="button" class="close"  style="width: 100%;class="btn btn-warning btn-lg" data-dismiss="modal" -->
<!-- 							aria-hidden="true"><span class="glyphicon glyphicon-ok-sign" >關閉</span> -->
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
			holdUser='${LoginOK.user_id}';//主揪
			var userIds = [];//已經邀請的，比對用
			var realUser=null;//已經邀請的，傳值用
			//-------------加入團員
		  $("#inser").click(function() {
				var name = null;//已邀請區塊
				var name2 = null;//共同管理員
				var newUser=false;
				$(':checkbox[name="inp"]:checked').each(function() {
					var userid = parseInt(($(this).val()).split('*')[0]);//員工編號
				    var username = ($(this).val()).split('*')[1];//員工姓名
				    var isUserAlreadyInvited=false;
				    //判斷是否已經被邀請
					$.each(userIds,function(index,value){
						if(userid==value){
							isUserAlreadyInvited=true;
						}
					})
					if(isUserAlreadyInvited==false){//員工還沒有被邀請
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
						newUser=true;
					}
				});			
				console.log('=============');
				if(newUser){
					$("#inem2").append(name)//已邀請區塊
					$("#inadmin").append(name2)//共同管理員
				}				
			});
		//---------------------共同管理員----------------------------------------
			var adminIds = null;
			var adminnas = [];//已經邀請的，比對用
			$('#setadmin').click(function() {
				var anames =null;			
				var newAdmin=false;
				var adminnames=null;
				$(':checkbox[name="adminDiv"]:checked').each(function() {
					var adminid = parseInt(($(this).val()).split('*')[0]);//員工編號
					var anames = ($(this).val()).split('*')[1];//員工姓名
					var isAdminAlreadyInvited=false;
					if(adminIds==null){
						adminIds=adminid;
					}else{
						adminIds=adminIds+','+adminid;
					}
				$.each(adminnas,function(index,value){
					if(anames==value){
						isAdminAlreadyInvited=true;
						}
					})
				if(isAdminAlreadyInvited==false){ //員工還沒有被邀請
					adminnas.push(anames);	
					if(adminnames==null){
						adminnames=anames;
					}else{
						adminnames=adminnames+anames;
					}
					$(this).prop('disabled',true);
				  newAdmin=true;
					  }
				})
				console.log("ad--"+adminIds);
				if(newAdmin){
				$("#admin2").append(adminnames)//共同管理員
				}
				
				
			});
			
			

			//-------------取消
			$("#clear").click(function() {
				$("#inem2").empty();
				userIds = [];
				//document.getElementById("inser").value = "加入";
				$("#inem1").empty();
				$("#inadmin").empty();
				$("#admin2").empty();
				realUser=null;
				<c:forEach var="bean" items="${emdep}">
					<c:set var='userID' value='${LoginOK.user_id}'/>
					<c:if test='${bean[0]!=userID}'>
						$("#inem1").append('<label><input type="checkbox" name="inp" value='+'${bean[0]}'+'*'+'${bean[1]}'+'>'+'${bean[1]}'+'</label><br>')
					</c:if>			
				</c:forEach>

			});
//-------------------------------------------------------------------------------------------------------	
			
//--------------------依部門分類------------------------------------------------------
			$("#select1").change(function() {
								
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
			
		   //----------------運     費 ------------------------------------------------------------
			
	      $("#havemon").click(function(){
	    	  
	    	  $("#money2").append(  	      	
	  	    	    '<label><input type="radio" name="gender" value="人頭分攤">'+'人頭分攤'+'</label>'+
	  				'<label><input type="radio" name="gender" value="數量分攤">'+'數量分攤'+'</label>'+
	  				'<label><input type="radio" name="gender" value="主揪自己吸收">'+'主揪自己吸收'+'</label>')
	  				$(this).prop('disabled',true);
	  				
	      })
			$("#havemon2").click(function(){
				$("#havemon").prop('disabled',false);
				 $("#money2").empty(); 	 
				
				
			})
			
			  
			  
			 
			
			
			
			
			
			var arr=[];//要傳送過去的JSON
			
		    //-----------------------發起團購
			$('#save').click(function() {
				var enddate = $("#enddate").val();
				var groupna=$("#Tex1").val();
				var ann=$("#Tex2").val();
				var gold1=$("#gold").val();
				
			    var gold=$(':radio[name="gender"]:checked').val()+'('+gold1+')';
			    
				if(adminIds==null){
					adminIds="0";
			   }
								
			
			if(groupna==0 || enddate==0){
				alert("請輸入團購名稱及截止日");
			}else if(groupna!=0 || enddate!=0){ 
				realUser=realUser+','+holdUser;
				arr.push({'store_name':'${sname}' ,'admin_id':adminIds,'user_Ids':realUser,'groupna':groupna,'ann':ann,'enddate':enddate,'store_no':'${store_no}','holdUser':holdUser,'shipment':gold});
				if(!$.isEmptyObject(arr)){
				var jsonString=JSON.stringify(arr);
			    console.log(jsonString);
			    $.ajax({
						"type":"post",
						"url":'<c:url value="/insertGroupServlet.controller"/>',
						"data":{jsonString},											
						"success":function(){
							alert("success");
							location.href='/projHY20160201/index/indexServlet.controller';
						}
					});	
				
				};}
			
			
			 
				
			
			});
		
			
			
		
		</script>
		<!-- 	載入底部 -->
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>