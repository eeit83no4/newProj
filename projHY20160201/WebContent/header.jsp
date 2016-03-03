<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<div id="header"> <img src="${pageContext.request.contextPath}/images/logo.png" />
	    <div id="nav">
	      <ul class="menu">
	        <li class="current_page_item"><a href="<c:url value='/index/indexServlet.controller'/>">首頁</a></li>
	        <li><a href='<c:url value="/userOrder/holdGroupServlet.controller"/>'>發起團購</a></li>
	        <li><a href="<c:url value='/StorePage/SelectStoreName.jsp'/>">店家資訊</a></li>
	        <li><a href="#">我的團購</a>
	        <ul class="sub-menu">
	            <li><a href="<c:url value='/xxx.controller?prodaction=進行中的團購'/>">進行中的團購</a></li>
	            <li><a href="<c:url value='/xxx.controller?prodaction=已完成的團購'/>">已完成的團購</a></li>
	            <li><a href="<c:url value='/xxx.controller?prodaction=個人歷史訂購紀錄'/>">個人歷史訂購紀錄</a></li>
	          </ul></li>
	        <li><a href="<c:url value='/admin.controller?prodaction=團購維護&uid=1'/>">管理者模式</a></li>
	        <li><a href="${pageContext.request.contextPath}/signout.jsp">登出</a></li>
	      </ul>
	    </div>
	    <!--end nav-->
	  </div>
	  <!--end header-->


