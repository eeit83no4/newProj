<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

	<div id="header"> <img src="${pageContext.request.contextPath}/images/logo.png" />
	<div align="right"><span class="glyphicon glyphicon-user"></span>hello,
		<a style="text-decoration:none;" href="<c:url value='/index/indexServlet.controller'/>"> &nbsp${empname}</a>
	</div>
	    <div id="nav">
	      <ul class="menu">	      	
	        <li class="current_page_item"><a style="text-decoration:none;" href="<c:url value='/index/indexServlet.controller'/>">首頁</a></li>
	        <li><a style="text-decoration:none;" href='<c:url value="/userOrder/holdGroupServlet.controller"/>'>發起團購</a></li>
	        <li><a style="text-decoration:none;" href="<c:url value='/StorePage/SelectStoreName.jsp'/>" >店家資訊</a></li>
	        <li><a style="text-decoration:none;" href="#">我的團購</a>
	        <ul class="sub-menu">
	            <li><a href="<c:url value='/xxx.controller?prodaction=進行中的團購'/>">進行中的團購</a></li>
	            <li><a href="<c:url value='/xxx.controller?prodaction=已完成的團購'/>">已完成的團購</a></li>
	            <li><a href="<c:url value='/xxx.controller?prodaction=個人歷史訂購紀錄'/>">個人歷史訂購紀錄</a></li>
	          </ul></li>
	        <c:if test='${!empty admin}'>
	        	<li><a style="text-decoration:none;" href="<c:url value='/admin.controller?prodaction=主畫面'/>">管理者模式</a></li>
	        </c:if>
	        <li><a style="text-decoration:none;" href="${pageContext.request.contextPath}/signout.jsp">登出</a></li>
	        
	      </ul>
	    </div>
	    <!--end nav-->
	  </div>
	  <!--end header-->


