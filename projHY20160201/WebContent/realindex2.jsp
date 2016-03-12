<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鴻揚科技有限公司-團購系統</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<style>
.Group{
	width:445px;
	height:72px;
	border:1px solid transparent;
}
.GroupLeft{
	float:left;
	width:70px;
}
.userNumber{
	margin-bottom:1px;
	background-color:#ffff88;
	font-size:20px;
	text-align:center;
}
.amount{
	background-color:#dddddd;
	text-align:center;
}
.manageGroup{
	font-size:10px;
}
.GroupRight{
	margin-left:75px;
	text-align:left;
	font-size:15px;
}
.divBlock{
	height:7px;
}
.scrollable{
	overflow:scroll;
	height:280px;
}
</style>
</head>
<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<!-------------------------------- 進行中的團購 ------------------------------------------->
	<div style='float:left;width:445px'>
		<h1>進行中的團購</h1>
		<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
		<c:if test="${!empty ingGroups}">
			<div class='scrollable'>
				<c:forEach var='ingGroup' items='${ingGroups}'>
					<div class='Group'>
						<div class='GroupLeft'>
							<div class='userNumber'>${ingGroup.usersNumber}</div>
							<div class='amount'>$${ingGroup.amount}</div>
						</div>					
						<div class='GroupRight'><a href='<c:url value="/MyGroup/group_detail.controller?xxx="/>${ingGroup.groupNo}'>${ingGroup.holderName}發起的${ingGroup.groupName}</a>→<a href='<c:url value="/userOrder/showItemsAction.action?groupno="/>${ingGroup.groupNo}'>我也要訂</a></div>			
					</div>
					<div class='divBlock'></div>
				</c:forEach>
			</div>
		</c:if>
		<!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->			
	</div>
	<!------------------------------ 最新公用店家 ---------------------------------------->
	<div style='float:right;width:150px'>
		<h3>最新公用店家</h3>
		<div>
			<c:forEach var='latestStore' items='${latestStores}' begin='1' end='8'>
				<a href='<c:url value="/userOrder/holdGroupServlet.controller?storeNo="/>${latestStore.store_no}'>${latestStore.store_name}</a><br/>
			</c:forEach>			
		</div>
		<a href='<c:url value="/userOrder/holdGroupServlet.controller"/>'>更多...</a><br/>
	</div>	
	<!-------------------------------- 我發起的團購 ----------------------------------------->
	<c:if test='${!empty myGroups}'>
		<div style='margin-left:455px;margin-right:160px'>
			<h1>我發起的團購</h1>
			<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
			<div class='scrollable'>			
				<c:forEach var='myGroup' items='${myGroups}'>
					<div class='Group'>
						<div class='GroupLeft'>
							<div class='userNumber'>${myGroup.usersNumber}</div>
							<div class='amount'>$${myGroup.amount}</div>									
						</div>
						
						<div class='GroupRight'><a href='<c:url value="/MyGroup/group_detail.controller?xxx="/>${myGroup.groupNo}'>我發起的${myGroup.groupName}</a>→<a href='<c:url value="/userOrder/showItemsAction.action?groupno="/>${myGroup.groupNo}'>我也要訂</a></div>			
					</div>
					<div class='divBlock'></div>
				</c:forEach>
			</div>			
			<!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->			
		</div>
	</c:if>	
	
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
</div>
</body>
</html>