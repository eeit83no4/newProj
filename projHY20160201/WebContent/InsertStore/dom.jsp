<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="radio" name="first" value="飲料" id="radio1" checked="checked" />飲料
  		<input type="radio" name="first" value="便當" id="radio2"/>便當
  		<input type="radio" name="first" value="甜點" id="radio3"/>甜點
  		<input type="radio" name="first" value="其他" id="radio4"/>其他
  		<input type="button" id="but"/>
	</body>
	<script>
		$(function(){
			$('#but').click(function(){
// 				alert($('#radio1').prop('checked'))
				$('#radio2').prop('checked',true)
			})
		})
	</script>
</html>