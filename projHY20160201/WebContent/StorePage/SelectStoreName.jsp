<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<style type="text/css">
#allstore{
width:400px;
height:300px;
border: 3px green double; 
overflow:scroll;
float:left
}
#mystore{
border: 3px green double; 
margin-left:420px;
width:400px;
overflow:scroll;
}
</style>
</head>
<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<div id="allstore">	
		<div>
			<input type="text" id="txtSearch" name="keyword" autocomplete="off">
		</div>
		<div id="div1"></div>
	</div>
	<!-- ------------------- -->
	<div id="mystore">	
		<div>
			<input type="text" id="sotretext" name="mystoreneme" autocomplete="off">
		</div>
		<div id="div2"></div>
	</div>	
	<!-- 判斷使用者是否有登入 -->
<c:if test="${empty LoginOK}">
	<c:redirect url="index.jsp"/>
</c:if>
<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>
<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script>
	//將資料存到陣列中
	var datas = [ '' ];
	var storedatas = [ '' ];
	var show;
	var showmystore;
	window.addEventListener("load", init, false);
	function init() {
		var txt = document.getElementById("txtSearch");
		txt.addEventListener("keyup", getData, false);
		show = document.getElementById("div1");	
		var sotretext = document.getElementById("sotretext");
		sotretext.addEventListener("keyup", getData, false);
		showmystore = document.getElementById("div2");
		
		getData();
	}

//------------------------------------------------------------------------------------------------------------------
	
	function getData() {
		show.style.display = "block";
		if (show.childNodes.length > 0 || showmystore.childNodes.length > 0) {  //清除舊的資料
			show.removeChild(show.childNodes[0]);
			showmystore.removeChild(showmystore.childNodes[0]);
		}
		var xml = new XMLHttpRequest();
		var Search = document.getElementById("txtSearch").value;
		var SearchStore = document.getElementById("sotretext").value;
			xml.onreadystatechange = function() {
				if (xml.readyState == 4) {
					if (xml.status == 200) {
						datas = JSON.parse(xml.responseText);
						var eleDiv = document.createElement("div");
						//JSON 取出 店家名稱 例:{list:[店家1,店家2,店家3],mylists:[店家2,店家3]}
						for (var j = 0; j < (datas.list.length); j++) {  
							var txtBtn = document.createTextNode(datas.list[j][1]);
							var p=document.createElement("p");
							var eleBtn = document.createElement("a");
							eleBtn.setAttribute("style","text-decoration:none");
							//改呼叫Servlet
							eleBtn.setAttribute("href","/projHY20160201/OpenStoreForGroupServlet.select?store_no="+datas.list[j][0]);
							eleBtn.appendChild(txtBtn);
							p.appendChild(eleBtn);
							eleDiv.appendChild(p);							
						}
						show.appendChild(eleDiv);
						var eleDivX = document.createElement("div");
						for (var i = 0; i < (datas.mylists.length); i++) { //陣列 取出我新增的 店家名稱
							var txtBtn1 = document.createTextNode(datas.mylists[i][1]);
							var p=document.createElement("p");
							var eleBtn = document.createElement("a");
							eleBtn.setAttribute("style","text-decoration:none");
							eleBtn.setAttribute("href","/projHY20160201/OpenStoreForGroupServlet.select?store_no="+datas.mylists[i][0]);
							eleBtn.appendChild(txtBtn1);
							var upBtn = document.createElement("input");
							upBtn.setAttribute("type","button");
							upBtn.setAttribute("value","修改");
							upBtn.setAttribute("onclick","updata("+datas.mylists[i][0]+")");
							var dlBtn = document.createElement("input");
							dlBtn.setAttribute("type","button");
							dlBtn.setAttribute("id",datas.mylists[i][0]);
							dlBtn.setAttribute("value","刪除");
							dlBtn.setAttribute("class","btn btn-default btn-xs");
							dlBtn.setAttribute("onclick","if(confirm('確定要刪除 :"+datas.mylists[i][1]+" 嗎?'))dldata("+datas.mylists[i][0]+")");
// 							dlBtn.setAttribute("onclick","dldata("+datas.mylists[i][0]+")");
							p.appendChild(eleBtn);
							p.appendChild(upBtn);
							p.appendChild(dlBtn);
							eleDivX.appendChild(p);							
						}
						showmystore.appendChild(eleDivX);
					}
				}
			}
		xml.open("get", "/projHY20160201/StoreServlet.select?keyword=" + Search +"&mystoreneme="+SearchStore, true);//傳值給StoreServlet
		xml.send();
	}
	
	function updata(upstore_no){
		location.href='<c:url value="/insertStoreAction?sub="/>'+upstore_no;
	}
	function dldata(dlstore_no){
		var xml = new XMLHttpRequest();
		$("#"+dlstore_no).parent("p").remove();
		xml.open("get", "/projHY20160201/UpstoreDeletestore.controller?dlstore_no="+dlstore_no, true);//傳值給StoreServlet
		xml.send();
	}
	//------------------------------------------------------------------------------------------------------------------
</script>

</body>
</html>