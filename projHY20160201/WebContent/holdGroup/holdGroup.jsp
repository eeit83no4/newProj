<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link type="text/css" rel="stylesheet" href="../css/table2.css" />

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
table,tr,td{
	text-align:center;
	font-size: 15px;
}
#holdGroup{
	margin:5px
}
#latestStore{
	width:600px
}
#sotre_NO{
	display:none
}
#sotreinfo{
	float:left;
	width:380px;
	margin-left:230px
}
#sotreitem{
	margin-left:620px
}
</style>
</head>
<body class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<!-- AutoComplete -->
	<div id="tabs" style='width:640px;margin:0 auto'>
	  <ul>
	    <li><a href="#tabs-1">最新店家</a></li>
	    <li><a href="#tabs-2">找一下</a></li>	    
	  </ul>
	  <div id="tabs-1"><!-- 最新店家 -->
	    <table id='latestStore'>
			<tr>
				<c:forEach var="eachStore" items="${allStoresTiemSorted}" begin="1" end="8">					
						<td><a href="#" id='${eachStore.store_no}'>${eachStore.store_name}</a></td>					
				</c:forEach>
			</tr>
		</table>
	  </div>
	  <div id="tabs-2"><!-- 找一下 -->
	    <div class="ui-widget">
		  <label for="tags">Tags: </label>
		  <input id="tags">
		</div>
	  </div>	  
	</div>
	<!--     --------------------     	彈跳視窗 -------------------------->
	<div id="dialog" title="Basic dialog"> 
	  <iframe id="iframe" width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" 
	   src=""></iframe>
	</div>
	<!-- 發起團購按鈕 -->
	<div style='text-align:center'>
		<input type="button" class="btn btn-primary" value="發起團購" id="holdGroup"/>
	</div>
	
	<c:choose>
		<c:when test="${!empty singleStore}">
			<!-- 載入singleStoretable -->
			<jsp:include page="tableSingleStore.jsp"/>
		</c:when>
		<c:otherwise>	
			<!-- 載入allStoretable -->
			<jsp:include page="tableAllStore.jsp"/>
		</c:otherwise>
	</c:choose>
	
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>
<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>
	$(function(){
		//---------autoComplete
		var availableTags=[];
		<c:forEach var="eachStore" items="${allStores}">
			<c:set var="storeNo" value="${eachStore.store_no}"/>
			availableTags.push('${storeNo}號,${eachStore.store_name}');	
		</c:forEach>		
		
	    $( "#tags" ).autocomplete({
	      source: availableTags,
	      
	    });
	    $('#tags').change(function(){
	    	var storeNofromAuto=parseInt(($(this).val().split(',')[0]).match(/[0-9]+/g));//商店編號
// 	    	console.log(storeNofromAuto);	    	
	    	<c:forEach var="eachStore" items="${allStores}">							
				<c:set var="storeNo" value="${eachStore.store_no}"/>
				if(storeNofromAuto=='${storeNo}'){
					$('#sotreinfo').empty();
					$('#sotreitem').empty();
					<c:if test='${!empty allSotreitems[storeNo]}'>
						$('#sotreitem').append($('<tr/>').prepend($('<td/>').text('商品清單')).append($('<td/>').text('價格')))
					</c:if>
					//------店家資訊
					$('#sotreinfo').prepend($('<tr/>').append($('<td/>').attr('colspan','2').text('店家資訊')))
					.append($('<tr/>').prepend($('<td/>').text('店名')).append($('<td/>').text('${eachStore.store_name}')))
					.append($('<tr/>').prepend($('<td/>').text('地址')).append($('<td/>').append($('<a/>').attr('href','#').attr('id','opener').text('${eachStore.address}'))))
					.append($('<tr/>').prepend($('<td/>').text('電話')).append($('<td/>').text('${eachStore.phone}')))
					.append($('<tr/>').prepend($('<td/>').text('擁有者')).append($('<td/>').text('${eachStore.employeeVO.name}')))
					.append($('<tr/>').prepend($('<td/>').text('店家類型')).append($('<td/>').text('${allStoreClass[storeNo]}')))
					.append($('<tr/>').prepend($('<td/>').attr('id','sotre_NO').text('${storeNo}')))
					//------------店家商品
					<c:forEach var="storeItems" items="${allSotreitems[storeNo]}">		
						$('#sotreitem').append($('<tr/>').prepend($('<td/>').text('${storeItems[0]}')).append($('<td/>').text('${storeItems[1]}')))
					</c:forEach>					
						
					//--------------彈跳視窗	
					$( "#opener" ).click(function() {
				    	var add=$(this).text();
				    	$('#iframe').attr('src','http://maps.google.com.tw/maps?f=q&hl=zh-tw$geocode=&q='+add+'&z=16&output=embed&t=')
				      $( "#dialog" ).dialog( "open" );
				      
				    });						
				}										
			</c:forEach>
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    })
		//-------------tab
		$( "#tabs" ).tabs({
			heightStyle: "content"
		});
		//--------------彈跳視窗，秀出googlemap
		$( "#dialog" ).dialog({
	      autoOpen: false,
	      width:640,
	      height:480
	    });
	 
	    $( "#opener" ).click(function() {
	    	var add=$(this).text();
	    	$('#iframe').attr('src','http://maps.google.com.tw/maps?f=q&hl=zh-tw$geocode=&q='+add+'&z=16&output=embed&t=')
	      $( "#dialog" ).dialog( "open" );
	      
	    });
		//---------按下店家時改變資料
		<c:forEach var="eachStore" items="${allStores}">
			$('#${eachStore.store_no}').click(function(){				
				<c:set var="storeNo" value="${eachStore.store_no}"/>
					$('#sotreinfo').empty();
					$('#sotreitem').empty();
					<c:if test='${!empty allSotreitems[storeNo]}'>
						$('#sotreitem').append($('<tr/>').prepend($('<td/>').text('商品清單')).append($('<td/>').text('價格')))
					</c:if>
					
					//------店家資訊
					$('#sotreinfo').prepend($('<tr/>').append($('<td/>').attr('colspan','2').text('店家資訊')))
					.append($('<tr/>').prepend($('<td/>').text('店名')).append($('<td/>').text('${eachStore.store_name}')))
					.append($('<tr/>').prepend($('<td/>').text('地址')).append($('<td/>').append($('<a/>').attr('href','#').attr('id','opener').text('${eachStore.address}'))))
					.append($('<tr/>').prepend($('<td/>').text('電話')).append($('<td/>').text('${eachStore.phone}')))
					.append($('<tr/>').prepend($('<td/>').text('擁有者')).append($('<td/>').text('${eachStore.employeeVO.name}')))
					.append($('<tr/>').prepend($('<td/>').text('店家類型')).append($('<td/>').text('${allStoreClass[storeNo]}')))
					.append($('<tr/>').prepend($('<td/>').attr('id','sotre_NO').text('${storeNo}')))
					//------------店家商品
					<c:forEach var="storeItems" items="${allSotreitems[storeNo]}">		
						$('#sotreitem').append($('<tr/>').prepend($('<td/>').text('${storeItems[0]}')).append($('<td/>').text('${storeItems[1]}')))
					</c:forEach>
					
						
					//--------------彈跳視窗	
					$( "#opener" ).click(function() {
				    	var add=$(this).text();
				    	$('#iframe').attr('src','http://maps.google.com.tw/maps?f=q&hl=zh-tw$geocode=&q='+add+'&z=16&output=embed&t=')
				      $( "#dialog" ).dialog( "open" );
				      
				    });
			})
		</c:forEach>
		//--------------按下發起團購-----------------
		$('#holdGroup').click(function(){
			var storeNumber=$("#sotre_NO").text();
			console.log(storeNumber);
		})
		
		
	})




</script>
</body>
</html>