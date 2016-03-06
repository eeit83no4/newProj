<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<style>
#leftDiv{
	border:1px solid gray;	
	width:500px;
	float:left;	
}
.required{
	color:red;
}
#newItemButton{
	text-align:center;
}
#ManualDiv{
	border:1px solid red;
	margin-left:510px;
}
.dialogDiv{
	text-align:center;
}
</style>
</head>
<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	
		<!--  -->
		<div id='leftDiv'>
			<h2>商品設定</h2>
			<!-- 商品名稱 -->
			<div>
				<font><a href='#'>紅茶</a></font>&nbsp;&nbsp;
				<font><a href='#'>奶茶</a></font>&nbsp;&nbsp;
				<font><a href='#'>綠茶</a></font>&nbsp;&nbsp;
				<font><a href='#'>青茶</a></font>&nbsp;&nbsp;
			</div>
			<!--  -->
			<form enctype="multipart/form-data" action='<c:url value="/newStore/createItemServlet.controller"/>' method='post'>
				<input type='text' name='storeNo' value='${storeNo}' style='display:none'>
				<span>商品名稱：</span><input type='text' name='itemName' size='20' required="required"/><span class='required'>${errorMsg.noItemName}(必填)</span><br/>
				<span>商品類型：</span>
				<label><input type='radio' name='itemClass1st' value='飲料' checked='checked'>飲料</label>
				<label><input type='radio' name='itemClass1st' value='便當'>便當</label>
				<label><input type='radio' name='itemClass1st' value='甜點'>甜點</label>				
				<label><input type='radio' name='itemClass1st' value='其他'>其他</label><br/>
				<!-- 商品大小價錢區 -->
				<span>商品價錢：</span><input type='text' size='60' name='SizePrice' required="required"/><span class='required'>(必填)</span><br/>
				<span class='required'>${errorMsg.noSizePrice}</span><br/>
				<!-- 商品照片 -->
				<span>商品照片：</span><input type="file" name='itemPic' accept="image/*" /><hr/>
				<!-- 第二第三層屬性區 -->
				<span>商品細項(單選)：</span><br/>
				<textarea name='class2class3' rows="4" cols="50"></textarea><hr/>
				<!-- 加料區 -->
				<span>加料(複選)：</span><br/>
				<textarea name='extraStuff' rows="4" cols="50"></textarea><br/>
				<!--  -->
				<div id='newItemButton'>
					<input type='submit' value='新增商品'>
				</div>
				<!--  -->
				<div id='newItemButton'>
					<input type='button' id='opener' value='商品全部新增完成'>
				</div>
				<!-- 確認完成的彈跳視窗 -->
				<div id="dialog" title="Basic dialog">
					<div class='dialogDiv'>
  						<h3>你確定全部新增完成?</h3><br/>  					
  						<input type='button' value='確定'/>
  						<input type='button' value='還沒完成'/>
  					</div>
				</div>
				<!-- 結束確認完成的彈跳視窗結束 -->								
			</form>			
		</div>
		<!-- 使用說明區域 -->		
		<div id='ManualDiv'>
			<h4>怎麼寫?</h4>
		</div>
	
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>
<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>
	$(function(){
		var storeNo='${storeNo}';//店家編號
		//-----彈跳視窗
	    $( "#dialog" ).dialog({
	        autoOpen: false,	        
	    });	   
	    $( "#opener" ).click(function() {
	      $( "#dialog" ).dialog( "open" );
	    });
	    //----------
	})

</script>



</body>
</html>