<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- add new item Dynamically in the show block -->
  <div id="showBlock"></div>
  <!-- click the button to add new item -->
  <a type="button" id="btn" value="addItem" >+</a>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
  //set the default value
  var txtId = 1;
  
  //add input block in showBlock
  $("#btn").click(function () {
      $("#showBlock").append('<input type="checkbox" id="div' + txtId + '" name="cbtxt' + txtId + '" /><input type="text" id="div' + txtId + '" name="cbtxt' + txtId + '" style="width: 50px"/><input type="button" id="div' + txtId + '" value="del" onclick="deltxt('+txtId+')">');
      //<div id="div' + txtId + '"></div>
      txtId++;
  });
  //remove div
  function deltxt(id) {
	  for(i=1;i<4;i++){
      $("#div"+id).remove();
	  }
  }
</script> 
</body>
</html>