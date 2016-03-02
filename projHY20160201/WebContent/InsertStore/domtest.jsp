<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="div1"></div>
<input type="button" id="bt" value="BT"/>



<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script>
	$(function(){
		$('#bt').click(function(){
			$('#div1').append(
					$('<div/>').text('xxxxx').append(
							$('<div/>').text('aaaa')));
// 			$('#div1').append($('<input/>').attr("type","checkbox")
// 										   .prop("checked",true));
// 			.attr("type","checkbox")
											
		})	
		
		
	})



</script>
</body>
</html>