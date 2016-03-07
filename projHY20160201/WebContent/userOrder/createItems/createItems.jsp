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
			<c:if test="${!empty itemVOs}">				
				<div>
					<c:forEach var='itemVO' items='${itemVOs}'>
						<font><a href='#' class='${itemVO.item_no}' >${itemVO.item_name}</a></font>&nbsp;&nbsp;
					</c:forEach>					
				</div>				
			</c:if>
			<!--  -->
			<form enctype="multipart/form-data" action='<c:url value="/newStore/createItemServlet.controller"/>' method='post'>
				<input type='text' name='storeNo' id='storeNo' value='${storeNo}' style='display:none'>
				<c:choose>
					<c:when test="${!empty itemVOs}">
						<jsp:include page="oldStoreInclude.jsp"/>
					</c:when>
					<c:otherwise>
						<jsp:include page="newStoreInclude.jsp"/>
					</c:otherwise>
				</c:choose>
<%-- 				<c:forEach var='itemVO' items='${itemVOs}' begin='1' end='1'> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${!empty itemNO}"> --%>
<%-- 							<c:set var='itemNo' value='${itemNO}'/> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<c:set var='itemNo' value='${itemVO.item_no}'/> --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose>	 --%>
<%-- 					<span>商品名稱：</span><input type='text' name='itemName' size='20' required="required" value='${itemName[itemNo]}'/><span class='required'>${errorMsg.noItemName}(必填)</span><br/> --%>
<!-- 					<span>商品類型：</span> -->
<!-- 					<label><input type='radio' name='itemClass1st' value='飲料' checked='checked'>飲料</label> -->
<!-- 					<label><input type='radio' name='itemClass1st' value='便當'>便當</label> -->
<!-- 					<label><input type='radio' name='itemClass1st' value='甜點'>甜點</label>				 -->
<!-- 					<label><input type='radio' name='itemClass1st' value='其他'>其他</label><br/> -->
<!-- 					商品大小價錢區 -->
<%-- 					<span>商品價錢：</span><input type='text' size='60' name='SizePrice' required="required" value='${itemsSize[itemNo]}'/><span class='required'>(必填)</span><br/> --%>
<%-- 					<span class='required'>${errorMsg.noSizePrice}</span><br/> --%>
<!-- 					商品照片 -->
<!-- 					<span>商品照片：</span><input type="file" name='itemPic' accept="image/*" /><hr/> -->
<!-- 					第二第三層屬性區 -->
<!-- 					<span>商品細項(單選)：</span><br/> -->
<%-- 					<textarea name='class2class3' rows="4" cols="50">${itemsc2c3[itemNo]}</textarea><hr/> --%>
<!-- 					加料區 -->
<!-- 					<span>加料(複選)：</span><br/> -->
<%-- 					<textarea name='extraStuff' rows="4" cols="50">${itemsextra[itemNo]}</textarea><br/> --%>
<%-- 				</c:forEach> --%>
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
	    $('a').click(function(){
	    	location.href='/projHY20160201/newStore/showItemServlet.controller?itemNO='+$(this).attr('class')+'&storeNo='+$('#storeNo').val();
	    })
	    
	    
	    
	    
	})

</script>



</body>
</html>