<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鴻揚科技有限公司-團購系統</title>
</head>
<body>


<script>

	window.onload=function(){
		var groupNo='${groupNo}';
		location.href='/projHY20160201/module.controller.group/updateAfterDeleteServlet.delete?groupNo='+groupNo;
	}


</script>
</body>
</html>