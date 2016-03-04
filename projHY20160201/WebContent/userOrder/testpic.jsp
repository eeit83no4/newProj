<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> --%>
<style>
.thumb{
	height:75px;margin:5px;
}

</style>
</head>
<body>
	
	<div id=dialog title="Basic dialog">
		<form enctype="multipart/form-data" action="" name="myFormElement">
			<span>請選擇圖片:</span><input type="file" accept="image/*" value="UploadPic" onchange="show(this)"/>
			  <br>
			  <input type="checkbox" name="check" value="check1" id="ch1">
			  <label for="ch1">check1</label>
			  <input type="checkbox" name="check" value="check2" checked="checked" id="ch2">
			  <label for="ch2">check2</label>
			<input type="button" name="submit" id="goPic" value="上傳"/>
		</form>
		
		
	</div>
	<div id="showPic" style="border:1px solid black;width:500px">
	
	</div>
	
	<p><tt id="results"></tt></p>
	
<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
	$(function(){
		
	})
	
	var arr=[];		

	function show(obj){
		var thefiles=obj.files;
//  		console.log(obj.files);
		for(var i=0;i<thefiles.length;i++){
			var reader=new FileReader();
			reader.readAsDataURL(thefiles[i]);
			reader.onload=function(c){
				var fileContent=c.target.result;
// 				console.log(fileContent);
				$('#showPic').append($('<img/>').attr('name','imagePic').attr('src',fileContent).attr('class','thumb'));
// 				arr.push(fileContent);
			
			}
		}
	}
	
	var form = $('form')[0]; // You need to use standart javascript object here
	
	
	$('#goPic').click(function(){
		var formData = new FormData(form);
		console.log(formData);
	})
	
// 	  function showValues() {
// 		    var str = $( "form" ).serialize();
// 		    console.log(str);
// 		    $( "#results" ).text( str );
// 	  }
	
// 	  $('#goPic').on('click',showValues);
	
	
	
	
	
// 	$('#goPic').click(function(){
		
// 		if(!$.isEmptyObject(arr)){
// 			var jsonString=JSON.stringify(arr);
// // 			console.log(jsonString);
// 			$.ajax({
// 				"type":"post",				
// 				'url':'<c:url value="/userOrder/insertPicServlet.controller"/>',
// 				"data":{jsonString},
		
// 				'success':function(){
// 					alert('ok');
// 				}
// 			})
// 		}else{
// 			console.log("沒有圖片");
// 		}
// 	})

</script>

</body>
</html>