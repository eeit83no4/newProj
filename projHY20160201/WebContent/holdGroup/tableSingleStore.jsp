<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 店家資訊與商品 -->
	<div>
		
			<table id="sotreinfo" class="table table-hover table-bordered table table-condensed">
				<c:set var="storeNo" value="${singleStore.store_no}"/>
				<tr>
					<td colspan="2">店家資訊</td>					
				</tr>
				<tr>
					<td>店名	</td>
					<td>${singleStore.store_name}</td>
				</tr>
				<tr>
					<td>地址	</td>
					<td><a href='#' id="opener">${singleStore.address}</a></td>
				</tr>
				<tr>
					<td>電話</td>
					<td>${singleStore.phone}</td>
				</tr>
				<tr>
					<td>擁有者</td>
					<td>${singleStore.employeeVO.name}</td>
				</tr>
				<tr>
					<td>店家類型</td>
					<td>${allStoreClass[storeNo]}</td>
				</tr>
				<tr>
					<!-- 商店編號 -->					
					<td id="sotre_NO">${storeNo}</td>
				</tr>
			</table>
			<table id="sotreitem" class="table table-hover table-bordered table table-condensed">
				<tr>
					<td>商品清單</td>
					<td>價格</td>
				</tr>
				<c:forEach var="storeItems" items="${allSotreitems[storeNo]}">
					<tr>
						<td>${storeItems[0]}</td>
						<td>${storeItems[1]}</td>
					</tr>
				</c:forEach>
			</table>
			
	</div>